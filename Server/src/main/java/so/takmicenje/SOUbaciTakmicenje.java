/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.takmicenje;

import java.util.Arrays;
import model.OpstiDomenskiObjekat;
import model.Takmicenje;
import so.OpsteIzvrsenjeSO;
import transfer.TransferObjekat;

/**
 *
 * @author lukad
 */
public class SOUbaciTakmicenje extends OpsteIzvrsenjeSO {

    public SOUbaciTakmicenje(TransferObjekat to) {
        setTo(to);
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
        if (!(odo instanceof Takmicenje)) {
            return false;
        }

        boolean signal = true;
        Takmicenje takmicenje = (Takmicenje) to.getOdo();
        if (takmicenje.getId() == 0) {
            signal = false;
        }
        if (takmicenje.getNaziv() == null || takmicenje.getNaziv().isBlank()) {
            signal = false;
        }
        String[] kategorija = {"Kadet", "Junior"};
        if (!Arrays.asList(kategorija).contains(takmicenje.getStarosnaKategorija().toString())) {
            signal = false;
        }
        String[] vrstaTrke = {"Skif", "Dubl", "Dvojac", "Cetverac", "Osmerac"};
        if (!Arrays.asList(vrstaTrke).contains(takmicenje.getVrstaTrke().toString())) {
            signal = false;
        }
        if (takmicenje.getDatum() == null) {
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
        return true;
    }

}
