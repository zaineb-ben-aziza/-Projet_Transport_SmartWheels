/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;
import Entities.Reparation;
import interfaces.InterfaceCRUD;
import utils.MyConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author yosra
 */

    public class ReparationCRUD implements InterfaceCRUD<Reparation>{

    /*@Override
    public void ajouterEntitee(Personne t) {
        try{
        String requte="INSERT INTO personne(nom,prenom)"
                + "VALUES ('"+t.getNom()+"','"+t.getPrenom()+"')";
        Statement st=new MyConnection().getCnx().createStatement();
        st.executeUpdate(requte);
        System.out.println("done");
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }*/
    @Override
    public void ajouterEntitee(Reparation r) {
        try{
        String requte="INSERT INTO Reparation(id_v,id_employee,Description_panne,Etat,Date_rep,Date_manu,Date_defect)"
                + "VALUES (?,?,?,?,?,?)";
        PreparedStatement pst= MyConnection.getInstance().getCnx().prepareStatement(requte);
        pst.setInt(1,r.getId_v()); 
        pst.setString(3,r.getDescription_panne());
        pst.setString(4,r.getEtat());
        pst.setDate(5,r.getDate_rep());
        pst.setDate(6,r.getDate_manu());
        pst.setDate(7,r.getDate_defect());
        pst.setInt(8,r.getId_employee());
        pst.executeUpdate();
        System.out.println("Done");
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
        
}
    }
    @Override
    public List<Reparation> listeDesEntites() {
     List<Reparation> myList= new ArrayList<>();
        try{
     
     String requete = "SELECT * FROM employee";
     Statement st = MyConnection.getInstance().getCnx().createStatement();
     ResultSet rs = st.executeQuery(requete);
     while(rs.next()){
         Reparation r = new Reparation();
         r.setId_v(rs.getInt("1"));
         r.setDescription_panne(rs.getString("Description_panne"));
         r.setEtat(rs.getString("Etat"));
         r.setDate_rep(rs.getDate("Date_rep"));
         r.setDate_manu(rs.getDate("Date_manu"));
         r.setDate_defect(rs.getDate("Datedefect"));
         r.setId_employee(rs.getInt("Id_employee")); 
         myList.add(r);
     }
    }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return myList;
        
    }
    public void supprimer(int x ) {
        String query = "DELETE FROM Reparation WHERE Reparation.id_Reparation = " + x + "";
        try{
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            st.executeUpdate(query);
            System.out.println("Article Deleted Successfully");
        }
        catch (SQLException r){
            System.out.println(r.getMessage());
        }
    }
    }
