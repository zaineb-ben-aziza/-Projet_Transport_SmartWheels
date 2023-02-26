/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entities.Commentaire;
import Services.CommentaireCrud;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class GestionCommentaireController implements Initializable {

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
         showCommentaire();
    }    
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
        alert.setContentText("commentaire ajoutée");
        alert.showAndWait();}
         else if(detectBadWords()){
         }
         else{
        Commentaire r =new Commentaire();
        r.setContenu(tfCmntr.getText());
        
        cmntr.ajouterCommentaire(r);
        showCommentaire();}
    }

    @FXML
    private void UpdateCmntr(ActionEvent event) {
         if (ControleCmntr().length()>0){
         Alert alert =new Alert(Alert.AlertType.WARNING);
        alert.setTitle("confirmer la modification");
        alert.setContentText("voulez vous vraiment modifier");
        alert.showAndWait();}
          else if(detectBadWords()){
         }
         else{
         Commentaire r =new Commentaire();
         int idModifier=tvcommentaire.getSelectionModel().getSelectedItem().getId_com();
        r.setContenu(tfCmntr.getText());
       
        cmntr.updateCommentaire(idModifier, r);
        showCommentaire();
    }}

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
     public boolean detectBadWords() {
        String[] badWords = { "bad", "ugly", "nasty" }; // replace with your own list of bad words
        String comment = tfCmntr.getText().toLowerCase();
        boolean hasBadWord = false;
        for (String word : badWords) {
            if (comment.contains(word)) {
                hasBadWord = true;
                break;
            }
        }
        if (hasBadWord) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setContentText("Your comment contains a bad word.");
            alert.showAndWait();
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Your comment is good.");
            alert.showAndWait();
            return false ;
        }
    }

   
}
