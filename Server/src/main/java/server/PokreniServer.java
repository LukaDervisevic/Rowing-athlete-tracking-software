package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Luka
 */
public class PokreniServer{
    
    List<ServerNit> klijentiServera;
    ServerSocket serverskiSoket;
    
    public PokreniServer(){
        klijentiServera = new ArrayList<>();
    }

    public static void main(String[] args) {
        try {
            PokreniServer server = new PokreniServer();
            server.serverskiSoket = new ServerSocket(9000);
            
            
            while(true){
                Socket klijentskiSoket = server.serverskiSoket.accept();
                server.klijentiServera.add(new ServerNit(klijentskiSoket,server.klijentiServera));
                System.out.println("Uspesno povezan klijent");
                
            }
            
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    
    
    
    
}
