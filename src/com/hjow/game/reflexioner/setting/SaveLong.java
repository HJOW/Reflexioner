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

public class SaveLong extends SaveData
{
    private static final long serialVersionUID = -5365454060693891813L;
    public SaveLong()
    {
        
    }
    public SaveLong(int value)
    {
        this.value = value;
    }
    public SaveLong(long value)
    {
        this.value = value;
    }
    public long longValue()
    {
        return value;
    }
    @Override
    public SaveData clone()
    {
        SaveLong newOne = new SaveLong(value);
        return newOne;
    }
    
}
