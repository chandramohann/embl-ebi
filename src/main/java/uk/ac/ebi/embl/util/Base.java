package uk.ac.ebi.embl.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

import org.apache.log4j.Logger;

public class Base {

	final static Logger logger = Logger.getLogger(Base.class);
	private static final String ZIP_FILE_NAME = "embl.txt.gz";
	private static final String FILE_NAME = "embl.txt";
	private static final String URL = "http://ftp.ebi.ac.uk/pub/databases/chembl/ChEMBLdb/latest/chembl_21_chemreps.txt.gz";

	private HashMap<String, String> emblBaseMap;

	public HashMap<String, String> getEmblBaseMap() {
		return emblBaseMap;
	}

	public void loadBaseMap() {
		try {
			logger.info("downloading the Base data");
			FileDownloader fDownload = new FileDownloader();
			fDownload.downloadFile(URL, ZIP_FILE_NAME);
			fDownload.gunzipIt(ZIP_FILE_NAME, FILE_NAME);
			BufferedReader buf = new BufferedReader(new FileReader(FILE_NAME));
			emblBaseMap = new HashMap<String, String>();
			String lineJustFetched = null;
			String[] wordsArray;
			while (true) {
				lineJustFetched = buf.readLine();
				if (lineJustFetched == null) {
					break;
				} else {
					wordsArray = lineJustFetched.split("\t");
					for (String each : wordsArray) {
						if (!"".equals(each)) {
							emblBaseMap.put(wordsArray[0], wordsArray[3]);
						}
					}
				}
			}
			buf.close();
			logger.info("loaded the GMBH data successfully");
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	public void printBaseMap() {
		for (String each : emblBaseMap.keySet()) {
			String key = each.toString();
			String value = emblBaseMap.get(each).toString();
			System.out.println(key + " " + value);
		}
	}
}
