/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import bbp.BrokerBazePodataka;
import model.OpstiDomenskiObjekat;
import transfer.TransferObjekat;

/**
 *
 * @author luka
 */
public abstract class OpsteIzvrsenjeSO {
    protected BrokerBazePodataka bbp = new BrokerBazePodataka("veslanje");
    protected int trenutniSlog = -1;
    protected OpstiDomenskiObjekat odo;
    protected TransferObjekat to;
    
    synchronized public boolean opsteIzvrsenjeSO() {
        if(!proveriOgranicenja(to.getOdo())) return false;
        bbp.napraviKonekciju();
        boolean signal = izvrsiSO();
        if(signal) {
            bbp.commitTransakcija();
        }else{
            bbp.rollbackTransakcija();
        }
        return signal;
    };
        
    protected abstract boolean izvrsiSO();
    protected abstract boolean proveriOgranicenja(OpstiDomenskiObjekat odo);
    
    public BrokerBazePodataka getBbp() {
        return bbp;
    }

    public void setBbp(BrokerBazePodataka bbp) {
        this.bbp = bbp;
    }

    public int getTrenutniSlog() {
        return trenutniSlog;
    }

    public void setTrenutniSlog(int trenutniSlog) {
        this.trenutniSlog = trenutniSlog;
    }

    public OpstiDomenskiObjekat getOdo() {
        return odo;
    }

    public void setOdo(OpstiDomenskiObjekat odo) {
        this.odo = odo;
    }

    public TransferObjekat getTo() {
        return to;
    }

    public void setTo(TransferObjekat to) {
        this.to = to;
    }
    
    
    
}
