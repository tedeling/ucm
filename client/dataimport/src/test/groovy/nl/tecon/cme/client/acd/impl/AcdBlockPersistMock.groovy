package nl.tecon.cme.client.acd.impl

import nl.tecon.cme.client.StubRepository
import nl.tecon.cme.client.acd.dto.AcdBlock
import nl.tecon.cme.client.acd.persist.AcdBlockPersist

/**
 * @author thies (Thies Edeling - thies@te-con.nl)
 * Created on: 12/29/10 - 1:09 PM
 */
@StubRepository()
class AcdBlockPersistMock implements AcdBlockPersist
{
  def blocks = []

  void persistAcdBlocks(List<AcdBlock> blocks)
  {
    this.blocks.addAll(blocks);
  }


}
