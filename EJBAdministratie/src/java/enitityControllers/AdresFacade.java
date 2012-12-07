/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enitityControllers;

import entitys.Adres;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author stinson
 */
@Stateless
public class AdresFacade extends AbstractFacade<Adres> implements AdresFacadeRemote {

    @PersistenceContext(unitName = "EJBAdministratiePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdresFacade() {
        super(Adres.class);
    }
}
