/*
 * Reader15693.java
 * Created on __DATE__, __TIME__
 */

package com.metarnet.reader15693;

import java.util.Date;


import org.apache.log4j.Logger;

import com.metarnet.tool.TimeDateUtil;

/**
 * @author __USER__
 */
public class Reader15693 extends javax.swing.JPanel
{
	protected final Logger log = Logger.getLogger( this.getClass() );

	private static final long serialVersionUID = - 7090478574446239681L;

	/** Creates new form Reader15693 */
	public Reader15693()
	{
		initComponents();
	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents()
	{

		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jTextFieldIp = new javax.swing.JTextField();
		jTextFieldPort = new javax.swing.JTextField();
		jButtonStart = new javax.swing.JButton();
		jButtonStop = new javax.swing.JButton();
		jScrollPane1 = new javax.swing.JScrollPane();
		jTextAreaData = new javax.swing.JTextArea();
		jTextFieldCardsn = new javax.swing.JTextField();
		jLabel3 = new javax.swing.JLabel();
		jButtonClear = new javax.swing.JButton();

		jLabel1.setText( "IP\u5730\u5740\uff1a" );

		jLabel2.setText( "\u7aef\u53e3\u53f7\uff1a" );

		jTextFieldIp.setText( "192.168.1.2" );

		jTextFieldPort.setText( "5010" );

		jButtonStart.setText( "\u5f00\u59cb\u8bfb\u5361" );
		jButtonStart.addMouseListener( new java.awt.event.MouseAdapter()
		{
			public void mouseReleased( java.awt.event.MouseEvent evt )
			{
				jButtonStartMouseReleased( evt );
			}
		} );

		jButtonStop.setText( "\u505c\u6b62\u8bfb\u5361" );
		jButtonStop.setEnabled( false );
		jButtonStop.addMouseListener( new java.awt.event.MouseAdapter()
		{
			public void mouseReleased( java.awt.event.MouseEvent evt )
			{
				jButtonStopMouseReleased( evt );
			}
		} );

		jTextAreaData.setColumns( 20 );
		jTextAreaData.setRows( 5 );
		jScrollPane1.setViewportView( jTextAreaData );

		jLabel3.setText( "\u5361\u53f7\uff1a" );

		jButtonClear.setText( "\u6e05\u5c4f" );
		jButtonClear.addMouseListener( new java.awt.event.MouseAdapter()
		{
			public void mouseClicked( java.awt.event.MouseEvent evt )
			{
				jButtonClearMouseClicked( evt );
			}
		} );

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout( this );
		this.setLayout( layout );
		layout.setHorizontalGroup( layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING )
				.addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING ,
						layout.createSequentialGroup()
								.addGroup(
										layout.createParallelGroup( javax.swing.GroupLayout.Alignment.TRAILING )
												.addGroup( layout.createSequentialGroup().addContainerGap().addComponent( jButtonClear ) )
												.addGroup(
														layout.createParallelGroup( javax.swing.GroupLayout.Alignment.TRAILING )
																.addGroup(
																		javax.swing.GroupLayout.Alignment.LEADING ,
																		layout.createSequentialGroup().addGap( 125 , 125 , 125 ).addComponent( jButtonStart ).addGap( 86 , 86 , 86 )
																				.addComponent( jButtonStop ) )
																.addGroup(
																		javax.swing.GroupLayout.Alignment.LEADING ,
																		layout.createSequentialGroup()
																				.addGap( 40 , 40 , 40 )
																				.addGroup(
																						layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING ).addComponent( jLabel1 )
																								.addComponent( jLabel3 ) )
																				.addPreferredGap( javax.swing.LayoutStyle.ComponentPlacement.RELATED )
																				.addGroup(
																						layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING )
																								.addComponent( jScrollPane1 , javax.swing.GroupLayout.Alignment.TRAILING ,
																										javax.swing.GroupLayout.DEFAULT_SIZE , 354 , Short.MAX_VALUE )
																								.addComponent( jTextFieldCardsn , javax.swing.GroupLayout.DEFAULT_SIZE , 354 , Short.MAX_VALUE )
																								.addGroup(
																										layout.createSequentialGroup()
																												.addComponent( jTextFieldIp , javax.swing.GroupLayout.PREFERRED_SIZE , 150 ,
																														javax.swing.GroupLayout.PREFERRED_SIZE )
																												.addGap( 18 , 18 , 18 )
																												.addComponent( jLabel2 )
																												.addGap( 36 , 36 , 36 )
																												.addComponent( jTextFieldPort , javax.swing.GroupLayout.DEFAULT_SIZE , 102 ,
																														Short.MAX_VALUE ) ) ) ) ) )
								.addContainerGap( 52 , javax.swing.GroupLayout.PREFERRED_SIZE ) ) );
		layout.setVerticalGroup( layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING ).addGroup(
				layout.createSequentialGroup()
						.addGap( 31 , 31 , 31 )
						.addGroup(
								layout.createParallelGroup( javax.swing.GroupLayout.Alignment.BASELINE ).addComponent( jLabel1 ).addComponent( jLabel2 )
										.addComponent( jTextFieldPort , javax.swing.GroupLayout.PREFERRED_SIZE , javax.swing.GroupLayout.DEFAULT_SIZE , javax.swing.GroupLayout.PREFERRED_SIZE )
										.addComponent( jTextFieldIp , javax.swing.GroupLayout.PREFERRED_SIZE , javax.swing.GroupLayout.DEFAULT_SIZE , javax.swing.GroupLayout.PREFERRED_SIZE ) )
						.addPreferredGap( javax.swing.LayoutStyle.ComponentPlacement.RELATED )
						.addGroup( layout.createParallelGroup( javax.swing.GroupLayout.Alignment.BASELINE ).addComponent( jButtonStop ).addComponent( jButtonStart ) )
						.addGap( 18 , 18 , 18 )
						.addGroup(
								layout.createParallelGroup( javax.swing.GroupLayout.Alignment.BASELINE ).addComponent( jLabel3 )
										.addComponent( jTextFieldCardsn , javax.swing.GroupLayout.PREFERRED_SIZE , javax.swing.GroupLayout.DEFAULT_SIZE , javax.swing.GroupLayout.PREFERRED_SIZE ) )
						.addGap( 27 , 27 , 27 ).addComponent( jScrollPane1 , javax.swing.GroupLayout.PREFERRED_SIZE , 227 , javax.swing.GroupLayout.PREFERRED_SIZE )
						.addPreferredGap( javax.swing.LayoutStyle.ComponentPlacement.RELATED ).addComponent( jButtonClear ).addContainerGap( 18 , Short.MAX_VALUE ) ) );
	}// </editor-fold>
	//GEN-END:initComponents

	private void jButtonClearMouseClicked( java.awt.event.MouseEvent evt )
	{
		jTextFieldCardsn.setText( "" );
		jTextAreaData.setText( "" );
	}

	private ReadBySocket readBySocket;

	private Thread readThread;

	private void jButtonStopMouseReleased( java.awt.event.MouseEvent evt )
	{
		jButtonStop.setEnabled( false );
		jButtonStart.setEnabled( true );
		jTextFieldIp.setEnabled( true );
		jTextFieldPort.setEnabled( true );
		readBySocket.setStop( true );
		readBySocket.close();
		waitTime( 1000 );
		readThread.interrupt();
		waitTime( 1000 );
	}

	private void jButtonStartMouseReleased( java.awt.event.MouseEvent evt )
	{
		jButtonStop.setEnabled( true );
		jButtonStart.setEnabled( false );
		jTextFieldIp.setEnabled( false );
		jTextFieldPort.setEnabled( false );

		readBySocket = new ReadBySocket( jTextFieldIp.getText() , Integer.parseInt( jTextFieldPort.getText() ) , this );
		readThread = new Thread( readBySocket );
		readThread.start();
	}

	public void deal( String cardsn )
	{
		jTextFieldCardsn.setText( cardsn );
	}

	public void info( String info )
	{
		String oldStr = jTextAreaData.getText();

		if ( ! oldStr.equals( "" ) )
		{
			oldStr = oldStr + "\n";
		}
		jTextAreaData.setText( oldStr + TimeDateUtil.getDateTime( new Date() ) + "\n" + info + "\n" );

		// JScrollBar jScrollBar = jScrollPane1.getVerticalScrollBar();
		// jScrollBar.setValue( jScrollBar.getMaximum() );
		jTextAreaData.setCaretPosition( jTextAreaData.getDocument().getLength() );

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

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton jButtonClear;

	private javax.swing.JButton jButtonStart;

	private javax.swing.JButton jButtonStop;

	private javax.swing.JLabel jLabel1;

	private javax.swing.JLabel jLabel2;

	private javax.swing.JLabel jLabel3;

	private javax.swing.JScrollPane jScrollPane1;

	private javax.swing.JTextArea jTextAreaData;

	private javax.swing.JTextField jTextFieldCardsn;

	private javax.swing.JTextField jTextFieldIp;

	private javax.swing.JTextField jTextFieldPort;
	// End of variables declaration//GEN-END:variables

}
