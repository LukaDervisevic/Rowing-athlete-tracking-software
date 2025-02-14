package forme.utils;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.text.SimpleDateFormat;
import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;

public class GlavnaFormaTable extends JTable {

    public GlavnaFormaTable() {
        setShowHorizontalLines(true);
        setGridColor(new Color(230, 230, 230));
        setRowHeight(30);
        setFont(new Font("Jetbrains Mono", 1, 14));
        setBorder(BorderFactory.createEmptyBorder());
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        getTableHeader().setReorderingAllowed(false);
        setRowSelectionAllowed(true);
        
        setSelectionBackground(new Color(14, 146, 244)); // Set row background to blue when selected
        setSelectionForeground(Color.WHITE);
        
        

        getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

                GlavnaFormaTableHeader header = new GlavnaFormaTableHeader(value + "");

                return header;

            }

        });
        setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

                Component com = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
                
                
                
                if (isSelected) {
                    com.setBackground(new Color(14,146,244));
                    com.setForeground(Color.WHITE);
                    

                } else {
                    com.setBackground(Color.WHITE);  // Set background to white for non-selected cells
                    com.setForeground(Color.BLACK);
                }

                if (value instanceof java.util.Date date) {
                    setText(sdf.format(date));
                }               
                return com;

            }

        });
    }

    
}
