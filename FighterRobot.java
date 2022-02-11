package summative;
import becker.robots.*;

public abstract class FighterRobot extends RobotSE{

	private int id;
	private int attack;
	private int defence;
	private int numMoves;
	
	public FighterRobot (City c, int a, int s, Direction d, int id, int attack, int defence, int numMoves){
		super(c,a,s,d);
		this.id = id;
		this.attack = attack;
		this.defence = defence;
		this.numMoves = numMoves;
		
		this.setLabel();
	}
	
	/**
	 * This method returns the ID number of the robot.  Does not need to be overridden by subclasses.
	 * @return	the ID number of the robot
	 */
	public int getID()
	{
		return this.id;
	}
	
	/**
	 * This method returns the attack value of the robot.  Does not need to be overridden by subclasses.
	 * @return	the attack value of the robot
	 */
	public int getAttack()
	{
		return this.attack;
	}
	
	/**
	 * This method returns the defence value of the robot.  Does not need to be overridden by subclasses.
	 * @return	the defence value of the robot
	 */
	public int getDefence()
	{
		return this.defence;
	}
	
	/**
	 * This method returns the number of moves the robot can perform.  Does not need to be overridden by subclasses.
	 * @return	the number of moves the robot can perform
	 */
	public int getNumMoves()
	{
		return this.numMoves;
	}
	
	/**
	 * This method labels the player.
	 * 
	 * NOTE: You need to override this method to print out your health
	 * and change your colour when you die.
	 */
	public void setLabel()
	{
		this.setLabel("ID: " + this.getID());
	}

	public abstract void goToLocation(int a, int s);
	
	public abstract TurnRequest takeTurn(int energy, OppData[] data);
		
	public abstract void battleResult(int healthLost, int oppID, int oppHealthLost, int numRoundsFought);
}
