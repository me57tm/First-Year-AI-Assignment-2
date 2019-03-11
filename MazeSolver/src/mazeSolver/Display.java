package mazeSolver;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Dimension;
import java.awt.Cursor;
import javax.swing.JLabel;
import java.awt.Point;
import javax.swing.JProgressBar;

/**
 * GUI on the local machine displaying current progress of the robot in exploring the Maze.
 * @author jonathancaines
 *
 */
public class Display {
	
	
	private final int GRID_WIDTH =  19;//Coordinator.map.getMapWidth()
	private final int GRID_HEIGHT =  13;//Coordinator.map.getMapLength()
	
	private boolean activeButtons;

	private JFrame frmEvMazeSolver;
	private JProgressBar progressBar;
	private JComponent[][] grid = new JComponent[GRID_HEIGHT][GRID_WIDTH];

	/**
	 * Default main method
	 * @param args
	 * Default parameter
	 */
	public static void main(String[] args) {
		Display window = new Display(false);
		int[][] map = new int[19][13];
		map = new int[][] {
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
			{-1, 1, 1, 1,-1, 1, 1, 1, 1, 1, 1, 1,-1},
			{-1, 1,-1, 1,-1,-1,-1,-1,-1,-1,-1, 1,-1},
			{-1, 1,-1, 1,-1, 1, 1, 1, 1, 1, 1, 1,-1},
			{-1, 1,-1, 1,-1, 1,-1,-1,-1,-1,-1,-1,-1},
			{-1, 1,-1, 1,-1, 1, 1, 1, 1, 1, 1, 1,-1},
			{-1, 1,-1, 1,-1, 1,-1,-1,-1,-1,-1, 1,-1},
			{-1, 1,-1, 1, 1, 1,-1, 1, 1, 1,-1, 1,-1},
			{-1, 1,-1, 1, 1, 1,-1, 1, 1, 1,-1, 1,-1},
			{-1, 1, 1, 1, 1, 1,-1, 1, 1, 1,-1, 1,-1},
			{-1, 1, 1, 1, 1, 1,-1, 1, 1, 1,-1, 1,-1},
			{-1, 1, 1, 1, 1, 1,-1, 1, 1, 1,-1, 1,-1},
			{-1,-1, 0,-1,-1,-1,-1, 1, 1, 1,-1, 1,-1},
			{-1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,-1},
			{-1, 1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
			{-1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,-1},
			{-1, 1,-1,-1,-1,-1,-1, 1, 1, 1,-1, 1,-1},
			{-1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,-1},
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		};
		CustomOccupancyMap robotMap = new CustomOccupancyMap(19,13,0);
		robotMap.updateRobotPosition();
		robotMap.updateRobotPosition();
		window.update(robotMap);
	}
	
	/**
	 * Update the display to have the correct colours.
	 * @param map 
	 * A 2D array representation of the map status (see CustomOccupancyMap).
	 */
	public void updateMap(int[][] map) {
		for(int i = 0; i < GRID_HEIGHT; i++) {
			for(int j = 0; j < GRID_WIDTH; j++) {
				grid[i][j].setBackground(getBGColour(map[j][i]));
				grid[i][j].setForeground(getFGColour(map[j][i]));
			}
		}		
	}
	
	/**
	 * Set the Colour of a specific grid square.
	 * @param xy 
	 * Coordinates of the  grid square to be coloured in.
	 * @param c
	 * The new colour of the grid square.
	 */
	public void setColour(int[] xy,Color c) {
		grid[xy[1]][xy[0]].setBackground(c);
	}
	
	/**
	 * Update the GUI to display the correct information about the robot.
	 * @param data
	 * The Robot's internal data to display.
	 */
	public void update(CustomOccupancyMap data) {
		updateMap(data.getMazeMap());
		int[] position = data.getRobotPosition();
		grid[position[1]][position[0]].setBackground(new Color(181, 70, 244));
		grid[position[1]][position[0]].setForeground(Color.BLACK);
		progressBar.setValue(data.getCompletion());
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
		
		progressBar = new JProgressBar();
		progressBar.setValue(1);
		progressBar.setMaximum(247);
		progressBar.setStringPainted(true);
		progressBar.setBounds(564, 23, 146, 14);
		pnlMiscInfo.add(progressBar);
		
		JPanel pnlGridMap = new JPanel();
		pnlGridMap.setSize(new Dimension(740, 500));
		frmEvMazeSolver.getContentPane().add(pnlGridMap, BorderLayout.CENTER);
		GridBagLayout gbl_pnlGridMap = new GridBagLayout();
		gbl_pnlGridMap.rowHeights = new int[] {20, 60, 20, 60, 20, 60, 20, 60, 20, 60, 20, 60, 20};
		gbl_pnlGridMap.columnWidths = new int[] {20, 60, 20, 60, 20, 60, 20, 60, 20, 60, 20, 60, 20, 60, 20, 60, 20, 60, 20};
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
						gbc_lblTemp.gridx = j;
						gbc_lblTemp.gridy = 12-i;
						pnlGridMap.add(lblTemp, gbc_lblTemp);
						grid[i][j] = lblTemp;
						
					}
					// Create a button
					else {
						btnTemp = new JButton("("+String.valueOf(j/2+1)+","+String.valueOf(i/2+1)+")");
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
						gbc_lblTemp.gridx = j;
						gbc_lblTemp.gridy = 12-i;
						pnlGridMap.add(lblTemp, gbc_lblTemp);
						grid[i][j] = lblTemp;
					}
					// Create a wide label
					else {
						lblTemp = createWall(59,19);
						gbc_lblTemp = new GridBagConstraints();
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