package so.ponudaveslaca;

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
        boolean signal = bbp.obrisiSlog(to.getOdo());
        getTo().setSignal(signal);
        return signal;
    }

    @Override
    protected boolean prostaVrednosnaOgranicenja(OpstiDomenskiObjekat odo) {
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

    @Override
    protected boolean slozenaVrednosnaOgranicenja(OpstiDomenskiObjekat odo) {
        // Problem
        return true;
    }

    @Override
    protected boolean strukturnaOgranicenja(OpstiDomenskiObjekat odo) {
        // Problem
        return true;
    }

}
