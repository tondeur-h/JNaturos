package com.htdev.jnaturos;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author herve
 */
public class RecherchePatFXMLController implements Initializable {

    @FXML
    private Button btnFermer;

    FXMLController fx;
    Database db;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void getCaller(FXMLController fx, Database db){
     this.fx=fx;  
     this.db=db;
    }
    
    
    /**
     * Fermer la fenêtre
     * @param event 
     */
    @FXML
    private void hBtnFermer(ActionEvent event) {
                fx.fermer_panel();
    }
    
}
