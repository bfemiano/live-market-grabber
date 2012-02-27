package org.goldandcoin.model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class InventoryTableModel extends AbstractTableModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<InventoryItem> data;
	private String[] columnIdentifiers;
	private String type;
	
	public InventoryTableModel(ArrayList<InventoryItem> data) {
		this.data = data;
		this.columnIdentifiers = new String[] {"Bid", "Name", "Sell"};
		fireTableStructureChanged();
	}
	
	public void updateCells() {
		fireTableRowsUpdated(0, getRowCount());
	}
	
	public int getColumnCount() {
		return columnIdentifiers.length;
	}

	public int getRowCount() {
		return data.size();
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Object obj =  data.get(rowIndex).getFieldValue(columnIndex);
		return obj;
	}
	
	public void removeIfExists(InventoryItem compareItem) {
		if(contains(compareItem)) {
			data.remove(compareItem);
		}
	}
	
	public boolean contains(InventoryItem item) {
		return data.contains(item);
	}
	
	public void removeRow(int row) {
		data.remove(row);
		fireTableRowsDeleted(row, row);
	}
	
	public void addRow(InventoryItem newItem)  {
		data.add(newItem);
		fireTableRowsInserted(0, 0);
	}
	
	public InventoryItem getItemAt(int rowIndex) throws ArrayIndexOutOfBoundsException{
		return data.get(rowIndex);
	}
	
	public void setValueAt(Object value, int row, int col) {
        InventoryItem item = data.get(row);
        item.setValueAt((String)value, col);
        fireTableCellUpdated(row, col);
    }
	
	public ArrayList<InventoryItem> getData() {
		return data;
	}
	
	public void setData(ArrayList<InventoryItem> data) {
		this.data = data;
	}
	
	public boolean isCellEditable(int row, int column) {
        return true;
    }

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
