import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class UselessSprite implements MovableSprite, DisplayableSprite{
	private double centerX = 0;
	private double centerY = 0;
	private double width = 80;
	private double height = 80;
	private boolean dispose = false;
	
	private String lastCar;
	private boolean change = true;
	private Image image;
	
	public UselessSprite(double centerX, double centerY) {
		this.centerX = centerX;
		this.centerY = centerY;
		
		
		if (image == null) {
			try {
				image = ImageIO.read(new File(GameAnimation.getCurrentCar()));
				lastCar = GameAnimation.getCurrentCar();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
	}
	
	public UselessSprite(double centerX, double centerY, boolean change, String imagePath, int width, int height) {
		this.centerX = centerX;
		this.centerY = centerY;
		this.width = width;
		this.height = height;
		
		this.change = change;
		
		if (image == null) {
			try {
				image = ImageIO.read(new File(imagePath));
				lastCar = imagePath;
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
		if (change == true) {
			if (lastCar != GameAnimation.getCurrentCar()) {
				try {
					image = ImageIO.read(new File(GameAnimation.getCurrentCar()));
					lastCar = GameAnimation.getCurrentCar();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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

}
