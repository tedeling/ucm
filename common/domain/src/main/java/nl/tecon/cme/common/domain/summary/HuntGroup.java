package nl.tecon.cme.common.domain.summary;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * User: x082062 (Thies Edeling - thies@te-con.nl)
 * Date: 12/24/10 2:26 PM
 */
public class HuntGroup implements Entity
{
    private Integer pilotNumber;
    private Integer huntGroupId;

    private List<AgentSummary> agentSummaries = new ArrayList<AgentSummary>();
    private List<QueueSummary> queueSummaries = new ArrayList<QueueSummary>();
    private List<HuntGroupSummary> huntGroupSummaries = new ArrayList<HuntGroupSummary>();

    public void addHuntGroupSummary(HuntGroupSummary summary)
    {
        huntGroupSummaries.add(summary);
    }

    public void addHuntGroupSummary(List<HuntGroupSummary> summaries)
    {
        huntGroupSummaries.addAll(summaries);
    }

    public List<HuntGroupSummary> getHuntGroupSummaries()
    {
        return huntGroupSummaries;
    }

    public void addAgentSummary(AgentSummary summary)
    {
        agentSummaries.add(summary);
    }

    public List<AgentSummary> getAgentSummaries()
    {
        return agentSummaries;
    }

    public void addQueueSummary(QueueSummary summary)
    {
        queueSummaries.add(summary);
    }

    public List<QueueSummary> getQueueSummaries()
    {
        return queueSummaries;
    }

    public Integer getPilotNumber()
    {
        return pilotNumber;
    }

    public void setPilotNumber(Integer pilotNumber)
    {
        this.pilotNumber = pilotNumber;
    }

    public Integer getHuntGroupId()
    {
        return huntGroupId;
    }

    public void setHuntGroupId(Integer huntGroupId)
    {
        this.huntGroupId = huntGroupId;
    }



    @Override
    public boolean equals(Object o)
    {
        if (o instanceof HuntGroup)
        {

            HuntGroup that = (HuntGroup) o;

            return new EqualsBuilder()
                            .append(getPilotNumber(), that.getPilotNumber())
                            .append(getHuntGroupId(), that.getHuntGroupId())
                            .isEquals();
        }
        return false;
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder()
                    .append(getPilotNumber())
                    .append(getHuntGroupId())
                    .toHashCode();
    }
}
