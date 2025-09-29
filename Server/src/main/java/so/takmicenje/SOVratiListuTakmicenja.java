package so.takmicenje;

import java.util.List;
import model.OpstiDomenskiObjekat;
import model.Takmicenje;
import so.OpsteIzvrsenjeSO;
import transfer.TransferObjekat;

/**
 *
 * @author lukad
 */
public class SOVratiListuTakmicenja extends OpsteIzvrsenjeSO {

    protected String kriterijum;

    public SOVratiListuTakmicenja(TransferObjekat to, String kriterijumTakmicenje) {
        this.to = to;
        this.kriterijum = kriterijumTakmicenje;
    }

    @Override
    protected boolean izvrsiSO() {
        List<OpstiDomenskiObjekat> listaOdo = bbp.pronadjiSlogоve(to.getOdo(), kriterijum);
        to.setListOdo(listaOdo);
        return true;
    }

    @Override
    protected boolean proveriOgranicenja(OpstiDomenskiObjekat odo) {
        return (odo instanceof Takmicenje);
    }

}
