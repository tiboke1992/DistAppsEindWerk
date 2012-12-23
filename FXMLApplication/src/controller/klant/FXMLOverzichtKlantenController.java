/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.klant;

import controller.DataController;
import entitys.Klant;
import java.net.URL;
import java.sql.Date;
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
 * @author tibo
 */
public class FXMLOverzichtKlantenController implements Initializable {

    @FXML
    private TableView tabel;
    private List<Klant> klantenLijst;
    private DataController controller;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        controller = new DataController();
        klantenLijst = controller.getKlantenLijst();
        //table maken en invullen

        ObservableList<Klant> list = FXCollections.observableArrayList(klantenLijst);
        //Kollommen
        TableColumn voornaamCol = new TableColumn();
        TableColumn naamCol = new TableColumn();
        TableColumn gebDatumCol = new TableColumn();
        TableColumn telefoonCol = new TableColumn();

        voornaamCol.setText("Voornaam");
        voornaamCol.setCellValueFactory(new PropertyValueFactory<Klant, String>("voorNaam"));
        naamCol.setText("Naam");
        naamCol.setCellValueFactory(new PropertyValueFactory<Klant, String>("naam"));
        gebDatumCol.setText("Geboorte Datum");
        gebDatumCol.setCellValueFactory(new PropertyValueFactory<Klant, Date>("geboorteDatum"));
        telefoonCol.setText("Telefoon nummer");
        telefoonCol.setCellValueFactory(new PropertyValueFactory<Klant, String>("telefoonNummer"));

        tabel.setItems(list);
        tabel.getColumns().clear();
        tabel.getColumns().addAll(voornaamCol, naamCol, gebDatumCol, telefoonCol);
        tabel.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }
}
