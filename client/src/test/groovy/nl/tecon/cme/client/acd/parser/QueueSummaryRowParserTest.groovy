package nl.tecon.cme.client.acd.parser

import nl.tecon.cme.client.acd.dto.AcdBlock
import nl.tecon.cme.client.common.ParseContext
import nl.tecon.cme.common.domain.summary.HuntGroup
import org.joda.time.DateMidnight
import org.junit.Before
import org.junit.Test

/**
 * @author thies (Thies Edeling - thies@te-con.nl)
 * Created on: 12/23/10 - 9:32 PM
 */
class QueueSummaryRowParserTest
{
  @Before
  void setUp()
  {
    ParseContext.importResult.reset()
  }

  @Test
  void parseQueueAndAddToQueue()
  {
    def row = "04, Wed 23:00 - 12:00, Queue, 00001, 00002, 00003, 00004, 00005, 00006, 00007, 00008, 00009,"

    def block = new AcdBlock(new DateMidnight(2011, 2, 17))
    def group = new HuntGroup(huntGroupId: 4)
    block.addHuntGroup group

    def parser = new QueueSummaryRowParser()
    parser.parseRowAndAddToBlock(row, block)

    def queue = group.queueSummaries[0]

    assert queue.summaryTime.dayOfMonth == 16
    assert queue.summaryTime.year == 2011
    assert queue.summaryTime.monthOfYear == 2
    assert queue.summaryTime.hourOfDay == 23

    assert ParseContext.importResult.importSuccessCount == 9
    assert queue.huntGroupId == 4
    assert queue.presentedCalls == 1
    assert queue.answeredCalls == 2
    assert queue.callsInQueue == 3
    assert queue.avgTimeToAnswer == 4
    assert queue.longestTimeToAnswer == 5
    assert queue.abandonedCalls == 6
    assert queue.avgTimeBeforeAbandonment == 7
    assert queue.callsFwdToVoiceMail == 8
    assert queue.callsAnsweredByVoiceMail == 9
  }
}
