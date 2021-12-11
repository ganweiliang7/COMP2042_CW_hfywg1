package sound;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Sound
{

    public static void playSound(File file) throws LineUnavailableException, UnsupportedAudioFileException, IOException {
        Clip clip = AudioSystem.getClip();



        AudioInputStream ais = AudioSystem.getAudioInputStream(file);
        clip.open(ais);
        clip.start();
    }
}
