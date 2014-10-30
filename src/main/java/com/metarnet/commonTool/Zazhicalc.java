/*
 * Zazhicalc.java
 * Created on __DATE__, __TIME__
 */

package com.metarnet.commonTool;

import java.text.DecimalFormat;

/**
 * @author __USER__
 */
public class Zazhicalc extends javax.swing.JPanel
{

	private static final long serialVersionUID = - 2035380318123406259L;

	/** Creates new form Zazhicalc */
	public Zazhicalc()
	{
		initComponents();
	}

	// GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents()
	{

		jTextFieldMaozhong = new javax.swing.JTextField();
		jTextFieldJinzhong = new javax.swing.JTextField();
		jTextFieldZazhi = new javax.swing.JTextField();
		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		jButtonCalc = new javax.swing.JButton();

		jLabel1.setText( "\u51c0\u91cd\uff1a" );

		jLabel2.setText( "\u6bdb\u91cd\uff1a" );

		jLabel3.setText( "\u6742\u8d28\uff1a" );

		jButtonCalc.setText( "\u8ba1\u7b97" );
		jButtonCalc.addMouseListener( new java.awt.event.MouseAdapter()
		{
			public void mouseClicked( java.awt.event.MouseEvent evt )
			{
				jButtonCalcMouseClicked( evt );
			}
		} );

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout( this );
		this.setLayout( layout );
		layout.setHorizontalGroup( layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING ).addGroup(
				layout.createSequentialGroup()
						.addGap( 60 , 60 , 60 )
						.addGroup(
								layout.createParallelGroup( javax.swing.GroupLayout.Alignment.TRAILING )
										.addComponent( jButtonCalc )
										.addGroup(
												layout.createSequentialGroup()
														.addGroup(
																layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING )
																		.addGroup(
																				layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING )
																						.addGroup( javax.swing.GroupLayout.Alignment.TRAILING ,
																								layout.createSequentialGroup().addComponent( jLabel2 ).addGap( 18 , 18 , 18 ) )
																						.addGroup( layout.createSequentialGroup().addComponent( jLabel1 ).addGap( 20 , 20 , 20 ) ) )
																		.addComponent( jLabel3 ) )
														.addGap( 18 , 18 , 18 )
														.addGroup(
																layout.createParallelGroup( javax.swing.GroupLayout.Alignment.TRAILING , false )
																		.addComponent( jTextFieldJinzhong , javax.swing.GroupLayout.Alignment.LEADING )
																		.addComponent( jTextFieldMaozhong , javax.swing.GroupLayout.Alignment.LEADING , javax.swing.GroupLayout.DEFAULT_SIZE , 148 ,
																				Short.MAX_VALUE ).addComponent( jTextFieldZazhi ) )
														.addPreferredGap( javax.swing.LayoutStyle.ComponentPlacement.RELATED ) ) ).addContainerGap( 118 , Short.MAX_VALUE ) ) );
		layout.setVerticalGroup( layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING ).addGroup(
				layout.createSequentialGroup()
						.addContainerGap( 49 , Short.MAX_VALUE )
						.addGroup(
								layout.createParallelGroup( javax.swing.GroupLayout.Alignment.BASELINE ).addComponent( jLabel2 )
										.addComponent( jTextFieldMaozhong , javax.swing.GroupLayout.PREFERRED_SIZE , javax.swing.GroupLayout.DEFAULT_SIZE , javax.swing.GroupLayout.PREFERRED_SIZE ) )
						.addGap( 26 , 26 , 26 )
						.addGroup(
								layout.createParallelGroup( javax.swing.GroupLayout.Alignment.BASELINE ).addComponent( jLabel1 )
										.addComponent( jTextFieldJinzhong , javax.swing.GroupLayout.PREFERRED_SIZE , javax.swing.GroupLayout.DEFAULT_SIZE , javax.swing.GroupLayout.PREFERRED_SIZE ) )
						.addGap( 18 , 18 , 18 )
						.addGroup(
								layout.createParallelGroup( javax.swing.GroupLayout.Alignment.BASELINE ).addComponent( jLabel3 )
										.addComponent( jTextFieldZazhi , javax.swing.GroupLayout.PREFERRED_SIZE , javax.swing.GroupLayout.DEFAULT_SIZE , javax.swing.GroupLayout.PREFERRED_SIZE ) )
						.addGap( 36 , 36 , 36 ).addComponent( jButtonCalc ).addContainerGap( 77 , Short.MAX_VALUE ) ) );
	}// </editor-fold>
		// GEN-END:initComponents

	private void jButtonCalcMouseClicked( java.awt.event.MouseEvent evt )
	{
		String maozhongStr = jTextFieldMaozhong.getText();
		String jinzhongStr = jTextFieldJinzhong.getText();

		float maozhong = 0;
		float jinzhong = 0;

		if ( ! maozhongStr.equals( "" ) )
		{
			maozhong = Float.parseFloat( maozhongStr );
		}

		if ( ! jinzhongStr.equals( "" ) )
		{
			jinzhong = Float.parseFloat( jinzhongStr );
		}

		float re = 0;
		if ( maozhong != 0 )
		{
			re = ( maozhong - jinzhong ) * 100 / maozhong;
		}
		String parten = "#.##";
		DecimalFormat decimal = new DecimalFormat(parten);
		String str= decimal.format(re);
		
		jTextFieldZazhi.setText( "" + str );
	}

	// GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton jButtonCalc;

	private javax.swing.JLabel jLabel1;

	private javax.swing.JLabel jLabel2;

	private javax.swing.JLabel jLabel3;

	private javax.swing.JTextField jTextFieldJinzhong;

	private javax.swing.JTextField jTextFieldMaozhong;

	private javax.swing.JTextField jTextFieldZazhi;
	// End of variables declaration//GEN-END:variables

}
