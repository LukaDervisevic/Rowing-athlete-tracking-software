/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.veslac;

import java.util.List;
import model.OpstiDomenskiObjekat;
import model.Veslac;
import so.OpsteIzvrsenjeSO;
import transfer.TransferObjekat;

/**
 *
 * @author lukad
 */
public class SOVratiListuVeslaci extends OpsteIzvrsenjeSO{

    protected String kriterijum;
    
    public SOVratiListuVeslaci(TransferObjekat to,String kriterijumVeslac) {
        this.to = to;
        this.kriterijum = kriterijumVeslac;
    }

    @Override
    protected boolean izvrsiSO() {
        List<OpstiDomenskiObjekat> listaOdo = bbp.pronadjiSlog(to.getOdo(), kriterijum);
        to.setListOdo(listaOdo);
        return true;
    }

    @Override
    protected boolean prostaVrednosnaOgranicenja(OpstiDomenskiObjekat odo) {
        return (odo instanceof Veslac);
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
