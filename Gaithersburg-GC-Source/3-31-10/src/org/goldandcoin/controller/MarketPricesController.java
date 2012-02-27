package org.goldandcoin.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.goldandcoin.exceptions.MarketUnparsableException;
import org.goldandcoin.grabber.FakeMarketGrabber;
import org.goldandcoin.grabber.LiveMarketGrabber;
import org.goldandcoin.model.InventoryItem;
import org.goldandcoin.model.InventoryTableColumnModel;
import org.goldandcoin.model.InventoryTableModel;
import org.goldandcoin.model.database.FlatFileEngine;
import org.goldandcoin.view.DealerTablePanel;
import org.goldandcoin.view.DisplayFrame;
import org.goldandcoin.view.PricesPanel;
import org.goldandcoin.view.PublicTablePanel;
import org.goldandcoin.view.RefreshPanel;
import org.goldandcoin.view.editor.FormulaCellEditor;
import org.goldandcoin.view.renderer.FormulaCellRenderer;

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
	private FormulaCellRenderer renderer;
	
	private static final String CONFIRM_ADD_MSG = "Add new item?";
	private static final String CONFIRM_ADD_TITLE = "Add";
	private static final String CONFIRM_DELETE_MSG = "   Delete selected item(s)?";
	private static final String CONFIRM_DELETE_TITLE = "Delete";
	private static final String CONFIRM_ADD_FAVORITE_MSG = "Mark selected item(s) as favorite?";
	private static final String CONFIRM_FAVORITE_TITLE = "Mark Favorite";
	private static final String ERROR_ADD_FAVORITE_MSG = "Item already in favorites: ";
	
	public MarketPricesController() {
		currentDate = new Date();
		dateFormatter = new SimpleDateFormat("EEE, d MMM yyyy hh:mm:ss a");
		initUI();
	}
	
	private void initUI() {
		dbEngine =  new FlatFileEngine();
		dbEngine.initialize();
		
		pricesPanel = new PricesPanel();
		refreshPanel = new RefreshPanel();
		publicPanel = new PublicTablePanel();
		dealerPanel = new DealerTablePanel();
		renderer = new FormulaCellRenderer();
		
		categoryModels = dbEngine.getModelMap();
		currentPublicModel = categoryModels.get("FAVORITES_PUBLIC");
		currentDealerModel = categoryModels.get("FAVORITES_DEALER");
		publicPanel.getTable().setModel(currentPublicModel);
		dealerPanel.getTable().setModel(currentDealerModel);
		publicPanel.getTable().setColumnModel(new InventoryTableColumnModel(renderer));
		dealerPanel.getTable().setColumnModel(new InventoryTableColumnModel(renderer));
		
		toggleBtnsFavoritesOnly(true);
		updatePriceComponents();
		refreshPanel.getRefreshPricesBtn().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updatePriceComponents();
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
		        toggleBtnsFavoritesOnly(categoryName.indexOf("Fav") != -1);
			}
		});
		
		publicPanel.getDeleteBtn().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deletePublic();
			}
		});
		
		publicPanel.getAddBtn().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addPublic();
			}
		});
		
		publicPanel.getFavoriteBtn().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addPublicFavorites();
			}
		});
		
		dealerPanel.getDeleteBtn().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteDealer();
			}
		});
		
		dealerPanel.getAddBtn().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addDealer();
			}
		});
		
		dealerPanel.getFavoriteBtn().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addDealerFavorites();
			}
		});
	}
	
	private void addPublicFavorites() {
		int value = showWarning(MarketPricesController.CONFIRM_ADD_FAVORITE_MSG, 
				MarketPricesController.CONFIRM_FAVORITE_TITLE);
		if(value == JOptionPane.YES_OPTION){
			InventoryTableModel publicFavModel = categoryModels.get("FAVORITES_PUBLIC");
			int[] rows = publicPanel.getTable().getSelectedRows();
			for(int i = 0; i < publicPanel.getTable().getSelectedRows().length; i++) {
				try {
					InventoryItem item = currentPublicModel.getItemAt(rows[i]);
					if(!publicFavModel.contains(item)) {
						item.setFavorite(true);
						publicFavModel.addRow(item);
					} else {
						showError(MarketPricesController.ERROR_ADD_FAVORITE_MSG + item.getName());
					}
				} catch (ArrayIndexOutOfBoundsException ex) {
					System.out.println("unable to retrieve public row from model at index: " + i);
				}
			}
		}
	}
	
	private void addDealerFavorites() {
		int value = showWarning(MarketPricesController.CONFIRM_ADD_FAVORITE_MSG, 
				MarketPricesController.CONFIRM_FAVORITE_TITLE);
		if(value == JOptionPane.YES_OPTION) {
			InventoryTableModel dealerFavModel = categoryModels.get("FAVORITES_DEALER");
			int[] rows = dealerPanel.getTable().getSelectedRows();
			for(int i = 0; i < dealerPanel.getTable().getSelectedRows().length; i++) {
				try {
					InventoryItem item = currentDealerModel.getItemAt(rows[i]);
					if(!dealerFavModel.contains(item)) {
						item.setFavorite(true);
						dealerFavModel.addRow(item);
					} else {
						showError(MarketPricesController.ERROR_ADD_FAVORITE_MSG + item.getName());
					}
				} catch (ArrayIndexOutOfBoundsException ex) {
					System.out.println("unable to retrieve dealer row from model at index: " + i);
				}
			}
		}
	}

	private void addPublic() {
		int value = showWarning(MarketPricesController.CONFIRM_ADD_MSG, 
				MarketPricesController.CONFIRM_ADD_TITLE);
		if(value == JOptionPane.YES_OPTION) {
			InventoryItem newItem = new InventoryItem();
			newItem.setBidFormula("");
			newItem.setName("");
			newItem.setSellFormula("");
			newItem.setFavorite(false);
			currentPublicModel.addRow(newItem);
			publicPanel.moveViewportBottm();
		}
	}
	
	private void addDealer() {
		int value = showWarning(MarketPricesController.CONFIRM_ADD_MSG, 
				MarketPricesController.CONFIRM_ADD_TITLE);
		if(value == JOptionPane.YES_OPTION) {
			InventoryItem newItem = new InventoryItem();
			newItem.setBidFormula("");
			newItem.setName("");
			newItem.setSellFormula("");
			newItem.setFavorite(false);
			currentDealerModel.addRow(newItem);
			dealerPanel.moveViewportBottm();
		}
	}
	
	private void deletePublic() {
		int value = showWarning(MarketPricesController.CONFIRM_DELETE_MSG, 
				MarketPricesController.CONFIRM_DELETE_TITLE);
		if(value == JOptionPane.YES_OPTION) {
			int length = publicPanel.getTable().getSelectedRows().length;
			for(int i = 0; i < length; i++) {
				int row = publicPanel.getTable().getSelectedRow();
				InventoryItem item = currentPublicModel.getItemAt(row);
				if(!currentPublicModel.getType().equalsIgnoreCase("FAVORITES_PUBLIC")) {
					categoryModels.get("FAVORITES_PUBLIC").removeIfExists(item);
				} else {
					item.setFavorite(false);
				}
				currentPublicModel.removeRow(row);
				
			}
		}
	}
	
	private void deleteDealer() {
		int value = showWarning(MarketPricesController.CONFIRM_DELETE_MSG, 
				MarketPricesController.CONFIRM_DELETE_TITLE);
		if(value == JOptionPane.YES_OPTION) {
			int length = dealerPanel.getTable().getSelectedRows().length;
			for(int i = 0; i < length; i++) {
				int row = dealerPanel.getTable().getSelectedRow();
				InventoryItem item = currentDealerModel.getItemAt(row);
				if(!currentPublicModel.getType().equalsIgnoreCase("FAVORITES_PUBLIC")) {
					categoryModels.get("FAVORITES_DEALER").removeIfExists(item);
				} else {
					item.setFavorite(false);
				}
				currentDealerModel.removeRow(row);
			}
		}
	}
	
	private void toggleBtnsFavoritesOnly(boolean isFavorites) {
			publicPanel.getAddBtn().setEnabled(!isFavorites);
			publicPanel.getFavoriteBtn().setEnabled(!isFavorites);
			dealerPanel.getAddBtn().setEnabled(!isFavorites);
			dealerPanel.getFavoriteBtn().setEnabled(!isFavorites);
	}
	
	private void updatePriceComponents() { 
		try {
			HashMap<String, Object> latestPrices = LiveMarketGrabber.getLatestPrices();
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
			renderer.setLatestValues(latestPrices);
			currentPublicModel.updateCells();
			currentDealerModel.updateCells();
			updateChangeLabel(pricesPanel.getGoldChangeLabelValue(), (String)latestPrices.get("goldChange"));
			updateChangeLabel(pricesPanel.getSilverChangeLabelValue(), (String)latestPrices.get("silverChange"));
			updateChangeLabel(pricesPanel.getPlatChangeLabelValue(), (String)latestPrices.get("platChange"));
			updateChangeLabel(pricesPanel.getPallChangeLabelValue(), (String)latestPrices.get("pallChange"));
			
		} catch (MarketUnparsableException e) {
			showError("Unable to retrieve prices from remote site.\nMake sure 'www.kitco.com/market' is" +
				" accessible from your network.");
		}
	}
	
	private int showWarning(String message, String title) {
		return JOptionPane.showConfirmDialog(publicPanel.getRootPane(), 
				message,
				title,
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);
	}
	
	private void showError(String message) {
		JOptionPane.showMessageDialog(pricesPanel.getRootPane(), message, "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	public void updateDB() {
		dbEngine.updateDB();
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
