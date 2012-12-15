/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enitityControllers;

import entitys.Bestelling;
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


}
