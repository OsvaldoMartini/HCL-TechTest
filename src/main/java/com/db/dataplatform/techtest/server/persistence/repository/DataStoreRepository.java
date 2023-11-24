package com.db.dataplatform.techtest.server.persistence.repository;

import com.db.dataplatform.techtest.server.persistence.BlockTypeEnum;
import com.db.dataplatform.techtest.server.persistence.model.DataBodyEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DataStoreRepository extends JpaRepository<DataBodyEntity, Long> {
  @Query(
      "select store from DataBodyEntity store, DataHeaderEntity header \n"
          + "where store.dataHeaderEntity.dataHeaderId = header.dataHeaderId \n"
          + "and header.blocktype = :blockType ")
  List<DataBodyEntity> getAllBlocksType(@Param("blockType") BlockTypeEnum blockType);
}
