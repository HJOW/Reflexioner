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

import java.util.Properties;

import com.hjow.game.reflexioner.mainClasses.RXUtils;
import com.hjow.game.reflexioner.mainClasses.XMLSerializableObject;

public class EnemyPattern extends XMLSerializableObject
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
        Properties prop = RXUtils.extractProperty(str, false);
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
        return RXUtils.serializeProperty(prop, false);
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
