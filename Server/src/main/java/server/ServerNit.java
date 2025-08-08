package server;

import kontroller.Controller;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;
import model.KlubTakmicenje;
import model.Nalog;
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
class ServerNit extends Thread {

    private static final Logger logger = LogManager.getRootLogger();

    Socket soketZaKomunikaciju;
    List<ServerNit> klijenti;
    Posiljalac posiljalac;
    Primalac primalac;

    public ServerNit(Socket soketZaKomunikaciju, List<ServerNit> klijenti) {
        this.soketZaKomunikaciju = soketZaKomunikaciju;
        this.klijenti = klijenti;
    }

    @Override
    public void run() {
        try {
            this.posiljalac = new Posiljalac(soketZaKomunikaciju);
            this.primalac = new Primalac(soketZaKomunikaciju);

            while (!soketZaKomunikaciju.isClosed()) {
                try {

                    Zahtev korisnikovZahtev = (Zahtev) primalac.primiPoruku();
                    if (korisnikovZahtev == null) {
                        System.out.println("Korisnik se odjavio");
                        break;
                    }

                    Object odgovorServera = obradiZahtev(korisnikovZahtev);
                    posiljalac.posaljiPoruku(odgovorServera);

                } catch (EOFException ex) {
                    logger.info("Klijent je zatvorio soket: " + ex);
                    break;
                } catch (SocketException ex) {
                    logger.info("Soket klijenta se gasi");
                    break;

                } catch (IOException | ClassNotFoundException ex) {
                    logger.error(ex.getMessage());
                    break;
                } catch (Exception ex) {
                    logger.error("Greska pri obradi: " + ex);
                    break;
                }

            }

        } catch (IOException ex) {
            logger.error(ex.getMessage());
        } finally {
            try {
                soketZaKomunikaciju.close();
            } catch (IOException ex) {
                logger.error(ex.getMessage());
            }
            klijenti.remove(this);
            System.out.println("Klijent obrisan: " + soketZaKomunikaciju);
        }
    }

