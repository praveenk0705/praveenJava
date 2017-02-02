package apriori.sources;

import java.util.ArrayList;
import java.util.List;

import apriori.core.Itemset;
import apriori.core.LabelsMap;

/**
 * Class for transforming CSV transactions into itemsets. A Csv transaction is a
 * coma separated list of strings (for instance: A,B,C,D,E)
 * 
 * Encodes the names into integers thanks to the LabelsMap.
 * 
 * @author Benjamin Bouvier
 * 
 */
public abstract class CsvSource implements TransactionSource {

	protected List<Integer> allProducts = new ArrayList<>();

	protected List<Itemset> fromCsvStrings(List<String> sources) {
		List<Itemset> transactions = new ArrayList<>();
		for (String s : sources) {
			Itemset tset = new Itemset();
			for (String q : s.split(",")) {
				int correspondance = LabelsMap.getInstance()
						.addNameCorrespondance(q);
				tset.add(correspondance);
			}
			transactions.add(tset);
		}
		return transactions;
	}
}
