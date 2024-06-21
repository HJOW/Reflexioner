package com.hjow.game.reflexioner.reflexioner;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.StringTokenizer;
import java.util.TreeSet;
import java.util.Vector;
import java.util.zip.GZIPOutputStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.plaf.FontUIResource;

import com.hjow.game.reflexioner.browser.OldBrowser;
import com.hjow.game.reflexioner.lang.English;
import com.hjow.game.reflexioner.lang.Korean;
import com.hjow.game.reflexioner.lang.Language;
import com.hjow.game.reflexioner.mainClasses.Code_Checker;
import com.hjow.game.reflexioner.mainClasses.MessageShowable;
import com.hjow.game.reflexioner.mainClasses.MouseDragCatcher;
import com.hjow.game.reflexioner.mainClasses.Openable;
import com.hjow.game.reflexioner.mainClasses.RunManager;
import com.hjow.game.reflexioner.mainClasses.ThreadAccumulate;
import com.hjow.game.reflexioner.mainClasses.Uninstaller;
import com.hjow.game.reflexioner.setting.Difficulty;
import com.hjow.game.reflexioner.setting.Lint;
import com.hjow.game.reflexioner.setting.Setting;

public class Reflexioner extends MouseDragCatcher implements Openable, WindowListener, ActionListener, MessageShowable, ListSelectionListener, ChangeListener, ItemListener
{
	private boolean independence = true;
	private Setting sets = null;
	
	private static boolean frame_loaded = false;
	
	public static final long version_nightly = 33;
	public static final int  version_main = 0;
	public static final int  version_sub_1 = 9;
	public static final int  version_sub_2 = 5;
	public static final char version_test = ' '; // Complete : ' ', Test : 'a' 'b' 'c'
	
	public static Language lang = null;
	private static String file_path = null;
	private static int speed = 9, react_delay = 37, boss_delay = 5000, difficulty_delay = 5000, boss_beam_delay = 400;
	private static int spaceShip_r = 50;
	private static int enemy_r = 35;
	public static Color color_spaceShip, color_spaceShip_missile, color_enemy_missile, color_enemy, color_bigenemy, color_item, color_item_text, color_useItem;
	public static String script_symbol = "> ";
	public static int KEY_1 = KeyEvent.VK_1;
	public static int KEY_2 = KeyEvent.VK_2;
	public static int KEY_3 = KeyEvent.VK_3;
	public static int KEY_4 = KeyEvent.VK_4;
	public static int KEY_5 = KeyEvent.VK_5;
	public static int KEY_6 = KeyEvent.VK_6;
	public static int KEY_7 = KeyEvent.VK_7;
	public static int KEY_8 = KeyEvent.VK_8;
	public static int KEY_9 = KeyEvent.VK_9;
	public static int KEY_0 = KeyEvent.VK_0;
	public static int KEY_SHIFT = KeyEvent.VK_SHIFT;
	public static int KEY_SPACE = KeyEvent.VK_SPACE;
	public static int KEY_K = KeyEvent.VK_K;
	public static int KEY_L = KeyEvent.VK_L;
	public static int KEY_LEFT = KeyEvent.VK_LEFT;
	public static int KEY_RIGHT = KeyEvent.VK_RIGHT;
	public static int KEY_UP = KeyEvent.VK_UP;
	public static int KEY_DOWN = KeyEvent.VK_DOWN;
	public static int KEY_W = KeyEvent.VK_W;
	public static int KEY_A = KeyEvent.VK_A;
	public static int KEY_S = KeyEvent.VK_S;
	public static int KEY_D = KeyEvent.VK_D;
	public static int max_enemies = 30;
	public static int fire_delay = 5;
	public static final int AUTO_SHIPNAME = 0;
	public static final int AUTO_SHIP_HP = 1;
	public static final int AUTO_SHIP_ENERGY = 2;
	public static final int AUTO_SHIP_SPEED = 3;
	public static final int AUTO_SHIP_SIZE = 4;
	public static final int AUTO_SHIP_SHAPE = 5;
	public static final int AUTO_WEAPON_1_TYPE = 6;
	public static final int AUTO_WEAPON_1_MAX = 7;
	public static final int AUTO_WEAPON_1_MIN = 8;
	public static final int AUTO_WEAPON_1_INTERVAL = 9;
	public static final int AUTO_WEAPON_1_HP = 10;
	public static final int AUTO_WEAPON_1_NEEDS = 11;
	public static final int AUTO_WEAPON_1_SPEED = 12;
	public static final int AUTO_WEAPON_1_SIZE = 13;
	public static final int AUTO_WEAPON_1_DAMAGE = 14;
	public static final int AUTO_WEAPON_1_DELAY = 15;
	public static final int AUTO_WEAPON_2_TYPE = 16;
	public static final int AUTO_WEAPON_2_MAX = 17;
	public static final int AUTO_WEAPON_2_MIN = 18;
	public static final int AUTO_WEAPON_2_INTERVAL = 19;
	public static final int AUTO_WEAPON_2_HP = 20;
	public static final int AUTO_WEAPON_2_NEEDS = 21;
	public static final int AUTO_WEAPON_2_SPEED = 22;
	public static final int AUTO_WEAPON_2_SIZE = 23;
	public static final int AUTO_WEAPON_2_DAMAGE = 24;
	public static final int AUTO_WEAPON_2_DELAY = 25;
	public static final int AUTO_WEAPON_3_TYPE = 26;
	public static final int AUTO_WEAPON_3_MAX = 27;
	public static final int AUTO_WEAPON_3_MIN = 28;
	public static final int AUTO_WEAPON_3_INTERVAL = 29;
	public static final int AUTO_WEAPON_3_HP = 30;
	public static final int AUTO_WEAPON_3_NEEDS = 31;
	public static final int AUTO_WEAPON_3_SPEED = 32;
	public static final int AUTO_WEAPON_3_SIZE = 33;
	public static final int AUTO_WEAPON_3_DAMAGE = 34;
	public static final int AUTO_WEAPON_3_DELAY = 35;
	public static final int AUTO_WEAPON_1_HELPER_TYPE = 36;
	public static final int AUTO_WEAPON_1_HELPER_COUNT = 37;
	public static final int AUTO_WEAPON_2_HELPER_TYPE = 38;
	public static final int AUTO_WEAPON_2_HELPER_COUNT = 39;
	public static final int AUTO_WEAPON_3_HELPER_TYPE = 40;
	public static final int AUTO_WEAPON_3_HELPER_COUNT = 41;
	
	private static boolean use_transparent = true;
	private static float transparent_opacity = 0.9f;
	
	public static boolean replay_save = false;
	public static int replay_interval = 1;
	public static int replay_now_delay = 0;
	
	public static String DELIM_SCEN = "|||||";
	public static String DELIM_ENEMY_PATTERN = "!!!";
	public static String DELIM_ENEMY = "???";
	
	public static int AUTO_LAST = -1;
	public static boolean image_allow = true, sound_allow = true;
	
	public static String usingFontName = null;
	public static Font usingFont = null;
	public static Font usingFontB = null;
	public static Font usingFont2 = null;
	public static Font usingFont2B = null;
	public static int default_fontSize = 12;
	
	private static transient int calc_grade = 0;
	public static transient String calc_grade_str = "Lite";
	
