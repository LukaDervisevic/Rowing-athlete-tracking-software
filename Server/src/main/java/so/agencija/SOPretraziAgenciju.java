/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.agencija;

import so.NadjiDK;
import transfer.TransferObjekat;

/**
 *
 * @author lukad
 */
public class SOPretraziAgenciju extends NadjiDK{

    public SOPretraziAgenciju(TransferObjekat to) {
        setTo(to);
        porukaUspeh = "Uspesno pretrazivanje agencija";
        porukaGreska = "Greska pri pretrazivanju agencija";
    }
    
}
