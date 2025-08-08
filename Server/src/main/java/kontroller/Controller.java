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
import so.VratiDK;
import so.VratiListuDK;
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

    private Controller() {
        odjavaSignal = false;
    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
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

    public Nalog login(Nalog nalog) throws Exception {

        Nalog ulogovaniNalog = dbb.pretraziVeslackiKlubLogin(nalog);

        if (ulogovaniNalog == null) {
            ulogovaniNalog = dbb.pretraziAgencijuLogin(nalog);
        }

        if (ulogovaniNalog == null) {
            throw new Exception("Neuspešna prijava naloga");
        }

        return ulogovaniNalog;

    }

    // VESLACKI KLUB
    public boolean kreirajVeslackiKlub(TransferObjekat to) throws Exception {
        KreirajDK kreirajSo = new SOKreirajKlub(to);
        return kreirajSo.opsteIzvrsenjeSO();
    }

    public boolean promeniVeslackiKlub(TransferObjekat to) throws Exception {
        PromeniDK promeniSo = new SOPromeniKlub(to);
        return promeniSo.opsteIzvrsenjeSO();
    }

    public boolean pretraziVeslackiKlub(TransferObjekat to) {
        NadjiDK nadjiSo = new SOPretraziKlub(to);
        return nadjiSo.opsteIzvrsenjeSO();
    }

    public boolean obrisiVeslackiKlub(TransferObjekat to) throws Exception {
        ObrisiDK obrisiSo = new SOObrisiKlub(to);
        return obrisiSo.opsteIzvrsenjeSO();
    }

    public boolean vratiListuVeslackiKlub(TransferObjekat to, String whereUslov) {
        VratiListuDK vratiListuSo = new VratiListuDK(to, whereUslov);
        return vratiListuSo.opsteIzvrsenjeSO();
    }

    public VeslackiKlub vratiVeslackiKlubPoId(Integer id) throws Exception {
        return dbb.vratiVeslackiKlubPoIdDB(id);
    }

    // VESLAC
    public boolean kreirajVeslac(TransferObjekat to) throws Exception {
        KreirajDK kreirajSo = new KreirajDK(to);
        return kreirajSo.opsteIzvrsenjeSO();
    }

    public boolean azurirajVeslac(TransferObjekat to) throws Exception {
        PromeniDK promeniSo = new SOPromeniVeslaca(to);
        return promeniSo.opsteIzvrsenjeSO();
    }

    public boolean obrisiVeslac(TransferObjekat to) throws Exception {
        ObrisiDK obrisiSo = new SOObrisiVeslaca(to);
        return obrisiSo.opsteIzvrsenjeSO();
    }

    public boolean pretraziVeslac(TransferObjekat to) throws Exception {
        NadjiDK nadjiSo = new SOPretraziVeslaca(to);
        return nadjiSo.opsteIzvrsenjeSO();
    }

    public boolean vratiListuVeslac(TransferObjekat to, String kriterijumVeslac) {
        VratiListuDK vratiSo = new VratiListuDK(to, kriterijumVeslac);
        return vratiSo.opsteIzvrsenjeSO();
    }

    // AGENCIJA
    public Agencija vratiAgencijuPoId(Integer id) {
        return dbb.vratiAgencijuPoId(id);
    }

    public boolean kreirajAgencija(TransferObjekat to) throws Exception {
        KreirajDK kreirajSo = new SOKreirajAgenciju(to);
        return kreirajSo.opsteIzvrsenjeSO();
    }

    public boolean obrisiAgenciju(TransferObjekat to) throws Exception {
        ObrisiDK obrisiSo = new SOObrisiKlub(to);
        return obrisiSo.opsteIzvrsenjeSO();
    }

    public boolean promeniAgencija(TransferObjekat to) throws Exception {
        PromeniDK promeniSo = new SOPromeniAgenciju(to);
        return promeniSo.opsteIzvrsenjeSO();
    }

    public boolean pretraziAgencija(TransferObjekat to) throws Exception {
        NadjiDK nadjiSo = new SOPretraziAgenciju(to);
        return nadjiSo.opsteIzvrsenjeSO();
    }

    public boolean vratiListuAgencija(TransferObjekat to, String kriterijumAgencija) {
        VratiListuDK vratiListuSo = new VratiListuDK(to, kriterijumAgencija);
        return vratiListuSo.opsteIzvrsenjeSO();
    }

    // DRZAVA
    public boolean ubaciDrzava(TransferObjekat to) throws Exception {
        KreirajDK kreirajSo = new SOUbaciDrzavu(to);
        return kreirajSo.opsteIzvrsenjeSO();
    }

    public boolean obrisiDrzava(TransferObjekat to) throws Exception {
        ObrisiDK obrisiSo = new SOObrisiDrzavu(to);
        return obrisiSo.opsteIzvrsenjeSO();
    }

    public boolean vratiListuSviDrzava(TransferObjekat to) {
        VratiListuDK vratiSo = new VratiListuDK(to, "");
        return vratiSo.opsteIzvrsenjeSO();
    }

    public boolean vratiListuDrzava(TransferObjekat to, String kriterijumDrzava) {
        VratiListuDK vratiSo = new VratiListuDK(to, kriterijumDrzava);
        return vratiSo.opsteIzvrsenjeSO();
    }

    // TAKMICENJE
    public boolean ubaciTakmicenje(TransferObjekat to) throws Exception {
        KreirajDK dodajSo = new SOUbaciTakmicenje(to);
        return dodajSo.opsteIzvrsenjeSO();
    }

    public boolean obrisiTakmicenje(TransferObjekat to) throws Exception {
        ObrisiDK obrisiSo = new SOObrisiTakmicenje(to);
        return obrisiSo.opsteIzvrsenjeSO();
    }

    public boolean pretraziTakmicenje(TransferObjekat to) {
        NadjiDK pretraziSo = new SOPretraziTakmicenje(to);
        return pretraziSo.opsteIzvrsenjeSO();
    }

    public boolean vratiListuSviTakmicenje(TransferObjekat to) {
        VratiListuDK vratiSo = new VratiListuDK(to, "");
        return vratiSo.opsteIzvrsenjeSO();
    }
    
    public boolean vratiListuTakmicenje(TransferObjekat to,String kriterijumTakmicenje) {
        VratiListuDK vratiSo = new VratiListuDK(to, kriterijumTakmicenje);
        return vratiSo.opsteIzvrsenjeSO();
    }

    // Ponuda veslaca
    public boolean kreirajPonudaVeslaca(TransferObjekat to) {
        KreirajDK kreirajSo = new SOKreirajPonudu(to);
        return kreirajSo.opsteIzvrsenjeSO();
    }
    
    public boolean obrisiPonudaVeslaca(TransferObjekat to) {
        ObrisiDK obrisiSo = new SOObrisiPonudu(to);
        return obrisiSo.opsteIzvrsenjeSO();
    }
    
    public boolean promeniPonudaVeslaca(TransferObjekat to) {
        PromeniDK promeniSo = new SOPromeniPonudu(to);
        return promeniSo.opsteIzvrsenjeSO();
    }
    
    public boolean pretraziPonudaVeslaca(TransferObjekat to) {
        NadjiDK nadjiSo = new SOPretraziPonudu(to);
        return nadjiSo.opsteIzvrsenjeSO();
    }
    
    public boolean vratiListPonudaVeslaca(TransferObjekat to, String kriterijum) {
        VratiListuDK vratiSo = new VratiListuDK(to, kriterijum);
        return vratiSo.opsteIzvrsenjeSO();
    }
    
    // Klub - Takmicenje
    
    public KlubTakmicenje dodajOsvojenoTakmicenje(KlubTakmicenje klubTakmicenje) throws Exception {
        return dbb.dodajOsvojenoTakmicenjeDB(klubTakmicenje);
    }

    public int[] prebrojOsvojenaTakmicenja(Integer idKluba) {
        return dbb.prebrojOsvojenaTakmicenjaDB(idKluba);
    }

    public Integer obrisiOsvojenoTakmicenje(KlubTakmicenje klubTakmicenje) throws Exception {
        return dbb.obrisiOsvojenoTakmicenjeDB(klubTakmicenje);
    }

    public int vratiPoslednjiIdPonude() {
        return dbb.vratiPoslednjiIdPonudeDB();
    }

    public PonudaVeslaca vratiPonuduPoId(int idPonude) throws Exception {
        return dbb.vratiPonuduPoIdDB(idPonude);
    }

    public List<StavkaPonude> vratiSveStavkePonude(Integer idPonude) {
        return dbb.vratiSveStavkePonudeDB(idPonude);
    }

    public Takmicenje vratiTakmicenjePoId(Integer idTakmicenja) {
        return dbb.vratiTakmicenjePoIdDB(idTakmicenja);
    }

}
