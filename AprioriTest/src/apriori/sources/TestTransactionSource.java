package apriori.sources;

import java.util.ArrayList;
import java.util.List;

import apriori.core.Itemset;

/**
 * Hard-coded transactions, used for testing purposes. These transactions are
 * encoded as Coma Separated Values and then processed to itemsets by the parent
 * method.
 * 
 * @author Benjamin Bouvier
 */
public class TestTransactionSource extends CsvSource {

	@Override
	public List<Itemset> generate() {

		String[] test_transactions = {
				"Beer,Beans,Strawberries",
				"Beans,Apple",
				"Beer,Strawberries",
				"Tomatos,Apple",
				"Tomatos,Beans,Lemon,Computer",
				"Lemon,Apple",
				"Tomatos,Beans,Strawberries",
				"Tomatos,Beans,Sugar",
				"Tomatos,Sheets,Apple",
				"Tomatos,Beer,Beans,Lemon,Strawberries",
				"Beer,Beans,Lemon",
				"Tomatos,Beer,Beans,Sugar,Lemon",
				"Tomatos,Beer",
				"Beer,Lemon",
				"Tomatos,Beer,Beans,Lemon,Strawberries,Apple",
				"Tomatos,Beans,Apple",
				"Beer,Beans,Lemon,Sheets",
				"Beans,Sugar",
				"Sheets,Apple",
				"Tomatos,Lemon,Sheets,Apple,Caviar"
		};
		
		List<String> sources = new ArrayList<>();
		for (String string : test_transactions) {
			sources.add(string);
		}

		return fromCsvStrings(sources);
	}
}
