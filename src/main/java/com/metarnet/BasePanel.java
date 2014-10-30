package com.metarnet;

import java.io.File;

import javax.swing.JOptionPane;

@SuppressWarnings( "serial" )
public class BasePanel extends javax.swing.JPanel
{
	public void myInit()
	{
		
	}
	
	public String nullToEmpty( Object s )
	{
		return s == null ? "" : s.toString();
	}

	public void showInfo( String info )
	{
		JOptionPane.showMessageDialog( this , info );
	}
	
	public void print( String s )
	{
		System.out.println( s );
	}

	public boolean checkDirExist(String path)
	{
		File file = new File( path );
		return file.exists();
	}
	public void makeDirExist( String path )
	{
		File file = new File( path );
		if ( ! file.exists() )
		{
			file.mkdirs();
		}
	}
}
