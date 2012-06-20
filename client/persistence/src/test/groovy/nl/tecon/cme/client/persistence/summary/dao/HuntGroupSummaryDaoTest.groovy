package nl.tecon.cme.client.persistence.summary.dao

import nl.tecon.cme.common.dao.AbstractDaoTest
import nl.tecon.cme.common.domain.summary.HuntGroupSummary
import org.joda.time.DateTime
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.transaction.annotation.Transactional

@ContextConfiguration(locations = [ "classpath:context-orm.xml", "classpath:context-persistence-scanner.xml"])
@Transactional
class HuntGroupSummaryDaoTest extends AbstractDaoTest
{
  @Autowired
  HuntGroupSummaryDao huntGroupSummaryDao

  @Test
  void shouldInsert()
  {
    def now = new DateTime()

    def summary = new HuntGroupSummary(huntGroupId: 1, summaryTime: now, agentCountMax: 1, agentCountMin: 2, callCount: 3,
            answeredCount: 4, abandonedCount: 5, avgTimeToAnswer: 6,
            longestTimeToAnswer: 7, avgTimeInCall: 8, longestTimeInCall: 9,
            avgTimeBeforeAbandonment: 10, callsOnHold: 11, avgTimeOnHold: 12, longestTimeOnHold: 13)
    huntGroupSummaryDao.persist(summary)

    def found = sql.firstRow("SELECT * FROM HUNTGROUP_SUMMARY WHERE HUNTGROUP_ID = 1")
    assert found.HUNTGROUP_ID == 1
    assert found.SUMMARY_TIME != null
    assert found.AGENT_COUNT_MAX == 1
    assert found.AGENT_COUNT_MIN == 2
    assert found.CALL_COUNT == 3
    assert found.ANSWERED_COUNT == 4
    assert found.ABANDONED_COUNT == 5
    assert found.AVG_TIME_TO_ANSWER == 6
    assert found.LONGEST_TIME_TO_ANSWER == 7
    assert found.AVG_TIME_IN_CALL == 8
    assert found.LONGEST_TIME_IN_CALL == 9
    assert found.AVG_TIME_BEFORE_ABANDONMENT == 10
    assert found.CALLS_ON_HOLD == 11
    assert found.AVG_TIME_ON_HOLD == 12
    assert found.LONGEST_TIME_ON_HOLD == 13
  }

  @Test
  void shouldFindDuplicates()
  {
    def time = new DateTime(1974, 9, 1, 10, 45, 00, 00);
    def timeAsString = time.toString("yyyy-MM-dd hh:mm:ss")

    sql.execute "INSERT INTO HUNTGROUP_SUMMARY (HUNTGROUP_ID, SUMMARY_TIME, AGENT_COUNT_MAX, AGENT_COUNT_MIN) VALUES(1, ${timeAsString}, 2,3)"

    def count = huntGroupSummaryDao.findDuplicateCount(new HuntGroupSummary(huntGroupId: 1, summaryTime: time))

    assert count != null
    assert count == 1
  }
}
