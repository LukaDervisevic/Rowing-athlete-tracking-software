/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author lukad
 */
public interface OpstiDomenskiObjekat extends Serializable{
    
    abstract public String vrednostiAtributaZaKreiranje();
    
    abstract public String azurirajVrednostiAtributa();
    
    abstract public String vratiNaziveKolona();
    
    abstract public String vratiNazivTabele();
    
    abstract public String vratiWhereUslov();
        
    abstract public OpstiDomenskiObjekat vratiNoviSlog(ResultSet rs) throws SQLException;
    
    abstract public String vratiPrimarniKljuc();
     
    abstract public String join();
    
    abstract public String alias();
    
    abstract public String vratiImePoKoloni(int i);
 
}
