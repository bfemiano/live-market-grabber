package org.me.updater;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class PoiSpreadsheetUpdater {
	
	private HSSFWorkbook workbook = null;
	
	public PoiSpreadsheetUpdater() {
		POIFSFileSystem fileStream = null;
		try {
			fileStream = new POIFSFileSystem(new FileInputStream("test_spreadsheet"));
			workbook = new HSSFWorkbook(fileStream);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
