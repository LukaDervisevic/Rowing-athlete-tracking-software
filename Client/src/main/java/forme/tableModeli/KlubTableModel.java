package forme.tableModeli;

import java.util.LinkedList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.VeslackiKlub;

/**
 *
 * @author luka
 */
public class KlubTableModel extends AbstractTableModel{

    List<VeslackiKlub> klubovi = new LinkedList<>();
    String[] kolone = {"Naziv","Adresa","Email","Broj Telefona"};
    
    public KlubTableModel(){
        
    }
    
    public KlubTableModel(List<VeslackiKlub> klubovi){
        this.klubovi = klubovi;
    }
    
    @Override
    public int getRowCount() {
        return klubovi.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        VeslackiKlub klub = klubovi.get(rowIndex);
        
        switch (columnIndex) {
            case 0 -> {
                return klub.getNaziv();
            }
            case 1 -> {
                return klub.getAdresa();
            }
            case 2 -> {
                return klub.getEmail();
            }
            case 3 -> {
                return klub.getTelefon();
            }
            default -> throw new AssertionError();
        }
        
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public List<VeslackiKlub> getKlubovi() {
        return klubovi;
    }
    
    public VeslackiKlub getKlub(int row){
        return klubovi.get(row);
    }

    public void setKlubovi(List<VeslackiKlub> klubovi) {
        this.klubovi = klubovi;
    }
    
    
    
}
