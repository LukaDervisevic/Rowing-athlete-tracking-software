package so.agencija;

import model.Agencija;
import model.OpstiDomenskiObjekat;
import so.OpsteIzvrsenjeSO;
import transfer.TransferObjekat;

/**
 *
 * @author lukad
 */
public class SOPromeniAgenciju extends OpsteIzvrsenjeSO{

    public SOPromeniAgenciju(TransferObjekat to) {
        setTo(to);
    }

    @Override
    public boolean izvrsiSO() {
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
        boolean signal = true;
        Agencija agencija = (Agencija) to.getOdo();
        if(agencija.getKorisnickoIme() == null || agencija.getKorisnickoIme().isBlank()) {
            signal = false;
        }
        if(agencija.getEmail() == null || agencija.getEmail().isBlank()) {
            signal = false;
        }
        if(!agencija.getEmail().contains("@")) {
            signal = false;
        }
        if(agencija.getTelefon() == null || agencija.getTelefon().isBlank()) {
            signal = false;
        }
        if(!agencija.getTelefon().contains("+")) {
            signal = false;
        }
        if(agencija.getTelefon().length() > 13) {
            signal = false;
        }
        if (agencija.getKorisnickoIme() == null || agencija.getKorisnickoIme().isBlank()) {
            signal = false;
        }
        if(agencija.getSifra() == null || agencija.getSifra().isBlank()) {
            signal = false;
        }
        if(agencija.getSifra().length() < 8) {
            signal = false;
        }
        return signal;
    }
    
}
