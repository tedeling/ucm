package nl.tecon.cme.client.acd.parser

import nl.tecon.cme.client.acd.dto.AcdBlock
import nl.tecon.cme.client.common.ParseContext
import nl.tecon.cme.common.domain.summary.HuntGroup
import org.joda.time.DateMidnight
import org.junit.Before
import org.junit.Test

/**
 * @author thies (Thies Edeling - thies@te-con.nl)
 * Created on: 12/23/10 - 8:54 PM
 */
class AgentSummaryRowParserTest
{
  @Before
  void setUp()
  {
    ParseContext.importResult.reset()
  }

  @Test
  void parseAgentAndAddToHuntGroup()
  {
    def row = "01, Wed 11:00 - 12:00, Agent, 220, 00001, 000002, 000003, 00004, 000005, 000006, 00002, 000243, 000462, 00001, 000147, 000147,"

    def parser = new AgentSummaryRowParser()

    def block = new AcdBlock(new DateMidnight(2011, 2, 10))
    def group = new HuntGroup(huntGroupId: 1)
    block.addHuntGroup group

    parser.parseRowAndAddToBlock(row, block)

    def summary = group.agentSummaries[0]

    assert ParseContext.importResult.importSuccessCount == 12

    assert summary.summaryTime.dayOfMonth == 9
    assert summary.summaryTime.hourOfDay == 11

    assert summary.huntGroupId == 1
    assert summary.agentId == 220
    assert summary.directCallsAnswered == 1
    assert summary.directAvgTimeInCall == 2
    assert summary.directLongestTimeInCall == 3
    assert summary.directTotalCallsOnHold == 4
    assert summary.directAvgHoldTime == 5
    assert summary.directLongestHoldTime == 6
    assert summary.queueCallsAnswered == 2
    assert summary.queueAvgTimeInCall == 243
    assert summary.queueLongestTimeInCall == 462
    assert summary.queueTotalCallsOnHold == 1
    assert summary.queueAvgHoldTime == 147
    assert summary.queueLongestHoldTime == 147
  }
}
