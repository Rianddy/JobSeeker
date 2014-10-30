package common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.JobSeeker.model.Jobs;

import edu.pitt.sis.iris.squirrel.search.SearchResult;
import function.index.RetrivalModel;
import function.index.indexReader;

public class GetJobidsFromIndex {
	private String query;
	private double weight=5;
	public GetJobidsFromIndex(String query) {
		this.query = query;
	}

	public List<String> Search() throws Exception {
		RetrivalModel retrivalModel = new RetrivalModel();
		indexReader indexTitle = new indexReader(Constants.TITLE);
		indexReader indexSummary = new indexReader(Constants.SUMMARY);
		indexReader indexCompany = new indexReader(Constants.COMPANY);
		retrivalModel.setIndex(indexTitle);
		Map<String, Double> resultsTitle = retrivalModel.search(query, 500);
		retrivalModel.setIndex(indexSummary);
		Map<String, Double> resultsSummary = retrivalModel.search(query, 500);
		retrivalModel.setIndex(indexCompany);
		Map<String, Double> resultsCompany = retrivalModel.search(query, 500);
		Map<String, Double> m1=mergeTwoMaps(resultsSummary,resultsCompany);
		Map<String, Double> result=mergeTwoMapsWithWeight(m1,resultsTitle);
		Map<String, Double> sortedResultMap=sortByComparator(result);
		List<String> jobids=new ArrayList<String>();
		for (Map.Entry<String, Double> entry : sortedResultMap.entrySet()) {
			jobids.add(entry.getKey());
			System.out.println(entry);
		}
		return jobids;
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

	public Map<String, Double> mergeTwoMaps(Map<String, Double> firstMap,
			Map<String, Double> secondMap) {
		Set<Map.Entry<String, Double>> entries = firstMap.entrySet();
		for (Map.Entry<String, Double> entry : entries) {
			Double secondMapValue = secondMap.get(entry.getKey());
			if (secondMapValue == null) {
				secondMap.put(entry.getKey(), entry.getValue());
			} else {
				secondMap
						.put(entry.getKey(), entry.getValue() - secondMapValue);
			}
		}
		return secondMap;
	}
	
	public Map<String, Double> mergeTwoMapsWithWeight(Map<String, Double> firstMap,
			Map<String, Double> secondMap) {
		Set<Map.Entry<String, Double>> entries = firstMap.entrySet();
		for (Map.Entry<String, Double> entry : entries) {
			Double secondMapValue = secondMap.get(entry.getKey());
			if (secondMapValue == null) {
				secondMap.put(entry.getKey(), entry.getValue());
			} else {
				secondMap
						.put(entry.getKey(), entry.getValue() - weight*secondMapValue);
			}
		}
		return secondMap;
	}
	
	public static void main(String args[]) throws Exception{
		GetJobidsFromIndex test=new GetJobidsFromIndex("UPMC");
		test.Search();
	}
}
