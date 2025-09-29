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
public class SOPromeniVeslaca extends OpsteIzvrsenjeSO{

    public SOPromeniVeslaca(TransferObjekat to) {
        setTo(to);
    }

    @Override
    protected boolean izvrsiSO() {
        OpstiDomenskiObjekat vraceniOdo = bbp.pronadjiSlog(to.getOdo());
            if (vraceniOdo != null) {
                if (bbp.azurirajSlog(vraceniOdo)) {
                    to.setSignal(true);
                } else {
                    to.setSignal(false);
                }
            } else {
                to.setSignal(false);
            }
        return to.isSignal();
    }

    @Override
    protected boolean proveriOgranicenja(OpstiDomenskiObjekat odo) {
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
        
        if(veslac.getBMI() != (veslac.getTezina() / (veslac.getVisina() * veslac.getVisina()))){
            signal = false;
        }
        
        return signal;
    }
    
}
