package org.goldandcoin.view;


import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

public class PricesPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel goldBidLabel;
	private JLabel goldAskLabel;
	private JLabel silverBidLabel;
	private JLabel silverAskLabel;
	private JLabel platinumBidLabel;
	private JLabel platinumAskLabel;
	private JLabel palladiumBidLabel;
	private JLabel palladiumAskLabel;
	private JLabel goldTimeLabel;
	private JLabel silverTimeLabel;
	private JLabel platTimeLabel;
	private JLabel pallTimeLabel;
	private JLabel goldChangeLabel;
	private JLabel silverChangeLabel;
	private JLabel platChangeLabel;
	private JLabel pallChangeLabel;
	private JLabel goldChangeLabelValue;
	private JLabel silverChangeLabelValue;
	private JLabel platChangeLabelValue;
	private JLabel pallChangeLabelValue;
	
	public PricesPanel() {
		initUI();
	}
	
	private void initUI() {
		this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Prices"));
		GridBagLayout layout = new GridBagLayout();
		layout.rowHeights = new int[] {20, 2};
		layout.columnWidths = new int[] {50, 50, 50, 50};
		this.setLayout(layout);
		
		goldBidLabel = new JLabel("Gold Bid(NY):     ");
		goldBidLabel.setFont(new Font("Dialog", 1, 16));
		goldAskLabel = new JLabel("Gold Ask(NY):     ");
		goldAskLabel.setFont(new Font("Dialog", 1, 16));
		silverBidLabel = new JLabel("Silver Bid(NY):    ");
		silverBidLabel.setFont(new Font("Dialog", 1, 16));
		silverAskLabel = new JLabel("Silver Ask(NY):    ");
		silverAskLabel.setFont(new Font("Dialog", 1, 16));
		platinumBidLabel = new JLabel("Platinum Bid:    ");
		platinumBidLabel.setFont(new Font("Dialog", 1, 16));
		platinumAskLabel = new JLabel("Platinum Ask:    ");
		platinumAskLabel.setFont(new Font("Dialog", 1, 16));
		palladiumBidLabel = new JLabel("Palladium Bid:    ");
		palladiumBidLabel.setFont(new Font("Dialog", 1, 16));
		palladiumAskLabel = new JLabel("Palladium Ask:    ");
		palladiumAskLabel.setFont(new Font("Dialog", 1, 16));
		goldTimeLabel = new JLabel("Time:    ");
		goldTimeLabel.setFont(new Font("Dialog", 1, 16));
		silverTimeLabel = new JLabel("Time:    ");
		silverTimeLabel.setFont(new Font("Dialog", 1, 16));
		platTimeLabel = new JLabel("Time:    ");
		platTimeLabel.setFont(new Font("Dialog", 1, 16));
		pallTimeLabel = new JLabel("Time:    ");
		pallTimeLabel.setFont(new Font("Dialog", 1, 16));
		goldChangeLabel = new JLabel("Change:    ");
		goldChangeLabel.setFont(new Font("Dialog", 1, 16));
		silverChangeLabel = new JLabel("Change:    ");
		silverChangeLabel.setFont(new Font("Dialog", 1, 16));
		platChangeLabel = new JLabel("Change:    ");
		platChangeLabel.setFont(new Font("Dialog", 1, 16));
		pallChangeLabel = new JLabel("Change:    ");
		pallChangeLabel.setFont(new Font("Dialog", 1, 16));
		goldChangeLabelValue = new JLabel("");
		goldChangeLabelValue.setFont(new Font("Dialog", 1, 16));
		silverChangeLabelValue = new JLabel("");
		silverChangeLabelValue.setFont(new Font("Dialog", 1, 16));
		platChangeLabelValue = new JLabel("");
		platChangeLabelValue.setFont(new Font("Dialog", 1, 16));
		pallChangeLabelValue = new JLabel("");
		pallChangeLabelValue.setFont(new Font("Dialog", 1, 16));
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = .1;
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		this.add(goldChangeLabel, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(0, 75, 0, 0);
		this.add(goldChangeLabelValue, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.insets = new Insets(0, 0, 0, 0);
		this.add(goldTimeLabel, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		this.add(goldBidLabel, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		this.add(goldAskLabel, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		this.add(silverChangeLabel, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.insets = new Insets(0, 75, 0, 0);
		this.add(silverChangeLabelValue, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.insets = new Insets(0, 0, 0, 0);
		this.add(silverTimeLabel, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		this.add(silverBidLabel, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 3;
		this.add(silverAskLabel, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 0;
		this.add(platChangeLabel, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.insets = new Insets(0, 75, 0, 0);
		this.add(platChangeLabelValue, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 1;
		gbc.insets = new Insets(0, 0, 0, 0);
		this.add(platTimeLabel, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 2;
		this.add(platinumBidLabel, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 3;
		this.add(platinumAskLabel, gbc);
		
		gbc.gridx = 3;
		gbc.gridy = 0;
		this.add(pallChangeLabel, gbc);
		
		gbc.gridx = 3;
		gbc.gridy = 0;
		gbc.insets = new Insets(0, 75, 0, 0);
		this.add(pallChangeLabelValue, gbc);
		
		gbc.gridx = 3;
		gbc.gridy = 1;
		gbc.insets = new Insets(0, 0, 0, 0);
		this.add(pallTimeLabel, gbc);
		
		gbc.gridx = 3;
		gbc.gridy = 2;
		this.add(palladiumBidLabel, gbc);
		
		gbc.gridx = 3;
		gbc.gridy = 3;
		this.add(palladiumAskLabel, gbc);
		
	}
	
	public JLabel getGoldBidLabel() {
		return goldBidLabel;
	}
	public void setGoldBidLabel(JLabel goldBidLabel) {
		this.goldBidLabel = goldBidLabel;
	}
	public JLabel getGoldAskLabel() {
		return goldAskLabel;
	}
	public void setGoldAskLabel(JLabel goldAskLabel) {
		this.goldAskLabel = goldAskLabel;
	}
	public JLabel getSilverBidLabel() {
		return silverBidLabel;
	}
	public void setSilverBidLabel(JLabel silverBidLabel) {
		this.silverBidLabel = silverBidLabel;
	}
	public JLabel getSilverAskLabel() {
		return silverAskLabel;
	}
	public void setSilverAskLabel(JLabel silverAskLabel) {
		this.silverAskLabel = silverAskLabel;
	}
	public JLabel getPlatinumBidLabel() {
		return platinumBidLabel;
	}
	public void setPlatinumBidLabel(JLabel platinumBidLabel) {
		this.platinumBidLabel = platinumBidLabel;
	}
	public JLabel getPlatinumAskLabel() {
		return platinumAskLabel;
	}
	public void setPlatinumAskLabel(JLabel platinumAskLabel) {
		this.platinumAskLabel = platinumAskLabel;
	}
	public JLabel getPalladiumBidLabel() {
		return palladiumBidLabel;
	}
	public void setPalladiumBidLabel(JLabel palladiumBidLabel) {
		this.palladiumBidLabel = palladiumBidLabel;
	}
	public JLabel getPalladiumAskLabel() {
		return palladiumAskLabel;
	}
	public void setPalladiumAskLabel(JLabel palladiumAskLabel) {
		this.palladiumAskLabel = palladiumAskLabel;
	}

	public JLabel getGoldTimeLabel() {
		return goldTimeLabel;
	}

	public void setGoldTimeLabel(JLabel goldTimeLabel) {
		this.goldTimeLabel = goldTimeLabel;
	}

	public JLabel getSilverTimeLabel() {
		return silverTimeLabel;
	}

	public void setSilverTimeLabel(JLabel silverTimeLabel) {
		this.silverTimeLabel = silverTimeLabel;
	}

	public JLabel getPlatTimeLabel() {
		return platTimeLabel;
	}

	public void setPlatTimeLabel(JLabel platTimeLabel) {
		this.platTimeLabel = platTimeLabel;
	}

	public JLabel getPallTimeLabel() {
		return pallTimeLabel;
	}

	public void setPallTimeLabel(JLabel pallTimeLabel) {
		this.pallTimeLabel = pallTimeLabel;
	}

	public JLabel getGoldChangeLabel() {
		return goldChangeLabel;
	}

	public void setGoldChangeLabel(JLabel goldChangeLabel) {
		this.goldChangeLabel = goldChangeLabel;
	}

	public JLabel getSilverChangeLabel() {
		return silverChangeLabel;
	}

	public void setSilverChangeLabel(JLabel silverChangeLabel) {
		this.silverChangeLabel = silverChangeLabel;
	}

	public JLabel getPlatChangeLabel() {
		return platChangeLabel;
	}

	public void setPlatChangeLabel(JLabel platChangeLabel) {
		this.platChangeLabel = platChangeLabel;
	}

	public JLabel getPallChangeLabel() {
		return pallChangeLabel;
	}

	public void setPallChangeLabel(JLabel pallChangeLabel) {
		this.pallChangeLabel = pallChangeLabel;
	}

	public JLabel getGoldChangeLabelValue() {
		return goldChangeLabelValue;
	}

	public void setGoldChangeLabelValue(JLabel goldChangeLabelValue) {
		this.goldChangeLabelValue = goldChangeLabelValue;
	}

	public JLabel getSilverChangeLabelValue() {
		return silverChangeLabelValue;
	}

	public void setSilverChangeLabelValue(JLabel silverChangeLabelValue) {
		this.silverChangeLabelValue = silverChangeLabelValue;
	}

	public JLabel getPlatChangeLabelValue() {
		return platChangeLabelValue;
	}

	public void setPlatChangeLabelValue(JLabel platChangeLabelValue) {
		this.platChangeLabelValue = platChangeLabelValue;
	}

	public JLabel getPallChangeLabelValue() {
		return pallChangeLabelValue;
	}

	public void setPallChangeLabelValue(JLabel pallChangeLabelValue) {
		this.pallChangeLabelValue = pallChangeLabelValue;
	}
}