    private synchronized Object obradiZahtev(Zahtev korisnikovZahtev) {

        Object objekat;
        TransferObjekat transferObj;

        try {
            transferObj = (TransferObjekat) korisnikovZahtev.getParametar();
            switch (korisnikovZahtev.getOperacija()) {
                case Operacija.PRIJAVA -> {
                    objekat = Controller.getInstance().login((Nalog) korisnikovZahtev.getParametar());
                    return new Odgovor(StatusPoruke.OK, objekat);
                }
                case Operacija.KREIRANJE_KLUB -> {
                    boolean signal = Controller.getInstance().kreirajVeslackiKlub(transferObj);
                    if (signal) {
                        return new Odgovor(StatusPoruke.OK, transferObj);
                    }
                }

                case Operacija.PROMENA_KLUB -> {
                    boolean signal = Controller.getInstance().promeniVeslackiKlub(transferObj);
                    if (signal) {
                        return new Odgovor(StatusPoruke.OK, transferObj);
                    }
                }

                case Operacija.PRETRAZIVANJE_KLUB -> {
                    boolean signal = Controller.getInstance().pretraziVeslackiKlub(transferObj);
                    if (signal) {
                        return new Odgovor(StatusPoruke.OK, transferObj);
                    }
                }

                case Operacija.BRISANJE_KLUB -> {
                    boolean signal = Controller.getInstance().obrisiVeslackiKlub((TransferObjekat) korisnikovZahtev.getParametar());
                    if(signal){
                        return new Odgovor(StatusPoruke.OK, transferObj);
                    }
                    
                }

                case Operacija.VRATI_SVE_KLUBOVE -> {
                    objekat = Controller.getInstance().vratiListuVeslackiKlub();
                    return new Odgovor(StatusPoruke.OK, objekat);
                }

                case Operacija.VRATI_KLUB_PO_ID -> {
                    objekat = Controller.getInstance().vratiVeslackiKlubPoId((Integer) korisnikovZahtev.getParametar());
                    return new Odgovor(StatusPoruke.OK, objekat);
                }
                case Operacija.KREIRANJE_AGENCIJA -> {
                    boolean signal = Controller.getInstance().kreirajAgencija((TransferObjekat) korisnikovZahtev.getParametar());
                    if(signal){
                       return new Odgovor(StatusPoruke.OK, transferObj); 
                    }
                    
                }

                case Operacija.PROMENA_AGENCIJA -> {
                    boolean signal = Controller.getInstance().promeniAgencija((TransferObjekat) korisnikovZahtev.getParametar());
                    if(signal){
                        return new Odgovor(StatusPoruke.OK, transferObj);
                    }
                    
                }

                case Operacija.PRETRAZIVANJE_AGENCIJA -> {
                    boolean signal = Controller.getInstance().pretraziAgencija((TransferObjekat) korisnikovZahtev.getParametar());
                    if(signal){
                        return new Odgovor(StatusPoruke.OK, transferObj);
                    }
                    
                }

                case Operacija.BRISANJE_AGENCIJA -> {
                    boolean signal = Controller.getInstance().obrisiAgenciju((TransferObjekat) korisnikovZahtev.getParametar());
                    if(signal){
                        return new Odgovor(StatusPoruke.OK, transferObj);
                    }
                    
                }

                case Operacija.VRATI_SVE_AGENCIJE -> {
                    objekat = Controller.getInstance().vratiListuAgencija();
                    return new Odgovor(StatusPoruke.OK, objekat);
                }
                case Operacija.KREIRANJE_VESLAC -> {
                    objekat = Controller.getInstance().kreirajVeslaca((Veslac) korisnikovZahtev.getParametar());
                    return new Odgovor(StatusPoruke.OK, objekat);
                }

                case Operacija.PRETRAZIVANJE_VESLAC -> {
                    objekat = Controller.getInstance().pretraziVeslac((Veslac) korisnikovZahtev.getParametar());
                    return new Odgovor(StatusPoruke.OK, objekat);
                }

                case Operacija.PROMENA_VESLAC -> {
                    objekat = Controller.getInstance().azurirajVeslac((Veslac) korisnikovZahtev.getParametar());
                    return new Odgovor(StatusPoruke.OK, objekat);
                }

                case Operacija.BRISANJE_VESLAC -> {
                    boolean signal = Controller.getInstance().obrisiVeslac((TransferObjekat) korisnikovZahtev.getParametar());
                    if(signal){
                       return new Odgovor(StatusPoruke.OK, transferObj); 
                    }
                    
                }

                case Operacija.VRATI_SVE_VESLACE -> {
                    objekat = Controller.getInstance().vratiSveVeslace((Integer) korisnikovZahtev.getParametar());
                    return new Odgovor(StatusPoruke.OK, objekat);
                }

                case Operacija.VRATI_VESLACA_PO_ID -> {
                    objekat = Controller.getInstance().vratiVeslacaPoId((Integer) korisnikovZahtev.getParametar());
                    return new Odgovor(StatusPoruke.OK, objekat);
                }
                case Operacija.UBACIVANJE_TAKMICENJE -> {
                    boolean signal = Controller.getInstance().ubaciTakmicenje((TransferObjekat) korisnikovZahtev.getParametar());
                    if(signal){
                        return new Odgovor(StatusPoruke.OK, transferObj);
                    }
                    
                }

                case Operacija.PRETRAZIVANJE_TAKMICENJE -> {
                    boolean signal = Controller.getInstance().pretraziTakmicenje((TransferObjekat) korisnikovZahtev.getParametar());
                    if(signal){
                        return new Odgovor(StatusPoruke.OK, transferObj);
                    }
                    
                }

                case Operacija.BRISANJE_TAKMICENJE -> {
                    boolean signal = Controller.getInstance().obrisiTakmicenje((TransferObjekat) korisnikovZahtev.getParametar());
                    if(signal){
                        return new Odgovor(StatusPoruke.OK, transferObj);
                    }
                    
                }

                case Operacija.VRATI_SVA_TAKMICENJA -> {
                    objekat = Controller.getInstance().vratiSvaTakmicenja();
                    return new Odgovor(StatusPoruke.OK, objekat);
                }

                case Operacija.VRATI_TAKMICENJE_PO_ID -> {
                    objekat = Controller.getInstance().vratiTakmicenjePoId((Integer) korisnikovZahtev.getParametar());
                    return new Odgovor(StatusPoruke.OK, objekat);
                }
                case Operacija.KREIRANJE_PONUDE -> {
                    boolean signal = Controller.getInstance().kreirajPonudaVeslaca((TransferObjekat) korisnikovZahtev.getParametar());
                    if(signal){
                       return new Odgovor(StatusPoruke.OK, transferObj); 
                    }
                }

                case Operacija.PRETRAZIVANJE_PONUDE -> {
                    boolean signal = Controller.getInstance().pretraziPonudaVeslaca((TransferObjekat) korisnikovZahtev.getParametar());
                    if(signal){
                       return new Odgovor(StatusPoruke.OK, transferObj); 
                    }
                    
                }
                case Operacija.BRISANJE_PONUDE -> {
                    boolean signal = Controller.getInstance().obrisiPonudaVeslaca((TransferObjekat) korisnikovZahtev.getParametar());
                    if(signal){
                       return new Odgovor(StatusPoruke.OK, transferObj); 
                    }
                    
                }

                case Operacija.VRATI_SVE_PONUDE_KLUBA -> {
                    objekat = Controller.getInstance().vratiSvePonudeKluba((Integer) korisnikovZahtev.getParametar());
                    return new Odgovor(StatusPoruke.OK, objekat);
                }

                case Operacija.VRATI_SVE_PONUDE_AGENCIJE -> {
                    objekat = Controller.getInstance().vratiSvePonudeAgencije((Integer) korisnikovZahtev.getParametar());
                    return new Odgovor(StatusPoruke.OK, objekat);
                }

                case Operacija.VRATI_POSLEDNJI_ID_PONUDE -> {
                    objekat = Controller.getInstance().vratiPoslednjiIdPonude();
                    return new Odgovor(StatusPoruke.OK, objekat);
                }

                case Operacija.VRATI_PONUDU_PO_ID -> {
                    objekat = Controller.getInstance().vratiPonuduPoId((Integer) korisnikovZahtev.getParametar());
                    return new Odgovor(StatusPoruke.OK, objekat);
                }

                case Operacija.PROMENA_PONUDE -> {
                    boolean signal = Controller.getInstance().promeniPonudaVeslaca((TransferObjekat) korisnikovZahtev.getParametar());
                    if(signal){
                       return new Odgovor(StatusPoruke.OK, transferObj); 
                    }      
                }

                case Operacija.VRATI_SVE_STAVKE_PONUDE -> {
                    objekat = Controller.getInstance().vratiSveStavkePonude((Integer) korisnikovZahtev.getParametar());
                    return new Odgovor(StatusPoruke.OK, objekat);
                }
                case Operacija.UBACIVANJE_DRZAVA -> {
                    boolean signal = Controller.getInstance().ubaciDrzava((TransferObjekat) korisnikovZahtev.getParametar());
                    if(signal){
                       return new Odgovor(StatusPoruke.OK, transferObj);  
                    }
                    
                }

                case Operacija.BRISANJE_DRZAVA -> {
                    boolean signal = Controller.getInstance().obrisiDrzava((TransferObjekat) korisnikovZahtev.getParametar());
                    if(signal){
                      return new Odgovor(StatusPoruke.OK, transferObj);   
                    }
                    
                }

                case Operacija.VRATI_SVE_DRZAVE -> {
                    objekat = Controller.getInstance().vratiListuSviDrzava();
                    return new Odgovor(StatusPoruke.OK, objekat);
                }
                case Operacija.OSVOJI_TAKMICENJE -> {
                    objekat = Controller.getInstance().dodajOsvojenoTakmicenje((KlubTakmicenje) korisnikovZahtev.getParametar());
                    return new Odgovor(StatusPoruke.OK, objekat);
                }

                case Operacija.BRISANJE_OSVOJENO_TAKMICENJE -> {
                    objekat = Controller.getInstance().obrisiOsvojenoTakmicenje((KlubTakmicenje) korisnikovZahtev.getParametar());
                    return new Odgovor(StatusPoruke.OK, objekat);
                }

                case Operacija.VRATI_TAKMICENJA_KLUBA -> {
                    objekat = Controller.getInstance().vratiTakmicenjaKluba((Integer) korisnikovZahtev.getParametar());
                    return new Odgovor(StatusPoruke.OK, objekat);
                }

                case Operacija.PREBROJ_OSVOJENA_TAKMICENJA -> {
                    objekat = Controller.getInstance().prebrojOsvojenaTakmicenja((Integer) korisnikovZahtev.getParametar());
                    return new Odgovor(StatusPoruke.OK, objekat);
                }
                case Operacija.PREKID -> {
                    soketZaKomunikaciju.close();
                    klijenti.remove(this);
                    return new Odgovor(StatusPoruke.OK, "Konekcija prekinuta");
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
            return new Odgovor(StatusPoruke.GRESKA, ex);
        }
        throw new RuntimeException("Ne bi trebalo da se dodje do ovde");
    }

}
