/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.agencija;

import model.Agencija;
import model.OpstiDomenskiObjekat;
import ogranicenja.Ogranicenje;
import so.PrijaviDK;
import transfer.TransferObjekat;

/**
 *
 * @author lukad
 */
public class SOPrijaviAgenciju extends PrijaviDK{

    public SOPrijaviAgenciju(TransferObjekat to) {
        setTo(to);
        porukaGreska += " Greska pri prijavi agencije";
        porukaUspeh += " Uspeh pri prijavi agencije";
    }
    
}
