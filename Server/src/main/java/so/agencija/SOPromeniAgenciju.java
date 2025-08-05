/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.agencija;

import so.PromeniDK;
import transfer.TransferObjekat;

/**
 *
 * @author lukad
 */
public class SOPromeniAgenciju extends PromeniDK{

    public SOPromeniAgenciju(TransferObjekat to) {
        setTo(to);
        porukaUspeh = "Uspesno azuriranje agencije";
        porukaGreska = "Greska pri azuriranju agencije: " + to.getPoruka();
    }
    
}
