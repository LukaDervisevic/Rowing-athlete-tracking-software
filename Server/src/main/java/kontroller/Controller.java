package kontroller;

import bbp.DBBroker;
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
import so.KreirajDK;
import so.NadjiDK;
import so.ObrisiDK;
import so.PromeniDK;
import so.agencija.SOKreirajAgenciju;
import so.agencija.SOPretraziAgenciju;
import so.agencija.SOPromeniAgenciju;
import so.drzava.SOObrisiDrzavu;
import so.drzava.SOUbaciDrzavu;
import so.ponudaveslaca.SOKreirajPonudu;
import so.ponudaveslaca.SOObrisiPonudu;
import so.ponudaveslaca.SOPretraziPonudu;
import so.ponudaveslaca.SOPromeniPonudu;
import so.takmicenje.SOObrisiTakmicenje;
import so.takmicenje.SOPretraziTakmicenje;
import so.takmicenje.SOUbaciTakmicenje;
import so.veslac.SOObrisiVeslaca;
import so.veslac.SOPretraziVeslaca;
import so.veslac.SOPromeniVeslaca;
import so.veslackiklub.SOKreirajKlub;
import so.veslackiklub.SOObrisiKlub;
import so.veslackiklub.SOPretraziKlub;
import so.veslackiklub.SOPromeniKlub;
import transfer.TransferObjekat;



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
        
        if(ulogovaniNalog == null){
            ulogovaniNalog = dbb.pretraziAgencijuLogin(nalog);
        }
        
        if(ulogovaniNalog == null){
            throw new Exception("Neuspešna prijava naloga");
        }
        
        return ulogovaniNalog;
       
    }

    public boolean kreirajVeslackiKlub(TransferObjekat to) throws Exception{
        KreirajDK kreirajSo = new SOKreirajKlub(to);
        return kreirajSo.opsteIzvrsenjeSO();
    }

    public boolean azuirirajVeslackiKlub(TransferObjekat to) throws Exception {
        PromeniDK promeniSo = new SOPromeniKlub(to);
        return promeniSo.opsteIzvrsenjeSO();
    }
    
//    public Veslac kreirajVeslaca(Veslac veslac) throws Exception {
//        return dbb.kreirajVeslacaUBazi(veslac);k
//    }
    public boolean pretraziKlub(TransferObjekat to) {
        NadjiDK nadjiSo = new SOPretraziKlub(to);
        return nadjiSo.opsteIzvrsenjeSO();
    }
//
//        public List<VeslackiKlub> pretraziKlub(String upitZaPretragu) {
//        return dbb.pretraziKlubDB(upitZaPretragu);
//    }
    
    public List<Takmicenje> vratiTakmicenja() {
        return dbb.vratiTakmicenjaIzBaze();
    }

//    public VeslackiKlub azuirirajVeslackiKlub(VeslackiKlub klub) throws Exception {
//        return dbb.azurirajVeslackiKlubUBazi(klub);
//    }

    public boolean obrisiVeslackiKlub(TransferObjekat to) throws Exception {
        ObrisiDK obrisiSo = new SOObrisiKlub(to);
        return obrisiSo.opsteIzvrsenjeSO();
    }
    
//    public Integer obrisiVeslackiKlub(Integer id) throws Exception {
//        return dbb.obrisiVeslackiKlubIzBaze(id);
//    }

    public VeslackiKlub vratiVeslackiKlubPoId(Integer id) throws Exception {
        return dbb.vratiVeslackiKlubPoIdDB(id);
    }
    
    public Agencija vratiAgencijuPoId(Integer id){
        return dbb.vratiAgencijuPoId(id);
    }

    public List<Veslac> vratiSveVeslace(Integer idKluba) {
        return dbb.vratiSveVeslaceDB(idKluba);
    }

    public boolean azurirajVeslaca(TransferObjekat to) throws Exception {
        PromeniDK promeniSo = new SOPromeniVeslaca(to);
        return promeniSo.opsteIzvrsenjeSO();
    }
    
    public boolean obrisiVeslaca(TransferObjekat to) throws Exception {
        ObrisiDK obrisiSo = new SOObrisiVeslaca(to);
        return obrisiSo.opsteIzvrsenjeSO();
    }
    
    public boolean pretraziVeslaca(TransferObjekat to) throws Exception{
        NadjiDK nadjiSo = new SOPretraziVeslaca(to);
        return nadjiSo.opsteIzvrsenjeSO();
    }
    
