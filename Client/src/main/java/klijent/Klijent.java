package klijent;

import java.time.LocalDateTime;
import java.util.List;
import model.Agencija;
import model.Drzava;
import model.KlubTakmicenje;
import model.Nalog;
import model.PonudaVeslaca;
import model.Veslac;
import model.VeslackiKlub;
import operacije.Operacija;
import operacije.Posiljalac;
import operacije.Primalac;
import operacije.Zahtev;

/**
 *
 * @author luka
 */
public class Klijent {
    
    private static Klijent instance;
    
    private Nalog ulogovaniNalog;
    
    private boolean odjavaSignal;
    
    private String uuid;
    
    private LocalDateTime vremeKreiranjaNaloga;
    
    private Primalac primalac;
    
    private Posiljalac posiljalac;
    
    private Klijent(){
        
    }

    public static Klijent getInstance() {
        if(instance == null)
            instance = new Klijent();
        return instance;
    }

    public Nalog getUlogovaniNalog() {
        return ulogovaniNalog;
    }

    public void setUlogovaniNalog(Nalog ulogovaniNalog) {
        this.ulogovaniNalog = ulogovaniNalog;
    }

    public boolean isOdjavaSignal() {
        return odjavaSignal;
    }

    public void setOdjavaSignal(boolean odjavaSignal) {
        this.odjavaSignal = odjavaSignal;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public LocalDateTime getVremeKreiranjaNaloga() {
        return vremeKreiranjaNaloga;
    }

    public void setVremeKreiranjaNaloga(LocalDateTime vremeKreiranjaNaloga) {
        this.vremeKreiranjaNaloga = vremeKreiranjaNaloga;
    }
    
    

    public Nalog login(Nalog nalog) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public VeslackiKlub kreirajVeslackiKlub(VeslackiKlub veslackiKlub) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Agencija kreirajAgenciju(Agencija agencija) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<Drzava> vratiSveDrzave() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public VeslackiKlub obrisiVeslackiKlub(Integer integer) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public VeslackiKlub azuirirajVeslackiKlub(VeslackiKlub veslackiKlub) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<Veslac> vratiSveVeslace() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<KlubTakmicenje> vratiTakmicenjaKluba(int idKluba) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<PonudaVeslaca> vratiSvePonudeKluba(int idKluba) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<Agencija> vratiSveAgencije() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    public List<PonudaVeslaca> pretraziPonudu(int idAgencije, int idKluba) throws Exception {
        PonudaVeslaca ponuda = new PonudaVeslaca(0,null,0,0,0,0,idKluba,idAgencije);
        Zahtev zahtev = new Zahtev(Operacija.PRETRAZIVANJE_PONUDE,ponuda);
        posiljalac.posaljiPoruku(zahtev);
    }
    
    
    
}
