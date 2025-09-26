package so.agencija;

import model.Agencija;
import model.OpstiDomenskiObjekat;
import so.OpsteIzvrsenjeSO;
import transfer.TransferObjekat;

/**
 *
 * @author lukad
 */
public class SOPretraziAgenciju extends OpsteIzvrsenjeSO {

    public SOPretraziAgenciju(TransferObjekat to) {
        setTo(to);

    }

    @Override
    public boolean izvrsiSO() {
        OpstiDomenskiObjekat vraceniOdo = (OpstiDomenskiObjekat) bbp.pronadjiSlog(to.getOdo());
        if (vraceniOdo != null) {
            to.setOdo(vraceniOdo);
            to.setSignal(true);
            to.setTrenutniSlog(bbp.vratiPozicijuSloga(to.getOdo()));
        } else {
            to.setSignal(false);
            to.setTrenutniSlog(-1);
        }

        return to.isSignal();
    }

    @Override
    protected boolean prostaVrednosnaOgranicenja(OpstiDomenskiObjekat odo) {
        if (!(odo instanceof Agencija)) {
            return false;
        }
        
        Agencija agencija = (Agencija) odo;
        boolean signal = false;
        if(agencija.getNaziv() == null || agencija.getNaziv().isBlank()){
            signal = false;
        }
        return signal;
    }

    @Override
    protected boolean slozenaVrednosnaOgranicenja(OpstiDomenskiObjekat odo) {
        return true;
    }

    @Override
    protected boolean strukturnaOgranicenja(OpstiDomenskiObjekat odo) {
        return true;
    }

}
