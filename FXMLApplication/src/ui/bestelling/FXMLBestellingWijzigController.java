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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import listCells.KlantListCell;
import listCells.ProductCustomListCell;

/**
 * FXML Controller class
 *
 * @author tibo
 */
public class FXMLBestellingWijzigController implements Initializable {

    @FXML
    private ComboBox box;
    private List<Klant> lijst;
    private Klant k;
    private DataController controller;
    @FXML
    private TableView bestellingenView;
    private List<Bestelling> bestellingLijst;
    private Bestelling bestelling;
    @FXML
    private TableView productenTabel;
    @FXML
    private ListView left;
    private List<Product> productenLijstVanKlant;
    private List<Product> productenLijst;
    @FXML
    private Button save;
    @FXML
    private Button add;
    @FXML
    private Button remove;
    @FXML
    private Label lbl;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        controller = new DataController();
        //combobox invullen
        this.populateCombo();
        this.wisselenVanComboBox();
        //besteling tabel invullen
        this.maakBestellingView();
        TableColumn datum = new TableColumn();
        datum.setText("Datum");
        datum.setCellValueFactory(new PropertyValueFactory<Bestelling, Date>("datum"));
        bestellingenView.getColumns().clear();
        bestellingenView.getColumns().addAll(datum);
        bestellingenView.getSelectionModel().selectFirst();
        bestellingenView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        setBestelling((Bestelling) bestellingenView.getSelectionModel().getSelectedItem());
        //productentabel invullen
        //producten van bestelling tabel
        try {
            List<Product> producten = controller.getProductenVanBestelling(this.getBestelling());
            ObservableList<Product> pList = FXCollections.observableArrayList(producten);
            productenTabel.setItems(pList);
        } catch (NullPointerException e) {
        }
        TableColumn naam = new TableColumn();
        TableColumn prijs = new TableColumn();
        naam.setText("Naam");
        prijs.setText("Prijs");
        naam.setCellValueFactory(new PropertyValueFactory<Product, String>("naam"));
        prijs.setCellValueFactory(new PropertyValueFactory<Product, Double>("prijs"));
        productenTabel.getColumns().clear();
        productenTabel.getColumns().addAll(naam, prijs);
        productenTabel.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        bestellingenView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue ov, Object t, Object t1) {
                if (t1 != null) {
                    Bestelling b = (Bestelling) t1;
                    if (b != null) {
                        setBestelling(b);
                        maakVolgendeTabel();
                        initializeProductenLijstVanKlant();
                    }
                }

            }
        });
        populateListViewLings();
        saveButton();
        productenLijstVanKlant = new ArrayList<Product>();
        initializeProductenLijstVanKlant();
        initializeAddButton();
        initializeRemoveButton();
    }

    public void populateCombo() {
        try {
            lijst = controller.getKlantenLijst();
            ObservableList<Klant> list = FXCollections.observableArrayList(lijst);
            box.setItems(list);
        } catch (NullPointerException e) {
        }
        box.setButtonCell(new KlantListCell());
        box.setCellFactory(new Callback<ListView<Klant>, ListCell<Klant>>() {
            @Override
            public ListCell<Klant> call(ListView<Klant> p) {
                return new KlantListCell();
            }
        });
        box.getSelectionModel().selectFirst();
        k = (Klant) box.getSelectionModel().getSelectedItem();
    }

    public void maakBestellingView() {
        try {
            bestellingLijst = controller.getBestellingenVanKlant(this.getCurrentKlant());
            ObservableList<Bestelling> list = FXCollections.observableArrayList(bestellingLijst);
            bestellingenView.setItems(list);
        } catch (NullPointerException e) {
        }
    }

    public void setSelectedKlant(Klant klant) {
        this.k = klant;
    }

    public Klant getCurrentKlant() {
        return this.k;
    }

    public Bestelling getBestelling() {
        return this.bestelling;
    }

    public void setBestelling(Bestelling bestelling) {
        this.bestelling = bestelling;
    }

    public void wisselenVanComboBox() {
        box.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue ov, Object t, Object t1) {
                Klant s = (Klant) t1;
                setSelectedKlant(s);
                pop1();
                pop2();
            }
        });
    }

    public void pop1() {
        try {
            bestellingLijst = controller.getBestellingenVanKlant(this.getCurrentKlant());
            ObservableList<Bestelling> list = FXCollections.observableArrayList(bestellingLijst);
            bestellingenView.setItems(list);
        } catch (NullPointerException e) {
        }
    }

    public void pop2() {
        try {
            bestellingenView.getSelectionModel().selectFirst();
            Bestelling r = (Bestelling) bestellingenView.getSelectionModel().getSelectedItem();
            this.setBestelling(r);
            List<Product> producten = controller.getProductenVanBestelling(this.getBestelling());
            ObservableList<Product> pList = FXCollections.observableArrayList(producten);
            productenTabel.setItems(pList);
        } catch (NullPointerException ex) {
            productenTabel.getItems().clear();
        }
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

    public void populateListViewLings() {
        try {
            productenLijst = controller.getProductenLijst();
            ObservableList<Product> list = FXCollections.observableArrayList(productenLijst);
            left.setItems(list);
        } catch (NullPointerException e) {
        }
        left.setCellFactory(new Callback<ListView<Product>, ListCell<Product>>() {
            @Override
            public ListCell<Product> call(ListView<Product> p) {
                return new ProductCustomListCell();
            }
        });
        left.getSelectionModel().selectFirst();
    }

    public void saveButton() {
        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                if(bestelling != null && productenLijstVanKlant.size() > 0 && k != null){
                    controller.BestellingWijzig(k, bestelling, productenLijstVanKlant);
                    lbl.setText("Saved!");
                }
            }
        });
    }

    public void initializeProductenLijstVanKlant() {
        try {
            productenLijstVanKlant = controller.getProductenVanBestelling(this.getBestelling());
        } catch (NullPointerException e) {
        }
    }

    public void initializeAddButton() {
        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                if (getBestelling() != null) {
                    Product b = (Product) left.getSelectionModel().getSelectedItem();
                    productenLijstVanKlant.add(b);
                    //product aan rechter lijst toevoegen
                    ObservableList<Product> list = FXCollections.observableArrayList(productenLijstVanKlant);
                    productenTabel.getItems().add(b);
                    productenTabel.setItems(list);
                }
            }
        });
    }

    public void initializeRemoveButton() {
        remove.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                if (productenTabel.getSelectionModel().getSelectedItem() != null) {
                    Product p = (Product) productenTabel.getSelectionModel().getSelectedItem();
                    productenLijstVanKlant.remove(p);
                    productenTabel.getItems().remove(p);
                }
            }
        });

    }
}
