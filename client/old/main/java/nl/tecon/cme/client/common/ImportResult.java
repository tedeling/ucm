package nl.tecon.cme.client.common;

/**
 * @author thies (Thies Edeling - thies@te-con.nl)
 *         Created on: 12/23/10 - 10:37 AM
 */
public class ImportResult
{
    private int importSuccessCount;
    private int importFailureCount;
    private int importWarningCount;


    public int getImportWarningCount()
    {
        return importWarningCount;
    }

    public void incImportWarningCount()
    {
        this.importWarningCount++;
    }

    public int getImportSuccessCount()
    {
        return importSuccessCount;
    }

    public void incImportSuccessCount()
    {
        this.importSuccessCount++;
    }

    public int getImportFailureCount()
    {
        return importFailureCount;
    }

    public void incImportFailureCount()
    {
        this.importFailureCount++;
    }

    public void reset()
    {
        importSuccessCount = 0;
        importFailureCount = 0;
        importWarningCount = 0;
    }
}
