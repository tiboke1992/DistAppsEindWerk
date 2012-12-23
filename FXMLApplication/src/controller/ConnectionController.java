/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import enitityControllers.AdresFacadeRemote;
import enitityControllers.BestellingFacadeRemote;
import enitityControllers.KlantFacadeRemote;
import enitityControllers.MagazijnFacadeRemote;
import enitityControllers.ProductFacadeRemote;
import enitityControllers.VasteKlantFacadeRemote;
import fxmlapplication.Main;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author tibo
 */
public class ConnectionController {

    //**Context en facades Declaratie
    Context c;
    public AdresFacadeRemote adresDB;
    public BestellingFacadeRemote bestellingDB;
    public KlantFacadeRemote klantDB;
    public MagazijnFacadeRemote magazijnDB;
    public ProductFacadeRemote productDB;
    public VasteKlantFacadeRemote vasteKlantDB;
    //**Context en facades Declaratie;;;;;

    public ConnectionController() {
        this.Initialize();
    }

    private void Initialize() {
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

    public AdresFacadeRemote getAdresDB() {
        return adresDB;
    }

    public BestellingFacadeRemote getBestellingDB() {
        return bestellingDB;
    }

    public KlantFacadeRemote getKlantDB() {
        return klantDB;
    }

    public MagazijnFacadeRemote getMagazijnDB() {
        return magazijnDB;
    }

    public ProductFacadeRemote getProductDB() {
        return productDB;
    }

    public VasteKlantFacadeRemote getVasteKlantDB() {
        return vasteKlantDB;
    }
}
