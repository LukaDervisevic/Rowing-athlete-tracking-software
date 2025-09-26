package so.ponudaveslaca;

import model.OpstiDomenskiObjekat;
import model.PonudaVeslaca;
import so.OpsteIzvrsenjeSO;
import transfer.TransferObjekat;

/**
 *
 * @author lukad
 */
public class SOPretraziPonudu extends OpsteIzvrsenjeSO{

    public SOPretraziPonudu(TransferObjekat to) {
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
    protected boolean prostaVrednosnaOgranicenja(OpstiDomenskiObjekat odo) {
        if(!(odo instanceof PonudaVeslaca)){
            return false;
        }
        PonudaVeslaca ponudaVeslaca = (PonudaVeslaca) odo;
        boolean signal = true;
        return true;
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
