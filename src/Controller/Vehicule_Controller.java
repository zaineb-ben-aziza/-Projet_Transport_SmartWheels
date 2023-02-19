/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;



import entities.Vehicule;
import interfaces.VehiculeCRUD;
import java.io.IOException;
import services.vehicule_Service;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;




/**
 *
 * @author user
 */
public class Vehicule_Controller implements Initializable {
    
    
 
 
   int myIndex;
    
    //les zone de textes
    @FXML
    TextField id;
    @FXML
    private ComboBox<String> marque;

    @FXML
    private ComboBox<String> vitesse_max;
    @FXML
    private TextField charge_max;
    @FXML
    private ComboBox<String> auto_b;

    
    //les boutons

    @FXML
    private TableColumn<?, ?> colid;
    @FXML
    private TableColumn<?, ?> colmarque;
    @FXML
    private TableColumn<?, ?> colvitesse_max;
    @FXML
    private TableColumn<?, ?> colcharge_max;
    @FXML
    private TableColumn<?, ?> colauto_b;
    @FXML
    private TableColumn<?, ?> colcouleur;
    @FXML
    private TableColumn<?, ?> coltype;
    @FXML
    private TextField couleur;

    @FXML
    private TableView<Vehicule> tab;
    @FXML
    private Button supprimer;
    @FXML
    private Pane left;
    @FXML
    private Button ajouter;
    @FXML
    private ComboBox<String> type;
    @FXML
    private Button modifier;
    @FXML
    private TextField prix;
    @FXML
    private TableColumn<?, ?> colprix;
    @FXML
    private Button annuler;
    @FXML
    private Button reservation;
    @FXML
    private AnchorPane contentArea;

   




    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      this.afficher();
      //comboBox type
      ObservableList<String> opt =  FXCollections.observableArrayList("Trotinette électrique","Vélo électrique");
      type.setPromptText("Type de véhicule");
      type.setItems(opt);
      
      //comboBox marque
       ObservableList<String> opt1 =  FXCollections.observableArrayList("Xiaomi","NIU","IsinWheel","inomile","Evercross EV","HITWAY","RCB","Sixfox","Eleglide","DYU");
       marque.setPromptText("Marque de véhicule");
       marque.setItems(opt1);
       
       //comboBox vitessMax klm/h
       ObservableList<String> opt2 =  FXCollections.observableArrayList("10klm/h","15klm/h","20klm/h","25klm/h","30klm/h","35klm/h","40klm/h","45klm/h","50klm/h");
       vitesse_max.setPromptText("kilométrage par heure");
       vitesse_max.setItems(opt2);
     
              //comboBox autonomie de la baterrie
       ObservableList<String> opt3 =  FXCollections.observableArrayList("20km/1charge","25km/1charge","30km/1charge","40km/1charge","50km/1charge");
       auto_b.setPromptText("autonomie de la batterie");
       auto_b.setItems(opt3);
       
       
      
      //lorsque on click deux fois  sur tableView(ligne)
tab.setOnMouseClicked(event ->{
           if (event.getClickCount() == 2 && !tab.getSelectionModel().isEmpty() )

        SetAllTextField();
        });

    }    

    
 //modifier zone de texte 
public void SetAllTextField()
   {
      vehicule_Service vs= new vehicule_Service(); 
     Vehicule v= new Vehicule();
     v=tab.getSelectionModel().getSelectedItem();
      id.setText(Integer.toString(v.getId()));
       marque.setValue(v.getMarque());
      
       vitesse_max.setValue(v.getVitesse_max());
       charge_max.setText(Float.toString(v.getCharge_max()));
       auto_b.setValue(v.getAuto_batterie());
        couleur.setText(v.getCouleur());
        type.setValue(v.getType_vehicule());
        prix.setText(Float.toString(v.getPrix()));
   }

//AFFICHAGE
 public void afficher(){
 
 vehicule_Service vs= new vehicule_Service(); 
         
         colid.setCellValueFactory(new PropertyValueFactory<>("id"));

         colmarque.setCellValueFactory(new PropertyValueFactory<>("marque"));
        
        
         colvitesse_max.setCellValueFactory(new PropertyValueFactory<>("vitesse_max"));
         colcharge_max.setCellValueFactory(new PropertyValueFactory<>("charge_max"));
         colauto_b.setCellValueFactory(new PropertyValueFactory<>("auto_batterie"));
         colcouleur.setCellValueFactory(new PropertyValueFactory<>("couleur"));
         coltype.setCellValueFactory(new PropertyValueFactory<>("type_vehicule"));
          colprix.setCellValueFactory(new PropertyValueFactory<>("prix"));
       
//appel fonction
         tab.setItems(vs.afficher_vehicule());  

 }   


