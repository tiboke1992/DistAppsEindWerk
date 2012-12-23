/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import enitityControllers.BestellingFacadeRemote;
import enitityControllers.KlantFacadeRemote;
import enitityControllers.ProductFacadeRemote;
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
}
