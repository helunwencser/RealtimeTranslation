import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.ibm.watson.developer_cloud.text_to_speech.v1.util.WaveUtils;


/**
 * This class provides as a demo for how to use {@link TextToVoice.java}
 * @author helunwen
 * */
public class Test {
	public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException {
		TextToVoice textToVoice = new TextToVoice();

		AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(WaveUtils.reWriteWaveHeader(textToVoice.synthesizeVoice("Hello world!")));

		Clip test = AudioSystem.getClip();  

        test.open(audioInputStream);
        test.start();

        while (!test.isRunning())
            Thread.sleep(10);
        while (test.isRunning())
            Thread.sleep(10);

        test.close();
	}
}
