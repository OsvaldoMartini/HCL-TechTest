package com.db.dataplatform.techtest.server.service;

import com.db.dataplatform.techtest.server.persistence.BlockTypeEnum;
import com.db.dataplatform.techtest.server.persistence.model.DataBodyEntity;
import java.util.List;

public interface DataBodyService {
  DataBodyEntity saveDataBody(DataBodyEntity dataBody);

  List<DataBodyEntity> getDataByBlockType(BlockTypeEnum blockType);
}
