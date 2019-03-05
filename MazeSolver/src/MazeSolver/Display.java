package MazeSolver;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Cursor;
/**
 * GUI on the local machine displaying current progress of the robot in exploring the Maze
 * @author jonasschaefer
 *
 */
public class Display {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Display window = new Display();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Display() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 887, 596);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.rowHeights = new int[] {30, 60, 30, 60, 30, 60, 30, 60, 30, 60, 30, 60};
		gbl_panel.columnWidths = new int[] {60, 20, 60, 20, 60, 20, 60, 20, 60, 20, 60, 20, 60, 20, 60, 20, 60, 20};
		gbl_panel.columnWeights = new double[]{};
		gbl_panel.rowWeights = new double[]{};
		panel.setLayout(gbl_panel);
		
		JButton button_25 = new JButton("0,3");
		button_25.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		JButton button_34 = new JButton("0,4");
		button_34.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton button_43 = new JButton("0,5");
		button_43.setPreferredSize(new Dimension(60, 60));
		button_43.setMinimumSize(new Dimension(59, 59));
		button_43.setMaximumSize(new Dimension(59, 59));
		button_43.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_button_43 = new GridBagConstraints();
		gbc_button_43.insets = new Insets(0, 0, 5, 5);
		gbc_button_43.gridx = 0;
		gbc_button_43.gridy = 1;
		panel.add(button_43, gbc_button_43);
		
		JButton button_44 = new JButton("1,5");
		button_44.setPreferredSize(new Dimension(60, 60));
		button_44.setMinimumSize(new Dimension(59, 59));
		button_44.setMaximumSize(new Dimension(59, 59));
		button_44.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_button_44 = new GridBagConstraints();
		gbc_button_44.insets = new Insets(0, 0, 5, 5);
		gbc_button_44.gridx = 2;
		gbc_button_44.gridy = 1;
		panel.add(button_44, gbc_button_44);
		
		JButton button_45 = new JButton("2,5");
		button_45.setPreferredSize(new Dimension(60, 60));
		button_45.setMinimumSize(new Dimension(59, 59));
		button_45.setMaximumSize(new Dimension(59, 59));
		button_45.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_button_45 = new GridBagConstraints();
		gbc_button_45.insets = new Insets(0, 0, 5, 5);
		gbc_button_45.gridx = 4;
		gbc_button_45.gridy = 1;
		panel.add(button_45, gbc_button_45);
		
		JButton button_46 = new JButton("3,5");
		button_46.setPreferredSize(new Dimension(60, 60));
		button_46.setMinimumSize(new Dimension(59, 59));
		button_46.setMaximumSize(new Dimension(59, 59));
		button_46.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_button_46 = new GridBagConstraints();
		gbc_button_46.insets = new Insets(0, 0, 5, 5);
		gbc_button_46.gridx = 6;
		gbc_button_46.gridy = 1;
		panel.add(button_46, gbc_button_46);
		
		JButton button_47 = new JButton("4,5");
		button_47.setPreferredSize(new Dimension(60, 60));
		button_47.setMinimumSize(new Dimension(59, 59));
		button_47.setMaximumSize(new Dimension(59, 59));
		button_47.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_button_47 = new GridBagConstraints();
		gbc_button_47.insets = new Insets(0, 0, 5, 5);
		gbc_button_47.gridx = 8;
		gbc_button_47.gridy = 1;
		panel.add(button_47, gbc_button_47);
		
		JButton button_48 = new JButton("5,5");
		button_48.setPreferredSize(new Dimension(60, 60));
		button_48.setMinimumSize(new Dimension(59, 59));
		button_48.setMaximumSize(new Dimension(59, 59));
		button_48.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_button_48 = new GridBagConstraints();
		gbc_button_48.insets = new Insets(0, 0, 5, 5);
		gbc_button_48.gridx = 10;
		gbc_button_48.gridy = 1;
		panel.add(button_48, gbc_button_48);
		
		JButton button_49 = new JButton("6,5");
		button_49.setPreferredSize(new Dimension(60, 60));
		button_49.setMinimumSize(new Dimension(59, 59));
		button_49.setMaximumSize(new Dimension(59, 59));
		button_49.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_button_49 = new GridBagConstraints();
		gbc_button_49.insets = new Insets(0, 0, 5, 5);
		gbc_button_49.gridx = 12;
		gbc_button_49.gridy = 1;
		panel.add(button_49, gbc_button_49);
		
		JButton button_50 = new JButton("7,5");
		button_50.setPreferredSize(new Dimension(60, 60));
		button_50.setMinimumSize(new Dimension(59, 59));
		button_50.setMaximumSize(new Dimension(59, 59));
		button_50.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_button_50 = new GridBagConstraints();
		gbc_button_50.insets = new Insets(0, 0, 5, 5);
		gbc_button_50.gridx = 14;
		gbc_button_50.gridy = 1;
		panel.add(button_50, gbc_button_50);
		
		JButton button_51 = new JButton("8,5");
		button_51.setPreferredSize(new Dimension(60, 60));
		button_51.setMinimumSize(new Dimension(59, 59));
		button_51.setMaximumSize(new Dimension(59, 59));
		button_51.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_button_51 = new GridBagConstraints();
		gbc_button_51.insets = new Insets(0, 0, 5, 5);
		gbc_button_51.gridx = 16;
		gbc_button_51.gridy = 1;
		panel.add(button_51, gbc_button_51);
		button_34.setPreferredSize(new Dimension(60, 60));
		button_34.setMinimumSize(new Dimension(59, 59));
		button_34.setMaximumSize(new Dimension(59, 59));
		button_34.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_button_34 = new GridBagConstraints();
		gbc_button_34.insets = new Insets(0, 0, 5, 5);
		gbc_button_34.gridx = 0;
		gbc_button_34.gridy = 3;
		panel.add(button_34, gbc_button_34);
		
		JButton button_35 = new JButton("1,4");
		button_35.setPreferredSize(new Dimension(60, 60));
		button_35.setMinimumSize(new Dimension(59, 59));
		button_35.setMaximumSize(new Dimension(59, 59));
		button_35.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_button_35 = new GridBagConstraints();
		gbc_button_35.insets = new Insets(0, 0, 5, 5);
		gbc_button_35.gridx = 2;
		gbc_button_35.gridy = 3;
		panel.add(button_35, gbc_button_35);
		
		JButton button_36 = new JButton("2,4");
		button_36.setPreferredSize(new Dimension(60, 60));
		button_36.setMinimumSize(new Dimension(59, 59));
		button_36.setMaximumSize(new Dimension(59, 59));
		button_36.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_button_36 = new GridBagConstraints();
		gbc_button_36.insets = new Insets(0, 0, 5, 5);
		gbc_button_36.gridx = 4;
		gbc_button_36.gridy = 3;
		panel.add(button_36, gbc_button_36);
		
		JButton button_37 = new JButton("3,4");
		button_37.setPreferredSize(new Dimension(60, 60));
		button_37.setMinimumSize(new Dimension(59, 59));
		button_37.setMaximumSize(new Dimension(59, 59));
		button_37.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_button_37 = new GridBagConstraints();
		gbc_button_37.insets = new Insets(0, 0, 5, 5);
		gbc_button_37.gridx = 6;
		gbc_button_37.gridy = 3;
		panel.add(button_37, gbc_button_37);
		
		JButton button_38 = new JButton("4,4");
		button_38.setPreferredSize(new Dimension(60, 60));
		button_38.setMinimumSize(new Dimension(59, 59));
		button_38.setMaximumSize(new Dimension(59, 59));
		button_38.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_button_38 = new GridBagConstraints();
		gbc_button_38.insets = new Insets(0, 0, 5, 5);
		gbc_button_38.gridx = 8;
		gbc_button_38.gridy = 3;
		panel.add(button_38, gbc_button_38);
		
		JButton button_39 = new JButton("5,4");
		button_39.setPreferredSize(new Dimension(60, 60));
		button_39.setMinimumSize(new Dimension(59, 59));
		button_39.setMaximumSize(new Dimension(59, 59));
		button_39.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_button_39 = new GridBagConstraints();
		gbc_button_39.insets = new Insets(0, 0, 5, 5);
		gbc_button_39.gridx = 10;
		gbc_button_39.gridy = 3;
		panel.add(button_39, gbc_button_39);
		
		JButton button_40 = new JButton("6,4");
		button_40.setPreferredSize(new Dimension(60, 60));
		button_40.setMinimumSize(new Dimension(59, 59));
		button_40.setMaximumSize(new Dimension(59, 59));
		button_40.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_button_40 = new GridBagConstraints();
		gbc_button_40.insets = new Insets(0, 0, 5, 5);
		gbc_button_40.gridx = 12;
		gbc_button_40.gridy = 3;
		panel.add(button_40, gbc_button_40);
		
		JButton button_41 = new JButton("7,4");
		button_41.setPreferredSize(new Dimension(60, 60));
		button_41.setMinimumSize(new Dimension(59, 59));
		button_41.setMaximumSize(new Dimension(59, 59));
		button_41.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_button_41 = new GridBagConstraints();
		gbc_button_41.insets = new Insets(0, 0, 5, 5);
		gbc_button_41.gridx = 14;
		gbc_button_41.gridy = 3;
		panel.add(button_41, gbc_button_41);
		
		JButton button_42 = new JButton("8,4");
		button_42.setPreferredSize(new Dimension(60, 60));
		button_42.setMinimumSize(new Dimension(59, 59));
		button_42.setMaximumSize(new Dimension(59, 59));
		button_42.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_button_42 = new GridBagConstraints();
		gbc_button_42.insets = new Insets(0, 0, 5, 5);
		gbc_button_42.gridx = 16;
		gbc_button_42.gridy = 3;
		panel.add(button_42, gbc_button_42);
		button_25.setPreferredSize(new Dimension(60, 60));
		button_25.setMinimumSize(new Dimension(59, 59));
		button_25.setMaximumSize(new Dimension(59, 59));
		button_25.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_button_25 = new GridBagConstraints();
		gbc_button_25.insets = new Insets(0, 0, 5, 5);
		gbc_button_25.gridx = 0;
		gbc_button_25.gridy = 5;
		panel.add(button_25, gbc_button_25);
		
		JButton button_26 = new JButton("1,3");
		button_26.setPreferredSize(new Dimension(60, 60));
		button_26.setMinimumSize(new Dimension(59, 59));
		button_26.setMaximumSize(new Dimension(59, 59));
		button_26.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_button_26 = new GridBagConstraints();
		gbc_button_26.insets = new Insets(0, 0, 5, 5);
		gbc_button_26.gridx = 2;
		gbc_button_26.gridy = 5;
		panel.add(button_26, gbc_button_26);
		
		JButton button_27 = new JButton("2,3");
		button_27.setPreferredSize(new Dimension(60, 60));
		button_27.setMinimumSize(new Dimension(59, 59));
		button_27.setMaximumSize(new Dimension(59, 59));
		button_27.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_button_27 = new GridBagConstraints();
		gbc_button_27.insets = new Insets(0, 0, 5, 5);
		gbc_button_27.gridx = 4;
		gbc_button_27.gridy = 5;
		panel.add(button_27, gbc_button_27);
		
		JButton button_28 = new JButton("3,3");
		button_28.setPreferredSize(new Dimension(60, 60));
		button_28.setMinimumSize(new Dimension(59, 59));
		button_28.setMaximumSize(new Dimension(59, 59));
		button_28.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_button_28 = new GridBagConstraints();
		gbc_button_28.insets = new Insets(0, 0, 5, 5);
		gbc_button_28.gridx = 6;
		gbc_button_28.gridy = 5;
		panel.add(button_28, gbc_button_28);
		
		JButton button_29 = new JButton("4,3");
		button_29.setPreferredSize(new Dimension(60, 60));
		button_29.setMinimumSize(new Dimension(59, 59));
		button_29.setMaximumSize(new Dimension(59, 59));
		button_29.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_button_29 = new GridBagConstraints();
		gbc_button_29.insets = new Insets(0, 0, 5, 5);
		gbc_button_29.gridx = 8;
		gbc_button_29.gridy = 5;
		panel.add(button_29, gbc_button_29);
		
		JButton button_30 = new JButton("5,3");
		button_30.setPreferredSize(new Dimension(60, 60));
		button_30.setMinimumSize(new Dimension(59, 59));
		button_30.setMaximumSize(new Dimension(59, 59));
		button_30.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_button_30 = new GridBagConstraints();
		gbc_button_30.insets = new Insets(0, 0, 5, 5);
		gbc_button_30.gridx = 10;
		gbc_button_30.gridy = 5;
		panel.add(button_30, gbc_button_30);
		
		JButton button_31 = new JButton("6,3");
		button_31.setPreferredSize(new Dimension(60, 60));
		button_31.setMinimumSize(new Dimension(59, 59));
		button_31.setMaximumSize(new Dimension(59, 59));
		button_31.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_button_31 = new GridBagConstraints();
		gbc_button_31.insets = new Insets(0, 0, 5, 5);
		gbc_button_31.gridx = 12;
		gbc_button_31.gridy = 5;
		panel.add(button_31, gbc_button_31);
		
		JButton button_32 = new JButton("7,3");
		button_32.setPreferredSize(new Dimension(60, 60));
		button_32.setMinimumSize(new Dimension(59, 59));
		button_32.setMaximumSize(new Dimension(59, 59));
		button_32.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_button_32 = new GridBagConstraints();
		gbc_button_32.insets = new Insets(0, 0, 5, 5);
		gbc_button_32.gridx = 14;
		gbc_button_32.gridy = 5;
		panel.add(button_32, gbc_button_32);
		
		JButton button_33 = new JButton("8,3");
		button_33.setPreferredSize(new Dimension(60, 60));
		button_33.setMinimumSize(new Dimension(59, 59));
		button_33.setMaximumSize(new Dimension(59, 59));
		button_33.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_button_33 = new GridBagConstraints();
		gbc_button_33.insets = new Insets(0, 0, 5, 5);
		gbc_button_33.gridx = 16;
		gbc_button_33.gridy = 5;
		panel.add(button_33, gbc_button_33);
		
		JButton button_16 = new JButton("0,2");
		button_16.setPreferredSize(new Dimension(60, 60));
		button_16.setMinimumSize(new Dimension(59, 59));
		button_16.setMaximumSize(new Dimension(59, 59));
		button_16.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_button_16 = new GridBagConstraints();
		gbc_button_16.insets = new Insets(0, 0, 5, 5);
		gbc_button_16.gridx = 0;
		gbc_button_16.gridy = 7;
		panel.add(button_16, gbc_button_16);
		
		JButton button_17 = new JButton("1,2");
		button_17.setPreferredSize(new Dimension(60, 60));
		button_17.setMinimumSize(new Dimension(59, 59));
		button_17.setMaximumSize(new Dimension(59, 59));
		button_17.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_button_17 = new GridBagConstraints();
		gbc_button_17.insets = new Insets(0, 0, 5, 5);
		gbc_button_17.gridx = 2;
		gbc_button_17.gridy = 7;
		panel.add(button_17, gbc_button_17);
		
		JButton button_18 = new JButton("2,2");
		button_18.setPreferredSize(new Dimension(60, 60));
		button_18.setMinimumSize(new Dimension(59, 59));
		button_18.setMaximumSize(new Dimension(59, 59));
		button_18.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_button_18 = new GridBagConstraints();
		gbc_button_18.insets = new Insets(0, 0, 5, 5);
		gbc_button_18.gridx = 4;
		gbc_button_18.gridy = 7;
		panel.add(button_18, gbc_button_18);
		
		JButton button_19 = new JButton("3,2");
		button_19.setPreferredSize(new Dimension(60, 60));
		button_19.setMinimumSize(new Dimension(59, 59));
		button_19.setMaximumSize(new Dimension(59, 59));
		button_19.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_button_19 = new GridBagConstraints();
		gbc_button_19.insets = new Insets(0, 0, 5, 5);
		gbc_button_19.gridx = 6;
		gbc_button_19.gridy = 7;
		panel.add(button_19, gbc_button_19);
		
		JButton button_20 = new JButton("4,2");
		button_20.setPreferredSize(new Dimension(60, 60));
		button_20.setMinimumSize(new Dimension(59, 59));
		button_20.setMaximumSize(new Dimension(59, 59));
		button_20.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_button_20 = new GridBagConstraints();
		gbc_button_20.insets = new Insets(0, 0, 5, 5);
		gbc_button_20.gridx = 8;
		gbc_button_20.gridy = 7;
		panel.add(button_20, gbc_button_20);
		
		JButton button_21 = new JButton("5,2");
		button_21.setPreferredSize(new Dimension(60, 60));
		button_21.setMinimumSize(new Dimension(59, 59));
		button_21.setMaximumSize(new Dimension(59, 59));
		button_21.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_button_21 = new GridBagConstraints();
		gbc_button_21.insets = new Insets(0, 0, 5, 5);
		gbc_button_21.gridx = 10;
		gbc_button_21.gridy = 7;
		panel.add(button_21, gbc_button_21);
		
		JButton button_22 = new JButton("6,2");
		button_22.setPreferredSize(new Dimension(60, 60));
		button_22.setMinimumSize(new Dimension(59, 59));
		button_22.setMaximumSize(new Dimension(59, 59));
		button_22.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_button_22 = new GridBagConstraints();
		gbc_button_22.insets = new Insets(0, 0, 5, 5);
		gbc_button_22.gridx = 12;
		gbc_button_22.gridy = 7;
		panel.add(button_22, gbc_button_22);
		
		JButton button_23 = new JButton("7,2");
		button_23.setPreferredSize(new Dimension(60, 60));
		button_23.setMinimumSize(new Dimension(59, 59));
		button_23.setMaximumSize(new Dimension(59, 59));
		button_23.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_button_23 = new GridBagConstraints();
		gbc_button_23.insets = new Insets(0, 0, 5, 5);
		gbc_button_23.gridx = 14;
		gbc_button_23.gridy = 7;
		panel.add(button_23, gbc_button_23);
		
		JButton button_24 = new JButton("8,2");
		button_24.setPreferredSize(new Dimension(60, 60));
		button_24.setMinimumSize(new Dimension(59, 59));
		button_24.setMaximumSize(new Dimension(59, 59));
		button_24.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_button_24 = new GridBagConstraints();
		gbc_button_24.insets = new Insets(0, 0, 5, 5);
		gbc_button_24.gridx = 16;
		gbc_button_24.gridy = 7;
		panel.add(button_24, gbc_button_24);
		
		JButton button_7 = new JButton("0,1");
		button_7.setPreferredSize(new Dimension(60, 60));
		button_7.setMinimumSize(new Dimension(59, 59));
		button_7.setMaximumSize(new Dimension(59, 59));
		button_7.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_button_7 = new GridBagConstraints();
		gbc_button_7.insets = new Insets(0, 0, 5, 5);
		gbc_button_7.gridx = 0;
		gbc_button_7.gridy = 9;
		panel.add(button_7, gbc_button_7);
		
		JButton button_8 = new JButton("1,1");
		button_8.setPreferredSize(new Dimension(60, 60));
		button_8.setMinimumSize(new Dimension(59, 59));
		button_8.setMaximumSize(new Dimension(59, 59));
		button_8.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_button_8 = new GridBagConstraints();
		gbc_button_8.insets = new Insets(0, 0, 5, 5);
		gbc_button_8.gridx = 2;
		gbc_button_8.gridy = 9;
		panel.add(button_8, gbc_button_8);
		
		JButton button_9 = new JButton("2,1");
		button_9.setPreferredSize(new Dimension(60, 60));
		button_9.setMinimumSize(new Dimension(59, 59));
		button_9.setMaximumSize(new Dimension(59, 59));
		button_9.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_button_9 = new GridBagConstraints();
		gbc_button_9.insets = new Insets(0, 0, 5, 5);
		gbc_button_9.gridx = 4;
		gbc_button_9.gridy = 9;
		panel.add(button_9, gbc_button_9);
		
		JButton button_10 = new JButton("3,1");
		button_10.setPreferredSize(new Dimension(60, 60));
		button_10.setMinimumSize(new Dimension(59, 59));
		button_10.setMaximumSize(new Dimension(59, 59));
		button_10.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_button_10 = new GridBagConstraints();
		gbc_button_10.insets = new Insets(0, 0, 5, 5);
		gbc_button_10.gridx = 6;
		gbc_button_10.gridy = 9;
		panel.add(button_10, gbc_button_10);
		
		JButton button_11 = new JButton("4,1");
		button_11.setPreferredSize(new Dimension(60, 60));
		button_11.setMinimumSize(new Dimension(59, 59));
		button_11.setMaximumSize(new Dimension(59, 59));
		button_11.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_button_11 = new GridBagConstraints();
		gbc_button_11.insets = new Insets(0, 0, 5, 5);
		gbc_button_11.gridx = 8;
		gbc_button_11.gridy = 9;
		panel.add(button_11, gbc_button_11);
		
		JButton button_12 = new JButton("5,1");
		button_12.setPreferredSize(new Dimension(60, 60));
		button_12.setMinimumSize(new Dimension(59, 59));
		button_12.setMaximumSize(new Dimension(59, 59));
		button_12.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_button_12 = new GridBagConstraints();
		gbc_button_12.insets = new Insets(0, 0, 5, 5);
		gbc_button_12.gridx = 10;
		gbc_button_12.gridy = 9;
		panel.add(button_12, gbc_button_12);
		
		JButton button_13 = new JButton("6,1");
		button_13.setPreferredSize(new Dimension(60, 60));
		button_13.setMinimumSize(new Dimension(59, 59));
		button_13.setMaximumSize(new Dimension(59, 59));
		button_13.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_button_13 = new GridBagConstraints();
		gbc_button_13.insets = new Insets(0, 0, 5, 5);
		gbc_button_13.gridx = 12;
		gbc_button_13.gridy = 9;
		panel.add(button_13, gbc_button_13);
		
		JButton button_14 = new JButton("7,1");
		button_14.setPreferredSize(new Dimension(60, 60));
		button_14.setMinimumSize(new Dimension(59, 59));
		button_14.setMaximumSize(new Dimension(59, 59));
		button_14.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_button_14 = new GridBagConstraints();
		gbc_button_14.insets = new Insets(0, 0, 5, 5);
		gbc_button_14.gridx = 14;
		gbc_button_14.gridy = 9;
		panel.add(button_14, gbc_button_14);
		
		JButton button_15 = new JButton("8,1");
		button_15.setPreferredSize(new Dimension(60, 60));
		button_15.setMinimumSize(new Dimension(59, 59));
		button_15.setMaximumSize(new Dimension(59, 59));
		button_15.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_button_15 = new GridBagConstraints();
		gbc_button_15.insets = new Insets(0, 0, 5, 5);
		gbc_button_15.gridx = 16;
		gbc_button_15.gridy = 9;
		panel.add(button_15, gbc_button_15);
		
		JButton button0 = new JButton("0,0");
		button0.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button0.setBackground(Color.LIGHT_GRAY);
		button0.setMinimumSize(new Dimension(59, 59));
		button0.setMaximumSize(new Dimension(59, 59));
		button0.setPreferredSize(new Dimension(60, 60));
		GridBagConstraints gbc_button0 = new GridBagConstraints();
		gbc_button0.insets = new Insets(0, 0, 0, 5);
		gbc_button0.gridx = 0;
		gbc_button0.gridy = 11;
		panel.add(button0, gbc_button0);
		
		JButton button1 = new JButton("1,0");
		button1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button1.setBackground(Color.LIGHT_GRAY);
		button1.setPreferredSize(new Dimension(60, 60));
		button1.setMinimumSize(new Dimension(59, 59));
		button1.setMaximumSize(new Dimension(59, 59));
		GridBagConstraints gbc_button1 = new GridBagConstraints();
		gbc_button1.insets = new Insets(0, 0, 0, 5);
		gbc_button1.gridx = 2;
		gbc_button1.gridy = 11;
		panel.add(button1, gbc_button1);
		
		JButton button = new JButton("2,0");
		button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button.setBackground(Color.LIGHT_GRAY);
		button.setPreferredSize(new Dimension(60, 60));
		button.setMinimumSize(new Dimension(59, 59));
		button.setMaximumSize(new Dimension(59, 59));
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.insets = new Insets(0, 0, 0, 5);
		gbc_button.gridx = 4;
		gbc_button.gridy = 11;
		panel.add(button, gbc_button);
		
		JButton button_1 = new JButton("3,0");
		button_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button_1.setBackground(Color.LIGHT_GRAY);
		button_1.setPreferredSize(new Dimension(60, 60));
		button_1.setMinimumSize(new Dimension(59, 59));
		button_1.setMaximumSize(new Dimension(59, 59));
		GridBagConstraints gbc_button_1 = new GridBagConstraints();
		gbc_button_1.insets = new Insets(0, 0, 0, 5);
		gbc_button_1.gridx = 6;
		gbc_button_1.gridy = 11;
		panel.add(button_1, gbc_button_1);
		
		JButton button_2 = new JButton("4,0");
		button_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button_2.setBackground(Color.LIGHT_GRAY);
		button_2.setPreferredSize(new Dimension(60, 60));
		button_2.setMinimumSize(new Dimension(59, 59));
		button_2.setMaximumSize(new Dimension(59, 59));
		GridBagConstraints gbc_button_2 = new GridBagConstraints();
		gbc_button_2.insets = new Insets(0, 0, 0, 5);
		gbc_button_2.gridx = 8;
		gbc_button_2.gridy = 11;
		panel.add(button_2, gbc_button_2);
		
		JButton button_3 = new JButton("5,0");
		button_3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button_3.setBackground(Color.LIGHT_GRAY);
		button_3.setPreferredSize(new Dimension(60, 60));
		button_3.setMinimumSize(new Dimension(59, 59));
		button_3.setMaximumSize(new Dimension(59, 59));
		GridBagConstraints gbc_button_3 = new GridBagConstraints();
		gbc_button_3.insets = new Insets(0, 0, 0, 5);
		gbc_button_3.gridx = 10;
		gbc_button_3.gridy = 11;
		panel.add(button_3, gbc_button_3);
		
		JButton button_4 = new JButton("6,0");
		button_4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button_4.setBackground(Color.LIGHT_GRAY);
		button_4.setPreferredSize(new Dimension(60, 60));
		button_4.setMinimumSize(new Dimension(59, 59));
		button_4.setMaximumSize(new Dimension(59, 59));
		GridBagConstraints gbc_button_4 = new GridBagConstraints();
		gbc_button_4.insets = new Insets(0, 0, 0, 5);
		gbc_button_4.gridx = 12;
		gbc_button_4.gridy = 11;
		panel.add(button_4, gbc_button_4);
		
		JButton button_5 = new JButton("7,0");
		button_5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button_5.setBackground(Color.LIGHT_GRAY);
		button_5.setPreferredSize(new Dimension(60, 60));
		button_5.setMinimumSize(new Dimension(59, 59));
		button_5.setMaximumSize(new Dimension(59, 59));
		GridBagConstraints gbc_button_5 = new GridBagConstraints();
		gbc_button_5.insets = new Insets(0, 0, 0, 5);
		gbc_button_5.gridx = 14;
		gbc_button_5.gridy = 11;
		panel.add(button_5, gbc_button_5);
		
		JButton button_6 = new JButton("8,0");
		button_6.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button_6.setBackground(Color.LIGHT_GRAY);
		button_6.setPreferredSize(new Dimension(60, 60));
		button_6.setMinimumSize(new Dimension(59, 59));
		button_6.setMaximumSize(new Dimension(59, 59));
		GridBagConstraints gbc_button_6 = new GridBagConstraints();
		gbc_button_6.insets = new Insets(0, 0, 0, 5);
		gbc_button_6.gridx = 16;
		gbc_button_6.gridy = 11;
		panel.add(button_6, gbc_button_6);
		
		JPanel[][] walls = new JPanel[6][9];
		JButton[][] cells = new JButton[6][9];
		GridBagConstraints[][] con = new GridBagConstraints[12][18]; 
	}

}
