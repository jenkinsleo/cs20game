import java.util.ArrayList;

import javax.imageio.ImageIO;

import java.awt.Image;
import java.io.File;


public class BetweenLevelUniverse implements Universe {

	private boolean complete = false;	
	private Background background = null;	
	private DisplayableSprite player1 = null;
	private ArrayList<DisplayableSprite> sprites = new ArrayList<DisplayableSprite>();

	private double xCenter = 600;
	private double yCenter = 500;
	
	private int count;
	private boolean pass;
	
	private DisplayableSprite passMessage;
	private DisplayableSprite failMessage;
	private ButtonSprite continueButton;
	private ButtonSprite retryButton;
	private ButtonSprite menuButton;
	
	
	private Image continue1;
	private Image continue2;
	private Image retry1;
	private Image retry2;
	private Image menu1;
	private Image menu2;
	
	private Image volume;
	private Image mute;
	
	private UselessSprite littleCar;
	private UselessSprite arrows;
	private ButtonSprite muteButton;
	
	private int activeNum = 1;
	
	private int timeChange = 150;
	private double lastTime = 0;
	
	
	public BetweenLevelUniverse (boolean passfail, int count) {
		double xPos = (AnimationFrame.SCREEN_WIDTH / 2) + 75;
		this.count = count;
		this.pass = passfail;
		this.background = new Level1Background(CSVReader.importFromCSV("res/maps/betweenmenumap.csv"), false);
		
		try {
			this.continue1 = ImageIO.read(new File("res/betweenlevelbuttons/continueblank.png"));
			this.continue2 = ImageIO.read(new File("res/betweenlevelbuttons/continueactive.png"));
			this.retry1 = ImageIO.read(new File("res/betweenlevelbuttons/retryblank.png"));
			this.retry2 = ImageIO.read(new File("res/betweenlevelbuttons/retryactive.png"));
			this.menu1 = ImageIO.read(new File("res/betweenlevelbuttons/menublank.png"));
			this.menu2 = ImageIO.read(new File("res/betweenlevelbuttons/menuactive.png"));
			
			this.volume = ImageIO.read(new File("res/buttons/volume.png"));
			this.mute = ImageIO.read(new File("res/buttons/mute.png"));
			
			littleCar = new UselessSprite(780, 690);			
			arrows = new UselessSprite(780, 690, false, "res/buttons/arrows.png", 150, 110);
			muteButton = new ButtonSprite(780, 550, volume,mute, 130, 130);
			
			
			this.continueButton = new ButtonSprite(xPos - 50, 550, continue1, continue2);
			this.retryButton = new ButtonSprite(xPos - 50,550, retry1, retry2);
			this.menuButton = new ButtonSprite(xPos - 50,690, menu1,menu2);
			
			
			
		} catch (Exception e) {
			
		}
		
		try {
			
			this.passMessage = new UselessSprite(xPos + 75, 200 * 1.75, false, "res/betweenlevel/levelpass.png", 600,200);
			this.failMessage = new UselessSprite(xPos + 75, 200 * 1.75, false, "res/betweenlevel/levelfail.png", 600 ,200);
			
			
			
		}
		catch (Exception e) {
			
			//do nothing
		}
		
		if (passfail) {
			sprites.add(passMessage);
			sprites.add(continueButton);
			
			
			
			
		} else {
			sprites.add(failMessage);
			sprites.add(retryButton);
			
		}
		
		sprites.add(menuButton);
		sprites.add(arrows);
		sprites.add(littleCar);
		sprites.add(muteButton);
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
			
			GameAnimation.setUniverseCount(0);
			complete = true;
		}

		
		
		
		
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
			activeNum = 2;
		} else if(activeNum > 2) {
			activeNum = 1;
		}
		
		if (activeNum == 1) {
			continueButton.setActive(true);
			retryButton.setActive(true);
			menuButton.setActive(false);
		} else if (activeNum == 2) {
			continueButton.setActive(false);
			retryButton.setActive(false);
			menuButton.setActive(true);
		}  else {
			continueButton.setActive(true);
			retryButton.setActive(true);
			menuButton.setActive(false);
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
		//enter
		
		if (keyboard.keyDown(10)) {
			complete = true;
			if (activeNum == 1) {
				complete = true;
				if (this.pass == true) {
					GameAnimation.setUniverseCount(count);
				} else {
					GameAnimation.setUniverseCount(count);
				}
				
			} else {
				GameAnimation.setUniverseCount(0);
			}
			
		}
		
		

	}
	
	public String toString() {
		if (this.pass == true) {
			return "Level Pass";
		} else {
			return "Level Fail";
		}
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