package summative;
import java.util.*;

public class PlayerStats {

	private int roundsWin;
	private int roundsLoss;
	private int fightsInitiatedWin;
	private int fightsInitiatedLoss;
	private int fightsInitiatedTie;
	private int fightsDefendWin;
	private int fightsDefendLoss;
	private int fightsDefendTie;
	private int totalNumMoves;
	private int numPenalties;
	private int numKills;
	private ArrayList penaltiesComments = new ArrayList();
	
	public PlayerStats()
	{
		this.roundsWin = 0;
		this.roundsLoss = 0;
		this.fightsInitiatedWin = 0;
		this.fightsInitiatedLoss = 0;
		this.fightsInitiatedTie = 0;
		this.fightsDefendWin = 0;
		this.fightsDefendLoss = 0;
		this.fightsDefendTie = 0;
		this.totalNumMoves = 0;
		this.numPenalties = 0;
		this.numKills = 0;
	}
	
	public void addRoundsWin()
	{
		this.roundsWin ++;
	}

	public void addRoundsLoss()
	{
		this.roundsLoss ++;
	}

	public void addFightsInitiatedWin()
	{
		this.fightsInitiatedWin ++;
	}

	public void addFightsInitiatedLoss()
	{
		this.fightsInitiatedLoss ++;
	}

	public void addFightsInitiatedTie()
	{
		this.fightsInitiatedTie ++;
	}

	public void addFightsDefendWin()
	{
		this.fightsDefendWin ++;
	}

	public void addFightsDefendLoss()
	{
		this.fightsDefendLoss ++;
	}

	public void addFightsDefendTie()
	{
		this.fightsDefendTie ++;
	}

	public void addTotalNumMoves(int num)
	{
		this.totalNumMoves += num;
	}

	public void addNumPenalties()
	{
		this.numPenalties ++;
	}
	
	public void addPenaltiesComments(String comment)
	{
		this.penaltiesComments.add(comment);
	}

	public void addNumKills()
	{
		this.numKills ++;
	}

	public int getRoundsWin()
	{
		return this.roundsWin;
	}

	public int getRoundsLoss()
	{
		return this.roundsLoss;
	}
	public int getFightsInitiatedWin()
	{
		return this.fightsInitiatedWin;
	}
	public int getFightsInitiatedLoss()
	{
		return this.fightsInitiatedLoss;
	}
	public int getFightsInitiatedTie()
	{
		return this.fightsInitiatedTie;
	}
	public int getFightsDefendWin()
	{
		return this.fightsDefendWin;
	}
	public int getFightsDefendLoss()
	{
		return this.fightsDefendLoss;
	}
	public int getFightsDefendTie()
	{
		return this.fightsDefendTie;
	}
	public int getTotalNumMoves()
	{
		return this.totalNumMoves;
	}
	public int getNumPenalties()
	{
		return this.numPenalties;
	}
	public ArrayList getPenalitiesComments()
	{
		return this.penaltiesComments;
	}
	public int getNumKills()
	{
		return this.numKills;
	}

}
