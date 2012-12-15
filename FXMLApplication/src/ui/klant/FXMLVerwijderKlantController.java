/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.klant;

import controller.DataController;
import entitys.Klant;
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
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import listCells.KlantListCell;

/**
 * FXML Controller class
 *
 * @author tibo
 */
public class FXMLVerwijderKlantController implements Initializable {

    @FXML
    private Button delete;
    @FXML
    private ComboBox box;
    private DataController controller;
    private List<Klant> lijst;
    private Klant k;

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
            }
        });
        delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                removeKlant();
                refreshComboBox();
            }
        });
    }

    public void setSelectedKlant(Klant klant) {
        this.k = klant;
    }

    public Klant getCurrentKlant() {
        return this.k;
    }

    public void removeKlant() {
        if (this.getCurrentKlant() != null) {
            controller.deleteKlant(this.getCurrentKlant());
        }
    }

    public void refreshComboBox() {
        lijst = controller.getKlantenLijst();
        ObservableList<Klant> list = FXCollections.observableArrayList(lijst);
        box.setItems(list);
        box.getSelectionModel().selectFirst();
        k = (Klant) box.getSelectionModel().getSelectedItem();
    }
}
