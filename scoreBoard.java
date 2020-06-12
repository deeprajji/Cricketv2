package Cricket;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

// This is the main class where all the action happens 

public class scoreBoard implements ActionListener {
	
// define and initialize class variables 
	JPanel panel = new JPanel();
	JFrame frame = new JFrame();
	JFrame asker = new JFrame();
	int resultOfBall;	 //the result of each ball, updates after each ball 
	
	
	private String overs;//firstInning overs
	private String secondInningOvers ;  //second inning overs 
	private int balls = 0; //first inning balls 
	private int secondInningBalls = 0;  // second inning balls 
	private int team1runs= 0;  //the number of runs team 1 has scored 
	private int team2runs = 0; //the number of runs team 2 has scored  
	private int outs= 0;  // the number of outs team 1 has 
	private int secondInningOUts = 0;  // the number of outs for the team batting 2nd 
	private int ScoreToBeat;  //the score to beat 

	private double maxBalls;  // the maximum number of balls per over 
	Team realteam1;   // the real first team that is given its reference when constructed 
	Team realteam2; // the real second team that is given its reference when constructed 
	Team battingTeam;  // the team that is currently batting 
	Team BowlingTeam;   // the team that is currently bowling  
	JLabel label;
	private double requiredStrikeRate;  // the required runs per ball necessary for the team batting second to win 
	private int ballsLeft;  //the number of balls left in the innings 

/**  main controller method */
	/**	the main method is where the entire code is ran, it sets up the number of players on each side and who the players are. Then the first inning begins and it keeps taking the result of each ball, then displaying the scoreboard after each ball. First the first inning happens, then the second inning. In between innings, the bowling and batting team is switched. After both innings the winner is decided */

public static void main(String[] args) throws InterruptedException {
	
	// 1.0 Set up the rules for the game - Overs, and players 
	settingUpGame x = new settingUpGame();
	
	// 2.0 Start the game and set the team 
	
	setUpOvers y = new setUpOvers();
	scoreBoard firstTrial = new scoreBoard(y.returnMaxOvers(), x.team1, x.team2);
	
	// Set the batting team as Team 1 and bowling team as Team 2 
	
	firstTrial.battingTeam = firstTrial.realteam1;
	firstTrial.BowlingTeam = firstTrial.realteam2;
	JFrame ballResultAsker;
	Border border = BorderFactory.createLineBorder(Color.white, 5);
	
	// Batting starts - Innings 1 
	// Till maximum number of overs are bowled or the team is bowled out 
	
	while(firstTrial.balls < firstTrial.maxBalls && firstTrial.outs < firstTrial.battingTeam.team.size()) {
		ballResultAsker = new JFrame();
		// Determine the result of the ball [ 0-6] runs scored or -1 denotes the player is out 
		
		String result = JOptionPane.showInputDialog(ballResultAsker,"What was the result of the ball?");
		if(result.compareTo("out") ==0|| result.compareTo("OUT")==0) {
			firstTrial.resultOfBall= -1;
		}
		
		else {
		firstTrial.resultOfBall = Integer.parseInt(result);
		}
		if(firstTrial.resultOfBall > -1 && firstTrial.resultOfBall < 7) {
			firstTrial.team1runs += firstTrial.resultOfBall;
			firstTrial.balls ++;
			firstTrial.recalculateOvers();
			JLabel score = new JLabel("Score: " + firstTrial.team1runs + "-" + firstTrial.outs);
			score.setBorder(border);
			score.setBackground(Color.GREEN);
			score.setOpaque(true);
			JLabel numberOfOvers = new JLabel("Overs: " + firstTrial.overs);
			numberOfOvers.setBorder(border);
			numberOfOvers.setBackground(Color.GREEN);
			numberOfOvers.setOpaque(true);
			
			
			JFrame scoreBoard = new JFrame();
			scoreBoard.setBackground(Color.pink);
			
			JPanel ScoreBoardPanel = new JPanel();
			ScoreBoardPanel.setBorder(BorderFactory.createEmptyBorder(290,290,290,290));
			ScoreBoardPanel.setLayout(new GridLayout(0, 1));
			ScoreBoardPanel.setBackground(Color.BLACK);
			
			scoreBoard.add(ScoreBoardPanel, BorderLayout.CENTER);
			scoreBoard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			scoreBoard.setTitle("Score Board");
			scoreBoard.pack(); 
			
			
			ScoreBoardPanel.add(score);
			ScoreBoardPanel.add(numberOfOvers);
			scoreBoard.setSize(1600, 1600);
			scoreBoard.setVisible(true);
			Thread.sleep(5000); // waits 5 seconds before it removes the frame and goes to the next ball
			scoreBoard.dispose();
		}
		else {
			
			firstTrial.outs ++;
			firstTrial.balls++;
			firstTrial.recalculateOvers();
			JLabel score = new JLabel("Score: " + firstTrial.team1runs + "-" + firstTrial.outs);
			score.setBorder(border);
			score.setBackground(Color.RED);
		
			score.setOpaque(true);
			JLabel numberOfOvers = new JLabel("Overs: " + firstTrial.overs);
			numberOfOvers.setBorder(border);
			numberOfOvers.setBackground(Color.RED);
			numberOfOvers.setOpaque(true);
			JFrame scoreBoard = new JFrame();
		
			scoreBoard.setBackground(Color.pink);
			
			JPanel ScoreBoardPanel = new JPanel();
			ScoreBoardPanel.setBorder(BorderFactory.createEmptyBorder(290,290,290,290));
			ScoreBoardPanel.setLayout(new GridLayout(0, 1));
			ScoreBoardPanel.setBackground(Color.BLACK);
			
			scoreBoard.add(ScoreBoardPanel, BorderLayout.CENTER);
			scoreBoard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			scoreBoard.setTitle("Score Board");
			scoreBoard.pack(); 
		
			
			
			ScoreBoardPanel.add(score);
			ScoreBoardPanel.add(numberOfOvers);
			scoreBoard.setSize(1600,1600);
			scoreBoard.setVisible(true);
			Thread.sleep(5000); // waits 5 seconds before it removes the frame and goes to the next ball
			scoreBoard.dispose();
		
			
		}
		
	
		
	}
	
	// Innings Second; Determine the score to beat 
	
	
	firstTrial.ScoreToBeat = firstTrial.team1runs;
	//Reverse team batting and bowling 
	
	firstTrial.BowlingTeam = firstTrial.realteam1;
	firstTrial.battingTeam = firstTrial.realteam2;
	
	
	while((firstTrial.secondInningBalls <= firstTrial.maxBalls) && (firstTrial.secondInningOUts<firstTrial.battingTeam.team.size()) && (firstTrial.team2runs <= firstTrial.ScoreToBeat))  
	{
		ballResultAsker = new JFrame();
		String result = JOptionPane.showInputDialog(ballResultAsker, "What was the result of the ball?");
		if(result.compareTo("out") ==0|| result.compareTo("OUT")==0) {
			firstTrial.resultOfBall= -1;
		}
		else {
		firstTrial.resultOfBall = Integer.parseInt(result);
		}
		if(firstTrial.resultOfBall > -1 && firstTrial.resultOfBall < 7) {
			firstTrial.team2runs += firstTrial.resultOfBall;
			firstTrial.secondInningBalls++;
			firstTrial.recalculateSecondInningOvers();
			
			firstTrial.ballsLeft = (int)(firstTrial.maxBalls) - firstTrial.secondInningBalls;
			firstTrial.requiredStrikeRate = (double)(firstTrial.ScoreToBeat - firstTrial.team2runs)/ firstTrial.ballsLeft;
			JLabel score = new JLabel("Score: " + firstTrial.team2runs +" - " + firstTrial.secondInningOUts);
			score.setBorder(border);
			score.setBackground(Color.GREEN);
			score.setOpaque(true);
			
			JLabel numberOfOvers = new JLabel("Overs: " + firstTrial.secondInningOvers);
			numberOfOvers.setBorder(border);
			numberOfOvers.setBackground(Color.GREEN);
			numberOfOvers.setOpaque(true);
			
			JLabel numberOfBallsLeft = new JLabel("Number of Balls Left: " + firstTrial.ballsLeft);
			numberOfBallsLeft.setBorder(border);
			numberOfBallsLeft.setBackground(Color.GREEN);
			numberOfBallsLeft.setOpaque(true);
			
			JLabel requiredStrikeRate = new JLabel("Required Strike Rate to win: " + firstTrial.requiredStrikeRate);
			requiredStrikeRate.setBorder(border);
			requiredStrikeRate.setBackground(Color.GREEN);
			requiredStrikeRate.setOpaque(true);
			
			JLabel whichInning = new JLabel("We are in Innings 2!");
			whichInning.setBorder(border);
			whichInning.setBackground(Color.LIGHT_GRAY);
			whichInning.setOpaque(true);
			
			JLabel scoreNeeded = new JLabel("Score to beat: "+ firstTrial.ScoreToBeat);
			scoreNeeded.setBorder(border);
			scoreNeeded.setBackground(Color.LIGHT_GRAY);
			scoreNeeded.setOpaque(true);
			
			
			JFrame scoreBoard = new JFrame();
			JPanel ScoreBoardPanel = new JPanel();
			ScoreBoardPanel.setBorder(BorderFactory.createEmptyBorder(290,290,290,290));
			ScoreBoardPanel.setLayout(new GridLayout(0, 1));
			ScoreBoardPanel.setBackground(Color.BLACK);
			
			scoreBoard.add(ScoreBoardPanel, BorderLayout.CENTER);
			scoreBoard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			scoreBoard.setTitle("Second Inning");
			scoreBoard.pack(); 
			
			
			ScoreBoardPanel.add(whichInning);
			ScoreBoardPanel.add(scoreNeeded);
			ScoreBoardPanel.add(score);
			ScoreBoardPanel.add(numberOfOvers);
			ScoreBoardPanel.add(numberOfBallsLeft);
			ScoreBoardPanel.add(requiredStrikeRate);
			scoreBoard.setSize(1600,1600);
			scoreBoard.setVisible(true);
			Thread.sleep(5000); // waits 5 seconds before it removes the frame and goes to the next ball
			scoreBoard.dispose();
			
		}
		else {
			firstTrial.secondInningOUts++;
			firstTrial.secondInningBalls++;
			firstTrial.recalculateSecondInningOvers();
			firstTrial.ballsLeft = (int)(firstTrial.maxBalls * 6) - firstTrial.secondInningBalls;
			firstTrial.requiredStrikeRate = (double)(firstTrial.ScoreToBeat - firstTrial.team2runs)/ firstTrial.ballsLeft;
			
			firstTrial.requiredStrikeRate = (double)(firstTrial.ScoreToBeat - firstTrial.team2runs)/ firstTrial.ballsLeft;
			
			JLabel score = new JLabel("Score: " + firstTrial.team2runs +" - " + firstTrial.secondInningOUts);
			score.setBorder(border);
			score.setBackground(Color.RED);
			score.setOpaque(true);
			
			JLabel numberOfOvers = new JLabel("Overs: " + firstTrial.secondInningOvers);
			numberOfOvers.setBorder(border);
			numberOfOvers.setBackground(Color.RED);
			numberOfOvers.setOpaque(true);
			
			JLabel numberOfBallsLeft = new JLabel("Number of Balls Left: " + firstTrial.ballsLeft);
			numberOfBallsLeft.setBorder(border);
			numberOfBallsLeft.setBackground(Color.RED);
			numberOfBallsLeft.setOpaque(true);
			
			JLabel requiredStrikeRate = new JLabel("Required Strike Rate to win: " + firstTrial.requiredStrikeRate);
			requiredStrikeRate.setBorder(border);
			requiredStrikeRate.setBackground(Color.RED);
			requiredStrikeRate.setOpaque(true);
			
			JLabel whichInning = new JLabel("We are in Inning 2!");
			whichInning.setBorder(border);
			whichInning.setBackground(Color.LIGHT_GRAY);
			whichInning.setOpaque(true);
			
			JFrame scoreBoard = new JFrame();
			JPanel ScoreBoardPanel = new JPanel();
			ScoreBoardPanel.setBorder(BorderFactory.createEmptyBorder(90,90,90,90));
			ScoreBoardPanel.setLayout(new GridLayout(0, 1));
			ScoreBoardPanel.setBackground(Color.BLACK);
			
			scoreBoard.add(ScoreBoardPanel, BorderLayout.CENTER);
			scoreBoard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			scoreBoard.setTitle("Second Inning");
			scoreBoard.pack(); 
			
			
			ScoreBoardPanel.add(whichInning);
			ScoreBoardPanel.add(score);
			ScoreBoardPanel.add(numberOfOvers);
			ScoreBoardPanel.add(numberOfBallsLeft);
			ScoreBoardPanel.add(requiredStrikeRate);
			scoreBoard.setSize(1600,1600);
			scoreBoard.setVisible(true);
			Thread.sleep(5000); // waits 5 seconds before it removes the frame and goes to the next ball
			scoreBoard.dispose();
		}
	
	}
	
	//3.0 Determine which team won the match 
	// Second team wins if they reach the scores to beat with balls to spare 
	// Second team loses if they lose all wickets before all the balls are bowled in the innings 
	// First and second team draw if they score equal runs 
	
	if(firstTrial.team2runs > firstTrial.ScoreToBeat) {
		JFrame winner = new JFrame();
		JPanel winnerPanel = new JPanel();
		winnerPanel.setBorder(BorderFactory.createEmptyBorder(190,190,190,190));
		winnerPanel.setLayout(new GridLayout(0, 1));
		
		winner.add(winnerPanel, BorderLayout.CENTER);
		winner.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		winner.setTitle("Second Inning");
		winner.pack(); 
		winner.setVisible(true);
		int wicketsleft = firstTrial.realteam2.team.size()-firstTrial.secondInningOUts;
		JLabel winningTeam = new JLabel("Congratulations to the team who batted second! You have won with "+ wicketsleft + " Wickets Left!!");
		JLabel team1scored = new JLabel("The team who batted first: " + firstTrial.team1runs + "-" + firstTrial.outs);
		JLabel team2scored = new JLabel("The team who batted second: " + firstTrial.team2runs +"-" + firstTrial.secondInningOUts);
		winnerPanel.add(winningTeam);
		winnerPanel.add(team1scored);
		winnerPanel.add(team2scored);
		
		
	}
	else if(firstTrial.team2runs < firstTrial.ScoreToBeat) {
		JFrame winner = new JFrame();
		JPanel winnerPanel = new JPanel();
		winnerPanel.setBorder(BorderFactory.createEmptyBorder(190,190,190,190));
		winnerPanel.setLayout(new GridLayout(0, 1));
		
		winner.add(winnerPanel, BorderLayout.CENTER);
		winner.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		winner.setTitle("result");
		winner.pack(); 
		winner.setVisible(true);
		int runsLeft = firstTrial.ScoreToBeat - firstTrial.team2runs;
		JLabel winningTeam = new JLabel("Congratulations to the team that batted first! you have won by a margin of " + runsLeft +" runs!!");
		JLabel team1scored = new JLabel("The team who batted first: " + firstTrial.team1runs + "-" + firstTrial.outs);
		JLabel team2scored = new JLabel("The team who batted second: " + firstTrial.team2runs +"-" + firstTrial.secondInningOUts);
		winnerPanel.add(winningTeam);
		winnerPanel.add(team1scored);
		winnerPanel.add(team2scored);
	}
	else {
		JFrame winner = new JFrame();
		JPanel winnerPanel = new JPanel();
		winnerPanel.setBorder(BorderFactory.createEmptyBorder(190,190,190,190));
		winnerPanel.setLayout(new GridLayout(0, 1));
		
		winner.add(winnerPanel, BorderLayout.CENTER);
		winner.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		winner.setTitle("result");
		winner.pack(); 
		winner.setVisible(true);
		JLabel draw = new JLabel("Game ends in a draw, create a new game to decide the winner");
		winnerPanel.add(draw);
		JLabel team1scored = new JLabel("The team who batted first: " + firstTrial.team1runs + "-" + firstTrial.outs);
		JLabel team2scored = new JLabel("The team who batted second: " + firstTrial.team2runs +"-" + firstTrial.secondInningOUts);
		
		winnerPanel.add(team1scored);
		winnerPanel.add(team2scored);
	}
} // end of main 
/**	 There are 6 balls in a cricket over  */
/**   constructor method, needs the max overs and references for each team */
public scoreBoard(double maxOvers, Team team1, Team team2)
{

	
	this.maxBalls = maxOvers * 6;
	this.realteam1 = team1;
	this.realteam2 = team2;
	
} // end of Constructor 

 
/**  determines what the over is for the first inning depending on the number of balls that have been bowled thus far */

public void recalculateOvers(){
	overs = "" + (balls/6) + "." + (balls%6);
}


/**  determines what the over is for the second inning depending on the number of balls that have been bowled thus far */

public void recalculateSecondInningOvers() {
	secondInningOvers = "" + (secondInningBalls/6) +"."+(secondInningBalls%6);
}

@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	
}
}