//SUPPRIMER
    @FXML
    private void supprimer(ActionEvent event) {
        Vehicule v= tab.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(AlertType.CONFIRMATION);
        vehicule_Service vs= new vehicule_Service();   
       
        if (v!=null){
        
        alert.setTitle("Confirmation de suppression");
         alert.setHeaderText("Confiramation de suppression");
         alert.setContentText("Voulez-vous vraiment supprimer le véhicule N°" +v.getId());
    
        Optional<ButtonType> result = alert.showAndWait();
         // ... user chose OK
       if (result.get() == ButtonType.OK){

            vs.Supprimer_Vehicule(v.getId());
            this.afficher();
       }
    }
  else
    {
           Alert al = new Alert(AlertType.ERROR);

           al.setTitle("Error alert");
           al.setHeaderText("Vous devez selectionner au moin un id pour suprrimer");
   
            al.showAndWait();
    }

    }
    //AJOUTER
    @FXML
    private void ajouter(ActionEvent event) {
        
    
        //if different ll vide ->ajouter 
        if(!(id.getText().equals("") || marque.getValue().equals("")  || vitesse_max.getValue().equals("") || charge_max.getText().equals("") || auto_b.getValue().equals("") || couleur.getText().equals("") || type.getValue().equals("") || prix.getText().equals("")    ))
        {
        Vehicule v= new Vehicule(Integer.parseInt(id.getText()),marque.getValue(),vitesse_max.getValue(),Float.parseFloat(charge_max.getText()),auto_b.getValue(),couleur.getText(),type.getValue(),Float.parseFloat(prix.getText()));
        vehicule_Service vs= new vehicule_Service();
        //if selected iteam different 
        if(!vs.GetAllIdUser().contains(String.valueOf(v.getId())))
        {
            
             vs.ajouter_vehicule(v);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Ajout avec succées!");
            alert.showAndWait();
        
        this.afficher();
        
        }
        else{
        Alert alert = new Alert(AlertType.ERROR);
alert.setTitle("Error alert");
alert.setHeaderText("l'identifiant N° "+v.getId()+" existe déja");
alert.showAndWait();
        }
        
        }
        
        //error :Les champs de texte d'un formulaire ne doivent pas être null/vide
        else 
        {
Alert alert = new Alert(AlertType.ERROR);
alert.setTitle("Error alert");
alert.setHeaderText("Les champs de texte d'un formulaire ne doivent pas être null/vide"); 
alert.showAndWait();
        }
        
     
    }
    
    
 //MODIFIER

    @FXML
    private void modifier(ActionEvent event) {
                if(!(id.getText().equals("") || marque.getValue().equals("")  || vitesse_max.getValue().equals("") || charge_max.getText().equals("") || auto_b.getValue().equals("") || couleur.getText().equals("") || type.getValue().equals("") || prix.getText().equals("")    ))
                {
        vehicule_Service vhS=new vehicule_Service();
        
        Vehicule vh_enregistre=vhS.GetUserById(Integer.parseInt(id.getText()));
        
        Vehicule v= new Vehicule(Integer.parseInt(id.getText()),marque.getValue(),vitesse_max.getValue(),Float.parseFloat(charge_max.getText()),auto_b.getValue(),couleur.getText(),type.getValue(),Float.parseFloat(prix.getText()));
        
        
         if (!(v.getId()==(vh_enregistre.getId()) 
               && v.getMarque().equals(vh_enregistre.getMarque()) 
               && v.getVitesse_max().equals(vh_enregistre.getVitesse_max()) 
               && v.getCharge_max()==(vh_enregistre.getCharge_max())
               && v.getAuto_batterie().equals(vh_enregistre.getAuto_batterie())
               && v.getCouleur().equals(vh_enregistre.getCouleur())
               && v.getType_vehicule().equals(vh_enregistre.getType_vehicule())
               && v.getPrix()==(vh_enregistre.getPrix())
                 
               )
             
            )
          
         {
        vehicule_Service vs= new vehicule_Service();
        
        vhS.modifier_vehicule(v);   
        
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Modification avec succées pour le véhicule N° "+v.getId());
        alert.showAndWait();
        this.afficher();     
         }
         else
         {
              Alert al = new Alert(AlertType.ERROR);
           al.setTitle("Error alert");
           al.setHeaderText("Vous devez Changer au moin un attribut");
            al.showAndWait();
         }
                }
                else
                {
                     Alert al = new Alert(AlertType.ERROR);
           al.setTitle("Error alert");
           al.setHeaderText("Vous devez selectionner au moin une vehicule pour modifier!");
            al.showAndWait();
                }
        
    }

    @FXML
    private void annuler(ActionEvent event) {
     

        
    }



    @FXML
    private void reservation(ActionEvent event) throws IOException {
            
           FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/GestionDesReservations.fxml"));
           Parent root1 = (Parent) fxmlLoader.load();
           Stage stage = new Stage();
           stage.setScene(new Scene(root1));
           stage.show();
          
 
    }


    




    

    }
    
              
