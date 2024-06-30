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

public class BossEnemyPattern extends EnemyPattern
{
    private static final long serialVersionUID = 901997232138099521L;
    private Boolean unique = new Boolean(false);
    public BossEnemyPattern()
    {
        super();
        setEnemy(null);
    }
    public BossEnemyPattern(long min, long max, double ratio, boolean unique)
    {
        setEnemy(null);
        setMin_delay(new Long(min));
        setMax_delay(new Long(max));
        ratio = new Double(ratio);
        setAddDamageRatio(new Double(0.0));
        setAddHPRatio(new Double(0.0));
        setUnique(new Boolean(unique));
    }
    public BossEnemyPattern(String str)
    {
        Properties prop = RXUtils.extractProperty(str);
        setEnemy(null);
        setMin_delay(new Long(prop.getProperty("MinDelay")));
        setMax_delay(new Long(prop.getProperty("MaxDelay")));
        setRatio(new Double(prop.getProperty("Ratio")));
        setAddDamageRatio(new Double(prop.getProperty("AddDamageRatio")));
        setAddHPRatio(new Double(prop.getProperty("AddHPRatio")));
        setUnique(RXUtils.parseBoolean(prop.getProperty("Unique")));
    }
    @Override
    public Properties convertProp()
    {
        Properties prop = new Properties();
        prop.setProperty("MinDelay"      , "" + getMin_delay()     );
        prop.setProperty("MaxDelay"      , "" + getMax_delay()     );
        prop.setProperty("Ratio"         , "" + getRatio()         );
        prop.setProperty("AddDamageRatio", "" + getAddDamageRatio());
        prop.setProperty("AddHPRatio"    , "" + getAddHPRatio()    );
        prop.setProperty("Unique"        , "" + getUnique()    );
        return prop;
    }
    @Override
    public Enemy createEnemy(String path, long difficulty)
    {
        return Boss.getNewInstance(path, difficulty, unique.booleanValue());
    }
    public Boolean getUnique()
    {
        return unique;
    }
    public void setUnique(Boolean unique)
    {
        this.unique = unique;
    }
    @Override
    public Enemy getEnemy()
    {
        return Boss.getNewInstance(Reflexioner.getFile_path(), 5000, unique.booleanValue());
    }
}
