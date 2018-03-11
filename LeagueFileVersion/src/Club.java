/*
 * Lucy Gormley - C16334766
 */
import java.io.Serializable;

public class Club implements Serializable
{
	private String clubName;
	private String owner;
	private String manager;
	private String website;

	public Club(String clubName, String owner, String manager, String website)//Constructor for club object
	{
	this.clubName = clubName;
	this.owner = owner;
	this.manager = manager;
	this.website = website;
	}

	public String toString()// returns club details
	{
	return "Club name: " + this.clubName + "     Club owner: "  + this.owner + "     Club manager: " + this.manager + "     Website: " + this.website;
	}

	public String getClubName()
	{
	return this.clubName;
	}

	public String getOwner()
	{
	return this.owner;
	}

	public String getManager()
	{
	return this.manager;
	}

	public String getWebsite()
	{
	return this.website;
	}
}


