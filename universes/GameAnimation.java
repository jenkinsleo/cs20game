
public class GameAnimation implements Animation {

	private static int universeCount = 0;
	private int retry = 0;
	
	public static int getUniverseCount() {
		return universeCount;
	}

	public void setUniverseCount(int count) {
		GameAnimation.universeCount = count;
	}

	public Universe getNextUniverse() {

		universeCount++;
		
		if (universeCount == 1) {
			return new LevelUniverse();
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
		this.retry = retry;
	}

	
	
}
