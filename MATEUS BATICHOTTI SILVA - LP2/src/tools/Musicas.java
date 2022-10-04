package tools;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class Musicas {

    public void playBGM() throws UnsupportedAudioFileException, IOException, LineUnavailableException {

        try {
            File file = new File("start.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            
            clip.start();
            clip.loop(-1);
        } catch (Exception e) {
            System.out.println("Archive not founded (music)");
        }
    }
    
    public void playSound(String path) throws UnsupportedAudioFileException, IOException, LineUnavailableException {

        try {
            File file = new File(path);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);

            clip.start();
        } catch (Exception e) {
            System.out.println("Archive not founded (music)");
        }
    }
}
