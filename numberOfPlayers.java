package Cricket;
import javax.swing.*;
//Helper class to setup the game

public class numberOfPlayers {
JFrame F;
int number;

/** Determine the number of Players */

public numberOfPlayers(int teamnum) {
	F = new JFrame();
	String StringNumber = JOptionPane.showInputDialog(F,"How many players are on this team " + teamnum+ "? (please input value greater than 0");
	number = Integer.parseInt(StringNumber);
	
}
/** return the number of Players */

public int returnPlayerNUmbers() {
	return number;
}
}