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

import java.io.Serializable;

public class StringWithInt implements Serializable
{
    private static final long serialVersionUID = 3511665895428776256L;
    private String str;
    private int num;
    public StringWithInt()
    {
        
    }
    public StringWithInt(String str, int num)
    {
        this.str = str;
        this.num = num;
    }
    public String getStr()
    {
        return str;
    }
    public void setStr(String str)
    {
        this.str = str;
    }
    public int getNum()
    {
        return num;
    }
    public void setNum(int num)
    {
        this.num = num;
    }
}
