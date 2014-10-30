/*
 * ReceiveMsg.java
 * Created on __DATE__, __TIME__
 */

package com.metarnet.jmstool;

import javax.swing.DefaultComboBoxModel;

import com.metarnet.msg.ReceiveMessage;

public class ReceiveMsg extends javax.swing.JPanel
{

	private static final long serialVersionUID = - 1024710563594121259L;

	private ReceiveMessage receiveMessage;

	/** Creates new form ReceiveMsg */
	public ReceiveMsg()
	{
		initComponents();
		myInit();
	}

	private void myInit()
	{
		jComboBoxListening.setModel( new javax.swing.DefaultComboBoxModel( new String [] { "" } ) );
	}

	// GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents()
	{

		jLabel1 = new javax.swing.JLabel();
		jTextFieldIp = new javax.swing.JTextField();
		jTextFieldPort = new javax.swing.JTextField();
		jLabel2 = new javax.swing.JLabel();
		jComboBoxListening = new javax.swing.JComboBox();
		jLabel3 = new javax.swing.JLabel();
		jTextFieldTopicname = new javax.swing.JTextField();
		jLabel4 = new javax.swing.JLabel();
		jScrollPane1 = new javax.swing.JScrollPane();
		jTextAreaContent = new javax.swing.JTextArea();
		jButtonAdd = new javax.swing.JButton();
		jButtonStartListen = new javax.swing.JButton();
		jButtonStopListen = new javax.swing.JButton();

		jLabel1.setText( "IP\u5730\u5740\uff1a" );

		jTextFieldIp.setText( "192.168.29.27" );

		jTextFieldPort.setText( "61616" );

		jLabel2.setText( "\u7aef\u53e3\u53f7\uff1a" );

		jComboBoxListening.setModel( new javax.swing.DefaultComboBoxModel( new String [] { "Item 1", "Item 2", "Item 3", "Item 4" } ) );

		jLabel3.setText( "\u4e3b\u9898\uff1a" );

		jLabel4.setText( "\u5df2\u76d1\u542c\u4e3b\u9898\uff1a" );

		jTextAreaContent.setColumns( 20 );
		jTextAreaContent.setRows( 5 );
		jScrollPane1.setViewportView( jTextAreaContent );

		jButtonAdd.setText( "\u6dfb\u52a0" );
		jButtonAdd.addMouseListener( new java.awt.event.MouseAdapter()
		{
			public void mouseReleased( java.awt.event.MouseEvent evt )
			{
				jButtonAddMouseReleased( evt );
			}
		} );

		jButtonStartListen.setText( "\u5f00\u59cb\u76d1\u542c" );
		jButtonStartListen.addMouseListener( new java.awt.event.MouseAdapter()
		{
			public void mouseReleased( java.awt.event.MouseEvent evt )
			{
				jButtonStartListenMouseReleased( evt );
			}
		} );

		jButtonStopListen.setText( "\u505c\u6b62\u76d1\u542c" );
		jButtonStopListen.setEnabled( false );
		jButtonStopListen.addMouseListener( new java.awt.event.MouseAdapter()
		{
			public void mouseReleased( java.awt.event.MouseEvent evt )
			{
				jButtonStopListenMouseReleased( evt );
			}
		} );

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout( this );
		this.setLayout( layout );
		layout.setHorizontalGroup( layout
				.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING )
				.addGroup( layout.createSequentialGroup().addGap( 125 , 125 , 125 ).addComponent( jButtonStartListen ).addContainerGap( 374 , Short.MAX_VALUE ) )
				.addGroup(
						layout.createSequentialGroup()
								.addGap( 37 , 37 , 37 )
								.addGroup(
										layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING )
												.addGroup(
														layout.createSequentialGroup()
																.addComponent( jScrollPane1 , javax.swing.GroupLayout.PREFERRED_SIZE , 480 , javax.swing.GroupLayout.PREFERRED_SIZE ).addContainerGap() )
												.addGroup(
														layout.createSequentialGroup()
																.addGroup( layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING ).addComponent( jLabel1 ).addComponent( jLabel4 ) )
																.addPreferredGap( javax.swing.LayoutStyle.ComponentPlacement.RELATED )
																.addGroup(
																		layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING )
																				.addGroup(
																						layout.createSequentialGroup()
																								.addComponent( jTextFieldIp , javax.swing.GroupLayout.DEFAULT_SIZE , 137 , Short.MAX_VALUE )
																								.addPreferredGap( javax.swing.LayoutStyle.ComponentPlacement.RELATED ) )
																				.addGroup(
																						layout.createSequentialGroup()
																								.addComponent( jComboBoxListening , javax.swing.GroupLayout.PREFERRED_SIZE ,
																										javax.swing.GroupLayout.DEFAULT_SIZE , javax.swing.GroupLayout.PREFERRED_SIZE )
																								.addGap( 48 , 48 , 48 ) ) )
																.addGroup(
																		layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING )
																				.addGroup(
																						layout.createSequentialGroup()
																								.addGroup(
																										layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING ).addComponent( jLabel2 )
																												.addComponent( jLabel3 ) )
																								.addPreferredGap( javax.swing.LayoutStyle.ComponentPlacement.RELATED )
																								.addGroup(
																										layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING , false )
																												.addComponent( jTextFieldTopicname )
																												.addComponent( jTextFieldPort , javax.swing.GroupLayout.PREFERRED_SIZE , 84 ,
																														javax.swing.GroupLayout.PREFERRED_SIZE ) )
																								.addPreferredGap( javax.swing.LayoutStyle.ComponentPlacement.RELATED ).addComponent( jButtonAdd ) )
																				.addGroup( layout.createSequentialGroup().addGap( 10 , 10 , 10 ).addComponent( jButtonStopListen ) ) )
																.addGap( 147 , 147 , 147 ) ) ) ) );
		layout.setVerticalGroup( layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING ).addGroup(
				layout.createSequentialGroup()
						.addGap( 28 , 28 , 28 )
						.addGroup(
								layout.createParallelGroup( javax.swing.GroupLayout.Alignment.BASELINE ).addComponent( jLabel1 )
										.addComponent( jTextFieldPort , javax.swing.GroupLayout.PREFERRED_SIZE , javax.swing.GroupLayout.DEFAULT_SIZE , javax.swing.GroupLayout.PREFERRED_SIZE )
										.addComponent( jLabel2 )
										.addComponent( jTextFieldIp , javax.swing.GroupLayout.PREFERRED_SIZE , javax.swing.GroupLayout.DEFAULT_SIZE , javax.swing.GroupLayout.PREFERRED_SIZE ) )
						.addPreferredGap( javax.swing.LayoutStyle.ComponentPlacement.RELATED )
						.addGroup(
								layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING )
										.addGroup(
												layout.createParallelGroup( javax.swing.GroupLayout.Alignment.BASELINE )
														.addComponent( jComboBoxListening , javax.swing.GroupLayout.PREFERRED_SIZE , javax.swing.GroupLayout.DEFAULT_SIZE ,
																javax.swing.GroupLayout.PREFERRED_SIZE ).addComponent( jLabel4 ) )
										.addGroup(
												layout.createParallelGroup( javax.swing.GroupLayout.Alignment.BASELINE )
														.addComponent( jTextFieldTopicname , javax.swing.GroupLayout.PREFERRED_SIZE , javax.swing.GroupLayout.DEFAULT_SIZE ,
																javax.swing.GroupLayout.PREFERRED_SIZE ).addComponent( jLabel3 ).addComponent( jButtonAdd ) ) )
						.addPreferredGap( javax.swing.LayoutStyle.ComponentPlacement.RELATED )
						.addGroup( layout.createParallelGroup( javax.swing.GroupLayout.Alignment.BASELINE ).addComponent( jButtonStartListen ).addComponent( jButtonStopListen ) )
						.addPreferredGap( javax.swing.LayoutStyle.ComponentPlacement.RELATED )
						.addComponent( jScrollPane1 , javax.swing.GroupLayout.PREFERRED_SIZE , 238 , javax.swing.GroupLayout.PREFERRED_SIZE )
						.addContainerGap( javax.swing.GroupLayout.DEFAULT_SIZE , Short.MAX_VALUE ) ) );
	}// </editor-fold>
		// GEN-END:initComponents

	private void jButtonStopListenMouseReleased( java.awt.event.MouseEvent evt )
	{
		print( "in jButtonStopListenMouseReleased" );
		jButtonStopListen.setEnabled( false );
		jButtonStartListen.setEnabled( true );
		jButtonAdd.setEnabled( true );
		jTextFieldPort.setEnabled( true );
		jTextFieldIp.setEnabled( true );
		jTextFieldTopicname.setEnabled( true );
		jComboBoxListening.setEnabled( true );

		if ( receiveMessage != null )
		{
			receiveMessage.close();
		}
	}

	private void jButtonStartListenMouseReleased( java.awt.event.MouseEvent evt )
	{
		print( "in jButtonStartListenMouseReleased" );
		jButtonStopListen.setEnabled( true );
		jButtonStartListen.setEnabled( false );
		jButtonAdd.setEnabled( false );
		jTextFieldPort.setEnabled( false );
		jTextFieldIp.setEnabled( false );
		jTextFieldTopicname.setEnabled( false );
		jComboBoxListening.setEnabled( false );

		String ip = jTextFieldIp.getText();
		int port = Integer.parseInt( jTextFieldPort.getText().toString() );

		DefaultComboBoxModel model = ( DefaultComboBoxModel ) jComboBoxListening.getModel();

		int size = model.getSize();

		String topics[] = new String [ size ];

		for( int i = 0 ; i < size ; i ++ )
		{
			String str = model.getElementAt( i ).toString();
			topics[ i ] = str;
		}

		receiveMessage = new ReceiveMessage( ip , port , topics , jTextAreaContent );
		
		receiveMessage.start();
	}
	
	public void deal(String str)
	{
		jTextAreaContent.setCaretPosition( jTextAreaContent.getDocument().getLength() );
	}

	private void jButtonAddMouseReleased( java.awt.event.MouseEvent evt )
	{
		print( "in jButtonAddMouseReleased" );
		String topicName = jTextFieldTopicname.getText();

		if ( "".equals( topicName ) )
		{
			return;
		}

		DefaultComboBoxModel model = ( DefaultComboBoxModel ) jComboBoxListening.getModel();

		int size = model.getSize();

		for( int i = 0 ; i < size ; i ++ )
		{
			String str = model.getElementAt( 0 ).toString();

			if ( topicName.equals( str ) )
			{
				return;
			}

		}
		model.addElement( topicName );

		jTextFieldTopicname.setText( "" );
	}

	public void print( String s )
	{
		System.out.println( s );
	}

	// GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton jButtonAdd;

	private javax.swing.JButton jButtonStartListen;

	private javax.swing.JButton jButtonStopListen;

	private javax.swing.JComboBox jComboBoxListening;

	private javax.swing.JLabel jLabel1;

	private javax.swing.JLabel jLabel2;

	private javax.swing.JLabel jLabel3;

	private javax.swing.JLabel jLabel4;

	private javax.swing.JScrollPane jScrollPane1;

	private javax.swing.JTextArea jTextAreaContent;

	private javax.swing.JTextField jTextFieldIp;

	private javax.swing.JTextField jTextFieldPort;

	private javax.swing.JTextField jTextFieldTopicname;
	// End of variables declaration//GEN-END:variables

}
