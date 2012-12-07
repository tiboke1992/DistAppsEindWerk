/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enitityControllers;

import entitys.Adres;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author stinson
 */
@Remote
public interface AdresFacadeRemote {

    Object create(Adres adres);

    Object edit(Adres adres);

    void remove(Adres adres);

    Adres find(Object id);

    List<Adres> findAll();

    List<Adres> findRange(int[] range);

    int count();
}
