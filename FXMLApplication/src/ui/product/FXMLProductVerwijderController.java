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
import listCells.ProductListCell;

/**
 * FXML Controller class
 *
 * @author tibo
 */
public class FXMLProductVerwijderController implements Initializable {

    @FXML
    private Button delete;
    @FXML
    private ComboBox box;
    private DataController controller;
    private List<Product> lijst;
    private Product p;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        controller = new DataController();
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
        box.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue ov, Object t, Object t1) {
                Product produ = (Product) t1;
                setSelectedProduct(produ);
            }
        });

        this.delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                removeProduct();
                refreshComboBox();
            }
        });
    }

    public void setSelectedProduct(Product p) {
        this.p = p;
    }

    public Product getSelectedProduct() {
        return this.p;
    }

    public void refreshComboBox() {
        lijst = controller.getProductenLijst();
        ObservableList<Product> list = FXCollections.observableArrayList(lijst);
        box.setItems(list);
        box.getSelectionModel().selectFirst();
        p = (Product) box.getSelectionModel().getSelectedItem();
    }
    
    public void removeProduct(){
        if(this.getSelectedProduct() != null){
            this.controller.deleteProduct(this.getSelectedProduct());
        }
    }
}
