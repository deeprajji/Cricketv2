package Cricket;
import javax.swing.*;
public class setUpOvers {
	JFrame f;
	int overs;
	double Finalovers;
	// Determine number of overs in the game
	public setUpOvers() {
		f = new JFrame();
		String StringNumber = JOptionPane.showInputDialog(f,"What is the maximum number of overs?");
		overs = Integer.parseInt(StringNumber);
		Finalovers = (double)(overs);

	}
	public double returnMaxOvers() {
		return Finalovers;
	}

}
