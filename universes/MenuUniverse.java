import java.util.ArrayList;

import javax.imageio.ImageIO;

import java.awt.Image;
import java.io.File;


public class MenuUniverse implements Universe {

	private boolean complete = false;	
	private Background background = null;	
	private DisplayableSprite player1 = null;
	private ArrayList<DisplayableSprite> sprites = new ArrayList<DisplayableSprite>();
	private int startCol = 1;
	private int startRow = 4;
	private double xCenter = 600;
	private double yCenter = 500;
	
	private Image play1;
	private Image play2;
	private Image tutorial1;
	private Image tutorial2;
	private Image quit1;
	private Image quit2;
	
	private Image volume;
	private Image mute;
	
	
	
	private ButtonSprite play;
	private ButtonSprite tutorial;
	private ButtonSprite quit;
	
	private ButtonSprite muteButton;
	
	private UselessSprite littleCar;
	private UselessSprite arrows;
	
	private int timeChange = 150;
	private double lastTime = 0;
	
	private int activeNum = 1;
	
	public MenuUniverse () {
		
		try {
			
			play1 = ImageIO.read(new File("res/buttons/play1.png"));
			play2 = ImageIO.read(new File("res/buttons/play2.png"));
			tutorial1 = ImageIO.read(new File("res/buttons/tutorial1.png"));
			tutorial2 = ImageIO.read(new File("res/buttons/tutorial2.png"));
			quit1 = ImageIO.read(new File("res/buttons/quit1.png"));
			quit2 = ImageIO.read(new File("res/buttons/quit2.png"));
			
			volume = ImageIO.read(new File("res/buttons/volume.png"));
			mute = ImageIO.read(new File("res/buttons/mute.png"));
			
			
			
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		littleCar = new UselessSprite(200 * (4.5), 200 * (4));
		sprites.add(littleCar);
		
		arrows = new UselessSprite(200 * (4.5), 200 * (4), false, "res/buttons/arrows.png", 150, 110);
		sprites.add(arrows);
		
		player1 = new MenuSprite(225 * (startCol + 0.5), 200 * (startRow + 1.25));
		sprites.add(player1);
		
		
		play = new ButtonSprite(200 * (4), 200 * (1.25), play1, play2);
		sprites.add(play);
		
		tutorial = new ButtonSprite(200 * (4), 200 * (2.25), tutorial1, tutorial2);
		sprites.add(tutorial);
		
		quit = new ButtonSprite(200 * (4), 200 * (3.25), quit1, quit2);
		sprites.add(quit);
		
		muteButton = new ButtonSprite(200 * 3.5, 200 * 4, volume,mute, 130, 130);
		sprites.add(muteButton);
		
		play.setActive(true);
		tutorial.setActive(true);
		quit.setActive(true);
		
		
		
		this.background = new Level1Background(CSVReader.importFromCSV("res/maps/menumap.csv"), false);
	
	}
	
	public double getScale() {
		return 1;
	}

	public double getXCenter() {
		return this.xCenter;
	}

	public double getYCenter() {
		return this.yCenter;
	}
	
	public void setXCenter(double xCenter) {
		this.xCenter = xCenter;
	}

	public void setYCenter(double yCenter) {
		this.yCenter = yCenter;
	}
	
	public boolean isComplete() {
		return complete;
	}

	public void setComplete(boolean complete) {
		complete = true;
	}

	public Background getBackground() {
		return background;
	}

	public DisplayableSprite getPlayer1() {
		return player1;
	}

	public ArrayList<DisplayableSprite> getSprites() {
		return sprites;
	}
		
	public boolean centerOnPlayer() {
		return false;
	}		
	
	public void update(KeyboardInput keyboard, long actual_delta_time) {
		GameAnimation.playCurrent();
		if (keyboard.keyDownOnce(27)) {
			
			//System.out.println("complete");
			complete = true;
		}
		//left
		if (keyboard.keyDown(37) && System.currentTimeMillis() - lastTime > timeChange) {
			lastTime = System.currentTimeMillis();
			GameAnimation.changeCar(0);
		} 
		//right
		if (keyboard.keyDown(39) && System.currentTimeMillis() - lastTime > timeChange) {
			lastTime = System.currentTimeMillis();
			GameAnimation.changeCar(0);
		}
		
		//up
		if (keyboard.keyDown(38) && System.currentTimeMillis() - lastTime > timeChange) {
			lastTime = System.currentTimeMillis();
			activeNum -= 1;
			}
		//down
		else if (keyboard.keyDown(40) && System.currentTimeMillis() - lastTime > timeChange) {
			lastTime = System.currentTimeMillis();
			activeNum += 1;
			}
		
		if (activeNum < 1) {
			activeNum = 3;
		} else if(activeNum > 3) {
			activeNum = 1;
		}
		
		if (activeNum == 1) {
			play.setActive(true);
			quit.setActive(false);
			tutorial.setActive(false);
		} else if (activeNum == 2) {
			play.setActive(false);
			quit.setActive(false);
			tutorial.setActive(true);
		} else if (activeNum == 3) {
			play.setActive(false);
			quit.setActive(true);
			tutorial.setActive(false);
		} else {
			play.setActive(true);
			quit.setActive(false);
			tutorial.setActive(false);
		}
		
		if (keyboard.keyDownOnce(77)) {
			muteButton.setActive(!muteButton.getActive());
			
		}
		
		if (muteButton.getActive() == false) {
			GameAnimation.overrideStop(false);
		} else {
			GameAnimation.overrideStop(true);
		}
		
		
		
		for (int i = 0; i < sprites.size(); i++) {
			DisplayableSprite sprite = sprites.get(i);
			sprite.update(this, keyboard, actual_delta_time);
    	}
		
		if (keyboard.keyDown(10)) {
			complete = true;
			if (activeNum == 2) {
				GameAnimation.setUniverseCount(2);
			} else if(activeNum == 1) {
				GameAnimation.setUniverseCount(3);
			} else if (activeNum == 3) {
				GameAnimation.setUniverseCount(1);
			}
			
		}
		
		

	}
	
	public String toString() {
		return "Main Menu";
	}

	@Override
	public boolean getFailure() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getRetry() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getCarSpeed() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean getMenu() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long getScore() {
		// TODO Auto-generated method stub
		return 0;
	}	

}