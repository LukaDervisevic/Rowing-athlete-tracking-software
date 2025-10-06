package so.veslac;

import bbp.BrokerBazePodataka;
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
        boolean signal;
        OpstiDomenskiObjekat vraceniOdo = (OpstiDomenskiObjekat) BrokerBazePodataka.getInstance().pronadjiSlog(to.getOdo(),to.getWhereUslov());
        if (vraceniOdo != null) {
            to.setOdo(vraceniOdo);
            signal = true;
        } else {
            signal = false;
        }
        return signal;
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
