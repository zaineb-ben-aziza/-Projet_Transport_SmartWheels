/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet_transport.controler;

import com.sun.org.apache.bcel.internal.generic.AALOAD;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import static projet_transport.loginController.containsOnlyLetters;
import static projet_transport.loginController.containsOnlyNumber;
import static projet_transport.loginController.isEmailValid;
import projet_transport.model.Carte_fidelite;
import projet_transport.model.Utilisateur;
import projet_transport.services.Carte_fideliteS;
import projet_transport.services.UtilisateurS;
import projet_transport.utils.MyConnexion;

/**
 * FXML Controller class
 *
 * @author aziz
 */
public class Gestion_UtilisateurController implements Initializable {

    @FXML
    private AnchorPane aze;
    @FXML
    private AnchorPane anchor;
    @FXML
    private Pane left;
    @FXML
    private AnchorPane anchor1;
    @FXML
    private TabPane tabpane;
    @FXML
    private TableView<Utilisateur> affichage;
    @FXML
    private TableColumn<?, ?> id;
    @FXML
    private TableColumn<?, ?> nom;
    @FXML
    private TableColumn<?, ?> prenom;
    @FXML
    private TableColumn<?, ?> genre;
    @FXML
    private TableColumn<?, ?> email;
    @FXML
    private TableColumn<?, ?> date_naissance;
    @FXML
    private TableColumn<?, ?> mot_passe;
    @FXML
    private TableColumn<?, ?> typee;
    @FXML
    private TableColumn<?, ?> telephone;
    @FXML
    private TableColumn<?, ?> region;
    @FXML
    private Pane layout22;
    @FXML
    private Label layout1_label112;
    @FXML
    private TextField u_choix;
    @FXML
    private Button supprimer;
    @FXML
    private Button ajouter;
    @FXML
    private TextField u_nom;
    @FXML
    private TextField u_prenom;
    @FXML
    private TextField u_email;
    @FXML
    private DatePicker u_date;
    @FXML
    private TextField u_region;
    @FXML
    private TextField u_passe;
    @FXML
    private RadioButton u_h;
    @FXML
    private RadioButton u_f;
    @FXML
    private RadioButton u_a;
    @FXML
    private ComboBox<String> u_type;
    @FXML
    private TextField u_telephone;
    @FXML
    private ToggleGroup genre2;
    @FXML
    private Pane layout221;
    @FXML
    private Button ajouter1;
    @FXML
    private TextField c_points;
    @FXML
    private TableColumn<?, ?> t_id;
    @FXML
    private TableColumn<?, ?> t_points;
    @FXML
    private TableView<Carte_fidelite> t_affichage;
    @FXML
    private ComboBox<String> c_idd;
    @FXML
    private Button annuler;
    @FXML
    private Button modifier;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        //init
        u_choix.setVisible(false);
        
        //set options admin ou user
        ObservableList<String> options =  FXCollections.observableArrayList("Admin","User");
        u_type.setItems(options);
        //affichage tableau 
        this.afficher(affichage);
        //selection multiple sur tableview 
         affichage.getSelectionModel().setSelectionMode(
         SelectionMode.MULTIPLE
         );
        //Modifier utilisateur en cliquant sur table view
        affichage.setOnMouseClicked(event ->{
           if (event.getClickCount() == 2  )
           SetAllTextField();
        });
        //Gestion carte
        this.afficher_carte();
         t_affichage.setOnMouseClicked(event ->{
           if (event.getClickCount() == 2  )
           SetAllTextField_carte();
        });
         this.GetAllIdUser();
       
    }
    
//affichage Tableview
 public void afficher(TableView<Utilisateur> affichage)
   {
       
         UtilisateurS util=new UtilisateurS();
         id.setCellValueFactory(new PropertyValueFactory<>("id"));      
         nom.setCellValueFactory(new PropertyValueFactory<>("nom"));    
         prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
         email.setCellValueFactory(new PropertyValueFactory<>("email"));
         typee.setCellValueFactory(new PropertyValueFactory<>("type"));
         email.setCellValueFactory(new PropertyValueFactory<>("email"));
         date_naissance.setCellValueFactory(new PropertyValueFactory<>("date"));
         region.setCellValueFactory(new PropertyValueFactory<>("region"));
         mot_passe.setCellValueFactory(new PropertyValueFactory<>("mot_passe"));
         telephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
         genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
    
         affichage.setItems(util.afficher());
     
   }    

  
