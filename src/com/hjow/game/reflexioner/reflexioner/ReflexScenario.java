package com.hjow.game.reflexioner.reflexioner;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

import com.hjow.game.reflexioner.reflexioner.Arena.ArenaThread;
import com.hjow.game.reflexioner.setting.Lint;

public class ReflexScenario implements Serializable, Comparable<ReflexScenario>
{
    private static final long serialVersionUID = 872068919420124788L;
    public static ReflexScenario defaultScenario = new ReflexScenario();
    static
    {
    	defaultScenario.setSerials(872068919420124789L);
    }
    
    private long serials = 0L;
    private String name, description, koreanDescription;
    private String spaceShip;
    private Boolean spaceShipSelectable;
    private Integer startItemM, startItemD, startItemS, startItemA, startItemK, startItemE, startItemG; // IReflexScenario
    private Integer difficulty, enemyLimit;
    private Long diffDelay, auth;
    private BigInteger deadLine;
    private boolean customPhase = false;
    private EnemyPattern[] patterns;
    private EnemyPattern defaultPattern;
    
    private Integer availableContinues; // BReflexScenario
    private Integer continueType;
    
    public static final int CONTINUE_WITH_ZERO_POINT = 0;
    public static final int CONTINUE_WITH_ZERO_ITEM = 1;
    
    public ReflexScenario()
    {
        name = "";
        description = "";
        koreanDescription = "";
        spaceShip  = "flex";
        difficulty = new Integer(1);
        enemyLimit = new Integer(20);
        diffDelay  = new Long(5000);
        auth       = new Long(0);
        startItemM = new Integer(0);
        startItemD = new Integer(0);
        startItemS = new Integer(0);
        startItemA = new Integer(0);
        startItemK = new Integer(0);
        startItemE = new Integer(0);
        startItemG = new Integer(0);
        availableContinues = new Integer(2);
        continueType = new Integer(CONTINUE_WITH_ZERO_POINT);
        spaceShipSelectable = new Boolean(true);
        customPhase = false;
        serials = new java.util.Random().nextLong();
        
        defaultPattern = new EnemyPattern();
        
        Enemy newEnemy = new Enemy();
		newEnemy.setColor(Reflexioner.color_enemy);
		newEnemy.setMax_energy(200);
		newEnemy.setMax_hp(500);
		newEnemy.setDamage(50);
		newEnemy.init();
        defaultPattern.setEnemy(newEnemy);
        defaultPattern.setRatio(0.1);
        
        Vector<EnemyPattern> pats = new Vector<EnemyPattern>();
        
        EnemyPattern newEnemyPattern = new EnemyPattern();
        newEnemyPattern.setMin_delay(new Long(5000));
        newEnemyPattern.setMax_delay(new Long(9999));
        newEnemyPattern.setAddDamageRatio(new Double(0.01));
        newEnemyPattern.setAddHPRatio(new Double(0.01));
        newEnemyPattern.setRatio(0.001);
        newEnemy = new Boss();
		newEnemy.setColor(Reflexioner.color_enemy);
		newEnemy.setMax_energy(200);
		newEnemy.setMax_hp(5000);		
		((Boss)newEnemy).setBeam_energy(300);
		((Boss)newEnemy).setRatio(0.99);
		((Boss)newEnemy).setUnique(false);
		newEnemy.init();
        newEnemyPattern.setEnemy(newEnemy);
        pats.add(newEnemyPattern);
        
        newEnemyPattern = new EnemyPattern();
        newEnemyPattern.setMin_delay(new Long(10000));
        newEnemyPattern.setMax_delay(new Long(-1));
        newEnemyPattern.setAddDamageRatio(new Double(0.01));
        newEnemyPattern.setAddHPRatio(new Double(0.01));
        newEnemyPattern.setRatio(0.001);
        newEnemy = new HyperBoss();
        newEnemy.setColor(Reflexioner.color_enemy);
        newEnemy.setMax_energy(200);
        newEnemy.setMax_hp(5000);
        newEnemy.setDamage(50);
        ((Boss)newEnemy).setBeam_energy(300);
        ((Boss)newEnemy).setRatio(0.99);
        ((Boss)newEnemy).setUnique(true);
        newEnemy.init();
        newEnemyPattern.setEnemy(newEnemy);
        pats.add(newEnemyPattern);
        
        patterns = new EnemyPattern[pats.size()];
        for(int idx=0; idx<patterns.length; idx++)
        {
        	patterns[idx] = pats.get(idx);
        }
        
        deadLine = BigInteger.ZERO;
    }
    public ReflexScenario(String serialized)
    {
    	this();
    	// TODO
    }
    public String stringData()
    {
    	return ""; // TODO
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public String getSpaceShip()
    {
        return spaceShip;
    }
    public void setSpaceShip(String spaceShip)
    {
        this.spaceShip = spaceShip;
    }
    public Integer getDifficulty()
    {
        return difficulty;
    }
    public void setDifficulty(Integer difficulty)
    {
        this.difficulty = difficulty;
    }
    public Long getDiffDelay()
    {
        return diffDelay;
    }
    public void setDiffDelay(Long diff_delay)
    {
        this.diffDelay = diff_delay;
    }
    public Long getAuth()
    {
        return auth;
    }
    public void setAuth(Long auth)
    {
        this.auth = auth;
    }

    public EnemyPattern[] getPatterns()
    {
        return patterns;
    }

    public void setPatterns(EnemyPattern[] patterns)
    {
        this.patterns = patterns;
    }
    
    public long authorized(int password)
    {
        long results = 0;
        for(char c : name.toCharArray())
        {
            results = results + (long) c;
        }
        for(char c : description.toCharArray())
        {
            results = results + (long) c;
        }
        for(char c : koreanDescription.toCharArray())
        {
            results = results + (long) c;
        }
        for(char c : spaceShip.toCharArray())
        {
            results = results + (long) c;
        }
        results = results + difficulty.intValue();
        results = results - diffDelay.longValue();
        results = results * enemyLimit.intValue();
        results = (long) (results + (defaultPattern.getAddDamageRatio().longValue() * 1026));
        results = (long) (results + (defaultPattern.getAddHPRatio().longValue() * 2732));
        results = (long) (results + (defaultPattern.getAddHPRatio() * 2183));
        results = (long) (results + (long) ((defaultPattern.getMax_delay().longValue() + defaultPattern.getMin_delay().longValue()) * defaultPattern.getRatio().doubleValue()));
        results = results + defaultPattern.getEnemy().getDamage();
        results = results + defaultPattern.getEnemy().getMissiles();
        results = results + defaultPattern.getEnemy().getMax_energy();
        results = results + defaultPattern.getEnemy().getMax_hp();
        if(defaultPattern.getEnemy() instanceof Boss)
        {
            results = results + 5124;            
        }
        if(defaultPattern.getEnemy() instanceof BigEnemy)
        {
            results = results + 8362;
            results = results + defaultPattern.getEnemy().getMissiles();
            results = results + defaultPattern.getEnemy().getR();            
        }
        else results = results + 2615;
        results = results + defaultPattern.getEnemy().getDy();
        results = results + defaultPattern.getEnemy().getDx();
        results = results + defaultPattern.getEnemy().getX();
        results = results + defaultPattern.getEnemy().getY();
        
        results = results / 183;        
        
        return results;
    }
    public final boolean isAuthorized()
    {
    	if(defaultScenario.equals(this)) return true;
        if(auth == null) return false;
        try
        {
            return (auth.longValue() == authorized(1937283 + 1001008));
        } 
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }        
    }
    
