/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.bestelling;

import controller.DataController;
import entitys.Klant;
import entitys.Product;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import listCells.KlantListCell;
import listCells.ProductCustomListCell;
import listCells.ProductListCell;

/**
 * FXML Controller class
 *
 * @author tibo
 */
public class FXMLBestellingNieuwController implements Initializable {

    @FXML
    private TextField datum;
    @FXML
    private ComboBox box;
    private DataController controller;
    private List<Klant> lijst;
    private Klant k;
    private List<Product> productenLijst;
    @FXML
    private ListView left;
    @FXML
    private Button add;
    private List<Product> productenLijstVanKlant;
    @FXML
    private ListView right;
    @FXML
    private Button remove;
    @FXML
    private Button save;
    @FXML
    private Label boodschap;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //DATUMVELD INVULLEN
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        datum.setText(dateFormat.format(date));
        //KLANTEN INVULLEN
        controller = new DataController();
        productenLijst = controller.getProductenLijst();
        populateComboBox();
        populateListViewLings();
        productenLijstVanKlant = new ArrayList<Product>();
        initializeRechterLijst();
        initializeAddButton();
        initializeRemoveButton();
        initializeSaveButton();
    }

    public void populateComboBox() {
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
        box.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue ov, Object t, Object t1) {
                Klant k = (Klant) t1;
                setSelectedKlant(k);
                //rechte lijst leefmaken
                emptyRight();
            }
        });
    }

    public void populateListViewLings() {
        ObservableList<Product> list = FXCollections.observableArrayList(productenLijst);
        left.setItems(list);
        left.setCellFactory(new Callback<ListView<Product>, ListCell<Product>>() {
            @Override
            public ListCell<Product> call(ListView<Product> p) {
                return new ProductCustomListCell();
            }
        });
        left.getSelectionModel().selectFirst();
    }

    public void setSelectedKlant(Klant klant) {
        this.k = klant;
    }

    public Klant getCurrentKlant() {
        return this.k;
    }

    public void initializeAddButton() {
        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                Product b = (Product) left.getSelectionModel().getSelectedItem();
                productenLijstVanKlant.add(b);
                //product aan rechter lijst toevoegen
                ObservableList<Product> list = FXCollections.observableArrayList(productenLijstVanKlant);
                right.getItems().add(b);
                right.setItems(list);
            }
        });
    }

    public void initializeRechterLijst() {
        //ObservableList<Product> list = FXCollections.observableArrayList(productenLijstVanKlant);
        //right.setItems(list);
        right.setCellFactory(new Callback<ListView<Product>, ListCell<Product>>() {
            @Override
            public ListCell<Product> call(ListView<Product> p) {
                return new ProductListCell();
            }
        });
    }

    public void emptyRight() {
        right.getItems().clear();
        productenLijstVanKlant.clear();
    }

    public void initializeRemoveButton() {
        remove.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                if (right.getSelectionModel().getSelectedItem() != null) {
                    Product p = (Product) right.getSelectionModel().getSelectedItem();
                    productenLijstVanKlant.remove(p);
                    right.getItems().remove(p);
                }
            }
        });

    }

    public void initializeSaveButton() {
        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                if (productenLijstVanKlant != null && productenLijstVanKlant.size() > 0) {
                    //controleer datumVeld
                    if (controleerDatumVeld()) {
                        addBestelling();
                    }else{
                        boodschap.setText("Datum niet juist");
                    }
                }
            }
        });
    }

    public boolean controleerDatumVeld() {
        boolean result = false;
        try {
            String d = this.datum.getText();
            String[] str = d.split("-");
            String year = str[0];
            String month = str[1];
            String day = str[2];
            int iYear = Integer.parseInt(year);
            int iMonth = Integer.parseInt(month);
            int iDay = Integer.parseInt(day);
            if (iYear > 1900 && iYear <= Calendar.getInstance().get(Calendar.YEAR) && iMonth > 0 && iMonth < 13 && iDay > 0 && iDay < 32) {
                result = true;
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            this.boodschap.setText("Datum niet juist");
        } catch (NullPointerException e) {
            this.boodschap.setText("Datum niet ingevuld");
        }
        return result;
    }

    public void addBestelling() {
        java.sql.Date date = java.sql.Date.valueOf(datum.getText());
        controller.addBestellingAanKlant(this.getCurrentKlant(), productenLijstVanKlant, date);
        emptyRight();
        this.boodschap.setText("Succesvol toegevoegd");
    }
}
