package com.metarnet.db;

public class OriginDb4Oracle implements IOriginDb
{
	/**
	 * SELECT
	 * AA.TABLE_NAME, AA.COLUMN_NAME,AA.DATA_TYPE,AA.NULLABLE,BB.CONSTRAINT_TYPE AS ISKEY,
	 * AA.DATA_SCALE
	 * FROM USER_TAB_COLUMNS AA,
	 * (
	 * SELECT
	 * CU.TABLE_NAME,CU.COLUMN_NAME ,AU.CONSTRAINT_TYPE
	 * FROM
	 * USER_CONS_COLUMNS CU, USER_CONSTRAINTS AU
	 * WHERE
	 * CU.CONSTRAINT_NAME = AU.CONSTRAINT_NAME
	 * AND
	 * AU.CONSTRAINT_TYPE='P'
	 * ) BB
	 * WHERE
	 * AA.TABLE_NAME IN
	 * (
	 * SELECT TABLE_NAME FROM USER_TABLES
	 * )
	 * AND
	 * AA.TABLE_NAME = BB.TABLE_NAME (+)
	 * AND AA.COLUMN_NAME = BB.COLUMN_NAME(+)
	 * ORDER BY AA.TABLE_NAME, AA.COLUMN_NAME
	 */
	public static final String ORALCE = "SELECT 			AA.TABLE_NAME, AA.COLUMN_NAME,AA.DATA_TYPE,AA.NULLABLE,BB.CONSTRAINT_TYPE AS ISKEY,AA.DATA_SCALE		FROM USER_TAB_COLUMNS AA,		(			SELECT 				CU.TABLE_NAME,CU.COLUMN_NAME ,AU.CONSTRAINT_TYPE 			FROM 				USER_CONS_COLUMNS CU, USER_CONSTRAINTS AU 			WHERE 				CU.CONSTRAINT_NAME = AU.CONSTRAINT_NAME			AND 				AU.CONSTRAINT_TYPE='P'		) BB WHERE 			AA.TABLE_NAME IN		(			SELECT TABLE_NAME FROM USER_TABLES		)		AND			AA.TABLE_NAME = BB.TABLE_NAME (+)		AND AA.COLUMN_NAME = BB.COLUMN_NAME(+)			ORDER BY AA.TABLE_NAME, AA.COLUMN_NAME";
	
	public static final int ORA_DATE = 91;
	
	public static final int ORA_STRING = 12;
	
	public static final int ORA_NUMBER = 2;
	
	@Override
	public String getSQL()
	{
		return ORALCE;
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