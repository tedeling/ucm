package nl.tecon.cme.client.syslog.parser.entityparser;

import nl.tecon.cme.client.syslog.RawCdr;

/**
 * User: x082062 (Thies Edeling - thies@te-con.nl)
 * Date: 12/20/10 5:21 PM
 */
public interface EntityParser
{
    /**
     * @param cdr to parse
     * @return true on success
     */
    public void parseRecord(RawCdr cdr);
}
