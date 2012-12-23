/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package listCells;

import entitys.Klant;
import javafx.scene.control.ListCell;

/**
 *
 * @author tibo
 */
public class KlantListCell extends ListCell<Klant> {

    @Override
    protected void updateItem(Klant k, boolean bln) {
        super.updateItem(k, bln);
        if (k != null) {
            setItem(k);
            setText(k.getVoorNaam() + " " + k.getNaam());
        }
    }
}
