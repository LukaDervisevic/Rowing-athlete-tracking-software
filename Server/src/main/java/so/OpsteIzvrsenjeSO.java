package so;

import bbp.BrokerBazePodataka;
import model.OpstiDomenskiObjekat;
import transfer.TransferObjekat;

/**
 *
 * @author luka
 */
public abstract class OpsteIzvrsenjeSO {
    protected TransferObjekat to;
    
    synchronized public boolean opsteIzvrsenjeSO() {
        if(!proveriOgranicenja(to.getOdo())) return false;
        BrokerBazePodataka.getInstance().napraviKonekciju();
        boolean signal = izvrsiSO();
        if(signal) {
            commit();
        }else{
            rollback();
        }
        return signal;
    };
        
    protected abstract boolean izvrsiSO();
    protected abstract boolean proveriOgranicenja(OpstiDomenskiObjekat odo);
    protected void commit() {
        BrokerBazePodataka.getInstance().commitTransakcija();
    }
    protected void rollback() {
        BrokerBazePodataka.getInstance().rollbackTransakcija();
    }


    public TransferObjekat getTo() {
        return to;
    }

    public void setTo(TransferObjekat to) {
        this.to = to;
    }
    
    
    
}
