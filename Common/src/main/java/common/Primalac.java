package common;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 *
 * @author luka
 */
public class Primalac {
    
    private final Socket soket;

    public Primalac(Socket soket) {
        this.soket = soket;
    }
    
    public Object primiPoruku() throws Exception {
        
        ObjectInputStream ois;
        
        try{
            
            ois = new ObjectInputStream(soket.getInputStream());
            return ois.readObject();
            
        }catch(IOException ex){
            ex.printStackTrace();
            throw new Error(ex);
        }
        
    }
    
}
