package com.metarnet.luene;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.apache.lucene.search.Query;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.search.highlight.SimpleSpanFragmenter;
import org.apache.lucene.search.highlight.TokenSources;
import org.apache.lucene.search.vectorhighlight.BaseFragmentsBuilder;
import org.apache.lucene.search.vectorhighlight.FastVectorHighlighter;
import org.apache.lucene.search.vectorhighlight.FieldQuery;
import org.apache.lucene.search.vectorhighlight.FragListBuilder;
import org.apache.lucene.search.vectorhighlight.FragmentsBuilder;
import org.apache.lucene.search.vectorhighlight.ScoreOrderFragmentsBuilder;
import org.apache.lucene.search.vectorhighlight.SimpleFragListBuilder;

@SuppressWarnings( "unused" )
public class MyLuene implements Runnable
{
	public static String [] TIKA_EXTEND = { "pdf", "doc", "docx", "ppt", "pptx", "xls", "xlsx", "html", "htm" };

	public static String [] TEXT_EXTEND = { "java", "cpp", "txt" };

	private IndexWriter indexWriter;

	public long fileCount = 0;

	public long indexFileCount = 0;

	private TikaFilesFilter tikefilter = new TikaFilesFilter();

	private TextFilesFilter textfilter = new TextFilesFilter();

	private MyLueneProcess myLueneProcess;

	private String dataPath;

	private Analyzer analyzer;

	public MyLuene( MyLueneProcess myLueneProcess )
	{
		this.myLueneProcess = myLueneProcess;
		initAnalyzer();
	}

	public MyLuene()
	{
		initAnalyzer();
	}

	public void initAnalyzer()
	{
		analyzer = new SmartChineseAnalyzer( Version.LUCENE_43 );
	}

	public static void main( String args[] )
	{
		MyLuene myLuene = new MyLuene();
		System.out.println( "文件数量：" + myLuene.countFile( "D:\\java6_api" ) );
	}

	private String content = "content";

	public FastVectorHighlighter getHighlighter()
	{
		FragListBuilder fragListBuilder = new SimpleFragListBuilder();
		FragmentsBuilder fragmentBuilder = new ScoreOrderFragmentsBuilder( BaseFragmentsBuilder.COLORED_PRE_TAGS , BaseFragmentsBuilder.COLORED_POST_TAGS );
		return new FastVectorHighlighter( true , true , fragListBuilder , fragmentBuilder );
	}

	public List<LuceneResult> queryHighlighter( String queryKey , String indexPath ) throws IOException , ParseException , InvalidTokenOffsetsException
	{
		File indexDir = new File( indexPath );
		FSDirectory directory = FSDirectory.open( indexDir );
		DirectoryReader directoryReader = DirectoryReader.open( directory );
		IndexSearcher indexSearch = new IndexSearcher( directoryReader );
		QueryParser parser = new QueryParser( Version.LUCENE_43 , content , analyzer );
		Query query = parser.parse( queryKey );

		TopDocs topDocs = indexSearch.search( query , 100 );

		FastVectorHighlighter highlighter = getHighlighter();
		FieldQuery fieldQuery = highlighter.getFieldQuery( query );

		List<LuceneResult> list = new ArrayList<LuceneResult>();
		for( ScoreDoc scoreDoc : topDocs.scoreDocs )
		{
			Document doc = indexSearch.doc( scoreDoc.doc );
			String path = doc.get( "path" );

			String result = "";
			String snippet = highlighter.getBestFragment( fieldQuery , indexSearch.getIndexReader() , scoreDoc.doc , content , 100 );
			if ( snippet != null )
			{
				System.out.println( scoreDoc.doc + " : " + snippet + "<br/>" );
				result = snippet;
			}

			Date lastModified = new Date( Long.parseLong( doc.get( "lastModified" ) ) );
			long usableSpace = Long.parseLong( doc.get( "usableSpace" ) ) / ( 1024 );
			LuceneResult luceneResult = new LuceneResult( path , result , lastModified , usableSpace );
			list.add( luceneResult );
			// System.out.println( "您要查找的'" + query + "'位于:" + doc.get( "path" ) + "" );
		}
		return list;
	}

