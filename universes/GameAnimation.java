
public class GameAnimation implements Animation {

	private static int universeCount = 0;
	private static int retry = 0;
	
	public int getUniverseCount() {
		return universeCount;
	}

	public static void setUniverseCount(int count) {
		GameAnimation.universeCount = count;
	}

	public Universe getNextUniverse() {

		universeCount ++;
		
		if (universeCount == 1) {
			return new MenuUniverse();
		} else if(universeCount == 3) {
			return new LevelUniverse(new int[][] {
				{2,2,3,3,3},
				{2,9,1,1,0},
				{2,1,3,3,3},
				{2,1,2,2,2},
				{2,1,2,2,2}
			}, 0, 4,1);
		} else if(universeCount == 2) {
			return null;
		} else if(universeCount == 4) {
			return new LevelUniverse(new int[][] {
		    	{2,2,3,3,3,3,3,3,3,3,2},
		    	{2,3,9,1,1,1,1,1,0,3,2},
		    	{2,3,1,3,3,3,3,3,3,3,2},
		    	{2,3,1,3,2,2,2,2,2,2,2},
		    	{2,3,1,3,3,3,3,3,3,3,2},
		    	{2,3,9,1,1,1,1,1,1,9,3},
		    	{2,2,3,3,3,3,3,3,3,1,2},
		    	{2,2,2,3,3,3,3,3,3,1,2},
		    	{2,2,3,9,1,1,1,1,1,9,2},
		    	{2,2,3,1,3,3,3,3,3,3,2},
		    	{2,2,3,1,3,2,2,2,2,2,2},
		    	{2,2,3,1,3,2,2,2,2,2,2},
		    	{2,2,3,1,3,2,2,2,2,2,2},
		    	{2,2,2,3,2,2,2,2,2,2,2},
		    	{2,2,2,2,2,2,2,2,2,2,2}}, 1, 12, 3);
		}
		else {
			return null;
		}

	}

	@Override
	public int getRetry() {
		// TODO Auto-generated method stub
		return retry;
	}
	
	@Override
	public void setRetry(int retry) {
		// TODO Auto-generated method stub
		GameAnimation.retry = retry;
	}

	
	
}
