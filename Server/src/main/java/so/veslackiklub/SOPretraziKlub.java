/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.veslackiklub;

import model.OpstiDomenskiObjekat;
import model.VeslackiKlub;
import so.OpsteIzvrsenjeSO;
import transfer.TransferObjekat;

/**
 *
 * @author lukad
 */
public class SOPretraziKlub extends OpsteIzvrsenjeSO{

    public SOPretraziKlub(TransferObjekat to) {
        setTo(to);
    }

    @Override
    protected boolean izvrsiSO() {
        OpstiDomenskiObjekat vraceniOdo = (OpstiDomenskiObjekat) bbp.pronadjiSlog(to.getOdo());
        if (vraceniOdo != null) {
            to.setOdo(vraceniOdo);
            to.setSignal(true);
            to.setTrenutniSlog(bbp.vratiPozicijuSloga(to.getOdo()));
        } else {
            to.setSignal(false);
            to.setTrenutniSlog(-1);
        }

        return to.isSignal();
    }

    @Override
    protected boolean prostaVrednosnaOgranicenja(OpstiDomenskiObjekat odo) {
        if (!(odo instanceof VeslackiKlub)) {
            return false;
        }

        boolean signal = true;
        VeslackiKlub veslackiKlub = (VeslackiKlub) odo;
        if(veslackiKlub.getNaziv() == null || veslackiKlub.getNaziv().isBlank()) {
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