	public List<LuceneResult> query( String queryKey , String indexPath ) throws IOException , ParseException
	{
		File indexDir = new File( indexPath );
		FSDirectory directory = FSDirectory.open( indexDir );
		DirectoryReader directoryReader = DirectoryReader.open( directory );
		IndexSearcher indexSearch = new IndexSearcher( directoryReader );

		// Term term = new Term( content , queryKey.toLowerCase() );
		// TermQuery query = new TermQuery( term );

		QueryParser parser = new QueryParser( Version.LUCENE_43 , content , analyzer );
		Query query = parser.parse( queryKey );

		TopDocs topDocs = indexSearch.search( query , 100 );

		List<LuceneResult> list = new ArrayList<LuceneResult>();

		for( ScoreDoc scoreDoc : topDocs.scoreDocs )
		{
			Document doc = indexSearch.doc( scoreDoc.doc );
			Date lastModified = new Date( Long.parseLong( doc.get( "lastModified" ) ) );
			long usableSpace = Long.parseLong( doc.get( "usableSpace" ) ) / ( 1024 );
			LuceneResult luceneResult = new LuceneResult( doc.get( "path" ) , "" , lastModified , usableSpace );
			list.add( luceneResult );
			// System.out.println( "您要查找的'" + query + "'位于:" + doc.get( "path" ) + "" );
		}
		return list;
	}

	public void initWriter( String indexPath ) throws IOException
	{
		File indexDir = new File( indexPath );

		analyzer = new SmartChineseAnalyzer( Version.LUCENE_43 );

		IndexWriterConfig config = new IndexWriterConfig( Version.LUCENE_43 , analyzer );

		indexWriter = new IndexWriter( FSDirectory.open( indexDir ) , config );

	}

	public long countFile( String dataPath )
	{
		long start = System.currentTimeMillis();
		fileCount = 0;
		countFileDetail( dataPath );
		long end = System.currentTimeMillis();

		System.out.println( "扫描文件数量用时：" + ( end - start ) );
		return fileCount;
	}

	private void countFileDetail( String dataPath )
	{
		File dataFile = new File( dataPath );
		if ( dataFile.isDirectory() && dataFile.canRead() && dataFile.listFiles() != null )
		{
			for( File subFile : dataFile.listFiles() )
			{
				countFileDetail( subFile.getPath() );
			}
		}
		else if ( ( textfilter.accept( dataFile ) || tikefilter.accept( dataFile ) ) && ! dataFile.isHidden() && dataFile.exists() && dataFile.canRead() )
		{
			fileCount ++ ;
		}
	}

	public void run()
	{
		try
		{
			makeIndex( getDataPath() );
		}
		catch ( IOException e )
		{
			e.printStackTrace();
		}
	}

	public Reader getReader( String path )
	{
		Reader reader = null;
		File dataFile = new File( path );
		if ( tikefilter.accept( dataFile ) )
		{
			String tikaContent = tika( dataFile );
			reader = new StringReader( tikaContent );
		}
		else if ( textfilter.accept( dataFile ) )
		{
			try
			{
				reader = new FileReader( dataFile );
			}
			catch ( FileNotFoundException e )
			{
				e.printStackTrace();
			}
		}
		return reader;
	}

	public void makeIndex( String dataPath ) throws IOException
	{
		countFile( dataPath );

		indexFileCount = 0;
		makeIndexDetail( dataPath );
		writeClose();
	}

	private void makeIndexDetail( String dataPath ) throws IOException
	{
		File dataFile = new File( dataPath );
		if ( dataFile.isDirectory() && dataFile.canRead() && dataFile.listFiles() != null )
		{
			for( File subFile : dataFile.listFiles() )
			{
				makeIndexDetail( subFile.getPath() );
			}
		}
		else if ( tikefilter.accept( dataFile ) && ! dataFile.isHidden() && dataFile.exists() && dataFile.canRead() )
		{
			Document document = new Document();
			String tikaContent = tika( dataFile );

			Field pathField = new StringField( "path" , dataFile.getPath() , Store.YES );
			Field lastModifiedField = new StringField( "lastModified" , dataFile.lastModified() + "" , Store.YES );
			Field usableSpaceField = new StringField( "usableSpace" , dataFile.length() + "" , Store.YES );
			// Field contentField = new TextField( "content" , tikaContent , Store.NO );
			Field contentField = new Field( "content" , tikaContent , getFieldTypeForContent() );

			document.add( pathField );
			document.add( contentField );
			document.add( lastModifiedField );
			document.add( usableSpaceField );

			writeDocument( document );
			callBack();
		}
		else if ( textfilter.accept( dataFile ) && ! dataFile.isHidden() && dataFile.exists() && dataFile.canRead() )
		{
			Document document = new Document();
			Reader dataReader = new FileReader( dataFile );

			Field pathField = new StringField( "path" , dataFile.getPath() , Store.YES );
			Field lastModifiedField = new StringField( "lastModified" , dataFile.lastModified() + "" , Store.YES );
			Field usableSpaceField = new StringField( "usableSpace" , dataFile.length() + "" , Store.YES );
			// Field contentField = new TextField( "content" , dataReader );
			// Field contentField = new Field( "content" , dataReader , getFieldTypeForContent() );
			Field contentField = new Field( "content" , textFile( dataFile ) , getFieldTypeForContent() );

			document.add( pathField );
			document.add( contentField );
			document.add( lastModifiedField );
			document.add( usableSpaceField );

			writeDocument( document );

			callBack();
		}
	}

