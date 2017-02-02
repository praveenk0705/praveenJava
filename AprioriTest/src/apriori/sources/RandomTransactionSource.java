package apriori.sources;

import java.util.ArrayList;
import java.util.List;

import apriori.core.Itemset;
import apriori.core.LabelsMap;

/**
 * Randomly generates transaction from a set of given objects associated to
 * probabilities of appearance.
 * 
 * Generates only transactions of size 2 minimum, so as to find a minimum of
 * rules.
 * 
 * @author Benjamin Bouvier
 * 
 */
public class RandomTransactionSource implements TransactionSource {

	protected int numberOfGenerations = 0;

	protected List<Integer> allProducts = new ArrayList<>();

	protected String[] products = { "Coke", "Beer", "Beans", "Toilet paper",
			"Pen", "Sheets", "Book", "Banana", "Watch", "Computer" };
	protected Double[] probabilities = { 0.6, 0.4, 0.3, 0.3, 0.2, 0.2, 0.2,
			0.4, 0.05, 0.05 };

	/**
	 * Will generates n transactions
	 * 
	 * @param n
	 *            Number of transactions to be generated
	 */
	public RandomTransactionSource(int n) {
		this.numberOfGenerations = n;
	}

	@Override
	public List<Itemset> generate() {

		List<Itemset> transactions = new ArrayList<>();
		for (String s : products) {
			int correspondance = LabelsMap.getInstance().addNameCorrespondance(
					s);
			allProducts.add(correspondance);
		}

		for (int i = 0; i < numberOfGenerations; ++i) {
			Itemset transaction = new Itemset();
			while (transaction.size() < 2) { // we only want transaction with a
												// minimum size 2
				for (int j = 0, s = allProducts.size(); j < s; ++j) {
					if (Math.random() > (1 - probabilities[j])) {
						transaction.add(j);
					}
				}
			}
			transactions.add(transaction);
		}
		return transactions;
	}

}
