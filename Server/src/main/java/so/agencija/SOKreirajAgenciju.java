/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.agencija;

import so.KreirajDK;

/**
 *
 * @author lukad
 */
public class SOKreirajAgenciju extends KreirajDK{

    public SOKreirajAgenciju() {
        setTo(to);
        porukaUspesno = "Uspesno kreiranje agencije";
        porukaGreska = "Greska pri kreiranju agencije: " + to.getPoruka();
    }
    
}
