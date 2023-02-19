/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.time.LocalDate;
import oracle.sql.DATE;

/**
 *
 * @author user
 */
public class Reservation {
     private  int id_res;
     private  String date_debut;
      private  String date_fin;
      private int duree;
      
          public  Reservation() {
    
      this.id_res=0;
      this.date_debut=null;
       this.date_fin=null;
        this.duree=0;
      
    }

    public Reservation(int id_res, String date_debut, String date_fin, int duree) {
        this.id_res = id_res;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.duree = duree;
    }

    public int getId_res() {
        return id_res;
    }

    public void setId_res(int id_res) {
        this.id_res = id_res;
    }

    public String getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(String date_debut) {
        this.date_debut = date_debut;
    }

    public String getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(String date_fin) {
        this.date_fin = date_fin;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }
     
    
}
