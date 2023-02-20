package controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */


import Entities.Employee;
import Entities.Reparation;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import services.EmployeeCRUD;

/**
 * FXML Controller class
 *
 * @author yosra
 */
public class EmployeeController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private TextField nom;
    @FXML
    private TextField type;
    @FXML
    private TextField prenom;
    @FXML
    private TableView<Employee> affichage;
    @FXML
    private TableColumn<?, ?> id;
    @FXML
    private TableColumn<?, ?> t_nom;
    @FXML
    private TableColumn<?, ?> t_prenom;
    @FXML
    private TableColumn<?, ?> t_type;
    @FXML
    private TextField idd;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.afficher();
           affichage.setOnMouseClicked(event ->{
           if (event.getClickCount() == 2 && !affichage.getSelectionModel().isEmpty() )
           setTextField();
        });
       
    }    

    @FXML
    private void ajouter(ActionEvent event) { 
        String emptyTxt="Les champs nom, prenom et type ne doivent pas etre nulls et ne doivent pas depasser 10 carateres";
        if(isInputValid(nom) && isInputValid(prenom) && isInputValid(type)){
            
// MyConnection mc= new MyConnection();
       Employee e = new Employee(nom.getText(),prenom.getText(),type.getText());
       EmployeeCRUD pcd = new EmployeeCRUD();
       pcd.ajouterEntitee(e);
       System.out.println(pcd.listeDesEntites());
       Employee e1= new Employee();
       this.afficher();
       return;
        }
              Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("Erreur");
           alert.setHeaderText(null);
           alert.setContentText(emptyTxt);
 Optional<ButtonType> result = alert.showAndWait();
 if (result.get() == ButtonType.OK){
          
         
        }
    }
    public void afficher()
   {
       
         EmployeeCRUD emp=new EmployeeCRUD();
         id.setCellValueFactory(new PropertyValueFactory<>("id"));      
         t_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));    
         t_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
         t_type.setCellValueFactory(new PropertyValueFactory<>("typee"));
    
         affichage.setItems(emp.afficher());
     
   } 

    @FXML
    private void Supprimer(ActionEvent event) {
        EmployeeCRUD es=new EmployeeCRUD();
           Alert alert = new Alert(AlertType.ERROR);
 Employee e=affichage.getSelectionModel().getSelectedItem();
  alert.setTitle("Conffirmation de suppression");
           alert.setHeaderText("Etes vous sur de supprimer cette id:");
           alert.setContentText(String.valueOf(e.getId()));
 Optional<ButtonType> result = alert.showAndWait();
 if (result.get() == ButtonType.OK){
          
            es.Supprimer(e.getId());
            this.afficher();
        }
        
       
                
    }

    @FXML
    private void modifier(ActionEvent event) {
         EmployeeCRUD es=new EmployeeCRUD();
         Employee e=new Employee(Integer.parseInt(idd.getText()),nom.getText(),prenom.getText(),type.getText());
           Alert alert = new Alert(AlertType.CONFIRMATION);
          alert.setTitle("Conffirmation de modification");
           alert.setHeaderText("Etes vous sur de modifier cet employe:");
           alert.setContentText(String.valueOf(e.getNom()));
 Optional<ButtonType> result = alert.showAndWait();
 if (result.get() == ButtonType.OK){
          es.modifier(e);
         this.afficher();
        }
        
      
    }
    public void setTextField()
    {           
 Employee e=affichage.getSelectionModel().getSelectedItem();
 idd.setText(String.valueOf(e.getId()));
 nom.setText(e.getNom());
 prenom.setText(e.getPrenom());
 type.setText(e.getTypee());
    }
          private boolean isInputValid(TextField textFiled) {
    Boolean b= false;
    if (!(textFiled.getText() == null || textFiled.getText().length() == 0 || textFiled.getText().length()>10)) {
        b=true;

}
  return b;  
}
    
}
