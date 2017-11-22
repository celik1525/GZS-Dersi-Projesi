/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ruhi ÇELİK
 */
@Entity
@Table(name = "nem")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Nem.findAll", query = "SELECT n FROM Nem n")
    , @NamedQuery(name = "Nem.findById", query = "SELECT n FROM Nem n WHERE n.id = :id")
    , @NamedQuery(name = "Nem.findByTarih", query = "SELECT n FROM Nem n WHERE n.tarih = :tarih")
    , @NamedQuery(name = "Nem.findByDeger", query = "SELECT n FROM Nem n WHERE n.deger = :deger")})
public class Nem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "tarih")
    private String tarih;
    @Column(name = "deger")
    private Integer deger;

    public Nem() {
    }

    public Nem(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    public Integer getDeger() {
        return deger;
    }

    public void setDeger(Integer deger) {
        this.deger = deger;
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
        if (!(object instanceof Nem)) {
            return false;
        }
        Nem other = (Nem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Nem[ id=" + id + " ]";
    }
    
}
