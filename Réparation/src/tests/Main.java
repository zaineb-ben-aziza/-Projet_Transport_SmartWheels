/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tests;

import Entities.Employee;
import Entities.Reparation;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import services.EmployeeCRUD;
import services.ReparationCRUD;
import utils.MyConnection;


/**
 *
 * @author yosra
 */
public class Main extends Application{
 @Override
    public void start(Stage stage) throws Exception {
        try{
        Parent root = FXMLLoader.load(getClass().getResource("/gui/Employee.fxml"));      
        stage.setResizable(false);
        Scene scene = new Scene(root);
        stage.setScene(scene);
      
        stage.setTitle("Transport");
        stage.show();
        }
        catch(IOException e)
        {
            System.out.println("Probleme:"+e);
        }
          // MyConnection mc= new MyConnection();
       Employee e = new Employee(22,"yosra","zaheg","reparateur");
       EmployeeCRUD pcd = new EmployeeCRUD();
       //pcd.ajouterEntitee(e);
       System.out.println(pcd.listeDesEntites());
       Reparation r= new Reparation();
       pcd.supprimer(3);
    }
    public static void main(String[] args){
       
     
        launch(args);
       
    }
    
    
}