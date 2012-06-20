package nl.tecon.cme.common.domain.summary

import org.junit.Test

/**
 * @author thies (Thies Edeling - thies@te-con.nl)
 * Created on: 1/4/11 - 3:57 PM
 */
class HuntGroupTest {

  @Test
  void shouldAddAgentSummary() {
    def group = new HuntGroup()

    group.addAgentSummary(new AgentSummary())

    assert group.agentSummaries.size == 1
  }
}