//Ajouter Utlisateur
    @FXML
    private void ajouter(MouseEvent event) {
        Alert al = new Alert(AlertType.ERROR);
        UtilisateurS util=new UtilisateurS();
         if (!(u_telephone.getText().equals("") || u_nom.getText().equals("") ||  u_email.getText().equals("") || u_prenom.getText().equals("") || u_region.getText().equals("") ||  u_passe.getText().equals("") || u_type.getValue()==null))
            {
                if (containsOnlyLetters(u_nom.getText())){
                
                al.setTitle("Error ");
                al.setHeaderText("nom est alphabetique ");
                al.showAndWait(); 
                u_nom.setText("");
                  
                 }
                else if (containsOnlyLetters(u_prenom.getText()))
                {
               
                al.setTitle("Error ");
                al.setHeaderText("Prenom est alphabetique ");
                al.showAndWait(); 
                u_prenom.setText("");
                }
                else   if (util.CheckUserByEmail(u_email.getText()) )
                {
              
                al.setTitle("Error ");
                al.setHeaderText("Email deja exisistant");
                al.showAndWait(); 
                u_email.setText("");
                    
                }
                else if ( !isEmailValid(u_email.getText()))
                 {
     
                al.setTitle("Error ");
                al.setHeaderText("Le format de l'Email est invalide");
                al.showAndWait();  
                u_email.setText("");
                 }
                else  if (containsOnlyNumber(u_telephone.getText()) || u_telephone.getText().length() != 8 )
                {
               
                al.setTitle("Error ");
                al.setHeaderText("Telephone est composer de 8 chiffres ");
                al.showAndWait();  
                u_telephone.setText("");
                }
                else if (containsOnlyLetters(u_region.getText()))
                {
               
                al.setTitle("Error ");
                al.setHeaderText("Region contient que des lettres alphabetique");
                al.showAndWait();  
                u_region.setText("");
                }
              
                else
                  {
                   Utilisateur utilisateur=new Utilisateur(u_nom.getText(),u_prenom.getText(),u_email.getText(),u_date.getValue().toString(),Integer.parseInt(u_telephone.getText()),u_region.getText() , u_passe.getText(),u_h,u_f,u_type.getValue());
                   util.ajouter(utilisateur);
                   System.out.println("Ajout avec succes");
                   //Initialisation tableau affichage et choix id
                   this.afficher(affichage);   
                    Alert a2 = new Alert(AlertType.CONFIRMATION);
                     a2.setTitle("Information ");
                     a2.setHeaderText("Ajout avec succes");
                     a2.showAndWait();  
                    
                  }
            }
             else
            {
            
            al.setTitle("Error ");
            al.setHeaderText("Vous devez Remplit tous le formulaire");
            al.showAndWait();  
            }
        
             
    
    }
  

   //set all Text field from id utilisateur
    public void SetAllTextField()
   {
    
       UtilisateurS util=new UtilisateurS();
       Utilisateur u=new Utilisateur();
       u=affichage.getSelectionModel().getSelectedItem();

         
       u_choix.setText(Integer.toString(u.getId()));
       u_nom.setText(u.getNom());
       u_prenom.setText(u.getPrenom());
       u_email.setText(u.getEmail());
       u_telephone.setText(Integer.toString(u.getTelephone()));
       u_region.setText(u.getRegion());
       u_date.setValue(LOCAL_DATE(u.getDate()));
       u_passe.setText(u.getMot_passe());
        switch (u.getGenre()) {
            case "Homme":
                u_h.setSelected(true);
                break;
            case "Femme":
                u_f.setSelected(true);
                break;
            default:
                u_a.setSelected(true);
                break;
        }
        if (u.getType().equals("User")){
       ObservableList<String> options =  FXCollections.observableArrayList( "Admin");
       
       u_type.setItems(options);
       u_type.setValue("User");
       }
       else 
       {
          ObservableList<String> options =  FXCollections.observableArrayList( "User");
          u_type.setItems(options);
          u_type.setValue("Admin");
       }
     
       
      ajouter.setVisible(false);
        supprimer.setVisible(false);
        annuler.setVisible(true);
        u_choix.setVisible(true);
   }
    //modifer utilisateur par id
    @FXML
    private void update(MouseEvent event) {
        UtilisateurS us=new UtilisateurS();
        Utilisateur utilisateur2 =us.getUserById(u_choix.getText());
        
        if (!u_choix.getText().equals(""))
        {
         Utilisateur utilisateur=new Utilisateur(Integer.parseInt(u_choix.getText()),u_nom.getText(),u_prenom.getText(), u_email.getText(), u_date.getValue().toString(),Integer.parseInt(u_telephone.getText()),u_region.getText() ,u_passe.getText(),u_h,u_f,u_type.getValue()); 
         Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de Modification");
        alert.setHeaderText("Voulais vous modifier cette Utilisateur:");
        alert.setContentText("id:"+u_choix.getText());
        
       
  
        // ... user chose OK
       if (!(utilisateur2.getNom().equals(utilisateur.getNom()) 
               && utilisateur2.getPrenom().equals(utilisateur.getPrenom()) 
               && utilisateur2.getEmail().equals(utilisateur.getEmail()) 
               && utilisateur2.getDate().equals(utilisateur.getDate()) 
               && utilisateur2.getTelephone() == utilisateur.getTelephone()
               && utilisateur2.getRegion().equals(utilisateur.getRegion())
               && utilisateur2.getMot_passe().equals(utilisateur.getMot_passe())
               && utilisateur2.getGenre().equals(utilisateur.getGenre())
               && utilisateur2.getType().equals(utilisateur.getType())
               )
          )
            {
         Optional<ButtonType> result = alert.showAndWait();
         if (result.get() == ButtonType.OK){
            UtilisateurS util=new UtilisateurS();
            util.modifier(utilisateur);
             System.out.println("modifier avec succes");
             this.afficher(affichage);
             u_choix.setText("");
             u_choix.setVisible(false);
             this.setVisibleLabel();
            }
          
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
           al.setHeaderText("Vous devez delectionner un utilisateur");
           al.showAndWait();  
        }
    }
    // LocalDate format pour type date 'yyyy-MM-dd'
    public static final LocalDate LOCAL_DATE (String dateString){
        
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate localDate = LocalDate.parse(dateString, formatter);
    return localDate;
        
    }

   
    @FXML
    private void home(ActionEvent event) {
        
         try{
         Parent root = FXMLLoader.load(getClass().getResource("/projet_transport/views/login.fxml"));  
         Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        }
        catch(Exception e)
        {
            System.out.println("Probleme:"+e);
        }
    }

    @FXML
    private void Supprimer(MouseEvent event) throws IOException  {
              
       UtilisateurS utils=new UtilisateurS();
       ObservableList <Utilisateur> utilisateur =affichage.getSelectionModel().getSelectedItems();
       
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppresion");
        alert.setHeaderText("Voulais vous supprimer ces ou cette id:");
        String id = "";
          
         for(Utilisateur c : utilisateur)
            {
             id=id+" "+c.getId();
            }
         if (!id.trim().equals("")){
             
            alert.setContentText("id:"+id);
            // ... user chose OK
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
              //ajouter les id suppriemr dans le fichier
              File file = new File("C:\\Users\\aziz\\Documents\\NetBeansProjects\\Projet_Transport\\src\\projet_transport\\Logs\\Data.txt");
              FileWriter fr = new FileWriter(file, true);
              BufferedWriter br = new BufferedWriter(fr);
            for(Utilisateur s : utilisateur){
            br.write(Integer.toString(s.getId())+" ");
            utils.Supprimer(s.getId());                           
            }
             br.close();
             fr.close();
          this.afficher(affichage);
          this.GetAllIdUser();
          this.afficher_carte();
            }
           
         }
            else{
            Alert al = new Alert(AlertType.ERROR);

           al.setTitle("Error alert");
           al.setHeaderText("Vous devez selectionner au moin un id pour suprrimer");
   
            al.showAndWait();
                
            }
         
    }
