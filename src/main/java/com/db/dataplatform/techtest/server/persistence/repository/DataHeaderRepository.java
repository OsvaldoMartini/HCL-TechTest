package com.db.dataplatform.techtest.server.persistence.repository;

import com.db.dataplatform.techtest.server.persistence.model.DataHeaderEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataHeaderRepository extends JpaRepository<DataHeaderEntity, Long> {
  Optional<DataHeaderEntity> findByName(String name);
}
