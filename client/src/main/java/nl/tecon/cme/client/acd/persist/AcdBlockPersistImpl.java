package nl.tecon.cme.client.acd.persist;

import nl.tecon.cme.client.acd.dto.AcdBlock;
import nl.tecon.cme.client.persistence.summary.dao.*;
import nl.tecon.cme.common.domain.summary.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author thies (Thies Edeling - thies@te-con.nl)
 *         Created on: 12/29/10 - 11:02 AM
 */
@Repository(value = "acdBlockPersist")
public class AcdBlockPersistImpl implements AcdBlockPersist
{
    @Autowired
    private HuntGroupDao huntGroupDao;

    @Autowired
    private HuntGroupSummaryDao huntGroupSummaryDao;

    @Autowired
    private AgentDao agentDao;

    @Autowired
    private AgentSummaryDao agentSummaryDao;

    @Autowired
    private QueueSummaryDao queueSummaryDao;

    @Override
    public void persistAcdBlocks(List<AcdBlock> blocks)
    {
        for (AcdBlock block : blocks)
        {
            persistAcdBlock(block);
        }
    }

    private void persistAcdBlock(AcdBlock block)
    {
        for (HuntGroup huntGroup : block.getHuntGroups())
        {
            persistHuntGroup(huntGroup);
            persistHuntGroupSummaries(huntGroup);
            persistQueueSummaries(huntGroup);
            persistAgentSummaries(huntGroup);
        }
    }

    private <T extends AbstractSummary> boolean persistSummary(SummaryDao<T> dao, T summary)
    {
        boolean wasPersisted = false;

        Integer duplicates = dao.findDuplicateCount(summary);

        if (duplicates == null || duplicates == 0)
        {
            dao.persist(summary);

            wasPersisted = true;
        }

        return wasPersisted;
    }


    private void persistAgentSummaries(HuntGroup group)
    {
        for (AgentSummary summary : group.getAgentSummaries())
        {
            boolean persisted = persistSummary(agentSummaryDao, summary);

            if (persisted)
            {
                Integer count = agentDao.findDuplicateCount(summary.getAgentId());

                if (count == null || count == 0)
                {
                    agentDao.persistAgent(summary.getAgent());
                }
            }
        }
    }

    private void persistQueueSummaries(HuntGroup group)
    {
        for (QueueSummary summary : group.getQueueSummaries())
        {
            persistSummary(queueSummaryDao, summary);
        }
    }


    private void persistHuntGroupSummaries(HuntGroup group)
    {
        for (HuntGroupSummary summary : group.getHuntGroupSummaries())
        {
            persistSummary(huntGroupSummaryDao, summary);
        }
    }

    private void persistHuntGroup(HuntGroup huntGroup)
    {
        Integer count= huntGroupDao.findDuplicateCount(huntGroup.getHuntGroupId());

        if (count == null || count == 0)
        {
            huntGroupDao.persistHuntGroup(huntGroup);
        }
    }

    public void setHuntGroupDao(HuntGroupDao huntGroupDao)
    {
        this.huntGroupDao = huntGroupDao;
    }

    public void setHuntGroupSummaryDao(HuntGroupSummaryDao huntGroupSummaryDao)
    {
        this.huntGroupSummaryDao = huntGroupSummaryDao;
    }

    public void setAgentDao(AgentDao agentDao)
    {
        this.agentDao = agentDao;
    }

    public void setAgentSummaryDao(AgentSummaryDao agentSummaryDao)
    {
        this.agentSummaryDao = agentSummaryDao;
    }

    public void setQueueSummaryDao(QueueSummaryDao queueSummaryDao)
    {
        this.queueSummaryDao = queueSummaryDao;
    }
}
