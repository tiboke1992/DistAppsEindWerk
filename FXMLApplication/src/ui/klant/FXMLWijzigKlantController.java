/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.klant;

import controller.DataController;
import entitys.Klant;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import javafx.scene.control.TextField;
import javafx.util.Callback;
import listCells.KlantListCell;

/**
 * FXML Controller class
 *
 * @author stinson
 */
public class FXMLWijzigKlantController implements Initializable {

    @FXML
    private ComboBox box;
    private DataController controller;
    private List<Klant> lijst;
    private Klant k;
    @FXML
    private TextField txtNaam;
    @FXML
    private TextField txtVoornaam;
    @FXML
    private TextField txtTelNr;
    @FXML
    private TextField txtGebDat;
   

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        controller = new DataController();
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
                initialiseKlantGegevens();
            }
        });
        this.initialiseKlantGegevens();
    }

    public void setSelectedKlant(Klant klant) {
        this.k = klant;
    }

    public Klant getCurrentKlant() {
        return this.k;
    }

    public void initialiseKlantGegevens() {
        this.txtNaam.setText(this.k.getNaam());
        this.txtVoornaam.setText(this.k.getVoorNaam());
        this.txtTelNr.setText(this.k.getTelefoonNummer());
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String text = df.format(this.k.getGeboorteDatum());
        this.txtGebDat.setText(text);
    }
}
