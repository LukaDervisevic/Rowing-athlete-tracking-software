/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.veslackiklub;

import so.PromeniDK;
import transfer.TransferObjekat;

/**
 *
 * @author lukad
 */
public class SOPromeniKlub extends PromeniDK{

    public SOPromeniKlub(TransferObjekat to) {
        setTo(to);
        porukaUspeh = "Uspesno azuriranje veslackog kluba";
        porukaGreska = "Greska pri azuriranju veslackog kluba: " + to.getPoruka();
    }

    
    
}
