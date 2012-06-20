package nl.tecon.cme.client.persistence.summary.dao;

import nl.tecon.cme.common.domain.summary.AbstractSummary;

/**
 * @author thies (Thies Edeling - thies@te-con.nl)
 *         Created on: 1/25/11 - 2:41 PM
 */
public interface SummaryDao<T extends AbstractSummary>
{
    Integer findDuplicateCount(T summary);

    void persist(T summary);

}
