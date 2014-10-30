package com.metarnet.recmsg;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.metarnet.jmstool.MsgDefault;

public class ReadExcel
{
	public static final String config = "/config.xlsx";

	/**
	 * @param args
	 */
	public static void main( String [] args )
	{

		ReadExcel readExcel = new ReadExcel();
		readExcel.read( config );

	}

	public MsgDefault readSheet( String findSheetName )
	{
		MsgDefault msgDefault = null;
		try
		{
			URL url = getClass().getResource( config );

			XSSFWorkbook demoWorkBook = new XSSFWorkbook( url.openStream() );

			int sheetCount = demoWorkBook.getNumberOfSheets();
			for( int sheetIndex = 0 ; sheetIndex < sheetCount ; sheetIndex ++ )
			{
				XSSFSheet sheet = demoWorkBook.getSheetAt( sheetIndex );
				String sheetName_ch = sheet.getSheetName();

				if ( findSheetName.equals( sheetName_ch ) )
				{
					int rowCount = sheet.getLastRowNum();

					String sheetName = getCellValue( sheet.getRow( 0 ).getCell( 0 ) );

					int msgType = Integer.parseInt( getCellValue( sheet.getRow( 0 ).getCell( 1 ) ) );
					
					msgDefault = new MsgDefault( sheetName , sheetName_ch ,msgType);

					for( int i = 2 ; i <= rowCount ; i ++ )
					{
						XSSFRow row = sheet.getRow( i );
						String useage = getCellValue( row.getCell( 0 ) );
						String proName = getCellValue( row.getCell( 1 ) );
						String proValue = getCellValue( row.getCell( 2 ) );
						System.out.println("用途："+useage+","+proName+","+proValue);
						msgDefault.addPro( useage , proName , proValue );
					}
					break;
				}

			}
		}
		catch ( FileNotFoundException e )
		{
			e.printStackTrace();
		}
		catch ( IOException e )
		{
			e.printStackTrace();
		}
		return msgDefault;
	}

	public Map<String,MsgDefault> read()
	{
		return read( config );
	}

	public Map<String,MsgDefault> read( String filename )
	{
		Map<String,MsgDefault> map = new HashMap<String,MsgDefault>();
		try
		{
			URL url = getClass().getResource( filename );

			XSSFWorkbook demoWorkBook = new XSSFWorkbook( url.openStream() );

			int sheetCount = demoWorkBook.getNumberOfSheets();
			for( int sheetIndex = 0 ; sheetIndex < sheetCount ; sheetIndex ++ )
			{
				XSSFSheet sheet = demoWorkBook.getSheetAt( sheetIndex );
				String sheetName_ch = sheet.getSheetName();
				int rowCount = sheet.getLastRowNum();

				String sheetName = getCellValue( sheet.getRow( 0 ).getCell( 0 ) );

				int msgType = Integer.parseInt( getCellValue( sheet.getRow( 0 ).getCell( 1 ) ) );
				
				MsgDefault msgDefault = new MsgDefault( sheetName , sheetName_ch ,msgType);

				for( int i = 2 ; i < rowCount ; i ++ )
				{
					XSSFRow row = sheet.getRow( i );
					String useage = getCellValue( row.getCell( 0 ) );
					String proName = getCellValue( row.getCell( 1 ) );
					String proValue = getCellValue( row.getCell( 2 ) );
					msgDefault.addPro( useage , proName , proValue );
				}
				map.put( sheetName_ch , msgDefault );
			}

		}
		catch ( FileNotFoundException e )
		{
			e.printStackTrace();
		}
		catch ( IOException e )
		{
			e.printStackTrace();
		}
		return map;
	}

	public String getCellValue( XSSFCell cell )
	{
		String s = "";

		if ( cell != null )
		{
			int cellType = cell.getCellType();
			if ( cellType == XSSFCell.CELL_TYPE_STRING )
			{
				s = cell.getStringCellValue();
			}
			else if ( cellType == XSSFCell.CELL_TYPE_NUMERIC )
			{
				s = cell.getNumericCellValue() + "";
				if( s.contains( "." ))
				{
					int index = s.indexOf( "." );
					s = s.substring( 0 , index );
				}
			}
			else 
			{
				s = cell.getStringCellValue();
			}
		}

		return s;
	}
}
