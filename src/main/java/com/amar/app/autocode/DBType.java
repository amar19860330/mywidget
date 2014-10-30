package com.amar.app.autocode;

public enum DBType
{
	ORACLE("oracle"), MYSQL("mysql");

	private String value;

	private DBType( String value )
	{
		this.value = value;
	}

	public static DBType getType( String value )
	{
		DBType [] items = values();
		for( DBType item : items )
		{
			if ( item.value.equals( value ) )
			{
				return item;
			}
		}
		return null;
	}
}
