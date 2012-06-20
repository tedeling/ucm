package nl.tecon.cme.client.persistence.summary.dao

import nl.tecon.cme.common.dao.AbstractDaoTest
import nl.tecon.cme.common.domain.summary.AgentSummary
import org.joda.time.DateTime
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.transaction.annotation.Transactional
import java.sql.ResultSet

@ContextConfiguration(locations = [ "classpath:context-orm.xml", "classpath:context-persistence-scanner.xml"])
class AgentSummaryDaoTest extends AbstractDaoTest
{
  @Autowired
  AgentSummaryDao agentSummaryDao

  @Test
  void shouldInsert()
  {
    def now = new DateTime()


      sql.query("SELECT * FROM AGENT_SUMMARY") { ResultSet rs ->
          while (rs.next()) println "wtf:" + rs.getString('AGENT_ID')
      }



    def summary = new AgentSummary(agentId: 1, huntGroupId: 2, summaryTime: now,
            directCallsAnswered: 3, directAvgTimeInCall: 2, directLongestTimeInCall: 5,
            directTotalCallsOnHold: 6, directAvgHoldTime: 7, directLongestHoldTime: 8,
            queueCallsAnswered: 9, queueAvgTimeInCall: 10, queueLongestTimeInCall: 11,
            queueTotalCallsOnHold: 12, queueAvgHoldTime: 13, queueLongestHoldTime: 14)
    agentSummaryDao.persist(summary)

    def found = sql.firstRow("SELECT * FROM AGENT_SUMMARY WHERE HUNTGROUP_ID = 2")
    assert found.AGENT_ID == 1
    assert found.HUNTGROUP_ID == 2
    assert found.SUMMARY_TIME != null
    assert found.DIRECT_CALLS_ANSWERED == 3
    assert found.DIRECT_AVG_TIME_IN_CALL == 2
    assert found.DIRECT_LONGEST_TIME_IN_CALL == 5
    assert found.DIRECT_TOTAL_CALLS_ON_HOLD == 6
    assert found.DIRECT_AVG_HOLD_TIME == 7
    assert found.DIRECT_LONGEST_HOLD_TIME == 8
    assert found.QUEUE_CALLS_ANSWERED == 9
    assert found.QUEUE_AVG_TIME_IN_CALL == 10
    assert found.QUEUE_LONGEST_TIME_IN_CALL == 11
    assert found.QUEUE_TOTAL_CALLS_ON_HOLD == 12
    assert found.QUEUE_AVG_HOLD_TIME == 13
    assert found.QUEUE_LONGEST_HOLD_TIME == 14
  }

  @Test
  void shouldFindDuplicates()
  {
    def time = new DateTime(1974, 9, 1, 10, 45, 00, 00);
    def timeAsString = time.toString("yyyy-MM-dd hh:mm:ss")

    sql.execute "INSERT INTO AGENT_SUMMARY (AGENT_ID, HUNTGROUP_ID, SUMMARY_TIME, DIRECT_CALLS_ANSWERED, DIRECT_AVG_TIME_IN_CALL) VALUES(1, 2, ${timeAsString}, 3, 4)"

    def count = agentSummaryDao.findDuplicateCount(new AgentSummary(huntGroupId: 2, summaryTime: time, agentId: 1))

    assert count != null
    assert count == 1
  }
}
