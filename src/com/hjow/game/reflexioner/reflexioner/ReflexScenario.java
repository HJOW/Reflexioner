package com.hjow.game.reflexioner.reflexioner;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class ReflexScenario implements Serializable, Comparable<ReflexScenario>
{
    private static final long serialVersionUID = 872068919420124788L;
    private String name, description, koreanDescription;
    private String spaceShip;
    private Boolean spaceShipSelectable;
    private Integer startItemM, startItemD, startItemS, startItemA, startItemK, startItemE, startItemG; // IReflexScenario
    private Integer difficulty, enemyLimit;
    private Long diffDelay, auth;
    private BigInteger deadLine;
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
        spaceShipSelectable = new Boolean(false);
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
    public boolean isAuthorized()
    {
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
	public int compareTo(ReflexScenario scen)
    {
        return getName().compareTo(scen.getName());
    }
}
