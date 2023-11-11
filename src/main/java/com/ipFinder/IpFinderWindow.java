package com.ipFinder;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.JToolBar;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.LineBorder;
import java.awt.Component;

/**
 * Window class extending JFrame
 *
 */
public class IpFinderWindow extends JFrame 
{
    private static final long serialVersionUID = 8808856066736997233L;
    
    final static String STR_APP_TITLE = "Ip finder";
    private static IpFinderWindow m_oIpFinderInstance;
    private static Dimension WINDOW_APP_SIZE = new Dimension(600,400);
    private JTextField oUrlTextField;
    private JTable oTable;

    
    

	public static void main( String[] args ) throws UnsupportedLookAndFeelException
    {
		UIManager.setLookAndFeel(new NimbusLookAndFeel());
		
		IpFinderWindow oIpFinderWindow = IpFinderWindow.getinstance();
		Image icon = Toolkit.getDefaultToolkit().getImage("icons/about.png");    
		oIpFinderWindow.setIconImage(icon);
		oIpFinderWindow.setVisible(true);
    }
	
	/**
	 * get instance of window 
	 * @return IpFinderWindow
	 */
	public static IpFinderWindow getinstance() {
		if(null == m_oIpFinderInstance) {
			m_oIpFinderInstance = new IpFinderWindow(WINDOW_APP_SIZE);
		}
		return m_oIpFinderInstance;
	}
	
	IpFinderWindow(Dimension iODimension){
		setResizable(false);
		WINDOW_APP_SIZE = iODimension;
		this.setSize(new Dimension(400, 200));
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		JToolBar toolBar = new JToolBar();
		getContentPane().add(toolBar, BorderLayout.NORTH);
		
		oUrlTextField = new JTextField();
		oUrlTextField.setToolTipText("enter url for protocol or only domain for others");
		toolBar.add(oUrlTextField);
		oUrlTextField.setColumns(20);
		
		JButton btnOk = new JButton("Ok");
		btnOk.setForeground(new Color(38, 162, 105));
		btnOk.setBackground(new Color(51, 209, 122));
		
		btnOk.addActionListener(this::updateViewData);
		toolBar.add(btnOk);
		
		oTable = new JTable();
		oTable.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		oTable.setAlignmentX(Component.RIGHT_ALIGNMENT);
		oTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		oTable.setSurrendersFocusOnKeystroke(true);
		oTable.setShowHorizontalLines(true);
		oTable.setShowVerticalLines(true);
		oTable.setModel(new DefaultTableModel(
			new Object[][] {
				{"Ip Address", ""},
				{"Host name", ""},
				{"Protocol", ""},
			},
			new String[] {
				"Data Types", "Values"
			}
		));
		oTable.setColumnSelectionAllowed(true);
		oTable.setCellSelectionEnabled(true);
		getContentPane().add(oTable, BorderLayout.CENTER);
		
		
	}
	
	/**
	 * this method allow to search data from domain or from full url with protocol
	 * @param arg0
	 */
	public void updateViewData(ActionEvent arg0) {
		//reset table content
		oTable.setModel(new DefaultTableModel(
				new Object[][] {
					{"Ip Address", ""},
					{"Host name", ""},
					{"Protocol", ""},
				},
				new String[] {
					"Data Types", "Values"
				}
			));
		//update table with new data
		String strUrl = oUrlTextField.getText();
		String strDomain = strUrl;
		String []lstUrlParts = strUrl.split("//"); 
		if(lstUrlParts.length>1) {
			strDomain = lstUrlParts[1].split("/")[0];
		}
		DefaultTableModel oDefaultTableModel = new DefaultTableModel(
				
				
				new Object[][] {
					{"Ip Address", UrlDataManager.getIp(strDomain)},
					{"Host name", UrlDataManager.getHostName(strDomain)},
					{"Protocol", UrlDataManager.getProtocol(strUrl)},
				},
				new String[] {
					"Data Types", "Values"
				}
			);
		oTable.setModel(oDefaultTableModel);
	}
	
	
	
}
