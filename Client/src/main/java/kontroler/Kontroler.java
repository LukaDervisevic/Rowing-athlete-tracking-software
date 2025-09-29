package kontroler;

import java.io.IOException;
import java.net.Socket;
import java.util.List;
import model.Agencija;
import model.Drzava;
import model.KategorijaVeslaca;
import model.KlubTakmicenje;
import model.PonudaVeslaca;
import model.Takmicenje;
import model.Veslac;
import model.VeslackiKlub;
import transfer.Odgovor;
import operacije.Operacija;
import transfer.Posiljalac;
import transfer.Primalac;
import transfer.StatusPoruke;
import transfer.Zahtev;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import transfer.TransferObjekat;

/**
 *
 * @author luka
 */
public class Kontroler {

    private static Kontroler instance;

    private Object ulogovaniNalog;

    private boolean odjavaSignal;

    private Primalac primalac;

    private Posiljalac posiljalac;

    private Socket soket;

    private static final Logger logger = LogManager.getRootLogger();

    private Kontroler() {
        try {
            soket = new Socket("localhost", 9000);
            primalac = new Primalac(soket);
            posiljalac = new Posiljalac(soket);

        } catch (IOException ex) {
            logger.error(ex.getMessage());
        }
    }

    public static Kontroler getInstance() {
        if (instance == null) {
            instance = new Kontroler();
        }
        return instance;
    }

    public Object getUlogovaniNalog() {
        return ulogovaniNalog;
    }

    public void setUlogovaniNalog(Object ulogovaniNalog) {
        if (ulogovaniNalog instanceof VeslackiKlub || ulogovaniNalog instanceof Agencija) {
            this.ulogovaniNalog = ulogovaniNalog;
        }
    }

    public boolean isOdjavaSignal() {
        return odjavaSignal;
    }

    public void setOdjavaSignal(boolean odjavaSignal) {
        this.odjavaSignal = odjavaSignal;
    }

