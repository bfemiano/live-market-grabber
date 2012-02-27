package org.goldandcoin.view.editor;

import java.awt.Component;

import javax.swing.AbstractCellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;

public class FormulaCellEditor extends DefaultCellEditor{

	/**
	 * 
	 */
	private JTextField field;
	private static final long serialVersionUID = 1L;
	
	public FormulaCellEditor() {
		super(new JTextField());
	}

	public Object getCellEditorValue() {
		return field.getText();
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		// TODO Auto-generated method stub
		return field;
	}

}
