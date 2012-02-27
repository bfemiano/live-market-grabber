package org.goldandcoin.grabber;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.HashMap;

import org.goldandcoin.exceptions.MarketUnparsableException;

public class LiveMarketGrabber {

	public static HashMap<String, Object> getLatestPrices() throws MarketUnparsableException{
		HashMap<String, Object>latestPrices = new HashMap<String, Object>();
		URL marketURL;
		try {
			marketURL = new URL("http://www.kitco.com/market/");
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					marketURL.openStream()));
			String line = null;
			String goldNewYorkBid = null;
			String goldNewYorkAsk = null;
			String silverNewYorkBid = null;
			String silverNewYorkAsk = null;
			String platinumBid = null;
			String platinumAsk = null;
			String palladiumBid = null;
			String palladiumAsk = null;
			
			String latestGoldTime = null;
			String latestSilverTime = null;
			String latestPlatTime = null;
			String latestPallTime = null;
			
			String goldChange = null;
			String silverChange = null;
			String platChange = null;
			String pallChange = null;
			
			String goldWorldBid = null;
			String goldWorldAsk = null;
			String silverWorldBid = null;
			String silverWorldAsk = null;
			
			while((line = reader.readLine()) != null) {
				if(line.contains("a href=\"/charts/livegoldnewyork.html")) {
					line = reader.readLine();
					line = reader.readLine();
					latestGoldTime = line = reader.readLine();
					goldNewYorkBid = line = reader.readLine();
					goldNewYorkAsk = line = reader.readLine();
					goldNewYorkBid = goldNewYorkBid.replaceAll("<td><p>", "");
					goldNewYorkBid = goldNewYorkBid.split("<")[0].trim();
					goldNewYorkAsk = goldNewYorkAsk.replaceAll("<td><p>", "");
					goldNewYorkAsk = goldNewYorkAsk.split("<")[0].trim();
					latestGoldTime = latestGoldTime.replaceAll("<td><p>", "");
					latestGoldTime = latestGoldTime.split("<")[0].trim();
					goldChange = line = reader.readLine();
					goldChange = goldChange.replaceAll("<td><p[= a-zA-Z1-9]+>", "");
					goldChange = goldChange.split("<")[0].trim();
				}
				
				if(line.contains("a href=\"/charts/livesilver.html#ny")) {
					line = reader.readLine();
					line = reader.readLine();
					latestSilverTime = line = reader.readLine();
					silverNewYorkBid = line = reader.readLine();
					silverNewYorkAsk = line = reader.readLine();
					silverNewYorkBid = silverNewYorkBid.replaceAll("<td><p>", "");
					silverNewYorkBid = silverNewYorkBid.split("<")[0].trim();
					silverNewYorkAsk = silverNewYorkAsk.replaceAll("<td><p>", "");
					silverNewYorkAsk = silverNewYorkAsk.split("<")[0].trim();
					latestSilverTime = latestSilverTime.replaceAll("<td><p>", "");
					latestSilverTime = latestSilverTime.split("<")[0].trim();
					silverChange = line = reader.readLine();
					silverChange = silverChange.replaceAll("<td><p[= a-zA-Z1-9]+>", "");
					silverChange = silverChange.split("<")[0].trim();
				}
				
				if(line.contains("a href=\"/charts/liveplatinum.html")) {
					line = reader.readLine();
					line = reader.readLine();
					latestPlatTime = line = reader.readLine();
					platinumBid = line = reader.readLine();
					platinumAsk = line = reader.readLine();
					platinumBid = platinumBid.replaceAll("<td><p>", "");
					platinumBid = platinumBid.split("<")[0].trim();
					platinumAsk = platinumAsk.replaceAll("<td><p>", "");
					platinumAsk = platinumAsk.split("<")[0].trim();
					latestPlatTime = latestPlatTime.replaceAll("<td><p>", "");
					latestPlatTime = latestPlatTime.split("<")[0].trim();
					platChange = line = reader.readLine();
					platChange = platChange.replaceAll("<td><p[= a-zA-Z1-9]+>", "");
					platChange = platChange.split("<")[0].trim();
				}
				
				if(line.contains("a href=\"/charts/livepalladium.html")) {
					line = reader.readLine();
					line = reader.readLine();
					latestPallTime = line = reader.readLine();
					palladiumBid = line = reader.readLine();
					palladiumAsk = line = reader.readLine();
					palladiumBid = palladiumBid.replaceAll("<td><p>", "");
					palladiumBid = palladiumBid.split("<")[0].trim();
					palladiumAsk = palladiumAsk.replaceAll("<td><p>", "");
					palladiumAsk = palladiumAsk.split("<")[0].trim();
					latestPallTime = latestPallTime.replaceAll("<td><p>", "");
					latestPallTime = latestPallTime.split("<")[0].trim();
					pallChange = line = reader.readLine();
					pallChange = pallChange.replaceAll("<td><p[= a-zA-Z1-9]+>", "");
					pallChange = pallChange.split("<")[0].trim();
				}
				
				if(line.contains("a href=\"/charts/livegold.html")) {
					line = reader.readLine();
					line = reader.readLine();
					line = reader.readLine();
					goldWorldBid = line = reader.readLine();
					goldWorldAsk = line = reader.readLine();
					goldWorldBid = goldWorldBid.replaceAll("<td width=\"68\" align=\"center\">", "");
					goldWorldBid = goldWorldBid.split("<")[0].trim();
					goldWorldAsk = goldWorldAsk.replaceAll("<td width=\"68\" align=\"center\">", "");
					goldWorldAsk = goldWorldAsk.split("<")[0].trim();
				}
				
				if(line.contains("a href=\"/charts/livesilver.html")) {
					line = reader.readLine();
					line = reader.readLine();
					line = reader.readLine();
					silverWorldBid = line = reader.readLine();
					silverWorldAsk = line = reader.readLine();
					silverWorldBid = silverWorldBid.replaceAll("<td width=\"68\" align=\"center\">", "");
					silverWorldBid = silverWorldBid.split("<")[0].trim();
					silverWorldAsk = silverWorldAsk.replaceAll("<td width=\"68\" align=\"center\">", "");
					silverWorldAsk = silverWorldAsk.split("<")[0].trim();
				}
				
				if(goldNewYorkBid != null &&
						goldNewYorkAsk != null &&
						silverNewYorkBid != null &&
						silverNewYorkAsk != null &&
						platinumBid != null &&
						platinumAsk != null &&
						palladiumBid != null &&
						palladiumAsk != null &&
						goldWorldBid != null &&
						goldWorldAsk != null &&
						silverWorldBid != null &&
						silverWorldAsk != null) {
						break;
				}
			}
			latestPrices.put("ga", goldNewYorkAsk);
			latestPrices.put("gb", goldNewYorkBid);
			latestPrices.put("sa", silverNewYorkAsk);
			latestPrices.put("sb", silverNewYorkBid);
			latestPrices.put("gwb", goldWorldBid);
			latestPrices.put("gwa", goldWorldAsk);
			latestPrices.put("swb", silverWorldBid);
			latestPrices.put("swa", silverWorldAsk);
			latestPrices.put("plb", platinumBid);
			latestPrices.put("pla", platinumAsk);
			latestPrices.put("pab", palladiumBid);
			latestPrices.put("paa", palladiumAsk);
			latestPrices.put("goldTime", latestGoldTime);
			latestPrices.put("silverTime", latestSilverTime);
			latestPrices.put("platTime", latestPlatTime);
			latestPrices.put("pallTime", latestPallTime);
			latestPrices.put("goldChange", goldChange);
			latestPrices.put("silverChange", silverChange);
			latestPrices.put("platChange", platChange);
			latestPrices.put("pallChange", pallChange);
		} catch (MalformedURLException e) {
			throw new MarketUnparsableException(e.getMessage());
		} catch (IOException e) {
			throw new MarketUnparsableException(e.getMessage());
		} catch (NumberFormatException e) {
			throw new MarketUnparsableException(e.getMessage());
		}
		return latestPrices;
	}
}
