/*
 * MainForm.java
 * Created on __DATE__, __TIME__
 */

package com.metarnet;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JComponent;

import com.metarnet.autocode.AutoCode;
import com.metarnet.commonTool.Zazhicalc;
import com.metarnet.jmstool.ReceiveMsg;
import com.metarnet.jmstool.SendMsg;
import com.metarnet.luene.LueneForm;
import com.metarnet.reader15693.Reader15693;

/**
 * @author __USER__
 */
public class MainForm extends javax.swing.JFrame
{

	private static final long serialVersionUID = - 4072818349681844891L;

	private Map<Integer,Integer> appMap = new HashMap<Integer,Integer>();

	private int appIndex = - 1;

	/** Creates new form MainForm */
	public MainForm()
	{
		initComponents();
	}

	// GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents()
	{

		contentsTab = new javax.swing.JTabbedPane();
		jMenuBar1 = new javax.swing.JMenuBar();
		jMenu1 = new javax.swing.JMenu();
		jMenu2 = new javax.swing.JMenu();
		MenuBtnSendMsg = new javax.swing.JMenuItem();
		MenuBtnReceiveMsg = new javax.swing.JMenuItem();
		jMenu3 = new javax.swing.JMenu();
		jMenuItemReader15693 = new javax.swing.JMenuItem();
		jMenu4 = new javax.swing.JMenu();
		jMenuItemAutoCode = new javax.swing.JMenuItem();
		jMenu5 = new javax.swing.JMenu();
		jMenuItemZazhiCalc = new javax.swing.JMenuItem();
		jMenuItemLucene = new javax.swing.JMenuItem();

		setDefaultCloseOperation( javax.swing.WindowConstants.EXIT_ON_CLOSE );

		jMenu1.setText( "File" );
		jMenuBar1.add( jMenu1 );

		jMenu2.setText( "\u6d88\u606f" );

		MenuBtnSendMsg.setText( "\u53d1\u6d88\u606f" );
		MenuBtnSendMsg.addMouseListener( new java.awt.event.MouseAdapter()
		{
			public void mouseReleased( java.awt.event.MouseEvent evt )
			{
				MenuBtnSendMsgMouseReleased( evt );
			}
		} );
		jMenu2.add( MenuBtnSendMsg );

		MenuBtnReceiveMsg.setText( "\u6536\u6d88\u606f" );
		MenuBtnReceiveMsg.addMouseListener( new java.awt.event.MouseAdapter()
		{
			public void mouseReleased( java.awt.event.MouseEvent evt )
			{
				MenuBtnReceiveMsgMouseReleased( evt );
			}
		} );
		jMenu2.add( MenuBtnReceiveMsg );

		jMenuBar1.add( jMenu2 );

		jMenu3.setText( "\u8bbe\u5907" );

		jMenuItemReader15693.setText( "\u5c0f\u767d\u8bfb\u5361\u5668" );
		jMenuItemReader15693.addMouseListener( new java.awt.event.MouseAdapter()
		{
			public void mouseReleased( java.awt.event.MouseEvent evt )
			{
				jMenuItemReader15693MouseReleased( evt );
			}
		} );
		jMenu3.add( jMenuItemReader15693 );

		jMenuBar1.add( jMenu3 );

		jMenu4.setText( "\u5f00\u53d1" );

		jMenuItemAutoCode.setText( "\u81ea\u52a8\u751f\u6210\u4ee3\u7801" );
		jMenuItemAutoCode.addMouseListener( new java.awt.event.MouseAdapter()
		{
			public void mouseReleased( java.awt.event.MouseEvent evt )
			{
				jMenuItemAutoCodeMouseReleased( evt );
			}
		} );
		jMenu4.add( jMenuItemAutoCode );

		jMenuBar1.add( jMenu4 );

		jMenu5.setText( "\u5e38\u7528\u5de5\u5177" );

		jMenuItemZazhiCalc.setText( "\u6742\u8d28\u8ba1\u7b97" );
		jMenuItemZazhiCalc.addMouseListener( new java.awt.event.MouseAdapter()
		{
			public void mouseReleased( java.awt.event.MouseEvent evt )
			{
				jMenuItemZazhiCalcMouseReleased( evt );
			}
		} );
		jMenu5.add( jMenuItemZazhiCalc );

		jMenuItemLucene.setText( "\u5168\u6587\u68c0\u7d22" );
		jMenuItemLucene.addMouseListener( new java.awt.event.MouseAdapter()
		{
			public void mouseReleased( java.awt.event.MouseEvent evt )
			{
				jMenuItemLuceneMouseReleased( evt );
			}
		} );
		jMenu5.add( jMenuItemLucene );

		jMenuBar1.add( jMenu5 );

		setJMenuBar( jMenuBar1 );

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout( getContentPane() );
		getContentPane().setLayout( layout );
		layout.setHorizontalGroup( layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING ).addComponent( contentsTab , javax.swing.GroupLayout.DEFAULT_SIZE , 782 , Short.MAX_VALUE ) );
		layout.setVerticalGroup( layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING ).addComponent( contentsTab , javax.swing.GroupLayout.DEFAULT_SIZE , 494 , Short.MAX_VALUE ) );

		pack();
	}// </editor-fold>
		// GEN-END:initComponents

	public void enableCompone( int formId , String title , JComponent component )
	{
		if ( appMap.containsKey( formId ) )
		{
			getContentsTab().setSelectedIndex( appMap.get( formId ) );
		}
		else
		{
			getContentsTab().addTab( title , component );
			appIndex ++ ;
			appMap.put( formId , appIndex );
			getContentsTab().setSelectedIndex( appIndex );
		}
	}

	private void jMenuItemLuceneMouseReleased( java.awt.event.MouseEvent evt )
	{
		print( "in jMenuItemLuceneMouseReleased" );
		int formId = 6;
		String title = "全文检索";
		LueneForm lueneForm = new LueneForm();
		enableCompone( formId , title , lueneForm );
	}

	private void jMenuItemZazhiCalcMouseReleased( java.awt.event.MouseEvent evt )
	{
		print( "in jMenuItemZazhiCalcMouseReleased" );

		int formId = 5;
		String title = "计算杂质";
		Zazhicalc zazhicalc = new Zazhicalc();
		enableCompone( formId , title , zazhicalc );
	}

	private void jMenuItemAutoCodeMouseReleased( java.awt.event.MouseEvent evt )
	{
		print( "in jMenuItemAutoCodeMouseClicked" );
		int formId = 4;
		String title = "自动生成代码";
		AutoCode autoCode = new AutoCode();
		enableCompone( formId , title , autoCode );
	}

	private void jMenuItemReader15693MouseReleased( java.awt.event.MouseEvent evt )
	{
		print( "in jMenuItemReader15693MouseReleased" );
		int formId = 2;
		String title = "小白读卡器";
		Reader15693 reader15693 = new Reader15693();
		enableCompone( formId , title , reader15693 );
	}

	private void MenuBtnReceiveMsgMouseReleased( java.awt.event.MouseEvent evt )
	{
		print( "in MenuBtnReceiveMsgMouseReleased" );
		int formId = 0;
		String title = "接收消息";
		ReceiveMsg receiveMsg = new ReceiveMsg();
		enableCompone( formId , title , receiveMsg );
	}

	private void MenuBtnSendMsgMouseReleased( java.awt.event.MouseEvent evt )
	{
		print( "in MenuBtnSendMsgMouseReleased" );
		int formId = 1;
		String title = "发送消息";
		SendMsg sendMsg = new SendMsg();
		enableCompone( formId , title , sendMsg );
	}

	public void print( String s )
	{
		System.out.println( s );
	}

	public javax.swing.JTabbedPane getContentsTab()
	{
		return contentsTab;
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main( String args[] )
	{
		java.awt.EventQueue.invokeLater( new Runnable()
		{
			public void run()
			{
				MainForm main = new MainForm();
				main.setVisible( true );

			}
		} );
	}

	// GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JMenuItem MenuBtnReceiveMsg;

	private javax.swing.JMenuItem MenuBtnSendMsg;

	private javax.swing.JTabbedPane contentsTab;

	private javax.swing.JMenu jMenu1;

	private javax.swing.JMenu jMenu2;

	private javax.swing.JMenu jMenu3;

	private javax.swing.JMenu jMenu4;

	private javax.swing.JMenu jMenu5;

	private javax.swing.JMenuBar jMenuBar1;

	private javax.swing.JMenuItem jMenuItemAutoCode;

	private javax.swing.JMenuItem jMenuItemLucene;

	private javax.swing.JMenuItem jMenuItemReader15693;

	private javax.swing.JMenuItem jMenuItemZazhiCalc;
	// End of variables declaration//GEN-END:variables

}
