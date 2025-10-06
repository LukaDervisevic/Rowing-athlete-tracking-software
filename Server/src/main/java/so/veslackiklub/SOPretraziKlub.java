package so.veslackiklub;

import bbp.BrokerBazePodataka;
import model.OpstiDomenskiObjekat;
import model.VeslackiKlub;
import so.OpsteIzvrsenjeSO;
import transfer.TransferObjekat;

/**
 *
 * @author lukad
 */
public class SOPretraziKlub extends OpsteIzvrsenjeSO{

    public SOPretraziKlub(TransferObjekat to) {
        setTo(to);
    }

    @Override
    protected boolean izvrsiSO() {
        boolean signal;
        OpstiDomenskiObjekat vraceniOdo = (OpstiDomenskiObjekat) BrokerBazePodataka.getInstance().pronadjiSlog(to.getOdo(),to.getWhereUslov());
        if (vraceniOdo != null) {
            to.setOdo(vraceniOdo);
            signal = true;
        } else {
            signal = false;
        }

        return signal;
    }

    @Override
    protected boolean proveriOgranicenja(OpstiDomenskiObjekat odo) {
        if (!(odo instanceof VeslackiKlub)) {
            return false;
        }

        boolean signal = true;
        VeslackiKlub veslackiKlub = (VeslackiKlub) odo;
        if(veslackiKlub.getNaziv() == null) {
            signal = false;
        }
        return signal;
    }

    
    
}
