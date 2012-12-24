/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import enitityControllers.BestellingFacadeRemote;
import enitityControllers.KlantFacadeRemote;
import enitityControllers.ProductFacadeRemote;
import entitys.Bestelling;
import entitys.Klant;
import entitys.Product;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author tibo
 */
public class DataController {

    private ConnectionController connController;
    private KlantFacadeRemote klantDB;
    private ProductFacadeRemote productDB;
    private BestellingFacadeRemote bestellingDB;

    public DataController() {
        this.initialize();
    }

    public void initialize() {
        connController = new ConnectionController();
        klantDB = connController.getKlantDB();
        bestellingDB = connController.getBestellingDB();
        productDB = connController.getProductDB();
    }

    public void addNewKlant(String voornaam, String naam, Date geboorteDatum, String telefoonNR) {
        klantDB.create(new Klant(voornaam, naam, geboorteDatum, telefoonNR));
    }

    public void addNewProduct(String naam, double prijs, int aantal) {
        productDB.create(new Product(naam, prijs, aantal));
    }

    public List<Klant> getKlantenLijst() {
        List<Klant> k = klantDB.findAll();
        return k;
    }

    public List<Product> getProductenLijst() {
        List<Product> p = productDB.findAll();
        return p;
    }
    
    public List<Bestelling> getBestellingenVanKlant(Klant k){
        List<Bestelling> b = bestellingDB.getBestellingenVanKlantMetId(k.getId());
        return b;
    }

    public void deleteKlant(Klant k) {
        bestellingDB.setBestellingenVanKlantOpNull(k.getId());
        klantDB.remove(k);
    }

    public void deleteProduct(Product p) {
        bestellingDB.setProductenInBestellingenOpNull(p.getId());
        productDB.remove(p);
    }

    public void wijzigKlant(Klant k) {
        klantDB.edit(k);
    }

    public void wijzigProduct(Product p) {
        productDB.edit(p);
    }
    
    public List<Product> getProductenVanBestelling(Bestelling b){
        return bestellingDB.getProductenVanBestelling(b.getId());
    }
    
    public void addBestellingAanKlant(Klant k , List<Product> producten, Date datum){
       Bestelling b = bestellingDB.addBestellingAanKlant(k.getId(), datum,producten);
       List<Bestelling> best = klantDB.getKlantMetBestellingen(k.getId());
       best.add(b);
       Klant k1 = klantDB.find(k.getId());
       k1.setBestellingen(best);
       klantDB.edit(k1);
       //alle producten aan bestelling toevoegen
       productDB.voegBestellingAanProductenToe(b.getId(), producten);
    }
    
    public void deleteBestelling(Klant k, Bestelling b){
        //eerst aan de klantedb zegge dat hij de geselecteerde klant die bestelling moet verwijderen
        klantDB.deleteBestelling(k.getId(), b.getId());
        productDB.deleteBestellingen(b.getId());
        bestellingDB.remove(b);
    }
}
