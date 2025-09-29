package bbp;

import java.util.List;
import model.OpstiDomenskiObjekat;


/**
 *
 * @author lukad
 */
public interface IBrokerBazePodataka {
    public abstract boolean napraviKonekciju();
    public abstract boolean kreirajSlog(OpstiDomenskiObjekat odo);
    public abstract boolean azurirajSlog(OpstiDomenskiObjekat odo, OpstiDomenskiObjekat stariOdo);
    public abstract boolean azurirajSlog(OpstiDomenskiObjekat odo);
    public abstract boolean obrisiSlog(OpstiDomenskiObjekat odo);
    public abstract boolean obrisiSlogove(OpstiDomenskiObjekat odo, String where);
    public abstract OpstiDomenskiObjekat pronadjiSlog(OpstiDomenskiObjekat odo);
    public abstract List<OpstiDomenskiObjekat> pronadjiSlogоve(OpstiDomenskiObjekat odo, String kriterijum);
    public abstract boolean commitTransakcija();
    public abstract boolean rollbackTransakcija();
    public abstract void zatvoriKonekciju();
    public abstract OpstiDomenskiObjekat vratiSlog(OpstiDomenskiObjekat odo, int index);
    public abstract int vratiBrojSloga(OpstiDomenskiObjekat odo);
    public abstract int vratiPozicijuSloga(OpstiDomenskiObjekat odo);
    public abstract int vratiNoviKljucPoKoloni(OpstiDomenskiObjekat odo);
    public abstract boolean bazaPovezana();
}
