/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.veslac;

import so.ObrisiDK;
import transfer.TransferObjekat;

/**
 *
 * @author lukad
 */
public class SOObrisiVeslaca extends ObrisiDK{

    public SOObrisiVeslaca(TransferObjekat to) {
        setTo(to);
        porukaUspesno = "Uspesno brisanje veslaca";
        porukaGreska = "Greska pri brisanju veslaca: " + to.getPoruka();
    }
}
