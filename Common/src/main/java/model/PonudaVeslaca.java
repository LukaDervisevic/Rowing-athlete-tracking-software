package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author luka
 */
public class PonudaVeslaca implements OpstiDomenskiObjekat{
    
    private int id;
    
    private Date datumKreiranja;
    
    private int brojKadeta;
    
    private int brojJuniora;
    
    private float prosecnoVremeKadeti;
    
    private float prosecnoVremeJuniori;
    
    private List<StavkaPonude> stavke;
    
    private int idKluba;
    
    private int idAgencije;

    public PonudaVeslaca() {
    }

    public PonudaVeslaca(int id, Date datumKreiranja, int brojKadeta, int brojJuniora, float prosecnoVremeKadeti, float prosecnoVremeJuniori,List<StavkaPonude> stavke,int idKluba, int idAgencije) {
        this.id = id;
        this.datumKreiranja = datumKreiranja;
        this.brojKadeta = brojKadeta;
        this.brojJuniora = brojJuniora;
        this.prosecnoVremeKadeti = prosecnoVremeKadeti;
        this.prosecnoVremeJuniori = prosecnoVremeJuniori;
        this.stavke = stavke;
        this.idKluba = idKluba;
        this.idAgencije = idAgencije;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDatumKreiranja() {
        return datumKreiranja;
    }

    public void setDatumKreiranja(Date datumKreiranja) {
        this.datumKreiranja = datumKreiranja;
    }

    public int getBrojKadeta() {
        return brojKadeta;
    }

    public void setBrojKadeta(int brojKadeta) {
        this.brojKadeta = brojKadeta;
    }

    public int getBrojJuniora() {
        return brojJuniora;
    }

    public void setBrojJuniora(int brojJuniora) {
        this.brojJuniora = brojJuniora;
    }

    public float getProsecnoVremeKadeti() {
        return prosecnoVremeKadeti;
    }

    public void setProsecnoVremeKadeti(float prosecnoVremeKadeti) {
        this.prosecnoVremeKadeti = prosecnoVremeKadeti;
    }

    public float getProsecnoVremeJuniori() {
        return prosecnoVremeJuniori;
    }

    public void setProsecnoVremeJuniori(float prosecnoVremeJuniori) {
        this.prosecnoVremeJuniori = prosecnoVremeJuniori;
    }

    public List<StavkaPonude> getStavke() {
        return stavke;
    }

    public void setStavke(List<StavkaPonude> stavke) {
        this.stavke = stavke;
    }

    public int getIdKluba() {
        return idKluba;
    }

    public void setIdKluba(int idKluba) {
        this.idKluba = idKluba;
    }

    public int getIdAgencije() {
        return idAgencije;
    }

    public void setIdAgencije(int idAgencije) {
        this.idAgencije = idAgencije;
    }

    @Override
    public String toString() {
        return "PonudaVeslaca{" + "id=" + id + ", datumKreiranja=" + datumKreiranja + ", brojKadeta=" + brojKadeta + ", brojJuniora=" + brojJuniora + ", prosecnoVremeKadeti=" + prosecnoVremeKadeti + ", prosecnoVremeJuniori=" + prosecnoVremeJuniori + ", idKluba=" + idKluba + ", idAgencije=" + idAgencije + '}';
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
        final PonudaVeslaca other = (PonudaVeslaca) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.brojKadeta != other.brojKadeta) {
            return false;
        }
        if (this.brojJuniora != other.brojJuniora) {
            return false;
        }
        if (this.idKluba != other.idKluba) {
            return false;
        }
        if (this.idAgencije != other.idAgencije) {
            return false;
        }
        if (!Objects.equals(this.prosecnoVremeKadeti, other.prosecnoVremeKadeti)) {
            return false;
        }
        if (!Objects.equals(this.prosecnoVremeJuniori, other.prosecnoVremeJuniori)) {
            return false;
        }
        return Objects.equals(this.datumKreiranja, other.datumKreiranja);
    }

    @Override
    public String vratiNaziveKolona() {
        return "datum_kreiranja,broj_kadeta,broj_juniora,prosecno_vreme_kadeti,prosecno_vreme_junior,id_kluba,id_agencije";
    }
    
    @Override
    public String vratiImePoKoloni(int i) {
        String[] kolone = {"id,datum_kreiranja,broj_kadeta,broj_juniora,prosecno_vreme_kadeti,prosecno_vreme_junior,id_kluba,id_agencije"};
        return kolone[i];
    }
    
    @Override
    public String vrednostiAtributaZaKreiranje() {
        return "datum_kreiranja,broj_kadeta,broj_juniora,prosecno_vreme_kadeti,prosecno_vreme_junior,id_kluba,id_agencije";
    }

    @Override
    public String azurirajVrednostiAtributa() {
        return "datum_kreiranja = " + new java.sql.Date(datumKreiranja.getTime()) + ",broj_kadeta = " + brojKadeta + 
                ",broj_juniora = " + brojJuniora + ",prosecno_vreme_kadeti = " + prosecnoVremeKadeti + ",prosecno_vreme_junior = " + prosecnoVremeJuniori + ", id_kluba = " + idKluba + ",id_agencije = " + idAgencije;
    }

    @Override
    public String vratiNazivTabele() {
        return "ponuda_veslaca";
    }

    @Override
    public String vratiWhereUslov() {
        return "id_ponude = " + id;
    }

    @Override
    public OpstiDomenskiObjekat vratiNoviSlog(ResultSet rs) throws SQLException {
        return null;
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "id_ponude = " + id;
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public String alias() {
        return "P";
    }
}
