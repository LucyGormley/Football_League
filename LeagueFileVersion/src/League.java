/*
 * Lucy Gormley - C16334766
 */
import java.util.ArrayList;
import java.util.*;
import java.io.*;
import java.util.Scanner;

public class League
{
	private ArrayList<Player>players;
	private ArrayList<Club>clubs;
	private Scanner scan;
	private File playersFile;
	private File clubsFile;


	public League()
	{
		players = new ArrayList<Player>(); 
		clubs = new ArrayList<Club>();
		scan = new Scanner(System.in);
		playersFile = new File("players.dat");
		clubsFile = new File("clubs.dat");
		readFromClubsFile();
		readFromPlayersFile();
	}

	/*
	 * Creates and stores club objects. Displays list of previously created clubs 
	 * to avoid clubs being created twice due to bad spelling, abbreviations etc that can't be avoided by the searchClubName() method
	 * Asks user if they still want to register a club as they may have found that their club has already been
	 * registered under a slightly different name eg abbreviated
	 */

	public void readFromPlayersFile()
	{
		try
		{
			FileInputStream fis = new FileInputStream(playersFile);
			ObjectInputStream ois = new ObjectInputStream(fis);
			players = (ArrayList<Player>) ois.readObject();
			ois.close();
		}
		catch(FileNotFoundException e)
		{
			System.out.println(e.getMessage());
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());

		}
		catch(ClassNotFoundException e)
		{
			System.out.println(e.getMessage());
		}
	}

	public void writeToPlayersFile()
	{
		try
		{
			FileOutputStream fos = new FileOutputStream(playersFile);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(players);
			oos.close();

		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
		}
	}

	public void readFromClubsFile()
	{
		try
		{
			FileInputStream fis = new FileInputStream(clubsFile);
			ObjectInputStream ois = new ObjectInputStream(fis);
			clubs = (ArrayList<Club>) ois.readObject();
			ois.close();
		}
		catch(FileNotFoundException e)
		{
			System.out.println(e.getMessage());
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());

		}
		catch(ClassNotFoundException e)
		{
			System.out.println(e.getMessage());
		}
	}

	public void writeToClubsFile()
	{
		try
		{
			FileOutputStream fos = new FileOutputStream(clubsFile);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(clubs);
			oos.close();

		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
		}
	}

	public Club enterClub() 
	{
		String response = "y";
		int index = 1;
		if(!clubs.isEmpty())
		{
			System.out.println("Clubs already registered on this system are:");
			for(Club club: clubs)
			{
					System.out.println(index + ": " + club.getClubName());
					index++;
			}
			System.out.print("Do you still want to register a club?(y/n)> ");
			response = scan.nextLine();
			System.out.println();
		}
		if(response.equalsIgnoreCase("y") || clubs.isEmpty())
		{
			String clubName;
			System.out.print("Enter club name: ");
			clubName = scan.nextLine();
			Club clubSearch = searchClubName(clubName);
			if(clubSearch != null)
			{
				System.out.println("This club has already been registered.");
			}
			else
			{
				System.out.print("Enter name of owner: ");
				String owner= scan.nextLine();
				System.out.print("Enter name of manager: ");
				String manager= scan.nextLine();
				System.out.print("Enter club website: ");
				String website= scan.nextLine();
				Club club= new Club(clubName, owner, manager, website);
				clubs.add(club);
				return club;
			}
		}
		else if (!response.equalsIgnoreCase("y"))
		{
			System.out.println("Club not registered.");
		}
		return null;
	}
	
	public boolean validInteger(String input)
	{
		if(input.length() < 1)
		{
			return false;
		}
		for(int i = 0; i < input.length(); i++)
		{
			char c = input.charAt(i);
			if(!Character.isDigit(c))
			{
				return false;

			}
		}
		return true;
	}
	
	public boolean validDouble(String input)
	{
		if(input.length() < 1)
		{
			return false;
		}
		for(int i = 0; i < input.length(); i++)
		{
			char c = input.charAt(i);
			if(!Character.isDigit(c))
			{
				return false;

			}
		}
		return true;
	}


	
	/*
	 * Searches the arrayList of clubs to find a matching name. If found the club is returned, otherwise null is returned.
	 */

	public Club searchClubName(String clubName)
	{
		for(Club club : clubs)
		{
			if (club.getClubName().equalsIgnoreCase(clubName))
			{
				return club;//returns club that was found in the search
			}
		}
		return null;//returns null if it hasn't found the matching club
	}
	
	/*
	 * enterPlayer() - Creates and stores player objects in the players arrayList. Club name is input and using the search method,
	 * the player is either stored with the matching club or a new club must be created, then player is created and created 
	 * club is stored with the player
	 */
	
	public void enterPlayer()//creates and stores player
    {
        System.out.print("Enter playerID: ");
        String playerId = scan.nextLine();
        System.out.print("Enter player name: ");
        String name = scan.nextLine();
        System.out.print("Enter player's position: ");
        String position = scan.nextLine();
        System.out.print("Total goals scored by player: ");
		String goals = scan.nextLine();
		while(!validInteger(goals))
		{
			System.out.print("Error - please enter the number of goals scored(numerically): ");
			goals = scan.nextLine();
		}
		
		int goalsScored = Integer.parseInt(goals);
        System.out.print("Total transfer fees: ");
        String fees = scan.nextLine();
		while(!validDouble(fees))
		{
			System.out.print("Error - please enter the total transfer fees(numerically): ");
			fees = scan.nextLine();
		}
		
		double transferFee = Double.parseDouble(fees);
        System.out.print("Enter player's club name: ");
        String clubName = scan.nextLine();
        Club club = searchClubName(clubName);
        if(club != null)// if club was already created
        {
            System.out.println("Club found"); 
        }
        else // if club was not previously created, it must now be created to create player
        {
            System.out.println("As " + clubName + " was not on our system, it will now be registered. \nPlease check list below to check if an abbreviated version of this club has been registered, otherwise please complete club details: ");
            club = enterClub(); // club stores the enterClub() method which returns club that has been created
        }
        
        if(club != null) // will only create player if club has been created, eg wont attempt this step if user inputs "n" for response in enterClub() method
        {
        Player player = new Player(playerId, name, position, goalsScored, transferFee, club);
        players.add(player);
        System.out.println("--> " + player.getName() + " (" + player.getPlayerId() + ") has been registered with " + club.getClubName());
        }
        else
        {
        	System.out.println("Player not registered.");
        }
 
    }
	
	/*
	 * searchPlayerName() - Searches the arrayList of players to find a matching name. If found the player is returned, otherwise null is returned.
	 */
	
	public Player searchPlayerName(String name)
	{
		for(Player player : players)
		{
			if (player.getName().equalsIgnoreCase(name))
			{
				return player;//returns player that was found in the search
			}
		}
		return null;//returns null if it hasn't found the matching player
	}
	
	public void showClubs() // Displays all registered clubs, displays error message if there are no registered clubs
    {
    	System.out.println("DETAILS FOR ALL CLUBS");
    	System.out.println();
    	if(!clubs.isEmpty())
    	{
    		for(Club club : clubs)
    		{
    			System.out.println(club.toString());
    		}
    		
    	}
    	else
    	{
    		System.out.println("There are currently no clubs registered.");
    		
    	}
    }
	
	public void showPlayers() //Displays all registered players, displays error message if there are no registered players
    {
    	System.out.println("DETAILS FOR ALL PLAYERS:");
    	System.out.println();
    	if(!players.isEmpty())
    	{
    		for(Player player : players)
    		{
    			System.out.println(player.toString());
    			System.out.println();
    		}
    	}
    	else
    	{
    		System.out.println("There are currently no players registered.");
    		
    	}
    }
	
	/*
	 * Searches the input player name, if found, searches the input club name, if found, changes the details for the club 
	 * object stored with player using setNewClub() and updates the transferFee figure using setTransferFee().
	 * Error messages displayed if no matching player or clubs are found. New player details displayed.
	 * Prevents player from being "transferred" to club they are already registered with and then will not take
	 * in a value for transferFee as transfer is unsuccessful
	 */
	
	public void transferPlayer()
	{
		System.out.println("TRANSFER PLAYER");
		if(!players.isEmpty())
		{
			System.out.print("Enter name of the player being transferred: ");
			String name = scan.nextLine();
			Player player = searchPlayerName(name);
			if(player != null)
			{
				System.out.println(player.toString());
				System.out.println();
				System.out.print("Enter name of the club the player is being transferred to: ");
				String clubName = scan.nextLine();
				if(player.getClub().getClubName().equalsIgnoreCase(clubName))
				{
					System.out.println(player.getName() + " is already registered with " + player.getClub().getClubName() + " and therefore cannot be transferred.");
				}
				else
				{
					Club club = searchClubName(clubName);
					if(club != null)
					{
						player.setNewClub(club);
						System.out.print("Please enter the transfer fee : ");
						double newTransferFee = scan.nextDouble();
						scan.nextLine();
						while(newTransferFee < 0)
						{
							System.out.print("You have entered a minus figure, please re-enter the transfer figure --> ");
							newTransferFee = scan.nextDouble();
							scan.nextLine();
						}
						
							player.setTransferFee(newTransferFee);
							System.out.println("-->" + player.getName() +" has been transferred to " + club.getClubName());
							System.out.println(player.toString());	
						
					}
					else
					{
						System.out.println("Club not found.");
					}
				}
			}
			else
			{
				System.out.println("Player not found.");
			}
		}
		else
		{
			System.out.println("There are currently no players registered.");
		}
	}

	/*
	 * updateGoalsScored() - Updates the number of goals scored by a player. Player name is input and arrayList of players is searched.
	 * If found the user is asked to input the update, calculated using setGoalsScored() method from class Player and player 
	 * details are displayed using toString() method.  
	 */
	
	public void updateGoalsScored()
	{
		System.out.println("UPDATE GOALS SCORED BY A PLAYER");
		if(!players.isEmpty())
		{
			System.out.print("Enter player name: ");
			String name = scan.nextLine();
			Player player = searchPlayerName(name);
			if(player != null)
			{
				System.out.println("This player has currently scored " + player.getGoalsScored() + " goals.");
				System.out.print("Please enter the amount of goals scored since last update : ");
				int newGoalsScored = scan.nextInt();
				scan.nextLine();
				player.setGoalsScored(newGoalsScored);
				System.out.println();
				System.out.println(player.toString());

			}
			else
			{
				System.out.println("Player not found.");
			}
		}
		else
		{
			System.out.println("There are currently no players registered.");
		}
	}
	/*
	 * listStrikers() - Displays a list of all strikers who have scored over 3 goals. Index is used to allow users to see 
	 * number of strikers who have scored over 3 goals but also prevents the message "no players have scored over 3 goals" 
	 * from displaying every time the arrayList is searched.
	 */
	
	public void listStrikers()
	{
		System.out.println("STRIKERS WHO HAVE SCORED OVER 3 GOALS");
		int index = 1;
		if(!players.isEmpty())
		{
			for(Player player : players)
			{
				if(player.getPosition().equalsIgnoreCase("striker") && player.getGoalsScored() > 3)
				{
					System.out.println(index + ": " + player.getName() + " (" + player.getGoalsScored() + " goals) " + "CLUB-> " + player.getClub().getClubName());
					index ++;
				}
			}
			if(index < 2)  // index less than 2, never incremented, therefore no strikers scored over 3 goals
			{
				System.out.println("There are currently no strikers who have scored over 3 goals.");

			}
		}
		else
		{
			System.out.println("There are currently no players registered.");
		}
	}
	
	/*
	 * listClubPlayers() - Displays the players in a particular club. Club name is 
	 * input and if found the players registered with that club are displayed. If club name is 
	 * not found or if no players are registered in the system, error messages are displayed. 
	 * Index is used to prevent the message(no players with this club) from being displayed
	 * every time the arrayList for players is searched and finds no match.
	 */
	
	public void listClubPlayers()
	{
		System.out.println("PLAYERS");
		int index = 1; // set to 1 to make list user friendly by starting at 1: , 2: etc
		if(!players.isEmpty())
		{
			System.out.print("Enter club name for players you wish to display> ");
			String clubName = scan.nextLine();
			Club club = searchClubName(clubName);
			if(club != null)
			{
				//boolean found = false;
				for(Player player : players)
				{
					if(player.getClub().getClubName().equalsIgnoreCase(clubName))
					{
						System.out.println(index + ": " + player.getName());
						//found = true;
						index ++;
					}
				}
				/*if(!found)
				{
					System.out.println("There are currently no players registered with " + club.getClubName());
				}*/
				if(index < 2)  // if index is less than 2, it was never incremented as no players are registered with this club
				{
					System.out.println("There are currently no players registered with " + club.getClubName());

				}
			}
			else
			{
				System.out.println(clubName + " is not registered in this system, you can register this club through option 1 on the menu.");
			}
		}
		else
		{
			System.out.println("There are currently no players registered.");
		}
	}
	/*
	 * totalClubPlayers() - Counts and displays the number of players in a particular club. Club name is 
	 * input and if found the number of players registered with that club are displayed. If club name is 
	 * not found or if no players are registered in the system, error messages are displayed. Index is used
	 * to count the players and also prevent the message(no players with this club) from being displayed
	 * every time the arrayList for players is searched and finds no match.
	 */
	
	public void totalClubPlayers()
	{
		System.out.println("NUMBER OF PLAYERS");
		int index = 0;  // set to 0 as this is used as a count
		if(!players.isEmpty())
		{
			System.out.print("Enter club name for players you wish to display> ");
			String clubName = scan.nextLine();
			Club club = searchClubName(clubName);
			if(club != null)
			{
				for(Player player : players)
				{
					if(player.getClub().getClubName().equalsIgnoreCase(clubName))
					{
						index ++;
					}
				}
				System.out.println("There are currently " + index + " player(s) registered with " + club.getClubName());
				
				/*if(index < 1) //if index is less than 1, index was never incremented therefore no players were registered with club
				
				{
					System.out.println("There are currently no players registered with " + club.getClubName());
				}*/
			}
			else
			{
				System.out.println(clubName + " is not registered in this system, you can register this club through option 1 on the menu.");
			}
		}
		else
		{
			System.out.println("There are currently no players registered.");
		}
	}
	
	
	public void displayMenu()
    {
    	System.out.println();
    	System.out.println("1 = Enter club details.");
    	System.out.println("2 = Enter player details.");
    	System.out.println("3 = Display all registered clubs.");
    	System.out.println("4 = Display all registered players.");
    	System.out.println("5 = Display strikers who have scored over 3 goals.");
    	System.out.println("6 = Update goals scored by a player.");
    	System.out.println("7 = Transfer player to a new club.");
    	System.out.println("8 = Display all players from a particular club.");
    	System.out.println("9 = Display number of players in a particular club.");
    	System.out.println("10 = Exit.");
    	System.out.print("WHAT DO YOU WANT TO DO?(1-10) > ");
    	
    }
	
	public void menu()
    {
		int response;
		do{
			displayMenu();
			String inputResponse = scan.nextLine();
			while(!validInteger(inputResponse))
			{
				System.out.print("Error - please enter one option(numerically): ");
				inputResponse = scan.nextLine();
			}
			response = Integer.parseInt(inputResponse);
			System.out.println();
    	
    		if(response == 1)
    		{
    			enterClub();
    		}
    		
    		else if(response == 2)
    		{
    			enterPlayer();
    		}
    		
    		else if(response == 3)
    		{
    			showClubs();
    		}
    		
    		else if(response == 4)
    		{
    			showPlayers();
    		}
    		else if(response == 5)
    		{
    			listStrikers();
    		}
    		else if(response == 6)
    		{
    			updateGoalsScored();
    		}
    		else if(response == 7)
    		{
    			transferPlayer();
    		}
    		
    		else if(response == 8)
    		{
    			listClubPlayers();
    		}
    		else if (response == 9)
    		{
    			totalClubPlayers();
    		}
    		
    		else if(response < 1 || response > 10)
    		{
    			System.out.println("Please check menu, figure entered does not correspond to available options.");
    		}
    	}while(response != 10);
    	writeToClubsFile();
    	writeToPlayersFile();
    	System.out.println("The league data has been saved - Goodbye!");
    }
	
	public static void main(String[] args) 
	{
		League league = new League();
		System.out.println("LEAGUE REGISTRATION");
		league.menu();		
	}
}