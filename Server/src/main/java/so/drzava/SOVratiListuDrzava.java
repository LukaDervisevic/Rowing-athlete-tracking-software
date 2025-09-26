package so.drzava;

import java.util.List;
import model.Drzava;
import model.OpstiDomenskiObjekat;
import so.OpsteIzvrsenjeSO;
import transfer.TransferObjekat;

/**
 *
 * @author lukad
 */
public class SOVratiListuDrzava extends OpsteIzvrsenjeSO{

    protected String kriterijum;

    public SOVratiListuDrzava(TransferObjekat to, String kriterijumDrzava) {
        this.to = to;
        this.kriterijum = kriterijumDrzava;
    }
    
    @Override
    protected boolean izvrsiSO() {
        List<OpstiDomenskiObjekat> listaOdo = bbp.pronadjiSlog(to.getOdo(), to.getWhereUslov());
        to.setListOdo(listaOdo);
        return true;
    }

    @Override
    protected boolean prostaVrednosnaOgranicenja(OpstiDomenskiObjekat odo) {
        return (odo instanceof Drzava);
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
