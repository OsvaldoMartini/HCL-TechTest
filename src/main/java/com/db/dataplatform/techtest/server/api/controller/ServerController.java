package com.db.dataplatform.techtest.server.api.controller;

import com.db.dataplatform.techtest.server.api.ErrorMessage;
import com.db.dataplatform.techtest.server.api.model.DataEnvelope;
import com.db.dataplatform.techtest.server.component.Server;
import com.db.dataplatform.techtest.server.persistence.BlockTypeEnum;
import com.db.dataplatform.techtest.server.persistence.model.DataBodyEntity;
import com.db.dataplatform.techtest.server.persistence.model.DataHeaderEntity;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/dataserver")
@RequiredArgsConstructor
@Validated
public class ServerController {

  private final Server server;

  @PostMapping(
      value = "/pushdata",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Boolean> pushData(@Valid @RequestBody DataEnvelope dataEnvelope)
      throws IOException, NoSuchAlgorithmException {

    log.info("Data envelope received: {}", dataEnvelope.getDataHeader().getName());
    boolean checksumPass = server.saveDataEnvelope(dataEnvelope);

    log.info("Data envelope persisted. Attribute name: {}", dataEnvelope.getDataHeader().getName());
    return ResponseEntity.ok(checksumPass);
  }

  @GetMapping("/data/{blockType}")
  public ResponseEntity<?> data(@PathVariable String blockType) {

    log.info("Block Type received: {}", blockType);
    List<DataBodyEntity> response =
        server.getAllBlockTypes(BlockTypeEnum.getBlockTypeEnumByTypeName(blockType).get());

    log.info("Total of persisted blocks for block Type: {}", response.size());

    if (response.size() > 0) {
      return ResponseEntity.ok().body(response);
    } else {
      String bodyOfResponse = "Block Type not Found!";
      return ResponseEntity.ok()
          .body(
              new ErrorMessage(
                  HttpStatus.NO_CONTENT.value(),
                  new Date(),
                  bodyOfResponse,
                  "Block type not in DB!"));
    }
  }

  @GetMapping("/{blockName}")
  public ResponseEntity<?> dataByBlockName(@PathVariable String blockName) {

    log.info("Block Name received: {}", blockName);
    Optional<DataHeaderEntity> response = server.getByBlockName(blockName);

    if (response.isPresent()) {
      return ResponseEntity.ok().body(response);
    } else {
      String bodyOfResponse = "Block Name not Found!";
      return ResponseEntity.ok()
          .body(
              new ErrorMessage(
                  HttpStatus.NO_CONTENT.value(),
                  new Date(),
                  bodyOfResponse,
                  "Block name not in DB!"));
    }
  }

  @PatchMapping(value = "/update/{name}/{newBlockType}")
  public ResponseEntity<?> update(@PathVariable String name, @PathVariable String newBlockType) {

    // Validates the "newBlockType"
    BlockTypeEnum.getBlockTypeEnumByTypeName(newBlockType).get();

    boolean response = server.updateByBlockName(name, newBlockType);

    log.info("Data envelope received: name {} newBlockType {}", name, newBlockType);
    return new ResponseEntity<Boolean>(response, HttpStatus.ACCEPTED);
  }
}
