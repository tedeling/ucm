package nl.tecon.cme.common.domain.cdr;

import java.io.Serializable;

/**
 * @author thies (Thies Edeling - thies@te-con.nl)
 *         Created on: 12/23/10 - 11:30 AM
 */
public abstract class AbstractCdr implements Serializable
{
    private static final long serialVersionUID = -8935896069412371088L;

    private String connectionId;

    public String getConnectionId()
    {
        return connectionId;
    }

    public void setConnectionId(String connectionId)
    {
        this.connectionId = connectionId;
    }
}
