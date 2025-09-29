package so.agencija;

import model.Agencija;
import model.OpstiDomenskiObjekat;
import so.OpsteIzvrsenjeSO;
import transfer.TransferObjekat;
import utils.HesiranjeServis;

/**
 *
 * @author lukad
 */
public class SOPrijaviAgenciju extends OpsteIzvrsenjeSO {

    public SOPrijaviAgenciju(TransferObjekat to) {
        setTo(to);
    }

    @Override
    public boolean izvrsiSO() {
        Agencija agencija = (Agencija) to.getOdo();
        boolean verifikovano;
        Agencija vracenaAgencija = (Agencija) bbp.prijaviSlog(to.getOdo());
        if (vracenaAgencija != null) {
            verifikovano = HesiranjeServis.proveriSifru(agencija.getSifra(), vracenaAgencija.getSifra());
            to.setOdo(vracenaAgencija);
            to.setSignal(verifikovano);

        } else {
            to.setSignal(false);
            to.setTrenutniSlog(-1);
        }
        return to.isSignal();
    }

    @Override
    protected boolean proveriOgranicenja(OpstiDomenskiObjekat odo) {
        if (!(odo instanceof Agencija)) {
            return false;
        }

        Agencija agencija = (Agencija) odo;
        boolean signal = true;
        if (agencija.getSifra() == null || agencija.getSifra().isBlank()) {
            signal = false;
        }

        if (agencija.getKorisnickoIme() == null || agencija.getKorisnickoIme().isBlank()) {
            signal = false;
        }

        if (agencija.getSifra().length() < 8) {
            signal = false;
        }
        return signal;
    }

}
