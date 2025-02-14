package utils;

import io.github.cdimascio.dotenv.Dotenv;
import java.util.Properties;
import java.util.UUID;
import javax.mail.*;
import javax.mail.internet.*;

/**
 *
 * @author luka
 */
public class EmailAutentikator{
    
    private static Dotenv dotenv = Dotenv.load();
    
    public static String posaljiEmail(String emailPrimaoca){
        
        
        String host = dotenv.get("SMTP_HOST");
        String emailPosiljaoca = dotenv.get("EMAIL_SENDER");
//        String emailPass = dotenv.get("EMAIL_PASS");
        String appPassword = dotenv.get("EMAIL_APP_PASS");
        String uuid = UUID.randomUUID().toString();
        uuid = (String) uuid.subSequence(0, uuid.indexOf("-"));
        
        
        Properties properties = new Properties();
        
        // Namestanje https protokola
        System.setProperty("https.protocols", "TLSv1.2");

        
        properties.put("mail.smtp.host",host);
        properties.put("mail.smtp.port","587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable","true");
        
        // Konfigurisanje smtp protokola
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
        
        Session session = Session.getInstance(properties, new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailPosiljaoca,appPassword);
            }
        });
        
        try{
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emailPosiljaoca));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(emailPrimaoca));
            message.setSubject("Kreiranje naloga sa privremenom lozinkom");
            String emailPoruka = "Pozdrav, vaš nalog je kreiran sa privremenom lozinkom: " + uuid + ", "
                    + "imate 15 minuta da promenite privremenu lozinku, u suprotnom će nalog biti obrisan";
            message.setText(emailPoruka);
            
            Transport.send(message);
            System.out.println("Successfully sent a message");
            
        // TODO instalirati java activation framework da bi MIME radio
        }catch(MessagingException ex){
            ex.printStackTrace();
            return null;
        }
        
        return uuid;
    }
    
}
