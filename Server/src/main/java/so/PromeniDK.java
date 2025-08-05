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
public class PromeniDK extends OpsteIzvrsenjeSO {

    protected String porukaUspeh;
    protected String porukaGreska;

    public void promeniDK(TransferObjekat to) {
        this.to = to;
        opsteIzvrsenjeSO();
    }

    @Override
    public boolean izvrsiSO() {
        Ogranicenje ogranicenje = new Ogranicenje();
        if (ogranicenje.proveriOgranicenja(to)) {
            OpstiDomenskiObjekat vraceniOdo = bbp.pronadjiSlog(to.getOdo());
            if (vraceniOdo != null) {

                if (bbp.azurirajSlog(vraceniOdo)) {
                    to.setPoruka(porukaUspeh);
                    to.setSignal(true);
                } else {
                    to.setPoruka(porukaGreska);
                    to.setSignal(false);
                }
            } else {
                to.setPoruka("Neuspesno pronalazenje domenskog objekta koji treba da se azurira");
            }
        }

        return to.isSignal();
    }

}
