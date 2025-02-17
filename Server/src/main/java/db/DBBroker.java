package db;

import controller.Controller;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import model.KategorijaVeslaca;
import model.Takmicenje;
import model.VeslackiKlub;
import model.VrstaTrke;
import io.github.cdimascio.dotenv.Dotenv;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import model.Agencija;
import model.Drzava;
import model.KlubTakmicenje;
import model.Nalog;
import model.PonudaVeslaca;
import model.StavkaPonude;
import model.Veslac;

public class DBBroker {

    private static DBBroker instance;

    private Dotenv dotenv = Dotenv.load();

    private DBBroker() {

    }

    public static DBBroker getInstance() {
        if (instance == null) {
            instance = new DBBroker();
        }
        return instance;
    }

    public VeslackiKlub vratiVeslackiKlubPoIdDB(Integer id) throws Exception {

        VeslackiKlub klub = new VeslackiKlub();

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/veslanje",
                dotenv.get("MYSQL_USER"),
                dotenv.get("MYSQL_PASS"));

        String upit = "SELECT * FROM `veslanje`.`veslacki_klub` WHERE id=?;";
        PreparedStatement statement = connection.prepareStatement(upit);

        statement.setInt(1, id);

        ResultSet rs = statement.executeQuery();
        if (rs.next()) {

            klub.setId(rs.getInt("id"));
            klub.setNaziv(rs.getString("naziv"));
            klub.setAdresa(rs.getString("adresa"));
            klub.setEmail(rs.getString("email"));
            klub.setTelefon(rs.getString("telefon"));
            klub.setKorisnickoIme(rs.getString("korisnicko_ime"));
            klub.setSifra(rs.getString("sifra"));

            return klub;
        }

