package com.amar.app.autocode;

public class DBDataType
{
	/**oracle*/
	public static final String ORACLE_DATE="DATE";
	
	public static final String ORACLE_NUMBER="NUMBER";
	
	public static final String ORACLE_VARCHAR="VARCHAR";
	
	public static final String ORACLE_BLOB="BLOB";
	
	/**ORACLE没有float，这样使用只是为了统一处理*/
	public static final String ORACLE_FLOAT="float";
	
	public static final String ORACLE_KEY = "P";
	
	/**msql*/
	public static final String MYSQL_VARCHAR="varchar";
	
	public static final String MYSQL_INT="int";
	
	public static final String MYSQL_DATE="date";
	
	public static final String MYSQL_FLOAT="float";
	
	public static final String MYSQL_BLOB="blob";
	
	public static final String MYSQL_KEY = "PRI";
	
	/**java*/
	public static final String JAVA_INT="int";
	
	public static final String JAVA_LONG="long";
	
	public static final String JAVA_STRING="String";
	
	public static final String JAVA_FLOAT="float";
	
	public static final String JAVA_DATE="Date";
	
	public static final String JAVA_BLOB="byte[]";
	
	/**jdbcType*/
	public static final String JDBC_NUMBER="NUMERIC";
	
	public static final String JDBC_DATE="DATE";
	
	public static final String JDBC_DATETIME="DATETIME";
	
	public static final String JDBC_VARCHAR="VARCHAR";
	
	
}
