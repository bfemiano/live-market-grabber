package org.goldandcoin.model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class InventoryTableModel extends AbstractTableModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<InventoryItem> data;
	private String[] columns;
	
	public InventoryTableModel(ArrayList<InventoryItem> data) {
		this.data = data;
		this.columns = new String[] {"Bid", "Name", "Sell"};
	}

	public int getColumnCount() {
		return columns.length;
	}

	public int getRowCount() {
		return data.size();
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Object obj =  data.get(rowIndex).getFieldValue(columnIndex);
		return obj;
	}
	
	public void removeRow(int row) {
		data.remove(row);
		fireTableRowsDeleted(row, row);
	}
	
	public void setValueAt(Object value, int row, int col) {
        InventoryItem item = data.get(row);
        item.setValueAt((String)value, col);
        fireTableCellUpdated(row, col);
    }
	
	public boolean isCellEditable(int row, int column) {
        return true;
    }
}
