package nl.tecon.cme.client.persistence.summary.dao

import nl.tecon.cme.common.dao.AbstractDaoTest
import nl.tecon.cme.common.domain.summary.QueueSummary
import org.joda.time.DateTime
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.junit.Ignore

/**
 * @author thies (Thies Edeling - thies@te-con.nl)
 * Created on: 12/30/10 - 2:13 AM
 */
@ContextConfiguration(locations = [ "classpath:context-orm.xml"])
class QueueSummaryDaoTest extends AbstractDaoTest
{
  @Autowired
  QueueSummaryDao queueSummaryDao

  @Test
  void shouldInsert()
  {
    def now = new DateTime()

    def summary = new QueueSummary(huntGroupId: 2, summaryTime: now, presentedCalls: 3, answeredCalls: 4, callsInQueue: 5,
            avgTimeToAnswer: 6, longestTimeToAnswer: 7, abandonedCalls: 8, avgTimeBeforeAbandonment: 9,
            callsFwdToVoiceMail: 10, callsAnsweredByVoiceMail: 11)
    queueSummaryDao.persist summary

    def found = sql.firstRow("SELECT * FROM QUEUE_SUMMARY WHERE HUNTGROUP_ID = 2")
    assert found.HUNTGROUP_ID == 2
    assert found.SUMMARY_TIME != null
    assert found.PRESENTED_CALLS == 3
    assert found.ANSWERED_CALLS == 4
    assert found.CALLS_IN_QUEUE == 5
    assert found.AVG_TIME_TO_ANSWER == 6
    assert found.LONGEST_TIME_TO_ANSWER == 7
    assert found.ABANDONED_CALLS == 8
    assert found.AVG_TIME_BEFORE_ABANDONMENT == 9
    assert found.CALLS_FWD_TO_VOICEMAIL == 10
    assert found.CALLS_ANSWERED_BY_VOICEMAIL == 11
  }

  @Test
  void shouldFindDuplicates()
  {
    def time = new DateTime(1974, 9, 1, 10, 45, 00, 00);
    def timeAsString = time.toString("yyyy-MM-dd hh:mm:ss")

    sql.execute "INSERT INTO QUEUE_SUMMARY (HUNTGROUP_ID, SUMMARY_TIME, PRESENTED_CALLS, ANSWERED_CALLS) VALUES(2, ${timeAsString}, 3, 4)"

    def count = queueSummaryDao.findDuplicateCount(new QueueSummary(huntGroupId: 2, summaryTime: time))

    assert count != null
    assert count == 1
  }
}
