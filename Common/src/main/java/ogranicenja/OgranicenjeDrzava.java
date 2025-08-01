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
    public boolean preduslovProstaVrednosnaOgranicenja_kreiraj(TransferObjekat to) {
        boolean signal = true;
        Drzava drzava = (Drzava) to.getOdo();
        if(drzava.getNaziv() == null || drzava.getNaziv().isBlank()) {
            signal = false;
            to.setPoruka(to.getPoruka() + " " + "Naruseno prosto vrednosno ogranicenje, naziv drzave ne moze biti prazan");
        }
        return signal;
    }

    @Override
    public boolean preduslovProstaVrednosnaOgranicenja_obrisi(TransferObjekat to) {
        boolean signal = true;
        Drzava drzava = (Drzava) to.getOdo();
        if(drzava.getNaziv() == null || drzava.getNaziv().isBlank()) {
            signal = false;
            to.setPoruka(to.getPoruka() + " " + "Naruseno prosto vrednosno ogranicenje, naziv drzave ne moze biti prazan");
        }
        return signal;
    }

    @Override
    public boolean preduslovProstaVrednosnaOgranicenja_promeni(TransferObjekat to) {
        boolean signal = true;
        Drzava drzava = (Drzava) to.getOdo();
        if(drzava.getNaziv() == null || drzava.getNaziv().isBlank()) {
            signal = false;
            to.setPoruka(to.getPoruka() + " " + "Naruseno prosto vrednosno ogranicenje, naziv drzave ne moze biti prazan");
        }
        return signal;
    }

    
    
    
    
}
