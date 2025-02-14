package forme.utils;

import java.util.LinkedList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Takmicenje;

/**
 *
 * @author luka
 */
public class TakmicenjaTableModel extends AbstractTableModel{
    
    private List<Takmicenje> takmicenja; 
    private String[] kolone = {"id","Naziv","Kategorija","Vrsta","Datum"};
    
    
    public TakmicenjaTableModel(){
        takmicenja = new LinkedList<>();
    }
    
    public TakmicenjaTableModel(List<Takmicenje> takmicenja){
        this.takmicenja = takmicenja;
    }
    
    @Override
    public int getRowCount() {
        return takmicenja.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        Takmicenje takmicenje = takmicenja.get(rowIndex);
        
        switch (columnIndex) {
            case 0:
                return takmicenje.getId();
            case 1:
                return takmicenje.getNaziv();
            case 2:
                return takmicenje.getStarosnaKategorija();
            case 3:
                return takmicenje.getVrstaTrke();
            case 4:
                return takmicenje.getDatum();
            default:
                throw new AssertionError();
        }
        
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public List<Takmicenje> getTakmicenja() {
        return takmicenja;
    }

    public void setTakmicenja(List<Takmicenje> takmicenja) {
        this.takmicenja = takmicenja;
    }
    
    public void dodajTakmicenje(Takmicenje t){
       takmicenja.add(t);
        int poslednjiIndex = takmicenja.size() - 1;
        fireTableRowsInserted(poslednjiIndex, poslednjiIndex);
    }
    
    public void obrisiTakmicenje(int idTakmicenja){
        takmicenja.removeIf(t -> {
            if(t.getId() == idTakmicenja){
                int index = takmicenja.indexOf(t);
                fireTableRowsDeleted(index,index);
                return true;
            }
            return false;
        });
    }
    
}