	public FieldType getFieldTypeForContent()
	{
		FieldType contentField = new FieldType();
		contentField.setIndexed( true );
		contentField.setTokenized( true );
		contentField.setStored( true );
		contentField.setStoreTermVectors( true );
		contentField.setStoreTermVectorPositions( true );
		contentField.setStoreTermVectorOffsets( true );
		contentField.freeze();
		return contentField;
	}

	private void callBack()
	{
		indexFileCount ++ ;
		long last = - 1;
		if ( myLueneProcess != null )
		{
			long oneOf100 = fileCount / 100;
			long now = oneOf100 == 0 ? 0 : indexFileCount / oneOf100;
			if ( now != last )
			{
				myLueneProcess.showLueneProcess( fileCount , indexFileCount );
				last = now;
			}
		}
	}

	private String textFile( File file )
	{
		StringBuilder content = new StringBuilder();
		BufferedReader reader = null;
		try
		{
			reader = new BufferedReader( new FileReader( file ) );
			String tempString = null;
			while ( ( tempString = reader.readLine() ) != null )
			{
				content.append( tempString );
			}
			reader.close();
		}
		catch ( IOException e )
		{
			e.printStackTrace();
		}
		finally
		{
			if ( reader != null )
			{
				try
				{
					reader.close();
				}
				catch ( IOException e1 )
				{
				}
			}
		}

		return content.toString();
	}

	private String tika( File file )
	{
		InputStream is = null;
		ContentHandler handler = new BodyContentHandler( 10 * 1024 * 1024 );

		try
		{
			is = new FileInputStream( file );

			Parser parser = new AutoDetectParser();

			ParseContext context = new ParseContext();

			context.set( Parser.class , parser );

			Metadata metadata = new Metadata();

			metadata.set( Metadata.RESOURCE_NAME_KEY , file.getName() );

			parser.parse( is , handler , metadata , context );

		}
		catch ( FileNotFoundException e )
		{
			e.printStackTrace();
		}
		catch ( IOException e )
		{
			e.printStackTrace();
		}
		catch ( SAXException e )
		{
			e.printStackTrace();
		}
		catch ( TikaException e )
		{
			e.printStackTrace();
		}
		finally
		{
			if ( is != null )
			{
				try
				{
					is.close();
				}
				catch ( IOException e )
				{
					e.printStackTrace();
				}
			}
		}
		return handler.toString();
	}

	private void writeDocument( Document document ) throws IOException
	{
		try
		{
			indexWriter.addDocument( document );
		}
		catch ( IOException e )
		{
			e.printStackTrace();
		}
	}

	public class TikaFilesFilter implements FileFilter
	{
		public boolean accept( File path )
		{
			boolean result = false;
			for( String extendName : TIKA_EXTEND )
			{
				if ( path.getName().toLowerCase().endsWith( "." + extendName ) )
				{
					result = true;
				}
			}
			return result;
		}
	}

	public class TextFilesFilter implements FileFilter
	{
		public boolean accept( File path )
		{
			boolean result = false;
			for( String extendName : TEXT_EXTEND )
			{
				if ( path.getName().toLowerCase().endsWith( "." + extendName ) )
				{
					result = true;
				}
			}
			return result;
		}
	}

	public void writeClose()
	{
		try
		{
			indexWriter.close();
		}
		catch ( IOException e )
		{
			e.printStackTrace();
		}
	}

	public String getDataPath()
	{
		return dataPath;
	}

	public void setDataPath( String dataPath )
	{
		this.dataPath = dataPath;
	}
}
