package so.drzava;

import model.Drzava;
import model.OpstiDomenskiObjekat;
import so.OpsteIzvrsenjeSO;
import transfer.TransferObjekat;

/**
 *
 * @author lukad
 */
public class SOObrisiDrzavu extends OpsteIzvrsenjeSO {

    public SOObrisiDrzavu(TransferObjekat to) {
        setTo(to);

    }

    @Override
    public boolean izvrsiSO() {
        boolean signal = bbp.obrisiSlog(to.getOdo());
        getTo().setSignal(signal);
        return signal;
    }

    @Override
    protected boolean proveriOgranicenja(OpstiDomenskiObjekat odo) {
        boolean signal = true;
        Drzava drzava = (Drzava) odo;
        if(drzava.getId() == 0){
            signal = false;
        }
        if(drzava.getNaziv() == null || drzava.getNaziv().isBlank()) {
            signal = false;
        }
        return signal;
    }
}
