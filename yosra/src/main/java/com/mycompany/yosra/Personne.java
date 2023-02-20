package com.mycompany.yosra;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author yosra
 */
public class Personne {
    private int age;
    private String nom;

    public int getAge() {
        return age;
    }

    public String getNom() {
        return nom;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "personne{" + "age=" + age + ", nom=" + nom + '}';
    }

    public Personne(int age, String nom) {
        this.age = age;
        this.nom = nom;
    }
    
}
