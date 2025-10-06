package so.agencija;

import bbp.BrokerBazePodataka;
import model.Agencija;
import model.Drzava;
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
        signal = BrokerBazePodataka.getInstance().kreirajSlog(getTo().getOdo());
        return signal;
    }

    @Override
    protected boolean proveriOgranicenja(OpstiDomenskiObjekat odo) {
        if (!(odo instanceof Agencija)) {
            return false;
        }

        // Prosto vrednosno ogranicenje
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

        // Strukturno ogranicenje
        Drzava drzava = (Drzava) BrokerBazePodataka.getInstance().pronadjiSlog(agencija.getDrzava(), agencija.getDrzava().vratiWhereUslov());
        if (drzava == null) {
            signal = false;
        }

        return signal;
    }

}
