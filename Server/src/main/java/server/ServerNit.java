package server;

import controller.Controller;
import java.net.Socket;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Agencija;
import model.Drzava;
import model.KategorijaVeslaca;
import model.Nalog;
import model.PonudaVeslaca;
import model.Takmicenje;
import model.Veslac;
import model.VeslackiKlub;
import model.VrstaTrke;
import operacije.Operacija;
import operacije.Posiljalac;
import operacije.Primalac;
import operacije.Zahtev;

/**
 *
 * @author luka
 */
class ServerNit extends Thread{

    Socket soketZaKomunikaciju;
    List<ServerNit> klijenti;
    Posiljalac posiljalac;
    Primalac primalac;

    public ServerNit(Socket soketZaKomunikaciju, List<ServerNit> klijenti){
        this.soketZaKomunikaciju = soketZaKomunikaciju;
        this.klijenti = klijenti;
        this.posiljalac = new Posiljalac(soketZaKomunikaciju);
        this.primalac = new Primalac(soketZaKomunikaciju);
    }

    @Override
    public void run() {
        
        try {
            // inicijalizacija object stream-ova
            
            while(true){
                try {
                    // Primanje zahteva od korisnika
                    Zahtev korisnikovZahtev = (Zahtev) primalac.primiPoruku();
                    // Obrada Zahteva
                    
                    Object odgovorServera = obradiZahtev(korisnikovZahtev);
                    
                    //Slanje zahteva
                    posiljalac.posaljiPoruku(odgovorServera);
                    
                    
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                
                
            }

        } catch (Exception ex) {
            Logger.getLogger(ServerNit.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    private synchronized Object obradiZahtev(Zahtev korisnikovZahtev) throws Exception{
        
        Object objekat = null;
        
        switch (korisnikovZahtev.getOperacija()) {
            case Operacija.PRIJAVA:
                return Controller.getInstance().login((Nalog) korisnikovZahtev.getParametar());
                
            case Operacija.REGISTRACIJA_KLUB:
                return Controller.getInstance().kreirajVeslackiKlub((VeslackiKlub) korisnikovZahtev.getParametar());
                
            case Operacija.PROMENA_KLUB:
                return Controller.getInstance().azuirirajVeslackiKlub((VeslackiKlub) korisnikovZahtev.getParametar());
                
            case Operacija.PRETRAZIVANJE_KLUB:
                return Controller.getInstance().pretraziKlub((String) korisnikovZahtev.getParametar());
                
            case Operacija.BRISANJE_KLUB:
                return Controller.getInstance().obrisiVeslackiKlub((Integer) korisnikovZahtev.getParametar());
                
            case Operacija.REGISTRACIJA_AGENCIJA:
                return Controller.getInstance().kreirajAgenciju((Agencija) korisnikovZahtev.getParametar());
                
            case Operacija.PROMENA_AGENCIJA:
                return Controller.getInstance().azurirajAgenciju((Agencija) korisnikovZahtev.getParametar());
                
//            case Operacija.PRETRAZIVANJE_AGENCIJA:
                
            case Operacija.BRISANJE_AGENCIJA:
                return Controller.getInstance().obrisiAgenciju((Integer) korisnikovZahtev.getParametar());
            
            case Operacija.KREIRANJE_VESLAC:
                return Controller.getInstance().kreirajVeslaca((Veslac) korisnikovZahtev.getParametar());
                
            case Operacija.PRETRAZIVANJE_VESLAC:
                return Controller.getInstance().pretraziVeslaca((Veslac) korisnikovZahtev.getParametar());
                
            case Operacija.PROMENA_VESLAC:
                return Controller.getInstance().azurirajVeslaca((Veslac) korisnikovZahtev.getParametar());
                
            case Operacija.BRISANJE_VESLAC:
                return Controller.getInstance().obrisiVeslaca((Integer) korisnikovZahtev.getParametar());
            
            case Operacija.UBACIVANJE_TAKMICENJE:
                return Controller.getInstance().dodajTakmicenje((Takmicenje) korisnikovZahtev.getParametar());
                
            case Operacija.PRETRAZIVANJE_TAKMICENJE:
                return Controller.getInstance().pretraziTakmicenja((String) korisnikovZahtev.getParametar());
                
            case Operacija.BRISANJE_TAKMICENJE:
                return Controller.getInstance().obrisiTakmicenje((Integer) korisnikovZahtev.getParametar());
                
            case Operacija.KREIRANJE_PONUDE:
                return Controller.getInstance().kreirajPonuduVeslaca((PonudaVeslaca) korisnikovZahtev.getParametar());
                
            case Operacija.PRETRAZIVANJE_PONUDE:
                return Controller.getInstance().pretraziPonudu((PonudaVeslaca) korisnikovZahtev.getParametar());
                
             //case Operacija.PROMENA_PONUDE: 
             
            case Operacija.BRISANJE_PONUDE:
                return Controller.getInstance().obrisiPonudu((Integer) korisnikovZahtev.getParametar());
                
            case Operacija.UBACIVANJE_DRZAVA:
//                return Controller.getInstance().dodajDrzavu((Drzava) korisnikovZahtev.getParametar());
                
            case Operacija.BRISANJE_DRZAVA:
//                return Controller.getInstance().obrisiDrzavu((Integer) korisnikovZahtev.getParametar());
                
            
            
            default:
                throw new AssertionError();
        }
        
        
        
    }
    
    
}
