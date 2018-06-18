package com.yz1017.util;

/**
 * DTO class to hold the error message
 *
 */
public class ErrorDTO {
  private String errorMessage;

  public ErrorDTO(String errorMessage) {
      this.errorMessage = errorMessage;
  }

  public String getErrorMessage() {
      return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
      this.errorMessage = errorMessage;
  }
}