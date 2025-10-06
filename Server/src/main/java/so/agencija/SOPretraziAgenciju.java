package so.agencija;

import bbp.BrokerBazePodataka;
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
        if (!(odo instanceof Agencija)) {
            return false;
        }

        Agencija agencija = (Agencija) odo;
        boolean signal = false;
        if (agencija.getNaziv() == null) {
            signal = false;
        }
        return signal;
    }

}
