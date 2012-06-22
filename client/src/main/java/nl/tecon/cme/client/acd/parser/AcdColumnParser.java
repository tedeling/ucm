package nl.tecon.cme.client.acd.parser;

import nl.tecon.cme.client.common.ImportResultHelper;
import org.apache.log4j.Logger;

import java.lang.reflect.Field;
import java.util.List;

/**
 * User: x082062 (Thies Edeling - thies@te-con.nl)
 * Date: 12/24/10 2:41 PM
 */
class AcdColumnParser
{
    private static final Logger LOG = Logger.getLogger(AcdColumnParser.class);

    private final String[] properties;

    AcdColumnParser(String[] properties)
    {
        this.properties = properties.clone();
    }

    void parseColumns(Object targetObject, List<String> columns)
    {
        int i = 0;

        for (String column : columns)
        {
            String value = column.trim();

            Integer intValue = AcdRowParserUtil.parseSafelyToInteger(value);
            String property = properties[i++];

            if (intValue != null)
            {
                setValueInTargetObject(targetObject, intValue, property);
            }
        }
    }

    /**
     * sets the value in the targetObject on property.
     */
    private void setValueInTargetObject(Object targetObject, Integer intValue, String property)
    {
        try
        {
            Field field = targetObject.getClass().getDeclaredField(property);
            field.setAccessible(true);
            field.set(targetObject, intValue);

            ImportResultHelper.incSuccessCount();
        } catch (Exception e)
        {
            ImportResultHelper.incFailureCount();

            LOG.error("Can't set value " + intValue + "in property " + property + " of " + targetObject.getClass().getSimpleName(), e);
        }
    }

}
