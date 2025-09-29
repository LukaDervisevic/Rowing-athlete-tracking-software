package so.ponudaveslaca;

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
        if (proveriOgranicenja(to.getOdo())) {
            OpstiDomenskiObjekat vraceniOdo = (OpstiDomenskiObjekat) bbp.pronadjiSlog(to.getOdo());
            if (vraceniOdo != null) {
                to.setOdo(vraceniOdo);
                to.setSignal(true);
                to.setTrenutniSlog(bbp.vratiPozicijuSloga(to.getOdo()));
            } else {
                to.setSignal(false);
                to.setTrenutniSlog(-1);
            }
        }
        return to.isSignal();
    }

    @Override
    protected boolean proveriOgranicenja(OpstiDomenskiObjekat odo) {
        if (!(odo instanceof PonudaVeslaca)) {
            return false;
        }
        PonudaVeslaca ponudaVeslaca = (PonudaVeslaca) odo;
        return true;
    }
}
