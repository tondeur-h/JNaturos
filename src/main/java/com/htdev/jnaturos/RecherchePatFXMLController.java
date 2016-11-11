package com.htdev.jnaturos;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

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
    @FXML
    private DatePicker dpDDN;
    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfPrenom;
    @FXML
    private Button btnRecherche;
    @FXML
    private TableView<?> tvListePat;
    @FXML
    private TableColumn<?, ?> colIPP;
    @FXML
    private TableColumn<?, ?> colNOM;
    @FXML
    private TableColumn<?, ?> colNOMMAR;
    @FXML
    private TableColumn<?, ?> colPRENOM;
    @FXML
    private TableColumn<?, ?> colDDN;
    @FXML
    private Button btnSelectioner;
    
    private ObservableList<Patient> olPatient=javafx.collections.FXCollections.observableArrayList();
    
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

    @FXML
    private void hBtnRecherche(ActionEvent event) {
    }

    @FXML
    private void hTVPatMC(MouseEvent event) {
    }

    @FXML
    private void hBtnSelectionner(ActionEvent event) {
    }
    
}
