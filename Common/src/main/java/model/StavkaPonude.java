package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author luka
 */
public class StavkaPonude implements OpstiDomenskiObjekat {

    private PonudaVeslaca ponudaVeslaca;

    private int rb;

    private int godineTreniranja;

    private Veslac veslac;

    public StavkaPonude() {
    }

    public StavkaPonude(PonudaVeslaca ponudaVeslaca, int rb, int godineTreniranja, Veslac veslac) {
        this.ponudaVeslaca = ponudaVeslaca;
        this.rb = rb;
        this.godineTreniranja = godineTreniranja;
        this.veslac = veslac;
    }

    public PonudaVeslaca getPonudaVeslaca() {
        return ponudaVeslaca;
    }

    public void setPonudaVeslaca(PonudaVeslaca ponudaVeslaca) {
        this.ponudaVeslaca = ponudaVeslaca;
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
        return "StavkaPonude{" + "ponudaVeslaca=" + ponudaVeslaca + ", rb=" + rb + ", godineTreniranja=" + godineTreniranja + ", veslac=" + veslac + '}';
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
        if (this.rb != other.rb) {
            return false;
        }
        if (this.godineTreniranja != other.godineTreniranja) {
            return false;
        }
        if (!Objects.equals(this.ponudaVeslaca, other.ponudaVeslaca)) {
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
        String[] kolone = {"id_ponude", "rb", "godine_treniranja", "id_veslaca"};
        return kolone[i];
    }

    @Override
    public String vrednostiAtributaZaKreiranje() {
        return ponudaVeslaca.getId() + "," + rb + "," + godineTreniranja + "," + veslac.getId();
    }

    @Override
    public String azurirajVrednostiAtributa() {
        return "id_ponude = " + ponudaVeslaca.getId() + ",rb = " + rb + ",godine_treniranja = " + godineTreniranja + ",id_veslaca = " + veslac.getId();
    }

    @Override
    public String vratiNazivTabele() {
        return "stavka_ponude";
    }

    @Override
    public String vratiWhereUslov() {
        return "id_ponude = " + ponudaVeslaca.getId() + " AND rb = " + rb;
    }

    @Override
    public OpstiDomenskiObjekat vratiNoviSlog(ResultSet rs) throws SQLException {
        return new StavkaPonude(
                new PonudaVeslaca(rs.getInt(ponudaVeslaca.alias()+".id"),
                        rs.getDate(ponudaVeslaca.alias() + ".datum_kreiranja"),
                        rs.getInt(ponudaVeslaca.alias() + ".broj_kadeta"), 
                        rs.getInt(ponudaVeslaca.alias() + ".broj_juniora"),
                        rs.getFloat(ponudaVeslaca.alias() + ".prosecno_vreme_kadeti"),
                        rs.getFloat(alias() + ".prosecno_vreme_junior"), null, null, null),
                rs.getInt(alias() + ".rb"),
                rs.getInt(alias() + ".godine_treniranja"),
                new Veslac(
                        rs.getInt(veslac.alias() + ".id"),
                        rs.getString(veslac.alias() + ".ime_prezime"),
                        new Date(rs.getDate(veslac.alias() + ".datum_rodjenja").getTime()),
                        rs.getFloat(veslac.alias() + ".visina"),
                        rs.getFloat(veslac.alias() + ".tezina"),
                        KategorijaVeslaca.valueOf(rs.getString(veslac.alias() + ".kategorija")),
                        rs.getFloat(veslac.alias() + ".najbolje_vreme"),
                        new Date(rs.getDate(veslac.alias() + ".datum_upisa").getTime()), 
                        new VeslackiKlub(
                                rs.getInt(veslac.getVeslackiKlub().alias() + ".id"),
                                rs.getString(veslac.getVeslackiKlub().alias() + ".naziv"),
                                rs.getString(veslac.getVeslackiKlub().alias() + ".adresa"),
                                rs.getString(veslac.getVeslackiKlub().alias() + ".email"),
                                rs.getString(veslac.getVeslackiKlub().alias() + ".telefon"),
                                rs.getString(veslac.getVeslackiKlub().alias() + ".korisnicko_ime"),
                                rs.getString(veslac.getVeslackiKlub().alias() + ".sifra")
                        )
                )
        );
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "id_stavke = " + ponudaVeslaca.getId() + " AND " + " rb = " + rb;
    }

    @Override
    public String join() {
        return "JOIN `veslanje`.`"+ ponudaVeslaca.vratiNazivTabele()+"` ON "+alias() +".id_ponude = "+ponudaVeslaca.alias()+".id"+
                " JOIN `veslanje`.`" + veslac.vratiNazivTabele() + "` ON " + alias() + ".id_veslaca = " + veslac.alias() + ".id";
    }

    @Override
    public String alias() {
        return "SP";
    }

    @Override
    public void postaviPrimarniKljuc(int id) {
        this.rb = id;
    }
    
    

}
