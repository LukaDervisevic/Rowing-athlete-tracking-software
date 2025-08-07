/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ogranicenja;

import model.VeslackiKlub;
import transfer.TransferObjekat;

/**
 *
 * @author lukad
 */
public class OgranicenjeVeslackiKlub extends Ogranicenje{

    @Override
    public boolean prostaVrednosnaOgranicenja(TransferObjekat to) {
        boolean signal = true;
        VeslackiKlub veslackiKlub = (VeslackiKlub) to.getOdo();
        if(veslackiKlub.getKorisnickoIme() == null || veslackiKlub.getKorisnickoIme().isBlank()) {
            signal = false;
            to.setPoruka(to.getPoruka() + " Naruseno prosto vrednosno ogranicenje - korisnicko ime ne moze biti prazno");
        }
        if(veslackiKlub.getEmail() == null || veslackiKlub.getEmail().isBlank()) {
            signal = false;
            to.setPoruka(to.getPoruka() + " Naruseno prosto vrednosno ogranicenje - email korisnika ne moze biti prazan");
        }
        if(!veslackiKlub.getEmail().contains("@")) {
            signal = false;
            to.setPoruka(to.getPoruka() + " Naruseno prosto vrednosno ogranicenje - email korisnika mora da sadrzi @ simbol");
        }
        if(veslackiKlub.getAdresa() == null || veslackiKlub.getAdresa().isBlank()) {
            signal = false;
            to.setPoruka(to.getPoruka() + " Naruseno prosto vrednosno ogranicenje - adresa korisnika ne moze biti prazna");
        }
        if(veslackiKlub.getTelefon() == null || veslackiKlub.getTelefon().isBlank()) {
            signal = false;
            to.setPoruka(to.getPoruka() + " Naruseno prosto vrednosno ogranicenje - telefon korisnika ne moze biti prazan");
        }
        if(!veslackiKlub.getTelefon().contains("+")) {
            signal = false;
            to.setPoruka(to.getPoruka() + " Naruseno prosto vrednosno ogranicenje - telefon korisnika mora da sadrzi simbol +");
        }
        if(veslackiKlub.getTelefon().length() > 13) {
            signal = false;
            to.setPoruka(to.getPoruka() + " Naruseno prosto vrednosno ogranicenje - telefon korisnika ne moze biti duzi od 13 karaktera");
        }
        if(veslackiKlub.getSifra() == null || veslackiKlub.getSifra().isBlank()) {
            signal = false;
            to.setPoruka(to.getPoruka() + " Naruseno prosto vrednosno ogranicenje - sifra korisnika ne moze biti prazna");
        }
        
        if(veslackiKlub.getSifra().length() < 8) {
            signal = false;
            to.setPoruka(to.getPoruka() + " Naruseno prosto vrednosno ogranicenje - sifra korisnika ne moze biti manja od 8 karaktera");
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
