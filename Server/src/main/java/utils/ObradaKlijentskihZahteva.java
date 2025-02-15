package utils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import operacije.Odgovor;
import operacije.Primalac;
import operacije.Zahtev;

/**
 *
 * @author Luka
 */
public class ObradaKlijentskihZahteva extends Thread {

    private Socket s;

    public ObradaKlijentskihZahteva(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {

        while (true) {

            Primalac primalac = new Primalac(s);
            Zahtev zahtev = (Zahtev) primalac.primiPoruku();
            Odgovor odgovor = new Odgovor();
            
            
//            switch (var) {
//            case val:
//                
//                break;
//            default:
//                throw new AssertionError();
//        }

        }
     
    }

    private Zahtev primiZahtev() {

        try {
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());

            try {
                return (Zahtev) ois.readObject();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return null;
    }

}
