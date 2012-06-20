package nl.tecon.cme.client.syslog.parser.entityparser;

import nl.tecon.cme.client.syslog.RawCdr;

import java.util.HashMap;
import java.util.Map;

/**
 * User: x082062 (Thies Edeling - thies@te-con.nl)
 * Date: 12/20/10 5:23 PM
 */
public final class EntityParserFactory
{
    private static final Map<String, EntityParser> PARSERS;

    static
    {
        PARSERS = new HashMap<String, EntityParser>();
        PARSERS.put("VOIP_CALL_HISTORY", new CdrHistoryEntityParser());
        PARSERS.put("VOIP_FEAT_HISTORY", new CdrVsaEntityParser());
    }

    private EntityParserFactory()
    {
    }

    public static EntityParser createEntityParser(RawCdr cdr)
    {
        String type = cdr.getType();

        if (type != null)
        {
            for (Map.Entry<String, EntityParser> parserEntry : PARSERS.entrySet())
            {
                if (type.contains(parserEntry.getKey()))
                {
                    return parserEntry.getValue();
                }
            }
        }

        return null;
    }
}
