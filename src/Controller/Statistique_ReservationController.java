/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import entities.Reservation;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import services.Reservation_Service;

/**
 * FXML Controller class
 *
 * @author user
 */
public class Statistique_ReservationController implements Initializable {

    @FXML
    private BarChart<String, Integer> chart;
    int  d=java.time.LocalDate.now().getDayOfMonth();
    @FXML
    private AnchorPane contentArea;
    @FXML
    private Pane left;
    @FXML
    private Button back_reservation;

    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          Reservation_Service s=new Reservation_Service();
         ObservableList<Reservation> l= s.afficher_reservation();
         String []data ={"A","b","A","b","A","b","A","b","A","b","A","b","A","b"};
         for (int i=0;i<l.size();i++)
         {
         data[i]= String.valueOf(Integer.parseInt(l.get(i).getDate_fin().substring(8, 10) )- Integer.parseInt(l.get(i).getDate_debut().substring(8, 10))) ;
         }
         for (int i=0;i<l.size();i++)
        System.out.println(data[i]);

        // calcul de la fréquence de chaque élément
        Map<String, Integer> frequencyMap = new HashMap<>();
        Arrays.stream(data).forEach(element -> frequencyMap.merge(element, 1, Integer::sum));

        // création d'un graphique à barres pour représenter la fréquence de chaque élément
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        frequencyMap.forEach((key, value) -> series.getData().add(new XYChart.Data<>(key, value)));
        chart.getData().add(series);
        chart.setTitle("Fréquence de chaque élément");
        xAxis.setLabel("Élément");
        yAxis.setLabel("Fréquence");
        chart.setLegendVisible(false);
        // TODO
    }    

    @FXML
    private void back_reservation(ActionEvent event) {
                try{
         Parent root = FXMLLoader.load(getClass().getResource("/gui/GestionDesReservations.fxml"));  
         Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        }
        catch(Exception e)
        {
            System.out.println("Probleme:"+e);
        }
    }
    
}