	private Setting setting;
	private RunManager manager;
	private String[] arguments;
	private Window window;
	private JPanel mainPanel;
	private Arena arena;
	private JPanel upPanel;
	private JPanel centerPanel;
	private JPanel downPanel;
	private JPanel infoPanel;
	private JPanel infoPnLeft, infoPnRight;
	private JLabel hpLabel;
	private JProgressBar hpBar;
	private UpdateHp updateHp;
	private JLabel pointLabel;
	private JTextField pointField;
	private UpdatePoint updatePoint;
	private JLabel energyLabel;
	private JProgressBar energyBar;
	private UpdateEnergy updateEnergy;
	private JDialog startDialog;
	private JPanel start_mainPanel;
	private JPanel start_upPanel;
	private JPanel start_downPanel;
	private JPanel start_centerPanel;
	private JPanel start_buttonPanel;
	private JButton bt_start;
	private JButton bt_exit;
	private JDialog finishDialog;
	private JPanel finish_mainPanel;
	private JPanel finish_upPanel;
	private JPanel finish_downPanel;
	private JPanel finish_centerPanel;
	private CardLayout finish_layout;
	private JTextArea finish_ta;
	private JButton bt_finish_ok;
	private JPanel finish_resultPanel;
	private JLabel finish_resultLabel;
	private JTextField finish_resultField;
	private JPanel[] finish_pns;
	private JLabel finish_catchEnemyLabel;
	private JTextField finish_catchEnemyField;
	private JLabel finish_catchItemLabel;
	private JTextField finish_catchItemField;
	private JPanel titlePanel;
	private JLabel titleLabel;
	private JPanel start_titlePanel;
	private JLabel start_titleLabel;
	private JPanel finish_titlePanel;
	private JLabel finish_titleLabel;
	private JPanel helpPanel;
	private JLabel helpLabel;
	private int mouse_x;
	private int mouse_y;
	private int mouse_start_x;
	private int mouse_start_y;
	private int mouse_finish_x;
	private int mouse_finish_y;
	private JPanel controlBtPanel;
	private JButton bt_left;
	private JButton bt_right;
	private JButton bt_fire;
	private JPanel secondBtPanel;
	private JButton bt_w1;
	private JButton bt_w2;
	private JButton bt_w3;
	private JButton bt_stop;
	private boolean touchMode = true;
	private JButton bt_touch;
	private JComboBox combo_ship;
	private JPanel start_namePanel;
	private JLabel start_nameLabel;
	private JTextField start_nameField;
	private JLabel finish_nameLabel;
	private JTextField finish_nameField;
	private JLabel finish_authLabel;
	private JTextField finish_authField, finish_authField2;
	private JButton bt_authCopy, bt_authCopy2;
	private JMenuBar menuBar;
	private JMenu menu_file;
	private JMenuItem menu_file_exit;
	private JMenu menu_view;
	private JMenuItem menu_view_check;
	private Code_Checker checker;
	private JPanel start_centerHelpPanel;
	private JTextArea start_centerHelpArea;
	private JScrollPane start_centerHelpScroll;
	private JButton bt_saveState;
	private JMenuItem menu_file_load;
	private JFileChooser fileChooser;
	private FileFilter fileFilter;
	private JDialog aboutDialog;
	private JPanel about_mainPanel;
	private JPanel about_upPanel;
	private JPanel about_downPanel;
	private JPanel about_centerPanel;
	private JButton bt_aboutClose;
	private JMenu menu_help;
	private JMenuItem menu_help_about;
	private JLabel about_centerLabel;
	private JPanel about_centerLabelPanel;
	private JPanel about_versionPanel;
	private JLabel about_versionLabel;
	private JPanel about_editionStringPanel;
	private JPanel about_titleStringPanel;
	private JLabel about_editionLabel;
	private JMenuItem menu_file_start;
	private int grade_mode = 1;
	private JDialog userDefinedDialog;
	private JPanel userDefined_mainPanel;
	private JPanel userDefined_upPanel;
	private JPanel userDefined_downPanel;
	private JPanel userDefined_centerPanel;
	private JTextArea userDefined_area;
	private JScrollPane userDefined_scroll;
	private JTabbedPane userDefined_tab;
	private JPanel userDefined_namePanel;
	private JLabel userDefined_nameLabel;
	private JTextField userDefined_nameField;
	private JButton bt_start_userDefined;
	private JButton bt_close_userDefined;
	private JMenuItem menu_file_start_userDefined;
	private JComboBox combo_dif;
	private JTabbedPane mainTab;
	private JPanel start_standardPanel;
	private JPanel start_userDefinedPanel;
	private JPanel start_userDefined_centerPanel;
	private JPanel start_userDefined_downPanel;
	private JTextArea start_userDefinedArea;
	private JScrollPane start_userDefinedScroll;
	private JButton bt_exit2;
	private JButton bt_start_userDefined2;
	private JPanel start_userDefined_upPanel;
	private JLabel start_userDefined_nameLabel;
	private JTextField start_userDefined_nameField;
	private JPanel start_scenarioPanel;
	private JList start_scenarioList;
	private JScrollPane start_scenarioListScroll;
	private JPanel start_scenario_upPanel;
	private JPanel start_scenario_centerPanel;
	private JPanel start_scenario_downPanel;
	private JLabel start_scenario_nameLabel;
	private JTextField start_scenario_nameField;
	private JTextArea start_scenario_description;
	private JScrollPane start_scenario_descriptionScroll;
	private JPanel start_scenario_namePanel;
	private JPanel start_scenario_descriptionPanel;
	private List<ReflexScenario> scenarios;
	private ReflexScenario selected_scenario = null;
	private JButton bt_start_scenario;
	private JButton bt_exit3;
	private JCheckBox start_userDefined_isScenario;
	private Color gradeColor;
	private JLabel start_verLabel;
	private JPanel start_noticePanel;
	private JPanel start_notice_upPanel;
	private JPanel start_notice_downPanel;
	private JPanel start_notice_centerPanel;
	private JEditorPane start_noticeArea;
	private JScrollPane start_noticeScroll;
	private JButton bt_next;
	private JButton bt_event_open;
	private JButton bt_exit4;
	private JPanel start_downloadPanel;
	private JPanel start_download_upPanel;
	private JPanel start_download_centerPanel;
	private JPanel start_download_downPanel;
	private JTextArea start_download_message;
	private JScrollPane start_download_scroll;
	private JButton bt_download;
	private JProgressBar progress_download;
	private int download_limit = 0;
	private JButton bt_exit5;
	private JMenu menu_file_manage;
	private JMenuItem menu_manage_uninstall;
	private JCheckBoxMenuItem menu_manage_enableImage;
	private JCheckBoxMenuItem menu_manage_enableSound;
	private String[] ships;
	private int[] shipKeys;
	private JButton bt_autoComplete;
	private JDialog autoUserDefinedDialog;
	private JPanel autoUserDefined_mainPanel;
	private JPanel autoUserDefined_upPanel;
	private JPanel autoUserDefined_downPanel;
	private JPanel autoUserDefined_centerPanel;
	private JPanel autoUserDefined_contentPanel;
	private JScrollPane autoUserDefined_contentScroll;
	private JPanel[] autoUserDefined_contents;
	private JButton bt_closeAutoUserDefined;
	private JButton bt_acceptAutoUserDefined;
	private JPanel autoUserDefined_titlePanel;
	private JLabel autoUserDefined_titleLabel;
	private int mouse_auto_x;
	private int mouse_auto_y;
	private JLabel[] autoUserDefined_labels;
	private JTextField[] autoUserDefined_fields;
	private JCheckBoxMenuItem menu_manage_autoControl;
	private JButton bt_continue;
	private JDialog messageDialog;
	private JPanel message_mainPanel;
	private JPanel message_titlePanel;
	private JLabel message_titleLabel;
	private JTextArea message_textArea;
	private JScrollPane message_textScroll;
	private JPanel message_upPanel;
	private JButton bt_close_message;
	private JMenuItem menu_view_message;
	private ReflexScenarioEditor scenarioEditor;
	private JMenuItem menu_view_editor;
	private JButton bt_saveReplay;
	private JCheckBoxMenuItem menu_manage_saveReplay;
	private JMenuItem menu_view_replayPlayer;
	private ReflexReplayPlayer replayPlayer;
	private JMenuItem menu_manage_setIntervalReplay;
	private FileFilter fileFilter_rp;
	private JFileChooser fileChooser_rp;
	private FileFilter fileFilter_rp2;
	private String download_url1, download_url2;
	private JButton bt_setUrl;
	private JComboBox start_scenario_selectShipCombo;
	private String selected_scenario_ship;
	private JPanel start_notice_descPanel;
	private JPanel start_notice_titlePanel;
	private JLabel start_notice_titleLabel;
	private JButton bt_runMacro;
	private JButton bt_helpMacro;
	private JDialog needfileDialog;
	private JPanel needfile_mainPanel;
	private JPanel needfile_upPanel;
	private JPanel needfile_centerPanel;
	private JPanel needfile_downPanel;
	private JSplitPane needfile_split;
	private JTextArea needfile_fileList;
	private JScrollPane needfile_fileScroll;
	private JTextArea needfile_descList;
	private JScrollPane needfile_descScroll;
	private JButton bt_close_needfile;
	private JPanel needfile_titlePanel;
	private JLabel needfile_titleLabel;
	private JMenuItem menu_help_files;
	private int needfile_x = 0;
	private int needfile_y = 0;
	private int message_x = 0;
	private int message_y = 0;
	private JButton bt_scenario;
	private JButton bt_user_defined;
	private JPanel start_todayPanel;
	private JPanel start_today_upPanel;
	private JPanel start_today_centerPanel;
	private JPanel start_today_downPanel;
	private JEditorPane start_today_area;
	private JScrollPane start_today_scroll;
	private JButton bt_start_today;
	private JButton bt_exit6;
	private double today_difficulty = 1.0;
	private int today_ship = SpaceShip.FLEX;
	private int[] startItem = null;
	private JLabel start_today_nameLabel;
	private JTextField start_today_nameField;
	private JPanel about_titlePanel;
	private JLabel about_titleLabel;
	private JPanel about_gapPanel;
	private int about_x;
	private int about_y;
	private JButton bt_viewOnEditor;
	
	
	public Reflexioner()
	{
		this(Setting.getNewInstance(), true);
	}
	public Reflexioner(boolean firstTime)
	{
		this(Setting.getNewInstance(), true, firstTime);
	}
	public Reflexioner(Setting sets, boolean independence)
	{
		super();
		this.sets = sets;
		this.independence = independence;
		window = new JFrame();		
		init();
	}	
	public Reflexioner(Setting sets, RunManager manager, String[] args, boolean independence)
	{
		super();
		this.sets = sets;
		this.independence = independence;
		this.manager = manager;
		this.arguments = args;
		window = new JFrame();		
		init();
	}
	public Reflexioner(Setting sets, boolean independence, boolean firstTime)
	{
		super();
		this.sets = sets;
		this.independence = independence;
		window = new JFrame();
		init();
	}
	public Reflexioner(Setting sets, RunManager manager, String[] args, boolean independence, boolean firstTime)
	{
		super();
		this.sets = sets;
		this.independence = independence;
		window = new JFrame();
		this.manager = manager;
		this.arguments = args;
		init();
	}
	public Reflexioner(Window sp, Setting sets, boolean independence)
	{
		super();
		this.sets = sets;
		this.independence = independence;
		window = new JDialog(sp);
		init();
	}
	private void init()
	{
		Dimension scrSize = sets.getScreenSize();
		
		window.setSize((int) (sets.getWidth()), (int) (sets.getHeight()));
		window.setMaximumSize(new Dimension((int) (scrSize.getWidth() - 50), (int) (scrSize.getHeight() - 50)));
		window.setMinimumSize(new Dimension(550, 450));
		window.setLocation((int)(sets.getScreenSize().getWidth()/2 - window.getWidth()/2), (int)(sets.getScreenSize().getHeight()/2 - window.getHeight()/2));
		window.setLayout(new BorderLayout());
		window.addWindowListener(this);
		
		script_symbol = sets.getLang().getText(Language.SCRIPT).toUpperCase() + "> ";
		
		menuBar = new JMenuBar();
		
		Reflexioner.prepareFont();	
		file_path = sets.getDefault_path();
		lang = sets.getLang().clone();
		
		if(window instanceof JFrame)
		{
			((JFrame) window).setTitle(sets.getLang().getText(Language.REFLEX));
			if(sets.isUse_own_titleBar())
			{
				((JFrame) window).setUndecorated(true);
			}
		}
		else if(window instanceof JDialog)
		{
			((JDialog) window).setTitle(sets.getLang().getText(Language.REFLEX));
			if(sets.isUse_own_titleBar())
			{
				((JDialog) window).setUndecorated(true);
			}
		}		
		
		window.setBackground(sets.getSelected_back());
		
		download_url1 = sets.getNotice_url();
		download_url2 = sets.getNotice_url2();
		
		try
		{
			window.setIconImage(new ImageIcon(getClass().getClassLoader().getResource("reflex_ico.png")).getImage());
		}
		catch(Exception e)
		{
			if(sets.isError_printDetail()) e.printStackTrace();			
		}
		
		if(window instanceof JFrame)
		{
			messageDialog = new JDialog(((JFrame) window), false);
		}
		else
		{
			messageDialog = new JDialog(window);
		}
		messageDialog.setUndecorated(true);
		messageDialog.setSize(window.getWidth(), 200);
		messageDialog.setLocation((int)(window.getLocation().getX()), (int)(window.getLocation().getY() + window.getSize().getHeight()));
		messageDialog.getContentPane().setLayout(new BorderLayout());
		message_mainPanel = new JPanel();
		messageDialog.getContentPane().add(message_mainPanel, BorderLayout.CENTER);
		message_mainPanel.setBackground(sets.getSelected_back());
		message_mainPanel.setLayout(new BorderLayout());
		message_mainPanel.setBorder(new EtchedBorder());
		message_textArea = new JTextArea();
		message_textArea.setEditable(false);
		message_textArea.setLineWrap(true);
		message_textArea.setBackground(sets.getSelected_inside_back());
		message_textArea.setForeground(sets.getSelected_fore());
		message_textScroll = new JScrollPane(message_textArea);
		message_mainPanel.add(message_textScroll, BorderLayout.CENTER);
		message_upPanel = new JPanel();
		message_upPanel.setLayout(new BorderLayout());
		message_upPanel.setBackground(sets.getSelected_inside_back());
		message_titlePanel = new JPanel();
		message_titlePanel.setBackground(sets.getSelected_inside_back());
		message_titlePanel.setBorder(new EtchedBorder());
		message_titlePanel.addMouseListener(this);
		message_titlePanel.addMouseMotionListener(this);
		message_titlePanel.setLayout(new FlowLayout());
		message_titleLabel = new JLabel(sets.getLang().getText(Language.LOG));
		message_titleLabel.setForeground(sets.getSelected_fore());
		message_titlePanel.add(message_titleLabel);
		message_upPanel.add(message_titlePanel, BorderLayout.CENTER);
		message_mainPanel.add(message_upPanel, BorderLayout.NORTH);
		bt_close_message = new JButton(sets.getLang().getText(Language.X));
		bt_close_message.addActionListener(this);
		bt_close_message.setForeground(sets.getSelected_fore());		
		message_upPanel.add(bt_close_message, BorderLayout.EAST);
		
		if(sets.getSelected_button() != null)
		{
			bt_close_message.setBackground(sets.getSelected_button());
		}
		if(usingFont != null)
		{
			message_titleLabel.setFont(usingFont);
			message_textArea.setFont(usingFont);
			bt_close_message.setFont(usingFont);
		}
		
		mainPanel = new JPanel();
		mainPanel.setBorder(new EtchedBorder());
		window.add(mainPanel);
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBackground(sets.getSelected_back());
		
		color_enemy = Color.DARK_GRAY;
		color_bigenemy = Color.BLACK;
		color_spaceShip = Color.GREEN;
		color_enemy_missile = Color.RED;
		color_spaceShip_missile = Color.BLUE;	
		color_item = Color.PINK;
		color_item_text = Color.RED;
		color_useItem = Color.CYAN;
		
		KEY_1 = sets.getKey_1().intValue();
		KEY_2 = sets.getKey_2().intValue();
		KEY_3 = sets.getKey_3().intValue();
		KEY_K = sets.getKey_k().intValue();
		KEY_L = sets.getKey_l().intValue();
		KEY_SHIFT = sets.getKey_shift().intValue();
		KEY_SPACE = sets.getKey_space().intValue();
		KEY_UP = sets.getKey_up().intValue();
		KEY_DOWN = sets.getKey_down().intValue();
		KEY_LEFT = sets.getKey_left().intValue();
		KEY_RIGHT = sets.getKey_right().intValue();		
		KEY_W = KeyEvent.VK_W;
		KEY_A = KeyEvent.VK_A;
		KEY_S = KeyEvent.VK_S;
		KEY_D = KeyEvent.VK_D;
		KEY_4 = KeyEvent.VK_4;
		KEY_5 = KeyEvent.VK_5;
		KEY_6 = KeyEvent.VK_6;
		KEY_7 = KeyEvent.VK_7;
		KEY_8 = KeyEvent.VK_8;
		KEY_9 = KeyEvent.VK_9;
		KEY_0 = KeyEvent.VK_0;
		
		updateHp = new UpdateHp();
		updatePoint = new UpdatePoint();
		updateEnergy = new UpdateEnergy();
		
		upPanel = new JPanel();
		centerPanel = new JPanel();
		downPanel = new JPanel();
		upPanel.setBackground(sets.getSelected_back());
		downPanel.setBackground(sets.getSelected_back());
		centerPanel.setBackground(sets.getSelected_back());
		mainPanel.add(upPanel, BorderLayout.NORTH);		
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		mainPanel.add(downPanel, BorderLayout.SOUTH);
		
		upPanel.setLayout(new BorderLayout());
		if(sets.isUse_own_titleBar())
		{
			titlePanel = new JPanel();
			titlePanel.setBackground(sets.getSelected_inside_back());
			titlePanel.setBorder(new EtchedBorder());
			titlePanel.setLayout(new FlowLayout());
			titleLabel = new JLabel(sets.getLang().getText(Language.REFLEX));
			titleLabel.setForeground(sets.getSelected_fore());
			if(usingFont != null)
				titleLabel.setFont(usingFont);
			titlePanel.add(titleLabel);
			titlePanel.addMouseListener(this);
			titlePanel.addMouseMotionListener(this);
			upPanel.add(titlePanel, BorderLayout.NORTH);
		}
		
		secondBtPanel = new JPanel();
		secondBtPanel.setBackground(sets.getSelected_back());
		upPanel.add(secondBtPanel, BorderLayout.CENTER);
		secondBtPanel.setLayout(new GridLayout(1, 4));
		bt_w1 = new JButton("1");
		bt_w2 = new JButton("2");
		bt_w3 = new JButton("3");
		bt_stop = new JButton(sets.getLang().getText(Language.GAME_STOP));
		bt_w1.addActionListener(this);
		bt_w2.addActionListener(this);
		bt_w3.addActionListener(this);
		bt_stop.addActionListener(this);
		if(usingFont != null)
		{
			bt_w1.setFont(usingFont);
			bt_w2.setFont(usingFont);
			bt_w3.setFont(usingFont);
			bt_stop.setFont(usingFont);
		}
		bt_w1.setForeground(sets.getSelected_fore());
		bt_w2.setForeground(sets.getSelected_fore());
		bt_w3.setForeground(sets.getSelected_fore());
		bt_stop.setForeground(sets.getSelected_fore());
		if(sets.getSelected_button() != null)
		{
			bt_w1.setBackground(sets.getSelected_back());
			bt_w2.setBackground(sets.getSelected_back());
			bt_w3.setBackground(sets.getSelected_back());
			bt_stop.setBackground(sets.getSelected_back());
		}
		
		secondBtPanel.add(bt_w1);
		secondBtPanel.add(bt_w2);
		secondBtPanel.add(bt_w3);
		secondBtPanel.add(bt_stop);
		
		centerPanel.setLayout(new BorderLayout());
		
		arena = new Arena(this, sets);
		centerPanel.add(arena, BorderLayout.CENTER);
		
		downPanel.setLayout(new BorderLayout());
		
		infoPanel = new JPanel();
		infoPanel.setBackground(sets.getSelected_back());
		downPanel.add(infoPanel, BorderLayout.NORTH);
		controlBtPanel = new JPanel();
		downPanel.add(controlBtPanel, BorderLayout.SOUTH);
		controlBtPanel.setLayout(new BorderLayout());
		controlBtPanel.setBackground(sets.getSelected_back());
		bt_left = new JButton("←");
		bt_right = new JButton("→");
		bt_fire = new JButton(sets.getLang().getText(Language.SHOOT));
		bt_left.addActionListener(this);
		bt_right.addActionListener(this);
		bt_fire.addActionListener(this);
		controlBtPanel.add(bt_left, BorderLayout.WEST);
		controlBtPanel.add(bt_right, BorderLayout.EAST);
		controlBtPanel.add(bt_fire, BorderLayout.CENTER);
		if(usingFont != null)
		{
			bt_left.setFont(usingFont);
			bt_right.setFont(usingFont);
			bt_fire.setFont(usingFont);
		}
		bt_left.setForeground(sets.getSelected_fore());
		bt_right.setForeground(sets.getSelected_fore());
		bt_fire.setForeground(sets.getSelected_fore());
		if(sets.getSelected_button() != null)
		{
			bt_left.setBackground(sets.getSelected_back());
			bt_right.setBackground(sets.getSelected_back());
			bt_fire.setBackground(sets.getSelected_back());
		}
		
		infoPanel.setLayout(new BorderLayout());
		
		infoPnLeft  = new JPanel();
		infoPnRight = new JPanel();
		
		infoPnLeft.setBackground(sets.getSelected_back());
		infoPnRight.setBackground(sets.getSelected_back());
		
		infoPnLeft.setLayout(new FlowLayout(FlowLayout.LEFT));
		infoPnRight.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		infoPanel.add(infoPnLeft, BorderLayout.CENTER);
		infoPanel.add(infoPnRight, BorderLayout.EAST);
		
		hpLabel = new JLabel(sets.getLang().getText(Language.HP));
		hpLabel.setForeground(sets.getSelected_fore());
		hpBar = new JProgressBar(JProgressBar.HORIZONTAL, 0, 100);
		if(usingFont != null)
			hpLabel.setFont(usingFont);
		
		int barWidth = (int) (sets.getWidth() / 3.0 - 50);
        if(barWidth < 250) barWidth = 250;
        
		Dimension barSize = new Dimension(barWidth, (int) hpBar.getMaximumSize().getHeight());
		hpBar.setSize(barSize);
		hpBar.setPreferredSize(barSize);
		
		infoPnLeft.add(hpLabel);
		infoPnLeft.add(hpBar);
		updateHp.setTarget(hpBar);
		
		energyLabel = new JLabel("Energe");
		energyLabel.setForeground(sets.getSelected_fore());
		if(usingFont != null)
			energyLabel.setFont(usingFont);
		energyBar = new JProgressBar(JProgressBar.HORIZONTAL, 0, 100);
		barSize = new Dimension(barWidth, (int) energyBar.getMaximumSize().getHeight());
		energyBar.setSize(barSize);
		energyBar.setPreferredSize(barSize);
		
		infoPnLeft.add(energyLabel);
		infoPnLeft.add(energyBar);
				
		updateEnergy.setTarget(energyBar);
		
		pointLabel = new JLabel(sets.getLang().getText(Language.POINT));
		pointLabel.setForeground(sets.getSelected_fore());
		if(usingFont != null)
			pointLabel.setFont(usingFont);
		pointField = new JTextField(10);
		pointField.setBackground(sets.getSelected_inside_back());
		pointField.setForeground(sets.getSelected_fore());
		pointField.setEditable(false);
		pointField.setBorder(new EtchedBorder());
		pointField.setText(String.valueOf(0));
		
		updatePoint.setTarget(pointField);
		
		infoPnLeft.add(pointLabel);
		infoPnLeft.add(pointField);
		
		bt_touch = new JButton("▼");
		bt_touch.addActionListener(this);
		bt_touch.setForeground(sets.getSelected_fore());
		if(sets.getSelected_button() != null)
		{
			bt_touch.setBackground(sets.getSelected_button());
		}
		if(usingFont != null)
			bt_touch.setFont(usingFont);
		infoPnRight.add(bt_touch);
		
		helpPanel = new JPanel();
		helpPanel.setBackground(sets.getSelected_back());
		upPanel.add(helpPanel, BorderLayout.SOUTH);
		helpPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		helpLabel = new JLabel(sets.getLang().getText(Language.REFLEX_SIMPLEHELP));
		helpLabel.setForeground(sets.getSelected_fore());
		if(usingFont != null)
			helpLabel.setFont(usingFont);
		helpPanel.add(helpLabel);
		
		
		
		startDialog = new JDialog(window);
		startDialog.addWindowListener(this);
		startDialog.setSize(450, 280);
		startDialog.setLocation((int)(sets.getScreenSize().getWidth()/2 - startDialog.getWidth()/2), (int)(sets.getScreenSize().getHeight()/2 - startDialog.getHeight()/2));
		startDialog.setLayout(new BorderLayout());
		
		
		start_mainPanel = new JPanel();
		start_mainPanel.setBackground(sets.getSelected_back());
		start_mainPanel.setBorder(new EtchedBorder());
		startDialog.add(start_mainPanel);
		start_mainPanel.setLayout(new BorderLayout());
		
		start_upPanel = new JPanel();
		start_downPanel = new JPanel();
		start_centerPanel = new JPanel();
		start_upPanel.setBackground(sets.getSelected_back());
		start_downPanel.setBackground(sets.getSelected_back());
		start_centerPanel.setBackground(sets.getSelected_back());
		
		start_mainPanel.add(start_upPanel, BorderLayout.NORTH);
		
		mainTab = new JTabbedPane();
		mainTab.addChangeListener(this);
		start_mainPanel.add(mainTab, BorderLayout.CENTER);
		mainTab.setBackground(sets.getSelected_back());
		mainTab.setForeground(sets.getSelected_fore());
		if(usingFont != null)
			mainTab.setFont(usingFont);
		
		start_noticePanel = new JPanel();
		start_noticePanel.setBackground(sets.getSelected_back());
		start_noticePanel.setForeground(sets.getSelected_fore());
		start_noticePanel.setLayout(new BorderLayout());
		mainTab.add(sets.getLang().getText(Language.NOTICE), start_noticePanel);
		start_notice_upPanel = new JPanel();
		start_notice_downPanel = new JPanel();
		start_notice_centerPanel = new JPanel();
		start_noticePanel.add(start_notice_upPanel, BorderLayout.NORTH);
		start_noticePanel.add(start_notice_centerPanel, BorderLayout.CENTER);
		start_noticePanel.add(start_notice_downPanel, BorderLayout.SOUTH);
		
		start_notice_downPanel.setBackground(sets.getSelected_back());
		start_notice_centerPanel.setBackground(sets.getSelected_back());
		start_notice_upPanel.setForeground(sets.getSelected_fore());
		start_notice_downPanel.setForeground(sets.getSelected_fore());
		start_notice_centerPanel.setForeground(sets.getSelected_fore());
		start_notice_centerPanel.setLayout(new BorderLayout());
		start_noticeArea = new OldBrowser(sets);
		start_noticeArea.setBackground(sets.getSelected_back());
		start_noticeArea.setForeground(sets.getSelected_fore());
		if(usingFont != null)
			start_noticeArea.setFont(usingFont);
		start_noticeArea.setEditable(false);
		start_noticeScroll = new JScrollPane(start_noticeArea);
		start_notice_centerPanel.add(start_noticeScroll);
		start_notice_downPanel.setLayout(new FlowLayout());
		
		bt_exit4 = new JButton(sets.getLang().getText(Language.EXIT));
		bt_next = new JButton(sets.getLang().getText(Language.STANDARD));
		bt_scenario = new JButton(sets.getLang().getText(Language.SCENARIO));
		bt_user_defined = new JButton(sets.getLang().getText(Language.USER_DEFINED));
		
		start_verLabel = new JLabel(getVersionString(true));		
		start_verLabel.addMouseListener(this);
		bt_next.addActionListener(this);
		bt_next.setForeground(sets.getSelected_fore());
		if(sets.getSelected_button() != null)
			bt_next.setBackground(sets.getSelected_button());
		if(usingFont != null)
			bt_next.setFont(usingFont);
		bt_exit4.addActionListener(this);
		bt_exit4.setForeground(sets.getSelected_fore());
		if(sets.getSelected_button() != null)
			bt_exit4.setBackground(sets.getSelected_button());
		if(usingFont != null)
			bt_exit4.setFont(usingFont);
		start_verLabel.setForeground(sets.getSelected_fore());
		if(usingFont != null)
			start_verLabel.setFont(usingFont);
		bt_scenario.addActionListener(this);
		bt_scenario.setForeground(sets.getSelected_fore());
		if(sets.getSelected_button() != null)
			bt_scenario.setBackground(sets.getSelected_button());
		if(usingFont != null)
			bt_scenario.setFont(usingFont);
		bt_user_defined.addActionListener(this);
		bt_user_defined.setForeground(sets.getSelected_fore());
		if(sets.getSelected_button() != null)
			bt_user_defined.setBackground(sets.getSelected_button());
		if(usingFont != null)
			bt_user_defined.setFont(usingFont);
		
		
		start_notice_downPanel.add(bt_next);
		start_notice_downPanel.add(bt_scenario);
		start_notice_downPanel.add(bt_user_defined);
		start_notice_downPanel.add(bt_exit4);
		
		start_notice_upPanel.setBackground(sets.getSelected_inside_back());
		start_notice_upPanel.setLayout(new BorderLayout());
		start_notice_upPanel.setBorder(new EtchedBorder());
		start_notice_descPanel = new JPanel();
		start_notice_descPanel.setBackground(sets.getSelected_inside_back());
		start_notice_upPanel.add(start_notice_descPanel, BorderLayout.EAST);
		start_notice_descPanel.setLayout(new FlowLayout());
		start_notice_descPanel.add(start_verLabel);
		start_notice_titlePanel = new JPanel();
		start_notice_titlePanel.setBackground(sets.getSelected_inside_back());
		start_notice_titlePanel.setLayout(new FlowLayout());
		start_notice_titleLabel = new JLabel(sets.getLang().getText(Language.REFLEX));
		start_notice_titleLabel.setForeground(sets.getSelected_fore());
		if(usingFont != null)
			start_notice_titleLabel.setFont(usingFontB);
		start_notice_titlePanel.add(start_notice_titleLabel);
		start_notice_upPanel.add(start_notice_titlePanel, BorderLayout.WEST);
		
		start_standardPanel = new JPanel();
		start_standardPanel.setBackground(sets.getSelected_back());
		start_standardPanel.setLayout(new BorderLayout());
		start_standardPanel.add(start_downPanel, BorderLayout.SOUTH);
		start_standardPanel.add(start_centerPanel, BorderLayout.CENTER);
		
		mainTab.add(sets.getLang().getText(Language.STANDARD), start_standardPanel);
		
		start_userDefinedPanel = new JPanel();
		start_userDefinedPanel.setBackground(sets.getSelected_back());
		start_userDefinedPanel.setLayout(new BorderLayout());
		
		start_userDefined_upPanel = new JPanel();
		start_userDefined_centerPanel = new JPanel();
		start_userDefined_downPanel = new JPanel();
		start_userDefined_upPanel.setBackground(sets.getSelected_back());
		start_userDefined_centerPanel.setBackground(sets.getSelected_back());
		start_userDefined_downPanel.setBackground(sets.getSelected_back());
		start_userDefined_centerPanel.setLayout(new BorderLayout());
		start_userDefinedArea = new JTextArea();
		start_userDefinedArea.setLineWrap(true);
		start_userDefinedArea.setBackground(sets.getSelected_inside_back());
		start_userDefinedArea.setForeground(sets.getSelected_fore());
		if(usingFont != null)
			start_userDefinedArea.setFont(usingFont);
		start_userDefinedScroll = new JScrollPane(start_userDefinedArea);
		start_userDefined_centerPanel.add(start_userDefinedScroll, BorderLayout.CENTER);
		start_userDefined_downPanel.setLayout(new FlowLayout());
		bt_start_userDefined2 = new JButton(sets.getLang().getText(Language.START));
		bt_exit2 = new JButton(sets.getLang().getText(Language.EXIT));
		bt_autoComplete = new JButton(sets.getLang().getText(Language.SUMMARY));
		bt_start_userDefined2.addActionListener(this);
		bt_exit2.addActionListener(this);
		bt_autoComplete.addActionListener(this);
		bt_start_userDefined2.setForeground(sets.getSelected_fore());
		bt_exit2.setForeground(sets.getSelected_fore());
		bt_autoComplete.setForeground(sets.getSelected_fore());
		if(usingFont != null)
		{
			bt_start_userDefined2.setFont(usingFont);
			bt_exit2.setFont(usingFont);
			bt_autoComplete.setFont(usingFont);
		}
		if(sets.getSelected_button() != null)
		{
			bt_start_userDefined2.setBackground(sets.getSelected_button());
			bt_exit2.setBackground(sets.getSelected_button());
			bt_autoComplete.setBackground(sets.getSelected_button());
		}
		start_userDefined_downPanel.add(bt_start_userDefined2);
		start_userDefined_downPanel.add(bt_autoComplete);
		start_userDefined_downPanel.add(bt_exit2);
		start_userDefined_upPanel.setLayout(new FlowLayout());
		start_userDefined_isScenario = new JCheckBox(sets.getLang().getText(Language.SCENARIO));
		start_userDefined_nameLabel = new JLabel(sets.getLang().getText(Language.NAME));
		start_userDefined_nameField = new JTextField(10);
		start_userDefined_nameLabel.setForeground(sets.getSelected_fore());
		start_userDefined_nameField.setForeground(sets.getSelected_fore());
		start_userDefined_nameField.setBackground(sets.getSelected_inside_back());
		start_userDefined_isScenario.setBackground(sets.getSelected_back());
		start_userDefined_isScenario.setForeground(sets.getSelected_fore());
		if(usingFont != null) 
		{
			start_userDefined_nameLabel.setFont(usingFont);
			start_userDefined_nameField.setFont(usingFont);
			start_userDefined_isScenario.setFont(usingFont);
		}
		try
		{
			start_userDefined_nameField.setText(System.getProperty("user.name"));
		}
		catch(Exception e)
		{
			
		}
		
		start_userDefined_upPanel.add(start_userDefined_nameLabel);
		start_userDefined_upPanel.add(start_userDefined_nameField);
		start_userDefined_upPanel.add(start_userDefined_isScenario);
		start_userDefinedPanel.add(start_userDefined_upPanel, BorderLayout.NORTH);
		start_userDefinedPanel.add(start_userDefined_centerPanel, BorderLayout.CENTER);
		start_userDefinedPanel.add(start_userDefined_downPanel, BorderLayout.SOUTH);
		start_userDefinedArea.setText(userDefined_first());		
		
		
		start_scenarioPanel = new JPanel();
		start_scenarioPanel.setBackground(sets.getSelected_back());
		start_scenarioPanel.setLayout(new BorderLayout());
		start_scenario_upPanel = new JPanel();
		start_scenario_centerPanel = new JPanel();
		start_scenario_downPanel = new JPanel();
		start_scenario_upPanel.setBackground(sets.getSelected_back());
		start_scenario_centerPanel.setBackground(sets.getSelected_back());
		start_scenario_downPanel.setBackground(sets.getSelected_back());
		start_scenarioPanel.add(start_scenario_upPanel, BorderLayout.NORTH);
		start_scenarioPanel.add(start_scenario_centerPanel, BorderLayout.CENTER);
		start_scenarioPanel.add(start_scenario_downPanel, BorderLayout.SOUTH);
		start_scenario_centerPanel.setLayout(new BorderLayout());
		start_scenarioList = new JList();
		start_scenarioList.addListSelectionListener(this);
		start_scenarioList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		start_scenarioList.setBackground(sets.getSelected_inside_back());
		start_scenarioList.setForeground(sets.getSelected_fore());
		if(usingFont != null) 
			start_scenarioList.setFont(usingFont);
		start_scenarioListScroll = new JScrollPane(start_scenarioList);
		
		start_scenario_nameLabel = new JLabel(sets.getLang().getText(Language.NAME));
		start_scenario_nameField = new JTextField(10);
		start_scenario_nameLabel.setForeground(sets.getSelected_fore());
		start_scenario_nameField.setForeground(sets.getSelected_fore());
		start_scenario_nameField.setBackground(sets.getSelected_inside_back());
		if(usingFont != null) 
		{
			start_scenario_nameLabel.setFont(usingFont);
			start_scenario_nameField.setFont(usingFont);
		}
		try
		{
			start_scenario_nameField.setText(System.getProperty("user.name"));
		}
		catch(Exception e)
		{
			
		}
		start_scenario_namePanel = new JPanel();
		start_scenario_namePanel.setBackground(sets.getSelected_back());
		start_scenario_namePanel.setLayout(new FlowLayout());
		start_scenario_namePanel.add(start_scenario_nameLabel);
		start_scenario_namePanel.add(start_scenario_nameField);
		start_scenario_description = new JTextArea();
		start_scenario_description.setLineWrap(true);
		start_scenario_description.setEditable(false);
		start_scenario_description.setBackground(sets.getSelected_inside_back());
		start_scenario_description.setForeground(sets.getSelected_fore());
		if(usingFont != null) 
		{
			start_scenario_description.setFont(usingFont);
		}
		start_scenario_descriptionScroll = new JScrollPane(start_scenario_description);
		start_scenario_descriptionPanel = new JPanel();
		start_scenario_descriptionPanel.setBackground(sets.getSelected_back());
		start_scenario_descriptionPanel.setLayout(new BorderLayout());
		start_scenario_descriptionPanel.add(start_scenario_descriptionScroll, BorderLayout.CENTER);		
		start_scenario_upPanel.setLayout(new BorderLayout());
		start_scenario_upPanel.add(start_scenario_namePanel);
		
		start_scenario_centerPanel.add(start_scenario_descriptionPanel, BorderLayout.CENTER);
		start_scenario_centerPanel.add(start_scenarioListScroll, BorderLayout.WEST);		
		scenarios = new Vector<ReflexScenario>();
		
		start_scenario_downPanel.setLayout(new FlowLayout());
		bt_start_scenario = new JButton(sets.getLang().getText(Language.START));
		bt_exit3 = new JButton(sets.getLang().getText(Language.EXIT));
		bt_viewOnEditor = new JButton(sets.getLang().getText(Language.EDIT));
		bt_start_scenario.addActionListener(this);
		bt_exit3.addActionListener(this);
		bt_viewOnEditor.addActionListener(this);
		bt_start_scenario.setForeground(sets.getSelected_fore());
		bt_viewOnEditor.setForeground(sets.getSelected_fore());
		bt_exit3.setForeground(sets.getSelected_fore());
		if(usingFont != null)
		{
			bt_start_scenario.setFont(usingFont);
			bt_exit3.setFont(usingFont);
			bt_viewOnEditor.setFont(usingFont);
		}
		if(sets.getSelected_button() != null)
		{
			bt_start_scenario.setBackground(sets.getSelected_button());
			bt_exit3.setBackground(sets.getSelected_button());
			bt_viewOnEditor.setBackground(sets.getSelected_button());
		}
		
		start_scenario_downPanel.add(bt_start_scenario);
		start_scenario_downPanel.add(bt_viewOnEditor);
		start_scenario_downPanel.add(bt_exit3);
		
		mainTab.add(sets.getLang().getText(Language.SCENARIO), start_scenarioPanel);
		mainTab.add(sets.getLang().getText(Language.USER_DEFINED), start_userDefinedPanel);		
		
		
		start_downloadPanel = new JPanel();
		// mainTab.add(sets.getLang().getText(Language.DOWNLOAD_PACK), start_downloadPanel);
		start_downloadPanel.setLayout(new BorderLayout());
		start_downloadPanel.setBackground(sets.getSelected_back());
		start_download_upPanel = new JPanel();
		start_download_centerPanel = new JPanel();
		start_download_downPanel = new JPanel();
		start_downloadPanel.add(start_download_upPanel, BorderLayout.NORTH);
		start_downloadPanel.add(start_download_centerPanel, BorderLayout.CENTER);
		start_downloadPanel.add(start_download_downPanel, BorderLayout.SOUTH);
		start_download_upPanel.setBackground(sets.getSelected_back());
		start_download_downPanel.setBackground(sets.getSelected_back());
		start_download_centerPanel.setBackground(sets.getSelected_back());
		start_download_centerPanel.setLayout(new BorderLayout());
		start_download_message = new JTextArea();
		start_download_message.setText(sets.getLang().getText(Language.CHECK_RELIABILITY) + "\n" + download_url1 + "\n" + download_url2 + "\n\n"
		+ sets.getLang().getText(Language.WILL_DOWNLOAD_AT) + " " + sets.getDefault_path() + "\n\n");
		start_download_message.setEditable(false);
		start_download_message.setLineWrap(true);
		start_download_message.setBackground(sets.getSelected_inside_back());
		start_download_message.setForeground(sets.getSelected_fore());
		try
		{
			start_download_message.append("\n" + get_imagepack_license() + "\n");
		}
		catch(Exception e)
		{
			
		}
		if(usingFont != null)
			start_download_message.setFont(usingFont);
		start_download_scroll = new JScrollPane(start_download_message);
		start_download_centerPanel.add(start_download_scroll, BorderLayout.CENTER);
		progress_download = new JProgressBar(JProgressBar.HORIZONTAL, 0, 1000);
		start_download_centerPanel.add(progress_download, BorderLayout.SOUTH);
		start_download_downPanel.setLayout(new FlowLayout());
		bt_download = new JButton(sets.getLang().getText(Language.DOWNLOAD));
		bt_download.addActionListener(this);
		bt_download.setForeground(sets.getSelected_fore());
		if(usingFont != null)
			bt_download.setFont(usingFont);
		if(sets.getSelected_button() != null)
			bt_download.setBackground(sets.getSelected_button());
		
		bt_setUrl = new JButton(sets.getLang().getText(Language.ADDRESS) + " " + sets.getLang().getText(Language.EDIT));
		bt_setUrl.addActionListener(this);
		bt_setUrl.setForeground(sets.getSelected_fore());
		if(usingFont != null)
			bt_setUrl.setFont(usingFont);
		if(sets.getSelected_button() != null)
			bt_setUrl.setBackground(sets.getSelected_button());
		
		bt_exit5 = new JButton(sets.getLang().getText(Language.EXIT));
		bt_exit5.addActionListener(this);
		bt_exit5.setForeground(sets.getSelected_fore());
		if(usingFont != null)
			bt_exit5.setFont(usingFont);
		if(sets.getSelected_button() != null)
			bt_exit5.setBackground(sets.getSelected_button());
		start_download_downPanel.add(bt_download);
		start_download_downPanel.add(bt_setUrl);
		start_download_downPanel.add(bt_exit5);
			
		start_upPanel.setLayout(new BorderLayout());
		
		start_buttonPanel = new JPanel();
		start_buttonPanel.setBackground(sets.getSelected_back());
		start_downPanel.setLayout(new BorderLayout());
		start_downPanel.add(start_buttonPanel, BorderLayout.SOUTH);
		start_buttonPanel.setLayout(new FlowLayout());
		
		if(sets.isUse_own_titleBar())
		{
			startDialog.setUndecorated(true);
			startDialog.setSize(455, 300);
			startDialog.setLocation((int)(sets.getScreenSize().getWidth()/2 - startDialog.getWidth()/2), (int)(sets.getScreenSize().getHeight()/2 - startDialog.getHeight()/2));
			start_titlePanel = new JPanel();
			start_titlePanel.setBackground(sets.getSelected_inside_back());
			start_titlePanel.setBorder(new EtchedBorder());
			start_titlePanel.setLayout(new FlowLayout());
			start_titleLabel = new JLabel(sets.getLang().getText(Language.REFLEX));
			start_titleLabel.setForeground(sets.getSelected_fore());
			if(usingFont != null)
				start_titleLabel.setFont(usingFont);
			start_titlePanel.add(start_titleLabel);
			start_titlePanel.addMouseListener(this);
			start_titlePanel.addMouseMotionListener(this);
			start_upPanel.add(start_titlePanel, BorderLayout.NORTH);
		}
		
		
		bt_start = new JButton(sets.getLang().getText(Language.START));
		bt_start.addActionListener(this);
		bt_exit = new JButton(sets.getLang().getText(Language.EXIT));
		bt_exit.addActionListener(this);
		
		if(usingFont != null)
			bt_start.setFont(usingFont);
		if(usingFont != null)
			bt_exit.setFont(usingFont);
		
		String[] difficultyModes;
		List<String> getShipNames = SpaceShip.spaceShipNameList(sets, grade_mode);
		List<Integer> getShipKeys = SpaceShip.spaceShipKeyIntsList(grade_mode);
		ships = new String[getShipNames.size()];
		shipKeys = new int[ships.length];
		for(int i=0; i<ships.length; i++)
		{
			ships[i] = getShipNames.get(i);
			shipKeys[i] = getShipKeys.get(i).intValue();
		}
		if(grade_mode >= 2)
		{		
			difficultyModes = new String[5];
		}
		else if(grade_mode >= 1)
		{
			difficultyModes = new String[4];
		}
		else
		{
			difficultyModes = new String[3];
			bt_viewOnEditor.setVisible(false);
		}
		long dif_calc = 0;
		for(int i=0; i<difficultyModes.length; i++)
		{
			dif_calc = (long)(Math.pow(10, i));
			
			difficultyModes[i] = Difficulty.intToString(dif_calc, 3.3);
		}
		
		combo_dif = new JComboBox(difficultyModes);
		combo_dif.setBackground(sets.getSelected_inside_back());
		combo_dif.setForeground(sets.getSelected_fore());
		combo_ship = new JComboBox(ships);
		combo_ship.setBackground(sets.getSelected_inside_back());
		combo_ship.setForeground(sets.getSelected_fore());
		if(usingFont != null)
		{
			combo_ship.setFont(usingFont);
			combo_dif.setFont(usingFont);
		}
		combo_ship.setSelectedIndex(0);
		combo_dif.setSelectedIndex(0);
		start_buttonPanel.add(combo_ship);
		start_buttonPanel.add(combo_dif);
		start_buttonPanel.add(bt_start);
		start_buttonPanel.add(bt_exit);
		
		
		start_scenario_selectShipCombo = new JComboBox(ships);
		start_scenario_selectShipCombo.setBackground(sets.getSelected_inside_back());
		start_scenario_selectShipCombo.setForeground(sets.getSelected_fore());
		if(usingFont != null)
			start_scenario_selectShipCombo.setFont(usingFont);
		start_scenario_descriptionPanel.add(start_scenario_selectShipCombo, BorderLayout.SOUTH);
		
		refresh_scenario(true);
		
		bt_start.setForeground(sets.getSelected_fore());
		bt_exit.setForeground(sets.getSelected_fore());
		
		if(sets.getSelected_button() != null)
		{
			bt_start.setBackground(sets.getSelected_back());
			bt_exit.setBackground(sets.getSelected_back());
		}
		
		start_centerPanel.setLayout(new BorderLayout());
		
		start_namePanel = new JPanel();
		start_namePanel.setBackground(sets.getSelected_back());
		start_centerPanel.add(start_namePanel, BorderLayout.NORTH);
		start_namePanel.setLayout(new FlowLayout());		
		start_nameLabel = new JLabel(sets.getLang().getText(Language.NAME));
		start_nameLabel.setForeground(sets.getSelected_fore());
		start_nameField = new JTextField(7);		
		start_nameField.setBackground(sets.getSelected_inside_back());
		start_nameField.setForeground(sets.getSelected_fore());
		try
		{
			start_nameField.setText(System.getProperty("user.name"));
		}
		catch(Exception e)
		{
			
		}
		start_namePanel.add(start_nameLabel);
		start_namePanel.add(start_nameField);
		if(usingFont != null)
		{
			start_nameLabel.setFont(usingFont);
			start_nameField.setFont(usingFont);
		}
		
		start_centerHelpPanel = new JPanel();
		start_centerPanel.add(start_centerHelpPanel, BorderLayout.CENTER);
		start_centerHelpPanel.setBackground(sets.getSelected_back());
		start_centerHelpPanel.setLayout(new BorderLayout());
		start_centerHelpArea = new JTextArea();
		start_centerHelpArea.setLineWrap(true);
		start_centerHelpArea.setEditable(false);
		start_centerHelpArea.setBackground(sets.getSelected_inside_back());
		start_centerHelpArea.setForeground(sets.getSelected_fore());
		if(usingFont != null)
			start_centerHelpArea.setFont(usingFont);
		start_centerHelpScroll = new JScrollPane(start_centerHelpArea);
		start_centerHelpPanel.add(start_centerHelpScroll);
		start_centerHelpArea.setText(sets.getLang().getText(Language.REFLEX_HELP));
		
		if(independence)
			start_upPanel.add(menuBar, BorderLayout.CENTER);
		
		start_todayPanel = new JPanel();		
		mainTab.add("!", start_todayPanel);
		start_todayPanel.setBackground(sets.getSelected_back());
		
		start_today_upPanel = new JPanel();
		start_today_centerPanel = new JPanel();
		start_today_downPanel = new JPanel();
		start_todayPanel.setLayout(new BorderLayout());
		start_todayPanel.add(start_today_upPanel, BorderLayout.NORTH);
		start_todayPanel.add(start_today_centerPanel, BorderLayout.CENTER);
		start_todayPanel.add(start_today_downPanel, BorderLayout.SOUTH);
		start_today_upPanel.setBackground(sets.getSelected_back());
		start_today_centerPanel.setBackground(sets.getSelected_back());
		start_today_downPanel.setBackground(sets.getSelected_back());
		start_today_area = new OldBrowser(sets);
		start_today_area.setEditable(false);
		start_today_area.setBackground(sets.getSelected_inside_back());
		start_today_area.setForeground(sets.getSelected_fore());		
		start_today_scroll = new JScrollPane(start_today_area);
		start_today_centerPanel.setLayout(new BorderLayout());
		start_today_centerPanel.add(start_today_scroll, BorderLayout.CENTER);
		start_today_upPanel.setLayout(new FlowLayout());
		start_today_nameLabel = new JLabel(sets.getLang().getText(Language.NAME));
		start_today_nameField = new JTextField(10);
		try
		{
			start_today_nameField.setText(System.getProperty("user.name"));
		}
		catch(Exception e)
		{
			
		}
		start_today_upPanel.add(start_today_nameLabel);
		start_today_upPanel.add(start_today_nameField);
		start_today_nameLabel.setForeground(sets.getSelected_fore());
		start_today_nameField.setForeground(sets.getSelected_fore());
		start_today_nameField.setBackground(sets.getSelected_inside_back());
		start_today_downPanel.setLayout(new FlowLayout());		
		bt_start_today = new JButton(sets.getLang().getText(Language.START));
		bt_exit6 = new JButton(sets.getLang().getText(Language.EXIT));
		bt_start_today.addActionListener(this);
		bt_exit6.addActionListener(this);
		bt_start_today.setForeground(sets.getSelected_fore());
		bt_exit6.setForeground(sets.getSelected_fore());
		if(usingFont != null)
		{
			start_today_area.setFont(usingFont);
			bt_start_today.setFont(usingFont);
			bt_exit6.setFont(usingFont);
			start_today_nameField.setFont(usingFont);
			start_today_nameLabel.setFont(usingFont);
		}
		if(sets.getSelected_button() != null)
		{
			bt_start_today.setBackground(sets.getSelected_button());
			bt_exit6.setBackground(sets.getSelected_button());
		}
		start_today_downPanel.add(bt_start_today);
		start_today_downPanel.add(bt_exit6);
		bt_start_today.setEnabled(false);
		
		
		
		menuBar.setBackground(sets.getSelected_inside_back());
		menuBar.setForeground(sets.getSelected_fore());
		
		menu_file = new JMenu(sets.getLang().getText(Language.MENU_FILE));
		menu_file.setBackground(sets.getSelected_back());
		menu_file.setForeground(sets.getSelected_fore());
		if(usingFont != null)
		{
			menu_file.setFont(usingFont);
		}
		menuBar.add(menu_file);
		
		menu_file_start = new JMenuItem(sets.getLang().getText(Language.START));
		menu_file_start.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, KeyEvent.CTRL_MASK));	
		menu_file_start.addActionListener(this);
		menu_file_start.setBackground(sets.getSelected_back());
		menu_file_start.setForeground(sets.getSelected_fore());
		if(usingFont != null)
		{
			menu_file_start.setFont(usingFont);
		}
		menu_file.add(menu_file_start);
		
		menu_file_start_userDefined = new JMenuItem(sets.getLang().getText(Language.EDIT) + " - " + sets.getLang().getText(Language.START));
		menu_file_start_userDefined.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, KeyEvent.ALT_MASK));	
		menu_file_start_userDefined.addActionListener(this);
		menu_file_start_userDefined.setBackground(sets.getSelected_back());
		menu_file_start_userDefined.setForeground(sets.getSelected_fore());
		if(usingFont != null)
		{
			menu_file_start_userDefined.setFont(usingFont);
		}
		//menu_file.add(menu_file_start_userDefined);
		if(grade_mode >= 1)
		{
			menu_file_start_userDefined.setVisible(true);
		}
		else
		{
			menu_file_start_userDefined.setVisible(false);
		}
		
		menu_file_load = new JMenuItem(sets.getLang().getText(Language.LOAD));
		menu_file_load.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, KeyEvent.CTRL_MASK));	
		menu_file_load.addActionListener(this);
		menu_file_load.setBackground(sets.getSelected_back());
		menu_file_load.setForeground(sets.getSelected_fore());
		if(usingFont != null)
		{
			menu_file_load.setFont(usingFont);
		}
		menu_file.add(menu_file_load);
		
		menu_file_manage = new JMenu(sets.getLang().getText(Language.MANAGEMENT));
		menu_file_manage.setBackground(sets.getSelected_back());
		menu_file_manage.setForeground(sets.getSelected_fore());
		if(usingFont != null)
		{
			menu_file_manage.setFont(usingFont);
		}
		menu_file.add(menu_file_manage);
		
		menu_manage_enableImage = new JCheckBoxMenuItem("Image");
		menu_manage_enableImage.setBackground(sets.getSelected_back());
		menu_manage_enableImage.setForeground(sets.getSelected_fore());
		if(usingFont != null)
		{
			menu_manage_enableImage.setFont(usingFont);
		}
		menu_manage_enableImage.addItemListener(this);
		menu_manage_enableImage.setSelected(true);
		menu_file_manage.add(menu_manage_enableImage);
		
		menu_manage_enableSound = new JCheckBoxMenuItem("Sound");
		menu_manage_enableSound.setBackground(sets.getSelected_back());
		menu_manage_enableSound.setForeground(sets.getSelected_fore());
		if(usingFont != null)
		{
			menu_manage_enableSound.setFont(usingFont);
		}
		menu_manage_enableSound.addItemListener(this);
		menu_file_manage.add(menu_manage_enableSound);
		
		menu_manage_enableImage.setSelected(image_allow);
		menu_manage_enableSound.setSelected(sound_allow);
		
		menu_manage_autoControl = new JCheckBoxMenuItem(sets.getLang().getText(Language.AI));
		menu_manage_autoControl.setBackground(sets.getSelected_back());
		menu_manage_autoControl.setForeground(sets.getSelected_fore());
		if(usingFont != null)
		{
			menu_manage_autoControl.setFont(usingFont);
		}
		menu_file_manage.add(menu_manage_autoControl);
		
		menu_manage_saveReplay = new JCheckBoxMenuItem(sets.getLang().getText(Language.REPLAY));
		menu_manage_saveReplay.setBackground(sets.getSelected_back());
		menu_manage_saveReplay.setForeground(sets.getSelected_fore());
		if(usingFont != null)
		{
			menu_manage_saveReplay.setFont(usingFont);
		}
		menu_file_manage.add(menu_manage_saveReplay);
		
		menu_manage_setIntervalReplay = new JMenuItem(sets.getLang().getText(Language.REPLAY) + " " + sets.getLang().getText(Language.DELAY));
		menu_manage_setIntervalReplay.addActionListener(this);
		menu_manage_setIntervalReplay.setBackground(sets.getSelected_back());
		menu_manage_setIntervalReplay.setForeground(sets.getSelected_fore());
		if(usingFont != null)
		{
			menu_manage_setIntervalReplay.setFont(usingFont);
		}
		menu_file_manage.add(menu_manage_setIntervalReplay);
		
		menu_manage_uninstall = new JMenuItem(sets.getLang().getText(Language.UNINSTALL));
		menu_manage_uninstall.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F9, KeyEvent.CTRL_MASK));
		menu_manage_uninstall.addActionListener(this);
		menu_manage_uninstall.setBackground(sets.getSelected_back());
		menu_manage_uninstall.setForeground(sets.getSelected_fore());
		if(usingFont != null)
		{
			menu_manage_uninstall.setFont(usingFont);
		}
		menu_file_manage.add(menu_manage_uninstall);
		
		menu_file_exit = new JMenuItem(sets.getLang().getText(Language.EXIT));
		menu_file_exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, KeyEvent.CTRL_MASK));
		menu_file_exit.addActionListener(this);
		menu_file_exit.setBackground(sets.getSelected_back());
		menu_file_exit.setForeground(sets.getSelected_fore());
		if(usingFont != null)
		{
			menu_file_exit.setFont(usingFont);
		}
		menu_file.add(menu_file_exit);
		
		menu_view = new JMenu(sets.getLang().getText(Language.VIEW));
		menu_view.setBackground(sets.getSelected_back());
		menu_view.setForeground(sets.getSelected_fore());
		if(usingFont != null)
		{
			menu_view.setFont(usingFont);
		}
		menuBar.add(menu_view);
		
		menu_view_message = new JMenuItem(sets.getLang().getText(Language.LOG));
		menu_view_message.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F8, KeyEvent.CTRL_MASK));
		menu_view_message.addActionListener(this);
		menu_view_message.setBackground(sets.getSelected_back());
		menu_view_message.setForeground(sets.getSelected_fore());
		if(usingFont != null)
		{
			menu_view_message.setFont(usingFont);
		}
		menu_view.add(menu_view_message);
		
		scenarioEditor = new ReflexScenarioEditor(startDialog, sets, this);
		
		
		
		menu_view_editor = new JMenuItem(sets.getLang().getText(Language.SCENARIO) + " " + sets.getLang().getText(Language.EDIT));
		menu_view_editor.addActionListener(this);
		menu_view_editor.setBackground(sets.getSelected_back());
		menu_view_editor.setForeground(sets.getSelected_fore());
		if(usingFont != null)
		{
			menu_view_editor.setFont(usingFont);
		}
		menu_view.add(menu_view_editor);
		
		replayPlayer = new ReflexReplayPlayer(startDialog, sets, this);
		
		menu_view_replayPlayer = new JMenuItem(sets.getLang().getText(Language.REPLAY) + " " + sets.getLang().getText(Language.PLAY));
		menu_view_replayPlayer.addActionListener(this);
		menu_view_replayPlayer.setBackground(sets.getSelected_back());
		menu_view_replayPlayer.setForeground(sets.getSelected_fore());
		if(usingFont != null)
		{
			menu_view_replayPlayer.setFont(usingFont);
		}
		menu_view.add(menu_view_replayPlayer);
		
		menu_view_check = new JMenuItem(sets.getLang().getText(Language.CODE_CHECKER));
		menu_view_check.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, KeyEvent.CTRL_MASK));
		menu_view_check.addActionListener(this);
		menu_view_check.setBackground(sets.getSelected_back());
		menu_view_check.setForeground(sets.getSelected_fore());
		if(usingFont != null)
		{
			menu_view_check.setFont(usingFont);
		}
		menu_view.add(menu_view_check);
		
		menu_help = new JMenu(sets.getLang().getText(Language.HELP));
		menu_help.setBackground(sets.getSelected_back());
		menu_help.setForeground(sets.getSelected_fore());
		if(usingFont != null)
		{
			menu_help.setFont(usingFont);
		}
		menuBar.add(menu_help);		
				
		menu_help_files = new JMenuItem();
		menu_help_files.addActionListener(this);
		menu_help_files.setBackground(sets.getSelected_back());
		menu_help_files.setForeground(sets.getSelected_fore());
		if(usingFont != null)
		{
			menu_help_files.setFont(usingFont);
		}
		menu_help.add(menu_help_files);
		menu_help_files.setText(sets.getLang().getText(Language.FILE_NEEDS));
		
		menu_help_about = new JMenuItem(sets.getLang().getText(Language.REFLEX) + " " + sets.getLang().getText(Language.IS));
		menu_help_about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, KeyEvent.CTRL_MASK));
		menu_help_about.addActionListener(this);
		menu_help_about.setBackground(sets.getSelected_back());
		menu_help_about.setForeground(sets.getSelected_fore());
		if(usingFont != null)
		{
			menu_help_about.setFont(usingFont);
		}
		menu_help.add(menu_help_about);
		
		finishDialog = new JDialog(window);
		finishDialog.setTitle(sets.getLang().getText(Language.RESULT));
		finishDialog.addWindowListener(this);
		finishDialog.setSize(400, 300);
		finishDialog.setLocation((int)(sets.getScreenSize().getWidth()/2 - finishDialog.getWidth()/2), (int)(sets.getScreenSize().getHeight()/2 - finishDialog.getHeight()/2));
		finishDialog.setLayout(new BorderLayout());
		finish_mainPanel = new JPanel();
		finish_mainPanel.setBackground(sets.getSelected_back());
		finish_mainPanel.setBorder(new EtchedBorder());
		finishDialog.add(finish_mainPanel);
		finish_mainPanel.setLayout(new BorderLayout());
		
		finish_upPanel = new JPanel();
		finish_downPanel = new JPanel();
		finish_centerPanel = new JPanel();
		finish_upPanel.setBackground(sets.getSelected_back());
		finish_downPanel.setBackground(sets.getSelected_back());
		finish_centerPanel.setBackground(sets.getSelected_back());
		
		finish_mainPanel.add(finish_upPanel, BorderLayout.NORTH);
		finish_mainPanel.add(finish_downPanel, BorderLayout.SOUTH);
		finish_mainPanel.add(finish_centerPanel, BorderLayout.CENTER);
		
		finish_downPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		String continue_label = "";
		continue_label = sets.getLang().getText(Language.CONTINUES);
		bt_continue = new JButton(continue_label);
		bt_continue.addActionListener(this);
		if(sets.getSelected_button() != null)
			bt_continue.setBackground(sets.getSelected_button());
		bt_continue.setForeground(sets.getSelected_fore());
		if(usingFont != null)
			bt_continue.setFont(usingFont);
		
		bt_finish_ok = new JButton(sets.getLang().getText(Language.EXIT));
		bt_finish_ok.addActionListener(this);
		if(usingFont != null)
			bt_finish_ok.setFont(usingFont);
		
		bt_finish_ok.setForeground(sets.getSelected_fore());
		if(sets.getSelected_button() != null)
		{
			bt_finish_ok.setBackground(sets.getSelected_button());
		}
		
		bt_event_open = new JButton(sets.getLang().getText(Language.EVENT));
		bt_event_open.addActionListener(this);
		if(usingFont != null)
			bt_event_open.setFont(usingFont);
		
		bt_event_open.setForeground(sets.getSelected_fore());
		if(sets.getSelected_button() != null)
		{
			bt_event_open.setBackground(sets.getSelected_button());
		}
		
		finish_resultPanel = new JPanel();
		finish_resultPanel.setBackground(sets.getSelected_back());
		finish_upPanel.setLayout(new BorderLayout());
		finish_upPanel.add(finish_resultPanel, BorderLayout.SOUTH);
		
		finish_resultPanel.setLayout(new FlowLayout());
		finish_resultLabel = new JLabel(sets.getLang().getText(Language.RESULT));
		finish_resultLabel.setForeground(sets.getSelected_fore());
		if(usingFont != null)
			finish_resultLabel.setFont(usingFont);
		finish_resultField = new JTextField(25);
		finish_resultField.setBackground(sets.getSelected_inside_back());
		finish_resultField.setForeground(sets.getSelected_fore());
		if(usingFont != null)
			finish_resultField.setFont(usingFont);
		finish_resultField.setEditable(false);
		finish_resultField.setBorder(new EtchedBorder());
		finish_resultPanel.add(finish_resultLabel);
		finish_resultPanel.add(finish_resultField);
		
		JPanel pnFinCenter1, pnFinCenter2;
		pnFinCenter1 = new JPanel();
		pnFinCenter2 = new JPanel();
		pnFinCenter1.setBackground(sets.getSelected_back());
		pnFinCenter2.setBackground(sets.getSelected_back());
		
		finish_layout = new CardLayout();
		finish_centerPanel.setLayout(finish_layout);
		
		finish_centerPanel.add(pnFinCenter1, "fin1");
		finish_centerPanel.add(pnFinCenter2, "fin2");
		
		finish_pns = new JPanel[7];
		pnFinCenter1.setLayout(new GridLayout(finish_pns.length, 1));
		for(int i=0; i<finish_pns.length; i++)
		{
			finish_pns[i] = new JPanel();
			finish_pns[i].setBackground(sets.getSelected_back());
			finish_pns[i].setLayout(new FlowLayout(FlowLayout.LEFT));			
			pnFinCenter1.add(finish_pns[i]);
		}
		
		finish_nameLabel = new JLabel(sets.getLang().getText(Language.NAME));
		finish_nameField = new JTextField(10);
		finish_nameField.setEditable(false);
		finish_nameField.setBorder(new EtchedBorder());
		finish_nameLabel.setForeground(sets.getSelected_fore());
		finish_nameField.setBackground(sets.getSelected_inside_back());
		finish_nameField.setForeground(sets.getSelected_fore());
		if(usingFont != null)
		{
			finish_nameLabel.setFont(usingFont);
			finish_nameField.setFont(usingFont);
		}
		finish_pns[1].add(finish_nameLabel);
		finish_pns[1].add(finish_nameField);
		
		finish_catchEnemyLabel = new JLabel(sets.getLang().getText(Language.CATCH) + sets.getLang().getText(Language.ENEMY));
		finish_catchEnemyLabel.setForeground(sets.getSelected_fore());
		finish_catchEnemyField = new JTextField(25);
		finish_catchEnemyField.setForeground(sets.getSelected_fore());
		finish_catchEnemyField.setBackground(sets.getSelected_inside_back());
		if(usingFont != null)
			finish_catchEnemyLabel.setFont(usingFont);
		if(usingFont != null)
			finish_catchEnemyField.setFont(usingFont);
		finish_catchEnemyField.setEditable(false);
		finish_catchEnemyField.setBorder(new EtchedBorder());
		finish_pns[2].add(finish_catchEnemyLabel);
		finish_pns[2].add(finish_catchEnemyField);
		
		finish_catchItemLabel = new JLabel(sets.getLang().getText(Language.CATCH) + sets.getLang().getText(Language.ITEM));
		finish_catchItemLabel.setForeground(sets.getSelected_fore());
		finish_catchItemField = new JTextField(25);
		finish_catchItemField.setForeground(sets.getSelected_fore());
		finish_catchItemField.setBackground(sets.getSelected_inside_back());
		if(usingFont != null)
			finish_catchItemLabel.setFont(usingFont);
		if(usingFont != null)
			finish_catchItemField.setFont(usingFont);
		finish_catchItemField.setEditable(false);
		finish_catchItemField.setBorder(new EtchedBorder());
		finish_pns[3].add(finish_catchItemLabel);
		finish_pns[3].add(finish_catchItemField);
		
		finish_authLabel = new JLabel(sets.getLang().getText(Language.AUTHORITY));
		finish_authField = new JTextField(20);
		finish_authField2= new JTextField(20);
		bt_authCopy  = new JButton(sets.getLang().getText(Language.COPY_CLIPBOARD));
		bt_authCopy2 = new JButton(sets.getLang().getText(Language.COPY_CLIPBOARD));
		bt_authCopy.addActionListener(this);
		bt_authCopy2.addActionListener(this);
		finish_authLabel.setForeground(sets.getSelected_fore());
		finish_authField.setForeground(sets.getSelected_fore());
		finish_authField2.setForeground(sets.getSelected_fore());
		bt_authCopy.setForeground(sets.getSelected_fore());
		bt_authCopy2.setForeground(sets.getSelected_fore());
		finish_authField.setBackground(sets.getSelected_inside_back());
		finish_authField2.setBackground(sets.getSelected_inside_back());
		if(sets.getSelected_button() != null)
		{
			bt_authCopy.setBackground(sets.getSelected_button());
			bt_authCopy2.setBackground(sets.getSelected_button());
		}
		finish_authField.setEditable(false);
		finish_authField.setBorder(new EtchedBorder());
		finish_authField2.setEditable(false);
		finish_authField2.setBorder(new EtchedBorder());
		if(usingFont != null)
		{
			finish_authLabel.setFont(usingFont);
			finish_authField.setFont(usingFont);
			finish_authField2.setFont(usingFont);
			bt_authCopy.setFont(usingFont);
			bt_authCopy2.setFont(usingFont);
		}
		
		finish_ta = new JTextArea();
		finish_ta.setEditable(false);
		finish_ta.setBackground(sets.getSelected_back());
		finish_ta.setForeground(sets.getSelected_fore());
		pnFinCenter2.setLayout(new BorderLayout());
		pnFinCenter2.add(new JScrollPane(finish_ta), BorderLayout.CENTER);
		
		JPanel fininsh_pnAuth = new JPanel();
		fininsh_pnAuth.setLayout(new BorderLayout());
		fininsh_pnAuth.add(finish_authField2, BorderLayout.CENTER);
		fininsh_pnAuth.add(bt_authCopy2, BorderLayout.EAST);
		pnFinCenter2.add(fininsh_pnAuth, BorderLayout.SOUTH);
		
		finish_pns[5].setLayout(new BorderLayout());
		
		JPanel pn5c, pn5r;
		pn5c = new JPanel();
		pn5r = new JPanel();
		pn5c.setBackground(sets.getSelected_back());
		pn5r.setBackground(sets.getSelected_back());
		pn5c.setLayout(new FlowLayout(FlowLayout.LEFT));
		pn5r.setLayout(new FlowLayout(FlowLayout.RIGHT));
		finish_pns[5].add(pn5c, BorderLayout.CENTER);
		finish_pns[5].add(pn5r, BorderLayout.EAST);
		
		pn5c.add(finish_authLabel);
		pn5c.add(finish_authField);
		pn5r.add(bt_authCopy);
		
		//bt_continue.setVisible(false);
		bt_saveReplay = new JButton(sets.getLang().getText(Language.REPLAY));
		bt_saveReplay.addActionListener(this);
		if(sets.getSelected_button() != null)
			bt_saveReplay.setBackground(sets.getSelected_button());
		bt_saveReplay.setForeground(sets.getSelected_fore());
		if(usingFont != null)
			bt_saveReplay.setFont(usingFont);
		
		finish_downPanel.add(bt_saveReplay);
		bt_saveReplay.setVisible(false);
		
		bt_saveState = new JButton(sets.getLang().getText(Language.SAVE));
		bt_saveState.addActionListener(this);
		if(sets.getSelected_button() != null)
			bt_saveState.setBackground(sets.getSelected_button());
		bt_saveState.setForeground(sets.getSelected_fore());
		if(usingFont != null)
			bt_saveState.setFont(usingFont);
		// finish_pns[6].add(bt_saveState);
		// finish_pns[6].add(bt_event_open);
		finish_downPanel.add(bt_saveState);
		finish_downPanel.add(bt_event_open);
		bt_saveState.setVisible(false);
		
		finish_downPanel.add(bt_continue);
		finish_downPanel.add(bt_finish_ok);
		
		if(sets.isUse_own_titleBar())
		{
			finishDialog.setUndecorated(true);
			finishDialog.setSize(400, 300);
			finishDialog.setLocation((int)(sets.getScreenSize().getWidth()/2 - finishDialog.getWidth()/2), (int)(sets.getScreenSize().getHeight()/2 - finishDialog.getHeight()/2));
			finish_titlePanel = new JPanel();
			finish_titlePanel.setBackground(sets.getSelected_inside_back());
			finish_titlePanel.setBorder(new EtchedBorder());
			finish_titlePanel.setLayout(new FlowLayout());
			finish_titleLabel = new JLabel(sets.getLang().getText(Language.REFLEX));
			finish_titleLabel.setForeground(sets.getSelected_fore());
			if(usingFont != null)
				finish_titleLabel.setFont(usingFont);
			finish_titlePanel.add(finish_titleLabel);
			finish_titlePanel.addMouseListener(this);
			finish_titlePanel.addMouseMotionListener(this);
			finish_upPanel.add(finish_titlePanel, BorderLayout.NORTH);
		}	
		
		aboutDialog = new JDialog(startDialog, true);
		aboutDialog.setSize(300, 170);
		aboutDialog.setLocation((int)(sets.getScreenSize().getWidth()/2 - aboutDialog.getWidth()/2), (int)(sets.getScreenSize().getHeight()/2 - aboutDialog.getHeight()/2));
		aboutDialog.addWindowListener(this);
		aboutDialog.setUndecorated(true);
		aboutDialog.getContentPane().setLayout(new BorderLayout());
		about_mainPanel = new JPanel();
		aboutDialog.getContentPane().add(about_mainPanel);
		about_mainPanel.setBackground(sets.getSelected_back());
		about_mainPanel.setBorder(new EtchedBorder());
		about_mainPanel.setLayout(new BorderLayout());
		about_upPanel = new JPanel();
		about_downPanel = new JPanel();
		about_centerPanel = new JPanel();
		about_titlePanel = new JPanel();
		about_upPanel.setBackground(sets.getUnselected_inside_back());
		about_titlePanel.setBackground(sets.getSelected_inside_back());
		about_titlePanel.setBorder(new EtchedBorder());
		about_centerPanel.setBackground(sets.getSelected_back());
		about_downPanel.setBackground(sets.getSelected_back());
		about_mainPanel.add(about_upPanel, BorderLayout.NORTH);
		about_mainPanel.add(about_centerPanel, BorderLayout.CENTER);
		about_mainPanel.add(about_downPanel, BorderLayout.SOUTH);
		about_centerPanel.setLayout(new BorderLayout());
		about_downPanel.setLayout(new FlowLayout());
		about_upPanel.setLayout(new BorderLayout());
		about_upPanel.add(about_titlePanel, BorderLayout.NORTH);
		about_gapPanel = new JPanel();
		about_gapPanel.setBackground(sets.getUnselected_inside_back());
		about_upPanel.add(about_gapPanel, BorderLayout.CENTER);
		about_titlePanel.addMouseListener(this);
		about_titlePanel.addMouseMotionListener(this);
		about_titlePanel.setLayout(new FlowLayout());
		about_titleLabel = new JLabel(sets.getLang().getText(Language.REFLEX));
		about_titleLabel.setForeground(sets.getSelected_fore());
		if(usingFont != null) about_titleLabel.setFont(usingFont);
		about_titlePanel.add(about_titleLabel);
		
		about_centerLabel = new JLabel(sets.getLang().getText(Language.REFLEX));
		if(usingFontName != null)
			about_centerLabel.setFont(new Font(usingFontName, Font.BOLD, 30));
		else
			about_centerLabel.setFont(new Font("dialog", Font.BOLD, 30));
		about_centerLabel.setForeground(sets.getSelected_fore());
		about_centerLabelPanel = new JPanel();
		about_centerLabelPanel.setLayout(new BorderLayout());
		about_centerPanel.add(about_centerLabelPanel, BorderLayout.CENTER);
		about_centerLabelPanel.setBackground(sets.getSelected_back());
		about_editionStringPanel = new JPanel();
		about_titleStringPanel = new JPanel();
		about_centerLabelPanel.add(about_editionStringPanel, BorderLayout.SOUTH);
		about_centerLabelPanel.add(about_titleStringPanel, BorderLayout.CENTER);
		about_titleStringPanel.setLayout(new FlowLayout());
		about_editionStringPanel.setLayout(new FlowLayout());
		about_titleStringPanel.setBackground(sets.getSelected_inside_back());
		about_editionStringPanel.setBackground(sets.getSelected_inside_back());
		about_titleStringPanel.add(about_centerLabel);
		about_editionLabel = new JLabel();
		about_editionLabel.addMouseListener(this);
		about_editionLabel.setText("BASIC");
		about_editionLabel.setForeground(Color.GRAY);
		about_editionStringPanel.add(about_editionLabel);
		about_versionPanel = new JPanel();
		about_versionPanel.setLayout(new FlowLayout());
		about_versionPanel.setBackground(sets.getUnselected_inside_back());
		about_versionLabel = new JLabel(getVersionString(true));
		about_versionLabel.setForeground(sets.getSelected_fore());
		if(usingFont != null)
			about_versionLabel.setFont(usingFont);
		about_versionPanel.add(about_versionLabel);
		about_centerPanel.add(about_versionPanel, BorderLayout.SOUTH);
		
		
		bt_aboutClose = new JButton(sets.getLang().getText(Language.CLOSE));
		bt_aboutClose.addActionListener(this);
		bt_aboutClose.setForeground(sets.getSelected_fore());
		if(usingFont != null)
			bt_aboutClose.setFont(usingFont);
		if(sets.getSelected_button() != null)
			bt_aboutClose.setBackground(sets.getSelected_button());
		about_downPanel.add(bt_aboutClose);
		
		userDefinedDialog = new JDialog(startDialog, true);
		userDefinedDialog.setSize(400, 300);
		userDefinedDialog.setLocation((int)(sets.getScreenSize().getWidth()/2 - userDefinedDialog.getWidth()/2), (int)(sets.getScreenSize().getHeight()/2 - userDefinedDialog.getHeight()/2));
		userDefinedDialog.getContentPane().setLayout(new BorderLayout());
		userDefinedDialog.setUndecorated(true);
		userDefined_mainPanel = new JPanel();
		userDefinedDialog.getContentPane().add(userDefined_mainPanel, BorderLayout.CENTER);
		userDefined_mainPanel.setLayout(new BorderLayout());
		userDefined_mainPanel.setBorder(new EtchedBorder());
		userDefined_mainPanel.setBackground(sets.getSelected_back());
		userDefined_upPanel = new JPanel();
		userDefined_downPanel = new JPanel();
		userDefined_centerPanel = new JPanel();
		userDefined_mainPanel.add(userDefined_upPanel, BorderLayout.NORTH);
		userDefined_mainPanel.add(userDefined_downPanel, BorderLayout.SOUTH);
		userDefined_mainPanel.add(userDefined_centerPanel, BorderLayout.CENTER);
		userDefined_upPanel.setBackground(sets.getSelected_back());
		userDefined_centerPanel.setBackground(sets.getSelected_back());
		userDefined_downPanel.setBackground(sets.getSelected_back());
		
		userDefined_upPanel.setLayout(new BorderLayout());
		userDefined_namePanel = new JPanel();
		userDefined_namePanel.setBackground(sets.getSelected_back());
		userDefined_upPanel.add(userDefined_namePanel, BorderLayout.SOUTH);
		userDefined_namePanel.setLayout(new FlowLayout());
		userDefined_nameLabel = new JLabel(sets.getLang().getText(Language.NAME));
		userDefined_nameLabel.setForeground(sets.getSelected_fore());
		userDefined_nameField = new JTextField(10);
		userDefined_nameField.setForeground(sets.getSelected_fore());
		userDefined_nameField.setBackground(sets.getSelected_inside_back());
		if(usingFont != null)
		{
			userDefined_nameLabel.setFont(usingFont);
			userDefined_nameField.setFont(usingFont);
		}
		userDefined_namePanel.add(userDefined_nameLabel);
		userDefined_namePanel.add(userDefined_nameField);
		
		userDefined_centerPanel.setLayout(new BorderLayout());
		userDefined_tab = new JTabbedPane();
		userDefined_tab.setBackground(sets.getSelected_back());
		userDefined_tab.setForeground(sets.getSelected_fore());
		userDefined_centerPanel.add(userDefined_tab, BorderLayout.CENTER);
		userDefined_area = new JTextArea();
		userDefined_area.setBackground(sets.getSelected_inside_back());
		userDefined_area.setForeground(sets.getSelected_fore());
		userDefined_area.setLineWrap(true);
		userDefined_area.setText(userDefined_first());
		if(usingFont != null)
		{
			userDefined_area.setFont(usingFont);
			userDefined_tab.setFont(usingFont);
		}
		userDefined_scroll = new JScrollPane(userDefined_area);
		userDefined_tab.add(sets.getLang().getText(Language.PLAYER), userDefined_scroll);
		
		userDefined_downPanel.setLayout(new FlowLayout());
		bt_start_userDefined = new JButton(sets.getLang().getText(Language.START));
		bt_close_userDefined = new JButton(sets.getLang().getText(Language.CLOSE));
		bt_start_userDefined.addActionListener(this);
		bt_close_userDefined.addActionListener(this);
		bt_start_userDefined.setForeground(sets.getSelected_fore());
		bt_close_userDefined.setForeground(sets.getSelected_fore());
		if(usingFont != null)
		{
			bt_start_userDefined.setFont(usingFont);
			bt_close_userDefined.setFont(usingFont);
		}
		if(sets.getSelected_button() != null)
		{
			bt_start_userDefined.setBackground(sets.getSelected_button());
			bt_close_userDefined.setBackground(sets.getSelected_button());
		}
		userDefined_downPanel.add(bt_start_userDefined);
		userDefined_downPanel.add(bt_close_userDefined);		
					
		bt_runMacro = new JButton(sets.getLang().getText(Language.RUN));
		bt_runMacro.addActionListener(this);
		bt_runMacro.setForeground(sets.getSelected_fore());
		if(usingFont != null)
			bt_runMacro.setFont(usingFont);
		if(sets.getSelected_button() != null)
			bt_runMacro.setBackground(sets.getSelected_button());
		
		bt_helpMacro = new JButton(sets.getLang().getText(Language.HELP));
		bt_helpMacro.addActionListener(this);
		bt_helpMacro.setForeground(sets.getSelected_fore());
		if(usingFont != null)
			bt_helpMacro.setFont(usingFont);
		if(sets.getSelected_button() != null)
			bt_helpMacro.setBackground(sets.getSelected_button());
		
		needfileDialog = new JDialog(startDialog, true);
		needfileDialog.setUndecorated(true);
		needfileDialog.setSize(620, 420);
		needfileDialog.setLocation((int)(sets.getScreenSize().getWidth()/2 - needfileDialog.getWidth()/2), (int)(sets.getScreenSize().getHeight()/2 - needfileDialog.getHeight()/2));
		needfileDialog.getContentPane().setLayout(new BorderLayout());
		needfile_mainPanel = new JPanel();
		needfileDialog.getContentPane().add(needfile_mainPanel, BorderLayout.CENTER);
		needfile_mainPanel.setLayout(new BorderLayout());
		needfile_mainPanel.setBorder(new EtchedBorder());
		needfile_mainPanel.setBackground(sets.getSelected_back());
		needfile_upPanel = new JPanel();
		needfile_centerPanel = new JPanel();
		needfile_downPanel = new JPanel();
		needfile_mainPanel.add(needfile_upPanel, BorderLayout.NORTH);
		needfile_mainPanel.add(needfile_centerPanel, BorderLayout.CENTER);
		needfile_mainPanel.add(needfile_downPanel, BorderLayout.SOUTH);
		needfile_upPanel.setBackground(sets.getSelected_back());
		needfile_centerPanel.setBackground(sets.getSelected_back());
		needfile_downPanel.setBackground(sets.getSelected_back());
		needfile_centerPanel.setLayout(new BorderLayout());
		needfile_split = new JSplitPane();
		needfile_split.setBackground(sets.getSelected_back());
		needfile_split.setForeground(sets.getSelected_fore());
		needfile_centerPanel.add(needfile_split);
		needfile_fileList = new JTextArea();
		needfile_fileList.setBackground(sets.getSelected_inside_back());
		needfile_fileList.setForeground(sets.getSelected_fore());
		needfile_fileList.setEditable(false);
		needfile_fileList.setLineWrap(true);
		needfile_fileScroll = new JScrollPane(needfile_fileList);
		needfile_split.setRightComponent(needfile_fileScroll);		
		needfile_descList = new JTextArea();
		needfile_descList.setBackground(sets.getSelected_inside_back());
		needfile_descList.setForeground(sets.getSelected_fore());
		needfile_descList.setEditable(false);
		needfile_descList.setLineWrap(true);
		needfile_descScroll = new JScrollPane(needfile_descList);
		needfile_split.setLeftComponent(needfile_descScroll);		
		needfile_downPanel.setLayout(new FlowLayout());
		bt_close_needfile = new JButton(sets.getLang().getText(Language.CLOSE));
		bt_close_needfile.setForeground(sets.getSelected_fore());
		if(sets.getSelected_button() != null) bt_close_needfile.setBackground(sets.getSelected_button());
		if(usingFont != null)
		{
			needfile_fileList.setFont(usingFont);
			needfile_descList.setFont(usingFont);
			bt_close_needfile.setFont(usingFont);
		}
		bt_close_needfile.addActionListener(new ActionListener()
		{			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				needfileDialog.setVisible(false);				
			}
		});
		needfile_downPanel.add(bt_close_needfile);
		needfile_upPanel.setLayout(new BorderLayout());
		needfile_titlePanel = new JPanel();
		needfile_upPanel.add(needfile_titlePanel);
		needfile_titlePanel.setBorder(new EtchedBorder());
		needfile_titlePanel.addMouseListener(this);
		needfile_titlePanel.addMouseMotionListener(this);
		needfile_titlePanel.setBackground(sets.getSelected_inside_back());
		needfile_titlePanel.setLayout(new FlowLayout());
		needfile_titleLabel = new JLabel();
		needfile_titleLabel.setText(sets.getLang().getText(Language.FILE_NEEDS));
		needfile_titleLabel.setForeground(sets.getSelected_fore());
		if(usingFont != null) needfile_titleLabel.setFont(usingFont);
		needfile_titlePanel.add(needfile_titleLabel);
		
		autoUserDefinedDialog = new JDialog(startDialog, true);
		autoUserDefinedDialog.setUndecorated(true);
		autoUserDefinedDialog.setSize(500, 400);
		autoUserDefinedDialog.setLocation((int)(sets.getScreenSize().getWidth()/2 - autoUserDefinedDialog.getWidth()/2), (int)(sets.getScreenSize().getHeight()/2 - autoUserDefinedDialog.getHeight()/2));
		autoUserDefinedDialog.getContentPane().setLayout(new BorderLayout());
		autoUserDefined_mainPanel = new JPanel();
		autoUserDefined_mainPanel.setBackground(sets.getSelected_back());
		autoUserDefinedDialog.getContentPane().add(autoUserDefined_mainPanel, BorderLayout.CENTER);
		autoUserDefined_mainPanel.setLayout(new BorderLayout());
		autoUserDefined_mainPanel.setBorder(new EtchedBorder());
		autoUserDefined_upPanel = new JPanel();
		autoUserDefined_downPanel = new JPanel();
		autoUserDefined_centerPanel = new JPanel();
		autoUserDefined_upPanel.setBackground(sets.getSelected_back());
		autoUserDefined_centerPanel.setBackground(sets.getSelected_back());
		autoUserDefined_downPanel.setBackground(sets.getSelected_back());
		autoUserDefined_mainPanel.add(autoUserDefined_upPanel, BorderLayout.NORTH);
		autoUserDefined_mainPanel.add(autoUserDefined_centerPanel, BorderLayout.CENTER);
		autoUserDefined_mainPanel.add(autoUserDefined_downPanel, BorderLayout.SOUTH);
		autoUserDefined_upPanel.setLayout(new BorderLayout());
		autoUserDefined_titlePanel = new JPanel();
		autoUserDefined_upPanel.add(autoUserDefined_titlePanel, BorderLayout.CENTER);
		autoUserDefined_titlePanel.setLayout(new FlowLayout());
		autoUserDefined_titlePanel.setBackground(sets.getSelected_inside_back());
		autoUserDefined_titlePanel.addMouseListener(this);
		autoUserDefined_titlePanel.addMouseMotionListener(this);
		autoUserDefined_titleLabel = new JLabel(sets.getLang().getText(Language.SUMMARY));
		autoUserDefined_titleLabel.setForeground(sets.getSelected_fore());
		if(usingFont != null)
		{
			autoUserDefined_titleLabel.setFont(usingFont);
		}
		autoUserDefined_titlePanel.add(autoUserDefined_titleLabel);
		autoUserDefined_downPanel.setLayout(new FlowLayout());
		bt_closeAutoUserDefined = new JButton(sets.getLang().getText(Language.CLOSE));
		bt_acceptAutoUserDefined = new JButton(sets.getLang().getText(Language.ACCEPT));
		bt_closeAutoUserDefined.addActionListener(this);
		bt_acceptAutoUserDefined.addActionListener(this);
		bt_closeAutoUserDefined.setForeground(sets.getSelected_fore());
		bt_acceptAutoUserDefined.setForeground(sets.getSelected_fore());
		if(usingFont != null)
		{
			bt_closeAutoUserDefined.setFont(usingFont);
			bt_acceptAutoUserDefined.setFont(usingFont);
		}
		if(sets.getSelected_button() != null)
		{
			bt_closeAutoUserDefined.setBackground(sets.getSelected_button());
			bt_acceptAutoUserDefined.setBackground(sets.getSelected_button());
		}
		autoUserDefined_downPanel.add(bt_acceptAutoUserDefined);
		autoUserDefined_downPanel.add(bt_closeAutoUserDefined);		
		autoUserDefined_centerPanel.setLayout(new BorderLayout());
		autoUserDefined_contentPanel = new JPanel();
		autoUserDefined_contentPanel.setBackground(sets.getSelected_back());
		autoUserDefined_contentScroll = new JScrollPane(autoUserDefined_contentPanel);
		autoUserDefined_centerPanel.add(autoUserDefined_contentScroll, BorderLayout.CENTER);
		autoUserDefined_contents = new JPanel[43];
		AUTO_LAST = autoUserDefined_contents.length - 1;
		autoUserDefined_contentPanel.setLayout(new GridLayout(autoUserDefined_contents.length, 1));
		autoUserDefined_labels = new JLabel[autoUserDefined_contents.length];
		autoUserDefined_fields = new JTextField[autoUserDefined_contents.length];
		FlowLayout newFlowLayout;
		for(int i=0; i<autoUserDefined_contents.length; i++)
		{
			newFlowLayout = new FlowLayout();
			newFlowLayout.setAlignment(FlowLayout.LEFT);
			autoUserDefined_contents[i] = new JPanel();
			autoUserDefined_contents[i].setBackground(sets.getSelected_back());
			autoUserDefined_contents[i].setLayout(newFlowLayout);
			autoUserDefined_labels[i] = new JLabel();
			autoUserDefined_fields[i] = new JTextField(10);
			autoUserDefined_labels[i].setForeground(sets.getSelected_fore());
			autoUserDefined_fields[i].setForeground(sets.getSelected_fore());
			autoUserDefined_fields[i].setBackground(sets.getSelected_inside_back());
			if(usingFont != null)
			{
				autoUserDefined_labels[i].setFont(usingFont);
				autoUserDefined_fields[i].setFont(usingFont);
			}
			autoUserDefined_contents[i].add(autoUserDefined_fields[i]);
			autoUserDefined_contents[i].add(autoUserDefined_labels[i]);
			
			autoUserDefined_contentPanel.add(autoUserDefined_contents[i]);
		}
		autoUserDefined_labels[AUTO_LAST].setText(sets.getLang().getText(Language.AUTHORITY));		
		
		autoUserDefined_labels[AUTO_SHIPNAME].setText(sets.getLang().getText(Language.SHIP_NAME));
		autoUserDefined_labels[AUTO_SHIP_HP].setText(sets.getLang().getText(Language.SHIP_HP));
		autoUserDefined_labels[AUTO_SHIP_SHAPE].setText(sets.getLang().getText(Language.SHAPE) + " (rectangle, circle)");
		autoUserDefined_labels[AUTO_SHIP_ENERGY].setText(sets.getLang().getText(Language.SHIP_E));
		autoUserDefined_labels[AUTO_SHIP_SIZE].setText(sets.getLang().getText(Language.SHIP_SIZE));
		autoUserDefined_labels[AUTO_SHIP_SPEED].setText(sets.getLang().getText(Language.SHIP_SPEED));
		autoUserDefined_labels[AUTO_WEAPON_1_DAMAGE].setText(sets.getLang().getText(Language.WEAPON) + " 1 " + sets.getLang().getText(Language.DAMAGE));
		autoUserDefined_labels[AUTO_WEAPON_1_DELAY].setText(sets.getLang().getText(Language.WEAPON) + " 1 " + sets.getLang().getText(Language.COOLING_TIME));
		autoUserDefined_labels[AUTO_WEAPON_1_HP].setText(sets.getLang().getText(Language.WEAPON) + " 1 " + sets.getLang().getText(Language.RANGE) + " (" + sets.getLang().getText(Language.GUIDE) + ")");
		autoUserDefined_labels[AUTO_WEAPON_1_INTERVAL].setText(sets.getLang().getText(Language.WEAPON) + " 1 " + sets.getLang().getText(Language.HORIZONTAL_INTERVAL));
		autoUserDefined_labels[AUTO_WEAPON_1_MAX].setText(sets.getLang().getText(Language.WEAPON) + " 1 " + sets.getLang().getText(Language.MAX));
		autoUserDefined_labels[AUTO_WEAPON_1_MIN].setText(sets.getLang().getText(Language.WEAPON) + " 1 " + sets.getLang().getText(Language.MINIMUM));
		autoUserDefined_labels[AUTO_WEAPON_1_NEEDS].setText(sets.getLang().getText(Language.WEAPON) + " 1 " + sets.getLang().getText(Language.SPEND_E));
		autoUserDefined_labels[AUTO_WEAPON_1_SIZE].setText(sets.getLang().getText(Language.WEAPON) + " 1 " + sets.getLang().getText(Language.MISSILE_SIZE));
		autoUserDefined_labels[AUTO_WEAPON_1_HELPER_TYPE].setText(sets.getLang().getText(Language.WEAPON) + " 1 " + sets.getLang().getText(Language.KIND_INTERCEPTOR));
		autoUserDefined_labels[AUTO_WEAPON_1_HELPER_COUNT].setText(sets.getLang().getText(Language.WEAPON) + " 1 " + sets.getLang().getText(Language.COUNT_INTERCEPTOR));
		autoUserDefined_labels[AUTO_WEAPON_1_SPEED].setText(sets.getLang().getText(Language.WEAPON) + " 1 " + sets.getLang().getText(Language.MISSILE_SPEED));
		autoUserDefined_labels[AUTO_WEAPON_1_TYPE].setText(sets.getLang().getText(Language.WEAPON) + " 1 " + sets.getLang().getText(Language.KINDS) + "(" + sets.getLang().getText(Language.MISSILE_KIND) + ")");
		autoUserDefined_labels[AUTO_WEAPON_2_DAMAGE].setText(sets.getLang().getText(Language.WEAPON) + " 2 " + sets.getLang().getText(Language.DAMAGE));
		autoUserDefined_labels[AUTO_WEAPON_2_DELAY].setText(sets.getLang().getText(Language.WEAPON) + " 2 " + sets.getLang().getText(Language.COOLING_TIME));
		autoUserDefined_labels[AUTO_WEAPON_2_HP].setText(sets.getLang().getText(Language.WEAPON) + " 2 " + sets.getLang().getText(Language.RANGE) + " (" + sets.getLang().getText(Language.GUIDE) + ")");
		autoUserDefined_labels[AUTO_WEAPON_2_INTERVAL].setText(sets.getLang().getText(Language.WEAPON) + " 2 " + sets.getLang().getText(Language.HORIZONTAL_INTERVAL));
		autoUserDefined_labels[AUTO_WEAPON_2_MAX].setText(sets.getLang().getText(Language.WEAPON) + " 2 " + sets.getLang().getText(Language.MAX));
		autoUserDefined_labels[AUTO_WEAPON_2_MIN].setText(sets.getLang().getText(Language.WEAPON) + " 2 " + sets.getLang().getText(Language.MINIMUM));
		autoUserDefined_labels[AUTO_WEAPON_2_NEEDS].setText(sets.getLang().getText(Language.WEAPON) + " 2 " + sets.getLang().getText(Language.SPEND_E));
		autoUserDefined_labels[AUTO_WEAPON_2_SIZE].setText(sets.getLang().getText(Language.WEAPON) + " 2 " + sets.getLang().getText(Language.MISSILE_SIZE));
		autoUserDefined_labels[AUTO_WEAPON_2_HELPER_TYPE].setText(sets.getLang().getText(Language.WEAPON) + " 2 " + sets.getLang().getText(Language.KIND_INTERCEPTOR));
		autoUserDefined_labels[AUTO_WEAPON_2_HELPER_COUNT].setText(sets.getLang().getText(Language.WEAPON) + " 2 " + sets.getLang().getText(Language.COUNT_INTERCEPTOR));
		autoUserDefined_labels[AUTO_WEAPON_2_SPEED].setText(sets.getLang().getText(Language.WEAPON) + " 2 " + sets.getLang().getText(Language.MISSILE_SPEED));
		autoUserDefined_labels[AUTO_WEAPON_2_TYPE].setText(sets.getLang().getText(Language.WEAPON) + " 2 " + sets.getLang().getText(Language.KINDS) + "(" + sets.getLang().getText(Language.MISSILE_KIND) + ")");
		autoUserDefined_labels[AUTO_WEAPON_3_DAMAGE].setText(sets.getLang().getText(Language.WEAPON) + " 3 " + sets.getLang().getText(Language.DAMAGE));
		autoUserDefined_labels[AUTO_WEAPON_3_DELAY].setText(sets.getLang().getText(Language.WEAPON) + " 3 " + sets.getLang().getText(Language.COOLING_TIME));
		autoUserDefined_labels[AUTO_WEAPON_3_HP].setText(sets.getLang().getText(Language.WEAPON) + " 3 " + sets.getLang().getText(Language.RANGE) + " (" + sets.getLang().getText(Language.GUIDE) + ")");
		autoUserDefined_labels[AUTO_WEAPON_3_INTERVAL].setText(sets.getLang().getText(Language.WEAPON) + " 3 " + sets.getLang().getText(Language.HORIZONTAL_INTERVAL));
		autoUserDefined_labels[AUTO_WEAPON_3_MAX].setText(sets.getLang().getText(Language.WEAPON) + " 3 " + sets.getLang().getText(Language.MAX));
		autoUserDefined_labels[AUTO_WEAPON_3_MIN].setText(sets.getLang().getText(Language.WEAPON) + " 3 " + sets.getLang().getText(Language.MINIMUM));
		autoUserDefined_labels[AUTO_WEAPON_3_NEEDS].setText(sets.getLang().getText(Language.WEAPON) + " 3 " + sets.getLang().getText(Language.SPEND_E));
		autoUserDefined_labels[AUTO_WEAPON_3_SIZE].setText(sets.getLang().getText(Language.WEAPON) + " 3 " + sets.getLang().getText(Language.MISSILE_SIZE));
		autoUserDefined_labels[AUTO_WEAPON_3_HELPER_TYPE].setText(sets.getLang().getText(Language.WEAPON) + " 3 " + sets.getLang().getText(Language.KIND_INTERCEPTOR));
		autoUserDefined_labels[AUTO_WEAPON_3_HELPER_COUNT].setText(sets.getLang().getText(Language.WEAPON) + " 3 " + sets.getLang().getText(Language.COUNT_INTERCEPTOR));
		autoUserDefined_labels[AUTO_WEAPON_3_SPEED].setText(sets.getLang().getText(Language.WEAPON) + " 3 " + sets.getLang().getText(Language.MISSILE_SPEED));
		autoUserDefined_labels[AUTO_WEAPON_3_TYPE].setText(sets.getLang().getText(Language.WEAPON) + " 3 " + sets.getLang().getText(Language.KINDS) + "(" + sets.getLang().getText(Language.MISSILE_KIND) + ")");
		
		try_apply_properties();
		try
		{
			if(sets.getProperties().containsKey("save_properties"))
			{
				if(sets.getProperties().get("save_properties").equalsIgnoreCase("true") || sets.getProperties().get("save_properties").equalsIgnoreCase("yes"))
				{
					try_key_to_property(sets);
					try_save_properties(sets, true);
				}
			}
		}
		catch(Exception e)
		{
			if(sets.isError_printDetail()) e.printStackTrace();
		}
		
		
		touchSetting();	
		frame_loaded = true;
		
		refresh_todayEvent();
		
		message();
		message(sets.getLang().getText(Language.REFLEX) + " " + getVersionString(true));
		message();
		message(sets.getLang().getText(Language.GAME_READY));
		
		if(use_transparent) refresh_transparency();
	}
	public Language getLanguage()
	{
		return sets.getLang().clone();
	}
	void try_apply_properties()
	{
		try
		{			
			if(sets.getProperties().containsKey("keys_1"))
			{
				try
				{
					KEY_1 = Integer.parseInt(sets.getProperties().get("keys_1"));
				} 
				catch (NumberFormatException e)
				{
					KEY_1 = sets.getProperties().get("keys_1").trim().toCharArray()[0];
				}
			}
			if(sets.getProperties().containsKey("keys_2"))
			{
				try
				{
					KEY_2 = Integer.parseInt(sets.getProperties().get("keys_2"));
				} 
				catch (NumberFormatException e)
				{
					KEY_2 = sets.getProperties().get("keys_2").trim().toCharArray()[0];
				}
			}
			if(sets.getProperties().containsKey("keys_3"))
			{
				try
				{
					KEY_3 = Integer.parseInt(sets.getProperties().get("keys_3"));
				} 
				catch (NumberFormatException e)
				{
					KEY_3 = sets.getProperties().get("keys_3").trim().toCharArray()[0];
				}
			}
			if(sets.getProperties().containsKey("keys_k"))
			{
				try
				{
					KEY_K = Integer.parseInt(sets.getProperties().get("keys_k"));
				} 
				catch (NumberFormatException e)
				{
					KEY_K = sets.getProperties().get("keys_k").trim().toCharArray()[0];
				}
			}
			if(sets.getProperties().containsKey("keys_l"))
			{
				try
				{
					KEY_L = Integer.parseInt(sets.getProperties().get("keys_l"));
				} 
				catch (NumberFormatException e)
				{
					KEY_L = sets.getProperties().get("keys_l").trim().toCharArray()[0];
				}
			}
			if(sets.getProperties().containsKey("keys_shift"))
			{
				try
				{
					KEY_SHIFT = Integer.parseInt(sets.getProperties().get("keys_shift"));
				} 
				catch (NumberFormatException e)
				{
					KEY_SHIFT = sets.getProperties().get("keys_shift").trim().toCharArray()[0];
				}
			}
			if(sets.getProperties().containsKey("keys_space"))
			{
				try
				{
					KEY_SPACE = Integer.parseInt(sets.getProperties().get("keys_space"));
				} 
				catch (NumberFormatException e)
				{
					KEY_SPACE = sets.getProperties().get("keys_space").trim().toCharArray()[0];
				}
			}
			if(sets.getProperties().containsKey("keys_left"))
			{
				try
				{
					KEY_LEFT = Integer.parseInt(sets.getProperties().get("keys_left"));
				} 
				catch (NumberFormatException e)
				{
					KEY_LEFT = sets.getProperties().get("keys_left").trim().toCharArray()[0];
				}
			}
			if(sets.getProperties().containsKey("keys_right"))
			{
				try
				{
					KEY_RIGHT = Integer.parseInt(sets.getProperties().get("keys_right"));
				} 
				catch (NumberFormatException e)
				{
					KEY_RIGHT = sets.getProperties().get("keys_right").trim().toCharArray()[0];
				}
			}
			if(sets.getProperties().containsKey("keys_up"))
			{
				try
				{
					KEY_UP = Integer.parseInt(sets.getProperties().get("keys_up"));
				} 
				catch (NumberFormatException e)
				{
					KEY_UP = sets.getProperties().get("keys_up").trim().toCharArray()[0];
				}
			}
			if(sets.getProperties().containsKey("keys_down"))
			{
				try
				{
					KEY_DOWN = Integer.parseInt(sets.getProperties().get("keys_down"));
				} 
				catch (NumberFormatException e)
				{
					KEY_DOWN = sets.getProperties().get("keys_down").trim().toCharArray()[0];
				}
			}
			if(sets.getProperties().containsKey("keys_w"))
			{
				try
				{
					KEY_W = Integer.parseInt(sets.getProperties().get("keys_w"));
				} 
				catch (NumberFormatException e)
				{
					KEY_W = sets.getProperties().get("keys_w").trim().toCharArray()[0];
				}
			}
			if(sets.getProperties().containsKey("keys_a"))
			{
				try
				{
					KEY_A = Integer.parseInt(sets.getProperties().get("keys_a"));
				} 
				catch (NumberFormatException e)
				{
					KEY_A = sets.getProperties().get("keys_a").trim().toCharArray()[0];
				}
			}
			if(sets.getProperties().containsKey("keys_s"))
			{
				try
				{
					KEY_S = Integer.parseInt(sets.getProperties().get("keys_s"));
				} 
				catch (NumberFormatException e)
				{
					KEY_S = sets.getProperties().get("keys_s").trim().toCharArray()[0];
				}
			}
			if(sets.getProperties().containsKey("keys_d"))
			{
				try
				{
					KEY_D = Integer.parseInt(sets.getProperties().get("keys_d"));
				} 
				catch (NumberFormatException e)
				{
					KEY_D = sets.getProperties().get("keys_d").trim().toCharArray()[0];
				}
			}
			if(sets.getProperties().containsKey("keys_4"))
			{
				try
				{
					KEY_4 = Integer.parseInt(sets.getProperties().get("keys_4"));
				} 
				catch (NumberFormatException e)
				{
					KEY_4 = sets.getProperties().get("keys_4").trim().toCharArray()[0];
				}
			}
			if(sets.getProperties().containsKey("keys_5"))
			{
				try
				{
					KEY_5 = Integer.parseInt(sets.getProperties().get("keys_5"));
				} 
				catch (NumberFormatException e)
				{
					KEY_5 = sets.getProperties().get("keys_5").trim().toCharArray()[0];
				}
			}
			if(sets.getProperties().containsKey("keys_6"))
			{
				try
				{
					KEY_6 = Integer.parseInt(sets.getProperties().get("keys_6"));
				} 
				catch (NumberFormatException e)
				{
					KEY_6 = sets.getProperties().get("keys_6").trim().toCharArray()[0];
				}
			}
			if(sets.getProperties().containsKey("keys_7"))
			{
				try
				{
					KEY_7 = Integer.parseInt(sets.getProperties().get("keys_7"));
				} 
				catch (NumberFormatException e)
				{
					KEY_7 = sets.getProperties().get("keys_7").trim().toCharArray()[0];
				}
			}
			if(sets.getProperties().containsKey("keys_8"))
			{
				try
				{
					KEY_8 = Integer.parseInt(sets.getProperties().get("keys_8"));
				} 
				catch (NumberFormatException e)
				{
					KEY_8 = sets.getProperties().get("keys_8").trim().toCharArray()[0];
				}
			}
			if(sets.getProperties().containsKey("keys_9"))
			{
				try
				{
					KEY_9 = Integer.parseInt(sets.getProperties().get("keys_9"));
				} 
				catch (NumberFormatException e)
				{
					KEY_9 = sets.getProperties().get("keys_9").trim().toCharArray()[0];
				}
			}
			if(sets.getProperties().containsKey("keys_0"))
			{
				try
				{
					KEY_0 = Integer.parseInt(sets.getProperties().get("keys_0"));
				} 
				catch (NumberFormatException e)
				{
					KEY_0 = sets.getProperties().get("keys_0").trim().toCharArray()[0];
				}
			}
			int[] langList = sets.getLang().getList();
			for(int i=0; i<langList.length; i++)
			{
				try
				{
					if(sets.getProperties().containsKey(String.valueOf(langList[i])))
					{
						sets.getLang().setText(langList[i], sets.getProperties().get(String.valueOf(langList[i])));
					}
				}
				catch(Exception e)
				{
					
				}
			}
			if(sets.getProperties().containsKey("auto_control"))
			{
				try
				{
					menu_manage_autoControl.setSelected(Boolean.parseBoolean(sets.getProperties().get("auto_control")));
				} 
				catch (Exception e)
				{
					if(sets.isError_printDetail()) e.printStackTrace();
				}
			}
			if(sets.getProperties().containsKey("look_and_feel"))
			{
				sets.setLookAndFeel(sets.getProperties().get("look_and_feel"));
			}
			if(sets.getProperties().containsKey("show_error"))
			{
				try
				{
					sets.setError_printDetail(Boolean.parseBoolean("show_error"));
				} 
				catch (Exception e)
				{
					if(sets.isError_printDetail()) e.printStackTrace();
				}
			}
			if(sets.getProperties().containsKey("script_5"))
			{
				arena.setSaved_script_5(sets.getProperties().get("script_5"));
			}
			if(sets.getProperties().containsKey("script_6"))
			{
				arena.setSaved_script_6(sets.getProperties().get("script_6"));
			}
			if(sets.getProperties().containsKey("script_7"))
			{
				arena.setSaved_script_7(sets.getProperties().get("script_7"));
			}
		}
		catch(Exception e)
		{
			if(sets.isError_printDetail()) e.printStackTrace();
		}		
	}
	public static void try_key_to_property(Setting sets)
	{
		sets.getProperties().put("keys_1", String.valueOf(KEY_1));
		sets.getProperties().put("keys_2", String.valueOf(KEY_2));
		sets.getProperties().put("keys_3", String.valueOf(KEY_3));
		sets.getProperties().put("keys_k", String.valueOf(KEY_K));
		sets.getProperties().put("keys_l", String.valueOf(KEY_L));
		sets.getProperties().put("keys_shift", String.valueOf(KEY_SHIFT));
		sets.getProperties().put("keys_space", String.valueOf(KEY_SPACE));
		sets.getProperties().put("keys_left", String.valueOf(KEY_LEFT));
		sets.getProperties().put("keys_right", String.valueOf(KEY_RIGHT));
		sets.getProperties().put("keys_up", String.valueOf(KEY_UP));
		sets.getProperties().put("keys_down", String.valueOf(KEY_DOWN));
		sets.getProperties().put("keys_w", String.valueOf(KEY_W));
		sets.getProperties().put("keys_a", String.valueOf(KEY_A));
		sets.getProperties().put("keys_s", String.valueOf(KEY_S));
		sets.getProperties().put("keys_d", String.valueOf(KEY_D));
		sets.getProperties().put("keys_4", String.valueOf(KEY_4));
		sets.getProperties().put("keys_5", String.valueOf(KEY_5));
		sets.getProperties().put("keys_6", String.valueOf(KEY_6));
		sets.getProperties().put("keys_7", String.valueOf(KEY_7));
		sets.getProperties().put("keys_8", String.valueOf(KEY_8));
		sets.getProperties().put("keys_9", String.valueOf(KEY_9));
		sets.getProperties().put("keys_0", String.valueOf(KEY_0));
	}
	public static void try_save_properties(Setting sets, boolean details)
	{
		FileWriter writer = null;
		BufferedWriter buffered = null;
		
		if(details)
		{
			try_key_to_property(sets);
		}
		
		try
		{			
			File dirFile = new File(sets.getDefault_path());
			if(! dirFile.exists()) dirFile.mkdir();
			File propertyFile = new File(sets.getDefault_path() + "property.cfg");
			writer = new FileWriter(propertyFile);
			buffered = new BufferedWriter(writer);
			
			buffered.write(" ");
			buffered.newLine();
			
			Set<String> keySet = sets.getProperties().keySet();
			Vector<String> keyArr = new Vector<String>();
			keyArr.addAll(keySet);
			Collections.sort(keyArr);
			
			Vector<String> startDescs = new Vector<String>();
			if(sets.getLang() instanceof Korean)
			{
				startDescs.add("첫 번째 줄은 인식하지 않습니다.");
				startDescs.add("# 기호로 시작하는 줄은 인식하지 않습니다.");
				startDescs.add("이 외에는, \':\' 기호 왼쪽에 속성의 키를, 오른쪽에 입력할 값을 넣고, 줄의 마지막에는 \';\' 기호를 붙입니다.");
			}
			else
			{
				startDescs.add("");
			}
			
			for(String s : startDescs)
			{
				buffered.write("# " + s);
				buffered.newLine();
			}
			
			if(details)
			{
				int[] indexes = sets.getLang().getList();
				for(int i=0; i<indexes.length; i++)
				{
					buffered.write(String.valueOf(indexes[i]) + " : " + sets.getLang().getText(indexes[i]) + ";");
					buffered.newLine();
				}
			}
			
			buffered.newLine();
			
			for(String s : keyArr)
			{
				if(s.startsWith("reflexioner")) continue;
				buffered.write(s + " : " + sets.getProperties().get(s) + ";");
				buffered.newLine();
			}			
			
			if(details)
			{
				if(! sets.getProperties().containsKey("look_and_feel"))
				{
					buffered.write("look_and_feel : " + sets.getLookAndFeel() + ";");
					buffered.newLine();
				}				
			}
		}
		catch(Exception e)
		{
			if(sets.isError_printDetail()) e.printStackTrace();
		}
		finally
		{
			try
			{
				buffered.close();
			}
			catch(Exception e)
			{
				
			}
			try
			{
				writer.close();
			}
			catch(Exception e)
			{
				
			}
		}
	}
	public static void try_load_properties(Setting sets)
	{
		FileReader reader = null;
		BufferedReader buffered = null;
		try
		{
			File propertyFile = new File(sets.getDefault_path() + "property.cfg");
			if(propertyFile.exists())
			{
				reader = new FileReader(propertyFile);
				buffered = new BufferedReader(reader);
				String reads = null;
				String accumulates = "";
				boolean first_skip = true;
				while(true)
				{									
					reads = buffered.readLine();
					if(reads == null) break;
					if(first_skip)
					{
						first_skip = false;
						continue;
					}
					reads = reads.trim();
					if(reads.equalsIgnoreCase("")) continue;					
					
					if(reads.startsWith("#")) continue;
					accumulates = accumulates + reads + " ";
				}
				StringTokenizer semicolonTokenizer = new StringTokenizer(accumulates, ";");
				StringTokenizer keyTokenizer;
				String keys, values;
				while(semicolonTokenizer.hasMoreTokens())
				{
					try
					{						
						keyTokenizer = new StringTokenizer(semicolonTokenizer.nextToken().trim(), ":");
						keys = keyTokenizer.nextToken().trim();
						if(keyTokenizer.hasMoreTokens())
						{
							values = keyTokenizer.nextToken().trim();
							sets.getProperties().put(keys, values);
						}
						else
						{
							sets.getProperties().put(keys, "true");
						}
					} 
					catch (Exception e)
					{
						if(sets.isError_printDetail()) e.printStackTrace();
					}
				}
			}			
			if(! sets.getProperties().containsKey("save_properties"))
			{
				sets.getProperties().put("save_properties", "false");
			}
			if(! sets.getProperties().containsKey("show_error"))
			{
				sets.getProperties().put("show_error", "false");
			}
		}
		catch(Exception e)
		{
			if(sets.isError_printDetail()) e.printStackTrace();
		}
		finally
		{
			try
			{
				buffered.close();
			}
			catch(Exception e)
			{
				
			}
			try
			{
				reader.close();
			}
			catch(Exception e)
			{
				
			}
		}		
	}
	public void refresh_transparency()
	{
		float value = transparent_opacity;
		float doubled = transparent_opacity + ((1.0f - transparent_opacity) * 0.5f);
		if(use_transparent)
		{
			value = transparent_opacity;
		}
		else
		{
			value = 1.0f;
		}
		// try_transparent(window, value);
		// try_transparent(replayPlayer.getWindow(), value);
		
		try_transparent(aboutDialog, value);
		try_transparent(finishDialog, doubled);
		try_transparent(startDialog, doubled);
		try_transparent(messageDialog, value);
		try_transparent(scenarioEditor, doubled);		
		if(checker == null) checker = new Code_Checker(false, startDialog, sets);
		try_transparent(checker.getWindow(), doubled);
		try_transparent(needfileDialog, doubled);
	}
	private void refresh_todayEvent()
	{
		boolean success = false;
		startItem = null;
				
		try
		{
			int year, month, day;
			Calendar cal = Calendar.getInstance();
			year = cal.get(Calendar.YEAR);
			month = cal.get(Calendar.MONTH) + 1;
			day = cal.get(Calendar.DAY_OF_MONTH);
			
			today_ship = 0;
			today_difficulty = 0.0;
			int max_ship_index = SpaceShip.spaceShipKeyIntsList(3).size();
			
			if(month % 7 == 0)
				today_ship = (10000 + year + month + day) % max_ship_index;
			else if(month % 6 == 0)
				today_ship = (10000 + year - month + day) % max_ship_index;
			else if(month % 5 == 0)
				today_ship = (10000 + year + month - day) % max_ship_index;
			else
				today_ship = (10000 - year - month - day) % max_ship_index;
			
			int original_index = today_ship;
			today_ship = SpaceShip.spaceShipKeyIntsList(3).get(original_index).intValue();
						
			today_difficulty = 1.0 + ((10000 + year + month + day) % 8);
			today_difficulty = today_difficulty / 2.0;
			
			String itemDesc = "";
			startItem = new int[3];
			int itemSelector = 0;
			for(int i=0; i<startItem.length; i++)
			{
				switch(i % 8)
				{
					case 0:
						itemSelector = 10000 + year + month + day;
						break;
					case 1:
						itemSelector = 10000 - year + month + day;
						break;
					case 2:
						itemSelector = 10000 + year - month + day;
						break;
					case 3:
						itemSelector = 10000 + year + month - day;
						break;
					case 4:
						itemSelector = 10000 - year - month + day;
						break;
					case 5:
						itemSelector = 10000 - year + month - day;
						break;
					case 6:
						itemSelector = 10000 + year - month - day;
						break;
					case 7:
						itemSelector = 10000 - year - month - day;
						break;
					default:
						itemSelector = 10000 + year + month + day;
						break;
				}
				switch(itemSelector & 7)
				{
					case 0:
						startItem[i] = Item.A_HP_ADD;
						itemDesc = itemDesc + "A";
						break;
					case 1:
						startItem[i] = Item.D_D_ADD;
						itemDesc = itemDesc + "D";
						break;
					case 2:
						startItem[i] = Item.E_E_ADD;
						itemDesc = itemDesc + "E";
						break;
					case 3:
						startItem[i] = Item.G_E_H_ADD;
						itemDesc = itemDesc + "G";
						break;
					case 4:
						startItem[i] = Item.K_HP_H_ADD;
						itemDesc = itemDesc + "K";
						break;
					case 5:
						startItem[i] = Item.M_M_ADD;
						itemDesc = itemDesc + "M";
						break;
					case 6:
						startItem[i] = Item.S_S_ADD;
						itemDesc = itemDesc + "S";
						break;
					default:
						startItem[i] = Item.A_HP_ADD;
						itemDesc = itemDesc + "A";
						break;
				}
			}
			today_difficulty = today_difficulty + (startItem.length * 0.5);
			
			String areaText = "";
			
			
			areaText = areaText + sets.getLang().getText(Language.TODAY_GAME) + "\n\n";
			areaText = areaText + String.valueOf(year) + sets.getLang().getText(Language.YEAR) + " " + String.valueOf(month) + sets.getLang().getText(Language.MONTH) + " " + String.valueOf(day) + sets.getLang().getText(Language.DAY) + "\n\n";	
			areaText = areaText + sets.getLang().getText(Language.SHIP) + " : ";
			String shipName = SpaceShip.spaceShipNameList(sets, 3).get(original_index);
			areaText = areaText + shipName + "\n";
			
			areaText = areaText + sets.getLang().getText(Language.DIFFICULTY) + " : ";
			
			double difValue = 1;
			difValue = Math.pow(10.0, (today_difficulty));
			areaText = areaText + Difficulty.intToString(Math.round(difValue), 3.3) + "\n";
			
			areaText = areaText + sets.getLang().getText(Language.TODAY_ITEM) + " : " + itemDesc + "\n\n";
			areaText = areaText + sets.getLang().getText(Language.TODAY_WARN);
			
			start_today_area.setText(areaText);
			
			success = true;					
		} 
		catch (Exception e)
		{
			if(sets.isError_printDetail()) e.printStackTrace();
			startItem = null;
			success = false;
		}
		
		bt_start_today.setEnabled(success);
	}
	public void inputScenario(AReflexScenario scen)
	{
		scenarios.add(scen);
		refresh_scenario(false);
		mainTab.setSelectedComponent(start_scenarioPanel);
	}
	public void playScenario(AReflexScenario scen)
	{
		replay_save = menu_manage_saveReplay.isSelected();
		start(scen, scen.getSpaceShip());
	}
	private void refresh_scenario(boolean delete_befores)
	{
		load_scenarios(delete_befores);
		Vector<String> listData = new Vector<String>();
		String[] listArray;
		String adds = "", key1, key2;
		BigInteger oldPoints = null;
		StringTokenizer delimToken;
		AReflexScenario star_target;
		int stars = 0;
		
		for(int i=0; i<scenarios.size(); i++)
		{
			adds = scenarios.get(i).getName();
			stars = 0;
			oldPoints = new BigInteger("0");
			try
			{
				if(scenarios.get(i) instanceof AReflexScenario)
				{
					star_target = (AReflexScenario) scenarios.get(i);
					adds = adds + " ";
					
					Set<String> keys = sets.getProperties().keySet();
					for(String key : keys)
					{
						try
						{
							delimToken = new StringTokenizer(key, "_");
							key1 = delimToken.nextToken();
							if(! delimToken.hasMoreTokens()) continue;
							key2 = delimToken.nextToken();
							if(key1.equalsIgnoreCase("reflexioner"))
							{
								if(key2.equalsIgnoreCase(star_target.getRandomCode().toString()))
								{
									oldPoints = Lint.big(sets.getProperties().get(key));
									break;
								}
							}
						} 
						catch (Exception e)
						{
							e.printStackTrace();
						}
					}
					boolean checking = arena.isSettingAuth(1937283 + 1001008, scenarios, sets);
					if(oldPoints != null && checking)
					{					
						if(oldPoints.compareTo(Lint.big(star_target.getStar1().longValue())) >= 1)
						{
							stars++;
						}
						if(oldPoints.compareTo(Lint.big(star_target.getStar2().longValue())) >= 1)
						{
							stars++;
						}
						if(oldPoints.compareTo(Lint.big(star_target.getStar3().longValue())) >= 1)
						{
							stars++;
						}
						if(star_target instanceof XReflexScenario)
						{
							if(oldPoints.compareTo(((XReflexScenario) star_target).getStar4()) >= 1)
							{
								stars++;
							}
							if(oldPoints.compareTo(((XReflexScenario) star_target).getStar5()) >= 1)
							{
								stars++;
							}
						}
						adds = adds + Difficulty.starToString(stars);
					}
					else if(! arena.isSettingAuth(1937283 + 1001008, scenarios, sets))
					{
						arena.resetSettingAuth(1937283 + 1001008, scenarios, sets);
					}
				}
			} 
			catch (Exception e)
			{
				
			}
			
			listData.add(adds);	
		}
		
		
		listArray = new String[listData.size()];
		for(int i=0; i<listData.size(); i++)
		{
			listArray[i] = listData.get(i);
		}
		start_scenarioList.setListData(listArray);
		start_scenarioList.setSelectedIndex(0);
	}
	private void load_scenarios(boolean delete_befores)
	{
		if(delete_befores) scenarios.clear();		
		
		SortedSet<ReflexScenario> dups = new TreeSet<ReflexScenario>();
		dups.addAll(ReflexScenarioSetter.standards());
		if(scenarios.size() >= 1) dups.addAll(scenarios);
		scenarios.clear();
		
		scenarios.addAll(dups);

		ReflexScenario loadScen;
		File loads = null;
		File[] lists = null;
		BufferedReader bufferedReader = null;
		InputStreamReader inputReader = null;		
		InputStream inputStream = null;
		ObjectInputStream objectStream = null;
		XMLDecoder decoder = null;
		URL webTarget = null;
		String webList, readLines, getScenario = "";
		try
		{
			loads = new File(sets.getDefault_path());
			if(loads.exists())
			{
				lists = loads.listFiles();
				for(int i=0; i<lists.length; i++)
				{
					loads = lists[i];
					if(loads.getAbsolutePath().endsWith(".rfsc") || loads.getAbsolutePath().endsWith(".RFSC")
							|| loads.getAbsolutePath().endsWith(".Rfsc"))
					{
						try
						{						
							inputStream = new FileInputStream(loads);
							objectStream = new ObjectInputStream(inputStream);
							loadScen = (ReflexScenario) objectStream.readObject();
							if(loadScen != null)
							{
								scenarios.add(loadScen);
							}
						}
						catch(Exception e)
						{
							if(sets.isError_printDetail())
								e.printStackTrace();
						}
						finally
						{
							try
							{
								objectStream.close();
							}
							catch(Exception e){}
							try
							{
								inputStream.close();
							}
							catch(Exception e){}
						}
					}
					else if(loads.getAbsolutePath().endsWith(".rfsx") || loads.getAbsolutePath().endsWith(".RFSX")
							|| loads.getAbsolutePath().endsWith(".Rfsx"))
					{
						try
						{						
							inputStream = new FileInputStream(loads);
							decoder = new XMLDecoder(inputStream);
							loadScen = (ReflexScenario) decoder.readObject();
							if(loadScen != null)
							{
								scenarios.add(loadScen);
							}
						}
						catch(Exception e)
						{
							if(sets.isError_printDetail())
								e.printStackTrace();
						}
						finally
						{
							try
							{
								decoder.close();
							}
							catch(Exception e){}
							try
							{
								inputStream.close();
							}
							catch(Exception e){}
						}
					}
					else if(loads.getAbsolutePath().endsWith(".rfst") || loads.getAbsolutePath().endsWith(".RFST")
							|| loads.getAbsolutePath().endsWith(".Rfst"))
					{
						try
						{						
							inputStream = new FileInputStream(loads);
							inputReader = new InputStreamReader(inputStream);
							bufferedReader = new BufferedReader(inputReader);
							getScenario = "";
							while(true)
							{
								readLines = bufferedReader.readLine();
								if(readLines == null) break;
								getScenario = getScenario + readLines + "\n";
							}
							scenarios.add(new BReflexScenario(getScenario));
							/*
							for(ReflexScenario s : scenarios)
							{
								System.out.println("Now : " + s.getName());
							}
							*/
						}
						catch(Exception e)
						{
							if(sets.isError_printDetail())
								e.printStackTrace();
						}
						finally
						{
							try
							{
								decoder.close();
							}
							catch(Exception e){}
							try
							{
								inputStream.close();
							}
							catch(Exception e){}
						}
					}
				}
			}
			
			webList = "";
			try
			{
				webTarget = new URL(sets.getNotice_url() + "reflex_scenlist.txt");
				inputStream = webTarget.openStream();
				inputReader = new InputStreamReader(inputStream);
				bufferedReader = new BufferedReader(inputReader);
				while(true)
				{
					try
					{
						readLines = bufferedReader.readLine();
						if(readLines == null) break;
						webList = webList + readLines + "||";	
					} 
					catch (Exception e)
					{
						if(sets.isError_printDetail())
							e.printStackTrace();
					}
				}
			} 
			catch (Exception e)
			{
				if(sets.isError_printDetail())
					e.printStackTrace();
			}
			try
			{
				bufferedReader.close();
			}
			catch(Exception e){}
			try
			{
				inputReader.close();
			}
			catch(Exception e){}
			try
			{
				inputStream.close();
			}
			catch(Exception e){}
			
			StringTokenizer tokens = new StringTokenizer(webList, "||");
			String tokenGet = "", lineGets = "", accum = "";
			while(tokens.hasMoreTokens())
			{
				try
				{
					tokenGet = tokens.nextToken();
					webTarget = new URL(tokenGet);
					inputStream = webTarget.openStream();
					if(tokenGet.endsWith(".rfst"))
					{
						inputReader = new InputStreamReader(inputStream);
						bufferedReader = new BufferedReader(inputReader);
						while(true)
						{
							lineGets = bufferedReader.readLine();
							if(lineGets == null) break;
							accum = accum + lineGets + "\n";
						}
						loadScen = new AReflexScenario(accum);
					}
					else
					{
						decoder = new XMLDecoder(inputStream);
						loadScen = (ReflexScenario) decoder.readObject();
					}
					
					if(loadScen != null)
					{
						scenarios.add(loadScen);
					}
				}
				catch(Exception e)
				{
					if(sets.isError_printDetail())
						e.printStackTrace();
				}
				finally
				{
					try
					{
						decoder.close();
					}
					catch(Exception e){}
					try
					{
						bufferedReader.close();
					}
					catch(Exception e){}
					try
					{
						inputReader.close();
					}
					catch(Exception e){}
					try
					{
						inputStream.close();
					}
					catch(Exception e){}
				}
			}
		}
		catch(Exception e)
		{
			if(sets.isError_printDetail())
				e.printStackTrace();
		}
		int i = 0;
		while(i < scenarios.size())
		{			
			if(scenarios.get(i).getGrade_limit() != null)
			{
				if(scenarios.get(i).getGrade_limit().intValue() >= grade_mode + 1)
				{
					//System.out.println("Remove : " + scenarios.get(i).getName() + ", " + scenarios.get(i).getGrade_limit());
					scenarios.remove(i);
					i = 0;
				}
				else i++;
			}
			else
			{
				i++;
			}
			
		}
	}
	private String userDefined_first()
	{
		String result = "";
		result = result + "# Make your own spaceship and play" + "\n";
		result = result + "" + "\n";
		result = result + "# Ship Name" + "\n";
		result = result + "name||UserMadeSpaceShip" + "\n";
		result = result + "# Basic HP" + "\n";
		result = result + "hp||1000" + "\n";
		result = result + "# Basic Energy" + "\n";
		result = result + "energy||1000" + "\n";
		result = result + "# Basic Speed" + "\n";
		result = result + "speed||" + String.valueOf(speed) + "\n";
		result = result + "\n";
		result = result + "# Size" + "\n";
		result = result + "size||" + String.valueOf(spaceShip_r) + "\n";
		result = result + "# Shape" + "\n";
		result = result + "#   circle, rectangle" + "\n";
		result = result + "shape||" + "circle" + "\n";
		result = result + "" + "\n";
		result = result + "" + "\n";
		result = result + "# Define your own weapons" + "\n";
		result = result + "" + "\n";
		result = result + "# type : type of missile weapon fire." + "\n";
		result = result + "#   normal : normal missile." + "\n";
		result = result + "#   guide : the missile which follows enemy." + "\n";
		result = result + "#   reflex : if this missile hit the enemy, the missile don\'t destroyed, just reflexed." + "\n";
		result = result + "#   reflex_perfect : Almost same as reflex missile, but this missile also reflexed if the missile hit the edge of screen." + "\n";
		result = result + "#   beam : this is not missile, raser beam, splash effects occured." + "\n";
		result = result + "# max : The maximum value of missile counts." + "\n";
		result = result + "# min : The minimum value of missile counts." + "\n";
		result = result + "# interval : If missile count is above 2, this value effects missile\'s locations." + "\n";
		result = result + "# hp : It is not necessary if the type is normal." + "\n";
		result = result + "#   If the type is reflex, reflex_perfect, or guide, this value effects the range of this missile flies." + "\n";
		result = result + "# needs : The value effects how much energy needed to fire." + "\n";
		result = result + "# speed : Missile\'s speed." + "\n";
		result = result + "# size : Missile\'s size." + "\n";
		result = result + "# damage : Missile\'s damage ratio. Missile\'s damage is calculated with ship\'s damage × this value." + "\n";
		result = result + "" + "\n";
		result = result + "" + "\n";
		result = result + "# Weapon 1" + "\n";
		result = result + "weapon1||type=normal" + "\n";
		result = result + "weapon1||max=5" + "\n";
		result = result + "weapon1||min=2" + "\n";
		result = result + "weapon1||interval=30" + "\n";
		result = result + "weapon1||hp=1000" + "\n";
		result = result + "weapon1||needs=0" + "\n";
		result = result + "weapon1||speed=2.0" + "\n";
		result = result + "weapon1||size=1.0" + "\n";
		result = result + "weapon1||damage=1.0" + "\n";
		result = result + "weapon1||delay=5" + "\n";
		result = result + "" + "\n";
		result = result + "# Weapon 2" + "\n";
		result = result + "weapon2||type=guide" + "\n";
		result = result + "weapon2||max=5" + "\n";
		result = result + "weapon2||min=2" + "\n";
		result = result + "weapon2||interval=30" + "\n";
		result = result + "weapon2||hp=1000" + "\n";
		result = result + "weapon2||needs=100" + "\n";
		result = result + "weapon2||speed=1.0" + "\n";
		result = result + "weapon2||size=4.0" + "\n";
		result = result + "weapon2||damage=1.0" + "\n";
		result = result + "weapon2||delay=5" + "\n";
		result = result + "" + "\n";
		result = result + "# Weapon 3" + "\n";
		result = result + "weapon3||type=beam" + "\n";
		result = result + "weapon3||max=5" + "\n";
		result = result + "weapon3||min=2" + "\n";
		result = result + "weapon3||interval=30" + "\n";
		result = result + "weapon3||hp=10" + "\n";
		result = result + "weapon3||needs=300" + "\n";
		result = result + "weapon3||speed=1.0" + "\n";
		result = result + "weapon3||size=1.0" + "\n";
		result = result + "weapon3||damage=1.0" + "\n";
		result = result + "weapon3||delay=7" + "\n";
		result = result + "" + "\n";
		result = result + "version||" + (String.valueOf(version_main) + String.valueOf(version_sub_1) + String.valueOf(version_sub_2)
				+ " "  + String.valueOf(version_nightly) + " " + String.valueOf(version_test)).trim() + "\n";
		if(grade_mode >= 3)
			result = result + "auth||" + (new AUserDefinedShip(result, new Vector<Enemy>()).authorize(1937283 + 1001008, false));
		else
			result = result + "auth||0" + "\n";
		
		
		return result;
	}
	public void touchSetting()
	{
		touchMode = ! touchMode;
		if(touchMode)
		{
			SwingUtilities.invokeLater(new Runnable()
			{
				@Override
				public void run()
				{
					secondBtPanel.setVisible(true);
					controlBtPanel.setVisible(true);
					helpPanel .setVisible(false);
					bt_touch.setText("▼");
				}			
			});	
		}
		else
		{
			
			SwingUtilities.invokeLater(new Runnable()
			{
				@Override
				public void run()
				{
					secondBtPanel.setVisible(false);
					controlBtPanel.setVisible(false);
					helpPanel.setVisible(true);
					bt_touch.setText("▲");
				}			
			});
		}
	}
	public void start(int shipIndex, double difficultyMode, int[] items, String name)
	{
		arena.player_type = shipIndex;
		arena.deleteUserDefinedShip();
		arena.deleteScenario();
		arena.setDifficultyMode(difficultyMode);
		arena.setAutoControlMode(menu_manage_autoControl);
		arena.setTodayEvent(true);
		arena.setName(name);
		arena.setStartItem(items);
		startDialog.setVisible(false);
		arena.game_start();
	}
	public void start()
	{
		//arena.player_type = combo_ship.getSelectedIndex();
		int selected_ship = combo_ship.getSelectedIndex();
		for(int i=0; i<shipKeys.length; i++)
		{
			if(i == selected_ship)
			{
				arena.player_type = shipKeys[i];
				break;
			}
		}
		replay_save = menu_manage_saveReplay.isSelected();
		arena.deleteUserDefinedShip();
		arena.setName(start_nameField.getText());
		arena.deleteScenario();
		arena.setTodayEvent(false);
		arena.setStartItem(null);
		try
		{
			switch(combo_dif.getSelectedIndex())
			{
				case 0:
					arena.setDifficultyMode(1.0);
					break;
				case 4:
					arena.setDifficultyMode(1.5 + (0.5 * (combo_dif.getSelectedIndex())));
					break;
				default:
					arena.setDifficultyMode(1.0 + (0.5 * (combo_dif.getSelectedIndex())));
			}
		} 
		catch (Exception e)
		{
			arena.setDifficultyMode(1.0);
		}
		//System.out.println(arena.getDifficultyMode());// For TEST
		arena.setAutoControlMode(menu_manage_autoControl);
		startDialog.setVisible(false);
		arena.game_start();
	}
	
	public void start(String commands)
	{
		arena.player_type = -1;
		replay_save = menu_manage_saveReplay.isSelected();
		String result = arena.setUserDefinedShip(commands);
		if(result == null)
		{
			JOptionPane.showMessageDialog(startDialog, sets.getLang().getText(Language.ERROR) + " : " + result);
		}
		if(result.equalsIgnoreCase("success"))
		{
			arena.deleteScenario();
			arena.setName(start_userDefined_nameField.getText());
			arena.setAutoControlMode(menu_manage_autoControl);
			arena.setTodayEvent(false);
			arena.setStartItem(null);
			startDialog.setVisible(false);
			arena.game_start();
		}
		else
		{
			JOptionPane.showMessageDialog(startDialog, sets.getLang().getText(Language.ERROR) + " : " + result);
		}
	}
	public void start(ReflexScenario scenario) throws NullPointerException
	{
		arena.setTodayEvent(false);
		arena.setStartItem(null);
		start(scenario, scenario.getSpaceShip());
	}
	public void start(ReflexScenario scenario, String spaceShip) throws NullPointerException
	{
		arena.player_type = -1;
		replay_save = menu_manage_saveReplay.isSelected();
		arena.setUserDefinedShip(spaceShip);
		arena.setScenario(scenario);
		arena.setName(start_scenario_nameField.getText());
		arena.setAutoControlMode(menu_manage_autoControl);
		arena.setTodayEvent(false);
		arena.setStartItem(null);
		startDialog.setVisible(false);		
		arena.game_start();		
	}
	public void start(ReflexSave state)
	{
		arena.applyState(state);
		startDialog.setVisible(false);
	}
	public void start(ReflexReplay replay, int index)
	{
		arena.applyState(replay, index);
		startDialog.setVisible(false);
	}
	public void finish()
	{
		arena.pause();
		finish_ta.setText("");
		finishDialog.setVisible(true);
		BigInteger getPoints = arena.getPoint();
		AReflexScenario arefrexScenario = null;
		boolean pointSave_accept = true;
		
		if(arena.getScenario() != null && (! Arena.isAutoControlMode()))
		{
			if(arena.getScenario() instanceof AReflexScenario)
			{
				//String contents = "0";
				if(arena.getScenario() instanceof DReflexScenario)
				{
					DReflexScenario dreflex = (DReflexScenario) arena.getScenario();
					long passed_dif = arena.getDifficulty();
					if(dreflex.getStar1_least() != null)
					{
						if(passed_dif < dreflex.getStar1_least().longValue())
						{
							pointSave_accept = false;
						}
					}
				}
				
				if(pointSave_accept)
				{
					try
					{
						arefrexScenario = (AReflexScenario) arena.getScenario();						
						String key1;
						
						key1 = "reflexioner_" + arefrexScenario.getRandomCode().toString();
						BigInteger oldOne;
						if(sets.getProperties().containsKey(key1))
						{
							oldOne = Lint.big(sets.getProperties().get(key1));
							if(getPoints.compareTo(oldOne) >= 1)
							{
								sets.getProperties().put(key1, getPoints.toString());
							}
						}
						else
						{
							sets.getProperties().put(key1, getPoints.toString());
						}
						arena.setSettingAuth(1937283 + 1001008, scenarios, sets);
					} 
					catch (Exception e)
					{
						e.printStackTrace();
					}
				}	
				try
				{
					refresh_scenario(false);
				}
				catch(Exception e)
				{
					
				}
			}
		}
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				StringBuilder report = new StringBuilder("");
				report = report.append(sets.getLang().getText(Language.RESULT)).append("\n\n");
				
				report = report.append(sets.getLang().getText(Language.NAME)).append("\t").append(arena.getName()).append("\n");
				report = report.append(sets.getLang().getText(Language.POINT)).append("\t").append(arena.getPoint().toString()).append("\n");
				report = report.append(sets.getLang().getText(Language.CATCH) + sets.getLang().getText(Language.ENEMY)).append("\t").append(arena.getCatchEnemy().toString()).append("\n");
				report = report.append(sets.getLang().getText(Language.CATCH) + sets.getLang().getText(Language.ITEM )).append("\t").append(arena.getCatchItem().toString()).append("\n");
				finish_layout.last(finish_centerPanel);
				
				finish_resultField.setText(arena.getPoint().toString());
				finish_catchEnemyField.setText(arena.getCatchEnemy().toString());
				finish_catchItemField.setText(arena.getCatchItem().toString());
				finish_nameField.setText(arena.getName());
				finish_authField.setText(arena.getAuth());
				finish_authField2.setText(arena.getAuth());
				if(! arena.getAuth().equals(sets.getLang().getText(Language.DESCRIPTIONS + 18)))
				{
					try
					{
						boolean exists = false;
						for(String s : sets.getReflexioner_ranks())
						{
							if(arena.getAuth().equals(s))
							{
								exists = true;
								break;
							}
						}
						if(! exists)
						{
							sets.getReflexioner_ranks().add(arena.getAuth());
						}
					}
					catch(Exception e)
					{
						
					}
				}
				String continue_label = "";
				boolean continue_available = true;
				
				if(arena.player_hp() >= 0)
				{
					if(arena.player_hp() >= 1)
					{
						bt_saveState.setVisible(true);
						continue_label = sets.getLang().getText(Language.CONTINUES);						
					}
					else
					{
						bt_saveState.setVisible(false);
						continue_label = sets.getLang().getText(Language.CONTINUE_ON_ZERO);	
						continue_label = continue_label + " (" + String.valueOf(arena.continue_lefts() + ")");
						continue_available = (arena.continue_lefts() >= 1);
					}
				}
				else
				{
					bt_saveState.setVisible(false);
					continue_label = sets.getLang().getText(Language.CONTINUE_ON_ZERO);	
					continue_label = continue_label + " (" + String.valueOf(arena.continue_lefts() + ")");
					continue_available = (arena.continue_lefts() >= 1);
				}
				
				bt_continue.setText(continue_label);
				bt_continue.setEnabled(continue_available);
				bt_saveReplay.setVisible(Reflexioner.replay_save);
				
				
				if(arena.event_accepted)					
				{
					try
					{
						if(Desktop.isDesktopSupported())
							bt_event_open.setVisible(true);
						else
							bt_event_open.setVisible(false);
					} 
					catch (Exception e)
					{
						bt_event_open.setVisible(false);
					}
				}
				else
				{
					bt_event_open.setVisible(false);
				}
				finish_ta.setText(report.toString().trim());
			}			
		});		
		arena.setScenario(null);
	}
	public void actionPerformed(ActionEvent e)
	{
		Object ob = e.getSource();
		if(ob instanceof JButton)
		{
			SoundCache.play("click");
		}
		if(ob == bt_start)
		{						
			start();
		}
		else if(ob == bt_start_today)
		{
			start(today_ship, today_difficulty, startItem, start_today_nameField.getText());
		}
		else if(ob == bt_viewOnEditor)
		{
			try
			{
				scenarioEditor.setScenario((AReflexScenario) scenarios.get(start_scenarioList.getSelectedIndex()));
				scenarioEditor.open();
			} 
			catch (Exception e1)
			{
				JOptionPane.showMessageDialog(startDialog, sets.getLang().getText(Language.ERROR) + " : " + e1.getMessage());
			}
		}
		else if(ob == bt_setUrl)
		{
			String urlDesc, url2Desc, urlGet, url2Get;
			
			urlDesc = sets.getLang().getText(Language.INPUT_URL);
			url2Desc = sets.getLang().getText(Language.INPUT_URL2);
			
			urlGet = JOptionPane.showInputDialog(startDialog, urlDesc);
			url2Get = JOptionPane.showInputDialog(startDialog, url2Desc);
			
			if(urlGet != null) download_url1 = urlGet;
			if(url2Get != null) download_url2 = url2Get;
			
			SwingUtilities.invokeLater(new Runnable()
			{
				@Override
				public void run()
				{
					start_download_message.setText(sets.getLang().getText(Language.CHECK_RELIABILITY) + "\n" + download_url1 + "\n" + download_url2 + "\n\n"
							+ sets.getLang().getText(Language.WILL_DOWNLOAD_AT) + " " + sets.getDefault_path() + "\n\n");
					try
					{
						start_download_message.append(get_imagepack_license());
					} 
					catch (Exception e)
					{
						
					}
				}
			});	
			
		}
		else if(ob == menu_view_message)
		{
			messageDialog.setVisible(true);
		}
		else if(ob == bt_close_message)
		{
			messageDialog.setVisible(false);
		}
		else if(ob == menu_help_files)
		{
			update_needfiles();
			needfileDialog.setVisible(true);
		}
		else if(ob == menu_view_editor)
		{
			scenarioEditor.open();
		}
		else if(ob == menu_view_replayPlayer)
		{
			replayPlayer.open();
		}
		else if(ob == menu_file_start)
		{
			start_findby_tab();
			
		}
		else if(ob == bt_start_userDefined)
		{
			userDefinedDialog.setVisible(false);
			start_userDefined_nameField.setText(userDefined_nameField.getText());
			start(userDefined_area.getText());
		}
		else if(ob == bt_start_userDefined2)
		{
			if(start_userDefined_isScenario.isSelected()) 
			{
				AReflexScenario gets;
				try
				{					
					gets = new CReflexScenario(start_userDefinedArea.getText());
					if(gets instanceof CReflexScenario)
					{
						if(((CReflexScenario) gets).getSpaceShip_selectable() != null)
						{
							if(((CReflexScenario) gets).getSpaceShip_selectable().booleanValue())
							{
								selected_scenario_ship = (String) start_scenario_selectShipCombo.getSelectedItem();
								start(gets, selected_scenario_ship);
							}
							else
							{
								start(gets);
							}
						}
						else
						{
							start(gets);
						}						
					}
					else
					{
						start(gets);
					}	
				} 
				catch (Exception e1)
				{
					JOptionPane.showMessageDialog(startDialog, sets.getLang().getText(Language.ERROR) + " : " + e1.getMessage());
				}			
							
			}
			else
			{
				start(start_userDefinedArea.getText());
			}
		}
		else if(ob == bt_start_scenario)
		{
			int getSelected = start_scenarioList.getSelectedIndex();
			if(getSelected >= 0 && getSelected < scenarios.size())
			{
				selected_scenario = scenarios.get(getSelected);
				if(selected_scenario instanceof CReflexScenario)
				{
					if(((CReflexScenario) selected_scenario).getSpaceShip_selectable() != null)
					{
						if(((CReflexScenario) selected_scenario).getSpaceShip_selectable().booleanValue())
						{
							selected_scenario_ship = (String) start_scenario_selectShipCombo.getSelectedItem();
							start(selected_scenario, selected_scenario_ship);
						}
						else
						{
							start(selected_scenario);
						}
					}
					else
					{
						start(selected_scenario);
					}						
				}
				else
				{
					start(selected_scenario);
				}
			}
			else
			{
				JOptionPane.showMessageDialog(startDialog, sets.getLang().getText(Language.ERROR) + " : null");
			}
		}
		else if(ob == bt_autoComplete)
		{
			loadAutoUserDefined();
			autoUserDefinedDialog.setVisible(true);
		}
		else if(ob == bt_exit || ob == menu_file_exit || ob == bt_exit2 || ob == bt_exit3 || ob == bt_exit4 || ob == bt_exit5 || ob == bt_exit6)
		{
			exit();
		}
		else if(ob == bt_closeAutoUserDefined)
		{
			autoUserDefinedDialog.setVisible(false);
		}
		else if(ob == bt_acceptAutoUserDefined)
		{
			acceptAutoUserDefined();
			autoUserDefinedDialog.setVisible(false);
		}
		else if(ob == bt_close_userDefined)
		{
			userDefinedDialog.setVisible(false);
		}
		else if(ob == menu_file_start_userDefined)
		{
			userDefined_area.setText(userDefined_first());
			userDefined_nameField.setText(start_nameField.getText());
			userDefinedDialog.setVisible(true);
		}
		else if(ob == bt_finish_ok)
		{
			finishDialog.setVisible(false);
			startDialog.setVisible(true);
		}
		else if(ob == bt_left)
		{
			arena.keyPressed(new KeyEvent(arena, 0, 0, 0, KEY_LEFT, '←'));
		}
		else if(ob == bt_right)
		{
			arena.keyPressed(new KeyEvent(arena, 0, 0, 0, KEY_RIGHT, '→'));
		}
		else if(ob == bt_fire)
		{
			arena.keyPressed(new KeyEvent(arena, 0, 0, 0, KEY_SPACE, ' '));
		}
		else if(ob == bt_w1)
		{
			arena.keyPressed(new KeyEvent(arena, 0, 0, 0, KEY_1, '1'));
		}
		else if(ob == bt_w2)
		{
			arena.keyPressed(new KeyEvent(arena, 0, 0, 0, KEY_2, '2'));
		}
		else if(ob == bt_w3)
		{
			arena.keyPressed(new KeyEvent(arena, 0, 0, 0, KEY_3, '3'));
		}
		else if(ob == bt_stop)
		{
			arena.game_finish();
		}
		else if(ob == bt_touch)
		{
			touchSetting();
		}
		else if(ob == bt_authCopy)
		{
			try
			{
				Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(finish_authField.getText()), null);
				alert(sets.getLang().getText(Language.DESC_COPY_CLIPBOARD));
			} 
			catch (Exception e1)
			{
				if(sets.isError_printDetail()) e1.printStackTrace();
				alert(sets.getLang().getText(Language.ERROR) + " : " + e1.getMessage());
			}
		}
		else if(ob == bt_authCopy2)
		{
			try
			{
				Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(finish_authField2.getText()), null);
				alert(sets.getLang().getText(Language.DESC_COPY_CLIPBOARD));
			} 
			catch (Exception e1)
			{
				if(sets.isError_printDetail()) e1.printStackTrace();
				alert(sets.getLang().getText(Language.ERROR) + " : " + e1.getMessage());
			}
		}
		else if(ob == menu_view_check)
		{
			if(checker == null) checker = new Code_Checker(false, startDialog, sets);
			checker.open();
		}
		else if(ob == bt_saveState)
		{
			saveState();
		}
		else if(ob == menu_file_load)
		{
			loadState();
		}
		else if(ob == bt_saveReplay)
		{
			saveReplay();
		}
		else if(ob == bt_continue)
		{
			arena.game_continue();
			finishDialog.setVisible(false);
			arena.resume();
		}
		else if(ob == menu_help_about)
		{
			aboutDialog.setVisible(true);
		}		
		else if(ob == bt_aboutClose)
		{
			aboutDialog.setVisible(false);
		}
		else if(ob == bt_next)
		{
			//mainTab.setSelectedIndex(1);
			mainTab.setSelectedComponent(start_standardPanel);
		}
		else if(ob == bt_scenario)
		{
			//mainTab.setSelectedIndex(1);
			mainTab.setSelectedComponent(start_scenarioPanel);
		}
		else if(ob == bt_user_defined)
		{
			//mainTab.setSelectedIndex(1);
			mainTab.setSelectedComponent(start_userDefinedPanel);
		}
		else if(ob == bt_event_open)
		{
			try
			{
				if(Desktop.isDesktopSupported())
				{
					Desktop newDesktop = Desktop.getDesktop();// arena.event_url
					newDesktop.browse(new URI(sets.getNotice_url() + "arena.event_url"));				
				}
			} 
			catch (Exception e1)
			{
				e1.printStackTrace();
			}
		}
		else if(ob == bt_download)
		{
			try_download_imagepack(download_url1, download_url2);
		}
		else if(ob == menu_manage_uninstall)
		{
			Uninstaller.uninstall(this, sets, true, true, false, true, startDialog);
		}
		else if(ob == menu_manage_setIntervalReplay)
		{
			String inputs, message;
			message = sets.getLang().getText(Language.INPUT_REPLAY_GAP);
			inputs = JOptionPane.showInputDialog(startDialog, message, String.valueOf(replay_interval));
			try
			{
				replay_interval = Integer.parseInt(inputs);
			}
			catch(NumberFormatException e1)
			{
				JOptionPane.showMessageDialog(startDialog, sets.getLang().getText(Language.ERROR) + " : " + e1.getMessage());
			}
		}
	}	
	private void update_needfiles()
	{
		List<String> needFileList, existFileList;
		needFileList = ImageCache.needFiles(sets, true);
		existFileList = new Vector<String>();
		try
		{
			File dir = new File(sets.getDefault_path());
			if(dir.exists())
			{
				File[] files = dir.listFiles();
				for(int i=0; i<files.length; i++)
				{
					existFileList.add(files[i].getName());
				}
			}
		}
		catch(Exception e)
		{
			
		}
		needfile_descList.setText("");
		if(sets.getLang() instanceof Korean)
		{
			needfile_descList.setText("");
		}
		else
		{
			needfile_descList.setText("This description is prepared only for korean.\n\n");
		}
		for(int i=0; i<needFileList.size(); i++)
		{			
			needfile_descList.append(needFileList.get(i) + "\n");
		}
		needfile_fileList.setText("");
		for(int i=0; i<existFileList.size(); i++)
		{			
			needfile_fileList.append(existFileList.get(i) + "\n");
		}
		needfile_descList.setCaretPosition(0);
		needfile_split.setDividerLocation((int)(needfileDialog.getWidth() / 1.5));
	}
	public void start_findby_tab()
	{
		Component comp = mainTab.getSelectedComponent();
		if(comp == start_standardPanel)
		{
			start();
		}
		else if(comp == start_scenarioPanel)
		{
			int getSelected = start_scenarioList.getSelectedIndex();
			if(getSelected >= 0 && getSelected < scenarios.size())
			{
				selected_scenario = scenarios.get(getSelected);
				if(selected_scenario instanceof CReflexScenario)
				{
					if(((CReflexScenario) selected_scenario).getSpaceShip_selectable() != null)
					{
						if(((CReflexScenario) selected_scenario).getSpaceShip_selectable().booleanValue())
						{
							selected_scenario_ship = (String) start_scenario_selectShipCombo.getSelectedItem();
							start(selected_scenario, selected_scenario_ship);
						}
						else
						{
							start(selected_scenario);
						}
					}
					else
					{
						start(selected_scenario);
					}						
				}
				else
				{
					start(selected_scenario);
				}
			}
			else
			{
				JOptionPane.showMessageDialog(startDialog, sets.getLang().getText(Language.ERROR) + " : null");
			}
		}
		else if(comp == start_userDefinedPanel)
		{
			if(start_userDefined_isScenario.isSelected()) 
			{
				AReflexScenario gets;
				try
				{
					gets = new AReflexScenario(start_userDefinedArea.getText());
					start(gets);
				} 
				catch (Exception e1)
				{
					JOptionPane.showMessageDialog(startDialog, sets.getLang().getText(Language.ERROR) + " : " + e1.getMessage());
				}			
							
			}
			else
			{
				start(start_userDefinedArea.getText());
			}
		}
		else if(comp == start_todayPanel)
		{
			if(bt_start_today.isEnabled())
				start(today_ship, today_difficulty, startItem, start_today_nameField.getText());
		}
		
		/*
		switch(mainTab.getSelectedIndex())
		{
			case 1:
				start();
				break;
			case 2:
				int getSelected = start_scenarioList.getSelectedIndex();
				if(getSelected >= 0 && getSelected < scenarios.size())
				{
					selected_scenario = scenarios.get(getSelected);
					if(selected_scenario instanceof CReflexScenario)
					{
						if(((CReflexScenario) selected_scenario).getSpaceShip_selectable() != null)
						{
							if(((CReflexScenario) selected_scenario).getSpaceShip_selectable().booleanValue())
							{
								selected_scenario_ship = (String) start_scenario_selectShipCombo.getSelectedItem();
								start(selected_scenario, selected_scenario_ship);
							}
							else
							{
								start(selected_scenario);
							}
						}
						else
						{
							start(selected_scenario);
						}						
					}
					else
					{
						start(selected_scenario);
					}
				}
				else
				{
					JOptionPane.showMessageDialog(startDialog, sets.getLang().getText(Language.ERROR) + " : null");
				}
				break;
			case 3:
				if(start_userDefined_isScenario.isSelected()) 
				{
					AReflexScenario gets;
					try
					{
						gets = new AReflexScenario(start_userDefinedArea.getText());
						start(gets);
					} 
					catch (Exception e1)
					{
						JOptionPane.showMessageDialog(startDialog, sets.getLang().getText(Language.ERROR) + " : " + e1.getMessage());
					}			
								
				}
				else
				{
					start(start_userDefinedArea.getText());
				}
				break;
		}
		*/
		
	}
	private void loadAutoUserDefined()
	{
		String gets = start_userDefinedArea.getText();
		StringTokenizer lineToken = new StringTokenizer(gets, "\n");
		StringTokenizer barToken, weaponToken;
		String line;
		String key, content, w_key, w_content;
		while(lineToken.hasMoreTokens())
		{			
			line = lineToken.nextToken();
			if(line.trim().startsWith("#")) continue;
			barToken = new StringTokenizer(line, "||");
			while(barToken.hasMoreTokens())
			{
				try
				{				
					key = barToken.nextToken();
					content = barToken.nextToken();
					
					if(key.equalsIgnoreCase("name"))
					{
						autoUserDefined_fields[AUTO_SHIPNAME].setText(content);
					}
					else if(key.equalsIgnoreCase("hp"))
					{
						autoUserDefined_fields[AUTO_SHIP_HP].setText(content);
					}
					else if(key.equalsIgnoreCase("energy"))
					{
						autoUserDefined_fields[AUTO_SHIP_ENERGY].setText(content);
					}
					else if(key.equalsIgnoreCase("speed"))
					{
						autoUserDefined_fields[AUTO_SHIP_SPEED].setText(content);
					}
					else if(key.equalsIgnoreCase("size"))
					{
						autoUserDefined_fields[AUTO_SHIP_SIZE].setText(content);
					}
					else if(key.equalsIgnoreCase("shape"))
					{
						autoUserDefined_fields[AUTO_SHIP_SHAPE].setText(content);
					}
					else if(key.equalsIgnoreCase("auth"))
					{
						autoUserDefined_fields[AUTO_LAST].setText(content);
					}
					else if(key.equalsIgnoreCase("weapon1") || key.equalsIgnoreCase("weapon2") || key.equalsIgnoreCase("weapon3"))
					{
						weaponToken = new StringTokenizer(content, "=");
						w_key = weaponToken.nextToken().trim();
						w_content = weaponToken.nextToken().trim();
						if(w_key.equalsIgnoreCase("type"))
						{
							if(key.equalsIgnoreCase("weapon1"))
							{
								autoUserDefined_fields[AUTO_WEAPON_1_TYPE].setText(w_content);
							}
							else if(key.equalsIgnoreCase("weapon2"))
							{
								autoUserDefined_fields[AUTO_WEAPON_2_TYPE].setText(w_content);
							}
							else if(key.equalsIgnoreCase("weapon3"))
							{
								autoUserDefined_fields[AUTO_WEAPON_3_TYPE].setText(w_content);
							}
						}
						else if(w_key.equalsIgnoreCase("max"))
						{
							if(key.equalsIgnoreCase("weapon1"))
							{
								autoUserDefined_fields[AUTO_WEAPON_1_MAX].setText(w_content);
							}
							else if(key.equalsIgnoreCase("weapon2"))
							{
								autoUserDefined_fields[AUTO_WEAPON_2_MAX].setText(w_content);
							}
							else if(key.equalsIgnoreCase("weapon3"))
							{
								autoUserDefined_fields[AUTO_WEAPON_3_MAX].setText(w_content);
							}
						}
						else if(w_key.equalsIgnoreCase("min"))
						{
							if(key.equalsIgnoreCase("weapon1"))
							{
								autoUserDefined_fields[AUTO_WEAPON_1_MIN].setText(w_content);
							}
							else if(key.equalsIgnoreCase("weapon2"))
							{
								autoUserDefined_fields[AUTO_WEAPON_2_MIN].setText(w_content);
							}
							else if(key.equalsIgnoreCase("weapon3"))
							{
								autoUserDefined_fields[AUTO_WEAPON_3_MIN].setText(w_content);
							}
						}
						else if(w_key.equalsIgnoreCase("interval"))
						{
							if(key.equalsIgnoreCase("weapon1"))
							{
								autoUserDefined_fields[AUTO_WEAPON_1_INTERVAL].setText(w_content);
							}
							else if(key.equalsIgnoreCase("weapon2"))
							{
								autoUserDefined_fields[AUTO_WEAPON_2_INTERVAL].setText(w_content);
							}
							else if(key.equalsIgnoreCase("weapon3"))
							{
								autoUserDefined_fields[AUTO_WEAPON_3_INTERVAL].setText(w_content);
							}
						}
						else if(w_key.equalsIgnoreCase("hp"))
						{
							if(key.equalsIgnoreCase("weapon1"))
							{
								autoUserDefined_fields[AUTO_WEAPON_1_HP].setText(w_content);
							}
							else if(key.equalsIgnoreCase("weapon2"))
							{
								autoUserDefined_fields[AUTO_WEAPON_2_HP].setText(w_content);
							}
							else if(key.equalsIgnoreCase("weapon3"))
							{
								autoUserDefined_fields[AUTO_WEAPON_3_HP].setText(w_content);
							}
						}
						else if(w_key.equalsIgnoreCase("needs"))
						{
							if(key.equalsIgnoreCase("weapon1"))
							{
								autoUserDefined_fields[AUTO_WEAPON_1_NEEDS].setText(w_content);
							}
							else if(key.equalsIgnoreCase("weapon2"))
							{
								autoUserDefined_fields[AUTO_WEAPON_2_NEEDS].setText(w_content);
							}
							else if(key.equalsIgnoreCase("weapon3"))
							{
								autoUserDefined_fields[AUTO_WEAPON_3_NEEDS].setText(w_content);
							}
						}
						else if(w_key.equalsIgnoreCase("speed"))
						{
							if(key.equalsIgnoreCase("weapon1"))
							{
								autoUserDefined_fields[AUTO_WEAPON_1_SPEED].setText(w_content);
							}
							else if(key.equalsIgnoreCase("weapon2"))
							{
								autoUserDefined_fields[AUTO_WEAPON_2_SPEED].setText(w_content);
							}
							else if(key.equalsIgnoreCase("weapon3"))
							{
								autoUserDefined_fields[AUTO_WEAPON_3_SPEED].setText(w_content);
							}
						}
						else if(w_key.equalsIgnoreCase("size"))
						{
							if(key.equalsIgnoreCase("weapon1"))
							{
								autoUserDefined_fields[AUTO_WEAPON_1_SIZE].setText(w_content);
							}
							else if(key.equalsIgnoreCase("weapon2"))
							{
								autoUserDefined_fields[AUTO_WEAPON_2_SIZE].setText(w_content);
							}
							else if(key.equalsIgnoreCase("weapon3"))
							{
								autoUserDefined_fields[AUTO_WEAPON_3_SIZE].setText(w_content);
							}
						}
						else if(w_key.equalsIgnoreCase("damage"))
						{
							if(key.equalsIgnoreCase("weapon1"))
							{
								autoUserDefined_fields[AUTO_WEAPON_1_DAMAGE].setText(w_content);
							}
							else if(key.equalsIgnoreCase("weapon2"))
							{
								autoUserDefined_fields[AUTO_WEAPON_2_DAMAGE].setText(w_content);
							}
							else if(key.equalsIgnoreCase("weapon3"))
							{
								autoUserDefined_fields[AUTO_WEAPON_3_DAMAGE].setText(w_content);
							}
						}
						else if(w_key.equalsIgnoreCase("delay"))
						{
							if(key.equalsIgnoreCase("weapon1"))
							{
								autoUserDefined_fields[AUTO_WEAPON_1_DELAY].setText(w_content);
							}
							else if(key.equalsIgnoreCase("weapon2"))
							{
								autoUserDefined_fields[AUTO_WEAPON_2_DELAY].setText(w_content);
							}
							else if(key.equalsIgnoreCase("weapon3"))
							{
								autoUserDefined_fields[AUTO_WEAPON_3_DELAY].setText(w_content);
							}
						}
						else if(w_key.equalsIgnoreCase("helper_type"))
						{
							if(key.equalsIgnoreCase("weapon1"))
							{
								autoUserDefined_fields[AUTO_WEAPON_1_HELPER_TYPE].setText(w_content);
							}
							else if(key.equalsIgnoreCase("weapon2"))
							{
								autoUserDefined_fields[AUTO_WEAPON_2_HELPER_TYPE].setText(w_content);
							}
							else if(key.equalsIgnoreCase("weapon3"))
							{
								autoUserDefined_fields[AUTO_WEAPON_3_HELPER_TYPE].setText(w_content);
							}
						}
						else if(w_key.equalsIgnoreCase("helper_count"))
						{
							if(key.equalsIgnoreCase("weapon1"))
							{
								autoUserDefined_fields[AUTO_WEAPON_1_HELPER_COUNT].setText(w_content);
							}
							else if(key.equalsIgnoreCase("weapon2"))
							{
								autoUserDefined_fields[AUTO_WEAPON_1_HELPER_COUNT].setText(w_content);
							}
							else if(key.equalsIgnoreCase("weapon3"))
							{
								autoUserDefined_fields[AUTO_WEAPON_1_HELPER_COUNT].setText(w_content);
							}
						}
					}
				}
				catch(Exception e)
				{
					
				}
			}		
		}
	}
	private void acceptAutoUserDefined()
	{
		String results = "";
		
		results = results + "# " + autoUserDefined_labels[AUTO_SHIPNAME].getText() + "\n";
		results = results + "name||" + autoUserDefined_fields[AUTO_SHIPNAME].getText() + "\n";
		results = results + "# " + autoUserDefined_labels[AUTO_SHIP_HP].getText() + "\n";
		results = results + "hp||" + autoUserDefined_fields[AUTO_SHIP_HP].getText() + "\n";
		results = results + "# " + autoUserDefined_labels[AUTO_SHIP_ENERGY].getText() + "\n";
		results = results + "energy||" + autoUserDefined_fields[AUTO_SHIP_ENERGY].getText() + "\n";
		results = results + "# " + autoUserDefined_labels[AUTO_SHIP_SPEED].getText() + "\n";
		results = results + "speed||" + autoUserDefined_fields[AUTO_SHIP_SPEED].getText() + "\n";
		results = results + "# " + autoUserDefined_labels[AUTO_SHIP_SIZE].getText() + "\n";
		results = results + "size||" + autoUserDefined_fields[AUTO_SHIP_SIZE].getText() + "\n";
		results = results + "# " + autoUserDefined_labels[AUTO_SHIP_SHAPE].getText() + "\n";
		results = results + "shape||" + autoUserDefined_fields[AUTO_SHIP_SHAPE].getText() + "\n\n";
		
		results = results + "# " + autoUserDefined_labels[AUTO_WEAPON_1_TYPE].getText() + "\n";
		results = results + "weapon1||type=" + autoUserDefined_fields[AUTO_WEAPON_1_TYPE].getText() + "\n";
		results = results + "# " + autoUserDefined_labels[AUTO_WEAPON_1_MAX].getText() + "\n";
		results = results + "weapon1||max=" + autoUserDefined_fields[AUTO_WEAPON_1_MAX].getText() + "\n";
		results = results + "# " + autoUserDefined_labels[AUTO_WEAPON_1_MIN].getText() + "\n";
		results = results + "weapon1||min=" + autoUserDefined_fields[AUTO_WEAPON_1_MIN].getText() + "\n";
		results = results + "# " + autoUserDefined_labels[AUTO_WEAPON_1_INTERVAL].getText() + "\n";
		results = results + "weapon1||interval=" + autoUserDefined_fields[AUTO_WEAPON_1_INTERVAL].getText() + "\n";
		results = results + "# " + autoUserDefined_labels[AUTO_WEAPON_1_HP].getText() + "\n";
		results = results + "weapon1||hp=" + autoUserDefined_fields[AUTO_WEAPON_1_HP].getText() + "\n";
		results = results + "# " + autoUserDefined_labels[AUTO_WEAPON_1_NEEDS].getText() + "\n";
		results = results + "weapon1||needs=" + autoUserDefined_fields[AUTO_WEAPON_1_NEEDS].getText() + "\n";
		results = results + "# " + autoUserDefined_labels[AUTO_WEAPON_1_SPEED].getText() + "\n";
		results = results + "weapon1||speed=" + autoUserDefined_fields[AUTO_WEAPON_1_SPEED].getText() + "\n";
		results = results + "# " + autoUserDefined_labels[AUTO_WEAPON_1_SIZE].getText() + "\n";
		results = results + "weapon1||size=" + autoUserDefined_fields[AUTO_WEAPON_1_SIZE].getText() + "\n";
		results = results + "# " + autoUserDefined_labels[AUTO_WEAPON_1_DAMAGE].getText() + "\n";
		results = results + "weapon1||damage=" + autoUserDefined_fields[AUTO_WEAPON_1_DAMAGE].getText() + "\n";
		if(! (autoUserDefined_fields[AUTO_WEAPON_1_HELPER_TYPE].getText().trim().equalsIgnoreCase("")))
		{
			results = results + "# " + autoUserDefined_labels[AUTO_WEAPON_1_HELPER_TYPE].getText() + "\n";
			results = results + "weapon1||helper_type=" + autoUserDefined_fields[AUTO_WEAPON_1_HELPER_TYPE].getText() + "\n";
		}
		if(! (autoUserDefined_fields[AUTO_WEAPON_1_HELPER_COUNT].getText().trim().equalsIgnoreCase("")))
		{
			results = results + "# " + autoUserDefined_labels[AUTO_WEAPON_1_HELPER_COUNT].getText() + "\n";
			results = results + "weapon1||helper_count=" + autoUserDefined_fields[AUTO_WEAPON_1_HELPER_COUNT].getText() + "\n";
		}
		results = results + "# " + autoUserDefined_labels[AUTO_WEAPON_1_DELAY].getText() + "\n";
		results = results + "weapon1||delay=" + autoUserDefined_fields[AUTO_WEAPON_1_DELAY].getText() + "\n\n";
		
		results = results + "# " + autoUserDefined_labels[AUTO_WEAPON_2_TYPE].getText() + "\n";
		results = results + "weapon2||type=" + autoUserDefined_fields[AUTO_WEAPON_2_TYPE].getText() + "\n";
		results = results + "# " + autoUserDefined_labels[AUTO_WEAPON_2_MAX].getText() + "\n";
		results = results + "weapon2||max=" + autoUserDefined_fields[AUTO_WEAPON_2_MAX].getText() + "\n";
		results = results + "# " + autoUserDefined_labels[AUTO_WEAPON_2_MIN].getText() + "\n";
		results = results + "weapon2||min=" + autoUserDefined_fields[AUTO_WEAPON_2_MIN].getText() + "\n";
		results = results + "# " + autoUserDefined_labels[AUTO_WEAPON_2_INTERVAL].getText() + "\n";
		results = results + "weapon2||interval=" + autoUserDefined_fields[AUTO_WEAPON_2_INTERVAL].getText() + "\n";
		results = results + "# " + autoUserDefined_labels[AUTO_WEAPON_2_HP].getText() + "\n";
		results = results + "weapon2||hp=" + autoUserDefined_fields[AUTO_WEAPON_2_HP].getText() + "\n";
		results = results + "# " + autoUserDefined_labels[AUTO_WEAPON_2_NEEDS].getText() + "\n";
		results = results + "weapon2||needs=" + autoUserDefined_fields[AUTO_WEAPON_2_NEEDS].getText() + "\n";
		results = results + "# " + autoUserDefined_labels[AUTO_WEAPON_2_SPEED].getText() + "\n";
		results = results + "weapon2||speed=" + autoUserDefined_fields[AUTO_WEAPON_2_SPEED].getText() + "\n";
		results = results + "# " + autoUserDefined_labels[AUTO_WEAPON_2_SIZE].getText() + "\n";
		results = results + "weapon2||size=" + autoUserDefined_fields[AUTO_WEAPON_2_SIZE].getText() + "\n";
		results = results + "# " + autoUserDefined_labels[AUTO_WEAPON_2_DAMAGE].getText() + "\n";
		results = results + "weapon2||damage=" + autoUserDefined_fields[AUTO_WEAPON_2_DAMAGE].getText() + "\n";
		if(! (autoUserDefined_fields[AUTO_WEAPON_2_HELPER_TYPE].getText().trim().equalsIgnoreCase("")))
		{
			results = results + "# " + autoUserDefined_labels[AUTO_WEAPON_2_HELPER_TYPE].getText() + "\n";
			results = results + "weapon2||helper_type=" + autoUserDefined_fields[AUTO_WEAPON_2_HELPER_TYPE].getText() + "\n";
		}
		if(! (autoUserDefined_fields[AUTO_WEAPON_2_HELPER_COUNT].getText().trim().equalsIgnoreCase("")))
		{
			results = results + "# " + autoUserDefined_labels[AUTO_WEAPON_2_HELPER_COUNT].getText() + "\n";
			results = results + "weapon2||helper_count=" + autoUserDefined_fields[AUTO_WEAPON_2_HELPER_COUNT].getText() + "\n";
		}
		results = results + "# " + autoUserDefined_labels[AUTO_WEAPON_2_DELAY].getText() + "\n";
		results = results + "weapon2||delay=" + autoUserDefined_fields[AUTO_WEAPON_2_DELAY].getText() + "\n\n";
		
		results = results + "# " + autoUserDefined_labels[AUTO_WEAPON_3_TYPE].getText() + "\n";
		results = results + "weapon3||type=" + autoUserDefined_fields[AUTO_WEAPON_3_TYPE].getText() + "\n";
		results = results + "# " + autoUserDefined_labels[AUTO_WEAPON_3_MAX].getText() + "\n";
		results = results + "weapon3||max=" + autoUserDefined_fields[AUTO_WEAPON_3_MAX].getText() + "\n";
		results = results + "# " + autoUserDefined_labels[AUTO_WEAPON_3_MIN].getText() + "\n";
		results = results + "weapon3||min=" + autoUserDefined_fields[AUTO_WEAPON_3_MIN].getText() + "\n";
		results = results + "# " + autoUserDefined_labels[AUTO_WEAPON_3_INTERVAL].getText() + "\n";
		results = results + "weapon3||interval=" + autoUserDefined_fields[AUTO_WEAPON_3_INTERVAL].getText() + "\n";
		results = results + "# " + autoUserDefined_labels[AUTO_WEAPON_3_HP].getText() + "\n";
		results = results + "weapon3||hp=" + autoUserDefined_fields[AUTO_WEAPON_3_HP].getText() + "\n";
		results = results + "# " + autoUserDefined_labels[AUTO_WEAPON_3_NEEDS].getText() + "\n";
		results = results + "weapon3||needs=" + autoUserDefined_fields[AUTO_WEAPON_3_NEEDS].getText() + "\n";
		results = results + "# " + autoUserDefined_labels[AUTO_WEAPON_3_SPEED].getText() + "\n";
		results = results + "weapon3||speed=" + autoUserDefined_fields[AUTO_WEAPON_3_SPEED].getText() + "\n";
		results = results + "# " + autoUserDefined_labels[AUTO_WEAPON_3_SIZE].getText() + "\n";
		results = results + "weapon3||size=" + autoUserDefined_fields[AUTO_WEAPON_3_SIZE].getText() + "\n";
		results = results + "# " + autoUserDefined_labels[AUTO_WEAPON_3_DAMAGE].getText() + "\n";
		results = results + "weapon3||damage=" + autoUserDefined_fields[AUTO_WEAPON_3_DAMAGE].getText() + "\n";
		if(! (autoUserDefined_fields[AUTO_WEAPON_3_HELPER_TYPE].getText().trim().equalsIgnoreCase("")))
		{
			results = results + "# " + autoUserDefined_labels[AUTO_WEAPON_3_HELPER_TYPE].getText() + "\n";
			results = results + "weapon3||helper_type=" + autoUserDefined_fields[AUTO_WEAPON_3_HELPER_TYPE].getText() + "\n";
		}
		if(! (autoUserDefined_fields[AUTO_WEAPON_3_HELPER_COUNT].getText().trim().equalsIgnoreCase("")))
		{
			results = results + "# " + autoUserDefined_labels[AUTO_WEAPON_3_HELPER_COUNT].getText() + "\n";
			results = results + "weapon3||helper_count=" + autoUserDefined_fields[AUTO_WEAPON_3_HELPER_COUNT].getText() + "\n";
		}
		results = results + "# " + autoUserDefined_labels[AUTO_WEAPON_3_DELAY].getText() + "\n";
		results = results + "weapon3||delay=" + autoUserDefined_fields[AUTO_WEAPON_3_DELAY].getText() + "\n\n";
		
		results = results + "version||" + (String.valueOf(version_main) + String.valueOf(version_sub_1) + String.valueOf(version_sub_2)
				+ " "  + String.valueOf(version_nightly) + " " + String.valueOf(version_test)).trim() + "\n";
		
		if(! (autoUserDefined_fields[AUTO_LAST].getText().trim().equalsIgnoreCase("")))
		{
			results = results + "auth||" + autoUserDefined_fields[AUTO_LAST].getText() + "\n\n";
		}
		else
		{
			results = results + "auth||0" + "\n\n";
		}
				
		if(sets.getLang() instanceof Korean)
		{
			results = "# 사용자 정의 함선 스크립트가 완성되었습니다.\n\n" + results;
		}
		else results = "# User-Made Ship script is created.\n\n" + results;
		
		start_userDefined_isScenario.setSelected(false);
		start_userDefinedArea.setText(results);		
		start_userDefinedArea.setCaretPosition(0);
	}
	private String get_imagepack_license()
	{
		String reads = "";
		String results = "";
		InputStream inputStream = null;
		InputStreamReader inputReader = null;
		BufferedReader bufferedReader = null;		
		int not_infinite_loop = 0;
		try
		{
			inputStream = new URL(RunManager.r65279(download_url1 + "reflex_imagepack_license.txt")).openStream();
		}
		catch(Exception e)
		{
			try
			{
				inputStream.close();
			}
			catch(Exception e1)
			{
				
			}
			try
			{
				inputStream = new URL(RunManager.r65279(download_url2 + "reflex_imagepack_license.txt")).openStream();
			} 
			catch (Exception e1)
			{
				return null;
			}
		}
		try
		{
			inputReader = new InputStreamReader(inputStream);
			bufferedReader = new BufferedReader(inputReader);
			while(true)
			{
				reads = bufferedReader.readLine();
				if(reads == null) break;
				not_infinite_loop++;
				if(not_infinite_loop >= 10000) break;
				results = results + reads + "\n";
			}			
		}
		catch (Exception e1)
		{
			results = null;
		}
		finally
		{
			try
			{
				bufferedReader.close();
			}
			catch(Exception e)
			{
				
			}
			try
			{
				inputReader.close();
			}
			catch(Exception e)
			{
				
			}
			try
			{
				inputStream.close();
			}
			catch(Exception e)
			{
				
			}
		}
		return sets.getLang().getText(Language.LICENSE) + "\n" + RunManager.r65279(results);
	}
	private void try_download_imagepack(String url, String url2)
	{
		Vector<String> urlList = new Vector<String>();
		String reads = "";
		InputStream inputStream = null;
		InputStreamReader inputReader = null;
		BufferedReader bufferedReader = null;		
		int not_infinite_loop = 0;
		String success_url = null; 
		
		download_limit--;		
		bt_download.setEnabled(false);
		menuBar.setEnabled(false);
		mainTab.setEnabled(false);
		
		
		try
		{
			success_url = url;
			inputStream = new URL(RunManager.r65279(url + "reflex_imagelist.txt")).openStream();
		}
		catch(Exception e)
		{
			try
			{
				inputStream.close();
			}
			catch(Exception e1)
			{
				
			}
			try
			{
				success_url = url2;
				inputStream = new URL(RunManager.r65279(url2 + "reflex_imagelist.txt")).openStream();
			} 
			catch (Exception e1)
			{
				return;
			}
		}
		start_download_message.setText(sets.getLang().getText(Language.CHECK_RELIABILITY) + "\n" + sets.getNotice_url() + "\n" + sets.getNotice_url2() + "\n\n" 
		+ sets.getLang().getText(Language.LEFT) + " " + sets.getLang().getText(Language.ITEM) + "\n");
		try
		{
			start_download_message.append("\n" + get_imagepack_license() + "\n");
		}
		catch(Exception e)
		{
			
		}
		if(inputStream != null)
		{
			try 
			{
				inputReader = new InputStreamReader(inputStream);
				bufferedReader = new BufferedReader(inputReader);
				while(true)
				{
					if(not_infinite_loop >= 1000) break;
					else not_infinite_loop++;
					try
					{
						reads = bufferedReader.readLine();
						if(reads == null) break;
						if(reads.startsWith("#")) continue;
						if(reads.endsWith(".png") || reads.endsWith(".PNG") || reads.endsWith(".jpg") || reads.endsWith(".JPG"))
						{
							urlList.add(reads.trim());
							start_download_message.append(reads + "\n");
						}					
					}
					catch(Exception e)
					{
						
					}
				}
			}
			finally
			{
				try { bufferedReader.close(); } catch(Exception e) { }
				try { inputReader.close();    } catch(Exception e) { }
			}
		}
		else return;
		try
		{
			inputStream.close();
		}
		catch(Exception e)
		{
			
		}
		
		String[] files = new String[urlList.size()];		
		
		for(int i=0; i<files.length; i++)
		{
			files[i] = urlList.get(i);
		}
		
		if(files.length <= 0) start_download_message.append(sets.getLang().getText(Language.NONE) + "\n");
		else start_download_message.append(sets.getLang().getText(Language.DOWNLOAD) + " " + sets.getLang().getText(Language.ITEM) + "\n");
		
		progress_download.setValue(0);
		File download_target;
		URL download_url = null;
		HttpURLConnection connection = null;
		BufferedInputStream bufferedStream = null;
		FileOutputStream saveStream = null;
		BufferedOutputStream saveBufferedStream = null;
		
		byte[] readBytes = new byte[1024];
		int readInt = 0;
		for(int i=0; i<files.length; i++)
		{
			try
			{
				if(! new File(sets.getDefault_path()).exists())
					new File(sets.getDefault_path()).mkdir();
			}
			catch(Exception e)
			{
				
			}
			try
			{			
				download_url = null;
				//Downloader.download(sets, files[i]);
				download_target = new File(RunManager.r65279(sets.getDefault_path() + files[i]));
				if(success_url.equals(url))
				{
					download_url = new URL(RunManager.r65279(url + files[i].trim()));
				}
				else if(success_url.equals(url2))
				{
					download_url = new URL(RunManager.r65279(url2 + files[i].trim()));					
				}
				if(download_url != null)
				{					
					connection = (HttpURLConnection) download_url.openConnection();
					//connection.connect();
					inputStream = connection.getInputStream();					
					//inputStream = download_url.openStream();
					bufferedStream = new BufferedInputStream(inputStream);
					saveStream = new FileOutputStream(download_target);
					saveBufferedStream = new BufferedOutputStream(saveStream);
					while(true)
					{
						
						readInt = bufferedStream.read(readBytes);
						if(readInt == -1) break;
						saveBufferedStream.write(readBytes, 0, readInt);
					}			
					try
					{
						saveBufferedStream.close();
						saveStream.close();
						bufferedStream.close();
						inputStream.close();
						connection.disconnect();
					}
					catch(Exception e)
					{
						
					}
				
					start_download_message.append(RunManager.r65279(files[i]) + " " + sets.getLang().getText(Language.COMPLETE) + "\n");
					progress_download.setValue((int) Math.round((i / files.length) * 990.0));
				}
			}
			catch(Exception e)
			{
				start_download_message.append(files[i] + " " + sets.getLang().getText(Language.ERROR) + " : " + e.getMessage() + "\n");
				progress_download.setValue((int) Math.round((i / files.length) * 990.0));
			}		
			finally
			{
				try
				{
					saveBufferedStream.close();
				}
				catch(Exception e)
				{
					
				}
				try
				{
					saveStream.close();
				}
				catch(Exception e)
				{
					
				}				
				try
				{
					bufferedStream.close();
				}
				catch(Exception e)
				{
					
				}
				try
				{
					inputStream.close();
				}
				catch(Exception e)
				{
					
				}
				try
				{
					connection.disconnect();
				}
				catch(Exception e)
				{
					
				}
			}
		}
		//waitTime(1000);
		
		loadAllImage();
		progress_download.setValue(1000);
		start_download_message.append("\n" + sets.getLang().getText(Language.COMPLETE) + "\n");
		if(download_limit <= 0)
		{
			SwingUtilities.invokeLater(new Runnable()
			{
				@Override
				public void run()
				{
					bt_download.setEnabled(false);
					menuBar.setEnabled(true);
					mainTab.setEnabled(true);
					
				}				
			});
		}
		else
		{
			SwingUtilities.invokeLater(new Runnable()
			{
				@Override
				public void run()
				{
					bt_download.setEnabled(true);
					menuBar.setEnabled(true);
					mainTab.setEnabled(true);
				}				
			});
		}
	}
	public synchronized void waitTime(int i)
	{
		try
		{
			wait(i);
		}
		catch(Exception e)
		{
			
		}		
	}
	private void loadAllImage()
	{
		ImageCache.prepareImage(sets);
		arena.loadAllImage();
	}
	public void update(long hp, long max_hp, BigInteger point, long l, long m)
	{		
		updateHp.setHp(hp);
		updateHp.setMax_hp(max_hp);
		SwingUtilities.invokeLater(updateHp);
		
		updatePoint.setPoint(point);
		SwingUtilities.invokeLater(updatePoint);
				
		
		//System.out.println(energy + "/" + max_energy);
		updateEnergy.setEnergy(l);
		updateEnergy.setMaxEnergy(m);
		SwingUtilities.invokeLater(updateEnergy);
	}
	
	@Override
	public void open()
	{
		window.setVisible(true);
		ImageCache.prepareImage(sets);
		SoundCache.prepareSound(sets);
		try
		{
			if((sets.getLang() instanceof Korean) && (sets.getOs().startsWith("Windows") || sets.getOs().startsWith("windows") || sets.getOs().startsWith("WINDOWS")))
				start_noticeArea.setPage(sets.getNotice_url() + "reflex_notice_kr.html");
			else
				start_noticeArea.setPage(sets.getNotice_url() + "reflex_notice.html");
		} 
		catch (Exception e)
		{
			try
			{
				if((sets.getLang() instanceof Korean) && (sets.getOs().startsWith("Windows") || sets.getOs().startsWith("windows") || sets.getOs().startsWith("WINDOWS")))
					start_noticeArea.setPage(sets.getNotice_url2() + "reflex_notice_kr.html");
				else
					start_noticeArea.setPage(sets.getNotice_url2() + "reflex_notice.html");
			}
			catch(Exception e1)
			{
				start_noticeArea.setText(sets.getLang().getText(Language.NOTICE_NULL));
			}
		}
		download_limit = grade_mode + 1;
		if(download_limit <= 0) bt_download.setEnabled(false);
		else bt_download.setEnabled(true);
		if(arena.ready()) arena.resume();
		else
		{
			arena.start();			
		}
		arena.pause();
		
		startDialog.setVisible(true);
		
		
		int areflexScen_index = -1;
		for(int i=0; i<scenarios.size(); i++)
		{
			if((scenarios.get(i) instanceof AReflexScenario))
			{
				if(((AReflexScenario) scenarios.get(i)).getRandomCode().longValue() == 82729313)
				{
					areflexScen_index = i;
					break;
				}				
			}
		}
		if(scenarioEditor != null && areflexScen_index >= 0) 
			scenarioEditor.setScenario(((AReflexScenario) scenarios.get(areflexScen_index)));
		
		loadAllImage();
		/*
		if(firstTime)
		{
			String[] options = new String[2];
			options[0] = sets.getLang().getText(Language.DOWNLOAD);
			options[1] = sets.getLang().getText(Language.NOT_NOW);
			int getSelection = JOptionPane.showOptionDialog(serialDialog, lang.getText(Language.FIRST_ADDITIONAL_DOWNLOAD), lang.getText(Language.FIRST_ADDITIONAL_DOWNLOAD), JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, null);
			if(getSelection == JOptionPane.YES_OPTION)
			{
				mainTab.setSelectedComponent(start_downloadPanel);
			}
			else
			{
				
			}
		}
		*/
	}
	@Override
	public void exit()
	{
		if(independence)
		{
			try
			{
				if(new File(RunManager.r65279("setting.xml")).exists())
					save_sets(sets, true);
				else
					save_sets(sets, false);
			} 
			catch (Exception e)
			{
				
			}
			arena.exit();					
			replayPlayer.exit();
			ThreadAccumulate.exitAll();
			if(replayPlayer != null)
			{
				try
				{
					replayPlayer.off_thread();
				}
				catch(Exception e)
				{
					
				}
			}
			System.exit(0);
		}
		else 
		{
			arena.pause();
			window.setVisible(false);
		}
	}
	@Override
	public void mouseDragged(MouseEvent e)
	{
		Object ob = e.getSource();
		if(ob == titlePanel)
		{
			window.setLocation(e.getXOnScreen() - mouse_x, e.getYOnScreen() - mouse_y);
		}
		else if(ob == start_titlePanel)
		{
			startDialog.setLocation(e.getXOnScreen() - mouse_start_x, e.getYOnScreen() - mouse_start_y);
		}
		else if(ob == finish_titlePanel)
		{
			finishDialog.setLocation(e.getXOnScreen() - mouse_finish_x, e.getYOnScreen() - mouse_finish_y);
		}
		else if(ob == autoUserDefined_titlePanel)
		{
			autoUserDefinedDialog.setLocation(e.getXOnScreen() - mouse_auto_x, e.getYOnScreen() - mouse_auto_y);
		}
		else if(ob == needfile_titlePanel)
		{
			needfileDialog.setLocation(e.getXOnScreen() - needfile_x, e.getYOnScreen() - needfile_y);
		}
		else if(ob == message_titlePanel)
		{
			messageDialog.setLocation(e.getXOnScreen() - message_x, e.getYOnScreen() - message_y);
		}
		else if(ob == about_titlePanel)
		{
			aboutDialog.setLocation(e.getXOnScreen() - about_x, e.getYOnScreen() - about_y);
		}
		else if(ob == arena)
		{
			if(touchMode)
			{
				if(! arena.player_area().contains((double) e.getX(), (double) e.getY()))
				{
					if(arena.player_x() > e.getX())
					{
						arena.keyPressed(new KeyEvent(arena, 0, 0, 0, KEY_LEFT, '←'));
					}
					else if(arena.player_x() < e.getX())
					{
						arena.keyPressed(new KeyEvent(arena, 0, 0, 0, KEY_RIGHT, '→'));
					}
					if(arena.player_y() > e.getY())
					{
						arena.keyPressed(new KeyEvent(arena, 0, 0, 0, KEY_UP, '↑'));
					}
					else if(arena.player_y() < e.getY())
					{
						arena.keyPressed(new KeyEvent(arena, 0, 0, 0, KEY_DOWN, '↓'));
					}
				}	
				else
				{
					arena.player_stop();
				}
				if(arena.player_fire_delay() <= 0)
				{
					arena.keyPressed(new KeyEvent(arena, 0, 0, 0, KEY_SPACE, ' '));				
				}
			}
		}
	}
	@Override
	public void mousePressed(MouseEvent e)
	{
		Object ob = e.getSource();
		if(ob == titlePanel)
		{
			mouse_x = e.getX();
			mouse_y = e.getY();
		}
		else if(ob == start_titlePanel)
		{
			mouse_start_x = e.getX();
			mouse_start_y = e.getY();
		}
		else if(ob == finish_titlePanel)
		{
			mouse_finish_x = e.getX();
			mouse_finish_y = e.getY();
		}
		else if(ob == autoUserDefined_titlePanel)
		{
			mouse_auto_x = e.getX();
			mouse_auto_y = e.getY();
		}
		else if(ob == needfile_titlePanel)
		{
			needfile_x = e.getX();
			needfile_y = e.getY();
		}
		else if(ob == message_titlePanel)
		{
			message_x = e.getX();
			message_y = e.getY();
		}
		else if(ob == about_titlePanel)
		{
			about_x = e.getX();
			about_y = e.getY();
		}
		else if(ob == start_verLabel)
		{
			aboutDialog.setVisible(true);
		}
	}
	@Override
	public void mouseEntered(MouseEvent e)
	{
		Object ob = e.getSource();
		
	}
	@Override
	public void mouseExited(MouseEvent e)
	{
		Object ob = e.getSource();
		
	}
	@Override
	public void windowActivated(WindowEvent arg0)
	{
		// TODO 자동 생성된 메소드 스텁
		
	}
	@Override
	public void windowClosed(WindowEvent arg0)
	{
		// TODO 자동 생성된 메소드 스텁
		
	}
	@Override
	public void windowClosing(WindowEvent e)
	{
		Object ob = e.getSource();
		if(ob == window || ob == startDialog)
			exit();
		else if(ob == finishDialog)
		{
			finishDialog.setVisible(false);
			startDialog.setVisible(true);
		}
	}
	@Override
	public void windowDeactivated(WindowEvent arg0)
	{
		// TODO 자동 생성된 메소드 스텁
		
	}
	@Override
	public void windowDeiconified(WindowEvent arg0)
	{
		// TODO 자동 생성된 메소드 스텁
		
	}
	@Override
	public void windowIconified(WindowEvent arg0)
	{
		// TODO 자동 생성된 메소드 스텁
		
	}
	@Override
	public void windowOpened(WindowEvent arg0)
	{
		// TODO 자동 생성된 메소드 스텁
		
	}
	
	private static boolean awtUtilAvail = true;
	public static void try_transparent(Window window, float opacity)
	{
		if(!awtUtilAvail) return;
		try
		{   
			Class<?> awtUtilClass = Class.forName("com.sun.awt.AWTUtilities");
			Method mthd = awtUtilClass.getMethod("setWindowOpacity", Window.class, Float.class);
			mthd.invoke(null, window, new Float(opacity));
			// com.sun.awt.AWTUtilities.setWindowOpacity(window, opacity);
		}
		catch(ClassNotFoundException e) 
		{
			awtUtilAvail = false;
		}
		catch(NoSuchMethodException e) 
		{
			awtUtilAvail = false;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}	
	}
	
	class UpdateHp implements Runnable
	{		
		private JProgressBar target;
		private long hp, max_hp;
		public void run()
		{
			if(hp >= Integer.MAX_VALUE)
			{
				target.setMaximum((int) (max_hp / 10000));
				target.setValue((int) (hp / 10000));
			}
			else
			{
				target.setMaximum((int) max_hp);
				target.setValue((int) hp);
			}
		}
		public JProgressBar getTarget()
		{
			return target;
		}
		public void setTarget(JProgressBar target)
		{
			this.target = target;
		}
		public long getHp()
		{
			return hp;
		}
		public void setHp(long hp)
		{
			this.hp = hp;
		}
		public long getMax_hp()
		{
			return max_hp;
		}
		public void setMax_hp(long max_hp)
		{
			this.max_hp = max_hp;
		}
	}
	class UpdatePoint implements Runnable
	{
		private JTextField target;
		private BigInteger point;
		public void run()
		{
			target.setText(String.valueOf(point));
		}
		public JTextField getTarget()
		{
			return target;
		}
		public void setTarget(JTextField target)
		{
			this.target = target;
		}
		public BigInteger getPoint()
		{
			return point;
		}
		public void setPoint(BigInteger point)
		{
			this.point = point;
		}		
	}
	class UpdateEnergy implements Runnable
	{
		private JProgressBar target;
		private long energy, maxEnergy;
		public void run()
		{
			if(maxEnergy >= Integer.MAX_VALUE)
			{
				target.setMaximum((int) (maxEnergy / 10000));
				target.setValue((int) (energy / 10000));
			}
			else
			{
				target.setMaximum((int) maxEnergy);
				target.setValue((int) energy);
			}
		}
		public JProgressBar getTarget()
		{
			return target;
		}
		public void setTarget(JProgressBar target)
		{
			this.target = target;
		}
		public long getEnergy()
		{
			return energy;
		}
		public void setEnergy(long energy)
		{
			this.energy = energy;
		}
		public long getMaxEnergy()
		{
			return maxEnergy;
		}
		public void setMaxEnergy(long maxEnergy)
		{
			this.maxEnergy = maxEnergy;
		}
	}
	@Override
	public void message()
	{
		message_textArea.append("\n");
		System.out.println();
		message_textArea.setCaretPosition(message_textArea.getDocument().getLength() - 1);
	}
	@Override
	public void message_bar()
	{
		message_textArea.append("------------------------------------------\n");
		System.out.println("------------------------------------------");
		message_textArea.setCaretPosition(message_textArea.getDocument().getLength() - 1);
	}
	@Override
	public void message(String str)
	{
		message_textArea.append(str + "\n");
		System.out.println(str);
		message_textArea.setCaretPosition(message_textArea.getDocument().getLength() - 1);
	}
	public void message(String kor, String eng)
	{
		String str = eng;
		if(sets.getLang() instanceof Korean) str = kor;
		
		message_textArea.append(str + "\n");
		System.out.println(str);
		message_textArea.setCaretPosition(message_textArea.getDocument().getLength() - 1);
	}
	@Override
	public void alert(String str)
	{
		message_textArea.append(str + "\n");
		JOptionPane.showMessageDialog(null, str);
		message_textArea.setCaretPosition(message_textArea.getDocument().getLength() - 1);
	}
	private void prepareFileChooser()
	{
		if(fileChooser == null)
		{
			fileChooser = new JFileChooser(new File(sets.getDefault_path()));
			if(usingFont != null)
			{
				setFontRecursively(fileChooser, usingFont);
			}
		}
		if(fileFilter == null)
		{
			fileFilter = new FileFilter()
			{				
				@Override
				public boolean accept(File file1)
				{
					if(file1 != null)
					{
						if(file1.isDirectory()) return true;
						if(file1.getAbsolutePath().endsWith(".rxsv")) return true;
					}
					return false;
				}
				@Override
				public String getDescription()
				{					
					return "Reflexioner - Save state (.rxsv)";
				}
			};
		}
		
		fileChooser.setFileFilter(fileFilter);
	}
	private void prepareReplayFileChooser()
	{
		if(fileChooser_rp == null)
		{
			fileChooser_rp = new JFileChooser(new File(sets.getDefault_path()));
			if(usingFont != null)
			{
                setFontRecursively(fileChooser_rp, usingFont);
			}
		}
		if(fileFilter_rp == null)
		{
			fileFilter_rp = new FileFilter()
			{				
				@Override
				public boolean accept(File file1)
				{
					if(file1 != null)
					{
						if(file1.isDirectory()) return true;
						if(file1.getAbsolutePath().endsWith(".rxrp")) return true;
					}
					return false;
				}
				@Override
				public String getDescription()
				{					
					return "Reflexioner - Save Replay (.rxrp)";
				}
			};
		}
		if(fileFilter_rp2 == null)
		{
			fileFilter_rp2 = new FileFilter()
			{				
				@Override
				public boolean accept(File file1)
				{
					if(file1 != null)
					{
						if(file1.isDirectory()) return true;
						if(file1.getAbsolutePath().endsWith(".rxrz")) return true;
					}
					return false;
				}
				@Override
				public String getDescription()
				{					
					return "Reflexioner - Compressed Save Replay (.rxrz)";
				}
			};
		}
		
		fileChooser_rp.setFileFilter(fileFilter_rp);
		fileChooser_rp.addChoosableFileFilter(fileFilter_rp2);
	}
	private void saveReplay()
	{
		if(fileChooser == null)
			prepareReplayFileChooser();
		
		File target = null;
		GZIPOutputStream zips = null;
		FileOutputStream stream = null;
		ObjectOutputStream obj = null;
		try
		{
			if(fileChooser_rp.showSaveDialog(finishDialog) == JFileChooser.APPROVE_OPTION)
			{
				target = fileChooser_rp.getSelectedFile();
				if(! (target.getAbsolutePath().endsWith(".rxrp") || target.getAbsolutePath().endsWith(".RXRP") || target.getAbsolutePath().endsWith(".Rxrp")
						|| target.getAbsolutePath().endsWith(".rxrz") || target.getAbsolutePath().endsWith(".RXRZ") || target.getAbsolutePath().endsWith(".Rxrz")))
				{
					target = new File(target.getAbsolutePath() + ".rxrz");
				}
				
				
				stream = new FileOutputStream(target);
				
				if(target.getAbsolutePath().endsWith(".rxrz") || target.getAbsolutePath().endsWith(".RXRZ") || target.getAbsolutePath().endsWith(".Rxrz"))
				{
					zips = new GZIPOutputStream(stream);
				}
				if(zips == null)
				{
					obj = new ObjectOutputStream(stream);
				}
				else
				{
					obj = new ObjectOutputStream(zips);
				}
				
				obj.writeObject(arena.savedReplay());
				message("리플레이가 저장되었습니다.", "Replay file is saved successfully.");
				message(target.getAbsolutePath());
			}
			else
			{
				
			}
		} 
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(finishDialog, sets.getLang().getText(Language.ERROR) + " : " + e.getMessage());
			if(sets.isError_printDetail()) e.printStackTrace();
		}
		finally
		{
			try
			{
				obj.close();
			} 
			catch (Exception e)
			{
				
			}
			try
			{
				zips.close();
			} 
			catch (Exception e)
			{
				
			}
			try
			{
				stream.close();
			} 
			catch (Exception e)
			{
				
			}
		}
	}
	private void saveState()
	{
		if(fileChooser == null)
			prepareFileChooser();
		
		File target = null;
		FileOutputStream stream = null;
		ObjectOutputStream obj = null;
		try
		{
			if(fileChooser.showSaveDialog(finishDialog) == JFileChooser.APPROVE_OPTION)
			{
				target = fileChooser.getSelectedFile();
				if(! (target.getAbsolutePath().endsWith(".rxsv") || target.getAbsolutePath().endsWith(".RXSV") || target.getAbsolutePath().endsWith(".Rxsv")))
				{
					target = new File(target.getAbsolutePath() + ".rxsv");
				}
				stream = new FileOutputStream(target);
				obj = new ObjectOutputStream(stream);
				obj.writeObject(arena.nowState());
				message("상태가 저장되었습니다.", "State is saved successfully.");
				message(target.getAbsolutePath());
			}
			else
			{
				
			}
		} 
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(finishDialog, sets.getLang().getText(Language.ERROR) + " : " + e.getMessage());
			if(sets.isError_printDetail()) e.printStackTrace();
		}
		finally
		{
			try
			{
				obj.close();
			} 
			catch (Exception e)
			{
				
			}
			try
			{
				stream.close();
			} 
			catch (Exception e)
			{
				
			}
		}
	}
	
	private void loadState()
	{
		if(fileChooser == null)
			prepareFileChooser();
		File target = null;
		FileInputStream stream = null;
		ObjectInputStream obj = null;
		ReflexSave load = null;
		try
		{
			if(fileChooser.showOpenDialog(finishDialog) == JFileChooser.APPROVE_OPTION)
			{
				target = fileChooser.getSelectedFile();
				stream = new FileInputStream(target);
				obj = new ObjectInputStream(stream);
				load = (ReflexSave) obj.readObject();
				if(load.accept())
				{
					arena.applyState(load);
					startDialog.setVisible(false);
				}
				else
				{
					JOptionPane.showMessageDialog(startDialog, sets.getLang().getText(Language.ERROR) + " : " + sets.getLang().getText(Language.FAIL) + " " + sets.getLang().getText(Language.AUTHORITY));
				}
			}
			else
			{
				
			}
		} 
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(startDialog, sets.getLang().getText(Language.ERROR) + " : " + e.getMessage());
			if(sets.isError_printDetail()) e.printStackTrace();
		}
		finally
		{
			try
			{
				obj.close();
			} 
			catch (Exception e)
			{
				
			}
			try
			{
				stream.close();
			} 
			catch (Exception e)
			{
				
			}
		}
	}
	@Override
	public void valueChanged(ListSelectionEvent e)
	{
		Object ob = e.getSource();
		if(ob == start_scenarioList)
		{
			int sels = start_scenarioList.getSelectedIndex();
			if(sels >= 0 && sels < scenarios.size())
			{
				if(sets.getLang() instanceof English)
					start_scenario_description.setText(scenarios.get(sels).getDescription());
				else if(sets.getLang() instanceof Korean)
					start_scenario_description.setText(scenarios.get(sels).getKoreanDescription());
				else
					start_scenario_description.setText(scenarios.get(sels).getDescription());
				
				start_scenario_description.setText(start_scenario_description.getText() + "\n\n"
						+ sets.getLang().getText(Language.DIFFICULTY) + " : "
						+ Difficulty.intToString(scenarios.get(sels).getDifficulty().intValue(), 3.3));
				
				if(scenarios.get(sels).isAuthorized())
				{
					start_scenario_description.setText(start_scenario_description.getText() + "\n\n" + sets.getLang().getText(Language.AUTHORITY)
							+ " " + sets.getLang().getText(Language.ACCEPT));
				}
				if(start_scenario_selectShipCombo != null)
				{
					if(scenarios.get(sels) instanceof CReflexScenario)
					{
						if(((CReflexScenario) scenarios.get(sels)).getSpaceShip_selectable() != null)
						{
							if(((CReflexScenario) scenarios.get(sels)).getSpaceShip_selectable().booleanValue())
							{
								start_scenario_selectShipCombo.setEnabled(true);
							}
							else
							{
								start_scenario_selectShipCombo.setEnabled(false);
							}
						}
						else
						{
							start_scenario_selectShipCombo.setEnabled(false);
						}
					}
					else
					{
						start_scenario_selectShipCombo.setEnabled(false);
					}
				}
			}
		}		
	}
	public Component getStartWindow()
	{
		return startDialog;
	}
	public Component getMainWindow()
	{
		return window;
	}
	public Component getFinishWindow()
	{
		return finishDialog;
	}
	@Override
	public void stateChanged(ChangeEvent e)
	{
		Object ob = e.getSource();
		if(ob == mainTab)
		{
			SoundCache.play("click");
			SwingUtilities.invokeLater(new Runnable()
			{

				@Override
				public void run()
				{
					if(mainTab.getSelectedComponent() == start_noticePanel)
					{
						if(menu_file_start != null)
							menu_file_start.setEnabled(false);
						if(menu_file_start_userDefined != null)
							menu_file_start_userDefined.setEnabled(false);
					}
					else if(mainTab.getSelectedComponent() == start_downloadPanel)
					{
						if(menu_file_start != null)
							menu_file_start.setEnabled(false);
						if(menu_file_start_userDefined != null)
							menu_file_start_userDefined.setEnabled(false);
					}
					else if(mainTab.getSelectedComponent() == start_todayPanel)
					{
						if(menu_file_start != null)
						{
							if(bt_start_today != null)
								menu_file_start.setEnabled(bt_start_today.isEnabled());
							else
								menu_file_start.setEnabled(false);
						}
						if(menu_file_start_userDefined != null)
							menu_file_start_userDefined.setEnabled(false);
					}
					else
					{
						if(menu_file_start != null)
							menu_file_start.setEnabled(true);
						if(menu_file_start_userDefined != null)
							menu_file_start_userDefined.setEnabled(false);
					}
				}
			});			
		}		
	}
	@Override
	public void itemStateChanged(ItemEvent e)
	{
		Object ob = e.getSource();
		if(ob == menu_manage_enableImage)
		{
			if(menu_manage_enableImage.isSelected())
			{
				image_allow = true;
			}
			else
			{
				image_allow = false;
			}
		}
		else if(ob == menu_manage_enableSound)
		{
			if(menu_manage_enableSound.isSelected())
			{
				sound_allow = true;
				if(frame_loaded)
				{
					// TODO Custom sound channel feature
					SoundCache.prepareSound(sets, 32);
					sound_allow = true;
				}
				else
				{
					SoundCache.prepareSound(sets, 32);
					sound_allow = true;
				}
			}
			else
			{
				sound_allow = false;
				SoundCache.clear();
			}
		}
	}
	public static int getSpaceShip_r()
	{
		return spaceShip_r;
	}
	public static int getFire_delay()
	{
		return fire_delay;
	}
	public static int getMax_enemies()
	{
		return max_enemies;
	}
	public static int getEnemy_r()
	{
		return enemy_r;
	}
	public static int getBoss_beam_delay()
	{
		return boss_beam_delay;
	}
	public static int getDifficulty_delay()
	{
		return difficulty_delay;
	}
	public static int getBoss_delay()
	{
		return boss_delay;
	}
	public static int getReact_delay()
	{
		return react_delay;
	}
	static void setReact_delay(int v)
	{
		react_delay = v;
	}
	public static int getSpeed()
	{
		return speed;
	}
	public static Language getLang()
	{
		return lang;
	}
	public static String getFile_path()
	{
		return new String(file_path);
	}
	@Override
	public void openConsole()
	{
		messageDialog.setVisible(true);
		
	}
	public static void setTransparent_opacity(float t)
	{
		transparent_opacity = t;
	}
	public static float getTransparent_opacity()
	{
		return transparent_opacity;
	}
	public static void prepareFont()
	{
		boolean font_loaded = false;
		String osName = System.getProperty("os.name");
		String locale = System.getProperty("user.language");
		
		Setting sets = Setting.getNewInstance();
		InputStream infs = null;
		FileInputStream finfs = null;
		ObjectInputStream objs = null;
		if(osName.startsWith("Windows") || osName.startsWith("windows") || osName.startsWith("WINDOWS"))
		{
			try
			{
				File fontFile = new File(sets.getDefault_path() + "basic_font.ttf");
				if(fontFile.exists())
				{
					finfs = new FileInputStream(fontFile);
					infs = new BufferedInputStream(finfs);
					usingFont = Font.createFont(Font.TRUETYPE_FONT, infs);
					usingFont = usingFont.deriveFont(Font.PLAIN, default_fontSize);
					usingFont2 = usingFont.deriveFont(Font.PLAIN, default_fontSize * 2);
					usingFontB = usingFont.deriveFont(Font.BOLD, default_fontSize);
					usingFont2B = usingFont.deriveFont(Font.BOLD, default_fontSize * 2);
					font_loaded = true;
				}
				else font_loaded = false;
			}
			catch(Exception e)
			{
				if(sets != null && sets.isError_printDetail())
					e.printStackTrace();
				font_loaded = false;
			}
			finally
			{
				try
				{
					infs.close();
				}
				catch(Exception e)
				{
					
				}
				try
				{
					finfs.close();
				}
				catch(Exception e)
				{
					
				}
			}
		}
		String truetype = "돋움";
		try
		{
			GraphicsEnvironment gr = GraphicsEnvironment.getLocalGraphicsEnvironment();
			String[] fontList = gr.getAvailableFontFamilyNames();
			for(int i=0; i<fontList.length; i++)
			{
				if(fontList[i].equals("나눔고딕") || fontList[i].equals("나눔고딕코딩") || fontList[i].equalsIgnoreCase("NanumGothic") || fontList[i].equalsIgnoreCase("NanumGothicCoding"))
				{
					truetype = fontList[i];
					break;
				}
			}
		} 
		catch (Exception e1)
		{
			truetype = "돋움";
		}
		
		if(! font_loaded)
		{
			try
			{
				finfs = new FileInputStream(sets.getDefault_path() + "setting.clst");
				objs = new ObjectInputStream(finfs);
				usingFont = (Font) objs.readObject();
				usingFont2 = usingFont.deriveFont(Font.PLAIN, usingFont.getSize() * 2);
				usingFontB = usingFont.deriveFont(Font.BOLD, usingFont.getSize());
				usingFont2B = usingFont.deriveFont(Font.BOLD, usingFont.getSize() * 2);
			} 
			catch (Exception e)
			{
				if(sets != null && sets.isError_printDetail())
					e.printStackTrace();
				font_loaded = false;
			}
			finally
			{
				try
				{
					objs.close();
				}
				catch(Exception e)
				{
					
				}
				try
				{
					finfs.close();
				}
				catch(Exception e)
				{
					
				}
			}
		}
		
		if(! font_loaded)
		{
			if(osName.equalsIgnoreCase("Windows Vista") || osName.equalsIgnoreCase("Windows 7")
					|| osName.equalsIgnoreCase("Windows 8")|| osName.equalsIgnoreCase("Windows 8.1"))
			{
				if(locale.startsWith("ko") || locale.startsWith("KO") || locale.startsWith("kr") || locale.startsWith("KR") || locale.startsWith("kor") || locale.startsWith("KOR"))
					usingFontName = truetype;
				else
					usingFontName = "Arial";
			}
			else if(osName.startsWith("Windows") || osName.startsWith("windows") || osName.startsWith("WINDOWS"))
			{
				if(osName.endsWith("95") || osName.endsWith("98") || osName.endsWith("me") || osName.endsWith("ME") || osName.endsWith("Me") || osName.endsWith("2000"))
				{
					if(locale.startsWith("ko") || locale.startsWith("KO") || locale.startsWith("kr") || locale.startsWith("KR") || locale.startsWith("kor") || locale.startsWith("KOR"))
						usingFontName = "돋움";
					else
						usingFontName = "Dialog";
				}
				else
				{
					if(locale.startsWith("ko") || locale.startsWith("KO") || locale.startsWith("kr") || locale.startsWith("KR") || locale.startsWith("kor") || locale.startsWith("KOR"))
						usingFontName = truetype;
					else
						usingFontName = "Arial";
				}
				
			}
			try
			{
				usingFont = new Font(usingFontName, Font.PLAIN, default_fontSize);
				usingFontB = new Font(usingFontName, Font.BOLD, default_fontSize);
				usingFont2 = new Font(usingFontName, Font.PLAIN, default_fontSize * 2);
				usingFont2B = new Font(usingFontName, Font.BOLD, default_fontSize * 2);
				font_loaded = true;
			} 
			catch (Exception e)
			{
				e.printStackTrace();
				font_loaded = false;
				usingFont = null;
			}
		}
		if(font_loaded)
		{
			try
			{
				UIManager.put("OptionPane.messageFont", new FontUIResource(usingFont));
				UIManager.put("OptionPane.font", new FontUIResource(usingFont));
				UIManager.put("OptionPane.buttonFont", new FontUIResource(usingFont));
				UIManager.put("JOptionPane.font", new FontUIResource(usingFont));
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	public static void setFontRecursively(Component comp, Font font)
	{
		try
		{
			if(font == null) return;
			comp.setFont(font);
			int max_limit = 100;
			if(comp instanceof Container)
			{
				Container cont = (Container) comp;
				int ub = cont.getComponentCount();
				for(int  j=0; j<ub; j++)
				{
					ub = cont.getComponentCount();
					if(ub > max_limit) ub = max_limit;
					setFontRecursively(cont.getComponent(j), font, max_limit);
				}
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void setFontRecursively(Component comp, Font font, int prevent_infiniteLoop)
	{
		try
		{
			if(font == null) return;
			comp.setFont(font);
			int max_limits = prevent_infiniteLoop;
			if(comp instanceof Container)
			{
				Container cont = (Container) comp;
				int ub = cont.getComponentCount();
				for(int  j=0; j<ub; j++)
				{
					ub = cont.getComponentCount();
					if(ub > max_limits) ub = max_limits;
					max_limits--;
					if(max_limits <= 0) break;
					setFontRecursively(cont.getComponent(j), font, max_limits);					
				}
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public static String getVersionString()
	{		
		return getVersionString(false);
	}
	public static String getVersionString(boolean detailed)
	{
		if(detailed)
		{
			String longValue = "";
			int howMany0 = 0;
			long nightly = version_nightly;
			while(nightly >= 10)
			{				
				howMany0++;
				nightly = nightly / 10;
			}
			howMany0 = 4 - howMany0;
			if(howMany0 < 0) howMany0 = 0;
			
			for(int i=0; i<howMany0; i++)
			{
				longValue = longValue + "0";
			}			
			longValue = longValue + String.valueOf(version_nightly);
			
			
			String results = String.format("v%d.%d%d  n", version_main, version_sub_1, version_sub_2) 
					+ longValue;		
			results = results + String.valueOf(version_test);
			return results;
		}
		else
		{
			String results = "v" + String.valueOf(version_main) + "." + String.valueOf(version_sub_1)
					+ "" + String.valueOf(version_sub_2);		
			results = results + String.valueOf(version_test);
			return results;
		}
	}
	
	public static void save_sets(Setting sets, boolean xml) throws Exception
	{		
		FileOutputStream fout = null;
		try
		{
			File file = new File(sets.getDefault_path());
			sets.setVer_main(version_main);
			sets.setVer_sub1(version_sub_1);
			sets.setVer_sub2(version_sub_2);
			if(! file.exists())
			{
				file.mkdir();
			}
			
			if(xml)
			{
				try
				{
					File xmlFile = new File(sets.getDefault_path() + "setting.xml");
					if(xmlFile.exists()) xmlFile.delete();
				} 
				catch (Exception e)
				{
					
				}
				System.gc();
				fout = new FileOutputStream(sets.getDefault_path() + "setting.xml");
				XMLEncoder encoder = new XMLEncoder(fout);
				sets.wrapperToObjects();
				encoder.writeObject(sets);
				encoder.close();
			}		
			else
			{
				try
				{
					File objectFile = new File(sets.getDefault_path() + "setting.clst");
					if(objectFile.exists()) objectFile.delete();
				} 
				catch (Exception e)
				{
					
				}
				System.gc();
				fout = new FileOutputStream(sets.getDefault_path() + "setting.clst");
				
				ObjectOutputStream obout = new ObjectOutputStream(fout);
				obout.writeObject(sets);
				obout.close();
				
			}
			
			try
			{
				fout.close();
			} 
			catch (Exception e)
			{
				
			}
		} 
		catch(Exception e)
		{
			throw e;
		}
	}
	public static long getAuthorizedCode(long password)
	{
		if(password == 2938291)
			return 4928539;
		else 
			return -1;
	}
	public static String version_auts()
	{
		return "sgagiowopm1634onoiandwsdx";
	}
	public static boolean isBeta()
	{
		char[] lists = {'a', 'b', 'c'};
		for(int i=0; i<lists.length; i++)
		{
			if(lists[i] == version_test)
			{
				return true;
			}
		}
		return false;
			
		
	}
	public Setting getSetting()
	{
		return setting;
	}
	public void setSetting(Setting s) {
		setting = s;
	}
	public Window getWindow() {
		return window;
	}
}
