/*
 * AutoCode.java
 * Created on __DATE__, __TIME__
 */

package com.metarnet.autocode;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import javax.swing.JFileChooser;
import com.amar.app.autocode.DBType;
import com.amar.app.autocode.GeneralCode;
import com.amar.app.autocode.Parameter;
import com.amar.app.autocode.TableInfo;
import com.metarnet.db.DBTest;
import com.metarnet.tool.Compress;
import com.metarnet.tool.TimeDateUtil;

/**
 * @author __USER__
 */
public class AutoCode extends javax.swing.JPanel
{

	private static final long serialVersionUID = 9176084367184724719L;

	private String [] default_urls = { "jdbc:oracle:thin:@192.168.29.28:1521:TINMS", "jdbc:mysql://127.0.0.1:3306" };

	private String [] databases = { "ORACLE", "MYSQL" };

	private String [] drivers = { "oracle.jdbc.driver.OracleDriver", "com.mysql.jdbc.Driver" };

	/** Creates new form AutoCode */
	public AutoCode()
	{
		initComponents();
		myInit();
	}

	public void myInit()
	{
		jComboBoxDbType.setModel( new javax.swing.DefaultComboBoxModel( databases ) );
	}

	// GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents()
	{

		jLabel1 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		jLabel5 = new javax.swing.JLabel();
		jTextFieldUrl = new javax.swing.JTextField();
		jTextFieldDbName = new javax.swing.JTextField();
		jComboBoxDbType = new javax.swing.JComboBox();
		jTextFieldXmlPath = new javax.swing.JTextField();
		jTextFieldModelPath = new javax.swing.JTextField();
		jLabelDbName = new javax.swing.JLabel();
		jTextFieldPassword = new javax.swing.JTextField();
		jLabel7 = new javax.swing.JLabel();
		jLabel8 = new javax.swing.JLabel();
		jLabel9 = new javax.swing.JLabel();
		jTextFieldDaoPath = new javax.swing.JTextField();
		jButtonGenerate = new javax.swing.JButton();
		jScrollPane1 = new javax.swing.JScrollPane();
		jTextAreaInfo = new javax.swing.JTextArea();
		jTextFieldSavePath = new javax.swing.JTextField();
		jButtonSavePath = new javax.swing.JButton();
		jTextFieldUsername = new javax.swing.JTextField();

		setMaximumSize( new java.awt.Dimension( 800 , 600 ) );

		jLabel1.setText( "\u7c7b\u578b\uff1a" );

		jLabel3.setText( "\u8fde\u63a5\u5730\u5740\uff1a" );

		jLabel4.setText( "\u7528\u6237\u540d\uff1a" );

		jLabel5.setText( "\u5bc6\u7801\uff1a" );

		jTextFieldUrl.setText( "jdbc:oracle:thin:@192.168.29.28:1521:TINMS" );

		jTextFieldDbName.setEnabled( false );

		jComboBoxDbType.setModel( new javax.swing.DefaultComboBoxModel( new String [] { "Item 1", "Item 2", "Item 3", "Item 4" } ) );
		jComboBoxDbType.addItemListener( new java.awt.event.ItemListener()
		{
			public void itemStateChanged( java.awt.event.ItemEvent evt )
			{
				jComboBoxDbTypeItemStateChanged( evt );
			}
		} );

		jTextFieldXmlPath.setText( "mybatis" );

		jTextFieldModelPath.setText( "com/model" );

		jLabelDbName.setText( "\u6570\u636e\u5e93\uff1a" );

		jTextFieldPassword.setText( "ims" );

		jLabel7.setText( "XML\u8def\u5f84\uff1a" );

		jLabel8.setText( "MODEL\u8def\u5f84\uff1a" );

		jLabel9.setText( "DAO\u8def\u5f84\uff1a" );

		jTextFieldDaoPath.setText( "com/dao" );

		jButtonGenerate.setText( "\u751f\u6210\u4ee3\u7801" );
		jButtonGenerate.addMouseListener( new java.awt.event.MouseAdapter()
		{
			public void mouseClicked( java.awt.event.MouseEvent evt )
			{
				jButtonGenerateMouseClicked( evt );
			}
		} );

		jTextAreaInfo.setColumns( 20 );
		jTextAreaInfo.setRows( 5 );
		jScrollPane1.setViewportView( jTextAreaInfo );

		jTextFieldSavePath.setText( "d:/autocode" );

		jButtonSavePath.setText( "\u5b58\u653e\uff1a" );
		jButtonSavePath.addMouseListener( new java.awt.event.MouseAdapter()
		{
			public void mouseClicked( java.awt.event.MouseEvent evt )
			{
				jButtonSavePathMouseClicked( evt );
			}
		} );

		jTextFieldUsername.setText( "ims" );

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout( this );
		this.setLayout( layout );
		layout.setHorizontalGroup( layout
				.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING )
				.addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING ,
						layout.createSequentialGroup()
								.addGroup(
										layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING )
												.addGroup(
														layout.createSequentialGroup()
																.addGap( 19 , 19 , 19 )
																.addGroup(
																		layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING )
																				.addGroup(
																						layout.createSequentialGroup()
																								.addGroup(
																										layout.createParallelGroup( javax.swing.GroupLayout.Alignment.TRAILING ).addComponent( jLabel4 )
																												.addComponent( jLabel1 ).addComponent( jLabel3 ) )
																								.addPreferredGap( javax.swing.LayoutStyle.ComponentPlacement.RELATED )
																								.addGroup(
																										layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING )
																												.addGroup(
																														layout.createSequentialGroup()
																																.addGroup(
																																		layout.createParallelGroup(
																																				javax.swing.GroupLayout.Alignment.LEADING )
																																				.addComponent( jTextFieldUsername ,
																																						javax.swing.GroupLayout.PREFERRED_SIZE , 204 ,
																																						javax.swing.GroupLayout.PREFERRED_SIZE )
																																				.addGroup(
																																						layout.createSequentialGroup()
																																								.addComponent( jComboBoxDbType , 0 ,
																																										204 , Short.MAX_VALUE )
																																								.addPreferredGap(
																																										javax.swing.LayoutStyle.ComponentPlacement.RELATED ) ) )
																																.addGap( 32 , 32 , 32 )
																																.addGroup(
																																		layout.createParallelGroup(
																																				javax.swing.GroupLayout.Alignment.LEADING , false )
																																				.addGroup(
																																						layout.createSequentialGroup()
																																								.addComponent( jButtonSavePath )
																																								.addPreferredGap(
																																										javax.swing.LayoutStyle.ComponentPlacement.RELATED ,
																																										javax.swing.GroupLayout.DEFAULT_SIZE ,
																																										Short.MAX_VALUE )
																																								.addComponent(
																																										jTextFieldSavePath ,
																																										javax.swing.GroupLayout.PREFERRED_SIZE ,
																																										220 ,
																																										javax.swing.GroupLayout.PREFERRED_SIZE ) )
																																				.addGroup(
																																						javax.swing.GroupLayout.Alignment.TRAILING ,
																																						layout.createSequentialGroup()
																																								.addGap( 21 , 21 , 21 )
																																								.addGroup(
																																										layout.createParallelGroup(
																																												javax.swing.GroupLayout.Alignment.TRAILING )
																																												.addComponent(
																																														jLabelDbName )
																																												.addComponent( jLabel5 ) )
																																								.addGap( 22 , 22 , 22 )
																																								.addGroup(
																																										layout.createParallelGroup(
																																												javax.swing.GroupLayout.Alignment.LEADING )
																																												.addComponent(
																																														jTextFieldDbName ,
																																														javax.swing.GroupLayout.PREFERRED_SIZE ,
																																														220 ,
																																														javax.swing.GroupLayout.PREFERRED_SIZE )
																																												.addComponent(
																																														jTextFieldPassword ,
																																														javax.swing.GroupLayout.Alignment.TRAILING ,
																																														javax.swing.GroupLayout.PREFERRED_SIZE ,
																																														220 ,
																																														javax.swing.GroupLayout.PREFERRED_SIZE ) ) ) ) )
																												.addComponent( jTextFieldUrl , javax.swing.GroupLayout.DEFAULT_SIZE , 547 ,
																														Short.MAX_VALUE ) ) )
																				.addGroup(
																						layout.createSequentialGroup().addPreferredGap( javax.swing.LayoutStyle.ComponentPlacement.RELATED )
																								.addComponent( jLabel7 ).addPreferredGap( javax.swing.LayoutStyle.ComponentPlacement.RELATED )
																								.addComponent( jTextFieldXmlPath , javax.swing.GroupLayout.DEFAULT_SIZE , 202 , Short.MAX_VALUE )
																								.addGap( 27 , 27 , 27 ).addComponent( jLabel8 ).addGap( 16 , 16 , 16 )
																								.addComponent( jTextFieldModelPath , javax.swing.GroupLayout.DEFAULT_SIZE , 220 , Short.MAX_VALUE ) ) )
																.addGap( 0 , 0 , 0 ) )
												.addGroup(
														layout.createSequentialGroup()
																.addContainerGap()
																.addComponent( jLabel9 )
																.addPreferredGap( javax.swing.LayoutStyle.ComponentPlacement.UNRELATED )
																.addGroup(
																		layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING )
																				.addComponent( jScrollPane1 , javax.swing.GroupLayout.PREFERRED_SIZE , 521 , javax.swing.GroupLayout.PREFERRED_SIZE )
																				.addComponent( jTextFieldDaoPath , javax.swing.GroupLayout.PREFERRED_SIZE , 202 ,
																						javax.swing.GroupLayout.PREFERRED_SIZE ) ).addGap( 23 , 23 , 23 ) ) ).addGap( 66 , 66 , 66 ) )
				.addGroup( layout.createSequentialGroup().addGap( 464 , 464 , 464 ).addComponent( jButtonGenerate ).addContainerGap( 152 , Short.MAX_VALUE ) ) );
		layout.setVerticalGroup( layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING ).addGroup(
				layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								layout.createParallelGroup( javax.swing.GroupLayout.Alignment.BASELINE ).addComponent( jLabel3 )
										.addComponent( jTextFieldUrl , javax.swing.GroupLayout.PREFERRED_SIZE , javax.swing.GroupLayout.DEFAULT_SIZE , javax.swing.GroupLayout.PREFERRED_SIZE ) )
						.addGroup(
								layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING )
										.addGroup(
												layout.createSequentialGroup()
														.addGap( 16 , 16 , 16 )
														.addGroup(
																layout.createParallelGroup( javax.swing.GroupLayout.Alignment.BASELINE )
																		.addComponent( jLabel1 )
																		.addComponent( jComboBoxDbType , javax.swing.GroupLayout.PREFERRED_SIZE , javax.swing.GroupLayout.DEFAULT_SIZE ,
																				javax.swing.GroupLayout.PREFERRED_SIZE ) )
														.addGroup(
																layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING )
																		.addGroup(
																				layout.createSequentialGroup().addPreferredGap( javax.swing.LayoutStyle.ComponentPlacement.RELATED )
																						.addComponent( jLabel4 ) )
																		.addGroup(
																				layout.createSequentialGroup()
																						.addGap( 9 , 9 , 9 )
																						.addComponent( jTextFieldUsername , javax.swing.GroupLayout.PREFERRED_SIZE ,
																								javax.swing.GroupLayout.DEFAULT_SIZE , javax.swing.GroupLayout.PREFERRED_SIZE ) ) ) )
										.addGroup(
												layout.createSequentialGroup()
														.addGap( 18 , 18 , 18 )
														.addGroup(
																layout.createParallelGroup( javax.swing.GroupLayout.Alignment.BASELINE )
																		.addComponent( jLabelDbName )
																		.addComponent( jTextFieldDbName , javax.swing.GroupLayout.PREFERRED_SIZE , javax.swing.GroupLayout.DEFAULT_SIZE ,
																				javax.swing.GroupLayout.PREFERRED_SIZE ) )
														.addPreferredGap( javax.swing.LayoutStyle.ComponentPlacement.UNRELATED )
														.addGroup(
																layout.createParallelGroup( javax.swing.GroupLayout.Alignment.BASELINE )
																		.addComponent( jTextFieldPassword , javax.swing.GroupLayout.PREFERRED_SIZE , javax.swing.GroupLayout.DEFAULT_SIZE ,
																				javax.swing.GroupLayout.PREFERRED_SIZE ).addComponent( jLabel5 ) ) ) )
						.addGap( 7 , 7 , 7 )
						.addGroup(
								layout.createParallelGroup( javax.swing.GroupLayout.Alignment.BASELINE )
										.addComponent( jLabel7 , javax.swing.GroupLayout.PREFERRED_SIZE , 17 , javax.swing.GroupLayout.PREFERRED_SIZE ).addComponent( jLabel8 )
										.addComponent( jTextFieldModelPath , javax.swing.GroupLayout.PREFERRED_SIZE , javax.swing.GroupLayout.DEFAULT_SIZE , javax.swing.GroupLayout.PREFERRED_SIZE )
										.addComponent( jTextFieldXmlPath , javax.swing.GroupLayout.PREFERRED_SIZE , javax.swing.GroupLayout.DEFAULT_SIZE , javax.swing.GroupLayout.PREFERRED_SIZE ) )
						.addPreferredGap( javax.swing.LayoutStyle.ComponentPlacement.RELATED )
						.addGroup(
								layout.createParallelGroup( javax.swing.GroupLayout.Alignment.BASELINE ).addComponent( jLabel9 ).addComponent( jButtonSavePath )
										.addComponent( jTextFieldSavePath , javax.swing.GroupLayout.PREFERRED_SIZE , javax.swing.GroupLayout.DEFAULT_SIZE , javax.swing.GroupLayout.PREFERRED_SIZE )
										.addComponent( jTextFieldDaoPath , javax.swing.GroupLayout.PREFERRED_SIZE , javax.swing.GroupLayout.DEFAULT_SIZE , javax.swing.GroupLayout.PREFERRED_SIZE ) )
						.addPreferredGap( javax.swing.LayoutStyle.ComponentPlacement.RELATED ).addComponent( jButtonGenerate ).addGap( 18 , 18 , 18 )
						.addComponent( jScrollPane1 , javax.swing.GroupLayout.PREFERRED_SIZE , 155 , javax.swing.GroupLayout.PREFERRED_SIZE ).addContainerGap( 116 , Short.MAX_VALUE ) ) );
	}// </editor-fold>
		// GEN-END:initComponents

	private void jComboBoxDbTypeItemStateChanged( java.awt.event.ItemEvent evt )
	{
		print( "in jComboBoxDbTypeItemStateChanged" );
		String dbType = jComboBoxDbType.getSelectedItem().toString();

		if ( dbType.equals( databases[ 0 ] ) )
		{
			jTextFieldUrl.setText( default_urls[ 0 ] );
			jTextFieldDbName.setEnabled( false );
			jLabelDbName.setEnabled( false );
		}
		else if ( dbType.equals( databases[ 1 ] ) )
		{
			jTextFieldUrl.setText( default_urls[ 1 ] );
			jTextFieldDbName.setEnabled( true );
			jLabelDbName.setEnabled( true );
		}
	}

	private void jButtonSavePathMouseClicked( java.awt.event.MouseEvent evt )
	{
		print( "in jButtonSavePathMouseClicked" );

		String savepath = jTextFieldSavePath.getText();

		JFileChooser jFileChooser1 = new JFileChooser();
		jFileChooser1.setCurrentDirectory( new File( savepath ) );
		jFileChooser1.setDialogTitle( "保存文件" );
		jFileChooser1.setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY );

		int ch = jFileChooser1.showDialog( this , "保存文件" );
		if ( ch == JFileChooser.APPROVE_OPTION )
		{
			jTextFieldSavePath.setText( jFileChooser1.getSelectedFile().getPath() );
		}
	}

	private void jButtonGenerateMouseClicked( java.awt.event.MouseEvent evt )
	{
		print( "in jButtonGenerateMouseClicked" );

		long start = System.currentTimeMillis();
		String username = jTextFieldUsername.getText();
		String password = jTextFieldPassword.getText();
		String xmlPath = jTextFieldXmlPath.getText();
		String url = jTextFieldUrl.getText();
		String dbName = jTextFieldDbName.getText();
		String daoPath = jTextFieldDaoPath.getText();
		String savepath = jTextFieldSavePath.getText();
		String dbType = jComboBoxDbType.getSelectedItem().toString();
		String modelPath = jTextFieldModelPath.getText();

		DBTest dbTest = new DBTest();

		String dbDriver = "";
		DBType dbtype = null;
		if ( dbType.equals( databases[ 0 ] ) )
		{
			dbDriver = drivers[ 0 ];
			dbtype = DBType.ORACLE;
		}
		else if ( dbType.equals( databases[ 1 ] ) )
		{
			dbDriver = drivers[ 1 ];
			dbtype = DBType.MYSQL;
		}

		try
		{
			List<TableInfo> tableInfoList = null;
			if ( databases[ 0 ].equals( dbType ) )
			{
				tableInfoList = dbTest.getOracleTableInfo( dbDriver , url , username , password );
			}
			else if ( databases[ 1 ].equals( dbType ) )
			{
				tableInfoList = dbTest.getMysqlTableInfo( dbDriver , url , username , password , dbName );
			}

			Parameter para = new Parameter();
			para.setDaoPath( daoPath );
			para.setModelPath( modelPath );
			para.setTableInfoList( tableInfoList );
			para.setDbname( dbName );
			para.setConfigPath( xmlPath );
			para.setPath( savepath );
			GeneralCode generalCode = new GeneralCode( dbtype );
			generalCode.general( para );

			String codeZipName = "autocode_" + ( new Date().getTime() ) + ".zip";
			Compress compress = new Compress();
			compress.zip( para.getPath() , para.getPath() + File.separatorChar + "/" + codeZipName );

			long end = System.currentTimeMillis();

			String oldData = jTextAreaInfo.getText();
			if ( ! oldData.equals( "" ) )
			{
				oldData = oldData + "\n\n";
			}

			jTextAreaInfo.setText( oldData + TimeDateUtil.getDateTime( new Date() ) + "\n代码生成成功，用时" + ( end - start ) + "毫秒" );

			jTextAreaInfo.setCaretPosition( jTextAreaInfo.getDocument().getLength() );
		}
		catch ( SQLException e )
		{
			e.printStackTrace();
			setError( e.getMessage() );
		}
		catch ( ClassNotFoundException e )
		{
			e.printStackTrace();
			setError( e.getMessage() );
		}
		catch ( IOException e )
		{
			e.printStackTrace();
			setError( e.getMessage() );
		}
	}

	public void setError( String errorInfo )
	{
		String s = jTextAreaInfo.getText();
		jTextAreaInfo.setText( s + "\n" + "出错了\n" + errorInfo );
	}

	public void print( String s )
	{
		System.out.println( s );
	}

	// GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton jButtonGenerate;

	private javax.swing.JButton jButtonSavePath;

	private javax.swing.JComboBox jComboBoxDbType;

	private javax.swing.JLabel jLabel1;

	private javax.swing.JLabel jLabel3;

	private javax.swing.JLabel jLabel4;

	private javax.swing.JLabel jLabel5;

	private javax.swing.JLabel jLabel7;

	private javax.swing.JLabel jLabel8;

	private javax.swing.JLabel jLabel9;

	private javax.swing.JLabel jLabelDbName;

	private javax.swing.JScrollPane jScrollPane1;

	private javax.swing.JTextArea jTextAreaInfo;

	private javax.swing.JTextField jTextFieldDaoPath;

	private javax.swing.JTextField jTextFieldDbName;

	private javax.swing.JTextField jTextFieldModelPath;

	private javax.swing.JTextField jTextFieldPassword;

	private javax.swing.JTextField jTextFieldSavePath;

	private javax.swing.JTextField jTextFieldUrl;

	private javax.swing.JTextField jTextFieldUsername;

	private javax.swing.JTextField jTextFieldXmlPath;
	// End of variables declaration//GEN-END:variables

}
