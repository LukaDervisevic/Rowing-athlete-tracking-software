package operacije;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

/**
 *
 * @author luka
 */
public class Posiljalac implements Serializable{
    
    private final Socket soket;
    private final ObjectOutputStream oos;

    public Posiljalac(Socket soket) throws IOException {
        this.soket = soket;
        this.oos = new ObjectOutputStream(soket.getOutputStream());
    }
    
    public void posaljiPoruku(Object poruka) throws Exception{
            oos.writeObject(poruka);
            oos.flush();
                    
    }
    
    
}
