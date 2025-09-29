package so.agencija;

import model.Agencija;
import model.OpstiDomenskiObjekat;
import so.OpsteIzvrsenjeSO;
import transfer.TransferObjekat;

/**
 *
 * @author lukad
 */
public class SOObrisiAgenciju extends OpsteIzvrsenjeSO {

    public SOObrisiAgenciju(TransferObjekat to) {
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
        if (!(odo instanceof Agencija)) {
            return false;
        }

        boolean signal = true;
        Agencija agencija = (Agencija) to.getOdo();
        if (agencija.getId() == 0) {
            signal = false;
        }
        if (agencija.getKorisnickoIme() == null || agencija.getKorisnickoIme().isBlank()) {
            signal = false;
        }
        if (agencija.getEmail() == null || agencija.getEmail().isBlank()) {
            signal = false;
        }
        if (!agencija.getEmail().contains("@")) {
            signal = false;
        }
        if (agencija.getTelefon() == null || agencija.getTelefon().isBlank()) {
            signal = false;
        }
        if (!agencija.getTelefon().contains("+")) {
            signal = false;
        }
        if (agencija.getTelefon().length() > 13) {
            signal = false;
        }
        if (agencija.getKorisnickoIme() == null || agencija.getKorisnickoIme().isBlank()) {
            signal = false;
        }
        if (agencija.getSifra() == null || agencija.getSifra().isBlank()) {
            signal = false;
        }
        if (agencija.getSifra().length() < 8) {
            signal = false;
        }
        return signal;
    }

}
