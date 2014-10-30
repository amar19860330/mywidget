package com.amar.app.autocode;

public interface DBSelector
{
	public boolean isKey( String key );

	public boolean isString( String type );

	public boolean isNumber( String type );

	public boolean isInt( String type );

	public boolean isFloat( String type );

	public boolean isDate( String type );

	public boolean isBlob( String type );

	public String string2JavaType( String type );

	public String number2JavaType( String type );

	public String int2JavaType( String type );

	public String float2JavaType( String type );

	public String date2JavaType( String type );

	public String blob2JavaType( String type );

	public String datetimeFunction( String value , String compareSign , String columnname );

	public String keyFunction( String keyname );

	public String dateType( String columnName );

	public String getJavaType( String type );
	
	public String getJdbcType( String type );

}
