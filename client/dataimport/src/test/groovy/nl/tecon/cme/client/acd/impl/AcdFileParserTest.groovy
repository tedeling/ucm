package nl.tecon.cme.client.acd.impl

import org.joda.time.DateTime
import org.junit.Test

/**
 * User: x082062 (Thies Edeling - thies@te-con.nl)
 * Date: 12/24/10 10:36 AM
 */
class AcdFileParserTest
{
  @Test
  void shouldParse()
  {
    def file = new File("src/test/resources/AcdFileParser/parser.csv")
    def parser = new AcdFileParser()
    def blocks = parser.parseAcdFile(file)

    assert 2 == blocks.size()

    def group = blocks[0].huntGroups;

    DateTime time = group.huntGroupSummaries[0].summaryTime

    assert time.dayOfMonth == 16
    assert time.hourOfDay == 10

    def huntGroup = blocks[1].huntGroups.iterator().next()

    assert huntGroup.huntGroupId == 1
    assert huntGroup.agentSummaries.size() == 4
  }

  @Test
  void shouldParseAndCalculateDateDifference()
  {
    def file = new File("src/test/resources/AcdFileParser/datediff.csv")
    def parser = new AcdFileParser()
    def blocks = parser.parseAcdFile(file)

    assert 1 == blocks.size()

    def group = blocks[0].huntGroups;

    DateTime time = group.huntGroupSummaries[0].summaryTime

    assert time.dayOfMonth == 17
    assert time.monthOfYear == 1
    assert time.year == 2011
    assert time.hourOfDay == 12
  }
}

