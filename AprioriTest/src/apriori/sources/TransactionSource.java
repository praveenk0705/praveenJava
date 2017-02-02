package apriori.sources;

import java.util.List;

import apriori.core.Itemset;

/**
 * A source of transactions.
 * 
 * This is an interface to specify that the class which implements it can
 * generate transactions. This way, we can have a lot of different transactions
 * generator with the same interface, which is really convenient for the tests
 * and the use in the algorithm.
 * 
 * @author Benjamin Bouvier
 * 
 */
public interface TransactionSource {
	/**
	 * Returns all generated transactions.
	 * 
	 * @return List of itemsets
	 */
	List<Itemset> generate();
}
