package forme.tableModeli;

import java.util.LinkedList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.PonudaVeslaca;

/**
 *
 * @author luka
 */
public class PonudaTableModelKlub extends AbstractTableModel {

    List<PonudaVeslaca> ponude = new LinkedList<>();
    String[] kolone = {"id", "Datum kreiranja", "Broj kadeta", "Broj juniora", "Prosečno vreme kadeta", "Prosečno vreme juniora", "Agencija"};

    public PonudaTableModelKlub(){
        
    }
    
    public PonudaTableModelKlub(List<PonudaVeslaca> ponude){
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
                return ponuda.getIdAgencije();
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
    
    public void obrisiPonudu(int idPonude){
        
        ponude.removeIf(p -> {
            if(p.getId() == idPonude){
                int index = ponude.indexOf(p);
                fireTableRowsDeleted(index, index);
                return true;
            }
            return false;
        });
        
    }

    public void dodajPonudu(PonudaVeslaca kreiranaPonuda) {
        ponude.add(kreiranaPonuda);
        int index = ponude.size() - 1;
        fireTableRowsInserted(index, index);
    }
    

}
