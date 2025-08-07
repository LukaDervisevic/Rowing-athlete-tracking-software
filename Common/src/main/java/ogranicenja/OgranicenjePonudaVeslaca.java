/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ogranicenja;

import model.PonudaVeslaca;
import transfer.TransferObjekat;

/**
 *
 * @author lukad
 */
public class OgranicenjePonudaVeslaca extends Ogranicenje{

    @Override
    public boolean prostaVrednosnaOgranicenja(TransferObjekat to) {
        boolean signal = true;
        PonudaVeslaca ponudaVeslaca = (PonudaVeslaca) to.getOdo();
        if(ponudaVeslaca.getId() <= 0) {
            signal = false;
            to.setPoruka(to.getPoruka() + " Naruseno prosto vrednosno ogranicenje - Identifikator ponude veslaca mora biti pozitivan");
        }
        if(ponudaVeslaca.getDatumKreiranja() == null) {
            signal = false;
            to.setPoruka(to.getPoruka() + " Naruseno prosto vrednosno ogranicenje - Datum kreiranja ne sme biti prazan  ");
        }
        if(ponudaVeslaca.getBrojKadeta() < 0) {
            signal = false;
            to.setPoruka(to.getPoruka() + " Naruseno prosto vrednosno ogranicenje - Broj kadeta ponude mora biti nenegativan");
        }
        if(ponudaVeslaca.getBrojJuniora() < 0) {
            signal = false;
            to.setPoruka(to.getPoruka() + " Naruseno prosto vrednosno ogranicenje - Broj juniora ponude mora biti nenegativan"); 
        }
        if(ponudaVeslaca.getProsecnoVremeJuniori() < 0) {
            signal = false;
            to.setPoruka(to.getPoruka() + " Naruseno prosto vrednosno ogranicenje - Prosecno vreme juniora mora biti nenegativno");
        }
        if(ponudaVeslaca.getProsecnoVremeKadeti() < 0) {
            signal = false;
            to.setPoruka(to.getPoruka() + " Naruseno prosto vrednosno ogranicenje - Prosecno vreme kadeta mora biti nenegativno");
        }
        if(ponudaVeslaca.getIdKluba() <= 0) {
            signal = false;
            to.setPoruka(to.getPoruka() + " Naruseno prosto vrednosno ogranicenje - Identifikator veslackog kluba ponude mora biti pozitivan");
        }
        if(ponudaVeslaca.getIdAgencije() <= 0) {
            signal = false;
            to.setPoruka(to.getPoruka() + " Naruseno prosto vrednosno ogranicenje - Identifikator agencije ponude mora biti pozitivan");
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
