package so.veslackiklub;

import model.OpstiDomenskiObjekat;
import model.VeslackiKlub;
import so.OpsteIzvrsenjeSO;
import transfer.TransferObjekat;

/**
 *
 * @author lukad
 */
public class SOPretraziKlub extends OpsteIzvrsenjeSO{

    public SOPretraziKlub(TransferObjekat to) {
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
        if (!(odo instanceof VeslackiKlub)) {
            return false;
        }

        boolean signal = true;
        VeslackiKlub veslackiKlub = (VeslackiKlub) odo;
        if(veslackiKlub.getNaziv() == null) {
            signal = false;
        }
        return signal;
    }

    
    
}
