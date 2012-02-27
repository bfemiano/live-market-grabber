package org.goldandcoin.model;

public  class InventoryItem {
	
	private Object[][] data;
	private String bidFormula;
	private String name;
	private String sellFormula;
	private String bidValue;
	private String sellValue;
	private String type;
	private boolean favorite;

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
	
	public String getBidFormula() {
		return bidFormula;
	}

	public void setBidFormula(String bidFormula) {
		this.bidFormula = bidFormula;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public boolean isFavorite() {
		return favorite;
	}

	public void setFavorite(boolean favorite) {
		this.favorite = favorite;
	}

	public Object getFieldValue(int index) {
		if(index == 0) {
			return this.getBidFormula();
		} else if(index == 1) {
			return this.getName();
		} else {
			return this.getSellFormula();
		}
	}
	
	public void setValueAt(String value, int col) {
		if(col == 0) {
			this.setBidFormula(value);
		} else if(col == 1) {
			this.setName(value);
		} else {
			this.setSellFormula(value);
		}
	}
}
