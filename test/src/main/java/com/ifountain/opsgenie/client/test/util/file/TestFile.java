
/*
 * Created on Jan 21, 2008
 *
 */
package com.ifountain.opsgenie.client.test.util.file;

import java.io.File;

public class TestFile extends File
{

    public static final String TESTOUTPUT_DIR = "output/testing";
    
    public TestFile()
    {
        super(getFilePath(""));
    }
    public TestFile(String pathname)
    {
        super(getFilePath(pathname));
    }
    public TestFile(File targetLocation)
    {
        this(targetLocation.getPath());
    }
    private static String getFilePath(String fileName)
    {
        if(new File(fileName).isAbsolute())
        {
            return fileName;
        }
        
        if(!fileName.toLowerCase().replaceAll("\\\\", "/").startsWith(TESTOUTPUT_DIR.toLowerCase().replaceAll("\\\\", "/")))
        {
            fileName = TESTOUTPUT_DIR+"/"+fileName;
        }
        return fileName;
    }

}
