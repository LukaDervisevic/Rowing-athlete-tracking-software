package controller;

import db.DBBroker;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import model.Agencija;
import model.Drzava;
import model.KategorijaVeslaca;
import model.KlubTakmicenje;
import model.Nalog;
import model.PonudaVeslaca;
import model.StavkaPonude;
import model.Takmicenje;
import model.Veslac;
import model.VeslackiKlub;
import model.VrstaTrke;
import utils.EmailAutentikator;
import utils.PotvrdaNalogaServis;
import utils.PozadinskiServis;


public class Controller {
    
    private static Controller instance;
    
    private Nalog ulogovaniNalog;
    
    private boolean odjavaSignal;
    
    private String uuid;
    
    private LocalDateTime vremeKreiranjaNaloga;
    
    private DBBroker dbb = DBBroker.getInstance();
    
    private Controller(){
        odjavaSignal = false;
    }

    
    public static Controller getInstance() {
        if(instance == null)
            instance = new Controller();
        return instance;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Nalog getUlogovaniNalog() {
        return ulogovaniNalog;
    }

    public void setUlogovaniNalog(Nalog ulogovaniNalog) {
        this.ulogovaniNalog = ulogovaniNalog;
        this.vremeKreiranjaNaloga = LocalDateTime.now();
    }
    
    
    public LocalDateTime getVremeKreiranjaNaloga() {
        return vremeKreiranjaNaloga;
    }

    public void setVremeKreiranjaNaloga(LocalDateTime vremeKreiranjaNaloga) {
        this.vremeKreiranjaNaloga = vremeKreiranjaNaloga;
    }

    public boolean isOdjavaSignal() {
        return odjavaSignal;
    }

    public void setOdjavaSignal(boolean odjavaSignal) {
        this.odjavaSignal = odjavaSignal;
    }

    public DBBroker getDbb() {
        return dbb;
    }

    public void setDbb(DBBroker dbb) {
        this.dbb = dbb;
    }
    
    
    
    
    
    public Nalog login(Nalog nalog) throws Exception{
        
        Nalog nalog = dbb.pretraziVeslackiKlubLogin(nalog);
        
        if(nalog == null){
            nalog = dbb.pretraziAgencijuLogin(nalog);
        }else {
            setUlogovaniNalog(nalog);
        }
        
        if(nalog == null){
            throw new Exception("Neuspešna prijava naloga");
        }else {
            setUlogovaniNalog(nalog);
        }
        
        return nalog;
       
    }

    public boolean kreirajVeslackiKlub(String naziv, String adresa, String email, String telefon, String korisnickoIme) {
        
        String uuid = EmailAutentikator.posaljiEmail(email);
        VeslackiKlub klub =  dbb.kreirajVeslackiKlubUBazi(naziv,adresa,email,telefon,korisnickoIme,uuid);
        setUuid(uuid);
        Controller.getInstance().setVremeKreiranjaNaloga(LocalDateTime.now());
        PotvrdaNalogaServis servis = new PotvrdaNalogaServis(klub,uuid);
        PozadinskiServis.proveraSifre(servis);
        return klub != null;
    }

    public Veslac kreirajVeslaca(String imePrezime,Date datumRodjenja, float visina, float tezina, String kategorija, Date datumUpisa, float najboljeVreme,int idKluba) throws Exception {
        return dbb.kreirajVeslacaUBazi(imePrezime, datumRodjenja, visina, tezina, kategorija, datumUpisa, najboljeVreme,idKluba);
    }

    public List<Takmicenje> vratiTakmicenja() {
        return dbb.vratiTakmicenjaIzBaze();
    }

    public boolean azuirirajVeslackiKlub(int id, String naziv, String adresa, String email, String telefon, String korisnickoIme, String sifra) {
        return dbb.azurirajVeslackiKlubUBazi(id, naziv, adresa, email,telefon,korisnickoIme,sifra);
    }

    public boolean obrisiVeslackiKlub(int id) {
        return dbb.obrisiVeslackiKlubIzBaze(id);
    }

    public VeslackiKlub vratiVeslackiKlubPoId(int id) {
        return dbb.vratiVeslackiKlubPoIdDB(id);
    }
    
    public Agencija vratiAgencijuPoId(int id){
        return dbb.vratiAgencijuPoId(id);
    }

    public List<Veslac> vratiSveVeslace() {
        return dbb.vratiSveVeslaceDB();
    }

    public boolean obrisiVeslaca(int id) {
        return dbb.obrisiVeslacaIzBaze(id);
    }

    public void azurirajVeslaca(Veslac v) throws Exception {
        dbb.azurirajVeslacaUBazi(v);
    }

    public boolean kreirajAgenciju(String naziv, String email, String telefon, Drzava drzava, String korisnickoIme) {
        String uuid = EmailAutentikator.posaljiEmail(email);
        Agencija agencija =  dbb.kreirajAgencijuUBazi(naziv, email, telefon, drzava, korisnickoIme,uuid);
        setUuid(uuid);
        Controller.getInstance().setVremeKreiranjaNaloga(LocalDateTime.now());
        PotvrdaNalogaServis servis = new PotvrdaNalogaServis(agencija,uuid);
        PozadinskiServis.proveraSifre(servis);
        return agencija != null;
        
    }

    public boolean obrisiAgenciju(int id) {
        return dbb.obrisiAgencijuIzBaze(id);
    }

    public List<Drzava> vratiSveDrzave() {
        return dbb.vratiSveDrzaveIzBaze();
    }

    public Takmicenje dodajTakmicenje(String nazivTakmicenja, KategorijaVeslaca kategorija, VrstaTrke vrstaTrke, LocalDate datumTakmicenja) throws SQLException {
        return dbb.dodajTakmicenjeDB(nazivTakmicenja,kategorija,vrstaTrke,datumTakmicenja);
    }

    public List<KlubTakmicenje> vratiTakmicenjaKluba(int idKluba) {
        return dbb.vratiTakmicenjaKlubaDB(idKluba);
        
    }

    public void obrisiTakmicenje(int idTakmicenja) throws SQLException {
        dbb.obrisiTakmicenjeDB(idTakmicenja);
    }

    public List<Takmicenje> vratiSvaTakmicenja() {
        return dbb.vratiSvaTakmicenjaDB();
    }

    public KlubTakmicenje dodajOsvojenoTakmicenje(int mesto, int idTakmicenja, int idKluba) throws Exception {
        return dbb.dodajOsvojenoTakmicenjeDB(mesto,idTakmicenja,idKluba);
    }

    public int[] prebrojOsvojenaTakmicenja() {
        return dbb.prebrojOsvojenaTakmicenjaDB();
    }

    public void obrisiOsvojenoTakmicenje(int idTakmicenja,int mesto) throws Exception {
        int idKluba = getInstance().getUlogovaniNalog().getId();
         dbb.obrisiOsvojenoTakmicenje(idTakmicenja,idKluba,mesto);
    }

    public Veslac vratiVeslacaPoId(int idVeslaca) {
        return dbb.vratiVeslacaPoId(idVeslaca);
    }

    public int vratiPoslednjiIdPonude() {
        return dbb.vratiPoslednjiIdPonudeDB();
    }

    public PonudaVeslaca kreirajPonuduVeslaca(int idAgencije, int idKluba, List<StavkaPonude> stavke) throws Exception {
        return dbb.kreirajPonuduVeslacaDB(idAgencije,idKluba,stavke);
    }

    public List<PonudaVeslaca> vratiSvePonudeKluba(int idKluba) {
        return dbb.vratiSvePonudeKlubaDB(idKluba);
    }

    public void obrisiPonudu(int idPonude) throws Exception {
        dbb.obrisiPonuduDB(idPonude);
    }

    public List<PonudaVeslaca> pretraziPonudu(int idAgencije,int idKluba) {
        return dbb.pretraziPonuduKlubaDB(idAgencije,idKluba);
    }

    public List<Veslac> pretraziVeslaca(String imePrezime, int idKluba) {
        return dbb.pretraziVeslacaDB(imePrezime,idKluba);
    }

    public List<Takmicenje> pretraziTakmicenja(String nazivTakmicenja) {
        return dbb.pretraziTakmicenjaDB(nazivTakmicenja);
    }

    public List<PonudaVeslaca> vratiSvePonudeAgencije(int idAgencije) {
        return dbb.vratiSvePonudeAgencijeDB(idAgencije);
    }

    public void azurirajAgenciju(int id, String naziv, String telefon, String email, String korisnickoIme, Drzava drzava, String sifra) throws Exception {
        
        dbb.azurirajAgencijuDB(id,naziv,telefon,email,korisnickoIme,drzava,sifra);
    }

    public List<VeslackiKlub> vratiSveKlubove() {
        return dbb.vratiSveKluboveDB();
    }

    public List<StavkaPonude> vratiSveStavkePonude(int idPonude) {
        return dbb.vratiSveStavkePonudeDB(idPonude);
    }

    public List<VeslackiKlub> pretraziKlub(String upitZaPretragu) {
        return dbb.pretraziKlubDB(upitZaPretragu);
    }

    public void obrisiKlub(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<Agencija> vratiSveAgencije() {
        return dbb.vratiSveAgencijeDB();
    }

    public Takmicenje vratiTakmicenjePoId(int idTakmicenja){
        return dbb.vratiTakmicenjePoIdDB(idTakmicenja);
    }
    

    
    
    
    
    
}
