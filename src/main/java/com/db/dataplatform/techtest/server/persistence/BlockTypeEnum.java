package com.db.dataplatform.techtest.server.persistence;

import java.util.Arrays;
import java.util.Optional;

public enum BlockTypeEnum {
  BLOCKTYPEA("blocktypea"),
  BLOCKTYPEB("blocktypeb");

  private final String typeName;

  BlockTypeEnum(String typeName) {
    this.typeName = typeName;
  }

  public String getTypeName() {
    return typeName;
  }

  // Reverse lookup methods
  public static Optional<BlockTypeEnum> getBlockTypeEnumByTypeName(String value) {
    return Optional.ofNullable(
        Arrays.stream(BlockTypeEnum.values())
            .filter(blockType -> blockType.typeName.equalsIgnoreCase(value))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Block Type provided not exist!")));
  }
}
