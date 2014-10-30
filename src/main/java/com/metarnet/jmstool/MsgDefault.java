package com.metarnet.jmstool;

import java.util.ArrayList;
import java.util.List;

public class MsgDefault
{
	private String title;

	private String title_ch;
	
	private int msgType;

	public static final int MSG_TYPE_TEXT = 0;
	public static final int MSG_TYPE_OBJECT = 1;
	
	public int getMsgType()
	{
		return msgType;
	}

	public void setMsgType( int msgType )
	{
		this.msgType = msgType;
	}

	private List<Property> proList = new ArrayList<Property>();

	public Object [][] toObjects()
	{
		Object [][] objects = new Object [ proList.size() ] [ 3 ];
		int i = 0;
		for( Property pro : proList )
		{
			objects[ i ][ 0 ] = pro.getUseage();
			objects[ i ][ 1 ] = pro.getProName();
			objects[ i ][ 2 ] = pro.getProValue();
			i++;
		}
		return objects;
	}

	public void addPro( String useage , String proName , String proValue )
	{
		Property pro = new Property( useage , proName , proValue );

		proList.add( pro );
	}

	public List<Property> getProList()
	{
		return proList;
	}

	public void setProList( List<Property> proList )
	{
		this.proList = proList;
	}

	public MsgDefault( String title , String title_ch ,int msgType)
	{
		super();
		this.title = title;
		this.title_ch = title_ch;
		this.msgType = msgType;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle( String title )
	{
		this.title = title;
	}

	public String getTitle_ch()
	{
		return title_ch;
	}

	public void setTitle_ch( String title_ch )
	{
		this.title_ch = title_ch;
	}

	public class Property
	{
		/** 用途 */
		private String useage;

		/** 属性名 */
		private String proName;

		/** 属性值 */
		private String proValue;

		public Property( String useage , String proName , String proValue )
		{
			super();
			this.useage = useage;
			this.proName = proName;
			this.proValue = proValue;
		}

		public String getUseage()
		{
			return useage;
		}

		public void setUseage( String useage )
		{
			this.useage = useage;
		}

		public String getProName()
		{
			return proName;
		}

		public void setProName( String proName )
		{
			this.proName = proName;
		}

		public String getProValue()
		{
			return proValue;
		}

		public void setProValue( String proValue )
		{
			this.proValue = proValue;
		}

	};

}
