/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet_transport.interfaces;

import java.util.ArrayList;
import java.util.Map;
import javafx.collections.ObservableList;
import projet_transport.model.Utilisateur;

/**
 *
 * @author aziz
 */
public interface interfaceClient<T> {
     public void inscription(T c);
     public void ajouter(T c);
     public void modifier(T c);
     public void Supprimer(int id);
     public Utilisateur getUserById(String id);
     public boolean CheckUserByEmail(String email);
     public ObservableList<Utilisateur> afficher() throws ClassNotFoundException;
}
