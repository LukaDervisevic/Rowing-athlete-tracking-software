package model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;


public class Nalog implements OpstiDomenskiObjekat{
    
    private int id;

    private String naziv;
    
    private String adresa;
    
    private String email;
    
    private String telefon;
    
    private String korisnicko_ime;
    
    private String sifra;
    
    private TipNaloga tipNaloga;

    public Nalog() {
    }

    public Nalog(TipNaloga tipNaloga,int id, String naziv, String adresa, String email, String telefon, String korisnicko_ime, String sifra) {
        this.id = id;
        this.naziv = naziv;
        this.adresa = adresa;
        this.email = email;
        this.telefon = telefon;
        this.korisnicko_ime = korisnicko_ime;
        this.sifra = sifra;
        this.tipNaloga = tipNaloga;
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

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getKorisnicko_ime() {
        return korisnicko_ime;
    }

    public void setKorisnicko_ime(String korisnicko_ime) {
        this.korisnicko_ime = korisnicko_ime;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    public TipNaloga getTipNaloga() {
        return tipNaloga;
    }

    public void setTipNaloga(TipNaloga tipNaloga) {
        this.tipNaloga = tipNaloga;
    }

    @Override
    public String toString() {
        return "Nalog{" + "id=" + id + ", naziv=" + naziv + ", adresa=" + adresa + ", email=" + email + ", telefon=" + telefon + ", korisnicko_ime=" + korisnicko_ime + ", sifra=" + sifra + ", tipNaloga=" + tipNaloga + '}';
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
        final Nalog other = (Nalog) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.naziv, other.naziv)) {
            return false;
        }
        if (!Objects.equals(this.adresa, other.adresa)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.telefon, other.telefon)) {
            return false;
        }
        if (!Objects.equals(this.korisnicko_ime, other.korisnicko_ime)) {
            return false;
        }
        if (!Objects.equals(this.sifra, other.sifra)) {
            return false;
        }
        return this.tipNaloga == other.tipNaloga;
    }

    @Override
    public String vrednostiAtributaZaKreiranje() {
        return "";
    }

    @Override
    public String azurirajVrednostiAtributa() {
        return "";
    }

    @Override
    public String vratiNaziveKolona() {
        return "";
    }

    @Override
    public String vratiNazivTabele() {
        return "";
    }

    @Override
    public String vratiWhereUslov() {
        return "";
    }
    
    @Override
    public OpstiDomenskiObjekat vratiNoviSlog(ResultSet rs) throws SQLException {
        return null;
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public String alias() {
        return "";
    }

    @Override
    public String vratiImePoKoloni(int i) {
        return "";
    }
    
    
    
}
