package server;

import controller.Controller;
import java.net.Socket;
import java.util.List;
import model.Agencija;
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

/**
 *
 * @author luka
 */
class ServerNit extends Thread {

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
            // inicijalizacija object stream-ova

            while (true) {

                // Primanje zahteva od korisnika
                Zahtev korisnikovZahtev = (Zahtev) primalac.primiPoruku();
                // Obrada Zahteva

                Object odgovorServera = obradiZahtev(korisnikovZahtev);

                //Slanje zahteva
                posiljalac.posaljiPoruku(odgovorServera);

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private synchronized Object obradiZahtev(Zahtev korisnikovZahtev){

        Object objekat;

        try {

            switch (korisnikovZahtev.getOperacija()) {
                case Operacija.PRIJAVA:
                    objekat = Controller.getInstance().login((Nalog) korisnikovZahtev.getParametar());
                    return new Odgovor(StatusPoruke.OK, objekat);

                //VESLACKI KLUB
                case Operacija.REGISTRACIJA_KLUB:
                    objekat = Controller.getInstance().kreirajVeslackiKlub((VeslackiKlub) korisnikovZahtev.getParametar());
                    return new Odgovor(StatusPoruke.OK, objekat);

                case Operacija.PROMENA_KLUB:
                    objekat = Controller.getInstance().azuirirajVeslackiKlub((VeslackiKlub) korisnikovZahtev.getParametar());
                    return new Odgovor(StatusPoruke.OK, objekat);

                case Operacija.PRETRAZIVANJE_KLUB:
                    objekat = Controller.getInstance().pretraziKlub((String) korisnikovZahtev.getParametar());
                    return new Odgovor(StatusPoruke.OK, objekat);

                case Operacija.BRISANJE_KLUB:
                    objekat = Controller.getInstance().obrisiVeslackiKlub((Integer) korisnikovZahtev.getParametar());
                    return new Odgovor(StatusPoruke.OK, objekat);

                //AGENCIJA
                case Operacija.REGISTRACIJA_AGENCIJA:
                    objekat = Controller.getInstance().kreirajAgenciju((Agencija) korisnikovZahtev.getParametar());
                    return new Odgovor(StatusPoruke.OK, objekat);

                case Operacija.PROMENA_AGENCIJA:
                    objekat = Controller.getInstance().azurirajAgenciju((Agencija) korisnikovZahtev.getParametar());
                    return new Odgovor(StatusPoruke.OK, objekat);

//            case Operacija.PRETRAZIVANJE_AGENCIJA:
                case Operacija.BRISANJE_AGENCIJA:
                    objekat = Controller.getInstance().obrisiAgenciju((Integer) korisnikovZahtev.getParametar());
                    return new Odgovor(StatusPoruke.OK, objekat);
                    
                // VESLAC
                case Operacija.KREIRANJE_VESLAC:
                    objekat = Controller.getInstance().kreirajVeslaca((Veslac) korisnikovZahtev.getParametar());
                    return new Odgovor(StatusPoruke.OK, objekat);

                case Operacija.PRETRAZIVANJE_VESLAC:
                    objekat = Controller.getInstance().pretraziVeslaca((Veslac) korisnikovZahtev.getParametar());
                    return new Odgovor(StatusPoruke.OK, objekat);

                case Operacija.PROMENA_VESLAC:
                    objekat = Controller.getInstance().azurirajVeslaca((Veslac) korisnikovZahtev.getParametar());
                    return new Odgovor(StatusPoruke.OK, objekat);

                case Operacija.BRISANJE_VESLAC:
                    objekat = Controller.getInstance().obrisiVeslaca((Integer) korisnikovZahtev.getParametar());
                    return new Odgovor(StatusPoruke.OK, objekat);
                    
                // TAKMICENJE
                case Operacija.UBACIVANJE_TAKMICENJE:
                    objekat = Controller.getInstance().dodajTakmicenje((Takmicenje) korisnikovZahtev.getParametar());
                    return new Odgovor(StatusPoruke.OK, objekat);

                case Operacija.PRETRAZIVANJE_TAKMICENJE:
                    objekat = Controller.getInstance().pretraziTakmicenja((String) korisnikovZahtev.getParametar());
                    return new Odgovor(StatusPoruke.OK, objekat);

                case Operacija.BRISANJE_TAKMICENJE:
                    objekat = Controller.getInstance().obrisiTakmicenje((Integer) korisnikovZahtev.getParametar());
                    return new Odgovor(StatusPoruke.OK, objekat);

                case Operacija.KREIRANJE_PONUDE:
                    objekat = Controller.getInstance().kreirajPonuduVeslaca((PonudaVeslaca) korisnikovZahtev.getParametar());
                    return new Odgovor(StatusPoruke.OK, objekat);

                case Operacija.PRETRAZIVANJE_PONUDE:
                    objekat = Controller.getInstance().pretraziPonudu((PonudaVeslaca) korisnikovZahtev.getParametar());
                    return new Odgovor(StatusPoruke.OK, objekat);

                //case Operacija.PROMENA_PONUDE: 
                case Operacija.BRISANJE_PONUDE:
                    objekat = Controller.getInstance().obrisiPonudu((Integer) korisnikovZahtev.getParametar());
                    return new Odgovor(StatusPoruke.OK, objekat);

                case Operacija.UBACIVANJE_DRZAVA:
//                return Controller.getInstance().dodajDrzavu((Drzava) korisnikovZahtev.getParametar());

                case Operacija.BRISANJE_DRZAVA:
//                return Controller.getInstance().obrisiDrzavu((Integer) korisnikovZahtev.getParametar());
                
                case Operacija.PREKID:
                    soketZaKomunikaciju.close();
                    klijenti.remove(this);
                    return new Odgovor(StatusPoruke.OK,"Konekcija prekinuta");
                
                default:
                    return new Odgovor(StatusPoruke.GRESKA, "Zeljena operacija ne postoji");
            }

        } catch (Exception ex) {
            return new Odgovor(StatusPoruke.GRESKA,ex);
        }
    }

}
