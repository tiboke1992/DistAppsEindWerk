/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import enitityControllers.BestellingFacadeRemote;
import enitityControllers.KlantFacadeRemote;
import entitys.Klant;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author tibo
 */
public class DataController {

    private ConnectionController connController;
    private KlantFacadeRemote klantDB;
    private BestellingFacadeRemote bestellingDB;

    public DataController() {
        this.initialize();
    }

    public void initialize() {
        connController = new ConnectionController();
        klantDB = connController.getKlantDB();
        bestellingDB = connController.getBestellingDB();
    }

    public void addNewKlant(String voornaam, String naam, Date geboorteDatum, String telefoonNR) {
        klantDB.create(new Klant(voornaam, naam, geboorteDatum, telefoonNR));
    }

    public List<Klant> getKlantenLijst() {
        List<Klant> k = klantDB.findAll();
        return k;
    }
    
    
    public void deleteKlant(Klant k){
        bestellingDB.setBestellingenVanKlantOpNull(k.getId());
        klantDB.remove(k);
    }
    
    public void wijzigKlant(Klant k){
        klantDB.edit(k);
    }
}
