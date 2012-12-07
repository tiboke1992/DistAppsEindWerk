/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enitityControllers;

import entitys.Magazijn;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author stinson
 */
@Stateless
public class MagazijnFacade extends AbstractFacade<Magazijn> implements MagazijnFacadeRemote {

    @PersistenceContext(unitName = "EJBAdministratiePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MagazijnFacade() {
        super(Magazijn.class);
    }
}
