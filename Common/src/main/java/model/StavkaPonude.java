package model;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author luka
 */
public class StavkaPonude implements Serializable{
 
    private int idEvidencije;
    
    private int rb;
    
    private int godineTreniranja;
    
    private Veslac veslac;

    public StavkaPonude() {
    }

    public StavkaPonude(int idEvidencije, int rb, int godineTreniranja, Veslac veslac) {
        this.idEvidencije = idEvidencije;
        this.rb = rb;
        this.godineTreniranja = godineTreniranja;
        this.veslac = veslac;
    }

    public int getIdEvidencije() {
        return idEvidencije;
    }

    public void setIdEvidencije(int idEvidencije) {
        this.idEvidencije = idEvidencije;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public int getGodineTreniranja() {
        return godineTreniranja;
    }

    public void setGodineTreniranja(int godineTreniranja) {
        this.godineTreniranja = godineTreniranja;
    }

    public Veslac getVeslac() {
        return veslac;
    }

    public void setVeslac(Veslac veslac) {
        this.veslac = veslac;
    }

    @Override
    public String toString() {
        return "StavkaPonude{" + "idEvidencije=" + idEvidencije + ", rb=" + rb + ", godineTreniranja=" + godineTreniranja + ", veslac=" + veslac + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final StavkaPonude other = (StavkaPonude) obj;
        if (this.idEvidencije != other.idEvidencije) {
            return false;
        }
        if(this.rb != other.rb){
            return false;
        }
        if (!Objects.equals(this.godineTreniranja, other.godineTreniranja)) {
            return false;
        }
        return Objects.equals(this.veslac, other.veslac);
    }

    

    
    
    
    
}