//    public List<Veslac> pretraziVeslaca(Veslac veslac) {
//        return dbb.pretraziVeslacaDB(veslac);
//    }    
    
//    public Integer obrisiVeslaca(Integer id) throws Exception {
//        return dbb.obrisiVeslacaIzBaze(id);
//    }
//    public Veslac azurirajVeslaca(Veslac veslac) throws Exception {
//        return dbb.azurirajVeslacaUBazi(veslac);
//    }

    public boolean kreirajAgenciju(TransferObjekat to) throws Exception {
        KreirajDK kreirajSo = new SOKreirajAgenciju(to);
        return kreirajSo.opsteIzvrsenjeSO();
    }
    
//    public Nalog kreirajAgenciju(Agencija agencija) throws Exception {
//        return dbb.kreirajAgencijuUBazi(agencija);
//    }

    public boolean obrisiAgenciju(TransferObjekat to) throws Exception {
        ObrisiDK obrisiSo = new SOObrisiKlub(to);
        return obrisiSo.opsteIzvrsenjeSO();
    }
    
//    public Integer obrisiAgenciju(Integer id) throws Exception {
//        return dbb.obrisiAgencijuIzBaze(id);
//    }

    public boolean azurirajAgenciju(TransferObjekat to) throws Exception {
        PromeniDK promeniSo = new SOPromeniAgenciju(to);
        return promeniSo.opsteIzvrsenjeSO();
    }    
//    public Agencija azurirajAgenciju(Agencija agencija) throws Exception {      
//        return dbb.azurirajAgencijuDB(agencija);
//    }
    
    public boolean pretraziAgenciju(TransferObjekat to) throws Exception {
        NadjiDK nadjiSo = new SOPretraziAgenciju(to);
        return nadjiSo.opsteIzvrsenjeSO();
    }
    
//        public List<Agencija> pretraziAgenciju(String nazivAgencije) throws Exception {
//        return dbb.pretraziAgencijuDB(nazivAgencije);
//    }

    public List<Drzava> vratiSveDrzave() {
        return dbb.vratiSveDrzaveIzBaze();
    }

    public boolean dodajTakmicenje(TransferObjekat to) throws Exception {
        KreirajDK dodajSo = new SOUbaciTakmicenje(to);
        return dodajSo.opsteIzvrsenjeSO();
    }
    
//    public Takmicenje dodajTakmicenje(Takmicenje takmicenje) throws Exception {
//        return dbb.dodajTakmicenjeDB(takmicenje);
//    }

    public List<KlubTakmicenje> vratiTakmicenjaKluba(Integer idKluba) throws Exception {
        return dbb.vratiTakmicenjaKlubaDB(idKluba);
        
    }

    public boolean obrisiTakmicenje(TransferObjekat to) throws Exception {
        ObrisiDK obrisiSo = new SOObrisiTakmicenje(to);
        return obrisiSo.opsteIzvrsenjeSO();
    }
    
//    public Integer obrisiTakmicenje(Integer idTakmicenja) throws SQLException, Exception {
//        return dbb.obrisiTakmicenjeDB(idTakmicenja);
//    }
    
    public boolean pretraziTakmicenja(TransferObjekat to) {
        NadjiDK pretraziSo = new SOPretraziTakmicenje(to);
        return  pretraziSo.opsteIzvrsenjeSO();
    }
    
