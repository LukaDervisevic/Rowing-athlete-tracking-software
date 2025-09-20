/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import java.util.List;
import model.OpstiDomenskiObjekat;
import transfer.TransferObjekat;

/**
 *
 * @author lukad
 */
public class VratiListuDK extends OpsteIzvrsenjeSO{

    protected String whereUslov;
    
    public VratiListuDK(TransferObjekat to,String whereUslov) {
        setTo(to);
        this.whereUslov = whereUslov;
    }

    @Override
    public boolean izvrsiSO() {
        boolean signal = false;
        List<OpstiDomenskiObjekat> listaOdo = bbp.pronadjiSlog(to.getOdo(), to.getWhereUslov());
        if(!listaOdo.isEmpty()) {
            to.setListOdo(listaOdo);
            signal = true;
        }
        to.setListOdo(listaOdo);
        return true;
    }
    
}
