package org.goldandcoin.grabber;

import java.util.HashMap;

import org.goldandcoin.exceptions.MarketUnparsableException;

public class FakeMarketGrabber {
	
	public static HashMap<String, Object> getLatestPrices() throws MarketUnparsableException{
		HashMap<String, Object> latestPrices = new HashMap<String, Object>();
		try {
			latestPrices.put("ga", new Double(1102.8));
			latestPrices.put("gb", new Double(1103.8));
			latestPrices.put("sa", new Double(17.02));
			latestPrices.put("sb", new Double(16.99));
			latestPrices.put("gwb", new Double(1102.8));
			latestPrices.put("gwa", new Double(1102.8));
			latestPrices.put("swb", new Double(1102.8));
			latestPrices.put("swa", new Double(1102.8));
			latestPrices.put("plb", new Double(1608.0));
			latestPrices.put("pla", new Double(1613.0));
			latestPrices.put("pab", new Double(465.0));
			latestPrices.put("paa", new Double(471.0));
			latestPrices.put("goldTime", "17:15");
			latestPrices.put("silverTime", "17:15");
			latestPrices.put("platTime", "17:15");
			latestPrices.put("pallTime", "17:15");
			latestPrices.put("goldChange", "+.20");
			latestPrices.put("silverChange", "+.20");
			latestPrices.put("platChange", "+.20");
			latestPrices.put("pallChange", "-.20");
		} catch (Exception e) {
			throw new MarketUnparsableException(e.getMessage());
		}
		
		
		return latestPrices;
	}

}
