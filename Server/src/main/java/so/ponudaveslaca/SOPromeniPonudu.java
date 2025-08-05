/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.ponudaveslaca;

import so.PromeniDK;
import transfer.TransferObjekat;

/**
 *
 * @author lukad
 */
public class SOPromeniPonudu extends PromeniDK{

    public SOPromeniPonudu(TransferObjekat to) {
        setTo(to);
        porukaUspeh = "Uspesno azuriranje ponude veslaca";
        porukaGreska = "Greska pri azuriranju ponuda veslaca";
    }
    
}
