package kontroller;

import so.agencija.SOObrisiAgenciju;
import so.agencija.SOUbaciAgenciju;
import so.agencija.SOPretraziAgenciju;
import so.agencija.SOPrijaviAgenciju;
import so.agencija.SOPromeniAgenciju;
import so.agencija.SOVratiListuAgencija;
import so.drzava.SOObrisiDrzavu;
import so.drzava.SOUbaciDrzavu;
import so.drzava.SOVratiListuDrzava;
import so.ponudaveslaca.SOUbaciPonudu;
import so.ponudaveslaca.SOObrisiPonudu;
import so.ponudaveslaca.SOPretraziPonudu;
import so.ponudaveslaca.SOPromeniPonudu;
import so.ponudaveslaca.SOVratiListuPonuda;
import so.takmicenje.SOObrisiTakmicenje;
import so.takmicenje.SOPretraziTakmicenje;
import so.takmicenje.SOUbaciTakmicenje;
import so.takmicenje.SOVratiListuTakmicenja;
import so.veslac.SOObrisiVeslaca;
import so.veslac.SOPretraziVeslaca;
import so.veslac.SOPromeniVeslaca;
import so.veslac.SOUbaciVeslaca;
import so.veslac.SOVratiListuVeslaci;
import so.veslackiklub.SOUbaciKlub;
import so.veslackiklub.SOObrisiKlub;
import so.veslackiklub.SOPretraziKlub;
import so.veslackiklub.SOPrijaviKlub;
import so.veslackiklub.SOPromeniKlub;
import so.veslackiklub.SOVratiListuKlub;
import transfer.TransferObjekat;

public class Controller {

    private static Controller kontroler;

    private boolean odjavaSignal;

    private Controller() {
        odjavaSignal = false;
    }

    public static Controller getInstance() {
        if (kontroler == null) {
            kontroler = new Controller();
        }
        return kontroler;
    }

    public boolean isOdjavaSignal() {
        return odjavaSignal;
    }

    public void setOdjavaSignal(boolean odjavaSignal) {
        this.odjavaSignal = odjavaSignal;
    }

    // VESLACKI KLUB
    public boolean prijaviVeslackiKlub(TransferObjekat to) throws Exception {
        SOPrijaviKlub prijaviSo = new SOPrijaviKlub(to);
        return prijaviSo.opsteIzvrsenjeSO();
    }

    public boolean ubaciVeslackiKlub(TransferObjekat to) throws Exception {
        SOUbaciKlub kreirajSo = new SOUbaciKlub(to);
        return kreirajSo.opsteIzvrsenjeSO();
    }

    public boolean promeniVeslackiKlub(TransferObjekat to) throws Exception {
        SOPromeniKlub promeniSo = new SOPromeniKlub(to);
        return promeniSo.opsteIzvrsenjeSO();
    }

    public boolean pretraziVeslackiKlub(TransferObjekat to) {
        SOPretraziKlub nadjiSo = new SOPretraziKlub(to);
        return nadjiSo.opsteIzvrsenjeSO();
    }

    public boolean obrisiVeslackiKlub(TransferObjekat to) throws Exception {
        SOObrisiKlub obrisiSo = new SOObrisiKlub(to);
        return obrisiSo.opsteIzvrsenjeSO();
    }

    public boolean vratiListuKlub(TransferObjekat to) {
        SOVratiListuKlub vratiListuSo = new SOVratiListuKlub(to);
        return vratiListuSo.opsteIzvrsenjeSO();
    }

    public boolean vratiListuSviKlub(TransferObjekat to) {
        SOVratiListuKlub vratiListuDK = new SOVratiListuKlub(to);
        return vratiListuDK.opsteIzvrsenjeSO();
    }

    // VESLAC
    public boolean ubaciVeslac(TransferObjekat to) throws Exception {
        SOUbaciVeslaca kreirajSo = new SOUbaciVeslaca(to);
        return kreirajSo.opsteIzvrsenjeSO();
    }

    public boolean promeniVeslaca(TransferObjekat to) throws Exception {
        SOPromeniVeslaca promeniSo = new SOPromeniVeslaca(to);
        return promeniSo.opsteIzvrsenjeSO();
    }

    public boolean obrisiVeslac(TransferObjekat to) throws Exception {
        SOObrisiVeslaca obrisiSo = new SOObrisiVeslaca(to);
        return obrisiSo.opsteIzvrsenjeSO();
    }

    public boolean pretraziVeslac(TransferObjekat to) throws Exception {
        SOPretraziVeslaca nadjiSo = new SOPretraziVeslaca(to);
        return nadjiSo.opsteIzvrsenjeSO();
    }

    public boolean vratiListuSviVeslac(TransferObjekat to) {
        SOVratiListuVeslaci vratiSo = new SOVratiListuVeslaci(to,"");
        return vratiSo.opsteIzvrsenjeSO();
    }

    public boolean vratiListuVeslac(TransferObjekat to, String kriterijumVeslac) {
        SOVratiListuVeslaci vratiSo = new SOVratiListuVeslaci(to, kriterijumVeslac);
        return vratiSo.opsteIzvrsenjeSO();
    }

    // AGENCIJA
    public boolean prijaviAgencija(TransferObjekat to) {
        SOPrijaviAgenciju prijavaSo = new SOPrijaviAgenciju(to);
        return prijavaSo.opsteIzvrsenjeSO();
    }

    public boolean UbaciAgenciju(TransferObjekat to) throws Exception {
        SOUbaciAgenciju kreirajSo = new SOUbaciAgenciju(to);
        return kreirajSo.opsteIzvrsenjeSO();
    }

