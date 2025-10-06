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
public class SOUbaciTakmicenje extends OpsteIzvrsenjeSO {

    public SOUbaciTakmicenje(TransferObjekat to) {
        setTo(to);
    }

    @Override
    public boolean izvrsiSO() {
        boolean signal = BrokerBazePodataka.getInstance().kreirajSlog(getTo().getOdo());
        return signal;
    }

    @Override
    protected boolean proveriOgranicenja(OpstiDomenskiObjekat odo) {
        if (!(odo instanceof Takmicenje)) {
            return false;
        }

        boolean signal = true;
        Takmicenje takmicenje = (Takmicenje) odo;

        if (takmicenje.getNaziv() == null || takmicenje.getNaziv().isBlank()) {
            signal = false;
        }
        String[] kategorija = {"KADET", "JUNIOR"};
        if (!Arrays.asList(kategorija).contains(takmicenje.getStarosnaKategorija().toString())) {
            signal = false;
        }
        String[] vrstaTrke = {"SKIF", "DUBL", "DVOJAC", "CETVERAC_SKUL", "CETVERAC_RIMEN", "OSMERAC"};
        if (!Arrays.asList(vrstaTrke).contains(takmicenje.getVrstaTrke().toString())) {
            signal = false;
        }
        if (takmicenje.getDatum() == null) {
            signal = false;
        }
        return signal;
    }

}
