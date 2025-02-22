package forme.tableModeli;

import java.util.LinkedList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Agencija;

/**
 *
 * @author Luka
 */
public class AgencijaTableModel extends AbstractTableModel{

    List<Agencija> agencije = new LinkedList<>();
    
    String[] kolone = {"id","Naziv","email","telefon","Drzava"};
    
    public AgencijaTableModel(){
        
    }
    
    public AgencijaTableModel(List<Agencija> agencije){
        this.agencije = agencije;
    }
    
    @Override
    public int getRowCount() {
        return agencije.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Agencija agencija = agencije.get(rowIndex);
        
        switch (columnIndex) {
            case 0:
                return agencija.getId();
            case 1:
                return agencija.getNaziv();
            case 2:
                return agencija.getEmail();
            case 3:
                return agencija.getTelefon();
            case 4:
                return agencija.getDrzava().getNaziv();
            default:
                throw new AssertionError();
        }
        
        
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public List<Agencija> getAgencije() {
        return agencije;
    }

    public void setAgencije(List<Agencija> agencije) {
        this.agencije = agencije;
    }
    
    
    
    
}
