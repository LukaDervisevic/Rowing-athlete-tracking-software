package model;

import java.util.Objects;

/**
 *
 * @author luka
 */
public class KlubTakmicenje {
    
    private int mesto;
    
    private VeslackiKlub klub;
    
    private Takmicenje takmicenje;

    public KlubTakmicenje() {
    }

    public KlubTakmicenje(int mesto, VeslackiKlub klub,Takmicenje takmicenje) {
        this.mesto = mesto;
        this.klub = klub;
        this.takmicenje = takmicenje;
    }

    public int getMesto() {
        return mesto;
    }

    public void setMesto(int mesto) {
        this.mesto = mesto;
    }

    public VeslackiKlub getKlub() {
        return klub;
    }

    public void setKlub(VeslackiKlub klub) {
        this.klub = klub;
    }

    public Takmicenje getTakmicenje() {
        return takmicenje;
    }

    public void setTakmicenje(Takmicenje takmicenje) {
        this.takmicenje = takmicenje;
    }

    @Override
    public String toString() {
        return "KlubTakmicenje{" + "mesto=" + mesto + ", klub=" + klub + ", takmicenje=" + takmicenje + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final KlubTakmicenje other = (KlubTakmicenje) obj;
        if (this.mesto != other.mesto) {
            return false;
        }
        if (!Objects.equals(this.klub, other.klub)) {
            return false;
        }
        return Objects.equals(this.takmicenje, other.takmicenje);
    }

}
