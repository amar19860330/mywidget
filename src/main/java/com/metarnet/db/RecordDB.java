package com.metarnet.db;

public class RecordDB
{
	private int index;
	
	private String sDBType;

	private String name;

	private int DBType;

	private int precision;

	private int scale;
	
	public RecordDB( int index,String sDBType , String name , int dBType , int precision , int scale )
	{
		super();
		this.index = index;
		this.sDBType = sDBType;
		this.name = name;
		DBType = dBType;
		this.precision = precision;
		this.scale = scale;
	}

	public int getIndex()
	{
		return index;
	}

	public void setIndex( int index )
	{
		this.index = index;
	}
	
	public String getsDBType()
	{
		return sDBType;
	}

	public void setsDBType( String sDBType )
	{
		this.sDBType = sDBType;
	}

	public String getName()
	{
		return name;
	}

	public void setName( String name )
	{
		this.name = name;
	}

	public int getDBType()
	{
		return DBType;
	}

	public void setDBType( int dBType )
	{
		DBType = dBType;
	}

	public int getPrecision()
	{
		return precision;
	}

	public void setPrecision( int precision )
	{
		this.precision = precision;
	}

	public int getScale()
	{
		return scale;
	}

	public void setScale( int scale )
	{
		this.scale = scale;
	}

	@Override
	public String toString()
	{
		return "RecordDB [index=" + index + ", sDBType=" + sDBType + ", name=" + name + ", DBType=" + DBType + ", precision=" + precision + ", scale=" + scale + "]";
	}

}
