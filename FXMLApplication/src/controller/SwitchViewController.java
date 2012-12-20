/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author tibo
 */
public class SwitchViewController {

    public AnchorPane getKlantNieuwAnchorPane() throws IOException {
        AnchorPane ap = (AnchorPane) FXMLLoader.load(this.getClass().getClassLoader().getResource("ui/klant/FXMLNieuweKlant.fxml"));
        return ap;
    }

    public AnchorPane getKlantOverzichtPane() throws IOException {
        AnchorPane ap = (AnchorPane) FXMLLoader.load(this.getClass().getClassLoader().getResource("ui/klant/FXMLOverzichtKlanten.fxml"));
        return ap;
    }

    public AnchorPane getKlantVerwijderenPane() throws IOException {
        AnchorPane ap = (AnchorPane) FXMLLoader.load(this.getClass().getClassLoader().getResource("ui/klant/FXMLVerwijderKlant.fxml"));
        return ap;
    }
    
    public AnchorPane getKlantWijzigPane() throws IOException{
         AnchorPane ap = (AnchorPane) FXMLLoader.load(this.getClass().getClassLoader().getResource("ui/klant/FXMLWijzigKlant.fxml"));
        return ap;
    }
}
