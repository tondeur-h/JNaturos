package com.htdev.jnaturos;

import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author herve
 */
public class RecherchePatFXMLController implements Initializable {

    @FXML
    private Button btnFermer;

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
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //bloquer le bouton Selectionner
        btnSelectioner.setDisable(true);
        
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
    private void hBtnFermer(ActionEvent event) {
                fx.fermer_panel_sans_action();
    }

    /**
     * Bouton de recherche d'un patient
     * @param event 
     */
    @FXML
    private void hBtnRecherche(ActionEvent event) {
        try {
            //purger l'observableList
            olPatSel.clear();
            
            //creer le patient Selction en tant qu'objet
            PatSelBean ps=null;
            
            //rechercher les patients demandé...
            String requetePat="select ID,NOM,NOMMAR,PRENOM,DDN FROM PATIENT";
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
    
}
