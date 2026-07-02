package com.vehicle.registry_service.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.vehicle.registry_service.dto.JiraAnalysisResponse;
import com.vehicle.registry_service.exception.GitRepositoryException;

@Service
public class JiraTicketResolverService {

  private static final Logger log = LoggerFactory.getLogger(JiraTicketResolverService.class);

  private static final Pattern ANALYSIS_FILE_PATH_PATTERN =
      Pattern.compile("Analysis Results[\\\\|/]([A-Z]+-\\d+)\\.md");

  public JiraAnalysisResponse triggerJiraTicketAnalyser(String jiraId) {
    log.info("Triggering Jira Ticket Analyser for ID: {}", jiraId);

    try {
      ProcessBuilder processBuilder = new ProcessBuilder("claude", "jira-ticket-analyser", jiraId);
      processBuilder.redirectErrorStream(true);

      Process process = processBuilder.start();

      StringBuilder output = new StringBuilder();
      try (BufferedReader reader = new BufferedReader(
          new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8))) {
        String line;
        while ((line = reader.readLine()) != null) {
          output.append(line).append("\n");
        }
      }

      int exitCode = process.waitFor();
      log.info("Jira Ticket Analyser completed with exit code: {}", exitCode);

      if (exitCode != 0) {
        return handleAgentError(jiraId, output.toString());
      }

      String filePath = extractAnalysisFilePath(output.toString());

      return new JiraAnalysisResponse(
          "success",
          jiraId,
          "Analysis completed successfully",
          filePath,
          System.currentTimeMillis()
      );

    } catch (GitRepositoryException e) {
      log.error("Git repository error for ticket {}: {}", jiraId, e.getMessage());
      throw e;
    } catch (Exception e) {
      log.error("Error triggering Jira Ticket Analyser for ID: {}", jiraId, e);
      throw new RuntimeException("Failed to trigger agent: " + e.getMessage(), e);
    }
  }

  private String extractAnalysisFilePath(String output) {
    Matcher matcher = ANALYSIS_FILE_PATH_PATTERN.matcher(output);
    if (matcher.find()) {
      return "C:\\Users\\2510172\\Documents\\Agents\\Jira Ticket Analyser Agent\\Analysis Results\\"
          + matcher.group(1) + ".md";
    }
    return "Analysis Results file path not found in output";
  }

  private JiraAnalysisResponse handleAgentError(String jiraId, String output) {
    String errorMessage = "Analysis failed";
    String errorDetails = output;

    if (output.toLowerCase().contains("git") || output.toLowerCase().contains("repository")) {
      errorMessage = "Unable to connect to git repository";
      throw new GitRepositoryException(errorMessage + ": " + output);
    }

    if (output.toLowerCase().contains("ticket") && output.toLowerCase().contains("not found")) {
      errorMessage = "Ticket not found. Verify the ticket ID and check the Jira Tickets folder.";
    }

    log.error("Agent error for ticket {}: {}", jiraId, errorMessage);
    throw new RuntimeException(errorMessage + ": " + errorDetails);
  }

}
