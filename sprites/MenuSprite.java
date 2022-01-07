import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MenuSprite implements MovableSprite, DisplayableSprite{
	private double centerX = 0;
	private double centerY = 0;
	private double width = 110;
	private double height = 110;
	private boolean dispose = false;
	private double initPosX;
	private double initPosY;
	
	private double xSpeed = 125;
	private double ySpeed = 0;
	
	private double maxChangeY = 1000;
	private double maxChangeX = 0;
	
	private Image image;
	
	public MenuSprite(double centerX, double centerY) {
		this.centerX = centerX;
		this.centerY = centerY;
		
		if (image == null) {
			try {
				image = ImageIO.read(new File("res/tiles/car3.png"));
				
			}
			catch (IOException e) {
				System.out.println(e.toString());
			} 
		}
		
		this.initPosX = centerX;
		this.initPosY = centerY;
		
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
		double movement_y = (this.xSpeed * actual_delta_time * 0.001);
		double movement_x = (this.ySpeed * actual_delta_time * 0.001);
		this.centerX += movement_x;
		this.centerY -= movement_y;
		
		if (this.initPosX - this.centerX > this.maxChangeX || this.initPosY - this.centerY > this.maxChangeY) {
			this.centerX = initPosX;
			this.centerY = initPosY;
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

}
