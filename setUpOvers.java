package Cricket;
import javax.swing.*;
public class setUpOvers {
	JFrame f;
	int overs;
	double Finalovers;
	/** determines the number of overs per inning */
	public setUpOvers() {
		f = new JFrame();
		String StringNumber = JOptionPane.showInputDialog(f,"What is the maximum number of overs?");
		overs = Integer.parseInt(StringNumber);
		Finalovers = (double)(overs);

	}
	/** returns the maximum number of overs */
	public double returnMaxOvers() {
		return Finalovers;
	}

}