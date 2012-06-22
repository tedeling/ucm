package nl.tecon.cme.client.persistence.summary.dao;

import nl.tecon.cme.common.domain.summary.HuntGroup;

/**
 * @author thies (Thies Edeling - thies@te-con.nl)
 *         Created on: 12/29/10 - 10:35 AM
 */
public interface HuntGroupDao
{
    Integer findDuplicateCount(Integer huntGroupId);

    void persistHuntGroup(HuntGroup huntGroup);
}
