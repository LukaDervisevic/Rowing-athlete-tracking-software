package kontroller;

import model.Nalog;
import so.KreirajDK;
import so.NadjiDK;
import so.ObrisiDK;
import so.PrijaviDK;
import so.PromeniDK;
import so.VratiListuDK;
import so.agencija.SOKreirajAgenciju;
import so.agencija.SOPretraziAgenciju;
import so.agencija.SOPrijaviAgenciju;
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
import so.veslackiklub.SOPrijaviKlub;
import so.veslackiklub.SOPromeniKlub;
import transfer.TransferObjekat;

public class Controller {

    private static Controller instance;

    private Nalog ulogovaniNalog;

    private boolean odjavaSignal;

    private Controller() {
        odjavaSignal = false;
    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public Nalog getUlogovaniNalog() {
        return ulogovaniNalog;
    }

    public void setUlogovaniNalog(Nalog ulogovaniNalog) {
        this.ulogovaniNalog = ulogovaniNalog;
    }

    public boolean isOdjavaSignal() {
        return odjavaSignal;
    }

    public void setOdjavaSignal(boolean odjavaSignal) {
        this.odjavaSignal = odjavaSignal;
    }

    // VESLACKI KLUB
    public boolean prijaviVeslackiKlub(TransferObjekat to) throws Exception{
        PrijaviDK prijaviSo = new SOPrijaviKlub(to);
        return prijaviSo.opsteIzvrsenjeSO();
    }
    
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
    
    public boolean vratiListuSviVeslackiKlub(TransferObjekat to) {
        VratiListuDK vratiListuDK = new VratiListuDK(to, "");
        return vratiListuDK.opsteIzvrsenjeSO();
    }

    // VESLAC
    public boolean kreirajVeslac(TransferObjekat to) throws Exception {
        KreirajDK kreirajSo = new KreirajDK(to);
        return kreirajSo.opsteIzvrsenjeSO();
    }

    public boolean promeniVeslac(TransferObjekat to) throws Exception {
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
    
    public boolean vratiListuSviVeslac(TransferObjekat to){
        VratiListuDK vratiSo = new VratiListuDK(to, "");
        return vratiSo.opsteIzvrsenjeSO();
    }

    public boolean vratiListuVeslac(TransferObjekat to, String kriterijumVeslac) {
        VratiListuDK vratiSo = new VratiListuDK(to, kriterijumVeslac);
        return vratiSo.opsteIzvrsenjeSO();
    }

    // AGENCIJA
    public boolean prijaviAgencija(TransferObjekat to) {
        PrijaviDK prijavaSo = new SOPrijaviAgenciju(to);
        return prijavaSo.opsteIzvrsenjeSO();
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
    
    public boolean vratiListuSviAgencija(TransferObjekat to) {
        VratiListuDK vratiListuDK = new VratiListuDK(to, "");
        return vratiListuDK.opsteIzvrsenjeSO();
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
    
    public boolean vratiListuSviPonudaVeslaca(TransferObjekat to) {
        VratiListuDK vratiSo = new VratiListuDK(to,"");
        return vratiSo.opsteIzvrsenjeSO();
    }
    
    public boolean vratiListuPonudaVeslaca(TransferObjekat to, String kriterijum) {
        VratiListuDK vratiSo = new VratiListuDK(to, kriterijum);
        return vratiSo.opsteIzvrsenjeSO();
    }
    
    // Klub - Takmicenje   
    public boolean kreirajKlubTakmicenje(TransferObjekat to){
        KreirajDK kreirajSo = new KreirajDK(to);
        return kreirajSo.opsteIzvrsenjeSO();
    }
    
    public boolean obrisiKlubTakmicenje(TransferObjekat to) throws Exception {
        ObrisiDK obrisiSo = new ObrisiDK();
        return obrisiSo.opsteIzvrsenjeSO();
    }

//    public int[] prebrojOsvojenaTakmicenja(Integer idKluba) {
//        return dbb.prebrojOsvojenaTakmicenjaDB(idKluba);
//    }
//
//    public int vratiPoslednjiIdPonude() {
//        return dbb.vratiPoslednjiIdPonudeDB();
//    }

    public boolean vratiSveStavkePonude(TransferObjekat to) {
        VratiListuDK vratiSo = new VratiListuDK(to, "");
        return vratiSo.opsteIzvrsenjeSO();
    }

}
