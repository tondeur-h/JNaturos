package com.htdev.jnaturos;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.derby.drda.NetworkServerControl;


/**********************************************
 * Classe de gestion des bases de données de type javaDerby embedded et client/Serveur
 * @author Tondeur Herve (2016)
 * @version 0.1
 **********************************************/
public class Database {

    private String dbName; //database Path and Name
    private String dbUser; //database User login
    private String dbPwd; //database Password
    private String dbServer; //server name or IP
    private int dbPort; //server javaDB defaut 1527
    private String dbBaseUrl; //javaDB connexion URL
    private Connection conn=null; 
    private ResultSet rs=null;
    private Statement st=null;
    private boolean javaDBRunning=false;
    private boolean javaDBEmbeddedRunning=false;
    private NetworkServerControl serverControl=null;

    
    /********************************
     * Récuperer le nom du serveur
     * sur lequel est installé JavaDB
     * @return type String - Nom du serveur
     ********************************/
    public String getDbServer() {
        return dbServer;
    }
    

    /********************************
     * Affecter le nom du serveur
     * sur lequel est installé JavaDB
     * @param dbServer type String - Nom du serveur
     ********************************/
    public void setDbServer(String dbServer) {
        this.dbServer = dbServer;
    }

    
    /*********************************
     * Récuperer le numéro du port du 
     * SGBD JavaDB
     * @return type int - Numero du port javaDB
     *********************************/
    public int getDbPort() {
        return dbPort;
    }

    
    /*********************************************
     * Affecter le uméro du port SGBD
     * de JavaDerby par défaut 1527 si valeur =-1
     * @param dbPort type int - Numero du port javaDB
     ********************************************/
    public void setDbPort(int dbPort) {
        if (dbPort<1 || dbPort>65535)
        {
            this.dbPort=1527;
        } 
        else
        {
        this.dbPort = dbPort;
        }
    }
 
    
    /*****************************************
     * Récuperer le nom de la base de données
     * @return type String - Nom de la base de données javaDB
     *****************************************/
    public String getDbName() {
        return dbName;
    }

    /*************************************
     * Mdifier le nomde la base de données
     * @param dbName type String - Nom de la base de données javaDB à ouvrir
     *************************************/
    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    /***************************
     * Récuperer le nom du User
     * @return type String - Nom de l'utilisateur = login javaDB
     ***************************/
    public String getDbUser() {
        return dbUser;
    }

    /********************************
     * Affecter un nom d'utilisateur
     * @param dbUser type String - Nom de l'utilisateur à utiliser = login
     ********************************/
    public void setDbUser(String dbUser) {
        this.dbUser = dbUser;
    }

    /***************************
     * Récuperer le mot de passe
     * @return type String - Mot de passe de l'utilisateur/login
     ***************************/
    public String getDbPwd() {
        return dbPwd;
    }

    /***************************
     * Affecter le mot de passe
     * @param dbPwd type String - Mot de passe de l'utilsiateur/login
     ***************************/
    public void setDbPwd(String dbPwd) {
        this.dbPwd = dbPwd;
    }

