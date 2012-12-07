/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entitys;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author stinson
 */
@Entity
public class Magazijn implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int magazijnNummer;
    @OneToMany(mappedBy = "magazijn", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Product> producten;

    public Magazijn() {
        this(0);
    }

    public Magazijn(int magazijnNummer) {
        this.setMagazijnNummer(magazijnNummer);
    }

    public List<Product> getProducten() {
        return producten;
    }

    public void setProducten(List<Product> producten) {
        this.producten = producten;
    }

    public int getMagazijnNummer() {
        return magazijnNummer;
    }

    public void setMagazijnNummer(int magazijnNummer) {
        this.magazijnNummer = magazijnNummer;
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
        if (!(object instanceof Magazijn)) {
            return false;
        }
        Magazijn other = (Magazijn) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitys.Magazijn[ id=" + id + " ]";
    }

    public void addProduct(Product product) {
        this.producten.add(product);
    }
}
