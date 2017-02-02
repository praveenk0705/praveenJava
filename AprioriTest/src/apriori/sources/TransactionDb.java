package apriori.sources;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import apriori.core.Itemset;
import apriori.core.LabelsMap;

/**
 * Transaction defined as a JPA entity.
 * 
 * @author Benjamin Bouvier
 * 
 */
@Entity
public class TransactionDb implements Serializable {

	private static final long serialVersionUID = -7733094162010186028L;

	private List<String> items = new ArrayList<>();

	public TransactionDb(Itemset itemset) {
		List<Integer> list = new ArrayList<>(itemset);
		for (int i : list) {
			items.add(LabelsMap.getInstance().nameCorrespondence(i));
		}
	}

	public List<String> getItems() {
		return items;
	}
}
