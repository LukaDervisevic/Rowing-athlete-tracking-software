package server;

import controller.Controller;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import operacije.Odgovor;
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
    
    private synchronized Object obradiZahtev(Zahtev korisnikovZahtev){
        
        Object objekat = null;
        
        switch (korisnikovZahtev.getOperacija()) {
            case Operacija.PRIJAVA:
                return Controller.getInstance().login(korisnickoIme, sifraKorisnika);
                
                break;
            default:
                throw new AssertionError();
        }
        
        
        return objekat;
        
    }
    
    
}
