/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.agencija;

import so.ObrisiDK;
import transfer.TransferObjekat;

/**
 *
 * @author lukad
 */
public class SOObrisiAgenciju extends ObrisiDK{

    public SOObrisiAgenciju(TransferObjekat to) {
        setTo(to);
        porukaUspesno = "Uspesno brisanje agencije";
        porukaGreska = "Greska pri brisanju agencije: " + to.getPoruka();
    }
    
}
