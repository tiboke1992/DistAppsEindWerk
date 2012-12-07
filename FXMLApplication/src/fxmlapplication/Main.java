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
import entitys.Magazijn;
import entitys.Product;
import entitys.VasteKlant;
import java.sql.Date;
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
        klant1 = (Klant) klantDB.edit(klant1);
        klant2 = (Klant) klantDB.edit(klant2);
        klant3 = (Klant) klantDB.edit(klant3);
        klant4 = (Klant) klantDB.edit(klant4);
        klant5 = (Klant) klantDB.edit(klant5);
        vasteKlant1 = (VasteKlant) vasteKlantDB.edit(vasteKlant1);
        vasteKlant2 = (VasteKlant) vasteKlantDB.edit(vasteKlant2);
        adres1.setKlant(klant1);
        adres2.setKlant(klant2);
        adres3.setKlant(klant3);
        adres4.setKlant(klant4);
        adres5.setKlant(klant5);
        adres6.setKlant(vasteKlant1);
        adres7.setKlant(vasteKlant2);
        adres1 = (Adres) adresDB.edit(adres1);
        adres2 = (Adres) adresDB.edit(adres2);
        adres3 = (Adres) adresDB.edit(adres3);
        adres4 = (Adres) adresDB.edit(adres4);
        adres5 = (Adres) adresDB.edit(adres5);
        adres6 = (Adres) adresDB.edit(adres6);
        adres7 = (Adres) adresDB.edit(adres7);
        //Einde relatie leggen tussen adressen en klanten

        //Magazijnen maken
        Magazijn magazijn1 = new Magazijn(12);
        Magazijn magazijn2 = new Magazijn(33);
        magazijn1 = (Magazijn) magazijnDB.create(magazijn1);
        magazijn2 = (Magazijn) magazijnDB.create(magazijn2);
        //Einde Magazijnen maken

        //Producten maken
        Product product1 = new Product("Thor broek", 200.0, 30);
        product1.setAantalInvoorraad(20);
        Product product2 = new Product("Thor kousen", 20.30, 5);
        Product product3 = new Product("Thor handschoenen", 124.99, 43);
        Product product4 = new Product("Thor protectors", 450, 2);
        Product product5 = new Product("Fox elbows", 40, 12);
        Product product6 = new Product("Fox banden", 55, 30);
        Product product7 = new Product("Tandwiel", 54, 10);
        Product product8 = new Product("Ketting", 244.60, 18);
        Product product9 = new Product("Stuur", 22.44, 4);
        Product product10 = new Product("Bouten", 5, 120);
        Product product11 = new Product("Roulementen", 14, 50);
        Product product12 = new Product("Dichtingen", 5, 200);
        Product product13 = new Product("Krukas", 440, 5);
        Product product14 = new Product("Pistons", 189, 6);
        Product product15 = new Product("Lagers", 88, 20);
        Product product16 = new Product("pinkers", 30, 20);

        product1 = (Product) productDB.create(product1);
        product2 = (Product) productDB.create(product2);
        product3 = (Product) productDB.create(product3);
        product4 = (Product) productDB.create(product4);
        product5 = (Product) productDB.create(product5);
        product6 = (Product) productDB.create(product6);
        product7 = (Product) productDB.create(product7);
        product8 = (Product) productDB.create(product8);
        product9 = (Product) productDB.create(product9);
        product10 = (Product) productDB.create(product10);
        product11 = (Product) productDB.create(product11);
        product12 = (Product) productDB.create(product12);
        product13 = (Product) productDB.create(product13);
        product14 = (Product) productDB.create(product14);
        product15 = (Product) productDB.create(product15);
        product16 = (Product) productDB.create(product16);

        //Einde producten maken

        //relatie producten met magazijn leggen
        product1.setMagazijn(magazijn1);
        product2.setMagazijn(magazijn1);
        product3.setMagazijn(magazijn1);
        product4.setMagazijn(magazijn1);
        product5.setMagazijn(magazijn1);
        product6.setMagazijn(magazijn1);
        product7.setMagazijn(magazijn1);
        product8.setMagazijn(magazijn1);
        product9.setMagazijn(magazijn1);
        product10.setMagazijn(magazijn2);
        product11.setMagazijn(magazijn2);
        product12.setMagazijn(magazijn2);
        product13.setMagazijn(magazijn2);
        product14.setMagazijn(magazijn2);
        product15.setMagazijn(magazijn2);
        product16.setMagazijn(magazijn2);

        magazijn1.addProduct(product1);
        magazijn1.addProduct(product2);
        magazijn1.addProduct(product3);
        magazijn1.addProduct(product4);
        magazijn1.addProduct(product5);
        magazijn1.addProduct(product6);
        magazijn1.addProduct(product7);
        magazijn1.addProduct(product8);
        magazijn1.addProduct(product9);
        magazijn2.addProduct(product10);
        magazijn2.addProduct(product11);
        magazijn2.addProduct(product12);
        magazijn2.addProduct(product13);
        magazijn2.addProduct(product14);
        magazijn2.addProduct(product15);
        magazijn2.addProduct(product16);
        
        magazijn1 = (Magazijn)magazijnDB.edit(magazijn1);
        magazijn2 = (Magazijn)magazijnDB.edit(magazijn2);
        product1 = (Product)productDB.edit(product1);
        product2 = (Product)productDB.edit(product2);
        product3 = (Product)productDB.edit(product3);
        product4 = (Product)productDB.edit(product4);
        product5 = (Product)productDB.edit(product5);
        product6 = (Product)productDB.edit(product6);
        product7 = (Product)productDB.edit(product7);
        product8 = (Product)productDB.edit(product8);
        product9 = (Product)productDB.edit(product9);
        product10 = (Product)productDB.edit(product10);
        product11 = (Product)productDB.edit(product11);
        product12 = (Product)productDB.edit(product12);
        product13 = (Product)productDB.edit(product13);
        product14 = (Product)productDB.edit(product14);
        product15 = (Product)productDB.edit(product15);
        product16 = (Product)productDB.edit(product16);
        //Einde relatie producten met magazijn leggen

    }
}
