package nl.tecon.cme.client.persistence.summary.dao

import nl.tecon.cme.common.dao.AbstractDaoTest
import nl.tecon.cme.common.domain.summary.Agent
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.transaction.annotation.Transactional

@ContextConfiguration(locations = [ "classpath:context-orm.xml", "classpath:context-persistence-scanner.xml"])
class AgentDaoTest extends AbstractDaoTest
{
  @Autowired
  AgentDao agentDao

  @Test
  void shouldInsert()
  {
    def agent = new Agent(agentId: 1)
    agentDao.persistAgent agent

    def found = sql.firstRow("SELECT * FROM AGENT WHERE AGENT_ID = 1")
    assert found.AGENT_ID == 1
  }

  @Test
  void shouldFindDupe()
  {
    sql.execute "INSERT INTO AGENT (AGENT_ID) VALUES(2)"

    def count = agentDao.findDuplicateCount(2)

    assert count == 1
  }

}
