package nl.tecon.cme.client.acd.parser;

import nl.tecon.cme.client.acd.dto.AcdBlock;

/**
 * @author thies (Thies Edeling - thies@te-con.nl)
 *         Created on: 12/23/10 - 1:49 PM
 */
public interface AcdRowParser
{
    void parseRowAndAddToBlock(String row, AcdBlock block);
}
