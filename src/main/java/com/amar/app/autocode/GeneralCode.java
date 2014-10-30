package com.amar.app.autocode;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

public class GeneralCode
{
	private final Logger log = Logger.getLogger( this.getClass() );

	private DBSelector selectorDB = null;

	public GeneralCode( DBType dBType )
	{
		selectorDB = DBSelectFactory.getSelector( dBType );
	}

	public void general( Parameter p ) throws IOException
	{
		log.info( "start general code..." );
		deal( p.getPath() , p.getModelPath() , p.getConfigPath() , p.getDaoPath() , p.getTableInfoList() );
		log.info( "general code finish..." );
	}

	private void deal( String path , String modelPath , String configPath , String daoPath , List<TableInfo> tableInfoList ) throws IOException
	{

		Map<String,List<TableInfo>> tableMap = generateTableMap( tableInfoList );

		checkPath( path , modelPath , configPath , daoPath );

		checkKey( tableMap , path );

		generateJavaBean( tableMap , path , modelPath );

		generateXML( tableMap , path , configPath , daoPath );

		generateDAO( tableMap , path , daoPath , modelPath );

		generateTypeAlias( tableMap , modelPath , path );
	}

	private void generateTypeAlias( Map<String,List<TableInfo>> tableMap , String modelPath , String path ) throws IOException
	{
		try
		{
			Iterator<String> tableNames = tableMap.keySet().iterator();
			File file = new File( path + File.separatorChar + "typeAlias.txt" );
			BufferedWriter bw = new BufferedWriter( new OutputStreamWriter( new FileOutputStream( file , true ) ) );
			StringBuilder sber = new StringBuilder();
			while ( tableNames.hasNext() )
			{
				String tablename = tableNames.next();

				sber.append( "<typeAlias alias=\"" ).append( tablename.toLowerCase() );
				sber.append( "\" type=\"" ).append( modelPath.replace( "/" , "." ) );
				sber.append( "." ).append( tablename ).append( "\" />\n" );

			}
			bw.write( sber.toString() );
			bw.close();
		}
		catch ( IOException e )
		{
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 先删除再创建路径
	 */
	private void checkPath( String path , String modelPath , String configPath , String daoPath )
	{
		File root_src = new File( path );

		rmdirs( root_src );

		File root = new File( path );
		root.mkdirs();

		File modelFile = new File( path + File.separatorChar + modelPath );
		modelFile.mkdirs();

		File configFile = new File( path + File.separatorChar + configPath );
		configFile.mkdirs();

		File daoFile = new File( path + File.separatorChar + daoPath );
		daoFile.mkdirs();

	}

	public void rmdirs( File file )
	{
		if ( file.isDirectory() )
		{
			for( File children : file.listFiles() )
			{
				rmdirs( children );
			}
		}
		file.delete();
	}

	private void generateDAO( Map<String,List<TableInfo>> tableMap , String path , String daoPath , String modelPath )
	{
		try
		{
			Iterator<String> tableNames = tableMap.keySet().iterator();
			while ( tableNames.hasNext() )
			{
				String tablename = tableNames.next();

				File javaFile = new File( path + File.separatorChar + daoPath + File.separatorChar + tablename + "DAO.java" );
				BufferedWriter bw = new BufferedWriter( new OutputStreamWriter( new FileOutputStream( javaFile , true ) ) );
				StringBuilder sber = new StringBuilder();

				sber.append( "package " ).append( daoPath.replace( "/" , "." ) ).append( ";\n" );
				sber.append( "import java.util.List;\n" );
				sber.append( "import " ).append( modelPath.replace( "/" , "." ) ).append( "." ).append( tablename ).append( ";\n\n" );
				sber.append( "public interface " ).append( tablename ).append( "DAO" ).append( "\n{\n\n" );

				sber.append( "public List<" + tablename + "> find" ).append( tablename );
				sber.append( "( " + tablename + " " + tablename.toLowerCase() + " );\n\n" );
				sber.append( "public void delete" ).append( tablename ).append( "( " + tablename + " " + tablename.toLowerCase() + " );\n\n" );
				sber.append( "public void add" ).append( tablename ).append( "( " + tablename + " " + tablename.toLowerCase() + " );\n\n" );
				sber.append( "public void edit" ).append( tablename ).append( "( " + tablename + " " + tablename.toLowerCase() + " );\n\n" );
				sber.append( "}\n" );
				bw.write( sber.toString() );
				bw.close();
			}
		}
		catch ( IOException e )
		{
			e.printStackTrace();
		}
	}

	private void generateXML( Map<String,List<TableInfo>> tableMap , String path , String configPath , String daoPath )
	{
		BufferedWriter bw = null;
		try
		{
			Iterator<String> tableNames = tableMap.keySet().iterator();
			while ( tableNames.hasNext() )
			{
				String tablename = tableNames.next();

				List<TableInfo> tableInfoList = tableMap.get( tablename );

				File javaFile = new File( path + File.separatorChar + configPath + File.separatorChar + tablename.toLowerCase() + ".xml" );

				bw = new BufferedWriter( new OutputStreamWriter( new FileOutputStream( javaFile , true ) ) );

				StringBuilder sber = new StringBuilder();
				sber.append( "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" );
				sber.append( "<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \n" );
				sber.append( " \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\"> \n" );
				sber.append( "<mapper namespace=\"" + ( daoPath + "/" ).replace( "/" , "." ) + tablename + "DAO" + "\">\n\n" );

				sber.append( resultMap( tableInfoList , tablename ) );
				// find
				sber.append( findFunction( tableInfoList , tablename ) );
				// add
				sber.append( addFunction( tableInfoList , tablename ) );
				// edit
				sber.append( editFunction( tableInfoList , tablename ) );
				// delete
				sber.append( deleteFunction( tableInfoList , tablename ) );

				sber.append( "</mapper>" );

				bw.write( sber.toString() );

				bw.close();
			}
		}
		catch ( IOException e )
		{
			e.printStackTrace();
			if ( bw != null )
				try
				{
					bw.close();
				}
				catch ( IOException e1 )
				{
					e1.printStackTrace();
				}
		}
	}

	private String resultMap( List<TableInfo> tableInfoList , String tablename )
	{
		StringBuilder sber = new StringBuilder();

		sber.append( "<resultMap id=\"" + tablename.toLowerCase() + "Map\" type=\"" + tablename.toLowerCase() + "\">\n" );
		for( TableInfo tableInfo : tableInfoList )
		{
			String columnName = tableInfo.getColumn_name().toLowerCase();
			String type = tableInfo.getData_type();
			String jdbctype = "";
			if ( selectorDB.isKey( tableInfo.getIskey() ) )
			{
				if ( ! selectorDB.isDate( type ) )
				{
					jdbctype = " jdbcType=\"" + selectorDB.getJdbcType( type ) + "\" ";
				}
				sber.append( "\t<id property=\"" + columnName + "\" column=\"" + columnName + "\"" + jdbctype + " />\n" );
			}
		}

		for( TableInfo tableInfo : tableInfoList )
		{
			String columnName = tableInfo.getColumn_name().toLowerCase();
			String type = tableInfo.getData_type();
			String jdbctype = "";
			if ( ! selectorDB.isKey( tableInfo.getIskey() ) )
			{
				if ( ! selectorDB.isDate( type ) )
				{
					jdbctype = " jdbcType=\"" + selectorDB.getJdbcType( type ) + "\" ";
				}
				sber.append( "\t<result property=\"" + columnName + "\" column=\"" + columnName + "\"" + jdbctype + " />\n" );
			}

		}
		sber.append( "</resultMap>\n\n" );

		return sber.toString();
	}

	private String deleteFunction( List<TableInfo> tableInfoList , String tablename )
	{
		StringBuilder sber = new StringBuilder();

		sber.append( "<delete id=\"delete" ).append( tablename ).append( "\" parameterType=\"" );
		sber.append( tablename.toLowerCase() ).append( "\" >\n" );
		sber.append( "DELETE FROM " ).append( tablename ).append( " \n<where> \n" );
		for( TableInfo tableInfo : tableInfoList )
		{
			String columnName = tableInfo.getColumn_name().toLowerCase();

			if ( selectorDB.isKey( tableInfo.getIskey() ) )
			{
				sber.append( "AND " ).append( columnName ).append( " = #{" ).append( columnName ).append( "}\n" );
			}
		}
		sber.append( "</where>\n" ).append( "</delete>\n\n" );

		return sber.toString();
	}

	private String editFunction( List<TableInfo> tableInfoList , String tablename )
	{
		StringBuilder sber = new StringBuilder();

		sber.append( "<update id=\"edit" + tablename + "\" parameterType=\"" + tablename.toLowerCase() + "\">\n" );

		sber.append( "update " ).append( tablename );
		sber.append( "<set>\n" );
		for( TableInfo tableInfo : tableInfoList )
		{
			String columnName = tableInfo.getColumn_name().toLowerCase();
			String type = tableInfo.getData_type();
			String key = tableInfo.getIskey();

			boolean iskey = selectorDB.isKey( key );
			if ( selectorDB.isString( type ) && ! iskey )
			{
				sber.append( "<if test=\" " + columnName + " != null\">\n" );
				sber.append( columnName ).append( "=#{" ).append( columnName ).append( "},\n" );
				sber.append( "</if>\n" );
			}
			else if ( selectorDB.isNumber( type ) && ! iskey )
			{
				sber.append( "<if test=\" " + columnName + " > 0 \">\n" );
				sber.append( columnName ).append( "=#{" ).append( columnName ).append( "},\n" );
				sber.append( "</if>\n" );
			}
			else if ( selectorDB.isFloat( type ) && ! iskey )
			{
				sber.append( "<if test=\" " + columnName + " > 0 \">\n" );
				sber.append( columnName ).append( "=#{" ).append( columnName ).append( "},\n" );
				sber.append( "</if>\n" );
			}
			else if ( selectorDB.isDate( type ) && ! iskey )
			{
				sber.append( "<if test=\" " + columnName + " != null \">\n" );
				sber.append( columnName ).append( "=" ).append( selectorDB.dateType( columnName ) ).append( "\n" );
				sber.append( "</if>\n" );
			}
		}
		sber.append( "</set>\n" );
		sber.append( "<where>\n" );
		for( TableInfo tableInfo : tableInfoList )
		{
			String columnName = tableInfo.getColumn_name().toLowerCase();
			String type = tableInfo.getData_type();
			String key = tableInfo.getIskey();
			boolean iskey = selectorDB.isKey( key );
			if ( selectorDB.isString( type ) && iskey )
			{
				sber.append( "AND " ).append( columnName ).append( " = #{" ).append( columnName ).append( "} " );
			}
			else if ( selectorDB.isNumber( type ) && iskey )
			{
				sber.append( "AND " ).append( columnName ).append( " = #{" ).append( columnName ).append( "} " );
			}
			else if ( selectorDB.isFloat( type ) && iskey )
			{
				sber.append( "AND " ).append( columnName ).append( " = #{" ).append( columnName ).append( "} " );
			}
		}
		sber.append( "\n</where>\n" );
		sber.append( "</update>\n\n" );

		return sber.toString();
	}

	private String findFunction( List<TableInfo> tableInfoList , String tablename )
	{
		StringBuilder sber = new StringBuilder();

		sber.append( "<select id=\"find" + tablename + "\" parameterType=\"" );
		sber.append( tablename.toLowerCase() + "\" resultType=\"" + tablename.toLowerCase() + "\">\n" );

		StringBuilder selectField = new StringBuilder();
		for( TableInfo tableInfo : tableInfoList )
		{
			selectField.append( "A." ).append( tableInfo.getColumn_name() ).append( "," );
		}
		selectField.deleteCharAt( selectField.length() - 1 );

		sber.append( "SELECT " ).append( selectField.toString() ).append( "\n FROM " ).append( tablename );
		sber.append( " A \n<where>\n" );
		for( TableInfo tableInfo : tableInfoList )
		{
			String columnName = tableInfo.getColumn_name().toLowerCase();
			String type = tableInfo.getData_type();
			if ( selectorDB.isString( type ) )
			{
				sber.append( "<if test=\" " + columnName + " != null\">\n" );
				sber.append( " AND A." ).append( columnName ).append( " = #{" ).append( columnName ).append( "}\n" );
				sber.append( "</if>\n" );
			}
			else if ( selectorDB.isFloat( type ) )
			{
				sber.append( "<if test=\" " + columnName + " > 0 \">\n" );
				sber.append( " AND A." ).append( columnName ).append( " = #{" ).append( columnName ).append( "}\n" );
				sber.append( "</if>\n" );
			}
			else if ( selectorDB.isNumber( type ) )
			{
				sber.append( "<if test=\" " + columnName + " > 0 \">\n" );
				sber.append( " AND A." ).append( columnName ).append( " = #{" ).append( columnName ).append( "}\n" );
				sber.append( "</if>\n" );
			}
			else if ( selectorDB.isDate( type ) )
			{
				sber.append( "<if test=\" " + columnName + "start" + " !=null \">\n" );
				sber.append( " AND " ).append( selectorDB.datetimeFunction( columnName + "start" , "<" , columnName ) + "\n" );
				sber.append( "</if>\n" );
				sber.append( "<if test=\" " + columnName + "end" + " !=null \">\n" );
				sber.append( " AND " ).append( selectorDB.datetimeFunction( columnName + "end" , ">" , columnName ) + "\n" );
				sber.append( "</if>\n" );
			}
		}
		sber.append( "</where>\n</select>\n\n" );

		return sber.toString();
	}

	private String addFunction( List<TableInfo> tableInfoList , String tablename )
	{
		StringBuilder sber = new StringBuilder();
		sber.append( "<insert id=\"add" + tablename + "\" parameterType=\"" + tablename.toLowerCase() + "\" >\n" );

		for( TableInfo tableInfo : tableInfoList )
		{
			String key = tableInfo.getIskey();
			boolean iskey = selectorDB.isKey( key );
			if ( iskey )
			{
				sber.append( selectorDB.keyFunction( tableInfo.getColumn_name().toLowerCase() ) );
			}
		}

		sber.append( "insert into " ).append( tablename ).append( "\n(\n" );
		StringBuilder insertFields = new StringBuilder();
		for( TableInfo tableInfo : tableInfoList )
		{
			String columnName = tableInfo.getColumn_name().toLowerCase();
			String type = tableInfo.getData_type();

			if ( selectorDB.isDate( type ) || selectorDB.isNumber( type ) || selectorDB.isString( type ) || selectorDB.isFloat( type ) )
			{
				insertFields.append( columnName ).append( "," );
			}
		}
		if ( insertFields.length() > 0 )
		{
			insertFields.deleteCharAt( insertFields.length() - 1 );
		}
		sber.append( insertFields ).append( "\n) values (\n" );
		StringBuilder insertValues = new StringBuilder();
		for( TableInfo tableInfo : tableInfoList )
		{
			String columnName = tableInfo.getColumn_name().toLowerCase();
			String type = tableInfo.getData_type();

			if ( selectorDB.isString( type ) )
			{
				insertValues.append( "#{" ).append( columnName ).append( ",jdbcType=VARCHAR}," );
			}
			else if ( selectorDB.isNumber( type ) || selectorDB.isFloat( type ) )
			{
				insertValues.append( "#{" ).append( columnName ).append( ",jdbcType=NUMERIC}," );
			}
			else if ( selectorDB.isDate( type ) )
			{
				insertValues.append( selectorDB.dateType( columnName ) );
			}
		}
		if ( insertValues.length() > 0 )
		{
			insertValues.deleteCharAt( insertValues.length() - 1 );
		}
		sber.append( insertValues ).append( "\n)\n" );
		sber.append( "</insert>\n\n" );
		return sber.toString();
	}

	private void generateJavaBean( Map<String,List<TableInfo>> tableMap , String path , String codePath ) throws IOException
	{
		try
		{
			Iterator<String> tableNames = tableMap.keySet().iterator();

			while ( tableNames.hasNext() )
			{
				String tablename = tableNames.next();

				List<TableInfo> tableInfoList = tableMap.get( tablename );
				File javaFile = new File( path + File.separatorChar + codePath + File.separatorChar + tablename + ".java" );
				BufferedWriter bw = new BufferedWriter( new OutputStreamWriter( new FileOutputStream( javaFile , true ) ) );

				StringBuilder sber = new StringBuilder();
				sber.append( "package " ).append( codePath.replace( "/" , "." ) ).append( ";####" );
				sber.append( "\npublic class " ).append( tablename ).append( "" ).append( "\n{\n" );
				boolean importDate = false;

				for( TableInfo tableInfo : tableInfoList )
				{
					String type = tableInfo.getData_type();

					if ( selectorDB.isDate( type ) )
					{
						importDate = true;

						sber.append( beanField( tableInfo.getColumn_name() + "start" , DBDataType.JAVA_STRING ) );
						sber.append( beanField( tableInfo.getColumn_name() + "end" , DBDataType.JAVA_STRING ) );
					}
					sber.append( beanField( tableInfo.getColumn_name() , type ) );
				}

				sber.append( "\n" );

				for( TableInfo tableInfo : tableInfoList )
				{
					String type = tableInfo.getData_type();
					if ( selectorDB.isDate( type ) )
					{
						importDate = true;
						sber.append( codeSetGet( tableInfo.getColumn_name().toLowerCase() + "start" , DBDataType.JAVA_STRING ) );
						sber.append( codeSetGet( tableInfo.getColumn_name().toLowerCase() + "end" , DBDataType.JAVA_STRING ) );
					}
					sber.append( codeSetGet( tableInfo.getColumn_name().toLowerCase() , type ) );

				}

				sber.append( "}" );
				String javaFileStr = "";
				if ( importDate )
				{
					javaFileStr = sber.toString().replace( "####" , "\nimport java.util.Date;\n" );
				}
				else
				{
					javaFileStr = sber.toString().replace( "####" , "" );
				}

				bw.write( javaFileStr );
				bw.close();
			}

		}
		catch ( IOException e )
		{
			e.printStackTrace();
			throw e;
		}
	}

	private String beanField( String column_name , String type )
	{
		StringBuilder sber = new StringBuilder();

		sber.append( "private " ).append( selectorDB.getJavaType( type ) ).append( " " ).append( column_name.toLowerCase() ).append( ";\n\n" );

		return sber.toString();
	}

	private String codeSetGet( String column_name , String type )
	{
		String javaType = selectorDB.getJavaType( type );
		StringBuilder sber = new StringBuilder();
		sber.append( "public void set" ).append( upperFirstChar( column_name ) );
		sber.append( "( " ).append( javaType ).append( " " ).append( column_name ).append( " )\n{\n" ).append( "this." ).append( column_name );
		sber.append( "=" ).append( column_name ).append( ";\n}\n\n" );

		sber.append( "public " ).append( javaType ).append( " get" ).append( upperFirstChar( column_name ) );
		sber.append( "()\n{\nreturn this." ).append( column_name ).append( ";\n}\n\n" );
		return sber.toString();
	}

	private String upperFirstChar( String str )
	{
		return ( str.charAt( 0 ) + "" ).toUpperCase() + str.substring( 1 , str.length() );
	}

	private Map<String,List<TableInfo>> generateTableMap( List<TableInfo> TableInfoList )
	{
		Map<String,List<TableInfo>> tableMap = new HashMap<String,List<TableInfo>>();
		for( TableInfo tableInfo : TableInfoList )
		{
			List<TableInfo> list = tableMap.get( upperFirstChar( tableInfo.getTable_name().toLowerCase() ) );

			String type = tableInfo.getData_type();
			if ( selectorDB.isNumber( type ) )
			{
				if ( tableInfo.getData_scale() != null && Integer.parseInt( tableInfo.getData_scale() ) > 0 )
				{
					tableInfo.setData_type( DBDataType.JAVA_FLOAT );
				}
			}

			if ( list == null )
			{
				list = new ArrayList<TableInfo>();
				list.add( tableInfo );
				tableMap.put( upperFirstChar( tableInfo.getTable_name().toLowerCase() ) , list );
			}
			else
			{
				list.add( tableInfo );
			}
		}
		return tableMap;
	}

	private void checkKey( Map<String,List<TableInfo>> tableMap , String path ) throws IOException
	{
		Iterator<String> tableNames = tableMap.keySet().iterator();
		File file = new File( path + File.separatorChar + "no key.txt" );
		BufferedWriter bw = new BufferedWriter( new OutputStreamWriter( new FileOutputStream( file , true ) ) );
		StringBuilder sber = new StringBuilder();

		while ( tableNames.hasNext() )
		{
			String tablename = tableNames.next();

			List<TableInfo> tableInfoList = tableMap.get( tablename );
			boolean key = false;
			for( TableInfo tableInfo : tableInfoList )
			{
				String iskey = tableInfo.getIskey();
				if ( selectorDB.isKey( iskey ) )
				{
					key = true;
					break;
				}
			}
			if ( ! key )
			{
				sber.append( tablename + "\n" );
			}
		}
		bw.write( sber.toString() );
		bw.close();
	}
}
