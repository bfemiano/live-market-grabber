package org.goldandcoin.view;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.goldandcoin.controller.MarketPricesController;

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
//		mainPanel.add(Box.createVerticalStrut(150));
		mainPanel.add(bottomPanel);
		
		this.getContentPane().add(mainPanel);
	}
}
