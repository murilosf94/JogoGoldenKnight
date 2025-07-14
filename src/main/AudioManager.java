package main;

import javax.sound.sampled.*;
import java.io.IOException;
import java.io.InputStream;

public class AudioManager {
    private Clip musica; 

     public void playBackgroundMusic(String caminhomusica) {
        InputStream audioStream = null; // Inicialize como null
        try {
            // Use getResourceAsStream() para carregar o recurso do classpath
            audioStream = getClass().getResourceAsStream(caminhomusica);

            if (audioStream == null) {
                System.err.println("Erro: Arquivo de música não encontrado no classpath: " + caminhomusica);
                return; // Não continue se o stream for nulo
            }

            // Agora use AudioSystem.getAudioInputStream com o InputStream
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(audioStream);

            musica = AudioSystem.getClip();
            musica.open(audioInput);
            musica.loop(Clip.LOOP_CONTINUOUSLY);
            musica.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.err.println("Erro ao carregar ou tocar a música: " + caminhomusica);
            e.printStackTrace();
        } finally {
            // É crucial fechar o InputStream para liberar recursos
            if (audioStream != null) {
                try {
                    audioStream.close();
                } catch (IOException e) {
                    System.err.println("Erro ao fechar o stream da música: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }
    }

    public void stopBackgroundMusic() {
        if (musica != null && musica.isRunning()) {
            musica.stop();
        }
    }


}

