package nl.tecon.cme.client.common;

import org.springframework.context.ApplicationContext;

/**
 * @author thies (Thies Edeling - thies@te-con.nl)
 *         Created on: 12/21/10 - 12:23 AM
 */
public final class ParseContext
{
    private static final ThreadLocal<ApplicationContext> SPRING_CONTEXT = new ThreadLocal<ApplicationContext>();
    private static final ThreadLocal<ImportResult> RESULT_CONTEXT = new ThreadLocal<ImportResult>();

    private ParseContext()
    {
    }

    public static ApplicationContext getSpringContext()
    {
        return SPRING_CONTEXT.get();
    }

    public static void setSpringContext(ApplicationContext context)
    {
        SPRING_CONTEXT.set(context);
    }

    public static ImportResult getImportResult()
    {
        ImportResult result = RESULT_CONTEXT.get();

        if (result == null)
        {
            result = new ImportResult();
            RESULT_CONTEXT.set(result);
        }

        return result;
    }
}
