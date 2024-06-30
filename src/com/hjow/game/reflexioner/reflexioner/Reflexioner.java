/*
Copyright 2024 HJOW (hujinone22@naver.com)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */
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
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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
import java.util.List;
import java.util.SortedSet;
import java.util.StringTokenizer;
import java.util.TreeSet;
import java.util.Vector;
import java.util.zip.GZIPOutputStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileFilter;

import com.hjow.game.reflexioner.browser.OldBrowser;
import com.hjow.game.reflexioner.mainClasses.CodeChecker;
import com.hjow.game.reflexioner.mainClasses.MessageShowable;
import com.hjow.game.reflexioner.mainClasses.MouseDragCatcher;
import com.hjow.game.reflexioner.mainClasses.Openable;
import com.hjow.game.reflexioner.mainClasses.RunManager;
import com.hjow.game.reflexioner.mainClasses.ThreadAccumulate;
import com.hjow.game.reflexioner.mainClasses.Uninstaller;
import com.hjow.game.reflexioner.pack.SecuredDist;
import com.hjow.game.reflexioner.setting.Difficulty;
import com.hjow.game.reflexioner.setting.Setting;
import com.hjow.game.reflexioner.ui.RComboBox;
import com.hjow.game.reflexioner.ui.RList;

public class Reflexioner extends MouseDragCatcher implements Openable, WindowListener, ActionListener, MessageShowable, ListSelectionListener, ChangeListener, ItemListener
{
    private boolean independence = true;
    private Setting sets = null;
    
    private static boolean frame_loaded = false;
    
    public static final long version_nightly = 34;
    public static final int  version_main = 0;
    public static final int  version_sub_1 = 9;
    public static final int  version_sub_2 = 6;
    public static final char version_test = ' '; // Complete : ' ', Test : 'a' 'b' 'c'
    
    private static String file_path = null;
    public static Color color_spaceShip, color_spaceShip_missile, color_enemy_missile, color_enemy, color_bigenemy, color_item, color_item_text, color_useItem;
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
    
    private static boolean useTransparent = true;
    private static float transparent_opacity = 0.9f;
    
    public static boolean replay_save = false;
    public static int replayInterval = 1;
    public static int replay_now_delay = 0;
    
    public static int AUTO_LAST = -1;
    public static boolean image_allow = true, sound_allow = true;
    
    public static Font usingFont = null;
    public static Font usingFontB = null;
    public static Font usingFont2 = null;
    public static Font usingFont2B = null;
    public static int default_fontSize = 12;
    
    public static transient String calc_grade_str = "Basic";
    
