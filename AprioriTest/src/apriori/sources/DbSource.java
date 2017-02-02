package apriori.sources;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import apriori.core.Itemset;
import apriori.core.LabelsMap;

/**
 * Retrieves transactions stored in a database, thanks to object db API.
 * 
 * @author Benjamin Bouvier
 * 
 */
public class DbSource implements TransactionSource {

	static private final int NUMBER_OF_TRANSACTIONS = 20;

	/**
	 * Method used for creating the database.
	 * 
	 * Randomly generates NUMBER_OF_TRANSACTIONS elements and stores them in the
	 * database.
	 */
	static public void createDb() {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("$objectdb/db/association.odb");
		EntityManager em = emf.createEntityManager();

		List<Itemset> transactions = new RandomTransactionSource(
				NUMBER_OF_TRANSACTIONS).generate();
		em.getTransaction().begin();
		for (Itemset itemset : transactions) {
			em.persist(new TransactionDb(itemset));
		}
		em.getTransaction().commit();
	}

	@Override
	/**
	 * Reads all transactions in the database and generates the corresponding itemsets.
	 */
	public List<Itemset> generate() {
		List<Itemset> list = new ArrayList<>();

        try {
            EntityManagerFactory emf = Persistence
                    .createEntityManagerFactory("$objectdb/db/association.odb");
            EntityManager em = emf.createEntityManager();

            Query q = em.createQuery("SELECT t FROM TransactionDb t");
            List<TransactionDb> results = (List<TransactionDb>) q.getResultList();
            for (TransactionDb t : results) {
                Itemset transaction = new Itemset();
                for (String s : t.getItems()) {
                    transaction.add(LabelsMap.getInstance()
                            .addNameCorrespondance(s));
                }
                list.add(transaction);
            }
        } catch (Exception e) {
            System.out.println("Error when retrieving transactions from db: " + e.toString());
        }
		return list;
	}

}
