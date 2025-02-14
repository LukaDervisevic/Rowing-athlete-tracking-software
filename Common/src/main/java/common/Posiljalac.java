package common;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author luka
 */
public class Posiljalac {
    
    private final Socket soket;

    public Posiljalac(Socket soket) {
        this.soket = soket;
    }
    
    public void posaljiPoruku(Object poruka) throws Exception{
        
        ObjectOutputStream oos;
        
        try{
            
            oos = new ObjectOutputStream(soket.getOutputStream());
            oos.writeObject(poruka);
            oos.flush();
            
        }catch(IOException ex){
            ex.printStackTrace();
            throw new Exception(ex);
        }
        
    }
    
    
}
