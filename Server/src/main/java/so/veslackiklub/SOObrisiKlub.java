package so.veslackiklub;

import bbp.BrokerBazePodataka;
import model.OpstiDomenskiObjekat;
import model.VeslackiKlub;
import so.OpsteIzvrsenjeSO;
import transfer.TransferObjekat;

/**
 *
 * @author lukad
 */
public class SOObrisiKlub extends OpsteIzvrsenjeSO {

    public SOObrisiKlub(TransferObjekat to) {
        setTo(to);
    }

    @Override
    protected boolean izvrsiSO() {
        boolean signal = false;
        signal = BrokerBazePodataka.getInstance().obrisiSlog(to.getOdo());
        getTo().setSignal(signal);
        return signal;
    }

    @Override
    protected boolean proveriOgranicenja(OpstiDomenskiObjekat odo) {
        if (!(odo instanceof VeslackiKlub)) {
            return false;
        }

        boolean signal = true;
        VeslackiKlub veslackiKlub = (VeslackiKlub) odo;
        if (veslackiKlub.getId() == 0) {
            signal = false;
        }
        if (veslackiKlub.getNaziv() == null || veslackiKlub.getNaziv().isBlank()) {
            signal = false;
        }
        if (veslackiKlub.getEmail() == null || veslackiKlub.getEmail().isBlank()) {
            signal = false;
        }
        if (!veslackiKlub.getEmail().contains("@")) {
            signal = false;
        }
        if (veslackiKlub.getAdresa() == null || veslackiKlub.getAdresa().isBlank()) {
            signal = false;
        }
        if (veslackiKlub.getTelefon() == null || veslackiKlub.getTelefon().isBlank()) {
            signal = false;
        }
        if (!veslackiKlub.getTelefon().contains("+")) {
            signal = false;
        }
        if (veslackiKlub.getTelefon().length() > 13) {
            signal = false;
        }
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
