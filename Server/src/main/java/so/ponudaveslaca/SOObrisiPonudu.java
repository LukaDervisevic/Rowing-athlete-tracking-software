package so.ponudaveslaca;

import bbp.BrokerBazePodataka;
import model.OpstiDomenskiObjekat;
import model.PonudaVeslaca;
import so.OpsteIzvrsenjeSO;
import transfer.TransferObjekat;

/**
 *
 * @author lukad
 */
public class SOObrisiPonudu extends OpsteIzvrsenjeSO {

    public SOObrisiPonudu(TransferObjekat to) {
        setTo(to);
    }

    @Override
    public boolean izvrsiSO() {
        boolean signal = BrokerBazePodataka.getInstance().obrisiSlog(to.getOdo());
        getTo().setSignal(signal);
        return signal;
    }

    @Override
    protected boolean proveriOgranicenja(OpstiDomenskiObjekat odo) {
        boolean signal = true;
        PonudaVeslaca ponudaVeslaca = (PonudaVeslaca) odo;
        if(ponudaVeslaca.getId() <= 0) {
            signal = false;
        }
        if(ponudaVeslaca.getDatumKreiranja() == null) {
            signal = false;
        }
        if(ponudaVeslaca.getBrojKadeta() < 0) {
            signal = false;
        }
        if(ponudaVeslaca.getBrojJuniora() < 0) {
            signal = false;
        }
        if(ponudaVeslaca.getProsecnoVremeJuniori() < 0) {
            signal = false;
        }
        if(ponudaVeslaca.getProsecnoVremeKadeti() < 0) {
            signal = false;
        }
        if(ponudaVeslaca.getVeslackiKlub().getId() <= 0) {
            signal = false;
        }
        if(ponudaVeslaca.getAgencija().getId() <= 0) {
            signal = false;
        }
        return signal;
    }

}