//Gestion carte
    @FXML
    private void ajouter_carte(ActionEvent event) {
        Carte_fidelite carte=new Carte_fidelite(Integer.parseInt(c_idd.getValue()),Integer.parseInt(c_points.getText()));
        Carte_fideliteS carteS=new Carte_fideliteS();
        carteS.ajouter(carte);
        this.afficher_carte();
    }
    public void afficher_carte()
    {
         Carte_fideliteS cartes=new Carte_fideliteS();
         t_id.setCellValueFactory(new PropertyValueFactory<>("id"));      
         t_points.setCellValueFactory(new PropertyValueFactory<>("points"));
         t_affichage.setItems(cartes.afficher());

    }
  
    public void SetAllTextField_carte()
    {
        Carte_fidelite carte=new Carte_fidelite();
        carte=t_affichage.getSelectionModel().getSelectedItem();
        c_idd.setValue(String.valueOf(carte.getId()));
        c_points.setText(String.valueOf(carte.getPoints()));
        
    }
    public void GetAllIdUser()
    {
        Carte_fideliteS carteS=new Carte_fideliteS();
        c_idd.setItems(carteS.GetAllIdUser());
      
    }

    @FXML
    private void annuler(ActionEvent event) {
      this.setVisibleLabel();
    }
    public void setVisibleLabel()
    {
          ajouter.setVisible(true);
        supprimer.setVisible(true);
        annuler.setVisible(false);
        u_choix.setVisible(false);
    }
}
