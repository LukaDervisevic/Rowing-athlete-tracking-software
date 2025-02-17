package model;


import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author luka
 */
public class Veslac implements Serializable{

    private int idVeslaca;

    private String imePrezime;

    private Date datumRodjenja;

    private float visina;

    private float tezina;

    private KategorijaVeslaca kategorija;

    private float BMI;

    private float najboljeVreme;

    private Date datumUpisa;
    
    private int idKluba;

    public Veslac() {
    }

    public Veslac(int idVeslaca,String imePrezime, Date datumRodjenja, float visina, float tezina, KategorijaVeslaca kategorija, float najboljeVreme, Date datumUpisa,int idKluba) {
        this.idVeslaca = idVeslaca;
        this.imePrezime = imePrezime;
        this.datumRodjenja = datumRodjenja;
        this.visina = visina;
        this.tezina = tezina;
        this.BMI = tezina / (visina * visina);
        this.kategorija = kategorija;
        this.najboljeVreme = najboljeVreme;
        this.datumUpisa = datumUpisa;
        this.idKluba = idKluba;
    }

    public int getIdVeslaca() {
        return idVeslaca;
    }

    public String getImePrezime() {
        return imePrezime;
    }

    public void setImePrezime(String imePrezime) {
        this.imePrezime = imePrezime;
    }

    public Date getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(Date datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public float getVisina() {
        return visina;
    }

    public void setVisina(float visina) {
        this.visina = visina;
    }

    public float getTezina() {
        return tezina;
    }

    public void setTezina(float tezina) {
        this.tezina = tezina;
    }

    public KategorijaVeslaca getKategorija() {
        return kategorija;
    }

    public void setKategorija(KategorijaVeslaca kategorija) {
        this.kategorija = kategorija;
    }

    public float getBMI() {
        return BMI;
    }

    public float getNajboljeVreme() {
        return najboljeVreme;
    }

    public void setNajboljeVreme(float najboljeVreme) {
        this.najboljeVreme = najboljeVreme;
    }

    public Date getDatumUpisa() {
        return datumUpisa;
    }

    public void setDatumUpisa(Date datumUpisa) {
        this.datumUpisa = datumUpisa;
    }

    public void setIdVeslaca(int idVeslaca) {
        this.idVeslaca = idVeslaca;
    }

    public void setBMI(float BMI) {
        this.BMI = BMI;
    }

    public int getIdKluba() {
        return idKluba;
    }

    public void setIdKluba(int idKluba) {
        this.idKluba = idKluba;
    }

    @Override
    public String toString() {
        return "Veslac{" + "idVeslaca=" + idVeslaca + ", imePrezime=" + imePrezime + ", datumRodjenja=" + datumRodjenja + ", visina=" + visina + ", tezina=" + tezina + ", kategorija=" + kategorija + ", BMI=" + BMI + ", najboljeVreme=" + najboljeVreme + ", datumUpisa=" + datumUpisa + ", idKluba=" + idKluba + '}';
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
        final Veslac other = (Veslac) obj;
        if (this.idVeslaca != other.idVeslaca) {
            return false;
        }
        if (Float.floatToIntBits(this.visina) != Float.floatToIntBits(other.visina)) {
            return false;
        }
        if (Float.floatToIntBits(this.tezina) != Float.floatToIntBits(other.tezina)) {
            return false;
        }
        if (Float.floatToIntBits(this.BMI) != Float.floatToIntBits(other.BMI)) {
            return false;
        }
        if (this.idKluba != other.idKluba) {
            return false;
        }
        if (!Objects.equals(this.imePrezime, other.imePrezime)) {
            return false;
        }
        if (!Objects.equals(this.najboljeVreme, other.najboljeVreme)) {
            return false;
        }
        if (!Objects.equals(this.datumRodjenja, other.datumRodjenja)) {
            return false;
        }
        if (this.kategorija != other.kategorija) {
            return false;
        }
        return Objects.equals(this.datumUpisa, other.datumUpisa);
    }

    
    
}
