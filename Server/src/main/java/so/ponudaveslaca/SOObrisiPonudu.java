/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.ponudaveslaca;

import so.ObrisiDK;
import transfer.TransferObjekat;

/**
 *
 * @author lukad
 */
public class SOObrisiPonudu extends ObrisiDK {

    public SOObrisiPonudu(TransferObjekat to) {
        setTo(to);
        porukaUspesno = "Uspesno brisanje ponude veslaca";
        porukaGreska = "Greska pri brisanju ponude veslaca";
    }

}
