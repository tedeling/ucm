package nl.tecon.cme.client.acd.impl;

import nl.tecon.cme.client.acd.dto.AcdBlock;
import nl.tecon.cme.client.acd.parser.AcdRowParser;
import nl.tecon.cme.client.acd.parser.AcdRowParserFactory;
import nl.tecon.cme.client.common.DateParserUtil;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.springframework.util.Assert;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author thies (Thies Edeling - thies@te-con.nl)
 *         Created on: 12/23/10 - 12:47 PM
 */
public class AcdFileParser
{
    private static final int READ_AHEAD_LIMIT = 1024;

    public List<AcdBlock> parseAcdFile(File file) throws IOException
    {
        Assert.isTrue(file.canRead());

        FileReader fileReader = null;
        BufferedReader reader = null;

        try
        {
            fileReader = new FileReader(file);
            reader = new BufferedReader(fileReader);

            return parseBlocks(reader);
        } finally
        {
            close(reader);
            close(fileReader);
        }
    }

    private List<AcdBlock> parseBlocks(BufferedReader reader) throws IOException
    {
        String line;

        List<AcdBlock> blocks = new ArrayList<AcdBlock>();

        while (!isLastLine(line = reader.readLine()))
        {
            String row = line.trim();

            if (isStartNewBlock(row))
            {
                AcdBlock block = parseBlock(reader, row);
                blocks.add(block);
            }
        }

        return blocks;
    }

    private AcdBlock parseBlock(BufferedReader reader, String dateLine) throws IOException
    {
        AcdBlock block = createNewBlock(dateLine);
        // skip the next empty line
        reader.readLine();

        String line;

        while (true)
        {
            reader.mark(READ_AHEAD_LIMIT);
            line = reader.readLine();

            if (isLastLine(line))
            {
                break;
            }

            String row = line.trim();

            if (isStartNewBlock(row))
            {
                reader.reset();
                break;
            }

            AcdRowParser parser = AcdRowParserFactory.getParser(row);
            parser.parseRowAndAddToBlock(row, block);
        }

        return block;
    }

    private boolean isLastLine(String line)
    {
        return line == null || StringUtils.isBlank(line) || getCommaCount(line) == 0;
    }


    private AcdBlock createNewBlock(String line)
    {
        String date = StringUtils.remove(line, ',');
        DateTime dateTime = DateParserUtil.parseDateAndTimeWithoutMs(date);

        return new AcdBlock(dateTime.toDateMidnight());
    }


    private boolean isStartNewBlock(String row)
    {
        int commaCount = getCommaCount(row);
        return (row.length() > 1 && commaCount == 1);
    }

    private int getCommaCount(String row)
    {
        return StringUtils.countMatches(row, ",");
    }

    private void close(Closeable closeable)
    {
        if (closeable != null)
        {
            try
            {
                closeable.close();
            } catch (IOException e)
            {
                // oh well
            }
        }
    }

}
