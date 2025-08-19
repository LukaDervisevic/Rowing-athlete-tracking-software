/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import model.OpstiDomenskiObjekat;
import ogranicenja.Ogranicenje;

/**
 *
 * @author lukad
 */
public class PrijaviDK extends OpsteIzvrsenjeSO{
    
    protected String porukaUspeh;
    protected String porukaGreska;
    
    @Override
    public boolean izvrsiSO() {
        Ogranicenje ogranicenje = new Ogranicenje();
        if (ogranicenje.proveriOgranicenja(to)) {
            
            OpstiDomenskiObjekat vraceniKlub= (OpstiDomenskiObjekat) bbp.prijaviSlog(to.getOdo(),"veslacki_klub");
            OpstiDomenskiObjekat vracenaAgencija = (OpstiDomenskiObjekat) bbp.prijaviSlog(to.getOdo(), "agencija");
            if (vraceniOdo != null) {
                to.setOdo(vraceniOdo);
                to.setSignal(true);
                to.setTrenutniSlog(bbp.vratiPozicijuSloga(odo));
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
