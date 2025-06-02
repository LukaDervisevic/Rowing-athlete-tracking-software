package forme.tableModeli;

import java.util.LinkedList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.PonudaVeslaca;

/**
 *
 * @author luka
 */
public class PonudaTableModelAgencija extends AbstractTableModel {

    List<PonudaVeslaca> ponude;
    String[] kolone = {"id", "Datum kreiranja", "Broj kadeta", "Broj juniora", "Prosečno vreme kadeta", "Prosečno vreme juniora", "Klub"};

    public PonudaTableModelAgencija(){
        ponude = new LinkedList<>();
    }
    
    public PonudaTableModelAgencija(List<PonudaVeslaca> ponude){
        this.ponude = ponude;
    }
    
    
    @Override
    public int getRowCount() {
        return ponude.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        PonudaVeslaca ponuda = ponude.get(rowIndex);
        switch (columnIndex) {
            
            case 0 -> {
                return ponuda.getId();
            }
            case 1 -> {
                return ponuda.getDatumKreiranja();
            }
            case 2 -> {
                return ponuda.getBrojKadeta();
            }
            case 3 -> {
                return ponuda.getBrojJuniora();
            }
            case 4 -> {
                return ponuda.getProsecnoVremeKadeti();
            }
            case 5 -> {
                return ponuda.getProsecnoVremeJuniori();
            }
            case 6 -> {
                return ponuda.getIdKluba();
            }

            default -> throw new AssertionError();
        }

    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public List<PonudaVeslaca> getPonude() {
        return ponude;
    }

    public void setPonude(List<PonudaVeslaca> ponude) {
        this.ponude = ponude;
    }
    
    

}
