package forme.tableModeli;

import java.util.LinkedList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.KlubTakmicenje;

/**
 *
 * @author Luka
 */
public class OsvojenaTakmicenjaTableModel extends AbstractTableModel {

    List<KlubTakmicenje> osvojenaTakmicenja = new LinkedList<>();
    String[] kolone = {"id", "Naziv", "Kategorija", "Vrsta trke", "Datum", "Mesto"};

    public OsvojenaTakmicenjaTableModel() {

    }

    public OsvojenaTakmicenjaTableModel(List<KlubTakmicenje> osvojenaTakmicenja) {
        this.osvojenaTakmicenja = osvojenaTakmicenja;
    }

    @Override
    public int getRowCount() {
        return osvojenaTakmicenja.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        KlubTakmicenje kt = osvojenaTakmicenja.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return kt.getTakmicenje().getId();
            case 1:
                return kt.getTakmicenje().getNaziv();
            case 2:
                return kt.getTakmicenje().getStarosnaKategorija();
            case 3:
                return kt.getTakmicenje().getVrstaTrke();
            case 4:
                return kt.getTakmicenje().getDatum();
            case 5:
                return kt.getMesto();
            default:
                throw new AssertionError();
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public List<KlubTakmicenje> getOsvojenaTakmicenja() {
        return osvojenaTakmicenja;
    }

    public void setOsvojenaTakmicenja(List<KlubTakmicenje> osvojenaTakmicenja) {
        this.osvojenaTakmicenja = osvojenaTakmicenja;
    }

    public void dodajOsvojenoTakmicenje(KlubTakmicenje kt) {
        osvojenaTakmicenja.add(kt);
        int poslednjiIndex = osvojenaTakmicenja.size() - 1;
        fireTableRowsInserted(poslednjiIndex, poslednjiIndex);
    }

    public void obrisiOsvojenoTakmicenje(int idKluba, int idTakmicenja, int mesto) {
        osvojenaTakmicenja.removeIf(kt -> {

            if (mesto == 0) {
                if (kt.getKlub().getId() == idKluba && kt.getTakmicenje().getId() == idTakmicenja) {
                    int index = osvojenaTakmicenja.indexOf(kt);
                    fireTableRowsDeleted(index, index);
                    return true;
                }
            }

            else if (kt.getKlub().getId() == idKluba && kt.getTakmicenje().getId() == idTakmicenja && kt.getMesto() == mesto) {
                int index = osvojenaTakmicenja.indexOf(kt);
                fireTableRowsDeleted(index, index);
                return true;
            }
            return false;
        });
    }

}
