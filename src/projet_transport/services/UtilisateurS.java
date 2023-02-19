/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet_transport.services;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;
import projet_transport.controler.Gestion_UtilisateurController;
import projet_transport.interfaces.interfaceClient;
import projet_transport.model.Utilisateur;
import projet_transport.utils.MyConnexion;
/**
 *
 * @author aziz
 */
public class UtilisateurS implements interfaceClient<Utilisateur>{
    @FXML private TableView<Utilisateur> affichage;
    @FXML private TableColumn<Utilisateur,String>nom;
    public void inscription(Utilisateur c)
    {
         try {
            String sql="insert into utilisateur (nom,prenom,email,date_naissance,telephone,region,genre,mot_passe) values (?,?,?,STR_TO_DATE(?,'%Y-%m-%d'),?,?,?,?)";
            PreparedStatement ps=MyConnexion.getInstance().getCnx().prepareStatement(sql);
            ps.setString(1,c.getNom());
            ps.setString(2,c.getPrenom());
            ps.setString(3,c.getEmail());
            ps.setString(4,c.getDate());
            ps.setInt(5,c.getTelephone());
            ps.setString(6,c.getRegion());
            ps.setString(7,c.getGenre());
            ps.setString(8,c.getMot_passe());
         ps.execute(); 
        } 
        catch (Exception ex) 
        {
        System.out.println(ex);
        }
    }
     public void ajouter(Utilisateur c)
    {
        try {      
        String sql="insert into utilisateur (nom,prenom,email,date_naissance,telephone,region,genre,mot_passe,type) values (?,?,?,STR_TO_DATE(?,'%Y-%m-%d'),?,?,?,?,?)";
        PreparedStatement ps=MyConnexion.getInstance().getCnx().prepareStatement(sql);
        ps.setString(1,c.getNom());
        ps.setString(2,c.getPrenom());
        ps.setString(3,c.getEmail());
        ps.setString(4,c.getDate());
        ps.setInt(5,c.getTelephone());
        ps.setString(6,c.getRegion());
        ps.setString(7,c.getGenre());
        ps.setString(8,c.getMot_passe());
        ps.setString(9,c.getType());
        ps.execute();       
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
     public ObservableList<Utilisateur> afficher() 
     {
        ObservableList<Utilisateur> data=FXCollections.observableArrayList();
        try
        {
        PreparedStatement ps=MyConnexion.getInstance().getCnx().prepareStatement("select * from utilisateur");
        ResultSet rs=ps.executeQuery();
            while (rs.next())
            {
            data.add(new Utilisateur(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10)));
            }
        }
        catch(Exception ex)
        {
        System.out.println(ex);
        }
        ;
             return data;
     }
     public void modifier(Utilisateur c)
    {
       try {
        String sql="update utilisateur set nom=?,prenom=?,email=?,date_naissance=?,telephone=?,region=?,genre=?,mot_passe=?,type=? where id=?;";
        PreparedStatement ps=MyConnexion.getInstance().getCnx().prepareStatement(sql);
        ps.setString(1,c.getNom());
        ps.setString(2,c.getPrenom());
        ps.setString(3,c.getEmail());
        ps.setString(4,c.getDate());
        ps.setInt(5,c.getTelephone());
        ps.setString(6,c.getRegion());
        ps.setString(7,c.getGenre());
        ps.setString(8,c.getMot_passe());
        ps.setString(9,c.getType());
        ps.setInt(10,c.getId());
        ps.execute();
        }
        catch (Exception ex) {
        System.out.println(ex);
        }
    }
     
  
 @Override
    public void Supprimer(int id) {
          try {
            PreparedStatement ps=MyConnexion.getInstance().getCnx().prepareStatement("delete from utilisateur where id=?");
            ps.setInt(1,id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Gestion_UtilisateurController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Utilisateur getUserById(String id) {
        
          Utilisateur u=new Utilisateur();
         try{
             
          PreparedStatement ps=MyConnexion.getInstance().getCnx().prepareStatement("select * from utilisateur where id=?");
          ps.setString(1, id);
          ResultSet rs=ps.executeQuery();
          while (rs.next())
          {
              u.setNom(rs.getString(2));
              u.setPrenom(rs.getString(3));
              u.setEmail(rs.getString(4));
              u.setDate(rs.getString(5));
              u.setTelephone(rs.getInt(6));
              u.setRegion(rs.getString(7));
              u.setGenre(rs.getString(8));
              u.setMot_passe(rs.getString(9));
             u.setType(rs.getString(10));   
          }
         
         }
         catch(Exception ex)
         {
             System.out.println(ex);
         }
         ;
         return u;
    }

   

    @Override
    public boolean CheckUserByEmail(String email) {
try{
             
          PreparedStatement ps=MyConnexion.getInstance().getCnx().prepareStatement("select * from utilisateur where email=?");
          ps.setString(1, email);
          ResultSet rs=ps.executeQuery();
          while (!rs.next())
          {
          return false;
              
          }
         }
         catch(Exception ex)
         {
             System.out.println(ex);
         }
           return true; 
           }
}
