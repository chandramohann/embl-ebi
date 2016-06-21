package uk.ac.ebi.embl.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

public class Base {

	private static final String ZIP_FILE_NAME = "embl.txt.gz";
	private static final String FILE_NAME = "embl.txt";
	private static final String URL = "http://ftp.ebi.ac.uk/pub/databases/chembl/ChEMBLdb/latest/chembl_21_chemreps.txt.gz";

	private HashMap<String, String> emblBaseMap;

	public HashMap<String, String> getEmblBaseMap() {
		return emblBaseMap;
	}

	public void loadBaseMap() {
		try {
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
		} catch (Exception e) {
			e.printStackTrace();
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
