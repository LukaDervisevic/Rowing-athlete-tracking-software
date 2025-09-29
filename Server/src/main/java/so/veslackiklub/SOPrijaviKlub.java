package so.veslackiklub;

import model.OpstiDomenskiObjekat;
import model.VeslackiKlub;
import so.OpsteIzvrsenjeSO;
import transfer.TransferObjekat;
import utils.HesiranjeServis;

/**
 *
 * @author lukad
 */
public class SOPrijaviKlub extends OpsteIzvrsenjeSO {

    public SOPrijaviKlub(TransferObjekat to) {
        setTo(to);
    }

    @Override
    public boolean izvrsiSO() {
        try {
            VeslackiKlub klub = (VeslackiKlub) to.getOdo();
            boolean verifikovano;

            VeslackiKlub vraceniKlub = (VeslackiKlub) bbp.prijaviSlog(to.getOdo());
            if (vraceniKlub != null) {
                verifikovano = HesiranjeServis.proveriSifru(klub.getSifra(), vraceniKlub.getSifra());
                to.setOdo(vraceniKlub);
                to.setSignal(verifikovano);

            } else {
                to.setSignal(false);
                to.setTrenutniSlog(-1);
            }

            return to.isSignal();
        } catch (Exception ex) {
            return false;
        }

    }

    @Override
    protected boolean proveriOgranicenja(OpstiDomenskiObjekat odo) {
        boolean signal = true;
        VeslackiKlub veslackiKlub = (VeslackiKlub) odo;
        if (veslackiKlub.getKorisnickoIme() == null || veslackiKlub.getKorisnickoIme().isBlank()) {
            signal = false;
        }

        if (veslackiKlub.getSifra() == null || veslackiKlub.getSifra().isBlank()) {
            signal = false;
        }

        if (veslackiKlub.getSifra().length() < 8) {
            signal = false;
        }
        return signal;
    }

}
