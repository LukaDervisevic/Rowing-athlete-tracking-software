/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.veslac;

import so.PromeniDK;
import transfer.TransferObjekat;

/**
 *
 * @author lukad
 */
public class SOPromeniVeslaca extends PromeniDK{

    public SOPromeniVeslaca(TransferObjekat to) {
        setTo(to);
        porukaUspeh = "Uspesno azuriranje veslaca";
        porukaGreska = "Greska pri azuriranju veslaca: " + to.getPoruka();
    }
    
}
