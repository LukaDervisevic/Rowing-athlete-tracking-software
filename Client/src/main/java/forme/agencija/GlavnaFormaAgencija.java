package forme.agencija;

import forme.tableModeli.KlubTableModel;
import forme.tableModeli.OsvojenaTakmicenjaTableModel;
import forme.tableModeli.PonudaTableModelAgencija;
import forme.tableModeli.StavkaPonudeTableModel;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import klijent.Klijent;
import model.KlubTakmicenje;
import model.PonudaVeslaca;
import model.StavkaPonude;
import model.VeslackiKlub;

/**
 *
 * @author luka
 */
public class GlavnaFormaAgencija extends javax.swing.JFrame {

    private int idAgencije;
    private PonudaTableModelAgencija patm;
    private PonudaTableModelAgencija patvm;
    private KlubTableModel ktm;
    private OsvojenaTakmicenjaTableModel ostm;
    private StavkaPonudeTableModel sptm;

    public GlavnaFormaAgencija() {

        try {

            initComponents();

            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            setSize(screenSize);

            idAgencije = Klijent.getInstance().getUlogovaniNalog().getId();

            List<PonudaVeslaca> ponudeAgencije = Klijent.getInstance().vratiSvePonudeAgencije(idAgencije);
            List<VeslackiKlub> klubovi = Klijent.getInstance().vratiSveKlubove();

            ponudeTable.setModel(new PonudaTableModelAgencija(ponudeAgencije));
            veslackiKluboviTable.setModel(new KlubTableModel(klubovi));
            ponudeVeslacaTable.setModel(new PonudaTableModelAgencija(ponudeAgencije));
            stavkePonudeTable.setModel(new StavkaPonudeTableModel());
            osvojenaTakmicenjaTable.setModel(new OsvojenaTakmicenjaTableModel());

            patm = (PonudaTableModelAgencija) ponudeTable.getModel();
            ktm = (KlubTableModel) veslackiKluboviTable.getModel();
            patvm = (PonudaTableModelAgencija) ponudeVeslacaTable.getModel();
            ostm = (OsvojenaTakmicenjaTableModel) osvojenaTakmicenjaTable.getModel();
            sptm = (StavkaPonudeTableModel) stavkePonudeTable.getModel();

            nalogLabel.setText(Klijent.getInstance().getUlogovaniNalog().getNaziv());

            pretraziInput.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    try {
                        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                            if (pretraziInput.getText().equals("Pretraži id kluba...") || pretraziInput.getText().isEmpty()
                                    || pretraziInput.getText().equals("Pretraži ime kluba...")) {

                                if (cardPanel.getComponentZOrder(kontrolnaTablaPanel) >= 0) {
                                    List<PonudaVeslaca> svePonude = Klijent.getInstance().vratiSvePonudeAgencije(idAgencije);
                                    patm.setPonude(svePonude);
                                    patm.fireTableDataChanged();
                                } else if (cardPanel.getComponentZOrder(pretrazivanjeTakmicenjaPanel) >= 0) {
                                    List<VeslackiKlub> sviKlubovi = Klijent.getInstance().vratiSveKlubove();
                                    ktm.setKlubovi(sviKlubovi);
                                    ktm.fireTableDataChanged();
                                } else if (cardPanel.getComponentZOrder(pretrazivanjePonudaPanel) >= 0) {
                                    List<PonudaVeslaca> svePonude = Klijent.getInstance().vratiSvePonudeAgencije(idAgencije);
                                    patvm.setPonude(svePonude);
                                    patvm.fireTableDataChanged();
                                }

                            } else {
                                String upitZaPretragu = pretraziInput.getText();

                                if (cardPanel.getComponentZOrder(kontrolnaTablaPanel) >= 0) {
                                    int idKluba = Integer.parseInt(upitZaPretragu);
                                    List<PonudaVeslaca> pretrazenePonude;
                                    try {
                                        pretrazenePonude = Klijent.getInstance().pretraziPonudu(new PonudaVeslaca(0, null, 0, 0, 0, 0, null, idKluba, idAgencije));
                                        patm.setPonude(pretrazenePonude);
                                        patm.fireTableDataChanged();
                                    } catch (Exception ex) {
                                        Logger.getLogger(GlavnaFormaAgencija.class.getName()).log(Level.SEVERE, null, ex);
                                    }

                                }

                                if (cardPanel.getComponentZOrder(pretrazivanjeTakmicenjaPanel) >= 0) {
                                    String nazivKluba = upitZaPretragu;
                                    List<VeslackiKlub> pretrazeniKlubovi = Klijent.getInstance().pretraziKlub(new VeslackiKlub(0, nazivKluba, null, null, null, null, null));
                                    ktm.setKlubovi(pretrazeniKlubovi);
                                    ktm.fireTableDataChanged();
                                }

                                if (cardPanel.getComponentZOrder(pretrazivanjePonudaPanel) >= 0) {
                                    int idKluba = Integer.parseInt(upitZaPretragu);
                                    List<PonudaVeslaca> pretrazenePonude;
                                    try {
                                        pretrazenePonude = Klijent.getInstance().pretraziPonudu(new PonudaVeslaca(0, null, 0, 0, 0, 0, null, idKluba, idAgencije));
                                        patvm.setPonude(pretrazenePonude);
                                        patvm.fireTableDataChanged();
                                    } catch (Exception ex) {
                                        Logger.getLogger(GlavnaFormaAgencija.class.getName()).log(Level.SEVERE, null, ex);
                                    }

                                }

                            }
                        }

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                }

            });

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        glavnaFormaPanel6 = new forme.utils.GlavnaFormaPanel();
        jLabel3 = new javax.swing.JLabel();
        pretraziInput = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        glavnaFormaPanel4 = new forme.utils.GlavnaFormaPanel();
        jButton5 = new javax.swing.JButton();
        glavnaFormaPanel5 = new forme.utils.GlavnaFormaPanel();
        jButton6 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        kontrolnaTablaButton = new javax.swing.JButton();
        pretraziButton = new javax.swing.JButton();
        takmicenjaButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        nalogLabel = new javax.swing.JLabel();
        azurirajNalogButton = new javax.swing.JButton();
        cardPanel = new javax.swing.JPanel();
        kontrolnaTablaPanel = new javax.swing.JPanel();
        kFormaPanel3 = new forme.utils.GlavnaFormaPanel();
        ponudeLabel = new javax.swing.JLabel();
        odobriPonuduButton = new javax.swing.JButton();
        odbaciPonuduButton = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        ponudeTable = new forme.utils.GlavnaFormaTable();
        pretrazivanjePonudaPanel = new javax.swing.JPanel();
        glavnaFormaPanel1 = new forme.utils.GlavnaFormaPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ponudeVeslacaTable = new forme.utils.GlavnaFormaTable();
        prikaziStavkeButton = new javax.swing.JButton();
        glavnaFormaPanel2 = new forme.utils.GlavnaFormaPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        stavkePonudeTable = new forme.utils.GlavnaFormaTable();
        jLabel4 = new javax.swing.JLabel();
        pretrazivanjeTakmicenjaPanel = new javax.swing.JPanel();
        glavnaFormaPanel3 = new forme.utils.GlavnaFormaPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        veslackiKluboviTable = new forme.utils.GlavnaFormaTable();
        prikaziTakmicenjaKlubaButton = new javax.swing.JButton();
        glavnaFormaPanel7 = new forme.utils.GlavnaFormaPanel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        osvojenaTakmicenjaTable = new forme.utils.GlavnaFormaTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        glavnaFormaPanel6.setBackground(new java.awt.Color(221, 221, 221));
        glavnaFormaPanel6.setMaximumSize(new java.awt.Dimension(285, 44));
        glavnaFormaPanel6.setMinimumSize(new java.awt.Dimension(285, 44));

        pretraziInput.setBackground(new java.awt.Color(221, 221, 221));
        pretraziInput.setFont(new java.awt.Font("JetBrains Mono", 2, 18)); // NOI18N
        pretraziInput.setText("Pretraži id kluba...");
        pretraziInput.setBorder(null);
        pretraziInput.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                pretraziInputFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                pretraziInputFocusLost(evt);
            }
        });

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/slike/search32.png"))); // NOI18N

        javax.swing.GroupLayout glavnaFormaPanel6Layout = new javax.swing.GroupLayout(glavnaFormaPanel6);
        glavnaFormaPanel6.setLayout(glavnaFormaPanel6Layout);
        glavnaFormaPanel6Layout.setHorizontalGroup(
            glavnaFormaPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(glavnaFormaPanel6Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pretraziInput, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        glavnaFormaPanel6Layout.setVerticalGroup(
            glavnaFormaPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(glavnaFormaPanel6Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(glavnaFormaPanel6Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(glavnaFormaPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pretraziInput)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        glavnaFormaPanel4.setBackground(new java.awt.Color(221, 221, 221));

        jButton5.setBackground(new java.awt.Color(221, 221, 221));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/slike/zvono32.png"))); // NOI18N
        jButton5.setBorderPainted(false);
        jButton5.setRolloverEnabled(false);

        javax.swing.GroupLayout glavnaFormaPanel4Layout = new javax.swing.GroupLayout(glavnaFormaPanel4);
        glavnaFormaPanel4.setLayout(glavnaFormaPanel4Layout);
        glavnaFormaPanel4Layout.setHorizontalGroup(
            glavnaFormaPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(glavnaFormaPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                .addContainerGap())
        );
        glavnaFormaPanel4Layout.setVerticalGroup(
            glavnaFormaPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, glavnaFormaPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        glavnaFormaPanel5.setBackground(new java.awt.Color(221, 221, 221));

        jButton6.setBackground(new java.awt.Color(221, 221, 221));
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/slike/upitnik32.png"))); // NOI18N
        jButton6.setBorderPainted(false);
        jButton6.setPreferredSize(new java.awt.Dimension(32, 32));
        jButton6.setRolloverEnabled(false);

        javax.swing.GroupLayout glavnaFormaPanel5Layout = new javax.swing.GroupLayout(glavnaFormaPanel5);
        glavnaFormaPanel5.setLayout(glavnaFormaPanel5Layout);
        glavnaFormaPanel5Layout.setHorizontalGroup(
            glavnaFormaPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(glavnaFormaPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                .addContainerGap())
        );
        glavnaFormaPanel5Layout.setVerticalGroup(
            glavnaFormaPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, glavnaFormaPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(238, 236, 236));

        kontrolnaTablaButton.setBackground(new java.awt.Color(238, 236, 236));
        kontrolnaTablaButton.setFont(new java.awt.Font("JetBrains Mono", 0, 24)); // NOI18N
        kontrolnaTablaButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/slike/kontrolnaTabla32.png"))); // NOI18N
        kontrolnaTablaButton.setText(" Kontrolna Tabla");
        kontrolnaTablaButton.setBorder(null);
        kontrolnaTablaButton.setBorderPainted(false);
        kontrolnaTablaButton.setContentAreaFilled(false);
        kontrolnaTablaButton.setFocusPainted(false);
        kontrolnaTablaButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                kontrolnaTablaButtonMouseEntered(evt);
            }
        });
        kontrolnaTablaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kontrolnaTablaButtonActionPerformed(evt);
            }
        });

        pretraziButton.setBackground(new java.awt.Color(238, 236, 236));
        pretraziButton.setFont(new java.awt.Font("JetBrains Mono", 0, 24)); // NOI18N
        pretraziButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/slike/ergometar32.png"))); // NOI18N
        pretraziButton.setText("Pretraži ponude");
        pretraziButton.setBorder(null);
        pretraziButton.setBorderPainted(false);
        pretraziButton.setContentAreaFilled(false);
        pretraziButton.setFocusPainted(false);
        pretraziButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pretraziButtonActionPerformed(evt);
            }
        });

        takmicenjaButton.setBackground(new java.awt.Color(238, 236, 236));
        takmicenjaButton.setFont(new java.awt.Font("JetBrains Mono", 0, 24)); // NOI18N
        takmicenjaButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/slike/takmicenje32.png"))); // NOI18N
        takmicenjaButton.setText("Takmičenja/klubovi");
        takmicenjaButton.setBorder(null);
        takmicenjaButton.setBorderPainted(false);
        takmicenjaButton.setContentAreaFilled(false);
        takmicenjaButton.setFocusPainted(false);
        takmicenjaButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        takmicenjaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                takmicenjaButtonActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("JetBrains Mono", 3, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/slike/rowing-boat(1).png"))); // NOI18N
        jLabel2.setText("Pretraži veslače");

        nalogLabel.setFont(new java.awt.Font("JetBrains Mono", 0, 24)); // NOI18N
        nalogLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        nalogLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/slike/user.png"))); // NOI18N
        nalogLabel.setText("   jLabel20");

        azurirajNalogButton.setBackground(new java.awt.Color(238, 236, 236));
        azurirajNalogButton.setFont(new java.awt.Font("JetBrains Mono", 1, 19)); // NOI18N
        azurirajNalogButton.setForeground(new java.awt.Color(255, 51, 51));
        azurirajNalogButton.setText("Ažuriraj nalog");
        azurirajNalogButton.setToolTipText("");
        azurirajNalogButton.setBorder(null);
        azurirajNalogButton.setFocusPainted(false);
        azurirajNalogButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                azurirajNalogButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pretraziButton)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(kontrolnaTablaButton, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(takmicenjaButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(nalogLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)
                        .addComponent(azurirajNalogButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(96, 96, 96)
                .addComponent(kontrolnaTablaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(pretraziButton, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(takmicenjaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(246, 246, 246)
                .addComponent(nalogLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(azurirajNalogButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        cardPanel.setBackground(new java.awt.Color(221, 221, 221));
        cardPanel.setLayout(new java.awt.CardLayout());

        kontrolnaTablaPanel.setBackground(new java.awt.Color(221, 221, 221));

        ponudeLabel.setFont(new java.awt.Font("JetBrains Mono", 1, 28)); // NOI18N
        ponudeLabel.setText("Ponude Veslača");

        odobriPonuduButton.setBackground(new java.awt.Color(13, 146, 244));
        odobriPonuduButton.setFont(new java.awt.Font("JetBrains Mono", 1, 19)); // NOI18N
        odobriPonuduButton.setForeground(new java.awt.Color(255, 255, 255));
        odobriPonuduButton.setText("Odobri");

        odbaciPonuduButton.setBackground(new java.awt.Color(13, 146, 244));
        odbaciPonuduButton.setFont(new java.awt.Font("JetBrains Mono", 1, 19)); // NOI18N
        odbaciPonuduButton.setForeground(new java.awt.Color(255, 255, 255));
        odbaciPonuduButton.setText("Odbaci");
        odbaciPonuduButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                odbaciPonuduButtonActionPerformed(evt);
            }
        });

        ponudeTable.setModel(new javax.swing.table.DefaultTableModel(
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
        ponudeTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jScrollPane4.setViewportView(ponudeTable);

        javax.swing.GroupLayout kFormaPanel3Layout = new javax.swing.GroupLayout(kFormaPanel3);
        kFormaPanel3.setLayout(kFormaPanel3Layout);
        kFormaPanel3Layout.setHorizontalGroup(
            kFormaPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kFormaPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(odbaciPonuduButton, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(odobriPonuduButton, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1335, Short.MAX_VALUE)
            .addGroup(kFormaPanel3Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(ponudeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        kFormaPanel3Layout.setVerticalGroup(
            kFormaPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kFormaPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(ponudeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 593, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(kFormaPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(odbaciPonuduButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(odobriPonuduButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );

        javax.swing.GroupLayout kontrolnaTablaPanelLayout = new javax.swing.GroupLayout(kontrolnaTablaPanel);
        kontrolnaTablaPanel.setLayout(kontrolnaTablaPanelLayout);
        kontrolnaTablaPanelLayout.setHorizontalGroup(
            kontrolnaTablaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kontrolnaTablaPanelLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(kFormaPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(210, Short.MAX_VALUE))
        );
        kontrolnaTablaPanelLayout.setVerticalGroup(
            kontrolnaTablaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kontrolnaTablaPanelLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(kFormaPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(838, Short.MAX_VALUE))
        );

        cardPanel.add(kontrolnaTablaPanel, "card2");

        pretrazivanjePonudaPanel.setBackground(new java.awt.Color(221, 221, 221));

        jLabel1.setFont(new java.awt.Font("JetBrains Mono", 3, 24)); // NOI18N
        jLabel1.setText("Pretrage ponuda veslača");

        ponudeVeslacaTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(ponudeVeslacaTable);

        prikaziStavkeButton.setBackground(new java.awt.Color(13, 146, 244));
        prikaziStavkeButton.setFont(new java.awt.Font("JetBrains Mono", 1, 19)); // NOI18N
        prikaziStavkeButton.setForeground(new java.awt.Color(255, 255, 255));
        prikaziStavkeButton.setText("Prikaži");
        prikaziStavkeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prikaziStavkeButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout glavnaFormaPanel1Layout = new javax.swing.GroupLayout(glavnaFormaPanel1);
        glavnaFormaPanel1.setLayout(glavnaFormaPanel1Layout);
        glavnaFormaPanel1Layout.setHorizontalGroup(
            glavnaFormaPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1330, Short.MAX_VALUE)
            .addGroup(glavnaFormaPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, glavnaFormaPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(prikaziStavkeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );
        glavnaFormaPanel1Layout.setVerticalGroup(
            glavnaFormaPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(glavnaFormaPanel1Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(prikaziStavkeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        stavkePonudeTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(stavkePonudeTable);

        jLabel4.setFont(new java.awt.Font("JetBrains Mono", 3, 24)); // NOI18N
        jLabel4.setText("Prikaz Stavki ponude");

        javax.swing.GroupLayout glavnaFormaPanel2Layout = new javax.swing.GroupLayout(glavnaFormaPanel2);
        glavnaFormaPanel2.setLayout(glavnaFormaPanel2Layout);
        glavnaFormaPanel2Layout.setHorizontalGroup(
            glavnaFormaPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addGroup(glavnaFormaPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        glavnaFormaPanel2Layout.setVerticalGroup(
            glavnaFormaPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, glavnaFormaPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(96, 96, 96))
        );

        javax.swing.GroupLayout pretrazivanjePonudaPanelLayout = new javax.swing.GroupLayout(pretrazivanjePonudaPanel);
        pretrazivanjePonudaPanel.setLayout(pretrazivanjePonudaPanelLayout);
        pretrazivanjePonudaPanelLayout.setHorizontalGroup(
            pretrazivanjePonudaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pretrazivanjePonudaPanelLayout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(pretrazivanjePonudaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(glavnaFormaPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(glavnaFormaPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(214, Short.MAX_VALUE))
        );
        pretrazivanjePonudaPanelLayout.setVerticalGroup(
            pretrazivanjePonudaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pretrazivanjePonudaPanelLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(glavnaFormaPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(glavnaFormaPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(767, Short.MAX_VALUE))
        );

        cardPanel.add(pretrazivanjePonudaPanel, "card3");

        pretrazivanjeTakmicenjaPanel.setBackground(new java.awt.Color(221, 221, 221));

        jLabel5.setFont(new java.awt.Font("JetBrains Mono", 3, 24)); // NOI18N
        jLabel5.setText("Pretraga veslačkih klubova");

        veslackiKluboviTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(veslackiKluboviTable);

        prikaziTakmicenjaKlubaButton.setBackground(new java.awt.Color(14, 146, 244));
        prikaziTakmicenjaKlubaButton.setFont(new java.awt.Font("JetBrains Mono", 1, 18)); // NOI18N
        prikaziTakmicenjaKlubaButton.setForeground(new java.awt.Color(255, 255, 255));
        prikaziTakmicenjaKlubaButton.setText("Prikaži takmičenja kluba");
        prikaziTakmicenjaKlubaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prikaziTakmicenjaKlubaButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout glavnaFormaPanel3Layout = new javax.swing.GroupLayout(glavnaFormaPanel3);
        glavnaFormaPanel3.setLayout(glavnaFormaPanel3Layout);
        glavnaFormaPanel3Layout.setHorizontalGroup(
            glavnaFormaPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
            .addGroup(glavnaFormaPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, glavnaFormaPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(prikaziTakmicenjaKlubaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );
        glavnaFormaPanel3Layout.setVerticalGroup(
            glavnaFormaPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(glavnaFormaPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(prikaziTakmicenjaKlubaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jLabel6.setFont(new java.awt.Font("JetBrains Mono", 3, 24)); // NOI18N
        jLabel6.setText("Pretraga osvojenih takmičenja kluba");

        osvojenaTakmicenjaTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane5.setViewportView(osvojenaTakmicenjaTable);

        javax.swing.GroupLayout glavnaFormaPanel7Layout = new javax.swing.GroupLayout(glavnaFormaPanel7);
        glavnaFormaPanel7.setLayout(glavnaFormaPanel7Layout);
        glavnaFormaPanel7Layout.setHorizontalGroup(
            glavnaFormaPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
            .addGroup(glavnaFormaPanel7Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        glavnaFormaPanel7Layout.setVerticalGroup(
            glavnaFormaPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(glavnaFormaPanel7Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 648, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pretrazivanjeTakmicenjaPanelLayout = new javax.swing.GroupLayout(pretrazivanjeTakmicenjaPanel);
        pretrazivanjeTakmicenjaPanel.setLayout(pretrazivanjeTakmicenjaPanelLayout);
        pretrazivanjeTakmicenjaPanelLayout.setHorizontalGroup(
            pretrazivanjeTakmicenjaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pretrazivanjeTakmicenjaPanelLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(glavnaFormaPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(glavnaFormaPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(175, Short.MAX_VALUE))
        );
        pretrazivanjeTakmicenjaPanelLayout.setVerticalGroup(
            pretrazivanjeTakmicenjaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pretrazivanjeTakmicenjaPanelLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(pretrazivanjeTakmicenjaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(glavnaFormaPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(glavnaFormaPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(779, Short.MAX_VALUE))
        );

        cardPanel.add(pretrazivanjeTakmicenjaPanel, "card4");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(glavnaFormaPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(glavnaFormaPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(glavnaFormaPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(cardPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(9, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(glavnaFormaPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(glavnaFormaPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(glavnaFormaPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cardPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void kontrolnaTablaButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kontrolnaTablaButtonMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_kontrolnaTablaButtonMouseEntered

    private void kontrolnaTablaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kontrolnaTablaButtonActionPerformed
        // TODO add your handling code here:
        cardPanel.removeAll();
        cardPanel.add(kontrolnaTablaPanel);
        cardPanel.repaint();
        cardPanel.revalidate();
    }//GEN-LAST:event_kontrolnaTablaButtonActionPerformed

    private void pretraziButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pretraziButtonActionPerformed
        // TODO add your handling code here:
        pretraziInput.setText("Pretraži id kluba...");
        cardPanel.removeAll();
        cardPanel.add(pretrazivanjePonudaPanel);
        cardPanel.repaint();
        cardPanel.revalidate();
    }//GEN-LAST:event_pretraziButtonActionPerformed

    private void takmicenjaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_takmicenjaButtonActionPerformed
        pretraziInput.setText("Pretraži ime kluba...");
        cardPanel.removeAll();
        cardPanel.add(pretrazivanjeTakmicenjaPanel);
        cardPanel.repaint();
        cardPanel.revalidate();
    }//GEN-LAST:event_takmicenjaButtonActionPerformed

    private void azurirajNalogButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_azurirajNalogButtonActionPerformed
        // TODO add your handling code here:
        IzmeniPodatkeAgencijaForma iaf = new IzmeniPodatkeAgencijaForma(this, true);

        iaf.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {

                if (Klijent.getInstance().isOdjavaSignal()) {
                    JOptionPane.showMessageDialog(null, "Nalog obrisan, gašenje programa...", "Info", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                    System.exit(0);
                }

            }

        });


    }//GEN-LAST:event_azurirajNalogButtonActionPerformed

    private void pretraziInputFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pretraziInputFocusGained
        // TODO add your handling code here:
        if (pretraziInput.getText().equals("Pretraži ime kluba...") || pretraziInput.getText().equals("Pretraži id kluba...")) {
            pretraziInput.setText("");
        }
    }//GEN-LAST:event_pretraziInputFocusGained

    private void pretraziInputFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pretraziInputFocusLost
        // TODO add your handling code here:
        if (pretraziInput.getText().isEmpty()) {
            if (cardPanel.getComponentZOrder(kontrolnaTablaPanel) >= 0 || cardPanel.getComponentZOrder(pretrazivanjePonudaPanel) >= 0) {
                pretraziInput.setText("Pretraži id kluba...");
            } else {
                pretraziInput.setText("Pretraži ime kluba...");
            }
        }
    }//GEN-LAST:event_pretraziInputFocusLost

    private void odbaciPonuduButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_odbaciPonuduButtonActionPerformed
        // TODO add your handling code here:
        int odgovor = JOptionPane.showConfirmDialog(this, "Da li ste sigurni da želite da odbacite ponudu");
        if (odgovor == JOptionPane.YES_OPTION) {

        }
    }//GEN-LAST:event_odbaciPonuduButtonActionPerformed

    private void prikaziTakmicenjaKlubaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prikaziTakmicenjaKlubaButtonActionPerformed

        try{
            
        
        if (veslackiKluboviTable.getSelectedRow() != -1) {

            int idKluba = (int) veslackiKluboviTable.getValueAt(veslackiKluboviTable.getSelectedRow(), 0);
            List<KlubTakmicenje> takmicenjaKluba = Klijent.getInstance().vratiTakmicenjaKluba(idKluba);
            ostm.setOsvojenaTakmicenja(takmicenjaKluba);
            ostm.fireTableDataChanged();

        } else {
            JOptionPane.showMessageDialog(this, "Klub nije selektovan, selektujte klub kako bi prikazali takmicenja", "Greska", JOptionPane.ERROR_MESSAGE);
        }
        
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
        
    }//GEN-LAST:event_prikaziTakmicenjaKlubaButtonActionPerformed

    private void prikaziStavkeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prikaziStavkeButtonActionPerformed
        // TODO add your handling code here:
        
        try{
            
        
        if (ponudeVeslacaTable.getSelectedRow() != -1) {

            int idPonude = (int) ponudeVeslacaTable.getValueAt(ponudeVeslacaTable.getSelectedRow(), 0);

            List<StavkaPonude> stavkePonude = Klijent.getInstance().vratiSveStavkePonude(idPonude);
            sptm.setStavkePonude(stavkePonude);
            sptm.fireTableDataChanged();

        } else {
            JOptionPane.showMessageDialog(this, "Ponuda nije selektovana, selektujte ponudu kako bi prikazali stavke ponude", "Greska", JOptionPane.ERROR_MESSAGE);

        }
        
        }catch(Exception ex){
            ex.printStackTrace();
        }

    }//GEN-LAST:event_prikaziStavkeButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton azurirajNalogButton;
    private javax.swing.JPanel cardPanel;
    private forme.utils.GlavnaFormaPanel glavnaFormaPanel1;
    private forme.utils.GlavnaFormaPanel glavnaFormaPanel2;
    private forme.utils.GlavnaFormaPanel glavnaFormaPanel3;
    private forme.utils.GlavnaFormaPanel glavnaFormaPanel4;
    private forme.utils.GlavnaFormaPanel glavnaFormaPanel5;
    private forme.utils.GlavnaFormaPanel glavnaFormaPanel6;
    private forme.utils.GlavnaFormaPanel glavnaFormaPanel7;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private forme.utils.GlavnaFormaPanel kFormaPanel3;
    private javax.swing.JButton kontrolnaTablaButton;
    private javax.swing.JPanel kontrolnaTablaPanel;
    private javax.swing.JLabel nalogLabel;
    private javax.swing.JButton odbaciPonuduButton;
    private javax.swing.JButton odobriPonuduButton;
    private forme.utils.GlavnaFormaTable osvojenaTakmicenjaTable;
    private javax.swing.JLabel ponudeLabel;
    private forme.utils.GlavnaFormaTable ponudeTable;
    private forme.utils.GlavnaFormaTable ponudeVeslacaTable;
    private javax.swing.JButton pretraziButton;
    private javax.swing.JTextField pretraziInput;
    private javax.swing.JPanel pretrazivanjePonudaPanel;
    private javax.swing.JPanel pretrazivanjeTakmicenjaPanel;
    private javax.swing.JButton prikaziStavkeButton;
    private javax.swing.JButton prikaziTakmicenjaKlubaButton;
    private forme.utils.GlavnaFormaTable stavkePonudeTable;
    private javax.swing.JButton takmicenjaButton;
    private forme.utils.GlavnaFormaTable veslackiKluboviTable;
    // End of variables declaration//GEN-END:variables
}
