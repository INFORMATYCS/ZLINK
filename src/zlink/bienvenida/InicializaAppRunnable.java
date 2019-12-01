/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zlink.bienvenida;

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
public class InicializaAppRunnable implements Runnable {

    static final Logger logger = LogManager.getLogger(InicializaAppRunnable.class);
    private javax.swing.JLabel monitorEstatus;

    /**
     *
     * @param monitorEstatus
     */
    public InicializaAppRunnable(javax.swing.JLabel monitorEstatus) {
        this.monitorEstatus = monitorEstatus;
    }

    /**
     *
     */
    public void run() {
        ConfigApp configApp = new ConfigApp();
        int sleepVista = 500;
        try {
            Thread.sleep(1000);

            this.monitorEstatus.setText("Cargando archivo de configuracion");
            configApp.loadProperties();

            Thread.sleep(sleepVista);
            this.monitorEstatus.setText("Buscando actualizaciones en la red");
            URL url = new URL("http://localhost/zlink/conta.php");

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
                for (String line; (line = reader.readLine()) != null;) {
                    System.out.println(line);
                }
            }
            
            String nameApp = configApp.pSystem.getProperty("nameApp");
            this.monitorEstatus.setText("Lanzando aplicacion "+nameApp+", este un momento...");
            
            //~Ejecuta EXE
            try {                
                Runtime obj = Runtime.getRuntime();
                obj.exec(configApp.pSystem.getProperty("pathApp"));

            } catch (Exception e) {
                /* Se lanza una excepción si no se encuentra en ejecutable o el fichero no es ejecutable. */
            }            

            Thread.sleep(sleepVista);
            this.monitorEstatus.setText("Terminado");
            
            Thread.sleep(sleepVista);
            System.exit(0);
        } catch (Exception e) {
            this.monitorEstatus.setText("Error durante el proceso." + e.getMessage());
            logger.error(e);
        }

    }
}
