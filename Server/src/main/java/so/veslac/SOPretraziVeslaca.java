package so.veslac;

import model.OpstiDomenskiObjekat;
import model.Veslac;
import so.OpsteIzvrsenjeSO;
import transfer.TransferObjekat;

/**
 *
 * @author lukad
 */
public class SOPretraziVeslaca extends OpsteIzvrsenjeSO {

    public SOPretraziVeslaca(TransferObjekat to) {
        setTo(to);
    }

    @Override
    protected boolean izvrsiSO() {
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
    protected boolean proveriOgranicenja(OpstiDomenskiObjekat odo) {
        if (!(odo instanceof Veslac)) {
            return false;
        }

        Veslac veslac = (Veslac) odo;
        boolean signal = true;
        if(veslac.getImePrezime() == null) {
            signal = false;
        }
        return signal;
    }
}
