package utils;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author luka
 */
public class PozadinskiServis {
    
    public static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public static void proveraSifre(PotvrdaNalogaServis servis){
        System.out.println(servis.getNalog());
        Runnable proveriZadatak = () -> {
            servis.obrisiKorisnikaAkoIstekneVreme(servis.getNalog());
        };
        scheduler.scheduleAtFixedRate(proveriZadatak, 0, 1, TimeUnit.MINUTES);
        
        scheduler.schedule(() -> {
            System.out.println("Stopping scheduler after 15 minutes");
            scheduler.shutdown();
        },16,TimeUnit.MINUTES);
    }
    
    
}
