package com.amar.app.autocode;

public class DBSelectFactory
{

	public static DBSelector getSelector( DBType dBType )
	{
		DBSelector select = null;

		switch ( dBType )
		{
		case MYSQL:
			select = new MysqlSelector();
			break;
		case ORACLE:
			select = new OracleSelector();
			break;
		}
		return select;
	}
}
