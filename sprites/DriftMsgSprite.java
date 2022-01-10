


import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class DriftMsgSprite implements MovableSprite, DisplayableSprite{
	private double centerX = 0;
	private double centerY = 0;
	private double width = 110;
	private double height = 110;
	private boolean dispose = false;
	
	
	private int active = 0;
	
	private Image good;
	private Image great;
	private boolean visible = true;
	
	public DriftMsgSprite(double centerX, double centerY){
		this.centerX = centerX;
		this.centerY = centerY;
		
			
				try {
					this.good = ImageIO.read(new File("res/good.png"));
					this.great = ImageIO.read(new File("res/great.png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			
			
			
			
			
		
		
		
		this.width = good.getWidth(null);
		this.height = good.getHeight(null);
		
	}

	@Override
	public Image getImage() {
		
		if (active == 1) {
			
			return this.great;
		} else if (active == 2) {
			
			return this.good;
		} else {
			
			return null;
		}
		
	}

	public boolean getVisible() {
		return this.visible;
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
	
	public void setActive(int active) {
		this.active = active;
	}

	@Override
	public void update(Universe universe, KeyboardInput keyboard, long actual_delta_time) {
		if (this.active == 0) {
			this.visible = false;
			
		} else {
			this.visible = true;
		}
		
	}

	@Override
	public void setCenterX(double centerX) {
		this.centerX = centerX;
		
	}

	@Override
	public void setCenterY(double centerY) {
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

