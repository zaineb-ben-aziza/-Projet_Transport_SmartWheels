/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.testjava83a17;

/**
 *
 * @author yosra
 */
public class  maindeclancheur {

    public static void main(String[] args) {
        declencherTraitement(new Declancheur() {
            @Override
            public void declancher(String msg) {
                System.out.println("msg");
            }
        });
}
public static void declencherTraitement (Declancheur d){
d.declancher("bonjour Tunsie");
        
}
}
