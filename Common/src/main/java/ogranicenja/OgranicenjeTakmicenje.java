/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ogranicenja;

import java.util.Arrays;

import model.Takmicenje;
import transfer.TransferObjekat;
/**
 *
 * @author lukad
 */
public class OgranicenjeTakmicenje extends Ogranicenje{

    // TODO: Razmisliliti da li spojiti ogranicenja preduslova i postuslova zajedno i da li kreirati id svakog objekta manuelno ili pomoc auto increment
    // auto increment se kosi sa dokumentacijom
    // Namestiti format enuma da se slaze sa ovim ogranicenjima
    // Ogranicenja svakog objekta moraju biti ispostovana u svakom trenutku promeni da sva ogranicenja vaze za svaku operaciju
    @Override
    public boolean prostaVrednosnaOgranicenja(TransferObjekat to) {
        boolean signal = true;
        Takmicenje takmicenje = (Takmicenje) to.getOdo();
        if(takmicenje.getId() == 0) {
           signal = false;
           to.setPoruka(to.getPoruka() + "Naruseno prosto vrednosno ogranicenje - identifikator takmicenja ne sme biti 0");
        }
        if(takmicenje.getNaziv() == null || takmicenje.getNaziv().isBlank()) {
            signal = false;
            to.setPoruka(to.getPoruka() + " Naruseno prosto vrednosno ogranicenje - naziv takmicenje ne sme biti prazan");
        }
        String[] kategorija = {"Kadet","Junior"};
        if(!Arrays.asList(kategorija).contains(takmicenje.getStarosnaKategorija().toString())) {
            signal = false;
            to.setPoruka(to.getPoruka() + "Naruseno prosto vrednosno ogranicenje - starosna kategorija nije iz definisanog skupa kategorija");
        }
        String[] vrstaTrke = {"Skif","Dubl","Dvojac","Cetverac","Osmerac"};
        if(!Arrays.asList(vrstaTrke).contains(takmicenje.getVrstaTrke().toString())) {
            signal = false;
            to.setPoruka(to.getPoruka() + "Naruseno prosto vrednosno ogranicenje - vrsta trke nije iz definisanog skupa trka");
        }
        if(takmicenje.getDatum() == null) {
            signal = false;
            to.setPoruka(to.getPoruka() + "Naruseno prosto vrednosno ogranicenje - datum trke ne sme biti prazan");
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
