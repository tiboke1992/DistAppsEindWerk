/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entitys;

import java.sql.Date;
import javax.persistence.Entity;

/**
 *
 * @author stinson
 */
@Entity
public class VasteKlant extends Klant {
   private double korting;
   
   public VasteKlant(){
       this(0);
   }
   
   public VasteKlant(double korting){
       super();
       this.setKorting(korting);
   }
   
   public VasteKlant(String voorNaam,String naam,Date geboorteDatum,String telefoonNr,double korting){
       super(voorNaam, naam, geboorteDatum, telefoonNr);
       this.setKorting(korting);
   }

    public double getKorting() {
        return korting;
    }

    public void setKorting(double korting) {
        this.korting = korting;
    }
   
}
