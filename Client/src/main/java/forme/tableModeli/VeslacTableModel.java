package forme.tableModeli;

import java.util.LinkedList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.KategorijaVeslaca;
import model.Veslac;

/**
 *
 * @author luka
 */
public class VeslacTableModel extends AbstractTableModel{
    
    private List<Veslac> veslaci;
    
    String[] kolone = {"Ime Prezime","Datum Rodjenja","Visina","Tezina","Kategorija","BMI","Najbolje vreme","Datum Upisa"};
    
    LinkedList<Veslac> veslaciZaAzuriranje = new LinkedList<>();
    
    public VeslacTableModel(){
        this.veslaci = new LinkedList<>();
    }
    
    public VeslacTableModel(List<Veslac> veslaci){
        this.veslaci = veslaci;
    }
    
    @Override
    public int getRowCount() {
        return veslaci.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        Veslac veslac = veslaci.get(rowIndex);
         
        switch (columnIndex) {
            case 0 -> {
                return veslac.getImePrezime();
            }
            case 1 -> {
                return veslac.getDatumRodjenja();
            }
            case 2 -> {
                return veslac.getVisina();
            }
            case 3 -> {
                return veslac.getTezina();
            }
            case 4 -> {
                return veslac.getKategorija();
            }
            case 5 -> {
                return veslac.getBMI();
            }
            case 6 -> {
                return veslac.getNajboljeVreme();
            }
            case 7 -> {
                return veslac.getDatumUpisa();
            }
            default -> throw new AssertionError();
        }
        
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Veslac veslac = veslaci.get(rowIndex);
        System.out.println(aValue);
        switch (columnIndex) {
            case 1 -> veslac.setImePrezime((String) aValue);
            case 3 -> veslac.setVisina(Float.parseFloat((String) aValue));
            case 4 -> veslac.setTezina(Float.parseFloat((String) aValue));
            case 5 -> veslac.setKategorija(KategorijaVeslaca.valueOf((String) aValue));
            case 7 -> veslac.setNajboljeVreme(Float.parseFloat((String) aValue));
            default -> throw new AssertionError();
        }
        if(!veslaciZaAzuriranje.contains(veslac)){
            veslaciZaAzuriranje.add(veslac);
        }
    }

    public List<Veslac> getVeslaci() {
        return  veslaci;
    }
    
    public Veslac getVeslac(int row) {
        return veslaci.get(row);
    }

    public List<Veslac> getVeslaciZaAzuriranje() {
        return veslaciZaAzuriranje;
    }

    public void setVeslaci(List<Veslac> veslaci) {
        this.veslaci = veslaci;
    }

    public void dodajVeslaca(Veslac v){
        veslaci.add(v);
        int poslednjiIndex = veslaci.size() - 1;
        fireTableRowsInserted(poslednjiIndex, poslednjiIndex);
        
    }
    
    public void obrisiVeslaca(int id){
        veslaci.removeIf(v -> {
            if(v.getId() == id){
                int index = veslaci.indexOf(v);
                fireTableRowsDeleted(index,index);
                return true;
            }
            return false;
        });
    }
    
    
}
