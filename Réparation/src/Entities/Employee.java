/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

/**
 *
 * @author yosra
 */
public class Employee {
    private int id;
    private String nom;
    private String prenom;
    private String typee;
    
    
    
    public Employee() { 
    }

    public Employee(int id, String nom, String prenom, String typee) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.typee= typee;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getTypee() {
        return typee;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setTypee(String typee) {
        this.typee = typee;
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom +"typee"+typee + '}';
    }
}
    

