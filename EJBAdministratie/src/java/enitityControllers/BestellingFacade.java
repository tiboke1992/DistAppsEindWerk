/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enitityControllers;

import entitys.Bestelling;
import entitys.Klant;
import entitys.Product;
import java.sql.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author stinson
 */
@Stateless
public class BestellingFacade extends AbstractFacade<Bestelling> implements BestellingFacadeRemote {

    @PersistenceContext(unitName = "EJBAdministratiePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BestellingFacade() {
        super(Bestelling.class);
    }

    @Override
    public void setBestellingenVanKlantOpNull(long id) {
        Query query = em.createQuery("UPDATE Bestelling b SET b.klant = null WHERE b.klant.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public void setProductenInBestellingenOpNull(long id) {
        Query query = em.createQuery("SELECT e FROM Product p join p.bestellingen e where p.id = :id").setParameter("id", id);
        List<Bestelling> bestellingen = query.getResultList();
        Query q = em.createQuery("SELECT p FROM Product p WHERE p.id = :id").setParameter("id", id);
        Product pp = (Product) q.getSingleResult();

        for (Bestelling b : bestellingen) {
            if (b.getProducten().contains(pp)) {
                b.getProducten().remove(pp);
            }
            super.edit(b);
        }
    }

    @Override
    public List<Bestelling> getBestellingenVanKlantMetId(long id) {
        Query query = em.createQuery("SELECT b FROM Bestelling b WHERE b.klant.id =:id").setParameter("id", id);
        List<Bestelling> bestellingen = query.getResultList();
        return bestellingen;
    }

    @Override
    public List<Product> getProductenVanBestelling(long id) {
        Query q = em.createQuery("SELECT p FROM Bestelling b join b.producten p WHERE b.id = :id").setParameter("id", id);
        List<Product> producten = q.getResultList();
        return producten;
    }

    @Override
    public Bestelling addBestellingAanKlant(long id, Date date, List<Product> producten) {
        Query q1 = em.createQuery("SELECT k FROM Klant as k WHERE k.id = :id").setParameter("id", id);
        Klant k = (Klant) q1.getSingleResult();
        Bestelling b = new Bestelling(date);
        b = (Bestelling) super.create(b);
        b.setKlant(k);
        b = (Bestelling)super.edit(b);
        b.setProducten(producten);
        b = (Bestelling)super.edit(b);
        System.out.println(b.getProducten().size());
        return b;
    }
}
