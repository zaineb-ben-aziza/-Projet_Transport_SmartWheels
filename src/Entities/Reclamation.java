/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Objects;

/**
 *
 * @author user
 */
public class Reclamation {
   private int Id;
   private String Nom;
   private String Prenom;
   private String Adresse;
   private String Contenu;
    public Reclamation(int Id, String Nom, String Prenom, String Adresse, String Contenu) {
        this.Id = Id;
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Adresse = Adresse;
        this.Contenu = Contenu;
    }

    public Reclamation() {
    }

    public int getId() {
        return Id;
    }

    public String getNom() {
        return Nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public String getAdresse() {
        return Adresse;
    }

    

    public String getContenu() {
        return Contenu;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public void setPrenom(String Prenom) {
        this.Prenom = Prenom;
    }

    public void setAdresse(String Adresse) {
        this.Adresse = Adresse;
    }

    public void setContenu(String Contenu) {
        this.Contenu = Contenu;
    }

   
    @Override
    public String toString() {
        return "Reclamation{" + "Id=" + Id + ", Nom=" + Nom + ", Prenom=" + Prenom + ", Adresse=" + Adresse + ", Contenu=" + Contenu + '}';
    }
     
}
