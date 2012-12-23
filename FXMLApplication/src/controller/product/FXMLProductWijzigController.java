/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.product;

import controller.DataController;
import entitys.Product;
import java.net.URL;
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
import listCells.ProductListCell;

/**
 * FXML Controller class
 *
 * @author tibo
 */
public class FXMLProductWijzigController implements Initializable {

    @FXML
    private ComboBox box;
    private DataController controller;
    private List<Product> lijst;
    private Product p;
    @FXML
    private TextField naam;
    @FXML
    private TextField prijs;
    @FXML
    private TextField aantal;
    @FXML
    private Button save;
    @FXML
    private Label lbl;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        controller = new DataController();
        comboboxInitialiseren();
        box.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue ov, Object t, Object t1) {
                Product produ = (Product) t1;
                setSelectedProduct(produ);
                combo();
            }
        });
        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                controleerVelden();
            }
        });
    }

    public void setSelectedProduct(Product p) {
        this.p = p;
    }

    public Product getSelectedProduct() {
        return this.p;
    }

    public void comboboxInitialiseren() {
        lijst = controller.getProductenLijst();
        ObservableList<Product> list = FXCollections.observableArrayList(lijst);
        box.setButtonCell(new ProductListCell());
        box.setCellFactory(new Callback<ListView<Product>, ListCell<Product>>() {
            @Override
            public ListCell<Product> call(ListView<Product> p) {
                return new ProductListCell();
            }
        });

        box.getItems().clear();
        box.setItems(list);
        box.getSelectionModel().selectFirst();
        p = (Product) box.getSelectionModel().getSelectedItem();
        this.combo();
    }

    public void combo() {
        this.aantal.setText("" + p.getAantalInvoorraad());
        this.naam.setText(p.getNaam());
        this.prijs.setText("" + p.getPrijs());
    }

    public void controleerVelden() {
        String result = "";
        try {
            String strnaam = this.naam.getText();
            String strprijs = this.prijs.getText();
            String strAantal = this.aantal.getText();
            if (strnaam != null && strprijs != null && strAantal != null) {
                if (!strnaam.equals("") && !strprijs.equals("") && !strAantal.equals("")) {
                    double dPrijs = Double.parseDouble(strprijs);
                    int iAantal = Integer.parseInt(strAantal);
                    if (dPrijs >= 0 && iAantal >= 0) {
                        //Edit methode
                        this.p.setNaam(strnaam);
                        this.p.setPrijs(dPrijs);
                        this.p.setAantalInvoorraad(iAantal);
                        controller.wijzigProduct(this.getSelectedProduct());
                        result = "Product succesvol gewijzigd!";
                    } else {
                        result = "Prijs en aantal moeten groter of gelijk zijn aan 0";
                    }
                } else {
                    result = "Alle velden moeten ingevuld zijn!";
                }
            } else {
                result = "Er werden null objecten meegegeven";
            }
            lbl.setText(result);
        } catch (NumberFormatException e) {
            lbl.setText("Voor de prijs moet je een double invullen en voor het aantal een geheel getal!");
        }
    }
}
