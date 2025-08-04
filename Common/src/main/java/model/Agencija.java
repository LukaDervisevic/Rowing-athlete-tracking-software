package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class Agencija extends Nalog implements OpstiDomenskiObjekat{
    
    private int id;
    
    private String naziv;
    
    private String email;
    
    private String telefon;
    
    private String korisnickoIme;
    
    private String sifra;
    
    private Drzava drzava;

    public Agencija() {
    }

    public Agencija(int id, String naziv, String email, String telefon, String korisnickoIme, String sifra, Drzava drzava) {
        this.id = id;
        this.naziv = naziv;
        this.email = email;
        this.telefon = telefon;
        this.korisnickoIme = korisnickoIme;
        this.sifra = sifra;
        this.drzava = drzava;
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

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    public Drzava getDrzava() {
        return drzava;
    }

    public void setDrzava(Drzava drzava) {
        this.drzava = drzava;
    }

    @Override
    public String toString() {
        return "Agencija{" + "id=" + id + ", naziv=" + naziv + ", email=" + email + ", telefon=" + telefon + ", korisnickoIme=" + korisnickoIme + ", sifra=" + sifra + ", drzava=" + drzava + '}';
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
        final Agencija other = (Agencija) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.naziv, other.naziv)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.telefon, other.telefon)) {
            return false;
        }
        if (!Objects.equals(this.korisnickoIme, other.korisnickoIme)) {
            return false;
        }
        if (!Objects.equals(this.sifra, other.sifra)) {
            return false;
        }
        return Objects.equals(this.drzava, other.drzava);
    }

    @Override
    public String vratiNaziveKolona() {
        return "naziv,email,telefon,korisnicko_ime,sifra,id_drzave";
    }
    
    @Override
    public String vratiImePoKoloni(int i) {
        String[] kolone = {"id","naziv","email","telefon","korisnicko_ime","sifra","id_drzave"};
        return kolone[i];
    }
    
    @Override
    public String vrednostiAtributaZaKreiranje() {
        return naziv + "," + email + "," + telefon + "," + korisnickoIme + "," + sifra + "," + drzava.getId();
    }

    @Override
    public String azurirajVrednostiAtributa() {
        return "naziv=" + naziv + ",email=" + email + ",telefon=" + telefon + ",korisnicko_ime=" + korisnickoIme + ",sifra=" + sifra + ",id_drzave=" + drzava.getId();
    }

    @Override
    public String vratiNazivTabele() {
        return "agencija";
    }

    @Override
    public String vratiWhereUslov() {
        return "id = " + id;
    }

    @Override
    public OpstiDomenskiObjekat vratiNoviSlog(ResultSet rs) throws SQLException {
        return new Agencija(rs.getInt("a.id"), rs.getString("a.naziv"), rs.getString("a.email"), rs.getString("a.telefon"), rs.getString("a.korisnicko_ime"), rs.getString("a.sifra"),
        new Drzava(rs.getInt("d.id"), rs.getString("d.naziv")));
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "id = " + id;
    }

    @Override
    public String join() {
        return "JOIN `veslanje`.`" + drzava.vratiNazivTabele() +"` AS " + drzava.alias() + " ON " + alias() + ".id_drzave = " + drzava.alias() + ".id";
    }

    @Override
    public String alias() {
        return "A";
    }
}
