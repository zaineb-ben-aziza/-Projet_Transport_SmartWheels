/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import Entities.Reparation;
import Entities.Employee;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import services.EmployeeCRUD;
import services.ReparationCRUD;
import java.util.Date;
import javafx.scene.control.DatePicker;
import java.time.format.DateTimeFormatter;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

/**
 * FXML Controller class
 *
 * @author yosra
 */
public class ReparationController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private TextField identifiant;
    @FXML
    private TextField description;
    @FXML
    private TextField etat;
    @FXML
    private DatePicker date_reparation;
    @FXML
    private DatePicker date_manutention;
    @FXML
    private DatePicker date_defecation;
    @FXML
    private TabPane tab;
    
    @FXML
    private TableView<Reparation> affichage;
     @FXML
    private TableColumn<?, ?> id;
    @FXML
    private TableColumn<?, ?> s_description;
    @FXML
    private TableColumn<?, ?> s_etat;
    @FXML
    private TableColumn<?, ?> s_datereparation;
    @FXML
    private TableColumn<?, ?> s_datemanutention;
    @FXML
    private TableColumn<?, ?> s_datedefecation;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
        Tab tab1 = new Tab();
        tab.getTabs().add(tab1);
        tab1.setContent((Node) FXMLLoader.load(this.getClass().getResource("/gui/Employee.fxml")));
        tab1.setText("Employee");
    } catch (IOException e) {
        e.printStackTrace();
    }
        identifiant.setVisible(false);
        this.afficher();
           affichage.setOnMouseClicked(event ->{
           if (event.getClickCount() == 2 && !affichage.getSelectionModel().isEmpty() )
           setTextField();
        });
    }    

     @FXML
     
    private void ajouter(ActionEvent event) {
        
        String emptyTxt="Les champs description et etat ne doivent pas etre nulls et ne doivent pas depasser 10 carateres";
        if(isInputValid(description) && isInputValid(etat)){
            
            Reparation r = new Reparation(description.getText(),etat.getText(),date_reparation.getValue(),date_manutention.getValue(),date_defecation.getValue());
       ReparationCRUD pcd = new ReparationCRUD();
       pcd.ajouterEntitee(r);
       System.out.println(pcd.listeDesEntites());
      
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
                
  
// 
    
    }
    public void afficher()
   {
       
         ReparationCRUD rep=new ReparationCRUD();
         id.setCellValueFactory(new PropertyValueFactory<>("id"));  
         s_description.setCellValueFactory(new PropertyValueFactory<>("Description_panne"));    
         s_etat.setCellValueFactory(new PropertyValueFactory<>("Etat"));
         s_datereparation.setCellValueFactory(new PropertyValueFactory<>("Date_rep"));
         s_datemanutention.setCellValueFactory(new PropertyValueFactory<>("Date_manu"));
         s_datedefecation.setCellValueFactory(new PropertyValueFactory<>("Date_defect"));
         
    
         affichage.setItems(rep.afficher());
     
   } 

    @FXML
       private void Supprimer(ActionEvent event) {
       ReparationCRUD es=new ReparationCRUD();
           Alert alert = new Alert(Alert.AlertType.ERROR);
      Reparation r =affichage.getSelectionModel().getSelectedItem();
      alert.setTitle("Conffirmation de suppression");
           alert.setHeaderText("Etes vous sur de supprimer cet etat:");
           alert.setContentText(String.valueOf(r.getId()));
      Optional<ButtonType> result = alert.showAndWait();
       if (result.get() == ButtonType.OK){
          
            es.supprimer(r.getId());
            this.afficher();
        }
        
       
                
    }

    @FXML
    private void modifier(ActionEvent event) {
         ReparationCRUD es=new ReparationCRUD();
         Reparation r=new Reparation(Integer.valueOf(identifiant.getText()),description.getText(),etat.getText(),date_reparation.getValue(),date_manutention.getValue(),date_defecation.getValue());
           Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
          alert.setTitle("Conffirmation de modification");
           alert.setHeaderText("Etes vous sur de modifier cet etat:");
           alert.setContentText(String.valueOf(r.getEtat()));
 Optional<ButtonType> result = alert.showAndWait();
 if (result.get() == ButtonType.OK){
          es.modifier(r);
         this.afficher();
        }
 
        
      
    }
    public void setTextField()
    {           
 Reparation r = affichage.getSelectionModel().getSelectedItem();
 identifiant.setText(String.valueOf(r.getId()));
 description.setText(r.getDescription_panne());
 etat.setText(r.getEtat());
 date_reparation.setValue(r.getDate_rep());
 date_manutention.setValue(r.getDate_manu());
 date_defecation.setValue(r.getDate_defect());
 
    }
        private boolean isInputValid(TextField textFiled) {
    Boolean b= false;
    if (!(textFiled.getText() == null || textFiled.getText().length() == 0 || textFiled.getText().length()>10)) {
        b=true;

}
  return b;  
}
    

}
    

