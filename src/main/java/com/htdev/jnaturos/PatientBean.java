package com.htdev.jnaturos;

/**
 *
 * @author herve
 */
class PatientBean {

   private String ID;
   private String NOM;
   private String PRENOM;
   private String NOMMAR;
   private String DDN;
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
   private String DATECREATION;
   private String REPERTOIRE; 
    
    public PatientBean(String ID, String NOM, String PRENOM, String NOMMAR, String DDN, String SEXE, String NATIONALITE, String ADRESSE1, String ADRESSE2, String CP, String VILLE, String TEL1, String TEL2, String EMAIL1, String EMAIL2, String NUMSS, String NOMCAISSE, String PROFESSION, String COMMENTAIRES, String DATECREATION, String REPERTOIRE) {
        this.ID=ID;
        this.NOM=NOM;
        this.PRENOM=PRENOM;
        this.NOMMAR=NOMMAR;
        this.DDN=DDN;
        this.SEXE=SEXE;
        this.NATIONALITE=NATIONALITE;
        this.ADRESSE1=ADRESSE1;
        this.ADRESSE2=ADRESSE2;
        this.CP=CP;
        this.VILLE=VILLE;
        this.TEL1=TEL1;
        this.TEL2=TEL2;
        this.EMAIL1=EMAIL1;
        this.EMAIL2=EMAIL2;
        this.NUMSS=NUMSS;
        this.NOMCAISSE=NOMCAISSE;
        this.PROFESSION=PROFESSION;
        this.COMMENTAIRES=COMMENTAIRES;
        this.DATECREATION=DATECREATION;
        this.REPERTOIRE=REPERTOIRE;
    }

    
    public PatientBean() {
       clear();
    }
  
    
    
   public void clear(){
       ID="-1";
        NOM="";
        NOMMAR="";
        PRENOM="";
        DDN="";
        SEXE="";
        NATIONALITE="";
        ADRESSE1="";
        ADRESSE2="";
        CP="";
        VILLE="";
        TEL1="";
        TEL2="";
        EMAIL1="";
        EMAIL2="";
        NUMSS="";
        NOMCAISSE="";
        PROFESSION="";
        COMMENTAIRES="";
        DATECREATION="";
        REPERTOIRE="";
   }

   //-----------ID-------------------- 
//   public StringProperty IDProperty( {
//        return ID;
//    }

    public void setID(String ID) {
        this.ID=ID;
    }

    public String getID(){
        return this.ID;
    }
    
    
    //------------NOM---------------
    public String getNOM() {
        return NOM;
    }

    public void setNOM(String NOM) {
        this.NOM=NOM;
    }

//    public StringProperty NOMProperty({
//        return NOM;
//    }
    
    //---------PRENOM-----------------
    public String getPRENOM() {
        return PRENOM;
    }

    public void setPRENOM(String PRENOM) {
        this.PRENOM=PRENOM;
    }

//    public StringProperty PRENOMProperty({
//        return PRENOM;
//    }
    
    //-----------NOMMAR--------------
    public String getNOMMAR() {
        return NOMMAR;
    }

    public void setNOMMAR(String NOMMAR) {
        this.NOMMAR=NOMMAR;
    }

//    public StringProperty NOMMARProperty({
//        return NOMMAR;
//    }
    
    //----------DDN----------------
    public String getDDN() {
        return DDN;
    }

    public void setDDN(String DDN) {
        this.DDN=DDN;
    }

//    public StringProperty DDNProperty({
//        return DDN;
//    }
    
    //------------SEXE---------------
    public String getSEXE() {
        return SEXE;
    }

    public void setSEXE(String SEXE) {
        this.SEXE=SEXE;
    }

//    public StringProperty SEXEProperty({
//        return SEXE;
//    }
    
    //------------NATIONALITE----------
    public String getNATIONALITE() {
        return NATIONALITE;
    }

    public void setNATIONALITE(String NATIONALITE) {
        this.NATIONALITE=NATIONALITE;
    }

//    public StringProperty NATIONALITEProperty({
//        return NATIONALITE;
//    }
    
