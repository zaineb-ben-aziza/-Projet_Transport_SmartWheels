/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet_transport;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author aziz
 */
public class main extends Application {
    
     @Override
    public void start(Stage stage) throws Exception {
        try{
        Parent root = FXMLLoader.load(getClass().getResource("/projet_transport/views/login.fxml"));      
        stage.setResizable(false);
        Scene scene = new Scene(root);
        stage.setScene(scene);
      
        stage.setTitle("Transport");
        stage.show();
        }
        catch(Exception e)
        {
            System.out.println("Probleme:"+e);
        }
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {    
        launch(args);
    }
    
}
