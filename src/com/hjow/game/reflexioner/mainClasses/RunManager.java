package com.hjow.game.reflexioner.mainClasses;

import java.awt.HeadlessException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;

import com.hjow.game.reflexioner.reflexioner.Reflexioner;
import com.hjow.game.reflexioner.setting.Setting;

public class RunManager implements Serializable
{
    private static final long serialVersionUID = -1983682874942538244L;
    public static final boolean OPEN_MANAGER = false;
    private static RunManager manager = null;
    private static Openable window = null;
    private static Class<?> indexClass = null;
    
    private RunManager()
    {
        
    }
    public static Class<?> getIndexClass() {
        if(indexClass == null) return RunManager.class;
        return indexClass;
    }
    public static long getNameCode(String str)
    {
        return getNameCode(str.toCharArray());
    }
    public static long getNameCode(String str, String before_charset, String now_charset) throws UnsupportedEncodingException
    {
        return getNameCode(new String(str.getBytes(before_charset), now_charset));
    }
    public static long getNameCode(char[] str)
    {
        long results = 0;
        for(int i=0; i<str.length; i++)
        {
            results += (long) ((int) str[i]);
        }
        return results;
    }
    public static RunManager getInstance()
    {
        if(manager == null) manager = new RunManager();
        return manager;
    }
    public static void run(String[] args, Setting befores, Class<?> caller) throws HeadlessException, UnsupportedEncodingException
    {
        indexClass = caller;
        RunManager.getInstance().runGame(args, befores, null);
    }
    public static void run(String[] args, Setting befores, String run_path, Class<?> caller) throws HeadlessException, UnsupportedEncodingException
    {
        indexClass = caller;
        Setting gets = befores;        
        RunManager.getInstance().runGame(args, gets, run_path);
    }
    public void runGame(String[] args, Setting befores, String run_path) throws HeadlessException, UnsupportedEncodingException
    {        
        //System.out.println("1");
        Setting setting = null;
        if(befores != null)
        {
            setting = befores;
        }
        else
        {
            setting = Setting.load(indexClass);
        }
        
        window = new Reflexioner(setting, true);
        window.open();
    }
    public static String r65279(String target)
    {
        char[] targets = target.toCharArray();
        String results = "";
        
        for(int i=0; i<targets.length; i++)
        {
            if(((int)targets[i]) != 65279)
            {
                results = results + String.valueOf(targets[i]);
            }
        }
        return results;
    }
}
