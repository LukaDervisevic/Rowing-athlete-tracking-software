package so.veslac;

import bbp.BrokerBazePodataka;
import java.util.Arrays;
import java.util.List;
import model.OpstiDomenskiObjekat;
import model.PonudaVeslaca;
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
        boolean signal = BrokerBazePodataka.getInstance().obrisiSlog(to.getOdo());
        return signal;
    }

    @Override
    protected boolean proveriOgranicenja(OpstiDomenskiObjekat odo) {
        if (!(odo instanceof Veslac)) {
            return false;
        }

        // Prosta i slozena vrednosna ogranicenja
        Veslac veslac = (Veslac) odo;
        boolean signal = true;

        if (veslac.getId() == 0) {
            signal = false;
        }
        if (veslac.getImePrezime() == null || veslac.getImePrezime().isBlank()) {
            signal = false;
        }
        if (veslac.getDatumRodjenja() == null) {
            signal = false;
        }
        if (veslac.getVisina() <= 0) {
            signal = false;
        }
        if (veslac.getTezina() <= 0) {
            signal = false;
        }
        String[] kategorija = {"KADET", "JUNIOR"};
        if (!Arrays.asList(kategorija).contains(veslac.getKategorija().toString())) {
            signal = false;
        }
        if (veslac.getBMI() <= 0) {
            signal = false;
        }
        if (veslac.getNajboljeVreme() <= 0) {
            signal = false;
        }
        if (veslac.getDatumUpisa() == null) {
            signal = false;
        }
        
        // Strukturna ogranicenja
        OpstiDomenskiObjekat veslacOdo = (OpstiDomenskiObjekat) BrokerBazePodataka.getInstance().pronadjiSlog(veslac, veslac.vratiWhereUslov());
        if(veslacOdo == null) {
            signal = false;
        }
        
        List<OpstiDomenskiObjekat> ponude = BrokerBazePodataka.getInstance().pronadjiSlogоve(new PonudaVeslaca(), veslac.alias()+".id = "+veslac.getId());
        if(!ponude.isEmpty()) {
            signal = false;
        }
        return signal;
    }
}
