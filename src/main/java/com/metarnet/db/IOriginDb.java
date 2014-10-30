package com.metarnet.db;

public interface IOriginDb
{
	String getSQL();
	
	boolean isDateType();
	
	boolean isNumberType();
	
	boolean isStringType();
}
