package transfer;

import java.io.Serializable;
import operacije.Operacija;

/**
 *
 * @author luka
 */
public class Zahtev implements Serializable{
    
    private Operacija operacija;
    
    private Object parametar;
    
    public Zahtev(Operacija operacija, Object parametar) {
        this.operacija = operacija;
        this.parametar = parametar;
    }
    
    public Zahtev(){
        
    }

    public Operacija getOperacija() {
        return operacija;
    }

    public void setOperacija(Operacija operacija) {
        this.operacija = operacija;
    }

    public Object getParametar() {
        return parametar;
    }

    public void setParametar(Object parametar) {
        this.parametar = parametar;
    }
    
    
    
}
