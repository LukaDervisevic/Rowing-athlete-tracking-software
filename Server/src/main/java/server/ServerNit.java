package server;

import controller.Controller;
import java.net.Socket;
import java.util.List;
import model.Agencija;
import model.Drzava;
import model.KlubTakmicenje;
import model.Nalog;
import model.PonudaVeslaca;
import model.Takmicenje;
import model.Veslac;
import model.VeslackiKlub;
import operacije.Odgovor;
import operacije.Operacija;
import operacije.Posiljalac;
import operacije.Primalac;
import operacije.StatusPoruke;
import operacije.Zahtev;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author luka
 */
class ServerNit extends Thread {

    private static final Logger logger = LogManager.getRootLogger();
    
    Socket soketZaKomunikaciju;
    List<ServerNit> klijenti;
    Posiljalac posiljalac;
    Primalac primalac;

    public ServerNit(Socket soketZaKomunikaciju, List<ServerNit> klijenti) {
        this.soketZaKomunikaciju = soketZaKomunikaciju;
        this.klijenti = klijenti;
        this.posiljalac = new Posiljalac(soketZaKomunikaciju);
        this.primalac = new Primalac(soketZaKomunikaciju);
    }

    @Override
    public void run() {

        try {
            while (true) {

                Zahtev korisnikovZahtev = (Zahtev) primalac.primiPoruku();

                Object odgovorServera = obradiZahtev(korisnikovZahtev);

                posiljalac.posaljiPoruku(odgovorServera);

            }

        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }

    }

