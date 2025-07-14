package utilz;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;

import javax.imageio.ImageIO;

public class LoadSave {

	public static final String PLAYER_ATLAS = "spritescavaleirogkg.png";
	public static final String LEVEL_ATLAS = "outsidesprites1gkg.png";
	public static final String MENU_BUTTONS = "botoesmenugkg.png";
	public static final String MENU_BACKGROUND = "backgrougmenugkg.png";
	public static final String PAUSE_BACKGROUND = "pausebackgrounggkg.png";
	public static final String MENU_BACKGROUND_IMG = "backgroundmeugkg.png";
	public static final String PLAYING_BG_IMG = "backgrounggkg.png";
	public static final String INIMIGOFOGO1 = "inimigofogo1gkg.png";
	public static final String STATUS_BAR = "barradevidagkg.png";
	public static final String COMPLETED_IMG = "levelcompletogkg.png";
	public static final String TRAP_ATLAS = "trap_atlas.png";
	public static final String CANHAODEFOGO = "canhaodefogogkg.png";
	public static final String BOLADEFOGO = "boladefogogkg.png";
	public static final String DEATH_SCREEN = "telamortegkg.png";
	public static final String OPTIONS_MENU = "options_background.png";
	public static final String BOLAINIMIGO = "inimigobolagkg.png";
	public static final String INIMIGOFOGO2 = "inimigofogo2gkg.png";
	public static final String PLANTA = "planta_atlas1gkg.png";
	public static final String GAME_COMPLETED = "game_completed.png";
	public static final String LAVAANIMACAO = "lavaatlasanimation1gkg.png";
	public static final String LAVA = "lava1gkg.png";
	public static final String URM_BUTTONS = "urm_buttons.png";


	public static BufferedImage GetSpriteAtlas(String fileName) {
		BufferedImage img = null;
		InputStream is = LoadSave.class.getResourceAsStream("/res/" + fileName);
		try {
			img = ImageIO.read(is);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return img;
	}

	public static BufferedImage[] GetAllLevels() {
		URL url = LoadSave.class.getResource("/res/lvls");
		File file = null;

		try {
			file = new File(url.toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		File[] files = file.listFiles();
		File[] filesSorted = new File[files.length];

		for (int i = 0; i < filesSorted.length; i++)
			for (int j = 0; j < files.length; j++) {
				if (files[j].getName().equals((i + 1) + ".png"))
					filesSorted[i] = files[j];

			}

		BufferedImage[] imgs = new BufferedImage[filesSorted.length];

		for (int i = 0; i < imgs.length; i++)
			try {
				imgs[i] = ImageIO.read(filesSorted[i]);
			} catch (IOException e) {
				e.printStackTrace();
			}

		return imgs;
	}

}