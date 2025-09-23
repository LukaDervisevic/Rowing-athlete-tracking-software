package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author luka
 */
public class Drzava implements OpstiDomenskiObjekat{
    
    private int id;
    
    private String naziv;

    public Drzava() {
    }

    public Drzava(int id, String naziv) {
        this.id = id;
        this.naziv = naziv;
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

    @Override
    public String toString() {
        return naziv;
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
        final Drzava other = (Drzava) obj;
        if (this.id != other.id) {
            return false;
        }
        return Objects.equals(this.naziv, other.naziv);
    }

    @Override
    public String vratiNaziveKolona() {
        return "naziv";
    }
    
    @Override
    public String vratiImePoKoloni(int i) {
        String[] kolone = {"id","naziv"};
        return kolone[i];
    }
    
    @Override
    public String vrednostiAtributaZaKreiranje() {
        return "'" + naziv + "'";
    }

    @Override
    public String azurirajVrednostiAtributa() {
        return "naziv = "+naziv;
    }
    
    @Override
    public String vratiWhereUslov() {
        return "id = "+id;
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiNoveSlogove(ResultSet rs) throws SQLException {
        List<OpstiDomenskiObjekat> drzave = new LinkedList<>();
        
        while(rs.next()) {
            Drzava drzava = new Drzava(rs.getInt("id"), rs.getString("naziv"));
            drzave.add(drzava);
        }
        
        return drzave;
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "id = " + id;
    }

    @Override
    public String vratiNazivTabele() {
        return "drzava";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public String alias() {
        return "D";
    }

    @Override
    public void postaviPrimarniKljuc(int id) {
        this.id = id;
    }
}
