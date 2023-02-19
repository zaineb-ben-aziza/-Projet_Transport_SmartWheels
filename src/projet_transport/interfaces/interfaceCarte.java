/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet_transport.interfaces;

import javafx.collections.ObservableList;
import projet_transport.model.Carte_fidelite;
import projet_transport.model.Utilisateur;

/**
 *
 * @author aziz
 */
public interface interfaceCarte <T> {
     public void ajouter(T c);
     public void modifier(T c);
     public void Supprimer(int id);
     public ObservableList<String> GetAllIdUser();
     public ObservableList<Carte_fidelite> afficher();
    
}
