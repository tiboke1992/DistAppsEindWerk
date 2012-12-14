/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.klant;

import controller.ConnectionController;
import enitityControllers.KlantFacadeRemote;
import entitys.Klant;
import java.net.URL;
import java.sql.Date;
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
    ConnectionController connController;
    KlantFacadeRemote klantDB;
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
        connController = new ConnectionController();
        klantDB = connController.getKlantDB();
        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                //Klant toevoegen
                addKlant();
            }
        });
    }    
    public AnchorPane getAnchorPane(){
        return this.p;
    }
    public void addKlant(){
        String kVoornaam = this.voorNaam.getText();
        String kNaam = this.naam.getText();
        String kGeboorteDatum = this.geboorteDatum.getText();
        Date date = Date.valueOf(kGeboorteDatum);
        String kTelefoorNR = this.telefoonNR.getText();
        this.klantDB.create(new Klant(kVoornaam, kNaam, date, kTelefoorNR));
        this.emptyFields();
    }
    
    public void emptyFields(){
        this.naam.clear();
        this.voorNaam.clear();
        this.geboorteDatum.clear();
        this.telefoonNR.clear();
        this.boodschap.setText("Klant succesvol toegevoegd");
    }
}
