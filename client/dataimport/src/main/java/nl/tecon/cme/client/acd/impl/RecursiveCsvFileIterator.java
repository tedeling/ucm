package nl.tecon.cme.client.acd.impl;

import java.io.File;
import java.util.Iterator;
import java.util.Stack;

/**
 * @author thies (Thies Edeling - thies@te-con.nl)
 *         Created on: 1/25/11 - 12:45 AM
 */
public class RecursiveCsvFileIterator implements Iterator<File>
{
    private Stack<File> fileQueue;
    private Stack<File> dirQueue;

    public RecursiveCsvFileIterator(String path)
    {
        File root = new File(path);

        fileQueue = new Stack<File>();
        dirQueue = new Stack<File>();

        dirQueue.add(root);
    }

    @Override
    public boolean hasNext()
    {
        if (!dirQueue.isEmpty())
        {
            scanPath();
        }

        return !fileQueue.isEmpty();
    }

    @Override
    public File next()
    {
        if (!dirQueue.isEmpty())
        {
            scanPath();
        }

        return fileQueue.pop();
    }

    @SuppressWarnings("unchecked")
    private void scanPath()
    {
        while (!dirQueue.isEmpty())
        {
            File dir = dirQueue.pop();

            scanDir(dir);
        }
    }

    private void scanDir(File dir)
    {
        for (File file : dir.listFiles())
        {
            if (file.isDirectory())
            {
                dirQueue.push(file);
            } else if (file.isFile() && file.getName().toLowerCase().endsWith(".csv"))
            {
                fileQueue.push(file);
            }
        }
    }

    @Override
    public void remove()
    {
        throw new IllegalArgumentException("Not supported");
    }
}
