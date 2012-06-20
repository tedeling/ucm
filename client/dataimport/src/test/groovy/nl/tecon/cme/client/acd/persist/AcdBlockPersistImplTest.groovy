package nl.tecon.cme.client.acd.persist

import nl.tecon.cme.client.acd.dto.AcdBlock
import nl.tecon.cme.common.domain.summary.HuntGroup
import org.joda.time.DateMidnight
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import nl.tecon.cme.client.persistence.summary.dao.*

/**
 * @author thies (Thies Edeling - thies@te-con.nl)
 * Created on: 12/29/10 - 11:37 AM
 */
class AcdBlockPersistImplTest {
  AcdBlockPersistImpl persister

  @Mock
  HuntGroupDao huntGroupDao

  @Mock
  HuntGroupSummaryDao huntGroupSummaryDao

  @Mock
  AgentDao agentDao

  @Mock
  AgentSummaryDao agentSummaryDao

  @Mock
  QueueSummaryDao queueSummaryDao


  @Before
  void setUp()
  {
    MockitoAnnotations.initMocks this

    persister = new AcdBlockPersistImpl()
    persister.huntGroupDao = huntGroupDao
    persister.huntGroupSummaryDao = huntGroupSummaryDao
    persister.agentDao = agentDao
    persister.agentSummaryDao = agentSummaryDao
    persister.queueSummaryDao = queueSummaryDao
  }

  @Test
  void shouldPersistAcdBlock()
  {
    def block = new AcdBlock(new DateMidnight())
    block.addHuntGroup new HuntGroup(huntGroupId: 1)

    persister.persistAcdBlock block
  }
}
