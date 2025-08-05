package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 *
 * @author luka
 */
public class KlubTakmicenje implements OpstiDomenskiObjekat{
    
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

    @Override
    public String vratiNaziveKolona() {
        return "id_kluba,id_takmicenja,mesto";
    }
    
    @Override
    public String vratiImePoKoloni(int i) {
        String[] kolone = {"id_kluba","id_takmicenja","mesto"};
        return kolone[i];
    }   
    
    @Override
    public String vrednostiAtributaZaKreiranje() {
        return klub.getId() + "," + takmicenje.getId() + "," + mesto;
    }

    @Override
    public String azurirajVrednostiAtributa() {
        return "id_kluba = " + klub.getId() + ",id_takmicenje = " + takmicenje.getId() + ",mesto = " + mesto;
    }

    @Override
    public String vratiNazivTabele() {
        return "klub_takmicenje";
    }

    @Override
    public String vratiWhereUslov() {
        return "id_kluba = " + klub.getId() + ",id_takmicenje = " + takmicenje.getId() + ",mesto = " + mesto;
    }

    @Override
    public OpstiDomenskiObjekat vratiNoviSlog(ResultSet rs) throws SQLException {
        return new KlubTakmicenje(rs.getInt("mesto"),
                new VeslackiKlub(rs.getInt(klub.alias()+".id"), rs.getString(klub.alias()+".naziv"),rs.getString(klub.alias()+".adresa"), rs.getString(klub.alias()+".email"), rs.getString(klub.alias()+".telefon"), rs.getString(klub.alias()+".korisnicko_ime"), rs.getString(klub.alias()+".sifra")) ,
                new Takmicenje(rs.getInt(takmicenje.alias()+".id"), rs.getString(takmicenje.alias()+".naziv"), KategorijaVeslaca.valueOf(rs.getString(takmicenje.alias()+".starosna_kategorija")), VrstaTrke.valueOf(takmicenje.alias()+".vrsta_trke"), new java.util.Date(rs.getDate(takmicenje.alias()+".datum").getTime())));
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "id_kluba = " + klub.getId() + ",id_takmicenja = " + takmicenje.getId() + ",mesto = " + mesto;
    }

    @Override
    public String join() {
        return "JOIN `veslanje`.`" + takmicenje.vratiNazivTabele() + "` AS " + alias() + " ON " + takmicenje.alias() + ".id = " + alias() + ".id_takmicenja" 
                + " JOIN `veslanje`.`" + klub.vratiNazivTabele() +   "` AS " + klub.alias() + " ON " + alias() + ".id_kluba = " + klub.alias() + ".id";
    }

    
    @Override
    public String alias() {
        return "KT";
    }

    

}
