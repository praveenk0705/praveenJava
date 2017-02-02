package apriori.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import apriori.core.Itemset;

/**
 * Computes and contains all the values of supports for all the considered
 * itemsets.
 * 
 * While the support value of itemset could have been an attribute of the class
 * Itemset, it's defined outside support, so that each itemset doesn't need to
 * access all the transactions. As a matter of fact, it's defined as a singleton
 * to be accessed from anywhere (Itemset and Rule).
 * 
 * The values of supports are memoized into a map. Recomputation is then avoided
 * by looking up if the support has already been computed, which makes the
 * algorithm faster.
 * 
 * @author Benjamin Bouvier
 * 
 */
public class SupportMap {
	// Singleton
	private static Map<Itemset, Integer> map = new HashMap<>();
	
	private static List<Itemset> transactions = null;

	/**
	 * At the beginning of the algorithm, saves internally the transactions, so
	 * as to be able to compute the support values.
	 * 
	 * @param transactions The list of transactions
	 */
	public static void setup(List<Itemset> _transactions) {
		clear();
		transactions = _transactions;
	}

	/**
	 * Tries to find the support of the itemset if it hasn't been computed previously, and computes it otherwise.
	 * @param itemset The itemset for which we want the support
	 * @return The support of the itemset, in number of transactions.
	 */
	public static int get(Itemset itemset) {
		if (!map.containsKey(itemset)) {
			int support = 0;
			for (Itemset it : transactions) {
				if (it.containsAll(itemset)) {
					++support;
				}
			}
			map.put(itemset, support);
			return support;
		}
		return map.get(itemset);
	}

	/**
	 * Returns the relative support of an itemset.
	 * @param itemset The itemset for which we want to retrieve the support
	 * @return The relative support of the itemset in % (between 0 and 100), rounded.
	 */
	public static int getRelative(Itemset itemset) {
		return 100 * get(itemset) / transactions.size();
	}

	/**
	 * Returns true if the itemset has already been considered. The APriori
	 * algorithm can sometimes generate several times the same itemset, and this
	 * function allows to avoid that.
	 * 
	 * @param itemset
	 *            The itemset we're considering
	 * @return True if the itemset has been already processed, false otherwise.
	 */
	public static boolean alreadyProcessed(Itemset itemset) {
		return map.containsKey(itemset);
	}

	/**
	 * Reinits the support map.
	 */
	public static void clear() {
		map.clear();
	}
}
