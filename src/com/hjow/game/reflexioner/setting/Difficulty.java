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
package com.hjow.game.reflexioner.setting;
import com.hjow.game.reflexioner.mainClasses.XMLSerializableObject;

public class Difficulty extends XMLSerializableObject
{    
    private static final long serialVersionUID = -3786474189851313009L;
    public static final double DEFAULT_UNIT_VALUE = 5.0;
    private Integer difficulty;
    public Difficulty()
    {
        difficulty = new Integer(1);
    }
    public Integer getDifficulty()
    {
        return difficulty;
    }
    public void setDifficulty(Integer difficulty)
    {
        this.difficulty = difficulty;
    }
    public Difficulty clone()
    {
        Difficulty newOne = new Difficulty();
        newOne.difficulty = new Integer(difficulty.intValue());
        return newOne;
    }    
    public String toBasicString()
    {
        return toBasicString(DEFAULT_UNIT_VALUE);
    }
    public String toBasicString(double unit)
    {
        int value = difficulty.intValue();
        String results = "";    
        
        while(value >= 1)
        {
            if(value >= 1000000)
            {
                results = results + "∬";
                value -= 1000000;
            }
            else if(value >= ((int) (100000.0 * unit)))
            {
                results = results + "∫";
                value -= (int) (100000.0 * unit);
            }
            else if(value >= 100000)
            {
                results = results + "∂";
                value -= 100000;
            }
            else if(value >= ((int) (10000.0 * unit)))
            {
                results = results + "∵";
                value -= (int) (10000.0 * unit);
            }
            else if(value >= 10000)
            {
                results = results + "♨";
                value -= 10000;
            }
            else if(value >= ((int) (1000.0 * unit)))
            {
                results = results + "★";
                value -= (int) (1000.0 * unit);
            }
            else if(value >= 1000)
            {
                results = results + "☆";
                value -= 1000;
            }
            else if(value >= ((int) (100.0 * unit)))
            {
                results = results + "◆";
                value -= (int) (100.0 * unit);
            }
            else if(value >= 100)
            {
                results = results + "◇";
                value -= 100;
            }
            else if(value >= ((int) (10.0 * unit)))
            {
                results = results + "■";
                value -= (int) (10.0 * unit);
            }
            else if(value >= 10)
            {
                results = results + "□";
                value -= 10;
            }
            else if(value >= ((int) unit))
            {
                results = results + "●";
                value -= (int) unit;
            }
            else if(value >= 1)
            {
                results = results + "○";
                value -= 1;
            }
            else break;
        }
        while(value <= -1)
        {
            if(value <= ((int) (-10.0 * unit)))
            {
                results = results + "♬";
                value += (int) (10.0 * unit);
            }
            else if(value <= (-10))
            {
                results = results + "♪";
                value += 10;
            }
            else if(value <= ((int) (-1.0 * unit)))
            {
                results = results + "♥";
                value += (int) (1.0 * unit);
            }
            else if(value <= (-1))
            {
                results = results + "♡";
                value += 1;
            }
            else break;
        }
        
        return results;
    }
    public static String starToString(long stars)
    {
        long value = stars;
        String results = "";
        while(value >= 1)
        {
            if(value >= 6)
            {
                results = results + "★";
                value -= 6;
            }
            else if(value >= 1)
            {
                results = results + "☆";
                value -= 1;
            }
        }
        return results;
    }
    public static String intToString(long v, double unit)
    {
        long value = v;
        String results = "";    
        
        while(value >= 1)
        {
            if(value >= 1000000)
            {
                results = results + "∬";
                value -= 1000000;
            }
            else if(value >= ((int) (100000.0 * unit)))
            {
                results = results + "∫";
                value -= (int) (100000.0 * unit);
            }
            else if(value >= 100000)
            {
                results = results + "∂";
                value -= 100000;
            }
            else if(value >= ((int) (10000.0 * unit)))
            {
                results = results + "∵";
                value -= (int) (10000.0 * unit);
            }            
            else if(value >= 10000)
            {
                results = results + "♨";
                value -= 10000;
            }
            else if(value >= ((int) (1000.0 * unit)))
            {
                results = results + "★";
                value -= (int) (1000.0 * unit);
            }
            else if(value >= 1000)
            {
                results = results + "☆";
                value -= 1000;
            }
            else if(value >= ((int) (100.0 * unit)))
            {
                results = results + "◆";
                value -= (int) (100.0 * unit);
            }
            else if(value >= 100)
            {
                results = results + "◇";
                value -= 100;
            }
            else if(value >= ((int) (10.0 * unit)))
            {
                results = results + "■";
                value -= (int) (10.0 * unit);
            }
            else if(value >= 10)
            {
                results = results + "□";
                value -= 10;
            }
            else if(value >= ((int) unit))
            {
                results = results + "●";
                value -= (int) unit;
            }
            else if(value >= 1)
            {
                results = results + "○";
                value -= 1;
            }
            else break;
        }
        while(value <= -1)
        {
            if(value <= ((int) (-10.0 * unit)))
            {
                results = results + "♬";
                value += (int) (10.0 * unit);
            }
            else if(value <= (-10))
            {
                results = results + "♪";
                value += 10;
            }
            else if(value <= ((int) (-1.0 * unit)))
            {
                results = results + "♥";
                value += (int) (1.0 * unit);
            }
            else if(value <= (-1))
            {
                results = results + "♡";
                value += 1;
            }
            else break;
        }
        
        return results;
    }
}
