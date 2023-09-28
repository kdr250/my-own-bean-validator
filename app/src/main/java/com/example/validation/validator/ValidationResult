package com.example.validation.validator;

public class ValidationResult {

  private final String fieldName;

  private final String errorMessage;

  public ValidationResult(String fieldName, String errorMsgKey) {
    this.fieldName = fieldName;
    this.errorMessage = errorMsgKey;
  }

  private ValidationResult() {
    this.fieldName = null;
    this.errorMessage = null;
  }

  public static ValidationResult noError() {
    return new ValidationResult();
  }

  public boolean hasError() {
    return this.fieldName != null && this.errorMessage != null;
  }

  public String getFieldName() {
    return fieldName;
  }

  public String getErrorMessage() {
    return errorMessage;
  }
}
