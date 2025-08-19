/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.agencija;

import model.Agencija;
import so.KreirajDK;
import transfer.TransferObjekat;
import utils.HesiranjeServis;

/**
 *
 * @author lukad
 */
public class SOKreirajAgenciju extends KreirajDK{

    public SOKreirajAgenciju(TransferObjekat to) {
        setTo(to);
        Agencija agencija = (Agencija) to.getOdo();
        agencija.setSifra(HesiranjeServis.hesirajSifru(agencija.getSifra()));
        porukaUspesno = "Uspesno kreiranje agencije";
        porukaGreska = "Greska pri kreiranju agencije: " + to.getPoruka();
    }
    
}
