/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.bestelling;

import controller.DataController;
import entitys.Bestelling;
import entitys.Klant;
import entitys.Product;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import listCells.KlantListCell;

/**
 * FXML Controller class
 *
 * @author tibo
 */
public class FXMLBestellingOverzichtController implements Initializable {

    @FXML
    private ComboBox box;
    @FXML
    private TableView tabel;
    @FXML
    private TableView productenTabel;
    private DataController controller;
    private List<Klant> lijst;
    private Klant k;
    private List<Bestelling> bestellingLijst;
    private Bestelling bestelling;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        productenTabel.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        controller = new DataController();
        this.pupulateCombo();

        bestellingLijst = controller.getBestellingenVanKlant(this.getCurrentKlant());
        ObservableList<Bestelling> list = FXCollections.observableArrayList(bestellingLijst);
        TableColumn datum = new TableColumn();
        datum.setText("Datum");
        datum.setCellValueFactory(new PropertyValueFactory<Bestelling, Date>("datum"));
        tabel.setItems(list);
        tabel.getColumns().clear();
        tabel.getColumns().addAll(datum);
        tabel.getSelectionModel().selectFirst();
        tabel.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        setBestelling((Bestelling) tabel.getSelectionModel().getSelectedItem());


        List<Product> producten = controller.getProductenVanBestelling(this.getBestelling());
        ObservableList<Product> pList = FXCollections.observableArrayList(producten);
        TableColumn naam = new TableColumn();
        TableColumn prijs = new TableColumn();
        naam.setText("Naam");
        prijs.setText("Prijs");
        naam.setCellValueFactory(new PropertyValueFactory<Product, String>("naam"));
        prijs.setCellValueFactory(new PropertyValueFactory<Product, Double>("prijs"));
        productenTabel.setItems(pList);
        productenTabel.getColumns().clear();
        productenTabel.getColumns().addAll(naam, prijs);
        productenTabel.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        box.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue ov, Object t, Object t1) {
                Klant s = (Klant) t1;
                setSelectedKlant(s);
                pop1();
                pop2();
            }
        });
        //tabel initialiseren

        tabel.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue ov, Object t, Object t1) {
                if (t1 != null) {
                    Bestelling b = (Bestelling) t1;
                    if (b != null) {
                        setBestelling(b);
                        maakVolgendeTabel();
                    }
                }

            }
        });
    }

    public void pupulateCombo() {
        lijst = controller.getKlantenLijst();
        ObservableList<Klant> list = FXCollections.observableArrayList(lijst);
        box.setButtonCell(new KlantListCell());
        box.setCellFactory(new Callback<ListView<Klant>, ListCell<Klant>>() {
            @Override
            public ListCell<Klant> call(ListView<Klant> p) {
                return new KlantListCell();
            }
        });
        box.getItems().clear();
        box.setItems(list);
        box.getSelectionModel().selectFirst();
        k = (Klant) box.getSelectionModel().getSelectedItem();
    }

    public void setSelectedKlant(Klant klant) {
        this.k = klant;
    }

    public Klant getCurrentKlant() {
        return this.k;
    }

    public void maakVolgendeTabel() {
        try {
            List<Product> producten = controller.getProductenVanBestelling(this.getBestelling());
            if (producten != null && producten.size() > 0) {
                ObservableList<Product> pList = FXCollections.observableArrayList(producten);
                productenTabel.setItems(pList);
            }
        } catch (NullPointerException e) {
        }
    }

    public Bestelling getBestelling() {
        return this.bestelling;
    }

    public void setBestelling(Bestelling bestelling) {
        this.bestelling = bestelling;
    }

    public void pop1() {
        bestellingLijst = controller.getBestellingenVanKlant(this.getCurrentKlant());
        ObservableList<Bestelling> list = FXCollections.observableArrayList(bestellingLijst);
        tabel.setItems(list);
    }

    public void pop2() {
        tabel.getSelectionModel().selectFirst();
        Bestelling r = (Bestelling) tabel.getSelectionModel().getSelectedItem();
        this.setBestelling(r);
        List<Product> producten = controller.getProductenVanBestelling(this.getBestelling());
        ObservableList<Product> pList = FXCollections.observableArrayList(producten);
        productenTabel.setItems(pList);
    }
}
