/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import transfer.TransferObjekat;

/**
 *
 * @author luka
 */
public class ObrisiDK extends OpsteIzvrsenjeSO{

    public void obrisiDK(TransferObjekat to) {
        this.to = to;
        opsteIzvrsenjeSO();
    }
    
    @Override
    public boolean izvrsiSO() {
        if(bbp.obrisiSlog(to.getOdo())) {
            to.setPoruka("Uspesno brisanje domenskog objekta");
            to.setSignal(true);
            to.setOdo(null);
        }else{
            to.setPoruka("Neuspeh pri brisanje domenskog objekta");
            to.setSignal(false);
            to.setOdo(odo);
        }
        return to.isSignal();
    }
    
}
