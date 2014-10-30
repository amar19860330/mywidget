package com.metarnet.db;

public class OriginDb4Mysql implements IOriginDb
{
	/**
	 * SELECT TABLE_NAME,COLUMN_NAME,IS_NULLABLE,DATA_TYPE,COLUMN_KEY AS ISKEY,
		NUMERIC_SCALE AS DATA_SCALE
 		FROM information_schema.columns where table_schema =#{dbname,jdbcType=VARCHAR} 
	 */
	public static final String MYSQL = "SELECT TABLE_NAME,COLUMN_NAME,IS_NULLABLE AS NULLABLE,DATA_TYPE,COLUMN_KEY AS ISKEY,NUMERIC_SCALE AS DATA_SCALE FROM information_schema.columns where table_schema =? ";
	
	@Override
	public String getSQL()
	{
		return MYSQL;
	}

	@Override
	public boolean isDateType()
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isNumberType()
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isStringType()
	{
		// TODO Auto-generated method stub
		return false;
	}

	

}
