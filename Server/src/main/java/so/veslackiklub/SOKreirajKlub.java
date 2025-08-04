/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.veslackiklub;

import model.VeslackiKlub;
import so.OpsteIzvrsenjeSO;
import transfer.TransferObjekat;

/**
 *
 * @author lukad
 */
public class SOKreirajKlub extends OpsteIzvrsenjeSO{
    
    public SOKreirajKlub(TransferObjekat to) {
        this.setTo(to);
        opsteIzvrsenjeSO();
    }

    @Override
    public boolean izvrsiSO() {  
        boolean signal = bbp.kreirajSlog(to.getOdo());
        return signal;
    }
    
}
