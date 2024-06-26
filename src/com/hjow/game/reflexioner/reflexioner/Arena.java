package com.hjow.game.reflexioner.reflexioner;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.lang.reflect.Constructor;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.Vector;
import java.util.zip.GZIPOutputStream;

import javax.imageio.ImageIO;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import com.hjow.game.reflexioner.mainClasses.RXUtils;
import com.hjow.game.reflexioner.mainClasses.RunManager;
import com.hjow.game.reflexioner.mainClasses.ThreadAccumulate;
import com.hjow.game.reflexioner.mainClasses.ThreadControl;
import com.hjow.game.reflexioner.pack.InstalledPack;
import com.hjow.game.reflexioner.pack.Pack;
import com.hjow.game.reflexioner.pack.SecuredDist;
import com.hjow.game.reflexioner.setting.Difficulty;
import com.hjow.game.reflexioner.setting.Lint;
import com.hjow.game.reflexioner.setting.Setting;

public class Arena extends JPanel implements KeyListener, ControllableShip
{
    private static final long serialVersionUID = 4261314730768659883L;
    
    private static int gspeed = 9, greactDelay = 37, gbossDelay = 5000, gdifficultyDelay = 5000, gbossBeamDelay = 400;
    private static int gspaceShipR = 50;
    private static int genemyR = 35;
    private static int gmaxEnemies = 30;
    private static int gfireDelay = 5;
    
    private double difficulty_mode = 1;
    private List<Enemy> enemies;
    private SpaceShip spaceShip;
    private List<Missile> missile;
    private ArenaThread thread;
    private RepaintThread thread2;
    private List<Boom> booms;
    private List<Item> items;
    private List<ReflexDecorate> decorates;
    private List<Enemy> ourEnemy;
    private List<Enemy> testEnemy;
    private Vector<String> messages;
    private Reflexioner sp;
    private boolean threadSwitch = false;
    private boolean show = false;
    private boolean hud = true;
    private boolean active = true;
    private boolean game_pause = false;
    private int k=0;
    private long difficulty = 0;
    private int message_delay = 50;
    private int game_xspeed = 1;
    private Boom newBoom;
    private long pause_left = 0;
    private int  simpleTuto = 0;
    private boolean autoFire = false;
    private boolean pause_printed = false;
    private transient int getKeyCode;
    private boolean boss_exist = false;
    private boolean unique_exist = false;
    private String name = "", authcode = "";
    private Setting sets;
    private long difficulty_delay = 5000;
    private long timeout = -1;
    private int decorationMax = 250;
    private BigInteger decorationPointUnit = new BigInteger("10000");
    
    private transient boolean authority_mode = false;
    private static transient boolean autoControl = false;
    public transient String event_url = null;
    transient boolean event_accepted = false;
    private transient BufferedImage backgroundImage = null;
    private transient ReflexScenario scenario = null;
    public transient BigInteger catchEnemies, catchItems;
    private transient long bossCount = 0;
    transient int player_type = 0;
    private transient UserDefinedShip userDefinedShip = null;
    private transient List<Missile> fired_missile;
    private transient String file_path = null;
    private transient long finish_count = -1;
    private transient int continue_left = 2;
    private static transient int swift_delay = 0;
    private transient int autoControlDelay = 0;
    private transient boolean todayEvent = false;
    private transient int[] startItems = null;
    private AReflexReplay replay;
    
