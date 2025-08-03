
package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;


public class VeslackiKlub extends Nalog implements OpstiDomenskiObjekat{

    
    private int id;

    private String naziv;

    private String adresa;

    private String email;

    private String telefon;

    private String korisnickoIme;

    private String sifra;

    public VeslackiKlub() {
    }

    public VeslackiKlub(int id, String naziv, String adresa, String email, String telefon, String korisnickoIme, String sifra) {
        this.id = id;
        this.naziv = naziv;
        this.adresa = adresa;
        this.email = email;
        this.telefon = telefon;
        this.korisnickoIme = korisnickoIme;
        this.sifra = sifra;
    }
    
    

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        if (naziv == null) {
            return;
        }
        this.naziv = naziv;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        if (adresa == null) {
            return;
        }
        this.adresa = adresa;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null) {
            return;
        }
        this.email = email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        if (korisnickoIme == null) {
            return;
        }
        this.korisnickoIme = korisnickoIme;
    }

    public void setId(int idKluba) {
        this.id = idKluba;
    }

    public int getId() {
        return id;
    }

    public void setSifra(String sifra) {
        if (sifra == null || sifra.length() < 8) {
            return;
        }
        this.sifra = sifra;
    }

    public String getSifra() {
        return sifra;
    }

    @Override
    public String toString() {
        return "VeslackiKlub{" + "idKluba=" + id + ", naziv=" + naziv + ", adresa=" + adresa + ", email=" + email + ", telefon=" + telefon + ", korisnickoIme=" + korisnickoIme +  ", sifra= "+ sifra +'}';
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
        final VeslackiKlub other = (VeslackiKlub) obj;

        if (!Objects.equals(this.id, other.id)) {
            return false;
        }

        if (!Objects.equals(this.id, other.id)) {
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
        return Objects.equals(this.korisnickoIme, other.korisnickoIme);
    }

    @Override
    public String vrednostiAtributaZaKreiranje() {
        return naziv + "," + adresa + "," + email + "," + telefon + "," +  korisnickoIme + "," + sifra;
    }

    @Override
    public String azurirajVrednostiAtributa() {
        return "naziv=" + naziv + ",adresa="+ adresa + ",email=" + email + ",telefon=" + telefon + ",korisnicko_ime=" + korisnickoIme + ",sifra=" + sifra;
    }

    @Override
    public String vratiNazivTabele() {
        return "veslacki_klub";
    }

    @Override
    public String vratiWhereUslov() {
        return "id = " + id;
    }

    @Override
    public String vratiImePoKoloni(int kolona) {
        return null;
    }

    @Override
    public OpstiDomenskiObjekat vratiNoviSlog(ResultSet rs) throws SQLException {
        return new VeslackiKlub(rs.getInt(alias()+".id"), rs.getString(alias()+".naziv"),rs.getString(alias()+".adresa"), rs.getString(alias()+".email"), rs.getString(alias()+".telefon"), rs.getString(alias()+".korisnicko_ime"), rs.getString(alias()+".sifra"));
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "id = " + id;
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public String alias() {
        return "VK";
    }

}
