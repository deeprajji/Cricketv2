package Cricket;
import javax.swing.*;
public class CreatingTeam {
	JFrame f; 
	String Playername;
	
	public CreatingTeam(int teamnum){  
	    f=new JFrame();   
	     Playername=JOptionPane.showInputDialog(f,"Enter Team " +teamnum +" player Name");      
	}  
	public String playerName() {
		return Playername;
	}
	
}
