package com.db.dataplatform.techtest.service;

import static com.db.dataplatform.techtest.TestDataHelper.createTestDataHeaderEntity;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.db.dataplatform.techtest.server.persistence.model.DataHeaderEntity;
import com.db.dataplatform.techtest.server.persistence.repository.DataHeaderRepository;
import com.db.dataplatform.techtest.server.service.DataHeaderService;
import com.db.dataplatform.techtest.server.service.impl.DataHeaderServiceImpl;
import java.time.Instant;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DataHeaderServiceTests {

  @Mock private DataHeaderRepository dataHeaderRepositoryMock;

  private DataHeaderService dataHeaderService;
  private DataHeaderEntity expectedDataHeaderEntity;

  @Before
  public void setup() {
    expectedDataHeaderEntity = createTestDataHeaderEntity(Instant.now());

    dataHeaderService = new DataHeaderServiceImpl(dataHeaderRepositoryMock);
  }

  @Test
  public void shouldSaveDataHeaderEntityAsExpected() {
    dataHeaderService.saveHeader(expectedDataHeaderEntity);

    verify(dataHeaderRepositoryMock, times(1)).save(eq(expectedDataHeaderEntity));
  }
}
