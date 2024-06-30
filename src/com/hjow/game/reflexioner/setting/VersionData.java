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
import com.hjow.game.reflexioner.reflexioner.Reflexioner;

public class VersionData extends XMLSerializableObject
{
    private static final long serialVersionUID = -144348653263971863L;
    private Integer v_m, v_1, v_2;
    private Character v_t;
    public VersionData()
    {
        v_m = new Integer(Reflexioner.version_main);
        v_1 = new Integer(Reflexioner.version_sub_1);
        v_2 = new Integer(Reflexioner.version_sub_2);
        v_t = new Character(Reflexioner.version_test);
    }
    public VersionData(int v_m, int v_1, int v_2, char v_t)
    {
        this.v_m = new Integer(v_m);
        this.v_1 = new Integer(v_1);
        this.v_2 = new Integer(v_2);
        this.v_t = new Character(v_t);
    }
    public VersionData clone()
    {
        VersionData newOne = new VersionData(v_m.intValue(), v_1.intValue(), v_2.intValue(), v_t.charValue());
        return newOne;
    }
    public int v_m()
    {
        return v_m.intValue();
    }
    public int v_1()
    {
        return v_1.intValue();
    }
    public int v_2()
    {
        return v_2.intValue();
    }
    public char v_t()
    {
        return v_t.charValue();
    }
    public Integer getV_m()
    {
        return v_m;
    }
    public void setV_m(Integer v_m)
    {
        this.v_m = v_m;
    }
    public Integer getV_1()
    {
        return v_1;
    }
    public void setV_1(Integer v_1)
    {
        this.v_1 = v_1;
    }
    public Integer getV_2()
    {
        return v_2;
    }
    public void setV_2(Integer v_2)
    {
        this.v_2 = v_2;
    }
    public Character getV_t()
    {
        return v_t;
    }
    public void setV_t(Character v_t)
    {
        this.v_t = v_t;
    }
    public long value()
    {
        long val = 0;
        char vt = getV_t();
        if(vt == ' ') val += 0;
        else val += ((int) (vt - 'a')) + 1;
        val += 0 * 10;
        val += getV_2() * 10 * ((long) Math.pow(1000, 1));
        val += getV_1() * 10 * ((long) Math.pow(1000, 2));
        val += getV_m() * 10 * ((long) Math.pow(1000, 3));
        return val;
    }
    @Override
    public String toString()
    {
        return getV_m() + "." + getV_1() + "." + getV_2();
    }
}
