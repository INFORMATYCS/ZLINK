/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zlink.lib;

import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author evert
 */
public class ConfigApp {

    static final Logger logger = LogManager.getLogger(ConfigApp.class);
    static public Properties pSystem;

    /**
     * Carga archivo de propiedades
     *
     * @throws IOException
     */
    public void loadProperties() throws IOException {
        logger.info("Cargando configuracion del sistema");

        pSystem = new Properties();

        File f = new File("config.properties");
        
        if (f.exists()) {
            pSystem.load(new FileReader("config.properties"));
        } else {
            pSystem.load(new FileReader("src/config.properties"));
        }
    }
    
    
    /**
     * 
     * @throws IOException 
     */
    public void loadRemote() throws IOException {
        String appRemote = pSystem.getProperty("appRemote");
    }
}
