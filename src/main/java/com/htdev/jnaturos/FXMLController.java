package com.htdev.jnaturos;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
    //variables globales 
    Stage monStage; //stage de la fenêtre principale
    Database db; //objet base de données
    List<Hyperlink> liens= new ArrayList<>(); //liste des patients récents...
    Patient patientCourant=null;
    String visiteCourante="-1";
    
    
    //constantes
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
     * Ajout d'hyperLink dans la liste des patients récents...
     * @param text
     * @param text0 
     */ 
    private void addHLPatients(String text, String num) {
        
       Hyperlink tmp=new Hyperlink(num+"-"+text);
       liens.add(tmp); //ajouter l'hyperlink à l'arrayList
       vbPatients.getChildren().add(tmp); //ajouter l'hyperlink à la Vbox
       liens.stream().forEach((hyperlink) -> { //et creer le lien vers ce patient
           hyperlink.setOnAction((ActionEvent t) -> {
               //récuperer le numéro du patient...
               //ouvrir la base de données sur les data du patient...     
               //afficher les données sur le bandeau patient...
               charge_et_affiche_patient(hyperlink.getText().substring(0,hyperlink.getText().indexOf('-')));
           });
        });     
    }
    
    
    /**
     * Transfert du stage principal...
     * @param stage 
     * @param db 
     */
    public void setStage(Stage stage, Database db){
        monStage=stage;
        this.db=db;
    }
    
    /**
     * Initialisation du controleur au démarrage
     * @param url
     * @param rb 
     */
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
                // ... appel de CANCEL on ne fait rien
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
        //creer un objet patient courant
        patientCourant=new Patient();
        
        //ajout d'un patient fitif 
        //DELETE
        addHLPatients("Tondeur Hervé", "1");
    }

    /**
     * Charger le patient dont le numero est passé en paramétres
     * @param numero 
     */
    private void charge_et_affiche_patient(String numero){
        try {
            System.out.println("select * from patient where id="+numero.trim());
            db.query("select * from patient where id="+numero.trim());
            while (db.getDB().next()){
                patientCourant.setID(db.getDB().getInt("ID"));
                patientCourant.setNOM(db.getDB().getString("NOM"));
                patientCourant.setNOMMAR(db.getDB().getString("NOMMAR"));
                patientCourant.setPRENOM(db.getDB().getString("PRENOM"));
                patientCourant.setDDN(db.getDB().getDate("DDN"));
                patientCourant.setSEXE(db.getDB().getString("SEXE"));
                patientCourant.setNATIONALITE(db.getDB().getString("NATIONALITE"));
                patientCourant.setADRESSE1(db.getDB().getString("ADRESSE1"));
                patientCourant.setADRESSE2(db.getDB().getString("ADRESSE2"));
                patientCourant.setCP(db.getDB().getString("CP"));
                patientCourant.setVILLE(db.getDB().getString("VILLE"));
                patientCourant.setTEL1(db.getDB().getString("TEL1"));
                patientCourant.setTEL2(db.getDB().getString("TEL2"));
                patientCourant.setEMAIL1(db.getDB().getString("EMAIL1"));
                patientCourant.setEMAIL2(db.getDB().getString("EMAIL2"));
                patientCourant.setNUMSS(db.getDB().getString("NUMSS"));
                patientCourant.setNOMCAISSE(db.getDB().getString("NOMCAISSE"));
                patientCourant.setPROFESSION(db.getDB().getString("PROFESSION"));
                patientCourant.setCOMMENTAIRES(db.getDB().getString("COMMENTAIRES"));
                patientCourant.setDATECREATION(db.getDB().getTimestamp("DATECREATION"));
                patientCourant.setREPERTOIRE(db.getDB().getInt("REPERTOIRE"));
            } 
            
            lbIDPATIENT.setText(""+patientCourant.getID());
            lbNOMPATIENT.setText(patientCourant.getNOM()+" "+patientCourant.getNOMMAR()+","+patientCourant.getPRENOM()+"("+patientCourant.getSEXE()+")");
            lbPROFESSIONPATIENT.setText(patientCourant.getPROFESSION());
            lbADRESSE1PATIENT.setText(patientCourant.getADRESSE1()+" "+patientCourant.getCP()+" "+patientCourant.getVILLE());
            lbADRESSE2PATIENT.setText(patientCourant.getADRESSE2());
            lbDATECREATION.setText(patientCourant.getDATECREATION().toString());
            lbDDNPATIENT.setText(patientCourant.getDDN().toString());
            lbTELEMAILPATIENT.setText(patientCourant.getTEL1()+"/"+patientCourant.getEMAIL1());
            
        } catch (SQLException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
    /**
     * Tester l'accés à la base de données [A SUPPRIMER]
     */
    //DELETE
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
