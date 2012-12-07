/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enitityControllers;

import entitys.VasteKlant;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author stinson
 */
@Remote
public interface VasteKlantFacadeRemote {

    Object create(VasteKlant vasteKlant);

    Object edit(VasteKlant vasteKlant);

    void remove(VasteKlant vasteKlant);

    VasteKlant find(Object id);

    List<VasteKlant> findAll();

    List<VasteKlant> findRange(int[] range);

    int count();
}