        return null;
    }

    public Nalog pretraziVeslackiKlubLogin(Nalog nalog) throws Exception {

        VeslackiKlub klub = new VeslackiKlub();

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/veslanje",
                dotenv.get("MYSQL_USER"),
                dotenv.get("MYSQL_PASS"));

        String upit = "SELECT * FROM `veslanje`.`veslacki_klub` WHERE korisnicko_ime=? AND sifra=?";
        PreparedStatement statement = connection.prepareStatement(upit);

        statement.setString(1, nalog.getKorisnicko_ime());
        statement.setString(2, nalog.getSifra());

        ResultSet rs = statement.executeQuery();

        if (rs.next()) {
            klub.setId(rs.getInt("id"));
            klub.setNaziv(rs.getString("naziv"));
            klub.setAdresa(rs.getString("adresa"));
            klub.setEmail(rs.getString("email"));
            klub.setTelefon(rs.getString("telefon"));
            klub.setKorisnickoIme(rs.getString("korisnicko_ime"));
            klub.setSifra(rs.getString("sifra"));

            return klub;

        }

        return null;

    }

    public VeslackiKlub kreirajVeslackiKlubUBazi(VeslackiKlub klub) throws Exception {

        VeslackiKlub kreiraniKlub = null;

        int brPromenjenihRedova = 0;

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/veslanje",
                dotenv.get("MYSQL_USER"),
                dotenv.get("MYSQL_PASS"));

        String upit = "INSERT INTO `veslanje`.`veslacki_klub` (naziv,adresa,email,telefon,korisnicko_ime,sifra) VALUES(?,?,?,?,?,?);";
        PreparedStatement statement = connection.prepareStatement(upit, Statement.RETURN_GENERATED_KEYS);

        statement.setString(1, klub.getNaziv());
        statement.setString(2, klub.getAdresa());
        statement.setString(3, klub.getEmail());
        statement.setString(4, klub.getTelefon());
        statement.setString(5, klub.getKorisnickoIme());
        statement.setString(6, klub.getSifra());

        brPromenjenihRedova = statement.executeUpdate();

        if (brPromenjenihRedova > 0) {

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                kreiraniKlub = vratiVeslackiKlubPoIdDB(generatedKeys.getInt(1));
            }

        }

        return kreiraniKlub;

    }

    public Veslac kreirajVeslacaUBazi(Veslac veslac) throws Exception {

        Veslac kreiranVeslac = null;
        Connection connection = null;
        int idVeslaca = 0;

        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/veslanje",
                dotenv.get("MYSQL_USER"),
                dotenv.get("MYSQL_PASS"));
        connection.setAutoCommit(false);

        String upit = "INSERT INTO `veslanje`.`veslac` (ime_prezime,datum_rodjenja,visina,tezina,kategorija, datum_upisa, najbolje_vreme,BMI,id_kluba) VALUES(?,?,?,?,?,?,?,?,?);";
        PreparedStatement statement = connection.prepareStatement(upit, Statement.RETURN_GENERATED_KEYS);

        statement.setString(1, veslac.getImePrezime());

        java.sql.Date datumRodjenjaSQL = new Date(veslac.getDatumRodjenja().getTime());
        statement.setDate(2, datumRodjenjaSQL);

        statement.setFloat(3, veslac.getVisina());
        statement.setFloat(4, veslac.getTezina());
        statement.setString(5, veslac.getKategorija().toString());

        java.sql.Date datumUpisaSQL = new Date(veslac.getDatumUpisa().getTime());
        statement.setDate(6, datumUpisaSQL);

        statement.setFloat(7, veslac.getNajboljeVreme());

        float BMI = veslac.getTezina() / (veslac.getVisina() * veslac.getVisina());

        statement.setFloat(8, BMI);
        statement.setInt(9, veslac.getIdKluba());

        statement.executeUpdate();

        ResultSet rs = statement.getGeneratedKeys();
        if (rs.next()) {
            idVeslaca = rs.getInt(1);
        }

        connection.commit();

        kreiranVeslac = new Veslac(idVeslaca, veslac.getImePrezime(), veslac.getDatumRodjenja(), veslac.getVisina(),
                veslac.getTezina(), veslac.getKategorija(), veslac.getNajboljeVreme(), veslac.getDatumUpisa(), veslac.getIdKluba());

        return kreiranVeslac;

    }

    public List<Takmicenje> vratiTakmicenjaIzBaze() {

        List<Takmicenje> vracenaTakmicenja = new LinkedList<>();

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/veslanje",
                dotenv.get("MYSQL_USER"),
                dotenv.get("MYSQL_PASS"))) {

            String upit = "SELECT * FROM `veslanje`.`takmicenje`;";
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(upit);

            while (rs.next()) {
                Takmicenje takmicenje = new Takmicenje();

                takmicenje.setId(rs.getInt("id"));
                takmicenje.setNaziv(rs.getString("naziv"));

                if (rs.getString("starosna_kategorija").equalsIgnoreCase("Kadet")) {
                    takmicenje.setStarosnaKategorija(KategorijaVeslaca.KADET);
                } else {
                    takmicenje.setStarosnaKategorija(KategorijaVeslaca.JUNIOR);
                }

                switch (rs.getString("vrsta_trke")) {
                    case "Skif" ->
                        takmicenje.setVrstaTrke(VrstaTrke.SKIF);
                    case "Dubl" ->
                        takmicenje.setVrstaTrke(VrstaTrke.DUBL);
                    case "Dvojac" ->
                        takmicenje.setVrstaTrke(VrstaTrke.DVOJAC);
                    case "Cetverac skul" ->
                        takmicenje.setVrstaTrke(VrstaTrke.CETVERAC_SKUL);
                    case "Cetverac rimen" ->
                        takmicenje.setVrstaTrke(VrstaTrke.CETVERAC_RIMEN);
                    case "Osmerac" ->
                        takmicenje.setVrstaTrke(VrstaTrke.OSMERAC);
                    default ->
                        throw new AssertionError();
                }

                java.util.Date datumUtil = new Date(rs.getDate("datum").getTime());
                takmicenje.setDatum(datumUtil);

                vracenaTakmicenja.add(takmicenje);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return vracenaTakmicenja;

    }

    public VeslackiKlub azurirajVeslackiKlubUBazi(VeslackiKlub klub) throws Exception {

        VeslackiKlub azuriraniKlub = null;

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/veslanje",
                dotenv.get("MYSQL_USER"),
                dotenv.get("MYSQL_PASS"));

        connection.setAutoCommit(false);
        String upit = "UPDATE `veslanje`.`veslacki_klub` SET naziv=?, adresa=?, email=?, telefon=?, korisnicko_ime=?, sifra=? WHERE id=?";

        PreparedStatement statement = connection.prepareStatement(upit, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, klub.getNaziv());
        statement.setString(2, klub.getAdresa());
        statement.setString(3, klub.getEmail());
        statement.setString(4, klub.getTelefon());
        statement.setString(5, klub.getKorisnickoIme());
        statement.setString(6, klub.getKorisnickoIme());
        statement.setInt(7, klub.getId());

        int brRedova = statement.executeUpdate();

        if (brRedova > 0) {
            connection.commit();

            azuriraniKlub = vratiVeslackiKlubPoIdDB(klub.getId());

        } else {
            connection.rollback();
        }

        return azuriraniKlub;

    }

    public Integer obrisiVeslackiKlubIzBaze(Integer id) throws Exception {

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/veslanje",
                dotenv.get("MYSQL_USER"),
                dotenv.get("MYSQL_PASS"));
        connection.setAutoCommit(false);

        String upit = "DELETE FROM `veslanje`.`veslacki_klub` WHERE id=?;";
        PreparedStatement statement = connection.prepareStatement(upit, Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, id);

        int brRedova = statement.executeUpdate();
        if (brRedova > 0) {
            connection.commit();

            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }

        } else {
            connection.rollback();
        }

        return 0;
    }

    public List<Veslac> vratiSveVeslaceDB(int idKluba) {

        List<Veslac> veslaci = new LinkedList<>();

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/veslanje",
                dotenv.get("MYSQL_USER"),
                dotenv.get("MYSQL_PASS"))) {

            String upit = "SELECT * FROM `veslanje`.`veslac` WHERE id_kluba=?;";
            PreparedStatement ps = connection.prepareStatement(upit);
            ps.setInt(1, Controller.getInstance().getUlogovaniNalog().getId());

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Veslac v = new Veslac();
                v.setIdVeslaca(rs.getInt("id"));
                v.setImePrezime(rs.getString("ime_prezime"));
                v.setDatumRodjenja(new java.util.Date(rs.getDate("datum_rodjenja").getTime()));
                v.setVisina(rs.getFloat("visina"));
                v.setTezina(rs.getFloat("tezina"));
                v.setBMI(rs.getFloat("BMI"));
                v.setNajboljeVreme(rs.getFloat("najbolje_vreme"));
                v.setKategorija(KategorijaVeslaca.valueOf(rs.getString("kategorija")));
                v.setDatumUpisa(new java.util.Date(rs.getDate("datum_upisa").getTime()));

                veslaci.add(v);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return veslaci;
    }

    public Integer obrisiVeslacaIzBaze(int id) throws Exception {

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/veslanje",
                dotenv.get("MYSQL_USER"),
                dotenv.get("MYSQL_PASS"));

        connection.setAutoCommit(false);

        String upit = "DELETE FROM `veslanje`.`veslac` WHERE id=?;";
        PreparedStatement statement = connection.prepareStatement(upit, Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, id);

        int brRedova = statement.executeUpdate();
        if (brRedova > 0) {
            connection.commit();

            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }

        } else {
            connection.rollback();
        }
        return 0;
    }

    public Veslac azurirajVeslacaUBazi(Veslac veslac) throws Exception {

        Connection connection = null;
        Veslac azuriraniVeslac = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/veslanje", dotenv.get("MYSQL_USER"), dotenv.get("MYSQL_PASS"));
            connection.setAutoCommit(false);

            String upit = "UPDATE `veslanje`.`veslac` SET ime_prezime=?, datum_rodjenja=?, visina=?, tezina=?, kategorija=?, BMI=?, najbolje_vreme=?, datum_upisa=?,id_kluba=? WHERE id=?;";
            PreparedStatement ps = connection.prepareStatement(upit);
            ps.setString(1, veslac.getImePrezime());

            java.sql.Date datum_rodjenja = new java.sql.Date(veslac.getDatumRodjenja().getTime());
            ps.setDate(2, datum_rodjenja);

            ps.setFloat(3, veslac.getVisina());
            ps.setFloat(4, veslac.getTezina());

            ps.setString(5, veslac.getKategorija().toString());
            ps.setFloat(6, veslac.getBMI());
            ps.setFloat(7, veslac.getNajboljeVreme());

            java.sql.Date datum_upisa = new java.sql.Date(veslac.getDatumUpisa().getTime());
            ps.setDate(8, datum_upisa);

            ps.setInt(9, veslac.getIdKluba());

            ps.setInt(10, veslac.getIdVeslaca());

            ps.executeUpdate();
            connection.commit();
            azuriraniVeslac = vratiVeslacaPoId(veslac.getIdVeslaca());
        } catch (SQLException ex) {
            connection.rollback();
            throw new Exception(ex);

        } finally {
            connection.close();
        }

        return veslac;

    }

    public Agencija kreirajAgencijuUBazi(Agencija agencija) throws Exception {

        Agencija kreiranaAgencija = null;
        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/veslanje", dotenv.get("MYSQL_USER"), dotenv.get("MYSQL_PASS"));

            String upit = "INSERT INTO `veslanje`.`agencija`(naziv,email,telefon,korisnicko_ime,sifra,id_drzave) VALUES (?,?,?,?,?,?);";
            PreparedStatement statement = connection.prepareStatement(upit, Statement.RETURN_GENERATED_KEYS);
            connection.setAutoCommit(false);

            statement.setString(1, agencija.getNaziv());
            statement.setString(2, agencija.getEmail());
            statement.setString(3, agencija.getTelefon());
            statement.setString(4, agencija.getKorisnickoIme());
            statement.setString(5, agencija.getSifra());
            statement.setInt(6, agencija.getDrzava().getId());

            statement.executeUpdate();
            connection.commit();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                kreiranaAgencija = vratiAgencijuPoId(generatedKeys.getInt(1));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            connection.rollback();
            throw new Exception(ex);
        }
        return kreiranaAgencija;
    }

    public Agencija vratiAgencijuPoId(int id) {

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/veslanje", dotenv.get("MYSQL_USER"), dotenv.get("MYSQL_PASS"))) {

            String upit = "SELECT * FROM `veslanje`.`agencija` WHERE id = ?;";
            PreparedStatement statement = connection.prepareStatement(upit);

            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                Agencija agencija = new Agencija();
                agencija.setId(id);
                agencija.setNaziv(rs.getString("naziv"));
                agencija.setEmail(rs.getString("email"));
                agencija.setTelefon(rs.getString("telefon"));
                agencija.setKorisnickoIme(rs.getString("korisnicko_ime"));
                agencija.setSifra(rs.getString("sifra"));
                Drzava drzava = vratiDrzavuPoId(rs.getInt("id_drzave"));
                agencija.setDrzava(drzava);
                return agencija;

            }

        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return null;
    }

    public Integer obrisiAgencijuIzBaze(Integer id) throws Exception {

        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/veslanje",
                    dotenv.get("MYSQL_USER"),
                    dotenv.get("MYSQL_PASS"));

            connection.setAutoCommit(false);

            String upit = "DELETE FROM `veslanje`.`agencija` WHERE id=?;";
            PreparedStatement statement = connection.prepareStatement(upit);
            statement.setInt(1, id);

            statement.executeUpdate();
            connection.commit();

            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            connection.rollback();
            throw new Exception(ex);
        } finally {
            connection.close();
        }

        return 0;
    }

    public List<Drzava> vratiSveDrzaveIzBaze() {

        List<Drzava> drzave = new LinkedList<>();

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/veslanje",
                dotenv.get("MYSQL_USER"),
                dotenv.get("MYSQL_PASS"))) {

            String upit = "SELECT * FROM `veslanje`.`drzava`;";
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(upit);

            while (rs.next()) {

                Drzava d = new Drzava();
                d.setId(rs.getInt("id"));
                d.setNaziv(rs.getString("naziv"));

                drzave.add(d);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return drzave;

    }

    public Nalog pretraziAgencijuLogin(Nalog nalog) {

        Agencija agencija = new Agencija();

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/veslanje",
                dotenv.get("MYSQL_USER"),
                dotenv.get("MYSQL_PASS"))) {

            String upit = "SELECT * FROM `veslanje`.`agencija` WHERE korisnicko_ime=? AND sifra=?";
            PreparedStatement statement = connection.prepareStatement(upit);

            statement.setString(1, nalog.getKorisnicko_ime());
            statement.setString(2, nalog.getSifra());

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                agencija.setId(rs.getInt("id"));
                agencija.setNaziv(rs.getString("naziv"));
                agencija.setEmail(rs.getString("email"));
                agencija.setTelefon(rs.getString("telefon"));
                agencija.setKorisnickoIme(rs.getString("korisnicko_ime"));
                agencija.setSifra(rs.getString("sifra"));

                Drzava drzava = vratiDrzavuPoId(rs.getInt("id_drzave"));
                agencija.setDrzava(drzava);

                return agencija;

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public Takmicenje dodajTakmicenjeDB(Takmicenje takmicenje) throws Exception {

        Takmicenje kreiranoTakmicenje = null;
        Connection connection = null;
        try {

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/veslanje",
                    dotenv.get("MYSQL_USER"),
                    dotenv.get("MYSQL_PASS"));

            connection.setAutoCommit(false);

            String upit1 = "INSERT INTO `veslanje`.`takmicenje`(naziv,starosna_kategorija,vrsta_trke,datum) VALUES (?,?,?,?)";
            PreparedStatement ps1 = connection.prepareStatement(upit1, Statement.RETURN_GENERATED_KEYS);
            ps1.setString(1, takmicenje.getNaziv());
            ps1.setString(2, takmicenje.getStarosnaKategorija().toString());
            ps1.setString(3, takmicenje.getVrstaTrke().toString());
            ps1.setDate(4, new Date(takmicenje.getDatum().getTime()));

            ps1.executeUpdate();
            connection.commit();

            ResultSet rs = ps1.getGeneratedKeys();
            if (rs.next()) {
                kreiranoTakmicenje = new Takmicenje(rs.getInt(1), takmicenje.getNaziv(), takmicenje.getStarosnaKategorija(), takmicenje.getVrstaTrke(), takmicenje.getDatum());
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            connection.close();
        }

        return kreiranoTakmicenje;
    }

    public List<KlubTakmicenje> vratiTakmicenjaKlubaDB(int idKluba) throws Exception {

        List<KlubTakmicenje> osvojenaTakmicenja = new LinkedList<>();

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/veslanje",
                dotenv.get("MYSQL_USER"),
                dotenv.get("MYSQL_PASS"))) {

            String upit = "SELECT * FROM `veslanje`.`takmicenje` as T JOIN `veslanje`.`klub_takmicenje` as KT  ON T.id=KT.id_takmicenja "
                    + "JOIN `veslanje`.`veslacki_klub` VK ON KT.id_kluba=VK.id WHERE VK.id=?";
            PreparedStatement ps = connection.prepareStatement(upit);
            ps.setInt(1, idKluba);
            ResultSet rs = ps.executeQuery();
            
            VeslackiKlub vk = vratiVeslackiKlubPoIdDB(idKluba);
            System.out.println("DB: " + vk);
            while (rs.next()) {

                Takmicenje t = new Takmicenje();
                t.setId(rs.getInt("id"));
                t.setNaziv(rs.getString("naziv"));
                t.setStarosnaKategorija(KategorijaVeslaca.valueOf(rs.getString("starosna_kategorija")));
                t.setVrstaTrke(VrstaTrke.valueOf(rs.getString("vrsta_trke")));
                t.setDatum(new java.util.Date(rs.getDate("datum").getTime()));

                KlubTakmicenje kt = new KlubTakmicenje();
                kt.setKlub(vk);
                kt.setTakmicenje(t);
                kt.setMesto(rs.getInt("mesto"));

                osvojenaTakmicenja.add(kt);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return osvojenaTakmicenja;
    }

    public Integer obrisiTakmicenjeDB(Integer idTakmicenja) throws Exception {

        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/veslanje",
                    dotenv.get("MYSQL_USER"),
                    dotenv.get("MYSQL_PASS"));
            connection.setAutoCommit(false);

            String upit = "DELETE T FROM  `veslanje`.`takmicenje` as T JOIN `veslanje`.`klub_takmicenje` KT ON T.id=KT.id_takmicenja WHERE KT.id_kluba = ? AND T.id = ?;";

            int idKluba = Controller.getInstance().getUlogovaniNalog().getId();
            PreparedStatement ps = connection.prepareStatement(upit, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, idKluba);
            ps.setInt(2, idTakmicenja);

            ps.executeUpdate();
            connection.commit();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            connection.rollback();
        } finally {
            connection.close();
        }

        return 0;

    }

    public List<Takmicenje> vratiSvaTakmicenjaDB() {

        Connection connection = null;
        List<Takmicenje> svaTakmicenja = new LinkedList<>();

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/veslanje", dotenv.get("MYSQL_USER"), dotenv.get("MYSQL_PASS"));
            String upit = "SELECT * FROM `veslanje`.`takmicenje` ORDER BY id DESC;";
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(upit);

            while (rs.next()) {

                Takmicenje t = new Takmicenje();
                t.setId(rs.getInt("id"));
                t.setNaziv(rs.getString("naziv"));
                t.setStarosnaKategorija(KategorijaVeslaca.valueOf(rs.getString("starosna_kategorija")));
                t.setVrstaTrke(VrstaTrke.valueOf(rs.getString("vrsta_trke")));
                t.setDatum(new java.util.Date(rs.getDate("datum").getTime()));

                svaTakmicenja.add(t);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return svaTakmicenja;

    }

    public KlubTakmicenje dodajOsvojenoTakmicenjeDB(KlubTakmicenje klubTakmicenje) throws Exception {

        KlubTakmicenje osvojenoTakmicenje = null;
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/veslanje", dotenv.get("MYSQL_USER"), dotenv.get("MYSQL_PASS"));
            connection.setAutoCommit(false);

            String upitProvera = "SELECT * FROM `veslanje`.`klub_takmicenje` WHERE mesto=? AND id_takmicenja=?";
            PreparedStatement ps = connection.prepareStatement(upitProvera);
            ps.setInt(1, klubTakmicenje.getMesto());
            ps.setInt(2, klubTakmicenje.getTakmicenje().getId());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                connection.rollback();
                throw new Exception("Vec je zauzeto to mesto");

            } else {

                String upit = "INSERT INTO `veslanje`.`klub_takmicenje` (mesto,id_takmicenja,id_kluba) VALUES (?,?,?);";
                PreparedStatement ps2 = connection.prepareStatement(upit);
                ps2.setInt(1, klubTakmicenje.getMesto());
                ps2.setInt(2, klubTakmicenje.getTakmicenje().getId());
                ps2.setInt(3, klubTakmicenje.getKlub().getId());

                ps2.executeUpdate();
                connection.commit();

                VeslackiKlub klub = vratiVeslackiKlubPoIdDB(klubTakmicenje.getKlub().getId());
                Takmicenje takmicenje = vratiTakmicenjePoIdDB(klubTakmicenje.getTakmicenje().getId());
                osvojenoTakmicenje = new KlubTakmicenje(klubTakmicenje.getMesto(), klub, takmicenje);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception(ex);
        } finally {
            connection.close();
        }

        return osvojenoTakmicenje;

    }

    public int[] prebrojOsvojenaTakmicenjaDB(int idKluba) {

        int[] brMesta = new int[3];

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/veslanje", dotenv.get("MYSQL_USER"),
                dotenv.get("MYSQL_PASS"))) {

            String upit = "SELECT SUM(CASE WHEN mesto=1 THEN 1 ELSE 0 END) as prvo,\n"
                    + "SUM(CASE WHEN mesto=2 THEN 1 ELSE 0 END) as drugo,\n"
                    + "SUM(CASE WHEN mesto=3 THEN 1 ELSE 0 END) as trece\n"
                    + "FROM `veslanje`.`klub_takmicenje`\n"
                    + "WHERE id_kluba = ?;";

            PreparedStatement ps = connection.prepareStatement(upit);
            ps.setInt(1, idKluba);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                brMesta[0] = rs.getInt("prvo");
                brMesta[1] = rs.getInt("drugo");
                brMesta[2] = rs.getInt("trece");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return brMesta;
    }

    public Integer obrisiOsvojenoTakmicenje(KlubTakmicenje klubTakmicenje) throws Exception {

        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/veslanje", dotenv.get("MYSQL_USER"), dotenv.get("MYSQL_PASS"));
            connection.setAutoCommit(false);

            String upit = "DELETE FROM `veslanje`.`klub_takmicenje` WHERE id_kluba=? AND id_takmicenja=? AND mesto=?";
            PreparedStatement ps = connection.prepareStatement(upit, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, klubTakmicenje.getKlub().getId());
            ps.setInt(2, klubTakmicenje.getTakmicenje().getId());
            ps.setInt(3, klubTakmicenje.getMesto());

            ps.executeUpdate();
            connection.commit();

            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            connection.rollback();
            throw new Exception(ex);
        } finally {
            connection.close();
        }

        return 0;
    }

    public Veslac vratiVeslacaPoId(int idVeslaca) {

        Veslac v = null;

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/veslanje",
                dotenv.get("MYSQL_USER"), dotenv.get("MYSQL_PASS"))) {

            String upit = "SELECT * FROM `veslanje`.`veslac` WHERE id=?";
            PreparedStatement ps = connection.prepareStatement(upit);
            ps.setInt(1, idVeslaca);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                v = new Veslac();
                v.setIdVeslaca(rs.getInt("id"));
                v.setImePrezime(rs.getString("ime_prezime"));
                v.setDatumRodjenja(new java.util.Date(rs.getDate("datum_rodjenja").getTime()));
                v.setVisina(rs.getFloat("visina"));
                v.setTezina(rs.getFloat("tezina"));
                v.setBMI(rs.getFloat("BMI"));
                v.setNajboljeVreme(rs.getFloat("najbolje_vreme"));
                v.setKategorija(KategorijaVeslaca.valueOf(rs.getString("kategorija")));
                v.setDatumUpisa(new java.util.Date(rs.getDate("datum_upisa").getTime()));

            }

        } catch (SQLException ex) {

        }

        return v;

    }

    public int vratiPoslednjiIdPonudeDB() {

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/veslanje",
                dotenv.get("MYSQL_USER"), dotenv.get("MYSQL_PASS"))) {

            String upit = "SELECT id from `veslanje`.`ponuda_veslaca` ORDER BY id DESC LIMIT 1";
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(upit);

            if (rs.next()) {

                return rs.getInt("id");

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public PonudaVeslaca kreirajPonuduVeslacaDB(PonudaVeslaca ponuda) throws Exception {

        Connection connection = null;
        PonudaVeslaca kreiranaPonuda = null;

        try {

            int idPonude;
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306", dotenv.get("MYSQL_USER"), dotenv.get("MYSQL_PASS"));
                connection.setAutoCommit(false);

                String upit = "INSERT INTO `veslanje`.`ponuda_veslaca` (datum_kreiranja,broj_kadeta,broj_juniora,prosecno_vreme_kadeti,prosecno_vreme_junior,id_kluba,id_agencije) "
                        + "VALUES (?,0,0,0,0,?,?)";

                PreparedStatement ps = connection.prepareStatement(upit, Statement.RETURN_GENERATED_KEYS);
                ps.setDate(1, new Date(ponuda.getDatumKreiranja().getTime()));
                ps.setInt(2, ponuda.getIdKluba());
                ps.setInt(3, ponuda.getIdAgencije());

                ps.executeUpdate();

                ResultSet kljuc = ps.getGeneratedKeys();

                if (kljuc.next()) {
                    idPonude = kljuc.getInt(1);
                } else {
                    throw new Exception("Nije vracen generisani id");
                }

                connection.commit();

            } catch (Exception ex) {
                connection.rollback();
                ex.printStackTrace();
                throw new Exception("Greska pri kreiranju ponude");
            }

            try {
                String upit_stavke = "INSERT INTO `veslanje`.`stavka_ponude` (id_ponude,rb,godine_treniranja,id_veslaca) VALUES(?,?,?,?)";
                PreparedStatement ps2 = connection.prepareStatement(upit_stavke);

                for (StavkaPonude stavka : ponuda.getStavke()) {

                    ps2.setInt(1, idPonude);
                    ps2.setInt(2, stavka.getRb());
                    ps2.setInt(3, stavka.getGodineTreniranja());
                    ps2.setInt(4, stavka.getVeslac().getIdVeslaca());

                    ps2.addBatch();

                }

                ps2.executeBatch();
                connection.commit();

            } catch (Exception ex) {
                connection.rollback();
                ex.printStackTrace();
                String upit = "DELETE FROM `veslanje`.`ponuda_veslaca` WHERE id=?";
                PreparedStatement ps = connection.prepareStatement(upit);
                ps.setInt(1, idPonude);

                ps.executeUpdate();
                connection.commit();

                throw new Exception("Greska pri kreiranju stavki");
            }

            try {

                int brojKadet = prebrojVeslace(idPonude, "KADET", ponuda.getIdKluba(), ponuda.getIdAgencije());
                int brojJuniora = prebrojVeslace(idPonude, "JUNIOR", ponuda.getIdKluba(), ponuda.getIdAgencije());

                float prosecnoVremeKadeti = prosecnoVremeVeslaci(idPonude, "KADET", ponuda.getIdKluba(), ponuda.getIdAgencije());
                float prosecnoVremeJuniori = prosecnoVremeVeslaci(idPonude, "JUNIOR", ponuda.getIdKluba(), ponuda.getIdAgencije());

                String upit_azuriranje = "UPDATE `veslanje`.`ponuda_veslaca` SET broj_kadeta=?, broj_juniora=?, prosecno_vreme_kadeti=?, prosecno_vreme_junior=? WHERE id=?";
                PreparedStatement ps = connection.prepareStatement(upit_azuriranje);
                ps.setInt(1, brojKadet);
                ps.setInt(2, brojJuniora);
                ps.setFloat(3, prosecnoVremeKadeti);
                ps.setFloat(4, prosecnoVremeJuniori);
                ps.setInt(5, idPonude);

                ps.executeUpdate();

                connection.commit();

                kreiranaPonuda = new PonudaVeslaca(idPonude, ponuda.getDatumKreiranja(), brojKadet, brojJuniora, prosecnoVremeKadeti, prosecnoVremeJuniori, ponuda.getStavke(), ponuda.getIdKluba(), ponuda.getIdAgencije());

            } catch (Exception ex) {
                connection.rollback();
                ex.printStackTrace();
                //Obrisace se i stavke zbog CASACADE
                String upit = "DELETE FROM `veslanje`.`ponuda_veslaca` WHERE id=?";
                PreparedStatement ps = connection.prepareStatement(upit);
                ps.setInt(1, idPonude);

                ps.executeUpdate();
                connection.commit();

                throw new Exception("Nije upesno azuriranje ponuda veslaca" + ex);
            }

        } catch (Exception ex) {
            throw new Exception(ex);
        } finally {
            connection.close();
        }

        return kreiranaPonuda;

    }

    public int prebrojVeslace(int idPonude, String tipVeslaca, int idKluba, int idAgencije) {

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/veslanje", dotenv.get("MYSQL_USER"), dotenv.get("MYSQL_PASS"))) {

            String upit = "SELECT COUNT(*) FROM `veslanje`.`stavka_ponude` SP JOIN  `veslanje`.`veslac` V ON SP.id_veslaca = V.id JOIN `veslanje`.`ponuda_veslaca` P ON SP.id_ponude=P.id"
                    + " WHERE SP.id_ponude=? AND V.kategorija=? AND P.id_kluba=? AND P.id_agencije=?;";

            PreparedStatement ps = connection.prepareStatement(upit);
            ps.setInt(1, idPonude);
            ps.setString(2, tipVeslaca);
            ps.setInt(3, idKluba);
            ps.setInt(4, idAgencije);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            } else {
                return 0;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public float prosecnoVremeVeslaci(int idPonude, String tipVeslaca, int idKluba, int idAgencije) {

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/veslanje", dotenv.get("MYSQL_USER"), dotenv.get("MYSQL_PASS"))) {

            String upit = "SELECT AVG(V.najbolje_vreme) FROM `veslanje`.`stavka_ponude` SP JOIN  `veslanje`.`veslac` V ON SP.id_veslaca = V.id JOIN `veslanje`.`ponuda_veslaca` P ON SP.id_ponude=P.id"
                    + " WHERE SP.id_ponude=? AND V.kategorija=? AND P.id_kluba=? AND P.id_agencije=?;";

            PreparedStatement ps = connection.prepareStatement(upit);
            ps.setInt(1, idPonude);
            ps.setString(2, tipVeslaca);
            ps.setInt(3, idKluba);
            ps.setInt(4, idAgencije);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getFloat(1);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public List<PonudaVeslaca> vratiSvePonudeKlubaDB(int idKluba) {

        List<PonudaVeslaca> ponude = new LinkedList<>();

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/veslanje",
                dotenv.get("MYSQL_USER"), dotenv.get("MYSQL_PASS"))) {

            String upit = "SELECT * FROM `veslanje`.`ponuda_veslaca` WHERE id_kluba=?";
            PreparedStatement ps = connection.prepareStatement(upit);
            ps.setInt(1, idKluba);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                PonudaVeslaca ponuda = new PonudaVeslaca();

                ponuda.setId(rs.getInt("id"));
                ponuda.setDatumKreiranja(new java.util.Date(rs.getDate("datum_kreiranja").getTime()));
                ponuda.setBrojKadeta(rs.getInt("broj_kadeta"));
                ponuda.setBrojJuniora(rs.getInt("broj_juniora"));

//                String vremeKadeti;
//
//                if (Math.round(rs.getFloat("prosecno_vreme_kadeti")) % 60 < 10) {
//                    vremeKadeti = (int) Math.floor(rs.getFloat("prosecno_vreme_kadeti") / 60) + ":0" + Math.round(rs.getFloat("prosecno_vreme_kadeti") % 60);
//                } else {
//                    vremeKadeti = (int) Math.floor(rs.getFloat("prosecno_vreme_kadeti") / 60) + ":" + Math.round(rs.getFloat("prosecno_vreme_kadeti") % 60);
//                }
//
//                String vremeJuniori;
//                if (Math.round(rs.getFloat("prosecno_vreme_junior") % 60) < 10) {
//                    vremeJuniori = (int) Math.floor(rs.getFloat("prosecno_vreme_junior") / 60) + ":0" + Math.round(rs.getFloat("prosecno_vreme_junior") % 60);
//                } else {
//                    vremeJuniori = (int) Math.floor(rs.getFloat("prosecno_vreme_junior") / 60) + ":" + Math.round(rs.getFloat("prosecno_vreme_junior") % 60);
//                }
                ponuda.setProsecnoVremeKadeti(rs.getFloat("prosecno_vreme_kadeti"));
                ponuda.setProsecnoVremeJuniori(rs.getFloat("prosecno_vreme_junior"));

                ponuda.setIdAgencije(rs.getInt("id_agencije"));
                ponuda.setIdKluba(rs.getInt("id_kluba"));

                ponude.add(ponuda);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return ponude;

    }

    public Integer obrisiPonuduDB(Integer idPonude) throws Exception {

        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/veslanje", dotenv.get("MYSQL_USER"), dotenv.get("MYSQL_PASS"));
            connection.setAutoCommit(false);
            String upit = "DELETE FROM `veslanje`.`ponuda_veslaca` WHERE id=?;";
            PreparedStatement ps = connection.prepareStatement(upit, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, idPonude);

            ps.executeUpdate();
            connection.commit();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException ex) {
            connection.rollback();
            throw new Exception("Greska pri brisanju ponude veslaca");
        } finally {
            connection.close();
        }

        return 0;
    }

    public List<PonudaVeslaca> pretraziPonuduKlubaDB(PonudaVeslaca ponuda) {

        List<PonudaVeslaca> ponude = new LinkedList<>();

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/veslanje",
                dotenv.get("MYSQL_USER"), dotenv.get("MYSQL_PASS"))) {

            String upit = "SELECT * FROM `veslanje`.`ponuda_veslaca` WHERE id_kluba=? AND id_agencije=?";
            PreparedStatement ps = connection.prepareStatement(upit);
            ps.setInt(1, ponuda.getIdKluba());
            ps.setInt(2, ponuda.getIdAgencije());

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                PonudaVeslaca pretrazenaPonuda = new PonudaVeslaca();
                pretrazenaPonuda.setId(rs.getInt("id"));
                pretrazenaPonuda.setDatumKreiranja(new java.util.Date(rs.getDate("datum_kreiranja").getTime()));
                pretrazenaPonuda.setBrojJuniora(rs.getInt("broj_juniora"));
                pretrazenaPonuda.setBrojKadeta(rs.getInt("broj_kadeta"));
                pretrazenaPonuda.setProsecnoVremeKadeti(rs.getFloat("prosecno_vreme_kadeti"));
                pretrazenaPonuda.setProsecnoVremeJuniori(rs.getFloat("prosecno_vreme_junior"));

                pretrazenaPonuda.setIdAgencije(rs.getInt("id_agencije"));
                pretrazenaPonuda.setIdKluba(rs.getInt("id_kluba"));

                ponude.add(pretrazenaPonuda);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return ponude;

    }

    public List<Veslac> pretraziVeslacaDB(Veslac veslac) {

        List<Veslac> veslaci = new LinkedList<>();

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/veslanje",
                dotenv.get("MYSQL_USER"), dotenv.get("MYSQL_PASS"))) {

            String upit;

            if (veslac.getIdKluba() == 0) {
                upit = "SELECT * FROM `veslanje`.`veslac` WHERE ime_prezime LIKE ?;";
            } else {
                upit = "SELECT * FROM `veslanje`.`veslac` WHERE ime_prezime LIKE ? AND id_kluba=?;";

            }

            String imePrezime = "%" + veslac.getImePrezime() + "%";
            PreparedStatement ps = connection.prepareStatement(upit);
            ps.setString(1, imePrezime);
            if (veslac.getIdKluba() != 0) {
                ps.setInt(2, veslac.getIdKluba());
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                
                Veslac pretrazenVeslac = new Veslac();
                pretrazenVeslac.setIdVeslaca(rs.getInt("id"));
                pretrazenVeslac.setDatumRodjenja(new java.util.Date(rs.getDate("datum_rodjenja").getTime()));
                pretrazenVeslac.setDatumUpisa(new java.util.Date(rs.getDate("datum_upisa").getTime()));
                pretrazenVeslac.setImePrezime(rs.getString("ime_prezime"));
                pretrazenVeslac.setKategorija(KategorijaVeslaca.valueOf(rs.getString("kategorija")));
//                float nbv = rs.getFloat("najbolje_vreme");
//                String najbolje_vreme;
//                if (nbv % 60 < 10) {
//                    najbolje_vreme = nbv / 60 + ":0" + nbv % 60;
//                } else {
//                    najbolje_vreme = nbv / 60 + ":" + nbv % 60;
//                }

                pretrazenVeslac.setNajboljeVreme(rs.getFloat("najbolje_vreme"));
                pretrazenVeslac.setBMI(rs.getFloat("BMI"));
                pretrazenVeslac.setTezina(rs.getFloat("tezina"));
                pretrazenVeslac.setVisina(rs.getFloat("visina"));
                pretrazenVeslac.setIdKluba(rs.getInt("id_kluba"));

                veslaci.add(pretrazenVeslac);
                System.out.println(pretrazenVeslac);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return veslaci;
    }

    public List<Takmicenje> pretraziTakmicenjaDB(String nazivTakmicenja) {

        List<Takmicenje> takmicenja = new LinkedList<>();

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/veslanje",
                dotenv.get("MYSQL_USER"), dotenv.get("MYSQL_PASS"))) {

            String upit = "SELECT * FROM `veslanje`.`takmicenje` WHERE naziv LIKE ?;";
            nazivTakmicenja = "%" + nazivTakmicenja + "%";

            PreparedStatement ps = connection.prepareStatement(upit);
            ps.setString(1, nazivTakmicenja);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Takmicenje t = new Takmicenje();
                t.setId(rs.getInt("id"));
                t.setNaziv(rs.getString("naziv"));
                t.setStarosnaKategorija(KategorijaVeslaca.valueOf(rs.getString("starosna_kategorija")));
                t.setVrstaTrke(VrstaTrke.valueOf(rs.getString("vrsta_trke")));
                t.setDatum(new java.util.Date(rs.getDate("datum").getTime()));

                takmicenja.add(t);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return takmicenja;
    }

    public List<PonudaVeslaca> vratiSvePonudeAgencijeDB(Integer idAgencije) {

        List<PonudaVeslaca> ponude = new LinkedList<>();

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/veslanje",
                dotenv.get("MYSQL_USER"), dotenv.get("MYSQL_PASS"))) {

            String upit = "SELECT * FROM `veslanje`.`ponuda_veslaca` WHERE id_agencije=?";
            PreparedStatement ps = connection.prepareStatement(upit);
            ps.setInt(1, idAgencije);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                PonudaVeslaca ponuda = new PonudaVeslaca();

                ponuda.setId(rs.getInt("id"));
                ponuda.setDatumKreiranja(new java.util.Date(rs.getDate("datum_kreiranja").getTime()));
                ponuda.setBrojKadeta(rs.getInt("broj_kadeta"));
                ponuda.setBrojJuniora(rs.getInt("broj_juniora"));

//                String vremeKadeti;
//
//                if (Math.round(rs.getFloat("prosecno_vreme_kadeti")) % 60 < 10) {
//                    vremeKadeti = (int) Math.floor(rs.getFloat("prosecno_vreme_kadeti") / 60) + ":0" + Math.round(rs.getFloat("prosecno_vreme_kadeti") % 60);
//                } else {
//                    vremeKadeti = (int) Math.floor(rs.getFloat("prosecno_vreme_kadeti") / 60) + ":" + Math.round(rs.getFloat("prosecno_vreme_kadeti") % 60);
//                }
//
//                String vremeJuniori;
//
//                if (Math.round(rs.getFloat("prosecno_vreme_junior") % 60) < 10) {
//                    vremeJuniori = (int) Math.floor(rs.getFloat("prosecno_vreme_junior") / 60) + ":0" + Math.round(rs.getFloat("prosecno_vreme_junior") % 60);
//                } else {
//                    vremeJuniori = (int) Math.floor(rs.getFloat("prosecno_vreme_junior") / 60) + ":" + Math.round(rs.getFloat("prosecno_vreme_junior") % 60);
//                }
                ponuda.setProsecnoVremeKadeti(rs.getFloat("prosecno_vreme_kadeti"));
                ponuda.setProsecnoVremeJuniori(rs.getFloat("prosecno_vreme_junior"));

                ponuda.setIdAgencije(rs.getInt("id_agencije"));
                ponuda.setIdKluba(rs.getInt("id_kluba"));

                ponude.add(ponuda);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return ponude;

    }

    public Agencija azurirajAgencijuDB(Agencija agencija) throws Exception {

        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/veslanje",
                    dotenv.get("MYSQL_USER"), dotenv.get("MYSQL_PASS"));
            connection.setAutoCommit(false);

            String upit = "UPDATE `veslanje`.`agencija` SET naziv=?, email=?, telefon=?, korisnicko_ime=?, sifra=?, id_drzave=? WHERE id=?;";

            PreparedStatement ps = connection.prepareStatement(upit, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, agencija.getNaziv());
            ps.setString(2, agencija.getEmail());
            ps.setString(3, agencija.getTelefon());
            ps.setString(4, agencija.getKorisnickoIme());
            ps.setString(5, agencija.getSifra());
            ps.setInt(6, agencija.getDrzava().getId());
            ps.setInt(7, agencija.getId());

            ps.executeUpdate();
            connection.commit();

            ResultSet rs = ps.getGeneratedKeys();
            return vratiAgencijuPoId(rs.getInt(1));

        } catch (SQLException ex) {
            ex.printStackTrace();
            connection.rollback();
            throw new Exception(ex);
        } finally {
            connection.close();
        }
    }

    public List<VeslackiKlub> vratiSveKluboveDB() {

        List<VeslackiKlub> klubovi = new LinkedList<>();

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/veslanje",
                dotenv.get("MYSQL_USER"), dotenv.get("MYSQL_PASS"))) {

            String upit = "SELECT * FROM `veslanje`.`veslacki_klub`;";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(upit);

            while (rs.next()) {

                VeslackiKlub klub = new VeslackiKlub();
                klub.setId(rs.getInt("id"));
                klub.setNaziv(rs.getString("naziv"));
                klub.setAdresa(rs.getString("adresa"));
                klub.setEmail(rs.getString("email"));
                klub.setTelefon(rs.getString("telefon"));
                klub.setKorisnickoIme(rs.getString("korisnicko_ime"));
                klub.setSifra(rs.getString("sifra"));

                klubovi.add(klub);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return klubovi;
    }

    public List<StavkaPonude> vratiSveStavkePonudeDB(int idPonude) {

        List<StavkaPonude> stavke = new LinkedList<>();

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/veslanje",
                dotenv.get("MYSQL_USER"), dotenv.get("MYSQL_PASS"))) {

            String upit = "SELECT * FROM `veslanje`.`stavka_ponude` WHERE id_ponude=?;";

            PreparedStatement ps = connection.prepareStatement(upit);
            ps.setInt(1, idPonude);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                StavkaPonude stavka = new StavkaPonude();
                stavka.setIdEvidencije(idPonude);
                stavka.setRb(rs.getInt("rb"));
                stavka.setGodineTreniranja(rs.getInt("godine_treniranja"));
                Veslac veslac = Controller.getInstance().vratiVeslacaPoId(rs.getInt("id_veslaca"));
                stavka.setVeslac(veslac);

                stavke.add(stavka);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return stavke;

    }

    public List<VeslackiKlub> pretraziKlubDB(String upitZaPretragu) {

        List<VeslackiKlub> pretrazeniKlubovi = new LinkedList<>();

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306", dotenv.get("MYSQL_USER"), dotenv.get("MYSQL_PASS"))) {

            upitZaPretragu = "%" + upitZaPretragu + "%";

            String upit = "SELECT * FROM `veslanje`.`veslacki_klub` WHERE naziv LIKE ?;";

            PreparedStatement ps = connection.prepareStatement(upit);
            ps.setString(1, upitZaPretragu);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                VeslackiKlub klub = new VeslackiKlub();
                klub.setId(rs.getInt("id"));
                klub.setNaziv(rs.getString("naziv"));
                klub.setAdresa(rs.getString("adresa"));
                klub.setEmail(rs.getString("email"));
                klub.setTelefon(rs.getString("telefon"));
                klub.setKorisnickoIme(rs.getString("korisnicko_ime"));
                klub.setSifra(rs.getString("sifra"));

                pretrazeniKlubovi.add(klub);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return pretrazeniKlubovi;

    }

    public Drzava vratiDrzavuPoId(int id) {

        Drzava drzava = null;

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306", dotenv.get("MYSQL_USER"), dotenv.get("MYSQL_PASS"))) {

            String upit = "SELECT * FROM `veslanje`.`drzava` WHERE id=?";
            PreparedStatement ps = connection.prepareStatement(upit);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                drzava = new Drzava();
                drzava.setId(rs.getInt("id"));
                drzava.setNaziv(rs.getString("naziv"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return drzava;
    }

    public List<Agencija> vratiSveAgencijeDB() {

        List<Agencija> agencije = new LinkedList<>();

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306", dotenv.get("MYSQL_USER"), dotenv.get("MYSQL_PASS"))) {

            String upit = "SELECT * FROM `veslanje`.`agencija`;";

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(upit);

            while (rs.next()) {
                Agencija agencija = new Agencija();
                agencija.setId(rs.getInt("id"));
                agencija.setNaziv(rs.getString("naziv"));
                agencija.setEmail(rs.getString("email"));
                agencija.setTelefon(rs.getString("telefon"));
                agencija.setKorisnickoIme(rs.getString("korisnicko_ime"));
                agencija.setSifra(rs.getString("sifra"));
                Drzava drzava = vratiDrzavuPoId(rs.getInt("id_drzave"));
                agencija.setDrzava(drzava);

                agencije.add(agencija);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return agencije;

    }

    public Takmicenje vratiTakmicenjePoIdDB(int idTakmicenja) {

        Takmicenje takmicenje = null;

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306",
                dotenv.get("MYSQL_USER"), dotenv.get("MYSQL_PASS"))) {

            String upit = "SELECT * FROM `veslanje`.`takmicenje` WHERE id=?;";
            PreparedStatement ps = connection.prepareStatement(upit);
            ps.setInt(1, idTakmicenja);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                takmicenje = new Takmicenje();
                takmicenje.setId(rs.getInt("id"));
                takmicenje.setNaziv(rs.getString("naziv"));
                takmicenje.setStarosnaKategorija(KategorijaVeslaca.valueOf(rs.getString("starosna_kategorija")));
                takmicenje.setVrstaTrke(VrstaTrke.valueOf(rs.getString("vrsta_trke")));
                takmicenje.setDatum(new java.util.Date(rs.getDate("datum").getTime()));

            }

        } catch (SQLException ex) {

        }

        return takmicenje;

    }

}
