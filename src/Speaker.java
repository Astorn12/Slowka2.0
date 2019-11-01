import com.voicerss.tts.*;


import java.io.*;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 * Created by osiza on 26.09.2019.
 */
public class Speaker
{
    public void speak(String text) throws Exception
    {
        VoiceProvider tts = new VoiceProvider(
                "9a6a12fb325547438a1733b6521de731"
        );

        VoiceParameters params = new VoiceParameters(text, Languages.French_France);
        params.setCodec(AudioCodec.WAV);
        params.setFormat(AudioFormat.Format_44KHZ.AF_44khz_16bit_stereo);
        params.setBase64(false);
        params.setSSML(false);
        params.setRate(0);

        tts.addSpeechErrorEventListener(new SpeechErrorEventListener() {
            @Override
            public void handleSpeechErrorEvent(SpeechErrorEvent e) {
                System.out.print(e.getException().getMessage());
            }
        });

        tts.addSpeechDataEventListener(new SpeechDataEventListener() {
            @Override
            public void handleSpeechDataEvent(SpeechDataEvent e) {
                try {
                    byte[] voice = (byte[])e.getData();

                    FileOutputStream fos = new FileOutputStream("voice.mp3");
                    fos.write(voice, 0, voice.length);
                    fos.flush();
                    fos.close();
                    play();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        tts.speechAsync(params);



    }

    public void play() throws IOException {
        InputStream in=new FileInputStream("voice.mp3");
        AudioStream as=new AudioStream(in);
        AudioPlayer.player.start(as);
    }


}
