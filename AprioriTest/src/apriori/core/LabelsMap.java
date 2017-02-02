package apriori.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is used to store the names of the items contained in the
 * transactions. For each item, a number is assigned, so that itemset only
 * contains integers. The real names can be found back thanks to the mapping.
 * 
 * @author Benjamin Bouvier
 * 
 */
public class LabelsMap {

	private Map<String, Integer> nameInt;
	private Map<Integer, String> intName;
	private int lastAvailableProductId = 0;

	private LabelsMap() {
		nameInt = new HashMap<>();
		intName = new HashMap<>();
	}

	/**
	 * LabelsMap is a singleton.
	 */
	static private LabelsMap instance;

	static public LabelsMap getInstance() {
		if (instance == null) {
			instance = new LabelsMap();
		}
		return instance;
	}

	/**
	 * Tries to find back the name linked to the given integer.
	 * @param i the ID of the product
	 * @return the given name of the product or null if it's not found
	 */
	public String nameCorrespondence(Integer i) {
		return ( intName.containsKey(i) ) ? intName.get(i) : null;
	}

	/**
	 * Adds the product name to the maps, if it wasn't already there.
	 * @param s the product name to convert into an ID
	 * @return the ID assigned to the product name
	 */
	public int addNameCorrespondance(String s) {
		if (nameInt.containsKey(s)) {
			return nameInt.get(s);
		}

		int res = lastAvailableProductId;
		nameInt.put(s, lastAvailableProductId);
		intName.put(lastAvailableProductId, s);
		++lastAvailableProductId;
		return res;
	}

	/**
	 * Returns the list of all products.
	 * As in the begin of the Apriori algorithm we need to compute the supports
	 * of itemsets of size 1, we need to know what are all the products.
	 * @return A list containing the unique products ids.
	 */
	public List<Integer> getAllProducts() {
		List<Integer> list = new ArrayList<>();
		for (String s : nameInt.keySet()) {
			list.add(nameInt.get(s));
		}
		return list;
	}

	/**
	 * Reinits the labels map
	 */
	public void clear() {
		nameInt.clear();
		intName.clear();
		lastAvailableProductId = 0;
	}
}
