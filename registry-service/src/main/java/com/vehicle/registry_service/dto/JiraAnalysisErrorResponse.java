package com.vehicle.registry_service.dto;

public class JiraAnalysisErrorResponse {

  private String status;
  private String ticketId;
  private String error;
  private String errorMessage;
  private String errorDetails;
  private long timestamp;

  public JiraAnalysisErrorResponse() {
  }

  public JiraAnalysisErrorResponse(String status, String ticketId, String error,
      String errorMessage, String errorDetails, long timestamp) {
    this.status = status;
    this.ticketId = ticketId;
    this.error = error;
    this.errorMessage = errorMessage;
    this.errorDetails = errorDetails;
    this.timestamp = timestamp;
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

  public String getError() {
    return error;
  }

  public void setError(String error) {
    this.error = error;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public String getErrorDetails() {
    return errorDetails;
  }

  public void setErrorDetails(String errorDetails) {
    this.errorDetails = errorDetails;
  }

  public long getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(long timestamp) {
    this.timestamp = timestamp;
  }

}
