package nl.tecon.cme.client.acd.parser;

import nl.tecon.cme.client.acd.dto.EntityType;

/**
 * @author thies (Thies Edeling - thies@te-con.nl)
 *         Created on: 12/23/10 - 1:49 PM
 */
public final class AcdRowParserFactory
{
    private static final int SUMMARY_TYPE_COLUMN = 2;

    private AcdRowParserFactory()
    {
    }

    public static AcdRowParser getParser(String row)
    {
        String[] split = row.split(",");

        EntityType rowType = EntityType.rawValueFor(split[SUMMARY_TYPE_COLUMN].trim());

        AcdRowParser parser;

        switch (rowType)
        {
            case HUNTGROUP:
                parser = new HuntGroupSummaryRowParser();
                break;
            case AGENT:
                parser = new AgentSummaryRowParser();
                break;
            case QUEUE:
                parser = new QueueSummaryRowParser();
                break;
            default:
                parser = null;
                break;
        }

        return parser;
    }
}
