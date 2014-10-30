package com.amar.app.autocode;

import java.util.List;

public class Parameter
{
	private List<TableInfo> tableInfoList;

	/** 程序根路径 */
	private String path;

	/** model路径 */
	private String modelPath;

	/** xml配置文件路径 */
	private String configPath;

	/** dao的路径 */
	private String daoPath;

	/** 数据库名称 */
	private String dbname;
	
	/**数据库类型*/
	private String dbtype;

	public List<TableInfo> getTableInfoList()
	{
		return tableInfoList;
	}

	public void setTableInfoList( List<TableInfo> tableInfoList )
	{
		this.tableInfoList = tableInfoList;
	}

	public String getPath()
	{
		return path;
	}

	public void setPath( String path )
	{
		this.path = path;
	}

	public String getModelPath()
	{
		return modelPath;
	}

	public void setModelPath( String modelPath )
	{
		this.modelPath = modelPath;
	}

	public String getConfigPath()
	{
		return configPath;
	}

	public void setConfigPath( String configPath )
	{
		this.configPath = configPath;
	}

	public String getDaoPath()
	{
		return daoPath;
	}

	public void setDaoPath( String daoPath )
	{
		this.daoPath = daoPath;
	}

	public String getDbname()
	{
		return dbname;
	}

	public void setDbname( String dbname )
	{
		this.dbname = dbname;
	}

	public String getDbtype()
	{
		return dbtype;
	}

	public void setDbtype( String dbtype )
	{
		this.dbtype = dbtype;
	}

}
