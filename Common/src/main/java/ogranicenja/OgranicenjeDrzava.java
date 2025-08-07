/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ogranicenja;

import model.Drzava;
import transfer.TransferObjekat;

/**
 *
 * @author lukad
 */
public class OgranicenjeDrzava extends Ogranicenje{

    @Override
    public boolean prostaVrednosnaOgranicenja(TransferObjekat to) {
        boolean signal = true;
        Drzava drzava = (Drzava) to.getOdo();
        if(drzava.getNaziv() == null || drzava.getNaziv().isBlank()) {
            signal = false;
            to.setPoruka(to.getPoruka() + " " + "Naruseno prosto vrednosno ogranicenje, naziv drzave ne moze biti prazan");
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
