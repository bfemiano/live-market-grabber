package org.goldandcoin.model.database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.goldandcoin.model.InventoryItem;
import org.goldandcoin.model.InventoryTableModel;

public class FlatFileEngine {
	
	private HashMap<String, InventoryTableModel> modelMap;
	
	public void initialize() {
		initMap();
		loadDBFile();
	}
	
	private void initMap() {
		modelMap = new HashMap<String, InventoryTableModel>();
		modelMap.put("GOLD_PUBLIC", buildModel("GOLD_PUBLIC"));
		modelMap.put("SILVER_PUBLIC", buildModel("SILVER_PUBLIC"));
		modelMap.put("PLATINUM_PUBLIC", buildModel("PLATINUM_PUBLIC"));
		modelMap.put("PALLADIUM_PUBLIC", buildModel("PALLADIUM_PUBLIC"));
		modelMap.put("FAVORITES_PUBLIC", buildModel("FAVORITES_PUBLIC"));
		modelMap.put("GOLD_DEALER", buildModel("GOLD_DEALER"));
		modelMap.put("SILVER_DEALER", buildModel("SILVER_DEALER"));
		modelMap.put("PLATINUM_DEALER", buildModel("PLATINUM_DEALER"));
		modelMap.put("PALLADIUM_DEALER", buildModel("PALLADIUM_DEALER"));
		modelMap.put("FAVORITES_DEALER", buildModel("FAVORITES_DEALER"));
	}
	
	private void loadDBFile() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File("formula_database.dat")));
			String line = reader.readLine();
			while(line != null) {
				String[] itemAttrs = line.split("[|]");
				InventoryItem item = new InventoryItem();
				
				item.setName(itemAttrs.length > 1 ? itemAttrs[1] : "");
				item.setBidFormula(itemAttrs.length > 2 ? itemAttrs[2] : "");
				item.setSellFormula(itemAttrs.length > 3 ? itemAttrs[3] : "");
				if (itemAttrs.length > 4) {
					item.setFavorite(true);
					if(modelMap.containsKey(itemAttrs[4])) {
						modelMap.get(itemAttrs[4]).addRow(item);
					}
				}
				if(modelMap.containsKey(itemAttrs[0])) {
					InventoryTableModel model = modelMap.get(itemAttrs[0]); //Identifier
					model.addRow(item);
				}
				line = reader.readLine();
			}
		} catch (FileNotFoundException e) {
			System.out.println("Warning: No Inventory file found in working directory.");
		} catch (IOException e) {
			System.out.println("Error reading line from Inventory file.");
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Inventory file is malformed");
		}
	}
	
	
	public void updateDB() {
		FileWriter writer = null;
		try {
			writer = new FileWriter(new File("formula_database.dat"));
			StringBuffer buffer = new StringBuffer();
			for(InventoryTableModel model : modelMap.values()) {
				ArrayList<InventoryItem> dataItems = model.getData();
				if(model.getType().indexOf("FAVORITES") == -1) {
					for(InventoryItem item: dataItems) {
						String publicOrDealer = model.getType().substring(model.getType().indexOf("_")+1, model.getType().length());
						buffer.append(model.getType());
						buffer.append("|");
						buffer.append(item.getName() == null ? "" : item.getName());
						buffer.append("|");
						buffer.append(item.getBidFormula() == null ? "" : item.getBidFormula());
						buffer.append("|");
						buffer.append(item.getSellFormula() == null ? "" : item.getSellFormula());
						buffer.append("|");
						if(item.isFavorite()) {
							buffer.append("FAVORITES_" + publicOrDealer);
							buffer.append("|");
						}
						buffer.append("\n");
						writer.write(buffer.toString());
						buffer.setLength(0);
					}
				}
			}
		} catch (IOException ioe) {
			System.out.println("error reading input to file writer");
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				System.out.println("error closing buffered writer");
			}
		}
	}
	
	private InventoryTableModel buildModel(String key) {
		ArrayList<InventoryItem> data = new ArrayList<InventoryItem>();
		InventoryTableModel model = new InventoryTableModel(data);
		model.setType(key);
		return model;
	}
	
	public HashMap<String, InventoryTableModel> getModelMap() {
		return modelMap;
	}

}
