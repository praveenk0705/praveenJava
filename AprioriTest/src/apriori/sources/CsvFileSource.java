package apriori.sources;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import apriori.core.Itemset;

/**
 * Retrieves CSV from a file and converts them into transactions.
 * 
 * @author Benjamin Bouvier
 * 
 */
public class CsvFileSource extends CsvSource {

	private String filename;

	public CsvFileSource(String filename) {
		this.filename = filename;
	}

	@Override
	public List<Itemset> generate() {
		List<String> sources = new ArrayList<>();
		try {
			Scanner scan = new Scanner(new File(filename));
			while (scan.hasNextLine()) {
				sources.add(scan.nextLine());
			}
			scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return fromCsvStrings(sources);
	}
}
