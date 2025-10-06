package so.drzava;

import bbp.BrokerBazePodataka;
import java.util.List;
import model.Drzava;
import model.OpstiDomenskiObjekat;
import so.OpsteIzvrsenjeSO;
import transfer.TransferObjekat;

/**
 *
 * @author lukad
 */
public class SOVratiListuDrzava extends OpsteIzvrsenjeSO {

    protected String kriterijum;

    public SOVratiListuDrzava(TransferObjekat to, String kriterijumDrzava) {
        this.to = to;
        this.kriterijum = kriterijumDrzava;
    }

    @Override
    protected boolean izvrsiSO() {
        List<OpstiDomenskiObjekat> listaOdo = BrokerBazePodataka.getInstance().pronadjiSlogоve(to.getOdo(), to.getWhereUslov());
        to.setListOdo(listaOdo);
        return true;
    }

    @Override
    protected boolean proveriOgranicenja(OpstiDomenskiObjekat odo) {
        return (odo instanceof Drzava);
    }

}
