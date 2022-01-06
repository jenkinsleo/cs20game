import java.util.ArrayList;

public class LevelUniverse implements Universe {


	private boolean complete = false;	
	private Background background = null;	
	private DisplayableSprite player1 = null;
	private ArrayList<DisplayableSprite> sprites = new ArrayList<DisplayableSprite>();
	private double xCenter = 0;
	private double yCenter = 0;
	private boolean failure = false;
	private int retry = 0;

	public LevelUniverse () {

		background = new Level1Background();
		ArrayList<DisplayableSprite> barriers = ((Level1Background)background).getBarriers();
		
		((Level1Background) background).getBarriers();
		
		int startCol = ((Level1Background) background).getStartCol();
		int startRow = ((Level1Background) background).getStartRow();
		
		
		player1 = new CarSprite(Level1Background.TILE_HEIGHT * (startCol + 0.5), Level1Background.TILE_WIDTH * (startRow + 0.5));
		
		sprites.add(player1);
		sprites.addAll(barriers);

	}

	public double getScale() {
		return 1;
	}
	public int getRetry() {
		return this.retry;
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
		return true;
	}
	
	public boolean getFailure() {
		return this.failure;
	}
	
	public void update(KeyboardInput keyboard, long actual_delta_time) {
		
		if (keyboard.keyDownOnce(27) || ((CarSprite) player1).fail()) {
			complete = true;
			failure = true;
			retry ++;
			
		}
		
		for (int i = 0; i < sprites.size(); i++) {
			DisplayableSprite sprite = sprites.get(i);
			sprite.update(this, keyboard, actual_delta_time);
			//System.out.println(sprite.getClass().toString());
			
			//((SimpleSprite) player1).getFailure();
    	}
		
		
	}

	public String toString() {
		return "Leo's game";
	}

	

	@Override
	public int getCarSpeed() {
		// TODO Auto-generated method stub
		
		return (int) (((CarSprite)player1).getSpeed() * -1) / 100;
		
	}	

}
