/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.ponudaveslaca;

import so.NadjiDK;
import transfer.TransferObjekat;

/**
 *
 * @author lukad
 */
public class SOPretraziPonudu extends NadjiDK{

    public SOPretraziPonudu(TransferObjekat to) {
        setTo(to);
        porukaUspeh = "Uspesno pretrazivanje ponuda veslaca";
        porukaGreska = "Greska pri pretrazivanju ponude veslaca";
    }
    
}
