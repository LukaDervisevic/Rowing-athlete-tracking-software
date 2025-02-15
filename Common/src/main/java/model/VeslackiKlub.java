
package model;

import java.io.Serializable;
import java.util.Objects;


public class VeslackiKlub extends Nalog implements Serializable{

    
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
//        if (telefon == null || !telefon.contains("+")) {
//            return;
//        }
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

}
