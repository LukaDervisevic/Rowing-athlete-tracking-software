/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import db.BrokerBazePodataka;
import model.OpstiDomenskiObjekat;
import transfer.TransferObjekat;

/**
 *
 * @author luka
 */
public abstract class OpsteIzvrsenjeSo {
    private BrokerBazePodataka bbp = new BrokerBazePodataka("veslanje");
    private int brojSlogova;
    private int trenutniSlog = -1;
    private OpstiDomenskiObjekat odo;
    private TransferObjekat to;
    
    synchronized public boolean opsteIzvrsenjeSO() {
        bbp.napraviKonekciju();
        boolean signal = izvrsiSO();
        if(signal) {
            bbp.commitTransakcija();
        }else{
            bbp.rollbackTransakcija();
        }
        return signal;
    };
    
    public abstract boolean izvrsiSO();

    public BrokerBazePodataka getBbp() {
        return bbp;
    }

    public void setBbp(BrokerBazePodataka bbp) {
        this.bbp = bbp;
    }

    public int getBrojSlogova() {
        return brojSlogova;
    }

    public void setBrojSlogova(int brojSlogova) {
        this.brojSlogova = brojSlogova;
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
