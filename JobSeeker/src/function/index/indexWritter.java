package function.index;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;

import edu.pitt.sis.iris.squirrel.analysis.TextAnalyzer;
import function.index.ConnectingToMysql;

import java.sql.ResultSet;
import java.sql.PreparedStatement;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.apache.lucene.analysis.standard.StandardAnalyzer;

import common.Constants;
public class indexWritter {
	private Directory directory;
	private IndexWriter ixwriter;
	TokenStream tokenStream;
	ConnectingToMysql connecting;
	Connection connection;
	String outPutPathDirectory=Constants.IndexDirectoryPath;
	static private String tableName="Jobs";
	ResultSet rs;
	Analyzer analyzer; 
	public indexWritter(){
		try {
			connecting = new ConnectingToMysql();
			connection = connecting.Get_Connection();
			directory = FSDirectory.open(new File(Constants.IndexDirectoryPath));
			analyzer = new StandardAnalyzer(Version.LUCENE_36);
			ixwriter = new IndexWriter( directory, new IndexWriterConfig( Version.LUCENE_36, TextAnalyzer.get( "lc", "std tk", "indri stop", "nostem" ) ) );
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void TokenizeAll() {

		try{
			PreparedStatement ps = connection.prepareStatement("SELECT jobid,title,company,location,summary FROM "
					+ tableName);	
			rs = ps.executeQuery();
			while (rs.next()) {
                String jobId=rs.getString(1);
                String title=rs.getString(2);
                String company=rs.getString(3);
                String location=rs.getString(4);
                String summary=rs.getString(5);
                Index(jobId,Constants.TITLE,title,Constants.COMPANY,company,Constants.LOCATION,location,Constants.SUMMARY,summary);
            }
			rs.close();
		}catch (Exception e){
			System.out.println(e);
		}
	}

	public void Index(String index, String type1, String s1,String type2, String s2,String type3, String s3,String type4, String s4) throws IOException{
		Document doc = new Document();
		doc.add(new Field(Constants.JOBID,index,Field.Store.YES,Field.Index.NOT_ANALYZED));
		doc.add(new Field(type1,s1,Field.Store.NO,Field.Index.ANALYZED, Field.TermVector.YES));
		doc.add(new Field(type2,s2,Field.Store.NO,Field.Index.ANALYZED, Field.TermVector.YES));
		doc.add(new Field(type3,s3,Field.Store.NO,Field.Index.ANALYZED, Field.TermVector.YES));
		doc.add(new Field(type4,s4,Field.Store.NO,Field.Index.ANALYZED, Field.TermVector.YES));
		ixwriter.addDocument(doc);
	}
	
	public void close() throws Exception{
		ixwriter.close();
		directory.close();
	}
	
	public static void main(String[] args){
		try{
			indexWritter index=new indexWritter();
			index.TokenizeAll();
			index.close();
		}catch(Exception e){
			System.out.println(e);
		}
	}
}
