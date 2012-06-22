package nl.tecon.cme.client.acd.dto;


import nl.tecon.ucm.domain.AgentSummary;
import nl.tecon.ucm.domain.HuntGroup;
import nl.tecon.ucm.domain.QueueSummary;
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
        if (huntGroups.containsKey(huntGroup.huntGroupId()))
        {
            huntGroups.get(huntGroup.huntGroupId()).addHuntGroupSummary(huntGroup.huntGroupSummaries());
        } else
        {
            huntGroups.put(huntGroup.huntGroupId(), huntGroup);
        }
    }

    public void addAgentSummary(AgentSummary agentSummary)
    {
        huntGroups.get(agentSummary.huntGroupId()).addAgentSummary(agentSummary);
    }

    public void addQueueSummary(QueueSummary queueSummary)
    {
        huntGroups.get(queueSummary.huntGroupId()).addQueueSummary(queueSummary);
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
