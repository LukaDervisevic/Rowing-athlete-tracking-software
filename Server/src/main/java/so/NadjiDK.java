/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import model.OpstiDomenskiObjekat;
import ogranicenja.Ogranicenje;
import transfer.TransferObjekat;

/**
 *
 * @author luka
 */
public class NadjiDK extends OpsteIzvrsenjeSO {

    protected String porukaUspeh;
    protected String porukaGreska;
    
    public void nadjiDK(TransferObjekat to) {
        setTo(to);
        opsteIzvrsenjeSO();
    }

    @Override
    public boolean izvrsiSO() {

        Ogranicenje ogranicenje = new Ogranicenje();
        if (ogranicenje.proveriOgranicenja(to)) {
            OpstiDomenskiObjekat vraceniOdo = (OpstiDomenskiObjekat) bbp.pronadjiSlog(to.getOdo());
            if (vraceniOdo != null) {
                to.setOdo(vraceniOdo);
                to.setSignal(true);
                to.setTrenutniSlog(bbp.vratiPozicijuSloga(to.getOdo()));
                to.setPoruka(porukaUspeh);
            } else {
                to.setSignal(false);
                to.setTrenutniSlog(-1);
                to.setPoruka(porukaGreska);
            }
        }

        return to.isSignal();
    }

}
