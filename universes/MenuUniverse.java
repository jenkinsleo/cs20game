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
	
	private ButtonSprite play;
	private ButtonSprite tutorial;
	private ButtonSprite quit;
	
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
			
			
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
	
		player1 = new MenuSprite(Level1Background.TILE_WIDTH * (startCol + 0.5), Level1Background.TILE_HEIGHT * (startRow + 1.25));
		sprites.add(player1);
		
		
		play = new ButtonSprite(Level1Background.TILE_WIDTH * (4), Level1Background.TILE_HEIGHT * (1.5), play1, play2);
		sprites.add(play);
		
		tutorial = new ButtonSprite(Level1Background.TILE_WIDTH * (4), Level1Background.TILE_HEIGHT * (2.5), tutorial1, tutorial2);
		sprites.add(tutorial);
		
		quit = new ButtonSprite(Level1Background.TILE_WIDTH * (4), Level1Background.TILE_HEIGHT * (3.5), quit1, quit2);
		sprites.add(quit);
		
		play.setActive(true);
		tutorial.setActive(true);
		quit.setActive(true);
		
		
		this.background = new Level1Background(new int[][] {
			{2,1,2,2,2,2},
			{2,1,2,2,2,2},
			{2,1,2,2,2,2},
			{2,1,2,2,2,2},
			{2,1,2,2,2,2}
		});
	
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

		if (keyboard.keyDownOnce(27)) {
			
			//System.out.println("complete");
			complete = true;
		}
		
		//up
		if (keyboard.keyDown(38) && System.currentTimeMillis() - lastTime > 100) {
			lastTime = System.currentTimeMillis();
			activeNum -= 1;
			}
		//down
		else if (keyboard.keyDown(40) && System.currentTimeMillis() - lastTime > 100) {
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

}