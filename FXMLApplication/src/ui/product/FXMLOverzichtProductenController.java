/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.product;

import controller.DataController;
import entitys.Product;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author stinson
 */
public class FXMLOverzichtProductenController implements Initializable {

    @FXML
    private TableView tabel;
    private List<Product> productenLijst;
    private DataController controller;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        controller = new DataController();
        productenLijst = controller.getProductenLijst();
        ObservableList<Product> list = FXCollections.observableArrayList(productenLijst);

        TableColumn naamCol = new TableColumn();
        TableColumn prijsCol = new TableColumn();
        TableColumn aantalInVoorraadCol = new TableColumn();

        naamCol.setText("Naam:");
        prijsCol.setText("Prijs");
        aantalInVoorraadCol.setText("Resterend aantal in voorraad");

        naamCol.setCellValueFactory(new PropertyValueFactory<Product, String>("naam"));
        prijsCol.setCellValueFactory(new PropertyValueFactory<Product, Double>("prijs"));
        aantalInVoorraadCol.setCellValueFactory(new PropertyValueFactory<Product, Integer>("aantalInvoorraad"));


        tabel.setItems(list);
        tabel.getColumns().clear();
        tabel.getColumns().addAll(naamCol, prijsCol, aantalInVoorraadCol);
        tabel.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }
}
