package transfer;

import java.io.Serializable;
import java.util.List;
import model.OpstiDomenskiObjekat;

/**
 *
 * @author luka
 * @param <T>
 */
public class TransferObjekat<T extends OpstiDomenskiObjekat> implements Serializable{
    public OpstiDomenskiObjekat odo;
    public List<T> listOdo;
    public boolean signal;
    public int trenutniSlog;
    public String whereUslov;

    public OpstiDomenskiObjekat getOdo() {
        return odo;
    }

    public void setOdo(OpstiDomenskiObjekat odo) {
        this.odo = odo;
    }
    
    public boolean isSignal() {
        return signal;
    }

    public void setSignal(boolean signal) {
        this.signal = signal;
    }

    public List<T> getListOdo() {
        return listOdo;
    }

    public void setListOdo(List<T> listOdo) {
        this.listOdo = listOdo;
    }

    public int getTrenutniSlog() {
        return trenutniSlog;
    }

    public void setTrenutniSlog(int trenutniSlog) {
        this.trenutniSlog = trenutniSlog;
    }

    public String getWhereUslov() {
        return whereUslov;
    }

    public void setWhereUslov(String whereUslov) {
        this.whereUslov = whereUslov;
    }
}
