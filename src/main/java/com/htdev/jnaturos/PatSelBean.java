package com.htdev.jnaturos;

import javafx.beans.property.SimpleStringProperty;

/**
 * Bean qui sert dans les TableView de sélection des patients
 * 
 * @author herve
 */
public class PatSelBean{    

   private SimpleStringProperty IPP = new SimpleStringProperty();
   private SimpleStringProperty NOM = new SimpleStringProperty(); 
   private SimpleStringProperty NOMMAR = new SimpleStringProperty();
   private SimpleStringProperty PRENOM = new SimpleStringProperty();
   private SimpleStringProperty DDN = new SimpleStringProperty();

    public PatSelBean() {
    }

        
    public String getIPP() {
      return IPP.get();
   }
    
   public String getNOM() {
      return NOM.get();
   }

   public String getNOMMAR() {
      return NOMMAR.get();
   }

   public String getPRENOM() {
      return PRENOM.get();
   }

   public String getDDN() {
      return DDN.get();
   }

    public void setIPP(String patIPP) {
        if (patIPP==null) patIPP="-1";
        this.IPP.set(patIPP);
    }

    public void setNOM(String patNOM) {
        if (patNOM==null) patNOM="";
        this.NOM.set(patNOM);
    }

    public void setNOMMAR(String patNOMMAR) {
        if (patNOMMAR==null) patNOMMAR="";
        this.NOMMAR.set(patNOMMAR);
    }

    public void setPRENOM(String patPRENOM) {
        if (patPRENOM==null) patPRENOM="";
        this.PRENOM.set(patPRENOM);
    }

    public void setDDN(String patDDN) {
        if (patDDN==null) patDDN="";
        this.DDN.set(patDDN);
    }
}