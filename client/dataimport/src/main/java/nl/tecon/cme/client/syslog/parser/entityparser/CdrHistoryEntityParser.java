package nl.tecon.cme.client.syslog.parser.entityparser;

import nl.tecon.cme.client.common.ImportResultHelper;
import nl.tecon.cme.client.common.ParseContext;
import nl.tecon.cme.client.persistence.cdr.dao.CdrDao;
import nl.tecon.cme.client.syslog.RawCdr;
import nl.tecon.cme.common.domain.cdr.Cdr;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import java.util.List;
import java.util.Map;

/**
 * User: x082062 (Thies Edeling - thies@te-con.nl)
 * Date: 12/20/10 5:21 PM
 */
public class CdrHistoryEntityParser implements EntityParser
{
    private static final Logger LOG = Logger.getLogger(CdrHistoryEntityParser.class);

    private static final Map<String, CdrHistoryDefinition> DEFINITION_MAP;
    private static final String INVALID_CONNECTION_ID = "0000";

    static
    {
        DEFINITION_MAP = EntityParserUtil.createDefinitionMap(CdrHistoryDefinition.values());
    }

    @Override
    public void parseRecord(RawCdr rawCdr)
    {
        Cdr cdr = new Cdr();

        cdr.setOriginalRecord(rawCdr.getRawCdr());

        String[] splittedCdr = rawCdr.getSplittedWithoutType();

        List<String> errors = EntityParserUtil.setValuesForRecord(splittedCdr, cdr, DEFINITION_MAP, ' ');

        if (INVALID_CONNECTION_ID.equalsIgnoreCase(cdr.getConnectionId()))
        {
            LOG.warn("Discarding connectionId: 0000. CDR: " + rawCdr.getRawCdr());
            ImportResultHelper.incWarningCount();
        } else if (cdr.getConnectionId() == null || !errors.isEmpty())
        {
            String joinedErrors = StringUtils.join(errors.toArray(), ";");

            LOG.error("Failed to parse CDR: " + rawCdr.getRawCdr() + ". Errors: " + joinedErrors);
            ImportResultHelper.incFailureCount();
        } else
        {
            persistCdr(cdr);
            ImportResultHelper.incSuccessCount();
        }
    }

    private void persistCdr(Cdr cdr)
    {
        ApplicationContext context = ParseContext.getSpringContext();
        CdrDao cdrDao = context.getBean(CdrDao.class);
        cdrDao.persistCdr(cdr);
    }
}
