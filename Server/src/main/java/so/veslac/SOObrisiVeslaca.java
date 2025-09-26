package so.veslac;

import java.util.Arrays;
import model.OpstiDomenskiObjekat;
import model.Veslac;
import so.OpsteIzvrsenjeSO;
import transfer.TransferObjekat;

/**
 *
 * @author lukad
 */
public class SOObrisiVeslaca extends OpsteIzvrsenjeSO {

    public SOObrisiVeslaca(TransferObjekat to) {
        setTo(to);
    }

    @Override
    public boolean izvrsiSO() {
        boolean signal = bbp.obrisiSlog(to.getOdo());
        getTo().setSignal(signal);
        return signal;
    }

    @Override
    protected boolean prostaVrednosnaOgranicenja(OpstiDomenskiObjekat odo) {
        if (!(odo instanceof Veslac)) {
            return false;
        }
        
        Veslac veslac = (Veslac) odo;
        boolean signal = true;
        
        if(veslac.getId() == 0) {
            signal = false;
        }
        if(veslac.getImePrezime() == null || veslac.getImePrezime().isBlank()) {
            signal = false;
        }
        if(veslac.getDatumRodjenja() == null) {
            signal = false;
        }
        if(veslac.getVisina() <= 0) {
            signal = false;
        }
        if(veslac.getTezina() <= 0) {
            signal = false;
        }
        String[] kategorija = {"Kadet","Junior"};
        if(!Arrays.asList(kategorija).contains(veslac.getKategorija().toString())) {
            signal = false;
        }
        if(veslac.getBMI() <= 0) {
            signal = false;
        }
        if(veslac.getNajboljeVreme() <= 0) {
            signal = false;
        }
        if(veslac.getDatumUpisa() == null) {
            signal = false;
        }
        
        return signal;
    }

    @Override
    protected boolean slozenaVrednosnaOgranicenja(OpstiDomenskiObjekat odo) {
        if (!(odo instanceof Veslac)) {
            return false;
        }

        Veslac veslac = (Veslac) odo;
        boolean signal = true;
        if(veslac.getBMI() != (veslac.getTezina() / (veslac.getVisina() * veslac.getVisina()))){
            signal = false;
        }
        return signal;
    }

    @Override
    protected boolean strukturnaOgranicenja(OpstiDomenskiObjekat odo) {
        return true;
    }
}
