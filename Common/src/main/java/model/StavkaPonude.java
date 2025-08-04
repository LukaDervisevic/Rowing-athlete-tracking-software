package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author luka
 */
public class StavkaPonude implements OpstiDomenskiObjekat{
 
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

    @Override
    public String vratiNaziveKolona() {
        return "id_ponude,rb,godine_treniranja,id_veslaca";
    }
    
    @Override
    public String vratiImePoKoloni(int i) {
        String[] kolone = {"id_ponude","rb","godine_treniranja","id_veslaca"};
        return kolone[i];
    }
    
    @Override
    public String vrednostiAtributaZaKreiranje() {
        return idEvidencije + "," + rb + "," + godineTreniranja + "," + veslac.getIdVeslaca();
    }

    @Override
    public String azurirajVrednostiAtributa() {
        return "id_ponude = " + idEvidencije + ",rb = " + rb + ",godine_treniranja = " + godineTreniranja + ",id_veslaca = " + veslac.getIdVeslaca();
    }

    @Override
    public String vratiNazivTabele() {
        return "stavka_ponude";
    }

    @Override
    public String vratiWhereUslov() {
        return "id_ponude = " + idEvidencije + " AND rb = " + rb; 
    }

    @Override
    public OpstiDomenskiObjekat vratiNoviSlog(ResultSet rs) throws SQLException {
        return new StavkaPonude(rs.getInt(alias()+".id_ponude"),rs.getInt(alias()+".rb"), rs.getInt(alias()+".godine_treniranja"), 
        new Veslac(rs.getInt(veslac.alias()+".id"), rs.getString(veslac.alias()+".ime_prezime"), new Date(rs.getDate(veslac.alias()+".datum_rodjenja").getTime()), rs.getFloat(veslac.alias()+".visina"), rs.getFloat(veslac.alias()+".tezina"), KategorijaVeslaca.valueOf(rs.getString(veslac.alias()+".kategorija")), rs.getFloat(veslac.alias()+".najbolje_vreme"), new Date(rs.getDate(veslac.alias()+".datum_upisa").getTime()),
        new VeslackiKlub(rs.getInt(veslac.getKlub().alias()+".id"), rs.getString(veslac.getKlub().alias()+".naziv"), rs.getString(veslac.getKlub().alias()+".adresa"), rs.getString(veslac.getKlub().alias()+".email"), rs.getString(veslac.getKlub().alias()+".telefon"), rs.getString(veslac.getKlub().alias()+".korisnicko_ime"), rs.getString(veslac.getKlub().alias()+".sifra"))));
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "id_stavke = " + idEvidencije + " AND " + " rb = " + rb;
    }

    @Override
    public String join() {
        return "JOIN `veslanje`.`" + veslac.vratiNazivTabele() + "` ON " + alias() + ".id_veslaca = " + veslac.alias() + ".id" + 
                "JOIN `veslanje`.`" + veslac.getKlub().vratiNazivTabele() +"` ON " + veslac.alias() + ".id_kluba = " 
                + veslac.getKlub().alias() + ".id";
    }

    @Override
    public String alias() {
        return "SP";
    }

    

    

    

    
    
    
    
}
