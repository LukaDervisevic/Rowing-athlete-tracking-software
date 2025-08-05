/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.drzava;

import so.ObrisiDK;
import transfer.TransferObjekat;

/**
 *
 * @author lukad
 */
public class SOObrisiDrzavu extends ObrisiDK{

    public SOObrisiDrzavu(TransferObjekat to) {
        setTo(to);
        porukaUspesno = "Uspesno brisanje drzave";
        porukaGreska = "Greska pri brisanju drzave: " + to.getPoruka();
    }
    
}
