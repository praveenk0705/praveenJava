package apriori.core;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * The class itemset is a model for a set of items. It can be also the first
 * part or the second part of a rule or a transaction.
 * 
 * For performance purposes, the itemset contains integer. At the beginning of
 * the analysis, an integer is assigned to a different name and stored, so that
 * the name can be retrieved then (@see LabelsMap).
 * 
 * @author Benjamin Bouvier
 */
public class Itemset extends HashSet<Integer> {

	private static final long serialVersionUID = 6460184394001610729L;

	public Itemset(Itemset is1) {
		super(is1);
	}

	public Itemset() {
		super();
	}

	/**
	 * Returns the absolute support of this itemset (number of transactions in
	 * which the item is present). The support is contained in the support map,
	 * as it's used in some other parts in the code.
	 * 
	 * @return An integer which is the absolute value of support.
	 */
	public int getSupport() {
		return SupportMap.get(this);
	}

	/**
	 * Returns the relative support of this itemset (% of transactions in which
	 * the item is present). The support is contained in the support map, as
	 * it's used in some other parts in the code.
	 * 
	 * @return
	 */
	public int getRelativeSupport() {
		return SupportMap.getRelative(this);
	}

	/**
	 * If the itemset is of size N, generates all the (N-1)-subsets of the
	 * current itemset. It's used in the generation of the itemset from one
	 * level to another.
	 * 
	 * This function uses the one-to-one between subsets and integers for
	 * generating the subsets. Indeed, for each int k between 0 and 2^n,
	 * representing k as a bitstring makes it possible to generate a subset: the
	 * i-th element of the set is added to the subset if the i-th bit in k is 1,
	 * otherwise it's not added.
	 * 
	 * @return A list containing all the (N-1) subsets of the itemset.
	 */
	public List<Itemset> subsetWithoutOneElement() {
		List<Itemset> list = new ArrayList<>();
		List<Integer> itemsetAsArray = new ArrayList<>(this);
		int size = itemsetAsArray.size(); // this is N
		int limit = (int) Math.pow(2, size) - 1; // 2^N - 1
		// Generates all itemsets and adds only the ones which size is N-1
		for (int i = 0; i < limit; ++i) {
			Itemset subset = new Itemset();
			for (int j = 0; j < size; ++j) {
				if (((i >> j) & 1) == 1) {
					subset.add(itemsetAsArray.get(j));
				}
			}
			// The itemsets returned should have the current size minus 1.
			if (!subset.isEmpty() && subset.size() == size - 1) {
				list.add(subset);
			}
		}
		return list;
	}

	/**
	 * Generates all the possible rules for an itemset given. This is used for
	 * generating the rules once the frequent itemsets have been computed.
	 * 
	 * This also uses the one-to-one generation function of subsets (@see
	 * subsetWithoutOneElement()). Here, if the i-th bit of the bitstring
	 * representing k is 1, we add the element to the first part of the rule
	 * (the premise); if it's 0, we add the element to the second part of the
	 * rule.
	 * 
	 * @return A list containing all the possible rules.
	 */
	public List<Rule> generateRules() {
		List<Rule> rules = new ArrayList<>();
		List<Integer> list = new ArrayList<>(this);
		int size = this.size();
		for (int i = 1, limit = (int) Math.pow(2, size) - 1; i < limit; ++i) {
			Itemset premisse = new Itemset();
			Itemset conclusion = new Itemset();
			for (int j = 0; j < size; ++j) {
				if (((i >> j) & 1) == 1) {
					premisse.add(list.get(j));
				} else {
					conclusion.add(list.get(j));
				}
			}
			rules.add(new Rule(premisse, conclusion));
		}
		return rules;
	}

	@Override
	/**
	 * Represents the itemset as a string, by retrieving the real names of the items.
	 */
	public String toString() {
		String result = "";
		int count = 0;
		for (Integer i : this) {
			if (count++ != 0) {
				result = result.concat(", ");
			}
			result = result.concat(LabelsMap.getInstance()
					.nameCorrespondence(i));
		}
		return result;
	}
}
