/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fxmlapplication;

import enitityControllers.AdresFacadeRemote;
import enitityControllers.BestellingFacadeRemote;
import enitityControllers.KlantFacadeRemote;
import enitityControllers.MagazijnFacadeRemote;
import enitityControllers.ProductFacadeRemote;
import enitityControllers.VasteKlantFacadeRemote;
import entitys.Adres;
import entitys.Klant;
import entitys.VasteKlant;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author stinson
 */
public class Main extends Application {

    //**Context en facades Declaratie
    Context c;
    AdresFacadeRemote adresDB;
    BestellingFacadeRemote bestellingDB;
    KlantFacadeRemote klantDB;
    MagazijnFacadeRemote magazijnDB;
    ProductFacadeRemote productDB;
    VasteKlantFacadeRemote vasteKlantDB;
    //**Context en facades Declaratie;;;;;

    @Override
    public void start(Stage stage) throws Exception {
        //-------------FACADES INITIALISEREN-----------------------
        this.Initialize();
        //-----------EINDE FACADES INITIALISEREN-------------

        //---------Objecten persisten----------------------
        this.Instantiate();
        //---------Einde Objecten persisten----------------


        Parent root = FXMLLoader.load(getClass().getResource("FXMLForm.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Method Initialize is used to initialize the context and the Facades
     */
    public void Initialize() {
        try {
            this.c = new InitialContext();
            this.adresDB = (AdresFacadeRemote) c.lookup("java:global/EJBAdministratie/AdresFacade");
            this.bestellingDB = (BestellingFacadeRemote) c.lookup("java:global/EJBAdministratie/BestellingFacade");
            this.klantDB = (KlantFacadeRemote) c.lookup("java:global/EJBAdministratie/KlantFacade");
            this.magazijnDB = (MagazijnFacadeRemote) c.lookup("java:global/EJBAdministratie/MagazijnFacade");
            this.productDB = (ProductFacadeRemote) c.lookup("java:global/EJBAdministratie/ProductFacade");
            this.vasteKlantDB = (VasteKlantFacadeRemote) c.lookup("java:global/EJBAdministratie/VasteKlantFacade");
        } catch (NamingException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Deze methode zorgt ervoor dat er al bepaalde testgegevens gecreerd worden
     * en relaties gelegd worden
     */
    public void Instantiate() {
        //Klaten aanmaken
        Klant klant1;
        Klant klant2;
        Klant klant3;
        Klant klant4;
        Klant klant5;
        Date datumKlant1;
        Date datumKlant2;
        Date datumKlant3;
        Date datumKlant4;
        Date datumKlant5;

        datumKlant1 = Date.valueOf("1992-09-30");
        datumKlant2 = Date.valueOf("1991-01-12");
        datumKlant3 = Date.valueOf("1993-05-18");
        datumKlant4 = Date.valueOf("1992-09-18");
        datumKlant5 = Date.valueOf("1993-07-20");

        klant1 = new Klant("Tibo", "Hulens", datumKlant1, "016471850");
        klant2 = new Klant("Jonas", "Vangoolen", datumKlant2, "016471890");
        klant3 = new Klant("Ilias", "Vanpeer", datumKlant3, "016474747");
        klant4 = new Klant("Stijn", "Willems", datumKlant4, "016229812");
        klant5 = new Klant("Tom", "Vanwyngaerden", datumKlant5, "016110099");

        klant1 = (Klant) klantDB.create(klant1);
        klant2 = (Klant) klantDB.create(klant2);
        klant3 = (Klant) klantDB.create(klant3);
        klant4 = (Klant) klantDB.create(klant4);
        klant5 = (Klant) klantDB.create(klant5);
        //Einde klanten aanmaken

        //Vaste Klanten maken
        VasteKlant vasteKlant1;
        VasteKlant vasteKlant2;

        vasteKlant1 = new VasteKlant("Klaas", "Van Parijs", Date.valueOf("1992-05-20"), "0477399220", 5.12);
        vasteKlant2 = new VasteKlant("Koen", "De ridder", Date.valueOf("1992-07-08"), "0477299110", 3.50);
        vasteKlant1 = (VasteKlant) vasteKlantDB.create(vasteKlant1);
        vasteKlant2 = (VasteKlant) vasteKlantDB.create(vasteKlant2);
        //Einde vaste Klanten maken

        //Adressen maken
        Adres adres1 = new Adres("hoekstraat", "4a", 3040, "Huldenberg");
        Adres adres2 = new Adres("hoekstraat", "10a", 3040, "Huldenberg");
        Adres adres3 = new Adres("wolfshaegen", "20b", 3040, "Huldenberg");
        Adres adres4 = new Adres("ophemstraat", "20", 3051, "Heverlee");
        Adres adres5 = new Adres("ophemstraat", "9", 3040, "Heverlee");
        Adres adres6 = new Adres("potterstraat", "3", 3040, "Heverlee");
        Adres adres7 = new Adres("potterstraat", "20s", 3040, "Heverlee");

        adres1 = (Adres) adresDB.create(adres1);
        adres2 = (Adres) adresDB.create(adres2);
        adres3 = (Adres) adresDB.create(adres3);
        adres4 = (Adres) adresDB.create(adres4);
        adres5 = (Adres) adresDB.create(adres5);
        adres6 = (Adres) adresDB.create(adres6);
        adres7 = (Adres) adresDB.create(adres7);
        //Einde adressen maken
        
        //Relaties leggen tussen adressen en klanten
        klant1.addAdres(adres1);
        klant2.addAdres(adres2);
        klant3.addAdres(adres3);
        klant4.addAdres(adres4);
        klant5.addAdres(adres5);
        vasteKlant1.addAdres(adres6);
        vasteKlant2.addAdres(adres7);
        klant1 = (Klant)klantDB.edit(klant1);
        klant2 = (Klant)klantDB.edit(klant2);
        klant3 = (Klant)klantDB.edit(klant3);
        klant4 = (Klant)klantDB.edit(klant4);
        klant5 = (Klant)klantDB.edit(klant5);
        vasteKlant1 = (VasteKlant)vasteKlantDB.edit(vasteKlant1);
        vasteKlant2 = (VasteKlant)vasteKlantDB.edit(vasteKlant2);
        adres1.setKlant(klant1);
        adres2.setKlant(klant2);
        adres3.setKlant(klant3);
        adres4.setKlant(klant4);
        adres5.setKlant(klant5);
        adres6.setKlant(vasteKlant1);
        adres7.setKlant(vasteKlant2);
        adres1 = (Adres)adresDB.edit(adres1);
        adres2 = (Adres)adresDB.edit(adres2);
        adres3 = (Adres)adresDB.edit(adres3);
        adres4 = (Adres)adresDB.edit(adres4);
        adres5 = (Adres)adresDB.edit(adres5);
        adres6 = (Adres)adresDB.edit(adres6);
        adres7 = (Adres)adresDB.edit(adres7);
        //Einde relatie leggen tussen adressen en klanten
        
        //Magazijnen maken
        //Einde Magazijnen maken

    }
}
