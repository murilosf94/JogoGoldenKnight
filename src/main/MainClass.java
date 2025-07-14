package main;

public class MainClass {

	public static void main(String[] args) {
        AudioManager audioManager = new AudioManager();
        
        audioManager.playBackgroundMusic("/res/musicabackgroundgkg (2).wav");
		
		new Game();
	}

}
