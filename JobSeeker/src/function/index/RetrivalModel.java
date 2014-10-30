package function.index;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.lang.Math;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import edu.pitt.sis.iris.squirrel.analysis.TextAnalyzer;
import edu.pitt.sis.iris.squirrel.search.SearchResult;

public class RetrivalModel {

	protected indexReader ixreader;
	private int u = 20;
	private TokenStream ts;
	private Analyzer analyzer;
	public RetrivalModel() {
		try {
			analyzer=TextAnalyzer.get( "lc", "std tk", "indri stop", "nostem" );
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public RetrivalModel setIndex(indexReader ixreader) {
		this.ixreader = ixreader;
		return this;
	}

	/**
	 * Search for the topic information. The returned results should be ranked
	 * by the score (from the most relevant to the least). max_return specifies
	 * the maximum number of results to be returned.
	 * 
	 * @param topic
	 *            The topic information to be searched for.
	 * @param max_return
	 *            The maximum number of returned document
	 * @return
	 */
	public Map<String,Double> search(String query, int max_return)
			throws IOException {
		String[] queryTerms = query.split(" ");
		ArrayList<String> allTermsConsidered = new ArrayList<String>();
		Set<Integer> allDocumentsConsidered = new HashSet<Integer>();
		Map<Integer, Double> documentProbability = new TreeMap<Integer, Double>();
		for (String term : queryTerms) {
			Reader reader=new StringReader(term);
			ts = analyzer.tokenStream("myfield", reader);
			CharTermAttribute charTermAttribute = ts.getAttribute(CharTermAttribute.class);
			ts.reset();
			while (ts.incrementToken()) {
				String queryTerm = charTermAttribute.toString();;
				int[][] postingList = ixreader.getPostingList(queryTerm);
				if (postingList != null) {
					Set<Integer> currentTermDocuments = new HashSet<Integer>();
					for (int i = 0; i < postingList.length; i++) {
						double beforeProbability;
						int docid = postingList[i][0];
						currentTermDocuments.add(docid);
						if (allDocumentsConsidered.contains(docid)) {
							beforeProbability = documentProbability
									.get(docid);
						} else {
							beforeProbability = 1;
							for (String beforeTerm : allTermsConsidered) {
								beforeProbability *= calculateSmoothingUnseenTerm(
										beforeTerm, docid);
							}
						}
						double currentTermProbability = CalculateProbability(
								queryTerm, docid, postingList[i][1]);
						documentProbability.put(docid, beforeProbability
								* currentTermProbability);
					}
					for (Integer docid : allDocumentsConsidered) {
						if (!currentTermDocuments.contains(docid)) {
							double currentTermSmoothProbability = calculateSmoothingUnseenTerm(
									queryTerm, docid);
							double beforeProbability = documentProbability
									.get(docid);
							documentProbability.put(docid,
									beforeProbability
											* currentTermSmoothProbability);
						}
					}
					allTermsConsidered.add(queryTerm);
					allDocumentsConsidered.addAll(currentTermDocuments);
				}
			}
		}
		Map<String, Double> result=new HashMap<String,Double>();
		Map<Integer, Double> sortedMap = sortByComparator(documentProbability);
		int index=0;
		for (Map.Entry entry : sortedMap.entrySet()) {
			// create SearchResult by sort and add them to list
			if (index == max_return)
				break;
			else {
				result.put(ixreader.getDocno((Integer) entry.getKey()), (Math.log10((Double) entry.getValue())));
			}
			index++;
		}
		return result;
	}

	private static Map sortByComparator(Map unsortMap) {
		List list = new LinkedList(unsortMap.entrySet());
		Collections.sort(list, new Comparator() {
			public int compare(Object o1, Object o2) {
				return ((Comparable) ((Map.Entry) (o2)).getValue())
						.compareTo(((Map.Entry) (o1)).getValue());
			}
		});
		Map sortedMap = new LinkedHashMap();
		for (Iterator it = list.iterator(); it.hasNext();) {
			Map.Entry entry = (Map.Entry) it.next();
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
	}

	public double CalculateProbability(String queryTerm, Integer docid,
			int countTermInDocument) {
		try {
			double score = 0;
			double smoothingDenominator = (double) u
					+ (double) ixreader.docLength(docid);
			double smoothingMolecular = (double) countTermInDocument
					+ (double) u * (double) ixreader.CollectionFreq(queryTerm)
					/ (double) ixreader.getCollectionLength();
			score = smoothingMolecular / smoothingDenominator;
			return score;
		} catch (Exception e) {
			System.out.println(e);
			return 0;
		}
	}

	public double calculateSmoothingUnseenTerm(String term, int docid) {
		try {
			double smoothingDenominator = (double) u
					+ (double) ixreader.docLength(docid);
			double smoothingMolecular = ((double) u * (double) (ixreader
					.CollectionFreq(term)))
					/ (double) (ixreader.getCollectionLength());
			return smoothingMolecular / smoothingDenominator;
		} catch (Exception e) {
			System.out.println(e);
		}
		return 0;
	}
}
