package so.drzava;

import model.Drzava;
import model.OpstiDomenskiObjekat;
import so.OpsteIzvrsenjeSO;
import transfer.TransferObjekat;

/**
 *
 * @author lukad
 */
public class SOUbaciDrzavu extends OpsteIzvrsenjeSO {

    public SOUbaciDrzavu(TransferObjekat to) {
        this.to = to;
    }

    @Override
    public boolean izvrsiSO() {
        boolean signal = false;
        int noviKljuc = getBbp().vratiNoviKljucPoKoloni(getTo().getOdo());
        if (noviKljuc != 0) {
            getTo().getOdo().postaviPrimarniKljuc(noviKljuc);
            signal = getBbp().kreirajSlog(getTo().getOdo());
            getTo().signal = signal;
        }
        return signal;
    }

    @Override
    protected boolean proveriOgranicenja(OpstiDomenskiObjekat odo) {
        if (!(odo instanceof Drzava)) {
            return false;
        }
        boolean signal = true;
        Drzava drzava = (Drzava) to.getOdo();
        if (drzava.getNaziv() == null || drzava.getNaziv().isBlank()) {
            signal = false;
        }
        return signal;
    }

}
