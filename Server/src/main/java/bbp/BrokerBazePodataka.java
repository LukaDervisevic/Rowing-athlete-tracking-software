/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bbp;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import model.OpstiDomenskiObjekat;

/**
 *
 * @author lukad
 */
public class BrokerBazePodataka implements IBrokerBazePodataka {

    private final String imeBaze;
    private Connection konekcija;
    private final Dotenv dotenv = Dotenv.load();

    public BrokerBazePodataka(String imeBaze) {
        this.imeBaze = imeBaze;
    }

    @Override
    public boolean napraviKonekciju() {
        String url;
        try {
            url = "jdbc:mysql://localhost:3306/" + imeBaze;
            konekcija = DriverManager.getConnection(url, dotenv.get("MYSQL_USER"),
                    dotenv.get("MYSQL_PASS"));
            konekcija.setAutoCommit(false);

        } catch (SQLException ex) {
            System.getLogger(BrokerBazePodataka.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            return false;
        }
        return true;
    }

    @Override
    public boolean kreirajSlog(OpstiDomenskiObjekat odo) {
        String upit = "INSERT INTO `" + imeBaze + "`.`" + odo.vratiNazivTabele() + "` (" + odo.vratiNaziveKolona() + ") VALUES (" + odo.vrednostiAtributaZaKreiranje() + ")";
        System.out.println(upit);
        return izvrsiAzuriranje(upit);
    }

    @Override
    public boolean azurirajSlog(OpstiDomenskiObjekat odo, OpstiDomenskiObjekat stariOdo) {
        String upit = "UPDATE `" + imeBaze + "`.`" + odo.vratiNazivTabele() + "` SET " + odo.azurirajVrednostiAtributa() + " WHERE " + stariOdo.vratiWhereUslov();
        return izvrsiAzuriranje(upit);
    }

    @Override
    public boolean azurirajSlog(OpstiDomenskiObjekat odo) {
        String upit = "UPDATE `" + imeBaze + "`.`" + odo.vratiNazivTabele() + "` SET " + odo.azurirajVrednostiAtributa() + " WHERE " + odo.vratiWhereUslov();
        return izvrsiAzuriranje(upit);
    }

    @Override
    public boolean obrisiSlog(OpstiDomenskiObjekat odo) {
        String upit = "DELETE FROM `" + imeBaze + "`.`" + odo.vratiNazivTabele() + "` WHERE " + odo.vratiWhereUslov();
        return izvrsiAzuriranje(upit);
    }

    @Override
    public boolean obrisiSlogove(OpstiDomenskiObjekat odo, String where) {
        String upit = "DELETE FROM `" + imeBaze + "`.`" + odo.vratiNazivTabele() + "` WHERE " + where;
        return izvrsiAzuriranje(upit);
    }

    @Override
    public OpstiDomenskiObjekat pronadjiSlog(OpstiDomenskiObjekat odo) {
        ResultSet rs = null;
        Statement statement = null;
        String upit = "SELECT * FROM `" + imeBaze + "`.`" + odo.vratiNazivTabele() + "` AS " + odo.alias() + " " + odo.join() + " WHERE " + odo.vratiWhereUslov();
        boolean signal;

        try {
            statement = konekcija.createStatement();
            rs = statement.executeQuery(upit);
            signal = rs.isBeforeFirst();
            if (signal == true) {
                odo = odo.vratiNoveSlogove(rs).getFirst();
            } else {
                odo = null;
            }

        } catch (SQLException ex) {
            System.getLogger(BrokerBazePodataka.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return odo;
    }

    public OpstiDomenskiObjekat prijaviSlog(OpstiDomenskiObjekat odo) {
        ResultSet rs = null;
        PreparedStatement statement = null;
        String upit = "SELECT * FROM `" + imeBaze + "`.`" + odo.vratiNazivTabele() + "` AS " + odo.alias() +" " + odo.join() + " WHERE " + odo.vratiWhereUslov();
        boolean signal;
        System.out.println(upit);
        try {
            statement = konekcija.prepareStatement(upit);
            rs = statement.executeQuery();
            signal = rs.isBeforeFirst();
            if (signal == true) {
                OpstiDomenskiObjekat vraceniOdo = odo.vratiNoveSlogove(rs).getFirst();
                odo = vraceniOdo;
                
            } else {
                odo = null;
            }

        } catch (SQLException ex) {
            System.getLogger(BrokerBazePodataka.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return odo;
    }

    @Override
    public List<OpstiDomenskiObjekat> pronadjiSlog(OpstiDomenskiObjekat odo, String where) {
        ResultSet rs = null;
        Statement statement = null;
        String upit;
        if (where == null) {
            upit = "SELECT * FROM `" + imeBaze + "`.`" + odo.vratiNazivTabele() + "` AS " + odo.alias() + " " + odo.join() + " ;";
        } else {
            upit = "SELECT * FROM `" + imeBaze + "`.`" + odo.vratiNazivTabele() + "` AS " + odo.alias() + " " + odo.join() + " WHERE " + where + ";";
        }
        List<OpstiDomenskiObjekat> lista = new LinkedList<>();
        System.out.println(upit);
        try {
            statement = konekcija.createStatement();
            rs = statement.executeQuery(upit);
            lista = odo.vratiNoveSlogove(rs);

        } catch (SQLException ex) {
            System.getLogger(BrokerBazePodataka.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } finally {
            ugasi(null, statement, rs);
        }
        return lista;
    }

    @Override
    public boolean commitTransakcija() {
        try {
            konekcija.commit();
        } catch (SQLException ex) {
            return false;
        }
        return true;
    }

    @Override
    public boolean rollbackTransakcija() {
        try {
            konekcija.rollback();
        } catch (SQLException ex) {
            return false;
        }
        return true;
    }

    @Override
    public void zatvoriKonekciju() {
        ugasi(konekcija, null, null);
    }

    @Override
    public OpstiDomenskiObjekat vratiSlog(OpstiDomenskiObjekat odo, int index) {
        ResultSet rs = null;
        Statement statement = null;
        int brojSlogova = 0;
        String upit = "SELECT * FROM `" + imeBaze + "`.`" + odo.vratiNazivTabele() + "` ORDER BY " + odo.vratiImePoKoloni(0) + " ASC LIMIT " + index + ",1";
        boolean signal;

        try {
            statement = konekcija.createStatement();
            rs = statement.executeQuery(upit);
            signal = rs.next();
            if (signal == true) {
                odo = odo.vratiNoveSlogove(rs).getFirst();
            } else {
                odo = null;
            }

        } catch (SQLException ex) {
            odo = null;
            System.getLogger(BrokerBazePodataka.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } finally {
            ugasi(null, statement, rs);
        }
        return odo;
    }

    @Override
    public int vratiBrojSloga(OpstiDomenskiObjekat odo) {
        ResultSet rs = null;
        Statement statement = null;
        int brojRedova = 0;
        String upit = "SELECT * FROM `" + imeBaze + "`.`" + odo.vratiNazivTabele() + "`;";
        boolean signal;

        try {
            statement = konekcija.createStatement();
            rs = statement.executeQuery(upit);
            if (rs.last()) {
                brojRedova = rs.getRow();
            }

        } catch (SQLException ex) {
            System.getLogger(BrokerBazePodataka.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } finally {
            ugasi(null, statement, rs);
        }
        return brojRedova;
    }

    @Override
    public int vratiPozicijuSloga(OpstiDomenskiObjekat odo) {
        ResultSet rs = null;
        Statement statement = null;
        String upit = "SELECT (COUNT(*)) as pozicija FROM `" + imeBaze + "`.`" + odo.vratiNazivTabele() + "` WHERE " + odo.vratiImePoKoloni(0) + " < " + odo.vratiPrimarniKljuc();
        boolean signal;
        try {
            statement = konekcija.createStatement();
            rs = statement.executeQuery(upit);
            signal = rs.next();
            if (signal == true) {
                return Integer.parseInt(rs.getString("pozicija"));
            }

        } catch (SQLException ex) {
            System.getLogger(BrokerBazePodataka.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } finally {
            ugasi(null, statement, rs);
        }
        return -1;

    }

    @Override
    public boolean bazaPovezana() {

        try {
            if (konekcija == null) {
                return false;
            }
            if (konekcija.isClosed()) {
                return false;
            }
        } catch (SQLException ex) {
            System.getLogger(BrokerBazePodataka.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            return false;
        }
        return true;
    }

    public boolean izvrsiAzuriranje(String upit) {
        Statement statement = null;
        boolean signal = false;
        try {
            statement = konekcija.createStatement();
            int brRedova = statement.executeUpdate(upit);
            if (brRedova > 0) {
                signal = true;
            }
        } catch (SQLException ex) {
            System.getLogger(BrokerBazePodataka.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            signal = false;
        } finally {
            ugasi(null, statement, null);
        }
        return signal;
    }

    public void ugasi(Connection konekcija, Statement statement, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                System.getLogger(BrokerBazePodataka.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        }

        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException ex) {
                System.getLogger(BrokerBazePodataka.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        }

        if (konekcija != null) {
            try {
                konekcija.close();
            } catch (SQLException ex) {
                System.getLogger(BrokerBazePodataka.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        }
    }

    @Override
    public int vratiNoviKljucPoKoloni(OpstiDomenskiObjekat odo) {
        ResultSet rs = null;
        Statement statement = null;
        String upit = "SELECT Max(" + odo.vratiImePoKoloni(0) + ") as noviKljuc FROM " + odo.vratiNazivTabele();
        System.out.println(upit);
        boolean signal;
        try {
            statement = konekcija.createStatement();
            rs = statement.executeQuery(upit);
            signal = rs.next();
            if (signal == true) {
                int kljuc = rs.getInt("noviKljuc") + 1;
                System.out.println(kljuc);
                return kljuc;
            } else {
                return 1;
            }
        } catch (SQLException ex) {
            System.getLogger(BrokerBazePodataka.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } finally {
            ugasi(null, statement, rs);
        }
        return 0;
    }

}