    private synchronized Object obradiZahtev(Zahtev korisnikovZahtev){

        Object objekat;

        try {

            switch (korisnikovZahtev.getOperacija()) {
                case Operacija.PRIJAVA -> {
                    objekat = Controller.getInstance().login((Nalog) korisnikovZahtev.getParametar());
                    return new Odgovor(StatusPoruke.OK, objekat);
                }
                case Operacija.KREIRANJE_KLUB -> {
                    objekat = Controller.getInstance().kreirajVeslackiKlub((VeslackiKlub) korisnikovZahtev.getParametar());
                    return new Odgovor(StatusPoruke.OK, objekat);
                }

                case Operacija.PROMENA_KLUB -> {
                    objekat = Controller.getInstance().azuirirajVeslackiKlub((VeslackiKlub) korisnikovZahtev.getParametar());
                    return new Odgovor(StatusPoruke.OK, objekat);
                }

                case Operacija.PRETRAZIVANJE_KLUB -> {
                    objekat = Controller.getInstance().pretraziKlub((String) korisnikovZahtev.getParametar());
                    return new Odgovor(StatusPoruke.OK, objekat);
                }

                case Operacija.BRISANJE_KLUB -> {
                    objekat = Controller.getInstance().obrisiVeslackiKlub((Integer) korisnikovZahtev.getParametar());
                    return new Odgovor(StatusPoruke.OK, objekat);
                }

                case Operacija.VRATI_SVE_KLUBOVE -> {
                    objekat = Controller.getInstance().vratiSveKlubove();
                    return new Odgovor(StatusPoruke.OK,objekat);
                }
                
                case Operacija.VRATI_KLUB_PO_ID -> {
                    objekat = Controller.getInstance().vratiVeslackiKlubPoId((Integer) korisnikovZahtev.getParametar());
                    return new Odgovor(StatusPoruke.OK,objekat);
                }
                case Operacija.KREIRANJE_AGENCIJA -> {
                    objekat = Controller.getInstance().kreirajAgenciju((Agencija) korisnikovZahtev.getParametar());
                    return new Odgovor(StatusPoruke.OK, objekat);
                }

                case Operacija.PROMENA_AGENCIJA -> {
                    objekat = Controller.getInstance().azurirajAgenciju((Agencija) korisnikovZahtev.getParametar());
                    return new Odgovor(StatusPoruke.OK, objekat);
                }

                case Operacija.PRETRAZIVANJE_AGENCIJA -> {
                    objekat = Controller.getInstance().pretraziAgenciju((String) korisnikovZahtev.getParametar());
                    return new Odgovor(StatusPoruke.OK,objekat);
                }
                    
                case Operacija.BRISANJE_AGENCIJA -> {
                    objekat = Controller.getInstance().obrisiAgenciju((Integer) korisnikovZahtev.getParametar());
                    return new Odgovor(StatusPoruke.OK, objekat);
                }
                    
                case Operacija.VRATI_SVE_AGENCIJE -> {
                    objekat = Controller.getInstance().vratiSveAgencije();
                    return new Odgovor(StatusPoruke.OK,objekat);
                }
                case Operacija.KREIRANJE_VESLAC -> {
                    objekat = Controller.getInstance().kreirajVeslaca((Veslac) korisnikovZahtev.getParametar());
                    return new Odgovor(StatusPoruke.OK, objekat);
                }

                case Operacija.PRETRAZIVANJE_VESLAC -> {
                    objekat = Controller.getInstance().pretraziVeslaca((Veslac) korisnikovZahtev.getParametar());
                    return new Odgovor(StatusPoruke.OK, objekat);
                }

                case Operacija.PROMENA_VESLAC -> {
                    objekat = Controller.getInstance().azurirajVeslaca((Veslac) korisnikovZahtev.getParametar());
                    return new Odgovor(StatusPoruke.OK, objekat);
                }

                case Operacija.BRISANJE_VESLAC -> {
                    objekat = Controller.getInstance().obrisiVeslaca((Integer) korisnikovZahtev.getParametar());
                    return new Odgovor(StatusPoruke.OK, objekat);
                }
                    
                case Operacija.VRATI_SVE_VESLACE -> {
                    objekat = Controller.getInstance().vratiSveVeslace((Integer) korisnikovZahtev.getParametar());
                    return new Odgovor(StatusPoruke.OK,objekat);
                }
                    
                case Operacija.VRATI_VESLACA_PO_ID -> {
                    objekat = Controller.getInstance().vratiVeslacaPoId((Integer) korisnikovZahtev.getParametar());
                    return new Odgovor(StatusPoruke.OK,objekat);
                }
                case Operacija.UBACIVANJE_TAKMICENJE -> {
                    objekat = Controller.getInstance().dodajTakmicenje((Takmicenje) korisnikovZahtev.getParametar());
                    return new Odgovor(StatusPoruke.OK, objekat);
                }

                case Operacija.PRETRAZIVANJE_TAKMICENJE -> {
                    objekat = Controller.getInstance().pretraziTakmicenja((String) korisnikovZahtev.getParametar());
                    return new Odgovor(StatusPoruke.OK, objekat);
                }

                case Operacija.BRISANJE_TAKMICENJE -> {
                    objekat = Controller.getInstance().obrisiTakmicenje((Integer) korisnikovZahtev.getParametar());
                    return new Odgovor(StatusPoruke.OK, objekat);
                }
                    
                case Operacija.VRATI_SVA_TAKMICENJA -> {
                    objekat = Controller.getInstance().vratiSvaTakmicenja();
                    return new Odgovor(StatusPoruke.OK,objekat);
                }
                    
                case Operacija.VRATI_TAKMICENJE_PO_ID -> {
                    objekat = Controller.getInstance().vratiTakmicenjePoId((Integer) korisnikovZahtev.getParametar());
                    return new Odgovor(StatusPoruke.OK,objekat);
                }
                case Operacija.KREIRANJE_PONUDE -> {
                    objekat = Controller.getInstance().kreirajPonuduVeslaca((PonudaVeslaca) korisnikovZahtev.getParametar());
                    return new Odgovor(StatusPoruke.OK, objekat);
                }

                case Operacija.PRETRAZIVANJE_PONUDE -> {
                    objekat = Controller.getInstance().pretraziPonudu((PonudaVeslaca) korisnikovZahtev.getParametar());
                    return new Odgovor(StatusPoruke.OK, objekat);
                }
                case Operacija.BRISANJE_PONUDE -> {
                    objekat = Controller.getInstance().obrisiPonudu((Integer) korisnikovZahtev.getParametar());
                    return new Odgovor(StatusPoruke.OK, objekat);
                }
                    
                case Operacija.VRATI_SVE_PONUDE_KLUBA -> {
                    objekat = Controller.getInstance().vratiSvePonudeKluba((Integer) korisnikovZahtev.getParametar());
                    return new Odgovor(StatusPoruke.OK, objekat);
                }
                
                case Operacija.VRATI_SVE_PONUDE_AGENCIJE -> {
                    objekat = Controller.getInstance().vratiSvePonudeAgencije((Integer) korisnikovZahtev.getParametar());
                    return new Odgovor(StatusPoruke.OK,objekat);
                }
                    
                case Operacija.VRATI_POSLEDNJI_ID_PONUDE -> {
                    objekat = Controller.getInstance().vratiPoslednjiIdPonude();
                    return new Odgovor(StatusPoruke.OK,objekat);
                }
                
                case Operacija.VRATI_PONUDU_PO_ID -> {
                    objekat = Controller.getInstance().vratiPonuduPoId((Integer) korisnikovZahtev.getParametar());
                    return new Odgovor(StatusPoruke.OK, objekat);
                }
                
                case Operacija.PROMENA_PONUDE -> {
                    objekat = Controller.getInstance().azurirajPonudu((PonudaVeslaca) korisnikovZahtev.getParametar());
                    return new Odgovor(StatusPoruke.OK, objekat);
                }

                case Operacija.VRATI_SVE_STAVKE_PONUDE -> {
                    objekat = Controller.getInstance().vratiSveStavkePonude((Integer) korisnikovZahtev.getParametar());
                    return new Odgovor(StatusPoruke.OK,objekat);
                }
                case Operacija.UBACIVANJE_DRZAVA -> {
                    objekat = Controller.getInstance().ubaciDrzavu((Drzava) korisnikovZahtev.getParametar());
                    return new Odgovor(StatusPoruke.OK,objekat);
                }

                case Operacija.BRISANJE_DRZAVA -> {
                    objekat = Controller.getInstance().obrisiDrzavu((Integer) korisnikovZahtev.getParametar());
                    return new Odgovor(StatusPoruke.OK,objekat);
                }
                    
                case Operacija.VRATI_SVE_DRZAVE -> {
                    objekat = Controller.getInstance().vratiSveDrzave();
                    return new Odgovor(StatusPoruke.OK,objekat);
                }
                case Operacija.OSVOJI_TAKMICENJE -> {
                    objekat = Controller.getInstance().dodajOsvojenoTakmicenje((KlubTakmicenje) korisnikovZahtev.getParametar());
                    return new Odgovor(StatusPoruke.OK,objekat);
                }
                
                case Operacija.BRISANJE_OSVOJENO_TAKMICENJE -> {
                    objekat = Controller.getInstance().obrisiOsvojenoTakmicenje((KlubTakmicenje) korisnikovZahtev.getParametar());
                    return new Odgovor(StatusPoruke.OK,objekat);
                }
                    
                case Operacija.VRATI_TAKMICENJA_KLUBA -> {
                    objekat = Controller.getInstance().vratiTakmicenjaKluba((Integer) korisnikovZahtev.getParametar());
                    return new Odgovor(StatusPoruke.OK,objekat);
                }
                
                case Operacija.PREBROJ_OSVOJENA_TAKMICENJA -> {
                    objekat = Controller.getInstance().prebrojOsvojenaTakmicenja((Integer) korisnikovZahtev.getParametar());
                    return new Odgovor(StatusPoruke.OK,objekat);
                }
                case Operacija.PREKID -> {
                    soketZaKomunikaciju.close();
                    klijenti.remove(this);
                    return new Odgovor(StatusPoruke.OK,"Konekcija prekinuta");
                }
                
                default -> {
                    return new Odgovor(StatusPoruke.GRESKA, new Exception("Greska nepostojeca operacija"));
                }
            }
            //VESLACKI KLUB
            //AGENCIJA
            // VESLAC
            // TAKMICENJE
            // PONUDA
            //case Operacija.PROMENA_PONUDE:
            // STAVKE PONUDE
            // DRZAVA
            // KLUB TAKMICENJE
            // Nije dobra logika
            
        } catch (Exception ex) {
            return new Odgovor(StatusPoruke.GRESKA,ex);
        }
    }

}