    public static String enemyToString(Enemy enemy)
    {
        String results = "";
        //if(enemy == null) return "";
        if(enemy instanceof HyperBoss)
        {
            results = results + "type" + Reflexioner.DELIM_ENEMY + "boss_3" + "\n";
            results = results + "beamdelay" + Reflexioner.DELIM_ENEMY + String.valueOf(((Boss) enemy).getBeam_std()) + "\n";
            results = results + "attack_ratio" + Reflexioner.DELIM_ENEMY + String.valueOf(((Boss) enemy).getRatio()) + "\n";
            results = results + "guide" + Reflexioner.DELIM_ENEMY + String.valueOf(((Boss) enemy).isGuide()) + "\n";
            results = results + "missiles" + Reflexioner.DELIM_ENEMY + String.valueOf(((Boss) enemy).getMissiles()) + "\n";
            results = results + "unique" + Reflexioner.DELIM_ENEMY + String.valueOf(((Boss) enemy).isUnique()) + "\n";
        }
        else if(enemy instanceof ExtremeBoss)
        {
            results = results + "type" + Reflexioner.DELIM_ENEMY + "boss_2" + "\n";
            results = results + "beamdelay" + Reflexioner.DELIM_ENEMY + String.valueOf(((Boss) enemy).getBeam_std()) + "\n";
            results = results + "attack_ratio" + Reflexioner.DELIM_ENEMY + String.valueOf(((Boss) enemy).getRatio()) + "\n";
            results = results + "guide" + Reflexioner.DELIM_ENEMY + String.valueOf(((Boss) enemy).isGuide()) + "\n";
            results = results + "missiles" + Reflexioner.DELIM_ENEMY + String.valueOf(((Boss) enemy).getMissiles()) + "\n";
            results = results + "unique" + Reflexioner.DELIM_ENEMY + String.valueOf(((Boss) enemy).isUnique()) + "\n";
        }
        else if(enemy instanceof Boss)
        {
            results = results + "type" + Reflexioner.DELIM_ENEMY + "boss" + "\n";
            results = results + "beamdelay" + Reflexioner.DELIM_ENEMY + String.valueOf(((Boss) enemy).getBeam_std()) + "\n";
            results = results + "attack_ratio" + Reflexioner.DELIM_ENEMY + String.valueOf(((Boss) enemy).getRatio()) + "\n";
            results = results + "guide" + Reflexioner.DELIM_ENEMY + String.valueOf(((Boss) enemy).isGuide()) + "\n";
            results = results + "missiles" + Reflexioner.DELIM_ENEMY + String.valueOf(((Boss) enemy).getMissiles()) + "\n";
            results = results + "unique" + Reflexioner.DELIM_ENEMY + String.valueOf(((Boss) enemy).isUnique()) + "\n";
        }
        else if(enemy instanceof BigEnemy)
        {
            results = results + "type" + Reflexioner.DELIM_ENEMY + "bigenemy" + "\n";
            results = results + "guide" + Reflexioner.DELIM_ENEMY + String.valueOf(((BigEnemy) enemy).isGuide()) + "\n";
            results = results + "missiles" + Reflexioner.DELIM_ENEMY + String.valueOf(((BigEnemy) enemy).getMissiles()) + "\n";
        }
        results = results + "hp" + Reflexioner.DELIM_ENEMY + enemy.getMax_hp() + "\n";
        results = results + "size" + Reflexioner.DELIM_ENEMY + enemy.getR() + "\n";
        results = results + "speed_x" + Reflexioner.DELIM_ENEMY + enemy.getDx() + "\n";
        results = results + "speed_y" + Reflexioner.DELIM_ENEMY + enemy.getDy() + "\n";
        results = results + "firedelay" + Reflexioner.DELIM_ENEMY + enemy.getMax_energy() + "\n";
        results = results + "damage" + Reflexioner.DELIM_ENEMY + enemy.getDamage() + "\n";        
        
        return results;
    }
    
