package forme.tableModeli;

import java.util.LinkedList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Drzava;

/**
 *
 * @author Luka
 */
public class DrzavaTableModel extends AbstractTableModel{
    
    List<Drzava> drzave = new LinkedList<>();
    String[] kolone = {"naziv"};
    
    public DrzavaTableModel(){
        
    }
    
    public DrzavaTableModel(List<Drzava> drzave){
        this.drzave = drzave;
    }
    
    @Override
    public int getRowCount() {
        return drzave.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Drzava drzava = drzave.get(rowIndex);
        switch (columnIndex) {
            case 0 -> {
                return drzava.getNaziv();
            }
            default -> throw new AssertionError();
        }
        
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }
    
    public List<Drzava> getDrzave(){
        return drzave;
    }
    
    public Drzava getDrzava(int row) {
        return drzave.get(row);
    }
    
    public void setDrzave(List<Drzava> drzave){
        this.drzave = drzave;
    }
    
    public void dodajDrzava(Drzava d) {
        drzave.add(d);
        int poslednjiIndex = drzave.size() - 1;
        fireTableRowsInserted(poslednjiIndex, poslednjiIndex);
    }
    
    public void obrisiVeslaca(int id){
        drzave.removeIf(d -> {
            if(d.getId() == id){
                int index = drzave.indexOf(d);
                fireTableRowsDeleted(index,index);
                return true;
            }
            return false;
        });
    }
    
}
