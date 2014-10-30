package com.amar.app.autocode;

public class TableInfo
{
	private String table_name;
	private String column_name;
	private String data_type;
	private String nullable;
	private String iskey;
	private String data_scale;
	
	public String getTable_name()
	{
		return table_name;
	}
	public void setTable_name( String table_name )
	{
		this.table_name = table_name;
	}
	public String getColumn_name()
	{
		return column_name;
	}
	public void setColumn_name( String column_name )
	{
		this.column_name = column_name;
	}
	public String getData_type()
	{
		return data_type;
	}
	public void setData_type( String data_type )
	{
		this.data_type = data_type;
	}
	public String getNullable()
	{
		return nullable;
	}
	public void setNullable( String nullable )
	{
		this.nullable = nullable;
	}
	public String getIskey()
	{
		return iskey;
	}
	public void setIskey( String iskey )
	{
		this.iskey = iskey;
	}
	public String getData_scale()
	{
		return data_scale;
	}
	public void setData_scale( String data_scale )
	{
		this.data_scale = data_scale;
	}
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append( "TableInfo [table_name=" ).append( table_name ).append( ", column_name=" ).append( column_name ).append( ", data_type=" ).append( data_type ).append( ", nullable=" )
				.append( nullable ).append( ", iskey=" ).append( iskey ).append( ", data_scale=" ).append( data_scale ).append( "]" );
		return builder.toString();
	}
	
	
}
