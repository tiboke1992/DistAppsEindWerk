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

    public AnchorPane getKlantWijzigPane() throws IOException {
        AnchorPane ap = (AnchorPane) FXMLLoader.load(this.getClass().getClassLoader().getResource("ui/klant/FXMLWijzigKlant.fxml"));
        return ap;
    }

    public AnchorPane getProductOverzichtPane() throws IOException {
        AnchorPane ap = (AnchorPane) FXMLLoader.load(this.getClass().getClassLoader().getResource("ui/product/FXMLOverzichtProducten.fxml"));
        return ap;
    }

    public AnchorPane getProductNieuwPane() throws IOException {
        AnchorPane ap = (AnchorPane) FXMLLoader.load(this.getClass().getClassLoader().getResource("ui/product/FXMLNieuwProduct.fxml"));
        return ap;
    }

    public AnchorPane getProductVerwijderPane() throws IOException {
        AnchorPane ap = (AnchorPane) FXMLLoader.load(this.getClass().getClassLoader().getResource("ui/product/FXMLProductVerwijder.fxml"));
        return ap;
    }

    public AnchorPane getProductWijzigPane() throws IOException {
        AnchorPane ap = (AnchorPane) FXMLLoader.load(this.getClass().getClassLoader().getResource("ui/product/FXMLProductWijzig.fxml"));
        return ap;
    }

    public AnchorPane getBestellingOverzichtPane() throws IOException {
        AnchorPane ap = (AnchorPane) FXMLLoader.load(this.getClass().getClassLoader().getResource("ui/bestelling/FXMLBestellingOverzicht.fxml"));
        return ap;
    }
}
