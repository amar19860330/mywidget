package com.amar.app.autocode;

public class OracleSelector implements DBSelector
{

	@Override
	public boolean isKey( String key )
	{
		return DBDataType.ORACLE_KEY.equals( key );
	}

	@Override
	public boolean isString( String type )
	{
		return type == null ? false : type.contains( DBDataType.ORACLE_VARCHAR );
	}

	@Override
	public boolean isNumber( String type )
	{
		return DBDataType.ORACLE_NUMBER.equals( type );
	}

	@Override
	public boolean isInt( String type )
	{
		return DBDataType.ORACLE_NUMBER.equals( type );
	}

	@Override
	public boolean isFloat( String type )
	{
		return DBDataType.ORACLE_FLOAT.equals( type );
	}

	@Override
	public boolean isDate( String type )
	{
		return type == null ? false : type.contains( DBDataType.ORACLE_DATE );
	}

	@Override
	public boolean isBlob( String type )
	{
		return DBDataType.ORACLE_BLOB.equals( type );
	}

	@Override
	public String string2JavaType( String type )
	{
		String result = "";
		if ( isString( type ) )
		{
			result = DBDataType.JAVA_STRING;
		}
		return result;
	}

	@Override
	public String number2JavaType( String type )
	{
		String result = "";
		if ( isNumber( type ) )
		{
			result = DBDataType.JAVA_INT;
		}
		return result;
	}

	@Override
	public String int2JavaType( String type )
	{
		String result = "";
		if ( isInt( type ) )
		{
			result = DBDataType.JAVA_INT;
		}
		return result;
	}

	@Override
	public String float2JavaType( String type )
	{

		String result = "";
		if ( isFloat( type ) )
		{
			result = DBDataType.JAVA_FLOAT;
		}
		return result;
	}

	@Override
	public String date2JavaType( String type )
	{
		String result = "";
		if ( isDate( type ) )
		{
			result = DBDataType.JAVA_DATE;
		}
		return result;
	}

	@Override
	public String blob2JavaType( String type )
	{
		String result = "";
		if ( isBlob( type ) )
		{
			result = DBDataType.JAVA_BLOB;
		}
		return result;
	}

	@Override
	public String datetimeFunction( String value , String compareSign , String columnname )
	{
		return columnname + " <![CDATA[" + compareSign + "]]> TO_DATE( '${" + value + "}','yyyy-mm-dd hh24:mi:ss')";
	}

	@Override
	public String keyFunction( String keyname )
	{
		StringBuilder sber = new StringBuilder();
		sber.append( "<selectKey keyProperty=\"" + keyname + "\" order=\"BEFORE\" resultType=\"long\"> \n" );
		sber.append( "select mybatis_sequence.nextval from dual \n" );
		sber.append( "</selectKey> \n" );
		return sber.toString();
	}

	@Override
	public String dateType( String columnName )
	{
		return new StringBuilder().append( "#{" ).append( columnName ).append( ",jdbcType=TIMESTAMP}," ).toString();
	}

	@Override
	public String getJavaType( String type )
	{
		String result = "";

		if ( DBDataType.JAVA_STRING.equals( type ) )
		{
			result = DBDataType.JAVA_STRING;
		}
		else if ( isString( type ) )
		{
			result = string2JavaType( type );
		}
		else if ( isDate( type ) )
		{
			result = date2JavaType( type );
		}
		else if ( isFloat( type ) )
		{
			result = float2JavaType( type );
		}
		else if ( isInt( type ) )
		{
			result = int2JavaType( type );
		}
		else if ( isNumber( type ) )
		{
			result = number2JavaType( type );
		}
		else if ( isBlob( type ) )
		{
			result = blob2JavaType( type );
		}
		return result;
	}

	@Override
	public String getJdbcType( String type )
	{
		String result = "";

		if ( isString( type ) )
		{
			result = DBDataType.JDBC_VARCHAR;
		}
		else if ( isDate( type ) )
		{
			result = DBDataType.JDBC_DATE;
		}
		else if ( isFloat( type ) )
		{
			result = DBDataType.JDBC_NUMBER;
		}
		else if ( isInt( type ) )
		{
			result = DBDataType.JDBC_NUMBER;
		}
		else if ( isNumber( type ) )
		{
			result = DBDataType.JDBC_NUMBER;
		}
		else if ( isBlob( type ) )
		{
			result = "";
		}

		return result;
	}
}
