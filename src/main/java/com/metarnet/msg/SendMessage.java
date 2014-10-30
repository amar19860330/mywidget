package com.metarnet.msg;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.Topic;
import org.apache.activemq.ActiveMQConnectionFactory;
import com.metarnet.jmstool.MsgDefault;
import com.metarnet.jmstool.SendMsg;

public class SendMessage implements Runnable
{
	private boolean allowSend;

	private SendMsg sendMsg;

	private String ip;

	private int port;

	private Map<String,String> data;

	private int msgType;

	private int time;

	private int sendGap;

	private String topicname;

	public SendMessage( String ip , int port , String topicname , Map<String,String> data , int msgType , int time , int sendGap , SendMsg sendMsg )
	{
		this.ip = ip;
		this.port = port;
		this.topicname = topicname;
		this.data = data;
		this.msgType = msgType;
		this.time = time;
		this.sendGap = sendGap;
		this.sendMsg = sendMsg;
	}

	public void setAllowSend( boolean allowSend )
	{
		this.allowSend = allowSend;
	}

	@Override
	public void run()
	{
		try
		{
			String url = "tcp://" + ip + ":" + port;
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory( url );
			Connection connection;

			connection = connectionFactory.createConnection();

			connection.start();

			Session session = connection.createSession( true , Session.AUTO_ACKNOWLEDGE );

			Topic topic = session.createTopic( topicname );

			MessageProducer producer = session.createProducer( topic );

			Message message = null;

			if ( msgType == MsgDefault.MSG_TYPE_TEXT )
			{
				message = session.createTextMessage();
			}
			else if ( msgType == MsgDefault.MSG_TYPE_OBJECT )
			{
				message = session.createObjectMessage();
			}

			Iterator<String> it = data.keySet().iterator();
			while ( it.hasNext() )
			{
				String proName = it.next();
				String proValue = data.get( proName );
				message.setStringProperty( proName , proValue );
			}
			
			Date start = new Date();
			
			for( int i = 0 ; i < time ; i ++ )
			{
				if ( allowSend == false )
				{
					break;
				}
				producer.send( message );
				sendMsg.addSendCount();
				session.commit();
				Thread.sleep( sendGap );
			}
			
			Date end = new Date();
			sendMsg.showInfo( "发送数据成功，用时：" + ( end.getTime() - start.getTime() ) );
			sendMsg.enableSend();
			session.close();
			connection.close();
		}
		catch ( JMSException e )
		{
			e.printStackTrace();
			sendMsg.showInfo( "发送数据失敗\n" + e.getMessage() );

		}
		catch ( Exception e )
		{
			e.printStackTrace();
			sendMsg.showInfo( "发送数据失敗\n" + e.getMessage() );

		}
	}
}
