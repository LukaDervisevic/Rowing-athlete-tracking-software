/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import model.OpstiDomenskiObjekat;
import transfer.TransferObjekat;

/**
 *
 * @author luka
 */
public class PromeniDK extends OpsteIzvrsenjeSO {

    public void promeniDK(TransferObjekat to) {
        this.to = to;
        opsteIzvrsenjeSO();
    }

    @Override
    public boolean izvrsiSO() {

        OpstiDomenskiObjekat vraceniOdo = bbp.pronadjiSlog(to.getOdo());
        if (vraceniOdo != null) {

            if (bbp.azurirajSlog(vraceniOdo)) {
                to.setPoruka("Uspesno azuriranje domenskog objekta");
                to.setSignal(true);
            } else {
                to.setPoruka("Neuspesno azuriranje domenskog objekta");
                to.setSignal(false);
            }
        } else {
            to.setPoruka("Neuspesno pronalazenje domenskog objekta koji treba da se azurira");
        }

        return to.isSignal();
    }

}
