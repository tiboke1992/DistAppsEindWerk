/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fxmlapplication;

import controller.SwitchViewController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 *
 * @author stinson
 */
public class FXMLFormController implements Initializable {

    //Formcontroller van het Main formulier
    @FXML
    private AnchorPane leftPane;
    @FXML
    private AnchorPane rightPane;
    @FXML
    private TreeView treeView;
    final TreeItem<String> treeRoot = new TreeItem<>("Beheer");
    private TreeItem<String> klant = new TreeItem<>("Klant");
    private TreeItem<String> bestelling = new TreeItem<>("Bestelling");
    private TreeItem<String> producten = new TreeItem<>("Producten");
    private TreeItem<String> klantNieuw = new TreeItem<>("Nieuwe klant");
    private TreeItem<String> KlantWijzig = new TreeItem<>("Wijzig klant");
    private TreeItem<String> KlantVerwijder = new TreeItem<>("Verwijder klant");
    private TreeItem<String> KlantOverzicht = new TreeItem<>("Overzicht klanten");
    private TreeItem<String> bestellingNieuw = new TreeItem<>("Nieuwe bestelling");
    private TreeItem<String> bestellingWijzig = new TreeItem<>("Wijzig bestelling");
    private TreeItem<String> bestellingVerwijder = new TreeItem<>("Verwijder bestelling");
    private TreeItem<String> bestellingOverzicht = new TreeItem<>("Overzicht bestellingen");
    private TreeItem<String> productenNieuw = new TreeItem<>("Nieuw product");
    private TreeItem<String> productenWijzig = new TreeItem<>("Wijzig product");
    private TreeItem<String> productenVerwijder = new TreeItem<>("Verwijder product");
    private TreeItem<String> productenOverzicht = new TreeItem<>("Overzicht producten");
    private AnchorPane rp;
    @FXML
    private Pane rightp;
    private SwitchViewController view;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        view = new SwitchViewController();
        //treeview maken
        treeRoot.getChildren().addAll(klant, bestelling, producten);
        treeRoot.getChildren().get(0).getChildren().addAll(KlantOverzicht, klantNieuw, KlantWijzig, KlantVerwijder);
        treeRoot.getChildren().get(1).getChildren().addAll(bestellingOverzicht, bestellingNieuw, bestellingWijzig, bestellingVerwijder);
        treeRoot.getChildren().get(2).getChildren().addAll(productenOverzicht, productenNieuw, productenWijzig, productenVerwijder);
        treeView.setShowRoot(true);
        treeView.setRoot(treeRoot);
        treeRoot.setExpanded(true);
        //eventlisteners toevoegen
        treeView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        treeView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue ov, Object t, Object t1) {
                TreeItem item = (TreeItem) t1;
                try {
                    openScene(item);
                } catch (IOException ex) {
                    Logger.getLogger(FXMLFormController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    public void openScene(TreeItem item) throws IOException {
        rightPane.getChildren().remove(rp);
        if (item == klantNieuw) {
            rp = view.getKlantNieuwAnchorPane();
        } else if (item == KlantOverzicht) {
            rp = view.getKlantOverzichtPane();
        } else if (item == KlantVerwijder) {
            rp = view.getKlantVerwijderenPane();
        } else if (item == KlantWijzig) {
            rp = view.getKlantWijzigPane();
        } else if (item == productenOverzicht) {
            rp = view.getProductOverzichtPane();
        } else if (item == productenNieuw) {
            rp = view.getProductNieuwPane();
        } else if (item == productenVerwijder) {
            rp = view.getProductVerwijderPane();
        }
        rightPane.getChildren().add(rp);
    }
}
