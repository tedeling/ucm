package nl.tecon.cme.client.acd.parser

import org.junit.Test

/**
 * @author thies (Thies Edeling - thies@te-con.nl)
 * Created on: 12/23/10 - 1:51 PM
 */
class AcdRowParserFactoryTest
{
  @Test
  void shouldCreateHuntGroupParser()
  {
    def parser = AcdRowParserFactory.getParser("01, Sat 16:00 - 17:00, HuntGp, 00, 00, 00000, 00000, 00000, 0000, 0000, 000000, 000000, 0000, 00000, 000000, 000000,")

    assert parser.class == HuntGroupSummaryRowParser.class
  }

  @Test
  void shouldCreateAgentParser()
  {
    def parser = AcdRowParserFactory.getParser("01, Wed 11:00 - 12:00, Agent, 220, 00001, 000002, 000003, 00004, 000005, 000006, 00002, 000243, 000462, 00001, 000147, 000147,")

    assert parser.class == AgentSummaryRowParser.class
  }

  @Test
  void shouldCreateQueueParser()
  {
    def parser = AcdRowParserFactory.getParser("05, Wed 11:00 - 12:00, Queue, 00001, 00001, 00000, 00004, 00004, 00000, 00000, 00000, 00000, ")

    assert parser.class == QueueSummaryRowParser.class
  }

}
