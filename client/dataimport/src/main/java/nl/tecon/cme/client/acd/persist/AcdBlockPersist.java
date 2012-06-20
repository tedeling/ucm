package nl.tecon.cme.client.acd.persist;

import nl.tecon.cme.client.acd.dto.AcdBlock;

import java.util.List;

/**
 * @author thies (Thies Edeling - thies@te-con.nl)
 *         Created on: 12/29/10 - 10:55 AM
 */
public interface AcdBlockPersist
{
    void persistAcdBlocks(List<AcdBlock> blocks);
}
