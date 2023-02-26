/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entities.Commentaire;
import Entities.Reclamation;
import Services.CommentaireCrud;
import Services.ReclamationCrud;
import Utils.MyConnection;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import static sun.security.jgss.GSSUtil.login;

/**
 *
 * @author user
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private TextField tfid;
    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfprenom;
    @FXML
    private TextField tfadresse;
    @FXML
    private TextField tfcontenu;
    @FXML
    private TableView<Reclamation> tvreclamation;
    @FXML
    private TableColumn<Reclamation,?> colid;
    @FXML
    private TableColumn<Reclamation, String> colnom;
    @FXML
    private TableColumn<Reclamation, String> colprenom;
    @FXML
    private TableColumn<Reclamation, String> coladresse;
    @FXML
    private TableColumn<Reclamation, String> colcontenu;
    @FXML
    private Button btninsert;
    @FXML
    private Button btnupdate;
    @FXML
    private Button btndelete;
    ReclamationCrud rc=new ReclamationCrud();
    CommentaireCrud cmntr =new CommentaireCrud();

    @FXML
    private AnchorPane anchor;
    @FXML
    private Pane left;
    @FXML
    private AnchorPane anchor1;
    @FXML
    private TextArea tfCmntr;
    @FXML
    private Button btnInsertCmntr;
    @FXML
    private Button btnUpdateCmntr;
    @FXML
    private Button btnDeleteCmntr;
    @FXML
    private TableView<Commentaire> tvcommentaire;
    @FXML
    private TableColumn<Commentaire,?> colpseudo;
    @FXML
    private TableColumn<Commentaire, String> colcommentaire;
    @FXML
    private TextField tfidcom;
    @FXML
    private Button btn_vehicule;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showReclamation();
        showCommentaire();
    }
    
        
  
public ObservableList<Reclamation>getReclamationList(){
ObservableList<Reclamation> ReclamationList = FXCollections.observableArrayList();
ReclamationList.addAll(rc.listeDesReclamations());
return ReclamationList; 

}  
public void showReclamation(){

ObservableList<Reclamation> List =getReclamationList();
colid.setCellValueFactory(new PropertyValueFactory<>("Id"));
colnom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
colprenom.setCellValueFactory(new PropertyValueFactory<>("Prenom"));
coladresse.setCellValueFactory(new PropertyValueFactory<>("Adresse"));
colcontenu.setCellValueFactory(new PropertyValueFactory<>("Contenu"));
tvreclamation.setItems(List);
}

/*ajout modifier et supprimer pour la reclamation */


    @FXML
    private void insert(ActionEvent event) {
        if (ControleSaisie().length()>0){
         Alert alert =new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ajout Reclamation");
        alert.setContentText(ControleSaisie());
        alert.showAndWait();}
        else {
            
        Reclamation r =new Reclamation();
        r.setNom(tfnom.getText());
        r.setPrenom(tfprenom.getText());
        r.setAdresse(tfadresse.getText());
        r.setContenu(tfcontenu.getText());
        if(rc.contentExist(r)){
            Alert alert =new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ajout Reclamation");
        alert.setContentText("Reclamation existe deja!!");
        alert.showAndWait();
        }
        else{
            rc.ajouterEntitee(r);
        showReclamation();
        }
        }


    }
    @FXML
    private void update(ActionEvent event) {
         Alert alert =new Alert(Alert.AlertType.WARNING);
        alert.setTitle("confirmer la modification");
        alert.setContentText("voulez vous vraiment modifier");
        alert.showAndWait();
         Reclamation r =new Reclamation();
         int idModifier=tvreclamation.getSelectionModel().getSelectedItem().getId();
        r.setNom(tfnom.getText());
        r.setPrenom(tfprenom.getText());
        r.setAdresse(tfadresse.getText());
        r.setContenu(tfcontenu.getText());
        rc.updateReclamation(idModifier, r);
        showReclamation();

    }

    @FXML
    private void Delete(ActionEvent event) {
        Alert alert =new Alert(Alert.AlertType.WARNING);
        alert.setTitle("confirmer la suppression");
        alert.setContentText("voulez vous vraiment supprimer la ligne");
        alert.showAndWait();
        int idSup=tvreclamation.getSelectionModel().getSelectedItem().getId();
        rc.supprimerReclamation(idSup);
        showReclamation();
    }
private String ControleSaisie(){
String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +"[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
    String erreur="";
if(tfnom.getText().trim().isEmpty()){
erreur+="nom vide \n";}
if(tfprenom.getText().trim().isEmpty()){
erreur+="prenom vide \n";}
if(tfadresse.getText().trim().isEmpty()){
erreur+="adresse vide \n";}
if(tfcontenu.getText().trim().isEmpty()){
erreur+="contenu vide \n";}
if (!pattern.matcher(tfadresse.getText().trim()).matches()) {
            erreur+="-Inserer correct email\n";
        }

return erreur;

}
/*Partie commentaire */
public ObservableList<Commentaire>getCommentaireList(){
ObservableList<Commentaire> CommentaireList= FXCollections.observableArrayList();
CommentaireList.addAll(cmntr.listeDesCommentaires());
return CommentaireList; 

}  
public void showCommentaire(){

ObservableList<Commentaire> List =getCommentaireList();
colpseudo.setCellValueFactory(new PropertyValueFactory<>("id_com"));

colcommentaire.setCellValueFactory(new PropertyValueFactory<>("contenu"));
   
tvcommentaire.setItems(List);
}
/*ajout modifier et supprimer pour les commentaires */


    @FXML
    private void InsertCmntr(ActionEvent event) {
         if (ControleCmntr().length()>0){
         Alert alert =new Alert(Alert.AlertType.ERROR);
        alert.setTitle("done");
        alert.setContentText(ControleSaisie());
        alert.showAndWait();}
        else {
        Commentaire r =new Commentaire();
        r.setContenu(tfCmntr.getText());
        
        cmntr.ajouterCommentaire(r);
        showCommentaire();}
    }

    @FXML
    private void UpdateCmntr(ActionEvent event) {
         Alert alert =new Alert(Alert.AlertType.WARNING);
        alert.setTitle("confirmer la modification");
        alert.setContentText("voulez vous vraiment modifier");
        alert.showAndWait();
         Commentaire r =new Commentaire();
         int idModifier=tvcommentaire.getSelectionModel().getSelectedItem().getId_com();
        r.setContenu(tfCmntr.getText());
       
        cmntr.updateCommentaire(idModifier, r);
        showCommentaire();
    }

    @FXML
    private void DeleteCmntr(ActionEvent event) {
         Alert alert =new Alert(Alert.AlertType.WARNING);
        alert.setTitle("confirmer la suppression");
        alert.setContentText("voulez vous vraiment supprimer ?");
        alert.showAndWait();
        int idSup=tvcommentaire.getSelectionModel().getSelectedItem().getId_com();
        cmntr.supprimerCommentaire(idSup);
        showCommentaire();
    }
    private String ControleCmntr(){

    String erreur="";
if(tfCmntr.getText().trim().isEmpty()){
erreur+="nom vide \n";}

return erreur;

}

    @FXML
    private void btn_vehicule(ActionEvent event) throws IOException {
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/GestionCommentaire.fxml"));
           Parent root1 = (Parent) fxmlLoader.load();
           Stage stage = new Stage();
           stage.setScene(new Scene(root1));
           stage.show();
        
    }
   
}
