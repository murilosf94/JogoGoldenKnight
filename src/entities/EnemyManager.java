package entities;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import gamestates.Playing;
import levels.Level;
import utilz.LoadSave;
import static utilz.Constants.EnemyConstants.*;

public class EnemyManager {

	private Playing playing;
	private BufferedImage[][] inimigofogo1Arr, bolainimigoArr, inimigofogo2Arr;
	private Level currentLevel;

	public EnemyManager(Playing playing) {
		this.playing = playing;
		loadEnemyImgs();
	}

	public void loadEnemies(Level level) {
		this.currentLevel = level;
	}

	public void update(int[][] lvlData) {
		boolean isAnyActive = false;
		for (inimigofogo1 c : currentLevel.getinimigofogo1())
			if (c.isActive()) {
				c.update(lvlData, playing);
				isAnyActive = true;
			}

		for (Bolainimigo p : currentLevel.getBolainimigo())
			if (p.isActive()) {
				p.update(lvlData, playing);
				isAnyActive = true;
			}

		for (inimigofogo2 s : currentLevel.getinimigofogo2())
			if (s.isActive()) {
				s.update(lvlData, playing);
				isAnyActive = true;
			}

		if (!isAnyActive)
			playing.setLevelCompleted(true);
	}

	public void draw(Graphics g, int xLvlOffset) {
		drawinimigofogo1(g, xLvlOffset);
		drawBolainimigo(g, xLvlOffset);
		drawinimigofogo2(g, xLvlOffset);
	}

	private void drawinimigofogo2(Graphics g, int xLvlOffset) {
		for (inimigofogo2 s : currentLevel.getinimigofogo2())
			if (s.isActive()) {
				g.drawImage(inimigofogo2Arr[s.getState()][s.getAniIndex()], (int) s.getHitbox().x - xLvlOffset - INIMIGOFOGO2_DRAWOFFSET_X + s.flipX(),
						(int) s.getHitbox().y - INIMIGOFOGO2_DRAWOFFSET_Y + (int) s.getPushDrawOffset(), INIMIGOFOGO2_WIDTH * s.flipW(), INIMIGOFOGO2_HEIGHT, null);
//				s.drawHitbox(g, xLvlOffset);
//				s.drawAttackBox(g, xLvlOffset);
			}
	}

	private void drawBolainimigo(Graphics g, int xLvlOffset) {
		for (Bolainimigo p : currentLevel.getBolainimigo())
			if (p.isActive()) {
				g.drawImage(bolainimigoArr[p.getState()][p.getAniIndex()], (int) p.getHitbox().x - xLvlOffset - BOLAINIMIGO_DRAWOFFSET_X + p.flipX(),
						(int) p.getHitbox().y - BOLAINIMIGO_DRAWOFFSET_Y + (int) p.getPushDrawOffset(), BOLAINIMIGO_WIDTH * p.flipW(), BOLAINIMIGO_HEIGHT, null);
//				p.drawHitbox(g, xLvlOffset);
			}
	}

	private void drawinimigofogo1(Graphics g, int xLvlOffset) {
		for (inimigofogo1 c : currentLevel.getinimigofogo1())
			if (c.isActive()) {

				g.drawImage(inimigofogo1Arr[c.getState()][c.getAniIndex()], (int) c.getHitbox().x - xLvlOffset - INIMIGOFOGO1_DRAWOFFSET_X + c.flipX(),
						(int) c.getHitbox().y - INIMIGOFOGO1_DRAWOFFSET_Y + (int) c.getPushDrawOffset(), INIMIGOFOGO1_WIDTH * c.flipW(), INIMIGOFOGO1_HEIGHT, null);

//				c.drawHitbox(g, xLvlOffset);
//				c.drawAttackBox(g, xLvlOffset);
			}

	}

	public void checkEnemyHit(Rectangle2D.Float attackBox) {
		for (inimigofogo1 c : currentLevel.getinimigofogo1())
			if (c.isActive())
				if (c.getState() != DEAD && c.getState() != HIT)
					if (attackBox.intersects(c.getHitbox())) {
						c.hurt(20);
						return;
					}

		for (Bolainimigo p : currentLevel.getBolainimigo())
			if (p.isActive()) {
				if (p.getState() == ATTACK && p.getAniIndex() >= 3)
					return;
				else {
					if (p.getState() != DEAD && p.getState() != HIT)
						if (attackBox.intersects(p.getHitbox())) {
							p.hurt(20);
							return;
						}
				}
			}

		for (inimigofogo2 s : currentLevel.getinimigofogo2())
			if (s.isActive()) {
				if (s.getState() != DEAD && s.getState() != HIT)
					if (attackBox.intersects(s.getHitbox())) {
						s.hurt(20);
						return;
					}
			}
	}

	private void loadEnemyImgs() {
		inimigofogo1Arr = getImgArr(LoadSave.GetSpriteAtlas(LoadSave.INIMIGOFOGO1), 9, 5, INIMIGOFOGO1_WIDTH_DEFAULT, INIMIGOFOGO1_HEIGHT_DEFAULT);
		bolainimigoArr = getImgArr(LoadSave.GetSpriteAtlas(LoadSave.BOLAINIMIGO), 8, 5, BOLAINIMIGO_WIDTH_DEFAULT, BOLAINIMIGO_HEIGHT_DEFAULT);
		inimigofogo2Arr = getImgArr(LoadSave.GetSpriteAtlas(LoadSave.INIMIGOFOGO2), 8, 5, INIMIGOFOGO2_WIDTH_DEFAULT, INIMIGOFOGO2_HEIGHT_DEFAULT);
	}

	private BufferedImage[][] getImgArr(BufferedImage atlas, int xSize, int ySize, int spriteW, int spriteH) {
		BufferedImage[][] tempArr = new BufferedImage[ySize][xSize];
		for (int j = 0; j < tempArr.length; j++)
			for (int i = 0; i < tempArr[j].length; i++)
				tempArr[j][i] = atlas.getSubimage(i * spriteW, j * spriteH, spriteW, spriteH);
		return tempArr;
	}

	public void resetAllEnemies() {
		for (inimigofogo1 c : currentLevel.getinimigofogo1())
			c.resetEnemy();
		for (Bolainimigo p : currentLevel.getBolainimigo())
			p.resetEnemy();
		for (inimigofogo2 s : currentLevel.getinimigofogo2())
			s.resetEnemy();
	}

}
