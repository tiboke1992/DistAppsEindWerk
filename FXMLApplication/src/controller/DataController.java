/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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

    public DataController() {
        this.initialize();
    }

    public void initialize() {
        connController = new ConnectionController();
        klantDB = connController.getKlantDB();
    }

    public void addNewKlant(String voornaam, String naam, Date geboorteDatum, String telefoonNR) {
        klantDB.create(new Klant(voornaam, naam, geboorteDatum, telefoonNR));
    }

    public List<Klant> getKlantenLijst() {
        List<Klant> k = klantDB.findAll();
        return k;
    }
}
