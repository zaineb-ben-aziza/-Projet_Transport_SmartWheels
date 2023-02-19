/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet_transport;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.BiConsumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import projet_transport.model.Utilisateur;
import projet_transport.services.UtilisateurS;
/**
 *
 * @author aziz
 */
public class loginController implements Initializable {
    
    @FXML
    private AnchorPane anchor;
    @FXML
    private Pane left;
    @FXML
    private Label layout2_label1;
    @FXML
    private Label layout2_label2;
    @FXML
    private Label layout2_inscrit;
    @FXML
    private Button layout2_sign_up;
    @FXML
    private Pane right;
    @FXML
    private Label layout2_label11;
    @FXML
    private Label layout2_label21;
    @FXML
    private Label layout2_inscrit2;
    @FXML
    private Pane layout1;
    @FXML
    private Label layout1_label1;
    @FXML
    private Button layout1_sign_in;
    @FXML
    private TextField c_nom;
    @FXML
    private TextField c_passe;
    @FXML
    private Pane layout2;
    @FXML
    private Label layout1_label11;
    @FXML
    private TextField i_nom;
    @FXML
    private TextField i_prenom;
    @FXML
    private TextField i_email;
    @FXML
    private DatePicker i_date;
    @FXML
    private TextField i_telephone;
    @FXML
    private TextField i_region;
    @FXML
    private TextField i_passe;
    @FXML
    private TextField i_passec;
    @FXML
    private RadioButton i_h;
    @FXML
    private ToggleGroup i_grenre;
    @FXML
    private RadioButton i_f;
    @FXML
    private RadioButton i_a;
    @FXML
    private Button layout2_sign_in;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private Label lb_vide;
    @FXML
    private Label lb_ajout;
    @FXML
    private Label lb_nom;
    @FXML
    private Label lb_prenom;
    @FXML
    private Label lb_region;
    @FXML
    private Label lb_email;
    @FXML
    private Label lb_tell;
    @FXML
    private Label lb_passe;
    @FXML
    private Label f_nom;
    @FXML
    private Label f_prenom;
    @FXML
    private Label f_region;
    @FXML
    private Label f_tel;
    @FXML
    private Label f_email;
    @FXML
    private Label f_passec;
    @FXML
    private Label f_passe;
    @FXML
    private Label lb_email1;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            
            lb_ajout.setVisible(false);
           lb_vide.setVisible(false);
           right.setVisible(false);
           layout2.setVisible(false);
          
    }    

    @FXML
    private void swip_right_btn(MouseEvent event) throws InterruptedException {
        TranslateTransition slide =new TranslateTransition();
        slide.setDuration(Duration.seconds(0.5));
        slide.setNode(anchor);
        slide.setToX(740);
        slide.play();
        PauseTransition pp = new PauseTransition(Duration.millis(360));
         pp.setOnFinished(e->{
        layout1.setVisible(false);
        left.setVisible(false);
        right.setVisible(true);
        layout2.setVisible(true);         
         });
         pp.play();

   
   
    }

    @FXML
    private void swip_left_btn(MouseEvent event)   {
        TranslateTransition slide =new TranslateTransition();
        slide.setDuration(Duration.seconds(0.5));
        slide.setNode(anchor);
        slide.setToX(0);
        slide.play();
        PauseTransition pp = new PauseTransition(Duration.millis(300));
        pp.setOnFinished(e->{
                      left.setVisible(true);
        right.setVisible(false);
        layout1.setVisible(true);
         layout2.setVisible(false);
                });
        pp.play();
       
    }
    @FXML
    private void inscription(MouseEvent event) {
        this.label_initialisation();
        UtilisateurS util=new UtilisateurS();  
       
                
            if (!(i_telephone.getText().equals("") || i_nom.getText().equals("") ||  i_email.getText().equals("") || i_prenom.getText().equals("") || i_region.getText().equals("") ||  i_passe.getText().equals("") || i_passec.getText().equals("")))
            {
                if (containsOnlyLetters(i_nom.getText())){
                    lb_nom.setVisible(true);
                    f_nom.setVisible(true);
                 }
                else if (containsOnlyLetters(i_prenom.getText()))
                {
                    lb_prenom.setVisible(true);
                    f_prenom.setVisible(true);
                }
                else   if (util.CheckUserByEmail(i_email.getText()) )
                {
                    lb_email.setVisible(true);
                    f_email.setVisible(true);
                }
                else if ( !isEmailValid(i_email.getText()))
                 {
                       f_email.setVisible(true);
                      lb_email1.setVisible(true);
                 }
                else  if (containsOnlyNumber(i_telephone.getText()) || i_telephone.getText().length() != 8 )
                {
                    lb_tell.setVisible(true);
                    f_tel.setVisible(true);
                }
                else if (containsOnlyLetters(i_region.getText()))
                {
                    lb_region.setVisible(true);
                    f_region.setVisible(true);
                }
                else if (!i_passe.getText().equals(i_passec.getText()))
                {
                    lb_passe.setVisible(true);
                    f_passe.setVisible(true);
                    f_passec.setVisible(true);
                }
                else
                  {
                  Utilisateur utilisateur=new Utilisateur(0,i_nom.getText(),i_prenom.getText(), i_email.getText(), i_date.getValue().toString(),Integer.parseInt(i_telephone.getText()),i_region.getText() , i_passe.getText(),i_h,i_f);
                  util.inscription(utilisateur);
                  lb_ajout.setVisible(true);
                  System.out.println("ajout avec succes");
                  }
            }
             else
            {
            lb_vide.setVisible(true);
            }
          
    }

    @FXML
    private void connexion(ActionEvent event) throws IOException {
        try{
         Parent root = FXMLLoader.load(getClass().getResource("/projet_transport/views/gestion_utilisateur.fxml"));  
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
    public static boolean containsOnlyLetters(String str) {
    return !str.matches("[a-zA-Z]+"); 
    }
    public static boolean containsOnlyNumber(String str) {
    return !str.matches("[0-9]+"); 
    }

   
    public void label_initialisation()
    {
        //controle en haut
        lb_vide.setVisible(false);
        lb_ajout.setVisible(false);
        lb_nom.setVisible(false);
        lb_prenom.setVisible(false);
       lb_region.setVisible(false);
       lb_tell.setVisible(false);
       lb_passe.setVisible(false);
       lb_email.setVisible(false);
       //fleche a droite
        f_nom.setVisible(false);
        f_prenom.setVisible(false);
        f_region.setVisible(false);
        f_tel.setVisible(false);
        f_passe.setVisible(false);
        f_passec.setVisible(false);
        f_email.setVisible(false);
        lb_email1.setVisible(false);
        
    }
   public static boolean isEmailValid(String email) {
    Pattern pattern = Pattern.compile("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
    Matcher matcher = pattern.matcher(email);
    return matcher.matches();
}
    
}


 
    

