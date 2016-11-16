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
    private ComboBox<?> cbSEXE;
    @FXML
    private ComboBox<?> cbNATIONALITE;
    @FXML
    private ComboBox<?> cbREPERTOIRE;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void hBtnAnnuler(ActionEvent event) {
    }

    @FXML
    private void hBtnModifier(ActionEvent event) {
    }
    
}
