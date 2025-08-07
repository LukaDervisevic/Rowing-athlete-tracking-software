/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ogranicenja;

import transfer.TransferObjekat;

/**
 *
 * @author luka
 */
public class Ogranicenje {
    
    // TODO: Razmisliti da li je potrebno pisati odvojeno metode za preduslove i postuslove
    
    public boolean proveriOgranicenja(TransferObjekat to) {
        return prostaVrednosnaOgranicenja(to) && slozenaVrednosnaOgranicenja(to) && strukturnoOgranicenje(to);
    }
    
    public boolean prostaVrednosnaOgranicenja(TransferObjekat to){
        return true;
    };
    
    public boolean slozenaVrednosnaOgranicenja(TransferObjekat to){
        return true;
    };
    
    public boolean strukturnoOgranicenje(TransferObjekat to){
        return true;
    };
    
}
