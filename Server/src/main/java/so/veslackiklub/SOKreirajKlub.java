/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.veslackiklub;

import so.KreirajDK;
import transfer.TransferObjekat;

/**
 *
 * @author lukad
 */
public class SOKreirajKlub extends KreirajDK{
    
    public SOKreirajKlub(TransferObjekat to) {
        this.setTo(to);
        porukaUspesno  = "Uspesno kreiranje veslackog kluba";
        porukaGreska = "Greska pri kreiranju veslackog kluba: " + to.getPoruka();
    }
    
}
