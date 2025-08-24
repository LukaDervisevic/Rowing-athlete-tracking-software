/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ogranicenja;

import java.util.Date;
import model.StavkaPonude;
import transfer.TransferObjekat;

/**
 *
 * @author lukad
 */
public class OgranicenjeStavkaPonude extends Ogranicenje{

    @Override
    public boolean prostaVrednosnaOgranicenja(TransferObjekat to) {
        boolean signal = true;
        StavkaPonude stavkaPonude = (StavkaPonude) to.getOdo();
        if(stavkaPonude.getIdEvidencije() <= 0) {
            signal = false;
            to.setPoruka(to.getPoruka() + " Naruseno prosto vrednosno ogranicenje - identifikator evidencije veslaca mora biti pozitivan");
        }
        if(stavkaPonude.getRb() <= 0) {
            signal = false;
            to.setPoruka(to.getPoruka() + " Naruseno prosto vrednosno ogranicenje - redni broj stavke mora biti pozitivan");
        }
        if(stavkaPonude.getGodineTreniranja() < 0) {
            signal = false;
            to.setPoruka(to.getPoruka() + " Naruseno prosto vrednosno ogranicenje - Godine treniranja moraju biti nenegativne");
        }
        
        if(stavkaPonude.getVeslac().getId() <= 0) {
            signal = false;
            to.setPoruka(to.getPoruka() + " Naruseno prosto vrednosno ogranicenje - Identifikator veslaca mora biti pozitivan ");
        }
        
        return signal;
    }

    @Override
    public boolean slozenaVrednosnaOgranicenja(TransferObjekat to) {
        boolean signal = true;
        StavkaPonude stavkaPonude = (StavkaPonude) to.getOdo();
        long vremeUpisa = stavkaPonude.getVeslac().getDatumUpisa().getTime();
        long trenutnoVreme = new Date().getTime();
        long razlika = trenutnoVreme - vremeUpisa;
        int godine = (int) (razlika / (1000L * 60 * 60 * 24 * 365));
        if(godine < 0) {
            signal = false;
            to.setPoruka(to.getPoruka() + " Naruseno slozeno vrednosno ogranicenje - godine treniranja moraju biti nenegativne");
        }else{
            stavkaPonude.setGodineTreniranja(godine);
        }
        return signal;
    }

    @Override
    public boolean strukturnoOgranicenje(TransferObjekat to) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