    //VESLACKI KLUB
    public VeslackiKlub prijaviVeslackiKlub(VeslackiKlub veslackiKlub) throws Exception {
        TransferObjekat transferObj = new TransferObjekat();
        transferObj.setOdo(veslackiKlub);

        Zahtev zahtev = new Zahtev(Operacija.PRIJAVA, transferObj);
        posiljalac.posaljiPoruku(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primiPoruku();

        transferObj = (TransferObjekat) odgovor.getParametar();
        return (VeslackiKlub) transferObj.getOdo();

    }

    public VeslackiKlub kreirajVeslackiKlub(VeslackiKlub veslackiKlub) throws Exception {
        TransferObjekat transferObj = new TransferObjekat();
        transferObj.setOdo(veslackiKlub);

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

    public VeslackiKlub azuirirajVeslackiKlub(VeslackiKlub veslackiKlub) throws Exception {
        TransferObjekat transferObj = new TransferObjekat();
        transferObj.setOdo(veslackiKlub);

        Zahtev zahtev = new Zahtev(Operacija.PROMENA_KLUB, transferObj);
        posiljalac.posaljiPoruku(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primiPoruku();

        if (odgovor.getStatus().equals(StatusPoruke.GRESKA)) {
            throw new Exception((Throwable) odgovor.getParametar());
        } else {
            transferObj = (TransferObjekat) odgovor.getParametar();
            return (VeslackiKlub) transferObj.getOdo();
        }
    }

    public Integer obrisiVeslackiKlub(int idKluba) throws Exception {
        TransferObjekat transferObj = new TransferObjekat();
        transferObj.setOdo(new VeslackiKlub(idKluba, null, null, null, null, null, null));

        Zahtev zahtev = new Zahtev(Operacija.BRISANJE_KLUB, transferObj);
        posiljalac.posaljiPoruku(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primiPoruku();

        if (odgovor.getStatus().equals(StatusPoruke.GRESKA)) {
            throw new Exception((Throwable) odgovor.getParametar());
        } else {
            transferObj = (TransferObjekat) odgovor.getParametar();
            return ((VeslackiKlub) transferObj.getOdo()).getId();
        }
    }

    public List<VeslackiKlub> pretraziKlub(String kriterijum, VeslackiKlub veslackiKlub) throws Exception {
        TransferObjekat transferObj = new TransferObjekat();
        transferObj.setOdo(veslackiKlub);
        transferObj.setWhereUslov(kriterijum);

        Zahtev zahtev = new Zahtev(Operacija.PRETRAZIVANJE_KLUB, transferObj);
        posiljalac.posaljiPoruku(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primiPoruku();

        if (odgovor.getStatus().equals(StatusPoruke.GRESKA)) {
            throw new Exception((Throwable) odgovor.getParametar());
        } else {
            return (List<VeslackiKlub>) odgovor.getParametar();
        }
    }

    public List<VeslackiKlub> vratiListuSviVeslackiKlub(List<VeslackiKlub> veslackiKlubovi) throws Exception {
        TransferObjekat transferObjekat = new TransferObjekat();
        transferObjekat.setListOdo(veslackiKlubovi);
        transferObjekat.setOdo(new VeslackiKlub());

        Zahtev zahtev = new Zahtev(Operacija.VRATI_LISTU_SVI_VESLACKI_KLUB, transferObjekat);
        posiljalac.posaljiPoruku(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primiPoruku();
        if (odgovor.getStatus().equals(StatusPoruke.GRESKA)) {
            throw new Exception((Throwable) odgovor.getParametar());
        } else {
            transferObjekat = (TransferObjekat) odgovor.getParametar();
            return (List<VeslackiKlub>) transferObjekat.getListOdo();
        }

    }

    public List<VeslackiKlub> vratiListuVeslackiKlub(String kriterijum, List<VeslackiKlub> veslackiKlubovi) throws Exception {
        TransferObjekat transferObjekat = new TransferObjekat();
        transferObjekat.setListOdo(veslackiKlubovi);
        transferObjekat.setOdo(new VeslackiKlub());
        transferObjekat.setWhereUslov(kriterijum);

        Zahtev zahtev = new Zahtev(Operacija.VRATI_LISTU_VESLACKI_KLUB, transferObjekat);
        posiljalac.posaljiPoruku(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primiPoruku();

        if (odgovor.getStatus().equals(StatusPoruke.GRESKA)) {
            throw new Exception((Throwable) odgovor.getParametar());
        } else {
            transferObjekat = (TransferObjekat) odgovor.getParametar();
            return (List<VeslackiKlub>) transferObjekat.getListOdo();
        }
    }

    //AGENCIJA
    public Agencija prijaviAgencija(Agencija agencija) throws Exception {
        TransferObjekat transferObj = new TransferObjekat();
        transferObj.setOdo(agencija);

        Zahtev zahtev = new Zahtev(Operacija.PRIJAVA, transferObj);
        posiljalac.posaljiPoruku(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primiPoruku();

        transferObj = (TransferObjekat) odgovor.getParametar();
        return (Agencija) transferObj.getOdo();
    }

    public Agencija kreirajAgenciju(Agencija agencija) throws Exception {
        TransferObjekat transferObj = new TransferObjekat();
        transferObj.setOdo(agencija);

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

    public Agencija azurirajAgenciju(Agencija agencija) throws Exception {
        TransferObjekat transferObj = new TransferObjekat();
        transferObj.setOdo(agencija);

        Zahtev zahtev = new Zahtev(Operacija.PROMENA_AGENCIJA, transferObj);
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
        transferObj.setOdo(new Agencija(id, null, null, null, null, null, null));

        Zahtev zahtev = new Zahtev(Operacija.BRISANJE_AGENCIJA, transferObj);
        posiljalac.posaljiPoruku(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primiPoruku();

        if (odgovor.getStatus().equals(StatusPoruke.GRESKA)) {
            throw new Exception((Throwable) odgovor.getParametar());
        } else {
            transferObj = (TransferObjekat) odgovor.getParametar();
            return ((Agencija) transferObj.getOdo()).getId();
        }
    }

    public List<Agencija> pretraziAgenciju(String kriterijum, Agencija agencija) throws Exception {
        TransferObjekat transferObj = new TransferObjekat();
        transferObj.setOdo(agencija);
        transferObj.setWhereUslov(kriterijum);
        Zahtev zahtev = new Zahtev(Operacija.PRETRAZIVANJE_AGENCIJA, transferObj);
        posiljalac.posaljiPoruku(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primiPoruku();

        if (odgovor.getStatus().equals(StatusPoruke.GRESKA)) {
            throw new Exception((Throwable) odgovor.getParametar());
        } else {
            transferObj = (TransferObjekat) odgovor.getParametar();
            return (List<Agencija>) transferObj.getListOdo();
        }
    }

    public List<Agencija> vratiListuSveAgencije(List<Agencija> agencije) throws Exception {
        TransferObjekat transferObjekat = new TransferObjekat();
        transferObjekat.setListOdo(agencije);
        transferObjekat.setOdo(new Agencija());

        Zahtev zahtev = new Zahtev(Operacija.VRATI_LISTU_SVE_AGENCIJE, transferObjekat);
        posiljalac.posaljiPoruku(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primiPoruku();

        if (odgovor.getStatus().equals(StatusPoruke.GRESKA)) {
            throw new Exception((Throwable) odgovor.getParametar());
        } else {
            transferObjekat = (TransferObjekat) odgovor.getParametar();
            return (List<Agencija>) transferObjekat.getListOdo();
        }
    }

    public List<Agencija> vratiListuAgencije(String kriterijum, List<Agencija> agencije) throws Exception {
        TransferObjekat transferObjekat = new TransferObjekat();
        transferObjekat.setListOdo(agencije);
        transferObjekat.setOdo(new Agencija());
        transferObjekat.setWhereUslov(kriterijum);

        Zahtev zahtev = new Zahtev(Operacija.VRATI_LISTU_AGENCIJE, transferObjekat);
        posiljalac.posaljiPoruku(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primiPoruku();

        if (odgovor.getStatus().equals(StatusPoruke.GRESKA)) {
            throw new Exception((Throwable) odgovor.getParametar());
        } else {
            transferObjekat = (TransferObjekat) odgovor.getParametar();
            return (List<Agencija>) transferObjekat.getListOdo();
        }
    }

    //VESLAC
    public Veslac kreirajVeslaca(Veslac veslac) throws Exception {
        TransferObjekat transferObj = new TransferObjekat();
        transferObj.setOdo(veslac);

        Zahtev zahtev = new Zahtev(Operacija.KREIRANJE_VESLAC, transferObj);
        posiljalac.posaljiPoruku(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primiPoruku();

        if (odgovor.getStatus().equals(StatusPoruke.GRESKA)) {
            throw new Exception((Throwable) odgovor.getParametar());
        } else {
            transferObj = (TransferObjekat) odgovor.getParametar();
            return (Veslac) transferObj.getOdo();
        }
    }

    public List<Veslac> pretraziVeslaca(Veslac veslac) throws Exception {
        TransferObjekat transferObj = new TransferObjekat();
        transferObj.setOdo(veslac);

        Zahtev zahtev = new Zahtev(Operacija.PRETRAZIVANJE_VESLAC, transferObj);
        posiljalac.posaljiPoruku(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primiPoruku();

        if (odgovor.getStatus().equals(StatusPoruke.GRESKA)) {
            throw new Exception((Throwable) odgovor.getParametar());
        } else {
            transferObj = (TransferObjekat) odgovor.getParametar();
            return (List<Veslac>) transferObj.getOdo();
        }
    }

    public Integer obrisiVeslaca(Integer id) throws Exception {
        TransferObjekat transferObj = new TransferObjekat();
        transferObj.setOdo(new Veslac(id, null, null, 0, 0, KategorijaVeslaca.KADET, 0, null, new VeslackiKlub()));

        Zahtev zahtev = new Zahtev(Operacija.BRISANJE_VESLAC, transferObj);
        posiljalac.posaljiPoruku(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primiPoruku();

        if (odgovor.getStatus().equals(StatusPoruke.GRESKA)) {
            throw new Exception((Throwable) odgovor.getParametar());
        } else {
            transferObj = (TransferObjekat) odgovor.getParametar();
            return ((Veslac) transferObj.getOdo()).getId();
        }
    }

    public Veslac azurirajVeslaca(Veslac veslac) throws Exception {
        TransferObjekat transferObj = new TransferObjekat();
        transferObj.setOdo(veslac);

        Zahtev zahtev = new Zahtev(Operacija.PROMENA_VESLAC, transferObj);
        posiljalac.posaljiPoruku(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primiPoruku();

        if (odgovor.getStatus().equals(StatusPoruke.GRESKA)) {
            throw new Exception((Throwable) odgovor.getParametar());
        } else {
            transferObj = (TransferObjekat) odgovor.getParametar();
            return (Veslac) transferObj.getOdo();
        }
    }

    public List<Veslac> vratiListuSviVeslaci(List<Veslac> veslaci) throws Exception {
        TransferObjekat transferObjekat = new TransferObjekat();
        transferObjekat.setListOdo(veslaci);
        transferObjekat.setOdo(new Veslac());

        Zahtev zahtev = new Zahtev(Operacija.VRATI_LISTU_SVI_VESLAC, veslaci);
        posiljalac.posaljiPoruku(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primiPoruku();

        if (odgovor.getStatus().equals(StatusPoruke.GRESKA)) {
            throw new Exception((Throwable) odgovor.getParametar());
        } else {
            transferObjekat = (TransferObjekat) odgovor.getParametar();
            return (List<Veslac>) transferObjekat.getListOdo();
        }
    }

    public List<Veslac> vratiListuVeslaci(String kriterijum, List<Veslac> veslaci) throws Exception {
        TransferObjekat transferObjekat = new TransferObjekat();
        transferObjekat.setListOdo(veslaci);
        transferObjekat.setOdo(new Veslac());
        transferObjekat.setWhereUslov(kriterijum);

        Zahtev zahtev = new Zahtev(Operacija.VRATI_LISTU_VESLAC, transferObjekat);
        posiljalac.posaljiPoruku(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primiPoruku();

        if (odgovor.getStatus().equals(StatusPoruke.GRESKA)) {
            throw new Exception((Throwable) odgovor.getParametar());
        } else {
            transferObjekat = (TransferObjekat) odgovor.getParametar();
            return (List<Veslac>) transferObjekat.getListOdo();
        }
    }

    //TAKMICENJE
    public Takmicenje dodajTakmicenje(Takmicenje takmicenje) throws Exception {
        TransferObjekat transferObj = new TransferObjekat();
        transferObj.setOdo(takmicenje);

        Zahtev zahtev = new Zahtev(Operacija.UBACIVANJE_TAKMICENJE, transferObj);
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

        Zahtev zahtev = new Zahtev(Operacija.BRISANJE_TAKMICENJE, transferObj);
        posiljalac.posaljiPoruku(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primiPoruku();

        if (odgovor.getStatus().equals(StatusPoruke.GRESKA)) {
            throw new Exception((Throwable) odgovor.getParametar());
        } else {
            transferObj = (TransferObjekat) odgovor.getParametar();
            return ((Takmicenje) transferObj.getOdo()).getId();
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

    public List<Takmicenje> vratiListuSvaTakmicenja(List<Takmicenje> takmicenja) throws Exception {
        TransferObjekat transferObjekat = new TransferObjekat();
        transferObjekat.setListOdo(takmicenja);
        transferObjekat.setOdo(new Takmicenje());

        Zahtev zahtev = new Zahtev(Operacija.VRATI_LISTU_SVI_TAKMICENJA, transferObjekat);
        posiljalac.posaljiPoruku(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primiPoruku();

        if (odgovor.getStatus().equals(StatusPoruke.GRESKA)) {
            throw new Exception((Throwable) odgovor.getParametar());
        } else {
            transferObjekat = (TransferObjekat) odgovor.getParametar();
            return (List<Takmicenje>) transferObjekat.getListOdo();
        }
    }

    public List<Takmicenje> vratiListuTakmicenja(String kriterijum, List takmicenja) throws Exception {
        TransferObjekat transferObjekat = new TransferObjekat();
        transferObjekat.setListOdo(takmicenja);
        transferObjekat.setOdo(new Takmicenje());
        transferObjekat.setWhereUslov(kriterijum);

        Zahtev zahtev = new Zahtev(Operacija.VRATI_LISTU_TAKMICENJA, transferObjekat);
        posiljalac.posaljiPoruku(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primiPoruku();

        if (odgovor.getStatus().equals(StatusPoruke.GRESKA)) {
            throw new Exception((Throwable) odgovor.getParametar());
        } else {
            transferObjekat = (TransferObjekat) odgovor.getParametar();
            return (List<Takmicenje>) transferObjekat.getListOdo();
        }
    }

    //PONUDA VESLACA
    public PonudaVeslaca kreirajPonuduVeslaca(PonudaVeslaca ponudaVeslaca) throws Exception {
        TransferObjekat transferObj = new TransferObjekat();
        transferObj.setOdo(ponudaVeslaca);

        Zahtev zahtev = new Zahtev(Operacija.KREIRANJE_PONUDE, transferObj);
        posiljalac.posaljiPoruku(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primiPoruku();

        if (odgovor.getStatus().equals(StatusPoruke.GRESKA)) {
            throw new Exception((Throwable) odgovor.getParametar());
        } else {
            transferObj = (TransferObjekat) odgovor.getParametar();
            return (PonudaVeslaca) transferObj.getOdo();
        }
    }

    public PonudaVeslaca pretraziPonudu(PonudaVeslaca ponuda) throws Exception {
        TransferObjekat transferObj = new TransferObjekat();
        transferObj.setOdo(ponuda);

        Zahtev zahtev = new Zahtev(Operacija.PRETRAZIVANJE_PONUDE, transferObj);
        posiljalac.posaljiPoruku(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primiPoruku();

        if (odgovor.getStatus().equals(StatusPoruke.GRESKA)) {
            throw new Exception((Throwable) odgovor.getParametar());
        } else {
            transferObj = (TransferObjekat) odgovor.getParametar();
            return (PonudaVeslaca) transferObj.getOdo();
        }
    }

    public Integer obrisiPonudu(Integer idPonude) throws Exception {
        TransferObjekat transferObj = new TransferObjekat();
        transferObj.setOdo(new PonudaVeslaca(idPonude, null, 0, 0, 0, 0, null, new VeslackiKlub(), new Agencija()));

        Zahtev zahtev = new Zahtev(Operacija.BRISANJE_PONUDE, transferObj);
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

        Zahtev zahtev = new Zahtev(Operacija.PROMENA_PONUDE, transferObj);
        posiljalac.posaljiPoruku(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primiPoruku();

        if (odgovor.getStatus().equals(StatusPoruke.GRESKA)) {
            throw new Exception((Throwable) odgovor.getParametar());
        } else {
            transferObj = (TransferObjekat) odgovor.getParametar();
            return (PonudaVeslaca) transferObj.getOdo();
        }

    }

    public List<PonudaVeslaca> vratiListuSvePonude(List<PonudaVeslaca> ponude) throws Exception {
        TransferObjekat transferObjekat = new TransferObjekat();
        transferObjekat.setListOdo(ponude);
        transferObjekat.setOdo(new PonudaVeslaca());

        Zahtev zahtev = new Zahtev(Operacija.VRATI_LISTU_SVI_PONUDE_VESLACA, transferObjekat);
        posiljalac.posaljiPoruku(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primiPoruku();

        if (odgovor.getStatus().equals(StatusPoruke.GRESKA)) {
            throw new Exception((Throwable) odgovor.getParametar());
        } else {
            transferObjekat = (TransferObjekat) odgovor.getParametar();
            return (List<PonudaVeslaca>) transferObjekat.getListOdo();
        }
    }

    public List<PonudaVeslaca> vratiListuPonude(String kriterijum, List<PonudaVeslaca> ponude) throws Exception {
        TransferObjekat transferObjekat = new TransferObjekat();
        transferObjekat.setListOdo(ponude);
        transferObjekat.setOdo(new PonudaVeslaca());
        transferObjekat.setWhereUslov(kriterijum);

        Zahtev zahtev = new Zahtev(Operacija.VRATI_LISTU_PONUDE_VESLACA, transferObjekat);
        posiljalac.posaljiPoruku(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primiPoruku();

        if (odgovor.getStatus().equals(StatusPoruke.GRESKA)) {
            throw new Exception((Throwable) odgovor.getParametar());
        } else {
            transferObjekat = (TransferObjekat) odgovor.getParametar();
            return (List<PonudaVeslaca>) transferObjekat.getListOdo();
        }
    }

    //DRZAVA
    public Drzava ubaciDrzavu(Drzava drzava) throws Exception {
        TransferObjekat transferObj = new TransferObjekat();
        transferObj.setOdo(drzava);

        Zahtev zahtev = new Zahtev(Operacija.UBACIVANJE_DRZAVA, transferObj);
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

        Zahtev zahtev = new Zahtev(Operacija.BRISANJE_DRZAVA, transferObj);
        posiljalac.posaljiPoruku(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primiPoruku();

        if (odgovor.getStatus().equals(StatusPoruke.GRESKA)) {
            throw new Exception((Throwable) odgovor.getParametar());
        } else {
            transferObj = (TransferObjekat) odgovor.getParametar();
            return ((Drzava) transferObj.getOdo()).getId();
        }
    }

    public List<Drzava> vratiListuSveDrzave(List<Drzava> drzave) throws Exception {
        TransferObjekat transferObjekat = new TransferObjekat();
        transferObjekat.setListOdo(drzave);
        transferObjekat.setOdo(new Drzava());

        Zahtev zahtev = new Zahtev(Operacija.VRATI_LISTU_SVE_DRZAVE, transferObjekat);
        posiljalac.posaljiPoruku(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primiPoruku();

        if (odgovor.getStatus().equals(StatusPoruke.GRESKA)) {
            throw new Exception((Throwable) odgovor.getParametar());
        } else {
            transferObjekat = (TransferObjekat) odgovor.getParametar();
            return (List<Drzava>) transferObjekat.getListOdo();
        }
    }

    public List<Drzava> vratiListuDrzave(String kriterijum, List<Drzava> drzave) throws Exception {
        TransferObjekat transferObjekat = new TransferObjekat();
        transferObjekat.setListOdo(drzave);
        transferObjekat.setOdo(new Drzava());
        transferObjekat.setWhereUslov(kriterijum);

        Zahtev zahtev = new Zahtev(Operacija.VRATI_LISTU_DRZAVE, transferObjekat);
        posiljalac.posaljiPoruku(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primiPoruku();

        if (odgovor.getStatus().equals(StatusPoruke.GRESKA)) {
            throw new Exception((Throwable) odgovor.getParametar());
        } else {
            transferObjekat = (TransferObjekat) odgovor.getParametar();
            return (List<Drzava>) transferObjekat.getListOdo();
        }

    }

    // KLUB TAKMICENJE
    public List<KlubTakmicenje> vratiTakmicenjaKluba(Integer idKluba) throws Exception {

        Zahtev zahtev = new Zahtev(Operacija.VRATI_LISTU_KLUB_TAKMICENJA, idKluba);
        posiljalac.posaljiPoruku(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primiPoruku();

        if (odgovor.getStatus().equals(StatusPoruke.GRESKA)) {
            throw new Exception((Throwable) odgovor.getParametar());
        } else {
            return (List<KlubTakmicenje>) odgovor.getParametar();
        }
    }

    // Predstavlja problem
//    public KlubTakmicenje dodajOsvojenoTakmicenje(int mesto, int idTakmicenja, int idKluba) throws Exception {
//        VeslackiKlub klub = vratiListuVeslackiKlub(idKluba);
//        Takmicenje takmicenje = vratiListuTakmicenja(idTakmicenja);
//
//        KlubTakmicenje klubTakmicenje = new KlubTakmicenje(mesto, klub, takmicenje);
//
//        Zahtev zahtev = new Zahtev(Operacija.KREIRANJE_KLUB_TAKMICENJE, klubTakmicenje);
//        posiljalac.posaljiPoruku(zahtev);
//
//        Odgovor odgovor = (Odgovor) primalac.primiPoruku();
//
//        if (odgovor.getStatus().equals(StatusPoruke.GRESKA)) {
//            throw new Exception((Throwable) odgovor.getParametar());
//        } else {
//            return (KlubTakmicenje) odgovor.getParametar();
//        }
//
//    }
    // Predstavlja problem
//    public Integer obrisiOsvojenoTakmicenje(int idKlub, int idTakmicenja, int mesto) throws Exception {
//
//        Takmicenje takmicenje = vratiListuTakmicenja(idTakmicenja);
//        VeslackiKlub veslackiKlub = vratiListuVeslackiKlub(idKlub);
//
//        KlubTakmicenje klubTakmicenje = new KlubTakmicenje(mesto, veslackiKlub, takmicenje);
//
//        Zahtev zahtev = new Zahtev(Operacija.BRISANJE_KLUB_TAKMICENJE, klubTakmicenje);
//        posiljalac.posaljiPoruku(zahtev);
//
//        Odgovor odgovor = (Odgovor) primalac.primiPoruku();
//
//        if (odgovor.getStatus().equals(StatusPoruke.GRESKA)) {
//            throw new Exception((Throwable) odgovor.getParametar());
//        } else {
//            return (Integer) odgovor.getParametar();
//        }
//
//    }
//    public int[] prebrojOsvojenaTakmicenja(int idKluba) throws Exception {
//
//        Zahtev zahtev = new Zahtev(Operacija.PREBROJ_TAKMICENJA, (Integer) idKluba);
//        posiljalac.posaljiPoruku(zahtev);
//
//        Odgovor odgovor = (Odgovor) primalac.primiPoruku();
//
//        if (odgovor.getStatus().equals(StatusPoruke.GRESKA)) {
//            throw new Exception((Throwable) odgovor.getParametar());
//        } else {
//            return (int[]) odgovor.getParametar();
//        }
//    }
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
}
