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
public class SOUbaciKlub extends OpsteIzvrsenjeSO {

    public SOUbaciKlub(TransferObjekat to) {
        this.setTo(to);
        VeslackiKlub vk = (VeslackiKlub) getTo().getOdo();
        vk.setSifra(HesiranjeServis.hesirajSifru(vk.getSifra()));
    }

    @Override
    protected boolean izvrsiSO() {
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
    protected boolean proveriOgranicenja(OpstiDomenskiObjekat odo) {
        if (!(odo instanceof VeslackiKlub)) {
            return false;
        }

        boolean signal = true;
        VeslackiKlub veslackiKlub = (VeslackiKlub) odo;
        if(veslackiKlub.getNaziv() == null || veslackiKlub.getNaziv().isBlank()) {
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
