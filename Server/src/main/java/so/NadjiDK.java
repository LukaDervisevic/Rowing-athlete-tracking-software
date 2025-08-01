/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import model.OpstiDomenskiObjekat;
import transfer.TransferObjekat;

/**
 *
 * @author luka
 */
public class NadjiDK extends OpsteIzvrsenjeSO{

    public void nadjiDK(TransferObjekat to) {
        setTo(to);
        opsteIzvrsenjeSO();
    }
    
    @Override
    public boolean izvrsiSO() {
        OpstiDomenskiObjekat odo = (OpstiDomenskiObjekat) bbp.pronadjiSlog(to.getOdo());
        to.setOdo(odo);
        if(odo != null) {
            to.setSignal(true);
            to.setTrenutniSlog(bbp.vratiPozicijuSloga(odo));
            to.setPoruka("Uspesno pri vracanju domenskog objekta");
        }else{
            to.setSignal(false);
            to.setTrenutniSlog(-1);
            to.setPoruka("Neuspeh pri vracanju domenskog objekta");
        }
        return to.isSignal();
    }
    
}
