package MazeSolver;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Cursor;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;

/**
 * GUI on the local machine displaying current progress of the robot in exploring the Maze
 * @author jonathancaines
 *
 */

public class Display {
	
	private boolean activeButtons;

	private JFrame frmEvMazeSolver;
	private JLabel label_11_1;
	private JLabel label_16_1;
	private JComponent[][] grid = new JComponent[18][12];
	private JButton[][] cells = new JButton[9][6];

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Display window = new Display();
					window.frmEvMazeSolver.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	
	
	
	public void update(int[][] map) {
		for(int i = 0; i < 12; i++) {
			for(int j = 0; j < 18; i++) {
				grid[i][j].setBackground(getColour(map[i][j]));
			}
			
		}
			
	}
	
	public static Color getColour(int state) {
			if (state == -1) {
				return Color.LIGHT_GRAY;
			}
			if (state == 0) {
				return Color.WHITE;
			}
			if (state == 1) {
				return Color.BLACK;
			}
			else return Color.ORANGE;
			
		}
	/**
	 * Create the application.
	 */
	public Display() {
		activeButtons = false;
		initialize();
	}
	public Display(boolean active) {
		activeButtons = active;
		initialize();
	}
	
	public JComponent[][] getGrid() {
		return grid;
	}
	public JComponent getItem(int x,int y) {
		return grid[x][y];
	}
	public JComponent getItem(int[] coords) {
		return grid[coords[0]][coords[1]];
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmEvMazeSolver = new JFrame();
		frmEvMazeSolver.setTitle("EV3 Maze Solver");
		frmEvMazeSolver.setBounds(100, 100, 848, 604);
		frmEvMazeSolver.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel pnlMiscInfo = new JPanel();
		frmEvMazeSolver.getContentPane().add(pnlMiscInfo, BorderLayout.NORTH);
		
		JPanel pnlGridMap = new JPanel();
		pnlGridMap.setPreferredSize(new Dimension(720, 480));
		pnlGridMap.setSize(new Dimension(720, 480));
		frmEvMazeSolver.getContentPane().add(pnlGridMap, BorderLayout.CENTER);
		GridBagLayout gbl_pnlGridMap = new GridBagLayout();
		gbl_pnlGridMap.rowHeights = new int[] {20, 60, 20, 60, 20, 60, 20, 60, 20, 60, 20, 60};
		gbl_pnlGridMap.columnWidths = new int[] {60, 20, 60, 20, 60, 20, 60, 20, 60, 20, 60, 20, 60, 20, 60, 20, 60, 20};
		//gbl_panel.columnWeights = new double[]{};
		//gbl_panel.rowWeights = new double[]{0.0};
		pnlGridMap.setLayout(gbl_pnlGridMap);
		
		JLabel lblTemp;
		JButton btnTemp;
		GridBagConstraints gbc_btnTemp;
		GridBagConstraints gbc_lblTemp;
		for (int i=0;i<18;i++) {
			for (int j=0;j<12;j++) {
				if(i%2 == 0) {
					if (j%2==1) { //Create a wide label at this location (Grid Space)
						lblTemp = new JLabel("");
						lblTemp.setSize(new Dimension(59, 19));
						lblTemp.setPreferredSize(new Dimension(59, 19));
						lblTemp.setOpaque(true);
						lblTemp.setMinimumSize(new Dimension(59, 19));
						lblTemp.setMaximumSize(new Dimension(59, 19));
						lblTemp.setBackground(getColour(-1));
						gbc_lblTemp = new GridBagConstraints();
						//gbc_lblTemp.insets = new Insets(0, 0, 0, 5);
						gbc_lblTemp.gridx = i;
						gbc_lblTemp.gridy = 11-j;
						pnlGridMap.add(lblTemp, gbc_lblTemp);
						grid[i][j] = lblTemp;
						
					}
					
					else {// Create a button
						btnTemp = new JButton("("+String.valueOf(i/2)+","+String.valueOf(j/2)+")");
						btnTemp.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
							}
						});
						btnTemp.setPreferredSize(new Dimension(60, 60));
						btnTemp.setMinimumSize(new Dimension(59, 59));
						btnTemp.setMaximumSize(new Dimension(59, 59));
						btnTemp.setBackground(getColour(-1));
						gbc_btnTemp = new GridBagConstraints();
						//gbc_btnTemp.insets = new Insets(0, 0, 5, 5);
						gbc_btnTemp.gridx = i;
						gbc_btnTemp.gridy = 11-j;
						pnlGridMap.add(btnTemp, gbc_btnTemp);
						
						cells[i/2][j/2] = new JButton("("+String.valueOf(i/2)+","+String.valueOf(j/2)+")");
						grid[i][j] = cells[i/2][j/2];
						
						
					}
				}
				
				else { // Create a square label
					if (j%2==1) {
						lblTemp = new JLabel("");
						lblTemp.setSize(new Dimension(19, 19));
						lblTemp.setPreferredSize(new Dimension(19, 19));
						lblTemp.setOpaque(true);
						lblTemp.setMinimumSize(new Dimension(19, 19));
						lblTemp.setMaximumSize(new Dimension(19, 19));
						lblTemp.setBackground(getColour(-1));
						gbc_lblTemp = new GridBagConstraints();
						//gbc_lblTemp.insets = new Insets(0, 0, 0, 5);
						gbc_lblTemp.gridx = i;
						gbc_lblTemp.gridy = 11-j;
						pnlGridMap.add(lblTemp, gbc_lblTemp);
						grid[i][j] = lblTemp;
						
						
					}
					
					else {// Create a tall label
						
						lblTemp = new JLabel("");
						lblTemp.setSize(new Dimension(19, 59));
						lblTemp.setPreferredSize(new Dimension(19, 59));
						lblTemp.setOpaque(true);
						lblTemp.setMinimumSize(new Dimension(19, 59));
						lblTemp.setMaximumSize(new Dimension(19, 59));
						lblTemp.setBackground(getColour(-1));
						gbc_lblTemp = new GridBagConstraints();
						//gbc_lblTemp.insets = new Insets(0, 0, 0, 5);
						gbc_lblTemp.gridx = i;
						gbc_lblTemp.gridy = 11-j;
						pnlGridMap.add(lblTemp, gbc_lblTemp);
						grid[i][j] = lblTemp;
					}
				}
			}
			
		}
		
	}
}
