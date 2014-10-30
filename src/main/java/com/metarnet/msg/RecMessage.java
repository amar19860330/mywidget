package com.metarnet.msg;

import java.util.Date;
import java.util.Enumeration;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.swing.JTextArea;

import org.apache.log4j.Logger;

import com.metarnet.tool.TimeDateUtil;

public class RecMessage implements MessageListener
{
	protected final Logger log = Logger.getLogger( this.getClass() );

	private String name = "";

	private Session session;

	private JTextArea jTextAreaContent;
	
	public RecMessage( String name , Session session ,JTextArea jTextAreaContent)
	{
		this.name = name;

		this.session = session;
		
		this.jTextAreaContent = jTextAreaContent;
	}
	
	public RecMessage( String name , Session session )
	{
		this.name = name;

		this.session = session;
	}

	public void onMessage( Message msg )
	{
		try
		{
			StringBuilder sb = new StringBuilder();
			@SuppressWarnings( "rawtypes" )
			Enumeration enumeration;

			if ( msg instanceof TextMessage )
			{
				TextMessage message = ( TextMessage ) msg;

				enumeration = message.getPropertyNames();

				sb.append( name ).append( " TextMessage" ).append( "\n===>" );

				while ( enumeration.hasMoreElements() )
				{
					String name = enumeration.nextElement().toString();
					sb.append( name + "=" + message.getStringProperty( name ) + "," );
				}
			}
			else if ( msg instanceof ObjectMessage )
			{
				ObjectMessage message = ( ObjectMessage ) msg;

				enumeration = message.getPropertyNames();

				sb.append( name ).append( " ObjectMessage" ).append( "\n===>" );

				while ( enumeration.hasMoreElements() )
				{
					String name = enumeration.nextElement().toString();
					sb.append( name + "=" + message.getStringProperty( name ) + "," );
				}
			}

			String oldStr = jTextAreaContent.getText();
			String s = oldStr+"\n\n"+TimeDateUtil.getDateTime( new Date() )+"\n"+sb.toString();
			jTextAreaContent.setText( s );
			jTextAreaContent.setCaretPosition( jTextAreaContent.getDocument().getLength() );
			session.commit();
			Thread.sleep( 1000 );

		}
		catch ( JMSException e )
		{
			e.printStackTrace();
		}
		catch ( InterruptedException e )
		{
			e.printStackTrace();
		}
	}

}
