import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.ibm.watson.developer_cloud.text_to_speech.v1.TextToSpeech;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.AudioFormat;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.Voice;
import com.ibm.watson.developer_cloud.text_to_speech.v1.util.WaveUtils;

/**
 * Translate from English to Spanish and synthesize that as a WAV file.
 */
public class TextToVoice {

  public static void main(String[] args) throws IOException {

    TextToSpeech synthesizer = new TextToSpeech();
    synthesizer.setUsernameAndPassword("58cf06d8-ee55-400a-98e2-918e138ba3e9", "t5Odue7bv4rQ");

    String text = "Greetings from Watson Developer Cloudl";

    // synthesize
    InputStream in = synthesizer.synthesize(text, Voice.EN_LISA, AudioFormat.WAV).execute();
    writeToFile(WaveUtils.reWriteWaveHeader(in), new File("output.wav"));
  }

  /**
   * Write the input stream to a file.
   */
  private static void writeToFile(InputStream in, File file) {
    try {
      OutputStream out = new FileOutputStream(file);
      byte[] buf = new byte[1024];
      int len;
      while ((len = in.read(buf)) > 0) {
        out.write(buf, 0, len);
      }
      out.close();
      in.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}