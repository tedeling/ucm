package nl.tecon.cme.client.acd;

import java.io.FileNotFoundException;

/**
 * @author thies (Thies Edeling - thies@te-con.nl)
 *         Created on: 12/23/10 - 11:48 AM
 */
public interface AcdImport
{
    int parseAcdFiles() throws FileNotFoundException;
}
