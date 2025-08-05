/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.takmicenje;

import so.KreirajDK;
import transfer.TransferObjekat;

/**
 *
 * @author lukad
 */
public class SOUbaciTakmicenje extends KreirajDK{

    public SOUbaciTakmicenje(TransferObjekat to) {
        setTo(to);
        porukaUspesno = "Uspesno ubacivanje takmicenja";
        porukaGreska = "Greska pri ubacivanju takmicenja: " + to.getPoruka();
    }
    
}
