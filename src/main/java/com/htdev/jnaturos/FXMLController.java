package com.htdev.jnaturos;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FXMLController implements Initializable {
    //variables globales 
    Stage monStage; //stage de la fenêtre principale
    Database db; //objet base de données
    List<Hyperlink> liens= new ArrayList<>(); //liste des patients récents...
    PatientBean patientCourant=null;
    String visiteCourante="-1";
    HashMap<TitledPane,Boolean > menuStatut;
    HashMap<String, String> patientFast; //liste d'accés rapide aux patients
    
    
    //controleur des dialogues
    RecherchePatFXMLController ctrlRP=null; //controleur dialogue recherche patient
    NouveauPatFXMLController ctrlNP=null; //controleur dialogue nouveau patient
    ModifierPatFXMLController ctrlMP=null; //controlleur dialogue modifier patient
    
    //constantes
    final String VIDE="";
    
    @FXML
    private TitledPane MenuPatients;
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
    private VBox vbPatients;
    @FXML
    private Pane paneCentral;
    @FXML
    private Hyperlink MnREcherchePatient;
    @FXML
    private Hyperlink mnNouveauPatient;
    @FXML
    private Hyperlink mnModifierPatient;
    @FXML
    private TitledPane MenuVisites;
    @FXML
    private TitledPane MenuBilanVital;
    @FXML
    private TitledPane MenuQuitter;
    @FXML
    private Hyperlink mnQuitter;
    @FXML
    private Hyperlink mnFermerPatient;
    
  
    
    /**
     * Ajout d'hyperLink dans la liste des patients récents...
     * @param nom
     * @param text0 
     */ 
    private void ajouter_Patients_Rapide(String nom, String num) {
       //rechercher si le patient est deja présent...
       boolean patientPresent=false;
        for (Map.Entry<String, String> entry : patientFast.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();            
            if (key.compareTo(num)==0 && value.compareTo(nom)==0) patientPresent=true;
        }
       
       //si oui sortir et ne rien faire
       if (!patientPresent){
       //ajouter celui ci dans la HashTable
       patientFast.put(num, nom);
           
       //creer l'hyperlink
       Hyperlink tmp=new Hyperlink(num+"-"+nom);
       
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
       } //fin ajout dans liste latérale
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
     * Lancer le dialogue de recherche d'un patient.
     * @param event
     * @throws Exception 
     */
    @FXML
    private void hMnRecherchePatient(ActionEvent event) throws Exception {
       //inserer la recherche patient
       FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RecherchePatFXML.fxml"));
       paneCentral.getChildren().add((Node)loader.load());
       ctrlRP = loader.getController();
       ctrlRP.setCaller(this, db);
       //masquer les menus actifs uniquement sauf Quitter!
       MenuPatients.setDisable(true); 
    }

    /**
     * Fermer le panneau courant 
     * toujours le numero 0 normalement
     */
    public void fermer_panel_sans_action(){
        paneCentral.getChildren().remove(0);
        //débloquer les menu
        init_menu();
    }

    
    /**
     * Fermer le panneau courant 
     * toujours le numero 0 normalement
     * et récuperer le numero ID du patient
     */
    public void fermer_panel_getID(){
        paneCentral.getChildren().remove(0);
        //débloquer les menu
        menuStatut.replace(MenuPatients, Boolean.FALSE);
        init_menu();
        //charger et afficher le patient sélectionné...
        charge_et_affiche_patient(ctrlRP.getReponse());
        ajouter_Patients_Rapide(patientCourant.getNOM()+" "+patientCourant.getPRENOM(),patientCourant.getID());
    }
    
    /**
     * Fermer le panneau courant 
     * toujours le numero 0 normalement
     * et récuperer le numero ID du patient
     */
    public void fermer_panel_getIDNP(){
        paneCentral.getChildren().remove(0);
        //débloquer les menu
        menuStatut.replace(MenuPatients, Boolean.FALSE);
        init_menu();
        //charger et afficher le patient sélectionné...
        charge_et_affiche_patient(ctrlNP.getReponse());
        ajouter_Patients_Rapide(patientCourant.getNOM()+" "+patientCourant.getPRENOM(),patientCourant.getID());
    }
    
     /**
     * Fermer le panneau courant 
     * toujours le numero 0 normalement
     * et récuperer le numero ID du patient
     */
    public void fermer_panel_getIDMP(){
        paneCentral.getChildren().remove(0);
        //débloquer les menu
        menuStatut.replace(MenuPatients, Boolean.FALSE);
        init_menu();
        //charger et afficher le patient sélectionné...
        charge_et_affiche_patient(ctrlMP.getReponse());
        ajouter_Patients_Rapide(patientCourant.getNOM()+" "+patientCourant.getPRENOM(),patientCourant.getID());
    }
    
    /**
     * Initialise le menu dans le dernier état sauvegardé.
     * ou modifié aprés une action particuliére
     */
    private void init_menu()
    {
        for (Map.Entry<TitledPane, Boolean> entry : menuStatut.entrySet()) {
            Boolean value = entry.getValue();
            TitledPane key = entry.getKey();
            if (value==Boolean.TRUE)
            {
                key.setDisable(true);
            }
            else
            {
                key.setDisable(false);
            }  
        }
    }
    
    
    /**
     * Initialise les composant du Stage principal au démarrage
     */
    private void init_components() {
        
        //etat des menu...
        menuStatut=new HashMap<>();
        menuStatut.put(MenuPatients, Boolean.FALSE);
        menuStatut.put(MenuVisites, Boolean.TRUE);
        menuStatut.put(MenuBilanVital, Boolean.TRUE);
        menuStatut.put(MenuQuitter, Boolean.FALSE);
        
        //init_menu
        init_menu();
        
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
        
        //creer un objet patient courant vide pour l'instant
        patientCourant=new PatientBean();
        
        //Préparation de la liste d'accés rapide des patients 
        patientFast=new HashMap<>();
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
                patientCourant.setID(""+db.getDB().getInt("ID"));
                patientCourant.setNOM(db.getDB().getString("NOM"));
                patientCourant.setNOMMAR(db.getDB().getString("NOMMAR"));
                patientCourant.setPRENOM(db.getDB().getString("PRENOM"));
                patientCourant.setDDN(db.getDB().getDate("DDN").toString());
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
                patientCourant.setDATECREATION(db.getDB().getTimestamp("DATECREATION").toString());
                patientCourant.setREPERTOIRE(""+db.getDB().getInt("REPERTOIRE"));
            } 
            
            lbIDPATIENT.setText(""+patientCourant.getID());
            lbNOMPATIENT.setText(patientCourant.getNOM()+" "+patientCourant.getNOMMAR()+","+patientCourant.getPRENOM()+"("+patientCourant.getSEXE()+")");
            lbPROFESSIONPATIENT.setText(patientCourant.getPROFESSION());
            lbADRESSE1PATIENT.setText(patientCourant.getADRESSE1()+" "+patientCourant.getCP()+" "+patientCourant.getVILLE());
            lbADRESSE2PATIENT.setText(patientCourant.getADRESSE2());
            lbDATECREATION.setText(patientCourant.getDATECREATION());
            lbDDNPATIENT.setText(patientCourant.getDDN());
            lbTELEMAILPATIENT.setText(patientCourant.getTEL1()+"/"+patientCourant.getEMAIL1());
            
        } catch (SQLException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
       
    /** 
     * Quitter la fenêtre principale...
     * @param event 
     */
    @FXML
    private void hMnQuitter(ActionEvent event) {
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
    private void hMnNouveauPatient(ActionEvent event) throws Exception {
      //inserer la recherche patient
       FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/NouveauPatFXML.fxml"));
       paneCentral.getChildren().add((Node)loader.load());
       ctrlNP = loader.getController();
       ctrlNP.setCaller(this, db);
       //masquer les menus actifs uniquement sauf Quitter!
       MenuPatients.setDisable(true);   
    }

    /**
     * appel du diaogue de modification d'un patient
     * celui en cours pour l'instant
     * on va passer pour cela le numero d'ID du patient
     * @param event
     * @throws IOException 
     */
    @FXML
    private void hMnModifierPatient(ActionEvent event) throws IOException {
         //inserer la recherche patient
       FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ModifierPatFXML.fxml"));
       paneCentral.getChildren().add((Node)loader.load());
       ctrlMP = loader.getController();
       ctrlMP.setCaller(this, db,patientCourant);
       //masquer les menus actifs uniquement sauf Quitter!
       MenuPatients.setDisable(true); 
    }
    
    
    /**
     * Fermer le patient en cours
     * on met -1 sur l'ID
     * @param event 
     */
    @FXML
    private void hMnFermerPatient(ActionEvent event) {
        if (patientCourant.getID().compareTo("-1")!=0)
            {
            Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle(monStage.getTitle());
            alert.setContentText("Etes vous sur de vouloir fermer\n le patient en cours ?");
            Optional<ButtonType> reponse= alert.showAndWait();
            if (reponse.get()==ButtonType.OK)
            {
                patientCourant.clear(); //met -1 sur ID patient
                //effacer les informations du bandeau patient
                lbADRESSE1PATIENT.setText(""); //effacer tous les champs du bandeau patient
                lbADRESSE2PATIENT.setText("");
                lbDATECREATION.setText("");
                lbDDNPATIENT.setText("");
                lbIDPATIENT.setText("");
                lbNOMPATIENT.setText("");
                lbPROFESSIONPATIENT.setText("");
                lbTELEMAILPATIENT.setText("");
                MenuVisites.setDisable(true); // réinitialiser les menus
                MenuBilanVital.setDisable(true);
                MenuPatients.setDisable(false);
            }
          }
    }
    
}
