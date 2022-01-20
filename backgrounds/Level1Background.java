import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;



public class Level1Background implements Background{
	protected static int TILE_WIDTH = 225;
    protected static int TILE_HEIGHT = 225;
    
    private Image road;
    private Image grass;
    private Image barrier;
    private Image turn;
    private Image barrierturn;
    
    private HashMap<Integer, Image> mapDict = new HashMap<Integer, Image>();
    
    private int roadNum = 1;
    private int grassNum = 2;
    private int barrierNum = 3;
    private int turnNum = 9;
    
    private int finish = 0;
    
    private int maxCols = 0;
    private int maxRows = 0;
    
    private int startCol = 3;
    private int startRow = 6;
    
    //lower is less
    private double obstacleSpawnPercent = 0.2;
    
    
    int[][] map;
    
    public Level1Background(int[][] map) {
    	this.map = map;
    	
    	this.maxRows = map.length - 1;
    	this.maxCols = map[0].length - 1;
    	
    	
    	
    	
    	try {
			this.road = ImageIO.read(new File("res/tiles/updated/road.png"));
			this.grass = ImageIO.read(new File("res/tiles/updated/green.png"));
			this.barrier = ImageIO.read(new File("res/tiles/updated/barrier.png"));
			this.turn = ImageIO.read(new File("res/tiles/updated/turn.png"));
			this.barrierturn = ImageIO.read(new File("res/tiles/updated/barrier_turn.png"));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
    	System.out.println(Arrays.deepToString(map));
    	try {
    		
    		
    		mapDict.put(0, road);
    		mapDict.put(1, road);
    		mapDict.put(2, ImageRotator.rotate(ImageRotator.rotate(road, 270), 180));
    		mapDict.put(3, ImageRotator.rotate(ImageRotator.rotate(road, 270), 180));
    		mapDict.put(-1, grass);
    		mapDict.put(4, barrier);
    		mapDict.put(5, ImageRotator.rotate(ImageRotator.rotate(barrier, 270), 180));
    		mapDict.put(6, ImageRotator.rotate(barrier, 180));
    		mapDict.put(7, ImageRotator.rotate(barrier, 270));
    		mapDict.put(8, barrierturn);
    		mapDict.put(9, ImageRotator.rotate(ImageRotator.rotate(barrierturn, 270), 180));
    		mapDict.put(10, ImageRotator.rotate(barrierturn, 180));
    		mapDict.put(11, ImageRotator.rotate(barrierturn, 270));
    		mapDict.put(12, turn);
    		mapDict.put(13, ImageRotator.rotate(ImageRotator.rotate(turn, 270), 180));
    		mapDict.put(14, ImageRotator.rotate(turn, 180));
    		mapDict.put(15, ImageRotator.rotate(turn, 270));
    		
    		mapDict.put(16, road);
    		mapDict.put(17, road);
    		mapDict.put(18, ImageRotator.rotate(ImageRotator.rotate(road, 270), 180));
    		mapDict.put(19, ImageRotator.rotate(ImageRotator.rotate(road, 270), 180));
    		
    		
    		
    		
    		
    	} catch (Exception e) {
    		//do nothing
    	}
    }

	@Override
	public Tile getTile(int col, int row) {
		Image image = null;
		
		try {
			image = mapDict.get(map[row][col]);
		} catch (Exception e) {
			image = null;
		}
			
		int x = (col * TILE_WIDTH);
		int y = (row * TILE_HEIGHT);
		
		Tile newTile = new Tile(image, x, y, TILE_WIDTH, TILE_HEIGHT, false);
		
		return newTile;
	}

	@Override
	public int getCol(int x) {
		// TODO Auto-generated method stub
		int col = 0;
		if (TILE_WIDTH != 0) {
			col = (x / TILE_WIDTH);
			if (x < 0) {
				return col - 1;
			}
			else {
				return col;
			}
		}
		else {
			return 0;
		}
	}

	@Override
	public int getRow(int y) {
		// TODO Auto-generated method stub
		int row = 0;
		
		if (TILE_HEIGHT != 0) {
			row = (y / TILE_HEIGHT);
			if (y < 0) {
				return row - 1;
			}
			else {
				return row;
			}
		}
		else {
			return 0;
		}
	}
	
	
	
	public ArrayList<DisplayableSprite> getBarriers() {
		ArrayList<DisplayableSprite> barriers = new ArrayList<DisplayableSprite>();
		System.out.println(this.maxRows);
		System.out.println(this.maxCols);
		for (int row = 0; row < map.length; row++) {
			for (int col = 0; col < map[0].length; col++) {
				try {
					//System.out.println(map[row][col]);
					if (map[row][col] >= 4 && map[row][col] <= 11) {
						barriers.add(new BarrierSprite((col * TILE_WIDTH) + 30, (row * TILE_HEIGHT) + 30, ((col + 1) * TILE_WIDTH) - 30, ((row + 1) * TILE_HEIGHT) - 30, false));
					} else if (map[row][col]  >= 16 && map[row][col] <= 19) {
						barriers.add(new ExitSprite((col * TILE_WIDTH) + 30, (row * TILE_HEIGHT) + 30, ((col + 1) * TILE_WIDTH) - 30, ((row + 1) * TILE_HEIGHT) - 30, true));
					}
					
					if (map[row][col] == 0 || map[row][col] == 1) {
						double rand = Math.random();
						
						if (rand < obstacleSpawnPercent) {
							//System.out.println("yes");
							barriers.add(new ObstacleSprite(col * TILE_WIDTH, row * TILE_HEIGHT, false));
							//barriers.add(new ObstacleSprite(col * TILE_WIDTH, row * TILE_HEIGHT, false));
						}
					}
					} catch (Exception e) {
					//e.printStackTrace();
				}
				 
			}
		}
		return barriers;
	}

}
