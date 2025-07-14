package main;

public class MainClass {

	public static void main(String[] args) {
        AudioManager audioManager = new AudioManager();
        
        audioManager.playBackgroundMusic("/res/musicabackgroundgkg.wav");
		
		new Game();
	}

}
