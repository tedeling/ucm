package nl.tecon.cme.client.persistence.cdr.dao;

import nl.tecon.cme.common.domain.cdr.Cdr;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author thies (thies@te-con.nl)
 *         Date: 12/18/10 11:04 PM
 */
public interface CdrDao
{
    void persistCdr(Cdr cdr);

    List<Cdr> findCdr(String connectionId);
}
