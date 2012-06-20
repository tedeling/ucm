package nl.tecon.cme.client.acd.impl;

import nl.tecon.cme.client.acd.AcdImport;
import nl.tecon.cme.client.acd.dto.AcdBlock;
import nl.tecon.cme.client.acd.persist.AcdBlockPersist;
import nl.tecon.cme.client.common.ImportResultHelper;
import org.apache.commons.lang.Validate;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;

/**
 * @author thies (Thies Edeling - thies@te-con.nl)
 *         Created on: 12/23/10 - 12:00 PM
 */
@Service
public class AcdImportImpl implements AcdImport
{
    private static final Logger LOG = Logger.getLogger(AcdImportImpl.class);

    @Value("${acd.directory}")
    private String acdDirectory;

    @Autowired
    private AcdBlockPersist blockPersister;

    @Override
    public int parseAcdFiles() throws FileNotFoundException
    {
        AcdFileParser parser = new AcdFileParser();

        Validate.notNull(acdDirectory, "ACD directory can't be empty");
        RecursiveCsvFileIterator iterator = new RecursiveCsvFileIterator(acdDirectory);

        int blocksFound = 0;

        while (iterator.hasNext())
        {
            File file = iterator.next();

            try
            {
                long startTimestamp = new Date().getTime();

                List<AcdBlock> blocks = parser.parseAcdFile(file);
                blockPersister.persistAcdBlocks(blocks);

                blocksFound += blocks.size();

                long duration = new Date().getTime() - startTimestamp;

                LOG.info("Parsed ACD " + file.getAbsolutePath() + " in " + duration + "ms");

            } catch (Exception e)
            {
                ImportResultHelper.incFailureCount();
                LOG.error("Failed to import " + file.getAbsolutePath(), e);
            }
        }

        return blocksFound;
    }
}
