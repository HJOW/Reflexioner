package com.hjow.game.reflexioner.mainClasses;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.zip.GZIPInputStream;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import com.hjow.game.reflexioner.lang.Language;
import com.hjow.game.reflexioner.pack.SecuredDist;
import com.hjow.game.reflexioner.reflexioner.Reflexioner;
import com.hjow.game.reflexioner.setting.Setting;

public class Code_Checker implements ActionListener, WindowListener, MouseListener, MouseMotionListener, Openable
{
	boolean independence = false;
	boolean gui = false;
	private JDialog checker_dialog;
	private InputStreamReader inread;
	private BufferedReader bfreader;
	boolean while_loop = true;
	Setting setting;
	private JPanel mainPanel;
	private JPanel centerPanel;
	private JPanel upPanel;
	private JPanel downPanel;
	private JPanel titlePanel;
	private JLabel title;
	private int mousex;
	private int mousey;
	private JPanel inputPanel;
	private JLabel inputLabel;
	private JTextArea ta;
	private JPanel[] inputPns;
	private JTextField inputField;
	private JButton checkButton;
	private JButton closeButton;
	private JPanel contentPanel;
	
	public Code_Checker(boolean independence)
	{
		this.gui = false;
		this.independence = independence;		
	}
	public Code_Checker(boolean independence, Setting setting)
	{
		this.gui = true;
		this.independence = independence;
		this.setting = setting.clone();
		init_frame();
	}
	public Code_Checker(boolean independence, JDialog upper, Setting setting)
	{
		this.gui = true;
		this.independence = independence;
		this.setting = setting.clone();
		init_frame(upper);
	}
	public Code_Checker(boolean independence, Frame upper, Setting setting)
	{
		this.gui = true;
		this.independence = independence;
		this.setting = setting.clone();
		init_frame(upper);
	}
	public Code_Checker(boolean independence, Dialog upper, Setting setting)
	{
		this.gui = true;
		this.independence = independence;
		this.setting = setting.clone();
		init_frame(upper);
	}
	public Properties check_code(String code)
	{
		GZIPInputStream gzipper = null;
		try
		{
			byte[] codeBytes = RXUtils.hexBytes(code);
			ByteArrayInputStream coll = new ByteArrayInputStream(codeBytes);
			Properties resultProp = new Properties();
			
			ByteArrayOutputStream collx = new ByteArrayOutputStream();
			
			gzipper = new GZIPInputStream(coll);
			coll = null;
			
			byte[] buffer = new byte[1024];
			int reads;
			while(true)
			{
				reads = gzipper.read(buffer, 0, buffer.length);
				if(reads < 0) break;
				collx.write(buffer, 0, reads);
			}
			gzipper.close(); gzipper = null;
			
			coll = new ByteArrayInputStream(collx.toByteArray());
			collx = null;
			
			resultProp.loadFromXML(coll);
			coll = null;
			code = null;
			
			return resultProp;
		} 
		catch (NumberFormatException e)
		{
			return null;
		}
		catch (ArrayIndexOutOfBoundsException e)
		{
			return null;
		}		
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
		finally
		{
			if(gzipper != null) { try { gzipper.close(); } catch(Exception ex) {} }
		}
	}
	public void open()
	{
		if(gui) checker_dialog.setVisible(true);
		else
		{
			inread = new InputStreamReader(System.in);
			bfreader = new BufferedReader(inread);
			while(while_loop)
			{
				int input = 0;				
				String input_code = "";
				System.out.println(setting.getLang().getText(Language.CODE_CHECKER));
				System.out.println();
				System.out.println("-----------------------------------------");
				System.out.println(setting.getLang().getText(Language.MENU));
				System.out.println("1. " + setting.getLang().getText(Language.CHECK));
				System.out.println("2. " + setting.getLang().getText(Language.EXIT));
				System.out.println("-----------------------------------------");
				System.out.print(setting.getLang().getText(Language.INPUT) + " : ");
				try
				{
					input = Integer.parseInt(bfreader.readLine());
					switch(input)
					{
						case 1:
							System.out.print(setting.getLang().getText(Language.AUTHORITY) + " " + setting.getLang().getText(Language.INPUT) + " : ");
							input_code = bfreader.readLine();
							Properties result = check_code(input_code);
							check_refresh(result);
							System.out.println("-----------------------------------------");
							break;
						case 2:
							exit();
							break;							
					}
				} 
				catch (IOException e)
				{
					e.printStackTrace();
					continue;
				}
				catch(NullPointerException e)
				{
					System.out.println(setting.getLang().getText(Language.DESCRIPTIONS + 20));
					continue;
				}
				catch(NumberFormatException e)
				{
					System.out.println(setting.getLang().getText(Language.DESCRIPTIONS + 20));
					continue;
				}				
			}
		}
	}
	public void exit()
	{
		if(independence) System.exit(0);
		else
		{
			if(gui)
			{
				checker_dialog.setVisible(false);
			}
			else
			{
				while_loop = false;
				try
				{
					inread.close();
					bfreader.close();
				} 
				catch (Exception e)
				{
					
				}
			}
		}
	}
	public void init_frame()
	{
		checker_dialog = new JDialog();
		init_other();
	}
	public void init_frame(JDialog upper)
	{
		if(upper != null) checker_dialog = new JDialog(upper, false);
		else  checker_dialog = new JDialog();
		init_other();
	}
	public void init_frame(Dialog upper)
	{
		if(upper != null) checker_dialog = new JDialog(upper, false);
		else  checker_dialog = new JDialog();
		init_other();
	}
	public void init_frame(Frame upper)
	{
		if(upper != null) checker_dialog = new JDialog(upper, false);
		else  checker_dialog = new JDialog();
		init_other();
	}
	public Window getWindow()
	{
		return checker_dialog;
	}
	public void init_other()
	{
		if(Reflexioner.usingFont == null)
			Reflexioner.prepareFont();
		checker_dialog.setSize(500, 400);
		checker_dialog.setLocation((int) (setting.getScreenSize().getWidth()/2 - checker_dialog.getWidth()/2), (int) (setting.getScreenSize().getHeight()/2 - checker_dialog.getHeight()/2));
		checker_dialog.setUndecorated(setting.isUse_own_titleBar());
		if(! setting.isUse_own_titleBar()) checker_dialog.addWindowListener(this);
		mainPanel = new JPanel();
		checker_dialog.getContentPane().setLayout(new BorderLayout());
		checker_dialog.getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setBorder(new EtchedBorder());
		
		centerPanel = new JPanel();
		upPanel = new JPanel();
		downPanel = new JPanel();
		
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		mainPanel.add(upPanel, BorderLayout.NORTH);
		mainPanel.add(downPanel, BorderLayout.SOUTH);
		
		mainPanel.setBackground(setting.getSelected_back());
		
		upPanel.setLayout(new BorderLayout());
		upPanel.setBackground(setting.getSelected_back());
		
		titlePanel = new JPanel();
		title = new JLabel(setting.getLang().getText(Language.CODE_CHECKER));
		if(Reflexioner.usingFont != null)
			title.setFont(Reflexioner.usingFont);
		if(setting.isUse_own_titleBar())
		{
			upPanel.add(titlePanel, BorderLayout.NORTH);
			titlePanel.addMouseListener(this);
			titlePanel.addMouseMotionListener(this);
			titlePanel.setBorder(new EtchedBorder());
			titlePanel.setBackground(setting.getSelected_inside_back());
			title.setForeground(setting.getSelected_fore());
			titlePanel.setLayout(new FlowLayout());
			titlePanel.add(title);
		}
		
		inputPanel = new JPanel();
		upPanel.add(inputPanel, BorderLayout.CENTER);
		inputPanel.setBackground(setting.getSelected_back());
		inputPns = new JPanel[2];
		inputPanel.setLayout(new GridLayout(inputPns.length, 1));
		for(int i=0; i<inputPns.length; i++)
		{
			inputPns[i] = new JPanel();
			inputPns[i].setBackground(setting.getSelected_back());
			inputPns[i].setLayout(new FlowLayout());
			inputPanel.add(inputPns[i]);
		}
		inputLabel = new JLabel(setting.getLang().getText(Language.DESCRIPTIONS + 19));
		if(Reflexioner.usingFont != null)
			inputLabel.setFont(Reflexioner.usingFont);
		inputLabel.setForeground(setting.getSelected_fore());
		inputPns[0].add(inputLabel);
		inputField = new JTextField(35);
		if(Reflexioner.usingFont != null)
			inputField.setFont(Reflexioner.usingFont);
		inputField.setForeground(setting.getSelected_fore());
		inputField.setBackground(setting.getSelected_inside_back());
		inputPns[1].add(inputField);
		checkButton = new JButton(setting.getLang().getText(Language.CHECK));
		if(Reflexioner.usingFont != null)
			checkButton.setFont(Reflexioner.usingFont);
		checkButton.addActionListener(this);
		checkButton.setForeground(setting.getSelected_fore());
		inputPns[1].add(checkButton);
		
		downPanel.setLayout(new FlowLayout());
		downPanel.setBackground(setting.getSelected_back());
		closeButton = new JButton(setting.getLang().getText(Language.CLOSE));
		if(Reflexioner.usingFont != null)
			closeButton.setFont(Reflexioner.usingFont);
		closeButton.addActionListener(this);
		closeButton.setForeground(setting.getSelected_fore());
		downPanel.add(closeButton);
		
		centerPanel.setBackground(setting.getSelected_back());
		centerPanel.setLayout(new BorderLayout());
		contentPanel = new JPanel();
		contentPanel.setBorder(new EtchedBorder());
		contentPanel.setBackground(setting.getSelected_back());
		centerPanel.add(contentPanel, BorderLayout.CENTER);
		
		ta = new JTextArea();
		ta.setEditable(false);
		contentPanel.setLayout(new BorderLayout());
		contentPanel.add(new JScrollPane(ta), BorderLayout.CENTER);
		
		ta.setBackground(setting.getSelected_inside_back());
		ta.setForeground(setting.getSelected_fore());
	}
	public void check_refresh(Properties result)
	{
		StringBuilder report = new StringBuilder("");
		if(result == null)
		{
			report = report.append("No results");
		}
		else
		{
			report = report.append("Results").append("\n\n");
			report = report.append("Name"           ).append("\t: ").append(result.getProperty("Name"           )).append("\n");
			report = report.append("Scenario"       ).append("\t: ").append(result.getProperty("Scenario"       )).append("\n");
			report = report.append("Difficulty"     ).append("\t: ").append(result.getProperty("Difficulty"     )).append("\n");
			report = report.append("Ship"           ).append("\t: ").append(result.getProperty("Ship"           )).append("\n");
			report = report.append("Point"          ).append("\t: ").append(result.getProperty("Point"          )).append("\n");
			report = report.append("PlayDate"       ).append("\t: ").append(result.getProperty("PlayDate"       )).append("\n");
			report = report.append("Version"        ).append("\t: ").append(result.getProperty("Version"        )).append("\n");
			report = report.append("Auto"           ).append("\t: ").append(result.getProperty("Auto"           )).append("\n");
			report = report.append("TodayEvent"     ).append("\t: ").append(result.getProperty("TodayEvent"     )).append("\n");
			
			String code1 = result.getProperty("Code1");
		    result.remove("Code1");
			
			boolean authRes = false;
			
			try
			{
				Set<String> keys = result.stringPropertyNames();
				List<String> keyList = new ArrayList<String>();
				keyList.addAll(keys);
				keys = null;
				Collections.sort(keyList);
				
				StringBuilder code1Creator = new StringBuilder("");
				for(String k : keyList)
				{
					code1Creator = code1Creator.append(k).append(":").append(result.getProperty(k)).append("|");
				}
				keyList = null;
			    
				SecuredDist sp = new SecuredDist();
				byte[] code1bin = (sp.getLeftPad() + code1Creator.toString() + sp.getRightPad()).getBytes("UTF-8");
				code1bin = RXUtils.hash(code1bin);
				
			    String code1re = RXUtils.hexString(code1bin);
			    authRes = code1.equals(code1re);
			}
			catch(Exception ex)
			{
				authRes = false;
				report = report.append(setting.getLang().getText(Language.AUTHORITY) + " ERROR - " + ex.getMessage());
			}
			
			if(authRes) report = report.append(setting.getLang().getText(Language.AUTHORITY) + " " + setting.getLang().getText(Language.COMPLETE)).append("\n");
			else        report = report.append(setting.getLang().getText(Language.AUTHORITY) + " " + setting.getLang().getText(Language.FAIL)).append("\n");
			
			report = report.append("\n");
			report = report.append("Details").append("\n\n");
			report = report.append("ShipKey"        ).append("\t: ").append(result.getProperty("ShipKey"        )).append("\n");
			report = report.append("ShipCode"       ).append("\t: ").append(result.getProperty("ShipCode"       )).append("\n");
			report = report.append("DifficultyValue").append("\t: ").append(result.getProperty("DifficultyValue")).append("\n");
			report = report.append("PlayDateValue"  ).append("\t: ").append(result.getProperty("PlayDateValue"  )).append("\n");
			report = report.append("Version1"       ).append("\t: ").append(result.getProperty("Version1"       )).append("\n");
			report = report.append("Version2"       ).append("\t: ").append(result.getProperty("Version2"       )).append("\n");
			report = report.append("Version3"       ).append("\t: ").append(result.getProperty("Version3"       )).append("\n");
			report = report.append("Code1"          ).append("\t: ").append(code1).append("\n");
		}
		
		
		if(gui)
		{
			ta.setText(report.toString().trim());
			ta.setCaretPosition(0);
		}
		else
		{
			System.out.println(report.toString().trim());
		}
	}
	@Override
	public void mouseDragged(MouseEvent e)
	{
		checker_dialog.setLocation(e.getXOnScreen() - mousex, e.getYOnScreen() - mousey);	
		
	}
	@Override
	public void mouseMoved(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseClicked(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e)
	{
		mousex = e.getX();
		mousey = e.getY();
		
	}
	@Override
	public void mouseReleased(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowActivated(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosed(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosing(WindowEvent e)
	{
		exit();
		
	}
	@Override
	public void windowDeactivated(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeiconified(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowIconified(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowOpened(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object ob = e.getSource();
		if(ob == closeButton)
		{
			exit();
		}
		else if(ob == checkButton)
		{
			//System.out.println(inputField.getText());
			check_refresh(check_code(inputField.getText()));
		}
		
	}
	
}
