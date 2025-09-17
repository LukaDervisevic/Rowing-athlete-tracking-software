package so.ponudaveslaca;

import model.KategorijaVeslaca;
import model.PonudaVeslaca;
import model.StavkaPonude;
import ogranicenja.Ogranicenje;
import so.KreirajDK;
import transfer.TransferObjekat;

/**
 *
 * @author lukad
 */
public class SOKreirajPonudu extends KreirajDK {

    public SOKreirajPonudu(TransferObjekat to) {
        setTo(to);
        porukaUspesno = "Uspesno kreiranje ponude veslaca";
        porukaGreska = "Greska pri kreiranju ponude veslaca : " + to.getPoruka();
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
        if(broj_juniora != 0){
            prosecno_vreme_juniori = suma_juniori / broj_juniora;
        }
        
        ponuda.setBrojKadeta(broj_kadeta);
        ponuda.setBrojJuniora(broj_juniora);
        ponuda.setProsecnoVremeKadeti(prosecno_vreme_kadeti);
        ponuda.setProsecnoVremeJuniori(prosecno_vreme_juniori);
        ponuda.postaviPrimarniKljuc(noviKljuc);

        Ogranicenje ogranicenje = new Ogranicenje();
        if (!ogranicenje.proveriOgranicenja(to)) {
            return signal;
        }
        signal = getBbp().kreirajSlog(getTo().getOdo());
        if (signal) {
            for (StavkaPonude stavkaPonude : ponuda.getStavke()) {
                signal = getBbp().kreirajSlog(stavkaPonude);
                if (!signal) {
                    break;
                }
            }
        }
        if (signal) {
            getTo().poruka = porukaUspesno;
        } else {
            getTo().poruka = porukaGreska;
        }

        return signal;
    }

}
