/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.klant;

import controller.DataController;
import entitys.Klant;
import java.net.URL;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.IllegalFormatException;
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
    @FXML
    private Button save;
    @FXML
    private Label lblError;

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
        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                save();
            }
        });
        lblError.setVisible(false);
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


    public void save() {
        String naam = this.txtNaam.getText();
        String voorNaam = this.txtVoornaam.getText();
        String gebDatum = this.txtGebDat.getText();
        String telefoonNr = this.txtTelNr.getText();
        String result;
        if (testValues(naam, voorNaam, gebDatum, telefoonNr)) {
            this.k.setNaam(naam);
            this.k.setVoorNaam(voorNaam);
            this.k.setGeboorteDatum(Date.valueOf(gebDatum));
            this.k.setTelefoonNummer(telefoonNr);
            controller.wijzigKlant(k);
            result = "Persoon correct gewijzigd";
        }else{
           //Label zette da er iet fout ingevuld is
            result = "Niet alles juist ingevuld!";
        }
        this.lblError.setText(result);
        this.lblError.setVisible(true);
    }
    
    public boolean testValues(String n, String v, String d, String t) {
        boolean result = false;
        if(n != null && v != null && d != null && t != null){
            if(n.equals("")||v.equals("")||d.equals("")||t.equals("")){
                
            }else{
                try{
                String[] str = d.split("-");
                String year = str[0];
                String month = str[1];
                String day = str[2];             
                    int iYear = Integer.parseInt(year);
                    int iMonth = Integer.parseInt(month);
                    int iDay = Integer.parseInt(day);
                    if(iYear>1900 && iYear < Calendar.getInstance().get(Calendar.YEAR) && iMonth > 0 && iMonth < 13 && iDay > 0 && iDay < 32){
                        result = true;
                    }
                }catch(ArrayIndexOutOfBoundsException ex){
                    this.lblError.setText("Datum niet juist ingevuld");
                }
            }
        }
        return result;
    }
}
