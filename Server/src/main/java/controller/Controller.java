package controller;

import db.DBBroker;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import model.Agencija;
import model.Drzava;
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
        
        Nalog ulogovaniNalog = dbb.pretraziVeslackiKlubLogin(nalog);
        
        if(nalog == null){
            ulogovaniNalog = dbb.pretraziAgencijuLogin(nalog);
        }else {
            setUlogovaniNalog(nalog);
        }
        
        if(nalog == null){
            throw new Exception("Neuspešna prijava naloga");
        }else {
            setUlogovaniNalog(nalog);
        }
        
        return ulogovaniNalog;
       
    }

    public VeslackiKlub kreirajVeslackiKlub(VeslackiKlub klub) throws Exception{
        String uuid = EmailAutentikator.posaljiEmail(klub.getEmail());
        VeslackiKlub kreiraniKlub =  dbb.kreirajVeslackiKlubUBazi(klub);
        setUuid(uuid);
        Controller.getInstance().setVremeKreiranjaNaloga(LocalDateTime.now());
        PotvrdaNalogaServis servis = new PotvrdaNalogaServis(klub,uuid);
        PozadinskiServis.proveraSifre(servis);
        return kreiraniKlub;
    }

    public Veslac kreirajVeslaca(Veslac veslac) throws Exception {
        return dbb.kreirajVeslacaUBazi(veslac);
    }

    public List<Takmicenje> vratiTakmicenja() {
        return dbb.vratiTakmicenjaIzBaze();
    }

    public VeslackiKlub azuirirajVeslackiKlub(VeslackiKlub klub) throws Exception {
        return dbb.azurirajVeslackiKlubUBazi(klub);
    }

    public Integer obrisiVeslackiKlub(Integer id) throws Exception {
        return dbb.obrisiVeslackiKlubIzBaze(id);
    }

    public VeslackiKlub vratiVeslackiKlubPoId(Integer id) throws Exception {
        return dbb.vratiVeslackiKlubPoIdDB(id);
    }
    
    public Agencija vratiAgencijuPoId(Integer id){
        return dbb.vratiAgencijuPoId(id);
    }

    public List<Veslac> vratiSveVeslace(Integer idKluba) {
        return dbb.vratiSveVeslaceDB(idKluba);
    }

    public Integer obrisiVeslaca(Integer id) throws Exception {
        return dbb.obrisiVeslacaIzBaze(id);
    }

    public Veslac azurirajVeslaca(Veslac veslac) throws Exception {
        return dbb.azurirajVeslacaUBazi(veslac);
    }

    public Nalog kreirajAgenciju(Agencija agencija) throws Exception {
        String uuid = EmailAutentikator.posaljiEmail(agencija.getEmail());
        Agencija kreiranaAgencija =  dbb.kreirajAgencijuUBazi(agencija);
        setUuid(uuid);
        Controller.getInstance().setVremeKreiranjaNaloga(LocalDateTime.now());
        PotvrdaNalogaServis servis = new PotvrdaNalogaServis(agencija,uuid);
        PozadinskiServis.proveraSifre(servis);
        return kreiranaAgencija;
        
    }

    public Integer obrisiAgenciju(Integer id) throws Exception {
        return dbb.obrisiAgencijuIzBaze(id);
    }

    public List<Drzava> vratiSveDrzave() {
        return dbb.vratiSveDrzaveIzBaze();
    }

    public Takmicenje dodajTakmicenje(Takmicenje takmicenje) throws Exception {
        return dbb.dodajTakmicenjeDB(takmicenje);
    }

    public List<KlubTakmicenje> vratiTakmicenjaKluba(Integer idKluba) throws Exception {
        return dbb.vratiTakmicenjaKlubaDB(idKluba);
        
    }

    public Integer obrisiTakmicenje(Integer idTakmicenja) throws SQLException, Exception {
        return dbb.obrisiTakmicenjeDB(idTakmicenja);
    }

    public List<Takmicenje> vratiSvaTakmicenja() {
        return dbb.vratiSvaTakmicenjaDB();
    }

    public KlubTakmicenje dodajOsvojenoTakmicenje(KlubTakmicenje klubTakmicenje) throws Exception {
        return dbb.dodajOsvojenoTakmicenjeDB(klubTakmicenje);
    }
    
    public int[] prebrojOsvojenaTakmicenja() {
        return dbb.prebrojOsvojenaTakmicenjaDB();
    }

    public Integer obrisiOsvojenoTakmicenje(KlubTakmicenje klubTakmicenje) throws Exception {
         return dbb.obrisiOsvojenoTakmicenje(klubTakmicenje);
    }

    public Veslac vratiVeslacaPoId(Integer idVeslaca) {
        return dbb.vratiVeslacaPoId(idVeslaca);
    }

    public int vratiPoslednjiIdPonude() {
        return dbb.vratiPoslednjiIdPonudeDB();
    }

    public PonudaVeslaca kreirajPonuduVeslaca(PonudaVeslaca ponuda) throws Exception {
        return dbb.kreirajPonuduVeslacaDB(ponuda);
    }

    public List<PonudaVeslaca> vratiSvePonudeKluba(Integer idKluba) {
        return dbb.vratiSvePonudeKlubaDB(idKluba);
    }

    public Integer obrisiPonudu(Integer idPonude) throws Exception {
        return dbb.obrisiPonuduDB(idPonude);
    }

    public List<PonudaVeslaca> pretraziPonudu(PonudaVeslaca ponuda) {
        return dbb.pretraziPonuduKlubaDB(ponuda);
    }

    public List<Veslac> pretraziVeslaca(Veslac veslac) {
        return dbb.pretraziVeslacaDB(veslac);
    }

    // Mozda i da ostavim ovako
    
    public List<Takmicenje> pretraziTakmicenja(String nazivTakmicenja) {
        return dbb.pretraziTakmicenjaDB(nazivTakmicenja);
    }

    public List<PonudaVeslaca> vratiSvePonudeAgencije(Integer idAgencije) {
        return dbb.vratiSvePonudeAgencijeDB(idAgencije);
    }

    public Agencija azurirajAgenciju(Agencija agencija) throws Exception {      
        return dbb.azurirajAgencijuDB(agencija);
    }

    public List<VeslackiKlub> vratiSveKlubove() {
        return dbb.vratiSveKluboveDB();
    }

    public List<StavkaPonude> vratiSveStavkePonude(Integer idPonude) {
        return dbb.vratiSveStavkePonudeDB(idPonude);
    }
    
    // Mozda da ostavim
    public List<VeslackiKlub> pretraziKlub(String upitZaPretragu) {
        return dbb.pretraziKlubDB(upitZaPretragu);
    }

    public Integer obrisiKlub(Integer id) throws Exception {
        return dbb.obrisiVeslackiKlubIzBaze(id);
    }

    public List<Agencija> vratiSveAgencije() {
        return dbb.vratiSveAgencijeDB();
    }

    public Takmicenje vratiTakmicenjePoId(Integer idTakmicenja){
        return dbb.vratiTakmicenjePoIdDB(idTakmicenja);
    }
    

    
    
    
    
    
}
