package model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author luka
 */
public class Takmicenje implements Serializable{
    
    private int id;
    
    private String naziv;
    
    private KategorijaVeslaca starosnaKategorija;
    
    private VrstaTrke vrstaTrke;
    
    private Date datum;

    public Takmicenje() {
    }

    public Takmicenje(int id, String naziv, KategorijaVeslaca starosnaKategorija, VrstaTrke vrstaTrke, Date datum) {
        this.id = id;
        this.naziv = naziv;
        this.starosnaKategorija = starosnaKategorija;
        this.vrstaTrke = vrstaTrke;
        this.datum = datum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public KategorijaVeslaca getStarosnaKategorija() {
        return starosnaKategorija;
    }

    public void setStarosnaKategorija(KategorijaVeslaca starosnaKategorija) {
        this.starosnaKategorija = starosnaKategorija;
    }

    public VrstaTrke getVrstaTrke() {
        return vrstaTrke;
    }

    public void setVrstaTrke(VrstaTrke vrstaTrke) {
        this.vrstaTrke = vrstaTrke;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    @Override
    public String toString() {
        return naziv + " " + starosnaKategorija + " " + vrstaTrke + " " + datum;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Takmicenje other = (Takmicenje) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.naziv, other.naziv)) {
            return false;
        }
        if (this.starosnaKategorija != other.starosnaKategorija) {
            return false;
        }
        if (this.vrstaTrke != other.vrstaTrke) {
            return false;
        }
        return Objects.equals(this.datum, other.datum);
    }
    
    
    
}
