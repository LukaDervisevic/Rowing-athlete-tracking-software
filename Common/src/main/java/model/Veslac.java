package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author luka
 */
public class Veslac implements OpstiDomenskiObjekat {

    private int id;
    private String imePrezime;
    private Date datumRodjenja;
    private float visina;
    private float tezina;
    private KategorijaVeslaca kategorija;
    private float BMI;
    private float najboljeVreme;
    private Date datumUpisa;
    private VeslackiKlub veslackiKlub;

    public Veslac() {
        veslackiKlub = new VeslackiKlub();
    }

    public Veslac(int idVeslaca, String imePrezime, Date datumRodjenja, float visina, float tezina, KategorijaVeslaca kategorija, 
            float najboljeVreme, Date datumUpisa, VeslackiKlub veslackiKlub) {
        this.id = idVeslaca;
        this.imePrezime = imePrezime;
        this.datumRodjenja = datumRodjenja;
        this.visina = visina;
        this.tezina = tezina;
        this.BMI = tezina / (visina * visina);
        this.kategorija = kategorija;
        this.najboljeVreme = najboljeVreme;
        this.datumUpisa = datumUpisa;
        this.veslackiKlub = veslackiKlub;
    }

    public int getId() {
        return id;
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
        this.id = idVeslaca;
    }

    public void setBMI(float BMI) {
        this.BMI = BMI;
    }

    public VeslackiKlub getVeslackiKlub() {
        return veslackiKlub;
    }

    public void setVeslackiKlub(VeslackiKlub veslackiKlub) {
        this.veslackiKlub = veslackiKlub;
    }

    @Override
    public String toString() {
        return "Veslac{" + "idVeslaca=" + id + ", imePrezime=" + imePrezime + ", datumRodjenja=" + datumRodjenja + ", visina=" + visina + ", tezina=" + tezina + ", kategorija=" + kategorija + ", BMI=" + BMI + ", najboljeVreme=" + najboljeVreme + ", datumUpisa=" + datumUpisa + '}';
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
        if (this.id != other.id) {
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
        if (Float.floatToIntBits(this.najboljeVreme) != Float.floatToIntBits(other.najboljeVreme)) {
            return false;
        }
        if (!Objects.equals(this.imePrezime, other.imePrezime)) {
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

    @Override
    public String vratiNaziveKolona() {
        return "ime_prezime,datum_rodjenja,visina,tezina,kategorija,BMI,najbolje_vreme,datum_upisa,id_kluba";
    }

    @Override
    public String vratiImePoKoloni(int i) {
        String[] kolone = {"id", "ime_prezime", "datum_rodjenja", "visina", "tezina", "kategorija", "BMI", "najbolje_vreme", "datum_upisa", "id_kluba"};
        return kolone[i];
    }

    @Override
    public String vrednostiAtributaZaKreiranje() {
        return "'" + imePrezime + "','" + new java.sql.Date(datumRodjenja.getTime()) + "'," + visina + "," + tezina + ",'" + kategorija + "'," + BMI + "," + najboljeVreme + ",'" + new java.sql.Date(datumUpisa.getTime()) + "'," + veslackiKlub.getId();
    }

    @Override
    public String azurirajVrednostiAtributa() {
        return "ime_prezime = " + imePrezime + ",datum_rodjenja = '" + new java.sql.Date(datumRodjenja.getTime()) + "' ,visina = " + visina + ",tezina = " + tezina + ",kategorija = " + kategorija.toString() + ",BMI = " + BMI + ",najbolje_vreme = "
                + najboljeVreme + ",datum_upisa = '" + new java.sql.Date(datumUpisa.getTime()) + "',id_kluba = " + veslackiKlub.getId();
    }

    @Override
    public String vratiNazivTabele() {
        return "veslac";
    }

    @Override
    public String vratiWhereUslov() {
        return "id = " + id;
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiNoveSlogove(ResultSet rs) throws SQLException {
        List<OpstiDomenskiObjekat> veslaci = new LinkedList<>();

        while (rs.next()) {
            Veslac veslac = new Veslac(
                    rs.getInt(alias() + ".id"),
                    rs.getString(alias() + ".ime_prezime"),
                    new Date(rs.getDate(alias() + ".datum_rodjenja").getTime()),
                    rs.getFloat(alias() + ".visina"),
                    rs.getFloat(alias() + ".tezina"),
                    KategorijaVeslaca.valueOf(rs.getString(alias() + ".kategorija").toUpperCase()),
                    rs.getFloat(alias() + ".najbolje_vreme"),
                    new Date(rs.getDate(alias() + ".datum_upisa").getTime()), // ✅ fixed
                    new VeslackiKlub(
                            rs.getInt(veslackiKlub.alias() + ".id"),
                            rs.getString(veslackiKlub.alias() + ".naziv"),
                            rs.getString(veslackiKlub.alias() + ".adresa"),
                            rs.getString(veslackiKlub.alias() + ".email"),
                            rs.getString(veslackiKlub.alias() + ".telefon"),
                            rs.getString(veslackiKlub.alias() + ".korisnicko_ime"),
                            rs.getString(veslackiKlub.alias() + ".sifra")
                    ));
            veslaci.add(veslac);

        }

        return veslaci;

    }

    @Override
    public String vratiPrimarniKljuc() {
        return "idVeslaca";
    }

    @Override
    public String join() {
        return "JOIN `veslanje`.`" + veslackiKlub.vratiNazivTabele() + "` AS " + veslackiKlub.alias() + " ON " + alias() + ".id_kluba = " + veslackiKlub.alias() + ".id";
    }

    @Override
    public String alias() {
        return "V";
    }

    @Override
    public void postaviPrimarniKljuc(int id) {
        this.id = id;
    }
}
