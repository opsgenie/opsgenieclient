package com.ifountain.opsgenie.client.util;

import java.io.File;
import java.net.URL;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

/**
 * Created by IntelliJ IDEA.
 * User: ifountain-qj
 * Date: 9/12/12
 * Time: 4:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class ManifestUtils {
    
    public static Manifest loadManifest(Class manifestFileClass){
        try{
            URL jarFileLocation = manifestFileClass.getClassLoader().getResource(manifestFileClass.getName().replace(".", "/") + ".class");
            if(jarFileLocation != null){
                String jarFilePath = jarFileLocation.getFile();
                jarFilePath = jarFilePath.substring(0, jarFilePath.indexOf("!"));
                if(jarFilePath.startsWith("file:")){
                    jarFilePath = jarFilePath.substring(jarFilePath.indexOf(":")+1);
                }
                JarFile file = new JarFile(new File(jarFilePath));
                return file.getManifest();
            }
        }
        catch (Throwable ignored){
        }
        return new Manifest();
    }
}
