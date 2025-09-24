/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ogranicenja;

import model.Agencija;
import transfer.TransferObjekat;

/**
 *
 * @author lukad
 */
public class OgranicenjeAgencija extends Ogranicenje{
    
    @Override
    public boolean prostaVrednosnaOgranicenja(TransferObjekat to) {
        boolean signal = true;
        Agencija agencija = (Agencija) to.getOdo();
        if(agencija.getKorisnickoIme() == null || agencija.getKorisnickoIme().isBlank()) {
            signal = false;
            to.setPoruka(to.getPoruka() + " Naruseno prosto vrednosno ogranicenje - korisnicko ime ne moze biti prazno");
        }
        if(agencija.getEmail() == null || agencija.getEmail().isBlank()) {
            signal = false;
            to.setPoruka(to.getPoruka() + " Naruseno prosto vrednosno ogranicenje - email korisnika ne moze biti prazan");
        }
        if(!agencija.getEmail().contains("@")) {
            signal = false;
            to.setPoruka(to.getPoruka() + " Naruseno prosto vrednosno ogranicenje - email korisnika mora da sadrzi @ simbol");
        }

        if(agencija.getTelefon() == null || agencija.getTelefon().isBlank()) {
            signal = false;
            to.setPoruka(to.getPoruka() + " Naruseno prosto vrednosno ogranicenje - telefon korisnika ne moze biti prazan");
        }
        if(!agencija.getTelefon().contains("+")) {
            signal = false;
            to.setPoruka(to.getPoruka() + " Naruseno prosto vrednosno ogranicenje - telefon korisnika mora da sadrzi simbol +");
        }
        if(agencija.getTelefon().length() > 13) {
            signal = false;
            to.setPoruka(to.getPoruka() + " Naruseno prosto vrednosno ogranicenje - telefon korisnika ne moze biti duzi od 13 karaktera");
        }
        if(agencija.getSifra() == null || agencija.getSifra().isBlank()) {
            signal = false;
            to.setPoruka(to.getPoruka() + " Naruseno prosto vrednosno ogranicenje - sifra korisnika ne moze biti prazna");
        }
        
        if(agencija.getSifra().length() < 8) {
            signal = false;
            to.setPoruka(to.getPoruka() + " Naruseno prosto vrednosno ogranicenje - sifra korisnika ne moze biti manja od 8 karaktera");
        }
        return signal;
    }

    @Override
    public boolean strukturnoOgranicenje(TransferObjekat to) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
