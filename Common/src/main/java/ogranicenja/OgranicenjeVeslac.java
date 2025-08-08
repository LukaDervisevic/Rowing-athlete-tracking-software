/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ogranicenja;

import java.util.Arrays;

import model.Veslac;
import transfer.TransferObjekat;

/**
 *
 * @author lukad
 */
public class OgranicenjeVeslac extends Ogranicenje{
    // TODO: Razmisliti da li je potrebno imati posebne metode za svaku vrstu so kad bi sve trebale ista ogranicenja da ispune, ali sinisa je radio ovako
    
    @Override
    public boolean prostaVrednosnaOgranicenja(TransferObjekat to) {
        boolean signal = true;
        Veslac veslac = (Veslac) to.getOdo();
        if(veslac.getIdVeslaca() == 0) {
            signal = false;
            to.setPoruka(to.getPoruka() + " Naruseno prosto vrednosno ogranicenje - identifikator veslaca ne moze biti 0");
        }
        if(veslac.getImePrezime() == null || veslac.getImePrezime().isBlank()) {
            signal = false;
            to.setPoruka(to.getPoruka() + " Naruseno prosto vrednosno ogranicenje - ime i prezime veslaca ne moze biti prazno");
        }
        if(veslac.getDatumRodjenja() == null) {
            signal = false;
            to.setPoruka(to.getPoruka() + " Naruseno prosto vrednosno ogranicenje - datum rodjenja ne moze biti prazan");
        }
        if(veslac.getVisina() <= 0) {
            signal = false;
            to.setPoruka(to.getPoruka() + " Naruseno prosto vrednosno ogranicenje - visina veslaca mora biti nenegativna");
        }
        if(veslac.getTezina() <= 0) {
            signal = false;
            to.setPoruka(to.getPoruka() + " Naruseno prosto vrednosno ogranicenje - tezina veslaca mora biti nenegativnas");
        }
        String[] kategorija = {"Kadet","Junior"};
        if(!Arrays.asList(kategorija).contains(veslac.getKategorija().toString())) {
            signal = false;
            to.setPoruka(to.getPoruka() + " Naruseno prosto vrednosno ogranicenje - kategorija ne pripada predefinisanom skupu kategorija");
        }
        if(veslac.getBMI() <= 0) {
            signal = false;
            to.setPoruka(to.getPoruka() + " Naruseno prosto vrednosno ogranicenje - BMI mora biti nenegativan");
        }
        if(veslac.getNajboljeVreme() <= 0) {
            signal = false;
            to.setPoruka(to.getPoruka() + " Naruseno prosto vrednosno ogranicenje - najbolje vreme mora biti nenegativno");
        }
        if(veslac.getDatumUpisa() == null) {
            signal = false;
            to.setPoruka(to.getPoruka() + " Naruseno prosto vrednosno ogranicenje - datum rodjenja ne moze biti prazan");
        }
        
        return signal;
    }

    @Override
    public boolean slozenaVrednosnaOgranicenja(TransferObjekat to) {
        boolean signal = true;
        Veslac veslac = (Veslac) to.getOdo();
        float bmi = (float) (veslac.getVisina() / (Math.pow(veslac.getTezina(), 2)));
        if(bmi <= 0) {
            signal = false;
            to.setPoruka(to.getPoruka() + " Naruseno slozeno strukturno ogranicenje - BMI veslaca mora biti pozitivan");
        }else{
            veslac.setBMI(bmi);
        }
        return signal;
    }

    @Override
    public boolean strukturnoOgranicenje(TransferObjekat to) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
}
