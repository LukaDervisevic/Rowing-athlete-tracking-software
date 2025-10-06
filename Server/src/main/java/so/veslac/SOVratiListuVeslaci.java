package so.veslac;

import bbp.BrokerBazePodataka;
import java.util.List;
import model.OpstiDomenskiObjekat;
import model.Veslac;
import so.OpsteIzvrsenjeSO;
import transfer.TransferObjekat;

/**
 *
 * @author lukad
 */
public class SOVratiListuVeslaci extends OpsteIzvrsenjeSO {

    protected String kriterijum;

    public SOVratiListuVeslaci(TransferObjekat to, String kriterijumVeslac) {
        this.to = to;
        this.kriterijum = kriterijumVeslac;
    }

    @Override
    protected boolean izvrsiSO() {
        List<OpstiDomenskiObjekat> listaOdo = BrokerBazePodataka.getInstance().pronadjiSlogоve(to.getOdo(), kriterijum);
        to.setListOdo(listaOdo);
        return true;
    }

    @Override
    protected boolean proveriOgranicenja(OpstiDomenskiObjekat odo) {
        return (odo instanceof Veslac);
    }

}
