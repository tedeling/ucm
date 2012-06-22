package nl.tecon.cme.client.common;

/**
 * @author thies (Thies Edeling - thies@te-con.nl)
 *         Created on: 12/23/10 - 11:05 AM
 */
public final class ImportResultHelper
{
    private ImportResultHelper()
    {
    }

    public static void incFailureCount()
    {
        ParseContext.getImportResult().incImportFailureCount();
    }

    public static void incWarningCount()
    {
        ParseContext.getImportResult().incImportWarningCount();
    }

    public static void incSuccessCount()
    {
        ParseContext.getImportResult().incImportSuccessCount();
    }

    public static void reset()
    {
        ParseContext.getImportResult().reset();
    }

}
