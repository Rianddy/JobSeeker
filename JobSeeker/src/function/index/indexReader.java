package function.index;
import java.io.File;
import java.io.IOException;
import common.Constants;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.FieldSelector;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.index.TermDocs;
import org.apache.lucene.index.TermFreqVector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import edu.pitt.sis.iris.squirrel.utils.FieldFilter;
import edu.pitt.sis.iris.squirrel.utils.IndexUtils;
public class indexReader {

	private static final FieldSelector fs_docno = FieldFilter.byName(Constants.JOBID);
	
	protected File dir;
	private Directory directory;
	private IndexReader ixreader;
	private String type;
	private int collectionLength;
	public indexReader(String type) throws IOException {
		this.dir = new File(Constants.IndexDirectoryPath);
		this.directory = FSDirectory.open(dir);
		this.ixreader = IndexReader.open( directory );
		this.type=type;
		int allDocuments=ixreader.numDocs();
		int collectionLength=0;
		for(int i=0;i<allDocuments;i++){
			collectionLength+=docLength(i);
		}
		this.collectionLength=collectionLength;
		
	}
	
	/**
	 * Get the (non-negative) integer docid for the requested docno.
	 * If -1 returned, it indicates the requested docno does not exist in the index.
	 * 
	 * @param docno
	 * @return
	 * @throws IOException 
	 */
	public int getDocid( String docno ) throws IOException {
		// you should implement this method.
		return IndexUtils.find( ixreader,  Constants.JOBID, docno );
	}
	
	/**
	 * Retrive the docno for the integer docid.
	 * 
	 * @param docid
	 * @return
	 * @throws IOException 
	 */
	public String getDocno( int docid ) throws IOException {
		// you should implement this method.
		Document doc = ixreader.document( docid, fs_docno );
		return (doc==null)?null:doc.get( Constants.JOBID);
	}
	
	/**
	 * Get the posting list for the requested token.
	 * 
	 * The posting list records the documents' docids the token appears and corresponding frequencies of the term, such as:
	 *  
	 *  [docid]		[freq]
	 *  1			3
	 *  5			7
	 *  9			1
	 *  13			9
	 * 
	 * ...
	 * 
	 * In the returned 2-dimension array, the first dimension is for each document, and the second dimension records the docid and frequency.
	 * 
	 * For example:
	 * array[0][0] records the docid of the first document the token appears.
	 * array[0][1] records the frequency of the token in the documents with docid = array[0][0]
	 * ...
	 * 
	 * NOTE that the returned posting list array should be ranked by docid from the smallest to the largest. 
	 * 
	 * @param token
	 * @return
	 */
	public int[][] getPostingList( String token) throws IOException {
		// you should implement this method.
		Term tm = new Term(type, token);
		int df = ixreader.docFreq( tm );
		int[][] posting = new int[df][];
		TermDocs docs = ixreader.termDocs( tm );
		if( docs!=null ) {
			int ix = 0;
			while( docs.next() ) {
				int id = docs.doc();
				int freq = docs.freq();
				posting[ix] = new int[] { id, freq };
				ix++;
			}
		}
		return posting;
	}
	
	/**
	 * Return the number of documents that contains the token.
	 * 
	 * @param token
	 * @return
	 */
	public int DocFreq( String token) throws IOException {
		Term tm = new Term(type, token);
		int df = ixreader.docFreq( tm );
		return df;
	}
	
	/**
	 * Return the total number of times the token appears in the collection.
	 * 
	 * @param token
	 * @return
	 */
	public long CollectionFreq( String token) throws IOException {
		// you should implement this method.
		Term tm = new Term(type, token);
		long ctf = 0;
		TermDocs docs = ixreader.termDocs( tm );
		if( docs!=null ) {
			while( docs.next() ) {
				ctf = ctf + docs.freq();
			}
		}
		return ctf;
	}
	
	/**
	 * Get the length of the requested document. 
	 * 
	 * @param docid
	 * @return
	 * @throws IOException
	 */
	public int docLength( int docid) throws IOException {
		TermFreqVector vector = ixreader.getTermFreqVector( docid,  type);
		int doc_length = 0;
		if (vector!=null){
			for( int freq:vector.getTermFrequencies() ) {
				doc_length = doc_length + freq;
			}
			return doc_length;
		}else
			return 0;
		
	}
	
	public int getCollectionLength() {
		return collectionLength;
	}

	public void setCollectionLength(int collectionLength) {
		this.collectionLength = collectionLength;
	}
	
	public void close() throws IOException {
		// you should implement this method when necessary
		ixreader.close();
		directory.close();
	}
	
	
}
