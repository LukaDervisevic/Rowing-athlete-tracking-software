/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.drzava;

import so.KreirajDK;
import transfer.TransferObjekat;

/**
 *
 * @author lukad
 */
public class SOUbaciDrzavu extends KreirajDK{

    public SOUbaciDrzavu(TransferObjekat to) {
        setTo(to);
        porukaUspesno = "Uspesno ubacivanje drzave";
        porukaGreska = "Greska pri ubacivanju drzave: " + to.getPoruka();
    }
    
}
