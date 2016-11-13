package com.htdev.jnaturos;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author herve
 */
public class RecherchePatFXMLController implements Initializable {

    @FXML
    private Button btnAnnuler;

    //paramétres du controleur principal
    FXMLController fx;
    Database db;
    
    @FXML
    private DatePicker dpDDN;
    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfPrenom;
    @FXML
    private Button btnRecherche;
    @FXML
    private TableView<PatSelBean> tvListePat;
    @FXML
    private TableColumn<PatSelBean, String> colIPP;
    @FXML
    private TableColumn<PatSelBean, String> colNOM;
    @FXML
    private TableColumn<PatSelBean, String> colNOMMAR;
    @FXML
    private TableColumn<PatSelBean, String> colPRENOM;
    @FXML
    private TableColumn<PatSelBean, String> colDDN;
    @FXML
    private Button btnSelectioner;
    
    private ObservableList<PatSelBean> olPatSel=javafx.collections.FXCollections.observableArrayList();
    
    private String reponse=null;
    @FXML
    private Button BtnAnnulerDDN;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //bloquer le bouton Selectionner
        btnSelectioner.setDisable(true);
        dpDDN.setTooltip(new Tooltip("Date de naissance"));
        tfNom.setTooltip(new Tooltip("Nom du patient en majuscule"));
        tfPrenom.setTooltip(new Tooltip("Préom du patient en majuscule"));
        BtnAnnulerDDN.setTooltip(new Tooltip("Effacer la date..."));
        btnAnnuler.setTooltip(new Tooltip("Fermer sans sélectionner"));
        btnRecherche.setTooltip(new Tooltip("Rechercher les patients selon les critères"));
        btnSelectioner.setTooltip(new Tooltip("Sélectionner le patient de la liste..."));
        dpDDN.setValue(LocalDate.of(1971, 1, 1)); //forcer la date au 1 Janver 1971
        dpDDN.setEditable(false);
        
        
        //creer les fabriques pour chaque colonnes
        colIPP.setCellValueFactory(new PropertyValueFactory<PatSelBean, String>("IPP")); 
        colNOM.setCellValueFactory(new PropertyValueFactory<PatSelBean, String>("NOM"));
        colNOMMAR.setCellValueFactory(new PropertyValueFactory<PatSelBean, String>("NOMMAR")); 
        colPRENOM.setCellValueFactory(new PropertyValueFactory<PatSelBean, String>("PRENOM")); 
        colDDN.setCellValueFactory(new PropertyValueFactory<PatSelBean, String>("DDN")); 
    }    

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
     * Fermer la fenêtre
     * @param event 
     */
    @FXML
    private void hBtnAnnuler(ActionEvent event) {
                fx.fermer_panel_sans_action();
    }

    /**
     * Bouton de recherche d'un patient
     * @param event 
     */
    @FXML
    private void hBtnRecherche(ActionEvent event) {
        try {
            String requetePat=null;
            //purger l'observableList
            olPatSel.clear();
            
            //creer le patient Selction en tant qu'objet
            PatSelBean ps=null;
            //construire la requête en fonction de ce qui sera renseigné dans les champs
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-DD");
            if (!dpDDN.getEditor().getText().isEmpty()){
            requetePat="select ID,NOM,NOMMAR,PRENOM,DDN FROM PATIENT WHERE NOM like '%"+tfNom.getText().trim().toUpperCase()+"%' AND PRENOM like '%"+tfPrenom.getText().trim().toUpperCase()+"%' and DDN='"+formatter.format(dpDDN.getValue())+"'";    
            }
            else
            {
            requetePat="select ID,NOM,NOMMAR,PRENOM,DDN FROM PATIENT WHERE NOM like '%"+tfNom.getText().trim().toUpperCase()+"%' AND PRENOM like '%"+tfPrenom.getText().trim().toUpperCase()+"%'";
            }
            
            //rechercher les patients demandés...
      
            db.query(requetePat);
            
            while (db.getDB().next()){
                ps=new PatSelBean();
                ps.setIPP(db.getDB().getString("ID"));
                ps.setNOM(db.getDB().getString("NOM"));
                ps.setNOMMAR(db.getDB().getString("NOMMAR"));
                ps.setPRENOM(db.getDB().getString("PRENOM"));
                ps.setDDN(db.getDB().getString("DDN"));
                
                //affecter le patient à l'observableList
                olPatSel.add(ps);
            }
            
            //affecter l'observable list à la table des patients...
            tvListePat.setItems(olPatSel);
        } catch (SQLException ex) {
            Logger.getLogger(RecherchePatFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    /**
     * clic sur sélection d'un patient
     * @param event 
     */
    @FXML
    private void hTVPatMC(MouseEvent event) {
        reponse=tvListePat.getSelectionModel().getSelectedItem().getIPP();
        if (reponse!=null && reponse.compareTo("-1")!=0) btnSelectioner.setDisable(false);
    }

    /**
     * clic sur le bouton sélectionner du dialogue
     * @param event 
     */
    @FXML
    private void hBtnSelectionner(ActionEvent event) {     
        //fermer et retourner l'ID du patient sélectionné
        fx.fermer_panel_getID();
    }

    /**
     * Annuler la date de naissance 
     * met a blanc la date pour la recherche
     * @param event 
     */
    @FXML
    private void hbtnAnnulerDDN(ActionEvent event) {
        //effacer la date DDN
        dpDDN.getEditor().clear();
    }
    
}
