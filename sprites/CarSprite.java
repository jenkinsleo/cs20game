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
	
	private double ACCELERATION = 1000;
	private double ROTATION_SPEED = 15;
	public static double TOPSPEED = 30;
	private double currentAngle = 90;
	
	private double lastAngle = 90;
	private double newAngle = 90;
	
	
	private double centerX = 0;
	private double centerY = 0;
	private double width = 95;
	private double height = 95;
	private boolean dispose = false;	

	private final double VELOCITY = 200;
	private double velocityX = 0;
	private double velocityY = 0;
	
	private double forwardSpeed = 0;
	private boolean fail = false;
	private boolean complete = false;
	
	
	
	
	
	private int driftMsg = 0;
	
	private long firstTime;
	
	private boolean currentlyDrifting = false;
	private int notDriftSpeed = 0;
	
	
	private long score = 0;
	private String mcqueen = "res/tiles/mcqueencar.png";
	
	public CarSprite(double centerX, double centerY) {
		this.centerX = centerX;
		this.centerY = centerY;
		
		lastAngle = currentAngle;
		
		if (image == null) {
				
			
				try {
					image = ImageIO.read(new File(GameAnimation.getCurrentCar()));
					brake = ImageIO.read(new File(GameAnimation.getBCar()));
					
					
					
					if (GameAnimation.getCurrentCar() == mcqueen) {
						this.ACCELERATION *= 1.5;
						this.TOPSPEED = 40;
					}
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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
	
	public boolean getComplete() {
		return complete;
	}
	public long returnScore() {
		return score;
	}
	@Override
	public void update(Universe universe, KeyboardInput keyboard, long actual_delta_time) {
		boolean accel = false;
		//LEFT	
		if (keyboard.keyDown(37)) {
			currentAngle -= ((ROTATION_SPEED * this.getSpeed()) * (actual_delta_time * 0.001));
		}
		// RIGHT
		if (keyboard.keyDown(39)) {
			currentAngle += ((ROTATION_SPEED * this.getSpeed()) * (actual_delta_time * 0.001));
		}
		//UP
		if (keyboard.keyDown(38)) {
			this.currentlyDrifting = false;
			accel = true;
			if(this.getSpeed() < this.TOPSPEED) {
				forwardSpeed -= ACCELERATION * (actual_delta_time * 0.001);
			}
			
			
		} else {
			if (forwardSpeed < 0) {
				
				forwardSpeed += (900 * 0.6) * (actual_delta_time * 0.001);
			} else {
				forwardSpeed = 0;
			}
			
		}
		
		// DOWN
		if (keyboard.keyDown(40)) {
			this.currentlyDrifting = false;
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
		
		
		
		if (accel == true) {
			lastAngle = currentAngle;
			newAngle = lastAngle;
			
			currentAngle %= 360;
			double angleInRadians = Math.toRadians(currentAngle);
			velocityX = Math.cos(angleInRadians) * forwardSpeed * actual_delta_time * 0.01;
			velocityY = Math.sin(angleInRadians) * forwardSpeed * actual_delta_time * 0.01;
			
			
		} else {
			int change;
			
			if (this.getSpeed() < TOPSPEED -5) {
				change = 15;
			} else {
				change = 11;
			}
			if (getAngleChange() > 15) {
				lastAngle %= 360;
				lastAngle += change * actual_delta_time * 0.01;
				if (lastAngle >= 360) {
					lastAngle -= 360;
				}
				if (lastAngle < 0) {
					lastAngle += 360;
				}
			} else if (getAngleChange() < -15) {
				lastAngle %= 360;
				lastAngle -= change * actual_delta_time * 0.01;
				if (lastAngle >= 360) {
					lastAngle -= 360;
				}
				if (lastAngle < 0) {
					lastAngle += 360;
				}
			}
			
			double angleInRadians = Math.toRadians(lastAngle);
			velocityX = Math.cos(angleInRadians) * forwardSpeed * actual_delta_time * 0.01;
			velocityY = Math.sin(angleInRadians) * forwardSpeed * actual_delta_time * 0.01;
		}
		
		
		
		//calculate new position assuming there are no changes in direction
		double movement_x = (this.velocityX * actual_delta_time * 0.001);
		double movement_y = (this.velocityY * actual_delta_time * 0.001);
		this.centerX = this.centerX + movement_x;
		this.centerY = this.centerY  + movement_y;
		
		testCollision(universe, movement_x, movement_y);
		isAtExit(universe, movement_x, movement_y);
		makeScore(actual_delta_time);
		
		
		
		
	}
	public double getSpeed() {
		// TODO Auto-generated method stub
		return (this.forwardSpeed  * -1) / 100;
	}
	
	private void testCollision(Universe sprites, double deltaX, double deltaY) {

		for (DisplayableSprite sprite : sprites.getSprites()) {
			if (sprite instanceof BarrierSprite) {
				
				if (CollisionDetection.overlaps(this.getMinX() + deltaX, this.getMinY() + deltaY, 
						this.getMaxX()  + deltaX, this.getMaxY() + deltaY, 
						sprite.getMinX(),sprite.getMinY(), 
						sprite.getMaxX(), sprite.getMaxY())) {
					
					
					
					fail = true;
					break;					
				}
			}
		}		
	}
	
	private void isAtExit(Universe sprites, double deltaX, double deltaY) {

		for (DisplayableSprite sprite : sprites.getSprites()) {
			if (sprite instanceof ExitSprite) {
				
				if (CollisionDetection.overlaps(this.getMinX() + deltaX, this.getMinY() + deltaY, 
						this.getMaxX()  + deltaX, this.getMaxY() + deltaY, 
						sprite.getMinX(),sprite.getMinY(), 
						sprite.getMaxX(), sprite.getMaxY())) {
					System.out.println("fail");
					//adds score and dispose coin
					
					//plays sound
					
					complete = true;
					break;					
				}
			}
		}		
	}
	private double getAngleChange() {
		

		double a = currentAngle - lastAngle;
		a = (a + 180) % 360 - 180;
		
		return a;
	}
	
	private double getActualChange() {
		double a = currentAngle - newAngle;
		a = (a + 180) % 360 - 180;
		
		return a;
	}
	private void makeScore(long actual_delta_time) {
		//adds bit of score for every amount of time over halfway of max speed
		long newScore = 0;
		
		if (this.getSpeed() == TOPSPEED) {
			newScore += actual_delta_time * 0.06;
			
		} else if(this.getSpeed() > TOPSPEED - 5) {
			newScore += actual_delta_time * 0.02;
		} else if(this.getSpeed() > TOPSPEED / 2) {
			newScore += actual_delta_time * 0.01;
		}
		
		double actualChange = getActualChange();
		if (currentlyDrifting == false) {
			notDriftSpeed = (int) this.getSpeed();
		} else if(this.getSpeed() < notDriftSpeed - 5) {
			actualChange = 0;
		}
		
		
		if (actualChange > 90 || actualChange * -1 > 90 ) {
			currentlyDrifting = true;
			newScore += actual_delta_time * 0.2;
			this.driftMsg = 2;
		} else if (actualChange > 45 || actualChange * -1 > 45) {
			currentlyDrifting = true;
			newScore += actual_delta_time * 0.1;
			this.driftMsg = 1;
		} else {
			
			this.driftMsg = 0;
		}
		
		
		GameAnimation.addScore(newScore);
		
		
		
	}
	
	public int driftType() {
		return this.driftMsg;
	}

}
