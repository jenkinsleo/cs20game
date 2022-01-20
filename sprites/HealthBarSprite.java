import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class HealthBarSprite implements MovableSprite, DisplayableSprite{
	private double centerX = 0;
	private double centerY = 0;
	private double width = 110;
	private double height = 110;
	private boolean dispose = false;
	
	
	private boolean active = false;
	
	
	private Image noHealth;
	private Image lastHealth;
	private Image halfHealth;
	private Image fullHealth;
	
	
	public HealthBarSprite(double centerX, double centerY) {
		this.centerX = centerX;
		this.centerY = centerY;
		
		try {
			noHealth = ImageIO.read(new File("res/heart/0.png"));
			lastHealth = ImageIO.read(new File("res/heart/1.png"));
			halfHealth = ImageIO.read(new File("res/heart/2.png"));
			fullHealth = ImageIO.read(new File("res/heart/3.png"));
		}catch (Exception e) {
			
		}
		
		this.width = 200;
		this.height = 62.5;
		
		
		
	}

	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		if (GameAnimation.getHealth() == 0) {
			return this.noHealth;
		} else if (GameAnimation.getHealth() == 1) {
			return this.lastHealth;
		} else if (GameAnimation.getHealth() == 2) {
			return this.halfHealth;
		} else if (GameAnimation.getHealth() == 3) {
			return this.fullHealth;
		} else {
			return this.noHealth;
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
		this.centerX = centerX;
	}

	@Override
	public void setCenterY(double centerY) {
		// TODO Auto-generated method stub
		this.centerY = centerY;
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