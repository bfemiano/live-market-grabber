package org.goldandcoin.view;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PublicTablePanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel publicLabel;
	private JTable table;
	private JButton addBtn;
	private JButton deleteBtn;
	private JButton favoriteBtn;
	private DefaultTableModel goldModel;
	private DefaultTableModel silverModel;
	
	public PublicTablePanel() {
		initUI();
	}
	
	private void initUI() {
		publicLabel = new JLabel("Public");
		addBtn = new JButton("Add");
		deleteBtn = new JButton("Delete");
		favoriteBtn = new JButton("Add to Favorites");
		table = new JTable();
		table.setFillsViewportHeight(true);
		
		JScrollPane tablePane = new JScrollPane(table);
		tablePane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		publicLabel.setFont(new Font("Dialog", 1, 16));
		GridBagLayout layout = new GridBagLayout();
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.add(addBtn);
		buttonsPanel.add(deleteBtn);
		buttonsPanel.add(favoriteBtn);
		layout.rowHeights = new int[] {20, 260, 20};
		this.setLayout(layout);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 3;
		gbc.gridy = 0;
		this.add(publicLabel, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = .1;
		gbc.gridwidth = 4;
		this.add(tablePane, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.weighty = 0;
		gbc.weightx = 0;
		gbc.fill = GridBagConstraints.NONE;
		this.add(buttonsPanel, gbc);
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public JButton getAddBtn() {
		return addBtn;
	}

	public void setAddBtn(JButton addBtn) {
		this.addBtn = addBtn;
	}

	public JButton getDeleteBtn() {
		return deleteBtn;
	}

	public void setDeleteBtn(JButton deleteBtn) {
		this.deleteBtn = deleteBtn;
	}

	public DefaultTableModel getGoldModel() {
		return goldModel;
	}

	public void setGoldModel(DefaultTableModel goldModel) {
		this.goldModel = goldModel;
	}

	public DefaultTableModel getSilverModel() {
		return silverModel;
	}

	public void setSilverModel(DefaultTableModel silverModel) {
		this.silverModel = silverModel;
	}

	public JButton getFavoriteBtn() {
		return favoriteBtn;
	}

	public void setFavoriteBtn(JButton favoriteBtn) {
		this.favoriteBtn = favoriteBtn;
	}
}
