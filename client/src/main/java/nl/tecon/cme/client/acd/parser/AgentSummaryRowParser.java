package nl.tecon.cme.client.acd.parser;

import nl.tecon.cme.client.acd.dto.AcdBlock;
import nl.tecon.cme.common.domain.summary.Agent;
import nl.tecon.cme.common.domain.summary.AgentSummary;
import org.joda.time.DateTime;

import java.util.Arrays;
import java.util.List;

/**
 * @author thies (Thies Edeling - thies@te-con.nl)
 *         Created on: 12/23/10 - 8:37 PM
 */
public class AgentSummaryRowParser implements AcdRowParser
{
    // order is important, this is the order of the csv row
    private static final String[] PROPERTIES = new String[]{"directCallsAnswered", "directAvgTimeInCall", "directLongestTimeInCall",
                                                            "directTotalCallsOnHold", "directAvgHoldTime", "directLongestHoldTime",
                                                            "queueCallsAnswered", "queueAvgTimeInCall", "queueLongestTimeInCall",
                                                            "queueTotalCallsOnHold", "queueAvgHoldTime", "queueLongestHoldTime"};

    private static final AcdColumnParser COLUMN_PARSER = new AcdColumnParser(PROPERTIES);

    @Override
    public void parseRowAndAddToBlock(String row, AcdBlock block)
    {
        Agent agent = new Agent();
        AgentSummary summary = new AgentSummary();
        summary.setAgent(agent);

        String[] columns = row.trim().split(",");

        int column = 0;

        Integer huntGroupId = Integer.valueOf(columns[column++].trim());
        summary.setHuntGroupId(huntGroupId);

        // parse the first time of Sat 16:00 - 17:00
        String timeString = columns[column++].trim();
        DateTime time = AcdRowParserUtil.offsetTime(block.getDate(), timeString);
        summary.setSummaryTime(time);

        // skip the type in column 3
        column++;

        // parse the agent
        Integer agentId = Integer.valueOf(columns[column++].trim());
        agent.setAgentId(agentId);
        summary.setAgentId(agentId);

        List<String> remainingColumns = Arrays.asList(columns).subList(column, columns.length);
        COLUMN_PARSER.parseColumns(summary, remainingColumns);

        block.addAgentSummary(summary);
    }
}
