/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.ponudaveslaca;

import bbp.BrokerBazePodataka;
import java.util.List;
import model.OpstiDomenskiObjekat;
import model.PonudaVeslaca;
import so.OpsteIzvrsenjeSO;
import transfer.TransferObjekat;

/**
 *
 * @author lukad
 */
public class SOVratiListuPonuda extends OpsteIzvrsenjeSO{
    
    protected String kriterijum;
    
    public SOVratiListuPonuda(TransferObjekat to,String kriterijumPonudaVeslaca) {
        this.to = to;
        this.kriterijum = kriterijumPonudaVeslaca;
    }

    @Override
    public boolean izvrsiSO() {
        List<OpstiDomenskiObjekat> listaOdo = BrokerBazePodataka.getInstance().pronadjiSlogоve(to.getOdo(), kriterijum);
        to.setListOdo(listaOdo);
        return true;
        
    }

    @Override
    protected boolean proveriOgranicenja(OpstiDomenskiObjekat odo) {
        return (odo instanceof PonudaVeslaca);
    }
}
