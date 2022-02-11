package summative;

import java.awt.Color;
import java.util.ArrayList;

import becker.robots.*;

/**
 * An individual, unique robot with AI capabilities coded to implement levels of determination to determine what to do on its turn
 * @author grace
 * @version January. 24, 2022
 */
public class TestFighterRobot extends FighterRobot{

	private static final int MOVES_ENERGY_COST = 5; // each step taken costs 5 points
	private int health;

	// Stores information from battle results
	private ArrayList<BattleOppData> battleData;

	public TestFighterRobot (City c, int a, int s, Direction d, int id, int health) {
		super (c, a, s, d, id, 4, 2, 4);

		this.health = health;
		battleData = new ArrayList<BattleOppData>();

		this.setLabel();
	}

	/** 
	 * Labels robot 
	 */
	public void setLabel() {

		// Changes color of robot to black when it dies
		if (this.health <= 0){
			this.setColor(Color.BLACK); 
		}
		// otherwise, color is set to cyan
		else {
			this.setColor(Color.CYAN);
		}
		// displays id and health 
		this.setLabel("ID: " + this.getID() + "  Health: " + this.health);		
	}

	/**
	 * Moving robot to the specified location
	 * @param a The end avenue 
	 * @param s The end street 
	 */
	public void goToLocation(int a, int s) {
		int curAve = this.getAvenue();
		int curStrt = this.getStreet();

		// when the end avenue is to the right of the current avenue 
		if (a > curAve) {
			// continue turning right until facing east
			while (this.getDirection() != Direction.EAST) {
				this.turnRight();
			}
			this.move(a - curAve);
		}

		// when the end avenue is to the left of the current avenue 
		else if (a < curAve) {
			// continue turning right until facing west
			while (this.getDirection() != Direction.WEST) {
				this.turnRight();
			}
			this.move(curAve - a);
		}

		// when the end street is below the current street
		if (s > curStrt) {
			// continue turning right until facing south
			while (this.getDirection() != Direction.SOUTH) {
				this.turnRight();
			}
			this.move(s - curStrt);
		}
		// when the end street is above the current street
		else if (s < curStrt) {
			// continue turning right until facing north
			while (this.getDirection() != Direction.NORTH) {
				this.turnRight();
			}
			this.move(curStrt - s);
		}	
	}

	/**
	 * Building request for the desired end location when robot takes its turn
	 * @param energy Robot's energy
	 * @param data Data of all the robots
	 * @return An object of class TurnRequest that provides the desired end location, fightID, and number of fight rounds for this turn 
	 */
	public TurnRequest takeTurn (int energy, OppData [] data) {

		// Calling method to update the data of all the robots
		SaveUpdatedOppData(data);

		// Local array lists 
		ArrayList<OppData> oppDataList = new ArrayList<OppData>();
		ArrayList<OppData> candidateOppData = new ArrayList<OppData>();

		// Loops through the array holding the data of all the robots
		for (int i = 0; i < data.length; i++) {

			// Skip if it is the robot itself 
			if (data[i].getID() == this.getID()){
				continue;
			}
			// List that contains data of all the robots except itself
			oppDataList.add(data[i]);

			// Skip if the opponent is dead
			if (data[i].getHealth() <= 0) {
				continue;
			}

			// Skip if requires more than the maximum number of moves designated by player
			int numOfMoves = Math.abs(data[i].getAvenue() - this.getAvenue()) + Math.abs(data[i].getStreet() - this.getStreet()); 
			if (numOfMoves > this.getNumMoves()){
				continue;
			} 

			// Skip if requires more than the available energy for moving or the robot's energy level is zero after moving
			if(numOfMoves * MOVES_ENERGY_COST >= energy){  
				continue;
			}

			// SKip if the opponent's location is beyond the battlefield
			if (data[i].getAvenue() > BattleManager.WIDTH - 1  || data[i].getStreet() > BattleManager.HEIGHT - 1 || data[i].getStreet() < 0 || data[i].getAvenue() < 0) {
				continue;
			}

			// Adding data of possible opponent candidates into an array list 
			candidateOppData.add(data[i]);

		}
		// Calling method to sort the list of possible candidates as well as list of all robots excluding itself
		insertionSortArrayList(candidateOppData);
		insertionSortArrayList(oppDataList);

		// If there is no possible candidate, move robot to the closest robot by the maximum steps it can take per turn
		if (candidateOppData.isEmpty()) {

			// Avenue value of the closest opponent robot
			int oppAve = oppDataList.get(0).getAvenue();

			// If the avenue distance is greater than or equal to the number of maximum steps the robot can take per turn
			if (Math.abs(oppAve - this.getAvenue()) >= this.getNumMoves()) {

				// Checks if opponent is on the right side, request the robot to move towards the right direction
				// When returning TurnRequest object, the ID = -1 indicates the robot will not fight
				if (oppAve > this.getAvenue()) {
					return new TurnRequest(this.getAvenue() + this.getNumMoves(), this.getStreet(), -1, this.getAttack());		
				}
				// If opponent is on the left side, request the robot to move towards the left direction
				else {
					return new TurnRequest(this.getAvenue() - this.getNumMoves(), this.getStreet(), -1, this.getAttack());
				}
			}
			// If the avenue distance is less than the number of maximum steps the robot can take per turn
			else {
				// Street value of the closest opponent robot
				int oppStreet = oppDataList.get(0).getStreet();

				// If opponent is below the robot, request the robot to move downwards
				// When returning TurnRequest object, the ID = -1 indicates the robot will not fight
				if (oppStreet > this.getStreet()) {
					return new TurnRequest(oppAve, this.getStreet() + (this.getNumMoves() - Math.abs(oppAve - this.getAvenue())) , -1, this.getAttack());
				}
				// If opponent is above the robot, request the robot to move upwards
				else {
					return new TurnRequest(oppAve, this.getStreet() - (this.getNumMoves() - Math.abs(oppAve - this.getAvenue())) , -1, this.getAttack());

				}
			}
		}

		// If there are possible candidates, determine which one to fight
		else {
			int j = 0;
			boolean found = false;
			// Loop through the list of candidate data 
			for (j = 0; j < candidateOppData.size(); j++) {
				// If robot's health is greater than the candidate's health and if robot hasn't lost to this candidate before
				if (this.health > candidateOppData.get(j).getHealth() && lostPreviousRound(candidateOppData.get(j).getID()) == false) {
					found = true;
					break;
				}
			}
			// If did not find opponent meeting requirements in the if statement above
			if (found == false) {
				for (j = 0; j < candidateOppData.size(); j++) {
					// If robot hasn't lost to this candidate before
					if (lostPreviousRound(candidateOppData.get(j).getID()) == false) {
						found = true;
						break;
					}
				}			
			}

			// If did not find opponent that robot hasn't lost to before
			if (found == false) {				
				for (j = 0; j < candidateOppData.size(); j++) {
					// If robot's health is greater than the candidate's health
					if (this.health > candidateOppData.get(j).getHealth()) {
						found = true;
						break;
					}
				}			
			}
			// If found, send request to fight the opponent
			if (found) {

				return new TurnRequest(candidateOppData.get(j).getAvenue(), candidateOppData.get(j).getStreet(), candidateOppData.get(j).getID(), this.getAttack());
			}
			// If no opponent is found, then fight the closest robot
			else {
				return new TurnRequest(candidateOppData.get(0).getAvenue(), candidateOppData.get(0).getStreet(), candidateOppData.get(0).getID(), this.getAttack());
			}

		}

	}

