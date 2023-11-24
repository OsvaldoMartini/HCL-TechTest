package com.db.dataplatform.techtest.service;

import static com.db.dataplatform.techtest.TestDataHelper.createTestDataEnvelopeApiObject;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import com.db.dataplatform.techtest.server.api.model.DataEnvelope;
import com.db.dataplatform.techtest.server.component.Server;
import com.db.dataplatform.techtest.server.mapper.ServerMapperConfiguration;
import com.db.dataplatform.techtest.server.persistence.model.DataBodyEntity;
import com.db.dataplatform.techtest.server.persistence.model.DataHeaderEntity;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

@RunWith(MockitoJUnitRunner.class)
public class ServerServiceTests {

  @Mock private Server server;

  private ModelMapper modelMapper;

  private DataBodyEntity expectedDataBodyEntity;
  private DataEnvelope testDataEnvelope;

  @Before
  public void setup() {
    ServerMapperConfiguration serverMapperConfiguration = new ServerMapperConfiguration();
    modelMapper = serverMapperConfiguration.createModelMapperBean();

    testDataEnvelope = createTestDataEnvelopeApiObject();
    expectedDataBodyEntity = modelMapper.map(testDataEnvelope.getDataBody(), DataBodyEntity.class);
    expectedDataBodyEntity.setDataHeaderEntity(
        modelMapper.map(testDataEnvelope.getDataHeader(), DataHeaderEntity.class));
  }

  @Test
  public void shouldSaveDataEnvelopeAsExpected() throws NoSuchAlgorithmException, IOException {
    when(server.saveDataEnvelope(any(DataEnvelope.class))).thenReturn(true);
    boolean success = server.saveDataEnvelope(testDataEnvelope);

    assertThat(success).isTrue();
    verify(server, times(1)).saveDataEnvelope(eq(testDataEnvelope));
  }
}
