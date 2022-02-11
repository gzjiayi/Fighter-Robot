package summative;

/**
 * BattleOppData is a record of information that stores the previous battle result with an opponent to the FightingRobot
 * when it is his/her turn.
 * @author grace
 * @version January. 24, 2022
 */
public class BattleOppData extends OppData{
	private int healthLost;
	private int oppHealthLost;
	private int numRoundsFought;
	
	/**
	 * The BattleOppData constructor used to make records of this type
	 * @param id the ID number of the player's ID for this BattleOppData record
	 * @param a the player's avenue for this BattleOppData
	 * @param s the player's street for this BattleOppData
	 * @param health the player's health for this BattleOppData
	 * @param healthLost the amount of health the robot lost
	 * @param oppHealthLost the amount of health the opponent robot lost
	 * @param numRoundsFought the number of rounds fought during this turn
	 */
	public BattleOppData (int id, int a, int s, int health, int healthLost, int oppHealthLost, int numRoundsFought)
	{
		super (id, a, s, health);

		this.healthLost = healthLost;
		this.oppHealthLost = oppHealthLost;
		this.numRoundsFought = numRoundsFought;
	}
	
	/**
	 * Accessor method for the robot's health lost
	 * @return robot's health lost
	 */
	public int getHealthLost()
	{
		return this.healthLost;
	}
	
	/**
	 * Modifier method for the health lost by the robot 
	 * @param healthLost the health lost by the robot
	 */
	public void setHealthLost(int healthLost)
	{
		this.healthLost = healthLost;
	}
	
	/**
	 * Accessor method for the opponent's health lost
	 * @return opponent's health lost
	 */
	public int getOppHealthLost()
	{
		return this.oppHealthLost;
	}
	
	/**
	 * Modifier method for the health lost by the opponent 
	 * @param oppHealthLost the health lost by the opponent
	 */
	public void setOppHealthLost(int oppHealthLost)
	{
		this.oppHealthLost = oppHealthLost;
	}
	
 	/**
	 * Accessor method for the number of rounds fought
	 * @return the number of rounds fought
	 */
	public int getNumRoundsFought()
	{
		return this.numRoundsFought;
	}

 	/**
	 * Modifier method for the number of rounds fought
	 * @param numRoundsFought the number of rounds fought
	 */
	public void setNumRoundsFought(int numRoundsFought)
	{
		this.numRoundsFought = numRoundsFought;
	}
}
