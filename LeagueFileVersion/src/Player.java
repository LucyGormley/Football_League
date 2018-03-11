/*
 * Lucy Gormley - C16334766
 */

import java.io.Serializable;
import java.text.NumberFormat;

public class Player implements Serializable 
{
	private String playerId;
	private String name;
	private String position;
	private int goalsScored;
	private double transferFee;
	private Club club;

	public Player(String playerId, String name, String position, int goalsScored, double transferFee, Club club) //constructor for club objects
	{
	this.playerId = playerId;
	this.name = name;
	this.position = position;
	this.goalsScored = goalsScored;
	this.transferFee = transferFee;
	this.club= club;
	}
	
	public Club getClub()
	{
	return this.club;
	}
	
	public void setNewClub(Club club)
	{
        this.club = club;
	}
	
	public String getPlayerId() 
	{
	return this.playerId;
	}

	public String getName() 
	{
	return this.name;
	}

	public String getPosition()
	{
	return this.position;
	}
	
	public int getGoalsScored()
	{
	return this.goalsScored;
	}
	
	public void setGoalsScored(int newGoalsScored)
	{
	this.goalsScored = this.goalsScored + newGoalsScored;
	}
	
	public double getTransferFee()
	{
	return this.transferFee;
	}
	
	public void setTransferFee(double newTransferFee)
	{
	this.transferFee = this.transferFee + newTransferFee;
	}

	public String toString() //returns player details aswell as that player's club details
	{
		String details = "PlayerID: " + this.playerId + "     Name: " + this.name + "     Position: " + this.position + "     Goals scored: " + this.goalsScored;
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		details = details + "     Transfer fees: " + nf.format(transferFee);
		details = details + "\nCLUB-->     " + this.club;
		return details;
	}

}


