package nl.tecon.cme.client.syslog.parser;

import nl.tecon.cme.client.syslog.RawCdr;
import nl.tecon.cme.client.syslog.parser.entityparser.EntityParser;
import nl.tecon.cme.client.syslog.parser.entityparser.EntityParserFactory;
import org.springframework.stereotype.Service;

/**
 * @author thies (Thies Edeling - thies@te-con.nl)
 *         Created on: 12/15/10 - 11:50 PM
 */
@Service
public class CdrParserImpl implements CdrParser
{
    @Override
    public void parseCdr(String cdr)
    {
        RawCdr rawCdr = new RawCdr(cdr);
        EntityParser entityParser = EntityParserFactory.createEntityParser(rawCdr);

        if (entityParser != null)
        {
            entityParser.parseRecord(rawCdr);
        }
    }
}
