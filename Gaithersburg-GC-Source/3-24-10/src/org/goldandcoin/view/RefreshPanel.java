package org.goldandcoin.view;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RefreshPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton refreshPricesBtn;
	private JLabel lastRefreshed;
	private JComboBox categoriesCombo;
	
	public RefreshPanel() {
		initUI();
	}
	
	private void initUI() {
		refreshPricesBtn = new JButton("Refresh");
		categoriesCombo = new JComboBox(new String[]{"Favorites", "Gold", "Silver", "Platinum", "Palladium"});
		lastRefreshed = new JLabel(" ");
		lastRefreshed.setFont(new Font("Dialog", 1, 16));
		GridBagLayout layout = new GridBagLayout();
		layout.columnWidths = new int[] {230, 20, 70, 100, 300};
		this.setLayout(layout);
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = 5;
		gbc.anchor = GridBagConstraints.WEST;
		this.add(lastRefreshed, gbc);
		
		gbc.gridx = 3;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.insets = new Insets(5, 0, 0, 0);
		gbc.anchor = GridBagConstraints.CENTER;
		this.add(refreshPricesBtn, gbc);
		
		gbc.gridx = 3;
		gbc.gridy = 1;
		gbc.insets = new Insets(0, 0, 0, 0);
		this.add(categoriesCombo, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 1;
		JLabel catLabel = new JLabel("Category:");
		catLabel.setFont(new Font("Dialog", 1, 16));
		this.add(catLabel, gbc);
		
	}
	
	public JButton getRefreshPricesBtn() {
		return refreshPricesBtn;
	}
	public void setRefreshPricesBtn(JButton refreshPricesBtn) {
		this.refreshPricesBtn = refreshPricesBtn;
	}

	public JLabel getLastRefreshed() {
		return lastRefreshed;
	}

	public void setLastRefreshed(JLabel lastRefreshed) {
		this.lastRefreshed = lastRefreshed;
	}

	public JComboBox getCategoriesCombo() {
		return categoriesCombo;
	}

	public void setCategoriesCombo(JComboBox categoriesCombo) {
		this.categoriesCombo = categoriesCombo;
	}
}
