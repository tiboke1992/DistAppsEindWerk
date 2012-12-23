/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.product;

import controller.DataController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author tibo
 */
public class FXMLNieuwProductController implements Initializable {

    private DataController controller;
    @FXML
    private TextField naam;
    @FXML
    private TextField prijs;
    @FXML
    private TextField voorraad;
    @FXML
    private Button save;
    @FXML
    private Label lbl;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        controller = new DataController();
        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                controleerVelden();
            }
        });
    }

    public void controleerVelden() {
        String result = "";
        try {
            String strnaam = this.naam.getText();
            String strprijs = this.prijs.getText();
            String strAantal = this.voorraad.getText();
            if (strnaam != null && strprijs != null && strAantal != null) {
                if (!strnaam.equals("") && !strprijs.equals("") && !strAantal.equals("")) {
                    double dPrijs = Double.parseDouble(strprijs);
                    int iAantal = Integer.parseInt(strAantal);
                    if (dPrijs >= 0 && iAantal >= 0) {
                        //toevoegen methode aanroepe
                        result = "Product seccesvol toegevoegd!";
                        voegProductToe(strnaam, dPrijs, iAantal);

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

    public void voegProductToe(String naam, double prijs, int aantal) {
        try {
            this.controller.addNewProduct(naam, prijs, aantal);
            this.leegVelden();
        } catch (IllegalArgumentException e) {
            this.lbl.setText("Er is een fout opgetreden, probeer opnieuw!");
        }
    }

    private void leegVelden() {
        this.naam.clear();
        this.prijs.clear();
        this.voorraad.clear();
    }
}
