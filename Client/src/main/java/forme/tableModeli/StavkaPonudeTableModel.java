package forme.tableModeli;

import java.util.LinkedList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.StavkaPonude;

/**
 *
 * @author luka
 */
public class StavkaPonudeTableModel extends AbstractTableModel{
    
    List<StavkaPonude> stavkePonude = new LinkedList<>();
    String[] kolone = {"RB stavke","Id veslača","Ime Prezime","Godine treniranja","Visina","Težina","Kategorija","Najbolje vreme"};
    
    public StavkaPonudeTableModel(){
        
    }
    
    public StavkaPonudeTableModel(List<StavkaPonude> stavke){
        this.stavkePonude = stavke;
    }
    
    @Override
    public int getRowCount() {
        return stavkePonude.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        StavkaPonude stavka = stavkePonude.get(rowIndex);
        
        switch (columnIndex) {
            case 0 -> {
                return stavka.getRb();
            }
            case 1 -> {
                return stavka.getVeslac().getIdVeslaca();
            }
            case 2 -> {
                return stavka.getVeslac().getImePrezime();
            }
            case 3 -> {
                return stavka.getGodineTreniranja();
            }
            case 4 -> {
                return stavka.getVeslac().getVisina();
            }
            case 5 -> {
                return stavka.getVeslac().getTezina();
            }
            case 6 -> {
                return stavka.getVeslac().getKategorija();
            }
            case 7 -> {
                return stavka.getVeslac().getNajboljeVreme();
            }
            default -> throw new AssertionError();
        }
        
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }
    
    public void clear(){
        stavkePonude.clear();
        fireTableDataChanged(); 
    }

    public List<StavkaPonude> getStavkePonude() {
        return stavkePonude;
    }

    public void setStavkePonude(List<StavkaPonude> stavkePonude) {
        this.stavkePonude = stavkePonude;
    }
    
    public void obrisiStavku(int rb) {
        stavkePonude.removeIf(s -> {
            if(s.getRb() == rb){
                int index = stavkePonude.indexOf(s);
                fireTableRowsDeleted(index, index);
                return true;
            }
            return false;
        });
    }
    
    public void dodajStavku(StavkaPonude stavka) {
        stavkePonude.add(stavka);
        int index = stavkePonude.size() - 1;
        fireTableRowsInserted(index, index);
    }
    
}
