package mainClasses;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.XMLDecoder;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;

import javax.swing.JFrame;
import javax.swing.UIManager;

import lang.English;
import lang.Korean;
import lang.Language;
import lang.UserDefinedKor;
import lang.UserDefinedLang;
import reflexioner.Reflexioner;
import setting.Setting;
import setting.VersionData;
import tracking.TrackStorage;

public class RunManager implements Serializable
{
	private static final long serialVersionUID = -1983682874942538244L;
	public static final boolean OPEN_MANAGER = false;
	public static int STANDALONE_MODE = 4; // -1 : Normal
	private static RunManager manager = null;
	private static Openable window = null;
	private static boolean classic_mode = false;
	private static boolean uninstall_mode = false;
	private static boolean make_config = false;
	private static boolean reflexoner = false;
	private static boolean firstTime = false;
	private static Class<?> indexClass = null;
	
	private RunManager()
	{
		
	}
	public static Class<?> getIndexClass() {
		if(indexClass == null) return RunManager.class;
		return indexClass;
	}
	public static long getNameCode(String str)
	{
		return getNameCode(str.toCharArray());
	}
	public static long getNameCode(String str, String before_charset, String now_charset) throws UnsupportedEncodingException
	{
		return getNameCode(new String(str.getBytes(before_charset), now_charset));
	}
	public static long getNameCode(char[] str)
	{
		long results = 0;
		for(int i=0; i<str.length; i++)
		{
			results += (long) ((int) str[i]);
		}
		return results;
	}
	public static RunManager getInstance()
	{
		if(manager == null) manager = new RunManager();
		return manager;
	}
	public static void run(String[] args, Setting befores, Class<?> caller) throws HeadlessException, UnsupportedEncodingException
	{
		indexClass = caller;
		RunManager.getInstance().runGame(args, befores, null);
	}
	public static void run(String[] args, Setting befores, String run_path, Class<?> caller) throws HeadlessException, UnsupportedEncodingException
	{
		indexClass = caller;
		Setting gets = befores;		
		RunManager.getInstance().runGame(args, gets, run_path);
	}
	public void runGame(String[] args, Setting befores, String run_path) throws HeadlessException, UnsupportedEncodingException
	{		
		//System.out.println("1");
		Setting setting = null;
		if(befores != null)
		{
			setting = befores;
		}
		
		String default_path = System.getProperty("user.home");
		String os = System.getProperty("os.name");
		String lang = System.getProperty("user.language");
		String jar_path = run_path;
		// boolean isDesktopAble = Desktop.isDesktopSupported();
		
		String separator = "";		
		classic_mode = false;
		uninstall_mode = false;
		make_config = false;
		reflexoner = false;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		boolean default_lang = true;
		boolean default_theme = true;
		boolean default_size = true;
		boolean theme_selection = false;
		int arg_index = 0;
		int arg_length = 0;
		System.out.print(".");	
		System.gc();
		System.out.print(".");
		separator = System.getProperty("file.separator");
		default_path = default_path + separator + ".reflexioner" + separator;
		boolean loadComplete = false;
		if(setting == null) setting = null;
		else setting = setting.clone();
		System.out.print(".");
		BeforeProgressDialog.showNow();
		if(args != null)
		{
			if(args.length >= 1)
			{
				for(int i=0; i<args.length; i++)
				{
					if(args[i].equalsIgnoreCase("classic"))
					{
						classic_mode = true;
					}
					else if(args[i].equalsIgnoreCase("uninstall"))
					{
						uninstall_mode = true;
					}
					else if(args[i].equalsIgnoreCase("config"))
					{
						make_config = true;
					}
				}
			}
		}		
		System.out.print(".");
		
		
		if(setting == null)
		{
			try
			{
				
				boolean xml_used = false;
				File setFile = new File(default_path + "setting.xml");
				FileInputStream fin;
				if(setFile.exists())
				{
					fin = new FileInputStream(setFile);
					xml_used = true;
				}
				else
				{
					fin = new FileInputStream(default_path + "setting.clst");
				}
				if(xml_used)
				{
					XMLDecoder decoder = new XMLDecoder(fin);
					setting = (Setting) decoder.readObject();
					setting.objectToWrapper();
					setting.wrapperObjectClean();
					decoder.close();
					firstTime = false;
				}
				else
				{					
					ObjectInputStream obin = new ObjectInputStream(fin);
					setting = (Setting) obin.readObject();
					obin.close();
					firstTime = false;
				}
				fin.close();
				
				if(setting.getVer_main() == Reflexioner.version_main 
						&& setting.getVer_sub1() == Reflexioner.version_sub_1
						&& setting.getVer_sub2() == Reflexioner.version_sub_2)
				{
					loadComplete = true;
					classic_mode = setting.isClassic_mode();
					setting.setJarfile_path(jar_path);
				} // aut need change
				else if(setting.getVer_main() == Reflexioner.version_main 
						&& setting.getVer_sub1() >= 9
						&& setting.getVer_sub2() >= 0
						&& (setting.getVersion_aut().equals(Reflexioner.version_auts())))
				{
					loadComplete = true;
					classic_mode = setting.isClassic_mode();
					setting.setJarfile_path(jar_path);
				}
				else
				{				
					setting = Setting.getNewInstance();	
					firstTime = true;
					loadComplete = false;	
					//loadComplete = true;
				}						
				
			}
			catch(InvalidClassException e)
			{
				setting = Setting.getNewInstance();
				System.out.println("Couldn\'t load the setting because of version.");
				firstTime = true;
				loadComplete = false;
			}
			catch(FileNotFoundException e)
			{
				setting = Setting.getNewInstance();	
				firstTime = true;
				loadComplete = false;
			}
			catch(NullPointerException e)
			{
				setting = Setting.getNewInstance();
				firstTime = true;
				loadComplete = false;
			}
			catch(Exception e)
			{
				e.printStackTrace();
				setting = Setting.getNewInstance();
				firstTime = true;
				loadComplete = false;
			}
		}
		else
		{
			loadComplete = true;
		}
		System.out.print(".");
		//BeforeProgressDialog.progress(0.11);
		//System.out.println(setting.center_tab);
		//System.out.println(loadComplete);
		//System.out.println(setting.classic_mode);
		
		Reflexioner.try_load_properties(setting);
		try
		{
			if(setting.getProperties().containsKey("look_and_feel"))
			{				
				setting.setLookAndFeel(setting.getProperties().get("look_and_feel").trim());						
			}
		} 
		catch (Exception e1)
		{
			
		}
							
		if(setting.getLookAndFeel() != null)
		{
			if(! setting.getLookAndFeel().equals("")) 
			{
				try
				{
					UIManager.setLookAndFeel(setting.getLookAndFeel());
				} 
				catch (Exception e)
				{
					e.printStackTrace();
					setting = Setting.getNewInstance();
					firstTime = true;
					loadComplete = false;
				} 
			}
		}
		reflexoner = true;
		if(! loadComplete)
		{
			setting.setDefault_path(new String(default_path));
			setting.setOs(new String(os));
			setting.setLocale(new String(lang));
			setting.setSeparator(new String(separator));
			setting.setScreenSize(screenSize);
			setting.setNotice_url("http://netstorm.woobi.co.kr/calc/"); // TODO
		}
		
		if(args != null)
		{
			arg_length = args.length;			
			if(args.length >= 1)
			{				
				while(arg_index < arg_length)
				{
					if(theme_selection)
					{
						if(args[arg_index].equalsIgnoreCase("default") || args[arg_index].equalsIgnoreCase("blue"))
						{
							setting.setSelected_back(new Color(190, 210, 255));
							setting.setSelected_inside_back(new Color(135, 180, 235));
							setting.setSelected_fore(new Color(0, 0, 139));
							setting.setUnselected_back(new Color(220, 230, 255));
							setting.setUnselected_inside_back(new Color(175, 200, 255));
							setting.setUnselected_fore(new Color(0, 0, 255));
						}
						else if(args[arg_index].equalsIgnoreCase("black"))
						{
							setting.setSelected_back(Color.gray);
							setting.setSelected_fore(Color.LIGHT_GRAY);
							setting.setSelected_inside_back(Color.DARK_GRAY);
							setting.setUnselected_back(Color.LIGHT_GRAY);
							setting.setUnselected_fore(Color.DARK_GRAY);
							setting.setUnselected_inside_back(Color.gray);
						}
						else if(args[arg_index].equalsIgnoreCase("green"))
						{
							setting.setSelected_back(new Color(71, 200, 62));
							setting.setSelected_inside_back(new Color(47, 157, 39));
							setting.setSelected_fore(new Color(34, 116, 28));
							setting.setUnselected_back(new Color(134, 229, 127));
							setting.setUnselected_inside_back(new Color(71, 200, 62));
							setting.setUnselected_fore(new Color(47, 157, 39));
						}
						default_theme = false;
						theme_selection = false;
						arg_index++;
						continue;
					}
					else
					{
						if(args[arg_index].equalsIgnoreCase("theme"))
						{
							theme_selection = true;
							arg_index++;
							continue;
						}
						else if(args[arg_index].startsWith("width:") || args[arg_index].startsWith("WIDTH:"))
						{
							char[] chars = args[arg_index].toCharArray();
							String lefts = "";
							int width = 0;
							for(int i=6; i<chars.length; i++)
							{
								lefts += String.valueOf(chars[i]);
							}
							try
							{
								width = Integer.parseInt(lefts);
								setting.setWidth(width);
								default_size = false;
								if(width < 1000) setting.setScrollBar(true);
							} 
							catch (NumberFormatException e)
							{
								
							}						
							arg_index++;
						}
						else if(args[arg_index].startsWith("w:") || args[arg_index].startsWith("W:"))
						{
							char[] chars = args[arg_index].toCharArray();
							String lefts = "";
							int width = 0;
							for(int i=2; i<chars.length; i++)
							{
								lefts += String.valueOf(chars[i]);
							}
							try
							{
								width = Integer.parseInt(lefts);
								setting.setWidth(width);
								default_size = false;
								if(width < 1000) setting.setScrollBar(true);
							} 
							catch (NumberFormatException e)
							{
								
							}						
							arg_index++;
						}
						else if(args[arg_index].startsWith("height:") || args[arg_index].startsWith("HEIGHT:"))
						{
							char[] chars = args[arg_index].toCharArray();
							String lefts = "";
							int height = 0;
							for(int i=7; i<chars.length; i++)
							{
								lefts += String.valueOf(chars[i]);
							}
							try
							{
								height = Integer.parseInt(lefts);
								setting.setHeight(height);
								default_size = false;
							} 
							catch (NumberFormatException e)
							{
								
							}						
							arg_index++;
						}
						else if(args[arg_index].startsWith("h:") || args[arg_index].startsWith("H:"))
						{
							char[] chars = args[arg_index].toCharArray();
							String lefts = "";
							int height = 0;
							for(int i=2; i<chars.length; i++)
							{
								lefts += String.valueOf(chars[i]);
							}
							try
							{
								height = Integer.parseInt(lefts);
								setting.setHeight(height);
								default_size = false;
							} 
							catch (NumberFormatException e)
							{
								
							}						
							arg_index++;
						}							
						else if(args[arg_index].equalsIgnoreCase("english"))
						{
							setting.setLang(new English());
							default_lang = false;
							arg_index++;
						}
						else if(args[arg_index].equalsIgnoreCase("korean"))
						{
							setting.setLang(new Korean());
							default_lang = false;
							arg_index++;
						}
						else if(args[arg_index].equalsIgnoreCase("classic"))
						{
							classic_mode = true;
							arg_index++;
							continue;
						}
						else if(args[arg_index].equalsIgnoreCase("nimbus"))
						{
							try
							{
								setting.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
								UIManager.setLookAndFeel(setting.getLookAndFeel());								
							} 
							catch(Exception e)
							{
								e.printStackTrace();
							}
							arg_index++;
						}
						else if(args[arg_index].equalsIgnoreCase("windows"))
						{
							try
							{
								setting.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
								UIManager.setLookAndFeel(setting.getLookAndFeel());	
								setting.setUse_color(false);
								default_theme = false;
							} 
							catch(Exception e)
							{
								e.printStackTrace();
							}
							arg_index++;
						}
						else if(args[arg_index].equalsIgnoreCase("synth"))
						{
							try
							{
								setting.setLookAndFeel("javax.swing.plaf.synth.SynthLookAndFeel");
								UIManager.setLookAndFeel(setting.getLookAndFeel());	
							} 
							catch(Exception e)
							{
								e.printStackTrace();
							}
							arg_index++;
						}
						else if(args[arg_index].equalsIgnoreCase("metal"))
						{
							try
							{
								setting.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
								UIManager.setLookAndFeel(setting.getLookAndFeel());	
							} 
							catch(Exception e)
							{
								e.printStackTrace();
							}
							arg_index++;
						}
						else if(args[arg_index].equalsIgnoreCase("native"))
						{
							try
							{
								setting.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
								UIManager.setLookAndFeel(setting.getLookAndFeel());
							} 
							catch(Exception e)
							{
								e.printStackTrace();
							}
							arg_index++;
						}
						else
						{
							arg_index++;
						}
					}					
				}
			}
		}
		else
		{
			if(classic_mode) setting.setClassic_mode(true);
		}
		
		
		if(! loadComplete)
		{
			if(default_lang)
			{
				//System.out.println("Default Language");
				if(setting.getLocale().startsWith("en") || setting.getLocale().startsWith("EN") || setting.getLocale().startsWith("eng") || setting.getLocale().startsWith("ENG")) setting.setLang(new English());
				else if(setting.getLocale().startsWith("ko") || setting.getLocale().startsWith("KO") || setting.getLocale().startsWith("kr") || setting.getLocale().startsWith("KR") || setting.getLocale().startsWith("kor") || setting.getLocale().startsWith("KOR")) setting.setLang(new Korean());
				else setting.setLang(new English());
			}			
			if(default_theme)
			{
				setting.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
				setting.setUse_color(true);
			}
			if(default_size)
			{
				if(screenSize.getWidth() >= 1920)
				{
					setting.setWidth(1800);
					if(setting.getSlots() <= 4)
					{
						setting.setScrollBar(false);
					}
				}
				else if(screenSize.getWidth() >= 1600)
				{
					setting.setWidth(1520);					
				}
				else if(screenSize.getWidth() >= 1280)
				{
					setting.setWidth(1200);
				}
				else if(screenSize.getWidth() >= 1024)
				{
					setting.setWidth(960);
					if(setting.getSlots() >= 4)
					{
						setting.setScrollBar(true);
					}
				}			
				else
				{
					setting.setWidth(620);
					if(setting.getSlots() >= 3)
					{
						setting.setScrollBar(true);
					}
				}
				
				
				if(screenSize.getHeight() >= 1080)
				{
					setting.setHeight(700);
				}
				else if(screenSize.getHeight() >= 1024)
				{
					setting.setHeight(650);
				}
				else if(screenSize.getHeight() >= 864)
				{
					setting.setHeight(550);
				}
				else if(screenSize.getHeight() >= 768)
				{
					setting.setHeight(450);
				}
				else if(screenSize.getHeight() >= 720)
				{
					setting.setHeight(410);
				}
				else setting.setHeight(350);
			}
			if(os.startsWith("window") || os.startsWith("windows") || os.startsWith("Window") || os.startsWith("Windows"))
			{
				setting.setOp_multiply('×');
			}
			else
			{
				setting.setOp_multiply('*');
			}
		}
		
		System.out.print(".");
		
		TrackStorage.newInstance(setting);
		System.out.print(".");
		
		ConfigSetting readConfigData = ConfigMaker.readConfig(setting);
		setting = readConfigData.sets;
		boolean langfile_load = readConfigData.lang_file;
		
		if(setting.getOtherObjects() != null && setting.getOtherObjects().size() >= 1)
		{
			try
			{
				String[] others;
				for(int i=0; i<setting.getOtherObjects().size(); i++)
				{
					others = setting.getOtherObjects().get(i).split("=");
					try
					{
						if(others[0].equalsIgnoreCase("load") && others[1].equalsIgnoreCase("lang"))
						{
							langfile_load = true;
						}
					}
					catch (Exception e)
					{
						
					}
				}
			} 
			catch (Exception e)
			{
				
			}
		}
		if(langfile_load)
		{
			File additionalSetitngFile = null;
			FileInputStream fin = null;
			InputStreamReader inr = null;
			BufferedReader buf = null;
			String buf_takes = null;
			try
			{
				additionalSetitngFile = new File(setting.getDefault_path() + "lang.cfg");
				if(additionalSetitngFile.exists())
				{
					fin = new FileInputStream(additionalSetitngFile);
					inr = new InputStreamReader(fin);
					buf = new BufferedReader(inr);
					StringBuffer langBuf = new StringBuffer("");
					while(true)
					{
						buf_takes = buf.readLine();
						if(buf_takes == null) break;
						langBuf = langBuf.append(buf_takes);
						langBuf = langBuf.append("\n");
					}
					String loc = "";
					try
					{
						loc = System.getProperty("user.language");						
					} 
					catch (Exception e)
					{
						
					}
					if(loc.equalsIgnoreCase("ko") || loc.equalsIgnoreCase("kr") || loc.equalsIgnoreCase("kor") || loc.equalsIgnoreCase("korean"))
					{
						setting.setLang(new UserDefinedKor(new String(langBuf)));
					}
					else
					{
						setting.setLang(new UserDefinedLang(new String(langBuf)));
					}
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			finally
			{
				try
				{
					buf.close();
				} 
				catch (Exception e)
				{
					
				}
				try
				{
					inr.close();
				} 
				catch (Exception e)
				{
					
				}
				try
				{
					fin.close();
				} 
				catch (Exception e)
				{
					
				}
			}
		}
		
		System.gc();
		
		switch(STANDALONE_MODE)
		{
			case -1:
				if(uninstall_mode)
				{
					System.out.println();
					if(setting.getOs().startsWith("Windows") || setting.getOs().startsWith("windows")
							|| setting.getOs().startsWith("Ubuntu") || setting.getOs().startsWith("ubuntu")
							|| setting.getOs().startsWith("Kubuntu") || setting.getOs().startsWith("kubuntu")
							|| setting.getOs().startsWith("Mac") || setting.getOs().startsWith("mac"))
					{
						Uninstaller.uninstall(null, setting, true);
					}
					
					System.exit(0);
				}
				else if(make_config)
				{
					window = new ConfigMaker(true, setting);
				}
				else if(reflexoner)
				{
					System.out.print(".");
					window = new Reflexioner(setting, this, args, true);
					System.out.print(".");
				}
				break;
			case 4:
				window = new Reflexioner(setting, true, firstTime);
				break;
				
		}
		
		System.out.println(".!");
		BeforeProgressDialog.close();
		window.open();
	}
	public static String r65279(String target)
	{
		char[] targets = target.toCharArray();
		String results = "";
		
		for(int i=0; i<targets.length; i++)
		{
			if(((int)targets[i]) != 65279)
			{
				results = results + String.valueOf(targets[i]);
			}
		}
		return results;
	}
}
class OpenManager extends Frame implements Openable, WindowListener, ActionListener
{
	private static final long serialVersionUID = -6033037958814960781L;
	private Setting set;
	private Panel mainPanel;
	private Panel centerPanel;
	private Panel upPanel;
	private Panel downPanel;
	private Panel[] pns;
	private Button[] start_buttons;
	private TextField[] descripts;
	private Label[] labels;
	private Button close;
	private String[] arguments;

	public OpenManager(Setting set, String[] args)
	{
		this.set = set.clone();
		arguments = args;
		this.addWindowListener(this);
		this.setSize(400, 300);
		this.setLocation((int)(set.getScreenSize().getWidth()/2 - this.getWidth()/2), (int)(set.getScreenSize().getHeight()/2 - this.getHeight()/2));
		mainPanel = new Panel();
		this.setLayout(new BorderLayout());
		this.add(mainPanel);
		mainPanel.setLayout(new BorderLayout());
		upPanel = new Panel();
		centerPanel = new Panel();
		downPanel = new Panel();
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		mainPanel.add(upPanel, BorderLayout.NORTH);
		mainPanel.add(downPanel, BorderLayout.SOUTH);
		pns = new Panel[2];
		start_buttons = new Button[pns.length];
		labels = new Label[pns.length];
		descripts = new TextField[pns.length];
		centerPanel.setLayout(new GridLayout(pns.length, 1));
		for(int i=0; i<pns.length; i++)
		{
			pns[i] = new Panel();
			pns[i].setLayout(new FlowLayout());
			labels[i] = new Label();
			descripts[i] = new TextField(10);
			descripts[i].setEditable(false);
			start_buttons[i] = new Button(set.getLang().getText(Language.RUN));
			start_buttons[i].addActionListener(this);
			pns[i].add(labels[i]);
			pns[i].add(descripts[i]);
			pns[i].add(start_buttons[i]);
			centerPanel.add(pns[i]);
		}
		labels[0].setText(set.getLang().getText(Language.TITLE));
		labels[1].setText(set.getLang().getText(Language.ONECARD));
		descripts[0].setText(set.getLang().getText(Language.DESC_CALC));
		descripts[1].setText(set.getLang().getText(Language.DESC_ONECARD));
		
		close = new Button(set.getLang().getText(Language.EXIT));
		close.addActionListener(this);
		downPanel.setLayout(new FlowLayout());
		downPanel.add(close);
	}
	@Override
	public void open()
	{
		this.setVisible(true);
	}

	@Override
	public void exit()
	{
		System.exit(0);
	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object ob = e.getSource();
		int select_index = -1;
		if(ob == close)
		{
			exit();
		}
		else
		{
			for(int i=0; i<start_buttons.length; i++)
			{
				if(ob == start_buttons[i])
				{
					select_index = i;
					break;
				}
			}
			if(select_index >= 0)
			{
				this.setVisible(false);
				Openable window;
				switch(select_index)
				{
					case 0:
						window = new Reflexioner(set, RunManager.getInstance(), arguments, true);
						ProgressDialog.show(set, (JFrame) window);
						ProgressDialog.progress(0);
						break;
					case 1:
						window = new OpenManager(set, arguments);
						break;
					default:
						window = new Reflexioner(set, RunManager.getInstance(), arguments, true);
						ProgressDialog.show(set, (JFrame) window);
						ProgressDialog.progress(0);
						
				}
				window.open();
			}
		}		
	}
	@Override
	public void windowActivated(WindowEvent e)
	{
		
		
	}
	@Override
	public void windowClosed(WindowEvent e)
	{
		
		
	}
	@Override
	public void windowClosing(WindowEvent e)
	{
		exit();
		
	}
	@Override
	public void windowDeactivated(WindowEvent e)
	{
		
		
	}
	@Override
	public void windowDeiconified(WindowEvent e)
	{
		
		
	}
	@Override
	public void windowIconified(WindowEvent e)
	{
		
		
	}
	@Override
	public void windowOpened(WindowEvent e)
	{
		
		
	}	
	
}
