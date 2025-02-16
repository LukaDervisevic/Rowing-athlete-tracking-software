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
public class Server{
    
    List<ServerNit> klijentiServera;
    ServerSocket serverskiSoket;
    
    public Server(){
        klijentiServera = new ArrayList<>();
    }

    public static void main(String[] args) {
        try {
            Server server = new Server();
            server.serverskiSoket = new ServerSocket(9000);
            System.out.println("Pokretanje servera na portu 9000...");
            
            
            while(true){
                Socket klijentskiSoket = server.serverskiSoket.accept();
                server.klijentiServera.add(new ServerNit(klijentskiSoket,server.klijentiServera));
                server.klijentiServera.get(server.klijentiServera.size() -1).start();
                System.out.println("Uspesno povezan klijent");
                
            }
            
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    
    
    
    
}
