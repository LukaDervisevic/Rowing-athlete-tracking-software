/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.veslackiklub;

import so.NadjiDK;
import transfer.TransferObjekat;

/**
 *
 * @author lukad
 */
public class SOPretraziKlub extends NadjiDK{

    public SOPretraziKlub(TransferObjekat to) {
        setTo(to);
        porukaUspeh = "Uspesna pretraga veslackih klubova";
        porukaGreska = "Greska pri pretrazivanju veslackih klubova: " + to.getPoruka();
    }

    
    
}
