package apriori.core;

/**
 * This class is a model for a Rule. A rule is defined by a premise and a
 * conclusion, and characterized by a support and a confidence.
 * 
 * PREMISE => CONCLUSION
 * 
 * @author Benjamin Bouvier
 */
public class Rule {

	protected Itemset premise;
	protected Itemset conclusion;

	protected double confidence;
	protected int support;
	protected int relativeSupport;
	
	public Rule(Itemset premise, Itemset conclusion) {
		this.premise = premise;
		this.conclusion = conclusion;

		// compute confidence
		Itemset union = (Itemset) premise.clone();
		union.addAll(conclusion);
		support = union.getSupport();
		relativeSupport = union.getRelativeSupport();
		confidence = 100 * ((double) support) / premise.getSupport();
	}

	// Accessors
	
	public Itemset getPremise() {
		return premise;
	}

	public Itemset getConclusion() {
		return conclusion;
	}

	public double getConfidence() {
		return confidence;
	}

	public int getSupport() {
		return support;
	}

	public int getRelativeSupport() {
		return relativeSupport;
	}

	public double confidence() {
		return confidence;
	}

	// To String!
	public String toString() {
		return premise
				.toString()
				.concat(" => ")
				.concat(conclusion.toString())
				.concat(" (support: " + support + " / confidence: "
						+ confidence + ")");
	}
}
