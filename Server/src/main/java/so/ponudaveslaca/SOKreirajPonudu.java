/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.ponudaveslaca;

import so.KreirajDK;
import transfer.TransferObjekat;

/**
 *
 * @author lukad
 */
public class SOKreirajPonudu extends KreirajDK{

    public SOKreirajPonudu(TransferObjekat to) {
        setTo(to);
        porukaUspesno = "Uspesno kreiranje ponude veslaca";
        porukaGreska = "Greska pri kreiranju ponude veslaca : " + to.getPoruka();
    }
    
    
    
}
