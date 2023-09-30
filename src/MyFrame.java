import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class MyFrame extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	JLabel info, mode, developer;
	JPanel button;
	JOptionPane pane;
	JButton[] buttons;
	
	//IF TRUE THEN X OTHERWISE O
	boolean XorO;
	boolean win=false, comp = false, compTurn = false;
	
	String bot = "X";
	Random random = new Random();
	int sec=5, index =0;
	
	MyFrame(){
	//BUTTONS
	buttons = new JButton[12];
	String but[] = {"", "", "", "", "", "", "", "", "", "Bot", "2 Player", "<--"};
	for(int i=0; i<12; i++) {
		buttons[i] = new JButton(but[i]);
		buttons[i].setEnabled(false);
		buttons[i].setFocusable(false);
		buttons[i].setFont(new Font("MV Boli",Font.PLAIN,75));
		buttons[i].setBackground(new Color(241, 127, 122));
		buttons[i].addActionListener(this);
		if(i>8)
			buttons[i].setFont(new Font("MV Boli",Font.PLAIN,15));
	}
		 
	buttons[9].setBounds(70, 80, 100, 25);
	buttons[10].setBounds(250, 80, 100, 25);
	buttons[11].setBounds(0, 0, 70, 60);
	
	//INFO LABEL
	info = new JLabel();
	info.setBackground(Color.BLACK);
	info.setOpaque(true);
	info.setBounds(0,0,420,60);
	info.setFont(new Font("MV Boli",Font.PLAIN,50));
	info.setForeground(new Color(255,25,25));
	info.setHorizontalAlignment(JLabel.CENTER);
	
	//MODE LABEL
	mode = new JLabel("Choose Mode:");
	mode.setOpaque(true);
	mode.setBounds(100,20,200,60);
	mode.setFont(new Font("MV Boli",Font.PLAIN,30));
	mode.setForeground(new Color(255,25,25));
	mode.setHorizontalAlignment(JLabel.CENTER);
		 
	//DEVELOPER LABEL
	developer = new JLabel("<html>Developed by: Ayush<br>Company: DeadSOUL<br><br>This is an open source software and built from scratch.</html>");
	developer.setBounds(25,200,360,170);
	developer.setFont(new Font("MV Boli",Font.PLAIN,18));
	developer.setForeground(new Color(167, 47, 211));
	developer.setHorizontalAlignment(JLabel.CENTER);
	
	//BUTTONS PANEL
	button = new JPanel();
	button.setBounds(0,60,420,320);
	this.add(developer);
	
	//FRAME
	this.setTitle("Tic Tac Toe");
	this.setIconImage(new ImageIcon("src/icon.jpg").getImage());
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setLayout(null);
	this.setSize(420,420);
	this.setResizable(false);
	this.setLocationRelativeTo(null);
	this.getContentPane().setBackground(Color.BLACK);
	this.add(info);
	this.add(button);
	this.setVisible(true);
	
	begin();

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		for(int i=0; i<9; i++) {
			if(e.getSource()==buttons[i]) {
				if(buttons[i].getText().equals("")) {
					if(XorO && !comp) {
						info.setText("O TURN");
						if(e.getSource()==buttons[i]) {
							buttons[i].setText("X");
							XorO=false;
						}
					}
					else if (!XorO && !comp){
						info.setText("X TURN");
						if(e.getSource()==buttons[i]) {
							buttons[i].setText("O");
							XorO=true;
						}
					}
					
					if(XorO && comp && compTurn == false && !bot.equals("X")) {
						info.setText("O TURN");
						if(e.getSource()==buttons[i]) {
							buttons[i].setText("X");
							XorO=false;
						}
						compTurn = true;
					}
					else if (!XorO && comp && compTurn == false && !bot.equals("O")){
						info.setText("X TURN");
						if(e.getSource()==buttons[i]) {
							buttons[i].setText("O");
							XorO=true;
						}
						compTurn = true;
					}
					win = false;
					check();
					if(comp && !win) {
						for(i=0; i<9; i++) {
							if(buttons[i].getText().equals(""))
								compLogic();
						}
					}
					win = false;
					check();
				}
			}
		}
		if(e.getSource()==buttons[10]) {
			first();
			for(int i1=0; i1<9; i1++) 
				button.add(buttons[i1]);
		}
		if(e.getSource()==buttons[9]) {
			int z = random.nextInt(2);
			if(z==0) {
				XorO=true;
				mode.setText("Bot: O, User: X");
				bot = "O";
			}
			else {
				XorO=false;
				mode.setText("Bot: X, User: O");
				bot = "X";
			}
			comp=true;
			first();
			for(int i1=0; i1<9; i1++) 
				button.add(buttons[i1]);
			this.setSize(420,500);
			this.add(mode);
			button.remove(mode);
			mode.setFont(new Font("MV Boli",Font.PLAIN,25));
			mode.setBackground(Color.black);
			mode.setForeground(Color.white);
			mode.setBounds(80, 395, 250, 40);		
		}
		if(e.getSource()==buttons[11]) {
			begin();
			this.remove(mode);
			button.add(mode);
			comp = false;
			info.remove(buttons[11]);
			mode.setBounds(100,20,200,60);
			mode.setBackground(button.getBackground());
			mode.setForeground(Color.red);
			mode.setFont(new Font("MV Boli",Font.PLAIN,30));
			mode.setText("Choose Mode:");
			this.setSize(420,420);
			for(int i1=0; i1<9; i1++) 
				buttons[i1].setText("");
			developer.setBounds(25,140,360,170);
			button.add(developer);
		}
		super.update(getGraphics());
	}
	
	public void begin() {
		info.setText("Tic Tac Toe");
		button.setLayout(null);
		button.add(buttons[9]);
		button.add(buttons[10]);
		button.add(mode);
		buttons[9].setEnabled(true);
		buttons[10].setEnabled(true);
		for(int i=0; i<9; i++) 
			button.remove(buttons[i]);
	}
	
	public void first() {
		button.setLayout(new GridLayout(3,3));
		for(int i=0; i<9; i++) 
			buttons[i].setEnabled(true);
		button.remove(buttons[9]);
		button.remove(buttons[10]);
		button.remove(mode);
		this.remove(developer);
		button.remove(developer);
		info.add(buttons[11]);
		buttons[11].setEnabled(true);
		int z = random.nextInt(2);
		if(z==0) {
			if(comp && bot.equals("X"))
				compTurn = true;
			XorO=true;
			info.setText("X TURN");
		}
		else{
			if(comp && bot.equals("O"))
				compTurn = true;
			XorO=false;
			info.setText("O TURN");
		}
		for(int i=0; i<9; i++) {
			buttons[i].setText("");
			buttons[i].setBackground(new Color(241, 127, 122));
		}
		if(comp)
			compLogic();
	}
	
	public void check() {
		//CHECKING IF "X" WON
		if(buttons[0].getText().equals("X") && buttons[1].getText().equals("X") && buttons[2].getText().equals("X")) {
			xWin(0,1,2);
			win = true;
		}
		else if(buttons[3].getText().equals("X") && buttons[4].getText().equals("X") && buttons[5].getText().equals("X")) {
			xWin(3,4,5);
			win = true;
		}
		else if(buttons[6].getText().equals("X") && buttons[7].getText().equals("X") && buttons[8].getText().equals("X")) {
			xWin(6,7,8);
			win = true;
		}
		else if(buttons[0].getText().equals("X") && buttons[3].getText().equals("X") && buttons[6].getText().equals("X")) {
			xWin(0,3,6);
			win = true;
		}
		else if(buttons[1].getText().equals("X") && buttons[4].getText().equals("X") && buttons[7].getText().equals("X")) {
			xWin(1,4,7);
			win = true;
		}
		else if(buttons[2].getText().equals("X") && buttons[5].getText().equals("X") && buttons[8].getText().equals("X")) {
			xWin(2,5,8);
			win = true;
		}
		else if(buttons[0].getText().equals("X") && buttons[4].getText().equals("X") && buttons[8].getText().equals("X")) {
			xWin(0,4,8);
			win = true;
		}
		else if(buttons[2].getText().equals("X") && buttons[4].getText().equals("X") && buttons[6].getText().equals("X")) {
			xWin(2,4,6);
			win = true;
		}
		
		//CHECKING IF "O" WON
		if(buttons[0].getText().equals("O") && buttons[1].getText().equals("O") && buttons[2].getText().equals("O")) {
			oWin(0,1,2);
			win = true;
		}
		else if(buttons[3].getText().equals("O") && buttons[4].getText().equals("O") && buttons[5].getText().equals("O")) {
			oWin(3,4,5);
			win = true;
		}
		else if(buttons[6].getText().equals("O") && buttons[7].getText().equals("O") && buttons[8].getText().equals("O")) {
			oWin(6,7,8);
			win = true;
		}
		else if(buttons[0].getText().equals("O") && buttons[3].getText().equals("O") && buttons[6].getText().equals("O")) {
			oWin(0,3,6);
			win = true;
		}
		else if(buttons[1].getText().equals("O") && buttons[4].getText().equals("O") && buttons[7].getText().equals("O")) {
			oWin(1,4,7);
			win = true;
		}
		else if(buttons[2].getText().equals("O") && buttons[5].getText().equals("O") && buttons[8].getText().equals("O")) {
			oWin(2,5,8);
			win = true;
		}
		else if(buttons[0].getText().equals("O") && buttons[4].getText().equals("O") && buttons[8].getText().equals("O")) {
			oWin(0,4,8);
			win = true;
		}
		else if(buttons[2].getText().equals("O") && buttons[4].getText().equals("O") && buttons[6].getText().equals("O")) {
			oWin(2,4,6);
			win = true;
		}
		
		//CHECKING FOR DRAW
		if(win==false && (!(buttons[0].getText().equals("")) && !(buttons[1].getText().equals("")) && !(buttons[2].getText().equals("")) && !(buttons[3].getText().equals("")) && !(buttons[4].getText().equals("")) && !(buttons[5].getText().equals("")) && !(buttons[6].getText().equals("")) && !(buttons[7].getText().equals("")) && !(buttons[8].getText().equals("")))) {
			for(int i=0; i<9; i++) 
				buttons[i].setEnabled(false);
			restart("Draw!");
		}
	}
	
	public void xWin(int a, int b, int c){
		for(int i=0; i<9; i++) 
			buttons[i].setEnabled(false);
		buttons[a].setBackground(Color.GREEN);
		buttons[b].setBackground(Color.GREEN);
		buttons[c].setBackground(Color.GREEN);
		restart("X WON");
	}

	public void oWin(int a, int b, int c){
		for(int i=0; i<9; i++) 
			buttons[i].setEnabled(false);
		buttons[a].setBackground(Color.GREEN);
		buttons[b].setBackground(Color.GREEN);
		buttons[c].setBackground(Color.GREEN);
		restart("O WON");
	}
	
	public void restart(String text) {
		sec = 5;
		info.setFont(new Font("MV Boli",Font.PLAIN,18));
		while(sec>=0) {
			try {
				if(!comp)
					info.setText("         "+text + " \n(Restarting game in "+sec+" sec)");
				else {
					if(bot.equals("X") && text.equals("X WON")) 
						info.setText("         Bot won" + " \n(Restarting game in "+sec+" sec)");
					else if(bot.equals("O") && text.equals("O WON")) 
						info.setText("         Bot won" + " \n(Restarting game in "+sec+" sec)");
					else if(text.equals("Draw!"))
						info.setText("         "+text + " \n(Restarting game in "+sec+" sec)");
					else
						info.setText("         User won" + " \n(Restarting game in "+sec+" sec)");
				}
				Thread.sleep(1000);
				sec--;
				super.update(getGraphics());
			} catch (InterruptedException e) {}
		}
		info.setFont(new Font("MV Boli",Font.PLAIN,50));
		first();
	}
	
	public void compLogic() {
		int z = random.nextInt(9);
		if(compTurn) {
			while(!buttons[z].getText().equals(""))
				z = random.nextInt(9);
			buttons[z].setText(bot);
		}
		compTurn = false;
		if(bot.equals("X")) {
			info.setText("O TURN");
			XorO = false;
		}
		if(bot.equals("O")) {
			info.setText("X TURN");
			XorO = true;
		}
	}
}