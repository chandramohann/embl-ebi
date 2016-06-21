package uk.ac.ebi.embl;

import java.util.HashMap;

import org.apache.log4j.Logger;

import uk.ac.ebi.embl.util.Base;
import uk.ac.ebi.embl.util.Dictionary;

/**
 * Main class
 *
 */
public class InChI {

	final static Logger logger = Logger.getLogger(InChI.class);

	public static void main(String args[]) {
		try {
			int count = 0;
			int defaultSize = 10;
			if (args.length == 1) {
				defaultSize = Integer.parseInt(args[0]);
			}
			Dictionary dictionary = new Dictionary();
			dictionary.loadDictionary();
			Base base = new Base();
			base.loadBaseMap();
			KeySearch ss = new KeySearch();
			HashMap<String, String> emblMap = base.getEmblBaseMap();
			for (String str : dictionary.getDictionaryList()) {
				for (String each : emblMap.keySet()) {
					String key = each.toString();
					String value = emblMap.get(each).toString();
					/* naive algo */
					// if (value.contains(str)) {
					// System.out.println(value + "," + str + "," + key);
					// count++;
					// }
					/* KMP algo */
					if (ss.KMP(value.toCharArray(), str.toCharArray())) {
						System.out.println(value + "," + str + "," + key);
						count++;
					}
				}
				if (count == defaultSize) {
					break;
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
}
