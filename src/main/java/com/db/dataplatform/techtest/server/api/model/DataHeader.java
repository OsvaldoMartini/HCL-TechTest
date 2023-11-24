package com.db.dataplatform.techtest.server.api.model;

import com.db.dataplatform.techtest.server.persistence.BlockTypeEnum;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@JsonSerialize(as = DataHeader.class)
@JsonDeserialize(as = DataHeader.class)
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class DataHeader {

  @NotBlank
  @NotNull
  @Size(min = 2, max = 30)
  private String name;

  private BlockTypeEnum blockType;
}
