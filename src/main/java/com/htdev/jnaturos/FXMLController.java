package com.htdev.jnaturos;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FXMLController implements Initializable {
    
    Stage monStage; //stage de la fenêtre principale
    Database db; //objet base de données
    
    final String VIDE="";
    
    @FXML
    private TitledPane MenuPatients;
    @FXML
    private TitledPane MenuVisites;
    @FXML
    private TitledPane MenuBilanVital;
    @FXML
    private Label lbNOMPATIENT;
    @FXML
    private Label lbDDNPATIENT;
    @FXML
    private Label lbIDPATIENT;
    @FXML
    private Label lbADRESSE1PATIENT;
    @FXML
    private Label lbADRESSE2PATIENT;
    @FXML
    private Label lbTELEMAILPATIENT;
    @FXML
    private Label lbPROFESSIONPATIENT;
    @FXML
    private Label lbDATECREATION;
    @FXML
    private Button btnQuitter;
    @FXML
    private Hyperlink MnREcherchePatient;
    @FXML
    private Hyperlink mnNouveauPatient;
    @FXML
    private Hyperlink mnModifierPatient;
    @FXML
    private Label label;
    @FXML
    private VBox vbPatients;
    
  
    /**
     * Transfert du stage principal...
     * @param stage 
     * @param db 
     */
    public void setStage(Stage stage, Database db){
        monStage=stage;
        this.db=db;
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        init_components(); //mettre dans un etat X les composants
    }  

    /** 
     * Quitter la fenêtre principale...
     * @param event 
     */
    @FXML
    private void handleBtnQuitter(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle(monStage.getTitle());
            alert.setHeaderText("Vous avez demandé la fermeture de l'application JNaturos...");
            alert.setContentText("Est vous sur de celà ?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                // ... appel de OK on fait rien et on ferme l'application
                Platform.exit();
            } else {
                // ... appel de CANCEL on consomme l'evenement pour l'annuler
            }
    }

    @FXML
    private void hMnRecherchePatient(ActionEvent event) {
    }

    @FXML
    private void hMnNouveauPatient(ActionEvent event) {
    }

    @FXML
    private void hMnModifierPatient(ActionEvent event) {
    }

    /**
     * Initialise les composant du Stage principal au démarrage
     */
    private void init_components() {
        //ouvrir par défaut le menu patient au démarrage...
        MenuPatients.setExpanded(true);
        lbADRESSE1PATIENT.setText(VIDE);
        lbADRESSE2PATIENT.setText(VIDE);
        lbDATECREATION.setText(VIDE);
        lbDDNPATIENT.setText(VIDE);
        lbIDPATIENT.setText(VIDE);
        lbNOMPATIENT.setText(VIDE);
        lbPROFESSIONPATIENT.setText(VIDE);
        lbTELEMAILPATIENT.setText(VIDE);
        
       
        
    }

    
    /**
     * Tester l'accés à la base de données [A SUPPRIMER]
     */
    private void test_database() {
        try {
           
            System.out.println("Schema:"+db.schema());
            System.out.println("Catalogue:"+db.catalog());
            System.out.println(db.infoClients());
            System.out.println("URL:"+db.metaData().getURL());
            System.out.println("UserName:"+db.metaData().getUserName());
            System.out.println(db.getAllTables());
            
            db.query("select * from patient");
            while (db.getDB().next()){
                System.out.println(db.getDB().getString(2)+" "+db.getDB().getString(3));  
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
