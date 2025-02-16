package operacije;

import java.io.Serializable;

/**
 *
 * @author luka
 */
public class Odgovor implements Serializable{
    
    private StatusPoruke status;
    
    private Object parametar;

    public Odgovor(StatusPoruke status, Object parametar) {
        this.status = status;
        this.parametar = parametar;
    }
    
    public Odgovor(){
        
    }

    public StatusPoruke getStatus() {
        return status;
    }

    public void setStatus(StatusPoruke status) {
        this.status = status;
    }

    public Object getParametar() {
        return parametar;
    }

    public void setParametar(Object parametar) {
        this.parametar = parametar;
    }
    
    
    
}
