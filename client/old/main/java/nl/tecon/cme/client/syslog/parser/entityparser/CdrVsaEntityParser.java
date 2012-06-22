package nl.tecon.cme.client.syslog.parser.entityparser;

import nl.tecon.cme.client.common.ImportResultHelper;
import nl.tecon.cme.client.common.ParseContext;
import nl.tecon.cme.client.persistence.cdr.dao.CdrVsaDao;
import nl.tecon.cme.client.syslog.RawCdr;
import nl.tecon.cme.common.domain.cdr.CdrVsa;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import java.util.List;
import java.util.Map;

/**
 * @author thies (thies@te-con.nl)
 *         Date: 12/23/10 12:21 AM
 */
public class CdrVsaEntityParser implements EntityParser
{
    private static final Logger LOG = Logger.getLogger(CdrVsaEntityParser.class);

    private static final Map<String, CdrVsaDefinition> DEFINITION_MAP;
    private static final String INVALID_CONNECTION_ID = "0000";

    static
    {
        DEFINITION_MAP = EntityParserUtil.createDefinitionMap(CdrVsaDefinition.values());
    }

    @Override
    public void parseRecord(RawCdr rawCdr)
    {
        CdrVsa cdrVsa = new CdrVsa();

        cdrVsa.setOriginalRecord(rawCdr.getRawCdr());

        String[] splittedCdr = rawCdr.getSplittedWithoutType();

        List<String> errors = EntityParserUtil.setValuesForRecord(splittedCdr, cdrVsa, DEFINITION_MAP, ':');

        if (INVALID_CONNECTION_ID.equalsIgnoreCase(cdrVsa.getConnectionId()))
        {
            LOG.warn("Discarding connectionId: 0000. CDR: " + rawCdr.getRawCdr());
            ImportResultHelper.incWarningCount();
        } else if (cdrVsa.getConnectionId() == null || !errors.isEmpty())
        {
            String joinedErrors = StringUtils.join(errors.toArray(), ";");

            LOG.error("Failed to parse CDR: " + rawCdr.getRawCdr() + ". Errors: " + joinedErrors);
            ImportResultHelper.incFailureCount();
        } else
        {
            persistCdrVsa(cdrVsa);
            ImportResultHelper.incSuccessCount();
        }
    }

    private void persistCdrVsa(CdrVsa cdrVsa)
    {
        // uh oh this looks like a service locator pattern
        ApplicationContext context = ParseContext.getSpringContext();
        CdrVsaDao cdrVsaDao = context.getBean(CdrVsaDao.class);
        cdrVsaDao.persistCdrVsa(cdrVsa);
    }
}
