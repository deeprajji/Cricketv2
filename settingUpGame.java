package Cricket;
import java.util.ArrayList;
public class settingUpGame {
	
	/* This class helps to setup the game. Like players on each team */
	
	// Create Teams 
		Team team1 = new Team();
		Team team2 = new Team();
		int teamnum = 1;
	
		
		/** constructor which adds the players to the team by asking for their names and creating a new player object per player inputed*/
		
		public settingUpGame() {
			
	// Get Number and names of Players on Team 1 
			numberOfPlayers temp = new numberOfPlayers(teamnum);
			team1.team = new ArrayList<Player>(temp.returnPlayerNUmbers());
			CreatingTeam placeHolder;
			
			for(int i =0; i < temp.returnPlayerNUmbers(); i++) {
				placeHolder = new CreatingTeam(teamnum);
				team1.team.add(new Player(placeHolder.playerName()));
			}
			
	// Get Number and names of Players on Team 2 
			teamnum++;
			temp = new numberOfPlayers(teamnum);
			team2.team = new ArrayList<Player>(temp.returnPlayerNUmbers());
			for(int i =0; i < temp.returnPlayerNUmbers(); i++) {
				placeHolder = new CreatingTeam(teamnum);
				team2.team.add(new Player(placeHolder.playerName()));
			}
			
			
		}
		
	}