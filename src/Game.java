import java.awt.Color;
import java.awt.Container;

import javax.swing.*;

public class Game extends JFrame{
	JPanel pan;
	JLabel count=new JLabel("hi");
	JLabel count2=new JLabel();
	JLabel pt;


private Container container;
public static void main(String args[])
{
new Game();
}
public Game(){
	super("Othello");
	setSize(1100,740);
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	setResizable(false);
	//JButton start=new JButton("Start");
	//.setLayout(new BoxLayout(maing,BoxLayout.Y_AXIS));

	GameButtons a=new GameButtons();
	 container = getContentPane();
	  container.setLayout(null);
		count=GameButtons.bco();
		count2=GameButtons.wco();
	pan=GameButtons.getPanel();
	count.setBounds(700, 200, 400, 100);
	//count.setBackground(Color.BLUE);
	count2.setBounds(700, 300, 400, 100);
	pan.setOpaque(true);
	pan.setBackground(Color.black);
	pt=GameButtons.turn();
	pt.setBounds(800,400,400,100);
	
	//p2.setBackground(Color.green);
	

	container.add(pt);
	
	container.add(pan);
	container.add(count);
	container.add(count2);
	
	add(pan);


	setVisible(true);
	
}
}
