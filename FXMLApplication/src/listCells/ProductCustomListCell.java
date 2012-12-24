/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package listCells;

import entitys.Product;
import javafx.scene.control.ListCell;

/**
 *
 * @author tibo
 */
public class ProductCustomListCell extends ListCell<Product> {

    @Override
    protected void updateItem(Product p, boolean bln) {
        super.updateItem(p, bln);
        if (p != null) {
            setItem(p);
            setText(p.getNaam() + " = € " + p.getPrijs());
        }
    }
}
