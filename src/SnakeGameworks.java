import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * 
 */

/**
 * @author Brandon Frison 300 243 731
 *
 */
public class SnakeGameworks extends JPanel implements KeyListener, ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8654330300510178806L;
	
	private int[] snklengthX = new int[750];
	private int[] snklengthY = new int[750];
	
	private boolean left, right, up, down, gameover, easy, medium, hard = false;
	
	private ImageIcon titleForGame, upHead, rightHead, downHead, leftHead, snakeBody, pokeball;
	private Timer timer;


	private int snklength = 2;
	private int score = 0;
	private String Highscore = "";
	private int moved = 0;
	
	private int[] pokeballxpos = {25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625,650,675,700,725,750,775,800,825,850};
	private int[] pokeballypos = {125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625,650};
	
	private Random randompos = new Random();
	
	private int posX = randompos.nextInt(34);
	private int posY = randompos.nextInt(22);
	
	
	
	public SnakeGameworks()
	{
		
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(true);
		
	}
	public void paint(Graphics g)
	{
		if(Highscore.equals(""))
		{
			Highscore = this.getHighScore();
		}
		
		
		if(moved == 0)
		{
			snklengthX[0] = 100;
			snklengthX[1] = 75;
			
			
			snklengthY[0] = 150;
			snklengthY[1] = 150;
			
		}
		//background
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, 905, 750);
		
		// Border for the Snake title
		g.setColor(Color.RED);
		g.drawRect(24,1,421,104);
		
		// Importing the title image
		titleForGame = new ImageIcon("SnakeGameTitle.jpg");
		titleForGame.paintIcon(this, g, 25, 2);
		
		// Border for the game itself
		g.setColor(Color.GREEN);
		g.drawRect(24, 124, 851, 577);
		
		
		//for the gameplay area
		g.setColor(Color.BLACK);
		g.fillRect(25, 125, 850, 575);
		
		//for the scores
		g.setColor(Color.WHITE);
		g.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		g.drawString("Current Score is "+score, 700, 30);
		g.setColor(Color.RED);
		g.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		g.drawString("Highscore is " + Highscore, 700, 50);
		g.drawString("numpad 5 to input a new highscore", 450, 105);
		//for the controls
		g.setColor(Color.WHITE);
		g.drawString("Arrow Keys to move", 450, 30);
		g.drawString("TO START PRESS:", 450, 45);
		g.drawString("numpad 1 for HARD (10 each)", 450, 60);
		g.drawString("numpad 2 for MEDIUM (8 each)", 450, 75);
		g.drawString("numpad 3 for EASY (6 each)", 450, 90);
		
		
			
		rightHead = new ImageIcon("ekansHeadRight.jpg");
		rightHead.paintIcon(this, g, snklengthX[0], snklengthY[0]);
		
		for(int i = 0; i < snklength; i++)
		{
			if(i == 0 && right)
			{
			rightHead = new ImageIcon("ekansHeadRight.jpg");
			rightHead.paintIcon(this, g, snklengthX[i], snklengthY[i]);
			}
			if(i == 0 && left)
			{
			leftHead = new ImageIcon("ekansHeadLeft.jpg");
			leftHead.paintIcon(this, g, snklengthX[i], snklengthY[i]);
			}
			if(i == 0 && down)
			{
			downHead = new ImageIcon("ekansHeadDown.jpg");
			downHead.paintIcon(this, g, snklengthX[i], snklengthY[i]);
			}
			if(i == 0 && up)
			{
			upHead = new ImageIcon("ekansHeadUp.jpg");
			upHead.paintIcon(this, g, snklengthX[i], snklengthY[i]);
			}
			if(i != 0)
			{
				snakeBody = new ImageIcon("ekansBody.jpg");
				snakeBody.paintIcon(this, g, snklengthX[i], snklengthY[i]);
			}
		}
		
		pokeball = new ImageIcon("pokeball.jpg");
		
		if((pokeballxpos[posX] == snklengthX[0]) && (pokeballypos[posY] == snklengthY[0]))
		{
		
			snklength++;
			posX = randompos.nextInt(34);
			posY = randompos.nextInt(22);
			if(easy)
			{
				score += 6;
				
			}
			if(medium)
			{
				score += 8;
			}
			if(hard)
			{
				score += 10;
			}
			
		}
		pokeball.paintIcon(this, g, pokeballxpos[posX], pokeballypos[posY]);
		
		for (int a = 1; a < snklength; a++)
		{
			if(snklengthX[a] == snklengthX[0] && snklengthY[a] == snklengthY[0])
			{
				right = false;
				left = false;
				up = false;
				down = false;
				gameover = true;
				
				
			}
		
		}
		
		if(gameover){
			g.setColor(Color.WHITE);
			g.setFont(new Font("Comic Sans MS", Font.BOLD, 50));
			g.drawString("Game Over", 300, 300);
			g.setFont(new Font("Comic Sans MS", Font.PLAIN, 40));
			g.drawString("Choose a Difficulty to restart game", 150, 400);
			g.setFont(new Font("Comic Sans MS", Font.ITALIC, 40));
			if(Integer.parseInt((Highscore.split("=")[1])) < score){
			g.setColor(Color.CYAN);
			g.drawString("Press Numpad 5", 100, 500);
			g.drawString("You have beaten the Highscore!!!", 100, 550);
			
			}
			timer.stop();
			}
		
		
		g.dispose();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		if(right)
		{
			for(int r = snklength-1; r>=0; r--)
			{
				snklengthY[r+1] = snklengthY[r];
			}
			for(int r = snklength-1; r>=0; r--)
			{
				if(r == 0)
				{
					snklengthX[r] = snklengthX[r] + 25;
				}
				else
				{
					snklengthX[r] = snklengthX[r-1];
				}
				if(snklengthX[r] > 850)
				{
					right = false;
					left = false;
					up = false;
					down = false;
					gameover = true;
					
				}
			}
			repaint();
		}
		if(left)
		{
			for(int r = snklength-1; r>=0; r--)
			{
				snklengthY[r+1] = snklengthY[r];
			}
			for(int r = snklength-1; r>=0; r--)
			{
				if(r == 0)
				{
					snklengthX[r] = snklengthX[r] - 25;
				}
				else
				{
					snklengthX[r] = snklengthX[r-1];
				}
				if(snklengthX[r] < 25)
				{
					right = false;
					left = false;
					up = false;
					down = false;
					gameover = true;
				}
			}
			repaint();
		}
		if(down)
		{
			for(int r = snklength-1; r>=0; r--)
			{
				snklengthX[r+1] = snklengthX[r];
			}
			for(int r = snklength-1; r>=0; r--)
			{
				if(r == 0)
				{
					snklengthY[r] = snklengthY[r] + 25;
				}
				else
				{
					snklengthY[r] = snklengthY[r-1];
				}
				if(snklengthY[r] > 675)
				{
					right = false;
					left = false;
					up = false;
					down = false;
					gameover = true;
				}
			}
			repaint();
		}
		if(up)
		{
			for(int r = snklength-1; r>=0; r--)
			{
				snklengthX[r+1] = snklengthX[r];
			}
			for(int r = snklength-1; r>=0; r--)
			{
				if(r == 0)
				{
					snklengthY[r] = snklengthY[r] - 25;
				}
				else
				{
					snklengthY[r] = snklengthY[r-1];
				}
				if(snklengthY[r] < 125)
				{
					right = false;
					left = false;
					up = false;
					down = false;
					gameover = true;
				}
			}
			repaint();
		}
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_NUMPAD5)
		{
			checkHighscore(); //numpad 5 for checking the highscore.
		}
		if(e.getKeyCode() == KeyEvent.VK_NUMPAD1)
		{
			//sets snake to default values
			moved = 0;
			score = 0;
			snklength = 2;
			gameover = false; //sets gameover to false so that you can start the game
			hard = true;
			medium = false;
			easy = false;
			timer = new Timer(50, this); //starts a timer at the fast speed.
			timer.start();
			repaint();
			
		}
		if(e.getKeyCode() == KeyEvent.VK_NUMPAD2)
		{
			
			moved = 0;
			score = 0;
			snklength = 2;
			gameover = false;
			hard = false;
			medium = true;
			easy = false;
			timer = new Timer(100, this); //starts a timer at the medium speed
			timer.start();
			repaint();
			
		}
		if(e.getKeyCode() == KeyEvent.VK_NUMPAD3)
		{
			
			moved = 0;
			score = 0;
			snklength = 2;
			gameover = false;
			hard = false;
			medium = false;
			easy = true;
			timer = new Timer(150, this); //starts a timer at the slow speed
			timer.start();
			repaint();
			
		}
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			if(!gameover){
			moved++;
			right = true;
			if(!left)
			{
				right = true;
			}
			else
			{
				right = false;
				left = true;
			}
			
			down = false;
			up = false;
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			if(!gameover){
			moved++;
			left = true;
			if(!right)
			{
				left = true;
			}
			else
			{
				left = false;
				right = true;
			}
			
			down = false;
			up = false;
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_UP)
		{
			if(!gameover){
			moved++;
			up = true;
			if(!down)
			{
				up = true;
			}
			else
			{
				up = false;
				down = true;
			}
			
			right = false;
			left = false;
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			if(!gameover){
			moved++;
			down = true;
			if(!up)
			{
				down = true;
			}
			else
			{
				down = false;
				up = true;
			}
			
			right = false;
			left = false;
			}
		}
		
	}
	
	public String getHighScore()
	{
		FileReader fileR = null;
		BufferedReader reader = null;
		try
		{
			fileR = new FileReader("highscore.dat");
			reader = new BufferedReader(fileR);
			return reader.readLine();
		} 
		catch (Exception ex) 
		{
			return "Anonymous=0";
		}
		finally
		{
			try {
				if (reader != null){
				reader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void checkHighscore()
	{
		if(score > Integer.parseInt((Highscore.split("=")[1])))
		{
			String name = JOptionPane.showInputDialog("you have beaten the Highscore. Please Enter your name");
			Highscore = name + "=" + score;
			
			File scoresFile = new File("highscore.dat");
			if(!scoresFile.exists())
			{
				try {
					scoresFile.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			FileWriter writeFile = null;
			BufferedWriter writer = null;
			try
			{
				writeFile = new FileWriter(scoresFile);
				writer = new BufferedWriter(writeFile);
				writer.write(this.Highscore);
			}
			catch (IOException e){}
			finally 
			{
				if(writer != null)
				{
					try {
						writer.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}
	
	
	
}
