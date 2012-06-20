package nl.tecon.cme.client.acd.dto;

import nl.tecon.cme.common.domain.summary.AgentSummary;
import nl.tecon.cme.common.domain.summary.HuntGroup;
import nl.tecon.cme.common.domain.summary.QueueSummary;
import org.joda.time.DateMidnight;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author thies (Thies Edeling - thies@te-con.nl)
 *         Created on: 12/23/10 - 1:26 PM
 */
public class AcdBlock
{
    private final DateMidnight date;

    private Map<Integer, HuntGroup> huntGroups = new HashMap<Integer, HuntGroup>();

    public AcdBlock(DateMidnight date)
    {
        this.date = date;
    }

    public void addHuntGroup(HuntGroup huntGroup)
    {
        if (huntGroups.containsKey(huntGroup.getHuntGroupId()))
        {
            huntGroups.get(huntGroup.getHuntGroupId()).addHuntGroupSummary(huntGroup.getHuntGroupSummaries());
        } else
        {
            huntGroups.put(huntGroup.getHuntGroupId(), huntGroup);
        }
    }

    public void addAgentSummary(AgentSummary agentSummary)
    {
        huntGroups.get(agentSummary.getHuntGroupId()).addAgentSummary(agentSummary);
    }

    public void addQueueSummary(QueueSummary queueSummary)
    {
        huntGroups.get(queueSummary.getHuntGroupId()).addQueueSummary(queueSummary);
    }

    public Collection<HuntGroup> getHuntGroups()
    {
        return huntGroups.values();
    }

    public DateMidnight getDate()
    {
        return date;
    }
}
