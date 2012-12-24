/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enitityControllers;

import entitys.Bestelling;
import entitys.Product;
import java.sql.Date;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author stinson
 */
@Remote
public interface BestellingFacadeRemote {

    Object create(Bestelling bestelling);

    Object edit(Bestelling bestelling);

    void remove(Bestelling bestelling);

    Bestelling find(Object id);

    List<Bestelling> findAll();

    List<Bestelling> findRange(int[] range);

    int count();

    void setBestellingenVanKlantOpNull(long id);

    void setProductenInBestellingenOpNull(long id);

    List<Bestelling> getBestellingenVanKlantMetId(long id);

    List<Product> getProductenVanBestelling(long id);
    
    Bestelling addBestellingAanKlant(long id,Date date, List<Product> producten);
}
