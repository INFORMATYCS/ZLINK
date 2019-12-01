/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zlink.bienvenida;

/**
 *
 * @author evert
 */
public class BienvenidaCommand {
    private InicialForm inicialForm;

    /**
     * 
     * @param inicialForm 
     */
    public BienvenidaCommand(InicialForm inicialForm) {
        this.inicialForm = inicialForm;
    }
    
    
    
    /**
     * Inicializa y carga configuracion
     */
    public void cargaPrograma(){
        
        
        InicializaAppRunnable inicializaAppRun = new InicializaAppRunnable(inicialForm.lblEstatus);
        
        new Thread(inicializaAppRun).start();
        
        inicialForm.lblEstatus.setText("asdasd");
    }
}
