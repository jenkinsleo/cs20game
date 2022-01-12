import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;
public class GameAnimation implements Animation {

	private static int universeCount = 0;
	private static int retry = 0;
	private static long score = 0000;
	
	private static String car1 = "res/tiles/car3.png";
	private static String car2 = "res/tiles/dababy.png";
	
	private static String bcar1 = "res/tiles/car2.png";
	private static String bcar2 = "res/tiles/dababy.png";
	
	private static String car1song = "res/sounds/tokyodrift.wav";
	private static String car2song = "res/sounds/roof.wav";
	
	
	
	private static int currentCar = 1;
	private static int maxCars = 2;
	
	public static AudioPlayer theme = new AudioPlayer();
	public static AudioPlayer theme1 = new AudioPlayer();
	
	
	
	public static void playCurrent() {
		AudioPlayer.setStopAll(false);
		theme.setStop(true);
		theme1.setStop(true);
		
		if (currentCar == 1) {
			//System.out.println("yes");
			if(theme.isPlayCompleted()) {
				theme.playAsynchronous(car1song);
			} else {
				theme.setStop(false);
			}
			
		} else {
			if(theme1.isPlayCompleted()) {
				theme1.playAsynchronous(car2song);
			} else {
				theme1.setStop(false);
			} 
		}
		
	}
	
	
	public int getUniverseCount() {
		return universeCount;
	}
	
	public static String getCurrentCar() {
		
		if (currentCar == 1) {
			return car1;
		} else if (currentCar == 2){
			return car2;
		} else {
			return car1;
		}
	}
	
	public static String getBCar() {
		if (currentCar == 1) {
			return bcar1;
		} else {
			return bcar2;
		}
	}
	
	public static void changeCar(int direction) {
		
		if (direction == 0) {
			GameAnimation.currentCar ++;
		} else {
			GameAnimation.currentCar --;
		}
		
		if (GameAnimation.currentCar > GameAnimation.maxCars) {
			GameAnimation.currentCar = 1;
		} else if (GameAnimation.currentCar <= 0) {
			GameAnimation.currentCar = GameAnimation.maxCars;
		}
	}

	public static void setUniverseCount(int count) {
		GameAnimation.universeCount = count;
	}

	public Universe getNextUniverse() {
		
		
		
		universeCount ++;
		
		
		
		if (universeCount == 1) {
			return new MenuUniverse();
		} else if(universeCount == 2) {
			
			return null;
			
			
		} else if(universeCount == 3) {
			AudioPlayer.setStopAll(true);
			//tutorial 
			return new LevelUniverse("res/maps/tutorial.csv", 0, 8,3);
		} else if(universeCount == 4) {
			//level 1
			AudioPlayer.setStopAll(true);
			return new LevelUniverse("res/maps/level1.csv", 1, 16,16);
			
		} else if(universeCount == 5) {
			return new LevelUniverse("res/maps/level2actual.csv", 2, 22,7);
		} else if(universeCount == 6) {
			//level 3
			
			return new LevelUniverse("res/maps/v2level2.csv", 3, 22,6);
		}
		else {
			return new MenuUniverse();
		}

	}

	@Override
	public int getRetry() {
		// TODO Auto-generated method stub
		return retry;
	}
	
	public static long getScore() {
		if (score < 10) {
			return 0;
		} else {
			return score;
		}
		
	}
	
	@Override
	public void setRetry(int retry) {
		// TODO Auto-generated method stub
		GameAnimation.retry = retry;
		
		
	}
	public static void resetScore() {
		
		GameAnimation.score = 0;
	}
	public static void addScore(long score) {
		GameAnimation.score += score;
	}

	
	
}
