package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luka
 */
class ServerNit extends Thread{

    BufferedReader ulazniTokOdKlijenta;
    PrintStream izlazniTokKaKlijentu;
    Socket soketZaKomunikaciju;
    List<ServerNit> klijenti;

    public ServerNit(Socket soketZaKomunikaciju, List<ServerNit> klijenti){
        this.soketZaKomunikaciju = soketZaKomunikaciju;
        this.klijenti = klijenti;
    }

    @Override
    public void run() {
        
        try {
            ulazniTokOdKlijenta = new BufferedReader(new InputStreamReader(soketZaKomunikaciju.getInputStream()));
            izlazniTokKaKlijentu = new PrintStream(soketZaKomunikaciju.getOutputStream());
            
            while(true){
                
                
                
            }

        } catch (IOException ex) {
            Logger.getLogger(ServerNit.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
    }
    
    
    
    
}
