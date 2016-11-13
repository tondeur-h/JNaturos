/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class NouveauPatFXMLController implements Initializable {

     //paramétres du controleur principal
    FXMLController fx;
    Database db;
    private String reponse=null;
    @FXML
    private Button btnAnnuler;
    @FXML
    private Button btnEnregistrer;
    
     /**
     * récupérer les infos du controlleur appelant
     * @param fx
     * @param db 
     */
    public void setCaller(FXMLController fx, Database db){
     this.fx=fx;  
     this.db=db;
    }
    
     /**
     * retour de la sélection vers le controleur principal
     * @return 
     */
    public String getReponse(){
        return reponse;
    }
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void hBtnAnnuler(ActionEvent event) {
        fx.fermer_panel_sans_action();
    }

    @FXML
    private void hBtnEnregistrer(ActionEvent event) {
    }
    
}
