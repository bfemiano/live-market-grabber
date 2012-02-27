package org.goldandcoin.grabber;

import java.util.HashMap;

import org.goldandcoin.exceptions.MarketUnparsableException;

public class FakeMarketGrabber {
	
	public static int counterTest = 0;
	
	public static HashMap<String, Object> getLatestPrices() throws MarketUnparsableException{
		HashMap<String, Object> latestPrices = new HashMap<String, Object>();
		try {
			latestPrices.put("ga", new Double(counterTest+1));
			latestPrices.put("gb", new Double(counterTest+2));
			latestPrices.put("sa", new Double(counterTest+3));
			latestPrices.put("sb", new Double(counterTest+4));
			latestPrices.put("gwb", new Double(counterTest+5));
			latestPrices.put("gwa", new Double(counterTest+6));
			latestPrices.put("swb", new Double(counterTest+7));
			latestPrices.put("swa", new Double(counterTest+8));
			latestPrices.put("plb", new Double(counterTest+9));
			latestPrices.put("pla", new Double(counterTest+10));
			latestPrices.put("pab", new Double(counterTest+11));
			latestPrices.put("paa", new Double(counterTest+12));
			latestPrices.put("goldTime", "17:15");
			latestPrices.put("silverTime", "17:15");
			latestPrices.put("platTime", "17:15");
			latestPrices.put("pallTime", "17:15");
			latestPrices.put("goldChange", "+.20");
			latestPrices.put("silverChange", "+.20");
			latestPrices.put("platChange", "+.20");
			latestPrices.put("pallChange", "-.20");
			counterTest++;
		} catch (Exception e) {
			throw new MarketUnparsableException(e.getMessage());
		}
		
		
		return latestPrices;
	}

}
