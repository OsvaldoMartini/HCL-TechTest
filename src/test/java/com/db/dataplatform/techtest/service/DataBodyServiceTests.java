package com.db.dataplatform.techtest.service;

import static com.db.dataplatform.techtest.TestDataHelper.createTestDataBodyEntity;
import static com.db.dataplatform.techtest.TestDataHelper.createTestDataHeaderEntity;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.db.dataplatform.techtest.server.persistence.model.DataBodyEntity;
import com.db.dataplatform.techtest.server.persistence.model.DataHeaderEntity;
import com.db.dataplatform.techtest.server.persistence.repository.DataStoreRepository;
import com.db.dataplatform.techtest.server.service.DataBodyService;
import com.db.dataplatform.techtest.server.service.impl.DataBodyServiceImpl;
import java.time.Instant;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DataBodyServiceTests {

  public static final String TEST_NAME_NO_RESULT = "TestNoResult";

  @Mock private DataStoreRepository dataStoreRepositoryMock;

  private DataBodyService dataBodyService;
  private DataBodyEntity expectedDataBodyEntity;

  @Before
  public void setup() {
    DataHeaderEntity testDataHeaderEntity = createTestDataHeaderEntity(Instant.now());
    expectedDataBodyEntity = createTestDataBodyEntity(testDataHeaderEntity);

    dataBodyService = new DataBodyServiceImpl(dataStoreRepositoryMock);
  }

  @Test
  public void shouldSaveDataBodyEntityAsExpected() {
    dataBodyService.saveDataBody(expectedDataBodyEntity);

    verify(dataStoreRepositoryMock, times(1)).save(eq(expectedDataBodyEntity));
  }
}
