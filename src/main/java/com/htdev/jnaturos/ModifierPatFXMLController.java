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
public class ModifierPatFXMLController implements Initializable {

    @FXML
    private TextField tfNOM;
    @FXML
    private TextField tfNOMMAR;
    @FXML
    private TextField tfPRENOM;
    @FXML
    private DatePicker dtDDN;
    @FXML
    private ComboBox<String> cbSEXE;
    @FXML
    private ComboBox<String> cbNATIONALITE;
    @FXML
    private ComboBox<RepertoireBean> cbREPERTOIRE;
    @FXML
    private TextField tfADRESSE1;
    @FXML
    private TextField tfADRESSE2;
    @FXML
    private TextField tfCP;
    @FXML
    private TextField tfVILLE;
    @FXML
    private TextField tfTEL1;
    @FXML
    private TextField tfTEL2;
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
    private Button btnAnnuler;
    @FXML
    private Button btnModifier;

    
    //ol pour les combobox...
    ObservableList<String> olSEXE=javafx.collections.FXCollections.observableArrayList();
    ObservableList<RepertoireBean> olREPERTOIRE=javafx.collections.FXCollections.observableArrayList();
    ObservableList<String> olNATIONALITE=javafx.collections.FXCollections.observableArrayList();
    
    //paramétres du controleur principal
    FXMLController fx; //controlleur parent
    Database db; //objet database ouvert
    PatientBean IDPatient=null; //ID du patient passé en paramètre
    
    private String reponse=null; 
    
    
     public void setCaller(FXMLController fx, Database db,PatientBean IDpat){
        try {
            //recuperer le controlleur parent et l'objet bd parent...
            this.fx=fx;
            this.db=db;
            this.IDPatient=IDpat;
            
            
            //verifier qu(il y a bien un patient sinon sortir
            if (IDPatient.getID().compareTo("-1")==0){
                Alert alert=new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Modifier un Patient...");
                alert.setContentText("Vous n'avez pas sélectionné de patients !");
                alert.showAndWait();
                fx.fermer_panel_sans_action();
            }
            
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
            
            //charger le patient dont l'ID à été passé e paramètre dnas les différents champs...
            tfNOM.setText(IDPatient.getNOM());
            tfPRENOM.setText(IDPatient.getPRENOM());
            tfNOMMAR.setText(IDPatient.getNOMMAR());
            dtDDN.getEditor().setText(IDPatient.getDDN());
            cbSEXE.setValue(IDPatient.getSEXE());
            //rechercher dans l'observable list le numero d'index de l'élément
            for (RepertoireBean rb:olREPERTOIRE){
              if (String.valueOf(rb.getNumREPERTOIRE()).compareTo(IDPatient.getREPERTOIRE())==0){
                cbREPERTOIRE.getSelectionModel().select(Integer.parseInt(IDPatient.getREPERTOIRE(),10));
              }  
            }
            //cbREPERTOIRE.setValue(IDPatient.getREPERTOIRE());
            cbNATIONALITE.setValue(IDPatient.getNATIONALITE());
            tfADRESSE1.setText(IDPatient.getADRESSE1());
            tfADRESSE2.setText(IDPatient.getADRESSE2());
            tfCP.setText(IDPatient.getCP());
            tfVILLE.setText(IDPatient.getVILLE());
            tfTEL1.setText(IDPatient.getTEL1());
            tfTEL2.setText(IDPatient.getTEL2());
            tfEMAIL1.setText(IDPatient.getEMAIL1());
            tfEMAIL2.setText(IDPatient.getEMAIL2());
            tfNUMSS.setText(IDPatient.getNUMSS());
            tfNOMCAISSE.setText(IDPatient.getNOMCAISSE());
            tfPROFESSION.setText(IDPatient.getPROFESSION());
            tfCOMMENTAIRES.setText(IDPatient.getCOMMENTAIRES());
            
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
        // ne rien faire
    }    

    
    @FXML
    private void hBtnAnnuler(ActionEvent event) {
        fx.fermer_panel_sans_action();
    }

    @FXML
    private void hBtnModifier(ActionEvent event) {

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
           
            
            //construire la requete d'update du patient
            String requeteUpdatePatient="UPDATE PATIENT SET NOM='"+tfNOM.getText()+"',PRENOM='"+tfPRENOM.getText()+"',NOMMAR='"+tfNOMMAR.getText()+"',DDN='"+DDN+"',SEXE='"+cbSEXE.getValue()+"',NATIONALITE='"+cbNATIONALITE.getValue()+"',ADRESSE1='"+tfADRESSE1.getText()+"',ADRESSE2='"+tfADRESSE2.getText()+"',CP='"+tfCP.getText()+"',VILLE='"+tfVILLE.getText()+"',TEL1='"+tfTEL1.getText()+"',TEL2='"+tfTEL2.getText()+"',EMAIL1='"+tfEMAIL1.getText()+"',EMAIL2='"+tfEMAIL2.getText()+"',NUMSS='"+tfNUMSS.getText()+"',NOMCAISSE='"+tfNOMCAISSE.getText()+"',PROFESSION='"+tfPROFESSION.getText()+"',COMMENTAIRES='"+tfCOMMENTAIRES.getText()+"',REPERTOIRE="+cbREPERTOIRE.getSelectionModel().getSelectedItem().getNumREPERTOIRE()+" WHERE ID="+IDPatient.getID();     
            db.insert(requeteUpdatePatient);
            //recuperer son numero de patient pour transmettre au controleur parent.
            reponse=IDPatient.getID();
            
            //fermer cette fenêtre
            fx.fermer_panel_getIDMP();
  
    }
    
}
