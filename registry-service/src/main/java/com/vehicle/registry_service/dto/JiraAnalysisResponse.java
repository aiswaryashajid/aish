package com.vehicle.registry_service.dto;

public class JiraAnalysisResponse {

  private String status;
  private String ticketId;
  private String message;
  private String analysisFilePath;
  private long analysisTimestamp;

  public JiraAnalysisResponse() {
  }

  public JiraAnalysisResponse(String status, String ticketId, String message,
      String analysisFilePath, long analysisTimestamp) {
    this.status = status;
    this.ticketId = ticketId;
    this.message = message;
    this.analysisFilePath = analysisFilePath;
    this.analysisTimestamp = analysisTimestamp;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getTicketId() {
    return ticketId;
  }

  public void setTicketId(String ticketId) {
    this.ticketId = ticketId;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getAnalysisFilePath() {
    return analysisFilePath;
  }

  public void setAnalysisFilePath(String analysisFilePath) {
    this.analysisFilePath = analysisFilePath;
  }

  public long getAnalysisTimestamp() {
    return analysisTimestamp;
  }

  public void setAnalysisTimestamp(long analysisTimestamp) {
    this.analysisTimestamp = analysisTimestamp;
  }

}
