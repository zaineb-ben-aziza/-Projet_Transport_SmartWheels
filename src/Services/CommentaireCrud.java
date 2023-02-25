/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Commentaire;

import Utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class CommentaireCrud implements CommentaireInterface<Commentaire> {
     Connection cnx;
    public CommentaireCrud() {
        cnx =MyConnection.getInstance().getCnx();
    }
     @Override
     public void ajouterCommentaire(Commentaire t) {
        try {
            String requete = "INSERT INTO `commentaire`(`id_com`, `contenu`)"
                    + "VALUES (?,?)";
            PreparedStatement pst = cnx
                                    .prepareStatement(requete);
            pst.setInt(1, t.getId_com());
            pst.setString(2, t.getContenu());
        
            pst.executeUpdate();
            System.out.println("Done!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
     @Override
     public void supprimerCommentaire(int id){
        try {
            String query ="DELETE FROM `commentaire` WHERE id_com="+id;
            Statement st=cnx.createStatement();
            st.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(CommentaireCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
}
     @Override
     public void updateCommentaire(int id,Commentaire C){
    try {
            String query ="UPDATE `commentaire` SET `contenu`='"+C.getContenu()+"' WHERE id_com="+id;
            Statement st=cnx.createStatement();
            st.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(CommentaireCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
}
    

     @Override
    public List<Commentaire> listeDesCommentaires() {

         List<Commentaire> myList = new ArrayList<>();
        try {

            String requete = "SELECT * FROM commentaire";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            
            while(rs.next()){
                  Commentaire p = new Commentaire();
                       System.out.println(rs.getString("contenu"));
                p.setId_com(rs.getInt("id_com"));
                p.setContenu(rs.getString("contenu"));
                myList.add(p);
                
               
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }
 
    
}
