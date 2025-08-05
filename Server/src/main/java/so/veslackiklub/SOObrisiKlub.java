/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.veslackiklub;

import so.ObrisiDK;
import transfer.TransferObjekat;

/**
 *
 * @author lukad
 */
public class SOObrisiKlub extends ObrisiDK{

    public SOObrisiKlub(TransferObjekat to) {
        setTo(to);
        porukaUspesno = "Uspesno brisanje veslackog kluba";
        porukaGreska = "Greska pri brisanju veslackog kluba: " + to.getPoruka();
    }
    
}
