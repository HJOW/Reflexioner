package com.hjow.game.reflexioner.reflexioner;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import java.io.File;
import java.lang.reflect.Constructor;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import com.hjow.game.reflexioner.lang.Language;
import com.hjow.game.reflexioner.mainClasses.RunManager;
import com.hjow.game.reflexioner.mainClasses.ThreadAccumulate;
import com.hjow.game.reflexioner.mainClasses.ThreadControl;
import com.hjow.game.reflexioner.pack.InstalledPack;
import com.hjow.game.reflexioner.pack.Pack;
import com.hjow.game.reflexioner.setting.Difficulty;
import com.hjow.game.reflexioner.setting.Lint;
import com.hjow.game.reflexioner.setting.Setting;

public class Arena extends JPanel implements KeyListener, ControllableShip
{
	private static final long serialVersionUID = 4261314730768659883L;
	
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
	private String saved_script_5 = "", saved_script_6 = "", saved_script_7 = "";	
	
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
	private transient Vector<ReflexTrigger> triggers;
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
		triggers = new Vector<ReflexTrigger>();
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
		file_path = sets.getDefault_path();
		
		loadBackground();
	}
	public void setStartItem(int[] items)
	{
		startItems = items;
	}
	public void loadBackground()
	{
		loadBackground(sets.getDefault_path());
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
		replay.setReplay_interval(new Integer(Reflexioner.replay_interval));
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
		catch (NullPointerException e)
		{
			e.printStackTrace();
			sp.message(sets.getLang().getText(Language.ERROR) + " : " + e.getMessage());
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
	public void reset()
	{
		pause();
		enemies.clear();
		ourEnemy.clear();
		testEnemy.clear();
		missile.clear();
		booms.clear();
		items.clear();
		triggers.clear();
		finish_count = -1;
		continue_left = 2;
		pause_left = 0;
		message_delay = 50;
		autoFire = false;
		replay = new BReflexReplay();
		replay.setReplay_interval(new Integer(Reflexioner.replay_interval));
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
		difficulty_delay = Reflexioner.getDifficulty_delay();
		if(difficulty_mode > 1.0)
			difficulty_delay = (long) Math.ceil(difficulty_delay / Math.pow(difficulty_mode, 1.2));
		//System.out.println(difficulty_delay + ", " + spaceShip.getMax_hp());// For TEST
		Reflexioner.max_enemies = 15;
		catchEnemies = Lint.big(0);
		catchItems = Lint.big(0);
		if(scenario != null)
		{
			difficulty_delay = scenario.getDiff_delay();
			authority_mode = scenario.isAuthorized();
			if((scenario instanceof AReflexScenario))
			{
				AReflexScenario ascen = (AReflexScenario) scenario;
				if(ascen.getTrig() != null)
				{
					if(! ascen.getTrig().trim().equalsIgnoreCase(""))
					{
						ReflexTrigger[] ntriggers = ReflexTrigger.stringToTriggers(ascen.getTrig());
						if(ntriggers != null)
						{
							for(ReflexTrigger t : ntriggers)
							{
								if(t != null)
								{
									triggers.add(t);
								}
							}
						}
					}
				}
			}
			if(scenario instanceof BReflexScenario)
			{
				if(((BReflexScenario) scenario).getAvailable_continues() != null)
					continue_left = ((BReflexScenario) scenario).getAvailable_continues().intValue() - 1;
			}
			if(scenario instanceof IReflexScenario)
			{
				IReflexScenario is = (IReflexScenario) scenario;
				if(is.getStart_time() != null)
				{
					difficulty = is.getStart_time().longValue();
				}
				if(is.getStart_item_a() != null)
				{
					for(int i=0; i<is.getStart_item_a().intValue(); i++)
					{
						applyItem(Item.A_HP_ADD);
					}
				}
				if(is.getStart_item_d() != null)
				{
					for(int i=0; i<is.getStart_item_d().intValue(); i++)
					{
						applyItem(Item.D_D_ADD);
					}
				}
				if(is.getStart_item_e() != null)
				{
					for(int i=0; i<is.getStart_item_e().intValue(); i++)
					{
						applyItem(Item.E_E_ADD);
					}
				}
				if(is.getStart_item_g() != null)
				{
					for(int i=0; i<is.getStart_item_g().intValue(); i++)
					{
						applyItem(Item.G_E_H_ADD);
					}
				}
				if(is.getStart_item_k() != null)
				{
					for(int i=0; i<is.getStart_item_k().intValue(); i++)
					{
						applyItem(Item.K_HP_H_ADD);
					}
				}
				if(is.getStart_item_m() != null)
				{
					for(int i=0; i<is.getStart_item_m().intValue(); i++)
					{
						applyItem(Item.M_M_ADD);
					}
				}
				if(is.getStart_item_s() != null)
				{
					for(int i=0; i<is.getStart_item_s().intValue(); i++)
					{
						applyItem(Item.S_S_ADD);
					}
				}
				if(is.getStart_script() != null)
				{
					try
					{
						// TODO : is.getStart_script()
					} 
					catch (Exception e)
					{
						sp.message(sets.getLang().getText(Language.ERROR) + " : " + e.getMessage());
					}
				}
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
			
			StringBuffer auth_code = new StringBuffer("");
			String[] auth_codes = new String[17];
			int ship_type = 1;
			if(spaceShip instanceof Clipper) ship_type = 3;
			else if(spaceShip instanceof Cruiser) ship_type = 2;
			if(Reflexioner.version_main >= 0 && Reflexioner.version_sub_1 >= 9 && Reflexioner.version_sub_2 >= 0 && Reflexioner.version_nightly >= 4)
			{
				ship_type = spaceShip.getKeyInt();
			}
			
			if(todayEvent)
			{
				spaceShip.setPoint(spaceShip.getPoint().multiply(Lint.big(2)));
			}
			
			auth_codes[0] = String.valueOf(spaceShip.getPoint().toString()); // point.toString()
			auth_codes[1] = name;
			auth_codes[2] = String.valueOf(ship_type);
			auth_codes[3] = String.valueOf(difficulty); // clearCount
			auth_codes[4] = String.valueOf(Reflexioner.version_main);
			auth_codes[5] = String.valueOf(Reflexioner.version_sub_1);
			auth_codes[6] = String.valueOf(Reflexioner.version_sub_2);
			BigInteger secret_code = new BigInteger("0");
			BigInteger secret_nameCode = new BigInteger("0");
			
			if(autoControl)
			{
				auth_codes[1] = auth_codes[1] + " (auto)";
			}
			
			
			Calendar calendar_inst = Calendar.getInstance();
			int aut_year, aut_month, aut_day, aut_hour, aut_min, aut_sec;
			aut_year = calendar_inst.get(Calendar.YEAR);
			aut_month = calendar_inst.get(Calendar.MONTH);
			aut_day = calendar_inst.get(Calendar.DATE);
			aut_hour = calendar_inst.get(Calendar.HOUR);
			aut_min = calendar_inst.get(Calendar.MINUTE);
			aut_sec = calendar_inst.get(Calendar.SECOND);
			
			if(todayEvent)
			{
				auth_codes[1] = auth_codes[1] + " (" + String.valueOf(aut_year) + "." + String.valueOf(aut_month) + "." + String.valueOf(aut_day) + ")";
			}
			
			secret_code = secret_code.add(new BigInteger(String.valueOf((Reflexioner.version_main * 100) + (Reflexioner.version_sub_1 * 10) + Reflexioner.version_sub_2)));
			secret_code = secret_code.multiply(Lint.big(difficulty)); // clearCount
			secret_code = secret_code.add(new BigInteger(String.valueOf(spaceShip.getPoint().toString()))); // point.toString()
			// authority_code used
			long authority_code = Reflexioner.getAuthorizedCode(2938291);
			secret_code = secret_code.add(Lint.big(authority_code + (ship_type * ((Reflexioner.version_main * 100) + (Reflexioner.version_sub_1 * 10) + Reflexioner.version_sub_2))));
									
			
			
			auth_codes[8] = String.valueOf(aut_year);
			auth_codes[9] = String.valueOf(aut_month);
			auth_codes[10] = String.valueOf(aut_day);
			auth_codes[11] = String.valueOf(aut_hour);
			auth_codes[12] = String.valueOf(aut_min);
			auth_codes[13] = String.valueOf(aut_sec);
			if(scenario == null)
				auth_codes[14] = "Reflexioner " + Difficulty.starToString((long) Math.round(difficulty_mode * 2)) + " " + spaceShip.getName(sets) + " " + Reflexioner.getVersionString(true);
			else
				auth_codes[14] = "Reflexioner " + Difficulty.intToString((long) Math.round(difficulty_mode * 2), 3.3) + " " + scenario.getName() + " " + Reflexioner.getVersionString(true);
			auth_codes[15] = "Calc";
						
			secret_nameCode = Lint.copy(secret_code);
			secret_nameCode = secret_nameCode.multiply(new BigInteger(String.valueOf(Math.round((double) RunManager.getNameCode(auth_codes[1]) / 100.0) + 5)));
			
			secret_code = secret_code.add(Lint.newBigInteger((difficulty + 2) * ((aut_year * 6) + (aut_month * 5) + (aut_day * 4) + (aut_hour * 3) + (aut_min * 2) + aut_sec)));
			// clearCount
			
			secret_nameCode = secret_nameCode.add(Lint.big(auth_codes[14].length()).multiply(Lint.big(authority_code)));
			auth_codes[7] = secret_code.toString();			
			auth_codes[16] = secret_nameCode.toString();			
			
			for(int i=0; i<auth_codes.length; i++)
			{
				auth_code.append(auth_codes[i]);
				if(i < auth_codes.length - 1) auth_code.append("||");
			}
			
			authcode = auth_code.toString();
			event_accepted = false;
			
			if(! authority_mode) authcode = sets.getLang().getText(Language.DESCRIPTIONS + 18);
			if(scenario != null)
			{
				if(scenario instanceof AReflexScenario)
				{
					boolean accept_event = true;
					AReflexScenario aref = (AReflexScenario) scenario;
					if(aref.getEvent_exist() != null)
					{
						if(! aref.getEvent_exist().booleanValue())
						{
							accept_event = true;
							event_accepted = false;
						}
						else
						{
							Calendar cal = Calendar.getInstance();
							if(aref.getEvent_year() != null)
							{
								if(aref.getEvent_year().intValue() < cal.get(Calendar.YEAR))
								{
									accept_event = false;
									event_accepted = false;
								}
							}
							if(accept_event)
							{
								if(aref.getEvent_month() != null)
								{
									if(aref.getEvent_month().intValue() < cal.get(Calendar.MONTH) + 1)
									{
										accept_event = false;
										event_accepted = false;
									}
								}
							}
							if(accept_event)
							{
								if(aref.getEvent_day() != null)
								{
									if(aref.getEvent_day().intValue() < cal.get(Calendar.DAY_OF_MONTH))
									{
										accept_event = false;
										event_accepted = false;
									}
								}
							}
							if(accept_event)
							{
								if(aref.getEvent_hour() != null)
								{
									if(aref.getEvent_hour().intValue() < cal.get(Calendar.HOUR_OF_DAY))
									{
										accept_event = false;
										event_accepted = false;
									}
								}
							}
							if(accept_event)
							{
								if(aref.getEvent_minute() != null)
								{
									if(aref.getEvent_minute().intValue() < cal.get(Calendar.MINUTE))
									{
										accept_event = false;
										event_accepted = false;
									}
								}
							}
							if(accept_event)
							{
								if(aref.getEvent_second() != null)
								{
									if(aref.getEvent_second().intValue() < cal.get(Calendar.SECOND))
									{
										accept_event = false;
										event_accepted = false;
									}
								}
							}
						}
					}
					else
					{
						accept_event = true;
						event_accepted = true;
						
					}
					if(event_accepted)
					{
						if(scenario instanceof AReflexScenario)
						{
							if(((AReflexScenario) scenario).getEvent_url() == null) event_accepted = false;
							else event_url = ((AReflexScenario) scenario).getEvent_url();
						}
						else event_accepted = false;
					}
					
					if(! accept_event)
					{
						authcode = sets.getLang().getText(Language.DESCRIPTIONS + 18);
					}
				}
			}
			
			try
			{
				if(scenario != null)
				{
					if(scenario instanceof IReflexScenario)
					{
						if(((IReflexScenario) scenario).getFinish_script() != null  )
						{
							// TODO : ((IReflexScenario) scenario).getFinish_script()
						}
					}
				}
			}
			catch(Exception e)
			{
				sp.message(sets.getLang().getText(Language.ERROR) + " : " + e.getMessage());
			}
			sp.message("게임이 끝났습니다.", "Game is finished.");
			pause_left = 0;
			sp.finish();
		}
	}
	public void game_start_by_reflex()
	{
		sp.start_findby_tab();
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
			{
				
			}			
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
				g.drawString(sets.getLang().getText(Language.PAUSE), convertX((maxWidth()/2) - (sets.getLang().getText(Language.PAUSE).length() * 9), this), convertY((maxHeight()/2) - 30, this));
				if(Reflexioner.usingFont != null) g.setFont(Reflexioner.usingFont.deriveFont((float) convertFontSize(Reflexioner.usingFont.getSize(), this)));
				g.drawString(String.valueOf(pause_left), convertX((maxWidth()/2) - (String.valueOf(pause_left).length() * 3), this), convertY((maxHeight()/2) + 30, this));
			}
			else if(game_pause)
			{
				g.drawString(sets.getLang().getText(Language.PAUSE), convertX((maxWidth()/2) - (sets.getLang().getText(Language.PAUSE).length() * 9), this), convertY((maxHeight()/2), this));
			}
			if(autoControl)
			{
				if(Reflexioner.usingFont != null) g.setFont(Reflexioner.usingFont.deriveFont((float) convertFontSize(Reflexioner.usingFont.getSize(), this)));
				g.drawString(sets.getLang().getText(Language.AI), convertX(5, this), convertY(Arena.maxHeight() - 10, this));
			}
			if(simpleTuto >= 1)
			{
				String tuto = " ," + sets.getLang().getText(Language.REFLEX_SIMPLEHELP);
				String[] splits = tuto.split(",");
				for(int idx=0; idx<splits.length; idx++) 
				{
					g.drawString(splits[idx], convertX((maxWidth()/6 - 10), this), convertY((maxHeight()/6 + (idx * 35)), this));
				}
			}
		}
		catch (Exception e1)
		{
			
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
	private void applyItem(int itemCode)
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
		private transient Item target_item = null, newItem = null;
		private transient Enemy target_enemy = null, newEnemy = null;
		private transient BigEnemy newBigEnemy = null;
		private transient Boss newBoss = null;
		private transient Boom target_boom = null;
		private transient Arena arena;
		private transient List<Enemy> getEnemies;
		private int unique_index = 0;
		private int newItemCount = 0;
		private double newItemCenter = 0.0;
		private double newItemProbability = 0.0;	
		private double newEnemyProbability = 0.0;		
		
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
						try
						{
							arena.boss_exist = false;
							arena.unique_exist = false;
							for(Enemy e : arena.enemies)
							{
								if(e instanceof Boss)
								{								
									arena.boss_exist = true;
									if(((Boss) e).isUnique())
									{
										arena.unique_exist = true;
										break;
									}
								}
							}
							if(arena.scenario == null)
							{
								if(arena.bossCount >= Reflexioner.getBoss_delay())
								{							
									arena.bossCount = 0;
									if(! arena.unique_exist)
									{
										for(int i=0; i<arena.enemies.size(); i++)
										{
											arena.newBoom = new OvalBoom(7);
											((OvalBoom) arena.newBoom).setMaker(i);
											arena.newBoom.loadImage(file_path);
											arena.newBoom.setX(arena.enemies.get(i).getX());
											arena.newBoom.setY(arena.enemies.get(i).getY());
											arena.booms.add(arena.newBoom);
											SoundCache.play("boom");
										}
										arena.enemies.clear();
										newBoss = Boss.getNewInstance(file_path, difficulty, true);										
										arena.enemies.add(newBoss);
									}							
								}
								else bossCount = bossCount + 1;
							}
							//System.out.println(ourEnemy.size());
							for(int i=0; i<arena.ourEnemy.size(); i++)
							{
								target_enemy = arena.ourEnemy.get(i);
								target_enemy.update();
								if(target_enemy instanceof Boss)
								{
									newBoss = (Boss) target_enemy;
									
									
									if(newBoss.getBeam_energy() >= newBoss.getBeam_std())
									{
										((Boss) target_enemy).setBeam_energy(0 - (int)(Math.random() * 100));											
										arena.missile.addAll(((Boss)target_enemy).shootBeam(arena.difficulty, i, arena.spaceShip));
									}
									else if((newBoss.getBeam_energy() <= (newBoss.getBeam_std() - (newBoss.getBeam_std() / 9.5)))
											&& (newBoss.getBeam_energy() >= (newBoss.getBeam_std() - (newBoss.getBeam_std() / 8.5))))
									{
										target_boom = new CircleBoom(20);
										((OvalBoom) target_boom).setR(20);
										((OvalBoom) target_boom).setRx(1);
										((OvalBoom) target_boom).setHp(20);											
										target_boom.setX(target_enemy.getX());
										target_boom.setY(target_enemy.getY());
										((OvalBoom) target_boom).setColor(Reflexioner.color_spaceShip_missile);
										target_boom.loadImage(arena.file_path);
										arena.booms.add(target_boom);
										SoundCache.play("boom");
									}
								}
								if (target_enemy.getEnergy() >= target_enemy.getMax_energy())
								{
									arena.missile.addAll(target_enemy.fire(arena.difficulty, i, arena.spaceShip, arena.enemies, arena.file_path));
								}
							}
							for (int i = 0; i < arena.enemies.size(); i++)
							{
								target_enemy = arena.enemies.get(i);
								target_enemy.update();
								//System.out.println(enemies.get(i).ready_to_fire);
								if(target_enemy instanceof Boss)
								{
									newBoss = (Boss) target_enemy;
									
									
									if(newBoss.getBeam_energy() >= newBoss.getBeam_std())
									{
										((Boss)target_enemy).setBeam_energy(0 - (int)(Math.random() * 100));											
										arena.missile.addAll(((Boss) target_enemy).shootBeam(arena.difficulty, i, arena.spaceShip));
									}
									else if((newBoss.getBeam_energy() <= (newBoss.getBeam_std() - (newBoss.getBeam_std() / 9.5)))
											&& (newBoss.getBeam_energy() >= (newBoss.getBeam_std() - (newBoss.getBeam_std() / 8.5))))
									{
										target_boom = new CircleBoom(20);
										((OvalBoom) target_boom).setR(20);
										((OvalBoom) target_boom).setRx(1);
										((OvalBoom) target_boom).setHp(20);											
										target_boom.setX(target_enemy.getX());
										target_boom.setY(target_enemy.getY());
										((OvalBoom) target_boom).setColor(Reflexioner.color_spaceShip_missile);
										target_boom.loadImage(arena.file_path);
										arena.booms.add(target_boom);
										SoundCache.play("boom");
									}
								}
								if (target_enemy.getEnergy() >= target_enemy.getMax_energy())
								{
									arena.missile.addAll(target_enemy.fire(arena.difficulty, i, arena.spaceShip, arena.enemies, arena.file_path));
									if(target_enemy instanceof SealedEnemy)
									{
										arena.newBoom = new OvalBoom();
										((OvalBoom) arena.newBoom).setMaker(i);
										arena.newBoom.loadImage(arena.file_path);
										arena.newBoom.setX(target_enemy.getX());
										arena.newBoom.setY(target_enemy.getY());
										((OvalBoom) arena.newBoom).setRx((int)Math.round(target_enemy.getR() * 0.3));
										arena.booms.add(arena.newBoom);
										SoundCache.play("boom");
									}
								}
									
							}
						} 
						catch (Exception e1)
						{
							e1.printStackTrace();
						}
						for (int i = 0; i < arena.booms.size(); i++)
						{
							try
							{
								arena.booms.get(i).update();
							} 
							catch (Exception e)
							{
								e.printStackTrace();
							}
						}
						k = 0;
						while (k < arena.booms.size())
						{
							if (arena.booms.get(k).getHp() <= 0)
							{
								arena.booms.remove(k);
							} 
							else
								k++;
						}
						k = 0;
						while (k < missile.size())
						{							
							target_missile = missile.get(k);
							if (! missile.get(k).isLaunched())
							{
								if(! (target_missile instanceof Beam))
								{
									arena.newBoom = new OvalBoom();
									((OvalBoom) arena.newBoom).setMaker(arena.missile.get(k).getOwner());
									arena.newBoom.loadImage(file_path);
									arena.newBoom.setX(target_missile.getX());
									arena.newBoom.setY(target_missile.getY());
									((OvalBoom) arena.newBoom).setRx(4);
									arena.booms.add(newBoom);
								}
								arena.missile.remove(k);		
								SoundCache.play("boom");
							} 
							else if (arena.missile.get(k).getHp() <= 0)
							{
								if(! (target_missile instanceof Beam))
								{
									arena.newBoom = new OvalBoom();
									((OvalBoom) arena.newBoom).setMaker(arena.missile.get(k).getOwner());
									arena.newBoom.loadImage(file_path);
									arena.newBoom.setX(target_missile.getX());
									arena.newBoom.setY(target_missile.getY());
									((OvalBoom) arena.newBoom).setRx(4);
									arena.booms.add(arena.newBoom);
								}
								if(arena.missile.get(k) instanceof EnemyMissile)
								{
									getEnemies = ((EnemyMissile) arena.missile.get(k)).getEnemies();
									try
									{
										while(getEnemies.size() + arena.enemies.size() > Reflexioner.max_enemies)
										{
											getEnemies.remove(0);
										}
									} 
									catch (Exception e)
									{
										
									}
									arena.enemies.addAll(getEnemies);
								}
								else if(arena.missile.get(k) instanceof HelperSpread)
								{
									getEnemies = ((HelperSpread) arena.missile.get(k)).open(arena.spaceShip, arena.difficulty_mode, arena.file_path);
									try
									{
										while(getEnemies.size() + arena.ourEnemy.size() > Reflexioner.max_enemies)
										{
											getEnemies.remove(0);
										}
									} 
									catch (Exception e)
									{
										
									}
									arena.ourEnemy.addAll(getEnemies);
								}
								arena.missile.remove(k);			
								SoundCache.play("boom");
							} 
							else if(((target_missile instanceof GuidedMissile)) 
									&& (target_missile.getX() < -500 || target_missile.getX() >= Arena.maxWidth() + 500
								|| target_missile.getY() < -500 || target_missile.getY() >= Arena.maxHeight() + 500))
							{
								if(arena.missile.get(k) instanceof EnemyMissile)
								{
									List<Enemy> getEnemies = ((EnemyMissile) arena.missile.get(k)).getEnemies();
									try
									{
										while(getEnemies.size() + arena.enemies.size() > Reflexioner.max_enemies)
										{
											getEnemies.remove(0);
										}
									} 
									catch (Exception e)
									{
										
									}
									arena.enemies.addAll(getEnemies);
								}
								arena.missile.remove(k);
							}
							else if((! ((target_missile instanceof Beam) || (target_missile instanceof Raser)))
								&&	(target_missile.getX() < -100 || target_missile.getX() >= Arena.maxWidth() + 100
								|| target_missile.getY() < -100 || target_missile.getY() >= Arena.maxHeight() + 100))
							{
								if(arena.missile.get(k) instanceof EnemyMissile)
								{
									List<Enemy> getEnemies = ((EnemyMissile) arena.missile.get(k)).getEnemies();
									try
									{
										while(getEnemies.size() + arena.enemies.size() > Reflexioner.max_enemies)
										{
											getEnemies.remove(0);
										}
									} 
									catch (Exception e)
									{
										
									}
									arena.enemies.addAll(getEnemies);
								}
								arena.missile.remove(k);
							}
							else k++;
						}
						k = 0;
						while (k < arena.items.size())
						{
							target_item = arena.items.get(k);
							if (target_item.getHp() <= 0)
							{
								arena.items.remove(k);							
							} 
							else if(target_item.getX() < -10 || target_item.getX() >= Arena.maxWidth() + 10
									|| target_item.getY() < -10 || target_item.getY() >= Arena.maxHeight() + 10)
							{
								arena.items.remove(k);
							}
							else k++;
						}
						k = 0;
						while (k < arena.enemies.size())
						{
							target_enemy = arena.enemies.get(k);
							if (target_enemy.getHp() <= 0)
							{							
								arena.spaceShip.setPoint(arena.spaceShip.getPoint().add(Lint.big((long) Math.round(target_enemy.getMax_hp() * arena.difficulty_mode))));
								newItemProbability = Math.random();
								newItemCount = target_enemy.makeItemCount(arena.difficulty, arena.difficulty_delay, newItemProbability);
								
								newItemCenter = newItemCount / 2.0;
								for(int it=0; it<newItemCount; it++)
								{
									newItem = new Item((int)(Math.random() * 10), arena.file_path);
									newItem.setX(target_enemy.getX() + (int)Math.round((it - newItemCenter) * 20));
									newItem.setY(target_enemy.getY());
									arena.items.add(newItem);
								}
								
								if(target_enemy instanceof Boss)
								{
									arena.newBoom = new OvalBoom(10);
									((OvalBoom) arena.newBoom).setMaker(k);
									arena.newBoom.loadImage(arena.file_path);
								}
								else
								{
									arena.newBoom = new OvalBoom(7);
									((OvalBoom) arena.newBoom).setMaker(k);
									arena.newBoom.loadImage(arena.file_path);
								}
								arena.newBoom.setX(target_enemy.getX());
								arena.newBoom.setY(target_enemy.getY());
								arena.booms.add(arena.newBoom);
								arena.enemies.remove(k);	
								SoundCache.play("boom");
								arena.catchEnemies = arena.catchEnemies.add(Lint.big(1));
							} 
							else k++;
						}
						k = 0;
						while (k < arena.ourEnemy.size())
						{
							target_enemy = arena.ourEnemy.get(k);
							if (target_enemy.getHp() <= 0)
							{													
								if(target_enemy instanceof Boss)
								{
									arena.newBoom = new OvalBoom(10);
									((OvalBoom) arena.newBoom).setMaker(arena.missile.get(k).getOwner());
									arena.newBoom.loadImage(arena.file_path);
								}
								else
								{
									arena.newBoom = new OvalBoom(7);
									((OvalBoom) arena.newBoom).setMaker(k);
									arena.newBoom.loadImage(arena.file_path);
								}
								arena.newBoom.setX(target_enemy.getX());
								arena.newBoom.setY(target_enemy.getY());
								arena.booms.add(arena.newBoom);
								arena.ourEnemy.remove(k);	
								SoundCache.play("boom");
							} 
							else k++;
						}
						for(int i=0; i<arena.items.size(); i++)
						{
							try
							{
								if(Arena.autoControl)
								{
									arena.items.get(i).update(arena.spaceShip.getX(), arena.spaceShip.getY());
								}
								else
								{
									arena.items.get(i).update();
								}
							}
							catch (Exception e)
							{
								e.printStackTrace();
							}
						}
						for (int i = 0; i < arena.missile.size(); i++)
						{
							try
							{
								arena.missile.get(i).update();
							} 
							catch (Exception e)
							{
								e.printStackTrace();
							}
						}
						try
						{
							arena.spaceShip.update();
						} 
						catch (Exception e1)
						{
							e1.printStackTrace();
						}
						if(arena.difficulty == 100)
						{
							if(arena.testEnemy.size() >= 1)
							{				
								arena.enemies.addAll(arena.testEnemy);
							}
						}
						try
						{
							if((! arena.unique_exist) && (arena.scenario == null))
							{
								newEnemyProbability = Math.random();
								if(arena.difficulty < arena.difficulty_delay)
								{									
									if (newEnemyProbability >= 0.95 && arena.enemies.size() <= Reflexioner.max_enemies)
									{
										newEnemy = new Enemy(arena.file_path);
										newEnemy.setX((int) (Math.random() * Arena.maxWidth()));
										newEnemy.setHp(newEnemy.getHp() + (int) (arena.difficulty / 200));
										newEnemy.setMax_hp(newEnemy.getHp());
										newEnemy.setMax_energy(200 - (int)(arena.difficulty / 1000.0));										
										newEnemy.setDamage(50 + (arena.difficulty / 100));
										if(newEnemy.getMax_energy() < 100) newEnemy.setMax_energy(100);
										if(arena.difficulty_mode >= 3.0) newEnemy.setMax_energy(newEnemy.getMax_energy() / 2);
										if(arena.difficulty_mode >= 4.0) newEnemy.setMax_energy(newEnemy.getMax_energy() / 2);
										
										arena.enemies.add(newEnemy);
									}
								}
								else if(arena.difficulty < arena.difficulty_delay * 2)
								{
									if (newEnemyProbability >= 0.98 && arena.enemies.size() <= Reflexioner.max_enemies)
									{
										newBigEnemy = new BigEnemy(arena.file_path);
										newBigEnemy.setGuide(true);
										newBigEnemy.setX((int) (Math.random() * Arena.maxWidth()));
										newBigEnemy.setHp((int)((newBigEnemy.getHp() + (int) (arena.difficulty / 200)) * 1.25));
										newBigEnemy.setMax_hp(newBigEnemy.getHp());
										newBigEnemy.setMax_energy(200 - (int) (arena.difficulty / 1000));
										newBigEnemy.setDamage(50 + (arena.difficulty / 100));
										if(newBigEnemy.getMax_energy() < 100) newBigEnemy.setMax_energy(100);
										if(arena.difficulty_mode >= 3.0) newBigEnemy.setMax_energy(newBigEnemy.getMax_energy() / 2);
										if(arena.difficulty_mode >= 4.0) newBigEnemy.setMax_energy(newBigEnemy.getMax_energy() / 2);
										arena.enemies.add(newBigEnemy);
									}
									else if (newEnemyProbability >= 0.95 && arena.enemies.size() <= Reflexioner.max_enemies)
									{
										newBigEnemy = new BigEnemy(arena.file_path);
										newBigEnemy.setGuide(false);
										newBigEnemy.setMissiles(1 + (int)Math.round(Math.random() * 1.5));
										newBigEnemy.setX((int) (Math.random() * Arena.maxWidth()));
										newBigEnemy.setHp((int)((newBigEnemy.getHp() + (int) (arena.difficulty / 200)) * 1.25));
										newBigEnemy.setMax_hp(newBigEnemy.getHp());
										newBigEnemy.setMax_energy(200 - (int)(arena.difficulty / 1000));
										newBigEnemy.setDamage(50 + (arena.difficulty / 100));
										if(newBigEnemy.getMax_energy() < 100) newBigEnemy.setMax_energy(100);
										if(arena.difficulty_mode >= 3.0) newBigEnemy.setMax_energy(newBigEnemy.getMax_energy() / 2);
										if(arena.difficulty_mode >= 4.0) newBigEnemy.setMax_energy(newBigEnemy.getMax_energy() / 2);
										arena.enemies.add(newBigEnemy);
									}
									else if (newEnemyProbability >= 0.90 && arena.enemies.size() <= Reflexioner.max_enemies)
									{
										newEnemy = new Enemy(arena.file_path);
										newEnemy.setX((int) (Math.random() * Arena.maxWidth()));
										newEnemy.setHp((int)((newEnemy.getHp() + (int) (arena.difficulty / 200)) * 1.25));
										newEnemy.setMax_hp(newEnemy.getHp());
										newEnemy.setMax_energy(200 - (int)(arena.difficulty / 1000));
										newEnemy.setDamage(50 + (arena.difficulty / 100));
										if(newEnemy.getMax_energy() < 100) newEnemy.setMax_energy(100);
										if(arena.difficulty_mode >= 3.0) newEnemy.setMax_energy(newEnemy.getMax_energy() / 2);
										if(arena.difficulty_mode >= 4.0) newEnemy.setMax_energy(newEnemy.getMax_energy() / 2);
										arena.enemies.add(newEnemy);
									}
								}
								else
								{
									if (newEnemyProbability >= 0.99 && arena.enemies.size() <= Reflexioner.max_enemies)
									{
										newEnemy = new SealedEnemy(arena.file_path);
										newEnemy.setX((int) (Math.random() * Arena.maxWidth()));
										newEnemy.setHp(newEnemy.getHp() + (int) (arena.difficulty / 300));
										newEnemy.setMax_hp(newEnemy.getHp());
										newEnemy.setMax_energy(200 - (int)(arena.difficulty / 1000));
										newEnemy.setDamage(50 + (arena.difficulty / 100));
										((SealedEnemy) newEnemy).setSeal_weakness(50 - (int)(arena.difficulty / 100000.0));
										if(((SealedEnemy) newEnemy).getSeal_weakness() < 20) ((SealedEnemy) newEnemy).setSeal_weakness(20); 
										if(newEnemy.getMax_energy() < 100) newEnemy.setMax_energy(100);
										if(arena.difficulty_mode >= 3.0) newEnemy.setMax_energy(newEnemy.getMax_energy() / 2);
										if(arena.difficulty_mode >= 4.0) newEnemy.setMax_energy(newEnemy.getMax_energy() / 2);
										arena.enemies.add(newEnemy);
									}
									else if (newEnemyProbability >= 0.97 && arena.enemies.size() <= Reflexioner.max_enemies)
									{
										newBigEnemy = new BigEnemy(arena.file_path);
										newBigEnemy.setGuide(true);
										newBigEnemy.setMissiles(1 + (int)Math.round(Math.random() * 1.5));
										newBigEnemy.setX((int) (Math.random() * Arena.maxWidth()));
										newBigEnemy.setHp((newBigEnemy.getHp() + (int) (arena.difficulty / 200)) * 2);
										newBigEnemy.setMax_hp(newBigEnemy.getHp());
										newBigEnemy.setMax_energy(200 - (int)(arena.difficulty / 1000));
										newBigEnemy.setDamage(50 + (arena.difficulty / 100));
										if(newBigEnemy.getMax_energy() < 100) newBigEnemy.setMax_energy(100);
										if(arena.difficulty_mode >= 3.0) newBigEnemy.setMax_energy(newBigEnemy.getMax_energy() / 2);
										if(arena.difficulty_mode >= 4.0) newBigEnemy.setMax_energy(newBigEnemy.getMax_energy() / 2);
										arena.enemies.add(newBigEnemy);
									}
									else if (newEnemyProbability >= 0.95 && arena.enemies.size() <= Reflexioner.max_enemies)
									{
										newBigEnemy = new BigEnemy(arena.file_path);
										newBigEnemy.setMissiles(2 + (int) Math.round(Math.random() * 2.5));
										newBigEnemy.setX((int) (Math.random() * Arena.maxWidth()));
										newBigEnemy.setHp((newBigEnemy.getHp() + (int) (difficulty / 200)) * 2);
										newBigEnemy.setMax_hp(newBigEnemy.getHp());
										newBigEnemy.setMax_energy(200 - (int)(arena.difficulty / 1000));
										newBigEnemy.setDamage(50 + (arena.difficulty / 100));
										if(newBigEnemy.getMax_energy() < 100) newBigEnemy.setMax_energy(100);
										if(arena.difficulty_mode >= 3.0) newBigEnemy.setMax_energy(newBigEnemy.getMax_energy() / 2);
										if(arena.difficulty_mode >= 4.0) newBigEnemy.setMax_energy(newBigEnemy.getMax_energy() / 2);
										arena.enemies.add(newBigEnemy);
									}
									else if (newEnemyProbability >= 0.90 && arena.enemies.size() <= Reflexioner.max_enemies)
									{
										newEnemy = new Enemy(arena.file_path);
										newEnemy.setX((int) (Math.random() * Arena.maxWidth()));
										newEnemy.setHp(newEnemy.getHp() + (int) (arena.difficulty / 200));
										newEnemy.setMax_hp(newEnemy.getHp());
										newEnemy.setMax_energy(200 - (int)(arena.difficulty / 1000));
										newEnemy.setDamage(50 + (arena.difficulty / 100));
										if(newEnemy.getMax_energy() < 100) newEnemy.setMax_energy(100);
										if(arena.difficulty_mode >= 3.0) newEnemy.setMax_energy(newEnemy.getMax_energy() / 2);
										if(arena.difficulty_mode >= 4.0) newEnemy.setMax_energy(newEnemy.getMax_energy() / 2);
										arena.enemies.add(newEnemy);
									}
								}
							}
							else if(arena.scenario != null)
							{
								if(arena.scenario.getPatterns() != null && (! arena.unique_exist))
								{
									for(EnemyPattern s : arena.scenario.getPatterns())
									{
										if((arena.difficulty >= s.getMin_delay().longValue()) && (arena.difficulty <= s.getMax_delay().longValue() || s.getMax_delay().longValue() <= -1))
										{
											if(Math.random() <= s.getRatio().doubleValue() && arena.enemies.size() <= arena.scenario.getEnemy_limit().intValue())
											{
												newEnemy = s.createEnemy(arena.file_path, arena.difficulty);
												newEnemy.loadImage(arena.file_path);
												newEnemy.setDamage(Math.round(newEnemy.getDamage() + (s.getAddDamageRatio().doubleValue() * arena.difficulty)));
												newEnemy.setMax_hp(Math.round(newEnemy.getMax_hp() + (s.getAddHPRatio().doubleValue() * arena.difficulty)));
												newEnemy.setHp(newEnemy.getMax_hp());
												newEnemy.init();
												arena.enemies.add(newEnemy);
											}
										}
									}
								}
								if(arena.scenario.getDefaultPattern() != null && (! arena.unique_exist))
								{
									if(Math.random() <= arena.scenario.getDefaultPattern().getRatio() && arena.enemies.size() <= arena.scenario.getEnemy_limit())
									{
										newEnemy = arena.scenario.getDefaultPattern().createEnemy(arena.file_path, arena.difficulty);
										newEnemy.loadImage(arena.file_path);
										newEnemy.setDamage(Math.round(newEnemy.getDamage() + (scenario.getDefaultPattern().getAddDamageRatio() * arena.difficulty)));
										newEnemy.setMax_hp(Math.round(newEnemy.getMax_hp() + (scenario.getDefaultPattern().getAddHPRatio()     * arena.difficulty)));
										newEnemy.setHp(newEnemy.getMax_hp());
										newEnemy.init();
										arena.enemies.add(newEnemy);
									}
								}
							}
						} 
						catch (Exception e1)
						{
							e1.printStackTrace();
						}
						for (int j = 0; j < arena.missile.size(); j++)
						{
							//System.out.println(j + " : " + missile.get(j).getOwner());
							try
							{
								target_missile = arena.missile.get(j);
							} 
							catch (Exception e1)
							{
								e1.printStackTrace();
								continue;
							}
							
							for (int i = 0; i < arena.enemies.size(); i++)
							{
								try
								{
									target_enemy = arena.enemies.get(i);
									if (target_missile.getOwner() == Missile.SPACESHIP && target_enemy.collapse(target_missile))
									{
										target_enemy.setHp(target_enemy.getHp() - target_missile.getDamage());
										if(target_missile instanceof PulseMissile)
										{
											target_boom = new OvalBoom(target_missile.getW());
											((OvalBoom) target_boom).setMaker(missile.get(j).getOwner());
											target_boom.loadImage(arena.file_path);
											target_boom.setX(target_missile.getX());
											target_boom.setY(target_missile.getY());
											arena.booms.add(target_boom);
											SoundCache.play("boom");
											target_missile.setHp(0);
										}
										else if(target_missile instanceof ReflexMissile)
										{
											target_missile.setHp(target_missile.getHp() - 100);
											target_missile.setDy(-1 * target_missile.getDy());
											target_boom = new OvalBoom(3);
											((OvalBoom) target_boom).setMaker(missile.get(j).getOwner());
											target_boom.loadImage(arena.file_path);
											target_boom.setX(target_missile.getX());
											target_boom.setY(target_missile.getY());
											arena.booms.add(target_boom);
											SoundCache.play("boom");
											if(((ReflexMissile) target_missile).getDx() == 0)
											{
												if(Math.random() >= 0.5)
												{
													((ReflexMissile) target_missile).setDx(Reflexioner.getSpeed());
												}
												else
												{
													((ReflexMissile) target_missile).setDx(-1 * Reflexioner.getSpeed());
												}
											}
										}
										else if(! ((target_missile instanceof Beam) || (target_missile instanceof Raser)))
										{
											target_missile.setHp(0);
										}
									}
								} 
								catch (Exception e)
								{
									e.printStackTrace();
								}
							}
							try
							{
								if (target_missile.getOwner() != Missile.SPACESHIP && arena.spaceShip.collapse(target_missile))
								{
									arena.spaceShip.setHp(arena.spaceShip.getHp() - target_missile.getDamage());
									if(! ((target_missile instanceof Beam) || (target_missile instanceof Raser)))
										target_missile.setHp(0);
								}
							} 
							catch (Exception e)
							{
								e.printStackTrace();
							}	
							try
							{
								for(int l=0; l<arena.ourEnemy.size(); l++)
								if(target_missile.getOwner() != Missile.SPACESHIP && arena.ourEnemy.get(l).collapse(target_missile))
								{
									arena.ourEnemy.get(l).setHp(arena.ourEnemy.get(l).getHp() - target_missile.getDamage());
									if(! ((target_missile instanceof Beam) || (target_missile instanceof Raser)))
										target_missile.setHp(0);
								}
							}
							catch (Exception e)
							{
								e.printStackTrace();
							}
						}
						
						for(ReflexTrigger t : arena.triggers)
						{
							if(t.isAccept(arena))
							{
								try
								{
									t.action(arena);
								} 
								catch (Exception e)
								{
									e.printStackTrace();
								}
							}
						}
						
						for (int i = 0; i < arena.enemies.size(); i++)
						{
							try
							{
								target_enemy = arena.enemies.get(i);
								if(arena.spaceShip.collapse(target_enemy))
								{
									arena.spaceShip.setHp(arena.spaceShip.getHp() - target_enemy.getHp());
									arena.newBoom = new OvalBoom(7);
									((OvalBoom) arena.newBoom).setMaker(i);
									arena.newBoom.loadImage(arena.file_path);
									arena.newBoom.setX(target_enemy.getX());
									arena.newBoom.setY(target_enemy.getY());
									arena.booms.add(arena.newBoom);
									SoundCache.play("boom");
									target_enemy.setHp(0);
								}
							}
							catch (Exception e)
							{
								e.printStackTrace();
							}
						}
						for(int i=0; i<arena.items.size(); i++)
						{
							try
							{
								if(arena.spaceShip.collapse(arena.items.get(i)))
								{
									arena.items.get(i).setHp(0);
									arena.newBoom = new ItemUseBoom(6);
									arena.newBoom.loadImage(arena.file_path);
									arena.newBoom.setX(arena.items.get(i).getX());
									arena.newBoom.setY(arena.items.get(i).getY());
									arena.booms.add(arena.newBoom);
									arena.catchItems = arena.catchItems.add(Lint.big(1));
									applyItem(arena.items.get(i).getType());
									SoundCache.play("takeitem");
								}
							}
							catch (Exception e)
							{
								e.printStackTrace();
							}
						}
						try
						{
							if(arena.spaceShip.getHp() <= 0 && arena.finish_count < 0)
							{
								for(int l=0; l<arena.ourEnemy.size(); l++)
								{
									arena.ourEnemy.get(l).setHp(0);
								}
								arena.newBoom = new OvalBoom(arena.spaceShip.getR() / 2, Missile.SPACESHIP);
								arena.newBoom.setX(arena.spaceShip.getX());
								arena.newBoom.setY(arena.spaceShip.getY());
								arena.newBoom.loadImage(arena.file_path);
								arena.booms.add(arena.newBoom);
								SoundCache.play("boom");
								arena.finish_count = (arena.spaceShip.getR() / 2) + 5;
							}
						}
						catch (Exception e1)
						{
							e1.printStackTrace();
						}
						
						
						if(arena.finish_count == 0)
						{
							arena.game_finish();
						}
						arena.decreaseFinishCount();
						
						if(arena.scenario != null)
						{
							if(arena.scenario instanceof DReflexScenario)
							{
								if(((DReflexScenario) arena.scenario).getDeadline() != null)
								{
									if((Lint.big(0).compareTo(((DReflexScenario) arena.scenario).getDeadline()) <= 1) && Lint.big(arena.difficulty).compareTo(((DReflexScenario) arena.scenario).getDeadline()) >= 1)
									{
										arena.game_finish();
									}
								}
							}
						}
						
						try
						{
							if (arena.sp != null)
								arena.sp.update(arena.spaceShip.getHp(), arena.spaceShip.getMax_hp(), arena.spaceShip.getPoint(), arena.spaceShip.getEnergy(), arena.spaceShip.getMax_energy());
						} 
						catch (Exception e1)
						{
							e1.printStackTrace();
						}	
						
						if(arena.decorates.size() <= 40 && Math.random() >= (0.7 - (arena.difficulty / 100000.0)))
						{
							arena.decorates.add(new ReflexDecorate("star", (int) (Math.random() * Arena.maxWidth()), 0, (int)(3 * Math.random() + 5 + (arena.difficulty / 500)), (int)(3 * Math.random() + 1), arena.file_path));
						}
						for(int i=0; i<arena.decorates.size(); i++)
						{
							if(arena.decorates.get(i) != null)
							{
								arena.decorates.get(i).update();
								if(arena.decorates.get(i).getX() < -10 || arena.decorates.get(i).getX() > Arena.maxWidth()
										|| arena.decorates.get(i).getY() < -10 || arena.decorates.get(i).getY() > Arena.maxHeight())
									arena.decorates.get(i).setHp(0);
							}
						}
						k = 0;
						while (k < arena.decorates.size())
						{
							if (arena.decorates.get(k).getHp() <= 0)
							{
								arena.decorates.remove(k);
							} 
							else
								k++;
						}
						
						if(arena.difficulty < (Integer.MAX_VALUE * (long)100) - 1)
						{
							if(! arena.unique_exist)
							{
								arena.difficulty = arena.difficulty + 1;
							}
						}
						
						if(arena.timeout >= 0)
						{
							if(arena.difficulty >= arena.timeout)
							{
								arena.game_finish();
							}
						}
						
						//showPlayerInfo();
						if(Reflexioner.max_enemies <= 30 && arena.difficulty % 5000 == 0)
						{
							Reflexioner.max_enemies += 1;
						}
						
						if(arena.spaceShip.getFire_delay() >= 1)
						{
							arena.spaceShip.setFire_delay(arena.spaceShip.getFire_delay() - 1);
						}
						
						if(arena.scenario != null)
						{
							if(arena.scenario instanceof XReflexScenario)
							{
								try
								{
									// TODO : ((XReflexScenario) arena.scenario).getScript()
								}
								catch(Exception e)
								{
									e.printStackTrace();
								}
							}
						}
						
						for(int i=0; i<arena.enemies.size(); i++)
						{
							unique_index = -2;
							if(arena.enemies.get(i) instanceof Boss)
							{
								if(((Boss) arena.enemies.get(i)).isUnique())
								{
									//System.out.println(enemies.get(i) + ", " + ((Boss) enemies.get(i)).isUnique());
									unique_index = i;
									break;
								}
							}
						}
						if(unique_index >= 0)
						{
							for(int i=0; i<arena.enemies.size(); i++)
							{
								if(i != unique_index) 
								{
									if(! arena.enemies.get(i).isOwn_unique())
									{
										arena.enemies.get(i).setHp(0);
									}
								}
							}
						}
						
						if(! arena.unique_exist)
						{
							for(int i=0; i<arena.enemies.size(); i++)
							{
								if(arena.enemies.get(i).isOwn_unique())
								{
									arena.enemies.get(i).setHp(0);
								}
							}
						}
						
						if(Reflexioner.replay_save)
						{
							if(arena.replay.getScenes().size() < Integer.MAX_VALUE)
							{								
								if(Reflexioner.replay_now_delay == 0)
								{
									arena.replay.addScene(nowState(true));
								}
								Reflexioner.replay_now_delay++;
								if(Reflexioner.replay_now_delay >= Reflexioner.replay_interval)
								{
									Reflexioner.replay_now_delay = 0;
								}
							}
						}
						
						if(Arena.swift_delay >= 1)
						{
							Arena.swift_delay--;
						}
												
						if(Arena.autoControl)
						{
							if(arena.active && (! arena.game_pause))
							{
								double controlRandom = Math.random();
								boolean boss_exist = false;
								int unders = 0;
								if(arena.spaceShip.getEnergy() / (double) arena.spaceShip.getMax_energy() >= 0.5)
								{
									controlRandom = controlRandom + 0.4;
								}
								else if(spaceShip.getEnergy() / (double)spaceShip.getMax_energy() >= 0.2)
								{
									controlRandom = controlRandom + 0.2;
								}
								for(Enemy e : arena.enemies)
								{
									if(e != null)
									{
										if(Math.abs(arena.spaceShip.getX() - e.getX()) < 2)
										{
											unders++;
										}
										if(e instanceof Boss)
										{
											boss_exist = true;
										}
									}
								}
								if(controlRandom >= 1.7 - arena.spaceShip.ai_advantage_mode3(arena.enemies.size(), Reflexioner.max_enemies, unders, boss_exist)) arena.spaceShip.setMode(3);
								else if(controlRandom >= 1.7 - arena.spaceShip.ai_advantage_mode2(arena.enemies.size(), Reflexioner.max_enemies, unders, boss_exist)) arena.spaceShip.setMode(2);
								else arena.spaceShip.setMode(1);
								if(arena.spaceShip.getFire_delay() <= 0)
								{
									arena.fired_missile = arena.spaceShip.fire();
									if(arena.fired_missile != null)
										arena.missile.addAll(arena.fired_missile);
									//System.out.println(spaceShip.fire());
									arena.spaceShip.setFire_delay(arena.spaceShip.afterFireDelay());
								}
								
								if(arena.autoControlDelay <= 0)
								{
									arena.control_auto();
									arena.autoControlDelay = 3;
								}
								else arena.autoControlDelay--;
							}
						}
						else if(arena.autoFire)
						{
							if(arena.spaceShip.getFire_delay() <= 0)
							{
								arena.fired_missile = arena.spaceShip.fire();
								if(arena.fired_missile != null)
									arena.missile.addAll(arena.fired_missile);
								//System.out.println(spaceShip.fire());
								arena.spaceShip.setFire_delay(arena.spaceShip.afterFireDelay());
							}
						}
						if(arena.messages.size() >= 1)
						{
							try
							{
								if(arena.message_delay <= 0)
								{
									arena.messages.remove(0);
									arena.message_delay = 50;
								}
								else
								{
									arena.message_delay--;
								}
							} 
							catch (Exception e)
							{
								
							}
						}
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
					if(Reflexioner.getReact_delay() <= 10) Reflexioner.setReact_delay(10);
					Thread.sleep(Reflexioner.getReact_delay() / arena.game_xspeed);
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
	public String getAuthcode()
	{
		return authcode;
	}
	public void setAuthcode(String authcode)
	{
		this.authcode = authcode;
	}
	public String getSaved_script_5()
	{
		return saved_script_5;
	}
	public void setSaved_script_5(String saved_script_5)
	{
		this.saved_script_5 = saved_script_5;
	}
	public String getSaved_script_6()
	{
		return saved_script_6;
	}
	public void setSaved_script_6(String saved_script_6)
	{
		this.saved_script_6 = saved_script_6;
	}
	public String getSaved_script_7()
	{
		return saved_script_7;
	}
	public void setSaved_script_7(String saved_script_7)
	{
		this.saved_script_7 = saved_script_7;
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
	private BigInteger getSettingAuth(long password, Setting sets)
	{
		if(Reflexioner.getAuthorizedCode(password) <= 1000) return Lint.big(0);
		BigInteger authCode;
		try
		{
			authCode = Lint.big(0);
			if(sets.getProperties().containsKey("reflexioner_starauth"))
				authCode = Lint.big(sets.getProperties().get("reflexioner_starauth"));
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return Lint.big(0);
		}
		return authCode;
	}
	private BigInteger getCorrectSettingAuth(long password, List<ReflexScenario> scenarioList, Setting sets)
	{
		BigInteger results = Lint.big(0);
		ReflexScenario scen = null;
		AReflexScenario ascen = null;
		String key1, key2;
		StringTokenizer delimToken;
		for(int i=0; i<scenarioList.size(); i++)
		{
			scen = scenarioList.get(i);
			if(scen instanceof AReflexScenario)
			{
				ascen = (AReflexScenario) scen;
				results = results.add(Lint.big(ascen.getRandomCode()));
				
				Set<String> keys = sets.getProperties().keySet();
				for(String k : keys)
				{
					try
					{
						delimToken = new StringTokenizer(k, "_");
						key1 = delimToken.nextToken();
						if(key1.equalsIgnoreCase("reflexioner"))
						{
							key2 = delimToken.nextToken();
							if(key2.equalsIgnoreCase("starauth"))
							{
								
							}
							else
							{
								results = results.add(Lint.big(sets.getProperties().get(k)));
							}
						}
					} 
					catch (Exception e)
					{
						e.printStackTrace();
					}
				}
				/*
				for(int j=0; j<sets.getOtherObjects().size(); j++)
				{
					try
					{
						delimToken = new StringTokenizer(sets.getOtherObjects().get(j), "|||");
						key1 = delimToken.nextToken();
						key2 = delimToken.nextToken();
						contents = delimToken.nextToken();
						if(key1.equalsIgnoreCase("reflexioner"))
						{
							if(key2.equalsIgnoreCase(ascen.getRandomCode().toString()))
							{
								results = results.add(new BigInteger(contents));
							}
						}
					} 
					catch (Exception e)
					{
						
					}	
				}
				*/
				
			}
		}
		results = results.multiply(Lint.big(623));
		results = results.add(Lint.big(9253));
		results = results.divide(Lint.big(51));
		return results;
	}
	public void setSettingAuth(long password, List<ReflexScenario> scenarioList, Setting sets)
	{
		if(Reflexioner.getAuthorizedCode(password) <= 1000) return;
		try
		{
			BigInteger corrects = getCorrectSettingAuth(password, scenarioList, sets);
			String key1, key2 = null;
			StringTokenizer delimToken;
			
			Set<String> keys = sets.getProperties().keySet();
			for(String k : keys)
			{
				delimToken = new StringTokenizer(k, "_");
				key1 = delimToken.nextToken();
				if(key1.equalsIgnoreCase("reflexioner_starauth"))
				{
					key2 = key1;
					break;
				}
			}
			if(key2 != null)
			{
				sets.getProperties().put(key2, corrects.toString());
			}
			else
			{
				sets.getProperties().put("reflexioner_starauth", corrects.toString());
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		/*
		for(int j=0; j<sets.getOtherObjects().size(); j++)
		{
			try
			{
				delimToken = new StringTokenizer(sets.getOtherObjects().get(j), "|||");
				key1 = delimToken.nextToken();
				key2 = delimToken.nextToken();
				delimToken.nextToken();
				if(key1.equalsIgnoreCase("reflexioner"))
				{
					if(key2.equalsIgnoreCase("star_auth"))
					{
						index = j;
						break;
					}
				}
			} 
			catch (Exception e)
			{
				
			}	
		}
		if(index == -1)
		{
			sets.getOtherObjects().add("reflexioner|||star_auth|||" + corrects.toString());
		}
		else
		{
			sets.getOtherObjects().set(index, "reflexioner|||star_auth|||" + corrects.toString());
		}
		*/
	}
	public void resetSettingAuth(long password, List<ReflexScenario> scenarioList, Setting sets)
	{
		String key1, key2 = null;
		StringTokenizer delimToken;
		
		Set<String> keys = sets.getProperties().keySet();
		for(String k : keys)
		{
			delimToken = new StringTokenizer(k, "_");
			key1 = delimToken.nextToken();
			if(key1.equalsIgnoreCase("reflexioner_starauth"))
			{
				key2 = key1;
				break;
			}
		}
		if(key2 != null)
		{
			sets.getProperties().put("reflexioner_starauth", "0");
		}
		else
		{
			sets.getProperties().put("reflexioner_starauth", "0");
		}
	}
	public boolean isSettingAuth(long password, List<ReflexScenario> scenarioList, Setting sets)
	{		
		try
		{
			BigInteger check_target = getSettingAuth(password, sets);
			BigInteger corrects = getCorrectSettingAuth(password, scenarioList, sets);
			if(check_target == null || corrects == null) return false;
			return (check_target.compareTo(corrects) == 0);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
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
			if(getScenario() instanceof AReflexScenario)
			{
				replay.setScenarioData(((AReflexScenario) getScenario()).stringData());
			}		
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
		Reflexioner.try_save_properties(sets, details);
	}
	public void try_apply_properties() throws Exception
	{
		sp.try_apply_properties();
	}
	public void pause_time(long time)
	{
		pause_left = time;
	}
	public void addDecorate(ReflexDecorate deco)
	{
		decorates.add(deco);
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
		if(getScenario() != null) return getScenario().getDiff_delay().longValue();
		return 0;
	}
}
