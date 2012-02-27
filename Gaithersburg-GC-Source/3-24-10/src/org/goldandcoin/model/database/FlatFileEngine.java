package org.goldandcoin.model.database;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.table.DefaultTableModel;

import org.goldandcoin.model.GoldItem;
import org.goldandcoin.model.InventoryItem;
import org.goldandcoin.model.InventoryTableModel;

public class FlatFileEngine {
	
	private HashMap<String, InventoryTableModel> modelMap;
	
	public void fakeInitialize() {
		modelMap = new HashMap<String, InventoryTableModel>();
		modelMap.put("GOLD_PUBLIC", buildModel("gold-public"));
		modelMap.put("SILVER_PUBLIC", buildModel("silver-public"));
		modelMap.put("PLATINUM_PUBLIC", buildModel("plat-public"));
		modelMap.put("PALLADIUM_PUBLIC", buildModel("pall-public"));
		modelMap.put("FAVORITES_PUBLIC", buildModel("fav-public"));
		modelMap.put("GOLD_DEALER", buildModel("gold-dealer"));
		modelMap.put("SILVER_DEALER", buildModel("silver-dealer"));
		modelMap.put("PLATINUM_DEALER", buildModel("plat-dealer"));
		modelMap.put("PALLADIUM_DEALER", buildModel("pall-dealer"));
		modelMap.put("FAVORITES_DEALER", buildModel("fav-dealer"));
	}
	
	private InventoryTableModel buildModel(String key) {
		ArrayList<InventoryItem> data = new ArrayList<InventoryItem>();
		for(int i = 0; i < 100; i++) {
			GoldItem item = new GoldItem();
			item.setBidValue("1011.3");
			item.setName(key + i);
			item.setSellValue("1022.5");
			data.add(item);
		}
		InventoryTableModel model = new InventoryTableModel(data);
		return model;
	}
	
	public HashMap<String, InventoryTableModel> getModelMap() {
		return modelMap;
	}

}
