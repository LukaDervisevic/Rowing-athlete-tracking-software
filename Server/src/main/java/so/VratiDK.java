/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import model.OpstiDomenskiObjekat;
import transfer.TransferObjekat;

/**
 *
 * @author lukad
 */
public class VratiDK extends OpsteIzvrsenjeSO{
    
    private String whereUslov;

    public VratiDK(TransferObjekat to) {
        setTo(to);
    }

    @Override
    public boolean izvrsiSO() {
        boolean signal = false;
        OpstiDomenskiObjekat odo = bbp.pronadjiSlog(to.getOdo());
        if(odo != null) {
            signal = true;
            to.setOdo(odo);
        }
        return signal;
    }
    
}
