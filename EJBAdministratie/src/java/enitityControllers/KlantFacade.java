/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enitityControllers;

import entitys.Bestelling;
import entitys.Klant;
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
public class KlantFacade extends AbstractFacade<Klant> implements KlantFacadeRemote {

    @PersistenceContext(unitName = "EJBAdministratiePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public KlantFacade() {
        super(Klant.class);
    }

    @Override
    public List<Bestelling> getKlantMetBestellingen(Long id) {
        Query q = em.createQuery("SELECT b FROM Klant k JOIN k.bestellingen b WHERE k.id =:id").setParameter("id", id);
        List<Bestelling> bestellingen = q.getResultList();
        return bestellingen;
    }

    @Override
    public void deleteBestelling(Long klantID, Long BestellingID) {
        Query q = em.createQuery("SELECT b FROM Bestelling AS b WHERE b.id = :id").setParameter("id", BestellingID);
        Bestelling best = (Bestelling)q.getSingleResult();
        Klant k = super.find(klantID);
        k.getBestellingen().remove(best);
    }
}
