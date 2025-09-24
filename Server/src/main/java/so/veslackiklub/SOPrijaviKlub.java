/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.veslackiklub;

import model.Agencija;
import model.VeslackiKlub;
import ogranicenja.Ogranicenje;
import so.PrijaviDK;
import transfer.TransferObjekat;
import utils.HesiranjeServis;

/**
 *
 * @author lukad
 */
public class SOPrijaviKlub extends PrijaviDK {

    public SOPrijaviKlub(TransferObjekat to) {
        setTo(to);
        porukaGreska = "Greska pri prijavljivanju veslackog kluba";
        porukaUspeh = "Uspesno prijavljivanje veslackog kluba";
    }

    @Override
    public boolean izvrsiSO() {
        try {
            VeslackiKlub klub = (VeslackiKlub) to.getOdo();
            boolean verifikovano;
            Ogranicenje ogranicenje = new Ogranicenje();

            if (ogranicenje.proveriOgranicenja(to)) {
                VeslackiKlub vraceniKlub = (VeslackiKlub) bbp.prijaviSlog(to.getOdo());
                if (vraceniKlub != null) {
                    verifikovano = HesiranjeServis.proveriSifru(klub.getSifra(), vraceniKlub.getSifra());
                    to.setOdo(vraceniKlub);
                    to.setSignal(verifikovano);
                    to.setPoruka(verifikovano ? porukaUspeh : porukaGreska);

                } else {
                    to.setSignal(false);
                    to.setTrenutniSlog(-1);
                    to.setPoruka(porukaGreska);
                }
            }

            return to.isSignal();
        } catch (Exception ex) {
            return false;
        }

    }

}
