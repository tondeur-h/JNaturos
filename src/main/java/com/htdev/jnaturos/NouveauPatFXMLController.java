/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.htdev.jnaturos;

import java.net.URL;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author herve
 */
public class NouveauPatFXMLController implements Initializable {

     //paramétres du controleur principal
    FXMLController fx; //controlleur parent
    Database db; //objet database ouvert
    
    private String reponse=null; 
    
    @FXML
    private Button btnAnnuler;
    @FXML
    private Button btnEnregistrer;
    @FXML
    private TextField tfNOM;
    @FXML
    private TextField tfNOMMAR;
    @FXML
    private ComboBox<String> cbSEXE;
    @FXML
    private DatePicker dtDDN;
    @FXML
    private TextField tfPRENOM;
    @FXML
    private ComboBox<String> cbNATIONALITE;
    @FXML
    private TextField tfADRESSE1;
    @FXML
    private TextField tfCP;
    @FXML
    private TextField tfVILLE;
    @FXML
    private TextField tfEMAIL1;
    @FXML
    private TextField tfEMAIL2;
    @FXML
    private TextField tfNUMSS;
    @FXML
    private TextField tfNOMCAISSE;
    @FXML
    private TextField tfPROFESSION;
    @FXML
    private TextArea tfCOMMENTAIRES;
    @FXML
    private ComboBox<RepertoireBean> cbREPERTOIRE;
    
    //ol pour les combobox...
    ObservableList<String> olSEXE=javafx.collections.FXCollections.observableArrayList();
    ObservableList<RepertoireBean> olREPERTOIRE=javafx.collections.FXCollections.observableArrayList();
    ObservableList<String> olNATIONALITE=javafx.collections.FXCollections.observableArrayList();
    @FXML
    private TextField tfADRESSE2;
    @FXML
    private TextField tfTEL1;
    @FXML
    private TextField tfTEL2;
    
     /**
     * récupérer les infos du controlleur appelant
     * @param fx
     * @param db 
     */
    public void setCaller(FXMLController fx, Database db){
        try {
            //recuperer le controlleur parent et l'objet bd parent...
            this.fx=fx;
            this.db=db;
            
            // initialiser le contenu des combobox
            //sexe
            olSEXE.addAll("F","M","I");
            cbSEXE.setItems(olSEXE);
            cbSEXE.getSelectionModel().selectFirst();
            
            //REPERTOIRE
            db.query("select ID, LABEL FROM REPERTOIRE order by ID");
            while (db.getDB().next()){
                olREPERTOIRE.add(new RepertoireBean(db.getDB().getString("LABEL"), db.getDB().getInt("ID")));
            }
            cbREPERTOIRE.setItems(olREPERTOIRE);
            cbREPERTOIRE.getSelectionModel().selectFirst();
            db.closeResultSet();
            db.closeStatement();
            
        } catch (SQLException ex) {
            Logger.getLogger(NouveauPatFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
            //NATIONALITE
            olNATIONALITE.addAll("FRA","SUI","ANG","ESP","ALL","ITA","POR","AUT");
            cbNATIONALITE.setItems(olNATIONALITE);
            cbNATIONALITE.getSelectionModel().selectFirst();
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
    //no init
    }    

    @FXML
    private void hBtnAnnuler(ActionEvent event) {
        fx.fermer_panel_sans_action();
    }

    @FXML
    private void hBtnEnregistrer(ActionEvent event) {
        try {
            //enregistrer en base...
            //controler presence du NOM et PRENOM
            if (tfNOM.getText().isEmpty() || tfPRENOM.getText().isEmpty() || dtDDN.getEditor().getText().isEmpty()){
                //ne pas continuer car NOM et PRENOM et DDN obligatoires
                Alert alert=new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Nouveau Patient - NOM/PRENOM/DDN");
                alert.setContentText("Il manque le NOM ou le PRENOM ou la DDN du patient\nCeux ci sont obligatoires...");
                alert.showAndWait();
                return; //sortir sans rien faire...
            }
            //convertir le format de la date...
           DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
           String DDN=dtDDN.getValue().format(formatter);
           
            
            //construire la requete d'insertion du nouveau patient
            String requeteInsertPatient="INSERT INTO PATIENT VALUES(DEFAULT,'"+tfNOM.getText()+"','"+tfPRENOM.getText()+"','"+tfNOMMAR.getText()+"','"+DDN+"','"+cbSEXE.getValue()+"','"+cbNATIONALITE.getValue()+"','"+tfADRESSE1.getText()+"','"+tfADRESSE2.getText()+"','"+tfCP.getText()+"','"+tfVILLE.getText()+"','"+tfTEL1.getText()+"','"+tfTEL2.getText()+"','"+tfEMAIL1.getText()+"','"+tfTEL2.getText()+"','"+tfNUMSS.getText()+"','"+tfNOMCAISSE.getText()+"','"+tfPROFESSION.getText()+"','"+tfCOMMENTAIRES.getText()+"',CURRENT_TIMESTAMP,"+cbREPERTOIRE.getSelectionModel().getSelectedItem().getNumREPERTOIRE()+")";
 
            db.insert(requeteInsertPatient);
            //recuperer son numero de patient pour transmettre au controleur parent.
            db.query("SELECT MAX(IDENTITY_VAL_LOCAL()) FROM PATIENT");
            db.getDB().next();
            reponse=db.getDB().getString(1);
            
            //fermer cette fenêtre
            fx.fermer_panel_getIDNP();
        } catch (SQLException ex) {
            Logger.getLogger(NouveauPatFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
