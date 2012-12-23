/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.klant;

import controller.DataController;
import java.net.URL;
import java.sql.Date;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author stinson
 */
public class FXMLNieuweKlantController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private DataController dataController;
    @FXML
    AnchorPane p;
    @FXML
    private TextField naam;
    @FXML
    private TextField voorNaam;
    @FXML
    private TextField geboorteDatum;
    @FXML
    private TextField telefoonNR;
    @FXML
    private Button save;
    @FXML
    private Label boodschap;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        dataController = new DataController();
        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                //Klant toevoegen
                boodschap.setText("Processing");
                addKlant();
            }
        });
    }

    public AnchorPane getAnchorPane() {
        return this.p;
    }

    public void addKlant() {
        String kVoornaam = this.voorNaam.getText();
        String kNaam = this.naam.getText();
        String kGeboorteDatum = this.geboorteDatum.getText();
        String kTelefoorNR = this.telefoonNR.getText();

        if (testValues(kVoornaam, kNaam, kGeboorteDatum, kTelefoorNR)) {
            Date date = Date.valueOf(kGeboorteDatum);
            this.dataController.addNewKlant(kVoornaam, kNaam, date, kTelefoorNR);
            this.emptyFields();
        } else {
            this.boodschap.setText("Deftig velden invullen aub");
        }

    }

    public void emptyFields() {
        this.naam.clear();
        this.voorNaam.clear();
        this.geboorteDatum.clear();
        this.telefoonNR.clear();
        this.boodschap.setText("Klant succesvol toegevoegd");
    }

    public boolean testValues(String n, String v, String d, String t) {
        boolean result = false;
        if (n != null && v != null && d != null && t != null) {
            if (n.equals("") || v.equals("") || d.equals("") || t.equals("")) {
            } else {
                try {
                    String[] str = d.split("-");
                    String year = str[0];
                    String month = str[1];
                    String day = str[2];
                    int iYear = Integer.parseInt(year);
                    int iMonth = Integer.parseInt(month);
                    int iDay = Integer.parseInt(day);
                    if (iYear > 1900 && iYear < Calendar.getInstance().get(Calendar.YEAR) && iMonth > 0 && iMonth < 13 && iDay > 0 && iDay < 32) {
                        result = true;
                    }
                } catch (ArrayIndexOutOfBoundsException ex) {
                    this.boodschap.setText("Datum niet juist");
                }
            }
        }
        return result;
    }
}
