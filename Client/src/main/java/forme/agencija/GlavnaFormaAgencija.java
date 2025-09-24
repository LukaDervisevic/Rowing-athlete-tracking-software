package forme.agencija;

import com.formdev.flatlaf.FlatLightLaf;
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
import java.util.LinkedList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import kontroler.Kontroler;
import model.Agencija;
import model.KlubTakmicenje;
import model.PonudaVeslaca;
import model.StavkaPonude;
import model.VeslackiKlub;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author luka
 */
public class GlavnaFormaAgencija extends javax.swing.JFrame {
    private int idAgencije;

    private List<PonudaVeslaca> ponudeAgencije = new LinkedList<>();
    private List<VeslackiKlub> klubovi = new LinkedList<>();

    private PonudaTableModelAgencija patvm;
    private KlubTableModel ktm;
    private OsvojenaTakmicenjaTableModel ostm;
    private StavkaPonudeTableModel sptm;

    private static final Logger logger = LogManager.getRootLogger();

    public GlavnaFormaAgencija() {
        try {
            
            UIManager.setLookAndFeel(new FlatLightLaf());
            initComponents();
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            setSize(screenSize);

            idAgencije = ((Agencija) Kontroler.getInstance().getUlogovaniNalog()).getId();
            ponudeAgencije = Kontroler.getInstance().vratiListuPonude(" id_agencije = " + idAgencije, new LinkedList<>());
            ponudeVeslacaTable.setModel(new PonudaTableModelAgencija(ponudeAgencije));
            patvm = (PonudaTableModelAgencija) ponudeVeslacaTable.getModel();

            stavkePonudeTable.setModel(new StavkaPonudeTableModel());
            sptm = (StavkaPonudeTableModel) stavkePonudeTable.getModel();

            nalogLabel.setText(((Agencija) Kontroler.getInstance().getUlogovaniNalog()).getNaziv());
            pretraziInput.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() != KeyEvent.VK_ENTER) {
                        return;
                    }

                    try {
                        if (pretraziInput.getText().equals("Pretraži naziv kluba...") || pretraziInput.getText().isEmpty()
                                || pretraziInput.getText().equals("Pretraži naziv veslača...")) {

                            if (cardPanel.getComponentZOrder(pretrazivanjeTakmicenjaPanel) >= 0) {
                                List<VeslackiKlub> sviKlubovi = Kontroler.getInstance().vratiListuSviVeslackiKlub(new LinkedList<>());
                                ktm.setKlubovi(sviKlubovi);
                                ktm.fireTableDataChanged();

                            } else if (cardPanel.getComponentZOrder(pretrazivanjePonudaPanel) >= 0) {
                                List<PonudaVeslaca> svePonude = Kontroler.getInstance().vratiListuPonude(" id_agencije = " + idAgencije, new LinkedList<>());
                                patvm.setPonude(svePonude);
                                patvm.fireTableDataChanged();
                            }

                        } else {
                            String upitZaPretragu = pretraziInput.getText();
                            if (cardPanel.getComponentZOrder(pretrazivanjeTakmicenjaPanel) >= 0) {

//                                    String nazivKluba = upitZaPretragu;
//                                    List<VeslackiKlub> pretrazeniKlubovi = Klijent.getInstance().pretraziKlub(nazivKluba);
//                                    ktm.setKlubovi(pretrazeniKlubovi);
//                                    ktm.fireTableDataChanged();
                            }

                            if (cardPanel.getComponentZOrder(pretrazivanjePonudaPanel) >= 0) {
                                String kriterijum = radio1.isSelected() ? " VK.naziv LIKE '%" + upitZaPretragu + "%'" : " V.ime_prezime LIKE '%" + upitZaPretragu + "%'";
                                kriterijum += " AND A.id = " + idAgencije;
                                List<PonudaVeslaca> pretrazenePonude;

                                pretrazenePonude = Kontroler.getInstance().vratiListuPonude(kriterijum, new LinkedList<>());
                                patvm.setPonude(pretrazenePonude);
                                patvm.fireTableDataChanged();
                            }

                        }
                    } catch (Exception ex) {
                        logger.error(ex.getMessage());
                    }

                }

            });
            
        } catch (Exception ex) {
            logger.error(ex.getMessage());
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
        pretraziButton = new javax.swing.JButton();
        takmicenjaButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        nalogLabel = new javax.swing.JLabel();
        azurirajNalogButton = new javax.swing.JButton();
        cardPanel = new javax.swing.JPanel();
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
        radio1 = new javax.swing.JRadioButton();
        radio2 = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        glavnaFormaPanel6.setBackground(new java.awt.Color(221, 221, 221));
        glavnaFormaPanel6.setMaximumSize(new java.awt.Dimension(285, 44));
        glavnaFormaPanel6.setMinimumSize(new java.awt.Dimension(285, 44));

        pretraziInput.setBackground(new java.awt.Color(221, 221, 221));
        pretraziInput.setFont(new java.awt.Font("JetBrains Mono", 2, 18)); // NOI18N
        pretraziInput.setText("Pretraži naziv kluba...");
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
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(takmicenjaButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(nalogLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)
                            .addComponent(azurirajNalogButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(pretraziButton)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(91, 91, 91)
                .addComponent(pretraziButton, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(takmicenjaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(368, 368, 368)
                .addComponent(nalogLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(azurirajNalogButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        cardPanel.setBackground(new java.awt.Color(221, 221, 221));
        cardPanel.setLayout(new java.awt.CardLayout());

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

        radio1.setFont(new java.awt.Font("JetBrains Mono", 0, 18)); // NOI18N
        radio1.setText("Veslački klub");
        radio1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radio1ActionPerformed(evt);
            }
        });

        radio2.setFont(new java.awt.Font("JetBrains Mono", 0, 18)); // NOI18N
        radio2.setText("Veslač");
        radio2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radio2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(radio1)
                .addGap(18, 18, 18)
                .addComponent(radio2)
                .addGap(18, 18, 18)
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
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap(9, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(glavnaFormaPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(glavnaFormaPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(glavnaFormaPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(radio1)
                            .addComponent(radio2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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

    private void pretraziButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pretraziButtonActionPerformed
        // TODO add your handling code here:
        pretraziInput.setText("Pretraži naziv kluba...");
        cardPanel.removeAll();
        cardPanel.add(pretrazivanjePonudaPanel);
        cardPanel.repaint();
        cardPanel.revalidate();

        radio1.setEnabled(true);
        radio1.setVisible(true);
        radio2.setVisible(true);
        radio2.setEnabled(true);

        if (!(ponudeVeslacaTable.getModel() instanceof PonudaTableModelAgencija)) {
            ponudeVeslacaTable.setModel(new PonudaTableModelAgencija(ponudeAgencije));
        }
        if (patvm == null) {
            patvm = (PonudaTableModelAgencija) ponudeVeslacaTable.getModel();
        }

        stavkePonudeTable.setModel(new StavkaPonudeTableModel());
        sptm = (StavkaPonudeTableModel) stavkePonudeTable.getModel();
    }//GEN-LAST:event_pretraziButtonActionPerformed

    private void takmicenjaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_takmicenjaButtonActionPerformed
        pretraziInput.setText("Pretraži naziv kluba...");
        cardPanel.removeAll();
        cardPanel.add(pretrazivanjeTakmicenjaPanel);
        cardPanel.repaint();
        cardPanel.revalidate();

        radio1.setEnabled(false);
        radio1.setVisible(false);
        radio2.setEnabled(false);
        radio2.setVisible(false);

        try {
            klubovi = Kontroler.getInstance().vratiListuSviVeslackiKlub(new LinkedList<>());
        } catch (Exception ex) {
            logger.error("Neuspeno ucitavanje klubova");
        }
        if (!(veslackiKluboviTable.getModel() instanceof KlubTableModel)) {
            veslackiKluboviTable.setModel(new KlubTableModel(klubovi));
        }
        if (ktm == null) {
            ktm = (KlubTableModel) veslackiKluboviTable.getModel();
        }

        ostm = (OsvojenaTakmicenjaTableModel) osvojenaTakmicenjaTable.getModel();
        osvojenaTakmicenjaTable.setModel(new OsvojenaTakmicenjaTableModel());

    }//GEN-LAST:event_takmicenjaButtonActionPerformed

    private void azurirajNalogButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_azurirajNalogButtonActionPerformed
        // TODO add your handling code here:
        IzmeniPodatkeAgencijaForma iaf = new IzmeniPodatkeAgencijaForma(this, true);

        iaf.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {

                if (Kontroler.getInstance().isOdjavaSignal()) {
                    JOptionPane.showMessageDialog(null, "Nalog obrisan, gašenje programa...", "Info", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                    System.exit(0);
                }

            }

        });


    }//GEN-LAST:event_azurirajNalogButtonActionPerformed

    private void pretraziInputFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pretraziInputFocusGained
        // TODO add your handling code here:
        if (pretraziInput.getText().equals("Pretraži naziv kluba...") || pretraziInput.getText().equals("Pretraži naziv veslača...")) {
            pretraziInput.setText("");
        }
    }//GEN-LAST:event_pretraziInputFocusGained

    private void pretraziInputFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pretraziInputFocusLost
        // TODO add your handling code here:
        if (pretraziInput.getText().isEmpty()) {
            if (cardPanel.getComponentZOrder(pretrazivanjePonudaPanel) >= 0) {
                pretraziInput.setText(radio1.isSelected() ? "Pretraži naziv kluba..." : "Pretraži naziv veslača...");
            } else {
                pretraziInput.setText("Pretraži naziv kluba...");
            }
        }
    }//GEN-LAST:event_pretraziInputFocusLost

    private void prikaziTakmicenjaKlubaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prikaziTakmicenjaKlubaButtonActionPerformed
        try {
            if (veslackiKluboviTable.getSelectedRow() != -1) {
                int idKluba = (int) veslackiKluboviTable.getValueAt(veslackiKluboviTable.getSelectedRow(), 0);
                List<KlubTakmicenje> takmicenjaKluba = Kontroler.getInstance().vratiTakmicenjaKluba(idKluba);
                ostm.setOsvojenaTakmicenja(takmicenjaKluba);
                ostm.fireTableDataChanged();

            } else {
                JOptionPane.showMessageDialog(this, "Klub nije selektovan, selektujte klub kako bi prikazali takmicenja", "Greska", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
    }//GEN-LAST:event_prikaziTakmicenjaKlubaButtonActionPerformed

    private void prikaziStavkeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prikaziStavkeButtonActionPerformed

        try {
            if (ponudeVeslacaTable.getSelectedRow() != -1) {
                int idPonude = (int) ponudeVeslacaTable.getValueAt(ponudeVeslacaTable.getSelectedRow(), 0);
                PonudaVeslaca ponudaVeslaca = Kontroler.getInstance().vratiListuPonude(" P.id = " + idPonude, new LinkedList<>()).getFirst();
                if (ponudaVeslaca != null) {
                    JOptionPane.showMessageDialog(this, "Sistem je našao ponudu veslača", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    List<StavkaPonude> stavkePonude = ponudaVeslaca.getStavke();
                    sptm.setStavkePonude(stavkePonude);
                    sptm.fireTableDataChanged();
                } else {
                    JOptionPane.showMessageDialog(this, "Sistem ne može da nadje ponudu veslača", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(this, "Ponuda nije selektovana, selektujte ponudu kako bi prikazali stavke ponude", "Greska", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }

    }//GEN-LAST:event_prikaziStavkeButtonActionPerformed

    private void radio1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radio1ActionPerformed
        if (radio1.isSelected()) {
            if (cardPanel.getComponentZOrder(pretrazivanjePonudaPanel) >= 0) {
                pretraziInput.setText("Pretraži naziv kluba...");
            }

            radio2.setSelected(false);
        }
    }//GEN-LAST:event_radio1ActionPerformed

    private void radio2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radio2ActionPerformed
        if (radio2.isSelected()) {
            if (cardPanel.getComponentZOrder(pretrazivanjePonudaPanel) >= 0) {
                pretraziInput.setText("Pretraži naziv veslača...");
            }
            radio1.setSelected(false);
        }
    }//GEN-LAST:event_radio2ActionPerformed


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
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel nalogLabel;
    private forme.utils.GlavnaFormaTable osvojenaTakmicenjaTable;
    private forme.utils.GlavnaFormaTable ponudeVeslacaTable;
    private javax.swing.JButton pretraziButton;
    private javax.swing.JTextField pretraziInput;
    private javax.swing.JPanel pretrazivanjePonudaPanel;
    private javax.swing.JPanel pretrazivanjeTakmicenjaPanel;
    private javax.swing.JButton prikaziStavkeButton;
    private javax.swing.JButton prikaziTakmicenjaKlubaButton;
    private javax.swing.JRadioButton radio1;
    private javax.swing.JRadioButton radio2;
    private forme.utils.GlavnaFormaTable stavkePonudeTable;
    private javax.swing.JButton takmicenjaButton;
    private forme.utils.GlavnaFormaTable veslackiKluboviTable;
    // End of variables declaration//GEN-END:variables
}
