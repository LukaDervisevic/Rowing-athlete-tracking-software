package so.veslackiklub;

import bbp.BrokerBazePodataka;
import java.util.List;
import model.OpstiDomenskiObjekat;
import model.VeslackiKlub;
import so.OpsteIzvrsenjeSO;
import transfer.TransferObjekat;

/**
 *
 * @author lukad
 */
public class SOVratiListuKlub extends OpsteIzvrsenjeSO {

    public SOVratiListuKlub(TransferObjekat to) {
        this.to = to;
    }

    @Override
    protected boolean izvrsiSO() {
        List<OpstiDomenskiObjekat> listaOdo = BrokerBazePodataka.getInstance().pronadjiSlogоve(to.getOdo(), to.getWhereUslov());
        to.setListOdo(listaOdo);
        return true;
    }

    @Override
    protected boolean proveriOgranicenja(OpstiDomenskiObjekat odo) {
        return (odo instanceof VeslackiKlub);
    }

}
