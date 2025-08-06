/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.takmicenje;

import so.ObrisiDK;
import transfer.TransferObjekat;

/**
 *
 * @author lukad
 */
public class SOObrisiTakmicenje extends ObrisiDK {

    public SOObrisiTakmicenje(TransferObjekat to) {
        setTo(to);
        porukaUspesno = "Uspesno brisanje takmicenja";
        porukaGreska  = "Greska pri brisanju takmicenja: " + to.getPoruka();
    }

}
