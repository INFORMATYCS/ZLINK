/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import zlink.lib.ConfigApp;

/**
 *
 * @author evert
 */
public class InicializaAppRun implements Runnable{
    static final Logger logger = LogManager.getLogger(InicializaAppRun.class);
    private javax.swing.JLabel monitorEstatus;
    
    /**
     * 
     * @param monitorEstatus 
     */
    public InicializaAppRun(javax.swing.JLabel monitorEstatus){
        this.monitorEstatus = monitorEstatus;
    }
    
    /**
     * 
     */
    public void run() {
        ConfigApp configApp = new ConfigApp();

        try {
            this.monitorEstatus.setText("Cargando archivo de configuracion");
            configApp.loadProperties();

            this.monitorEstatus.setText("Buscando actualizaciones en la red");
            URL url = new URL("http://localhost/zlink/conta.php");

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
                for (String line; (line = reader.readLine()) != null;) {
                    System.out.println(line);
                }
            }

            
            logger.info(configApp.pSystem.getProperty("nameApp"));
            
            this.monitorEstatus.setText("Terminado");
        } catch (Exception e) {
            this.monitorEstatus.setText("Error durante el proceso."+e.getMessage());
            logger.error(e);
        }

        
    }
}
