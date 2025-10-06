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
    String[] kolone = {"Datum kreiranja", "Broj kadeta", "Broj juniora", "Prosečno vreme kadeta", "Prosečno vreme juniora", "Klub"};

    public PonudaTableModelAgencija() {
        ponude = new LinkedList<>();
    }

    public PonudaTableModelAgencija(List<PonudaVeslaca> ponude) {
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
                return ponuda.getDatumKreiranja();
            }
            case 1 -> {
                return ponuda.getBrojKadeta();
            }
            case 2 -> {
                return ponuda.getBrojJuniora();
            }
            case 3 -> {
                return ponuda.getProsecnoVremeKadeti();
            }
            case 4 -> {
                return ponuda.getProsecnoVremeJuniori();
            }
            case 5 -> {
                return ponuda.getVeslackiKlub().getNaziv();
            }

            default ->
                throw new AssertionError();
        }

    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public List<PonudaVeslaca> getPonude() {
        return ponude;
    }

    public PonudaVeslaca getPonuda(int row) {
        return ponude.get(row);
    }

    public void setPonude(List<PonudaVeslaca> ponude) {
        this.ponude = ponude;
    }

}
