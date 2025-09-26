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
public class SOUbaciAgenciju extends OpsteIzvrsenjeSO {

    public SOUbaciAgenciju(TransferObjekat to) {
        setTo(to);
        Agencija agencija = (Agencija) to.getOdo();
        agencija.setSifra(HesiranjeServis.hesirajSifru(agencija.getSifra()));

    }

    @Override
    public boolean izvrsiSO() {
        boolean signal = false;
        int noviKljuc = getBbp().vratiNoviKljucPoKoloni(getTo().getOdo());
        if (noviKljuc != 0) {
            getTo().getOdo().postaviPrimarniKljuc(noviKljuc);
            signal = getBbp().kreirajSlog(getTo().getOdo());
            getTo().signal = signal;
        }

        return signal;
    }

    @Override
    protected boolean prostaVrednosnaOgranicenja(OpstiDomenskiObjekat odo) {
        if (!(odo instanceof Agencija)) {
            return false;
        }

        boolean signal = true;
        Agencija agencija = (Agencija) to.getOdo();
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

    @Override
    protected boolean slozenaVrednosnaOgranicenja(OpstiDomenskiObjekat odo) {
        return true;
    }

    @Override
    protected boolean strukturnaOgranicenja(OpstiDomenskiObjekat odo) {
        // TREBA DA POPRAVIS
        return true;
    }

}
