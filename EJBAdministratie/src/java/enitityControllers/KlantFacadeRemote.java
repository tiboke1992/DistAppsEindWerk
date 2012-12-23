/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enitityControllers;

import entitys.Klant;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author stinson
 */
@Remote
public interface KlantFacadeRemote {

    Object create(Klant klant);

    Object edit(Klant klant);

    void remove(Klant klant);

    Klant find(Object id);

    List<Klant> findAll();

    List<Klant> findRange(int[] range);

    int count();
}
