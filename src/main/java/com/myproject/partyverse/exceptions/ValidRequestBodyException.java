package com.myproject.partyverse.exceptions;

import com.myproject.partyverse.http.HttpResponseDo;

public class ValidRequestBodyException extends RuntimeException {

  private final HttpResponseDo validationError;
  
  public ValidRequestBodyException(HttpResponseDo validationError){
    this.validationError = validationError;
  }

  public HttpResponseDo getValidationError() {
    return validationError;
  }
}
