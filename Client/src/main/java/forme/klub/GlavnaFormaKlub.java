package forme.klub;

import com.formdev.flatlaf.FlatLightLaf;
import forme.tableModeli.AgencijaTableModel;
import forme.tableModeli.PonudaTableModelKlub;
import forme.tableModeli.VeslacTableModel;
import java.awt.Color;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import model.KategorijaVeslaca;
import model.Takmicenje;
import model.Veslac;
import model.VrstaTrke;
import forme.utils.TakmicenjaTableModel;
import model.KlubTakmicenje;
import forme.tableModeli.OsvojenaTakmicenjaTableModel;
import forme.tableModeli.StavkaPonudeTableModel;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.Period;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Agencija;
import model.PonudaVeslaca;
import model.StavkaPonude;
import forme.veslac.IzmeniVeslacForma;
import klijent.Klijent;

/**
 *
 * @author luka
 */
public class GlavnaFormaKlub extends javax.swing.JFrame {
    
    private int idKluba = Klijent.getInstance().getUlogovaniNalog().getId();
    private int idPonude = Klijent.getInstance().vratiPoslednjiIdPonude() + 1;
    private int rb = 0;
    private List<StavkaPonude> stavkePonude = new LinkedList<>();
    private List<Veslac> veslaciKlubaPonuda = Klijent.getInstance().vratiSveVeslace();
    private List<Veslac> obrisaniVeslaci = new LinkedList<>();
    private Queue<Integer> izbaceniRb = new LinkedList<>();
    
    private PonudaTableModelKlub ptm;
    private StavkaPonudeTableModel sptm;
    private VeslacTableModel vtm;
    private VeslacTableModel vptm;
    private TakmicenjaTableModel ttm;
    private OsvojenaTakmicenjaTableModel ostm;
    private AgencijaTableModel atm;

