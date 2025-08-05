/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.veslac;

import so.NadjiDK;

/**
 *
 * @author lukad
 */
public class SOPretraziVeslaca extends NadjiDK{

    public SOPretraziVeslaca() {
        setTo(to);
        porukaUspeh = "Uspesno pretrazivanje veslaca";
        porukaGreska = "Greska pri pretrazivanju veslaca: " + to.getPoruka();
    }
    
}
