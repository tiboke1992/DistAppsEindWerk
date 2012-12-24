/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enitityControllers;

import entitys.Bestelling;
import entitys.Product;
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
public class ProductFacade extends AbstractFacade<Product> implements ProductFacadeRemote {

    @PersistenceContext(unitName = "EJBAdministratiePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductFacade() {
        super(Product.class);
    }

    @Override
    public void voegBestellingAanProductenToe(Long bestellingID, List<Product> producten) {
        Query q1 = em.createQuery("SELECT b FROM Bestelling b WHERE b.id =:id").setParameter("id", bestellingID);
        Bestelling b = (Bestelling)q1.getSingleResult(); 
        for(Product p : producten){
            Product s = super.find(p.getId());
            s.addBestelling(b);
            super.edit(s);
        }
    }
}
