/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ogranicenja;

import model.KlubTakmicenje;
import transfer.TransferObjekat;

/**
 *
 * @author lukad
 */
public class OgranicenjeKlubTakmicenje extends Ogranicenje{

    @Override
    public boolean prostaVrednosnaOgranicenja(TransferObjekat to) {
        boolean signal = true;
        KlubTakmicenje klubTakmicenje = (KlubTakmicenje) to.getOdo();
        if(klubTakmicenje.getKlub() == null || klubTakmicenje.getKlub().getId() == 0) {
            signal = false;
            to.setPoruka(to.getPoruka() + " Naruseno prosto vrednosno ogranicenje - identifikator kluba ne moze biti 0");
        }
        if(klubTakmicenje.getTakmicenje() == null || klubTakmicenje.getTakmicenje().getId() == 0) {
            signal = false;
            to.setPoruka(to.getPoruka() + " Naruseno prosto vrednosno ogranicenje - identifikator takmicenja ne moze biti 0");
        }
        if(klubTakmicenje.getMesto() > 3 || klubTakmicenje.getMesto() < 1) {
            signal = false;
            to.setPoruka(to.getPoruka() + " Naruseno prosto vrednosno ogranicenje - mesto ne moze biti van predefinasnog opsega");
        }
        return signal;
    }

    @Override
    public boolean slozenaVrednosnaOgranicenja(TransferObjekat to) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean strukturnoOgranicenje(TransferObjekat to) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