    //-----------ADRESSE1------------
    public String getADRESSE1() {
        return ADRESSE1;
    }

    public void setADRESSE1(String ADRESSE1) {
        this.ADRESSE1=ADRESSE1;
    }

//    public StringProperty ADRESSE1Property({
//        return ADRESSE1;
//    }
    
    //-----------ADRESSE2------------
    public String getADRESSE2() {
        return ADRESSE2;
    }

    public void setADRESSE2(String ADRESSE2) {
        this.ADRESSE1=ADRESSE2;
    }

//    public StringProperty ADRESSE2Property({
//        return ADRESSE2;
//    }

    //------------CP----------------
    public String getCP() {
        return CP;
    }

    public void setCP(String CP) {
        this.CP=CP;
    }

//    public StringProperty CPProperty({
//        return CP;
//    }
    
    //-----------VILLE-------------
    public String getVILLE() {
        return VILLE;
    }

    public void setVILLE(String VILLE) {
        this.VILLE=VILLE;
    }

//    public StringProperty VILLEProperty({
//        return VILLE;
//    }
    
    //-------------TEL1-----------------
    public String getTEL1() {
        return TEL1;
    }

    public void setTEL1(String TEL1) {
        this.TEL1=TEL1;
    }

//    public StringProperty TEL1Property({
//        return TEL1;
//    }
    
    //-------------TEL2-----------------
    public String getTEL2() {
        return TEL2;
    }

    public void setTEL2(String TEL2) {
        this.TEL2=TEL2;
    }

//    public StringProperty TEL2Property({
//        return TEL2;
//    }
    
    //-------------EMAIL1-----------------
    public String getEMAIL1() {
        return EMAIL1;
    }

    public void setEMAIL1(String EMAIL1) {
        this.EMAIL1=EMAIL1;
    }

//    public StringProperty EMAIL1Property({
//        return EMAIL1;
//    }
    
    //-------------EMAIL2-----------------
    public String getEMAIL2() {
        return EMAIL2;
    }

    public void setEMAIL2(String EMAIL2) {
        this.EMAIL2=EMAIL2;
    }

//    public StringProperty EMAIL2Property({
//        return EMAIL2;
//    }
    
    //---------------NUMSS------------
    public String getNUMSS() {
        return NUMSS;
    }

    public void setNUMSS(String NUMSS) {
        this.NUMSS=NUMSS;
    }

//    public StringProperty NUMSSProperty({
//        return NUMSS;
//    }
    
    //------------NOMCAISSE------------
    public String getNOMCAISSE() {
        return NOMCAISSE;
    }

    public void setNOMCAISSE(String NOMCAISSE) {
        this.NOMCAISSE=NOMCAISSE;
    }

//    public StringProperty NOMCAISSEProperty({
//        return NOMCAISSE;
//    }
    
    //-----------PROFESSION-------------
    public String getPROFESSION() {
        return PROFESSION;
    }

    public void setPROFESSION(String PROFESSION) {
        this.PROFESSION=PROFESSION;
    }

//    public StringProperty PROFESSIONProperty({
//        return PROFESSION;
//    }
    
    //---------------COMMENTAIRES------------
    public String getCOMMENTAIRES() {
        return COMMENTAIRES;
    }

    public void setCOMMENTAIRES(String COMMENTAIRES) {
        this.COMMENTAIRES=COMMENTAIRES;
    }

//    public StringProperty COMMENTAIRESProperty({
//        return COMMENTAIRES;
//    }
    
    //-----------DATECREATION---------------
    public String getDATECREATION() {
        return DATECREATION;
    }

    public void setDATECREATION(String DATECREATION) {
        this.DATECREATION=DATECREATION;
    }

//    public StringProperty DATECREATIONProperty({
//        return DATECREATION;
//    }
    
    //------------REPERTOIRE--------------
    public String getREPERTOIRE() {
        return REPERTOIRE;
    }

    public void setREPERTOIRE(String REPERTOIRE) {
        this.REPERTOIRE=REPERTOIRE;
    }
   
//   public StringProperty REPERTOIREProperty({
//       return REPERTOIRE;
//   }
 
}
