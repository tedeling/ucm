package nl.tecon.cme.client.acd.parser;

import nl.tecon.cme.client.acd.dto.AcdBlock;
import nl.tecon.cme.common.domain.summary.HuntGroup;
import nl.tecon.cme.common.domain.summary.HuntGroupSummary;
import org.joda.time.DateTime;

import java.util.Arrays;
import java.util.List;

/**
 * @author thies (Thies Edeling - thies@te-con.nl)
 *         Created on: 12/23/10 - 1:50 PM
 */
public class HuntGroupSummaryRowParser implements AcdRowParser
{
    private static final String[] PROPERTIES = new String[]{"agentCountMax", "agentCountMin", "callCount", "answeredCount",
                                                            "abandonedCount", "avgTimeToAnswer", "longestTimeToAnswer", "avgTimeInCall",
                                                            "longestTimeInCall", "avgTimeBeforeAbandonment", "callsOnHold", "avgTimeOnHold", "longestTimeOnHold"};
    private static final AcdColumnParser COLUMN_PARSER = new AcdColumnParser(PROPERTIES);

    @Override
    public void parseRowAndAddToBlock(String row, AcdBlock block)
    {
        HuntGroup huntGroup = new HuntGroup();
        HuntGroupSummary summary = new HuntGroupSummary();
        huntGroup.addHuntGroupSummary(summary);

        String[] columns = row.trim().split(",");

        int column = 0;

        Integer huntGroupId = Integer.valueOf(columns[column++].trim());
        huntGroup.setHuntGroupId(huntGroupId);
        summary.setHuntGroupId(huntGroupId);

        // parse the first time of Sat 16:00 - 17:00
        String timeString = columns[column++].trim();
        DateTime time = AcdRowParserUtil.offsetTime(block.getDate(), timeString);
        summary.setSummaryTime(time);

        // skip the type in column 3
        column++;

        List<String> remainingColumns = Arrays.asList(columns).subList(column, columns.length);
        COLUMN_PARSER.parseColumns(summary, remainingColumns);

        block.addHuntGroup(huntGroup);
    }
}
