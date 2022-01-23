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
	private DisplayableSprite continueMsg;
	private DisplayableSprite failMsg;
	
	private DisplayableSprite menuMsg;
	
	public BetweenLevelUniverse (boolean passfail, int count) {
		this.count = count;
		this.pass = passfail;
		this.background = new Level1Background(CSVReader.importFromCSV("res/maps/betweenmenumap.csv"));
		
		try {
			double xPos = (AnimationFrame.SCREEN_WIDTH / 2) + 75;
			this.passMessage = new UselessSprite(xPos, 200 * 1.75, false, "res/betweenlevel/levelpass.png", 600,200);
			this.failMessage = new UselessSprite(xPos, 200 * 1.75, false, "res/betweenlevel/levelfail.png", 600 ,200);
			this.continueMsg = new UselessSprite(xPos, 200 * 2.75, false, "res/betweenlevel/entertocontinue.png", 400 ,150);
			this.failMsg = new UselessSprite(xPos, 200 * 2.75, false, "res/betweenlevel/entertoretry.png", 400 ,150);
			this.menuMsg = new UselessSprite(xPos, 200 * 3.5, false, "res/betweenlevel/esctomenu.png", 400 ,150);
		} catch (Exception e) {
			//do nothing
		}
		
		if (passfail) {
			sprites.add(passMessage);
			sprites.add(continueMsg);
			
		} else {
			sprites.add(failMessage);
			sprites.add(failMsg);
		}
		
		sprites.add(menuMsg);
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

		//enter
		if (keyboard.keyDown(10)) {
			complete = true;
			if (this.pass == true) {
				GameAnimation.setUniverseCount(count);
			} else {
				GameAnimation.setUniverseCount(count);
			}
			
			complete = true;
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