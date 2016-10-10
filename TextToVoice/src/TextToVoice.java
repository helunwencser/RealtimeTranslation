import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.ibm.watson.developer_cloud.text_to_speech.v1.TextToSpeech;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.AudioFormat;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.Voice;

/**
 * This class provides the function of synthesizing English voice from text.
 * @author helunwen
 */
public class TextToVoice {
	
	private Properties properties;
	private TextToSpeech synthesizer;
	
	public TextToVoice() {
		this.properties = new Properties();
		try {
			properties.load(new FileInputStream("resource/credentials.properties"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		this.synthesizer = new TextToSpeech();
		synthesizer.setUsernameAndPassword(properties.getProperty("username"), properties.getProperty("password"));
	}
	
	/**
	 * Synthesize voice stream from text
	 * 
	 * @param text text which will be synthesized as voice input stream
	 * 
	 * @return {@link InputStream} of voice with .wav format
	 * */
	public InputStream synthesizeVoice(String text) {
		return this.synthesizer.synthesize(text, Voice.EN_LISA, AudioFormat.WAV).execute();
	}
}