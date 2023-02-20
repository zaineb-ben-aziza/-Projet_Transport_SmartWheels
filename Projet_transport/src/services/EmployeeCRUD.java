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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



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
public ObservableList<Employee> afficher() 
     {
        ObservableList<Employee> data=FXCollections.observableArrayList();
        try
        {
        PreparedStatement ps=MyConnection.getInstance().getCnx().prepareStatement("select * from employee");
        ResultSet rs=ps.executeQuery();
            while (rs.next())
            {
            data.add(new Employee(rs.getInt("id"),rs.getString("nom"),rs.getString("prenom"),rs.getString("type")));
            }
        }
        catch(Exception ex)
        {
        System.out.println(ex);
        }
        ;
             return data;
     }
public void Supprimer(int id) {
          try {
            PreparedStatement ps=MyConnection.getInstance().getCnx().prepareStatement("delete from employee where id=?");
            ps.setInt(1,id);
            ps.executeUpdate();
        } catch (SQLException ex) {
              System.out.println(ex);
        }
    }
 public void modifier(Employee c)
    {
      
       try {
        String sql="update employee set nom=?,prenom=?,type=? where id=?;";
        PreparedStatement ps=MyConnection.getInstance().getCnx().prepareStatement(sql);
        ps.setString(1,c.getNom());
        ps.setString(2,c.getPrenom());
        ps.setString(3,c.getTypee());
         ps.setInt(4,c.getId());
        ps.execute();
        }
        catch (Exception ex) {
        System.out.println(ex);
        }
    }
}

    

