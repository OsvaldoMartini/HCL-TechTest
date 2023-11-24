package com.db.dataplatform.techtest.server.component;

import com.db.dataplatform.techtest.server.api.model.DataEnvelope;
import com.db.dataplatform.techtest.server.persistence.BlockTypeEnum;
import com.db.dataplatform.techtest.server.persistence.model.DataBodyEntity;
import com.db.dataplatform.techtest.server.persistence.model.DataHeaderEntity;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

public interface Server {
  boolean saveDataEnvelope(DataEnvelope envelope) throws IOException, NoSuchAlgorithmException;

  List<DataBodyEntity> getAllBlockTypes(BlockTypeEnum blockType);

  Optional<DataHeaderEntity> getByBlockName(String blockName);

  boolean updateByBlockName(String blockName, String newBlockType);
}
