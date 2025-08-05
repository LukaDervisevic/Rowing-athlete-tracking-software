/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.veslac;

import so.KreirajDK;
import transfer.TransferObjekat;

/**
 *
 * @author lukad
 */
public class SOKreirajVeslaca extends KreirajDK{

    public SOKreirajVeslaca(TransferObjekat to) {
        setTo(to);
        porukaUspesno = "Uspesno kreiranje veslaca";
        porukaGreska = "Greska pri kreiranju veslaca: " + to.getPoruka();
    }  
}
