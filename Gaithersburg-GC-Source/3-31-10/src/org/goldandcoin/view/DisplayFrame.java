package org.goldandcoin.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.goldandcoin.controller.MarketPricesController;

import com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel;
import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;

public class DisplayFrame extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MarketPricesController controller;
	
	public DisplayFrame(String title) {
		this.setTitle(title);
		initUI();
	}
	
	private void initUI() {
		controller = new MarketPricesController();
		JPanel mainPanel = new JPanel();
		JPanel topPanel = new JPanel();
		JPanel bottomPanel = new JPanel();
		
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
		topPanel.add(controller.getPricesPanel());
		
		bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
		bottomPanel.add(controller.getPublicPanel());
		bottomPanel.add(Box.createHorizontalStrut(10));
		bottomPanel.add(controller.getDealerPanel());
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.add(topPanel);
		mainPanel.add(controller.getRefreshPanel());
		mainPanel.add(bottomPanel);
		
		this.getContentPane().add(mainPanel);
		
		ImageIcon imageIcon = new ImageIcon("coins.GIF");
		
		JMenuBar menuBar = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenuItem save = new JMenuItem("Save");
		
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.updateDB();
			}
		});
		
		JMenuItem exit = new JMenuItem("Exit");
		
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeWarning();
			}
		});
		
		file.add(save);
		file.addSeparator();
		file.add(exit);
		
		JMenu help = new JMenu("Help");
		JMenuItem about = new JMenuItem("About");
		JMenuItem legend = new JMenuItem("Legend");
		about.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(DisplayFrame.this.getContentPane().getParent(), 
						"GCE Price Updater Version:1.0\n\nAuthor:Brian Femiano (bfemiano@gmail.com)\n\nAll price updates are taken live from http://www.kitco.com. © 2010 Kitco Metals Inc.\n\nGCE is not responsible for any inaccuracies or discrepancies in prices.", "About", JOptionPane.PLAIN_MESSAGE);
			}
		});
		help.add(about);
		help.add(legend);
		menuBar.add(file);
		menuBar.add(help);
		
		this.setJMenuBar(menuBar);
		this.setIconImage(imageIcon.getImage());
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		this.setSize(new Dimension(900, 570));
		this.addWindowListener(new WindowAdapter() {
			public  void windowClosing(WindowEvent e) {
				closeWarning();
			}
		});
	}
	
	private void closeWarning() {
		int value = JOptionPane.showConfirmDialog(DisplayFrame.this, 
				"Are you sure you want to exit?",
				"Warning",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);
		if(value == JOptionPane.YES_OPTION) {
			controller.updateDB();
			DisplayFrame.this.setVisible(false);
			DisplayFrame.this.dispose();
			System.exit(0);
		}
	}
	
}
