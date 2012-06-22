package nl.tecon.cme.client.acd.parser;

import nl.tecon.cme.client.acd.dto.AcdBlock;
import nl.tecon.cme.common.domain.summary.QueueSummary;
import org.joda.time.DateTime;

import java.util.Arrays;
import java.util.List;

/**
 * @author thies (Thies Edeling - thies@te-con.nl)
 *         Created on: 12/23/10 - 9:21 PM
 */
public class QueueSummaryRowParser implements AcdRowParser
{
    // order is important, this is the order of the csv row
    private static final String[] PROPERTIES = new String[]{"presentedCalls", "answeredCalls", "callsInQueue",
                                                            "avgTimeToAnswer", "longestTimeToAnswer", "abandonedCalls",
                                                            "avgTimeBeforeAbandonment", "callsFwdToVoiceMail", "callsAnsweredByVoiceMail",};

    private static final AcdColumnParser COLUMN_PARSER = new AcdColumnParser(PROPERTIES);

    @Override
    public void parseRowAndAddToBlock(String row, AcdBlock block)
    {
        QueueSummary queue = new QueueSummary();

        String[] columns = row.trim().split(",");

        int column = 0;

        Integer huntGroupId = Integer.valueOf(columns[column++].trim());
        queue.setHuntGroupId(huntGroupId);

        // parse the first time of Sat 16:00 - 17:00
        String timeString = columns[column++].trim();
        DateTime time = AcdRowParserUtil.offsetTime(block.getDate(), timeString);
        queue.setSummaryTime(time);

        // skip the type in column 3
        column++;

        List<String> remainingColumns = Arrays.asList(columns).subList(column, columns.length);
        COLUMN_PARSER.parseColumns(queue, remainingColumns);

        block.addQueueSummary(queue);
    }
}
