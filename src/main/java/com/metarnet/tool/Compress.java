package com.metarnet.tool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;

public class Compress
{
	private static final int BUFFEREDSIZE = 1024;

	/**
	 * @param args
	 */
	public static void main( String [] args )
	{
		Compress compress = new Compress();

		try
		{
			compress.zip( "d:/auto" , "d:/auto.zip" );
		}
		catch ( IOException e )
		{
			e.printStackTrace();
		}
	}

	public void zip( String inputFilename , String zipFilename ) throws IOException
	{
		ZipOutputStream out = new ZipOutputStream( new FileOutputStream( zipFilename ) );
		try
		{
			zip( new File( inputFilename ) , out , "" );
		}
		catch ( IOException e )
		{
			throw e;
		}
		finally
		{
			out.close();
		}
	}

	private void zip( File inputFile , ZipOutputStream out , String base ) throws IOException
	{
		if ( inputFile.isDirectory() )
		{
			File [] inputFiles = inputFile.listFiles();

			if ( base.length() > 0 )
			{
				out.putNextEntry( new ZipEntry( base + "/" ) );
			}

			base = base.length() == 0 ? "" : base + "/";
			for( int i = 0 ; i < inputFiles.length ; i ++ )
			{
				zip( inputFiles[ i ] , out , base + inputFiles[ i ].getName() );
			}
		}
		else
		{
			if ( base.length() > 0 )
			{
				out.putNextEntry( new ZipEntry( base ) );
			}
			else
			{
				out.putNextEntry( new ZipEntry( inputFile.getName() ) );
			}
			FileInputStream in = new FileInputStream( inputFile );
			try
			{
				int c;
				byte [] by = new byte [ BUFFEREDSIZE ];
				while ( ( c = in.read( by ) ) != - 1 )
				{
					out.write( by , 0 , c );
				}
			}
			catch ( IOException e )
			{
				throw e;
			}
			finally
			{
				in.close();
			}
		}
	}

	/**
	 * 解压zip或者rar包的内容到指定的目录下，可以处理其文件夹下包含子文件夹的情况
	 * 
	 * @param zipFilename
	 *            要解压的zip或者rar包文件
	 * @param outputDirectory
	 *            解压后存放的目录
	 */
	@SuppressWarnings( "rawtypes" )
	public void unzip( String zipFilename , String outputDirectory ) throws IOException
	{
		File outFile = new File( outputDirectory );
		if ( ! outFile.exists() )
		{
			outFile.mkdirs();
		}
		ZipFile zipFile = new ZipFile( zipFilename );
		Enumeration en = zipFile.getEntries();
		ZipEntry zipEntry = null;
		while ( en.hasMoreElements() )
		{
			zipEntry = ( ZipEntry ) en.nextElement();
			if ( zipEntry.isDirectory() )
			{
				// mkdir directory
				String dirName = zipEntry.getName();
				// System.out.println("=dirName is:=" + dirName + "=end=");
				dirName = dirName.substring( 0 , dirName.length() - 1 );
				File f = new File( outFile.getPath() + File.separator + dirName );
				f.mkdirs();
			}
			else
			{
				// unzip file
				String strFilePath = outFile.getPath() + File.separator + zipEntry.getName();
				File f = new File( strFilePath );
				// 判断文件不存在的话，就创建该文件所在文件夹的目录
				if ( ! f.exists() )
				{
					String [] arrFolderName = zipEntry.getName().split( "/" );
					String strRealFolder = "";
					for( int i = 0 ; i < ( arrFolderName.length - 1 ) ; i ++ )
					{
						strRealFolder += arrFolderName[ i ] + File.separator;
					}
					strRealFolder = outFile.getPath() + File.separator + strRealFolder;
					File tempDir = new File( strRealFolder );
					// 此处使用.mkdirs()方法，而不能用.mkdir()
					tempDir.mkdirs();
				}
				f.createNewFile();
				InputStream in = zipFile.getInputStream( zipEntry );
				FileOutputStream out = new FileOutputStream( f );
				try
				{
					int c;
					byte [] by = new byte [ BUFFEREDSIZE ];
					while ( ( c = in.read( by ) ) != - 1 )
					{
						out.write( by , 0 , c );
					}
					// out.flush();
				}
				catch ( IOException e )
				{
					throw e;
				}
				finally
				{
					out.close();
					in.close();
				}
			}
		}
	}
}
