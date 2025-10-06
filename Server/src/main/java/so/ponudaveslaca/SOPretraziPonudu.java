package so.ponudaveslaca;

import bbp.BrokerBazePodataka;
import model.OpstiDomenskiObjekat;
import model.PonudaVeslaca;
import so.OpsteIzvrsenjeSO;
import transfer.TransferObjekat;

/**
 *
 * @author lukad
 */
public class SOPretraziPonudu extends OpsteIzvrsenjeSO {

    public SOPretraziPonudu(TransferObjekat to) {
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
        return odo instanceof PonudaVeslaca;
    }
}