    public boolean obrisiAgenciju(TransferObjekat to) throws Exception {
        SOObrisiAgenciju obrisiSo = new SOObrisiAgenciju(to);
        return obrisiSo.opsteIzvrsenjeSO();
    }

    public boolean promeniAgencija(TransferObjekat to) throws Exception {
        SOPromeniAgenciju promeniSo = new SOPromeniAgenciju(to);
        return promeniSo.opsteIzvrsenjeSO();
    }

    public boolean pretraziAgencija(TransferObjekat to) throws Exception {
        SOPretraziAgenciju nadjiSo = new SOPretraziAgenciju(to);
        return nadjiSo.opsteIzvrsenjeSO();
    }

    public boolean vratiListuSviAgencija(TransferObjekat to) {
        SOVratiListuAgencija vratiListuDK = new SOVratiListuAgencija(to, "");
        return vratiListuDK.opsteIzvrsenjeSO();
    }

    public boolean vratiListuAgencija(TransferObjekat to, String kriterijumAgencija) {
        SOVratiListuAgencija vratiListuSo = new SOVratiListuAgencija(to, kriterijumAgencija);
        return vratiListuSo.opsteIzvrsenjeSO();
    }

    // DRZAVA
    public boolean ubaciDrzava(TransferObjekat to) throws Exception {
        SOUbaciDrzavu kreirajSo = new SOUbaciDrzavu(to);
        return kreirajSo.opsteIzvrsenjeSO();
    }

    public boolean obrisiDrzava(TransferObjekat to) throws Exception {
        SOObrisiDrzavu obrisiSo = new SOObrisiDrzavu(to);
        return obrisiSo.opsteIzvrsenjeSO();
    }

    public boolean vratiListuSviDrzava(TransferObjekat to) {
        SOVratiListuDrzava vratiSo = new SOVratiListuDrzava(to, "");
        return vratiSo.opsteIzvrsenjeSO();
    }

    public boolean vratiListuDrzava(TransferObjekat to, String kriterijumDrzava) {
        SOVratiListuDrzava vratiSo = new SOVratiListuDrzava(to, kriterijumDrzava);
        return vratiSo.opsteIzvrsenjeSO();
    }

    // TAKMICENJE
    public boolean ubaciTakmicenje(TransferObjekat to) throws Exception {
        SOUbaciTakmicenje dodajSo = new SOUbaciTakmicenje(to);
        return dodajSo.opsteIzvrsenjeSO();
    }

    public boolean obrisiTakmicenje(TransferObjekat to) throws Exception {
        SOObrisiTakmicenje obrisiSo = new SOObrisiTakmicenje(to);
        return obrisiSo.opsteIzvrsenjeSO();
    }

    public boolean pretraziTakmicenje(TransferObjekat to) {
        SOPretraziTakmicenje pretraziSo = new SOPretraziTakmicenje(to);
        return pretraziSo.opsteIzvrsenjeSO();
    }

    public boolean vratiListuSviTakmicenje(TransferObjekat to) {
        SOVratiListuTakmicenja vratiSo = new SOVratiListuTakmicenja(to, "");
        return vratiSo.opsteIzvrsenjeSO();
    }

    public boolean vratiListuTakmicenje(TransferObjekat to, String kriterijumTakmicenje) {
        SOVratiListuTakmicenja vratiSo = new SOVratiListuTakmicenja(to, kriterijumTakmicenje);
        return vratiSo.opsteIzvrsenjeSO();
    }

    // Ponuda veslaca
    public boolean ubaciPonudaVeslaca(TransferObjekat to) {
        SOUbaciPonudu kreirajSo = new SOUbaciPonudu(to);
        return kreirajSo.opsteIzvrsenjeSO();
    }

    public boolean obrisiPonudaVeslaca(TransferObjekat to) {
        SOObrisiPonudu obrisiSo = new SOObrisiPonudu(to);
        return obrisiSo.opsteIzvrsenjeSO();
    }

    public boolean promeniPonudaVeslaca(TransferObjekat to) {
        SOPromeniPonudu promeniSo = new SOPromeniPonudu(to);
        return promeniSo.opsteIzvrsenjeSO();
    }

    public boolean pretraziPonudaVeslaca(TransferObjekat to) {
        SOPretraziPonudu nadjiSo = new SOPretraziPonudu(to);
        return nadjiSo.opsteIzvrsenjeSO();
    }

    public boolean vratiListuSviPonudaVeslaca(TransferObjekat to) {
        SOVratiListuPonuda vratiSo = new SOVratiListuPonuda(to, "");
        return vratiSo.opsteIzvrsenjeSO();
    }

    public boolean vratiListuPonudaVeslaca(TransferObjekat to, String kriterijum) {
        SOVratiListuPonuda vratiSo = new SOVratiListuPonuda(to, kriterijum);
        return vratiSo.opsteIzvrsenjeSO();
    }

    // Klub - Takmicenje   
//    public boolean kreirajKlubTakmicenje(TransferObjekat to) {
//        SOUbaciTakmicenje kreirajSo = new SoUbv(to);
//        return kreirajSo.opsteIzvrsenjeSO();
//    }

//    public boolean obrisiKlubTakmicenje(TransferObjekat to) throws Exception {
//        ObrisiDK obrisiSo = new ObrisiDK();
//        return obrisiSo.opsteIzvrsenjeSO();
//    }

//    public int[] prebrojOsvojenaTakmicenja(Integer idKluba) {
//        return dbb.prebrojOsvojenaTakmicenjaDB(idKluba);
//    }
//
//    public int vratiPoslednjiIdPonude() {
//        return dbb.vratiPoslednjiIdPonudeDB();
//    }
}
