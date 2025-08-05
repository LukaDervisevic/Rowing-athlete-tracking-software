/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.takmicenje;

import so.NadjiDK;

/**
 *
 * @author lukad
 */
public class SOPretraziTakmicenje extends NadjiDK {

    public SOPretraziTakmicenje() {
        setTo(to);
        porukaUspeh = "Uspesno pretrazivanje takmicenja";
        porukaGreska = "Greska pri pretrazivanju takmicenja: " + to.getPoruka();
    }

}
