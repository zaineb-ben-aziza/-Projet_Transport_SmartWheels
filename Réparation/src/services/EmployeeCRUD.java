/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;
import Entities.Employee;
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

    public class EmployeeCRUD implements InterfaceCRUD<Employee>{

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
    public void ajouterEntitee(Employee e) {
        try{
        String requte="INSERT INTO employee(nom,prenom,type)"
                + "VALUES (?,?,?)";
        PreparedStatement pst= MyConnection.getInstance().getCnx().prepareStatement(requte);
        pst.setString(1,e.getNom());
        pst.setString(2,e.getPrenom());
        pst.setString(3,e.getTypee());
        pst.executeUpdate();
        System.out.println("Done");
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
    @Override
    public List<Employee> listeDesEntites() {
     List<Employee> myList= new ArrayList<>();
        try{
     
     String requete = "SELECT * FROM employee";
     Statement st = MyConnection.getInstance().getCnx().createStatement();
     ResultSet rs = st.executeQuery(requete);
     while(rs.next()){
         Employee e = new Employee();
         e.setId(rs.getInt(1));
         e.setNom(rs.getString("nom"));
         e.setPrenom(rs.getString("prenom"));
         e.setTypee(rs.getString("typee"));
         myList.add(e);
     }
    }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return myList;
        
    }
    public void supprimer(int x ) {
        String query = "DELETE FROM Employee WHERE Employee.id = " + x + "";
        try{
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            st.executeUpdate(query);
            System.out.println("Employee Deleted Successfully");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

}
    

