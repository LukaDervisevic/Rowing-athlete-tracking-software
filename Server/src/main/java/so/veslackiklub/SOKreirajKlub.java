/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.veslackiklub;

import model.VeslackiKlub;
import so.KreirajDK;
import transfer.TransferObjekat;
import utils.HesiranjeServis;

/**
 *
 * @author lukad
 */
public class SOKreirajKlub extends KreirajDK{
    
    public SOKreirajKlub(TransferObjekat to) {
        this.setTo(to);
        VeslackiKlub vk = (VeslackiKlub) getTo().getOdo();
        vk.setSifra(HesiranjeServis.hesirajSifru(vk.getSifra()));
        porukaUspesno  = "Uspesno kreiranje veslackog kluba";
        porukaGreska = "Greska pri kreiranju veslackog kluba: " + to.getPoruka();
    }
    
}
