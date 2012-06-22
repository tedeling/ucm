package nl.tecon.cme.client.persistence.cdr.dao;

import nl.tecon.cme.common.domain.cdr.CdrVsa;

import java.util.List;

/**
 * User: x082062 (Thies Edeling - thies@te-con.nl)
 * Date: 12/22/10 2:04 PM
 */
public interface CdrVsaDao
{
    public List<CdrVsa> findCdrVsa(String connectionId);

    public void persistCdrVsa(CdrVsa cdrVsa);
}
