/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.takmicenje;

import java.util.List;
import model.OpstiDomenskiObjekat;
import so.OpsteIzvrsenjeSO;
import transfer.TransferObjekat;

/**
 *
 * @author lukad
 */
public class SOVratiListuTakmicenja extends OpsteIzvrsenjeSO{

    protected String kriterijum;
    
    public SOVratiListuTakmicenja(TransferObjekat to, String kriterijumTakmicenje) {
        this.to = to;
        this.kriterijum = kriterijumTakmicenje;
    }
    
    @Override
    protected boolean izvrsiSO() {
        List<OpstiDomenskiObjekat> listaOdo = bbp.pronadjiSlog(to.getOdo(), kriterijum);
        to.setListOdo(listaOdo);
        return true;
    }

    @Override
    protected boolean prostaVrednosnaOgranicenja(OpstiDomenskiObjekat odo) {
        return true;
    }

    @Override
    protected boolean slozenaVrednosnaOgranicenja(OpstiDomenskiObjekat odo) {
        return true;
    }

    @Override
    protected boolean strukturnaOgranicenja(OpstiDomenskiObjekat odo) {
        return true;
    }
    
}
