package transfer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.net.Socket;

/**
 *
 * @author luka
 */
public class Primalac implements Serializable {

    private final Socket soket;
    private final ObjectInputStream ois;

    public Primalac(Socket soket) throws IOException {
        this.soket = soket;
        this.ois = new ObjectInputStream(this.soket.getInputStream());
    }

    public Object primiPoruku() throws Exception {
        return ois.readObject();
    }

}
