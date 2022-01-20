import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ObstacleSprite implements MovableSprite, DisplayableSprite{
	private double centerX = 0;
	private double centerY = 0;
	private double width = 40;
	private double height = 60;
	private boolean dispose = false;
	
	private double xSpeed = 50;
	private double ySpeed = 50;
	
	private boolean pauseVar = false;
	
	
	private Image image;
	
	
	//true means Y
	//false means X
	private boolean xyDirection;
	
	
	private boolean axisDirection;
	
	
	
	
	public ObstacleSprite(double centerX, double centerY, boolean move) {
		if (move == false) {
			centerX += Math.random() * 100;
		} else {
			centerY += Math.random() * 100;
		}
		this.centerX = centerX;
		this.centerY = centerY;
		
		
		
		this.xyDirection = move;
		
		
		//get random starting direction
		double direct = Math.random();
		
		if (direct > 0.5) {
			axisDirection = true;
		} else {
			axisDirection = false;
		}
		
		if (image == null) {
			try {
				image = ImageIO.read(new File("res/pylon.png"));
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		}
		
		
		
		
		
		
		
	}

	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return this.image;
	}

	public boolean getVisible() {
		return true;
	}
	
	public double getMinX() {
		return centerX - (width / 2);
	}

	public double getMaxX() {
		return centerX + (width / 2);
	}

	public double getMinY() {
		return centerY - (height / 2);
	}

	public double getMaxY() {
		return centerY + (height / 2);
	}

	public double getHeight() {
		return height;
	}

	public double getWidth() {
		return width;
	}

	public double getCenterX() {
		return centerX;
	};

	public double getCenterY() {
		return centerY;
	};
	
	
	public boolean getDispose() {
		return dispose;
	}

	@Override
	public void update(Universe universe, KeyboardInput keyboard, long actual_delta_time) {
		
		double deltaX;
		double deltaY;
		
		if (pauseVar == false) {
			if (this.xyDirection) {
				deltaX = 0;
				deltaY = this.xSpeed * actual_delta_time * 0.001;
				
			} else {
				deltaY = 0;
				deltaX = this.ySpeed * actual_delta_time * 0.001;
				
			}
		
		} else {
			deltaX = 0;
			deltaY = 0;
		}
		if (axisDirection == true) {
			deltaX *= 1;
			deltaY *= 1;
		} else {
			deltaX *= -1;
			deltaY *= -1;
		}
		
		this.centerX += deltaX;
		this.centerY += deltaY;
		
		for (DisplayableSprite sprite :universe.getSprites()) {
			if (sprite instanceof BarrierSprite) {
				
				if (CollisionDetection.overlaps(this.getMinX() + deltaX, this.getMinY() + deltaY, 
						this.getMaxX()  + deltaX, this.getMaxY() + deltaY, 
						sprite.getMinX(),sprite.getMinY(), 
						sprite.getMaxX(), sprite.getMaxY())) {
					
					
					axisDirection = !axisDirection;
					
					
					
					break;					
				}
			}
		}
		
	}

	@Override
	public void setCenterX(double centerX) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCenterY(double centerY) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveX(double pixelsPerSecond) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveY(double pixelsPerSecond) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}
	
	public void pause(boolean pausevar) {
		this.pauseVar = pausevar;
	}
	
	public void setDispose(boolean dispose) {
		this.dispose = dispose;
	}
	

}