//    public List<Takmicenje> pretraziTakmicenja(String nazivTakmicenja) {
//        return dbb.pretraziTakmicenjaDB(nazivTakmicenja);
//    }
    

    public List<Takmicenje> vratiSvaTakmicenja() {
        return dbb.vratiSvaTakmicenjaDB();
    }

    public KlubTakmicenje dodajOsvojenoTakmicenje(KlubTakmicenje klubTakmicenje) throws Exception {
        return dbb.dodajOsvojenoTakmicenjeDB(klubTakmicenje);
    }
    
    public int[] prebrojOsvojenaTakmicenja(Integer idKluba) {
        return dbb.prebrojOsvojenaTakmicenjaDB(idKluba);
    }

    public Integer obrisiOsvojenoTakmicenje(KlubTakmicenje klubTakmicenje) throws Exception {
         return dbb.obrisiOsvojenoTakmicenjeDB(klubTakmicenje);
    }

    public Veslac vratiVeslacaPoId(Integer idVeslaca) {
        return dbb.vratiVeslacaPoId(idVeslaca);
    }

    public int vratiPoslednjiIdPonude() {
        return dbb.vratiPoslednjiIdPonudeDB();
    }

//    public PonudaVeslaca kreirajPonuduVeslaca(PonudaVeslaca ponuda) throws Exception {
//        return dbb.kreirajPonuduVeslacaDB(ponuda);
//    }
    
    public boolean kreirajPonuduVeslaca(TransferObjekat to) {
        KreirajDK kreirajSo = new SOKreirajPonudu(to);
        return kreirajSo.opsteIzvrsenjeSO();
    }

    public List<PonudaVeslaca> vratiSvePonudeKluba(Integer idKluba) {
        return dbb.vratiSvePonudeKlubaDB(idKluba);
    }

//    public Integer obrisiPonudu(Integer idPonude) throws Exception {
//        return dbb.obrisiPonuduDB(idPonude);
//    }
    
    public boolean obrisiPonudu(TransferObjekat to) {
        ObrisiDK obrisiSo = new SOObrisiPonudu(to);
        return obrisiSo.opsteIzvrsenjeSO();
    }

    public PonudaVeslaca vratiPonuduPoId(int idPonude) throws Exception{
        return dbb.vratiPonuduPoIdDB(idPonude);
    }
    
//    public PonudaVeslaca azurirajPonudu(PonudaVeslaca ponudaVeslaca) throws Exception {
//        return dbb.azurirajPonuduDB(ponudaVeslaca);
//    }
    
    public boolean azurirajPonudu(TransferObjekat to) {
        PromeniDK promeniSo = new SOPromeniPonudu(to);
        return promeniSo.opsteIzvrsenjeSO();
    }
    
    public boolean pretraziPonudu(TransferObjekat to) {
        NadjiDK nadjiSo = new SOPretraziPonudu(to);
        return nadjiSo.opsteIzvrsenjeSO();
    }
    
//    public List<PonudaVeslaca> pretraziPonudu(PonudaVeslaca ponuda) {
//        return dbb.pretraziPonuduKlubaDB(ponuda);
//    }

    public List<PonudaVeslaca> vratiSvePonudeAgencije(Integer idAgencije) {
        return dbb.vratiSvePonudeAgencijeDB(idAgencije);
    }



    public List<VeslackiKlub> vratiSveKlubove() {
        return dbb.vratiSveKluboveDB();
    }

    public List<StavkaPonude> vratiSveStavkePonude(Integer idPonude) {
        return dbb.vratiSveStavkePonudeDB(idPonude);
    }
    
    public List<Agencija> vratiSveAgencije() {
        return dbb.vratiSveAgencijeDB();
    }

    public Takmicenje vratiTakmicenjePoId(Integer idTakmicenja){
        return dbb.vratiTakmicenjePoIdDB(idTakmicenja);
    }


    
    public boolean ubaciDrzavu(TransferObjekat to) throws Exception{
        KreirajDK kreirajSo = new SOUbaciDrzavu(to);
        return kreirajSo.opsteIzvrsenjeSO();
    }
    
//    public Drzava ubaciDrzavu(Drzava drzava) throws Exception{
//        return dbb.ubaciDrzavuDB(drzava);
//    }
    
    public boolean obrisiDrzavu(TransferObjekat to) throws Exception {
        ObrisiDK obrisiSo = new SOObrisiDrzavu(to);
        return obrisiSo.opsteIzvrsenjeSO();
    }
    
//    public Integer obrisiDrzavu(Integer drzavaId) throws Exception {
//        return dbb.obrisiDrzavuDB(drzavaId);
//    }
       
}
