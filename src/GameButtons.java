import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GameButtons implements ActionListener {
	JButton buttons[][] = new JButton[8][8];
	static JPanel p = new JPanel();
//	static JPanel restart=new JPanel();
	ImageIcon white, black, UO, LEL;
	boolean wturn = false;
	// int lc,wc,bc;
	static JPanel black_count = new JPanel();
	// JLabel label=new JLabel();
	static JLabel wh;// = new JLabel(new ImageIcon(this.getClass().getResource("/white3.png")));
	static JLabel bl;// = new JLabel(new ImageIcon(this.getClass().getResource("/black3.png")));
	static JLabel Player_turn = new JLabel("Black's turn");
	int bcount, wcount;
	//JButton res=new JButton("Restart");
	static JLabel side_panel=new JLabel();
	GameButtons() {
		wh = new JLabel(new ImageIcon(this.getClass().getResource("/white3.png")));
		bl = new JLabel(new ImageIcon(this.getClass().getResource("/black3.png")));
		Player_turn.setFont(new Font("Chiller",Font.BOLD,30));
		side_panel.setBounds(700,0,400,700);
		side_panel.setOpaque(true);
		white = new ImageIcon(this.getClass().getResource("/white3.png"));
		black = new ImageIcon(this.getClass().getResource("/black3.png"));
		p.setLayout(new GridLayout(8, 8, 5, 5));
		p.setBounds(0, 0, 700, 700);
	//	restart.add(res);
	//	res.setName("restart");
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				buttons[i][j] = new JButton();
				buttons[i][j].setName("UO");
				buttons[i][j].setBackground(Color.RED);
				p.add(buttons[i][j]);
				buttons[i][j].addActionListener(this);
			}
		}
		// p.setBounds(0, 0, 600, 600);

		// buttons[3][3].setBackground(Color.RED);
		buttons[3][3].setIcon(white);
		try {
			buttons[3][3].setBounds(null);
		} catch (Exception e) {
		}
		// buttons[4][4].setText("white");
		buttons[4][4].setIcon(white);
		buttons[3][4].setIcon(black);
		// buttons[4][3].setText("black");
		buttons[4][3].setIcon(black);
		buttons[3][3].setName("white");
		buttons[4][4].setName("white");
		buttons[3][4].setName("black");
		buttons[4][3].setName("black");
		update_count();
		this.legal();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getSource());
		JButton bt = (JButton) e.getSource();
		int y = bt.getY();
		int x = bt.getX();
		x = x / 88;
		y = y / 85;
		update_count();
		/*
		 * int i=0,j=0; for(i=0;i<8;i++) { for(j=0;j<8;j++) {
		 * if(buttons[i][j]==bt) { break; } } }
		 */
		//System.out.println("" + x + " " + y);
		// p.setText("bye");
		if(bt.getName().compareTo("restart")==0)
		{	
			new Game(); }
		if (bt.getName().compareTo("LEL") == 0) {
			if (wturn == false) {
				bt.setName("black");

				// bt.setText("black");
				bt.setIcon(black);
				

				this.resetLegal();
				this.fliptile(y, x);
				wturn = true;
				p.setOpaque(true);
				p.setBackground(Color.white);
				Player_turn.setText("White's turn");
				
				
			//	side_panel.setBackground(Color.white);
				
				this.legal();
				if(!Count.pass(buttons)&&!(wcount+bcount==64))
				{
					JOptionPane.showMessageDialog(p,"pass");
					Player_turn.setText("black's turn");
					wturn=false;
					p.setBackground(Color.black);
					this.legal();
					
				}
			} else {
				bt.setName("white");
				// bt.setText("white");
				bt.setIcon(white);
				this.resetLegal();
				this.fliptile(y, x);
				wturn = false;
				wcount = Count.count(buttons, "white");
				p.setOpaque(true);
				p.setBackground(Color.black);
			//	side_panel.setBackground(Color.black);
				this.legal();
				Player_turn.setText("Black's turn");
				if(!Count.pass(buttons)&&!(wcount+bcount==64))
				{
					JOptionPane.showMessageDialog(p,"pass");
					Player_turn.setText("White's turn");
					wturn=true;
					this.legal();
					p.setBackground(Color.white);
				}
				
				
			}
		}
		// setText("bye");
		// System.out.println(getName());
		// if(getName()=="hi33")
		// {
		// hi44.setName("lol");
		// }
	}

	void legal() {
		int tx;
		int ty;
		int c;
		int d;
		boolean x = wturn;
		if (x == true) {
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if ((buttons[i][j].getName().compareTo("white")) == 0) {
						// System.out.println(i+" "+j+" "+a[i][j]);

						for (int n = i - 1; n <= i + 1; n++) {

							for (int m = j - 1; m <= j + 1; m++) {
								// System.out.println(n+" "+m+" "+a[n][m]);
								if ((m != j) || (n != i)) {
									// System.out.println(n+" "+m+"
									// "+a[n][m]);

									try {
										if (buttons[n][m].getName().compareTo("black") == 0) {
											// System.out.println(n+" "+m+"
											// "+a[n][m]);
											tx = n - i;
											ty = m - j;
											c = n + tx;
											d = m + ty;
											// System.out.println(c+" "+d+"
											// "+a[c][d]);
											if (buttons[c][d].getName().compareTo("UO") == 0) {
												buttons[c][d].setName("LEL");
												// buttons[c][d].setText("LEL");
												buttons[c][d].setBackground(Color.yellow);
											}
											while ((c < 8) && (c > -1) && (d < 8) && (d > -1)
													&& (buttons[c][d].getName() != "LEL")) {
												if (buttons[c][d].getName().compareTo("white") == 0) {
													break;
												}
												if (buttons[c][d].getName().compareTo("black") == 0) {
													c = c + tx;
													d = d + ty;
												}
												if (buttons[c][d].getName().compareTo("UO") == 0) {
													buttons[c][d].setName("LEL");
													// buttons[c][d].setText("LEL");
													buttons[c][d].setBackground(Color.yellow);
													break;
												}
											}
										}
									} catch (Exception e) {
										System.out.println(e);
									}
								}
							}

						}

					}
				}
			}
		} else {

			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (buttons[i][j].getName().compareTo("black") == 0) {
						// System.out.println(i+" Q "+j+" W "+a[i][j]);

						for (int n = i - 1; n <= i + 1; n++) {

							for (int m = j - 1; m <= j + 1; m++) {
								// System.out.println(n+" E "+m+" R
								// "+a[n][m]);
								if ((m != j) || (n != i)) {
									// System.out.println(n+" T "+m+" Y
									// "+a[n][m]);

									try {
										if (buttons[n][m].getName().compareTo("white") == 0) {
											// System.out.println(n+" U "+m+" I
											// "+a[n][m]);
											tx = n - i;
											ty = m - j;
											c = n + tx;
											d = m + ty;
											// System.out.println(c+" O "+d+" P
											// "+a[c][d]);
											if (buttons[c][d].getName().compareTo("UO") == 0) {
												buttons[c][d].setName("LEL");// ="LEL";
												// buttons[c][d].setText("LEL");
												buttons[c][d].setBackground(Color.yellow);
												//System.out.println("working");
											}
											while ((c < 8) && (c > -1) && (d < 8) && (d > -1)
													&& (buttons[c][d].getName().compareTo("LEL") != 0)) {
												if (buttons[c][d].getName().compareTo("black") == 0) {
													break;
												}

												if (buttons[c][d].getName().compareTo("white") == 0) {
													c = c + tx;
													d = d + ty;
													// System.out.println(c+" A
													// "+d+" S "+a[c][d]);
												}
												if (buttons[c][d].getName().compareTo("UO") == 0) {
													buttons[c][d].setName("LEL");// ="LEL";
													// buttons[c][d].setText("LEL");
													buttons[c][d].setBackground(Color.yellow);
													// System.out.println(c+" D
													// "+d+" F "+a[c][d]);
													break;
												}
											}
										}
									} catch (Exception e) {
										System.out.println(e);
									}

								}
							}

						}

					}
				}
			}
			//System.out.println("xyz");
		}
	}
	void resetLegal() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (buttons[i][j].getName().compareTo("LEL") == 0) {
					buttons[i][j].setName("UO");
					// buttons[i][j].setText("UO");
					buttons[i][j].setBackground(Color.red);
				}
			}
		}

	}

	void fliptile(int x, int y) {
//		System.out.println("hi int");
		int tx, ty, c, d;
		if (wturn == true) {
//			System.out.println("hi int2");
			for (int n = x - 1; n <= x + 1; n++) {
	//			System.out.println("hi int3");
				for (int m = y - 1; m <= y + 1; m++) {
					if ((n != x) || (m != y)) {

						try {
							if (buttons[n][m].getName().compareTo("black") == 0) {
								tx = n - x;
								ty = m - y;
								c = n + tx;
								d = m + ty;
								while (buttons[c][d].getName().compareTo("black") == 0) {
									c = c + tx;
									d = d + ty;
						//			System.out.println("hi1");
								}
								if (buttons[c][d].getName().compareTo("white") == 0) {
									c = c - tx;
									d = d - ty;
									while (buttons[c][d].getName().compareTo("black") == 0) {
										// a[c][d] = 'w';
										buttons[c][d].setName("white");
										// buttons[c][d].setText("white");
										// t=Thread.currentThread();
										/*
										 * try{ Thread.sleep(10000); }
										 * catch(Exception e) {}
										 */

										buttons[c][d].setIcon(white);
										c = c - tx;
										d = d - ty;
									//	System.out.println("hi2");
									}
								}
							}
						} catch (Exception e) {
							System.out.println(e);
						}

					}
				}
			}
		} else {
			for (int n = x - 1; n <= x + 1; n++) {
			//	System.out.println("hi int bl");
				for (int m = y - 1; m <= y + 1; m++) {
				//	System.out.println("hi int bl2");
					if ((n != x) || (m != y)) {
					//	System.out.println("hi int b3");

						try {
							if (buttons[n][m].getName().compareTo("white") == 0) {
							//	System.out.println("hi int bl4");
								tx = n - x;
								ty = m - y;
								c = n + tx;
								d = m + ty;
								while (buttons[c][d].getName().compareTo("white") == 0) {
									c = c + tx;
									d = d + ty;
							//		System.out.println("hi int bl15");
								}
								if (buttons[c][d].getName().compareTo("black") == 0) {
								//	System.out.println("hi int bl16");
									c = c - tx;
									d = d - ty;
									while (buttons[c][d].getName().compareTo("white") == 0) {
										// a[c][d] = 'b';
									//	System.out.println("hi int bl17");
										buttons[c][d].setName("black");
										// buttons[c][d].setText("black");
										buttons[c][d].setIcon(black);
										c = c - tx;
										d = d - ty;
									}
								}
							}
						} catch (Exception e) {
							System.out.println(e);
						}

					}
				}
			}
		}
		update_count();
	}

	static JPanel getPanel() {
		return p;
	}

	void update_count() {

		bcount = Count.count(buttons, "black");
		wcount = Count.count(buttons, "white");
		wh.setText("White : " + wcount);
		wh.setFont(new Font("Impact", Font.BOLD, 25));

		// wh.setBounds(700,0,200,200);
		bl.setText("Black : " + bcount);
		bl.setFont(new Font("Impact", Font.BOLD, 25));
		if (bcount + wcount == 64||wcount==0||bcount==0) {
			if (bcount > wcount) {
				JOptionPane.showMessageDialog(p, "Black wins!!!");
			} else if(wcount>bcount){
				JOptionPane.showMessageDialog(p, "White wins!!!");
			}else
			{JOptionPane.showMessageDialog(p, "Match Tie !!!!");
			
				
			}

			// .setBounds(700,200,200,200);
		}
		
	}

	static JLabel wco() {
		return wh;
	}

	static JLabel bco() {
		return bl;
	}

	static JLabel turn() {
		return Player_turn;
	}
	static JLabel getSidePanel() {
		return side_panel;
	}
	/*static JPanel getRestart()
	{
		return restart;
	}*/
	
}