    public GlavnaFormaKlub() {
        initComponents();
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        
        setSize(screenSize);
        
        // Inicijalno prikupljanje objekata
        
        List<Veslac> veslaci = Klijent.getInstance().vratiSveVeslace();
        LinkedList<Takmicenje> takmicenja = Klijent.getInstance().vratiSvaTakmicenja();
        List<KlubTakmicenje> osvojenaTakmicenja = Klijent.getInstance().vratiTakmicenjaKluba(idKluba);
        List<PonudaVeslaca> ponudeVeslaca = Klijent.getInstance().vratiSvePonudeKluba(idKluba);
        List<Agencija> agencije = Klijent.getInstance().vratiSveAgencije();
        
        // Dodeljivanje TableModela tabelama
        
        ponudeTable.setModel(new PonudaTableModelKlub(ponudeVeslaca));
        veslaciTable.setModel(new VeslacTableModel(veslaci));
        takmicenjaTable.setModel(new TakmicenjaTableModel(takmicenja));
        osvojenaTakmicenjaTable.setModel(new OsvojenaTakmicenjaTableModel(osvojenaTakmicenja));
        veslaciPonudaTable.setModel(new VeslacTableModel((LinkedList<Veslac>) veslaciKlubaPonuda));
        stavkaPonudeTable.setModel(new StavkaPonudeTableModel(stavkePonude));
        agencijeTable.setModel(new AgencijaTableModel(agencije));
        
        // Izvdajanje table modela
        
        ptm = (PonudaTableModelKlub) ponudeTable.getModel();
        vtm = (VeslacTableModel) veslaciTable.getModel();
        ttm = (TakmicenjaTableModel) takmicenjaTable.getModel();
        ostm = (OsvojenaTakmicenjaTableModel) osvojenaTakmicenjaTable.getModel();
        vptm = (VeslacTableModel) veslaciTable.getModel();
        sptm = (StavkaPonudeTableModel) stavkaPonudeTable.getModel();
        atm = (AgencijaTableModel) agencijeTable.getModel();
        
        mestoComboBox.addItem(1);
        mestoComboBox.addItem(2);
        mestoComboBox.addItem(3);

        nalogLabel.setText(Klijent.getInstance().getUlogovaniNalog().getNaziv());

        kategorijaTakmicaraComboBox.addItem(KategorijaVeslaca.KADET);
        kategorijaTakmicaraComboBox.addItem(KategorijaVeslaca.JUNIOR);

        vrstaTrkeComboBox.addItem(VrstaTrke.SKIF);
        vrstaTrkeComboBox.addItem(VrstaTrke.DUBL);
        vrstaTrkeComboBox.addItem(VrstaTrke.DVOJAC);
        vrstaTrkeComboBox.addItem(VrstaTrke.CETVERAC_SKUL);
        vrstaTrkeComboBox.addItem(VrstaTrke.CETVERAC_RIMEN);
        vrstaTrkeComboBox.addItem(VrstaTrke.OSMERAC);

        pretraziInput.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (pretraziInput.getText().equals("Pretraži id agencije...") || pretraziInput.getText().isEmpty() || 
                            pretraziInput.getText().equals("Pretraži ime veslača...") || pretraziInput.getText().equals("Pretraži naziv takmičenja...")) {

                    } else {

                        String upitZaPretragu = pretraziInput.getText();
                        if (cardPanel.getComponentZOrder(kontrolnaTablaPanel) >= 0) {
                            int idAgencije = Integer.parseInt(upitZaPretragu);
                            List<PonudaVeslaca> ponude;
                            try {
                                ponude = Klijent.getInstance().pretraziPonudu(new PonudaVeslaca(0, null, 0, 0, 0, 0,null,idKluba, idAgencije));
                                ptm.setPonude(ponude);
                                ptm.fireTableDataChanged();
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                            

                        } else if (cardPanel.getComponentZOrder(veslacPanel) >= 0) {
                            String imePrezime = upitZaPretragu;
                            LinkedList<Veslac> veslaci = Klijent.getInstance().pretraziVeslaca(
                            new Veslac(0,imePrezime,null,0,0,null,0,null, idKluba));
                            vtm.setVeslaci(veslaci);
                            vtm.fireTableDataChanged();
                            
                            
                        } else {
                            String nazivTakmicenja = upitZaPretragu;
                            List<Takmicenje> takmicenja = Klijent.getInstance().pretraziTakmicenja(new Takmicenje(0,nazivTakmicenja,null,null,null));
                            ttm.setTakmicenja(takmicenja);
                            ttm.fireTableDataChanged();
                        }
                    }
                }
            }

        });

        prebrojTakmicenja();
        
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        takmicenjaTableModel1 = new forme.utils.TakmicenjaTableModel();
        takmicenjaTableModel2 = new forme.utils.TakmicenjaTableModel();
        jPanel1 = new javax.swing.JPanel();
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
        evidentirajButton = new javax.swing.JButton();
        takmicenjaButton = new javax.swing.JButton();
        ponudaButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        nalogLabel = new javax.swing.JLabel();
        azurirajNalogButton = new javax.swing.JButton();
        cardPanel = new javax.swing.JPanel();
        kontrolnaTablaPanel = new javax.swing.JPanel();
        kFormaPanel1 = new forme.utils.GlavnaFormaPanel();
        glavnaFormaPanel11 = new forme.utils.GlavnaFormaPanel();
        zlatoLabel = new javax.swing.JLabel();
        glavnaFormaPanel12 = new forme.utils.GlavnaFormaPanel();
        srebroLabel = new javax.swing.JLabel();
        glavnaFormaPanel22 = new forme.utils.GlavnaFormaPanel();
        bronzaLabel = new javax.swing.JLabel();
        kFormaPanel3 = new forme.utils.GlavnaFormaPanel();
        ponudeLabel = new javax.swing.JLabel();
        obrisiPonuduButton = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        ponudeTable = new forme.utils.GlavnaFormaTable();
        veslacPanel = new javax.swing.JPanel();
        glavnaFormaPanel8 = new forme.utils.GlavnaFormaPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        glavnaFormaPanel2 = new forme.utils.GlavnaFormaPanel();
        datumRodjenjaPicker = new com.github.lgooddatepicker.components.DatePicker();
        jLabel8 = new javax.swing.JLabel();
        glavnaFormaPanel3 = new forme.utils.GlavnaFormaPanel();
        datumUpisaPicker = new com.github.lgooddatepicker.components.DatePicker();
        jLabel9 = new javax.swing.JLabel();
        glavnaFormaPanel7 = new forme.utils.GlavnaFormaPanel();
        imePrezimeInput = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        glavnaFormaPanel13 = new forme.utils.GlavnaFormaPanel();
        tezinaInput = new javax.swing.JTextField();
        glavnaFormaPanel14 = new forme.utils.GlavnaFormaPanel();
        visinaInput = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        evidentirajVeslacaButton = new javax.swing.JButton();
        glavnaFormaPanel15 = new forme.utils.GlavnaFormaPanel();
        kategorijaComboBox = new javax.swing.JComboBox<>();
        glavnaFormaPanel16 = new forme.utils.GlavnaFormaPanel();
        najboljeVremeInput = new javax.swing.JTextField();
        glavnaFormaPanel1 = new forme.utils.GlavnaFormaPanel();
        jLabel4 = new javax.swing.JLabel();
        obrisiVeslacaButton = new javax.swing.JButton();
        promeniVeslacaButton = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        veslaciTable = new forme.utils.GlavnaFormaTable();
        takmicenjaPanel = new javax.swing.JPanel();
        glavnaFormaPanel9 = new forme.utils.GlavnaFormaPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        glavnaFormaPanel17 = new forme.utils.GlavnaFormaPanel();
        nazivTakmicenjaInput = new javax.swing.JTextField();
        glavnaFormaPanel18 = new forme.utils.GlavnaFormaPanel();
        kategorijaTakmicaraComboBox = new javax.swing.JComboBox<>();
        glavnaFormaPanel19 = new forme.utils.GlavnaFormaPanel();
        vrstaTrkeComboBox = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        glavnaFormaPanel20 = new forme.utils.GlavnaFormaPanel();
        datumTakmicenjaPicker = new com.github.lgooddatepicker.components.DatePicker();
        dodajNovoTakmicenjeButton = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        glavnaFormaPanel10 = new forme.utils.GlavnaFormaPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        osvojenaTakmicenjaTable = new forme.utils.GlavnaFormaTable();
        obrisiOsvojenoTakmicenjeButton = new javax.swing.JButton();
        glavnaFormaPanel21 = new forme.utils.GlavnaFormaPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        takmicenjaTable = new forme.utils.GlavnaFormaTable();
        obrisiNovoTakmicenjeButton = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        glavnaFormaPanel23 = new forme.utils.GlavnaFormaPanel();
        mestoComboBox = new javax.swing.JComboBox<>();
        dodajOsvojenoTakmicenje = new javax.swing.JButton();
        kreirajPonuduPanel = new javax.swing.JPanel();
        glavnaFormaPanel24 = new forme.utils.GlavnaFormaPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        veslaciPonudaTable = new forme.utils.GlavnaFormaTable();
        obrisiStavkuButton = new javax.swing.JButton();
        dodajStavkuButton = new javax.swing.JButton();
        glavnaFormaPanel25 = new forme.utils.GlavnaFormaPanel();
        jLabel21 = new javax.swing.JLabel();
        glavnaFormaPanel26 = new forme.utils.GlavnaFormaPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        stavkaPonudeTable = new forme.utils.GlavnaFormaTable();
        glavnaFormaPanel27 = new forme.utils.GlavnaFormaPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        agencijeTable = new forme.utils.GlavnaFormaTable();
        potvrdiPonuduButton = new javax.swing.JButton();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setPreferredSize(new java.awt.Dimension(1920, 1050));

        jPanel4.setBackground(new java.awt.Color(246, 245, 245));

        glavnaFormaPanel6.setBackground(new java.awt.Color(221, 221, 221));
        glavnaFormaPanel6.setMaximumSize(new java.awt.Dimension(285, 44));
        glavnaFormaPanel6.setMinimumSize(new java.awt.Dimension(285, 44));

        pretraziInput.setFont(new java.awt.Font("JetBrains Mono", 2, 18)); // NOI18N
        pretraziInput.setText("Pretraži id agencije...");
        pretraziInput.setBackground(new java.awt.Color(221, 221, 221));
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
                .addComponent(pretraziInput, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
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

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/slike/zvono32.png"))); // NOI18N
        jButton5.setBackground(new java.awt.Color(221, 221, 221));
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

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/slike/upitnik32.png"))); // NOI18N
        jButton6.setBackground(new java.awt.Color(221, 221, 221));
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

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(1285, Short.MAX_VALUE)
                .addComponent(glavnaFormaPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(glavnaFormaPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(glavnaFormaPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(103, 103, 103))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(glavnaFormaPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(glavnaFormaPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(glavnaFormaPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(238, 236, 236));

        kontrolnaTablaButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/slike/kontrolnaTabla32.png"))); // NOI18N
        kontrolnaTablaButton.setText(" Kontrolna Tabla");
        kontrolnaTablaButton.setBackground(new java.awt.Color(238, 236, 236));
        kontrolnaTablaButton.setBorder(null);
        kontrolnaTablaButton.setBorderPainted(false);
        kontrolnaTablaButton.setFocusPainted(false);
        kontrolnaTablaButton.setFont(new java.awt.Font("JetBrains Mono", 0, 24)); // NOI18N
        kontrolnaTablaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kontrolnaTablaButtonActionPerformed(evt);
            }
        });

        evidentirajButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/slike/ergometar32.png"))); // NOI18N
        evidentirajButton.setText("Evidentiraj Veslača");
        evidentirajButton.setBackground(new java.awt.Color(238, 236, 236));
        evidentirajButton.setBorder(null);
        evidentirajButton.setBorderPainted(false);
        evidentirajButton.setFocusPainted(false);
        evidentirajButton.setFont(new java.awt.Font("JetBrains Mono", 0, 24)); // NOI18N
        evidentirajButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                evidentirajButtonActionPerformed(evt);
            }
        });

        takmicenjaButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/slike/takmicenje32.png"))); // NOI18N
        takmicenjaButton.setText("Takmičenja");
        takmicenjaButton.setBackground(new java.awt.Color(238, 236, 236));
        takmicenjaButton.setBorder(null);
        takmicenjaButton.setBorderPainted(false);
        takmicenjaButton.setFocusPainted(false);
        takmicenjaButton.setFont(new java.awt.Font("JetBrains Mono", 0, 24)); // NOI18N
        takmicenjaButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        takmicenjaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                takmicenjaButtonActionPerformed(evt);
            }
        });

        ponudaButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/slike/plus32.png"))); // NOI18N
        ponudaButton.setText(" Kreiraj Ponudu");
        ponudaButton.setBackground(new java.awt.Color(13, 146, 244));
        ponudaButton.setFocusPainted(false);
        ponudaButton.setFont(new java.awt.Font("JetBrains Mono", 1, 24)); // NOI18N
        ponudaButton.setForeground(new java.awt.Color(255, 255, 255));
        ponudaButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ponudaButton.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ponudaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ponudaButtonActionPerformed(evt);
            }
        });

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/slike/rowing-boat(1).png"))); // NOI18N
        jLabel2.setText("Preporuči veslača");
        jLabel2.setFont(new java.awt.Font("JetBrains Mono", 3, 24)); // NOI18N

        nalogLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        nalogLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/slike/user.png"))); // NOI18N
        nalogLabel.setText("   jLabel20");
        nalogLabel.setFont(new java.awt.Font("JetBrains Mono", 0, 24)); // NOI18N

        azurirajNalogButton.setText("Ažuriraj nalog");
        azurirajNalogButton.setBackground(new java.awt.Color(238, 236, 236));
        azurirajNalogButton.setBorder(null);
        azurirajNalogButton.setFocusPainted(false);
        azurirajNalogButton.setFont(new java.awt.Font("JetBrains Mono", 1, 19)); // NOI18N
        azurirajNalogButton.setForeground(new java.awt.Color(255, 51, 51));
        azurirajNalogButton.setToolTipText("");
        azurirajNalogButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                azurirajNalogButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(evidentirajButton)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(kontrolnaTablaButton, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(takmicenjaButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ponudaButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)
                        .addComponent(nalogLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(azurirajNalogButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(114, 114, 114)
                .addComponent(kontrolnaTablaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(evidentirajButton, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(takmicenjaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72)
                .addComponent(ponudaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 157, Short.MAX_VALUE)
                .addComponent(nalogLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(azurirajNalogButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(169, 169, 169))
        );

        cardPanel.setBackground(new java.awt.Color(221, 221, 221));
        cardPanel.setLayout(new java.awt.CardLayout());

        kontrolnaTablaPanel.setBackground(new java.awt.Color(221, 221, 221));

        glavnaFormaPanel11.setBackground(new java.awt.Color(255, 255, 255));

        zlatoLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/slike/ranking.png"))); // NOI18N
        zlatoLabel.setText("12 osvojenih zlata");
        zlatoLabel.setFont(new java.awt.Font("JetBrains Mono", 3, 24)); // NOI18N

        javax.swing.GroupLayout glavnaFormaPanel11Layout = new javax.swing.GroupLayout(glavnaFormaPanel11);
        glavnaFormaPanel11.setLayout(glavnaFormaPanel11Layout);
        glavnaFormaPanel11Layout.setHorizontalGroup(
            glavnaFormaPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(glavnaFormaPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(zlatoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
                .addContainerGap())
        );
        glavnaFormaPanel11Layout.setVerticalGroup(
            glavnaFormaPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(glavnaFormaPanel11Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(zlatoLabel)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        glavnaFormaPanel12.setBackground(new java.awt.Color(255, 255, 255));

        srebroLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/slike/podium.png"))); // NOI18N
        srebroLabel.setText(" 6 osvojenih srebra");
        srebroLabel.setFont(new java.awt.Font("JetBrains Mono", 3, 24)); // NOI18N

        javax.swing.GroupLayout glavnaFormaPanel12Layout = new javax.swing.GroupLayout(glavnaFormaPanel12);
        glavnaFormaPanel12.setLayout(glavnaFormaPanel12Layout);
        glavnaFormaPanel12Layout.setHorizontalGroup(
            glavnaFormaPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(glavnaFormaPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(srebroLabel)
                .addContainerGap(19, Short.MAX_VALUE))
        );
        glavnaFormaPanel12Layout.setVerticalGroup(
            glavnaFormaPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(glavnaFormaPanel12Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(srebroLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        glavnaFormaPanel22.setBackground(new java.awt.Color(255, 255, 255));

        bronzaLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/slike/competition.png"))); // NOI18N
        bronzaLabel.setText("5 osvojenih bronzi");
        bronzaLabel.setFont(new java.awt.Font("JetBrains Mono", 3, 24)); // NOI18N
        bronzaLabel.setToolTipText("");

        javax.swing.GroupLayout glavnaFormaPanel22Layout = new javax.swing.GroupLayout(glavnaFormaPanel22);
        glavnaFormaPanel22.setLayout(glavnaFormaPanel22Layout);
        glavnaFormaPanel22Layout.setHorizontalGroup(
            glavnaFormaPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(glavnaFormaPanel22Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(bronzaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );
        glavnaFormaPanel22Layout.setVerticalGroup(
            glavnaFormaPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, glavnaFormaPanel22Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bronzaLabel)
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout kFormaPanel1Layout = new javax.swing.GroupLayout(kFormaPanel1);
        kFormaPanel1.setLayout(kFormaPanel1Layout);
        kFormaPanel1Layout.setHorizontalGroup(
            kFormaPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kFormaPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(glavnaFormaPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(glavnaFormaPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(glavnaFormaPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );
        kFormaPanel1Layout.setVerticalGroup(
            kFormaPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kFormaPanel1Layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addGroup(kFormaPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(glavnaFormaPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(glavnaFormaPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(glavnaFormaPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(21, 21, 21))
        );

        ponudeLabel.setText("Ponude Veslača");
        ponudeLabel.setFont(new java.awt.Font("JetBrains Mono", 1, 28)); // NOI18N

        obrisiPonuduButton.setText("Obriši");
        obrisiPonuduButton.setBackground(new java.awt.Color(13, 146, 244));
        obrisiPonuduButton.setFont(new java.awt.Font("JetBrains Mono", 1, 19)); // NOI18N
        obrisiPonuduButton.setForeground(new java.awt.Color(255, 255, 255));
        obrisiPonuduButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                obrisiPonuduButtonActionPerformed(evt);
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
            .addGroup(kFormaPanel3Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(ponudeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1377, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kFormaPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(obrisiPonuduButton, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );
        kFormaPanel3Layout.setVerticalGroup(
            kFormaPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kFormaPanel3Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(ponudeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(obrisiPonuduButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout kontrolnaTablaPanelLayout = new javax.swing.GroupLayout(kontrolnaTablaPanel);
        kontrolnaTablaPanel.setLayout(kontrolnaTablaPanelLayout);
        kontrolnaTablaPanelLayout.setHorizontalGroup(
            kontrolnaTablaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kontrolnaTablaPanelLayout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(kontrolnaTablaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(kFormaPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(kFormaPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(569, Short.MAX_VALUE))
        );
        kontrolnaTablaPanelLayout.setVerticalGroup(
            kontrolnaTablaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kontrolnaTablaPanelLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(kFormaPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(kFormaPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(701, Short.MAX_VALUE))
        );

        cardPanel.add(kontrolnaTablaPanel, "card2");

        veslacPanel.setBackground(new java.awt.Color(221, 221, 221));

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Evidentiraj veslača");
        jLabel6.setFont(new java.awt.Font("JetBrains Mono", 1, 28)); // NOI18N

        jLabel7.setText("Datum Rodjenja:");
        jLabel7.setFont(new java.awt.Font("JetBrains Mono", 0, 18)); // NOI18N

        glavnaFormaPanel2.setBackground(new java.awt.Color(221, 221, 221));

        datumRodjenjaPicker.setBackground(new java.awt.Color(221, 221, 221));

        javax.swing.GroupLayout glavnaFormaPanel2Layout = new javax.swing.GroupLayout(glavnaFormaPanel2);
        glavnaFormaPanel2.setLayout(glavnaFormaPanel2Layout);
        glavnaFormaPanel2Layout.setHorizontalGroup(
            glavnaFormaPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(glavnaFormaPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(datumRodjenjaPicker, javax.swing.GroupLayout.PREFERRED_SIZE, 236, Short.MAX_VALUE)
                .addContainerGap())
        );
        glavnaFormaPanel2Layout.setVerticalGroup(
            glavnaFormaPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, glavnaFormaPanel2Layout.createSequentialGroup()
                .addContainerGap(7, Short.MAX_VALUE)
                .addComponent(datumRodjenjaPicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel8.setText("Ime I Prezime:");
        jLabel8.setFont(new java.awt.Font("JetBrains Mono", 0, 18)); // NOI18N

        glavnaFormaPanel3.setBackground(new java.awt.Color(221, 221, 221));

        javax.swing.GroupLayout glavnaFormaPanel3Layout = new javax.swing.GroupLayout(glavnaFormaPanel3);
        glavnaFormaPanel3.setLayout(glavnaFormaPanel3Layout);
        glavnaFormaPanel3Layout.setHorizontalGroup(
            glavnaFormaPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(glavnaFormaPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(datumUpisaPicker, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        glavnaFormaPanel3Layout.setVerticalGroup(
            glavnaFormaPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, glavnaFormaPanel3Layout.createSequentialGroup()
                .addContainerGap(9, Short.MAX_VALUE)
                .addComponent(datumUpisaPicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel9.setText("Datum Upisa:");
        jLabel9.setFont(new java.awt.Font("JetBrains Mono", 0, 18)); // NOI18N

        glavnaFormaPanel7.setBackground(new java.awt.Color(221, 221, 221));

        imePrezimeInput.setFont(new java.awt.Font("JetBrains Mono", 2, 14)); // NOI18N
        imePrezimeInput.setText("Unesite ime i prezime...");
        imePrezimeInput.setBackground(new java.awt.Color(221, 221, 221));
        imePrezimeInput.setBorder(null);
        imePrezimeInput.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                imePrezimeInputFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                imePrezimeInputFocusLost(evt);
            }
        });

        javax.swing.GroupLayout glavnaFormaPanel7Layout = new javax.swing.GroupLayout(glavnaFormaPanel7);
        glavnaFormaPanel7.setLayout(glavnaFormaPanel7Layout);
        glavnaFormaPanel7Layout.setHorizontalGroup(
            glavnaFormaPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(glavnaFormaPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imePrezimeInput, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        glavnaFormaPanel7Layout.setVerticalGroup(
            glavnaFormaPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(glavnaFormaPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imePrezimeInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel10.setText("Kategorija:");
        jLabel10.setFont(new java.awt.Font("JetBrains Mono", 0, 18)); // NOI18N

        jLabel11.setText("Visina:");
        jLabel11.setFont(new java.awt.Font("JetBrains Mono", 0, 18)); // NOI18N

        jLabel12.setText("Težina:");
        jLabel12.setFont(new java.awt.Font("JetBrains Mono", 0, 18)); // NOI18N

        glavnaFormaPanel13.setBackground(new java.awt.Color(221, 221, 221));

        tezinaInput.setFont(new java.awt.Font("JetBrains Mono", 2, 14)); // NOI18N
        tezinaInput.setText("Unesite težinu...");
        tezinaInput.setBackground(new java.awt.Color(221, 221, 221));
        tezinaInput.setBorder(null);
        tezinaInput.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tezinaInputFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tezinaInputFocusLost(evt);
            }
        });

        javax.swing.GroupLayout glavnaFormaPanel13Layout = new javax.swing.GroupLayout(glavnaFormaPanel13);
        glavnaFormaPanel13.setLayout(glavnaFormaPanel13Layout);
        glavnaFormaPanel13Layout.setHorizontalGroup(
            glavnaFormaPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(glavnaFormaPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tezinaInput, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                .addContainerGap())
        );
        glavnaFormaPanel13Layout.setVerticalGroup(
            glavnaFormaPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(glavnaFormaPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tezinaInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        glavnaFormaPanel14.setBackground(new java.awt.Color(221, 221, 221));

        visinaInput.setFont(new java.awt.Font("JetBrains Mono", 2, 14)); // NOI18N
        visinaInput.setText("Unesite visinu...");
        visinaInput.setBackground(new java.awt.Color(221, 221, 221));
        visinaInput.setBorder(null);
        visinaInput.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                visinaInputFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                visinaInputFocusLost(evt);
            }
        });

        javax.swing.GroupLayout glavnaFormaPanel14Layout = new javax.swing.GroupLayout(glavnaFormaPanel14);
        glavnaFormaPanel14.setLayout(glavnaFormaPanel14Layout);
        glavnaFormaPanel14Layout.setHorizontalGroup(
            glavnaFormaPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(glavnaFormaPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(visinaInput, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                .addContainerGap())
        );
        glavnaFormaPanel14Layout.setVerticalGroup(
            glavnaFormaPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(glavnaFormaPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(visinaInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel14.setText("Najbolje vreme:");
        jLabel14.setFont(new java.awt.Font("JetBrains Mono", 0, 18)); // NOI18N

        evidentirajVeslacaButton.setText("Evidentiraj");
        evidentirajVeslacaButton.setBackground(new java.awt.Color(13, 146, 244));
        evidentirajVeslacaButton.setFont(new java.awt.Font("JetBrains Mono", 1, 18)); // NOI18N
        evidentirajVeslacaButton.setForeground(new java.awt.Color(255, 255, 255));
        evidentirajVeslacaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                evidentirajVeslacaButtonActionPerformed(evt);
            }
        });

        glavnaFormaPanel15.setBackground(new java.awt.Color(221, 221, 221));

        kategorijaComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "KADET", "JUNIOR" }));
        kategorijaComboBox.setBackground(new java.awt.Color(221, 221, 221));
        kategorijaComboBox.setBorder(null);
        kategorijaComboBox.setFont(new java.awt.Font("JetBrains Mono", 2, 14)); // NOI18N

        javax.swing.GroupLayout glavnaFormaPanel15Layout = new javax.swing.GroupLayout(glavnaFormaPanel15);
        glavnaFormaPanel15.setLayout(glavnaFormaPanel15Layout);
        glavnaFormaPanel15Layout.setHorizontalGroup(
            glavnaFormaPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(glavnaFormaPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(kategorijaComboBox, 0, 218, Short.MAX_VALUE)
                .addContainerGap())
        );
        glavnaFormaPanel15Layout.setVerticalGroup(
            glavnaFormaPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(glavnaFormaPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(kategorijaComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        glavnaFormaPanel16.setBackground(new java.awt.Color(221, 221, 221));

        najboljeVremeInput.setFont(new java.awt.Font("JetBrains Mono", 2, 14)); // NOI18N
        najboljeVremeInput.setText("Unesite najbolje vreme...");
        najboljeVremeInput.setBackground(new java.awt.Color(221, 221, 221));
        najboljeVremeInput.setBorder(null);
        najboljeVremeInput.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                najboljeVremeInputFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                najboljeVremeInputFocusLost(evt);
            }
        });

        javax.swing.GroupLayout glavnaFormaPanel16Layout = new javax.swing.GroupLayout(glavnaFormaPanel16);
        glavnaFormaPanel16.setLayout(glavnaFormaPanel16Layout);
        glavnaFormaPanel16Layout.setHorizontalGroup(
            glavnaFormaPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(glavnaFormaPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(najboljeVremeInput, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                .addContainerGap())
        );
        glavnaFormaPanel16Layout.setVerticalGroup(
            glavnaFormaPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, glavnaFormaPanel16Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(najboljeVremeInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout glavnaFormaPanel8Layout = new javax.swing.GroupLayout(glavnaFormaPanel8);
        glavnaFormaPanel8.setLayout(glavnaFormaPanel8Layout);
        glavnaFormaPanel8Layout.setHorizontalGroup(
            glavnaFormaPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(glavnaFormaPanel8Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(glavnaFormaPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(glavnaFormaPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(glavnaFormaPanel8Layout.createSequentialGroup()
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(glavnaFormaPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(jLabel6)
                        .addGroup(glavnaFormaPanel8Layout.createSequentialGroup()
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(glavnaFormaPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(glavnaFormaPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(glavnaFormaPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(46, 46, 46)
                .addGroup(glavnaFormaPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addGap(51, 51, 51)
                .addGroup(glavnaFormaPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(glavnaFormaPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(glavnaFormaPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(68, 68, 68)
                .addGroup(glavnaFormaPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(glavnaFormaPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(32, 32, 32)
                        .addComponent(glavnaFormaPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(glavnaFormaPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(evidentirajVeslacaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(glavnaFormaPanel8Layout.createSequentialGroup()
                            .addComponent(jLabel14)
                            .addGap(18, 18, 18)
                            .addComponent(glavnaFormaPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(55, Short.MAX_VALUE))
        );
        glavnaFormaPanel8Layout.setVerticalGroup(
            glavnaFormaPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(glavnaFormaPanel8Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(glavnaFormaPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(glavnaFormaPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addGroup(glavnaFormaPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(glavnaFormaPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(glavnaFormaPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(glavnaFormaPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47)
                .addGroup(glavnaFormaPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(glavnaFormaPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(glavnaFormaPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(glavnaFormaPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(glavnaFormaPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(glavnaFormaPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(60, 60, 60)
                .addGroup(glavnaFormaPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(glavnaFormaPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(evidentirajVeslacaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(41, Short.MAX_VALUE))
        );

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Veslači");
        jLabel4.setFont(new java.awt.Font("JetBrains Mono", 1, 28)); // NOI18N

        obrisiVeslacaButton.setText("Obriši");
        obrisiVeslacaButton.setBackground(new java.awt.Color(13, 146, 244));
        obrisiVeslacaButton.setFont(new java.awt.Font("JetBrains Mono", 1, 19)); // NOI18N
        obrisiVeslacaButton.setForeground(new java.awt.Color(255, 255, 255));
        obrisiVeslacaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                obrisiVeslacaButtonActionPerformed(evt);
            }
        });

        promeniVeslacaButton.setText("Promeni");
        promeniVeslacaButton.setBackground(new java.awt.Color(13, 146, 244));
        promeniVeslacaButton.setFont(new java.awt.Font("JetBrains Mono", 1, 19)); // NOI18N
        promeniVeslacaButton.setForeground(new java.awt.Color(255, 255, 255));
        promeniVeslacaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                promeniVeslacaButtonActionPerformed(evt);
            }
        });

        veslaciTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(veslaciTable);

        javax.swing.GroupLayout glavnaFormaPanel1Layout = new javax.swing.GroupLayout(glavnaFormaPanel1);
        glavnaFormaPanel1.setLayout(glavnaFormaPanel1Layout);
        glavnaFormaPanel1Layout.setHorizontalGroup(
            glavnaFormaPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, glavnaFormaPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(obrisiVeslacaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(promeniVeslacaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
            .addGroup(glavnaFormaPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane3)
        );
        glavnaFormaPanel1Layout.setVerticalGroup(
            glavnaFormaPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, glavnaFormaPanel1Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(glavnaFormaPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(obrisiVeslacaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(promeniVeslacaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60))
        );

        javax.swing.GroupLayout veslacPanelLayout = new javax.swing.GroupLayout(veslacPanel);
        veslacPanel.setLayout(veslacPanelLayout);
        veslacPanelLayout.setHorizontalGroup(
            veslacPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(veslacPanelLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(veslacPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(glavnaFormaPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(glavnaFormaPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(565, Short.MAX_VALUE))
        );
        veslacPanelLayout.setVerticalGroup(
            veslacPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(veslacPanelLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(glavnaFormaPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(glavnaFormaPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        cardPanel.add(veslacPanel, "card3");

        takmicenjaPanel.setBackground(new java.awt.Color(221, 221, 221));

        jLabel15.setText("Dodaj novo takmičenje");
        jLabel15.setFont(new java.awt.Font("JetBrains Mono", 1, 28)); // NOI18N

        jLabel16.setText("Kategorija:");
        jLabel16.setFont(new java.awt.Font("JetBrains Mono", 0, 18)); // NOI18N

        jLabel17.setText("Naziv:");
        jLabel17.setFont(new java.awt.Font("JetBrains Mono", 0, 18)); // NOI18N

        glavnaFormaPanel17.setBackground(new java.awt.Color(221, 221, 221));

        nazivTakmicenjaInput.setFont(new java.awt.Font("JetBrains Mono", 2, 18)); // NOI18N
        nazivTakmicenjaInput.setText("Unesite naziv...");
        nazivTakmicenjaInput.setBackground(new java.awt.Color(221, 221, 221));
        nazivTakmicenjaInput.setBorder(null);
        nazivTakmicenjaInput.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                nazivTakmicenjaInputFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                nazivTakmicenjaInputFocusLost(evt);
            }
        });

        javax.swing.GroupLayout glavnaFormaPanel17Layout = new javax.swing.GroupLayout(glavnaFormaPanel17);
        glavnaFormaPanel17.setLayout(glavnaFormaPanel17Layout);
        glavnaFormaPanel17Layout.setHorizontalGroup(
            glavnaFormaPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(glavnaFormaPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nazivTakmicenjaInput, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
                .addContainerGap())
        );
        glavnaFormaPanel17Layout.setVerticalGroup(
            glavnaFormaPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(glavnaFormaPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nazivTakmicenjaInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        glavnaFormaPanel18.setBackground(new java.awt.Color(221, 221, 221));

        kategorijaTakmicaraComboBox.setBackground(new java.awt.Color(221, 221, 221));
        kategorijaTakmicaraComboBox.setBorder(null);
        kategorijaTakmicaraComboBox.setFont(new java.awt.Font("JetBrains Mono", 0, 18)); // NOI18N

        javax.swing.GroupLayout glavnaFormaPanel18Layout = new javax.swing.GroupLayout(glavnaFormaPanel18);
        glavnaFormaPanel18.setLayout(glavnaFormaPanel18Layout);
        glavnaFormaPanel18Layout.setHorizontalGroup(
            glavnaFormaPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(glavnaFormaPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(kategorijaTakmicaraComboBox, 0, 277, Short.MAX_VALUE)
                .addContainerGap())
        );
        glavnaFormaPanel18Layout.setVerticalGroup(
            glavnaFormaPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, glavnaFormaPanel18Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(kategorijaTakmicaraComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        glavnaFormaPanel19.setBackground(new java.awt.Color(221, 221, 221));

        vrstaTrkeComboBox.setBackground(new java.awt.Color(221, 221, 221));
        vrstaTrkeComboBox.setBorder(null);
        vrstaTrkeComboBox.setFont(new java.awt.Font("JetBrains Mono", 0, 18)); // NOI18N

        javax.swing.GroupLayout glavnaFormaPanel19Layout = new javax.swing.GroupLayout(glavnaFormaPanel19);
        glavnaFormaPanel19.setLayout(glavnaFormaPanel19Layout);
        glavnaFormaPanel19Layout.setHorizontalGroup(
            glavnaFormaPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(glavnaFormaPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(vrstaTrkeComboBox, 0, 264, Short.MAX_VALUE)
                .addContainerGap())
        );
        glavnaFormaPanel19Layout.setVerticalGroup(
            glavnaFormaPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, glavnaFormaPanel19Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(vrstaTrkeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel19.setText("Vrsta:");
        jLabel19.setFont(new java.awt.Font("JetBrains Mono", 0, 18)); // NOI18N

        glavnaFormaPanel20.setBackground(new java.awt.Color(221, 221, 221));

        datumTakmicenjaPicker.setBackground(new java.awt.Color(221, 221, 221));

        javax.swing.GroupLayout glavnaFormaPanel20Layout = new javax.swing.GroupLayout(glavnaFormaPanel20);
        glavnaFormaPanel20.setLayout(glavnaFormaPanel20Layout);
        glavnaFormaPanel20Layout.setHorizontalGroup(
            glavnaFormaPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(glavnaFormaPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(datumTakmicenjaPicker, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
                .addContainerGap())
        );
        glavnaFormaPanel20Layout.setVerticalGroup(
            glavnaFormaPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(glavnaFormaPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(datumTakmicenjaPicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(7, Short.MAX_VALUE))
        );

        dodajNovoTakmicenjeButton.setText("Dodaj novo takmičenje");
        dodajNovoTakmicenjeButton.setBackground(new java.awt.Color(14, 146, 244));
        dodajNovoTakmicenjeButton.setFont(new java.awt.Font("JetBrains Mono", 1, 18)); // NOI18N
        dodajNovoTakmicenjeButton.setForeground(new java.awt.Color(255, 255, 255));
        dodajNovoTakmicenjeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dodajNovoTakmicenjeButtonActionPerformed(evt);
            }
        });

        jLabel20.setText("Datum Održavanja:");
        jLabel20.setFont(new java.awt.Font("JetBrains Mono", 0, 18)); // NOI18N

        javax.swing.GroupLayout glavnaFormaPanel9Layout = new javax.swing.GroupLayout(glavnaFormaPanel9);
        glavnaFormaPanel9.setLayout(glavnaFormaPanel9Layout);
        glavnaFormaPanel9Layout.setHorizontalGroup(
            glavnaFormaPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(glavnaFormaPanel9Layout.createSequentialGroup()
                .addGroup(glavnaFormaPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(glavnaFormaPanel9Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(glavnaFormaPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(glavnaFormaPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(glavnaFormaPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(glavnaFormaPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(glavnaFormaPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(glavnaFormaPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(glavnaFormaPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(glavnaFormaPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(glavnaFormaPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(glavnaFormaPanel9Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel15)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, glavnaFormaPanel9Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(dodajNovoTakmicenjeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );
        glavnaFormaPanel9Layout.setVerticalGroup(
            glavnaFormaPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(glavnaFormaPanel9Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(glavnaFormaPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(glavnaFormaPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addGroup(glavnaFormaPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(glavnaFormaPanel9Layout.createSequentialGroup()
                                .addComponent(glavnaFormaPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6))
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(glavnaFormaPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(glavnaFormaPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(glavnaFormaPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(glavnaFormaPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addComponent(glavnaFormaPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(dodajNovoTakmicenjeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jLabel5.setText("Osvojena takmičenja");
        jLabel5.setFont(new java.awt.Font("JetBrains Mono", 1, 28)); // NOI18N

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

        obrisiOsvojenoTakmicenjeButton.setText("Obriši osvojeno takmičenje");
        obrisiOsvojenoTakmicenjeButton.setBackground(new java.awt.Color(13, 146, 244));
        obrisiOsvojenoTakmicenjeButton.setFont(new java.awt.Font("JetBrains Mono", 1, 19)); // NOI18N
        obrisiOsvojenoTakmicenjeButton.setForeground(new java.awt.Color(255, 255, 255));
        obrisiOsvojenoTakmicenjeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                obrisiOsvojenoTakmicenjeButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout glavnaFormaPanel10Layout = new javax.swing.GroupLayout(glavnaFormaPanel10);
        glavnaFormaPanel10.setLayout(glavnaFormaPanel10Layout);
        glavnaFormaPanel10Layout.setHorizontalGroup(
            glavnaFormaPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 726, Short.MAX_VALUE)
            .addGroup(glavnaFormaPanel10Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, glavnaFormaPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(obrisiOsvojenoTakmicenjeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        glavnaFormaPanel10Layout.setVerticalGroup(
            glavnaFormaPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(glavnaFormaPanel10Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(obrisiOsvojenoTakmicenjeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        takmicenjaTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(takmicenjaTable);

        obrisiNovoTakmicenjeButton.setText("Obriši novo takmičenje");
        obrisiNovoTakmicenjeButton.setBackground(new java.awt.Color(13, 146, 244));
        obrisiNovoTakmicenjeButton.setFont(new java.awt.Font("JetBrains Mono", 1, 19)); // NOI18N
        obrisiNovoTakmicenjeButton.setForeground(new java.awt.Color(255, 255, 255));
        obrisiNovoTakmicenjeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                obrisiNovoTakmicenjeButtonActionPerformed(evt);
            }
        });

        jLabel18.setText("Osvojeno mesto:");
        jLabel18.setFont(new java.awt.Font("JetBrains Mono", 0, 18)); // NOI18N

        glavnaFormaPanel23.setBackground(new java.awt.Color(221, 221, 221));

        mestoComboBox.setBackground(new java.awt.Color(221, 221, 221));
        mestoComboBox.setBorder(null);

        javax.swing.GroupLayout glavnaFormaPanel23Layout = new javax.swing.GroupLayout(glavnaFormaPanel23);
        glavnaFormaPanel23.setLayout(glavnaFormaPanel23Layout);
        glavnaFormaPanel23Layout.setHorizontalGroup(
            glavnaFormaPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(glavnaFormaPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mestoComboBox, 0, 201, Short.MAX_VALUE)
                .addContainerGap())
        );
        glavnaFormaPanel23Layout.setVerticalGroup(
            glavnaFormaPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(glavnaFormaPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mestoComboBox)
                .addContainerGap())
        );

        dodajOsvojenoTakmicenje.setText("Dodaj osvojeno takmičenje");
        dodajOsvojenoTakmicenje.setBackground(new java.awt.Color(13, 146, 244));
        dodajOsvojenoTakmicenje.setFont(new java.awt.Font("JetBrains Mono", 1, 19)); // NOI18N
        dodajOsvojenoTakmicenje.setForeground(new java.awt.Color(255, 255, 255));
        dodajOsvojenoTakmicenje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dodajOsvojenoTakmicenjeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout glavnaFormaPanel21Layout = new javax.swing.GroupLayout(glavnaFormaPanel21);
        glavnaFormaPanel21.setLayout(glavnaFormaPanel21Layout);
        glavnaFormaPanel21Layout.setHorizontalGroup(
            glavnaFormaPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(glavnaFormaPanel21Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(glavnaFormaPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(glavnaFormaPanel21Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(obrisiNovoTakmicenjeButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(dodajOsvojenoTakmicenje)
                .addGap(18, 18, 18))
        );
        glavnaFormaPanel21Layout.setVerticalGroup(
            glavnaFormaPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(glavnaFormaPanel21Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(glavnaFormaPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(glavnaFormaPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(glavnaFormaPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(obrisiNovoTakmicenjeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dodajOsvojenoTakmicenje, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
        );

        javax.swing.GroupLayout takmicenjaPanelLayout = new javax.swing.GroupLayout(takmicenjaPanel);
        takmicenjaPanel.setLayout(takmicenjaPanelLayout);
        takmicenjaPanelLayout.setHorizontalGroup(
            takmicenjaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(takmicenjaPanelLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(takmicenjaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(glavnaFormaPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(glavnaFormaPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(27, 27, 27)
                .addComponent(glavnaFormaPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(570, Short.MAX_VALUE))
        );
        takmicenjaPanelLayout.setVerticalGroup(
            takmicenjaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(takmicenjaPanelLayout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(takmicenjaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(glavnaFormaPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(takmicenjaPanelLayout.createSequentialGroup()
                        .addComponent(glavnaFormaPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(glavnaFormaPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(704, Short.MAX_VALUE))
        );

        cardPanel.add(takmicenjaPanel, "card4");

        kreirajPonuduPanel.setBackground(new java.awt.Color(221, 221, 221));

        veslaciPonudaTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(veslaciPonudaTable);

        obrisiStavkuButton.setText("Obriši stavku");
        obrisiStavkuButton.setBackground(new java.awt.Color(13, 146, 244));
        obrisiStavkuButton.setFont(new java.awt.Font("JetBrains Mono", 1, 19)); // NOI18N
        obrisiStavkuButton.setForeground(new java.awt.Color(255, 255, 255));
        obrisiStavkuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                obrisiStavkuButtonActionPerformed(evt);
            }
        });

        dodajStavkuButton.setText("Dodaj stavku");
        dodajStavkuButton.setBackground(new java.awt.Color(14, 146, 244));
        dodajStavkuButton.setFont(new java.awt.Font("JetBrains Mono", 1, 18)); // NOI18N
        dodajStavkuButton.setForeground(new java.awt.Color(255, 255, 255));
        dodajStavkuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dodajStavkuButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout glavnaFormaPanel24Layout = new javax.swing.GroupLayout(glavnaFormaPanel24);
        glavnaFormaPanel24.setLayout(glavnaFormaPanel24Layout);
        glavnaFormaPanel24Layout.setHorizontalGroup(
            glavnaFormaPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, glavnaFormaPanel24Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(obrisiStavkuButton)
                .addGap(18, 18, 18)
                .addComponent(dodajStavkuButton)
                .addGap(21, 21, 21))
        );
        glavnaFormaPanel24Layout.setVerticalGroup(
            glavnaFormaPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(glavnaFormaPanel24Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(glavnaFormaPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(obrisiStavkuButton)
                    .addComponent(dodajStavkuButton))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/slike/document(2).png"))); // NOI18N
        jLabel21.setText("Dodavanje stavki u ponudu veslača");
        jLabel21.setFont(new java.awt.Font("JetBrains Mono", 3, 24)); // NOI18N

        javax.swing.GroupLayout glavnaFormaPanel25Layout = new javax.swing.GroupLayout(glavnaFormaPanel25);
        glavnaFormaPanel25.setLayout(glavnaFormaPanel25Layout);
        glavnaFormaPanel25Layout.setHorizontalGroup(
            glavnaFormaPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(glavnaFormaPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        glavnaFormaPanel25Layout.setVerticalGroup(
            glavnaFormaPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(glavnaFormaPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                .addContainerGap())
        );

        stavkaPonudeTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane6.setViewportView(stavkaPonudeTable);

        javax.swing.GroupLayout glavnaFormaPanel26Layout = new javax.swing.GroupLayout(glavnaFormaPanel26);
        glavnaFormaPanel26.setLayout(glavnaFormaPanel26Layout);
        glavnaFormaPanel26Layout.setHorizontalGroup(
            glavnaFormaPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 1409, Short.MAX_VALUE)
        );
        glavnaFormaPanel26Layout.setVerticalGroup(
            glavnaFormaPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(glavnaFormaPanel26Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        agencijeTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane7.setViewportView(agencijeTable);

        potvrdiPonuduButton.setText("Potvrdi ponudu");
        potvrdiPonuduButton.setBackground(new java.awt.Color(13, 146, 244));
        potvrdiPonuduButton.setFont(new java.awt.Font("JetBrains Mono", 1, 19)); // NOI18N
        potvrdiPonuduButton.setForeground(new java.awt.Color(255, 255, 255));
        potvrdiPonuduButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                potvrdiPonuduButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout glavnaFormaPanel27Layout = new javax.swing.GroupLayout(glavnaFormaPanel27);
        glavnaFormaPanel27.setLayout(glavnaFormaPanel27Layout);
        glavnaFormaPanel27Layout.setHorizontalGroup(
            glavnaFormaPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, glavnaFormaPanel27Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(potvrdiPonuduButton)
                .addGap(14, 14, 14))
        );
        glavnaFormaPanel27Layout.setVerticalGroup(
            glavnaFormaPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(glavnaFormaPanel27Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(potvrdiPonuduButton)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout kreirajPonuduPanelLayout = new javax.swing.GroupLayout(kreirajPonuduPanel);
        kreirajPonuduPanel.setLayout(kreirajPonuduPanelLayout);
        kreirajPonuduPanelLayout.setHorizontalGroup(
            kreirajPonuduPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kreirajPonuduPanelLayout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(kreirajPonuduPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(glavnaFormaPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(glavnaFormaPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(glavnaFormaPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(glavnaFormaPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(535, Short.MAX_VALUE))
        );
        kreirajPonuduPanelLayout.setVerticalGroup(
            kreirajPonuduPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kreirajPonuduPanelLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(glavnaFormaPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(glavnaFormaPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(glavnaFormaPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(glavnaFormaPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(667, Short.MAX_VALUE))
        );

        cardPanel.add(kreirajPonuduPanel, "card5");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(cardPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 2376, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1683, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void ponudaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ponudaButtonActionPerformed
        
        pretraziInput.setText("Pretraži id agencije...");
        cardPanel.removeAll();
        cardPanel.add(kreirajPonuduPanel);
        cardPanel.repaint();
        cardPanel.revalidate();
    }//GEN-LAST:event_ponudaButtonActionPerformed

    private void kontrolnaTablaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kontrolnaTablaButtonActionPerformed
        // TODO add your handling code here:
        pretraziInput.setText("Pretraži id agencije...");
        cardPanel.removeAll();
        cardPanel.add(kontrolnaTablaPanel);
        cardPanel.repaint();
        cardPanel.revalidate();

    }//GEN-LAST:event_kontrolnaTablaButtonActionPerformed

    private void takmicenjaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_takmicenjaButtonActionPerformed
        // TODO add your handling code here:
        pretraziInput.setText("Pretraži naziv takmičenja...");
        cardPanel.removeAll();
        cardPanel.add(takmicenjaPanel);
        cardPanel.repaint();
        cardPanel.revalidate();
    }//GEN-LAST:event_takmicenjaButtonActionPerformed

    private void evidentirajButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_evidentirajButtonActionPerformed
        // TODO add your handling code here:
        pretraziInput.setText("Pretraži ime veslača...");
        cardPanel.removeAll();
        cardPanel.add(veslacPanel);
        cardPanel.repaint();
        cardPanel.revalidate();
    }//GEN-LAST:event_evidentirajButtonActionPerformed

    private void imePrezimeInputFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_imePrezimeInputFocusGained
        // TODO add your handling code here:
        if (imePrezimeInput.getText().equals("Unesite ime i prezime...")) {
            imePrezimeInput.setText("");
        }
    }//GEN-LAST:event_imePrezimeInputFocusGained

    private void imePrezimeInputFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_imePrezimeInputFocusLost
        // TODO add your handling code here:
        if (imePrezimeInput.getText().isEmpty()) {
            imePrezimeInput.setText("Unesite ime i prezime...");
        }
    }//GEN-LAST:event_imePrezimeInputFocusLost

    private void visinaInputFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_visinaInputFocusGained
        // TODO add your handling code here:
        if (visinaInput.getText().equals("Unesite visinu...")) {
            visinaInput.setText("");
        }
    }//GEN-LAST:event_visinaInputFocusGained

    private void visinaInputFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_visinaInputFocusLost
        // TODO add your handling code here:
        if (visinaInput.getText().isEmpty()) {
            visinaInput.setText("Unesite visinu...");
        }
    }//GEN-LAST:event_visinaInputFocusLost

    private void tezinaInputFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tezinaInputFocusGained
        // TODO add your handling code here:
        if (tezinaInput.getText().equals("Unesite težinu...")) {
            tezinaInput.setText("");
        }
    }//GEN-LAST:event_tezinaInputFocusGained

    private void tezinaInputFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tezinaInputFocusLost
        // TODO add your handling code here:
        if (tezinaInput.getText().isEmpty()) {
            tezinaInput.setText("Unesite težinu...");
        }
    }//GEN-LAST:event_tezinaInputFocusLost

    private void najboljeVremeInputFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_najboljeVremeInputFocusGained
        // TODO add your handling code here:
        if (najboljeVremeInput.getText().equals("Unesite najbolje vreme...")) {
            najboljeVremeInput.setText("");
        }
    }//GEN-LAST:event_najboljeVremeInputFocusGained

    private void najboljeVremeInputFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_najboljeVremeInputFocusLost
        // TODO add your handling code here:
        if (najboljeVremeInput.getText().isEmpty()) {
            najboljeVremeInput.setText("Unesite najbolje vreme...");
        }
    }//GEN-LAST:event_najboljeVremeInputFocusLost

    private void evidentirajVeslacaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_evidentirajVeslacaButtonActionPerformed
        // TODO add your handling code here:
        boolean greska = false;
        Veslac noviVeslac = null;

        if (imePrezimeInput.getText().isEmpty() || imePrezimeInput.getText().equals("Unesite ime i prezime...")) {
            imePrezimeInput.setForeground(new Color(255, 51, 51));
            greska = true;
        }

        // Promena boje emailInput polja pri gresci
        if (visinaInput.getText().isEmpty() || visinaInput.getText().equals("Unesite visinu...")) {
            visinaInput.setForeground(new Color(255, 51, 51));
            greska = true;
        }
        // Promena boje telefonInput polja pri gresci
        if (tezinaInput.getText().isEmpty() || tezinaInput.getText().equals("Unesite težinu...")) {
            tezinaInput.setForeground(new Color(255, 51, 51));
            greska = true;
        }
        // Promena boje korisnickoImeInput polja pri gresci

        if (najboljeVremeInput.getText().isEmpty() || najboljeVremeInput.getText().equals("Unesite najbolje vreme...")) {
            najboljeVremeInput.setForeground(new Color(255, 51, 51));
            greska = true;
        }

        // Ako nije napravljena greska kreira se novi veslački klub
        if (!greska) {

            String imePrezime = imePrezimeInput.getText();

            LocalDate dlRodjenja = datumRodjenjaPicker.getDate();
            LocalDate dlUpisa = datumUpisaPicker.getDate();

            Date dRodjenja = Date.from(dlRodjenja.atStartOfDay(ZoneId.systemDefault()).toInstant());
            Date dUpisa = Date.from(dlUpisa.atStartOfDay(ZoneId.systemDefault()).toInstant());

            float visina = Float.parseFloat(visinaInput.getText());
            float tezina = Float.parseFloat(tezinaInput.getText());

            String kategorija = (String) kategorijaComboBox.getSelectedItem();

            String najboljeVreme = najboljeVremeInput.getText();

            float najboljeVremeFloat = Float.parseFloat(najboljeVreme);

            Veslac kreiranVeslac;
            try {
                kreiranVeslac = Klijent.getInstance().kreirajVeslaca(new Veslac(0, imePrezime, dRodjenja, visina, tezina, (KategorijaVeslaca) kategorijaComboBox.getSelectedItem(), najboljeVremeFloat, dUpisa, idKluba));
                
                if (kreiranVeslac != null) {
                JOptionPane.showMessageDialog(this, "Veslač je unet u bazu", "Upseh", JOptionPane.INFORMATION_MESSAGE);
                vtm.dodajVeslaca(kreiranVeslac);

            } else {
                JOptionPane.showMessageDialog(this, "Greska pri unosu veslača u bazu, pokušajte ponovo", "Greska", JOptionPane.ERROR_MESSAGE);
            }
                
            } catch (Exception ex) {
                Logger.getLogger(GlavnaFormaKlub.class.getName()).log(Level.SEVERE, null, ex);
            }

            

        }
    }//GEN-LAST:event_evidentirajVeslacaButtonActionPerformed

    private void obrisiVeslacaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_obrisiVeslacaButtonActionPerformed
        // TODO add your handling code here:
        if (veslaciTable.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Morate selektovati veslača za brisanje", "Greska", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int id = (int) veslaciTable.getValueAt(veslaciTable.getSelectedRow(), 0);
        Veslac obrisaniVeslac = Klijent.getInstance().obrisiVeslaca(id);

        if (obrisaniVeslac != null) {
            JOptionPane.showMessageDialog(this, "Uspešno brisanje veslača", "", JOptionPane.INFORMATION_MESSAGE);
            vtm.obrisiVeslaca(id);
        } else {
            JOptionPane.showMessageDialog(this, "Neuspelo brisanje veslača", "Greska", JOptionPane.ERROR_MESSAGE);

        }


    }//GEN-LAST:event_obrisiVeslacaButtonActionPerformed

    private void nazivTakmicenjaInputFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nazivTakmicenjaInputFocusGained
        // TODO add your handling code here:
        if (nazivTakmicenjaInput.getText().equals("Unesite naziv...")) {
            nazivTakmicenjaInput.setText("");
        }
    }//GEN-LAST:event_nazivTakmicenjaInputFocusGained

    private void nazivTakmicenjaInputFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nazivTakmicenjaInputFocusLost
        // TODO add your handling code here:
        if (nazivTakmicenjaInput.getText().isEmpty()) {
            nazivTakmicenjaInput.setText("Unesite naziv...");
        }
    }//GEN-LAST:event_nazivTakmicenjaInputFocusLost

    private void promeniVeslacaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_promeniVeslacaButtonActionPerformed
        // TODO add your handling code here:
        if (veslaciTable.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Morate selektovati veslača za ažuriranje", "Greska", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Veslac v = new Veslac();
        v.setIdVeslaca((int) veslaciTable.getValueAt(veslaciTable.getSelectedRow(),0));
        v.setImePrezime((String) veslaciTable.getValueAt(veslaciTable.getSelectedRow(), 1));
        v.setDatumRodjenja((java.util.Date) veslaciTable.getValueAt(veslaciTable.getSelectedRow(), 2));
        v.setVisina((float) veslaciTable.getValueAt(veslaciTable.getSelectedRow(), 3));
        v.setTezina((float) veslaciTable.getValueAt(veslaciTable.getSelectedRow(), 4));
        v.setKategorija((KategorijaVeslaca)veslaciTable.getValueAt(veslaciTable.getSelectedRow(), 5));
        v.setBMI((float) veslaciTable.getValueAt(veslaciTable.getSelectedRow(), 6));
        v.setNajboljeVreme((float) veslaciTable.getValueAt(veslaciTable.getSelectedRow(), 7));
//        v.setNajboljeVreme(Math.floor(najboljeVremeFloat / 60) + ":" + Math.floor(najboljeVremeFloat % 60));
        v.setDatumUpisa((java.util.Date) veslaciTable.getValueAt(veslaciTable.getSelectedRow(), 8));
        v.setIdKluba(idKluba);
        
        try {
            IzmeniVeslacForma ivf = new IzmeniVeslacForma(this,true,v);
            
            ivf.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {

                LinkedList<Veslac> veslaci = (LinkedList<Veslac>) Klijent.getInstance().vratiSveVeslace();
                veslaciTable.setModel(new VeslacTableModel(veslaci));
                veslaciTable.repaint();
                veslaciTable.revalidate();

            }

        });
            
//        
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,"Greška","Greška pri ažuriranju veslača", JOptionPane.ERROR_MESSAGE);
        }


    }//GEN-LAST:event_promeniVeslacaButtonActionPerformed

    private void azurirajNalogButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_azurirajNalogButtonActionPerformed
        // TODO add your handling code here:
        IzmeniPodatkeVKForma ivf = new IzmeniPodatkeVKForma(this, true);

        ivf.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {

                if(Klijent.getInstance().isOdjavaSignal()){
                    JOptionPane.showMessageDialog(null, "Nalog obrisan, gašenje programa...","Info",JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                    System.exit(0);
                }

            }

        });
        
    }//GEN-LAST:event_azurirajNalogButtonActionPerformed

    private void dodajNovoTakmicenjeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dodajNovoTakmicenjeButtonActionPerformed
        // TODO add your handling code here:
        if (!nazivTakmicenjaInput.getText().isEmpty() && datumTakmicenjaPicker.getDate() != null) {

            String nazivTakmicenja = nazivTakmicenjaInput.getText();
            KategorijaVeslaca kategorija = (KategorijaVeslaca) kategorijaTakmicaraComboBox.getSelectedItem();
            VrstaTrke vrstaTrke = (VrstaTrke) vrstaTrkeComboBox.getSelectedItem();
            Date datumTakmicenja =  Date.from(datumTakmicenjaPicker.getDate().atStartOfDay(ZoneId.systemDefault()).toInstant()); 

            try {
                Takmicenje kreiranoTakmicenje = Klijent.getInstance().dodajTakmicenje(new Takmicenje(0,nazivTakmicenja, kategorija, vrstaTrke, datumTakmicenja));
                ttm.dodajTakmicenje(kreiranoTakmicenje);
                

                nazivTakmicenjaInput.setText("");
                datumTakmicenjaPicker.setDate(null);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Greska " + ex, "Greska", JOptionPane.ERROR_MESSAGE);
            }

        }

    }//GEN-LAST:event_dodajNovoTakmicenjeButtonActionPerformed

    private void obrisiNovoTakmicenjeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_obrisiNovoTakmicenjeButtonActionPerformed
        // TODO add your handling code here:
        if (takmicenjaTable.getSelectedRow() != -1) {
            int idTakmicenja = (int) takmicenjaTable.getValueAt(takmicenjaTable.getSelectedRow(), 0);
            try {
                Klijent.getInstance().obrisiTakmicenje(idTakmicenja);
                ttm.obrisiTakmicenje(idTakmicenja);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex, "Greska", JOptionPane.ERROR_MESSAGE);
            }

        } else {
            JOptionPane.showMessageDialog(this, "Nijedno takmicenje nije selektovano za brisanje, selektujte takmicenje", "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_obrisiNovoTakmicenjeButtonActionPerformed

    private void dodajOsvojenoTakmicenjeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dodajOsvojenoTakmicenjeActionPerformed
        // TODO add your handling code here:
        int mesto = (int) mestoComboBox.getSelectedItem();
        if (takmicenjaTable.getSelectedRow() != -1) {
            int idTakmicenja = (int) takmicenjaTable.getValueAt(takmicenjaTable.getSelectedRow(), 0);

            try {
                KlubTakmicenje osvojenoTakmicenje = Klijent.getInstance().dodajOsvojenoTakmicenje(mesto, idTakmicenja, idKluba);
                ostm.dodajOsvojenoTakmicenje(osvojenoTakmicenje);

                prebrojTakmicenja();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Mesto je vec zauzeto", "Greska", JOptionPane.ERROR_MESSAGE);
            }

        } else {
            JOptionPane.showMessageDialog(this, "Takmicenje nije selektovano, selektujte takmicenje da bi ga dodali", "Greska", JOptionPane.ERROR_MESSAGE);
        }


    }//GEN-LAST:event_dodajOsvojenoTakmicenjeActionPerformed

    private void obrisiOsvojenoTakmicenjeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_obrisiOsvojenoTakmicenjeButtonActionPerformed
        // TODO add your handling code here:

        if (osvojenaTakmicenjaTable.getSelectedRow() != -1) {

            int idTakmicenja = (int) osvojenaTakmicenjaTable.getValueAt(osvojenaTakmicenjaTable.getSelectedRow(), 0);
            int mesto = (int) osvojenaTakmicenjaTable.getValueAt(osvojenaTakmicenjaTable.getSelectedRow(), 5);
            try {
                Klijent.getInstance().obrisiOsvojenoTakmicenje(idTakmicenja, mesto);
                ostm.obrisiOsvojenoTakmicenje(idKluba,idTakmicenja,mesto);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex, "Greska", JOptionPane.ERROR_MESSAGE);
            }

        } else {
            JOptionPane.showMessageDialog(this, "Osvojeno takmičenje nije selektovano, selektujte takmičenje da bi ga obrisali", "Greška", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_obrisiOsvojenoTakmicenjeButtonActionPerformed

    private void obrisiPonuduButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_obrisiPonuduButtonActionPerformed
        // TODO add your handling code here:

        if (ponudeTable.getSelectedRow() != -1) {

            int idPonude = (int) ponudeTable.getValueAt(ponudeTable.getSelectedRow(), 0);
            try {
                Klijent.getInstance().obrisiPonudu(idPonude);
                ptm.obrisiPonudu(idPonude);


            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex, "Greska", JOptionPane.ERROR_MESSAGE);
            }

        } else {
            JOptionPane.showMessageDialog(this, "Ponuda nije selektovana, selektujte ponudu kako bi je obrisali", "Greska", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_obrisiPonuduButtonActionPerformed

    private void pretraziInputFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pretraziInputFocusGained
        // TODO add your handling code here:
        if (pretraziInput.getText().equals("Pretraži id agencije...") || pretraziInput.getText().equals("Pretraži ime veslača...")
                || pretraziInput.getText().equals("Pretraži naziv takmičenja...")) {
            pretraziInput.setText("");
        }
    }//GEN-LAST:event_pretraziInputFocusGained

    private void pretraziInputFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pretraziInputFocusLost
        // TODO add your handling code here:
        if (pretraziInput.getText().isEmpty()) {
           if(cardPanel.getComponentZOrder(kontrolnaTablaPanel) >= 0) pretraziInput.setText("Pretraži id agencije...");
           else if(cardPanel.getComponentZOrder(veslacPanel) >= 0) pretraziInput.setText("Pretraži ime veslača...");
           else pretraziInput.setText("Pretraži naziv takmičenja...");
        }
    }//GEN-LAST:event_pretraziInputFocusLost

    private void obrisiStavkuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_obrisiStavkuButtonActionPerformed
        if (stavkaPonudeTable.getSelectedRow() != -1) {
            int rbStavke = (int) stavkaPonudeTable.getValueAt(stavkaPonudeTable.getSelectedRow(), 0);
            stavkePonude.removeIf(stavka -> {
                if(stavka.getRb() == rbStavke){
                    veslaciKlubaPonuda.add(stavka.getVeslac());
                    izbaceniRb.add(stavka.getRb());
                    return true;
                }else return false;

            });
            // Ne bih menjao, mozda za veslaciKlubaPonuda
            veslaciPonudaTable.setModel(new VeslacTableModel((LinkedList<Veslac>) veslaciKlubaPonuda));
            veslaciPonudaTable.repaint();
            veslaciPonudaTable.revalidate();

            stavkaPonudeTable.setModel(new StavkaPonudeTableModel(stavkePonude));
            stavkaPonudeTable.repaint();
            stavkaPonudeTable.revalidate();

        } else {
            JOptionPane.showMessageDialog(this, "Stavka nije selektovana,selektujte stavku da bi obrisali stavku ponude", "Greska", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_obrisiStavkuButtonActionPerformed

    private void dodajStavkuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dodajStavkuButtonActionPerformed
        if (veslaciPonudaTable.getSelectedRow() != -1) {
            int idVeslaca = (int) veslaciPonudaTable.getValueAt(veslaciPonudaTable.getSelectedRow(), 0);
            StavkaPonude s = new StavkaPonude();

            Veslac izabraniVeslac = Klijent.getInstance().vratiVeslacaPoId(idVeslaca);
            s.setVeslac(izabraniVeslac);
            Date datumUpisa = izabraniVeslac.getDatumUpisa();
            LocalDate datumUpisaLD = datumUpisa.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            Period p = Period.between(datumUpisaLD, LocalDate.now());
            int years = p.getYears();
            s.setGodineTreniranja(years);

            s.setIdEvidencije(idPonude);

            if(izbaceniRb.isEmpty()){
                s.setRb(++rb);
            }else{
                s.setRb(izbaceniRb.remove());
            }

            stavkePonude.add(s);

            veslaciKlubaPonuda.removeIf(veslac -> {
                if (veslac.equals(s.getVeslac())) {
                    obrisaniVeslaci.add(veslac);
                    return true;
                } else {
                    return false;
                }

            });
            // Isto, ako bude moralo
            veslaciPonudaTable.setModel(new VeslacTableModel((LinkedList<Veslac>) veslaciKlubaPonuda));
            veslaciPonudaTable.repaint();
            veslaciPonudaTable.revalidate();

            stavkaPonudeTable.setModel(new StavkaPonudeTableModel(stavkePonude));
            stavkaPonudeTable.repaint();
            stavkaPonudeTable.revalidate();

        } else {
            JOptionPane.showMessageDialog(this, "Veslac nije selektovan,selektujte veslaca da bi kreirali stavku ponude", "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_dodajStavkuButtonActionPerformed

    private void potvrdiPonuduButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_potvrdiPonuduButtonActionPerformed
        // TODO add your handling code here:
        if(agencijeTable.getSelectedRow() != -1 && !stavkePonude.isEmpty()){
            int idAgencije = (int) agencijeTable.getValueAt(agencijeTable.getSelectedRow(), 0);
            try {
                PonudaVeslaca kreiranaPonuda = Klijent.getInstance().kreirajPonuduVeslaca(idAgencije, idKluba, stavkePonude);
                JOptionPane.showMessageDialog(this,"Uspesno kreiranje ponude","Uspeh",JOptionPane.INFORMATION_MESSAGE);
                ptm.dodajPonudu(kreiranaPonuda);
                
                for(StavkaPonude stavka : stavkePonude){
                    veslaciKlubaPonuda.add(stavka.getVeslac());
                }
                
                StavkaPonudeTableModel sptm = (StavkaPonudeTableModel) stavkaPonudeTable.getModel();
                sptm.clear();
                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,"Greska pri kreiranju ponude","Greska", JOptionPane.ERROR_MESSAGE);
            }
            
        }else JOptionPane.showMessageDialog(this,"Neispravni parametri","Greska", JOptionPane.ERROR_MESSAGE);
        
    }//GEN-LAST:event_potvrdiPonuduButtonActionPerformed
    // TODO add your handling code here:
    // TODO add your handling code here:

    private void prebrojTakmicenja() {
        int[] brMesta = Klijent.getInstance().prebrojOsvojenaTakmicenja();
        zlatoLabel.setText(brMesta[0] + " osvojenih zlata");
        srebroLabel.setText(brMesta[1] + " osvojenih srebra");
        bronzaLabel.setText(brMesta[2] + " osvojenih bronzi");

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private forme.utils.GlavnaFormaTable agencijeTable;
    private javax.swing.JButton azurirajNalogButton;
    private javax.swing.JLabel bronzaLabel;
    private javax.swing.JPanel cardPanel;
    private com.github.lgooddatepicker.components.DatePicker datumRodjenjaPicker;
    private com.github.lgooddatepicker.components.DatePicker datumTakmicenjaPicker;
    private com.github.lgooddatepicker.components.DatePicker datumUpisaPicker;
    private javax.swing.JButton dodajNovoTakmicenjeButton;
    private javax.swing.JButton dodajOsvojenoTakmicenje;
    private javax.swing.JButton dodajStavkuButton;
    private javax.swing.JButton evidentirajButton;
    private javax.swing.JButton evidentirajVeslacaButton;
    private forme.utils.GlavnaFormaPanel glavnaFormaPanel1;
    private forme.utils.GlavnaFormaPanel glavnaFormaPanel10;
    private forme.utils.GlavnaFormaPanel glavnaFormaPanel11;
    private forme.utils.GlavnaFormaPanel glavnaFormaPanel12;
    private forme.utils.GlavnaFormaPanel glavnaFormaPanel13;
    private forme.utils.GlavnaFormaPanel glavnaFormaPanel14;
    private forme.utils.GlavnaFormaPanel glavnaFormaPanel15;
    private forme.utils.GlavnaFormaPanel glavnaFormaPanel16;
    private forme.utils.GlavnaFormaPanel glavnaFormaPanel17;
    private forme.utils.GlavnaFormaPanel glavnaFormaPanel18;
    private forme.utils.GlavnaFormaPanel glavnaFormaPanel19;
    private forme.utils.GlavnaFormaPanel glavnaFormaPanel2;
    private forme.utils.GlavnaFormaPanel glavnaFormaPanel20;
    private forme.utils.GlavnaFormaPanel glavnaFormaPanel21;
    private forme.utils.GlavnaFormaPanel glavnaFormaPanel22;
    private forme.utils.GlavnaFormaPanel glavnaFormaPanel23;
    private forme.utils.GlavnaFormaPanel glavnaFormaPanel24;
    private forme.utils.GlavnaFormaPanel glavnaFormaPanel25;
    private forme.utils.GlavnaFormaPanel glavnaFormaPanel26;
    private forme.utils.GlavnaFormaPanel glavnaFormaPanel27;
    private forme.utils.GlavnaFormaPanel glavnaFormaPanel3;
    private forme.utils.GlavnaFormaPanel glavnaFormaPanel4;
    private forme.utils.GlavnaFormaPanel glavnaFormaPanel5;
    private forme.utils.GlavnaFormaPanel glavnaFormaPanel6;
    private forme.utils.GlavnaFormaPanel glavnaFormaPanel7;
    private forme.utils.GlavnaFormaPanel glavnaFormaPanel8;
    private forme.utils.GlavnaFormaPanel glavnaFormaPanel9;
    private javax.swing.JTextField imePrezimeInput;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private forme.utils.GlavnaFormaPanel kFormaPanel1;
    private forme.utils.GlavnaFormaPanel kFormaPanel3;
    private javax.swing.JComboBox<String> kategorijaComboBox;
    private javax.swing.JComboBox<KategorijaVeslaca> kategorijaTakmicaraComboBox;
    private javax.swing.JButton kontrolnaTablaButton;
    private javax.swing.JPanel kontrolnaTablaPanel;
    private javax.swing.JPanel kreirajPonuduPanel;
    private javax.swing.JComboBox<Integer> mestoComboBox;
    private javax.swing.JTextField najboljeVremeInput;
    private javax.swing.JLabel nalogLabel;
    private javax.swing.JTextField nazivTakmicenjaInput;
    private javax.swing.JButton obrisiNovoTakmicenjeButton;
    private javax.swing.JButton obrisiOsvojenoTakmicenjeButton;
    private javax.swing.JButton obrisiPonuduButton;
    private javax.swing.JButton obrisiStavkuButton;
    private javax.swing.JButton obrisiVeslacaButton;
    private forme.utils.GlavnaFormaTable osvojenaTakmicenjaTable;
    private javax.swing.JButton ponudaButton;
    private javax.swing.JLabel ponudeLabel;
    private forme.utils.GlavnaFormaTable ponudeTable;
    private javax.swing.JButton potvrdiPonuduButton;
    private javax.swing.JTextField pretraziInput;
    private javax.swing.JButton promeniVeslacaButton;
    private javax.swing.JLabel srebroLabel;
    private forme.utils.GlavnaFormaTable stavkaPonudeTable;
    private javax.swing.JButton takmicenjaButton;
    private javax.swing.JPanel takmicenjaPanel;
    private forme.utils.GlavnaFormaTable takmicenjaTable;
    private forme.utils.TakmicenjaTableModel takmicenjaTableModel1;
    private forme.utils.TakmicenjaTableModel takmicenjaTableModel2;
    private javax.swing.JTextField tezinaInput;
    private javax.swing.JPanel veslacPanel;
    private forme.utils.GlavnaFormaTable veslaciPonudaTable;
    private forme.utils.GlavnaFormaTable veslaciTable;
    private javax.swing.JTextField visinaInput;
    private javax.swing.JComboBox<VrstaTrke> vrstaTrkeComboBox;
    private javax.swing.JLabel zlatoLabel;
    // End of variables declaration//GEN-END:variables
}
