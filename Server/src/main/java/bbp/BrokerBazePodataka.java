package bbp;

import io.github.cdimascio.dotenv.Dotenv;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import model.OpstiDomenskiObjekat;

/**
 *
 * @author lukad
 */
public class BrokerBazePodataka implements IBrokerBazePodataka {

    private String imeBaze;
    private Connection konekcija;
    private static BrokerBazePodataka instance;
    
    private BrokerBazePodataka() {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("dbconfig.properties"));
            imeBaze = properties.getProperty("DB_NAME");
            
        } catch (Exception ex) {
            System.getLogger(BrokerBazePodataka.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    public static BrokerBazePodataka getInstance() {
        if(instance == null) instance = new BrokerBazePodataka();
        return instance;
    }

    @Override
    public boolean napraviKonekciju() {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("dbconfig.properties"));
            String url = properties.getProperty("DB_URL") + imeBaze;
            String user = properties.getProperty("MYSQL_USER");
            String pass = properties.getProperty("MYSQL_PASS");
            konekcija = DriverManager.getConnection(url,user,pass);
            konekcija.setAutoCommit(false);

        } catch (Exception ex) {
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
    public boolean azurirajSlog(OpstiDomenskiObjekat odo) {
        String upit = "UPDATE `" + imeBaze + "`.`" + odo.vratiNazivTabele() +"`AS "+odo.alias()+" SET " + odo.azurirajVrednostiAtributa() + " WHERE " + odo.vratiWhereUslov();
        System.out.println(upit);
        return izvrsiAzuriranje(upit);
    }

    @Override
    public boolean obrisiSlog(OpstiDomenskiObjekat odo) {
        String upit = "DELETE FROM `" + imeBaze + "`.`" + odo.vratiNazivTabele() + "` AS " +odo.alias() +" WHERE " + odo.vratiWhereUslov();
        System.out.println(upit);
        return izvrsiAzuriranje(upit);
    }

    @Override
    public OpstiDomenskiObjekat pronadjiSlog(OpstiDomenskiObjekat odo,String where) {
        ResultSet rs = null;
        Statement statement = null;
        String upit = "SELECT * FROM `" + imeBaze + "`.`" + odo.vratiNazivTabele() + "` AS " + odo.alias() + " " + odo.join() + " WHERE " + where;
        System.out.println(upit);
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

    @Override
    public List<OpstiDomenskiObjekat> pronadjiSlogоve(OpstiDomenskiObjekat odo, String where) {
        ResultSet rs = null;
        Statement statement = null;
        String upit;
        if (where == null || where.isBlank()) {
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
    public int vratiPozicijuSloga(OpstiDomenskiObjekat odo) {
        ResultSet rs = null;
        Statement statement = null;
        String upit = "SELECT (COUNT(*)) as pozicija FROM `" + imeBaze + "`.`" + odo.vratiNazivTabele() + "` WHERE " + odo.vratiImePoKoloni(0) + " < " + odo.vratiPrimarniKljuc();
        System.out.println(upit);
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
