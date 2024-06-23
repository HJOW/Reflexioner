package com.hjow.game.reflexioner.reflexioner;

import java.io.Serializable;
import java.util.Properties;

import com.hjow.game.reflexioner.mainClasses.RXUtils;

public class EnemyPattern implements Serializable
{
    private static final long serialVersionUID = 685579182641435599L;
    private Enemy enemy;
    private Long min_delay = new Long(0), max_delay = new Long(-1);
    private Double ratio = new Double(0.01), addHPRatio = new Double(0.001), addDamageRatio = new Double(0.001);
    
    
    public EnemyPattern()
    {
        
    }
    public EnemyPattern(Enemy enemy, long min, long max, double ratio)
    {
        this.enemy = enemy;
        this.min_delay = new Long(min);
        this.max_delay = new Long(max);
        this.ratio = new Double(ratio);
        this.addDamageRatio = new Double(0.01);
        this.addHPRatio = new Double(0.02);
    }
    public EnemyPattern(String str)
    {
    	Properties prop = RXUtils.extractProperty(str);
    	String strEnemy     = prop.getProperty("Enemy");
    	this.enemy          = Enemy.stringToEnemy(strEnemy);
    	this.min_delay      = new Long(prop.getProperty("MinDelay"));
        this.max_delay      = new Long(prop.getProperty("MaxDelay"));
        this.ratio          = new Double(prop.getProperty("Ratio"));
        this.addDamageRatio = new Double(prop.getProperty("AddDamageRatio"));
        this.addHPRatio     = new Double(prop.getProperty("AddHPRatio"));
    }
    public static EnemyPattern convertPattern(String str)
    {
        if(str.startsWith("boss_pattern"))
        {
            return new BossEnemyPattern(str);
        }
        else
        {
            return new EnemyPattern(str);
        }
    }
    public Properties convertProp()
    {
    	Properties prop = new Properties();
    	prop.setProperty("Enemy", Enemy.enemyToString(enemy));
    	prop.setProperty("MinDelay", "" + min_delay);
    	prop.setProperty("MaxDelay", "" + max_delay);
    	prop.setProperty("Ratio"         , "" + ratio);
    	prop.setProperty("AddDamageRatio", "" + addDamageRatio);
    	prop.setProperty("AddHPRatio"    , "" + addHPRatio);
    	return prop;
    }
    public String convertStr()
    {
    	Properties prop = convertProp();
    	return RXUtils.serializeProperty(prop);
    }
    public Enemy createEnemy(String path, long difficulty)
    {
        return enemy.clone();
    }
    public Enemy getEnemy()
    {
        return enemy;
    }
    public void setEnemy(Enemy enemy)
    {
        this.enemy = enemy;
    }
    public Long getMin_delay()
    {
        return min_delay;
    }
    public void setMin_delay(Long min_delay)
    {
        this.min_delay = min_delay;
    }
    public Long getMax_delay()
    {
        return max_delay;
    }
    public void setMax_delay(Long max_delay)
    {
        this.max_delay = max_delay;
    }
    public Double getRatio()
    {
        return ratio;
    }
    public void setRatio(Double ratio)
    {
        this.ratio = ratio;
    }
    public Double getAddHPRatio()
    {
        return addHPRatio;
    }
    public void setAddHPRatio(Double addHPRatio)
    {
        this.addHPRatio = addHPRatio;
    }
    public Double getAddDamageRatio()
    {
        return addDamageRatio;
    }
    public void setAddDamageRatio(Double addDamageRatio)
    {
        this.addDamageRatio = addDamageRatio;
    }
}
