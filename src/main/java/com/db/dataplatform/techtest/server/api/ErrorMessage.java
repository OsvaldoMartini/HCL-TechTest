package com.db.dataplatform.techtest.server.api;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorMessage {
  private int statusCode;
  private Date timestamp;
  private String message;
  private String description;
}
