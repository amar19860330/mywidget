package com.metarnet.reader15693;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import org.apache.log4j.Logger;


public class ReadBySocket implements Runnable
{
	protected final Logger log = Logger.getLogger( this.getClass() );

	protected Socket socket;

	protected OutputStream os;

	protected InputStream is;

	private boolean stop = false;

	private Reader15693 reader15693;

	private String ip;

	private int port;

	public ReadBySocket( String ip , int port , Reader15693 reader15693 )
	{
		this.reader15693 = reader15693;
		this.ip = ip;
		this.port = port;
	}

	public boolean isStop()
	{
		return stop;
	}

	public void setStop( boolean stop )
	{
		this.stop = stop;
	}

	public void run()
	{
		while ( ! stop )
		{
			if ( ! connDistanceServer( ip , port ) )
			{
				reader15693.info( ip + "连接异常,程序将在" + 30 + "秒后自动重连." );
				waitTime( 30 * 1000 );
				continue;
			}
			reader15693.info( ip + "连接成功,开始接收数据." );
			while ( ! stop )
			{
				try
				{
					sendReadCardCmd();

					waitTime( 1000 );

					String res = getResponse();

					if ( res.contains( "10FF08" ) && res.length() >= 22 )
					{
						res = res.substring( 6 , 22 );

						reader15693.deal( res );
						reader15693.info( "读取到卡号:"+res );
					}

				}
				catch ( IOException e )
				{
					reader15693.info( "15693读卡器连接异常：" + e.getMessage() );
					close();
					break;

				}

			}

		}

	}

	public boolean connDistanceServer( String ip , int port )
	{
		boolean re = false;
		try
		{

			socket = new Socket();
			InetSocketAddress isa = new InetSocketAddress( ip , port );
			socket.connect( isa , 5 * 1000 );
			//socket.setKeepAlive( true );
			socket.setSoTimeout(5*1000);
			os = socket.getOutputStream();
			is = socket.getInputStream();
			re = true;
		}
		catch ( IOException e )
		{
			reader15693.info( ip + "连接socket发生异常：" + e.getMessage() );
			close();
		}

		return re;
	}

	public void close()
	{
		try
		{
			if ( socket != null )
			{
				reader15693.info("连接即将中断..");
				socket.close();
				socket = null;
			}

		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
	}

	public void sendReadCardCmd()
	{
		int [] cmds = { 0x10, 0xFF, 0x4D };
		sendCmd( cmds );
	}

	public void sendCmd( int [] cmds )
	{
		byte [] b = new byte [ cmds.length ];
		try
		{
			for( int i = 0 ; i < cmds.length ; i ++ )
			{
				b[ i ] = ( byte ) cmds[ i ];
			}
			os.write( b );
			os.flush();
		}
		catch ( IOException e )
		{
			log.error( "socket 写入异常。异常信息：" + e.getMessage() );
			log.error( "getReaderInformation cmd:" + b );
		}
	}

	public String getResponse() throws IOException
	{
		String str = null;
		// 接受读卡器返回的命令并解析
		byte [] bytes = new byte [ 1024 ];
		int r = 0;
		r = is.read( bytes );
		if ( r > 0 )
		{
			byte [] bb = new byte [ r ];
			for( int i = 0 ; i < bb.length ; i ++ )
			{
				bb[ i ] = bytes[ i ];
			}
			str = btyetoString( bb );
		}

		String nres = str.replace( " " , "" );
		return nres;
	}

	public String btyetoString( byte [] bArray )
	{
		StringBuffer sb = new StringBuffer( bArray.length );
		String sTemp;
		for( int i = 0 ; i < bArray.length ; i ++ )
		{
			sTemp = Integer.toHexString( 0xFF & bArray[ i ] );
			if ( sTemp.length() == 1 )
			{
				sb.append( 0 );

			}
			sb.append( sTemp.toUpperCase() );
			sb.append( " " );
		}
		return sb.toString();
	}

	public void waitTime( long millis )
	{
		try
		{
			Thread.sleep( millis );
		}
		catch ( InterruptedException e )
		{

			e.printStackTrace();
		}
	}
}
