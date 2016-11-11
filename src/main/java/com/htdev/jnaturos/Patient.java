/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.htdev.jnaturos;

import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author herve
 */
public class Patient {
   private int ID;
   private String NOM;
   private String PRENOM;
   private String NOMMAR;
   private Date DDN;
   private String SEXE;
   private String NATIONALITE;
   private String ADRESSE1;
   private String ADRESSE2;
   private String CP;
   private String VILLE;
   private String TEL1;
   private String TEL2;
   private String EMAIL1;
   private String EMAIL2;
   private String NUMSS;
   private String NOMCAISSE;
   private String PROFESSION;
   private String COMMENTAIRES;
   private Timestamp DATECREATION;
   private int REPERTOIRE; 

   public void clear(){
       ID=-1;
   }
   
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNOM() {
        return NOM;
    }

    public void setNOM(String NOM) {
        this.NOM = NOM;
    }

    public String getPRENOM() {
        return PRENOM;
    }

    public void setPRENOM(String PRENOM) {
        this.PRENOM = PRENOM;
    }

    public String getNOMMAR() {
        return NOMMAR;
    }

    public void setNOMMAR(String NOMMAR) {
        this.NOMMAR = NOMMAR;
    }

    public Date getDDN() {
        return DDN;
    }

    public void setDDN(Date DDN) {
        this.DDN = DDN;
    }

    public String getSEXE() {
        return SEXE;
    }

    public void setSEXE(String SEXE) {
        this.SEXE = SEXE;
    }

    public String getNATIONALITE() {
        return NATIONALITE;
    }

    public void setNATIONALITE(String NATIONALITE) {
        this.NATIONALITE = NATIONALITE;
    }

    public String getADRESSE1() {
        return ADRESSE1;
    }

    public void setADRESSE1(String ADRESSE1) {
        this.ADRESSE1 = ADRESSE1;
    }

    public String getADRESSE2() {
        return ADRESSE2;
    }

    public void setADRESSE2(String ADRESSE2) {
        this.ADRESSE2 = ADRESSE2;
    }

    public String getCP() {
        return CP;
    }

    public void setCP(String CP) {
        this.CP = CP;
    }

    public String getVILLE() {
        return VILLE;
    }

    public void setVILLE(String VILLE) {
        this.VILLE = VILLE;
    }

    public String getTEL1() {
        return TEL1;
    }

    public void setTEL1(String TEL1) {
        this.TEL1 = TEL1;
    }

    public String getTEL2() {
        return TEL2;
    }

    public void setTEL2(String TEL2) {
        this.TEL2 = TEL2;
    }

    public String getEMAIL1() {
        return EMAIL1;
    }

    public void setEMAIL1(String EMAIL1) {
        this.EMAIL1 = EMAIL1;
    }

    public String getEMAIL2() {
        return EMAIL2;
    }

    public void setEMAIL2(String EMAIL2) {
        this.EMAIL2 = EMAIL2;
    }

    public String getNUMSS() {
        return NUMSS;
    }

    public void setNUMSS(String NUMSS) {
        this.NUMSS = NUMSS;
    }

    public String getNOMCAISSE() {
        return NOMCAISSE;
    }

    public void setNOMCAISSE(String NOMCAISSE) {
        this.NOMCAISSE = NOMCAISSE;
    }

    public String getPROFESSION() {
        return PROFESSION;
    }

    public void setPROFESSION(String PROFESSION) {
        this.PROFESSION = PROFESSION;
    }

    public String getCOMMENTAIRES() {
        return COMMENTAIRES;
    }

    public void setCOMMENTAIRES(String COMMENTAIRES) {
        this.COMMENTAIRES = COMMENTAIRES;
    }

    public Timestamp getDATECREATION() {
        return DATECREATION;
    }

    public void setDATECREATION(Timestamp DATECREATION) {
        this.DATECREATION = DATECREATION;
    }

    public int getREPERTOIRE() {
        return REPERTOIRE;
    }

    public void setREPERTOIRE(int REPERTOIRE) {
        this.REPERTOIRE = REPERTOIRE;
    }

    public Patient() {
    this.ID=-1;
    }

    
    
    public Patient(int ID, String NOM, String PRENOM, String NOMMAR, Date DDN, String SEXE, String NATIONALITE, String ADRESSE1, String ADRESSE2, String CP, String VILLE, String TEL1, String TEL2, String EMAIL1, String EMAIL2, String NUMSS, String NOMCAISSE, String PROFESSION, String COMMENTAIRES, Timestamp DATECREATION, int REPERTOIRE) {
        this.ID = ID;
        this.NOM = NOM;
        this.PRENOM = PRENOM;
        this.NOMMAR = NOMMAR;
        this.DDN = DDN;
        this.SEXE = SEXE;
        this.NATIONALITE = NATIONALITE;
        this.ADRESSE1 = ADRESSE1;
        this.ADRESSE2 = ADRESSE2;
        this.CP = CP;
        this.VILLE = VILLE;
        this.TEL1 = TEL1;
        this.TEL2 = TEL2;
        this.EMAIL1 = EMAIL1;
        this.EMAIL2 = EMAIL2;
        this.NUMSS = NUMSS;
        this.NOMCAISSE = NOMCAISSE;
        this.PROFESSION = PROFESSION;
        this.COMMENTAIRES = COMMENTAIRES;
        this.DATECREATION = DATECREATION;
        this.REPERTOIRE = REPERTOIRE;
    }
}
