package game;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;



public class tic_tac_toe extends JFrame implements ActionListener {
	
	public static int BOARD_SIZE  = 3;
	
	public static enum GameStatus{
		Incomplete,Xwins,Zwins, Tie;
	}
	
	private JButton[][] buttons = new JButton[BOARD_SIZE][BOARD_SIZE];
	Boolean crossTurn = true;
	
	public tic_tac_toe() {
		super.setTitle("Tic-Tac-Toe");
		super.setSize(800, 800);
		GridLayout grid = new GridLayout(BOARD_SIZE,BOARD_SIZE);
		super.setLayout(grid);
		Font font = new Font("Comic Sans",1,150);
		
		for(int row = 0 ; row < BOARD_SIZE; row++) {
			for(int col = 0; col < BOARD_SIZE; col++) {
				
				JButton button = new JButton();
				buttons[row][col] = button;
				button.setFont(font);
				button.addActionListener(this);
				super.add(button);
				
				
				
			}
		}
		
		
		super.setResizable(false);
		super.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton clickButton = (JButton)e.getSource();
		makemove(clickButton);
		GameStatus gs = this.getGameStatus();
		if(gs == GameStatus.Incomplete) {
			return;
		}
		
		declareWinner(gs);
		
		int choice = JOptionPane.showConfirmDialog(this, "Do you want to restart the Game ?");
		
		if(choice == JOptionPane.YES_OPTION) {
			
			for(int row = 0 ; row < BOARD_SIZE; row++) {
				for(int col = 0; col < BOARD_SIZE; col++) {
					
					buttons[row][col].setText("");	
				}
			}
			crossTurn = true;
			
		}else {
			super.dispose();
		}
		
	}

	private void makemove(JButton clickButton) {

     String btnext = clickButton.getText();
     if(btnext.length()>0) {
    	 JOptionPane.showMessageDialog(this, "Invalid Move");
     }else {
    	 if(crossTurn) {
    		 clickButton.setText("X");
    	 }else {
    		 clickButton.setText("O");
    	 }
    	 crossTurn =!crossTurn;
     }
		
	}
	
	private GameStatus getGameStatus() {
		String text1 ="",text2 = "";
		 
		int row = 0, col = 0;
		
		//text inside rows
		row = 0;
		while(row<BOARD_SIZE){
			col = 0;
			while(col<BOARD_SIZE-1) {
				text1 = buttons[row][col].getText();
				text2 = buttons[row][col+1].getText();
				
				if(!text1.equals(text2) || text1.length()==0) {
					break;
				}
				col++;
			}
			
			if(col == BOARD_SIZE-1) {
				if(text1.equals("X"))
					return GameStatus.Xwins;
				else
					return GameStatus.Zwins;	
			}
			row++;
		}
		
		//text inside columns
			   col = 0;
				while(col<BOARD_SIZE){
					row = 0;
					while(row<BOARD_SIZE-1) {
						text1 = buttons[row][col].getText();
						text2 = buttons[row+1][col].getText();
						
						if(!text1.equals(text2) || text1.length()==0) {
							break;
						}
						row++;
					}
					
					if(row == BOARD_SIZE-1) {
						if(text1.equals("X"))
							return GameStatus.Xwins;
						else
							return GameStatus.Zwins;	
					}
				   col++;
				}
				
				
				//text inside diagonal1
				       col = 0;
					
						row = 0;
						while(row<BOARD_SIZE-1) {
							text1 = buttons[row][col].getText();
							text2 = buttons[row+1][col+1].getText();
							
							if(!text1.equals(text2) || text1.length()==0) {
								break;
							}
							row++;
							col++;
						}
						
						if(row == BOARD_SIZE-1) {
							if(text1.equals("X"))
								return GameStatus.Xwins;
							else
								return GameStatus.Zwins;	
						}
						
						//text inside diagonal2
					       col = 0;
						
							row = BOARD_SIZE-1;
							while(row<0) {
								text1 = buttons[row][col].getText();
								text2 = buttons[row-1][col+1].getText();
								
								if(!text1.equals(text2) || text1.length()==0) {
									break;
								}
								row--;
								col++;
							}
							
							if(row == 0) {
								if(text1.equals("X"))
									return GameStatus.Xwins;
								else
									return GameStatus.Zwins;	
							}
						  
							String txt = "";
							for( row = 0; row < BOARD_SIZE; row++) {
								for(col = 0; col< BOARD_SIZE; col++) {
									
									txt = buttons[row][col].getText();
									
									if(txt.length()==0) {
										return GameStatus.Incomplete;
									}
								}
							}
							
							return GameStatus.Tie;		
	}
	
	
	private void declareWinner(GameStatus gs) {
		
		if(gs == GameStatus.Xwins) {
			
			JOptionPane.showMessageDialog(this, "X Wins");
			
		}else if(gs == GameStatus.Zwins) {
			
			JOptionPane.showMessageDialog(this, "Z Wins");
			
		}else {
			
			JOptionPane.showMessageDialog(this, "It is Tie");
		}
		
		
	}

}
