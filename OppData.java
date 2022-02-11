package summative;
/**
 * OppData is a record of information that will be passed about an opponent to the FightingRobot
 * when it is his/her turn.
 * @author Mr. Ching
 */
public class OppData {
	private int id;
	private int avenue;
	private int street;
	private int health;
	
	/**
	 * The OppData constructor used to make records of this type.
	 * @param id		the ID number of the player's ID for this OppData record
	 * @param a			the player's avenue for this OppData
	 * @param s			the player's street for this OppData
	 * @param health	the player's health for this OppData
	 */
	public OppData (int id, int a, int s, int health)
	{
		this.id = id;
		this.avenue = a;
		this.street = s;
		this.health = health;
	}
	
	/**
	 * Accessor method for the player's ID for this OppData record.
	 * @return	the ID number of the player's ID for this OppData record
	 */
	public int getID()
	{
		return this.id;
	}
	
	/**
	 * Accessor method for the player's avenue for this OppData record.
	 * @return	the player's avenue for this OppData
	 */
	public int getAvenue()
	{
		return this.avenue;
	}
	
	/**
	 * Modifier method for the player's avenue for this OppData record.
	 * @param avenue  the player's avenue for this OppData
	 */
	public void setAvenue(int avenue)
	{
		this.avenue = avenue;
	}
	
	/**
	 * Accessor method for the player's street for this OppData record.
	 * @return	the player's street for this OppData
	 */
	public int getStreet()
	{
		return this.street;
	}
	
	/**
	 * Modifier method for the player's street for this OppData record.
	 * @param street  the player's street for this OppData
	 */
	public void setStreet(int street)
	{
		this.street = street;
	}
	
	/**
	 * Accessor method for the player's health for this OppData record.
	 * @return	the player's health for this OppData
	 */
	public int getHealth()
	{
		return this.health;
	}
	
	/**
	 * Modifier method for the player's health for this OppData record.
	 * @param health  the player's health for this OppData
	 */
	public void setHealth(int health)
	{
		this.health = health;
	}
}
