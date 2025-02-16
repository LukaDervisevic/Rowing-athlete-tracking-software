package klijent;

import java.io.IOException;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Agencija;
import model.Drzava;
import model.KlubTakmicenje;
import model.Nalog;
import model.PonudaVeslaca;
import model.StavkaPonude;
import model.Takmicenje;
import model.Veslac;
import model.VeslackiKlub;
import operacije.Odgovor;
import operacije.Operacija;
import operacije.Posiljalac;
import operacije.Primalac;
import operacije.StatusPoruke;
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
    
    private Socket soket;
    
    private Klijent(){
        try {
            soket = new Socket("localhost",9000);
            primalac = new Primalac(soket);
            posiljalac = new Posiljalac(soket);
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
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
    
    

    public Nalog login(Nalog nalog) throws Exception {
        Zahtev zahtev= new Zahtev(Operacija.PRIJAVA,nalog);
        posiljalac.posaljiPoruku(zahtev);
        
        Odgovor odgovor = (Odgovor) primalac.primiPoruku();
        
        if(odgovor.getStatus().equals(StatusPoruke.GRESKA)){
            throw new Exception((Throwable) odgovor.getParametar());
        }else{
            return (Nalog) odgovor.getParametar();
        }
        
    }

    public VeslackiKlub kreirajVeslackiKlub(VeslackiKlub veslackiKlub) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.KREIRANJE_KLUB,veslackiKlub);
        posiljalac.posaljiPoruku(zahtev);
        
        Odgovor odgovor = (Odgovor) primalac.primiPoruku();
        
        if(odgovor.getStatus().equals(StatusPoruke.GRESKA)){
            throw new Exception((Throwable) odgovor.getParametar());
        }else{
            return (VeslackiKlub) odgovor.getParametar();
        }
    }

    public Agencija kreirajAgenciju(Agencija agencija) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.KREIRANJE_AGENCIJA,agencija);
        posiljalac.posaljiPoruku(zahtev);
        
        Odgovor odgovor = (Odgovor) primalac.primiPoruku();
        
        if(odgovor.getStatus().equals(StatusPoruke.GRESKA)){
            throw new Exception((Throwable) odgovor.getParametar());
        }else{
            return (Agencija) odgovor.getParametar();
        }
    }

    public List<Drzava> vratiSveDrzave() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Integer obrisiVeslackiKlub(Integer idKluba) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.BRISANJE_KLUB,idKluba);
        posiljalac.posaljiPoruku(zahtev);
        
        Odgovor odgovor = (Odgovor) primalac.primiPoruku();
        
        if(odgovor.getStatus().equals(StatusPoruke.GRESKA)){
            throw new Exception((Throwable) odgovor.getParametar());
        }else{
            return (Integer) odgovor.getParametar();
        }
    }

    public VeslackiKlub azuirirajVeslackiKlub(VeslackiKlub veslackiKlub) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.PROMENA_KLUB,veslackiKlub);
        posiljalac.posaljiPoruku(zahtev);
        
        Odgovor odgovor = (Odgovor) primalac.primiPoruku();
        
        if(odgovor.getStatus().equals(StatusPoruke.GRESKA)){
            throw new Exception((Throwable) odgovor.getParametar());
        }else{
            return (VeslackiKlub) odgovor.getParametar();
        }
    }

    public List<Veslac> vratiSveVeslace(Integer idKluba) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.VRATI_SVE_VESLACE,idKluba);
        posiljalac.posaljiPoruku(zahtev);
        
        Odgovor odgovor = (Odgovor) primalac.primiPoruku();
        
        if(odgovor.getStatus().equals(StatusPoruke.GRESKA)){
            throw new Exception((Throwable) odgovor.getParametar());
        }else{
            return (List<Veslac>) odgovor.getParametar();
        }
    }

    public List<KlubTakmicenje> vratiTakmicenjaKluba(int idKluba) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<PonudaVeslaca> vratiSvePonudeKluba(int idKluba) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<Agencija> vratiSveAgencije() throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.VRATI_SVE_VESLACE,null);
        posiljalac.posaljiPoruku(zahtev);
        
        Odgovor odgovor = (Odgovor) primalac.primiPoruku();
        
        if(odgovor.getStatus().equals(StatusPoruke.GRESKA)){
            throw new Exception((Throwable) odgovor.getParametar());
        }else{
            return (List<Agencija>) odgovor.getParametar();
        }
    }

    
    public List<PonudaVeslaca> pretraziPonudu(PonudaVeslaca ponuda) throws Exception {        
        return null;
    }

    public LinkedList<Veslac> pretraziVeslaca(Veslac veslac) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.PRETRAZIVANJE_VESLAC,veslac);
        posiljalac.posaljiPoruku(zahtev);
        
        Odgovor odgovor = (Odgovor) primalac.primiPoruku();
        
        if(odgovor.getStatus().equals(StatusPoruke.GRESKA)){
            throw new Exception((Throwable) odgovor.getParametar());
        }else{
            return (LinkedList<Veslac>) odgovor.getParametar();
        }
    }

    public List<Takmicenje> pretraziTakmicenja(Takmicenje takmicenje) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public LinkedList<Takmicenje> vratiSvaTakmicenja() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Veslac kreirajVeslaca(Veslac veslac) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.KREIRANJE_VESLAC,veslac);
        posiljalac.posaljiPoruku(zahtev);
        
        Odgovor odgovor = (Odgovor) primalac.primiPoruku();
        
        if(odgovor.getStatus().equals(StatusPoruke.GRESKA)){
            throw new Exception((Throwable) odgovor.getParametar());
        }else{
            return (Veslac) odgovor.getParametar();
        }
    }

    public Integer obrisiVeslaca(Integer id) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.BRISANJE_VESLAC,id);
        posiljalac.posaljiPoruku(zahtev);
        
        Odgovor odgovor = (Odgovor) primalac.primiPoruku();
        
        if(odgovor.getStatus().equals(StatusPoruke.GRESKA)){
            throw new Exception((Throwable) odgovor.getParametar());
        }else{
            return (Integer) odgovor.getParametar();
        }
    }

    public Takmicenje dodajTakmicenje(Takmicenje takmicenje) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void obrisiTakmicenje(int idTakmicenja) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    // Predstavlja problem
    
    public KlubTakmicenje dodajOsvojenoTakmicenje(int mesto, int idTakmicenja, int idKluba) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    // Predstavlja problem
    public void obrisiOsvojenoTakmicenje(int idTakmicenja, int mesto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void obrisiPonudu(int idPonude) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Veslac vratiVeslacaPoId(int idVeslaca) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.VRATI_VESLACA_PO_ID,idVeslaca);
        posiljalac.posaljiPoruku(zahtev);
        
        Odgovor odgovor = (Odgovor) primalac.primiPoruku();
        
        if(odgovor.getStatus().equals(StatusPoruke.GRESKA)){
            throw new Exception((Throwable) odgovor.getParametar());
        }else{
            return (Veslac) odgovor.getParametar();
        }
    }
    
    // Predstavlja problem
    
    public PonudaVeslaca kreirajPonuduVeslaca(int idAgencije, int idKluba, List<StavkaPonude> stavkePonude) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    // Predstavlja problem
    
    public int[] prebrojOsvojenaTakmicenja() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int vratiPoslednjiIdPonude() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<PonudaVeslaca> vratiSvePonudeAgencije(int idAgencije) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<VeslackiKlub> vratiSveKlubove() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<StavkaPonude> vratiSveStavkePonude(int idPonude) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<VeslackiKlub> pretraziKlub(VeslackiKlub veslackiKlub) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Veslac azurirajVeslaca(Veslac veslac) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.PROMENA_VESLAC,veslac);
        posiljalac.posaljiPoruku(zahtev);
        
        Odgovor odgovor = (Odgovor) primalac.primiPoruku();
        
        if(odgovor.getStatus().equals(StatusPoruke.GRESKA)){
            throw new Exception((Throwable) odgovor.getParametar());
        }else{
            return (Veslac) odgovor.getParametar();
        }
    }

    public Agencija azurirajAgenciju(Agencija agencija) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.PROMENA_AGENCIJA,agencija);
        posiljalac.posaljiPoruku(zahtev);
        
        Odgovor odgovor = (Odgovor) primalac.primiPoruku();
        
        if(odgovor.getStatus().equals(StatusPoruke.GRESKA)){
            throw new Exception((Throwable) odgovor.getParametar());
        }else{
            return (Agencija) odgovor.getParametar();
        }
    }

    public Integer obrisiAgenciju(Integer id) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.PROMENA_AGENCIJA,id);
        posiljalac.posaljiPoruku(zahtev);
        
        Odgovor odgovor = (Odgovor) primalac.primiPoruku();
        
        if(odgovor.getStatus().equals(StatusPoruke.GRESKA)){
            throw new Exception((Throwable) odgovor.getParametar());
        }else{
            return (Integer) odgovor.getParametar();
        }
    }
    
    
    
}
