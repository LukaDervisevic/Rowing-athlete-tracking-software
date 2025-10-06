package so.takmicenje;

import bbp.BrokerBazePodataka;
import model.OpstiDomenskiObjekat;
import model.Takmicenje;
import so.OpsteIzvrsenjeSO;
import transfer.TransferObjekat;

/**
 *
 * @author lukad
 */
public class SOPretraziTakmicenje extends OpsteIzvrsenjeSO {

    public SOPretraziTakmicenje(TransferObjekat to) {
        setTo(to);
    }

    @Override
    protected boolean izvrsiSO() {
            OpstiDomenskiObjekat vraceniOdo = (OpstiDomenskiObjekat) BrokerBazePodataka.getInstance().pronadjiSlog(to.getOdo(),to.getWhereUslov());
            if (vraceniOdo != null) {
                to.setOdo(vraceniOdo);
                to.setSignal(true);
                to.setTrenutniSlog(BrokerBazePodataka.getInstance().vratiPozicijuSloga(to.getOdo()));
            } else {
                to.setSignal(false);
                to.setTrenutniSlog(-1);
            }
        return to.isSignal();
    }

    @Override
    protected boolean proveriOgranicenja(OpstiDomenskiObjekat odo) {
        if (!(odo instanceof Takmicenje)) {
            return false;
        }

        boolean signal = true;
        Takmicenje takmicenje = (Takmicenje) odo;
        if (takmicenje.getNaziv() == null) {
            signal = false;
        }
        return signal;
    }

}
