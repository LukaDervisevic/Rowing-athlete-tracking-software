/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.takmicenje;

import model.Takmicenje;
import so.NadjiDK;
import transfer.TransferObjekat;

/**
 *
 * @author lukad
 */
public class SOPretraziTakmicenje extends NadjiDK {

    public SOPretraziTakmicenje(TransferObjekat to) {
        setTo(to);
        porukaUspeh = "Uspesno pretrazivanje takmicenja";
        porukaGreska = "Greska pri pretrazivanju takmicenja: " + to.getPoruka();
    }

}
