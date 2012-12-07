/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enitityControllers;

import entitys.Magazijn;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author stinson
 */
@Remote
public interface MagazijnFacadeRemote {

    Object create(Magazijn magazijn);

    Object edit(Magazijn magazijn);

    void remove(Magazijn magazijn);

    Magazijn find(Object id);

    List<Magazijn> findAll();

    List<Magazijn> findRange(int[] range);

    int count();
}
