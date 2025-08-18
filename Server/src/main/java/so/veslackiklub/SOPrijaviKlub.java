/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.veslackiklub;

import so.PrijaviDK;
import transfer.TransferObjekat;

/**
 *
 * @author lukad
 */
public class SOPrijaviKlub extends PrijaviDK{

    public SOPrijaviKlub(TransferObjekat to) {
        setTo(to);
        porukaGreska = "Greska pri prijavljivanju veslackog kluba";
        porukaUspeh = "Uspesno prijavljivanje veslackog kluba";
    }
    
    
    
}
