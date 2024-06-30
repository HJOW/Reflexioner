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

public class S
{
    public static String encoding = "UTF-8";
    public static String _t(byte[] str)
    {
        try
        {
            return new String(str, encoding);
        } 
        catch (Exception e)
        {
            e.printStackTrace();
            return new String(str);
        }
    }    
    public static String _t(String str)
    {
        try
        {
            return new String(str.getBytes(), encoding);
        } 
        catch (Exception e)
        {
            e.printStackTrace();
            return str;
        }
    }
    public static String _t(byte[] str, String encoding)
    {
        try
        {
            return new String(str, encoding);
        } 
        catch (Exception e)
        {
            e.printStackTrace();
            return new String(str);
        }
    }    
    public static String _t(String str, String encoding)
    {
        try
        {
            return new String(str.getBytes(), encoding);
        } 
        catch (Exception e)
        {
            e.printStackTrace();
            return str;
        }
    }
}
