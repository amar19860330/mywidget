package com.metarnet.msg;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.Topic;
import javax.swing.JTextArea;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.Logger;

public class ReceiveMessage
{
	protected final Logger log = Logger.getLogger( this.getClass() );

	private Connection connection;

	private String url;

	private String [] topics;

	private JTextArea jTextAreaContent;

	public ReceiveMessage( String ip , int port , String [] topics , JTextArea jTextAreaContent )
	{
		this.url = "tcp://" + ip + ":" + port;
		this.topics = topics;
		this.jTextAreaContent = jTextAreaContent;
	}

	public ReceiveMessage( String ip , int port , String [] topics )
	{
		this.url = "tcp://" + ip + ":" + port;
		this.topics = topics;
	}

	public void close()
	{
		try
		{
			connection.close();
		}
		catch ( JMSException e )
		{
			e.printStackTrace();
		}
	}

	public void start()
	{
		try
		{
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory( url );

			connection = connectionFactory.createConnection();

			String _topics = "";

			for( String tpoic : topics )
			{
				_topics = _topics + "," + tpoic;
			}

			log.info( url + "===> listening topic:" + _topics );

			for( String tpoic : topics )
			{
				doTopic( tpoic );
			}
		}
		catch ( JMSException e )
		{
			e.printStackTrace();
		}
	}

	public void doTopic( String topics ) throws JMSException
	{
		final Session session = connection.createSession( true , Session.CLIENT_ACKNOWLEDGE );

		Topic topic = session.createTopic( topics );

		MessageConsumer consumer = session.createConsumer( topic );

		consumer.setMessageListener( new RecMessage( topics , session,jTextAreaContent ) );

		connection.start();
	}
}
