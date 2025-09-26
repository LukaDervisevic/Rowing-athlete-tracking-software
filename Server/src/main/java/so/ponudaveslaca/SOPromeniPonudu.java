/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.ponudaveslaca;

import java.util.LinkedList;
import java.util.List;
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
public class SOPromeniPonudu extends OpsteIzvrsenjeSO {

    public SOPromeniPonudu(TransferObjekat to) {
        setTo(to);
    }

    @Override
    public boolean izvrsiSO() {
        boolean signal = false;
        PonudaVeslaca staraPonuda = (PonudaVeslaca) bbp.pronadjiSlog(to.getOdo());
        PonudaVeslaca novaPonuda = (PonudaVeslaca) to.getOdo();
        if (staraPonuda == null || novaPonuda == null) {
            return signal;
        }

        List<StavkaPonude> stavkeZaBrisanje = new LinkedList<>();
        for (StavkaPonude staraStavka : staraPonuda.getStavke()) {
            if (!novaPonuda.getStavke().contains(staraStavka)) {
                stavkeZaBrisanje.add(staraStavka);
            }
        }

        List<StavkaPonude> stavkeZaDodavanje = new LinkedList<>();
        for (StavkaPonude novaStavka : novaPonuda.getStavke()) {
            if (!staraPonuda.getStavke().contains(novaStavka)) {
                stavkeZaDodavanje.add(novaStavka);
            }
        }
        
        for(StavkaPonude stavkaPonude : stavkeZaBrisanje) {
            signal = bbp.obrisiSlog(stavkaPonude);
            if(!signal) return signal;
        }
        
        for(StavkaPonude stavkaPonude : stavkeZaDodavanje) {
            signal = bbp.kreirajSlog(stavkaPonude);
            if(!signal) return signal;
        }
        
        int broj_kadeta = 0, broj_juniora = 0;
        float prosecno_vreme_kadeti = 0, prosecno_vreme_juniori = 0;
        int suma_kadeti = 0, suma_juniori = 0;
        for (StavkaPonude stavkaPonude : novaPonuda.getStavke()) {
            if (stavkaPonude.getVeslac().getKategorija().equals(KategorijaVeslaca.KADET)) {
                stavkaPonude.setPonudaVeslaca(novaPonuda);
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
        
        novaPonuda.setBrojKadeta(broj_kadeta);
        novaPonuda.setBrojJuniora(broj_juniora);
        novaPonuda.setProsecnoVremeKadeti(prosecno_vreme_kadeti);
        novaPonuda.setProsecnoVremeJuniori(prosecno_vreme_juniori);
        
        signal = bbp.azurirajSlog(novaPonuda);

        return signal;
    }

    @Override
    protected boolean prostaVrednosnaOgranicenja(OpstiDomenskiObjekat odo) {
        if (!(odo instanceof PonudaVeslaca)) {
            return false;
        }

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
        return true;
    }

    @Override
    protected boolean strukturnaOgranicenja(OpstiDomenskiObjekat odo) {
        return true;
    }
}