    public static Enemy stringToEnemy(String str)
    {
        Enemy enemy = new Enemy();
        enemy.setColor(Reflexioner.color_enemy);
        enemy.setX(Arena.maxWidth());
        enemy.setY(10);
        enemy.setR(Reflexioner.getEnemy_r());
        enemy.setColor(Reflexioner.color_enemy);
        enemy.setHp(500);
        enemy.setMax_hp(500);
        enemy.setDamage((enemy.getHp() / 10));
        
        StringTokenizer stoken = new StringTokenizer(str, "\n");
        StringTokenizer keyToken;
        String lines = "", key = "", target = "";
        while(stoken.hasMoreTokens())
        {
            lines = stoken.nextToken().trim();
            if(lines.startsWith("#")) continue;
            keyToken = new StringTokenizer(lines, Reflexioner.DELIM_ENEMY);
            try
            {
                key = keyToken.nextToken();                
                target = keyToken.nextToken();
                if(key.equalsIgnoreCase("type"))
                {
                    if(target.equalsIgnoreCase("boss_3"))
                    {
                        enemy = new HyperBoss();
                        enemy.setColor(Reflexioner.color_bigenemy);
                        enemy.setX(Arena.maxWidth());
                        enemy.setY(10);
                        enemy.setR(Reflexioner.getEnemy_r() * 2);
                        enemy.setColor(Reflexioner.color_enemy);
                        enemy.setHp(5000);
                        enemy.setMax_hp(5000);
                        enemy.setDamage((enemy.getHp() / 10));
                        ((Boss) enemy).setBeam_energy(300);
                        ((Boss) enemy).setRatio(0.9);
                    }
                    else if(target.equalsIgnoreCase("boss_2"))
                    {
                        enemy = new ExtremeBoss();
                        enemy.setColor(Reflexioner.color_bigenemy);
                        enemy.setX(Arena.maxWidth());
                        enemy.setY(10);
                        enemy.setR(Reflexioner.getEnemy_r() * 2);
                        enemy.setColor(Reflexioner.color_enemy);
                        enemy.setHp(5000);
                        enemy.setMax_hp(5000);
                        enemy.setDamage((enemy.getHp() / 10));
                        ((Boss) enemy).setBeam_energy(300);
                        ((Boss) enemy).setRatio(0.9);
                    }
                    else if(target.equalsIgnoreCase("boss"))
                    {
                        enemy = new Boss();
                        enemy.setColor(Reflexioner.color_bigenemy);
                        enemy.setX(Arena.maxWidth());
                        enemy.setY(10);
                        enemy.setR(Reflexioner.getEnemy_r() * 2);
                        enemy.setColor(Reflexioner.color_enemy);
                        enemy.setHp(5000);
                        enemy.setMax_hp(5000);
                        enemy.setDamage((enemy.getHp() / 10));
                        ((Boss) enemy).setBeam_energy(300);
                        ((Boss) enemy).setRatio(0.9);
                    }
                    else if(target.equalsIgnoreCase("bigenemy"))
                    {
                        enemy = new BigEnemy();
                        enemy.setColor(Reflexioner.color_bigenemy);
                        enemy.setX(Arena.maxWidth());
                        enemy.setY(10);
                        enemy.setR(Reflexioner.getEnemy_r());
                        enemy.setColor(Reflexioner.color_enemy);
                        enemy.setHp(500);
                        enemy.setMax_hp(500);
                        enemy.setDamage((enemy.getHp() / 10));
                    }
                }
                else if(key.equalsIgnoreCase("hp"))
                {
                    enemy.setMax_hp(Long.parseLong(target));
                    enemy.setHp(Long.parseLong(target));
                }
                else if(key.equalsIgnoreCase("size"))
                {
                    enemy.setR(Integer.parseInt(target));
                }
                else if(key.equalsIgnoreCase("speed_x"))
                {
                    enemy.setDx(Integer.parseInt(target));
                }
                else if(key.equalsIgnoreCase("speed_y"))
                {
                    enemy.setDy(Integer.parseInt(target));
                }
                else if(key.equalsIgnoreCase("firedelay"))
                {
                    enemy.setMax_energy(Long.parseLong(target));
                    enemy.setEnergy(Long.parseLong(target));
                }
                else if(key.equalsIgnoreCase("damage"))
                {
                    enemy.setDamage(Long.parseLong(target));
                }
                else if(key.equalsIgnoreCase("guide"))
                {
                    ((BigEnemy) enemy).setGuide(Boolean.parseBoolean(target));
                }
                else if(key.equalsIgnoreCase("missiles"))
                {
                    ((BigEnemy) enemy).setMissiles(Integer.parseInt(target));
                }
                else if(key.equalsIgnoreCase("beamdelay"))
                {
                    ((Boss) enemy).setBeam_std(Integer.parseInt(target));
                    ((Boss) enemy).setBeam_energy(0);
                }
                else if(key.equalsIgnoreCase("attack_ratio"))
                {
                    ((Boss) enemy).setRatio(Double.parseDouble(target));
                }
                else if(key.equalsIgnoreCase("unique"))
                {
                    ((Boss) enemy).setUnique(Boolean.parseBoolean(target));
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        
        
        return enemy;
    }

    public EnemyPattern getDefaultPattern()
    {
        return defaultPattern;
    }

    public void setDefaultPattern(EnemyPattern defaultPattern)
    {
        this.defaultPattern = defaultPattern;
    }
    public Integer getEnemyLimit()
    {
		return enemyLimit;
	}
	public void setEnemyLimit(Integer enemyLimit)
	{
		this.enemyLimit = enemyLimit;
	}
	public String getDescription()
    {
        return description;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }
    public String getKoreanDescription()
    {
        return koreanDescription;
    }
    public void setKoreanDescription(String koreanDescription)
    {
        this.koreanDescription = koreanDescription;
    }
    public Integer getStartItemM() {
		return startItemM;
	}
	public void setStartItemM(Integer startItemM) {
		this.startItemM = startItemM;
	}
	public Integer getStartItemD() {
		return startItemD;
	}
	public void setStartItemD(Integer startItemD) {
		this.startItemD = startItemD;
	}
	public Integer getStartItemS() {
		return startItemS;
	}
	public void setStartItemS(Integer startItemS) {
		this.startItemS = startItemS;
	}
	public Integer getStartItemA() {
		return startItemA;
	}
	public void setStartItemA(Integer startItemA) {
		this.startItemA = startItemA;
	}
	public Integer getStartItemK() {
		return startItemK;
	}
	public void setStartItemK(Integer startItemK) {
		this.startItemK = startItemK;
	}
	public Integer getStartItemE() {
		return startItemE;
	}
	public void setStartItemE(Integer startItemE) {
		this.startItemE = startItemE;
	}
	public Integer getStartItemG() {
		return startItemG;
	}
	public void setStartItemG(Integer startItemG) {
		this.startItemG = startItemG;
	}
	public Integer getAvailableContinues() {
		return availableContinues;
	}
	public void setAvailableContinues(Integer availableContinues) {
		this.availableContinues = availableContinues;
	}
	public Integer getContinueType() {
		return continueType;
	}
	public void setContinueType(Integer continueType) {
		this.continueType = continueType;
	}
	public Boolean getSpaceShipSelectable() {
		return spaceShipSelectable;
	}
	public void setSpaceShipSelectable(Boolean spaceShipSelectable) {
		this.spaceShipSelectable = spaceShipSelectable;
	}
	public BigInteger getDeadLine() {
		return deadLine;
	}
	public void setDeadLine(BigInteger deadLine) {
		this.deadLine = deadLine;
	}
	@Override
	public int compareTo(ReflexScenario scen)
    {
        return new Long(getSerials()).compareTo(new Long(scen.getSerials()));
    }
	public boolean customPhase(Arena arena, ArenaThread t)
	{
		return true;
	}
	public final void actPhase(Arena arena, ArenaThread t)
	{
		if(isCustomPhase())
		{
			if(customPhase(arena, t)) return;
		}
		
		Missile target_missile = null;
        Item target_item = null, newItem = null;
        Enemy target_enemy = null, newEnemy = null;
        BigEnemy newBigEnemy = null;
        Boss newBoss = null;
        Boom newBoom = null;
        Boom target_boom = null;
		try
        {
            arena.setBoss_exist(false);
            arena.setUnique_exist(false);
            for(Enemy e : arena.getEnemies())
            {
                if(e instanceof Boss)
                {                                
                    arena.setBoss_exist(true);
                    if(((Boss) e).isUnique())
                    {
                        arena.setUnique_exist(true);
                        break;
                    }
                }
            }
            if(defaultScenario.equals(this))
            {
                if(arena.getBossCount() >= Reflexioner.getBoss_delay())
                {                            
                    arena.setBossCount(0);
                    if(! arena.isUnique_exist())
                    {
                        for(int i=0; i<arena.getEnemies().size(); i++)
                        {
                            newBoom = new OvalBoom(7);
                            ((OvalBoom) newBoom).setMaker(i);
                            newBoom.loadImage(arena.getFile_path());
                            newBoom.setX(arena.getEnemies().get(i).getX());
                            newBoom.setY(arena.getEnemies().get(i).getY());
                            arena.getBooms().add(newBoom);
                            SoundCache.play("boom");
                        }
                        arena.getEnemies().clear();
                        newBoss = Boss.getNewInstance(arena.getFile_path(), difficulty, true);                                        
                        arena.getEnemies().add(newBoss);
                    }                            
                }
                else arena.setBossCount(arena.getBossCount() + 1);
            }
            
            for(int i=0; i<arena.getOurEnemy().size(); i++)
            {
                target_enemy = arena.getOurEnemy().get(i);
                target_enemy.update();
                if(target_enemy instanceof Boss)
                {
                    newBoss = (Boss) target_enemy;
                    
                    
                    if(newBoss.getBeam_energy() >= newBoss.getBeam_std())
                    {
                        ((Boss) target_enemy).setBeam_energy(0 - (int)(Math.random() * 100));                                            
                        arena.getMissile().addAll(((Boss)target_enemy).shootBeam(arena.getDifficulty(), i, arena.getSpaceShip()));
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
                        target_boom.loadImage(arena.getFile_path());
                        arena.getBooms().add(target_boom);
                        SoundCache.play("boom");
                    }
                }
                if (target_enemy.getEnergy() >= target_enemy.getMax_energy())
                {
                    arena.getMissile().addAll(target_enemy.fire(arena.getDifficulty(), i, arena.getSpaceShip(), arena.getEnemies(), arena.getFile_path()));
                }
            }
            for (int i = 0; i < arena.getEnemies().size(); i++)
            {
                target_enemy = arena.getEnemies().get(i);
                target_enemy.update();
                //System.out.println(enemies.get(i).ready_to_fire);
                if(target_enemy instanceof Boss)
                {
                    newBoss = (Boss) target_enemy;
                    
                    
                    if(newBoss.getBeam_energy() >= newBoss.getBeam_std())
                    {
                        ((Boss)target_enemy).setBeam_energy(0 - (int)(Math.random() * 100));                                            
                        arena.getMissile().addAll(((Boss) target_enemy).shootBeam(arena.getDifficulty(), i, arena.getSpaceShip()));
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
                        target_boom.loadImage(arena.getFile_path());
                        arena.getBooms().add(target_boom);
                        SoundCache.play("boom");
                    }
                }
                if (target_enemy.getEnergy() >= target_enemy.getMax_energy())
                {
                    arena.getMissile().addAll(target_enemy.fire(arena.getDifficulty(), i, arena.getSpaceShip(), arena.getEnemies(), arena.getFile_path()));
                    if(target_enemy instanceof SealedEnemy)
                    {
                        newBoom = new OvalBoom();
                        ((OvalBoom) newBoom).setMaker(i);
                        newBoom.loadImage(arena.getFile_path());
                        newBoom.setX(target_enemy.getX());
                        newBoom.setY(target_enemy.getY());
                        ((OvalBoom) newBoom).setRx((int)Math.round(target_enemy.getR() * 0.3));
                        arena.getBooms().add(newBoom);
                        SoundCache.play("boom");
                    }
                }
                    
            }
        } 
        catch (Exception e1)
        {
            e1.printStackTrace();
        }
        for (int i = 0; i < arena.getBooms().size(); i++)
        {
            try
            {
                arena.getBooms().get(i).update();
            } 
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        int k = 0;
        while (k < arena.getBooms().size())
        {
            if (arena.getBooms().get(k).getHp() <= 0)
            {
                arena.getBooms().remove(k);
            } 
            else
                k++;
        }
        k = 0;
        while (k < arena.getMissile().size())
        {                            
            target_missile = arena.getMissile().get(k);
            if (! arena.getMissile().get(k).isLaunched())
            {
                if(! (target_missile instanceof Beam))
                {
                    newBoom = new OvalBoom();
                    ((OvalBoom) newBoom).setMaker(arena.getMissile().get(k).getOwner());
                    newBoom.loadImage(arena.getFile_path());
                    newBoom.setX(target_missile.getX());
                    newBoom.setY(target_missile.getY());
                    ((OvalBoom) newBoom).setRx(4);
                    arena.getBooms().add(newBoom);
                }
                arena.getMissile().remove(k);        
                SoundCache.play("boom");
            } 
            else if (arena.getMissile().get(k).getHp() <= 0)
            {
                if(! (target_missile instanceof Beam))
                {
                    newBoom = new OvalBoom();
                    ((OvalBoom) newBoom).setMaker(arena.getMissile().get(k).getOwner());
                    newBoom.loadImage(arena.getFile_path());
                    newBoom.setX(target_missile.getX());
                    newBoom.setY(target_missile.getY());
                    ((OvalBoom) newBoom).setRx(4);
                    arena.getBooms().add(newBoom);
                }
                if(arena.getMissile().get(k) instanceof EnemyMissile)
                {
                	List<Enemy> getEnemies = ((EnemyMissile) arena.getMissile().get(k)).getEnemies();
                    try
                    {
                        while(getEnemies.size() + arena.getEnemies().size() > Reflexioner.max_enemies)
                        {
                            getEnemies.remove(0);
                        }
                    } 
                    catch (Exception e)
                    {
                        
                    }
                    arena.getEnemies().addAll(getEnemies);
                }
                else if(arena.getMissile().get(k) instanceof HelperSpread)
                {
                	List<Enemy> getEnemies = ((HelperSpread) arena.getMissile().get(k)).open(arena.getSpaceShip(), arena.getDifficulty_mode(), arena.getFile_path());
                    try
                    {
                        while(getEnemies.size() + arena.getOurEnemy().size() > Reflexioner.max_enemies)
                        {
                            getEnemies.remove(0);
                        }
                    } 
                    catch (Exception e)
                    {
                        
                    }
                    arena.getOurEnemy().addAll(getEnemies);
                }
                arena.getMissile().remove(k);            
                SoundCache.play("boom");
            } 
            else if(((target_missile instanceof GuidedMissile)) 
                    && (target_missile.getX() < -500 || target_missile.getX() >= Arena.maxWidth() + 500
                || target_missile.getY() < -500 || target_missile.getY() >= Arena.maxHeight() + 500))
            {
                if(arena.getMissile().get(k) instanceof EnemyMissile)
                {
                    List<Enemy> getEnemies = ((EnemyMissile) arena.getMissile().get(k)).getEnemies();
                    try
                    {
                        while(getEnemies.size() + arena.getEnemies().size() > Reflexioner.max_enemies)
                        {
                            getEnemies.remove(0);
                        }
                    } 
                    catch (Exception e)
                    {
                        
                    }
                    arena.getEnemies().addAll(getEnemies);
                }
                arena.getMissile().remove(k);
            }
            else if((! ((target_missile instanceof Beam) || (target_missile instanceof Raser)))
                &&    (target_missile.getX() < -100 || target_missile.getX() >= Arena.maxWidth() + 100
                || target_missile.getY() < -100 || target_missile.getY() >= Arena.maxHeight() + 100))
            {
                if(arena.getMissile().get(k) instanceof EnemyMissile)
                {
                    List<Enemy> getEnemies = ((EnemyMissile) arena.getMissile().get(k)).getEnemies();
                    try
                    {
                        while(getEnemies.size() + arena.getEnemies().size() > Reflexioner.max_enemies)
                        {
                            getEnemies.remove(0);
                        }
                    } 
                    catch (Exception e)
                    {
                        
                    }
                    arena.getEnemies().addAll(getEnemies);
                }
                arena.getMissile().remove(k);
            }
            else k++;
        }
        k = 0;
        while (k < arena.getItems().size())
        {
            target_item = arena.getItems().get(k);
            if (target_item.getHp() <= 0)
            {
                arena.getItems().remove(k);                            
            } 
            else if(target_item.getX() < -10 || target_item.getX() >= Arena.maxWidth() + 10
                    || target_item.getY() < -10 || target_item.getY() >= Arena.maxHeight() + 10)
            {
                arena.getItems().remove(k);
            }
            else k++;
        }
        k = 0;
        while (k < arena.getEnemies().size())
        {
            target_enemy = arena.getEnemies().get(k);
            if (target_enemy.getHp() <= 0)
            {                            
                arena.getSpaceShip().setPoint(arena.getSpaceShip().getPoint().add(Lint.big((long) Math.round(target_enemy.getMax_hp() * arena.getDifficulty_mode()))));
                double newItemProbability = Math.random();
                int newItemCount = target_enemy.makeItemCount(arena.getDifficulty(), arena.getDifficulty_delay(), newItemProbability);
                
                double newItemCenter = newItemCount / 2.0;
                for(int it=0; it<newItemCount; it++)
                {
                    newItem = new Item((int)(Math.random() * 10), arena.getFile_path());
                    newItem.setX(target_enemy.getX() + (int)Math.round((it - newItemCenter) * 20));
                    newItem.setY(target_enemy.getY());
                    arena.getItems().add(newItem);
                }
                
                if(target_enemy instanceof Boss)
                {
                    newBoom = new OvalBoom(10);
                    ((OvalBoom) newBoom).setMaker(k);
                    newBoom.loadImage(arena.getFile_path());
                }
                else
                {
                    newBoom = new OvalBoom(7);
                    ((OvalBoom) newBoom).setMaker(k);
                    newBoom.loadImage(arena.getFile_path());
                }
                newBoom.setX(target_enemy.getX());
                newBoom.setY(target_enemy.getY());
                arena.getBooms().add(newBoom);
                arena.getEnemies().remove(k);    
                SoundCache.play("boom");
                arena.catchEnemies = arena.catchEnemies.add(Lint.big(1));
            } 
            else k++;
        }
        k = 0;
        while (k < arena.getOurEnemy().size())
        {
            target_enemy = arena.getOurEnemy().get(k);
            if (target_enemy.getHp() <= 0)
            {                                                    
                if(target_enemy instanceof Boss)
                {
                    newBoom = new OvalBoom(10);
                    ((OvalBoom) newBoom).setMaker(arena.getMissile().get(k).getOwner());
                    newBoom.loadImage(arena.getFile_path());
                }
                else
                {
                    newBoom = new OvalBoom(7);
                    ((OvalBoom) newBoom).setMaker(k);
                    newBoom.loadImage(arena.getFile_path());
                }
                newBoom.setX(target_enemy.getX());
                newBoom.setY(target_enemy.getY());
                arena.getBooms().add(newBoom);
                arena.getOurEnemy().remove(k);    
                SoundCache.play("boom");
            } 
            else k++;
        }
        for(int i=0; i<arena.getItems().size(); i++)
        {
            try
            {
                if(Arena.isAutoControl())
                {
                    arena.getItems().get(i).update(arena.getSpaceShip().getX(), arena.getSpaceShip().getY());
                }
                else
                {
                    arena.getItems().get(i).update();
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < arena.getMissile().size(); i++)
        {
            try
            {
                arena.getMissile().get(i).update();
            } 
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        try
        {
            arena.getSpaceShip().update();
        } 
        catch (Exception e1)
        {
            e1.printStackTrace();
        }
        if(arena.getDifficulty() == 100)
        {
            if(arena.getTestEnemy().size() >= 1)
            {                
                arena.getEnemies().addAll(arena.getTestEnemy());
            }
        }
        try
        {
            if((! arena.isUnique_exist()) && (defaultScenario.equals(this)))
            {
                double newEnemyProbability = Math.random();
                if(arena.getDifficulty() < arena.getDifficulty_delay())
                {                                    
                    if (newEnemyProbability >= 0.95 && arena.getEnemies().size() <= Reflexioner.max_enemies)
                    {
                        newEnemy = new Enemy(arena.getFile_path());
                        newEnemy.setX((int) (Math.random() * Arena.maxWidth()));
                        newEnemy.setHp(newEnemy.getHp() + (int) (arena.getDifficulty() / 200));
                        newEnemy.setMax_hp(newEnemy.getHp());
                        newEnemy.setMax_energy(200 - (int)(arena.getDifficulty() / 1000.0));                                        
                        newEnemy.setDamage(50 + (arena.getDifficulty() / 100));
                        if(newEnemy.getMax_energy() < 100) newEnemy.setMax_energy(100);
                        if(arena.getDifficulty_mode() >= 3.0) newEnemy.setMax_energy(newEnemy.getMax_energy() / 2);
                        if(arena.getDifficulty_mode() >= 4.0) newEnemy.setMax_energy(newEnemy.getMax_energy() / 2);
                        
                        arena.getEnemies().add(newEnemy);
                    }
                }
                else if(arena.getDifficulty() < arena.getDifficulty_delay() * 2)
                {
                    if (newEnemyProbability >= 0.98 && arena.getEnemies().size() <= Reflexioner.max_enemies)
                    {
                        newBigEnemy = new BigEnemy(arena.getFile_path());
                        newBigEnemy.setGuide(true);
                        newBigEnemy.setX((int) (Math.random() * Arena.maxWidth()));
                        newBigEnemy.setHp((int)((newBigEnemy.getHp() + (int) (arena.getDifficulty() / 200)) * 1.25));
                        newBigEnemy.setMax_hp(newBigEnemy.getHp());
                        newBigEnemy.setMax_energy(200 - (int) (arena.getDifficulty() / 1000));
                        newBigEnemy.setDamage(50 + (arena.getDifficulty() / 100));
                        if(newBigEnemy.getMax_energy() < 100) newBigEnemy.setMax_energy(100);
                        if(arena.getDifficulty_mode() >= 3.0) newBigEnemy.setMax_energy(newBigEnemy.getMax_energy() / 2);
                        if(arena.getDifficulty_mode() >= 4.0) newBigEnemy.setMax_energy(newBigEnemy.getMax_energy() / 2);
                        arena.getEnemies().add(newBigEnemy);
                    }
                    else if (newEnemyProbability >= 0.95 && arena.getEnemies().size() <= Reflexioner.max_enemies)
                    {
                        newBigEnemy = new BigEnemy(arena.getFile_path());
                        newBigEnemy.setGuide(false);
                        newBigEnemy.setMissiles(1 + (int)Math.round(Math.random() * 1.5));
                        newBigEnemy.setX((int) (Math.random() * Arena.maxWidth()));
                        newBigEnemy.setHp((int)((newBigEnemy.getHp() + (int) (arena.getDifficulty() / 200)) * 1.25));
                        newBigEnemy.setMax_hp(newBigEnemy.getHp());
                        newBigEnemy.setMax_energy(200 - (int)(arena.getDifficulty() / 1000));
                        newBigEnemy.setDamage(50 + (arena.getDifficulty() / 100));
                        if(newBigEnemy.getMax_energy() < 100) newBigEnemy.setMax_energy(100);
                        if(arena.getDifficulty_mode() >= 3.0) newBigEnemy.setMax_energy(newBigEnemy.getMax_energy() / 2);
                        if(arena.getDifficulty_mode() >= 4.0) newBigEnemy.setMax_energy(newBigEnemy.getMax_energy() / 2);
                        arena.getEnemies().add(newBigEnemy);
                    }
                    else if (newEnemyProbability >= 0.90 && arena.getEnemies().size() <= Reflexioner.max_enemies)
                    {
                        newEnemy = new Enemy(arena.getFile_path());
                        newEnemy.setX((int) (Math.random() * Arena.maxWidth()));
                        newEnemy.setHp((int)((newEnemy.getHp() + (int) (arena.getDifficulty() / 200)) * 1.25));
                        newEnemy.setMax_hp(newEnemy.getHp());
                        newEnemy.setMax_energy(200 - (int)(arena.getDifficulty() / 1000));
                        newEnemy.setDamage(50 + (arena.getDifficulty() / 100));
                        if(newEnemy.getMax_energy() < 100) newEnemy.setMax_energy(100);
                        if(arena.getDifficulty_mode() >= 3.0) newEnemy.setMax_energy(newEnemy.getMax_energy() / 2);
                        if(arena.getDifficulty_mode() >= 4.0) newEnemy.setMax_energy(newEnemy.getMax_energy() / 2);
                        arena.getEnemies().add(newEnemy);
                    }
                }
                else
                {
                    if (newEnemyProbability >= 0.99 && arena.getEnemies().size() <= Reflexioner.max_enemies)
                    {
                        newEnemy = new SealedEnemy(arena.getFile_path());
                        newEnemy.setX((int) (Math.random() * Arena.maxWidth()));
                        newEnemy.setHp(newEnemy.getHp() + (int) (arena.getDifficulty() / 300));
                        newEnemy.setMax_hp(newEnemy.getHp());
                        newEnemy.setMax_energy(200 - (int)(arena.getDifficulty() / 1000));
                        newEnemy.setDamage(50 + (arena.getDifficulty() / 100));
                        ((SealedEnemy) newEnemy).setSeal_weakness(50 - (int)(arena.getDifficulty() / 100000.0));
                        if(((SealedEnemy) newEnemy).getSeal_weakness() < 20) ((SealedEnemy) newEnemy).setSeal_weakness(20); 
                        if(newEnemy.getMax_energy() < 100) newEnemy.setMax_energy(100);
                        if(arena.getDifficulty_mode() >= 3.0) newEnemy.setMax_energy(newEnemy.getMax_energy() / 2);
                        if(arena.getDifficulty_mode() >= 4.0) newEnemy.setMax_energy(newEnemy.getMax_energy() / 2);
                        arena.getEnemies().add(newEnemy);
                    }
                    else if (newEnemyProbability >= 0.97 && arena.getEnemies().size() <= Reflexioner.max_enemies)
                    {
                        newBigEnemy = new BigEnemy(arena.getFile_path());
                        newBigEnemy.setGuide(true);
                        newBigEnemy.setMissiles(1 + (int)Math.round(Math.random() * 1.5));
                        newBigEnemy.setX((int) (Math.random() * Arena.maxWidth()));
                        newBigEnemy.setHp((newBigEnemy.getHp() + (int) (arena.getDifficulty() / 200)) * 2);
                        newBigEnemy.setMax_hp(newBigEnemy.getHp());
                        newBigEnemy.setMax_energy(200 - (int)(arena.getDifficulty() / 1000));
                        newBigEnemy.setDamage(50 + (arena.getDifficulty() / 100));
                        if(newBigEnemy.getMax_energy() < 100) newBigEnemy.setMax_energy(100);
                        if(arena.getDifficulty_mode() >= 3.0) newBigEnemy.setMax_energy(newBigEnemy.getMax_energy() / 2);
                        if(arena.getDifficulty_mode() >= 4.0) newBigEnemy.setMax_energy(newBigEnemy.getMax_energy() / 2);
                        arena.getEnemies().add(newBigEnemy);
                    }
                    else if (newEnemyProbability >= 0.95 && arena.getEnemies().size() <= Reflexioner.max_enemies)
                    {
                        newBigEnemy = new BigEnemy(arena.getFile_path());
                        newBigEnemy.setMissiles(2 + (int) Math.round(Math.random() * 2.5));
                        newBigEnemy.setX((int) (Math.random() * Arena.maxWidth()));
                        newBigEnemy.setHp((newBigEnemy.getHp() + (int) (difficulty / 200)) * 2);
                        newBigEnemy.setMax_hp(newBigEnemy.getHp());
                        newBigEnemy.setMax_energy(200 - (int)(arena.getDifficulty() / 1000));
                        newBigEnemy.setDamage(50 + (arena.getDifficulty() / 100));
                        if(newBigEnemy.getMax_energy() < 100) newBigEnemy.setMax_energy(100);
                        if(arena.getDifficulty_mode() >= 3.0) newBigEnemy.setMax_energy(newBigEnemy.getMax_energy() / 2);
                        if(arena.getDifficulty_mode() >= 4.0) newBigEnemy.setMax_energy(newBigEnemy.getMax_energy() / 2);
                        arena.getEnemies().add(newBigEnemy);
                    }
                    else if (newEnemyProbability >= 0.90 && arena.getEnemies().size() <= Reflexioner.max_enemies)
                    {
                        newEnemy = new Enemy(arena.getFile_path());
                        newEnemy.setX((int) (Math.random() * Arena.maxWidth()));
                        newEnemy.setHp(newEnemy.getHp() + (int) (arena.getDifficulty() / 200));
                        newEnemy.setMax_hp(newEnemy.getHp());
                        newEnemy.setMax_energy(200 - (int)(arena.getDifficulty() / 1000));
                        newEnemy.setDamage(50 + (arena.getDifficulty() / 100));
                        if(newEnemy.getMax_energy() < 100) newEnemy.setMax_energy(100);
                        if(arena.getDifficulty_mode() >= 3.0) newEnemy.setMax_energy(newEnemy.getMax_energy() / 2);
                        if(arena.getDifficulty_mode() >= 4.0) newEnemy.setMax_energy(newEnemy.getMax_energy() / 2);
                        arena.getEnemies().add(newEnemy);
                    }
                }
            }
            else if(! defaultScenario.equals(this))
            {
                if(getPatterns() != null && (! arena.isUnique_exist()))
                {
                    for(EnemyPattern s : getPatterns())
                    {
                        if((arena.getDifficulty() >= s.getMin_delay().longValue()) && (arena.getDifficulty() <= s.getMax_delay().longValue() || s.getMax_delay().longValue() <= -1))
                        {
                            if(Math.random() <= s.getRatio().doubleValue() && arena.getEnemies().size() <= getEnemyLimit().intValue())
                            {
                                newEnemy = s.createEnemy(arena.getFile_path(), arena.getDifficulty());
                                newEnemy.loadImage(arena.getFile_path());
                                newEnemy.setDamage(Math.round(newEnemy.getDamage() + (s.getAddDamageRatio().doubleValue() * arena.getDifficulty())));
                                newEnemy.setMax_hp(Math.round(newEnemy.getMax_hp() + (s.getAddHPRatio().doubleValue() * arena.getDifficulty())));
                                newEnemy.setHp(newEnemy.getMax_hp());
                                newEnemy.init();
                                arena.getEnemies().add(newEnemy);
                            }
                        }
                    }
                }
                if(getDefaultPattern() != null && (! arena.isUnique_exist()))
                {
                    if(Math.random() <= getDefaultPattern().getRatio() && arena.getEnemies().size() <= getEnemyLimit())
                    {
                        newEnemy = getDefaultPattern().createEnemy(arena.getFile_path(), arena.getDifficulty());
                        newEnemy.loadImage(arena.getFile_path());
                        newEnemy.setDamage(Math.round(newEnemy.getDamage() + (getDefaultPattern().getAddDamageRatio() * arena.getDifficulty())));
                        newEnemy.setMax_hp(Math.round(newEnemy.getMax_hp() + (getDefaultPattern().getAddHPRatio()     * arena.getDifficulty())));
                        newEnemy.setHp(newEnemy.getMax_hp());
                        newEnemy.init();
                        arena.getEnemies().add(newEnemy);
                    }
                }
            }
        } 
        catch (Exception e1)
        {
            e1.printStackTrace();
        }
        for (int j = 0; j < arena.getMissile().size(); j++)
        {
            //System.out.println(j + " : " + missile.get(j).getOwner());
            try
            {
                target_missile = arena.getMissile().get(j);
            } 
            catch (Exception e1)
            {
                e1.printStackTrace();
                continue;
            }
            
            for (int i = 0; i < arena.getEnemies().size(); i++)
            {
                try
                {
                    target_enemy = arena.getEnemies().get(i);
                    if (target_missile.getOwner() == Missile.SPACESHIP && target_enemy.collapse(target_missile))
                    {
                        target_enemy.setHp(target_enemy.getHp() - target_missile.getDamage());
                        if(target_missile instanceof PulseMissile)
                        {
                            target_boom = new OvalBoom(target_missile.getW());
                            ((OvalBoom) target_boom).setMaker(arena.getMissile().get(j).getOwner());
                            target_boom.loadImage(arena.getFile_path());
                            target_boom.setX(target_missile.getX());
                            target_boom.setY(target_missile.getY());
                            arena.getBooms().add(target_boom);
                            SoundCache.play("boom");
                            target_missile.setHp(0);
                        }
                        else if(target_missile instanceof ReflexMissile)
                        {
                            target_missile.setHp(target_missile.getHp() - 100);
                            target_missile.setDy(-1 * target_missile.getDy());
                            target_boom = new OvalBoom(3);
                            ((OvalBoom) target_boom).setMaker(arena.getMissile().get(j).getOwner());
                            target_boom.loadImage(arena.getFile_path());
                            target_boom.setX(target_missile.getX());
                            target_boom.setY(target_missile.getY());
                            arena.getBooms().add(target_boom);
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
                if (target_missile.getOwner() != Missile.SPACESHIP && arena.getSpaceShip().collapse(target_missile))
                {
                    arena.getSpaceShip().setHp(arena.getSpaceShip().getHp() - target_missile.getDamage());
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
                for(int l=0; l<arena.getOurEnemy().size(); l++)
                if(target_missile.getOwner() != Missile.SPACESHIP && arena.getOurEnemy().get(l).collapse(target_missile))
                {
                    arena.getOurEnemy().get(l).setHp(arena.getOurEnemy().get(l).getHp() - target_missile.getDamage());
                    if(! ((target_missile instanceof Beam) || (target_missile instanceof Raser)))
                        target_missile.setHp(0);
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        
        for (int i = 0; i < arena.getEnemies().size(); i++)
        {
            try
            {
                target_enemy = arena.getEnemies().get(i);
                if(arena.getSpaceShip().collapse(target_enemy))
                {
                    arena.getSpaceShip().setHp(arena.getSpaceShip().getHp() - target_enemy.getHp());
                    newBoom = new OvalBoom(7);
                    ((OvalBoom) newBoom).setMaker(i);
                    newBoom.loadImage(arena.getFile_path());
                    newBoom.setX(target_enemy.getX());
                    newBoom.setY(target_enemy.getY());
                    arena.getBooms().add(newBoom);
                    SoundCache.play("boom");
                    target_enemy.setHp(0);
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        for(int i=0; i<arena.getItems().size(); i++)
        {
            try
            {
                if(arena.getSpaceShip().collapse(arena.getItems().get(i)))
                {
                    arena.getItems().get(i).setHp(0);
                    newBoom = new ItemUseBoom(6);
                    newBoom.loadImage(arena.getFile_path());
                    newBoom.setX(arena.getItems().get(i).getX());
                    newBoom.setY(arena.getItems().get(i).getY());
                    arena.getBooms().add(newBoom);
                    arena.catchItems = arena.catchItems.add(Lint.big(1));
                    arena.applyItem(arena.getItems().get(i).getType());
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
            if(arena.getSpaceShip().getHp() <= 0 && arena.getFinish_count() < 0)
            {
                for(int l=0; l<arena.getOurEnemy().size(); l++)
                {
                    arena.getOurEnemy().get(l).setHp(0);
                }
                newBoom = new OvalBoom(arena.getSpaceShip().getR() / 2, Missile.SPACESHIP);
                newBoom.setX(arena.getSpaceShip().getX());
                newBoom.setY(arena.getSpaceShip().getY());
                newBoom.loadImage(arena.getFile_path());
                arena.getBooms().add(newBoom);
                SoundCache.play("boom");
                arena.setFinish_count((arena.getSpaceShip().getR() / 2) + 5);
            }
        }
        catch (Exception e1)
        {
            e1.printStackTrace();
        }
        
        
        if(arena.getFinish_count() == 0)
        {
            arena.game_finish();
        }
        arena.decreaseFinishCount();
        
        if((Lint.big(0).compareTo(getDeadLine()) < 0) && Lint.big(arena.getDifficulty()).compareTo(getDeadLine()) >= 1)
        {
            arena.game_finish();
        }
        
        try
        {
            if (arena.getSp() != null)
                arena.getSp().update(arena.getSpaceShip().getHp(), arena.getSpaceShip().getMax_hp(), arena.getSpaceShip().getPoint(), arena.getSpaceShip().getEnergy(), arena.getSpaceShip().getMax_energy());
        } 
        catch (Exception e1)
        {
            e1.printStackTrace();
        }    
        
        int decoSize = arena.getDecorates().size();
        if(decoSize <= arena.getDecorationMax() && Math.random() >= (0.7 - (arena.getDifficulty() / 100000.0)))
        {
            int decoSpeed = (int) (arena.getPoint().divide(arena.getDecorationPointUnit()).longValue() * 3);
            if(decoSpeed > 50) decoSpeed = 50;
            decoSpeed = (int)((3 * Math.random()) + decoSpeed + (arena.getDifficulty() / 500));
            if(decoSpeed > 100) decoSpeed = 100;
            arena.getDecorates().add(new ReflexDecorate("star", (int) (Math.random() * Arena.maxWidth()), 0, decoSpeed, (int)(3 * Math.random() + 1), arena.getFile_path()));
            if(arena.getDecorationMax() - decoSize >= 6)
            {
                for(int i=0; i<5; i++)
                {
                    arena.getDecorates().add(new ReflexDecorate("star", (int) (Math.random() * Arena.maxWidth()), 0, decoSpeed, (int)(3 * Math.random() + 1), arena.getFile_path()));
                }
            }
        }
        for(int i=0; i<arena.getDecorates().size(); i++)
        {
            if(arena.getDecorates().get(i) != null)
            {
                arena.getDecorates().get(i).update();
                if(arena.getDecorates().get(i).getX() < -10 || arena.getDecorates().get(i).getX() > Arena.maxWidth()
                        || arena.getDecorates().get(i).getY() < -10 || arena.getDecorates().get(i).getY() > Arena.maxHeight())
                    arena.getDecorates().get(i).setHp(0);
            }
        }
        k = 0;
        while (k < arena.getDecorates().size())
        {
            if (arena.getDecorates().get(k).getHp() <= 0)
            {
                arena.getDecorates().remove(k);
            } 
            else
                k++;
        }
        
        if(arena.getDifficulty() < (Integer.MAX_VALUE * (long)100) - 1)
        {
            if(! arena.isUnique_exist())
            {
                arena.setDifficulty(arena.getDifficulty() + 1);
            }
        }
        
        if(arena.getTimeout() >= 0)
        {
            if(arena.getDifficulty() >= arena.getTimeout())
            {
                arena.game_finish();
            }
        }
        
        //showPlayerInfo();
        if(Reflexioner.max_enemies <= 30 && arena.getDifficulty() % 5000 == 0)
        {
            Reflexioner.max_enemies += 1;
        }
        
        if(arena.getSpaceShip().getFire_delay() >= 1)
        {
            arena.getSpaceShip().setFire_delay(arena.getSpaceShip().getFire_delay() - 1);
        }
        
        int unique_index = 0;
        for(int i=0; i<arena.getEnemies().size(); i++)
        {
            unique_index = -2;
            if(arena.getEnemies().get(i) instanceof Boss)
            {
                if(((Boss) arena.getEnemies().get(i)).isUnique())
                {
                    //System.out.println(enemies.get(i) + ", " + ((Boss) enemies.get(i)).isUnique());
                    unique_index = i;
                    break;
                }
            }
        }
        if(unique_index >= 0)
        {
            for(int i=0; i<arena.getEnemies().size(); i++)
            {
                if(i != unique_index) 
                {
                    if(! arena.getEnemies().get(i).isOwn_unique())
                    {
                        arena.getEnemies().get(i).setHp(0);
                    }
                }
            }
        }
        
        if(! arena.isUnique_exist())
        {
            for(int i=0; i<arena.getEnemies().size(); i++)
            {
                if(arena.getEnemies().get(i).isOwn_unique())
                {
                    arena.getEnemies().get(i).setHp(0);
                }
            }
        }
        
        if(Reflexioner.replay_save)
        {
            if(arena.getReplay().getScenes().size() < Integer.MAX_VALUE)
            {                                
                if(Reflexioner.replay_now_delay == 0)
                {
                    arena.getReplay().addScene(arena.nowState(true));
                }
                Reflexioner.replay_now_delay++;
                if(Reflexioner.replay_now_delay >= Reflexioner.replay_interval)
                {
                    Reflexioner.replay_now_delay = 0;
                }
            }
        }
        
        if(Arena.getSwift_delay() >= 1)
        {
            Arena.setSwift_delay(Arena.getSwift_delay() - 1);
        }
                                
        if(Arena.isAutoControl())
        {
            if(arena.isActive() && (! arena.isGame_pause()))
            {
                double controlRandom = Math.random();
                boolean boss_exist = false;
                int unders = 0;
                if(arena.getSpaceShip().getEnergy() / (double) arena.getSpaceShip().getMax_energy() >= 0.5)
                {
                    controlRandom = controlRandom + 0.4;
                }
                else if(arena.getSpaceShip().getEnergy() / (double) arena.getSpaceShip().getMax_energy() >= 0.2)
                {
                    controlRandom = controlRandom + 0.2;
                }
                for(Enemy e : arena.getEnemies())
                {
                    if(e != null)
                    {
                        if(Math.abs(arena.getSpaceShip().getX() - e.getX()) < 2)
                        {
                            unders++;
                        }
                        if(e instanceof Boss)
                        {
                            boss_exist = true;
                        }
                    }
                }
                if(controlRandom >= 1.7 - arena.getSpaceShip().ai_advantage_mode3(arena.getEnemies().size(), Reflexioner.max_enemies, unders, boss_exist)) arena.getSpaceShip().setMode(3);
                else if(controlRandom >= 1.7 - arena.getSpaceShip().ai_advantage_mode2(arena.getEnemies().size(), Reflexioner.max_enemies, unders, boss_exist)) arena.getSpaceShip().setMode(2);
                else arena.getSpaceShip().setMode(1);
                if(arena.getSpaceShip().getFire_delay() <= 0)
                {
                    arena.setFired_missile(arena.getSpaceShip().fire());
                    if(arena.getFired_missile() != null)
                        arena.getMissile().addAll(arena.getFired_missile());
                    //System.out.println(spaceShip.fire());
                    arena.getSpaceShip().setFire_delay(arena.getSpaceShip().afterFireDelay());
                }
                
                if(arena.getAutoControlDelay() <= 0)
                {
                    arena.control_auto();
                    arena.setAutoControlDelay(3);
                }
                else arena.setAutoControlDelay(arena.getAutoControlDelay() - 1);
            }
        }
        else if(arena.isAutoFire())
        {
            if(arena.getSpaceShip().getFire_delay() <= 0)
            {
                arena.setFired_missile(arena.getSpaceShip().fire());
                if(arena.getFired_missile() != null)
                    arena.getMissile().addAll(arena.getFired_missile());
                //System.out.println(spaceShip.fire());
                arena.getSpaceShip().setFire_delay(arena.getSpaceShip().afterFireDelay());
            }
        }
        if(arena.getMessages().size() >= 1)
        {
            try
            {
                if(arena.getMessage_delay() <= 0)
                {
                    arena.getMessages().remove(0);
                    arena.setMessage_delay(50);
                }
                else
                {
                    arena.setMessage_delay(arena.getMessage_delay() - 1);
                }
            } 
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
	}
	public final boolean isCustomPhase() {
		return customPhase;
	}
	public final void setCustomPhase(boolean customPhase) {
		this.customPhase = customPhase;
	}
	public final long getSerials() {
		return serials;
	}
	public final void setSerials(long serials) {
		if(serials == defaultScenario.getSerials())
		{
			if(! defaultScenario.equals(this))
			{
				throw new RuntimeException("Cannot use this serial number.");
			}
		}
		this.serials = serials;
	}
}
