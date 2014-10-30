package com.metarnet.luene;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.w3c.dom.Node;

public class DealXML
{
	public String path;

	public String remark;

	public DealXML()
	{

	}

	public DealXML( String path , String remark )
	{
		this.path = path;
		this.remark = remark;
	}

	public final String rootPath = getClass().getResource( "/" ).getPath();

	public final String indexPath = rootPath + "index";

	public final String luceneConfig = rootPath + "luceneConfig.xml";

	public static void main( String [] args )
	{
		// DealXML deal = new DealXML();
		// deal.check( deal.luceneConfig );
		// deal.add( deal.luceneConfig , "e:/" , "备注信息" );
		// deal.add( deal.luceneConfig , "c:/" , "备注信息" );
		// deal.add( deal.luceneConfig , "d:/" , "备注信息" );
		// deal.check( deal.luceneConfig );
		// deal.delete( deal.luceneConfig , "e:/" );
		// parser.read( deal.luceneConfig );

	}

	public void check( String configPath )
	{
		try
		{
			Document document = parse( configPath );
			if ( document == null )
			{
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();
				Document documentNew = builder.newDocument();

				Element rootElement = documentNew.createElement( "monitors" );
				documentNew.appendChild( rootElement );
				makeToFile( documentNew , configPath );
			}
		}
		catch ( ParserConfigurationException e )
		{
			e.printStackTrace();
		}
	}

	public void makeToFile( Document document , String filePath )
	{
		makeToFile( document , new File( filePath ) );
	}

	public void makeToFile( Document document , File file )
	{
		try
		{
			TransformerFactory tfactory = TransformerFactory.newInstance();
			Transformer transformer = tfactory.newTransformer();
			DOMSource source = new DOMSource( document );
			StreamResult result = new StreamResult( file );
			transformer.transform( source , result );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
	}

	public void delete( String configPath , String path )
	{
		Document document = parse( configPath );
		if ( document != null )
		{
			try
			{
				Element rootElement = document.getDocumentElement();

				NodeList nodeList = rootElement.getElementsByTagName( "monitor" );
				if ( nodeList != null )
				{
					for( int i = 0 ; i < nodeList.getLength() ; i ++ )
					{
						Element element = ( Element ) nodeList.item( i );
						NodeList MonitorNodeList = element.getElementsByTagName( "path" );
						if ( MonitorNodeList != null )
						{
							for( int j = 0 ; j < MonitorNodeList.getLength() ; j ++ )
							{
								Element elementPath = ( Element ) MonitorNodeList.item( j );

								String value = elementPath.getTextContent();
								if ( path.equals( value ) )
								{
									rootElement.removeChild( element );
									break;
								}
							}
						}
					}
					makeToFile( document , configPath );
				}
			}
			catch ( Exception e )
			{
				e.printStackTrace();
			}
		}
	}

	public void add( String configPath , String path , String remark )
	{
		Document document = parse( configPath );
		try
		{
			if ( document != null )
			{
				Element rootElement = document.getDocumentElement();

				Node newNode = document.createElement( "monitor" );
				Node pathNode = document.createElement( "path" );
				pathNode.setTextContent( path );
				Node remarkNode = document.createElement( "remark" );
				remarkNode.setTextContent( remark );

				newNode.appendChild( pathNode );
				newNode.appendChild( remarkNode );

				rootElement.appendChild( newNode );

				makeToFile( document , configPath );
			}
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
	}

	public List<String> read( String path )
	{
		List<String> list = new ArrayList<String>();

		Document document = parse( path );

		if ( document != null )
		{
			Element rootElement = document.getDocumentElement();

			NodeList nodeList = rootElement.getElementsByTagName( "monitor" );
			if ( nodeList != null )
			{
				for( int i = 0 ; i < nodeList.getLength() ; i ++ )
				{
					Element element = ( Element ) nodeList.item( i );
					NodeList MonitorNodeList = element.getElementsByTagName( "path" );
					if ( MonitorNodeList != null )
					{
						for( int j = 0 ; j < MonitorNodeList.getLength() ; j ++ )
						{
							Element elementPath = ( Element ) MonitorNodeList.item( j );

							list.add( elementPath.getTextContent() );
						}
					}
				}
			}
		}
		return list;
	}

	public Document parse( String filePath )
	{
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		Document document = null;
		try
		{
			DocumentBuilder builder = builderFactory.newDocumentBuilder();
			document = builder.parse( new File( filePath ) );
		}
		catch ( ParserConfigurationException e )
		{
			e.printStackTrace();
		}
		catch ( SAXException e )
		{
			e.printStackTrace();
		}
		catch ( IOException e )
		{
			e.printStackTrace();
		}
		return document;
	}

}
