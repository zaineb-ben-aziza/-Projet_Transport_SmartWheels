/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet_transport.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aziz
 */
public class MyConnexion {
     String url="jdbc:mysql://localhost:3306/smart_wheel";
    String login="root";
    String pwd="";
    static MyConnexion instance;
    private Connection cnx;
    private MyConnexion() {
        try {
           cnx=DriverManager.getConnection(url,login,pwd);
        } catch (SQLException ex) {
            Logger.getLogger(MyConnexion.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

    public Connection getCnx() {
        return cnx;
    }
    public static MyConnexion getInstance(){
        if (instance == null)
             return new MyConnexion();
        else
            return instance ;
                
                
    }
    
}
