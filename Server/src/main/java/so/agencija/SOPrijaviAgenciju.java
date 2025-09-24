/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.agencija;

import model.Agencija;
import model.OpstiDomenskiObjekat;
import ogranicenja.Ogranicenje;
import so.PrijaviDK;
import transfer.TransferObjekat;
import utils.HesiranjeServis;

/**
 *
 * @author lukad
 */
public class SOPrijaviAgenciju extends PrijaviDK{

    public SOPrijaviAgenciju(TransferObjekat to) {
        setTo(to);
        porukaGreska += " Greska pri prijavi agencije";
        porukaUspeh += " Uspeh pri prijavi agencije";
        
    }

    @Override
    public boolean izvrsiSO() {
       Agencija agencija = (Agencija) to.getOdo();
       boolean verifikovano;
       Ogranicenje ogranicenje = new Ogranicenje();
       
        if (ogranicenje.proveriOgranicenja(to)) {
              Agencija vracenaAgencija = (Agencija) bbp.prijaviSlog(to.getOdo());
              if (vracenaAgencija != null) {
                  verifikovano = HesiranjeServis.proveriSifru(agencija.getSifra(), vracenaAgencija.getSifra());
                  to.setOdo(vracenaAgencija);
                  to.setSignal(verifikovano);
                  to.setPoruka(verifikovano ? porukaUspeh : porukaGreska);
                  
              }else{
                  to.setSignal(false);
                    to.setTrenutniSlog(-1);
                    to.setPoruka(porukaGreska);
              }
        }

        return to.isSignal(); 
    }
    
    
      
}
