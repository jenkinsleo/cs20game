import java.util.ArrayList;

import javax.imageio.ImageIO;

import java.awt.Image;
import java.io.File;
import java.io.IOException;


public class Level1Background implements Background{
	protected static int TILE_WIDTH = 200;
    protected static int TILE_HEIGHT = 200;
    
    private Image road;
    private Image grass;
    private Image barrier;
    private Image turn;
    
    
    private int roadNum = 1;
    private int grassNum = 2;
    private int barrierNum = 3;
    private int turnNum = 9;
    
    private int maxCols = 0;
    private int maxRows = 0;
    
    private int startCol = 3;
    private int startRow = 6;
    
    
    int[][] map = new int[][] {
    	{2,2,2,2,2,2,2},
    	{2,2,2,3,3,3,3},
    	{2,2,3,9,1,1,1},
    	{2,2,3,1,3,2,2},
    	{2,2,3,1,3,2,2},
    	{2,2,3,1,3,2,2},
    	{2,2,3,1,3,2,2}
    };
    
    public Level1Background() {
    	this.maxRows = map.length - 1;
    	this.maxCols = map[0].length - 1;
    	
    	
    	try {
			this.road = ImageIO.read(new File("res/tiles/road.png"));
			this.grass = ImageIO.read(new File("res/tiles/green.png"));
			this.barrier = ImageIO.read(new File("res/tiles/barrier.png"));
			this.turn = ImageIO.read(new File("res/tiles/turn.png"));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
    	
    	
    }

	@Override
	public Tile getTile(int col, int row) {
		Image image = null;
		
		if (row < 0 || row > maxRows || col < 0 || col > maxCols) {
			image = null;
		}
		else if (map[row][col] == roadNum) {
			image = road;
		}
		else if (map[row][col] == grassNum) {
			image = grass;
		}
		else if (map[row][col] == barrierNum) {
			image = barrier;
		}
		else if (map[row][col] == turnNum) {
			image = turn;
		}
		else {
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
	
	public int getStartCol() {
		return this.startCol;
	}
	public int getStartRow() {
		return this.startRow;
	}
	
	public ArrayList<DisplayableSprite> getBarriers() {
		ArrayList<DisplayableSprite> barriers = new ArrayList<DisplayableSprite>();
		for (int col = 0; col < map[0].length; col++) {
			for (int row = 0; row < map.length; row++) {
				if (map[row][col] == barrierNum) {
					barriers.add(new BarrierSprite((col * TILE_WIDTH) + 20, (row * TILE_HEIGHT) + 20, ((col + 1) * TILE_WIDTH) - 20, ((row + 1) * TILE_HEIGHT) - 20, true));
				} 
			}
		}
		return barriers;
	}

}
