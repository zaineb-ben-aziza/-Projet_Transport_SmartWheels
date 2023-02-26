/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author user
 */
public class MyConnexion {
    
    String url="jdbc:mysql://localhost:3307/smart_wheel";
    String login="root";
    String pwd="";
   private  Connection cnx;
   
   //2 etape creer  d'une  instance  Myconnexion
   private static MyConnexion instance;
//etape 1: changer private
    private MyConnexion() {
        
        try { 
            cnx= DriverManager.getConnection(url,login,pwd);
            //System.out.println("connexion etablie");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Connection getCnx() {
        return cnx;
    }
    
    
    //3 etape creer  methoed static instance
    public static MyConnexion getIstance()
    {
    if(instance==null){
    instance =new MyConnexion();
    
    }
    return instance;  
    }
    
    
    
    
    
    }
    
