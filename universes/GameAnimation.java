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
	
	private static String suscar1 = "res/tiles/suscar.png";
	private static String suscar2 = "res/tiles/suscar1.png";
	
	private static String shrekcar1 = "res/tiles/shrekcar.png";
	private static String shrekcar2 = "res/tiles/shrekb.png";
	
	private static String queencar1 = "res/tiles/mcqueencar.png";
	private static String queencar2 = "res/tiles/mcqueencar.png";
	
	
	
	
	private static String car1song = "res/sounds/tokyodrift.wav";
	private static String car2song = "res/sounds/roof.wav";
	private static String suscarSong = "res/sounds/sus.wav";
	private static String carsCarSong = "res/sounds/lifeishighway.wav";
	private static String shrekCarSong = "res/sounds/shrek.wav";
	
	
	private static int currentCar = 1;
	private static int maxCars = 5;
	
	public static AudioPlayer theme = new AudioPlayer();
	public static AudioPlayer theme1 = new AudioPlayer();
	
	public static AudioPlayer susTheme = new AudioPlayer();
	public static AudioPlayer carsTheme = new AudioPlayer();
	public static AudioPlayer shrekTheme = new AudioPlayer();
	
	
	private static int health = 3;
	private static int maxHealth = 3;
	
	private static boolean stopAll;
	
	
	public static void overrideStop(boolean stop) {
		AudioPlayer.setStopAll(stop);
		stopAll = stop;
	}
	
	public static int getHealth() {
		return health;
	}
	
	public static void lowerHealth() {
		health --;
	}
	
	public static void resetHealth() {
		health = maxHealth;
	}
	
	
	public static void playCurrent() {
		if (stopAll == false) {
			
			
			AudioPlayer.setStopAll(false);
			theme.setStop(true);
			theme1.setStop(true);
			susTheme.setStop(true);
			carsTheme.setStop(true);
			shrekTheme.setStop(true);
			
			if (currentCar == 1) {
				//System.out.println("yes");
				if(theme.isPlayCompleted()) {
					theme.playAsynchronous(car1song);
				} else {
					theme.setStop(false);
				}
				
			} else if (currentCar == 2) {
				if(theme1.isPlayCompleted()) {
					theme1.playAsynchronous(car2song);
				} else {
					theme1.setStop(false);
				} 
			}
			
			else if (currentCar == 3) {
				if(susTheme.isPlayCompleted()) {
					susTheme.playAsynchronous(suscarSong);
				} else {
					susTheme.setStop(false);
				}
			}
			
			else if (currentCar == 4) {
				if(shrekTheme.isPlayCompleted()) {
					shrekTheme.playAsynchronous(shrekCarSong);
				} else {
					shrekTheme.setStop(false);
				}
			}
			
			else if (currentCar == 5) {
				if(carsTheme.isPlayCompleted()) {
					carsTheme.playAsynchronous(carsCarSong);
				} else {
					carsTheme.setStop(false);
				}
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
		} else if (currentCar == 3) {
			return suscar1;
		} else if (currentCar == 4) {
			return shrekcar1;
		} else if (currentCar == 5) {
			return queencar1;
		} else {
			return car1;
		}
	}
	
	public static String getBCar() {
		if (currentCar == 1) {
			return bcar1;
		} else  if (currentCar == 2){
			return bcar2;
		} else if (currentCar == 3) {
			return suscar2;
		} else if (currentCar == 4) {
			return shrekcar2;
		} else if (currentCar == 5) {
			return queencar2;
		} else {
			return bcar1;
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
			AudioPlayer.setStopAll(false);
			//tutorial 
			return new TutorialUniverse("res/maps/tutorial.csv", 0, 8,3);
		} else if(universeCount == 4) {
			//level 1
			AudioPlayer.setStopAll(false);
			return new LevelUniverse("res/maps/level1.csv", 1, 16,16);
			
		} else if(universeCount == 5) {
			//level 2
			return new LevelUniverse("res/maps/level2.csv", 2, 22,6);
		} else if(universeCount == 6) {
			
			//level 3
			
			return new LevelUniverse("res/maps/level3.csv", 3, 22,5);
		} else if(universeCount == 7) {
			//level 4
			
			return new LevelUniverse("res/maps/level4.csv", 3, 22,6);
		} else if(universeCount == 8) {
			//level 5
			
			return new LevelUniverse("res/maps/level5.csv", 3, 22,6);
		} else {
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
