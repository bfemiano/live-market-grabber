package org.me.livemarket;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class LiveMarketGrabber {

	
	public static void main(String[] args) {
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
			
			String goldWorldBid = null;
			String goldWorldAsk = null;
			String silverWorldBid = null;
			String silverWorldAsk = null;
			
			while((line = reader.readLine()) != null) {
				if(line.contains("a href=\"/charts/livegoldnewyork.html")) {
					line = reader.readLine();
					line = reader.readLine();
					line = reader.readLine();
					goldNewYorkBid = line = reader.readLine();
					goldNewYorkAsk = line = reader.readLine();
					goldNewYorkBid = goldNewYorkBid.replaceAll("<td><p>", "");
					goldNewYorkBid = goldNewYorkBid.split("<")[0].trim();
					goldNewYorkAsk = goldNewYorkAsk.replaceAll("<td><p>", "");
					goldNewYorkAsk = goldNewYorkAsk.split("<")[0].trim();
				}
				
				if(line.contains("a href=\"/charts/livesilver.html#ny")) {
					line = reader.readLine();
					line = reader.readLine();
					line = reader.readLine();
					silverNewYorkBid = line = reader.readLine();
					silverNewYorkAsk = line = reader.readLine();
					silverNewYorkBid = silverNewYorkBid.replaceAll("<td><p>", "");
					silverNewYorkBid = silverNewYorkBid.split("<")[0].trim();
					silverNewYorkAsk = silverNewYorkAsk.replaceAll("<td><p>", "");
					silverNewYorkAsk = silverNewYorkAsk.split("<")[0].trim();
				}
				
				if(line.contains("a href=\"/charts/liveplatinum.html")) {
					line = reader.readLine();
					line = reader.readLine();
					line = reader.readLine();
					platinumBid = line = reader.readLine();
					platinumAsk = line = reader.readLine();
					platinumBid = platinumBid.replaceAll("<td><p>", "");
					platinumBid = platinumBid.split("<")[0].trim();
					platinumAsk = platinumAsk.replaceAll("<td><p>", "");
					platinumAsk = platinumAsk.split("<")[0].trim();
				}
				
				if(line.contains("a href=\"/charts/livepalladium.html")) {
					line = reader.readLine();
					line = reader.readLine();
					line = reader.readLine();
					palladiumBid = line = reader.readLine();
					palladiumAsk = line = reader.readLine();
					palladiumBid = palladiumBid.replaceAll("<td><p>", "");
					palladiumBid = palladiumBid.split("<")[0].trim();
					palladiumAsk = palladiumAsk.replaceAll("<td><p>", "");
					palladiumAsk = palladiumAsk.split("<")[0].trim();
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
			String output = "GOLD NEW YORK bid: " + goldNewYorkBid + " ask: " + goldNewYorkAsk + "\r\n" +
			"SILVER NEW YORK bid: " + silverNewYorkBid + " ask: " + silverNewYorkAsk + "\r\n" +
			"GOLD WORLD bid: " + goldWorldBid + " ask: " + goldWorldAsk + "\r\n" + 
			"SILVER WORLD bid: " + silverWorldBid + " ask: " + silverWorldAsk + "\r\n" + 
			"PLATINUM bid: " + platinumBid + " ask: " + platinumAsk + "\r\n" + 
			"PALLADIUM bid: " + palladiumBid + " ask: " + palladiumAsk; 
			
			
			File file = new File("grabber_output.txt");
			FileWriter fileWriter = new FileWriter(file);
			fileWriter.write(output);
			fileWriter.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

}
