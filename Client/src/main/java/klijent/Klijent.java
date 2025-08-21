package klijent;

import java.io.IOException;
import java.net.Socket;
import java.time.LocalDateTime;
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
import operacije.Odgovor;
import operacije.Operacija;
import operacije.Posiljalac;
import operacije.Primalac;
import operacije.StatusPoruke;
import operacije.Zahtev;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import transfer.TransferObjekat;

/**
 *
 * @author luka
 */
public class Klijent {

    private static Klijent instance;

    private Nalog ulogovaniNalog;

    private boolean odjavaSignal;

    private String uuid;

    private LocalDateTime vremeKreiranjaNaloga;

    private Primalac primalac;

    private Posiljalac posiljalac;

    private Socket soket;

    private static final Logger logger = LogManager.getRootLogger();

    private Klijent() {
        try {
            soket = new Socket("localhost", 9000);
            primalac = new Primalac(soket);
            posiljalac = new Posiljalac(soket);

        } catch (IOException ex) {
            logger.error(ex.getMessage());
        }
    }

    public static Klijent getInstance() {
        if (instance == null) {
            instance = new Klijent();
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

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public LocalDateTime getVremeKreiranjaNaloga() {
        return vremeKreiranjaNaloga;
    }

    public void setVremeKreiranjaNaloga(LocalDateTime vremeKreiranjaNaloga) {
        this.vremeKreiranjaNaloga = vremeKreiranjaNaloga;
    }

//    public Nalog login(Nalog nalog) throws Exception {
//        TransferObjekat transferObj = new TransferObjekat();
//        transferObj.setOdo(nalog);
//        transferObj.setNazivSo("prijava");
//        
//        Zahtev zahtev = new Zahtev(Operacija.PRIJAVA, transferObj);
//        posiljalac.posaljiPoruku(zahtev);
//
//        Odgovor odgovor = (Odgovor) primalac.primiPoruku();
//
//        if (odgovor.getStatus().equals(StatusPoruke.GRESKA)) {
//            throw new Exception((Throwable) odgovor.getParametar());
//        } else {
//            return (Nalog) odgovor.getParametar();
//        }
//
//    }

    public VeslackiKlub prijaviVeslackiKlub(VeslackiKlub veslackiKlub) throws Exception {
        TransferObjekat transferObj = new TransferObjekat();
        transferObj.setOdo(veslackiKlub);
        transferObj.setNazivSo("prijaviVeslackiKlub");
        Zahtev zahtev = new Zahtev(Operacija.PRIJAVA, transferObj);
        posiljalac.posaljiPoruku(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primiPoruku();

        if (odgovor.getStatus().equals(StatusPoruke.GRESKA)) {
            throw new Exception((Throwable) odgovor.getParametar());
        } else {
            transferObj = (TransferObjekat) odgovor.getParametar();
            return (VeslackiKlub) transferObj.getOdo();
        }
    }

    public VeslackiKlub kreirajVeslackiKlub(VeslackiKlub veslackiKlub) throws Exception {
        TransferObjekat transferObj = new TransferObjekat();
        transferObj.setOdo(veslackiKlub);
        transferObj.setNazivSo("kreirajVeslackiKlub");

        Zahtev zahtev = new Zahtev(Operacija.KREIRANJE_KLUB, transferObj);
        posiljalac.posaljiPoruku(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primiPoruku();

        if (odgovor.getStatus().equals(StatusPoruke.GRESKA)) {
            throw new Exception((Throwable) odgovor.getParametar());
        } else {
            transferObj = (TransferObjekat) odgovor.getParametar();
            return (VeslackiKlub) transferObj.getOdo();
        }
    }

    public Agencija kreirajAgenciju(Agencija agencija) throws Exception {
        TransferObjekat transferObj = new TransferObjekat();
        transferObj.setOdo(agencija);
        transferObj.setNazivSo("KreirajAgencija");
        
        Zahtev zahtev = new Zahtev(Operacija.KREIRANJE_AGENCIJA, transferObj);
        posiljalac.posaljiPoruku(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primiPoruku();

        if (odgovor.getStatus().equals(StatusPoruke.GRESKA)) {
            throw new Exception((Throwable) odgovor.getParametar());
        } else {
            transferObj = (TransferObjekat) odgovor.getParametar();
            return (Agencija) transferObj.getOdo();
        }
    }

    public List<Drzava> vratiSveDrzave() throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.VRATI_SVE_DRZAVE, null);
        posiljalac.posaljiPoruku(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primiPoruku();

        if (odgovor.getStatus().equals(StatusPoruke.GRESKA)) {
            throw new Exception((Throwable) odgovor.getParametar());
        } else {
            return (List<Drzava>) odgovor.getParametar();
        }
    }

    public Integer obrisiVeslackiKlub(Integer idKluba) throws Exception {
        TransferObjekat transferObj = new TransferObjekat();
        transferObj.setOdo(new VeslackiKlub(idKluba, null, null, null, null, null, null));
        transferObj.setNazivSo("obrisiVeslackiKlub");
        
        Zahtev zahtev = new Zahtev(Operacija.BRISANJE_KLUB, idKluba);
        posiljalac.posaljiPoruku(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primiPoruku();

        if (odgovor.getStatus().equals(StatusPoruke.GRESKA)) {
            throw new Exception((Throwable) odgovor.getParametar());
        } else {
            transferObj = (TransferObjekat) odgovor.getParametar();
            return ((VeslackiKlub) transferObj.getOdo()).getId();
        }
    }

    public VeslackiKlub azuirirajVeslackiKlub(VeslackiKlub veslackiKlub) throws Exception {
        TransferObjekat transferObj = new TransferObjekat();
        transferObj.setOdo(veslackiKlub);
        transferObj.setNazivSo("azurirajVeslackiKlub");
        
        Zahtev zahtev = new Zahtev(Operacija.PROMENA_KLUB, veslackiKlub);
        posiljalac.posaljiPoruku(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primiPoruku();

        if (odgovor.getStatus().equals(StatusPoruke.GRESKA)) {
            throw new Exception((Throwable) odgovor.getParametar());
        } else {
            transferObj = (TransferObjekat) odgovor.getParametar();
            return (VeslackiKlub) transferObj.getOdo();
        }
    }

    public List<Veslac> vratiSveVeslace(Integer idKluba) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.VRATI_SVE_VESLACE, idKluba);
        posiljalac.posaljiPoruku(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primiPoruku();

        if (odgovor.getStatus().equals(StatusPoruke.GRESKA)) {
            throw new Exception((Throwable) odgovor.getParametar());
        } else {
            return (List<Veslac>) odgovor.getParametar();
        }
    }

    public List<KlubTakmicenje> vratiTakmicenjaKluba(Integer idKluba) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.VRATI_TAKMICENJA_KLUBA, idKluba);
        posiljalac.posaljiPoruku(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primiPoruku();

        if (odgovor.getStatus().equals(StatusPoruke.GRESKA)) {
            throw new Exception((Throwable) odgovor.getParametar());
        } else {
            return (List<KlubTakmicenje>) odgovor.getParametar();
        }
    }

    public List<PonudaVeslaca> vratiSvePonudeKluba(Integer idKluba) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.VRATI_SVE_PONUDE_KLUBA, idKluba);
        posiljalac.posaljiPoruku(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primiPoruku();

        if (odgovor.getStatus().equals(StatusPoruke.GRESKA)) {
            throw new Exception((Throwable) odgovor.getParametar());
        } else {
            return (List<PonudaVeslaca>) odgovor.getParametar();
        }
    }

    public List<Agencija> vratiSveAgencije() throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.VRATI_SVE_AGENCIJE, null);
        posiljalac.posaljiPoruku(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primiPoruku();

        if (odgovor.getStatus().equals(StatusPoruke.GRESKA)) {
            throw new Exception((Throwable) odgovor.getParametar());
        } else {
            return (List<Agencija>) odgovor.getParametar();
        }
    }

    public List<PonudaVeslaca> pretraziPonudu(PonudaVeslaca ponuda) throws Exception {
        TransferObjekat transferObj = new TransferObjekat();
        transferObj.setOdo(ponuda);
        transferObj.setNazivSo("pretraziPonudu");
        
        Zahtev zahtev = new Zahtev(Operacija.PRETRAZIVANJE_PONUDE, ponuda);
        posiljalac.posaljiPoruku(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primiPoruku();

        if (odgovor.getStatus().equals(StatusPoruke.GRESKA)) {
            throw new Exception((Throwable) odgovor.getParametar());
        } else {
            transferObj = (TransferObjekat) odgovor.getParametar();
            return (List<PonudaVeslaca>) transferObj.getOdo();
        }
    }

    public List<Veslac> pretraziVeslaca(Veslac veslac) throws Exception {
        TransferObjekat transferObj = new TransferObjekat();
        transferObj.setOdo(veslac);
        transferObj.setNazivSo("pretraziVeslaca");
        
        Zahtev zahtev = new Zahtev(Operacija.PRETRAZIVANJE_VESLAC, veslac);
        posiljalac.posaljiPoruku(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primiPoruku();

        if (odgovor.getStatus().equals(StatusPoruke.GRESKA)) {
            throw new Exception((Throwable) odgovor.getParametar());
        } else {
            transferObj = (TransferObjekat) odgovor.getParametar();
            return (List<Veslac>) transferObj.getOdo();
        }
    }

    public List<Takmicenje> pretraziTakmicenja(String upit) throws Exception {
        
        Zahtev zahtev = new Zahtev(Operacija.PRETRAZIVANJE_TAKMICENJE, upit);
        posiljalac.posaljiPoruku(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primiPoruku();

        if (odgovor.getStatus().equals(StatusPoruke.GRESKA)) {
            throw new Exception((Throwable) odgovor.getParametar());
        } else {
            
            return (List<Takmicenje>) odgovor.getParametar();
        }
    }

    public List<Takmicenje> vratiSvaTakmicenja() throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.VRATI_SVA_TAKMICENJA, null);
        posiljalac.posaljiPoruku(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primiPoruku();

        if (odgovor.getStatus().equals(StatusPoruke.GRESKA)) {
            throw new Exception((Throwable) odgovor.getParametar());
        } else {
            return (List<Takmicenje>) odgovor.getParametar();
        }
    }

    public Veslac kreirajVeslaca(Veslac veslac) throws Exception {
        TransferObjekat transferObj = new TransferObjekat();
        transferObj.setOdo(veslac);
        transferObj.setNazivSo("kreirajVeslaca");
        
        Zahtev zahtev = new Zahtev(Operacija.KREIRANJE_VESLAC, veslac);
        posiljalac.posaljiPoruku(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primiPoruku();

        if (odgovor.getStatus().equals(StatusPoruke.GRESKA)) {
            throw new Exception((Throwable) odgovor.getParametar());
        } else {
            transferObj = (TransferObjekat) odgovor.getParametar();
            return (Veslac) transferObj.getOdo();
        }
    }

    public Integer obrisiVeslaca(Integer id) throws Exception {
        TransferObjekat transferObj = new TransferObjekat();
        transferObj.setOdo(new Veslac(id, null, null, 0, 0, KategorijaVeslaca.KADET, 0, null, null));
        transferObj.setNazivSo("kreirajVeslaca");
        
        Zahtev zahtev = new Zahtev(Operacija.BRISANJE_VESLAC, id);
        posiljalac.posaljiPoruku(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primiPoruku();

        if (odgovor.getStatus().equals(StatusPoruke.GRESKA)) {
            throw new Exception((Throwable) odgovor.getParametar());
        } else {
            transferObj = (TransferObjekat) odgovor.getParametar();
            return ((Veslac) transferObj.getOdo()).getIdVeslaca();
        }
    }

    public Takmicenje dodajTakmicenje(Takmicenje takmicenje) throws Exception {
        TransferObjekat transferObj = new TransferObjekat();
        transferObj.setOdo(takmicenje);
        transferObj.setNazivSo("ubaciTakmicenje");
        
        Zahtev zahtev = new Zahtev(Operacija.UBACIVANJE_TAKMICENJE, takmicenje);
        posiljalac.posaljiPoruku(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primiPoruku();

        if (odgovor.getStatus().equals(StatusPoruke.GRESKA)) {
            throw new Exception((Throwable) odgovor.getParametar());
        } else {
            transferObj = (TransferObjekat) odgovor.getParametar();
            return (Takmicenje) transferObj.getOdo();
        }
    }

    public Integer obrisiTakmicenje(Integer idTakmicenja) throws Exception {
        TransferObjekat transferObj = new TransferObjekat();
        transferObj.setOdo(new Takmicenje(idTakmicenja, null, null, null, null));
        transferObj.setNazivSo("kreirajVeslaca");
        
        Zahtev zahtev = new Zahtev(Operacija.BRISANJE_TAKMICENJE, idTakmicenja);
        posiljalac.posaljiPoruku(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primiPoruku();

        if (odgovor.getStatus().equals(StatusPoruke.GRESKA)) {
            throw new Exception((Throwable) odgovor.getParametar());
        } else {
            transferObj = (TransferObjekat) odgovor.getParametar();
            return ((Takmicenje) transferObj.getOdo()).getId();
        }
    }

    // Predstavlja problem
    public KlubTakmicenje dodajOsvojenoTakmicenje(int mesto, int idTakmicenja, int idKluba) throws Exception {
        VeslackiKlub klub = vratiVeslackiKlubPoId(idKluba);
        Takmicenje takmicenje = vratiTakmicenjePoId(idTakmicenja);

        KlubTakmicenje klubTakmicenje = new KlubTakmicenje(mesto, klub, takmicenje);

        Zahtev zahtev = new Zahtev(Operacija.OSVOJI_TAKMICENJE, klubTakmicenje);
        posiljalac.posaljiPoruku(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primiPoruku();

        if (odgovor.getStatus().equals(StatusPoruke.GRESKA)) {
            throw new Exception((Throwable) odgovor.getParametar());
        } else {
            return (KlubTakmicenje) odgovor.getParametar();
        }

    }

    // Predstavlja problem
    public Integer obrisiOsvojenoTakmicenje(int idKlub, int idTakmicenja, int mesto) throws Exception {
        
        Takmicenje takmicenje = vratiTakmicenjePoId(idTakmicenja);
        VeslackiKlub veslackiKlub = vratiVeslackiKlubPoId(idKlub);

        KlubTakmicenje klubTakmicenje = new KlubTakmicenje(mesto, veslackiKlub, takmicenje);

        Zahtev zahtev = new Zahtev(Operacija.BRISANJE_OSVOJENO_TAKMICENJE, klubTakmicenje);
        posiljalac.posaljiPoruku(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primiPoruku();

        if (odgovor.getStatus().equals(StatusPoruke.GRESKA)) {
            throw new Exception((Throwable) odgovor.getParametar());
        } else {
            return (Integer) odgovor.getParametar();
        }

    }

    public Integer obrisiPonudu(Integer idPonude) throws Exception {
        TransferObjekat transferObj = new TransferObjekat();
        transferObj.setOdo(new PonudaVeslaca(idPonude, null, 0, 0, 0, 0, null, 0, 0));
        transferObj.setNazivSo("obrisiPonudu");
        
        Zahtev zahtev = new Zahtev(Operacija.BRISANJE_PONUDE, idPonude);
        posiljalac.posaljiPoruku(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primiPoruku();

        if (odgovor.getStatus().equals(StatusPoruke.GRESKA)) {
            throw new Exception((Throwable) odgovor.getParametar());
        } else {
            transferObj = (TransferObjekat) odgovor.getParametar();
            return ((PonudaVeslaca) transferObj.getOdo()).getId();        
        }
    }

    public PonudaVeslaca promeniPonudu(PonudaVeslaca ponudaVeslaca) throws Exception {
        TransferObjekat transferObj = new TransferObjekat();
        transferObj.setOdo(ponudaVeslaca);
        transferObj.setNazivSo("promeniPonudu");
        
        Zahtev zahtev = new Zahtev(Operacija.PROMENA_PONUDE, ponudaVeslaca);
        posiljalac.posaljiPoruku(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primiPoruku();

        if (odgovor.getStatus().equals(StatusPoruke.GRESKA)) {
            throw new Exception((Throwable) odgovor.getParametar());
        } else {
            transferObj = (TransferObjekat) odgovor.getParametar();
            return (PonudaVeslaca) transferObj.getOdo();
        }

    }

    public Veslac vratiVeslacaPoId(int idVeslaca) throws Exception {
        
        
        Zahtev zahtev = new Zahtev(Operacija.VRATI_VESLACA_PO_ID, idVeslaca);
        posiljalac.posaljiPoruku(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primiPoruku();

        if (odgovor.getStatus().equals(StatusPoruke.GRESKA)) {
            throw new Exception((Throwable) odgovor.getParametar());
        } else {
            return (Veslac) odgovor.getParametar();
        }
    }

    // Predstavlja problem
    public PonudaVeslaca kreirajPonuduVeslaca(int idAgencije, int idKluba, List<StavkaPonude> stavkePonude) throws Exception {
        PonudaVeslaca ponuda = new PonudaVeslaca(0, null, 0, 0, 0, 0, stavkePonude, idKluba, idAgencije);
        TransferObjekat transferObj = new TransferObjekat();
        transferObj.setOdo(ponuda);
        transferObj.setNazivSo("kreirajPonudu");

        Zahtev zahtev = new Zahtev(Operacija.KREIRANJE_PONUDE, ponuda);
        posiljalac.posaljiPoruku(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primiPoruku();

        if (odgovor.getStatus().equals(StatusPoruke.GRESKA)) {
            throw new Exception((Throwable) odgovor.getParametar());
        } else {
            transferObj = (TransferObjekat) odgovor.getParametar();
            return (PonudaVeslaca) transferObj.getOdo();
        }
    }

    // Predstavlja problem
    public int[] prebrojOsvojenaTakmicenja(int idKluba) throws Exception {

        Zahtev zahtev = new Zahtev(Operacija.PREBROJ_OSVOJENA_TAKMICENJA, (Integer) idKluba);
        posiljalac.posaljiPoruku(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primiPoruku();

        if (odgovor.getStatus().equals(StatusPoruke.GRESKA)) {
            throw new Exception((Throwable) odgovor.getParametar());
        } else {
            return (int[]) odgovor.getParametar();
        }
    }

    public Integer vratiPoslednjiIdPonude() throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.VRATI_POSLEDNJI_ID_PONUDE, null);
        posiljalac.posaljiPoruku(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primiPoruku();

        if (odgovor.getStatus().equals(StatusPoruke.GRESKA)) {
            throw new Exception((Throwable) odgovor.getParametar());
        } else {
            return (Integer) odgovor.getParametar();
        }
    }

    public List<PonudaVeslaca> vratiSvePonudeAgencije(Integer idAgencije) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.VRATI_SVE_PONUDE_AGENCIJE, idAgencije);
        posiljalac.posaljiPoruku(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primiPoruku();

        if (odgovor.getStatus().equals(StatusPoruke.GRESKA)) {
            throw new Exception((Throwable) odgovor.getParametar());
        } else {
            return (List<PonudaVeslaca>) odgovor.getParametar();
        }
    }

    public List<VeslackiKlub> vratiSveKlubove() throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.VRATI_SVE_KLUBOVE, null);
        posiljalac.posaljiPoruku(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primiPoruku();

        if (odgovor.getStatus().equals(StatusPoruke.GRESKA)) {
            throw new Exception((Throwable) odgovor.getParametar());
        } else {
            return (List<VeslackiKlub>) odgovor.getParametar();
        }

    }

    public List<StavkaPonude> vratiSveStavkePonude(Integer idPonude) throws Exception {

        Zahtev zahtev = new Zahtev(Operacija.VRATI_SVE_STAVKE_PONUDE, idPonude);
        posiljalac.posaljiPoruku(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primiPoruku();

        if (odgovor.getStatus().equals(StatusPoruke.GRESKA)) {
            throw new Exception((Throwable) odgovor.getParametar());
        } else {
            return (List<StavkaPonude>) odgovor.getParametar();
        }

    }

    public List<VeslackiKlub> pretraziKlub(String upitZaPretragu) throws Exception {
//        TransferObjekat transferObj = new TransferObjekat();
//        transferObj.setOdo(ponuda);
//        transferObj.setNazivSo("kreirajPonudu");
        
        Zahtev zahtev = new Zahtev(Operacija.PRETRAZIVANJE_KLUB, upitZaPretragu);
        posiljalac.posaljiPoruku(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primiPoruku();

        if (odgovor.getStatus().equals(StatusPoruke.GRESKA)) {
            throw new Exception((Throwable) odgovor.getParametar());
        } else {
            return (List<VeslackiKlub>) odgovor.getParametar();
        }
    }

    public Veslac azurirajVeslaca(Veslac veslac) throws Exception {
        TransferObjekat transferObj = new TransferObjekat();
        transferObj.setOdo(veslac);
        transferObj.setNazivSo("kreirajVeslaca");
        
        Zahtev zahtev = new Zahtev(Operacija.PROMENA_VESLAC, veslac);
        posiljalac.posaljiPoruku(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primiPoruku();

        if (odgovor.getStatus().equals(StatusPoruke.GRESKA)) {
            throw new Exception((Throwable) odgovor.getParametar());
        } else {
            transferObj = (TransferObjekat) odgovor.getParametar();
            return (Veslac) transferObj.getOdo();
        }
    }

    public Agencija azurirajAgenciju(Agencija agencija) throws Exception {
        TransferObjekat transferObj = new TransferObjekat();
        transferObj.setOdo(agencija);
        transferObj.setNazivSo("azurirajAgenciju");
        
        Zahtev zahtev = new Zahtev(Operacija.PROMENA_AGENCIJA, agencija);
        posiljalac.posaljiPoruku(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primiPoruku();

        if (odgovor.getStatus().equals(StatusPoruke.GRESKA)) {
            throw new Exception((Throwable) odgovor.getParametar());
        } else {
            transferObj = (TransferObjekat) odgovor.getParametar();
            return (Agencija) transferObj.getOdo();
        }
    }

    public Integer obrisiAgenciju(Integer id) throws Exception {
        TransferObjekat transferObj = new TransferObjekat();
        transferObj.setOdo(new Agencija(id, null, null, null,null, null, null));
        transferObj.setNazivSo("azurirajAgenciju");
        
        Zahtev zahtev = new Zahtev(Operacija.BRISANJE_AGENCIJA, id);
        posiljalac.posaljiPoruku(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primiPoruku();

        if (odgovor.getStatus().equals(StatusPoruke.GRESKA)) {
            throw new Exception((Throwable) odgovor.getParametar());
        } else {
            transferObj = (TransferObjekat) odgovor.getParametar();
            return ((Agencija) transferObj.getOdo()).getId();
        }
    }

    private Takmicenje vratiTakmicenjePoId(Integer idTakmicenja) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.VRATI_TAKMICENJE_PO_ID, idTakmicenja);
        posiljalac.posaljiPoruku(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primiPoruku();

        if (odgovor.getStatus().equals(StatusPoruke.GRESKA)) {
            throw new Exception((Throwable) odgovor.getParametar());
        } else {
            return (Takmicenje) odgovor.getParametar();
        }
    }

    private VeslackiKlub vratiVeslackiKlubPoId(Integer idKluba) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.VRATI_KLUB_PO_ID, idKluba);
        posiljalac.posaljiPoruku(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primiPoruku();

        if (odgovor.getStatus().equals(StatusPoruke.GRESKA)) {
            throw new Exception((Throwable) odgovor.getParametar());
        } else {
            return (VeslackiKlub) odgovor.getParametar();
        }
    }

    public Agencija prijaviAgencija(Agencija agencija) throws Exception {
        TransferObjekat transferObj = new TransferObjekat();
        transferObj.setOdo(agencija);
        transferObj.setNazivSo("prijaviAgencija");
        Zahtev zahtev = new Zahtev(Operacija.PRIJAVA, agencija);
        posiljalac.posaljiPoruku(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primiPoruku();

        if (odgovor.getStatus().equals(StatusPoruke.GRESKA)) {
            throw new Exception((Throwable) odgovor.getParametar());
        } else {
            transferObj = (TransferObjekat) odgovor.getParametar();
            return (Agencija) transferObj.getOdo();
        }
    }

    public List<Agencija> pretraziAgenciju(String nazivAgencije) throws Exception {
//        TransferObjekat transferObj = new TransferObjekat();
//        transferObj.setOdo(agencija);
//        transferObj.setNazivSo("pretraziAgenciju");
        Zahtev zahtev = new Zahtev(Operacija.PRETRAZIVANJE_AGENCIJA, nazivAgencije);
        posiljalac.posaljiPoruku(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primiPoruku();

        if (odgovor.getStatus().equals(StatusPoruke.GRESKA)) {
            throw new Exception((Throwable) odgovor.getParametar());
        } else {
            return (List<Agencija>) odgovor.getParametar();
        }
    }

    public Drzava ubaciDrzavu(Drzava drzava) throws Exception {
        TransferObjekat transferObj = new TransferObjekat();
        transferObj.setOdo(drzava);
        transferObj.setNazivSo("ubaciDrzavu");
        
        Zahtev zahtev = new Zahtev(Operacija.UBACIVANJE_DRZAVA, drzava);
        posiljalac.posaljiPoruku(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primiPoruku();
        if (odgovor.getStatus().equals(StatusPoruke.GRESKA)) {
            throw new Exception((Throwable) odgovor.getParametar());
        } else {
            transferObj = (TransferObjekat) odgovor.getParametar();
            return (Drzava) transferObj.getOdo();
        }
    }

    public Integer obrisiDrzavu(Integer id) throws Exception {
        TransferObjekat transferObj = new TransferObjekat();
        transferObj.setOdo(new Drzava(id, null));
        transferObj.setNazivSo("obrisiDrzavu");
        
        Zahtev zahtev = new Zahtev(Operacija.BRISANJE_DRZAVA, id);
        posiljalac.posaljiPoruku(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primiPoruku();

        if (odgovor.getStatus().equals(StatusPoruke.GRESKA)) {
            throw new Exception((Throwable) odgovor.getParametar());
        } else {
            transferObj = (TransferObjekat) odgovor.getParametar();
            return ((Drzava) transferObj.getOdo()).getId();
        }
    }

    public PonudaVeslaca vratiPonuduPoId(int idPonude) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.VRATI_PONUDU_PO_ID, idPonude);
        posiljalac.posaljiPoruku(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primiPoruku();
        if (odgovor.getStatus().equals(StatusPoruke.GRESKA)) {
            throw new Exception((Throwable) odgovor.getParametar());
        } else {
            return (PonudaVeslaca) odgovor.getParametar();
        }
    }

}
