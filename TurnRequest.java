package summative;
/**
 * This is a record that the player will return for what it wants to do
 * @author Mr. Ching
 *
 */
public class TurnRequest {
	
	private int endAvenue;
	private int endStreet;
	private int fightID; // -1 for nothing
	private int numRounds;
	
	public TurnRequest(int endAvenue, int endStreet, int fightID, int numRounds)
	{
		this.endAvenue = endAvenue;
		this.endStreet = endStreet;
		this.fightID = fightID;
		this.numRounds = numRounds;
	}
	
	public int getEndAvenue()
	{
		return this.endAvenue;
	}
	
	public int getEndStreet()
	{
		return this.endStreet;
	}
	
	public int getFightID()
	{
		return this.fightID;
	}
	
	public int getNumRounds()
	{
		return this.numRounds;
	}
}
