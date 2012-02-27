package org.goldandcoin.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import org.goldandcoin.exceptions.MarketUnparsableException;
import org.goldandcoin.grabber.FakeMarketGrabber;
import org.goldandcoin.model.InventoryItem;
import org.goldandcoin.model.database.FlatFileEngine;
import org.goldandcoin.view.DealerTablePanel;
import org.goldandcoin.model.InventoryTableModel;
import org.goldandcoin.view.PricesPanel;
import org.goldandcoin.view.PublicTablePanel;
import org.goldandcoin.view.RefreshPanel;
import javax.swing.table.DefaultTableModel;

public class MarketPricesController {
	
	private HashMap<String, Double> currentPrices;
	private PricesPanel pricesPanel;
	private RefreshPanel refreshPanel;
	private PublicTablePanel publicPanel;
	private DealerTablePanel dealerPanel;
	private DateFormat dateFormatter;
	private InventoryTableModel currentPublicModel;
	private InventoryTableModel currentDealerModel;
	private Date currentDate;
	private HashMap<String, InventoryTableModel> categoryModels;
	private FlatFileEngine dbEngine;
	
	public MarketPricesController() {
		currentDate = new Date();
		dateFormatter = new SimpleDateFormat("EEE, d MMM yyyy hh:mm:ss a");
		initUI();
	}
	
	private void initUI() {
		dbEngine =  new FlatFileEngine();
		dbEngine.fakeInitialize();
		
		pricesPanel = new PricesPanel();
		refreshPanel = new RefreshPanel();
		publicPanel = new PublicTablePanel();
		dealerPanel = new DealerTablePanel();
		categoryModels = dbEngine.getModelMap();
		currentPublicModel = categoryModels.get("GOLD_PUBLIC");
		currentDealerModel = categoryModels.get("GOLD_DEALER");
		publicPanel.getTable().setModel(currentPublicModel);
		dealerPanel.getTable().setModel(currentDealerModel);
		refreshPanel.getRefreshPricesBtn().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateLabelsWithPrices();
			}
		});
		
		refreshPanel.getCategoriesCombo().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox)e.getSource();
		        String categoryName = (String)cb.getSelectedItem();
		        currentPublicModel = categoryModels.get(categoryName.toUpperCase() + "_PUBLIC");
		        currentDealerModel = categoryModels.get(categoryName.toUpperCase() + "_DEALER");
		        publicPanel.getTable().setModel(currentPublicModel);
		        dealerPanel.getTable().setModel(currentDealerModel);
			}
		});
		
		publicPanel.getDeleteBtn().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int length = publicPanel.getTable().getSelectedRows().length;
				for(int i = 0; i < length; i++) {
					currentPublicModel.removeRow(publicPanel.getTable().getSelectedRow());
				}
			}
		});
		
		dealerPanel.getDeleteBtn().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int length = dealerPanel.getTable().getSelectedRows().length;
				for(int i = 0; i < length; i++) {
					currentDealerModel.removeRow(dealerPanel.getTable().getSelectedRow());
				}
			}
		});
	}
	
	private void updateLabelsWithPrices() { 
		try {
			HashMap<String, Object> latestPrices = FakeMarketGrabber.getLatestPrices();
			currentDate.setTime(System.currentTimeMillis());
			refreshPanel.getLastRefreshed().setText("Last Refreshed: " + dateFormatter.format(currentDate));
			pricesPanel.getGoldBidLabel().setText("Gold Bid(NY):  " + latestPrices.get("gb"));
			pricesPanel.getGoldAskLabel().setText("Gold Ask(NY): " + latestPrices.get("ga"));
			pricesPanel.getSilverBidLabel().setText("Silver Bid(NY): " + latestPrices.get("sb"));
			pricesPanel.getSilverAskLabel().setText("Silver Ask(NY): " + latestPrices.get("sa"));
			pricesPanel.getPlatinumBidLabel().setText("Platinum Bid:  " + latestPrices.get("plb"));
			pricesPanel.getPlatinumAskLabel().setText("Platinum Ask: " + latestPrices.get("pla"));
			pricesPanel.getPalladiumBidLabel().setText("Palladium Bid:  " + latestPrices.get("pab"));
			pricesPanel.getPalladiumAskLabel().setText("Palladium Ask: " + latestPrices.get("paa"));
			pricesPanel.getGoldTimeLabel().setText("Time: " + latestPrices.get("goldTime"));
			pricesPanel.getSilverTimeLabel().setText("Time: " + latestPrices.get("silverTime"));
			pricesPanel.getPlatTimeLabel().setText("Time: " + latestPrices.get("platTime"));
			pricesPanel.getPallTimeLabel().setText("Time: " + latestPrices.get("pallTime"));
			
			updateChangeLabel(pricesPanel.getGoldChangeLabelValue(), (String)latestPrices.get("goldChange"));
			updateChangeLabel(pricesPanel.getSilverChangeLabelValue(), (String)latestPrices.get("silverChange"));
			updateChangeLabel(pricesPanel.getPlatChangeLabelValue(), (String)latestPrices.get("platChange"));
			updateChangeLabel(pricesPanel.getPallChangeLabelValue(), (String)latestPrices.get("pallChange"));
			
		} catch (MarketUnparsableException e) {
			JOptionPane.showMessageDialog(pricesPanel.getRootPane(), "Unable to retrieve prices from remote site.\nMake sure 'www.kitco.com/market' is" +
					" accessible from your network.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void updateChangeLabel(JLabel label, String value) {
		label.setText(value);
		if(value.indexOf("+") != -1) {
			label.setForeground(Color.GREEN);
		} else {
			label.setForeground(Color.RED);
		}
	}
	
	public HashMap<String, Double> getCurrentPrices() {
		return currentPrices;
	}

	public void setCurrentPrices(HashMap<String, Double> currentPrices) {
		this.currentPrices = currentPrices;
	}

	public PricesPanel getPricesPanel() {
		return pricesPanel;
	}

	public void setPricesPanel(PricesPanel pricesPanel) {
		this.pricesPanel = pricesPanel;
	}

	public RefreshPanel getRefreshPanel() {
		return refreshPanel;
	}

	public void setRefreshPanel(RefreshPanel refreshPanel) {
		this.refreshPanel = refreshPanel;
	}

	public PublicTablePanel getPublicPanel() {
		return publicPanel;
	}

	public void setPublicPanel(PublicTablePanel publicPanel) {
		this.publicPanel = publicPanel;
	}

	public DealerTablePanel getDealerPanel() {
		return dealerPanel;
	}

	public void setDealerPanel(DealerTablePanel dealerPanel) {
		this.dealerPanel = dealerPanel;
	}
}