    public Arena(Reflexioner sp, Setting sets)
    {
        super();
        this.sets = sets;
        this.sp = sp;
        this.addKeyListener(this);
        this.requestFocus();
        setFocusable(true);
        enemies = new Vector<Enemy>();
        missile = new Vector<Missile>();
        booms = new Vector<Boom>();
        items = new Vector<Item>();
        decorates = new Vector<ReflexDecorate>();
        ourEnemy = new Vector<Enemy>();
        testEnemy = new Vector<Enemy>();
        messages = new Vector<String>();
        spaceShip = new SpaceShip(enemies);
        this.setBorder(new EtchedBorder());
        this.addMouseListener(sp);
        this.addMouseMotionListener(sp);
        thread  = new ArenaThread(this);
        thread2 = new RepaintThread(this);
        catchEnemies = Lint.big(0);
        catchItems = Lint.big(0);
        file_path = sets.getDefaultPath();
        
        loadBackground();
    }
    public void setStartItem(int[] items)
    {
        startItems = items;
    }
    public void loadBackground()
    {
        loadBackground(sets.getDefaultPath());
    }
    public void setTodayEvent(boolean bools)
    {
        todayEvent = bools;
    }
    public int messageCount()
    {
        return messages.size();
    }
    public void addMessage(String msg)
    {
        messages.add(msg);
    }
    public void clearMessage()
    {
        messages.clear();
    }
    public String getMessage(int i)
    {
        return messages.get(i);
    }
    public void removeMessage(String s)
    {
        messages.remove(s);
    }
    public void removeMessage(int i)
    {
        messages.remove(i);
    }
    public void loadBackground(String path)
    {
        File w;
        
        if(Reflexioner.image_allow)
        {
            try
            {
                if(ImageCache.img_background != null)
                {
                    backgroundImage = ImageCache.img_background;
                }
                else
                {
                    w = new File(path + RunManager.r65279("background.png"));
                    if(w.exists())
                    {
                        backgroundImage = ImageIO.read(w);
                    }
                    else
                    {
                        w = new File(path + RunManager.r65279("﻿background.jpg"));
                        if(w.exists())
                        {
                            backgroundImage = ImageIO.read(w);
                        }
                        else
                        {
                            backgroundImage = null;
                        }
                    }
                }
            }
            catch(Exception e)
            {
                backgroundImage = null;
            }
        }
    }
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if(backgroundImage != null && Reflexioner.image_allow)
        {
            g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), null);
        }
    }
    void loadAllImage()
    {
        if(spaceShip != null) spaceShip.loadImages();
        loadBackground();
    }
    public static boolean swift_available()
    {
        return (swift_delay <= 0);
    }
    public static void setSwiftDelay(int d)
    {
        swift_delay = d;
    }
    public boolean isAuthMode()
    {
        return authority_mode;
    }
    public void flushReplay()
    {
        replay = new BReflexReplay();
        replay.setReplay_interval(new Integer(Reflexioner.replayInterval));
        System.gc();
    }
    public void addEnemy(Enemy enemy)
    {
        if(enemy != null) enemies.add(enemy);
    }
    public void addEnemy(String type)
    {
        Enemy e = newEnemy(type);
        if(e != null)
            enemies.add(e);
    }
    
    public Enemy newEnemy(String type)
    {
        if(active)
        {
            if(type.equalsIgnoreCase("normal"))
            {
                Enemy newEnemy = new Enemy(file_path);
                newEnemy.setX((int) (Math.random() * Arena.maxWidth()));
                newEnemy.setHp(newEnemy.getHp() + (int) (difficulty / 2000));
                newEnemy.setMax_hp(newEnemy.getHp());
                newEnemy.setMax_energy(200 - (int)(difficulty / 1000.0));                                        
                newEnemy.setDamage(50 + (difficulty / 100));
                if(newEnemy.getMax_energy() < 100) newEnemy.setMax_energy(100);
                if(difficulty_mode >= 3.0) newEnemy.setMax_energy(newEnemy.getMax_energy() / 2);
                if(difficulty_mode >= 4.0) newEnemy.setMax_energy(newEnemy.getMax_energy() / 2);
                
                return newEnemy;
                //enemies.add(newEnemy);
            }
            else if(type.equalsIgnoreCase("sealed"))
            {
                Enemy newEnemy = new SealedEnemy(file_path);
                newEnemy.setX((int) (Math.random() * Arena.maxWidth()));
                newEnemy.setHp(newEnemy.getHp() + (int) (difficulty / 2000));
                newEnemy.setMax_hp(newEnemy.getHp());
                newEnemy.setMax_energy(200 - (int)(difficulty / 1000.0));                                        
                newEnemy.setDamage(50 + (difficulty / 100));
                ((SealedEnemy) newEnemy).setSeal_weakness(50 - (int)(difficulty / 1000000.0));
                if(((SealedEnemy) newEnemy).getSeal_weakness() < 20) ((SealedEnemy) newEnemy).setSeal_weakness(20); 
                if(newEnemy.getMax_energy() < 100) newEnemy.setMax_energy(100);
                if(difficulty_mode >= 3.0) newEnemy.setMax_energy(newEnemy.getMax_energy() / 2);
                if(difficulty_mode >= 4.0) newEnemy.setMax_energy(newEnemy.getMax_energy() / 2);
                
                return newEnemy;
                //enemies.add(newEnemy);
            }
            else if(type.equalsIgnoreCase("guide"))
            {
                BigEnemy newBigEnemy = new BigEnemy(file_path);
                newBigEnemy.setGuide(true);
                newBigEnemy.setX((int) (Math.random() * Arena.maxWidth()));
                newBigEnemy.setHp((newBigEnemy.getHp() + (int) (difficulty / 2000)) * 2);
                newBigEnemy.setMax_hp(newBigEnemy.getHp());
                newBigEnemy.setMax_energy(200 - (int)(difficulty / 1000));
                newBigEnemy.setDamage(50 + (difficulty / 100));
                if(newBigEnemy.getMax_energy() < 100) newBigEnemy.setMax_energy(100);
                if(difficulty_mode >= 3.0) newBigEnemy.setMax_energy(newBigEnemy.getMax_energy() / 2);
                if(difficulty_mode >= 4.0) newBigEnemy.setMax_energy(newBigEnemy.getMax_energy() / 2);
                return newBigEnemy;
                //enemies.add(newBigEnemy);
            }
            else if(type.equalsIgnoreCase("big"))
            {
                BigEnemy newBigEnemy = new BigEnemy(file_path);
                newBigEnemy.setGuide(false);
                newBigEnemy.setMissiles(3);
                newBigEnemy.setX((int) (Math.random() * Arena.maxWidth()));
                newBigEnemy.setHp((newBigEnemy.getHp() + (int) (difficulty / 2000)) * 2);
                newBigEnemy.setMax_hp(newBigEnemy.getHp());
                newBigEnemy.setMax_energy(200 - (int)(difficulty / 1000));
                newBigEnemy.setDamage(50 + (difficulty / 100));
                if(newBigEnemy.getMax_energy() < 100) newBigEnemy.setMax_energy(100);
                if(difficulty_mode >= 3.0) newBigEnemy.setMax_energy(newBigEnemy.getMax_energy() / 2);
                if(difficulty_mode >= 4.0) newBigEnemy.setMax_energy(newBigEnemy.getMax_energy() / 2);
                return newBigEnemy;
                //enemies.add(newBigEnemy);
            }
            else if(type.equalsIgnoreCase("boss"))
            {
                return Boss.getNewInstance(file_path, difficulty, false);
            }
            else return null;
        }
        return null;
    }
    public String getFile_path() {
        return file_path;
    }
    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }
    public void setScenario(ReflexScenario scen)
    {
        this.scenario = scen;
    }
    public ReflexScenario getScenario()
    {
        return scenario;
    }
    public void deleteScenario()
    {
        scenario = null;
    }
    public void prepareTest()
    {
        /*
        System.out.println("Test Unit");
        String formula;
        Enemy newEnemy = new HyperBoss(file_path);
        //newEnemy = new Boss(file_path);
        newEnemy.setX((int) (Math.random() * Reflexioner.size_x));
        newEnemy.setHp(newEnemy.getHp() + (int) (difficulty / 2000));
        newEnemy.setMax_hp(newEnemy.getHp());
        newEnemy.setMax_energy(200 - (int)(difficulty / 1000.0));                                        
        // newEnemy.setDamage(50 + (difficulty / 100));
        newEnemy.setDamage(1);
        if(newEnemy.getMax_energy() < 100) newEnemy.setMax_energy(100);
        if(difficulty_mode >= 3.0) newEnemy.setMax_energy(newEnemy.getMax_energy() / 2);
        if(difficulty_mode >= 4.0) newEnemy.setMax_energy(newEnemy.getMax_energy() / 2);
                
        String[] formulas = new String[2];
        
        formula = "";                            
        formula = formula + "x2 default + 1.0 1.0" + "\n";
        formula = formula + "y2 default - 10.0 2.0" + "\n";
        formula = formula + "y2 default + size_x 1.0" + "\n";
        formula = formula + "y2 constant - 0.5*size_x 2.0" + "\n";
        formula = formula + "y2 constant + size_y 1.0" + "\n";
        formula = formula + "y1 constant + 0.0 1.0" + "\n";
        formula = formula + "x1 constant + 0.5*size_x 1.0" + "\n";
        formula = formula + "x2 constant + 0 1.0" + "\n";
        formulas[0] = new String(formula);
        formula = "";
        formula = formula + "x2 constant + size_x 1.0" + "\n";
        formula = formula + "x2 default - 1.0 1.0" + "\n";
        formula = formula + "y2 default - 10.0 2.0" + "\n";
        formula = formula + "y2 default + size_x 1.0" + "\n";
        formula = formula + "y2 constant - 0.5*size_x 2.0" + "\n";
        formula = formula + "y2 constant + size_y 1.0" + "\n";
        formula = formula + "y1 constant + 0.0 1.0" + "\n";
        formula = formula + "x1 constant + 0.5*size_x 1.0" + "\n";
        formula = formula + "x2 constant + 0 1.0" + "\n";
        formulas[1] = new String(formula);
        
        ((HyperBoss) newEnemy).setFormula(formulas);
        
        testEnemy.add(newEnemy);
        
        Raser raser = new Raser(null, 1, "");
        raser.setHp(1000);
        raser.setDy(10);
        raser.setDamage(800 + (difficulty / 200));
        raser.setColor(Reflexioner.color_enemy_missile);
        raser.setLaunched(true);
        formula = "";                            
        formula = formula + "x2 constant - 0.5*range 1.0" + "\n";
        formula = formula + "x2 default + 1.0 1.0" + "\n";
        formula = formula + "y2 constant + range 1.0" + "\n";
        formula = formula + "y1 constant + 0.0 1.0" + "\n";
        formula = formula + "x1 constant + 0.5*range 1.0" + "\n";
        formula = formula + "x2 constant + 0 1.0" + "\n";
        raser.setDy(100);
        raser.setFormula(formula);
        raser.setMax_progress(1000);
        System.out.println("Raser Shoot");
        missile.add(raser);
        
        spaceShip.setMax_hp(5000);
        spaceShip.setHp_heal(50);
        spaceShip.setHp(5000);
        */
        /*
        Enemy newEnemy;
        newEnemy = new SealedQuickBoss(file_path);
        newEnemy.setX((int) (Math.random() * Arena.maxWidth()));
        newEnemy.setHp(newEnemy.getHp() + (int) (difficulty / 2000));
        newEnemy.setMax_hp(newEnemy.getHp());
        newEnemy.setMax_energy(200 - (int)(difficulty / 1000.0));
        newEnemy.setDamage(1);
        if(newEnemy.getMax_energy() < 100) newEnemy.setMax_energy(100);
        if(difficulty_mode >= 3.0) newEnemy.setMax_energy(newEnemy.getMax_energy() / 2);
        if(difficulty_mode >= 4.0) newEnemy.setMax_energy(newEnemy.getMax_energy() / 2);
        enemies.add(newEnemy);
        */
    }
    public String setUserDefinedShip(String commands)
    {
        try
        {
            if(commands.equalsIgnoreCase(new SpaceShip().getKeyName()))
            {
                userDefinedShip = null;
                player_type = SpaceShip.FLEX;
            }
            else if(commands.equalsIgnoreCase(new Cruiser().getKeyName()))
            {
                userDefinedShip = null;
                player_type = SpaceShip.BERSERK;
            }
            else if(commands.equalsIgnoreCase(new Clipper().getKeyName()))
            {
                userDefinedShip = null;
                player_type = SpaceShip.CLIPPER;
            }
            else if(commands.equalsIgnoreCase(new Warship().getKeyName()))
            {
                userDefinedShip = null;
                player_type = SpaceShip.WARSHIP;
            }
            else if(commands.equalsIgnoreCase(new Chaser().getKeyName()))
            {
                userDefinedShip = null;
                player_type = SpaceShip.CHASER;
            }
            else if(commands.equalsIgnoreCase(new Carrier().getKeyName()))
            {
                userDefinedShip = null;
                player_type = SpaceShip.CARRIER;
            }
            else if(commands.equalsIgnoreCase(new Satellite().getKeyName()))
            {
                userDefinedShip = null;
                player_type = SpaceShip.SATELLITE;
            }
            else
            {
                boolean find = false;
                
                List<Pack> packs = InstalledPack.getPacks();
                for(Pack p : packs)
                {
                    List<SpaceShip> pships = p.getSpaceShip();
                    for(SpaceShip pship : pships)
                    {
                        if(commands.equalsIgnoreCase(pship.getKeyName()))
                        {
                            find = true;
                            userDefinedShip = null;
                            player_type = pship.getKeyInt();
                            break;
                        }
                    }
                }
                
                if(! find) userDefinedShip = new AUserDefinedShip(commands, enemies);
            }
            return "success";
        } 
        catch (Exception e)
        {
            e.printStackTrace();
            sp.message(sets.trans("Error") + " - (" + e.getClass().getSimpleName() + ") " + e.getMessage());
            return e.getMessage();
        }        
    }
    public void deleteUserDefinedShip()
    {
        userDefinedShip = null;
    }
    public BigInteger getCatchItem()
    {
        return catchItems;
    }
    public BigInteger getCatchEnemy()
    {
        return catchEnemies;
    }
    public long getBossCount() {
        return bossCount;
    }
    public void setBossCount(long bossCount) {
        this.bossCount = bossCount;
    }
    public boolean isAutoFire() {
        return autoFire;
    }
    public void setAutoFire(boolean autoFire) {
        this.autoFire = autoFire;
    }
    public BigInteger getPoint()
    {
        return spaceShip.getPoint();
    }
    public boolean ready()
    {
        return threadSwitch;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public String getName()
    {
        return name;
    }
    public String getAuth()
    {
        return authcode;
    }
    public Setting getSetting()
    {
        return sets;
    }
    public synchronized void decreaseFinishCount()
    {
        if(finish_count >= 0)
            finish_count--;
    }
    public void setDifficultyMode(double mode)
    {
        difficulty_mode = mode;
    }
    public double getDifficultyMode()
    {
        return difficulty_mode;
    }
    public List<Enemy> getTestEnemy() {
        return testEnemy;
    }
    public void setTestEnemy(List<Enemy> testEnemy) {
        this.testEnemy = testEnemy;
    }
    public void reset()
    {
        pause();
        enemies.clear();
        ourEnemy.clear();
        testEnemy.clear();
        missile.clear();
        booms.clear();
        items.clear();
        finish_count = -1;
        continue_left = 2;
        pause_left = 0;
        message_delay = 50;
        autoFire = false;
        replay = new BReflexReplay();
        replay.setReplay_interval(new Integer(Reflexioner.replayInterval));
        Reflexioner.replay_now_delay = 0;
        autoControlDelay = 0;
        simpleTuto = 80;
        show = true;
                
        prepareTest();
        if(userDefinedShip == null)
        {
            authority_mode = true;
            switch(player_type)
            {
                case SpaceShip.FLEX:
                    spaceShip = new SpaceShip(enemies);                        
                    break;
                case SpaceShip.BERSERK:
                    spaceShip = new Cruiser(enemies);    
                    break;
                case SpaceShip.CHASER:
                    spaceShip = new Chaser(enemies);
                    break;
                case SpaceShip.CLIPPER:
                    spaceShip = new Clipper(enemies);
                    break;
                case SpaceShip.WARSHIP:
                    spaceShip = new Warship(enemies);
                    break;
                case SpaceShip.CARRIER:
                    spaceShip = new Carrier(enemies);
                    break;
                case SpaceShip.SATELLITE:
                    spaceShip = new Satellite(enemies);
                    break;
                default:
                    boolean find = false;
                    
                    List<Pack> packs = InstalledPack.getPacks();
                    for(Pack p : packs)
                    {
                        List<SpaceShip> pships = p.getSpaceShip();
                        for(SpaceShip pship : pships)
                        {
                            if(player_type == pship.getKeyInt())
                            {
                                find = true;
                                try
                                {
                                    Class<? extends SpaceShip> shipClass = pship.getClass();
                                    Constructor<? extends SpaceShip> cons = shipClass.getConstructor(List.class);
                                    spaceShip = cons.newInstance(enemies);
                                }
                                catch(Exception e)
                                {
                                    find = false;
                                }
                                break;
                            }
                        }
                    }
                    
                    if(! find) spaceShip = new SpaceShip(enemies);        
            }
            spaceShip.init();
        }
        else            
        {
            userDefinedShip.init();
            spaceShip = userDefinedShip;
            authority_mode = userDefinedShip.isAuthorized();
        }
        spaceShip.loadImages();
        spaceShip.setEnemyList(enemies);
        spaceShip.setMax_hp((long) Math.ceil(spaceShip.getMax_hp() / difficulty_mode));
        spaceShip.setHp(spaceShip.getMax_hp());
        spaceShip.setX(Arena.maxWidth() / 2);
        spaceShip.setY(Arena.maxHeight() - (Arena.maxHeight() / 4));
        difficulty = 0;
        bossCount = 0;
        difficulty_delay = getGdifficultyDelay();
        if(difficulty_mode > 1.0)
            difficulty_delay = (long) Math.ceil(difficulty_delay / Math.pow(difficulty_mode, 1.2));
        //System.out.println(difficulty_delay + ", " + spaceShip.getMax_hp());// For TEST
        setGmaxEnemies(15);
        catchEnemies = Lint.big(0);
        catchItems = Lint.big(0);

        if(scenario == null) scenario = ReflexScenario.defaultScenario;
        difficulty_delay = scenario.getDiffDelay();
        if(authority_mode)
        {
            authority_mode = scenario.isAuthorized();
        }
        
        if(scenario.getAvailableContinues() != null)
            continue_left = scenario.getAvailableContinues().intValue() - 1;
        
        if(scenario.getStartItemA() != null)
        {
            for(int i=0; i<scenario.getStartItemA().intValue(); i++)
            {
                applyItem(Item.A_HP_ADD);
            }
        }
        if(scenario.getStartItemD() != null)
        {
            for(int i=0; i<scenario.getStartItemD().intValue(); i++)
            {
                applyItem(Item.D_D_ADD);
            }
        }
        if(scenario.getStartItemE() != null)
        {
            for(int i=0; i<scenario.getStartItemE().intValue(); i++)
            {
                applyItem(Item.E_E_ADD);
            }
        }
        if(scenario.getStartItemG() != null)
        {
            for(int i=0; i<scenario.getStartItemG().intValue(); i++)
            {
                applyItem(Item.G_E_H_ADD);
            }
        }
        if(scenario.getStartItemK() != null)
        {
            for(int i=0; i<scenario.getStartItemK().intValue(); i++)
            {
                applyItem(Item.K_HP_H_ADD);
            }
        }
        if(scenario.getStartItemM() != null)
        {
            for(int i=0; i<scenario.getStartItemM().intValue(); i++)
            {
                applyItem(Item.M_M_ADD);
            }
        }
        if(scenario.getStartItemS() != null)
        {
            for(int i=0; i<scenario.getStartItemS().intValue(); i++)
            {
                applyItem(Item.S_S_ADD);
            }
        }
        
        if(autoControl)
        {
            spaceShip.setAccel_x(spaceShip.getAccel());
        }
        if(todayEvent)
        {
            if(startItems != null)
            {
                for(int i=0; i<startItems.length; i++)
                {
                    applyItem(startItems[i]);
                }
            }
        }
        System.gc();
        game_pause = false;
        this.requestFocus();
    }
    public void game_finish()
    {        
        if(active)
        {
            pause();
            game_pause = false;
            
            authcode = "";
            Properties propAuth = new Properties();
            
            int ship_type = 1;
            if(spaceShip instanceof Clipper) ship_type = 3;
            else if(spaceShip instanceof Cruiser) ship_type = 2;
            ship_type = spaceShip.getKeyInt();
            
            if(todayEvent)
            {
                spaceShip.setPoint(spaceShip.getPoint().multiply(Lint.big(2)));
            }
            
            Calendar calendar_inst = Calendar.getInstance();
            int aut_year, aut_month, aut_day, aut_hour, aut_min, aut_sec;
            aut_year  = calendar_inst.get(Calendar.YEAR);
            aut_month = calendar_inst.get(Calendar.MONTH);
            aut_day   = calendar_inst.get(Calendar.DATE);
            aut_hour  = calendar_inst.get(Calendar.HOUR);
            aut_min   = calendar_inst.get(Calendar.MINUTE);
            aut_sec   = calendar_inst.get(Calendar.SECOND);
            
            propAuth.setProperty("Point"           , spaceShip.getPoint().toString());
            propAuth.setProperty("Name"            , name);
            propAuth.setProperty("Ship"            , spaceShip.getName(sets));
            propAuth.setProperty("ShipCode"        , String.valueOf(ship_type));
            propAuth.setProperty("ShipKey"         , spaceShip.getKeyName());
            propAuth.setProperty("Difficulty"      , Difficulty.intToString(difficulty, 3.3));
            propAuth.setProperty("DifficultyValue" , String.valueOf(difficulty));
            propAuth.setProperty("PlayDate"        , String.valueOf(aut_year + "." + (aut_month + 1) + "." + aut_day + " " + aut_hour + ":" + aut_min + ":" + aut_sec));
            propAuth.setProperty("PlayDateValue"   , String.valueOf(calendar_inst.getTimeInMillis()));
            propAuth.setProperty("Version1"        , String.valueOf(Reflexioner.version_main));
            propAuth.setProperty("Version2"        , String.valueOf(Reflexioner.version_sub_1));
            propAuth.setProperty("Version3"        , String.valueOf(Reflexioner.version_sub_2));
            propAuth.setProperty("Version"         , String.valueOf(Reflexioner.getVersionString(true)));
            propAuth.setProperty("Auto"            , String.valueOf(autoControl));
            propAuth.setProperty("TodayEvent"      , String.valueOf(todayEvent));
            if(scenario.equals(ReflexScenario.defaultScenario)) propAuth.setProperty("Scenario", "Standard");
            else                                                propAuth.setProperty("Scenario", scenario.getName());
            
            propAuth.remove("Code1");
            Set<String> keys = propAuth.stringPropertyNames();
            List<String> keyList = new ArrayList<String>();
            keyList.addAll(keys);
            keys = null;
            Collections.sort(keyList);
            
            StringBuilder code1Creator = new StringBuilder("");
            for(String k : keyList)
            {
                propAuth.setProperty(k, propAuth.getProperty(k).replace("\n", " "));
                code1Creator = code1Creator.append(k).append(":").append(propAuth.getProperty(k)).append("|");
            }
            keyList = null;
            
            GZIPOutputStream gzipper = null;
            try
            {
                SecuredDist sp = new SecuredDist();
                byte[] code1bin = (sp.getLeftPad(Reflexioner.version_main, Reflexioner.version_sub_1, Reflexioner.version_sub_2) + code1Creator.toString() + sp.getRightPad(Reflexioner.version_main, Reflexioner.version_sub_1, Reflexioner.version_sub_2)).getBytes("UTF-8");
                code1bin = RXUtils.hash(code1bin);
                
                String code1 = RXUtils.hexString(code1bin);
                code1Creator = null;
                propAuth.setProperty("Code1", code1);
                
                ByteArrayOutputStream coll = new ByteArrayOutputStream();
                propAuth.storeToXML(coll, "Reflexioner Result Code");
                propAuth.clear();
                
                byte[] propXml = coll.toByteArray();
                coll = new ByteArrayOutputStream();
                gzipper = new GZIPOutputStream(coll);
                
                gzipper.write(propXml);
                propXml = null;
                gzipper.close(); gzipper = null;
                
                authcode = RXUtils.hexString(coll.toByteArray());
                authcode = authcode.replace("\n", " ");
                coll = null;
                event_accepted = false;
            }
            catch(Exception ex)
            { ex.printStackTrace(); authcode = ""; }
            finally
            {
                if(gzipper != null) { try { gzipper.close(); } catch(Exception ex) {} }
            }
            
            if(! authority_mode) authcode = sets.trans("Play authorized game for get authorized code.");
            
            sp.message("게임이 끝났습니다.", "Game is finished.");
            pause_left = 0;
            sp.finish();
        }
    }
    public void game_start_by_reflex()
    {
        sp.startFindbyTab();
    }
    public void game_start()
    {
        sp.message("게임이 시작됩니다.", "Game will be started.");
        reset();
        resume();
    }
    public void game_continue()
    {
        if(spaceShip.getHp() <= 0 || finish_count >= 0)
        {
            
            if(continue_left <= 0)
            {
                sp.message("이어할 수 있는 한도를 초과하였습니다.", "Continue limit is over.");
                return;
            }
            sp.message("점수를 희생하여 함선을 회복합니다.", "Consume all the points to heal the ship.");
            spaceShip.setPoint(Lint.big(0));
            spaceShip.setHp(spaceShip.getMax_hp());
            try
            {
                for(Missile m : missile)
                {
                    m.setHp(0);
                }
            } 
            catch (Exception e)
            { }            
            finish_count = -1;
            continue_left--;
        }
        else sp.message("게임을 이어서 합니다.", "Continue the game.");
        pause_left = 50;
    }
    public int continue_lefts()
    {
        return continue_left;
    }
    public void start()
    {
        threadSwitch = true;
        if(thread2 != null) thread2.exit();
        thread2 = new RepaintThread(this);
        ThreadAccumulate.add(thread);
        ThreadAccumulate.add(thread2);
        thread2.start();
        thread.start();
    }
    public void exit()
    {
        threadSwitch = false;
    }
    public void pause()
    {
        active = false;
    }
    public void resume()
    {
        active = true;
    }
    
    @Override
    public void keyPressed(KeyEvent e)
    {
        getKeyCode = e.getKeyCode();
        if(autoControl && (getKeyCode != Reflexioner.KEY_K && getKeyCode != Reflexioner.KEY_L)) return;
        if(spaceShip == null || spaceShip.getHp() <= 0) return;
        if(getKeyCode == Reflexioner.KEY_SPACE)
        {
            if(active && (! game_pause) && (! autoFire) && (! autoControl))
            {
                if(spaceShip.getFire_delay() <= 0)
                {
                    fired_missile = spaceShip.fire();
                    if(fired_missile != null)
                        missile.addAll(fired_missile);
                    //System.out.println(spaceShip.fire());
                    spaceShip.setFire_delay(spaceShip.afterFireDelay());
                }
            }
        }
        else if(getKeyCode == Reflexioner.KEY_L)
        {
            if(active)
            {
                game_pause = ! game_pause;
                if(game_pause)
                {
                    pause_printed = false;
                }
                if(pause_left >= 1)
                {
                    pause_left = 0;
                }
            }
        }            
        else if(getKeyCode == Reflexioner.KEY_K)
        {
            if(active)
            {
                game_finish();
            }
        }
        else if(getKeyCode == Reflexioner.KEY_4)
        {
            autoFire = ! autoFire;
        }
        else if(getKeyCode == Reflexioner.KEY_5)
        {
            
        }
        else if(getKeyCode == Reflexioner.KEY_6)
        {
            
        }
        else if(getKeyCode == Reflexioner.KEY_7)
        {
            
        }
        else if(getKeyCode == Reflexioner.KEY_8)
        {
            
        }
        else if(getKeyCode == Reflexioner.KEY_9)
        {
            
        }
        else if(getKeyCode == Reflexioner.KEY_0)
        {
            
        }
        else
        {
            if(! game_pause)
            {
                spaceShip.keyPressed(e);
            }
        }
    }
    @Override
    public void keyReleased(KeyEvent e)
    {
        
    }
    @Override
    public void keyTyped(KeyEvent e)
    {
        
    }    
    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        if(! show)
        {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, getWidth(), getHeight());
            return;
        }
        try
        {
            for(Enemy e : enemies)
            {
                try
                {
                    g.setColor(e.getColor());
                    e.draw(g, this);
                } 
                catch (Exception e1)
                {
                    
                }
            }
        } 
        catch (Exception e2)
        {
            
        }
        try
        {
            for(Enemy e : ourEnemy)
            {
                try
                {
                    g.setColor(e.getColor());
                    e.draw(g, this);
                } 
                catch (Exception e1)
                {
                    
                }
            }
        } 
        catch (Exception e)
        {
            
        }
        try
        {
            for(Missile m : missile)
            {
                try
                {
                    g.setColor(m.getColor());
                    m.draw(g, this);
                } 
                catch (Exception e1)
                {
                    
                }
            }
        } 
        catch (Exception e)
        {
            
        }
        try
        {
            for(Boom b : booms)
            {
                try
                {
                    g.setColor(b.getColor());
                    b.draw(g, this);
                } 
                catch (Exception e1)
                {
                    
                }
            }
        } 
        catch (Exception e)
        {
            
        }
        try
        {
            for(Item i : items)
            {
                try
                {
                    g.setColor(i.getColor());
                    i.draw(g, this);
                } 
                catch (Exception e1)
                {
                    
                }
            }
        } 
        catch (Exception e)
        {
            
        }
        try
        {
            for(ReflexDecorate d : decorates)
            {
                try
                {
                    g.setColor(Color.WHITE);
                    d.draw(g, this);
                } 
                catch (Exception e1)
                {
                    
                }
            }
        } 
        catch (Exception e)
        {
            
        }
        try
        {
            g.setColor(spaceShip.getColor());
            spaceShip.draw(g, this);
        } 
        catch (Exception e1)
        {
            
        }
        try
        {
            getSpaceShip().drawHud(g, this);
            g.setColor(Color.MAGENTA);
            if(Reflexioner.usingFont2B != null) g.setFont(Reflexioner.usingFont2B.deriveFont((float) convertFontSize(Reflexioner.usingFont2B.getSize(), this)));
            if(pause_left >= 1)
            {                
                g.drawString(sets.trans("Pause"), convertX((maxWidth()/2) - (sets.trans("Pause").length() * 9), this), convertY((maxHeight()/2) - 30, this));
                if(Reflexioner.usingFont != null) g.setFont(Reflexioner.usingFont.deriveFont((float) convertFontSize(Reflexioner.usingFont.getSize(), this)));
                g.drawString(String.valueOf(pause_left), convertX((maxWidth()/2) - (String.valueOf(pause_left).length() * 3), this), convertY((maxHeight()/2) + 30, this));
            }
            else if(game_pause)
            {
                g.drawString(sets.trans("Pause"), convertX((maxWidth()/2) - (sets.trans("Pause").length() * 9), this), convertY((maxHeight()/2), this));
            }
            if(autoControl)
            {
                if(Reflexioner.usingFont != null) g.setFont(Reflexioner.usingFont.deriveFont((float) convertFontSize(Reflexioner.usingFont.getSize(), this)));
                g.drawString(sets.trans("AI"), convertX(5, this), convertY(Arena.maxHeight() - 10, this));
            }
            if(simpleTuto >= 1)
            {
                String tuto = " ," + sets.trans("Move →←↑↓, Break SHIFT, Fire SPACE, Change weapon 123, Toggle auto Fire 4, Pause L, Exit K");
                String[] splits = tuto.split(",");
                for(int idx=0; idx<splits.length; idx++) 
                {
                    g.drawString(splits[idx], convertX((maxWidth()/6 - 10), this), convertY((maxHeight()/6 + (idx * 35)), this));
                }
            }
        }
        catch (Exception e1)
        {
            // TODO
        }
        try
        {
            g.setColor(Color.GREEN);
            if(Reflexioner.usingFont != null) g.setFont(Reflexioner.usingFont.deriveFont((float) convertFontSize(Reflexioner.usingFont.getSize(), this)));
            for(int i=0; i<messages.size(); i++)
            {
                g.drawString(messages.get(i), convertX((int) Math.round((g.getFont().getSize() / 16.0) * messages.get(i).length()), this), convertY((15 * i), this));
            }
        }
        catch (Exception e1)
        {
            // TODO
        }
    }
    public static int convertX(int x, JPanel a) 
    {
        int res = (int) (x * (((double) a.getWidth()) / maxWidth()));
        if(res < -100) res = -100;
        return res;
    }
    public static int convertY(int y, JPanel a) 
    {
        int res = (int) (y * ((double) a.getHeight() / maxHeight()));
        if(res < -100) res = -100;
        return res;
    }
    public static int convertWidth(int w, JPanel a) 
    {
        int res = (int) (w * (((double) a.getWidth()) / maxWidth()));
        if(res < 1) res = 1;
        return res;
    }
    public static int convertHeight(int h, JPanel a) 
    {
        int res = (int) (h * ((double) a.getHeight() / maxHeight()));
        if(res < 1) res = 1;
        return res;
    }
    public static int convertFontSize(int fontSize, JPanel a) 
    {
        int calc = (int) Math.round(fontSize * (((double) a.getWidth()) / maxWidth()));
        if(calc != fontSize) { if(calc < 5) return 5; else return calc; }
        return fontSize;
    }
    public static int maxWidth() 
    {
        return 800;
    }
    public static int maxHeight() 
    {
        return 600;
    }
    public void player_stop()
    {
        getSpaceShip().setAccel_x(0);
        getSpaceShip().setAccel_y(0);
    }
    public Area player_area()
    {
        return spaceShip.area();
    }
    public long player_hp()
    {
        if(spaceShip == null) return -1;
        return spaceShip.getHp();
    }
    public int player_x()
    {
        return spaceShip.getX();
    }
    public int player_y()
    {
        return spaceShip.getY();
    }
    public int player_fire_delay()
    {
        return spaceShip.getFire_delay();
    }
    public void showPlayerInfo()
    {
        System.out.println("Player x " + spaceShip.getX() + ", y " + spaceShip.getY() + ", HP " + spaceShip.getHp() + ", Max HP " + spaceShip.getMax_hp() 
                + ", Heal HP " + spaceShip.getHp_heal() + ", Energy " + spaceShip.getEnergy() + ", Max Energy " + spaceShip.getMax_energy() + ", Heal Energy " + spaceShip.getEnergy_heal());
    }
    public static boolean isAutoControlMode()
    {
        return autoControl;
    }
    public void setAutoControlMode(JCheckBox target_checkBox)
    {
        if(target_checkBox == null) autoControl = false;
        else autoControl = target_checkBox.isSelected();
    }
    public void setAutoControlMode(JCheckBoxMenuItem target_checkBox)
    {
        if(target_checkBox == null) autoControl = false;
        else autoControl = target_checkBox.isSelected();
    }
    public void applyItem(int itemCode)
    {
        switch(itemCode)
        {
            case Item.R_HP_FULL:
                spaceShip.setHp(spaceShip.getMax_hp());
                break;
            case Item.C_E_FULL:
                spaceShip.setEnergy(spaceShip.getMax_energy());
                break;
            case Item.A_HP_ADD:
                spaceShip.setMax_hp(spaceShip.getMax_hp() + 20);
                break;
            case Item.E_E_ADD:
                spaceShip.setMax_energy(spaceShip.getMax_energy() + 10);
                break;
            case Item.M_M_ADD:
                spaceShip.setMissiles(spaceShip.getMissiles() + 1);                                            
                break;
            case Item.D_D_ADD:
                spaceShip.setDamage(spaceShip.getDamage() + 5);
                break;
            case Item.S_S_ADD:
                if(spaceShip.getAccel() <= spaceShip.getMax_accel())
                    spaceShip.setAccel(spaceShip.getAccel() + 1);
                else
                    spaceShip.setDamage(spaceShip.getDamage() + 1);
                break;
            case Item.K_HP_H_ADD:
                if(spaceShip.getHp_heal() <= spaceShip.getMax_hp_heal())
                    spaceShip.setHp_heal(spaceShip.getHp_heal() + 1);
                else
                    spaceShip.setDamage(spaceShip.getDamage() + 5);
                break;
            case Item.G_E_H_ADD:
                if(spaceShip.getEnergy_heal() <= spaceShip.getMax_energy_heal())
                    spaceShip.setEnergy_heal(spaceShip.getEnergy_heal() + 1);
                else
                    spaceShip.setMax_hp(spaceShip.getMax_hp() + 20);
                break;
            case Item.X_PT_ADD:
                spaceShip.setPoint(spaceShip.getPoint().multiply(Lint.big(2)));
                break;
            default:
                spaceShip.setDamage(spaceShip.getDamage() + 4);
                break;
        }
    }
    class RepaintThread extends Thread implements ThreadControl
    {
        private transient Arena arena;
        private transient boolean ths;
        
        public RepaintThread()
        {
            super();
        }
        public RepaintThread(Arena a)
        {
            this();
            this.arena = a; 
        }
        
        @Override
        public void run()
        {
            ths = true;
            while(ths)
            {
                try
                {
                    arena.repaint();
                    Thread.sleep(8);
                } 
                catch (InterruptedException e)
                {
                    break;
                }
            }
        }
        
        @Override
        public void exit() {
            ths = false;
            this.arena = null;
        }
    }
    class ArenaThread extends Thread implements ThreadControl
    {        
        private transient Missile target_missile = null;
        private transient Item target_item = null;
        private transient Enemy target_enemy = null;
        private transient Boom target_boom = null;
        private transient Arena arena;
        private int unique_index = 0;      
        
        public Missile getTarget_missile() {
            return target_missile;
        }
        public void setTarget_missile(Missile target_missile) {
            this.target_missile = target_missile;
        }
        public Item getTarget_item() {
            return target_item;
        }
        public void setTarget_item(Item target_item) {
            this.target_item = target_item;
        }
        public Enemy getTarget_enemy() {
            return target_enemy;
        }
        public void setTarget_enemy(Enemy target_enemy) {
            this.target_enemy = target_enemy;
        }
        public Boom getTarget_boom() {
            return target_boom;
        }
        public void setTarget_boom(Boom target_boom) {
            this.target_boom = target_boom;
        }
        public int getUnique_index() {
            return unique_index;
        }
        public void setUnique_index(int unique_index) {
            this.unique_index = unique_index;
        }
        public ArenaThread()
        {
            super();
        }
        public ArenaThread(Arena arena)
        {
            this();
            this.arena = arena;
        }
        @Override
        public void run()
        {
            threadSwitch = true;
            while(threadSwitch)
            {
                if(arena == null) break;
                if (arena.active)
                {
                    if((! arena.game_pause) && (arena.pause_left <= 0))
                    {
                        scenario.actPhase(arena, this);
                    }
                    else if(arena.pause_left >= 1)
                    {
                        arena.pause_left--;
                    }
                    else if(arena.game_pause && (! arena.pause_printed))
                    {
                        arena.pause_printed = true;
                    }
                    if(arena.simpleTuto >= 1) 
                    {
                        arena.simpleTuto--;
                    }
                }
                try
                {
                    if(arena.game_xspeed <= 0) arena.game_xspeed = 1;
                    if(getGreactDelay() <= 10) setGreactDelay(10);
                    Thread.sleep(getGreactDelay() / arena.game_xspeed);
                } 
                catch (InterruptedException e)
                {
                    break;
                }
            }
        }
        @Override
        public void exit()
        {
            threadSwitch = false;
            arena = null;
        }
    }
    public ReflexSave nowState()
    {
        return nowState(false);
    }
    public ReflexSave nowState(boolean copy)
    {
        ReflexSave newSave;
        
        if(copy)
        {
            SpaceShip ship = spaceShip.clone(true);
            List<Enemy> newEnemies = new Vector<Enemy>();
            for(int i=0; i<enemies.size(); i++)
            {
                newEnemies.add(enemies.get(i).clone(true));
            }
            List<Enemy> newOurEnemies = new Vector<Enemy>();
            for(int i=0; i<ourEnemy.size(); i++)
            {
                newOurEnemies.add(ourEnemy.get(i).clone(true));
            }
            List<Missile> newMissiles = new Vector<Missile>();
            for(int i=0; i<missile.size(); i++)
            {
                newMissiles.add(missile.get(i).clone(true));
            }
            List<Boom> newBooms = new Vector<Boom>();
            for(int i=0; i<booms.size(); i++)
            {
                newBooms.add(booms.get(i).clone(true));
            }
            List<Item> newItem = new Vector<Item>();
            for(int i=0; i<items.size(); i++)
            {
                newItem.add(items.get(i).clone(true));
            }
            List<ReflexDecorate> newDeco = new Vector<ReflexDecorate>();
            for(int i=0; i<newDeco.size(); i++)
            {
                newDeco.add(decorates.get(i).clone(true));
            }
            
            newSave = new AReflexSave(ship, newEnemies, newMissiles, newBooms, newItem, newDeco, difficulty, difficulty_mode, Reflexioner.getVersionString(true), continue_left, scenario, autoControl, newOurEnemies, authority_mode, pause_left);
            
        }
        else newSave = new AReflexSave(spaceShip, enemies, missile, booms, items, decorates, difficulty, difficulty_mode, Reflexioner.getVersionString(true), continue_left, scenario, autoControl, ourEnemy, authority_mode, pause_left);
                        
        return newSave;
    }
    public double getDifficulty_mode()
    {
        return difficulty_mode;
    }
    public void setDifficulty_mode(double difficulty_mode)
    {
        this.difficulty_mode = difficulty_mode;
    }
    public List<Enemy> getEnemies()
    {
        return enemies;
    }
    public void setEnemies(List<Enemy> enemies)
    {
        this.enemies = enemies;
    }
    public SpaceShip getSpaceShip()
    {
        return spaceShip;
    }
    public void setSpaceShip(SpaceShip spaceShip)
    {
        this.spaceShip = spaceShip;
    }
    public List<Missile> getMissile()
    {
        return missile;
    }
    public void setMissile(List<Missile> missile)
    {
        this.missile = missile;
    }
    public ArenaThread getThread()
    {
        return thread;
    }
    public void setThread(ArenaThread thread)
    {
        this.thread = thread;
    }
    public List<Boom> getBooms()
    {
        return booms;
    }
    public void setBooms(List<Boom> booms)
    {
        this.booms = booms;
    }
    public List<Item> getItems()
    {
        return items;
    }
    public void setItems(List<Item> items)
    {
        this.items = items;
    }
    public Reflexioner getSp()
    {
        return sp;
    }
    public void setSp(Reflexioner sp)
    {
        this.sp = sp;
    }
    public boolean isThreadSwitch()
    {
        return threadSwitch;
    }
    public void setThreadSwitch(boolean threadSwitch)
    {
        this.threadSwitch = threadSwitch;
    }
    public boolean isActive()
    {
        return active;
    }
    public void setActive(boolean active)
    {
        this.active = active;
    }
    public boolean isGame_pause()
    {
        return game_pause;
    }
    public void setGame_pause(boolean game_pause)
    {
        this.game_pause = game_pause;
    }
    public int getK()
    {
        return k;
    }
    public void setK(int k)
    {
        this.k = k;
    }
    public long getDifficulty()
    {
        return difficulty;
    }
    public void setDifficulty(long difficulty)
    {
        this.difficulty = difficulty;
    }
    public Boom getNewBoom()
    {
        return newBoom;
    }
    public void setNewBoom(Boom newBoom)
    {
        this.newBoom = newBoom;
    }
    public boolean isBoss_exist()
    {
        return boss_exist;
    }
    public void setBoss_exist(boolean boss_exist)
    {
        this.boss_exist = boss_exist;
    }
    public boolean isUnique_exist() {
        return unique_exist;
    }
    public void setUnique_exist(boolean unique_exist) {
        this.unique_exist = unique_exist;
    }
    public UserDefinedShip getUserDefinedShip() {
        return userDefinedShip;
    }
    public void setUserDefinedShip(UserDefinedShip userDefinedShip) {
        this.userDefinedShip = userDefinedShip;
    }
    public String getAuthcode()
    {
        return authcode;
    }
    public void setAuthcode(String authcode)
    {
        this.authcode = authcode;
    }
    public Setting getSets()
    {
        return sets;
    }
    public void setSets(Setting sets)
    {
        this.sets = sets;
    }
    public long getDifficulty_delay()
    {
        return difficulty_delay;
    }
    public void setDifficulty_delay(long difficulty_delay)
    {
        this.difficulty_delay = difficulty_delay;
    }
    public List<ReflexDecorate> getDecorates() {
        return decorates;
    }
    public void setDecorates(List<ReflexDecorate> decorates) {
        this.decorates = decorates;
    }
    public List<Enemy> getOurEnemy() {
        return ourEnemy;
    }
    public void setOurEnemy(List<Enemy> ourEnemy) {
        this.ourEnemy = ourEnemy;
    }
    public Vector<String> getMessages() {
        return messages;
    }
    public void setMessages(Vector<String> messages) {
        this.messages = messages;
    }
    public boolean isHud() {
        return hud;
    }
    public void setHud(boolean hud) {
        this.hud = hud;
    }
    public int getMessage_delay() {
        return message_delay;
    }
    public void setMessage_delay(int message_delay) {
        this.message_delay = message_delay;
    }
    public int getGame_xspeed() {
        return game_xspeed;
    }
    public void setGame_xspeed(int game_xspeed) {
        this.game_xspeed = game_xspeed;
    }
    public int getGetKeyCode() {
        return getKeyCode;
    }
    public void setGetKeyCode(int getKeyCode) {
        this.getKeyCode = getKeyCode;
    }
    public int getDecorationMax() {
        return decorationMax;
    }
    public void setDecorationMax(int decorationMax) {
        this.decorationMax = decorationMax;
    }
    public BigInteger getDecorationPointUnit() {
        return decorationPointUnit;
    }
    public void setDecorationPointUnit(BigInteger decorationPointUnit) {
        this.decorationPointUnit = decorationPointUnit;
    }
    public boolean isAuthority_mode() {
        return authority_mode;
    }
    public void setAuthority_mode(boolean authority_mode) {
        this.authority_mode = authority_mode;
    }
    public static boolean isAutoControl() {
        return autoControl;
    }
    public static void setAutoControl(boolean autoControl) {
        Arena.autoControl = autoControl;
    }
    public BufferedImage getBackgroundImage() {
        return backgroundImage;
    }
    public void setBackgroundImage(BufferedImage backgroundImage) {
        this.backgroundImage = backgroundImage;
    }
    public BigInteger getCatchEnemies() {
        return catchEnemies;
    }
    public void setCatchEnemies(BigInteger catchEnemies) {
        this.catchEnemies = catchEnemies;
    }
    public BigInteger getCatchItems() {
        return catchItems;
    }
    public void setCatchItems(BigInteger catchItems) {
        this.catchItems = catchItems;
    }
    public int getPlayer_type() {
        return player_type;
    }
    public void setPlayer_type(int player_type) {
        this.player_type = player_type;
    }
    public List<Missile> getFired_missile() {
        return fired_missile;
    }
    public void setFired_missile(List<Missile> fired_missile) {
        this.fired_missile = fired_missile;
    }
    public long getFinish_count() {
        return finish_count;
    }
    public void setFinish_count(long finish_count) {
        this.finish_count = finish_count;
    }
    public static int getSwift_delay() {
        return swift_delay;
    }
    public static void setSwift_delay(int swift_delay) {
        Arena.swift_delay = swift_delay;
    }
    public int getAutoControlDelay() {
        return autoControlDelay;
    }
    public void setAutoControlDelay(int autoControlDelay) {
        this.autoControlDelay = autoControlDelay;
    }
    public int[] getStartItems() {
        return startItems;
    }
    public void setStartItems(int[] startItems) {
        this.startItems = startItems;
    }
    public AReflexReplay getReplay() {
        return replay;
    }
    public void setReplay(AReflexReplay replay) {
        this.replay = replay;
    }
    public void applyState(ReflexReplay loadReplay, int index)
    {
        pause();
        try
        {
            replay = new BReflexReplay();
            for(int i=0; i<index; i++)
            {
                if(loadReplay.getScenes().get(i).accept()) replay.addScene(loadReplay.getScenes().get(i));
            }
            Reflexioner.replay_save = true;
        } 
        catch (Exception e)
        {
            replay = new BReflexReplay();
            Reflexioner.replay_save = false;
        }
        applyState(loadReplay.getScenes().get(index));
    }
    public void applyState(ReflexSave state)
    {
        pause();
        this.spaceShip = state.getSpaceShip();
        if(Reflexioner.image_allow)
        {
            //spaceShip.loadImages();
            if(! spaceShip.isImage_w1_exist())
            {
                spaceShip.loadImage(1);
            }
            if(! spaceShip.isImage_w2_exist())
            {
                spaceShip.loadImage(2);
            }
            if(! spaceShip.isImage_w3_exist())
            {
                spaceShip.loadImage(3);
            }
            
        }
        Arena.autoControl = state.getAutoControl().booleanValue();
        this.continue_left = state.getContinue_left().intValue();
        this.enemies = state.getEnemies();
        this.missile = state.getMissile();
        this.booms = state.getBooms();
        this.items = state.getItems();
        this.decorates = state.getDecorates();
        this.ourEnemy = state.getOurEnemy();
        if(Reflexioner.image_allow)
        {
            try
            {
                for(Enemy e : this.enemies)
                {
                    e.loadImage();
                }
            } 
            catch (Exception e)
            {
                
            }
            try
            {
                for(Missile m : missile)
                {
                    m.loadImage();
                }
            } 
            catch (Exception e)
            {
                
            }
            try
            {
                for(Item i : items)
                {
                    i.loadImage();
                }
            } 
            catch (Exception e)
            {
                
            }
            try
            {
                for(ReflexDecorate i : decorates)
                {
                    i.loadImage();
                }
            } 
            catch (Exception e)
            {
                
            }
        }
        this.difficulty = state.getDifficulty().longValue();
        if(state.getScenario() != null) this.scenario = state.getScenario();
        authority_mode = false;
        try
        {
            if(state instanceof AReflexSave)
            {
                if(((AReflexSave) state).getAuthority_game() != null)
                {
                    authority_mode = ((AReflexSave) state).getAuthority_game().booleanValue();
                }
            }
        } 
        catch (Exception e)
        {            
            e.printStackTrace();
        }
        try
        {
            spaceShip.setEnemies(enemies);
        } 
        catch (Exception e1)
        {
            
        }
        
        for(int i=0; i<missile.size(); i++)
        {
            try
            {
                if(missile.get(i) instanceof GuidedMissile)
                {
                    ((GuidedMissile) missile.get(i)).setEnemyList(enemies);
                }
            }
            catch(Exception e)
            {
                
            }
        }
        if(Arena.autoControl)
        {
            if(spaceShip.getAccel_x() <= 0) spaceShip.setAccel_x(spaceShip.getAccel());
        }
        todayEvent = false;
        pause_left = 50;
        active = true;
        resume();        
    }
    public long getTimeout()
    {
        return timeout;
    }
    public void setTimeout(long timeout)
    {
        this.timeout = timeout;
    }
    public void disable_authmode()
    {
        authority_mode = false;
    }
    public ReflexReplay savedReplay()
    {        
        replay.setName(getName());
        if(getScenario() != null)
        {
            replay.setScenarioData(getScenario().stringData());
            replay.setScenarioName(getScenario().getName());            
        }
        replay.setFinal_point(spaceShip.getPoint());        
        replay.setAuthority_code(getAuthcode());
        replay.setAuthority_game(new Boolean(authority_mode));
        replay.applyAuth(1937283 + 1001008);
        return replay;
    }
    public void try_save_properties(boolean details) throws Exception
    {
        Reflexioner.trySaveProperties(sets, details);
    }
    public void try_apply_properties() throws Exception
    {
        sp.tryApplyProperties();
    }
    public void pause_time(long time)
    {
        pause_left = time;
    }
    public void addDecorate(ReflexDecorate deco)
    {
        decorates.add(deco);
    }
    public boolean usingHud()
    {
        return hud;
    }
    public void setHudUsage(boolean usage)
    {
        hud = usage;
    }
    @Override
    public void control(int k) 
    {
        if(k == Reflexioner.KEY_4) control_4();
        else if(k == Reflexioner.KEY_5) control_5();
        else if(k == Reflexioner.KEY_6) control_6();
        else if(k == Reflexioner.KEY_7) control_7();
        else spaceShip.control(k);
    }
    @Override
    public void control_up()
    {
        spaceShip.control_up();        
    }
    @Override
    public void control_down()
    {
        spaceShip.control_down();        
    }
    @Override
    public void control_left()
    {
        spaceShip.control_left();        
    }
    @Override
    public void control_right()
    {
        spaceShip.control_right();        
    }
    @Override
    public void control_break()
    {
        spaceShip.control_break();        
    }
    @Override
    public void control_w()
    {
        spaceShip.control_w();        
    }
    @Override
    public void control_a()
    {
        spaceShip.control_a();        
    }
    @Override
    public void control_s()
    {
        spaceShip.control_s();        
    }
    @Override
    public void control_d()
    {
        spaceShip.control_d();        
    }
    @Override
    public void control_1()
    {
        spaceShip.control_1();        
    }
    @Override
    public void control_2()
    {
        spaceShip.control_2();        
    }
    @Override
    public void control_3()
    {
        spaceShip.control_3();        
    }
    public void control_auto()
    {
        control_auto(enemies, missile);
    }
    @Override
    public void control_auto(List<Enemy> enemyList, List<Missile> missileList)
    {
        spaceShip.control_auto(enemyList, missileList);        
    }
    public void control_4()
    {
        autoFire = ! autoFire;
    }
    public void control_5()
    {
        
    }
    public void control_6()
    {
        
    }
    public void control_7()
    {
        
    }
    public void control_8()
    {
        
    }
    public void control_9()
    {
        
    }
    public void control_0()
    {
        
    }
    public String scenario_name()
    {
        if(getScenario() != null) return new String(getScenario().getName());
        return null;
    }
    public int scenario_difficulty()
    {
        if(getScenario() != null) return getScenario().getDifficulty().intValue();
        return 0;
    }
    public long scenario_difficulty_delay()
    {
        if(getScenario() != null) return getScenario().getDiffDelay().longValue();
        return 0;
    }
    public static int getGspeed() {
        return gspeed;
    }
    public static void setGspeed(int gspeed) {
        Arena.gspeed = gspeed;
    }
    public static int getGreactDelay() {
        return greactDelay;
    }
    public static void setGreactDelay(int greactDelay) {
        Arena.greactDelay = greactDelay;
    }
    public static int getGbossDelay() {
        return gbossDelay;
    }
    public static void setGbossDelay(int gbossDelay) {
        Arena.gbossDelay = gbossDelay;
    }
    public static int getGdifficultyDelay() {
        return gdifficultyDelay;
    }
    public static void setGdifficultyDelay(int gdifficultyDelay) {
        Arena.gdifficultyDelay = gdifficultyDelay;
    }
    public static int getGbossBeamDelay() {
        return gbossBeamDelay;
    }
    public static void setGbossBeamDelay(int gbossBeamDelay) {
        Arena.gbossBeamDelay = gbossBeamDelay;
    }
    public static int getGspaceShipR() {
        return gspaceShipR;
    }
    public static void setGspaceShipR(int gspaceShipR) {
        Arena.gspaceShipR = gspaceShipR;
    }
    public static int getGenemyR() {
        return genemyR;
    }
    public static void setGenemyR(int genemyR) {
        Arena.genemyR = genemyR;
    }
    public static int getGmaxEnemies() {
        return gmaxEnemies;
    }
    public static void setGmaxEnemies(int gmaxEnemies) {
        Arena.gmaxEnemies = gmaxEnemies;
    }
    public static int getGfireDelay() {
        return gfireDelay;
    }
    public static void setGfireDelay(int gfireDelay) {
        Arena.gfireDelay = gfireDelay;
    }
}
