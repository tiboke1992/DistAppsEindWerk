/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entitys;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

/**
 *
 * @author stinson
 */
@Entity
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String naam;
    private double prijs;
    private int aantalInvoorraad;
    @ManyToMany
    private List<Bestelling> bestellingen;
    @ManyToOne
    private Magazijn magazijn;

    public Magazijn getMagazijn() {
        return magazijn;
    }

    public void setMagazijn(Magazijn magazijn) {
        this.magazijn = magazijn;
    }

    public List<Bestelling> getBestellingen() {
        return bestellingen;
    }

    public void setBestellingen(List<Bestelling> bestellingen) {
        this.bestellingen = bestellingen;
    }

    public Product() {
        this(null, 0, 0);
    }

    public Product(String naam, double prijs, int aantalInVoorraad) {
        this.setNaam(naam);
        this.setPrijs(prijs);
        this.setAantalInvoorraad(aantalInvoorraad);
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public double getPrijs() {
        return prijs;
    }

    public void setPrijs(double prijs) {
        this.prijs = prijs;
    }

    public int getAantalInvoorraad() {
        return aantalInvoorraad;
    }

    public void setAantalInvoorraad(int aantalInvoorraad) {
        this.aantalInvoorraad = aantalInvoorraad;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Product)) {
            return false;
        }
        Product other = (Product) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitys.Product[ id=" + id + " ]";
    }
}
