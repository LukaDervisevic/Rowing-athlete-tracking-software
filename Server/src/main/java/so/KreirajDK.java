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
public class KreirajDK extends OpsteIzvrsenjeSO{

    public void kreirajDK(TransferObjekat to) {
        this.setTo(to);
        opsteIzvrsenjeSO();
    }
    
    @Override
    public boolean izvrsiSO() {
        
        if(getBbp().kreirajSlog(getTo().getOdo())) {
            getTo().poruka = "Uspesno kreirana domenska klasa";
            getTo().signal = true;
        }else{
            getTo().poruka = "Neuspesno kreiranje domenske klase";
            getTo().signal = false;
        }
        
        return getTo().signal;
    }

    public KreirajDK() {
    }
    
    public KreirajDK(TransferObjekat to) {
        this.setTo(to);
    }
    
    
}
