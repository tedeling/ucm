package nl.tecon.cme.client.acd.parser

import nl.tecon.cme.client.acd.dto.AcdBlock
import nl.tecon.cme.client.common.ParseContext
import org.joda.time.DateMidnight
import org.junit.Before
import org.junit.Test

/**
 * @author thies (Thies Edeling - thies@te-con.nl)
 * Created on: 12/23/10 - 2:21 PM
 */
class HuntGroupSummaryRowParserTest
{
  @Before
  void setUp()
  {
    ParseContext.importResult.reset()
  }

  @Test
  void parseHuntGroup()
  {

    def row = "01, Sat 15:00 - 16:00, HuntGp, 01, 02, 00003, 00004, 00005, 0006, 0007, 000008, 000009, 0010, 00011, 000012, 000013,"

    def block = new AcdBlock(new DateMidnight(2011, 2, 6))
    def parser = new HuntGroupSummaryRowParser()

    parser.parseRowAndAddToBlock(row, block)

    def group = block.getHuntGroups().iterator().next()

    assert group.huntGroupId == 1
    def summary = group.huntGroupSummaries[0]

    assert ParseContext.importResult.importSuccessCount == 13
    assert summary.summaryTime.dayOfMonth == 5
    assert summary.summaryTime.hourOfDay == 15
    assert summary.agentCountMax == 1
    assert summary.agentCountMin == 2
    assert summary.callCount == 3
    assert summary.answeredCount == 4
    assert summary.abandonedCount == 5
    assert summary.avgTimeToAnswer == 6
    assert summary.longestTimeToAnswer == 7
    assert summary.avgTimeInCall == 8
    assert summary.longestTimeInCall == 9
    assert summary.avgTimeBeforeAbandonment == 10
    assert summary.callsOnHold == 11
    assert summary.avgTimeOnHold == 12
    assert summary.longestTimeOnHold == 13
  }

  @Test
  void parseHuntGroupWithFailures()
  {

    def row = "01, Sat 15:00 - 16:00, HuntGp, 01, 02, 00003, 00004, 00005, 00a6, 0007, 000008, 000009, 0010, 00011, 000012, 000013,"

    def block = new AcdBlock(new DateMidnight(2011, 2, 6))
    def parser = new HuntGroupSummaryRowParser()

    parser.parseRowAndAddToBlock(row, block)

    def group = block.getHuntGroups().iterator().next()

    assert group.huntGroupId == 1
    def summary = group.huntGroupSummaries[0]

    assert ParseContext.importResult.importSuccessCount == 12
    assert ParseContext.importResult.importFailureCount == 1
    assert summary.agentCountMax == 1
    assert summary.summaryTime.dayOfMonth == 5
    assert summary.summaryTime.hourOfDay == 15
    assert summary.agentCountMin == 2
    assert summary.callCount == 3
    assert summary.answeredCount == 4
    assert summary.abandonedCount == 5
    assert summary.avgTimeToAnswer == null
    assert summary.longestTimeToAnswer == 7
    assert summary.avgTimeInCall == 8
    assert summary.longestTimeInCall == 9
    assert summary.avgTimeBeforeAbandonment == 10
    assert summary.callsOnHold == 11
    assert summary.avgTimeOnHold == 12
    assert summary.longestTimeOnHold == 13
  }

  @Test
  void parseHuntGroupWithMissingColumns()
  {

    def row = "01, Sat 15:00 - 16:00, HuntGp, 01, 02, 00003, 00004, 00005, 0006, 0007, 000008, 000009, 0010, 00011, 000012, "

    def block = new AcdBlock(new DateMidnight(2011, 2, 6))
    def parser = new HuntGroupSummaryRowParser()

    parser.parseRowAndAddToBlock(row, block)

    def group = block.getHuntGroups().iterator().next()

    assert group.huntGroupId == 1
    def summary = group.huntGroupSummaries[0]
    assert ParseContext.importResult.importSuccessCount == 12

    assert summary.summaryTime.dayOfMonth == 5
    assert summary.summaryTime.hourOfDay == 15
    assert summary.agentCountMax == 1
    assert summary.agentCountMin == 2
    assert summary.callCount == 3
    assert summary.answeredCount == 4
    assert summary.abandonedCount == 5
    assert summary.avgTimeToAnswer == 6
    assert summary.longestTimeToAnswer == 7
    assert summary.avgTimeInCall == 8
    assert summary.longestTimeInCall == 9
    assert summary.avgTimeBeforeAbandonment == 10
    assert summary.callsOnHold == 11
    assert summary.avgTimeOnHold == 12
    assert summary.longestTimeOnHold == null
  }
}

