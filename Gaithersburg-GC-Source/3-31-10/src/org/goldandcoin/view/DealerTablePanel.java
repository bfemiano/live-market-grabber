package org.goldandcoin.view;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DealerTablePanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private JLabel publicLabel;
	private JTable table;
	private JScrollPane pane;
	private JButton addBtn;
	private JButton deleteBtn;
	private JButton favoriteBtn;
	
	public DealerTablePanel() {
		initUI();
	}
	
	private void initUI() {
		publicLabel = new JLabel("Dealer");
		addBtn = new JButton("Add");
		deleteBtn = new JButton("Delete");
		favoriteBtn = new JButton("Add to Favorites");
		table = new JTable();
		table.setFillsViewportHeight(true);
		table.setAutoCreateColumnsFromModel(false);
		pane = new JScrollPane(table);
		pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
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
		this.add(pane, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.weighty = 0;
		gbc.weightx = 0;
		gbc.fill = GridBagConstraints.NONE;
		this.add(buttonsPanel, gbc);
	}
	
	public void moveViewportBottm() {
		int x = this.table.getSize().width;
		int y = this.table.getSize().height;
		this.pane.getViewport().setViewPosition(new Point(x, y));
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

	public JButton getFavoriteBtn() {
		return favoriteBtn;
	}

	public void setFavoriteBtn(JButton favoriteBtn) {
		this.favoriteBtn = favoriteBtn;
	}
}
