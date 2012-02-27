package org.goldandcoin.model;

public abstract class InventoryItem {
	
	private Object[][] data;
	private String bidFormula;
	private String name;
	private String sellFormula;
	private String bidValue;
	private String sellValue;
	
	public abstract String getType();

	public Object[][] getData() {
		return data;
	}

	public void setData(Object[][] data) {
		this.data = data;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSellFormula() {
		return sellFormula;
	}

	public void setSellFormula(String sellFormula) {
		this.sellFormula = sellFormula;
	}

	public String getBidValue() {
		return bidValue;
	}

	public void setBidValue(String bidValue) {
		this.bidValue = bidValue;
	}

	public String getSellValue() {
		return sellValue;
	}

	public void setSellValue(String sellValue) {
		this.sellValue = sellValue;
	}
	
	public Object getFieldValue(int index) {
		if(index == 0) {
			return this.getBidValue();
		} else if(index == 1) {
			return this.getName();
		} else {
			return this.getSellValue();
		}
	}
	
	public void setValueAt(String value, int col) {
		if(col == 0) {
			this.setBidValue(value);
		} else if(col == 1) {
			this.setName(value);
		} else {
			this.setSellValue(value);
		}
	}
}
