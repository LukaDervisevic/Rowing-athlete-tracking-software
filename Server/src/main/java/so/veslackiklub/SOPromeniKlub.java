/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
public class SOPromeniKlub extends OpsteIzvrsenjeSO {

    public SOPromeniKlub(TransferObjekat to) {
        setTo(to);
    }

    @Override
    protected boolean izvrsiSO() {
        OpstiDomenskiObjekat vraceniOdo = BrokerBazePodataka.getInstance().pronadjiSlog(to.getOdo(),to.getWhereUslov());
        if (vraceniOdo != null) {

            if (BrokerBazePodataka.getInstance().azurirajSlog(vraceniOdo)) {
                to.setSignal(true);
            } else {
                to.setSignal(false);
            }
        } else {
            to.setSignal(false);
        }
        return to.isSignal();
    }

    @Override
    protected boolean proveriOgranicenja(OpstiDomenskiObjekat odo) {
        if (!(odo instanceof VeslackiKlub)) {
            return false;
        }

        boolean signal = true;
        VeslackiKlub veslackiKlub = (VeslackiKlub) odo;
        if(veslackiKlub.getNaziv() == null || veslackiKlub.getNaziv().isBlank()) {
            signal = false;
        }
        if (veslackiKlub.getEmail() == null || veslackiKlub.getEmail().isBlank()) {
            signal = false;
        }
        if (!veslackiKlub.getEmail().contains("@")) {
            signal = false;
        }
        if (veslackiKlub.getAdresa() == null || veslackiKlub.getAdresa().isBlank()) {
            signal = false;
        }
        if (veslackiKlub.getTelefon() == null || veslackiKlub.getTelefon().isBlank()) {
            signal = false;
        }
        if (!veslackiKlub.getTelefon().contains("+")) {
            signal = false;
        }
        if (veslackiKlub.getTelefon().length() > 13) {
            signal = false;
        }
        if (veslackiKlub.getKorisnickoIme() == null || veslackiKlub.getKorisnickoIme().isBlank()) {
            signal = false;
        }
        if (veslackiKlub.getSifra() == null || veslackiKlub.getSifra().isBlank()) {
            signal = false;
        }

        if (veslackiKlub.getSifra().length() < 8) {
            signal = false;
        }
        return signal;
    }

}
