package so.agencija;

import java.util.List;
import model.OpstiDomenskiObjekat;
import so.OpsteIzvrsenjeSO;
import transfer.TransferObjekat;

/**
 *
 * @author lukad
 */
public class SOVratiListuAgencija extends OpsteIzvrsenjeSO {

    protected String kriterijum;

    public SOVratiListuAgencija(TransferObjekat to, String kriterijumAgencija) {
        this.to = to;
        this.kriterijum = kriterijumAgencija;
    }
    
    @Override
    public boolean izvrsiSO() {
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