    /**************************************
     * Récuperer l'URL de base de connexion
     * @return type String - Url compléte construite pour l'accés à la base javaDB
     **************************************/
    public String getDbBaseUrl() {
        return dbBaseUrl;
    }

    
    /******************************
     * Récuperer le ResultSet En cours
     * @return type ResultSet - ensemble résultat contenant le ResultSet en cours si pas de Resultset retourne null
     ******************************/
    public ResultSet getDB(){
        return rs;
    }
    
    
    /**************************************
     * Lancer javaDB en mode client Serveur
     * cette méthode permet de lancer automatiquement le serveur javaDB en mode client/serveur
     * cette méthode est éxécuté dans le constructeur si le mode client/serveur est sélectionné.
     **************************************/
    private void runJavaDB(){
        try {
        serverControl = new NetworkServerControl();
        serverControl.start(null);
            System.out.println(serverControl.getRuntimeInfo());
            System.out.println(serverControl.getSysinfo());
            javaDBRunning=true; //semaphore is running
        } catch (Exception ex) {
        Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    /***************************************
     * Arreter javaDB en mode client serveur
     * Cette méthode est appelée par la méthode disconnect()
     ***************************************/
    private void stopJavaDB(){
        if (javaDBRunning)
        {
          try {
                serverControl.shutdown();
              } catch (Exception ex) 
              {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
              }  
        }
    }
    
    
    /*********************************************
     * Fixer le nom du scéma par defaut à utiliser
     * dans les requetes...
     * @param schema type String - affecte le nom du schéma par défaut.
     *********************************************/
    public void setSchema(String schema){
        if (conn!=null) try {
            conn.setSchema(schema);
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    /*******************************
     * Constructeur de la classe
     * @param dbName type String - CHEMIN+NOM de la base de données javaDB
     * @param dbUser type String - nom de l'utilisateur/login pour accéder à la base de données
     * @param dbPwd type String - Mot de passe de l'utilisateur/login
     * @param dbServer type String - nom ou adresse IP du serveur ou se trouve javaDB
     * @param dbPort type int - numéro du port socket de fonctionnement de javaDB inf à 0 ou sup à 65535 on prend 1527 en valeur sinon le numéro du port que vous donnzrez.
     * @param embeded type boolean - true indique que la base javaDB est de type embedded, false de type client/serveur
     * @param create type boolean - true impose la création de la base si elle existe pas 'creation=true'
     *******************************/
    public Database(String dbName, String dbUser, String dbPwd, String dbServer, int dbPort,boolean embeded, boolean create) {
        try {
            if (embeded)
            {
                //connection à une base embarquée
                Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
                dbBaseUrl="jdbc:derby:";
                dbBaseUrl=dbBaseUrl+dbName;
                if (create) dbBaseUrl=dbBaseUrl+";create=true;";
                javaDBEmbeddedRunning=true;
            }
            else
            {
                //connection à une base client/serveur 
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                if (dbPort<1) dbPort=1527;
                dbBaseUrl="jdbc:derby://";
                dbBaseUrl=dbBaseUrl+dbServer+":"+dbPort+"/"+dbName;
                if (create) dbBaseUrl=dbBaseUrl+";create=true;";
                runJavaDB();
            }
            
            this.dbName = dbName;
            this.dbUser = dbUser;
            this.dbPwd = dbPwd;
            this.dbPort=dbPort;
            this.dbServer=dbServer;
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    /**********************************
     * connecter la base de données
     * @return  type boolean - true si la base est connectée sinon false
     **********************************/
    public boolean connect(){
        try 
        {
            conn=DriverManager.getConnection(dbBaseUrl, dbUser, dbPwd);
            conn.setAutoCommit(true); //forcer autoCommit sur la base
            rs=null;
            st=null;
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return false; //connexion KO
        }
        return true; //connecxion OK
    }
    
    
    /******************************
     * Fermer la connexion en cours
     * Ferme la connexion, le Statement et le ResultSet
     ******************************/
    public void close(){
        try {
            if (rs!=null) rs.close();
            if (st!=null) st.close();
            if (conn!=null) conn.close(); 
            conn=null;
            st=null;
            rs=null;
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, "Arret javaDB...", ex);
        }
    }
    
    
    /*************************************
     * Déconnecter la base de données...
     * fermer la base de données et décharger javaDB
     * si on est en mode client/serveur, arrete le serveur
     * si on est en mode embedded, fait un shutdown sur la base de données.
     *************************************/
    public void disconnect(){
        try {
            //stopper les bases de données
            if (javaDBRunning) stopJavaDB();
            if (javaDBEmbeddedRunning) DriverManager.getConnection("jdbc:derby:;shutdown=true");
            
            //décharger le Driver de la jvm actuelle
            
        } catch (SQLException ex) {
            System.out.println("JavaDB Embedded disconnect...");
        }
    }
    
    
    /***************************
     * Effectuer une requete et 
     * retourner le résultat dans un ResultSet (a exploiter avec getDB())
     * @param sql type String - requete correctement forme de type SELECT
     * @return type ResultSet - ensemble résultats de la requête.
     ***************************/
    public ResultSet query(String sql){
        try {
            if (sql!=null) rs=conn.createStatement().executeQuery(sql); else throw new SQLException("requete nulle!");
            
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return rs;
    }
    
    
    /*************************************
     * Fermer le statement en cours pour 
     * que le Garbage Collector puisse le supprimer
     *************************************/
    public void closeStatement(){
        if (st!=null) try {
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    /***************************************
     * Supprimer le ResulSet en cours pour
     * que le Garbage Collector puisse le supprimer
     ***************************************/
    public void closeResultSet(){
        if (rs!=null) try {
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**************************************
     * Effectuer une requete UPDATE
     * et retourner le nombre de lignes maj
     * -1 si la requête à échouée.
     * @param sql type String - Requete SQL de type UPDATE
     * @return type int - Nombre de lignes mises à jours.
     **************************************/
    public int update(String sql){
        int nb=-1;
        try {
            if (sql!=null) nb=conn.createStatement().executeUpdate(sql); else throw new SQLException("requete nulle!");
            
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
        return nb;
    }
    
    
    /**************************************
     * Effectuer une requete INSERT
     * et retouner le nb de lignes insérées
       * @param sql type String - Requete SQL de type UPDATE
     * @return type int - Nombre de lignes insérées.
     **************************************/
     public int insert(String sql){
        int nb=-1;
        try {
             if (sql!=null) nb=conn.createStatement().executeUpdate(sql); else throw new SQLException("requete nulle!");
            
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
        return nb;
    }
     
   //-------------------------------GESTION DES METADONNEES---------------------------------
    /**
     * Acceder aux métadonnées de la base
     * @return l'objet DatabaseMetaData - permet d'exploiter l'ensemble de ses méthodes.
     */
     public DatabaseMetaData metaData(){
        DatabaseMetaData dbm;
        try {
            
            dbm=conn.getMetaData();
            
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return dbm;
    }
    
    /*****************************
     * Liste les infos des clients
     * @return l'objet Properties qui contient toutes les infos sur les clients
     *****************************/
    public Properties infoClients(){
        Properties prop;
        try {
            
            prop=conn.getClientInfo();
            
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return prop;
    } 
    
    /***********************
     * Afficher le catalogue en cours
     * @return type String - Le nom du catalogue en cours
     ***********************/
    public String catalog(){
        String cat;
        try {
            
            cat=conn.getCatalog();
            
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return cat;
    }
    
    /*****************************
     * Afficher le schéma en cours
     * @return type String - Le nom du schéma de la base de données en cours
     *****************************/
    public String schema(){
        String sch;
        try {
            
            sch=conn.getSchema();
            
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return sch;
    }
      
    /******************************
     * Lister toutes les tables de la base javaDB en cours
     * @return type String - un tableau avec la liste des tables et vues.
     ******************************/
    public String getAllTables()
    {
        String tables="";
         try {
            ResultSet rsT=null;
            String[] types=new String[2];
            types[0]="TABLE";
            types[1]="VIEW";
            rsT=conn.getMetaData().getTables(null, null, "%", types);
            while (rsT.next())
            {
              tables=tables+rsT.getString(3)+"-"+rsT.getString(4)+'\n';
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return tables;
    }
    
} //fin de la classe DataBase
