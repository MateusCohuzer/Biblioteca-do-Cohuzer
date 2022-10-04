package Main;

import GUI.MenuPrincipal;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import org.json.simple.parser.ParseException;

/**
 *
 * @author radames
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws MalformedURLException, IOException, ParseException, UnsupportedAudioFileException, LineUnavailableException {
        MenuPrincipal menuPrincipal = new MenuPrincipal();
    }
}
