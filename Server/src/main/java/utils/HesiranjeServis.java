package utils;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class HesiranjeServis {
    
    public static String hesirajSifru(String sifra){
        return BCrypt.withDefaults().hashToString(10, sifra.toCharArray());
    }
    
    public static boolean proveriSifru(String sifra, String hesiranaSifra){
        BCrypt.Result rezultat = BCrypt.verifyer().verify(sifra.toCharArray(), hesiranaSifra);
        return rezultat.verified;
    }

}
