package com.metarnet.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amar.app.autocode.TableInfo;

public class DBTest
{

	public static void main( String [] args )
	{
		DBTest dBTest = new DBTest();
		dBTest.doATest4Mysql();
	}

	public void doATest4Mysql()
	{
		List<TableInfo> list = new ArrayList<TableInfo>();
		try
		{
			list = getMysqlTableInfo( "com.mysql.jdbc.Driver" , "jdbc:mysql://127.0.0.1:3306" , "root" , "123456" , "myaccounts" );
		}
		catch ( SQLException e )
		{
			e.printStackTrace();
		}
		catch ( ClassNotFoundException e )
		{
			e.printStackTrace();
		}
		System.out.println( "size:" + list.size() );
	}

	public List<TableInfo> getMysqlTableInfo( String dbDriver , String url , String username , String password , String dbname ) throws SQLException , ClassNotFoundException
	{
		Class.forName( dbDriver );
		Connection conn = DriverManager.getConnection( url , username , password );
		PreparedStatement stmt;
		ResultSet rs;
		String sql = OriginDb4Mysql.MYSQL;
		stmt = conn.prepareStatement( sql );

		stmt.setString( 1 , dbname );
		rs = stmt.executeQuery();

		List<TableInfo> list = new ArrayList<TableInfo>();

		while ( rs.next() )
		{
			TableInfo tableInfo = new TableInfo();

			tableInfo.setColumn_name( rs.getString( "COLUMN_NAME" ) );
			tableInfo.setData_scale( rs.getString( "DATA_SCALE" ) );
			tableInfo.setData_type( rs.getString( "DATA_TYPE" ) );
			tableInfo.setIskey( rs.getString( "ISKEY" ) );
			tableInfo.setNullable( rs.getString( "NULLABLE" ) );
			tableInfo.setTable_name( rs.getString( "TABLE_NAME" ) );

			list.add( tableInfo );
		}

		rs.close();
		stmt.close();
		stmt.close();
		return list;
	}

	public List<TableInfo> getOracleTableInfo( String dbDriver , String url , String username , String password ) throws SQLException , ClassNotFoundException
	{
		Class.forName( dbDriver );
		Connection conn = DriverManager.getConnection( url , username , password );
		Statement stmt;
		ResultSet rs;
		String sql = OriginDb4Oracle.ORALCE;
		stmt = conn.createStatement();
		rs = stmt.executeQuery( sql );

		List<TableInfo> list = new ArrayList<TableInfo>();

		while ( rs.next() )
		{
			TableInfo tableInfo = new TableInfo();

			tableInfo.setColumn_name( rs.getString( "COLUMN_NAME" ) );
			tableInfo.setData_scale( rs.getString( "DATA_SCALE" ) );
			tableInfo.setData_type( rs.getString( "DATA_TYPE" ) );
			tableInfo.setIskey( rs.getString( "ISKEY" ) );
			tableInfo.setNullable( rs.getString( "NULLABLE" ) );
			tableInfo.setTable_name( rs.getString( "TABLE_NAME" ) );

			list.add( tableInfo );
		}

		rs.close();
		stmt.close();
		stmt.close();
		return list;
	}

	public < T > List<T> envelope( ResultSet rs , Class<T> c , Map<Integer,RecordDB> columMap ) throws SQLException
	{
		List<T> list = new ArrayList<T>();

		while ( rs.next() )
		{

		}

		return list;
	}

	public void doATest4Oracle()
	{
		try
		{
			Class.forName( "oracle.jdbc.driver.OracleDriver" );
			Connection conn = DriverManager.getConnection( "jdbc:oracle:thin:@192.168.29.28:1521:TINMS" , "imstest" , "imstest" );
			Statement stmt;
			ResultSet rs;
			String sql = "select * from diningchargetemplate";

			stmt = conn.createStatement();
			rs = stmt.executeQuery( sql );

			getRecordDB( rs );

			rs.close();
			stmt.close();
			stmt.close();
		}
		catch ( ClassNotFoundException e )
		{
			e.printStackTrace();
		}
		catch ( SQLException e )
		{
			e.printStackTrace();
		}
	}

	public Map<Integer,RecordDB> getRecordDB( ResultSet rs ) throws SQLException
	{
		Map<Integer,RecordDB> map = new HashMap<Integer,RecordDB>();

		ResultSetMetaData rsmd = rs.getMetaData();
		int columnCount = rsmd.getColumnCount();

		StringBuilder sb = new StringBuilder();
		for( int i = 1 ; i <= columnCount ; i ++ )
		{
			RecordDB r = new RecordDB( i , rsmd.getColumnTypeName( i ) , rsmd.getColumnName( i ) , rsmd.getColumnType( i ) , rsmd.getScale( i ) , rsmd.getPrecision( i ) );
			map.put( i , r );

			sb.append( r.toString() + "\n" );
		}

		System.out.println( sb.toString() );
		return map;
	}

}