    private Setting setting;
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
    private RComboBox combo_ship;
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
    private CodeChecker checker;
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
    private RComboBox combo_dif;
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
    private RList start_scenarioList;
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
    private RComboBox start_scenario_selectShipCombo;
    private String selected_scenario_ship;
    private JPanel start_notice_descPanel;
    private JPanel start_notice_titlePanel;
    private JLabel start_notice_titleLabel;
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
        this(new Setting(), true);
    }
    public Reflexioner(boolean firstTime)
    {
        this(new Setting(), true, firstTime);
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
        // this.manager = manager;
        // this.arguments = args;
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
        // this.manager = manager;
        // this.arguments = args;
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
        SecuredDist dist = new SecuredDist();
        
        usingFont   = sets.getUsingFont();
        usingFont2  = sets.getUsingFont2();
        usingFontB  = sets.getUsingFontB();
        usingFont2B = sets.getUsingFont2B();
        default_fontSize = sets.getInt("FontSize");
        
        window.setSize(sets.getInt("Width"), sets.getInt("Height"));
        window.setMaximumSize(new Dimension((int) (scrSize.getWidth() - 50), (int) (scrSize.getHeight() - 50)));
        window.setMinimumSize(new Dimension(550, 450));
        window.setLocation((int)(sets.getScreenSize().getWidth()/2 - window.getWidth()/2), (int)(sets.getScreenSize().getHeight()/2 - window.getHeight()/2));
        window.setLayout(new BorderLayout());
        window.addWindowListener(this);
        menuBar = new JMenuBar();
        file_path = sets.getDefaultPath();
        
        if(window instanceof JFrame)
        {
            ((JFrame) window).setTitle(sets.trans("Reflexioner"));
            if(sets.getBool("Undecorated"))
            {
                ((JFrame) window).setUndecorated(true);
            }
        }
        else if(window instanceof JDialog)
        {
            ((JDialog) window).setTitle(sets.trans("Reflexioner"));
            if(sets.getBool("Undecorated"))
            {
                ((JDialog) window).setUndecorated(true);
            }
        }        
        
        window.setBackground(sets.getColor("ColorSelectedBack"));
        
        download_url1 = sets.get("ServerURL1");
        download_url2 = sets.get("ServerURL2");
        if(download_url2 == null) download_url2 = download_url1;
        
        try
        {
            window.setIconImage(new ImageIcon(getClass().getClassLoader().getResource("reflex_ico.png")).getImage());
        }
        catch(Exception e)
        {
            if(sets.getBool("ErrorStackTraceConsole")) e.printStackTrace();
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
        message_mainPanel.setBackground(sets.getColor("ColorSelectedBack"));
        message_mainPanel.setLayout(new BorderLayout());
        message_mainPanel.setBorder(new EtchedBorder());
        message_textArea = new JTextArea();
        message_textArea.setEditable(false);
        message_textArea.setLineWrap(true);
        message_textArea.setBackground(sets.getColor("ColorSelectedInsideBack"));
        message_textArea.setForeground(sets.getColor("ColorSelectedFore"));
        message_textScroll = new JScrollPane(message_textArea);
        message_mainPanel.add(message_textScroll, BorderLayout.CENTER);
        message_upPanel = new JPanel();
        message_upPanel.setLayout(new BorderLayout());
        message_upPanel.setBackground(sets.getColor("ColorSelectedInsideBack"));
        message_titlePanel = new JPanel();
        message_titlePanel.setBackground(sets.getColor("ColorSelectedInsideBack"));
        message_titlePanel.setBorder(new EtchedBorder());
        message_titlePanel.addMouseListener(this);
        message_titlePanel.addMouseMotionListener(this);
        message_titlePanel.setLayout(new FlowLayout());
        message_titleLabel = new JLabel(sets.trans("Log"));
        message_titleLabel.setForeground(sets.getColor("ColorSelectedFore"));
        message_titlePanel.add(message_titleLabel);
        message_upPanel.add(message_titlePanel, BorderLayout.CENTER);
        message_mainPanel.add(message_upPanel, BorderLayout.NORTH);
        bt_close_message = new JButton("X");
        bt_close_message.addActionListener(this);
        bt_close_message.setForeground(sets.getColor("ColorSelectedFore"));        
        message_upPanel.add(bt_close_message, BorderLayout.EAST);
        
        if(sets.getColor("ColorSelectedButton") != null)
        {
            bt_close_message.setBackground(sets.getColor("ColorSelectedButton"));
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
        mainPanel.setBackground(sets.getColor("ColorSelectedBack"));
        
        color_enemy = Color.DARK_GRAY;
        color_bigenemy = Color.BLACK;
        color_spaceShip = Color.GREEN;
        color_enemy_missile = Color.RED;
        color_spaceShip_missile = Color.BLUE;    
        color_item = Color.PINK;
        color_item_text = Color.RED;
        color_useItem = Color.CYAN;
        
        KEY_1     = sets.getInt("KEY_1");
        KEY_2     = sets.getInt("KEY_2");
        KEY_3     = sets.getInt("KEY_3");
        KEY_K     = sets.getInt("KEY_K");
        KEY_L     = sets.getInt("KEY_L");
        KEY_SHIFT = sets.getInt("KEY_SHIFT");
        KEY_SPACE = sets.getInt("KEY_SPACE");
        KEY_UP    = sets.getInt("KEY_UP");
        KEY_DOWN  = sets.getInt("KEY_DOWN");
        KEY_LEFT  = sets.getInt("KEY_LEFT");
        KEY_RIGHT = sets.getInt("KEY_RIGHT");        
        KEY_W     = sets.getInt("KEY_W");
        KEY_A     = sets.getInt("KEY_A");
        KEY_S     = sets.getInt("KEY_S");
        KEY_D     = sets.getInt("KEY_D");
        KEY_4     = sets.getInt("KEY_4");
        KEY_5     = sets.getInt("KEY_5");
        KEY_6     = sets.getInt("KEY_6");
        KEY_7     = sets.getInt("KEY_7");
        KEY_8     = sets.getInt("KEY_8");
        KEY_9     = sets.getInt("KEY_9");
        KEY_0     = sets.getInt("KEY_0");
        
        updateHp     = new UpdateHp();
        updatePoint  = new UpdatePoint();
        updateEnergy = new UpdateEnergy();
        
        upPanel = new JPanel();
        centerPanel = new JPanel();
        downPanel = new JPanel();
        upPanel.setBackground(sets.getColor("ColorSelectedBack"));
        downPanel.setBackground(sets.getColor("ColorSelectedBack"));
        centerPanel.setBackground(sets.getColor("ColorSelectedBack"));
        mainPanel.add(upPanel, BorderLayout.NORTH);        
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(downPanel, BorderLayout.SOUTH);
        
        upPanel.setLayout(new BorderLayout());
        if(sets.getBool("Undecorated"))
        {
            titlePanel = new JPanel();
            titlePanel.setBackground(sets.getColor("ColorSelectedInsideBack"));
            titlePanel.setBorder(new EtchedBorder());
            titlePanel.setLayout(new FlowLayout());
            titleLabel = new JLabel(sets.trans("Reflexioner"));
            titleLabel.setForeground(sets.getColor("ColorSelectedFore"));
            if(usingFont != null)
                titleLabel.setFont(usingFont);
            titlePanel.add(titleLabel);
            titlePanel.addMouseListener(this);
            titlePanel.addMouseMotionListener(this);
            upPanel.add(titlePanel, BorderLayout.NORTH);
        }
        
        secondBtPanel = new JPanel();
        secondBtPanel.setBackground(sets.getColor("ColorSelectedBack"));
        upPanel.add(secondBtPanel, BorderLayout.CENTER);
        secondBtPanel.setLayout(new GridLayout(1, 4));
        bt_w1 = new JButton("1");
        bt_w2 = new JButton("2");
        bt_w3 = new JButton("3");
        bt_stop = new JButton(sets.trans("Stop this game"));
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
        bt_w1.setForeground(sets.getColor("ColorSelectedFore"));
        bt_w2.setForeground(sets.getColor("ColorSelectedFore"));
        bt_w3.setForeground(sets.getColor("ColorSelectedFore"));
        bt_stop.setForeground(sets.getColor("ColorSelectedFore"));
        if(sets.getColor("ColorSelectedButton") != null)
        {
            bt_w1.setBackground(sets.getColor("ColorSelectedBack"));
            bt_w2.setBackground(sets.getColor("ColorSelectedBack"));
            bt_w3.setBackground(sets.getColor("ColorSelectedBack"));
            bt_stop.setBackground(sets.getColor("ColorSelectedBack"));
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
        infoPanel.setBackground(sets.getColor("ColorSelectedBack"));
        downPanel.add(infoPanel, BorderLayout.NORTH);
        controlBtPanel = new JPanel();
        downPanel.add(controlBtPanel, BorderLayout.SOUTH);
        controlBtPanel.setLayout(new BorderLayout());
        controlBtPanel.setBackground(sets.getColor("ColorSelectedBack"));
        bt_left = new JButton("←");
        bt_right = new JButton("→");
        bt_fire = new JButton(sets.trans("Shoot"));
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
        bt_left.setForeground(sets.getColor("ColorSelectedFore"));
        bt_right.setForeground(sets.getColor("ColorSelectedFore"));
        bt_fire.setForeground(sets.getColor("ColorSelectedFore"));
        if(sets.getColor("ColorSelectedButton") != null)
        {
            bt_left.setBackground(sets.getColor("ColorSelectedBack"));
            bt_right.setBackground(sets.getColor("ColorSelectedBack"));
            bt_fire.setBackground(sets.getColor("ColorSelectedBack"));
        }
        
        infoPanel.setLayout(new BorderLayout());
        
        infoPnLeft  = new JPanel();
        infoPnRight = new JPanel();
        
        infoPnLeft.setBackground(sets.getColor("ColorSelectedBack"));
        infoPnRight.setBackground(sets.getColor("ColorSelectedBack"));
        
        infoPnLeft.setLayout(new FlowLayout(FlowLayout.LEFT));
        infoPnRight.setLayout(new FlowLayout(FlowLayout.RIGHT));
        
        infoPanel.add(infoPnLeft, BorderLayout.CENTER);
        infoPanel.add(infoPnRight, BorderLayout.EAST);
        
        hpLabel = new JLabel(sets.trans("HP"));
        hpLabel.setForeground(sets.getColor("ColorSelectedFore"));
        hpBar = new JProgressBar(JProgressBar.HORIZONTAL, 0, 100);
        if(usingFont != null)
            hpLabel.setFont(usingFont);
        
        int barWidth = (int) (sets.getInt("Width") / 3.0 - 50);
        if(barWidth < 250) barWidth = 250;
        
        Dimension barSize = new Dimension(barWidth, (int) hpBar.getMaximumSize().getHeight());
        hpBar.setSize(barSize);
        hpBar.setPreferredSize(barSize);
        
        infoPnLeft.add(hpLabel);
        infoPnLeft.add(hpBar);
        updateHp.setTarget(hpBar);
        
        energyLabel = new JLabel("Energe");
        energyLabel.setForeground(sets.getColor("ColorSelectedFore"));
        if(usingFont != null)
            energyLabel.setFont(usingFont);
        energyBar = new JProgressBar(JProgressBar.HORIZONTAL, 0, 100);
        barSize = new Dimension(barWidth, (int) energyBar.getMaximumSize().getHeight());
        energyBar.setSize(barSize);
        energyBar.setPreferredSize(barSize);
        
        infoPnLeft.add(energyLabel);
        infoPnLeft.add(energyBar);
                
        updateEnergy.setTarget(energyBar);
        
        pointLabel = new JLabel(sets.trans("Point"));
        pointLabel.setForeground(sets.getColor("ColorSelectedFore"));
        if(usingFont != null)
            pointLabel.setFont(usingFont);
        pointField = new JTextField(10);
        pointField.setBackground(sets.getColor("ColorSelectedInsideBack"));
        pointField.setForeground(sets.getColor("ColorSelectedFore"));
        pointField.setEditable(false);
        pointField.setBorder(new EtchedBorder());
        pointField.setText(String.valueOf(0));
        
        updatePoint.setTarget(pointField);
        
        infoPnLeft.add(pointLabel);
        infoPnLeft.add(pointField);
        
        bt_touch = new JButton("▼");
        bt_touch.addActionListener(this);
        bt_touch.setForeground(sets.getColor("ColorSelectedFore"));
        if(sets.getColor("ColorSelectedButton") != null)
        {
            bt_touch.setBackground(sets.getColor("ColorSelectedButton"));
        }
        if(usingFont != null)
            bt_touch.setFont(usingFont);
        infoPnRight.add(bt_touch);
        
        helpPanel = new JPanel();
        helpPanel.setBackground(sets.getColor("ColorSelectedBack"));
        upPanel.add(helpPanel, BorderLayout.SOUTH);
        helpPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        helpLabel = new JLabel(sets.trans("Move →←↑↓, Break SHIFT, Fire SPACE, Change weapon 123, Toggle auto Fire 4, Pause L, Exit K"));
        helpLabel.setForeground(sets.getColor("ColorSelectedFore"));
        if(usingFont != null)
            helpLabel.setFont(usingFont);
        helpPanel.add(helpLabel);
        
        
        
        startDialog = new JDialog(window);
        startDialog.addWindowListener(this);
        startDialog.setSize(450, 280);
        startDialog.setLocation((int)(sets.getScreenSize().getWidth()/2 - startDialog.getWidth()/2), (int)(sets.getScreenSize().getHeight()/2 - startDialog.getHeight()/2));
        startDialog.setLayout(new BorderLayout());
        
        
        start_mainPanel = new JPanel();
        start_mainPanel.setBackground(sets.getColor("ColorSelectedBack"));
        start_mainPanel.setBorder(new EtchedBorder());
        startDialog.add(start_mainPanel);
        start_mainPanel.setLayout(new BorderLayout());
        
        start_upPanel = new JPanel();
        start_downPanel = new JPanel();
        start_centerPanel = new JPanel();
        start_upPanel.setBackground(sets.getColor("ColorSelectedBack"));
        start_downPanel.setBackground(sets.getColor("ColorSelectedBack"));
        start_centerPanel.setBackground(sets.getColor("ColorSelectedBack"));
        
        start_mainPanel.add(start_upPanel, BorderLayout.NORTH);
        
        mainTab = new JTabbedPane();
        mainTab.addChangeListener(this);
        start_mainPanel.add(mainTab, BorderLayout.CENTER);
        mainTab.setBackground(sets.getColor("ColorSelectedBack"));
        mainTab.setForeground(sets.getColor("ColorSelectedFore"));
        if(usingFont != null)
            mainTab.setFont(usingFont);
        
        start_noticePanel = new JPanel();
        start_noticePanel.setBackground(sets.getColor("ColorSelectedBack"));
        start_noticePanel.setForeground(sets.getColor("ColorSelectedFore"));
        start_noticePanel.setLayout(new BorderLayout());
        mainTab.add(sets.trans("Notice"), start_noticePanel);
        start_notice_upPanel = new JPanel();
        start_notice_downPanel = new JPanel();
        start_notice_centerPanel = new JPanel();
        start_noticePanel.add(start_notice_upPanel, BorderLayout.NORTH);
        start_noticePanel.add(start_notice_centerPanel, BorderLayout.CENTER);
        start_noticePanel.add(start_notice_downPanel, BorderLayout.SOUTH);
        
        start_notice_downPanel.setBackground(sets.getColor("ColorSelectedBack"));
        start_notice_centerPanel.setBackground(sets.getColor("ColorSelectedBack"));
        start_notice_upPanel.setForeground(sets.getColor("ColorSelectedFore"));
        start_notice_downPanel.setForeground(sets.getColor("ColorSelectedFore"));
        start_notice_centerPanel.setForeground(sets.getColor("ColorSelectedFore"));
        start_notice_centerPanel.setLayout(new BorderLayout());
        start_noticeArea = new OldBrowser(sets);
        start_noticeArea.setBackground(sets.getColor("ColorSelectedBack"));
        start_noticeArea.setForeground(sets.getColor("ColorSelectedFore"));
        if(usingFont != null)
            start_noticeArea.setFont(usingFont);
        start_noticeArea.setEditable(false);
        start_noticeScroll = new JScrollPane(start_noticeArea);
        start_notice_centerPanel.add(start_noticeScroll);
        start_notice_downPanel.setLayout(new FlowLayout());
        
        bt_exit4 = new JButton(sets.trans("Exit"));
        bt_next = new JButton(sets.trans("Standard"));
        bt_scenario = new JButton(sets.trans("Scenario"));
        bt_user_defined = new JButton(sets.trans("Custom"));
        
        start_verLabel = new JLabel(getVersionString(true));        
        start_verLabel.addMouseListener(this);
        bt_next.addActionListener(this);
        bt_next.setForeground(sets.getColor("ColorSelectedFore"));
        if(sets.getColor("ColorSelectedButton") != null)
            bt_next.setBackground(sets.getColor("ColorSelectedButton"));
        if(usingFont != null)
            bt_next.setFont(usingFont);
        bt_exit4.addActionListener(this);
        bt_exit4.setForeground(sets.getColor("ColorSelectedFore"));
        if(sets.getColor("ColorSelectedButton") != null)
            bt_exit4.setBackground(sets.getColor("ColorSelectedButton"));
        if(usingFont != null)
            bt_exit4.setFont(usingFont);
        start_verLabel.setForeground(sets.getColor("ColorSelectedFore"));
        if(usingFont != null)
            start_verLabel.setFont(usingFont);
        bt_scenario.addActionListener(this);
        bt_scenario.setForeground(sets.getColor("ColorSelectedFore"));
        if(sets.getColor("ColorSelectedButton") != null)
            bt_scenario.setBackground(sets.getColor("ColorSelectedButton"));
        if(usingFont != null)
            bt_scenario.setFont(usingFont);
        bt_user_defined.addActionListener(this);
        bt_user_defined.setForeground(sets.getColor("ColorSelectedFore"));
        if(sets.getColor("ColorSelectedButton") != null)
            bt_user_defined.setBackground(sets.getColor("ColorSelectedButton"));
        if(usingFont != null)
            bt_user_defined.setFont(usingFont);
        
        
        start_notice_downPanel.add(bt_next);
        start_notice_downPanel.add(bt_scenario);
        start_notice_downPanel.add(bt_user_defined);
        start_notice_downPanel.add(bt_exit4);
        
        start_notice_upPanel.setBackground(sets.getColor("ColorSelectedInsideBack"));
        start_notice_upPanel.setLayout(new BorderLayout());
        start_notice_upPanel.setBorder(new EtchedBorder());
        start_notice_descPanel = new JPanel();
        start_notice_descPanel.setBackground(sets.getColor("ColorSelectedInsideBack"));
        start_notice_upPanel.add(start_notice_descPanel, BorderLayout.EAST);
        start_notice_descPanel.setLayout(new FlowLayout());
        start_notice_descPanel.add(start_verLabel);
        start_notice_titlePanel = new JPanel();
        start_notice_titlePanel.setBackground(sets.getColor("ColorSelectedInsideBack"));
        start_notice_titlePanel.setLayout(new FlowLayout());
        start_notice_titleLabel = new JLabel(sets.trans("Reflexioner"));
        start_notice_titleLabel.setForeground(sets.getColor("ColorSelectedFore"));
        if(usingFont != null)
            start_notice_titleLabel.setFont(usingFontB);
        start_notice_titlePanel.add(start_notice_titleLabel);
        start_notice_upPanel.add(start_notice_titlePanel, BorderLayout.WEST);
        
        start_standardPanel = new JPanel();
        start_standardPanel.setBackground(sets.getColor("ColorSelectedBack"));
        start_standardPanel.setLayout(new BorderLayout());
        start_standardPanel.add(start_downPanel, BorderLayout.SOUTH);
        start_standardPanel.add(start_centerPanel, BorderLayout.CENTER);
        
        mainTab.add(sets.trans("Standard"), start_standardPanel);
        
        start_userDefinedPanel = new JPanel();
        start_userDefinedPanel.setBackground(sets.getColor("ColorSelectedBack"));
        start_userDefinedPanel.setLayout(new BorderLayout());
        
        start_userDefined_upPanel = new JPanel();
        start_userDefined_centerPanel = new JPanel();
        start_userDefined_downPanel = new JPanel();
        start_userDefined_upPanel.setBackground(sets.getColor("ColorSelectedBack"));
        start_userDefined_centerPanel.setBackground(sets.getColor("ColorSelectedBack"));
        start_userDefined_downPanel.setBackground(sets.getColor("ColorSelectedBack"));
        start_userDefined_centerPanel.setLayout(new BorderLayout());
        start_userDefinedArea = new JTextArea();
        start_userDefinedArea.setLineWrap(true);
        start_userDefinedArea.setBackground(sets.getColor("ColorSelectedInsideBack"));
        start_userDefinedArea.setForeground(sets.getColor("ColorSelectedFore"));
        if(usingFont != null)
            start_userDefinedArea.setFont(usingFont);
        start_userDefinedScroll = new JScrollPane(start_userDefinedArea);
        start_userDefined_centerPanel.add(start_userDefinedScroll, BorderLayout.CENTER);
        start_userDefined_downPanel.setLayout(new FlowLayout());
        bt_start_userDefined2 = new JButton(sets.trans("Start"));
        bt_exit2 = new JButton(sets.trans("Exit"));
        bt_autoComplete = new JButton(sets.trans("Summary"));
        bt_start_userDefined2.addActionListener(this);
        bt_exit2.addActionListener(this);
        bt_autoComplete.addActionListener(this);
        bt_start_userDefined2.setForeground(sets.getColor("ColorSelectedFore"));
        bt_exit2.setForeground(sets.getColor("ColorSelectedFore"));
        bt_autoComplete.setForeground(sets.getColor("ColorSelectedFore"));
        if(usingFont != null)
        {
            bt_start_userDefined2.setFont(usingFont);
            bt_exit2.setFont(usingFont);
            bt_autoComplete.setFont(usingFont);
        }
        if(sets.getColor("ColorSelectedButton") != null)
        {
            bt_start_userDefined2.setBackground(sets.getColor("ColorSelectedButton"));
            bt_exit2.setBackground(sets.getColor("ColorSelectedButton"));
            bt_autoComplete.setBackground(sets.getColor("ColorSelectedButton"));
        }
        start_userDefined_downPanel.add(bt_start_userDefined2);
        start_userDefined_downPanel.add(bt_autoComplete);
        start_userDefined_downPanel.add(bt_exit2);
        start_userDefined_upPanel.setLayout(new FlowLayout());
        start_userDefined_isScenario = new JCheckBox(sets.trans("Scenario"));
        start_userDefined_nameLabel = new JLabel(sets.trans("Name"));
        start_userDefined_nameField = new JTextField(10);
        start_userDefined_nameLabel.setForeground(sets.getColor("ColorSelectedFore"));
        start_userDefined_nameField.setForeground(sets.getColor("ColorSelectedFore"));
        start_userDefined_nameField.setBackground(sets.getColor("ColorSelectedInsideBack"));
        start_userDefined_isScenario.setBackground(sets.getColor("ColorSelectedBack"));
        start_userDefined_isScenario.setForeground(sets.getColor("ColorSelectedFore"));
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
        start_scenarioPanel.setBackground(sets.getColor("ColorSelectedBack"));
        start_scenarioPanel.setLayout(new BorderLayout());
        start_scenario_upPanel = new JPanel();
        start_scenario_centerPanel = new JPanel();
        start_scenario_downPanel = new JPanel();
        start_scenario_upPanel.setBackground(sets.getColor("ColorSelectedBack"));
        start_scenario_centerPanel.setBackground(sets.getColor("ColorSelectedBack"));
        start_scenario_downPanel.setBackground(sets.getColor("ColorSelectedBack"));
        start_scenarioPanel.add(start_scenario_upPanel, BorderLayout.NORTH);
        start_scenarioPanel.add(start_scenario_centerPanel, BorderLayout.CENTER);
        start_scenarioPanel.add(start_scenario_downPanel, BorderLayout.SOUTH);
        start_scenario_centerPanel.setLayout(new BorderLayout());
        start_scenarioList = new RList();
        start_scenarioList.addListSelectionListener(this);
        start_scenarioList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        start_scenarioList.setBackground(sets.getColor("ColorSelectedInsideBack"));
        start_scenarioList.setForeground(sets.getColor("ColorSelectedFore"));
        if(usingFont != null) 
            start_scenarioList.setFont(usingFont);
        start_scenarioListScroll = new JScrollPane(start_scenarioList);
        
        start_scenario_nameLabel = new JLabel(sets.trans("Name"));
        start_scenario_nameField = new JTextField(10);
        start_scenario_nameLabel.setForeground(sets.getColor("ColorSelectedFore"));
        start_scenario_nameField.setForeground(sets.getColor("ColorSelectedFore"));
        start_scenario_nameField.setBackground(sets.getColor("ColorSelectedInsideBack"));
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
        start_scenario_namePanel.setBackground(sets.getColor("ColorSelectedBack"));
        start_scenario_namePanel.setLayout(new FlowLayout());
        start_scenario_namePanel.add(start_scenario_nameLabel);
        start_scenario_namePanel.add(start_scenario_nameField);
        start_scenario_description = new JTextArea();
        start_scenario_description.setLineWrap(true);
        start_scenario_description.setEditable(false);
        start_scenario_description.setBackground(sets.getColor("ColorSelectedInsideBack"));
        start_scenario_description.setForeground(sets.getColor("ColorSelectedFore"));
        if(usingFont != null) 
        {
            start_scenario_description.setFont(usingFont);
        }
        start_scenario_descriptionScroll = new JScrollPane(start_scenario_description);
        start_scenario_descriptionPanel = new JPanel();
        start_scenario_descriptionPanel.setBackground(sets.getColor("ColorSelectedBack"));
        start_scenario_descriptionPanel.setLayout(new BorderLayout());
        start_scenario_descriptionPanel.add(start_scenario_descriptionScroll, BorderLayout.CENTER);        
        start_scenario_upPanel.setLayout(new BorderLayout());
        start_scenario_upPanel.add(start_scenario_namePanel);
        
        start_scenario_centerPanel.add(start_scenario_descriptionPanel, BorderLayout.CENTER);
        start_scenario_centerPanel.add(start_scenarioListScroll, BorderLayout.WEST);        
        scenarios = new Vector<ReflexScenario>();
        
        start_scenario_downPanel.setLayout(new FlowLayout());
        bt_start_scenario = new JButton(sets.trans("Start"));
        bt_exit3 = new JButton(sets.trans("Exit"));
        bt_viewOnEditor = new JButton(sets.trans("Edit"));
        bt_start_scenario.addActionListener(this);
        bt_exit3.addActionListener(this);
        bt_viewOnEditor.addActionListener(this);
        bt_start_scenario.setForeground(sets.getColor("ColorSelectedFore"));
        bt_viewOnEditor.setForeground(sets.getColor("ColorSelectedFore"));
        bt_exit3.setForeground(sets.getColor("ColorSelectedFore"));
        if(usingFont != null)
        {
            bt_start_scenario.setFont(usingFont);
            bt_exit3.setFont(usingFont);
            bt_viewOnEditor.setFont(usingFont);
        }
        if(sets.getColor("ColorSelectedButton") != null)
        {
            bt_start_scenario.setBackground(sets.getColor("ColorSelectedButton"));
            bt_exit3.setBackground(sets.getColor("ColorSelectedButton"));
            bt_viewOnEditor.setBackground(sets.getColor("ColorSelectedButton"));
        }
        
        start_scenario_downPanel.add(bt_start_scenario);
        start_scenario_downPanel.add(bt_viewOnEditor);
        start_scenario_downPanel.add(bt_exit3);
        
        mainTab.add(sets.trans("Scenario"), start_scenarioPanel);
        mainTab.add(sets.trans("Custom"), start_userDefinedPanel);        
        
        
        start_downloadPanel = new JPanel();
        mainTab.add(sets.trans("Download Additional Assets"), start_downloadPanel);
        start_downloadPanel.setLayout(new BorderLayout());
        start_downloadPanel.setBackground(sets.getColor("ColorSelectedBack"));
        start_download_upPanel = new JPanel();
        start_download_centerPanel = new JPanel();
        start_download_downPanel = new JPanel();
        start_downloadPanel.add(start_download_upPanel, BorderLayout.NORTH);
        start_downloadPanel.add(start_download_centerPanel, BorderLayout.CENTER);
        start_downloadPanel.add(start_download_downPanel, BorderLayout.SOUTH);
        start_download_upPanel.setBackground(sets.getColor("ColorSelectedBack"));
        start_download_downPanel.setBackground(sets.getColor("ColorSelectedBack"));
        start_download_centerPanel.setBackground(sets.getColor("ColorSelectedBack"));
        start_download_centerPanel.setLayout(new BorderLayout());
        start_download_message = new JTextArea();
        start_download_message.setText(sets.trans("Do you trust this web address?") + "\n" + download_url1 + "\n" + download_url2 + "\n\n"
        + sets.trans("Files will be located at") + " " + sets.getDefaultPath() + "\n\n");
        start_download_message.setEditable(false);
        start_download_message.setLineWrap(true);
        start_download_message.setBackground(sets.getColor("ColorSelectedInsideBack"));
        start_download_message.setForeground(sets.getColor("ColorSelectedFore"));
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
        bt_download = new JButton(sets.trans("Download"));
        bt_download.addActionListener(this);
        bt_download.setForeground(sets.getColor("ColorSelectedFore"));
        if(usingFont != null)
            bt_download.setFont(usingFont);
        if(sets.getColor("ColorSelectedButton") != null)
            bt_download.setBackground(sets.getColor("ColorSelectedButton"));
        
        bt_setUrl = new JButton(sets.trans("URL") + " " + sets.trans("Edit"));
        bt_setUrl.addActionListener(this);
        bt_setUrl.setForeground(sets.getColor("ColorSelectedFore"));
        if(usingFont != null)
            bt_setUrl.setFont(usingFont);
        if(sets.getColor("ColorSelectedButton") != null)
            bt_setUrl.setBackground(sets.getColor("ColorSelectedButton"));
        
        bt_exit5 = new JButton(sets.trans("Exit"));
        bt_exit5.addActionListener(this);
        bt_exit5.setForeground(sets.getColor("ColorSelectedFore"));
        if(usingFont != null)
            bt_exit5.setFont(usingFont);
        if(sets.getColor("ColorSelectedButton") != null)
            bt_exit5.setBackground(sets.getColor("ColorSelectedButton"));
        start_download_downPanel.add(bt_download);
        start_download_downPanel.add(bt_setUrl);
        start_download_downPanel.add(bt_exit5);
            
        start_upPanel.setLayout(new BorderLayout());
        
        start_buttonPanel = new JPanel();
        start_buttonPanel.setBackground(sets.getColor("ColorSelectedBack"));
        start_downPanel.setLayout(new BorderLayout());
        start_downPanel.add(start_buttonPanel, BorderLayout.SOUTH);
        start_buttonPanel.setLayout(new FlowLayout());
        
        if(sets.getBool("Undecorated"))
        {
            startDialog.setUndecorated(true);
            startDialog.setSize(455, 300);
            startDialog.setLocation((int)(sets.getScreenSize().getWidth()/2 - startDialog.getWidth()/2), (int)(sets.getScreenSize().getHeight()/2 - startDialog.getHeight()/2));
            start_titlePanel = new JPanel();
            start_titlePanel.setBackground(sets.getColor("ColorSelectedInsideBack"));
            start_titlePanel.setBorder(new EtchedBorder());
            start_titlePanel.setLayout(new FlowLayout());
            start_titleLabel = new JLabel(sets.trans("Reflexioner"));
            start_titleLabel.setForeground(sets.getColor("ColorSelectedFore"));
            if(usingFont != null)
                start_titleLabel.setFont(usingFont);
            start_titlePanel.add(start_titleLabel);
            start_titlePanel.addMouseListener(this);
            start_titlePanel.addMouseMotionListener(this);
            start_upPanel.add(start_titlePanel, BorderLayout.NORTH);
        }
        
        
        bt_start = new JButton(sets.trans("Start"));
        bt_start.addActionListener(this);
        bt_exit = new JButton(sets.trans("Exit"));
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
        
        combo_dif = new RComboBox(difficultyModes);
        combo_dif.setBackground(sets.getColor("ColorSelectedInsideBack"));
        combo_dif.setForeground(sets.getColor("ColorSelectedFore"));
        combo_ship = new RComboBox(ships);
        combo_ship.setBackground(sets.getColor("ColorSelectedInsideBack"));
        combo_ship.setForeground(sets.getColor("ColorSelectedFore"));
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
        
        
        start_scenario_selectShipCombo = new RComboBox(ships);
        start_scenario_selectShipCombo.setBackground(sets.getColor("ColorSelectedInsideBack"));
        start_scenario_selectShipCombo.setForeground(sets.getColor("ColorSelectedFore"));
        if(usingFont != null)
            start_scenario_selectShipCombo.setFont(usingFont);
        start_scenario_descriptionPanel.add(start_scenario_selectShipCombo, BorderLayout.SOUTH);
        
        refreshScenario(true);
        
        bt_start.setForeground(sets.getColor("ColorSelectedFore"));
        bt_exit.setForeground(sets.getColor("ColorSelectedFore"));
        
        if(sets.getColor("ColorSelectedButton") != null)
        {
            bt_start.setBackground(sets.getColor("ColorSelectedBack"));
            bt_exit.setBackground(sets.getColor("ColorSelectedBack"));
        }
        
        start_centerPanel.setLayout(new BorderLayout());
        
        start_namePanel = new JPanel();
        start_namePanel.setBackground(sets.getColor("ColorSelectedBack"));
        start_centerPanel.add(start_namePanel, BorderLayout.NORTH);
        start_namePanel.setLayout(new FlowLayout());        
        start_nameLabel = new JLabel(sets.trans("Name"));
        start_nameLabel.setForeground(sets.getColor("ColorSelectedFore"));
        start_nameField = new JTextField(7);        
        start_nameField.setBackground(sets.getColor("ColorSelectedInsideBack"));
        start_nameField.setForeground(sets.getColor("ColorSelectedFore"));
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
        start_centerHelpPanel.setBackground(sets.getColor("ColorSelectedBack"));
        start_centerHelpPanel.setLayout(new BorderLayout());
        start_centerHelpArea = new JTextArea();
        start_centerHelpArea.setLineWrap(true);
        start_centerHelpArea.setEditable(false);
        start_centerHelpArea.setBackground(sets.getColor("ColorSelectedInsideBack"));
        start_centerHelpArea.setForeground(sets.getColor("ColorSelectedFore"));
        if(usingFont != null)
            start_centerHelpArea.setFont(usingFont);
        start_centerHelpScroll = new JScrollPane(start_centerHelpArea);
        start_centerHelpPanel.add(start_centerHelpScroll);
        
        start_centerHelpArea.setText(
        "Reflexioner\n\n"
        + "You can play with keyboard.\n"
        + "If you using touch screen, press ▲ button to open other buttons to control.\n\n"                
        + "You will control the green circle. See following key you will press.\n\n"
        + "Move →←↑↓, Break SHIFT, Fire SPACE, Change weapon 123, Toggle auto Fire 4, Pause L, Exit K" + "\n\n"
        + "After the game start, dark circles will appear, and they shoot some missiles.\n"
        + "You will control the green circle to avoid these missiles.\n\n"
        + "Also, you can shoot missiles to attack dark circles.\n"
        + "You can get points to earn points.\n\n"
        + "Sometimes, pink circles will appear, and you can improve the green circle\'s abilities.\n\n"
        + "The game will finish when the green circle is destroyed.\n"
        + "You can see your points, and authority code for you to demonstrate your game skill to others.\n"
        + "\n"
        + "Made by HJOW\n"
        + "hujinone22@naver.com\n"
        + "http://hjow.github.io/Reflexioner/");
        
        if(independence)
            start_upPanel.add(menuBar, BorderLayout.CENTER);
        
        start_todayPanel = new JPanel();        
        mainTab.add("!", start_todayPanel);
        start_todayPanel.setBackground(sets.getColor("ColorSelectedBack"));
        
        start_today_upPanel = new JPanel();
        start_today_centerPanel = new JPanel();
        start_today_downPanel = new JPanel();
        start_todayPanel.setLayout(new BorderLayout());
        start_todayPanel.add(start_today_upPanel, BorderLayout.NORTH);
        start_todayPanel.add(start_today_centerPanel, BorderLayout.CENTER);
        start_todayPanel.add(start_today_downPanel, BorderLayout.SOUTH);
        start_today_upPanel.setBackground(sets.getColor("ColorSelectedBack"));
        start_today_centerPanel.setBackground(sets.getColor("ColorSelectedBack"));
        start_today_downPanel.setBackground(sets.getColor("ColorSelectedBack"));
        start_today_area = new OldBrowser(sets);
        start_today_area.setEditable(false);
        start_today_area.setBackground(sets.getColor("ColorSelectedInsideBack"));
        start_today_area.setForeground(sets.getColor("ColorSelectedFore"));        
        start_today_scroll = new JScrollPane(start_today_area);
        start_today_centerPanel.setLayout(new BorderLayout());
        start_today_centerPanel.add(start_today_scroll, BorderLayout.CENTER);
        start_today_upPanel.setLayout(new FlowLayout());
        start_today_nameLabel = new JLabel(sets.trans("Start"));
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
        start_today_nameLabel.setForeground(sets.getColor("ColorSelectedFore"));
        start_today_nameField.setForeground(sets.getColor("ColorSelectedFore"));
        start_today_nameField.setBackground(sets.getColor("ColorSelectedInsideBack"));
        start_today_downPanel.setLayout(new FlowLayout());        
        bt_start_today = new JButton(sets.trans("Start"));
        bt_exit6 = new JButton(sets.trans("Exit"));
        bt_start_today.addActionListener(this);
        bt_exit6.addActionListener(this);
        bt_start_today.setForeground(sets.getColor("ColorSelectedFore"));
        bt_exit6.setForeground(sets.getColor("ColorSelectedFore"));
        if(usingFont != null)
        {
            start_today_area.setFont(usingFont);
            bt_start_today.setFont(usingFont);
            bt_exit6.setFont(usingFont);
            start_today_nameField.setFont(usingFont);
            start_today_nameLabel.setFont(usingFont);
        }
        if(sets.getColor("ColorSelectedButton") != null)
        {
            bt_start_today.setBackground(sets.getColor("ColorSelectedButton"));
            bt_exit6.setBackground(sets.getColor("ColorSelectedButton"));
        }
        start_today_downPanel.add(bt_start_today);
        start_today_downPanel.add(bt_exit6);
        bt_start_today.setEnabled(false);
        
        
        
        menuBar.setBackground(sets.getColor("ColorSelectedInsideBack"));
        menuBar.setForeground(sets.getColor("ColorSelectedFore"));
        
        menu_file = new JMenu(sets.trans("File"));
        menu_file.setBackground(sets.getColor("ColorSelectedBack"));
        menu_file.setForeground(sets.getColor("ColorSelectedFore"));
        if(usingFont != null)
        {
            menu_file.setFont(usingFont);
        }
        menuBar.add(menu_file);
        
        menu_file_start = new JMenuItem(sets.trans("Start"));
        menu_file_start.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, KeyEvent.CTRL_MASK));    
        menu_file_start.addActionListener(this);
        menu_file_start.setBackground(sets.getColor("ColorSelectedBack"));
        menu_file_start.setForeground(sets.getColor("ColorSelectedFore"));
        if(usingFont != null)
        {
            menu_file_start.setFont(usingFont);
        }
        menu_file.add(menu_file_start);
        
        menu_file_start_userDefined = new JMenuItem(sets.trans("Start (Custom)"));
        menu_file_start_userDefined.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, KeyEvent.ALT_MASK));    
        menu_file_start_userDefined.addActionListener(this);
        menu_file_start_userDefined.setBackground(sets.getColor("ColorSelectedBack"));
        menu_file_start_userDefined.setForeground(sets.getColor("ColorSelectedFore"));
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
        
        menu_file_load = new JMenuItem(sets.trans("Load"));
        menu_file_load.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, KeyEvent.CTRL_MASK));    
        menu_file_load.addActionListener(this);
        menu_file_load.setBackground(sets.getColor("ColorSelectedBack"));
        menu_file_load.setForeground(sets.getColor("ColorSelectedFore"));
        if(usingFont != null)
        {
            menu_file_load.setFont(usingFont);
        }
        menu_file.add(menu_file_load);
        
        menu_file_manage = new JMenu(sets.trans("Management"));
        menu_file_manage.setBackground(sets.getColor("ColorSelectedBack"));
        menu_file_manage.setForeground(sets.getColor("ColorSelectedFore"));
        if(usingFont != null)
        {
            menu_file_manage.setFont(usingFont);
        }
        menu_file.add(menu_file_manage);
        
        menu_manage_enableImage = new JCheckBoxMenuItem("Image");
        menu_manage_enableImage.setBackground(sets.getColor("ColorSelectedBack"));
        menu_manage_enableImage.setForeground(sets.getColor("ColorSelectedFore"));
        if(usingFont != null)
        {
            menu_manage_enableImage.setFont(usingFont);
        }
        menu_manage_enableImage.addItemListener(this);
        menu_manage_enableImage.setSelected(true);
        menu_file_manage.add(menu_manage_enableImage);
        
        menu_manage_enableSound = new JCheckBoxMenuItem("Sound");
        menu_manage_enableSound.setBackground(sets.getColor("ColorSelectedBack"));
        menu_manage_enableSound.setForeground(sets.getColor("ColorSelectedFore"));
        if(usingFont != null)
        {
            menu_manage_enableSound.setFont(usingFont);
        }
        menu_manage_enableSound.addItemListener(this);
        menu_file_manage.add(menu_manage_enableSound);
        
        menu_manage_enableImage.setSelected(image_allow);
        menu_manage_enableSound.setSelected(sound_allow);
        
        menu_manage_autoControl = new JCheckBoxMenuItem(sets.trans("AI"));
        menu_manage_autoControl.setBackground(sets.getColor("ColorSelectedBack"));
        menu_manage_autoControl.setForeground(sets.getColor("ColorSelectedFore"));
        if(usingFont != null)
        {
            menu_manage_autoControl.setFont(usingFont);
        }
        menu_file_manage.add(menu_manage_autoControl);
        
        menu_manage_saveReplay = new JCheckBoxMenuItem(sets.trans("Replay"));
        menu_manage_saveReplay.setBackground(sets.getColor("ColorSelectedBack"));
        menu_manage_saveReplay.setForeground(sets.getColor("ColorSelectedFore"));
        if(usingFont != null)
        {
            menu_manage_saveReplay.setFont(usingFont);
        }
        menu_file_manage.add(menu_manage_saveReplay);
        
        menu_manage_setIntervalReplay = new JMenuItem(sets.trans("Replay Interval"));
        menu_manage_setIntervalReplay.addActionListener(this);
        menu_manage_setIntervalReplay.setBackground(sets.getColor("ColorSelectedBack"));
        menu_manage_setIntervalReplay.setForeground(sets.getColor("ColorSelectedFore"));
        if(usingFont != null)
        {
            menu_manage_setIntervalReplay.setFont(usingFont);
        }
        menu_file_manage.add(menu_manage_setIntervalReplay);
        
        menu_manage_uninstall = new JMenuItem(sets.trans("Uninstall"));
        menu_manage_uninstall.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F9, KeyEvent.CTRL_MASK));
        menu_manage_uninstall.addActionListener(this);
        menu_manage_uninstall.setBackground(sets.getColor("ColorSelectedBack"));
        menu_manage_uninstall.setForeground(sets.getColor("ColorSelectedFore"));
        if(usingFont != null)
        {
            menu_manage_uninstall.setFont(usingFont);
        }
        menu_file_manage.add(menu_manage_uninstall);
        
        menu_file_exit = new JMenuItem(sets.trans("Exit"));
        menu_file_exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, KeyEvent.CTRL_MASK));
        menu_file_exit.addActionListener(this);
        menu_file_exit.setBackground(sets.getColor("ColorSelectedBack"));
        menu_file_exit.setForeground(sets.getColor("ColorSelectedFore"));
        if(usingFont != null)
        {
            menu_file_exit.setFont(usingFont);
        }
        menu_file.add(menu_file_exit);
        
        menu_view = new JMenu(sets.trans("View"));
        menu_view.setBackground(sets.getColor("ColorSelectedBack"));
        menu_view.setForeground(sets.getColor("ColorSelectedFore"));
        if(usingFont != null)
        {
            menu_view.setFont(usingFont);
        }
        menuBar.add(menu_view);
        
        menu_view_message = new JMenuItem(sets.trans("Log"));
        menu_view_message.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F8, KeyEvent.CTRL_MASK));
        menu_view_message.addActionListener(this);
        menu_view_message.setBackground(sets.getColor("ColorSelectedBack"));
        menu_view_message.setForeground(sets.getColor("ColorSelectedFore"));
        if(usingFont != null)
        {
            menu_view_message.setFont(usingFont);
        }
        menu_view.add(menu_view_message);
        
        scenarioEditor = new ReflexScenarioEditor(startDialog, sets, this);
        
        
        
        menu_view_editor = new JMenuItem(sets.trans("Scenario Editor"));
        menu_view_editor.addActionListener(this);
        menu_view_editor.setBackground(sets.getColor("ColorSelectedBack"));
        menu_view_editor.setForeground(sets.getColor("ColorSelectedFore"));
        if(usingFont != null)
        {
            menu_view_editor.setFont(usingFont);
        }
        menu_view.add(menu_view_editor);
        
        replayPlayer = new ReflexReplayPlayer(startDialog, sets, this);
        
        menu_view_replayPlayer = new JMenuItem(sets.trans("Replay Player"));
        menu_view_replayPlayer.addActionListener(this);
        menu_view_replayPlayer.setBackground(sets.getColor("ColorSelectedBack"));
        menu_view_replayPlayer.setForeground(sets.getColor("ColorSelectedFore"));
        if(usingFont != null)
        {
            menu_view_replayPlayer.setFont(usingFont);
        }
        menu_view.add(menu_view_replayPlayer);
        
        menu_view_check = new JMenuItem(sets.trans("Code Checker"));
        menu_view_check.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, KeyEvent.CTRL_MASK));
        menu_view_check.addActionListener(this);
        menu_view_check.setBackground(sets.getColor("ColorSelectedBack"));
        menu_view_check.setForeground(sets.getColor("ColorSelectedFore"));
        if(usingFont != null)
        {
            menu_view_check.setFont(usingFont);
        }
        menu_view.add(menu_view_check);
        
        menu_help = new JMenu(sets.trans("Help"));
        menu_help.setBackground(sets.getColor("ColorSelectedBack"));
        menu_help.setForeground(sets.getColor("ColorSelectedFore"));
        if(usingFont != null)
        {
            menu_help.setFont(usingFont);
        }
        menuBar.add(menu_help);        
                
        menu_help_files = new JMenuItem();
        menu_help_files.addActionListener(this);
        menu_help_files.setBackground(sets.getColor("ColorSelectedBack"));
        menu_help_files.setForeground(sets.getColor("ColorSelectedFore"));
        if(usingFont != null)
        {
            menu_help_files.setFont(usingFont);
        }
        menu_help.add(menu_help_files);
        menu_help_files.setText(sets.trans("Asset files information (Written in Korean)"));
        
        menu_help_about = new JMenuItem(sets.trans("Reflexioner") + " " + sets.trans("is..."));
        menu_help_about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, KeyEvent.CTRL_MASK));
        menu_help_about.addActionListener(this);
        menu_help_about.setBackground(sets.getColor("ColorSelectedBack"));
        menu_help_about.setForeground(sets.getColor("ColorSelectedFore"));
        if(usingFont != null)
        {
            menu_help_about.setFont(usingFont);
        }
        menu_help.add(menu_help_about);
        
        finishDialog = new JDialog(window);
        finishDialog.setTitle(sets.trans("Result"));
        finishDialog.addWindowListener(this);
        finishDialog.setSize(400, 300);
        finishDialog.setLocation((int)(sets.getScreenSize().getWidth()/2 - finishDialog.getWidth()/2), (int)(sets.getScreenSize().getHeight()/2 - finishDialog.getHeight()/2));
        finishDialog.setLayout(new BorderLayout());
        finish_mainPanel = new JPanel();
        finish_mainPanel.setBackground(sets.getColor("ColorSelectedBack"));
        finish_mainPanel.setBorder(new EtchedBorder());
        finishDialog.add(finish_mainPanel);
        finish_mainPanel.setLayout(new BorderLayout());
        
        finish_upPanel = new JPanel();
        finish_downPanel = new JPanel();
        finish_centerPanel = new JPanel();
        finish_upPanel.setBackground(sets.getColor("ColorSelectedBack"));
        finish_downPanel.setBackground(sets.getColor("ColorSelectedBack"));
        finish_centerPanel.setBackground(sets.getColor("ColorSelectedBack"));
        
        finish_mainPanel.add(finish_upPanel, BorderLayout.NORTH);
        finish_mainPanel.add(finish_downPanel, BorderLayout.SOUTH);
        finish_mainPanel.add(finish_centerPanel, BorderLayout.CENTER);
        
        finish_downPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        
        String continue_label = "";
        continue_label = sets.trans("Continue");
        bt_continue = new JButton(continue_label);
        bt_continue.addActionListener(this);
        if(sets.getColor("ColorSelectedButton") != null)
            bt_continue.setBackground(sets.getColor("ColorSelectedButton"));
        bt_continue.setForeground(sets.getColor("ColorSelectedFore"));
        if(usingFont != null)
            bt_continue.setFont(usingFont);
        
        bt_finish_ok = new JButton(sets.trans("Exit"));
        bt_finish_ok.addActionListener(this);
        if(usingFont != null)
            bt_finish_ok.setFont(usingFont);
        
        bt_finish_ok.setForeground(sets.getColor("ColorSelectedFore"));
        if(sets.getColor("ColorSelectedButton") != null)
        {
            bt_finish_ok.setBackground(sets.getColor("ColorSelectedButton"));
        }
        
        bt_event_open = new JButton(sets.trans("Event"));
        bt_event_open.addActionListener(this);
        if(usingFont != null)
            bt_event_open.setFont(usingFont);
        
        bt_event_open.setForeground(sets.getColor("ColorSelectedFore"));
        if(sets.getColor("ColorSelectedButton") != null)
        {
            bt_event_open.setBackground(sets.getColor("ColorSelectedButton"));
        }
        
        finish_resultPanel = new JPanel();
        finish_resultPanel.setBackground(sets.getColor("ColorSelectedBack"));
        finish_upPanel.setLayout(new BorderLayout());
        finish_upPanel.add(finish_resultPanel, BorderLayout.SOUTH);
        
        finish_resultPanel.setLayout(new FlowLayout());
        finish_resultLabel = new JLabel(sets.trans("Result"));
        finish_resultLabel.setForeground(sets.getColor("ColorSelectedFore"));
        if(usingFont != null)
            finish_resultLabel.setFont(usingFont);
        finish_resultField = new JTextField(25);
        finish_resultField.setBackground(sets.getColor("ColorSelectedInsideBack"));
        finish_resultField.setForeground(sets.getColor("ColorSelectedFore"));
        if(usingFont != null)
            finish_resultField.setFont(usingFont);
        finish_resultField.setEditable(false);
        finish_resultField.setBorder(new EtchedBorder());
        finish_resultPanel.add(finish_resultLabel);
        finish_resultPanel.add(finish_resultField);
        
        JPanel pnFinCenter1, pnFinCenter2;
        pnFinCenter1 = new JPanel();
        pnFinCenter2 = new JPanel();
        pnFinCenter1.setBackground(sets.getColor("ColorSelectedBack"));
        pnFinCenter2.setBackground(sets.getColor("ColorSelectedBack"));
        
        finish_layout = new CardLayout();
        finish_centerPanel.setLayout(finish_layout);
        
        finish_centerPanel.add(pnFinCenter1, "fin1");
        finish_centerPanel.add(pnFinCenter2, "fin2");
        
        finish_pns = new JPanel[7];
        pnFinCenter1.setLayout(new GridLayout(finish_pns.length, 1));
        for(int i=0; i<finish_pns.length; i++)
        {
            finish_pns[i] = new JPanel();
            finish_pns[i].setBackground(sets.getColor("ColorSelectedBack"));
            finish_pns[i].setLayout(new FlowLayout(FlowLayout.LEFT));            
            pnFinCenter1.add(finish_pns[i]);
        }
        
        finish_nameLabel = new JLabel(sets.trans("Start"));
        finish_nameField = new JTextField(10);
        finish_nameField.setEditable(false);
        finish_nameField.setBorder(new EtchedBorder());
        finish_nameLabel.setForeground(sets.getColor("ColorSelectedFore"));
        finish_nameField.setBackground(sets.getColor("ColorSelectedInsideBack"));
        finish_nameField.setForeground(sets.getColor("ColorSelectedFore"));
        if(usingFont != null)
        {
            finish_nameLabel.setFont(usingFont);
            finish_nameField.setFont(usingFont);
        }
        finish_pns[1].add(finish_nameLabel);
        finish_pns[1].add(finish_nameField);
        
        finish_catchEnemyLabel = new JLabel(sets.trans("Eliminated enemies"));
        finish_catchEnemyLabel.setForeground(sets.getColor("ColorSelectedFore"));
        finish_catchEnemyField = new JTextField(25);
        finish_catchEnemyField.setForeground(sets.getColor("ColorSelectedFore"));
        finish_catchEnemyField.setBackground(sets.getColor("ColorSelectedInsideBack"));
        if(usingFont != null)
            finish_catchEnemyLabel.setFont(usingFont);
        if(usingFont != null)
            finish_catchEnemyField.setFont(usingFont);
        finish_catchEnemyField.setEditable(false);
        finish_catchEnemyField.setBorder(new EtchedBorder());
        finish_pns[2].add(finish_catchEnemyLabel);
        finish_pns[2].add(finish_catchEnemyField);
        
        finish_catchItemLabel = new JLabel(sets.trans("Taken items"));
        finish_catchItemLabel.setForeground(sets.getColor("ColorSelectedFore"));
        finish_catchItemField = new JTextField(25);
        finish_catchItemField.setForeground(sets.getColor("ColorSelectedFore"));
        finish_catchItemField.setBackground(sets.getColor("ColorSelectedInsideBack"));
        if(usingFont != null)
            finish_catchItemLabel.setFont(usingFont);
        if(usingFont != null)
            finish_catchItemField.setFont(usingFont);
        finish_catchItemField.setEditable(false);
        finish_catchItemField.setBorder(new EtchedBorder());
        finish_pns[3].add(finish_catchItemLabel);
        finish_pns[3].add(finish_catchItemField);
        
        finish_authLabel = new JLabel(sets.trans("Authority"));
        finish_authField = new JTextField(20);
        finish_authField2= new JTextField(20);
        bt_authCopy  = new JButton(sets.trans("Copy to clipboard"));
        bt_authCopy2 = new JButton(sets.trans("Copy to clipboard"));
        bt_authCopy.addActionListener(this);
        bt_authCopy2.addActionListener(this);
        finish_authLabel.setForeground(sets.getColor("ColorSelectedFore"));
        finish_authField.setForeground(sets.getColor("ColorSelectedFore"));
        finish_authField2.setForeground(sets.getColor("ColorSelectedFore"));
        bt_authCopy.setForeground(sets.getColor("ColorSelectedFore"));
        bt_authCopy2.setForeground(sets.getColor("ColorSelectedFore"));
        finish_authField.setBackground(sets.getColor("ColorSelectedInsideBack"));
        finish_authField2.setBackground(sets.getColor("ColorSelectedInsideBack"));
        if(sets.getColor("ColorSelectedButton") != null)
        {
            bt_authCopy.setBackground(sets.getColor("ColorSelectedButton"));
            bt_authCopy2.setBackground(sets.getColor("ColorSelectedButton"));
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
        finish_ta.setBackground(sets.getColor("ColorSelectedBack"));
        finish_ta.setForeground(sets.getColor("ColorSelectedFore"));
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
        pn5c.setBackground(sets.getColor("ColorSelectedBack"));
        pn5r.setBackground(sets.getColor("ColorSelectedBack"));
        pn5c.setLayout(new FlowLayout(FlowLayout.LEFT));
        pn5r.setLayout(new FlowLayout(FlowLayout.RIGHT));
        finish_pns[5].add(pn5c, BorderLayout.CENTER);
        finish_pns[5].add(pn5r, BorderLayout.EAST);
        
        pn5c.add(finish_authLabel);
        pn5c.add(finish_authField);
        pn5r.add(bt_authCopy);
        
        //bt_continue.setVisible(false);
        bt_saveReplay = new JButton(sets.trans("Replay"));
        bt_saveReplay.addActionListener(this);
        if(sets.getColor("ColorSelectedButton") != null)
            bt_saveReplay.setBackground(sets.getColor("ColorSelectedButton"));
        bt_saveReplay.setForeground(sets.getColor("ColorSelectedFore"));
        if(usingFont != null)
            bt_saveReplay.setFont(usingFont);
        
        finish_downPanel.add(bt_saveReplay);
        bt_saveReplay.setVisible(false);
        
        bt_saveState = new JButton(sets.trans("Save"));
        bt_saveState.addActionListener(this);
        if(sets.getColor("ColorSelectedButton") != null)
            bt_saveState.setBackground(sets.getColor("ColorSelectedButton"));
        bt_saveState.setForeground(sets.getColor("ColorSelectedFore"));
        if(usingFont != null)
            bt_saveState.setFont(usingFont);
        // finish_pns[6].add(bt_saveState);
        // finish_pns[6].add(bt_event_open);
        finish_downPanel.add(bt_saveState);
        finish_downPanel.add(bt_event_open);
        bt_saveState.setVisible(false);
        
        finish_downPanel.add(bt_continue);
        finish_downPanel.add(bt_finish_ok);
        
        if(sets.getBool("Undecorated"))
        {
            finishDialog.setUndecorated(true);
            finishDialog.setSize(400, 300);
            finishDialog.setLocation((int)(sets.getScreenSize().getWidth()/2 - finishDialog.getWidth()/2), (int)(sets.getScreenSize().getHeight()/2 - finishDialog.getHeight()/2));
            finish_titlePanel = new JPanel();
            finish_titlePanel.setBackground(sets.getColor("ColorSelectedInsideBack"));
            finish_titlePanel.setBorder(new EtchedBorder());
            finish_titlePanel.setLayout(new FlowLayout());
            finish_titleLabel = new JLabel(sets.trans("Reflexioner"));
            finish_titleLabel.setForeground(sets.getColor("ColorSelectedFore"));
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
        about_mainPanel.setBackground(sets.getColor("ColorSelectedBack"));
        about_mainPanel.setBorder(new EtchedBorder());
        about_mainPanel.setLayout(new BorderLayout());
        about_upPanel = new JPanel();
        about_downPanel = new JPanel();
        about_centerPanel = new JPanel();
        about_titlePanel = new JPanel();
        about_upPanel.setBackground(sets.getColor("ColorUnselectedInsideBack"));
        about_titlePanel.setBackground(sets.getColor("ColorSelectedInsideBack"));
        about_titlePanel.setBorder(new EtchedBorder());
        about_centerPanel.setBackground(sets.getColor("ColorSelectedBack"));
        about_downPanel.setBackground(sets.getColor("ColorSelectedBack"));
        about_mainPanel.add(about_upPanel, BorderLayout.NORTH);
        about_mainPanel.add(about_centerPanel, BorderLayout.CENTER);
        about_mainPanel.add(about_downPanel, BorderLayout.SOUTH);
        about_centerPanel.setLayout(new BorderLayout());
        about_downPanel.setLayout(new FlowLayout());
        about_upPanel.setLayout(new BorderLayout());
        about_upPanel.add(about_titlePanel, BorderLayout.NORTH);
        about_gapPanel = new JPanel();
        about_gapPanel.setBackground(sets.getColor("ColorUnselectedInsideBack"));
        about_upPanel.add(about_gapPanel, BorderLayout.CENTER);
        about_titlePanel.addMouseListener(this);
        about_titlePanel.addMouseMotionListener(this);
        about_titlePanel.setLayout(new FlowLayout());
        about_titleLabel = new JLabel(sets.trans("Reflexioner"));
        about_titleLabel.setForeground(sets.getColor("ColorSelectedFore"));
        if(usingFont != null) about_titleLabel.setFont(usingFont);
        about_titlePanel.add(about_titleLabel);
        
        about_centerLabel = new JLabel(sets.trans("Reflexioner"));
        about_centerLabel.setFont(usingFont2B.deriveFont(Font.BOLD).deriveFont(30.0f));
        about_centerLabel.setForeground(sets.getColor("ColorSelectedFore"));
        about_centerLabelPanel = new JPanel();
        about_centerLabelPanel.setLayout(new BorderLayout());
        about_centerPanel.add(about_centerLabelPanel, BorderLayout.CENTER);
        about_centerLabelPanel.setBackground(sets.getColor("ColorSelectedBack"));
        about_editionStringPanel = new JPanel();
        about_titleStringPanel = new JPanel();
        about_centerLabelPanel.add(about_editionStringPanel, BorderLayout.SOUTH);
        about_centerLabelPanel.add(about_titleStringPanel, BorderLayout.CENTER);
        about_titleStringPanel.setLayout(new FlowLayout());
        about_editionStringPanel.setLayout(new FlowLayout());
        about_titleStringPanel.setBackground(sets.getColor("ColorSelectedInsideBack"));
        about_editionStringPanel.setBackground(sets.getColor("ColorSelectedInsideBack"));
        about_titleStringPanel.add(about_centerLabel);
        about_editionLabel = new JLabel();
        about_editionLabel.addMouseListener(this);
        about_editionLabel.setText(dist.getName());
        about_editionLabel.setForeground(dist.getColor());
        about_editionStringPanel.add(about_editionLabel);
        about_versionPanel = new JPanel();
        about_versionPanel.setLayout(new FlowLayout());
        about_versionPanel.setBackground(sets.getColor("ColorUnselectedInsideBack"));
        about_versionLabel = new JLabel(getVersionString(true));
        about_versionLabel.setForeground(sets.getColor("ColorSelectedFore"));
        if(usingFont != null)
            about_versionLabel.setFont(usingFont);
        about_versionPanel.add(about_versionLabel);
        about_centerPanel.add(about_versionPanel, BorderLayout.SOUTH);
        
        
        bt_aboutClose = new JButton(sets.trans("Close"));
        bt_aboutClose.addActionListener(this);
        bt_aboutClose.setForeground(sets.getColor("ColorSelectedFore"));
        if(usingFont != null)
            bt_aboutClose.setFont(usingFont);
        if(sets.getColor("ColorSelectedButton") != null)
            bt_aboutClose.setBackground(sets.getColor("ColorSelectedButton"));
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
        userDefined_mainPanel.setBackground(sets.getColor("ColorSelectedBack"));
        userDefined_upPanel = new JPanel();
        userDefined_downPanel = new JPanel();
        userDefined_centerPanel = new JPanel();
        userDefined_mainPanel.add(userDefined_upPanel, BorderLayout.NORTH);
        userDefined_mainPanel.add(userDefined_downPanel, BorderLayout.SOUTH);
        userDefined_mainPanel.add(userDefined_centerPanel, BorderLayout.CENTER);
        userDefined_upPanel.setBackground(sets.getColor("ColorSelectedBack"));
        userDefined_centerPanel.setBackground(sets.getColor("ColorSelectedBack"));
        userDefined_downPanel.setBackground(sets.getColor("ColorSelectedBack"));
        
        userDefined_upPanel.setLayout(new BorderLayout());
        userDefined_namePanel = new JPanel();
        userDefined_namePanel.setBackground(sets.getColor("ColorSelectedBack"));
        userDefined_upPanel.add(userDefined_namePanel, BorderLayout.SOUTH);
        userDefined_namePanel.setLayout(new FlowLayout());
        userDefined_nameLabel = new JLabel(sets.trans("Start"));
        userDefined_nameLabel.setForeground(sets.getColor("ColorSelectedFore"));
        userDefined_nameField = new JTextField(10);
        userDefined_nameField.setForeground(sets.getColor("ColorSelectedFore"));
        userDefined_nameField.setBackground(sets.getColor("ColorSelectedInsideBack"));
        if(usingFont != null)
        {
            userDefined_nameLabel.setFont(usingFont);
            userDefined_nameField.setFont(usingFont);
        }
        userDefined_namePanel.add(userDefined_nameLabel);
        userDefined_namePanel.add(userDefined_nameField);
        
        userDefined_centerPanel.setLayout(new BorderLayout());
        userDefined_tab = new JTabbedPane();
        userDefined_tab.setBackground(sets.getColor("ColorSelectedBack"));
        userDefined_tab.setForeground(sets.getColor("ColorSelectedFore"));
        userDefined_centerPanel.add(userDefined_tab, BorderLayout.CENTER);
        userDefined_area = new JTextArea();
        userDefined_area.setBackground(sets.getColor("ColorSelectedInsideBack"));
        userDefined_area.setForeground(sets.getColor("ColorSelectedFore"));
        userDefined_area.setLineWrap(true);
        userDefined_area.setText(userDefined_first());
        if(usingFont != null)
        {
            userDefined_area.setFont(usingFont);
            userDefined_tab.setFont(usingFont);
        }
        userDefined_scroll = new JScrollPane(userDefined_area);
        userDefined_tab.add(sets.trans("Player"), userDefined_scroll);
        
        userDefined_downPanel.setLayout(new FlowLayout());
        bt_start_userDefined = new JButton(sets.trans("Start"));
        bt_close_userDefined = new JButton(sets.trans("Close"));
        bt_start_userDefined.addActionListener(this);
        bt_close_userDefined.addActionListener(this);
        bt_start_userDefined.setForeground(sets.getColor("ColorSelectedFore"));
        bt_close_userDefined.setForeground(sets.getColor("ColorSelectedFore"));
        if(usingFont != null)
        {
            bt_start_userDefined.setFont(usingFont);
            bt_close_userDefined.setFont(usingFont);
        }
        if(sets.getColor("ColorSelectedButton") != null)
        {
            bt_start_userDefined.setBackground(sets.getColor("ColorSelectedButton"));
            bt_close_userDefined.setBackground(sets.getColor("ColorSelectedButton"));
        }
        userDefined_downPanel.add(bt_start_userDefined);
        userDefined_downPanel.add(bt_close_userDefined);        
            
        needfileDialog = new JDialog(startDialog, true);
        needfileDialog.setUndecorated(true);
        needfileDialog.setSize(620, 420);
        needfileDialog.setLocation((int)(sets.getScreenSize().getWidth()/2 - needfileDialog.getWidth()/2), (int)(sets.getScreenSize().getHeight()/2 - needfileDialog.getHeight()/2));
        needfileDialog.getContentPane().setLayout(new BorderLayout());
        needfile_mainPanel = new JPanel();
        needfileDialog.getContentPane().add(needfile_mainPanel, BorderLayout.CENTER);
        needfile_mainPanel.setLayout(new BorderLayout());
        needfile_mainPanel.setBorder(new EtchedBorder());
        needfile_mainPanel.setBackground(sets.getColor("ColorSelectedBack"));
        needfile_upPanel = new JPanel();
        needfile_centerPanel = new JPanel();
        needfile_downPanel = new JPanel();
        needfile_mainPanel.add(needfile_upPanel, BorderLayout.NORTH);
        needfile_mainPanel.add(needfile_centerPanel, BorderLayout.CENTER);
        needfile_mainPanel.add(needfile_downPanel, BorderLayout.SOUTH);
        needfile_upPanel.setBackground(sets.getColor("ColorSelectedBack"));
        needfile_centerPanel.setBackground(sets.getColor("ColorSelectedBack"));
        needfile_downPanel.setBackground(sets.getColor("ColorSelectedBack"));
        needfile_centerPanel.setLayout(new BorderLayout());
        needfile_split = new JSplitPane();
        needfile_split.setBackground(sets.getColor("ColorSelectedBack"));
        needfile_split.setForeground(sets.getColor("ColorSelectedFore"));
        needfile_centerPanel.add(needfile_split);
        needfile_fileList = new JTextArea();
        needfile_fileList.setBackground(sets.getColor("ColorSelectedInsideBack"));
        needfile_fileList.setForeground(sets.getColor("ColorSelectedFore"));
        needfile_fileList.setEditable(false);
        needfile_fileList.setLineWrap(true);
        needfile_fileScroll = new JScrollPane(needfile_fileList);
        needfile_split.setRightComponent(needfile_fileScroll);        
        needfile_descList = new JTextArea();
        needfile_descList.setBackground(sets.getColor("ColorSelectedInsideBack"));
        needfile_descList.setForeground(sets.getColor("ColorSelectedFore"));
        needfile_descList.setEditable(false);
        needfile_descList.setLineWrap(true);
        needfile_descScroll = new JScrollPane(needfile_descList);
        needfile_split.setLeftComponent(needfile_descScroll);        
        needfile_downPanel.setLayout(new FlowLayout());
        bt_close_needfile = new JButton(sets.trans("Close"));
        bt_close_needfile.setForeground(sets.getColor("ColorSelectedFore"));
        if(sets.getColor("ColorSelectedButton") != null) bt_close_needfile.setBackground(sets.getColor("ColorSelectedButton"));
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
        needfile_titlePanel.setBackground(sets.getColor("ColorSelectedInsideBack"));
        needfile_titlePanel.setLayout(new FlowLayout());
        needfile_titleLabel = new JLabel();
        needfile_titleLabel.setText(sets.trans("Help (Asset file names)"));
        needfile_titleLabel.setForeground(sets.getColor("ColorSelectedFore"));
        if(usingFont != null) needfile_titleLabel.setFont(usingFont);
        needfile_titlePanel.add(needfile_titleLabel);
        
        autoUserDefinedDialog = new JDialog(startDialog, true);
        autoUserDefinedDialog.setUndecorated(true);
        autoUserDefinedDialog.setSize(500, 400);
        autoUserDefinedDialog.setLocation((int)(sets.getScreenSize().getWidth()/2 - autoUserDefinedDialog.getWidth()/2), (int)(sets.getScreenSize().getHeight()/2 - autoUserDefinedDialog.getHeight()/2));
        autoUserDefinedDialog.getContentPane().setLayout(new BorderLayout());
        autoUserDefined_mainPanel = new JPanel();
        autoUserDefined_mainPanel.setBackground(sets.getColor("ColorSelectedBack"));
        autoUserDefinedDialog.getContentPane().add(autoUserDefined_mainPanel, BorderLayout.CENTER);
        autoUserDefined_mainPanel.setLayout(new BorderLayout());
        autoUserDefined_mainPanel.setBorder(new EtchedBorder());
        autoUserDefined_upPanel = new JPanel();
        autoUserDefined_downPanel = new JPanel();
        autoUserDefined_centerPanel = new JPanel();
        autoUserDefined_upPanel.setBackground(sets.getColor("ColorSelectedBack"));
        autoUserDefined_centerPanel.setBackground(sets.getColor("ColorSelectedBack"));
        autoUserDefined_downPanel.setBackground(sets.getColor("ColorSelectedBack"));
        autoUserDefined_mainPanel.add(autoUserDefined_upPanel, BorderLayout.NORTH);
        autoUserDefined_mainPanel.add(autoUserDefined_centerPanel, BorderLayout.CENTER);
        autoUserDefined_mainPanel.add(autoUserDefined_downPanel, BorderLayout.SOUTH);
        autoUserDefined_upPanel.setLayout(new BorderLayout());
        autoUserDefined_titlePanel = new JPanel();
        autoUserDefined_upPanel.add(autoUserDefined_titlePanel, BorderLayout.CENTER);
        autoUserDefined_titlePanel.setLayout(new FlowLayout());
        autoUserDefined_titlePanel.setBackground(sets.getColor("ColorSelectedInsideBack"));
        autoUserDefined_titlePanel.addMouseListener(this);
        autoUserDefined_titlePanel.addMouseMotionListener(this);
        autoUserDefined_titleLabel = new JLabel(sets.trans("Summary"));
        autoUserDefined_titleLabel.setForeground(sets.getColor("ColorSelectedFore"));
        if(usingFont != null)
        {
            autoUserDefined_titleLabel.setFont(usingFont);
        }
        autoUserDefined_titlePanel.add(autoUserDefined_titleLabel);
        autoUserDefined_downPanel.setLayout(new FlowLayout());
        bt_closeAutoUserDefined = new JButton(sets.trans("Close"));
        bt_acceptAutoUserDefined = new JButton(sets.trans("Accept"));
        bt_closeAutoUserDefined.addActionListener(this);
        bt_acceptAutoUserDefined.addActionListener(this);
        bt_closeAutoUserDefined.setForeground(sets.getColor("ColorSelectedFore"));
        bt_acceptAutoUserDefined.setForeground(sets.getColor("ColorSelectedFore"));
        if(usingFont != null)
        {
            bt_closeAutoUserDefined.setFont(usingFont);
            bt_acceptAutoUserDefined.setFont(usingFont);
        }
        if(sets.getColor("ColorSselectedButton") != null)
        {
            bt_closeAutoUserDefined.setBackground(sets.getColor("ColorSelectedButton"));
            bt_acceptAutoUserDefined.setBackground(sets.getColor("ColorSelectedButton"));
        }
        autoUserDefined_downPanel.add(bt_acceptAutoUserDefined);
        autoUserDefined_downPanel.add(bt_closeAutoUserDefined);        
        autoUserDefined_centerPanel.setLayout(new BorderLayout());
        autoUserDefined_contentPanel = new JPanel();
        autoUserDefined_contentPanel.setBackground(sets.getColor("ColorSelectedBack"));
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
            autoUserDefined_contents[i].setBackground(sets.getColor("ColorSelectedBack"));
            autoUserDefined_contents[i].setLayout(newFlowLayout);
            autoUserDefined_labels[i] = new JLabel();
            autoUserDefined_fields[i] = new JTextField(10);
            autoUserDefined_labels[i].setForeground(sets.getColor("ColorSelectedFore"));
            autoUserDefined_fields[i].setForeground(sets.getColor("ColorSelectedFore"));
            autoUserDefined_fields[i].setBackground(sets.getColor("ColorSelectedInsideBack"));
            if(usingFont != null)
            {
                autoUserDefined_labels[i].setFont(usingFont);
                autoUserDefined_fields[i].setFont(usingFont);
            }
            autoUserDefined_contents[i].add(autoUserDefined_fields[i]);
            autoUserDefined_contents[i].add(autoUserDefined_labels[i]);
            
            autoUserDefined_contentPanel.add(autoUserDefined_contents[i]);
        }
        autoUserDefined_labels[AUTO_LAST].setText(sets.trans("Authority"));        
        
        autoUserDefined_labels[AUTO_SHIPNAME].setText(sets.trans("Ship Name"));
        autoUserDefined_labels[AUTO_SHIP_HP].setText(sets.trans("Ship HP"));
        autoUserDefined_labels[AUTO_SHIP_SHAPE].setText(sets.trans("Shape") + " (rectangle, circle)");
        autoUserDefined_labels[AUTO_SHIP_ENERGY].setText(sets.trans("Energy"));
        autoUserDefined_labels[AUTO_SHIP_SIZE].setText(sets.trans("Size"));
        autoUserDefined_labels[AUTO_SHIP_SPEED].setText(sets.trans("Speed"));
        autoUserDefined_labels[AUTO_WEAPON_1_DAMAGE].setText(sets.trans("Weapon") + " 1 " + sets.trans("Damage"));
        autoUserDefined_labels[AUTO_WEAPON_1_DELAY].setText(sets.trans("Weapon") + " 1 " + sets.trans("Cooling Time"));
        autoUserDefined_labels[AUTO_WEAPON_1_HP].setText(sets.trans("Weapon") + " 1 " + sets.trans("Range") + " (" + sets.trans("Guide") + ")");
        autoUserDefined_labels[AUTO_WEAPON_1_INTERVAL].setText(sets.trans("Weapon") + " 1 " + sets.trans("Interval"));
        autoUserDefined_labels[AUTO_WEAPON_1_MAX].setText(sets.trans("Weapon") + " 1 " + sets.trans("Max"));
        autoUserDefined_labels[AUTO_WEAPON_1_MIN].setText(sets.trans("Weapon") + " 1 " + sets.trans("Minimum"));
        autoUserDefined_labels[AUTO_WEAPON_1_NEEDS].setText(sets.trans("Weapon") + " 1 " + sets.trans("Energy Cunsume"));
        autoUserDefined_labels[AUTO_WEAPON_1_SIZE].setText(sets.trans("Weapon") + " 1 " + sets.trans("Missile Size"));
        autoUserDefined_labels[AUTO_WEAPON_1_HELPER_TYPE].setText(sets.trans("Weapon") + " 1 " + sets.trans("Interceptor Type"));
        autoUserDefined_labels[AUTO_WEAPON_1_HELPER_COUNT].setText(sets.trans("Weapon") + " 1 " + sets.trans("Interceptor Count"));
        autoUserDefined_labels[AUTO_WEAPON_1_SPEED].setText(sets.trans("Weapon") + " 1 " + sets.trans("Missile Speed"));
        autoUserDefined_labels[AUTO_WEAPON_1_TYPE].setText(sets.trans("Weapon") + " 1 " + sets.trans("Missile Type (normal, super, guide, reflex, reflex_perfect, beam, helper)"));
        autoUserDefined_labels[AUTO_WEAPON_2_DAMAGE].setText(sets.trans("Weapon") + " 2 " + sets.trans("Damage"));
        autoUserDefined_labels[AUTO_WEAPON_2_DELAY].setText(sets.trans("Weapon") + " 2 " + sets.trans("Cooling Time"));
        autoUserDefined_labels[AUTO_WEAPON_2_HP].setText(sets.trans("Weapon") + " 2 " + sets.trans("Range") + " (" + sets.trans("Guide") + ")");
        autoUserDefined_labels[AUTO_WEAPON_2_INTERVAL].setText(sets.trans("Weapon") + " 2 " + sets.trans("Interval"));
        autoUserDefined_labels[AUTO_WEAPON_2_MAX].setText(sets.trans("Weapon") + " 2 " + sets.trans("Max"));
        autoUserDefined_labels[AUTO_WEAPON_2_MIN].setText(sets.trans("Weapon") + " 2 " + sets.trans("Minimum"));
        autoUserDefined_labels[AUTO_WEAPON_2_NEEDS].setText(sets.trans("Weapon") + " 2 " + sets.trans("Energy Cunsume"));
        autoUserDefined_labels[AUTO_WEAPON_2_SIZE].setText(sets.trans("Weapon") + " 2 " + sets.trans("Missile Size"));
        autoUserDefined_labels[AUTO_WEAPON_2_HELPER_TYPE].setText(sets.trans("Weapon") + " 2 " + sets.trans("Interceptor Type"));
        autoUserDefined_labels[AUTO_WEAPON_2_HELPER_COUNT].setText(sets.trans("Weapon") + " 2 " + sets.trans("Interceptor Count"));
        autoUserDefined_labels[AUTO_WEAPON_2_SPEED].setText(sets.trans("Weapon") + " 2 " + sets.trans("Missile Speed"));
        autoUserDefined_labels[AUTO_WEAPON_2_TYPE].setText(sets.trans("Weapon") + " 2 " + sets.trans("Missile Type (normal, super, guide, reflex, reflex_perfect, beam, helper)"));
        autoUserDefined_labels[AUTO_WEAPON_3_DAMAGE].setText(sets.trans("Weapon") + " 3 " + sets.trans("Damage"));
        autoUserDefined_labels[AUTO_WEAPON_3_DELAY].setText(sets.trans("Weapon") + " 3 " + sets.trans("Cooling Time"));
        autoUserDefined_labels[AUTO_WEAPON_3_HP].setText(sets.trans("Weapon") + " 3 " + sets.trans("Range") + " (" + sets.trans("Guide") + ")");
        autoUserDefined_labels[AUTO_WEAPON_3_INTERVAL].setText(sets.trans("Weapon") + " 3 " + sets.trans("Interval"));
        autoUserDefined_labels[AUTO_WEAPON_3_MAX].setText(sets.trans("Weapon") + " 3 " + sets.trans("Max"));
        autoUserDefined_labels[AUTO_WEAPON_3_MIN].setText(sets.trans("Weapon") + " 3 " + sets.trans("Minimum"));
        autoUserDefined_labels[AUTO_WEAPON_3_NEEDS].setText(sets.trans("Weapon") + " 3 " + sets.trans("Energy Cunsume"));
        autoUserDefined_labels[AUTO_WEAPON_3_SIZE].setText(sets.trans("Weapon") + " 3 " + sets.trans("Missile Size"));
        autoUserDefined_labels[AUTO_WEAPON_3_HELPER_TYPE].setText(sets.trans("Weapon") + " 3 " + sets.trans("Interceptor Type"));
        autoUserDefined_labels[AUTO_WEAPON_3_HELPER_COUNT].setText(sets.trans("Weapon") + " 3 " + sets.trans("Interceptor Count"));
        autoUserDefined_labels[AUTO_WEAPON_3_SPEED].setText(sets.trans("Weapon") + " 3 " + sets.trans("Missile Speed"));
        autoUserDefined_labels[AUTO_WEAPON_3_TYPE].setText(sets.trans("Weapon") + " 3 " + sets.trans("Missile Type (normal, super, guide, reflex, reflex_perfect, beam, helper)"));
        
        tryApplyProperties();
        try
        {
            if(sets.getBool("SaveProperties"))
            {
                tryKeyToProperty(sets);
                trySaveProperties(sets, true);
            }
        }
        catch(Exception e)
        {
            if(sets.getBool("ErrorStackTraceConsole")) e.printStackTrace();
        }
        
        
        touchSetting();    
        frame_loaded = true;
        
        refreshTodayEvent();
        
        message();
        message(sets.trans("Reflexioner") + " " + getVersionString(true));
        message();
        message(sets.trans("Game Prepared"));
        
        if(useTransparent) refreshTransparency();
    }
    void tryApplyProperties()
    {
        try
        {            
            KEY_1     = sets.getInt("KEY_1");
            KEY_2     = sets.getInt("KEY_2");
            KEY_3     = sets.getInt("KEY_3");
            KEY_K     = sets.getInt("KEY_K");
            KEY_L     = sets.getInt("KEY_L");
            KEY_SHIFT = sets.getInt("KEY_SHIFT");
            KEY_SPACE = sets.getInt("KEY_SPACE");
            KEY_UP    = sets.getInt("KEY_UP");
            KEY_DOWN  = sets.getInt("KEY_DOWN");
            KEY_LEFT  = sets.getInt("KEY_LEFT");
            KEY_RIGHT = sets.getInt("KEY_RIGHT");        
            KEY_W     = sets.getInt("KEY_W");
            KEY_A     = sets.getInt("KEY_A");
            KEY_S     = sets.getInt("KEY_S");
            KEY_D     = sets.getInt("KEY_D");
            KEY_4     = sets.getInt("KEY_4");
            KEY_5     = sets.getInt("KEY_5");
            KEY_6     = sets.getInt("KEY_6");
            KEY_7     = sets.getInt("KEY_7");
            KEY_8     = sets.getInt("KEY_8");
            KEY_9     = sets.getInt("KEY_9");
            KEY_0     = sets.getInt("KEY_0");
        }
        catch(Exception e)
        {
            if(sets.getBool("ErrorStackTraceConsole")) e.printStackTrace();
        }        
    }
    public static void tryKeyToProperty(Setting sets)
    {
        KEY_1     = sets.getInt("KEY_1");
        KEY_2     = sets.getInt("KEY_2");
        KEY_3     = sets.getInt("KEY_3");
        KEY_K     = sets.getInt("KEY_K");
        KEY_L     = sets.getInt("KEY_L");
        KEY_SHIFT = sets.getInt("KEY_SHIFT");
        KEY_SPACE = sets.getInt("KEY_SPACE");
        KEY_UP    = sets.getInt("KEY_UP");
        KEY_DOWN  = sets.getInt("KEY_DOWN");
        KEY_LEFT  = sets.getInt("KEY_LEFT");
        KEY_RIGHT = sets.getInt("KEY_RIGHT");        
        KEY_W     = sets.getInt("KEY_W");
        KEY_A     = sets.getInt("KEY_A");
        KEY_S     = sets.getInt("KEY_S");
        KEY_D     = sets.getInt("KEY_D");
        KEY_4     = sets.getInt("KEY_4");
        KEY_5     = sets.getInt("KEY_5");
        KEY_6     = sets.getInt("KEY_6");
        KEY_7     = sets.getInt("KEY_7");
        KEY_8     = sets.getInt("KEY_8");
        KEY_9     = sets.getInt("KEY_9");
        KEY_0     = sets.getInt("KEY_0");
        
        
        sets.set("KEY_1"    , KEY_1    );
        sets.set("KEY_2"    , KEY_2    );
        sets.set("KEY_3"    , KEY_3    );
        sets.set("KEY_K"    , KEY_K    );
        sets.set("KEY_L"    , KEY_L    );
        sets.set("KEY_SHIFT", KEY_SHIFT);
        sets.set("KEY_SPACE", KEY_SPACE);
        sets.set("KEY_UP"   , KEY_UP   );
        sets.set("KEY_DOWN" , KEY_DOWN );
        sets.set("KEY_LEFT" , KEY_LEFT );
        sets.set("KEY_RIGHT", KEY_RIGHT);
        sets.set("KEY_W"    , KEY_W    );     
        sets.set("KEY_A"    , KEY_A    );     
        sets.set("KEY_S"    , KEY_S    );     
        sets.set("KEY_D"    , KEY_D    );     
        sets.set("KEY_4"    , KEY_4    );     
        sets.set("KEY_5"    , KEY_5    );     
        sets.set("KEY_6"    , KEY_6    );     
        sets.set("KEY_7"    , KEY_7    );     
        sets.set("KEY_8"    , KEY_8    );     
        sets.set("KEY_9"    , KEY_9    );     
        sets.set("KEY_0"    , KEY_0    );     
    }
    public static void trySaveProperties(Setting sets, boolean details)
    {
        if(details)
        {
            tryKeyToProperty(sets);
        }
        
        sets.save(RunManager.getIndexClass());
    }
    public static void tryLoadProperties(Setting sets)
    {
        sets = Setting.load(RunManager.getIndexClass());
        tryKeyToProperty(sets);
    }
    public void refreshTransparency()
    {
        float value = transparent_opacity;
        float doubled = transparent_opacity + ((1.0f - transparent_opacity) * 0.5f);
        if(useTransparent)
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
        if(checker == null) checker = new CodeChecker(false, startDialog, sets);
        try_transparent(checker.getWindow(), doubled);
        try_transparent(needfileDialog, doubled);
    }
    private void refreshTodayEvent()
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
            
            
            areaText = areaText + sets.trans("Today's Game") + "\n\n";
            areaText = areaText + String.valueOf(year) + sets.trans("Year") + " " + String.valueOf(month) + sets.trans("Month") + " " + String.valueOf(day) + sets.trans("Day") + "\n\n";    
            areaText = areaText + sets.trans("Ship") + " : ";
            String shipName = SpaceShip.spaceShipNameList(sets, 3).get(original_index);
            areaText = areaText + shipName + "\n";
            
            areaText = areaText + sets.trans("Difficulty") + " : ";
            
            double difValue = 1;
            difValue = Math.pow(10.0, (today_difficulty));
            areaText = areaText + Difficulty.intToString(Math.round(difValue), 3.3) + "\n";
            
            areaText = areaText + sets.trans("Reinforcement at starts") + " : " + itemDesc + "\n\n";
            areaText = areaText + sets.trans("Caution : Double-point effect is work at the game finish. This effect is not saved in state, and replay.");
            
            start_today_area.setText(areaText);
            
            success = true;                    
        } 
        catch (Exception e)
        {
            if(sets.getBool("ErrorStackTraceConsole")) e.printStackTrace();
            startItem = null;
            success = false;
        }
        
        bt_start_today.setEnabled(success);
    }
    public void inputScenario(ReflexScenario scen)
    {
        scenarios.add(scen);
        refreshScenario(false);
        mainTab.setSelectedComponent(start_scenarioPanel);
    }
    public void playScenario(ReflexScenario scen)
    {
        replay_save = menu_manage_saveReplay.isSelected();
        start(scen, scen.getSpaceShip());
    }
    @SuppressWarnings("unchecked")
	private void refreshScenario(boolean delete_befores)
    {
        loadScenarios(delete_befores);
        Vector<String> listData = new Vector<String>();
        String[] listArray;
        String adds = "";
        
        for(int i=0; i<scenarios.size(); i++)
        {
            adds = scenarios.get(i).getName();
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
    private void loadScenarios(boolean delete_befores)
    {
        if(delete_befores) scenarios.clear();        
        
        SortedSet<ReflexScenario> dups = new TreeSet<ReflexScenario>();
        dups.addAll(ReflexScenarioSetter.standards());
        if(scenarios.size() >= 1) dups.addAll(scenarios);
        scenarios.clear();
        
        scenarios.addAll(dups);

        File loads = null;
        File[] lists = null;
        BufferedReader bufferedReader = null;
        InputStreamReader inputReader = null;        
        InputStream inputStream = null;
        URL webTarget = null;
        String webList, readLines;
        try
        {
            loads = new File(sets.getDefaultPath() + File.separator + "scenarios");
            if(! loads.exists()) loads.mkdirs();
            if(loads.exists())
            {
                lists = loads.listFiles(new java.io.FileFilter() 
                {    
                    @Override
                    public boolean accept(File pathname) {
                        if(! pathname.exists()) return false;
                        if(pathname.isDirectory()) return false;
                        String name = pathname.getName().toLowerCase();
                        return name.endsWith(".rscn");
                    }
                });
                for(int i=0; i<lists.length; i++)
                {
                    loads = lists[i];
                    
                    String lines = "";
                    StringBuilder accum = new StringBuilder("");
                    try
                    {
                        inputStream = new FileInputStream(loads);
                        inputReader = new InputStreamReader(inputStream, "UTF-8");
                        bufferedReader = new BufferedReader(inputReader);
                        while(true)
                        {
                            lines = bufferedReader.readLine();
                            if(lines == null) break;
                            accum = accum.append(lines).append("\n");
                        }
                        bufferedReader.close(); bufferedReader = null;
                        inputReader.close();    inputReader    = null;
                        inputStream.close();    inputStream    = null;
                        ReflexScenario scen = new ReflexScenario(accum.toString().trim());
                        accum = null;
                        if(! scenarios.contains(scen)) scenarios.add(scen);
                    }
                    catch (Exception ex)
                    {
                        ex.printStackTrace();
                    }
                    finally
                    {
                        try { if(bufferedReader != null) bufferedReader.close(); bufferedReader = null; } catch(Exception exc) {}
                        try { if(inputReader    != null) inputReader.close();    inputReader    = null; } catch(Exception exc) {}
                        try { if(inputStream    != null) inputStream.close();    inputStream    = null; } catch(Exception exc) {}
                    }
                }
            }
            
            webList = "";
            try
            {
                webTarget      = new URL(sets.get("ServerURL1") + "reflex_scenlist.txt");
                message(sets.trans("Access") + " " + webTarget.toString());
                inputStream    = webTarget.openStream();
                inputReader    = new InputStreamReader(inputStream, "UTF-8");
                bufferedReader = new BufferedReader(inputReader);
                while(true)
                {
                    try
                    {
                        readLines = bufferedReader.readLine();
                        if(readLines == null) break;
                        webList = webList + readLines + "\n";    
                    } 
                    catch (Exception e)
                    {
                        if(sets.getBool("ErrorStackTraceConsole")) e.printStackTrace();
                    }
                }
            } 
            catch (Exception e)
            {
                if(sets.getBool("ErrorStackTraceConsole")) e.printStackTrace();
            }
            try { if(bufferedReader != null) bufferedReader.close(); bufferedReader = null; } catch(Exception exc) {}
            try { if(inputReader    != null) inputReader.close();    inputReader    = null; } catch(Exception exc) {}
            try { if(inputStream    != null) inputStream.close();    inputStream    = null; } catch(Exception exc) {}
            
            if(! webList.startsWith("<"))
            {
                StringTokenizer tokens = new StringTokenizer(webList, "\n");
                String url;
                
                while(tokens.hasMoreTokens())
                {
                    try
                    {
                        url = RunManager.r65279(tokens.nextToken().trim());
                        if(url.equals("")) continue;
                        message(sets.trans("Access") + " " + url);
                        
                        String lines = "";
                        StringBuilder accum = new StringBuilder("");
                        
                        inputStream    = new URL(url.toString()).openStream();
                        inputReader    = new InputStreamReader(inputStream, "UTF-8");
                        bufferedReader = new BufferedReader(inputReader);
                        while(true)
                        {
                            lines = bufferedReader.readLine();
                            if(lines == null) break;
                            accum = accum.append(lines).append("\n");
                        }
                        bufferedReader.close(); bufferedReader = null;
                        inputReader.close();    inputReader    = null;
                        inputStream.close();    inputStream    = null;
                        ReflexScenario scen = new ReflexScenario(accum.toString().trim());
                        accum = null;
                        if(! scenarios.contains(scen)) scenarios.add(scen);
                    }
                    catch(Exception e)
                    {
                        if(sets.getBool("ErrorStackTraceConsole")) e.printStackTrace();
                    }
                    finally
                    {
                        try { if(bufferedReader != null) bufferedReader.close(); bufferedReader = null; } catch(Exception exc) {}
                        try { if(inputReader    != null) inputReader.close();    inputReader    = null; } catch(Exception exc) {}
                        try { if(inputStream    != null) inputStream.close();    inputStream    = null; } catch(Exception exc) {}
                    }
                }
            }
        }
        catch(Exception e)
        {
            if(sets.getBool("ErrorStackTraceConsole")) e.printStackTrace();
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
        result = result + "speed||" + String.valueOf(Arena.getGspeed()) + "\n";
        result = result + "\n";
        result = result + "# Size" + "\n";
        result = result + "size||" + String.valueOf(Arena.getGspaceShipR()) + "\n";
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
            JOptionPane.showMessageDialog(startDialog, sets.trans("Error") + " : " + result);
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
            JOptionPane.showMessageDialog(startDialog, sets.trans("Error") + " : " + result);
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
        
        if(arena.getScenario() != null && (! arena.getScenario().equals(ReflexScenario.defaultScenario)) && (! Arena.isAutoControlMode()))
        {
            try
            {
                refreshScenario(false);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                StringBuilder report = new StringBuilder("");
                report = report.append(sets.trans("Result")).append("\n\n");
                
                report = report.append(sets.trans("Start")).append("\t").append(arena.getName()).append("\n");
                report = report.append(sets.trans("Point")).append("\t").append(arena.getPoint().toString()).append("\n");
                report = report.append(sets.trans("Eliminated enemies")).append("\t").append(arena.getCatchEnemy().toString()).append("\n");
                report = report.append(sets.trans("Taken items")).append("\t").append(arena.getCatchItem().toString()).append("\n");
                finish_layout.last(finish_centerPanel);
                
                finish_resultField.setText(arena.getPoint().toString());
                finish_catchEnemyField.setText(arena.getCatchEnemy().toString());
                finish_catchItemField.setText(arena.getCatchItem().toString());
                finish_nameField.setText(arena.getName());
                finish_authField.setText(arena.getAuth());
                finish_authField2.setText(arena.getAuth());
                String continue_label = "";
                boolean continue_available = true;
                
                if(arena.player_hp() >= 0)
                {
                    if(arena.player_hp() >= 1)
                    {
                        bt_saveState.setVisible(true);
                        continue_label = sets.trans("Continue");                        
                    }
                    else
                    {
                        bt_saveState.setVisible(false);
                        continue_label = sets.trans("Continue (without Points)");    
                        continue_label = continue_label + " (" + String.valueOf(arena.continue_lefts() + ")");
                        continue_available = (arena.continue_lefts() >= 1);
                    }
                }
                else
                {
                    bt_saveState.setVisible(false);
                    continue_label = sets.trans("Continue (without Points)");
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
        arena.setScenario(ReflexScenario.defaultScenario);
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
                scenarioEditor.setScenario(scenarios.get(start_scenarioList.getSelectedIndex()));
                scenarioEditor.open();
            } 
            catch (Exception e1)
            {
                JOptionPane.showMessageDialog(startDialog, sets.trans("Error") + " : " + e1.getMessage());
            }
        }
        else if(ob == bt_setUrl)
        {
            String urlDesc, url2Desc, urlGet, url2Get;
            
            urlDesc  = sets.trans("Input the URL to check notices and available assets.");
            url2Desc = sets.trans("Input the URL to check notices and available assets. (Secondary)");
            
            urlGet = JOptionPane.showInputDialog(startDialog, urlDesc);
            url2Get = JOptionPane.showInputDialog(startDialog, url2Desc);
            
            if(urlGet != null) download_url1 = urlGet;
            if(url2Get != null) download_url2 = url2Get;
            
            SwingUtilities.invokeLater(new Runnable()
            {
                @Override
                public void run()
                {
                    start_download_message.setText(sets.trans("Do you trust this web address?") + "\n" + download_url1 + "\n" + download_url2 + "\n\n"
                            + sets.trans("Files will be located at") + " " + sets.getDefaultPath() + "\n\n");
                    try
                    {
                        start_download_message.append(get_imagepack_license());
                    } 
                    catch (Exception e)
                    {
                        e.printStackTrace();
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
            updateNeedfiles();
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
            startFindbyTab();
            
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
                ReflexScenario gets;
                try
                {                    
                    gets = new ReflexScenario(start_userDefinedArea.getText());
                    if(gets.getSpaceShipSelectable() != null)
                    {
                        if(gets.getSpaceShipSelectable().booleanValue())
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
                catch (Exception e1)
                {
                    JOptionPane.showMessageDialog(startDialog, sets.trans("Error") + " : " + e1.getMessage());
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
                if(selected_scenario.getSpaceShipSelectable() != null)
                {
                    if(selected_scenario.getSpaceShipSelectable().booleanValue())
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
                JOptionPane.showMessageDialog(startDialog, sets.trans("Error") + " : null");
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
                alert(sets.trans("The code is copied to the clipboard."));
            } 
            catch (Exception e1)
            {
                if(sets.getBool("ErrorStackTraceConsole")) e1.printStackTrace();
                alert(sets.trans("Error") + " : " + e1.getMessage());
            }
        }
        else if(ob == bt_authCopy2)
        {
            try
            {
                Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(finish_authField2.getText()), null);
                alert(sets.trans("The code is copied to the clipboard."));
            } 
            catch (Exception e1)
            {
                if(sets.getBool("ErrorStackTraceConsole")) e1.printStackTrace();
                alert(sets.trans("Error") + " : " + e1.getMessage());
            }
        }
        else if(ob == menu_view_check)
        {
            if(checker == null) checker = new CodeChecker(false, startDialog, sets);
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
                    newDesktop.browse(new URI(sets.get("ServerURL1") + "arena.event_url"));                
                }
            } 
            catch (Exception e1)
            {
                e1.printStackTrace();
            }
        }
        else if(ob == bt_download)
        {
            tryDownloadAssets(download_url1, download_url2);
        }
        else if(ob == menu_manage_uninstall)
        {
            Uninstaller.uninstall(this, sets, true, true, false, true, startDialog);
        }
        else if(ob == menu_manage_setIntervalReplay)
        {
            String inputs, message;
            message = sets.trans("Input number to skip frames when saving replays.\nInput big number to make replay file smaller.\nToo big number occurs unnatural play.");
            inputs = JOptionPane.showInputDialog(startDialog, message, String.valueOf(replayInterval));
            try
            {
                replayInterval = Integer.parseInt(inputs);
            }
            catch(NumberFormatException e1)
            {
                JOptionPane.showMessageDialog(startDialog, sets.trans("Error") + " : " + e1.getMessage());
            }
        }
    }    
    private void updateNeedfiles()
    {
        List<String> needFileList, existFileList;
        needFileList = ImageCache.needFiles(sets, true);
        existFileList = new Vector<String>();
        try
        {
            File dir = new File(sets.getDefaultPath());
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
        if(sets.get("Language").equals("ko"))
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
    public void startFindbyTab()
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
                if(selected_scenario.getSpaceShipSelectable() != null)
                {
                    if(selected_scenario.getSpaceShipSelectable().booleanValue())
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
                JOptionPane.showMessageDialog(startDialog, sets.trans("Error") + " : null");
            }
        }
        else if(comp == start_userDefinedPanel)
        {
            if(start_userDefined_isScenario.isSelected()) 
            {
                ReflexScenario gets;
                try
                {
                    gets = new ReflexScenario(start_userDefinedArea.getText());
                    start(gets);
                } 
                catch (Exception e1)
                {
                    JOptionPane.showMessageDialog(startDialog, sets.trans("Error") + " : " + e1.getMessage());
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
                
        results = "# " + sets.trans("User-Made Ship script is created.") + "\n\n" + results;
        
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
            message(sets.trans("Access") + " " + RunManager.r65279(download_url1 + "reflex_asset_license.txt"));
            inputStream = new URL(RunManager.r65279(download_url1 + "reflex_asset_license.txt")).openStream();
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
                message(sets.trans("Access") + " " + download_url2 + "reflex_asset_license.txt");
                inputStream = new URL(RunManager.r65279(download_url2 + "reflex_asset_license.txt")).openStream();
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
        return sets.trans("License") + "\n" + RunManager.r65279(results);
    }
    private void tryDownloadAssets(String url, String url2)
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
            message(sets.trans("Access") + " " + url + "reflex_assetlist.txt");
            inputStream = new URL(RunManager.r65279(url + "reflex_assetlist.txt")).openStream();
        }
        catch(Exception e)
        {
            try { inputStream.close(); } catch(Exception e1) { }
            try
            {
                success_url = url2;
                message(sets.trans("Access") + " " + url2 + "reflex_assetlist.txt");
                inputStream = new URL(RunManager.r65279(url2 + "reflex_assetlist.txt")).openStream();
            } 
            catch (Exception e1)
            {
                if(sets.getBool("ErrorStackTraceConsole")) e1.printStackTrace();
                return;
            }
        }
        start_download_message.setText(sets.trans("Do you trust this web address?") + "\n" + sets.get("ServerURL1") + "\n" + sets.get("ServerURL2") + "\n\n" 
        + sets.trans("Left items") + "\n");
        try
        {
            start_download_message.append("\n" + get_imagepack_license() + "\n");
        }
        catch(Exception e)
        {
            if(sets.getBool("ErrorStackTraceConsole")) e.printStackTrace();
        }
        if(inputStream != null)
        {
            try 
            {
                inputReader = new InputStreamReader(inputStream);
                bufferedReader = new BufferedReader(inputReader);
                String lower;
                while(true)
                {
                    if(not_infinite_loop >= 1000) break;
                    else not_infinite_loop++;
                    try
                    {
                        reads = bufferedReader.readLine();
                        if(reads == null) break;
                        if(reads.startsWith("#")) continue;
                        lower = reads.toLowerCase();
                        if(lower.endsWith(".png") || lower.endsWith(".jpg") || lower.endsWith(".txt") || lower.endsWith(".wav") || lower.endsWith(".mp3"))
                        {
                            urlList.add(reads.trim());
                            start_download_message.append(reads + "\n");
                        }                    
                    }
                    catch(Exception e)
                    {
                        if(sets.getBool("ErrorStackTraceConsole")) e.printStackTrace();
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
        try { inputStream.close(); } catch(Exception e) { }
        
        String[] files = new String[urlList.size()];        
        
        for(int i=0; i<files.length; i++)
        {
            files[i] = urlList.get(i);
        }
        
        if(files.length <= 0) start_download_message.append(sets.trans("None") + "\n");
        else start_download_message.append(sets.trans("Download") + " " + sets.trans("Item") + "\n");
        
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
                if(! new File(sets.getDefaultPath()).exists())
                    new File(sets.getDefaultPath()).mkdir();
            }
            catch(Exception e)
            {
                if(sets.getBool("ErrorStackTraceConsole")) e.printStackTrace();
            }
            try
            {            
                download_url = null;
                //Downloader.download(sets, files[i]);
                download_target = new File(RunManager.r65279(sets.getDefaultPath() + File.separator + "assets"));
                if(! download_target.exists()) download_target.mkdirs();
                download_target = new File(RunManager.r65279(download_target.getAbsolutePath() + File.separator + files[i]));
                if(success_url.equals(url))
                {
                    download_url = new URL(RunManager.r65279(url + "assets/" + files[i].trim()));
                }
                else if(success_url.equals(url2))
                {
                    download_url = new URL(RunManager.r65279(url2 + "assets/" + files[i].trim()));                    
                }
                if(download_url != null)
                {                    
                    message(sets.trans("Access") + " " + download_url);
                    connection = (HttpURLConnection) download_url.openConnection();
                    inputStream = connection.getInputStream();   
                    bufferedStream = new BufferedInputStream(inputStream);
                    saveStream = new FileOutputStream(download_target);
                    saveBufferedStream = new BufferedOutputStream(saveStream);
                    while(true)
                    {
                        readInt = bufferedStream.read(readBytes);
                        if(readInt == -1) break;
                        saveBufferedStream.write(readBytes, 0, readInt);
                    }
                    try { if(saveBufferedStream != null) { saveBufferedStream.close(); } } catch(Exception ex) {}
                    try { if(saveStream         != null) { saveStream.close();         } } catch(Exception ex) {}
                    try { if(bufferedStream     != null) { bufferedStream.close();     } } catch(Exception ex) {}
                    try { if(inputStream        != null) { inputStream.close();        } } catch(Exception ex) {}
                    try { if(connection         != null) { connection.disconnect();    } } catch(Exception ex) {}
                    
                    start_download_message.append(RunManager.r65279(files[i]) + " " + sets.trans("Complete") + "\n");
                    progress_download.setValue((int) Math.round((i / files.length) * 990.0));
                }
            }
            catch(Exception e)
            {
                start_download_message.append(files[i] + " " + sets.trans("Error") + " : " + e.getMessage() + "\n");
                progress_download.setValue((int) Math.round((i / files.length) * 990.0));
                if(sets.getBool("ErrorStackTraceConsole")) e.printStackTrace();
            }        
            finally
            {
                try { if(saveBufferedStream != null) { saveBufferedStream.close(); } } catch(Exception ex) {}
                try { if(saveStream         != null) { saveStream.close();         } } catch(Exception ex) {}
                try { if(bufferedStream     != null) { bufferedStream.close();     } } catch(Exception ex) {}
                try { if(inputStream        != null) { inputStream.close();        } } catch(Exception ex) {}
                try { if(connection         != null) { connection.disconnect();    } } catch(Exception ex) {}
            }
        }
        //waitTime(1000);
        
        loadAllImage();
        progress_download.setValue(1000);
        start_download_message.append("\n" + sets.trans("Complete") + "\n");
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
        {}        
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
            if((sets.get("Language").equals("ko")) && (sets.get("OS").startsWith("Windows") || sets.get("OS").startsWith("windows") || sets.get("OS").startsWith("WINDOWS")))
                start_noticeArea.setPage(sets.get("ServerURL1") + "reflex_notice_kr.html");
            else
                start_noticeArea.setPage(sets.get("ServerURL1") + "reflex_notice.html");
        } 
        catch (Exception e)
        {
            try
            {
                if((sets.get("Language").equals("ko")) && (sets.get("OS").startsWith("Windows") || sets.get("OS").startsWith("windows") || sets.get("OS").startsWith("WINDOWS")))
                    start_noticeArea.setPage(sets.trans("ServerURL2") + "reflex_notice_kr.html");
                else
                    start_noticeArea.setPage(sets.trans("ServerURL2") + "reflex_notice.html");
            }
            catch(Exception e1)
            {
                start_noticeArea.setText(sets.trans(
                "Cannot read notice online.\n"
                + "\n"
                + "Visit http://hjow.github.io/Reflexioner/\n"
                + "to check newest version and notices."
                ));
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
        if(scenarioEditor != null && areflexScen_index >= 0) 
            scenarioEditor.setScenario(scenarios.get(areflexScen_index));
        
        loadAllImage();
        /*
        if(firstTime)
        {
            String[] options = new String[2];
            options[0] = sets.trans("Download");
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
            sets.set("VersionMain", "" + version_main);
            sets.set("VersionSub1", "" + version_sub_1);
            sets.set("VersionSub2", "" + version_sub_2);
            sets.save(RunManager.getIndexClass());
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
        // Object ob = e.getSource();
        
    }
    @Override
    public void mouseExited(MouseEvent e)
    {
        // Object ob = e.getSource();
        
    }
    @Override
    public void windowActivated(WindowEvent arg0)
    {
        
    }
    @Override
    public void windowClosed(WindowEvent arg0)
    {
        
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
        
    }
    @Override
    public void windowDeiconified(WindowEvent arg0)
    {
        
    }
    @Override
    public void windowIconified(WindowEvent arg0)
    {
        
    }
    @Override
    public void windowOpened(WindowEvent arg0)
    {
        
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
        if(sets.get("Language").equals("ko")) str = kor;
        
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
            fileChooser = new JFileChooser(new File(sets.getDefaultPath()));
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
            fileChooser_rp = new JFileChooser(new File(sets.getDefaultPath()));
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
            JOptionPane.showMessageDialog(finishDialog, sets.trans("Error") + " : " + e.getMessage());
            if(sets.getBool("ErrorStackTraceConsole")) e.printStackTrace();
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
            JOptionPane.showMessageDialog(finishDialog, sets.trans("Error") + " : " + e.getMessage());
            if(sets.getBool("ErrorStackTraceConsole")) e.printStackTrace();
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
                    JOptionPane.showMessageDialog(startDialog, sets.trans("Error") + " : " + sets.trans("Fail") + " " + sets.trans("Authority"));
                }
            }
            else
            {
                
            }
        } 
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(startDialog, sets.trans("Error") + " : " + e.getMessage());
            if(sets.getBool("ErrorStackTraceConsole")) e.printStackTrace();
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
                if(sets.get("Language").equals("ko"))
                    start_scenario_description.setText(scenarios.get(sels).getKoreanDescription());
                else
                    start_scenario_description.setText(scenarios.get(sels).getDescription());
                
                start_scenario_description.setText(start_scenario_description.getText() + "\n\n"
                        + sets.trans("Difficulty") + " : "
                        + Difficulty.intToString(scenarios.get(sels).getDifficulty().intValue(), 3.3));
                
                if(scenarios.get(sels).isAuthorized())
                {
                    start_scenario_description.setText(start_scenario_description.getText() + "\n\n" + sets.trans("Authority")
                            + " " + sets.trans("Accept"));
                }
                if(start_scenario_selectShipCombo != null)
                {
                    if(scenarios.get(sels).getSpaceShipSelectable() != null)
                    {
                        if(scenarios.get(sels).getSpaceShipSelectable().booleanValue())
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
