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
    
    abstract public String getVrednostAtributa();
    
    abstract public String setVrednostAtributa();
    
    abstract public String getClassName();
    
    abstract public String getWhereUslov();
    
    abstract public String getImePoKoloni(int kolona);
    
    abstract public OpstiDomenskiObjekat vratiNoviSlog(ResultSet rs) throws SQLException;
    
    abstract public int getPrimaryKey();
    
    abstract public void setPrimarniKljuc(int id);
    
    abstract public void print();
    
}
