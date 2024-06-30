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

import java.util.StringTokenizer;

import com.hjow.game.reflexioner.reflexioner.Reflexioner;

public class DetailedVersionData extends VersionData
{
    private static final long serialVersionUID = -3806159464521969479L;
    private Long nightly;
    public DetailedVersionData()
    {
        setV_m(new Integer(Reflexioner.version_main));
        setV_1(new Integer(Reflexioner.version_sub_1));
        setV_2(new Integer(Reflexioner.version_sub_2));
        setV_t(new Character(Reflexioner.version_test));
        setNightly(new Long(Reflexioner.version_nightly));
    }
    public DetailedVersionData(int v_m, int v_1, int v_2, char v_t)
    {
        setV_m(new Integer(v_m));
        setV_1(new Integer(v_1));
        setV_2(new Integer(v_2));
        setV_t(new Character(v_t));
        setNightly(new Long(Reflexioner.version_nightly));
    }
    public DetailedVersionData(int v_m, int v_1, int v_2, char v_t, long v_n)
    {
        setV_m(new Integer(v_m));
        setV_1(new Integer(v_1));
        setV_2(new Integer(v_2));
        setV_t(new Character(v_t));
        setNightly(new Long(v_n));
    }
    public DetailedVersionData(String str)
    {
        try
        {
            StringTokenizer numberToken = new StringTokenizer(str.trim(), " ");
            String mainVer = numberToken.nextToken();
            String nightly = numberToken.nextToken();
            String test = " ";
            if(numberToken.hasMoreTokens())
                test = numberToken.nextToken();
            setV_m(new Integer(Integer.parseInt(String.valueOf(mainVer.toCharArray()[0]))));
            setV_1(new Integer(Integer.parseInt(String.valueOf(mainVer.toCharArray()[1]))));
            setV_2(new Integer(Integer.parseInt(String.valueOf(mainVer.toCharArray()[2]))));
            setNightly(new Long(nightly));
            setV_t(new Character(test.toCharArray()[0]));
        } 
        catch (Exception e)
        {
            e.printStackTrace();
            setV_m(new Integer(Reflexioner.version_main));
            setV_1(new Integer(Reflexioner.version_sub_1));
            setV_2(new Integer(Reflexioner.version_sub_2));
            setV_t(new Character(Reflexioner.version_test));
            setNightly(new Long(Reflexioner.version_nightly));
        }
    }
    public DetailedVersionData clone()
    {
        DetailedVersionData newOne = new DetailedVersionData(getV_m().intValue(), getV_1().intValue(), getV_2().intValue(), getV_t().charValue(), getNightly().longValue());
        return newOne;
    }
    public Long getNightly()
    {
        return nightly;
    }
    public void setNightly(Long nightly)
    {
        this.nightly = nightly;
    }
    @Override
    public long value()
    {
        long val = 0;
        char vt = getV_t();
        if(vt == ' ') val += 0;
        else val += ((int) (vt - 'a')) + 1;
        val += getNightly() * 10;
        val += getV_2() * 10 * ((long) Math.pow(1000, 1));
        val += getV_1() * 10 * ((long) Math.pow(1000, 2));
        val += getV_m() * 10 * ((long) Math.pow(1000, 3));
        return val;
    }
    @Override
    public String toString()
    {
        String val = super.toString();
        if(getV_t() != ' ') val += String.valueOf(getV_t());
        val += "(" + getNightly() + ")";
        return val;
    }
}
