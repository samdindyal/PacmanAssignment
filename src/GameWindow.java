import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.JOptionPane;
/**
	Title: The "GameWindow" class
	Date Written: February 2014 - March 2014
	Author: Samuel Dindyal
	Description: A graphical user interface that has displays a game.
*/
public class GameWindow extends JFrame implements ActionListener, PopupMenuListener{

	private JPanel controls;
	private GameComponent game;
	private JLabel score, status, time, difficultyL, pSpeedL, title, scoreAdd;
	private JButton pause, reset;
	private Timer timer;
	private int counter, scoreAddCounter;
	private boolean gameStatus;
	private JComboBox predatorSpeed, difficulty;
	private Font text, text2;
	private final String[] difficulties = new String[]{"Easy", "Medium", "Hard"};
/**
	Creates a "GameWindow".
*/
	public GameWindow()
	{
		super("Assignment 1 - Sam Dindyal");

		setUndecorated(true);
        getContentPane().setBackground(Color.DARK_GRAY);
        setUndecorated(false);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setResizable(false);
		setSize(480, 600);
		setLocationRelativeTo(null);
		timer = new Timer(10, this);
		counter = 0;
		scoreAddCounter = 0;
		title = new JLabel("PacMan Assignment");
		title.setForeground(Color.WHITE);
		title.setFont(new Font ("Arial", Font.BOLD, 42));

		add(controls());
		add(title);

		controls.setBounds(0, getHeight()-(controls.getHeight()/2), getWidth(), controls.getHeight());
		game = new GameComponent(getWidth()-20, 3*getHeight()/4, Color.BLACK);
		add(game);
		game.setBounds(10, 75, game.getWidth(), game.getHeight());
		title.setBounds((getWidth() - (int)title.getPreferredSize().getWidth())/2, (game.getY()-(int)title.getPreferredSize().getHeight())/2, (int)title.getPreferredSize().getWidth(),(int)title.getPreferredSize().getHeight());

		game.trigger();
		game.setDifficulty("Easy");

		addKeyListener(game);
		game.grabFocus();

		setVisible(true);

	}
/**
	Creates the JPanel which holds all of the controls and information about the game.
*/
	public JPanel controls()
	{
		controls = new JPanel();
		controls.setLayout(null);
		controls.setSize(getWidth(), (int)Math.floor((getHeight()/4.0)));

		time = new JLabel("Time Elapsed: 0:00.00");
		pause = new JButton("Go");
		reset = new JButton("Reset");
		score = new JLabel("Score: 0");
		scoreAdd = new JLabel("+0");
		difficultyL = new JLabel("Difficulty");
		pSpeedL = new JLabel("Predator Speed");
		text = new Font("Arial", Font.PLAIN, 12);
		text2 = new Font("Arial", Font.BOLD, 24);

		difficulty = new JComboBox(difficulties);
		difficulty.setSelectedIndex(0);

		predatorSpeed = new JComboBox(new String[]{"1", "2", "3", "4", "5"});
		predatorSpeed.setSelectedIndex(2);
		time.setForeground(Color.WHITE);
		score.setForeground(Color.WHITE);
		difficultyL.setForeground(Color.WHITE);
		pSpeedL.setForeground(Color.WHITE);


		time.setFont(text);
		score.setFont(text2);
		scoreAdd.setFont(text2);
		difficultyL.setFont(text);
		pSpeedL.setFont(text);


		controls.add(time);
		controls.add(pause);
		controls.add(reset);
		controls.add(score);
		controls.add(predatorSpeed);
		controls.add(scoreAdd);
		controls.add(difficultyL);
		controls.add(pSpeedL);
		controls.add(difficulty);

		time.setBounds(5,0, (int)time.getPreferredSize().getWidth(), (int)time.getPreferredSize().getHeight());
		pause.setBounds(getWidth()-(int)pause.getPreferredSize().getWidth()-25, 0, (int)pause.getPreferredSize().getWidth() + 25, 25);
		reset.setBounds(getWidth()-(int)pause.getPreferredSize().getWidth()-25, pause.getY()+pause.getHeight(), (int)pause.getPreferredSize().getWidth()+25, 25);
		score.setBounds(5, time.getY()+time.getHeight()+5, (int)score.getPreferredSize().getWidth(), (int)score.getPreferredSize().getHeight());
		difficulty.setBounds(time.getX()+time.getWidth() + 150, 25, (int)difficulty.getPreferredSize().getWidth(), (int)difficulty.getPreferredSize().getHeight());
		difficultyL.setBounds((int)(difficulty.getX()+(Math.abs(difficulty.getWidth()-(int)difficultyL.getPreferredSize().getWidth()))/2.0), difficulty.getY()-(int)difficultyL.getPreferredSize().getHeight()-5, (int)difficultyL.getPreferredSize().getWidth(),(int)difficultyL.getPreferredSize().getHeight());
		predatorSpeed.setBounds(difficulty.getX()-(int)predatorSpeed.getPreferredSize().getWidth()-20, difficulty.getY(), (int)predatorSpeed.getPreferredSize().getWidth(),(int)predatorSpeed.getPreferredSize().getHeight());
		pSpeedL.setBounds(predatorSpeed.getX()+((Math.abs((int)pSpeedL.getPreferredSize().getWidth()-predatorSpeed.getWidth()))/2)-25, difficultyL.getY(), (int)pSpeedL.getPreferredSize().getWidth(),(int)pSpeedL.getPreferredSize().getHeight());

		pause.addActionListener(this);
		reset.addActionListener(this);
		predatorSpeed.addPopupMenuListener(this);
		difficulty.addPopupMenuListener(this);

		controls.setOpaque(false);

		return controls;
	}
/**
	Resets everything to a default.
*/
	public void reset()
	{
		remove (game);
		game = new GameComponent(getWidth()-20, 3*getHeight()/4, Color.BLACK);
		game.setPredatorSpeed(predatorSpeed.getSelectedIndex()+1);
		game.setDifficulty(difficulties[difficulty.getSelectedIndex()]);
		add(game);
		time.setText(timer.isRunning() ? "Go" : "Pause");
		time.setText("Time Elapsed: 0:00.00");
		score.setText("Score: 0");
		counter = 0;
		scoreAddCounter = 0;

		game.repaint();
		game.setBounds(10, 75, game.getWidth(), game.getHeight());
		time.setBounds(5,0, (int)time.getPreferredSize().getWidth(), (int)time.getPreferredSize().getHeight());
		score.setBounds(5, time.getY()+time.getHeight()+5, (int)score.getPreferredSize().getWidth(), (int)score.getPreferredSize().getHeight());
	}
/**
	Resumes the game by starting the timer.
*/
	public void resume()
	{
		timer.start();
		time.setForeground(Color.WHITE);
		pause.setText("Pause");
		score.setForeground(Color.WHITE);
		game.grabFocus();

		pause.setBounds(getWidth()-(int)pause.getPreferredSize().getWidth()-25, 0, (int)pause.getPreferredSize().getWidth() + 25, 25);

	}
/**
	Pauses the game by stopping the timer.
*/
	public void pause()
	{
		timer.stop();
		time.setForeground(Color.LIGHT_GRAY);
		pause.setText("Go");
		score.setForeground(Color.LIGHT_GRAY);
		pause.setBounds(getWidth()-(int)pause.getPreferredSize().getWidth()-25, 0, (int)pause.getPreferredSize().getWidth() + 25, 25);

	}
/**
	Ends the game by asking the user if he/she would like to play again, then, resets if yes, terminates if no.
*/
	public void finish()
	{
		System.out.println(time.getText());
		System.out.println(score.getText());
        if (JOptionPane.showConfirmDialog(null, "You won! \n Would you like to play again?", "Winner!", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
          reset();
        else
        {
           JOptionPane.showMessageDialog(this, "Thanks for playing! \nGoodbye!", null, JOptionPane.PLAIN_MESSAGE);
           System.exit(0);
       }

	}
	public static void main (String[] args)
	{
		new GameWindow();
	}
/**
	Handles ActionListener events.
*/
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == timer)
		{
			int tempScore = game.getScore();
			int oldCounterMod = (scoreAddCounter/100)%2;
			counter++;
			if (scoreAdd.isVisible())
				scoreAddCounter++;
			int newCounterMod = (scoreAddCounter/100)%2;
			time.setText("Time Elapsed: " + (int)((counter/100.0)/60)%60 + ":"+ ((int)(counter/100.0)%60 < 10 ? "0" : "")  + (int)(counter/100.0)%60  + "."+ ((int)(counter%100) <10 ? "0" : "") +counter%100 + "");
			game.trigger();
			score.setText("Score: " + game.getScore());
			if (tempScore < game.getScore())
			{
					scoreAdd.setVisible(true);
					scoreAdd.setForeground(game.getLastKilledColor());
					scoreAdd.setText("+" + game.getLastKilledRanking());
					scoreAddCounter = 0;
					scoreAdd.setBounds(score.getX()+score.getWidth()+15, score.getY(), (int)score.getPreferredSize().getWidth(), (int)score.getPreferredSize().getHeight());
			}
			if (oldCounterMod != newCounterMod && scoreAddCounter > 0)
				if (scoreAdd.isVisible())
					scoreAdd.setVisible(false);

			time.setBounds(5,0, (int)time.getPreferredSize().getWidth(), (int)time.getPreferredSize().getHeight());
			score.setBounds(5, time.getY()+time.getHeight()+5, (int)score.getPreferredSize().getWidth(), (int)score.getPreferredSize().getHeight());
			if (!predatorSpeed.hasFocus())
				game.grabFocus();

			if (game.amountOfPrey() == 0)
				finish();
		}
		else if (e.getSource() == pause)
			if(timer.isRunning())
				pause();
			else
				resume();
		else if (e.getSource() == reset)
			reset();
	}

	public void popupMenuCanceled(PopupMenuEvent e) {}
/**
	Acts if popup menus are unselected.
*/
	public void popupMenuWillBecomeInvisible(PopupMenuEvent e)
	{
		if (e.getSource() == difficulty)
			game.setDifficulty(difficulties[difficulty.getSelectedIndex()]);
		else if (e.getSource() == predatorSpeed)
			game.setPredatorSpeed(predatorSpeed.getSelectedIndex()+1);
		if (gameStatus)
			resume();
	}
/**
	Acts if popup menus are selected.
*/
	public void popupMenuWillBecomeVisible(PopupMenuEvent e)
	{
		gameStatus = timer.isRunning();
		pause();

	}
}
