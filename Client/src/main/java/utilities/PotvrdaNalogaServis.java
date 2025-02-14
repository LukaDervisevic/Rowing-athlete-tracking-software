package utilities;

import controller.Controller;
import java.time.Duration;
import java.time.LocalDateTime;
import model.Nalog;
import model.TipNaloga;


public class PotvrdaNalogaServis {

    private Nalog nalog;

    private String uuid;

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
        if (istekloVreme()) {
            if (nalog.getTipNaloga().equals(TipNaloga.VESLACKI_KLUB)) {

                Nalog trenutniNalog = Controller.getInstance().vratiVeslackiKlubPoId(nalog.getId());
                System.out.println(uuid);
                System.out.println(trenutniNalog);

                if (trenutniNalog.getSifra().equals(uuid)) {
                    boolean uspesno = Controller.getInstance().obrisiVeslackiKlub(trenutniNalog.getId());
                    if (uspesno) {
                        System.out.println("Uspesno brisanje korisnika, gasenje...");
                    }
                }

            } else {
                Nalog trenutniNalog = Controller.getInstance().vratiAgencijuPoId(nalog.getId());
                System.out.println(uuid);
                System.out.println(trenutniNalog);

                if (trenutniNalog.getSifra().equals(uuid)) {
                    boolean uspesno = Controller.getInstance().obrisiAgenciju(trenutniNalog.getId());
                    if (uspesno) {
                        System.out.println("Uspesno brisanje korisnika, gasenje...");
                    }
                }

            }

        }

    }

    public boolean istekloVreme() {
        LocalDateTime trenutno = LocalDateTime.now();
        Duration trajanje = Duration.between(Controller.getInstance().getVremeKreiranjaNaloga(), trenutno);

        return trajanje.toMinutes() > 15;
    }

}
