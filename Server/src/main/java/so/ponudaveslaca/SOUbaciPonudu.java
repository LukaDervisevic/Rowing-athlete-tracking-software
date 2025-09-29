package so.ponudaveslaca;

import model.KategorijaVeslaca;
import model.OpstiDomenskiObjekat;
import model.PonudaVeslaca;
import model.StavkaPonude;
import so.OpsteIzvrsenjeSO;
import transfer.TransferObjekat;

/**
 *
 * @author lukad
 */
public class SOUbaciPonudu extends OpsteIzvrsenjeSO {

    public SOUbaciPonudu(TransferObjekat to) {
        setTo(to);
    }

    @Override
    public boolean izvrsiSO() {
        boolean signal = false;
        PonudaVeslaca ponuda = (PonudaVeslaca) getTo().getOdo();
        int noviKljuc = getBbp().vratiNoviKljucPoKoloni(ponuda);
        if (noviKljuc == 0) {
            return signal;
        }
        ponuda.setId(noviKljuc);
        int broj_kadeta = 0, broj_juniora = 0;
        float prosecno_vreme_kadeti = 0, prosecno_vreme_juniori = 0;
        int suma_kadeti = 0, suma_juniori = 0;
        for (StavkaPonude stavkaPonude : ponuda.getStavke()) {
            stavkaPonude.setPonudaVeslaca(ponuda);
            if (stavkaPonude.getVeslac().getKategorija().equals(KategorijaVeslaca.KADET)) {
                suma_kadeti += stavkaPonude.getVeslac().getNajboljeVreme();
                broj_kadeta++;
            } else {
                suma_juniori += stavkaPonude.getVeslac().getNajboljeVreme();
                broj_juniora++;
            }
        }
        if (broj_kadeta != 0) {
            prosecno_vreme_kadeti = suma_kadeti / broj_kadeta;

        }
        if (broj_juniora != 0) {
            prosecno_vreme_juniori = suma_juniori / broj_juniora;
        }

        ponuda.setBrojKadeta(broj_kadeta);
        ponuda.setBrojJuniora(broj_juniora);
        ponuda.setProsecnoVremeKadeti(prosecno_vreme_kadeti);
        ponuda.setProsecnoVremeJuniori(prosecno_vreme_juniori);
        ponuda.postaviPrimarniKljuc(noviKljuc);

        signal = getBbp().kreirajSlog(getTo().getOdo());
        if (signal) {
            for (StavkaPonude stavkaPonude : ponuda.getStavke()) {
                signal = getBbp().kreirajSlog(stavkaPonude);
                if (!signal) {
                    break;
                }
            }
        }
        return signal;
    }

    @Override
    protected boolean proveriOgranicenja(OpstiDomenskiObjekat odo) {
        if (!(odo instanceof PonudaVeslaca)) {
            return false;
        }

        boolean signal = true;
        PonudaVeslaca ponudaVeslaca = (PonudaVeslaca) odo;

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
