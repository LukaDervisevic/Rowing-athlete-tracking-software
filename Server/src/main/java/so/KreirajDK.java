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
public class KreirajDK extends OpsteIzvrsenjeSO {

    protected String porukaUspesno;
    protected String porukaGreska;

    public void kreirajDK(TransferObjekat to) {
        this.setTo(to);
        opsteIzvrsenjeSO();
    }

    @Override
    public boolean izvrsiSO() {
        boolean signal = false;
        int noviKljuc = getBbp().vratiNoviKljucPoKoloni(getTo().getOdo());
        if(noviKljuc != 0) {
            getTo().getOdo().postaviPrimarniKljuc(noviKljuc);
            Ogranicenje ogranicenje = new Ogranicenje();
        if (ogranicenje.proveriOgranicenja(to)) {
            signal = getBbp().kreirajSlog(getTo().getOdo());
            if (signal) {
                getTo().poruka = porukaUspesno;
            } else {
                getTo().poruka = porukaGreska;
            }
            getTo().signal = signal;
        }
        }
        

        return signal;
    }

    public KreirajDK() {
    }

    public KreirajDK(TransferObjekat to) {
        this.setTo(to);
    }

}
