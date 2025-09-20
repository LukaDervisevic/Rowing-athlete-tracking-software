package forme.prijava;

import com.formdev.flatlaf.FlatLightLaf;
import forme.drzava.DrzavaForma;
import java.awt.Color;
import java.awt.Window;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.MatteBorder;
import kontroler.Kontroler;
import model.Agencija;

import model.TipNaloga;
import model.Drzava;
import model.VeslackiKlub;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RegistracijaForma extends javax.swing.JDialog {

    private static final Logger logger = LogManager.getRootLogger();

    public RegistracijaForma(java.awt.Frame parent, boolean modal) {
        super(parent, modal);

        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException ex) {
            logger.error(ex.getMessage());
        }

        initComponents();
        setLocationRelativeTo(null);
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException ex) {
            logger.error(ex.getMessage());
        }
        inicijalnoRenderovanje();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public RegistracijaForma(JFrame roditelj) {
        super(roditelj, "Registracija korisnika", true);

        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException ex) {
            logger.error(ex.getMessage());
        }

        initComponents();
        setLocationRelativeTo(null);
        inicijalnoRenderovanje();
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException ex) {
            logger.error(ex.getMessage());
        }
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titlePanel = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        izaberiteTipLabel = new javax.swing.JLabel();
        tipNalogaComboBox = new javax.swing.JComboBox<>();
        infoPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        registracijaButton = new javax.swing.JButton();
        nazivLabel = new javax.swing.JLabel();
        adresaLabel = new javax.swing.JLabel();
        adresaInput = new javax.swing.JTextField();
        emailLabel = new javax.swing.JLabel();
        emailInput = new javax.swing.JTextField();
        telefonLabel = new javax.swing.JLabel();
        telefonInput = new javax.swing.JTextField();
        telefonComboBox = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        adresaZvezdica = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        potvrdaSifreLabel = new javax.swing.JLabel();
        korisnickoImeInput = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        korisnickoImeLabel = new javax.swing.JLabel();
        potvrdaSifreInput = new javax.swing.JPasswordField();
        sifraLabel = new javax.swing.JLabel();
        sifraInput = new javax.swing.JPasswordField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        greskaPotvrdaLabel = new javax.swing.JLabel();
        greskaSifraLabel = new javax.swing.JLabel();
        greskaTelefonLabel = new javax.swing.JLabel();
        drzavaLabel = new javax.swing.JLabel();
        drzavaComboBox = new javax.swing.JComboBox<>();
        drzavaZvezdica = new javax.swing.JLabel();
        nazivInput = new javax.swing.JTextField();
        drzavePitanjeLbl = new javax.swing.JLabel();
        dodajDrzavuBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registracija");
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        titlePanel.setBackground(new java.awt.Color(13, 146, 244));

        titleLabel.setFont(new java.awt.Font("JetBrains Mono", 3, 48)); // NOI18N
        titleLabel.setForeground(new java.awt.Color(255, 255, 255));
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel.setText("Klub ili Agencija?");

        izaberiteTipLabel.setFont(new java.awt.Font("JetBrains Mono", 3, 24)); // NOI18N
        izaberiteTipLabel.setForeground(new java.awt.Color(255, 255, 255));
        izaberiteTipLabel.setText("Izaberite tip naloga");

        tipNalogaComboBox.setFont(new java.awt.Font("JetBrains Mono", 1, 24)); // NOI18N
        tipNalogaComboBox.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        tipNalogaComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tipNalogaComboBoxItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout titlePanelLayout = new javax.swing.GroupLayout(titlePanel);
        titlePanel.setLayout(titlePanelLayout);
        titlePanelLayout.setHorizontalGroup(
            titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titlePanelLayout.createSequentialGroup()
                .addGroup(titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(titlePanelLayout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addComponent(tipNalogaComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(titlePanelLayout.createSequentialGroup()
                        .addGap(142, 142, 142)
                        .addComponent(izaberiteTipLabel)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, titlePanelLayout.createSequentialGroup()
                .addGap(0, 62, Short.MAX_VALUE)
                .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 538, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        titlePanelLayout.setVerticalGroup(
            titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titlePanelLayout.createSequentialGroup()
                .addGap(284, 284, 284)
                .addComponent(titleLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(izaberiteTipLabel)
                .addGap(40, 40, 40)
                .addComponent(tipNalogaComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(328, Short.MAX_VALUE))
        );

        getContentPane().add(titlePanel);

        infoPanel.setBackground(new java.awt.Color(255, 255, 255));
        infoPanel.setPreferredSize(new java.awt.Dimension(600, 800));

        jLabel1.setFont(new java.awt.Font("JetBrains Mono", 1, 36)); // NOI18N
        jLabel1.setText("Registracija korisnika");

        registracijaButton.setBackground(new java.awt.Color(13, 146, 244));
        registracijaButton.setFont(new java.awt.Font("JetBrains Mono", 1, 24)); // NOI18N
        registracijaButton.setForeground(new java.awt.Color(255, 255, 255));
        registracijaButton.setText("Registrujte se");
        registracijaButton.setBorder(null);
        registracijaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registracijaButtonActionPerformed(evt);
            }
        });

        nazivLabel.setFont(new java.awt.Font("JetBrains Mono", 2, 24)); // NOI18N
        nazivLabel.setText("Naziv");

        adresaLabel.setFont(new java.awt.Font("JetBrains Mono", 2, 24)); // NOI18N
        adresaLabel.setText("Adresa");

        adresaInput.setFont(new java.awt.Font("JetBrains Mono", 2, 18)); // NOI18N
        adresaInput.setForeground(new java.awt.Color(153, 153, 153));
        adresaInput.setText("Unesite adresu...");
        adresaInput.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));
        adresaInput.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                adresaInputFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                adresaInputFocusLost(evt);
            }
        });

        emailLabel.setFont(new java.awt.Font("JetBrains Mono", 2, 24)); // NOI18N
        emailLabel.setText("Email");

        emailInput.setFont(new java.awt.Font("JetBrains Mono", 2, 18)); // NOI18N
        emailInput.setForeground(new java.awt.Color(153, 153, 153));
        emailInput.setText("Unesite email...");
        emailInput.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));
        emailInput.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                emailInputFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                emailInputFocusLost(evt);
            }
        });

        telefonLabel.setFont(new java.awt.Font("JetBrains Mono", 2, 24)); // NOI18N
        telefonLabel.setText("Telefon");

        telefonInput.setFont(new java.awt.Font("Ubuntu", 2, 18)); // NOI18N
        telefonInput.setForeground(new java.awt.Color(153, 153, 153));
        telefonInput.setText("6512345");
        telefonInput.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                telefonInputFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                telefonInputFocusLost(evt);
            }
        });

        telefonComboBox.setFont(new java.awt.Font("Ubuntu", 2, 18)); // NOI18N
        telefonComboBox.setForeground(new java.awt.Color(153, 153, 153));
        telefonComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "+381", "+1", "+44", "+61", "+91", "+49", "+33", "+39", "+34", "+52", "+55", "+86", "+81", "+82", "+27", "+7", "+20", "+90", "+54", "+966", "+234", "+92", "+62", "+66", "+64", "+63", "+46", "+31", "+32", "+41", "+351", "+30", "+972", "+84", "+56", "+48", "+65", "+60", "+380" }));

        jLabel10.setForeground(new java.awt.Color(251, 51, 51));
        jLabel10.setText("*");

        adresaZvezdica.setForeground(new java.awt.Color(251, 51, 51));
        adresaZvezdica.setText("*");

        jLabel12.setForeground(new java.awt.Color(251, 51, 51));
        jLabel12.setText("*");

        jLabel13.setForeground(new java.awt.Color(251, 51, 51));
        jLabel13.setText("*");

        potvrdaSifreLabel.setFont(new java.awt.Font("JetBrains Mono", 2, 24)); // NOI18N
        potvrdaSifreLabel.setText("Potvrda šifre");

        korisnickoImeInput.setFont(new java.awt.Font("JetBrains Mono", 2, 18)); // NOI18N
        korisnickoImeInput.setForeground(new java.awt.Color(153, 153, 153));
        korisnickoImeInput.setText("Unesite korisnika...");
        korisnickoImeInput.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));
        korisnickoImeInput.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                korisnickoImeInputFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                korisnickoImeInputFocusLost(evt);
            }
        });

        jLabel15.setForeground(new java.awt.Color(251, 51, 51));
        jLabel15.setText("*");

        korisnickoImeLabel.setFont(new java.awt.Font("JetBrains Mono", 2, 24)); // NOI18N
        korisnickoImeLabel.setText("Korisničko ime");

        potvrdaSifreInput.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        potvrdaSifreInput.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));

        sifraLabel.setFont(new java.awt.Font("JetBrains Mono", 2, 24)); // NOI18N
        sifraLabel.setText("Šifra");

        sifraInput.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        sifraInput.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));

        jLabel18.setForeground(new java.awt.Color(251, 51, 51));
        jLabel18.setText("*");

        jLabel19.setForeground(new java.awt.Color(251, 51, 51));
        jLabel19.setText("*");

        greskaPotvrdaLabel.setFont(new java.awt.Font("JetBrains Mono", 2, 18)); // NOI18N
        greskaPotvrdaLabel.setForeground(new java.awt.Color(255, 51, 51));
        greskaPotvrdaLabel.setText("Greška pri potvrdi šifre");

        greskaSifraLabel.setFont(new java.awt.Font("JetBrains Mono", 2, 18)); // NOI18N
        greskaSifraLabel.setForeground(new java.awt.Color(255, 51, 51));
        greskaSifraLabel.setText("Greška pri unosu šifre");

        greskaTelefonLabel.setFont(new java.awt.Font("JetBrains Mono", 2, 18)); // NOI18N
        greskaTelefonLabel.setForeground(new java.awt.Color(255, 51, 51));
        greskaTelefonLabel.setText("Greška pri unosu telefona");

        drzavaLabel.setFont(new java.awt.Font("JetBrains Mono", 2, 24)); // NOI18N
        drzavaLabel.setText("Država");

        drzavaZvezdica.setForeground(new java.awt.Color(251, 51, 51));
        drzavaZvezdica.setText("*");

        nazivInput.setFont(new java.awt.Font("JetBrains Mono", 2, 18)); // NOI18N
        nazivInput.setForeground(new java.awt.Color(153, 153, 153));
        nazivInput.setText("Unesite naziv...");
        nazivInput.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));
        nazivInput.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                nazivInputFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                nazivInputFocusLost(evt);
            }
        });

        drzavePitanjeLbl.setFont(new java.awt.Font("JetBrains Mono", 2, 18)); // NOI18N
        drzavePitanjeLbl.setText("Nema vaše države?");

        dodajDrzavuBtn.setFont(new java.awt.Font("JetBrains Mono", 2, 18)); // NOI18N
        dodajDrzavuBtn.setForeground(new java.awt.Color(255, 51, 51));
        dodajDrzavuBtn.setText("Dodaj");
        dodajDrzavuBtn.setBorder(null);
        dodajDrzavuBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dodajDrzavuBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout infoPanelLayout = new javax.swing.GroupLayout(infoPanel);
        infoPanel.setLayout(infoPanelLayout);
        infoPanelLayout.setHorizontalGroup(
            infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, infoPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(registracijaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(91, 91, 91))
            .addGroup(infoPanelLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(infoPanelLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(infoPanelLayout.createSequentialGroup()
                        .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, infoPanelLayout.createSequentialGroup()
                                    .addComponent(nazivLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(177, 177, 177))
                                .addGroup(infoPanelLayout.createSequentialGroup()
                                    .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(nazivInput, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(infoPanelLayout.createSequentialGroup()
                                            .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addGroup(infoPanelLayout.createSequentialGroup()
                                                    .addComponent(emailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(emailInput, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                                .addComponent(korisnickoImeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel2)))
                                        .addComponent(korisnickoImeInput)
                                        .addComponent(sifraInput)
                                        .addComponent(greskaSifraLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                            .addGroup(infoPanelLayout.createSequentialGroup()
                                .addComponent(sifraLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(potvrdaSifreInput)
                            .addGroup(infoPanelLayout.createSequentialGroup()
                                .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(infoPanelLayout.createSequentialGroup()
                                        .addComponent(potvrdaSifreLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(adresaInput, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(infoPanelLayout.createSequentialGroup()
                                            .addComponent(adresaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(adresaZvezdica, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(infoPanelLayout.createSequentialGroup()
                                            .addComponent(drzavaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(drzavaZvezdica, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(infoPanelLayout.createSequentialGroup()
                                            .addComponent(telefonComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(telefonInput, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(infoPanelLayout.createSequentialGroup()
                                            .addComponent(telefonLabel)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(greskaTelefonLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(greskaPotvrdaLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(drzavaComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, infoPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(drzavePitanjeLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dodajDrzavuBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)))
                .addGap(33, 33, 33))
        );
        infoPanelLayout.setVerticalGroup(
            infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoPanelLayout.createSequentialGroup()
                .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(infoPanelLayout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jLabel3))
                    .addGroup(infoPanelLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel1)))
                .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(infoPanelLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nazivLabel)
                            .addComponent(jLabel10)
                            .addComponent(adresaLabel)
                            .addComponent(adresaZvezdica))
                        .addGap(30, 30, 30)
                        .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(adresaInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nazivInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39)
                        .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(emailLabel)
                            .addComponent(jLabel12)
                            .addComponent(telefonLabel)
                            .addComponent(jLabel13))
                        .addGap(22, 22, 22)
                        .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(telefonInput, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(emailInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(telefonComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 42, Short.MAX_VALUE)
                        .addComponent(greskaTelefonLabel)
                        .addGap(18, 18, 18)
                        .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(korisnickoImeLabel)
                            .addComponent(jLabel15)
                            .addComponent(drzavaLabel)
                            .addComponent(drzavaZvezdica))
                        .addGap(18, 18, 18))
                    .addGroup(infoPanelLayout.createSequentialGroup()
                        .addGap(163, 163, 163)
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(drzavaComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(korisnickoImeInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(infoPanelLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(drzavePitanjeLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dodajDrzavuBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(sifraLabel)
                            .addComponent(potvrdaSifreLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, infoPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, infoPanelLayout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addGap(27, 27, 27))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, infoPanelLayout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addGap(26, 26, 26)))))
                .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sifraInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(potvrdaSifreInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(greskaPotvrdaLabel)
                    .addComponent(greskaSifraLabel))
                .addGap(33, 33, 33)
                .addComponent(registracijaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
        );

        getContentPane().add(infoPanel);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void registracijaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registracijaButtonActionPerformed

        try {

            boolean greska = false;
            // Promena boje nazivInput polja pri gresci
            if (nazivInput.getText().isEmpty() || nazivInput.getText().equals("Unesite naziv...")) {
                nazivInput.setForeground(new Color(255, 51, 51));
                nazivInput.setBorder(new MatteBorder(0, 0, 1, 0, new Color(255, 51, 51)));
                greska = true;
            }
            // Promena boje adresaInput polja pri gresci
            if (((TipNaloga) tipNalogaComboBox.getSelectedItem()).equals(TipNaloga.VESLACKI_KLUB) && (adresaInput.getText().isEmpty() || adresaInput.getText().equals("Unesite adresu..."))) {
                adresaInput.setForeground(new Color(255, 51, 51));
                adresaInput.setBorder(new MatteBorder(0, 0, 1, 0, new Color(255, 51, 51)));
                greska = true;
            }
            // Promena boje emailInput polja pri gresci
            if (emailInput.getText().isEmpty() || emailInput.getText().equals("Unesite email...")) {
                emailInput.setForeground(new Color(255, 51, 51));
                emailInput.setBorder(new MatteBorder(0, 0, 1, 0, new Color(255, 51, 51)));
                greska = true;
            }
            // Promena boje telefonInput polja pri gresci
            if (telefonInput.getText().isEmpty() || telefonInput.getText().equals("6512345")) {
                telefonInput.setForeground(new Color(255, 51, 51));
                greskaTelefonLabel.setVisible(true);
                greska = true;
            }
            // Promena boje korisnickoImeInput polja pri gresci
            if (korisnickoImeInput.getText().isEmpty() || korisnickoImeInput.getText().equals("Unesite korisnika...")) {
                korisnickoImeInput.setForeground(new Color(255, 51, 51));
                korisnickoImeInput.setBorder(new MatteBorder(0, 0, 1, 0, new Color(255, 51, 51)));
                greska = true;
            }

            if (sifraInput.getText().isEmpty()) {
                sifraInput.setForeground(new Color(255, 51, 51));
                sifraInput.setBorder(new MatteBorder(0, 0, 1, 0, new Color(255, 51, 51)));
                greska = true;
            }

            if (potvrdaSifreInput.getText().isEmpty()) {
                sifraInput.setForeground(new Color(255, 51, 51));
                sifraInput.setBorder(new MatteBorder(0, 0, 1, 0, new Color(255, 51, 51)));
                greska = true;
            }

            if (!greska && sifraInput.getText().equals(potvrdaSifreInput.getText())) {

                String naziv = nazivInput.getText();
                String adresa = adresaInput.getText();
                String email = emailInput.getText();
                String korisnickoIme = korisnickoImeInput.getText();
                String telefon = (String) telefonComboBox.getSelectedItem() + telefonInput.getText();
                String sifra = sifraInput.getText();

                if (tipNalogaComboBox.getSelectedItem().equals(TipNaloga.VESLACKI_KLUB)) {
                    VeslackiKlub kreiraniKlub = Kontroler.getInstance().kreirajVeslackiKlub(new VeslackiKlub(0, naziv, adresa, email, telefon, korisnickoIme, sifra));
                    if (kreiraniKlub != null) {
                        Kontroler.getInstance().setUlogovaniNalog(kreiraniKlub);
                        JOptionPane.showMessageDialog(this, "Uspešno kreiranje naloga", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                        this.dispose();
                    }else{
                        throw new RuntimeException();
                    }

                } else {
                    Drzava drzava = (Drzava) drzavaComboBox.getSelectedItem();
                    System.out.println(drzava);
                    Agencija kreiranaAgencija = Kontroler.getInstance().kreirajAgenciju(new Agencija(0, naziv, email, telefon, korisnickoIme, sifra, drzava));
                    if (kreiranaAgencija != null) {
                        Kontroler.getInstance().setUlogovaniNalog(kreiranaAgencija);
                        JOptionPane.showMessageDialog(this, "Sistem je zapamtio agenciju", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                        this.dispose();
                    } else {
                        throw new RuntimeException();

                    }

                }

            } else {
                throw new RuntimeException();
            }

        } catch (Exception ex) {
            String tip = tipNalogaComboBox.getSelectedItem().equals(TipNaloga.VESLACKI_KLUB) ? "veslacki klub" : "agenciju";
            logger.error(ex.getMessage());
            JOptionPane.showMessageDialog(this, "Sistem ne može da zapamti " + tip, "Greška", JOptionPane.ERROR_MESSAGE);
        }


    }//GEN-LAST:event_registracijaButtonActionPerformed

    private void nazivInputFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nazivInputFocusGained
        if (nazivInput.getText().equals("Unesite naziv...")) {
            nazivInput.setText("");
        }
    }//GEN-LAST:event_nazivInputFocusGained

    private void nazivInputFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nazivInputFocusLost
        if (nazivInput.getText().isEmpty()) {
            nazivInput.setText("Unesite naziv...");
        }
    }//GEN-LAST:event_nazivInputFocusLost

    private void adresaInputFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_adresaInputFocusGained
        if (adresaInput.getText().equals("Unesite adresu...")) {
            adresaInput.setText("");
        }
    }//GEN-LAST:event_adresaInputFocusGained

    private void emailInputFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_emailInputFocusGained
        // TODO add your handling code here:
        if (emailInput.getText().equals("Unesite email...")) {
            emailInput.setText("");
        }
    }//GEN-LAST:event_emailInputFocusGained

    private void emailInputFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_emailInputFocusLost
        // TODO add your handling code here:
        if (emailInput.getText().isEmpty()) {
            emailInput.setText("Unesite email...");
        }
    }//GEN-LAST:event_emailInputFocusLost

    private void telefonInputFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_telefonInputFocusGained
        // TODO add your handling code here:
        if (telefonInput.getText().equals("6512345")) {
            telefonInput.setText("");
        }
    }//GEN-LAST:event_telefonInputFocusGained

    private void telefonInputFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_telefonInputFocusLost
        // TODO add your handling code here:
        if (telefonInput.getText().isEmpty()) {
            telefonInput.setText("6512345");
        }
    }//GEN-LAST:event_telefonInputFocusLost

    private void korisnickoImeInputFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_korisnickoImeInputFocusGained
        // TODO add your handling code here:
        if (korisnickoImeInput.getText().equals("Unesite korisnika...")) {
            korisnickoImeInput.setText("");
        }
    }//GEN-LAST:event_korisnickoImeInputFocusGained

    private void korisnickoImeInputFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_korisnickoImeInputFocusLost
        // TODO add your handling code here:
        if (korisnickoImeInput.getText().isEmpty()) {
            korisnickoImeInput.setText("Unesite korisnika...");
        }
    }//GEN-LAST:event_korisnickoImeInputFocusLost

    private void adresaInputFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_adresaInputFocusLost
        // TODO add your handling code here:
        if (adresaInput.getText().isEmpty()) {
            adresaInput.setText("Unesite adresu...");
        }
    }//GEN-LAST:event_adresaInputFocusLost

    private void tipNalogaComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tipNalogaComboBoxItemStateChanged
        // TODO add your handling code here:
        if (tipNalogaComboBox.getSelectedItem().equals(TipNaloga.VESLACKI_KLUB)) {
            drzavaComboBox.setEditable(false);
            drzavaComboBox.setEnabled(false);
            drzavaLabel.setForeground(new Color(153, 153, 153));
            drzavaZvezdica.setForeground(Color.WHITE);
            dodajDrzavuBtn.setEnabled(false);
            drzavePitanjeLbl.setVisible(false);

            adresaInput.setEditable(true);
            adresaInput.setEnabled(true);
            adresaInput.setForeground(Color.BLACK);
            adresaZvezdica.setForeground(new Color(251, 251, 251));

        } else {

            drzavaComboBox.setEditable(true);
            drzavaComboBox.setEnabled(true);
            drzavaLabel.setForeground(Color.BLACK);
            drzavaZvezdica.setForeground(new Color(251, 51, 51));
            dodajDrzavuBtn.setEnabled(true);
            drzavePitanjeLbl.setVisible(true);
            dodajDrzavuBtn.setVisible(true);

            adresaInput.setEditable(false);
            adresaInput.setEnabled(false);
            adresaInput.setForeground(new Color(153, 153, 153));
            adresaZvezdica.setForeground(Color.WHITE);

        }
    }//GEN-LAST:event_tipNalogaComboBoxItemStateChanged

    private void dodajDrzavuBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dodajDrzavuBtnActionPerformed
        // TODO add your handling code here:
        Window window = SwingUtilities.windowForComponent(dodajDrzavuBtn);
        DrzavaForma df = new DrzavaForma(window);


    }//GEN-LAST:event_dodajDrzavuBtnActionPerformed

    private void inicijalnoRenderovanje() {

        try {
            // Inicijalno sakriva poruke gresaka pri pokretanju forme
            greskaTelefonLabel.setVisible(false);
            greskaSifraLabel.setVisible(false);
            greskaPotvrdaLabel.setVisible(false);

            drzavaComboBox.setEditable(false);
            drzavaComboBox.setEnabled(false);
            drzavaLabel.setForeground(new Color(153, 153, 153));
            drzavaZvezdica.setForeground(Color.WHITE);

            drzavePitanjeLbl.setVisible(false);
            dodajDrzavuBtn.setVisible(false);

            List<TipNaloga> tipoviNaloga = List.of(TipNaloga.VESLACKI_KLUB, TipNaloga.AGENCIJA_ZA_TALENTE);

            for (TipNaloga tn : tipoviNaloga) {
                tipNalogaComboBox.addItem(tn);
            }

            List<Drzava> drzave = Kontroler.getInstance().vratiListuSveDrzave(new LinkedList<>());

            drzavaComboBox.addItem(null);

            for (Drzava d : drzave) {
                drzavaComboBox.addItem(d);
            }

        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField adresaInput;
    private javax.swing.JLabel adresaLabel;
    private javax.swing.JLabel adresaZvezdica;
    private javax.swing.JButton dodajDrzavuBtn;
    private javax.swing.JComboBox<Drzava> drzavaComboBox;
    private javax.swing.JLabel drzavaLabel;
    private javax.swing.JLabel drzavaZvezdica;
    private javax.swing.JLabel drzavePitanjeLbl;
    private javax.swing.JTextField emailInput;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JLabel greskaPotvrdaLabel;
    private javax.swing.JLabel greskaSifraLabel;
    private javax.swing.JLabel greskaTelefonLabel;
    private javax.swing.JPanel infoPanel;
    private javax.swing.JLabel izaberiteTipLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField korisnickoImeInput;
    private javax.swing.JLabel korisnickoImeLabel;
    private javax.swing.JTextField nazivInput;
    private javax.swing.JLabel nazivLabel;
    private javax.swing.JPasswordField potvrdaSifreInput;
    private javax.swing.JLabel potvrdaSifreLabel;
    private javax.swing.JButton registracijaButton;
    private javax.swing.JPasswordField sifraInput;
    private javax.swing.JLabel sifraLabel;
    private javax.swing.JComboBox<String> telefonComboBox;
    private javax.swing.JTextField telefonInput;
    private javax.swing.JLabel telefonLabel;
    private javax.swing.JComboBox<TipNaloga> tipNalogaComboBox;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JPanel titlePanel;
    // End of variables declaration//GEN-END:variables

}
