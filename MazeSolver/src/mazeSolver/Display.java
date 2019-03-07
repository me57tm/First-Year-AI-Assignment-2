package mazeSolver;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
import java.awt.Point;

/**
 * GUI on the local machine displaying current progress of the robot in exploring the Maze.
 * @author jonathancaines
 *
 */
public class Display {
	
	
	private final int GRID_WIDTH =  19;//Coordinator.map.getMapWidth();
	private final int GRID_HEIGHT =  13;//Coordinator.map.getMapLength();
	
	private boolean activeButtons;

	private JFrame frmEvMazeSolver;
	private JComponent[][] grid = new JComponent[GRID_HEIGHT][GRID_WIDTH];
	//private JButton[][] cells = new JButton[6][9];

	/**
	 * Default main method
	 * @param args
	 * Default parameter
	 */
	public static void main(String[] args) {
		Display window = new Display(true);
		int[][] map = new int[19][13];
		for (int i = 0; i < 19; i++) {
			for (int j = 0; j < 13; j++) {
				if (i == 0 || j == 0 || i == 18 || j == 12) {
					map[i][j] = -1;
				}
				else {
					map[i][j] = 0;
				}
			}
		}
		PathFinder p = new PathFinder(map);
		window.update(map);
		int[][] path = p.getPathU(new int[] {11,9},new int[] {1,1});
		for (int[] square: path) {
			window.setColour(square,Color.BLUE);
		}
	}
	
	/**
	 * Update the display to have the correct colours.
	 * @param map 
	 * A 2D array representation of the map status (see CustomOccupancyMap).
	 */
	public void update(int[][] map) {
		for(int i = 0; i < GRID_HEIGHT; i++) {
			for(int j = 0; j < GRID_WIDTH; j++) {
				grid[i][j].setBackground(getBGColour(map[j][i]));
				grid[i][j].setForeground(getFGColour(map[j][i]));
			}
		}		
	}
	
	public void setColour(int[] xy,Color c) {
		grid[xy[1]][xy[0]].setBackground(c);
	}
	
	public void update(CustomOccupancyMap data) {
		update(data.getMazeMap());
		int[] position = data.getRobotPosition();
		grid[position[1]][position[0]].setBackground(Color.blue);
	}
	
	
	/**
	 * Get the correct background colour for the grid square.
	 * @param state 
	 * The state of the grid square (-1 occupied, 0 unknown, 1, clear).
	 * @return 
	 * The colour for the GUI to display for this state.
	 */
	public static Color getBGColour(int state) {
			if (state == -1)
				return Color.BLACK;
			if (state == 0)
				return Color.LIGHT_GRAY;
			if (state == 1)
				return Color.WHITE;
			
			else return Color.ORANGE;
		}
	/**
	 * Get the correct foreground colour for the grid square.
	 * @param state 
	 * The state of the grid square (-1 occupied, 0 unknown, 1, clear).
	 * @return 
	 * The colour for the GUI to display for this state.
	 */
	public static Color getFGColour(int state) {
			if (state == -1)
				return Color.WHITE;
			if (state == 0) 
				return Color.BLACK;
			if (state == 1)
				return Color.BLACK;
			
			else return Color.BLACK;
		}
	/**
	 * Create the application with buttons inactive.
	 */
	public Display() {
		this.activeButtons = false;
		initialize();
		frmEvMazeSolver.setVisible(true);
		grid[1][0].setBackground(Color.BLUE);
	}
	
	/**
	 * Create the application.
	 * @param active 
	 * Whether or not the buttons should be active.
	 */
	public Display(boolean active) {
		this.activeButtons = active;
		initialize();
		frmEvMazeSolver.setVisible(true);
	}
	
	/**
	 * Get the grid.
	 * @return 
	 * The 2D array containing all swing components on the grid.
	 */
	public JComponent[][] getGrid() {
		return grid;
	}
	/**
	 * Get a specific swing component from the grid.
	 * @param x 
	 * x coordinate.
	 * @param y 
	 * y coordinate.
	 * @return 
	 * Swing component at this location.
	 */
	public JComponent getItem(int x,int y) {
		return grid[y][x];
	}
	/**
	 * Get a specific swing component from the grid.
	 * @param coords 
	 * Coordinates [x,y].
	 * @return 
	 * Swing component at this location.
	 */
	public JComponent getItem(int[] coords) {
		return grid[coords[1]][coords[0]];
	}

