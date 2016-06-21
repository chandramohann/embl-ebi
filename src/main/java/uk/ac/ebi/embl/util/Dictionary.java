package uk.ac.ebi.embl.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Dictionary {

	private List<String> dictionaryList;
	private List<String> orderedList;
	private List<String> unorderedList;
	private static final String FILE_NAME = "Dictionary.txt";
	private static final String URL = "https://raw.githubusercontent.com/jonbcard/scrabble-bot/master/src/dictionary.txt";

	public void loadDictionary() {
		dictionaryList = new ArrayList<String>();
		try {
			FileDownloader fDownload = new FileDownloader();
			fDownload.downloadFile(URL, FILE_NAME);
			BufferedReader buf = new BufferedReader(new FileReader(FILE_NAME));
			String lineJustFetched = null;
			String[] wordsArray;
			while (true) {
				lineJustFetched = buf.readLine();
				if (lineJustFetched == null) {
					break;
				} else {
					wordsArray = lineJustFetched.split("\n");
					wordsArray = lineJustFetched.split(",");
					for (String each : wordsArray) {
						if (!"".equals(each)) {
							dictionaryList.add(each);
						}
					}
				}
			}
			buf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void orderDictionary() {
		unorderedList = new ArrayList<String>();
		orderedList = new ArrayList<String>();
		Collections.sort(dictionaryList);
		int size = dictionaryList.size();
		for (int i = 0; i < size; i++) {
			System.out.println(dictionaryList.get(i));
			for (int j = i + 1; j < size;) {
				System.out.println(dictionaryList.get(j));
				if (dictionaryList.get(j).length() > dictionaryList.get(i).length()
						&& dictionaryList.get(j).contains(dictionaryList.get(i))) {
					orderedList.add(dictionaryList.get(j));
					break;
				} else {
					unorderedList.add(dictionaryList.get(i));
					break;
				}
			}
		}

	}

	public List<String> getDictionaryList() {
		dictionaryList.sort(Comparator.comparing(String::length).reversed());
		System.out.println(dictionaryList);
		return dictionaryList;
	}
}
