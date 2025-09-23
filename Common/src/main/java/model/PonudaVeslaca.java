package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author luka
 */
public class PonudaVeslaca implements OpstiDomenskiObjekat {

    private int id;

    private Date datumKreiranja;

    private int brojKadeta;

    private int brojJuniora;

    private float prosecnoVremeKadeti;

    private float prosecnoVremeJuniori;

    private List<StavkaPonude> stavke;

    private VeslackiKlub veslackiKlub;

    private Agencija agencija;

    public PonudaVeslaca() {
        this.veslackiKlub = new VeslackiKlub();
        this.agencija = new Agencija();
    }

    public PonudaVeslaca(int id, Date datumKreiranja, int brojKadeta, int brojJuniora, float prosecnoVremeKadeti, float prosecnoVremeJuniori, List<StavkaPonude> stavke, VeslackiKlub veslackiKlub, Agencija agencija) {
        this.id = id;
        this.datumKreiranja = datumKreiranja;
        this.brojKadeta = brojKadeta;
        this.brojJuniora = brojJuniora;
        this.prosecnoVremeKadeti = prosecnoVremeKadeti;
        this.prosecnoVremeJuniori = prosecnoVremeJuniori;
        this.stavke = stavke;
        this.veslackiKlub = veslackiKlub;
        this.agencija = agencija;
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

    public VeslackiKlub getVeslackiKlub() {
        return veslackiKlub;
    }

    public void setVeslackiKlub(VeslackiKlub veslackiKlub) {
        this.veslackiKlub = veslackiKlub;
    }

    public Agencija getAgencija() {
        return agencija;
    }

    public void setAgencija(Agencija agencija) {
        this.agencija = agencija;
    }

    @Override
    public String toString() {
        return "PonudaVeslaca{" + "id=" + id + ", datumKreiranja=" + datumKreiranja + ", brojKadeta=" + brojKadeta + ", brojJuniora=" + brojJuniora + ", prosecnoVremeKadeti=" + prosecnoVremeKadeti + ", prosecnoVremeJuniori=" + prosecnoVremeJuniori + ", stavke=" + stavke + ", veslackiKlub=" + veslackiKlub + ", agencija=" + agencija + '}';
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
        if (Float.floatToIntBits(this.prosecnoVremeKadeti) != Float.floatToIntBits(other.prosecnoVremeKadeti)) {
            return false;
        }
        if (Float.floatToIntBits(this.prosecnoVremeJuniori) != Float.floatToIntBits(other.prosecnoVremeJuniori)) {
            return false;
        }
        if (!Objects.equals(this.datumKreiranja, other.datumKreiranja)) {
            return false;
        }
        if (!Objects.equals(this.stavke, other.stavke)) {
            return false;
        }
        if (!Objects.equals(this.veslackiKlub, other.veslackiKlub)) {
            return false;
        }
        return Objects.equals(this.agencija, other.agencija);
    }

    @Override
    public String vratiNaziveKolona() {
        return "id,datum_kreiranja,broj_kadeta,broj_juniora,prosecno_vreme_kadeti,prosecno_vreme_junior,id_kluba,id_agencije";
    }

    @Override
    public String vratiImePoKoloni(int i) {
        String[] kolone = {"id", "datum_kreiranja", "broj_kadeta", "broj_juniora", "prosecno_vreme_kadeti", "prosecno_vreme_junior", "id_kluba", "id_agencije"};
        return kolone[i];
    }

    @Override
    public String vrednostiAtributaZaKreiranje() {
        return id + ",'" + new java.sql.Date(datumKreiranja.getTime()) + "'," + brojKadeta + "," + brojJuniora + "," + prosecnoVremeKadeti + "," + prosecnoVremeJuniori + "," + veslackiKlub.getId() + "," + agencija.getId();
    }

    @Override
    public String azurirajVrednostiAtributa() {
        return "datum_kreiranja = " + new java.sql.Date(datumKreiranja.getTime()) + ",broj_kadeta = " + brojKadeta
                + ",broj_juniora = " + brojJuniora + ",prosecno_vreme_kadeti = " + prosecnoVremeKadeti + ",prosecno_vreme_junior = " + prosecnoVremeJuniori + ", id_kluba = " + veslackiKlub.getId() + ",id_agencije = " + agencija.getId();
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
    public List<OpstiDomenskiObjekat> vratiNoveSlogove(ResultSet rs) throws SQLException {
        List<OpstiDomenskiObjekat> ponude = new LinkedList<>();
        HashMap<Integer, PonudaVeslaca> ponudeMap = new HashMap<>();

        while (rs.next()) {

            PonudaVeslaca ponuda = new PonudaVeslaca();
            List<StavkaPonude> listaStavki = new LinkedList<>();
            ponuda.setStavke(listaStavki);

            VeslackiKlub vk = new VeslackiKlub(
                    rs.getInt(veslackiKlub.alias() + ".id"),
                    rs.getString(veslackiKlub.alias() + ".naziv"),
                    rs.getString(veslackiKlub.alias() + ".adresa"),
                    rs.getString(veslackiKlub.alias() + ".email"),
                    rs.getString(veslackiKlub.alias() + ".telefon"),
                    rs.getString(veslackiKlub.alias() + ".korisnicko_ime"),
                    rs.getString(veslackiKlub.alias() + ".sifra")
            );

            Agencija ag = new Agencija(
                    rs.getInt(agencija.alias() + ".id"),
                    rs.getString(agencija.alias() + ".naziv"),
                    rs.getString(agencija.alias() + ".email"),
                    rs.getString(agencija.alias() + ".telefon"),
                    rs.getString(agencija.alias() + ".korisnicko_ime"),
                    rs.getString(agencija.alias() + ".sifra"),
                    null);

            if (!ponudeMap.keySet().contains(rs.getInt(alias() + ".id"))) {

                ponuda.setId(rs.getInt(alias() + ".id"));
                ponudeMap.put(ponuda.getId(), ponuda);
                ponuda.setDatumKreiranja(rs.getDate(alias() + ".datum_kreiranja"));
                ponuda.setBrojKadeta(rs.getInt(alias() + ".broj_kadeta"));
                ponuda.setBrojJuniora(rs.getInt(alias() + ".broj_juniora"));
                ponuda.setProsecnoVremeKadeti(rs.getFloat(alias() + ".prosecno_vreme_kadeti"));
                ponuda.setProsecnoVremeJuniori(rs.getFloat(alias() + ".prosecno_vreme_junior"));

                ponuda.setVeslackiKlub(vk);
                ponuda.setAgencija(ag);

                ponude.add(ponuda);

            }
            
            StavkaPonude stavka = new StavkaPonude();
            stavka.setPonudaVeslaca(ponudeMap.get((Integer) rs.getInt(alias() + ".id")));
            stavka.setGodineTreniranja(rs.getInt(stavka.alias() + ".godine_treniranja"));
            stavka.setRb(rs.getInt(stavka.alias() + ".rb"));

            Veslac veslac = new Veslac();

            veslac = new Veslac(
                    rs.getInt(veslac.alias() + ".id"),
                    rs.getString(veslac.alias() + ".ime_prezime"),
                    new Date(rs.getDate(veslac.alias() + ".datum_rodjenja").getTime()),
                    rs.getFloat(veslac.alias() + ".visina"),
                    rs.getFloat(veslac.alias() + ".tezina"),
                    KategorijaVeslaca.valueOf(rs.getString(veslac.alias() + ".kategorija").toUpperCase()),
                    rs.getFloat(veslac.alias() + ".najbolje_vreme"),
                    new Date(rs.getDate(veslac.alias() + ".datum_upisa").getTime()),
                    vk
            );
            stavka.setVeslac(veslac);
            ponudeMap.get((Integer) rs.getInt(alias() + ".id")).getStavke().add(stavka);

        }

        return ponude;
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "id_ponude = " + id;
    }

    @Override
    public String join() {
        return " JOIN veslacki_klub AS " + veslackiKlub.alias() + " ON " + alias() + ".id_kluba = " + veslackiKlub.alias() + ".id"
                + " JOIN agencija AS " + agencija.alias() + " ON " + alias() + ".id_agencije = " + agencija.alias() + ".id"
                + " JOIN stavka_ponude AS SP ON " + alias() + ".id = SP.id_ponude";
    }

    @Override
    public String alias() {
        return "P";
    }

    @Override
    public void postaviPrimarniKljuc(int id) {
        this.id = id;
    }
}
