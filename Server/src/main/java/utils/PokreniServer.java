package utils;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


/**
 *
 * @author Luka
 */
public class PokreniServer extends Thread {

    @Override
    public void run() {
        try {
            ServerSocket serverskiSoket = new ServerSocket(9000);
            
            while(true){
                
                Socket klijentskiSoket = serverskiSoket.accept();
                
            }
            
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    
    
}
