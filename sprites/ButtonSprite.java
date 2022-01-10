import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ButtonSprite implements MovableSprite, DisplayableSprite{
	private double centerX = 0;
	private double centerY = 0;
	private double width = 110;
	private double height = 110;
	private boolean dispose = false;
	
	
	private boolean active = false;
	
	private Image image;
	private Image activeImage;
	
	public ButtonSprite(double centerX, double centerY, Image image1, Image image2) {
		this.centerX = centerX;
		this.centerY = centerY;
		
		this.image = image1;
		this.activeImage = image2;
		
		this.width = image.getWidth(null);
		this.height = image.getHeight(null);
		
	}

	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		if (active == true) {
			return this.activeImage;
		} else {
			return this.image;
		}
		
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
	
	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public void update(Universe universe, KeyboardInput keyboard, long actual_delta_time) {
		
		
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