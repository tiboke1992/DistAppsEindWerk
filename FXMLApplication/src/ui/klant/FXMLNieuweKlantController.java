/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.klant;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    @FXML 
    AnchorPane p;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    public AnchorPane getAnchorPane(){
        return this.p;
    }
}
