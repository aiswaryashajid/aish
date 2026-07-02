package com.vehicle.registry_service.exception;

public class GitRepositoryException extends RuntimeException {

  public GitRepositoryException(String message) {
    super(message);
  }

  public GitRepositoryException(String message, Throwable cause) {
    super(message, cause);
  }

}
