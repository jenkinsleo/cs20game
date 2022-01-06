import java.awt.Image;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import java.lang.Math;

public class CarSprite implements MovableSprite, DisplayableSprite {
	private Image image;
	private Image brake;
	private Image use;
	private Image rotatedImage;
	private static Image[] rotatedImages = new Image[360];
	
	private double ACCELERATION = 400;
	private double ROTATION_SPEED = 180;
	private double TOPSPEED = 1;
	private double currentAngle = 90;
	
	
	private double centerX = 0;
	private double centerY = 0;
	private double width = 150;
	private double height = 150;
	private boolean dispose = false;	

	private final double VELOCITY = 200;
	private double velocityX = 0;
	private double velocityY = 0;
	
	private double forwardSpeed = 0;
	private boolean fail = false;
	
	public CarSprite(double centerX, double centerY) {
		this.centerX = centerX;
		this.centerY = centerY;
		
		if (image == null) {
			try {
				image = ImageIO.read(new File("res/tiles/car3.png"));
				brake = ImageIO.read(new File("res/tiles/car2.png"));
				
			}
			catch (IOException e) {
				System.out.println(e.toString());
			}
			
			
			
		}
		
		if (image != null) {
			use = image;
		}
	}
	public Image getImage() {
		return ImageRotator.rotate(image, (int) currentAngle);
	}
	
	//DISPLAYABLE
	
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
	
	public boolean fail() {
		return fail;
	}
	
	@Override
	public void update(Universe universe, KeyboardInput keyboard, long actual_delta_time) {
		boolean accel = false;
		//LEFT	
		if (keyboard.keyDown(37)) {
			currentAngle -= (ROTATION_SPEED * (actual_delta_time * 0.001));
		}
		// RIGHT
		if (keyboard.keyDown(39)) {
			currentAngle += (ROTATION_SPEED * (actual_delta_time * 0.001));
		}
		//UP
		if (keyboard.keyDown(38)) {
			accel = true;
			forwardSpeed -= ACCELERATION * (actual_delta_time * 0.001);
			
		} else {
			if (forwardSpeed < 0) {
				
				forwardSpeed += (ACCELERATION / 2) * (actual_delta_time * 0.001);
			} else {
				forwardSpeed = 0;
			}
			
		}
		
		// DOWN
		if (keyboard.keyDown(40)) {
			accel = true;
			forwardSpeed += (ACCELERATION * 2) * (actual_delta_time * 0.001);
			image = brake;
		} else {
			image = use;
		}
		
		
		if (currentAngle >= 360) {
			currentAngle -= 360;
		}
		if (currentAngle < 0) {
			currentAngle += 360;
		}	

		currentAngle %= 360;
		if (accel == true) {
			double angleInRadians = Math.toRadians(currentAngle);
			velocityX = Math.cos(angleInRadians) * forwardSpeed * actual_delta_time * 0.01;
			velocityY = Math.sin(angleInRadians) * forwardSpeed * actual_delta_time * 0.01;
		}
		
		
		//calculate new position assuming there are no changes in direction
		double movement_x = (this.velocityX * actual_delta_time * 0.001);
		double movement_y = (this.velocityY * actual_delta_time * 0.001);
		this.centerX = this.centerX + movement_x;
		this.centerY = this.getCenterY()  + movement_y;
		
		testCollision(universe, movement_x, movement_y);
		
	}
	public double getSpeed() {
		// TODO Auto-generated method stub
		return this.forwardSpeed;
	}
	
	private void testCollision(Universe sprites, double deltaX, double deltaY) {

		for (DisplayableSprite sprite : sprites.getSprites()) {
			if (sprite instanceof BarrierSprite) {
				
				if (CollisionDetection.overlaps(this.getMinX() + deltaX, this.getMinY() + deltaY, 
						this.getMaxX()  + deltaX, this.getMaxY() + deltaY, 
						sprite.getMinX(),sprite.getMinY(), 
						sprite.getMaxX(), sprite.getMaxY())) {
					System.out.println("fail");
					//adds score and dispose coin
					fail = true;
					break;					
				}
			}
		}		
	}

}
