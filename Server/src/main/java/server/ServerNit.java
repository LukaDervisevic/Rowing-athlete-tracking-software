package server;

import kontroller.Controller;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;
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
                        System.out.println("Klijent je poslao null zahtev prekida se veza");
                        break;
                    }

                    Object odgovorServera = obradiZahtev(korisnikovZahtev);
                    posiljalac.posaljiPoruku(odgovorServera);

                } catch (EOFException ex) {
                    logger.info("Klijent je zatvorio soket: " + ex);
                    ex.printStackTrace();
                    break;
                    
                } catch (SocketException ex) {
                    logger.info("Soket klijenta se gasi");
                    ex.printStackTrace();
                    break;

                } catch (IOException | ClassNotFoundException ex) {
                    logger.error(ex.getMessage());
                    ex.printStackTrace();
                } catch (Exception ex) {
                    logger.error("Greska pri obradi: " + ex);
                    ex.printStackTrace();
                }

            }

        } catch (IOException ex) {
            ex.printStackTrace();
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

        TransferObjekat transferObj;

        try {
            transferObj = (TransferObjekat) korisnikovZahtev.getParametar();
            switch (korisnikovZahtev.getOperacija()) {
                case Operacija.PRIJAVA -> {
                    boolean signal;
                    if (transferObj.getOdo() instanceof VeslackiKlub) {
                        signal = Controller.getInstance().prijaviVeslackiKlub(transferObj);
                    } else {
                        signal = Controller.getInstance().prijaviAgencija(transferObj);

                    }

                    if (signal) {
                        return new Odgovor(StatusPoruke.OK, transferObj);
                    } else {
                        transferObj.setOdo(null);
                        return new Odgovor(StatusPoruke.GRESKA, transferObj);
                    }

                }
                case Operacija.KREIRANJE_KLUB -> {
                    boolean signal = Controller.getInstance().ubaciVeslackiKlub(transferObj);
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
                    if (signal) {
                        return new Odgovor(StatusPoruke.OK, transferObj);
                    }

                }

                case Operacija.VRATI_LISTU_SVI_VESLACKI_KLUB -> {
                    boolean signal = Controller.getInstance().vratiListuSviKlub(transferObj);
                    if (signal) {
                        return new Odgovor(StatusPoruke.OK, transferObj);
                    }
                }

                case Operacija.VRATI_LISTU_VESLACKI_KLUB -> {
                    boolean signal = Controller.getInstance().vratiListuKlub(transferObj);
                    if (signal) {
                        return new Odgovor(StatusPoruke.OK, transferObj);
                    }
                }
                case Operacija.KREIRANJE_AGENCIJA -> {
                    boolean signal = Controller.getInstance().UbaciAgenciju((TransferObjekat) korisnikovZahtev.getParametar());
                    if (signal) {
                        return new Odgovor(StatusPoruke.OK, transferObj);
                    }

                }

                case Operacija.PROMENA_AGENCIJA -> {
                    boolean signal = Controller.getInstance().promeniAgencija((TransferObjekat) korisnikovZahtev.getParametar());
                    if (signal) {
                        return new Odgovor(StatusPoruke.OK, transferObj);
                    }

                }

                case Operacija.PRETRAZIVANJE_AGENCIJA -> {
                    boolean signal = Controller.getInstance().pretraziAgencija((TransferObjekat) korisnikovZahtev.getParametar());
                    if (signal) {
                        return new Odgovor(StatusPoruke.OK, transferObj);
                    }

                }

                case Operacija.BRISANJE_AGENCIJA -> {
                    boolean signal = Controller.getInstance().obrisiAgenciju((TransferObjekat) korisnikovZahtev.getParametar());
                    if (signal) {
                        return new Odgovor(StatusPoruke.OK, transferObj);
                    }

                }

                case Operacija.VRATI_LISTU_SVE_AGENCIJE -> {
                    boolean signal = Controller.getInstance().vratiListuSviAgencija(transferObj);
                    if (signal) {
                        return new Odgovor(StatusPoruke.OK, transferObj);
                    }
                }

                case Operacija.VRATI_LISTU_AGENCIJE -> {
                    boolean signal = Controller.getInstance().vratiListuAgencija(transferObj, transferObj.getWhereUslov());
                    if (signal) {
                        return new Odgovor(StatusPoruke.OK, transferObj);
                    }
                }

                case Operacija.KREIRANJE_VESLAC -> {
                    boolean signal = Controller.getInstance().ubaciVeslac(transferObj);
                    if (signal) {
                        return new Odgovor(StatusPoruke.OK, transferObj);
                    }
                }

                case Operacija.PRETRAZIVANJE_VESLAC -> {
                    boolean signal = Controller.getInstance().pretraziVeslac(transferObj);
                    if (signal) {
                        return new Odgovor(StatusPoruke.OK, transferObj);
                    }
                }

                case Operacija.PROMENA_VESLAC -> {
                    boolean signal = Controller.getInstance().promeniVeslaca(transferObj);
                    if (signal) {
                        return new Odgovor(StatusPoruke.OK, transferObj);
                    }
                }

                case Operacija.BRISANJE_VESLAC -> {
                    boolean signal = Controller.getInstance().obrisiVeslac((TransferObjekat) korisnikovZahtev.getParametar());
                    if (signal) {
                        return new Odgovor(StatusPoruke.OK, transferObj);
                    }

                }

                case Operacija.VRATI_LISTU_SVI_VESLAC -> {
                    boolean signal = Controller.getInstance().vratiListuSviVeslac(transferObj);
                    if (signal) {
                        return new Odgovor(StatusPoruke.OK, transferObj);
                    }
                }

                case Operacija.VRATI_LISTU_VESLAC -> {
                    boolean signal = Controller.getInstance().vratiListuVeslac(transferObj, transferObj.getWhereUslov());
                    if (signal) {
                        return new Odgovor(StatusPoruke.OK, transferObj);
                    }
                }
                case Operacija.UBACIVANJE_TAKMICENJE -> {
                    boolean signal = Controller.getInstance().ubaciTakmicenje((TransferObjekat) korisnikovZahtev.getParametar());
                    if (signal) {
                        return new Odgovor(StatusPoruke.OK, transferObj);
                    }

                }

                case Operacija.PRETRAZIVANJE_TAKMICENJE -> {
                    boolean signal = Controller.getInstance().pretraziTakmicenje((TransferObjekat) korisnikovZahtev.getParametar());
                    if (signal) {
                        return new Odgovor(StatusPoruke.OK, transferObj);
                    }

                }

                case Operacija.BRISANJE_TAKMICENJE -> {
                    boolean signal = Controller.getInstance().obrisiTakmicenje((TransferObjekat) korisnikovZahtev.getParametar());
                    if (signal) {
                        return new Odgovor(StatusPoruke.OK, transferObj);
                    }

                }

                case Operacija.VRATI_LISTU_SVI_TAKMICENJA -> {
                    boolean signal = Controller.getInstance().vratiListuSviTakmicenje(transferObj);
                    if (signal) {
                        return new Odgovor(StatusPoruke.OK, transferObj);
                    }
                }

                case Operacija.VRATI_LISTU_TAKMICENJA -> {
                    boolean signal = Controller.getInstance().vratiListuTakmicenje(transferObj, transferObj.getWhereUslov());
                    if (signal) {
                        return new Odgovor(StatusPoruke.OK, transferObj);
                    }
                }
                case Operacija.KREIRANJE_PONUDE -> {
                    boolean signal = Controller.getInstance().ubaciPonudaVeslaca((TransferObjekat) korisnikovZahtev.getParametar());
                    if (signal) {
                        return new Odgovor(StatusPoruke.OK, transferObj);
                    }
                }

                case Operacija.PRETRAZIVANJE_PONUDE -> {
                    boolean signal = Controller.getInstance().pretraziPonudaVeslaca((TransferObjekat) korisnikovZahtev.getParametar());
                    if (signal) {
                        return new Odgovor(StatusPoruke.OK, transferObj);
                    }

                }
                case Operacija.BRISANJE_PONUDE -> {
                    boolean signal = Controller.getInstance().obrisiPonudaVeslaca((TransferObjekat) korisnikovZahtev.getParametar());
                    if (signal) {
                        return new Odgovor(StatusPoruke.OK, transferObj);
                    }

                }

                case Operacija.VRATI_LISTU_SVI_PONUDE_VESLACA -> {
                    boolean signal = Controller.getInstance().vratiListuSviPonudaVeslaca(transferObj);
                    if (signal) {
                        return new Odgovor(StatusPoruke.OK, transferObj);
                    }
                }

                case Operacija.VRATI_LISTU_PONUDE_VESLACA -> {
                    boolean signal = Controller.getInstance().vratiListuPonudaVeslaca(transferObj, transferObj.getWhereUslov());
                    if (signal) {
                        return new Odgovor(StatusPoruke.OK, transferObj);
                    }
                }

                case Operacija.PROMENA_PONUDE -> {
                    boolean signal = Controller.getInstance().promeniPonudaVeslaca((TransferObjekat) korisnikovZahtev.getParametar());
                    if (signal) {
                        return new Odgovor(StatusPoruke.OK, transferObj);
                    }
                }
                
                case Operacija.UBACIVANJE_DRZAVA -> {
                    boolean signal = Controller.getInstance().ubaciDrzava((TransferObjekat) korisnikovZahtev.getParametar());
                    if (signal) {
                        return new Odgovor(StatusPoruke.OK, transferObj);
                    }

                }

                case Operacija.BRISANJE_DRZAVA -> {
                    boolean signal = Controller.getInstance().obrisiDrzava((TransferObjekat) korisnikovZahtev.getParametar());
                    if (signal) {
                        return new Odgovor(StatusPoruke.OK, transferObj);
                    }

                }

                case Operacija.VRATI_LISTU_SVE_DRZAVE -> {
                    boolean signal = Controller.getInstance().vratiListuSviDrzava(transferObj);
                    if (signal) {
                        return new Odgovor(StatusPoruke.OK, transferObj);
                    }
                }
                // STA CU SA OVIM ???
//                case Operacija.KREIRANJE_KLUB_TAKMICENJE -> {
//                    objekat = Controller.getInstance().kreirajKlubTakmicenje((TransferObjekat) korisnikovZahtev.getParametar());
//                    return new Odgovor(StatusPoruke.OK, objekat);
//                }
//
//                case Operacija.BRISANJE_KLUB_TAKMICENJE -> {
//                    objekat = Controller.getInstance().obrisiKlubTakmicenje((TransferObjekat) korisnikovZahtev.getParametar());
//                    return new Odgovor(StatusPoruke.OK, objekat);
//                }
//
//                case Operacija.VRATI_LISTU_KLUB_TAKMICENJA -> {
//                    boolean signal = Controller.getInstance().vratiListuTakmicenje(transferObj, transferObj.getWhereUslov());
//                    if (signal) {
//                        return new Odgovor(StatusPoruke.OK, transferObj);
//                    }
//                }

//                case Operacija.PREBROJ_TAKMICENJA -> {
//                    objekat = Controller.getInstance().prebrojOsvojenaTakmicenja((Integer) korisnikovZahtev.getParametar());
//                    return new Odgovor(StatusPoruke.OK, objekat);
//                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return new Odgovor(StatusPoruke.GRESKA, ex);
        }
        return null;
    }

}
