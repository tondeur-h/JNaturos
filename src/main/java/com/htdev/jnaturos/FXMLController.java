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
import javafx.stage.Stage;

public class FXMLController implements Initializable {
    
    Stage monStage;
    
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
    
  
    /**
     * Transfert du stage principal...
     * @param stage 
     */
    public void setStage(Stage stage){
        monStage=stage;
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        init_components();
        init_database();
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

    private void init_components() {
        //ouvrir par défaut le menu patient au démarrage...
        MenuPatients.setExpanded(true);
    }

    private void init_database() {
        try {
            //connecter la base de données
            Database db=new Database("/home/herve/dev/jNaturos/Naturos", "herve", "herve",null,0,true,false);
            //Database db=new Database("/home/herve/dev/HTTEST", "herve", "herve","localhost",0,false,false);
            
            if (db.connect()) {System.out.println("Naturos est connectée");} else {System.out.println("Naturos n'est pas connectée!");System.exit(0x01);}
    
            db.setSchema("APP");
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
            
            db.close();
            db.disconnect();
            
        } catch (SQLException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
