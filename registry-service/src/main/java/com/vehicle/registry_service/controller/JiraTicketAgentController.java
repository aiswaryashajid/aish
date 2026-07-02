package com.vehicle.registry_service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vehicle.registry_service.dto.JiraAnalysisErrorResponse;
import com.vehicle.registry_service.dto.JiraAnalysisResponse;
import com.vehicle.registry_service.dto.JiraTicketRequest;
import com.vehicle.registry_service.exception.GitRepositoryException;
import com.vehicle.registry_service.service.JiraTicketResolverService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Tag(name = "Jira Ticket Agent", description = "APIs to trigger Jira ticket resolver agent")
@RestController
@RequestMapping("/api/v1/jira-tickets")
public class JiraTicketAgentController {

  private static final Logger log = LoggerFactory.getLogger(JiraTicketAgentController.class);

  private final JiraTicketResolverService jiraTicketResolverService;

  public JiraTicketAgentController(JiraTicketResolverService jiraTicketResolverService) {
    this.jiraTicketResolverService = jiraTicketResolverService;
  }

  @Operation(summary = "Trigger Jira ticket resolver agent",
      description = "Triggers the Jira ticket resolver agent to analyze a ticket and generate analysis report",
      responses = {
          @ApiResponse(responseCode = "200", description = "Analysis completed successfully",
              content = @Content(mediaType = "application/json",
                  schema = @Schema(implementation = JiraAnalysisResponse.class))),
          @ApiResponse(responseCode = "400", description = "Invalid input or git repository connection failed",
              content = @Content(mediaType = "application/json",
                  schema = @Schema(implementation = JiraAnalysisErrorResponse.class))),
          @ApiResponse(responseCode = "500", description = "Agent execution failed")})
  @PostMapping("/resolve")
  public ResponseEntity<?> resolveJiraTicket(
      @Valid @RequestBody JiraTicketRequest request, HttpServletRequest httpRequest) {

    log.info("Received Jira ticket resolver request for ID: {}", request.getJiraId());

    try {
      JiraAnalysisResponse analysisResponse =
          jiraTicketResolverService.triggerJiraTicketAnalyser(request.getJiraId());

      log.info("Jira ticket resolver agent completed successfully for ID: {}", request.getJiraId());
      log.info("Analysis report generated at: {}", analysisResponse.getAnalysisFilePath());

      return ResponseEntity.status(HttpStatus.OK).body(analysisResponse);

    } catch (GitRepositoryException e) {
      log.warn("Git repository error for ticket {}: {}", request.getJiraId(), e.getMessage());

      JiraAnalysisErrorResponse errorResponse = new JiraAnalysisErrorResponse(
          "error",
          request.getJiraId(),
          "GIT_REPOSITORY_ERROR",
          "Unable to connect to git repository",
          e.getMessage(),
          System.currentTimeMillis()
      );

      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);

    } catch (RuntimeException e) {
      log.error("Error executing Jira ticket resolver for ID: {}", request.getJiraId(), e);

      JiraAnalysisErrorResponse errorResponse = new JiraAnalysisErrorResponse(
          "error",
          request.getJiraId(),
          "ANALYSIS_FAILED",
          "Failed to analyze ticket",
          e.getMessage(),
          System.currentTimeMillis()
      );

      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
  }

}
