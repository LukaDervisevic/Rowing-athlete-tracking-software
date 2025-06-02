package utils;

import controller.Controller;
import java.time.Duration;
import java.time.LocalDateTime;
import model.Nalog;
import model.TipNaloga;
import org.apache.logging.log4j.LogManager;

public class PotvrdaNalogaServis {

    private Nalog nalog;

    private String uuid;
    
    private static final org.apache.logging.log4j.Logger logger = LogManager.getRootLogger();

    public PotvrdaNalogaServis(Nalog nalog, String uuid) {
        this.nalog = nalog;
        this.uuid = uuid;
    }

    public Nalog getNalog() {
        return nalog;
    }

    public String getUuid() {
        return uuid;
    }

    public void obrisiKorisnikaAkoIstekneVreme(Nalog nalog) {
        try {

            if (istekloVreme()) {
                if (nalog.getTipNaloga().equals(TipNaloga.VESLACKI_KLUB)) {

                    Nalog trenutniNalog = Controller.getInstance().vratiVeslackiKlubPoId(nalog.getId());
                    System.out.println(uuid);
                    System.out.println(trenutniNalog);

                    if (trenutniNalog.getSifra().equals(uuid)) {
                        Integer obrisaniId = Controller.getInstance().obrisiVeslackiKlub(trenutniNalog.getId());
                        if (obrisaniId > 0) {
                            System.out.println("Uspesno brisanje korisnika, gasenje...");
                        }
                    }

                } else {
                    Nalog trenutniNalog = Controller.getInstance().vratiAgencijuPoId(nalog.getId());
                    System.out.println(uuid);
                    System.out.println(trenutniNalog);

                    if (trenutniNalog.getSifra().equals(uuid)) {
                        Integer obrisaniId = Controller.getInstance().obrisiAgenciju(trenutniNalog.getId());
                        if (obrisaniId > 0) {
                            System.out.println("Uspesno brisanje korisnika, gasenje...");
                        }
                    }

                }

            }
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }

    }

    public boolean istekloVreme() {
        LocalDateTime trenutno = LocalDateTime.now();
        Duration trajanje = Duration.between(Controller.getInstance().getVremeKreiranjaNaloga(), trenutno);

        return trajanje.toMinutes() > 15;
    }

}
