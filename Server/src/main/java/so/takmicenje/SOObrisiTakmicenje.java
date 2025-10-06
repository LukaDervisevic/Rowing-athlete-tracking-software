package so.takmicenje;

import bbp.BrokerBazePodataka;
import java.util.Arrays;
import model.OpstiDomenskiObjekat;
import model.Takmicenje;
import so.OpsteIzvrsenjeSO;
import transfer.TransferObjekat;

/**
 *
 * @author lukad
 */
public class SOObrisiTakmicenje extends OpsteIzvrsenjeSO {

    public SOObrisiTakmicenje(TransferObjekat to) {
        setTo(to);
    }

    @Override
    protected boolean izvrsiSO() {
        boolean signal = BrokerBazePodataka.getInstance().obrisiSlog(to.getOdo());
        getTo().setSignal(signal);
        return signal;
    }

    @Override
    protected boolean proveriOgranicenja(OpstiDomenskiObjekat odo) {
        if (!(odo instanceof Takmicenje)) {
            return false;
        }

        boolean signal = true;
        Takmicenje takmicenje = (Takmicenje) odo;
        if (takmicenje.getId() == 0) {
            signal = false;
        }
        if (takmicenje.getNaziv() == null || takmicenje.getNaziv().isBlank()) {
            signal = false;
        }
        String[] kategorija = {"Kadet", "Junior"};
        if (!Arrays.asList(kategorija).contains(takmicenje.getStarosnaKategorija().toString())) {
            signal = false;
        }
        String[] vrstaTrke = {"Skif", "Dubl", "Dvojac", "Cetverac", "Osmerac"};
        if (!Arrays.asList(vrstaTrke).contains(takmicenje.getVrstaTrke().toString())) {
            signal = false;
        }
        if (takmicenje.getDatum() == null) {
            signal = false;
        }
        return signal;
    }

}
