package Cricket;
import javax.swing.*;
public class CreatingTeam {
	JFrame f; 
	String Playername;
	/** creates the frame for the user to input the player name when setting up a game */
	public CreatingTeam(int teamnum){  
	    f=new JFrame();   
	     Playername=JOptionPane.showInputDialog(f,"Enter Team " +teamnum +" player Name");      
	}  
	/** returns the name of the player that was inputted by the user */
	public String playerName() {
		return Playername;
	}
	
}