	/**
	 * Updates the battle results into local list -> battleData 
	 * @param healthLost The amount of health the robot lost while fighting with opponent
	 * @param oppID The robot id of opponent 
	 * @param oppHealthLost The amount of health the opponent lost while fighting with robot
	 * @param numRoundsFought The number of rounds fought during the turn 
	 */
	public void battleResult (int healthLost, int oppID, int oppHealthLost, int numRoundsFought) {

		// Update robot's health level
		this.health -= healthLost;
		if (this.health < 0) {
			this.health = 0;
		}
		// Update robot's label
		this.setLabel();

		// If a fight occurred 
		if (oppID != -1) {

			// Loops through the list of battle data
			for (int j = 0; j < battleData.size(); j++) {
				// If found the match for the opponent id
				if (oppID == battleData.get(j).getID()) {
					// Update the battle results 
					battleData.get(j).setHealthLost(healthLost);
					battleData.get(j).setOppHealthLost(oppHealthLost);
					battleData.get(j).setNumRoundsFought(numRoundsFought);	

					break;
				}
			}
		}
	}	

	/**
	 * Save updated opponent data into the local list -> battleData 
	 * @param data 
	 */
	private void SaveUpdatedOppData(OppData [] data) {
		// Loops through the list of opponent data
		for (int i = 0; i< data.length; i++) {
			boolean foundBattleData = false; 
			// Loops through the list of battle data
			for (int j = 0; j < battleData.size(); j++) {
				// If found match for the opponent id
				if (data[i].getID() == battleData.get(j).getID()) {
					// Update opponent data 
					battleData.get(j).setAvenue(data[i].getAvenue());
					battleData.get(j).setStreet(data[i].getStreet());
					battleData.get(j).setHealth(data[i].getHealth());	

					foundBattleData = true;
					break;
				}
			}
			// If did not find the match for opponent id, create a new opponent data to hold the information
			if (foundBattleData == false) {
				battleData.add(new BattleOppData(data[i].getID(), data[i].getAvenue(), data[i].getStreet(), data[i].getHealth(), 0, 0, 0));
			} 
		}	
	}

	/**
	 * Insertion sorts the opponent data by distance from robot 
	 * @param data All the opponents data
	 */	
	public void insertionSortArrayList(ArrayList<OppData> data) {
		for (int j = 1; j < data.size(); j++) {
			OppData temp = data.get(j);
			int i = j-1;
			while ((i > -1) && 
					((Math.abs(data.get(i).getAvenue() - this.getAvenue()) + Math.abs(data.get(i).getStreet() - this.getStreet())) > 
					(Math.abs(temp.getAvenue() - this.getAvenue()) + Math.abs(temp.getStreet() - this.getStreet())))) {
				data.set(i+1, data.get(i));
				i--;
			}
			data.set(i+1, temp);
		}
	}


	/**
	 * Check if the robot has lost the previous fight with the opponent robot
	 * @param oppID	the opponent's ID
	 * @return returns true if robot lost more health than the opponent, otherwise return false
	 */
	private boolean lostPreviousRound(int oppID) {
		boolean lost = false;

		for (int i = 0; i < battleData.size(); i++) {
			// If opponent Id is found and robot lost more health than the opponent, return true
			if (oppID == battleData.get(i).getID()) {
				if (battleData.get(i).getHealthLost() > battleData.get(i).getOppHealthLost()) {
					lost = true;						
				}					
				break;
			}
		}


		return lost;
	}
}
