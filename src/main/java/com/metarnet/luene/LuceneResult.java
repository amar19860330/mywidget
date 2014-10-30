package com.metarnet.luene;

import java.util.Date;

public class LuceneResult
{
	private String path;
	private String remark;
	private Date lastModityDate;
	private long usableSpace;

	public LuceneResult( String path , String remark , Date lastModityDate , long usableSpace )
	{
		super();
		this.path = path;
		this.remark = remark;
		this.lastModityDate = lastModityDate;
		this.usableSpace = usableSpace;
	}

	public String getPath()
	{
		return path;
	}

	public void setPath( String path )
	{
		this.path = path;
	}

	public String getRemark()
	{
		return remark;
	}

	public void setRemark( String remark )
	{
		this.remark = remark;
	}

	public Date getLastModityDate()
	{
		return lastModityDate;
	}

	public void setLastModityDate( Date lastModityDate )
	{
		this.lastModityDate = lastModityDate;
	}

	public long getUsableSpace()
	{
		return usableSpace;
	}

	public void setUsableSpace( long usableSpace )
	{
		this.usableSpace = usableSpace;
	}
	
	
}
