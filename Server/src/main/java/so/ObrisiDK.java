/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import ogranicenja.Ogranicenje;
import transfer.TransferObjekat;

/**
 *
 * @author luka
 */
public class ObrisiDK extends OpsteIzvrsenjeSO {

    protected String porukaUspesno;
    protected String porukaGreska;

    public void obrisiDK(TransferObjekat to) {
        this.to = to;
        opsteIzvrsenjeSO();
    }

    @Override
    public boolean izvrsiSO() {
        boolean signal = false;
        Ogranicenje ogranicenje = new Ogranicenje();
        if (ogranicenje.proveriOgranicenja(to)) {
            signal = bbp.obrisiSlog(to.getOdo());
            if (signal) {
                getTo().poruka = porukaUspesno;
            } else {
                getTo().poruka = porukaGreska;
            }
            getTo().setSignal(signal);
        }

        return signal;
    }

}
