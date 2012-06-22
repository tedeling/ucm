package nl.tecon.cme.client.boot;

import nl.tecon.cme.client.acd.AcdImport;
import nl.tecon.cme.client.common.ImportResult;
import nl.tecon.cme.client.common.ImportResultHelper;
import nl.tecon.cme.client.common.ParseContext;
import nl.tecon.cme.client.syslog.SysLogImport;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;

/**
 * @author thies (Thies Edeling - thies@te-con.nl)
 *         Created on: 12/15/10 - 10:00 PM
 */
@Service
public class DataImport implements ApplicationContextAware
{
    private static final Logger LOG = Logger.getLogger(DataImport.class);
    private static final String DEFAULT_CONFIG_FILE = "acd.properties";

    @Autowired
    private SysLogImport sysLogImport;

    @Autowired
    private AcdImport acdImport;

    public DataImport()
    {
    }

    public static void main(String[] args)
    {
        LOG.info("CME CDR parsing client v0.3");

        setConfigLocation(args.length > 0 ? args[0] : null);

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("context-root.xml");
        DataImport dataImport = context.getBean(DataImport.class);
        dataImport.start();
    }

    private static void setConfigLocation(String filename)
    {
        String configFilename = (StringUtils.isBlank(filename)) ? DEFAULT_CONFIG_FILE : filename;

        LOG.info("Using configuration file " + configFilename);

        System.getProperties().put("acdConfig", configFilename);
    }

    public void start()
    {
        ImportResultHelper.reset();
        parseCdr();

        ImportResultHelper.reset();
        parseAcd();
    }

    // stage 1
    private void parseCdr()
    {
        LOG.info("*** Stage 1: Parsing CDR's from Syslog");
        sysLogImport.parseSysLog();

        ImportResult importResult = getResult();

        LOG.info("*** Stage 1: completed. Records successfully parsed: " + importResult.getImportSuccessCount() +
                ", failures: " + importResult.getImportFailureCount() +
                ", warnings: " + importResult.getImportWarningCount());
    }

    private void parseAcd()
    {
        LOG.info("*** Stage 2: Parsing ACD's");
        int blocksFound = 0;

        try
        {
            blocksFound = acdImport.parseAcdFiles();
        } catch (FileNotFoundException e)
        {
            LOG.info("*** " + e.getMessage());
        }

        ImportResult importResult = getResult();

        LOG.info("*** Stage 2: completed. Found " + blocksFound + " blocks. Individual records successfully parsed: " + importResult.getImportSuccessCount() +
                ", failures: " + importResult.getImportFailureCount() +
                ", warnings: " + importResult.getImportWarningCount());
    }

    private ImportResult getResult()
    {
        return ParseContext.getImportResult();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
    {
        ParseContext.setSpringContext(applicationContext);
    }

    void setSysLogImport(SysLogImport sysLogImport)
    {
        this.sysLogImport = sysLogImport;
    }

    void setAcdImport(AcdImport acdImport)
    {
        this.acdImport = acdImport;
    }
}