	/**
	 * Initialise the contents of the frame.
	 */
	private void initialize() {
		frmEvMazeSolver = new JFrame();
		frmEvMazeSolver.setResizable(true);
		frmEvMazeSolver.setTitle("EV3 Maze Solver");
		frmEvMazeSolver.setBounds(100, 100, 740, 600);
		frmEvMazeSolver.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel pnlMiscInfo = new JPanel();
		pnlMiscInfo.setPreferredSize(new Dimension(740, 50));
		frmEvMazeSolver.getContentPane().add(pnlMiscInfo, BorderLayout.NORTH);
		pnlMiscInfo.setLayout(null);
		
		JLabel lblButtonsEnabled = new JLabel("Buttons Disabled");
		if (activeButtons)
			lblButtonsEnabled = new JLabel("Buttons Enabled.");
		else
			lblButtonsEnabled = new JLabel("Buttons Disabled.");
		lblButtonsEnabled.setSize(102, 16);
		lblButtonsEnabled.setLocation(new Point(12, 13));
		pnlMiscInfo.add(lblButtonsEnabled);
		
		JPanel pnlGridMap = new JPanel();
		//pnlGridMap.setPreferredSize(new Dimension(720, 480));
		pnlGridMap.setSize(new Dimension(740, 500));
		frmEvMazeSolver.getContentPane().add(pnlGridMap, BorderLayout.CENTER);
		GridBagLayout gbl_pnlGridMap = new GridBagLayout();
		gbl_pnlGridMap.rowHeights = new int[] {20, 60, 20, 60, 20, 60, 20, 60, 20, 60, 20, 60, 20};
		gbl_pnlGridMap.columnWidths = new int[] {20, 60, 20, 60, 20, 60, 20, 60, 20, 60, 20, 60, 20, 60, 20, 60, 20, 60, 20};
		//gbl_panel.columnWeights = new double[]{};
		//gbl_panel.rowWeights = new double[]{0.0};
		pnlGridMap.setLayout(gbl_pnlGridMap);
		
		JLabel lblTemp;
		JButton btnTemp;
		GridBagConstraints gbc_btnTemp;
		GridBagConstraints gbc_lblTemp;
		for (int i=0;i<GRID_HEIGHT;i++) {
			for (int j=0;j<GRID_WIDTH;j++) {
				if(i%2 == 1) {
					//Create a tall label
					if (j%2==0) { 
						lblTemp = createWall(19,59);
						gbc_lblTemp = new GridBagConstraints();
						//gbc_lblTemp.insets = new Insets(0, 0, 0, 5);
						gbc_lblTemp.gridx = j;
						gbc_lblTemp.gridy = 12-i;
						pnlGridMap.add(lblTemp, gbc_lblTemp);
						grid[i][j] = lblTemp;
						
					}
					// Create a button
					else {
						btnTemp = new JButton("("+String.valueOf(j/2)+","+String.valueOf(i/2)+")");
						btnTemp.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) { //The code executed when this button is pressed
								if (activeButtons)
								System.out.println(e.getActionCommand());
							}
						});
						if (activeButtons)
							btnTemp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));;
						btnTemp.setPreferredSize(new Dimension(60, 60));
						btnTemp.setMinimumSize(new Dimension(59, 59));
						btnTemp.setMaximumSize(new Dimension(59, 59));
						btnTemp.setBackground(getBGColour(0));
						btnTemp.setForeground(getFGColour(0));
						btnTemp.setOpaque(true);
						gbc_btnTemp = new GridBagConstraints();
						//gbc_btnTemp.insets = new Insets(0, 0, 5, 5);
						gbc_btnTemp.gridx = j;
						gbc_btnTemp.gridy = 12-i;
						pnlGridMap.add(btnTemp, gbc_btnTemp);
						grid[i][j] = btnTemp;
						
						
					}
				}
				
				else {
					// Create a small square label
					if (j%2==0) {
						lblTemp = createWall(19,19);
						gbc_lblTemp = new GridBagConstraints();
						//gbc_lblTemp.insets = new Insets(0, 0, 0, 5);
						gbc_lblTemp.gridx = j;
						gbc_lblTemp.gridy = 12-i;
						pnlGridMap.add(lblTemp, gbc_lblTemp);
						grid[i][j] = lblTemp;
					}
					// Create a wide label
					else {
						lblTemp = createWall(59,19);
						gbc_lblTemp = new GridBagConstraints();
						//gbc_lblTemp.insets = new Insets(0, 0, 0, 5);
						gbc_lblTemp.gridx = j;
						gbc_lblTemp.gridy = 12-i;
						pnlGridMap.add(lblTemp, gbc_lblTemp);
						grid[i][j] = lblTemp;
					}
				}
			}
		}	
	}
	
	private JLabel createWall(int width, int height) {
		JLabel out = new JLabel("");
		out.setSize(new Dimension(width, height));
		out.setPreferredSize(new Dimension(width, height));
		out.setMinimumSize(new Dimension(width, height));
		out.setMaximumSize(new Dimension(width, height));
		out.setBackground(getBGColour(0));
		out.setForeground(getFGColour(0));
		out.setOpaque(true);
		return out;
	}
}
