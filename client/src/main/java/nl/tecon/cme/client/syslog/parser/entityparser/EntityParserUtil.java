package nl.tecon.cme.client.syslog.parser.entityparser;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: x082062 (Thies Edeling - thies@te-con.nl)
 * Date: 12/22/10 11:30 AM
 */
final class EntityParserUtil
{
    private EntityParserUtil()
    {
    }


    static <T extends SysLogColumnDefinition<?>> Map<String, T> createDefinitionMap(T[] definitions)
    {
        Map<String, T> map = new HashMap<String, T>();

        for (T definition : definitions)
        {
            map.put(definition.getKey(), definition);
        }

        return map;
    }

    static <T, D extends SysLogColumnDefinition<T>> List<String> setValuesForRecord(String[] rawRecord, T entity, Map<String, D> definitions, char keyValueSeparator)
    {
        List<String> errors = new ArrayList<String>();

        for (String rawColumn : rawRecord)
        {
            String column = rawColumn.trim();

            int separatorIndex = column.indexOf(keyValueSeparator);

            if (separatorIndex < 0)
            {
                continue;
            }

            String value = column.substring(separatorIndex + 1);
            String trimmed = value.trim();

            if (StringUtils.isNotBlank(trimmed))
            {
                String key = column.substring(0, separatorIndex).trim();
                D definition = definitions.get(key);

                try
                {
                    if (definition != null)
                    {
                        definition.setValue(trimmed, entity);
                    }
                } catch (Exception e)
                {
                    errors.add(definition.name() + ", value: " + trimmed + ", error: " + e.getMessage());
                }

            }
        }


        return errors;
    }
}
