package com.htdev.jnaturos;

import java.util.Optional;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class MainApp extends Application {

    //constantes
    final String VERSION="JNaturos version 0.1 ";
    
    //variables
    Database db;
    
    @Override
    public void start(Stage stage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("/fxml/MainScene.fxml"));
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainScene.fxml"));
        Parent root = (Parent)loader.load();
        final FXMLController controller = loader.getController();
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        
        /* On laisse le titre avec un tiré ceci permettra de passer des infos sur la barre du titre*/
        stage.setTitle(VERSION);
        stage.setMaximized(true); //maximiser la fenêtre a l'affichage
        
              //======================================================================
        //prevenir d'une fermeture de la fenêtre sur l'evenement stage.close()
            stage.setOnCloseRequest((WindowEvent we) -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle(VERSION);
            alert.setHeaderText("Vous avez demandé la fermeture de l'application JNaturos...");
            alert.setContentText("Est vous sur de celà ?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                // ... appel de OK on fait rien et on ferme l'application
            } else {
                // ... appel de CANCEL on consomme l'evenement pour l'annuler
                we.consume();
            }
        } //fin handle
        );
        //======================================================================
        
        runDatabase();
        controller.setStage(stage,db);
        
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() throws Exception {
     //fermer la base de données
     db.close();
     db.disconnect();
        System.out.println("Naturos est deconnectée");   
    super.stop();
    }
    
    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private void runDatabase() {
         //connecter la base de données
            db=new Database("/home/herve/dev/jNaturos/Naturos", "herve", "herve",null,0,true,false);
            //Database db=new Database("/home/herve/dev/HTTEST", "herve", "herve","localhost",0,false,false);
            
            if (db.connect()) {System.out.println("Naturos est connectée");} else {System.out.println("Naturos n'est pas connectée!");Platform.exit();}
    
            db.setSchema("APP");
    }

}
