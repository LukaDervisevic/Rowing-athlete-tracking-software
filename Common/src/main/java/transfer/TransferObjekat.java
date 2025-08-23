/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transfer;

import java.io.Serializable;
import java.util.List;
import model.OpstiDomenskiObjekat;
import ogranicenja.Ogranicenje;

/**
 *
 * @author luka
 * @param <T>
 */
public class TransferObjekat<T extends OpstiDomenskiObjekat> implements Serializable{
    public OpstiDomenskiObjekat odo;
    public List<T> listOdo;
    public String poruka;
    public boolean signal;
    public int trenutniSlog;
    public String nazivSo;
    public String whereUslov;
    public String upit;
    public Ogranicenje ogranicenje;

    public OpstiDomenskiObjekat getOdo() {
        return odo;
    }

    public void setOdo(OpstiDomenskiObjekat odo) {
        this.odo = odo;
    }
    
    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }

    public boolean isSignal() {
        return signal;
    }

    public void setSignal(boolean signal) {
        this.signal = signal;
    }

    public String getNazivSo() {
        return nazivSo;
    }

    public void setNazivSo(String nazivSo) {
        this.nazivSo = nazivSo;
    }

    public List<T> getListOdo() {
        return listOdo;
    }

    public void setListOdo(List<T> listOdo) {
        this.listOdo = listOdo;
    }

    public int getTrenutniSlog() {
        return trenutniSlog;
    }

    public void setTrenutniSlog(int trenutniSlog) {
        this.trenutniSlog = trenutniSlog;
    }

    public String getWhereUslov() {
        return whereUslov;
    }

    public void setWhereUslov(String whereUslov) {
        this.whereUslov = whereUslov;
    }

    public String getUpit() {
        return upit;
    }

    public void setUpit(String upit) {
        this.upit = upit;
    }

    public Ogranicenje getOgranicenje() {
        return ogranicenje;
    }

    public void setOgranicenje(Ogranicenje ogranicenje) {
        this.ogranicenje = ogranicenje;
    }
    
    
    
}
