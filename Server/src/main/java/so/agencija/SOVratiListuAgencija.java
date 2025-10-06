package so.agencija;

import bbp.BrokerBazePodataka;
import java.util.List;
import model.Agencija;
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
        List<OpstiDomenskiObjekat> listaOdo = BrokerBazePodataka.getInstance().pronadjiSlogоve(to.getOdo(), kriterijum);
        to.setListOdo(listaOdo);
        return true;
    }

    @Override
    protected boolean proveriOgranicenja(OpstiDomenskiObjekat odo) {
        return (odo instanceof Agencija);
    }

}
