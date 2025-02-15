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
public class PokreniServer extends Thread {
    
    List<ServerNit> klijentiServera;
    
    public PokreniServer(){
        klijentiServera = new ArrayList<>();
    }

    @Override
    public void run() {
        try {
            ServerSocket serverskiSoket = new ServerSocket(9000);
            
            while(true){
                
                Socket klijentskiSoket = serverskiSoket.accept();
                klijentiServera.add(new ServerNit(klijentskiSoket, klijentiServera));
                klijentiServera.get(klijentiServera.size() - 1).start();
                
            }
            
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    
    
}
