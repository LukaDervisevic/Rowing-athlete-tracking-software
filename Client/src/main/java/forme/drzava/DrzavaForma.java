package forme.drzava;

import forme.tableModeli.DrzavaTableModel;
import java.awt.Window;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import klijent.Klijent;
import model.Drzava;

/**
 *
 * @author Luka
 */
public class DrzavaForma extends javax.swing.JDialog {
    
    private DrzavaTableModel dtm;
    List<Drzava> drzave;
    
    public DrzavaForma(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    public DrzavaForma(Window window){
        super(window);
        initComponents();
        try {
            drzave = Klijent.getInstance().vratiSveDrzave();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,"Greška pri učitavanju država","Greška", JOptionPane.ERROR_MESSAGE);
        }
        drzavaTable.setModel(new DrzavaTableModel(drzave));
        dtm = (DrzavaTableModel) drzavaTable.getModel();
        
        setLocationRelativeTo(window);
        setModal(true);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        drzavaTable = new forme.utils.GlavnaFormaTable();
        dodajDrzavuBtn = new javax.swing.JButton();
        obrisiDrzavuBtn = new javax.swing.JButton();
        drzavaInput = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("JetBrains Mono NL", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Unesite naziv države");

        jLabel3.setFont(new java.awt.Font("JetBrains Mono", 3, 18)); // NOI18N
        jLabel3.setText("Naziv države:");

        drzavaTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(drzavaTable);

        dodajDrzavuBtn.setBackground(new java.awt.Color(13, 146, 244));
        dodajDrzavuBtn.setFont(new java.awt.Font("JetBrains Mono", 1, 19)); // NOI18N
        dodajDrzavuBtn.setForeground(new java.awt.Color(255, 255, 255));
        dodajDrzavuBtn.setText("Dodaj");
        dodajDrzavuBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dodajDrzavuBtnActionPerformed(evt);
            }
        });

        obrisiDrzavuBtn.setBackground(new java.awt.Color(13, 146, 244));
        obrisiDrzavuBtn.setFont(new java.awt.Font("JetBrains Mono", 1, 19)); // NOI18N
        obrisiDrzavuBtn.setForeground(new java.awt.Color(255, 255, 255));
        obrisiDrzavuBtn.setText("Obriši");
        obrisiDrzavuBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                obrisiDrzavuBtnActionPerformed(evt);
            }
        });

        drzavaInput.setFont(new java.awt.Font("JetBrains Mono", 2, 18)); // NOI18N
        drzavaInput.setForeground(new java.awt.Color(153, 153, 153));
        drzavaInput.setText("Unesite državu...");
        drzavaInput.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));
        drzavaInput.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                drzavaInputFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                drzavaInputFocusLost(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(drzavaInput))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(obrisiDrzavuBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dodajDrzavuBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(drzavaInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dodajDrzavuBtn)
                    .addComponent(obrisiDrzavuBtn))
                .addGap(14, 14, 14))
        );

        getContentPane().add(jPanel2);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void dodajDrzavuBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dodajDrzavuBtnActionPerformed
        // TODO add your handling code here:
        if(!drzavaInput.getText().isEmpty()){
            try {
                Drzava drzava = Klijent.getInstance().ubaciDrzavu(new Drzava(0,drzavaInput.getText()));
                dtm.dodajDrzava(drzava);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,"Greška pri kreiranju države","Greška", JOptionPane.ERROR_MESSAGE);
            }
        }
        
    }//GEN-LAST:event_dodajDrzavuBtnActionPerformed

    private void obrisiDrzavuBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_obrisiDrzavuBtnActionPerformed
        // TODO add your handling code here:
        if(drzavaTable.getSelectedRow() != -1){
            try {
                Integer idDrzave = Klijent.getInstance().obrisiDrzavu((Integer) drzavaTable.getValueAt(drzavaTable.getSelectedRow(), 0));
                dtm.obrisiVeslaca(idDrzave);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,"Greška pri brisanju države","Greška", JOptionPane.ERROR_MESSAGE);
            }
            
        }else{
            JOptionPane.showMessageDialog(this,"Država nije selektovana za brisanje","Greška", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_obrisiDrzavuBtnActionPerformed

    private void drzavaInputFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_drzavaInputFocusGained
        if (drzavaInput.getText().equals("Unesite državu...")) {
            drzavaInput.setText("");
        }
    }//GEN-LAST:event_drzavaInputFocusGained

    private void drzavaInputFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_drzavaInputFocusLost
        // TODO add your handling code here:
        if (drzavaInput.getText().isEmpty()) {
            drzavaInput.setText("Unesite državu...");
        }
    }//GEN-LAST:event_drzavaInputFocusLost

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton dodajDrzavuBtn;
    private javax.swing.JTextField drzavaInput;
    private forme.utils.GlavnaFormaTable drzavaTable;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton obrisiDrzavuBtn;
    // End of variables declaration//GEN-END:variables
}
