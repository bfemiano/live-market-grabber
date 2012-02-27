package org.goldandcoin.view.renderer;


import java.awt.Color;
import java.awt.Component;
import java.text.DecimalFormat;
import java.util.HashMap;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import org.goldandcoin.exceptions.PricesUnavailableException;
import org.goldandcoin.model.InventoryItem;
import org.goldandcoin.model.InventoryTableModel;

public class FormulaCellRenderer implements TableCellRenderer{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultTableCellRenderer tableRenderer = new DefaultTableCellRenderer();
	private final ScriptEngineManager manager = new ScriptEngineManager ();
    private final ScriptEngine engine = manager.getEngineByName ("js");
    private HashMap<String, Object> latestValues;
    private DecimalFormat formatter = new DecimalFormat("0.00");
	public FormulaCellRenderer() {
		super();
	}

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		tableRenderer = (DefaultTableCellRenderer) tableRenderer.getTableCellRendererComponent(table,
		        value, isSelected, hasFocus, row, column);
		InventoryTableModel model = (InventoryTableModel)table.getModel();
		InventoryItem editingItem = model.getItemAt(row);
		updateValue(tableRenderer, editingItem, value, column);
		return tableRenderer;
	}
	
	private void updateValue(DefaultTableCellRenderer tableRenderer, InventoryItem item, Object value, int col) {
		try {
			if(value != null && ((String)value).length() > 0) {
				String finalFormula = replaceVariable(value);
				String formulaResult = formatter.format((Double)engine.eval(finalFormula));
				tableRenderer.setText(formulaResult);
				tableRenderer.setForeground(null);
//				item.setFormula((String)value, col);
			} else {
				tableRenderer.setText("");
				tableRenderer.setForeground(null);
//				item.setFormula((String)value, col);
			}
		} catch (ScriptException e) {
			tableRenderer.setText("INVALID FORMULA");
			tableRenderer.setForeground(Color.RED);
//			item.setFormula((String)value, col);
		} catch (PricesUnavailableException e) {
			tableRenderer.setText("Latest Prices Unavailable");
			tableRenderer.setForeground(Color.RED);
//			item.setFormula((String)value, col);
		}
	}
	
	private String replaceVariable(Object value) throws PricesUnavailableException {
		String result = (String)value;
		if(latestValues != null) {
			result = result.replaceAll("ga", (String)latestValues.get("ga"));
			result = result.replaceAll("gb", (String)latestValues.get("gb"));
			result = result.replaceAll("sa", (String)latestValues.get("sa"));
			result = result.replaceAll("sb", (String)latestValues.get("sb"));
			result = result.replaceAll("pla", (String)latestValues.get("pla"));
			result = result.replaceAll("plb", (String)latestValues.get("plb"));
			result = result.replaceAll("paa", (String)latestValues.get("paa"));
			result = result.replaceAll("pab", (String)latestValues.get("pab"));
		} else {
			throw new PricesUnavailableException();
		}
		return result;
	}

	public HashMap<String, Object> getLatestValues() {
		return latestValues;
	}

	public void setLatestValues(HashMap<String, Object> latestValues) {
		this.latestValues = latestValues;
	}
}
