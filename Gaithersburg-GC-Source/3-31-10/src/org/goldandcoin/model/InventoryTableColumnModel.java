package org.goldandcoin.model;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;

import org.goldandcoin.view.renderer.FormulaCellRenderer;

public class InventoryTableColumnModel extends DefaultTableColumnModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InventoryTableColumnModel(FormulaCellRenderer renderer) {
		super();
		TableColumn bid = new TableColumn();
		bid.setHeaderValue("Bid");
		bid.setCellRenderer(renderer);
		bid.setModelIndex(0);
		addColumn(bid);
		
		TableColumn name = new TableColumn();
		name.setHeaderValue("Name");
		name.setCellRenderer(new DefaultTableCellRenderer());
		name.setModelIndex(1);
		addColumn(name);
		
		TableColumn sell = new TableColumn();
		sell.setHeaderValue("Sell");
		sell.setCellRenderer(renderer);
		sell.setModelIndex(2);
		addColumn(sell);
	}

}
