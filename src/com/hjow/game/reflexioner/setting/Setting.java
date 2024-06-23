package com.hjow.game.reflexioner.setting;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import com.hjow.game.reflexioner.lang.English;
import com.hjow.game.reflexioner.lang.Korean;
import com.hjow.game.reflexioner.lang.Language;
import com.hjow.game.reflexioner.mainClasses.RXUtils;
import com.hjow.game.reflexioner.mainClasses.RunManager;
import com.hjow.game.reflexioner.pack.SecuredDist;
import com.hjow.game.reflexioner.reflexioner.Reflexioner;

public class Setting implements CanBeClone
{
    private static final long serialVersionUID = 8204101111362161792L;    
    protected Properties property;
    protected Properties language;
    protected Font usingFont, usingFont2, usingFontB, usingFont2B;
    protected transient boolean firsts = true;
    protected transient Class<?> caller;
    
    public Setting()
    {
        this(null);
    }
    
    public Setting(Class<?> caller)
    {
        property = new Properties();
        language = new Properties();
        this.caller = caller;
        inputBasics();
    }
    
    @Override
    public Setting clone()
    {
        Setting s = new Setting(caller);
        s.property.putAll(this.property);
        s.language.putAll(this.language);
        return s;
    }
    
    public String getDefaultPath()
    {
        String originalVal = get("DefaultPath");
        if(RXUtils.isEmpty(originalVal))
        {
            originalVal = System.getProperty("user.home") + File.separator + ".reflexioner" + File.separator;
            set("DefaultPath", originalVal);
            File dir = new File(originalVal);
            if(! dir.exists()) dir.mkdirs();
        }
        return originalVal;
    }
    
    public String get(String key)
    {
        return property.getProperty(key);
    }
    
    public boolean contains(String key)
    {
        return property.containsKey(key);
    }
    
    public boolean getBool(String key)
    {
        return RXUtils.parseBoolean(get(key));
    }
    
    public int getInt(String key)
    {
        if(key == null) return 0;
        return Integer.parseInt(get(key).replace(",", "").replace(" ", "").trim());
    }
    
    public Color getColor(String key)
    {
        if(key == null) return null;
        int r = 0;
        int g = 0;
        int b = 0;
        String colors = get(key);
        if(colors == null)
        {
        	return null;
        }
        StringTokenizer commaTokenizer = new StringTokenizer(colors, ",");
        int i = 0;
        while(commaTokenizer.hasMoreTokens())
        {
            switch(i)
            {
            case 0:
                r = Integer.parseInt(commaTokenizer.nextToken().trim());
                break;
            case 1:
                g = Integer.parseInt(commaTokenizer.nextToken().trim());
                break;
            case 2:
                b = Integer.parseInt(commaTokenizer.nextToken().trim());
                break;
            }
            i++;
        }
        if(r < 0) r = 0;
        if(g < 0) g = 0;
        if(b < 0) b = 0;
        if(r > 255) r = 255;
        if(g > 255) g = 255;
        if(b > 255) b = 255;
        return new Color(r, g, b);
    }
    
    public void set(String key, Object val)
    {
        if(val == null)
        {
            property.remove(key);
            return;
        }
        
        if(val instanceof Properties)
        {
        	property.setProperty(key, Setting.serializeProperty((Properties) val));
        	return;
        }
        
        if(val instanceof Color)
        {
            Color c = (Color) val;
            property.setProperty(key, c.getRed() + "," + c.getGreen() + "," + c.getBlue());
            return;
        }
        
        property.setProperty(key, String.valueOf(val));
    }
    
    public Set<String> keys()
    {
        return property.stringPropertyNames();
    }
    
    public String gets(String ... keys)
    {
    	Properties prop = property;
    	String currentKey = null;
    	for(int idx=0; idx<keys.length; idx++)
    	{
    		currentKey = keys[idx];
    		String val = prop.getProperty(currentKey);
    		if(idx == keys.length - 1) return val;
    		prop = extractProperty(val);
    	}
    	return null;
    }
    
    public String trans(String english)
    {
        String contents = language.getProperty(english);
        if(contents == null)
        {
        	System.out.println("[LANG] " + english);
        	language.setProperty(english, english);
            return english;
        }
        return contents;
    }
    
    public String t(String englinsh)
    {
        return trans(englinsh);
    }
    
    public Set<String> ments()
    {
        return language.stringPropertyNames();
    }

    public Properties getProperty() {
        return property;
    }

    public void setProperty(Properties property) {
        this.property = property;
    }

    public Properties getLanguage() {
        return language;
    }

    public void setLanguage(Properties language) {
        this.language = language;
    }
    
    public Dimension getScreenSize()
    {
        int width  = getInt("Width");
        int height = getInt("Height");
        return new Dimension(width, height);
    }
    
    public Font getUsingFont() {
        return usingFont;
    }

    public void setUsingFont(Font usingFont) {
        this.usingFont = usingFont;
    }

    public Font getUsingFont2() {
        return usingFont2;
    }

    public void setUsingFont2(Font usingFont2) {
        this.usingFont2 = usingFont2;
    }

    public Font getUsingFontB() {
        return usingFontB;
    }

    public void setUsingFontB(Font usingFontB) {
        this.usingFontB = usingFontB;
    }

    public Font getUsingFont2B() {
        return usingFont2B;
    }

    public void setUsingFont2B(Font usingFont2B) {
        this.usingFont2B = usingFont2B;
    }
    
    public Class<?> getIndexClass()
    {
        return caller;
    }
    
	public boolean isAuthorizedSetting() 
    {
    	if(firsts) return true;
    	if(! contains("SettingAuthCode")) return false;
    	
    	StringBuilder collects = new StringBuilder("");
    	for(String k : keys())
    	{
    		if(k.equals("SettingAuthCode")) continue;
    		collects = collects.append(k).append(":").append(get(k)).append("\n");
        }
    	String c = collects.toString();
    	collects = null;
    	c = c.trim();
    	
    	SecuredDist sp = new SecuredDist();
        String svm = get("VersionMain");
        String sv1 = get("VersionSub1");
        String sv2 = get("VersionSub2");
        
    	try 
    	{ 
    		int vm, v1, v2;
    		vm = Integer.parseInt(svm);
    		v1 = Integer.parseInt(sv1);
    		v2 = Integer.parseInt(sv2);
    		if(get("SettingAuthCode").equals(sp.getLeftPad(vm, v1, v2) + RXUtils.hexString(RXUtils.hash(c.getBytes("UTF-8"))) + sp.getRightPad(vm, v1, v2))) return true; 
    	} catch(Exception ex) { ex.printStackTrace(); }
    	return false;
    }
    
    public static Setting load()
    {
    	return load(RunManager.getIndexClass());
    }

    public static Setting load(Class<?> caller)
    {
        return load(new File(System.getProperty("user.home") + File.separator + ".reflexioner"), caller);
    }
    
    public void save(Class<?> caller)
    {
        save(new File(System.getProperty("user.home") + File.separator + ".reflexioner"), caller);
    }
    
    public static Setting load(File directory, Class<?> caller)
    {
        if(directory == null)
        {
            return new Setting(caller);
        }
        if(! directory.exists())
        {
            directory.mkdirs();
            return new Setting(caller);
        }
        if(! directory.isDirectory()) throw new RuntimeException("No directory ! " + directory.getAbsolutePath());
        
        Setting sets = new Setting(caller);
        File target;
        
        sets.firsts = false;
        
        FileInputStream inp = null;
        
        target = new File(directory.getAbsolutePath() + File.separator + "setting.properties");
        if(target.exists())
        {
            try
            {
                inp = new FileInputStream(target);
                sets.getProperty().load(inp);
                inp.close();
                inp = null;
            }
            catch(Exception exc)
            { throw new RuntimeException(exc.getMessage()); }
            finally
            {
                if(inp != null) { try { inp.close(); } catch(Exception exc) {} }
            }
        }
        
        target = new File(directory.getAbsolutePath() + File.separator + "setting.xml");
        if(target.exists())
        {
            try
            {
                inp = new FileInputStream(target);
                sets.getProperty().loadFromXML(inp);
                inp.close();
                inp = null;
            }
            catch(Exception exc)
            { throw new RuntimeException(exc.getMessage()); }
            finally
            {
                if(inp != null) { try { inp.close(); } catch(Exception exc) {} }
            }
        }
        
        target = new File(directory.getAbsolutePath() + File.separator + "language.properties");
        if(target.exists())
        {
            try
            {
                inp = new FileInputStream(target);
                sets.getLanguage().load(inp);
                inp.close();
                inp = null;
            }
            catch(Exception exc)
            { throw new RuntimeException(exc.getMessage()); }
            finally
            {
                if(inp != null) { try { inp.close(); } catch(Exception exc) {} }
            }
        }
        
        target = new File(directory.getAbsolutePath() + File.separator + "language.xml");
        if(target.exists())
        {
            try
            {
                inp = new FileInputStream(target);
                sets.getLanguage().loadFromXML(inp);
                inp.close();
                inp = null;
            }
            catch(Exception exc)
            { throw new RuntimeException(exc.getMessage()); }
            finally
            {
                if(inp != null) { try { inp.close(); } catch(Exception exc) {} }
            }
        }
        
        return sets;
    }
    
    public void save(File directory, Class<?> caller)
    {
        if(directory == null)
        {
            return;
        }
        if(! directory.isDirectory()) throw new RuntimeException("No directory !");
        if(! directory.exists()) directory.mkdirs();
        
        SecuredDist sp = new SecuredDist();
        DetailedVersionData v = new DetailedVersionData();
        set("VersionMain", v.getV_m());
        set("VersionSub1", v.getV_1());
        set("VersionSub2", v.getV_2());
        
        if(isAuthorizedSetting())
        {
        	StringBuilder collects = new StringBuilder("");
        	for(String k : keys())
        	{
        		if(k.equals("SettingAuthCode")) continue;
        		collects = collects.append(k).append(":").append(get(k)).append("\n");
            }
        	String c = collects.toString();
        	collects = null;
        	c = c.trim();
        	
        	try { set("SettingAuthCode", sp.getLeftPad(Reflexioner.version_main, Reflexioner.version_sub_1, Reflexioner.version_sub_2) + RXUtils.hexString(RXUtils.hash(c.getBytes("UTF-8"))) + sp.getRightPad(Reflexioner.version_main, Reflexioner.version_sub_1, Reflexioner.version_sub_2) ); } catch(Exception ex) { ex.printStackTrace(); }
        }
        
        File target;
        
        FileOutputStream out = null;
        boolean setComplete = false;
        boolean langComplete = false;
        
        target = new File(directory.getAbsolutePath() + File.separator + "setting.properties");
        if((! setComplete) && target.exists())
        {
            try
            {
                out = new FileOutputStream(target);
                this.getProperty().store(out, "Reflexioner Settings");
                setComplete = true;
                out.close();
                out = null;
            }
            catch(Exception exc)
            { throw new RuntimeException(exc.getMessage()); }
            finally
            {
                if(out != null) { try { out.close(); } catch(Exception exc) {} }
            }
        }
        
        target = new File(directory.getAbsolutePath() + File.separator + "setting.xml");
        if(! setComplete)
        {
            try
            {
                out = new FileOutputStream(target);
                this.getProperty().storeToXML(out, "Reflexioner Settings");
                setComplete = true;
                out.close();
                out = null;
            }
            catch(Exception exc)
            { throw new RuntimeException(exc.getMessage()); }
            finally
            {
                if(out != null) { try { out.close(); } catch(Exception exc) {} }
            }
        }
        
        target = new File(directory.getAbsolutePath() + File.separator + "language.properties");
        if((! langComplete) && target.exists())
        {
            try
            {
                out = new FileOutputStream(target);
                this.getLanguage().store(out, "Reflexioner Language Table");
                setComplete = true;
                out.close();
                out = null;
            }
            catch(Exception exc)
            { throw new RuntimeException(exc.getMessage()); }
            finally
            {
                if(out != null) { try { out.close(); } catch(Exception exc) {} }
            }
        }
        
        target = new File(directory.getAbsolutePath() + File.separator + "language.xml");
        if(! langComplete)
        {
            try
            {
                out = new FileOutputStream(target);
                this.getLanguage().storeToXML(out, "Reflexioner Language Table");
                setComplete = true;
                out.close();
                out = null;
            }
            catch(Exception exc)
            { throw new RuntimeException(exc.getMessage()); }
            finally
            {
                if(out != null) { try { out.close(); } catch(Exception exc) {} }
            }
        }
    }
    
    public static String serializeProperty(Properties prop)
    {
        ByteArrayOutputStream coll = new ByteArrayOutputStream();
        GZIPOutputStream gzipper = null;
        try
        {
            gzipper = new GZIPOutputStream(coll);
            prop.storeToXML(gzipper, "");
            gzipper.close();
            gzipper = null;
        }
        catch(Exception exc)
        { throw new RuntimeException(exc.getMessage()); }
        finally
        {
            if(gzipper != null) { try { gzipper.close(); } catch(Exception exc) {} }
            if(coll    != null) { try { coll.close();    } catch(Exception exc) {} }
        }
        
        return RXUtils.hexString(coll.toByteArray());
    }
    
    public static Properties extractProperty(String serialized)
    {
        GZIPInputStream gzipper = null;
        ByteArrayInputStream coll = null;
        try
        {
            Properties prop = new Properties();
            coll = new ByteArrayInputStream(RXUtils.hexBytes(serialized));
            serialized = null;
            
            gzipper = new GZIPInputStream(coll);
            prop.loadFromXML(gzipper);
            
            coll = null;
            gzipper.close(); gzipper = null;
            return prop;
        }
        catch(Exception exc)
        { throw new RuntimeException(exc.getMessage()); }
        finally
        {
            if(gzipper != null) { try { gzipper.close(); } catch(Exception exc) {} }
            if(coll    != null) { try { coll.close();    } catch(Exception exc) {} }
        }
    }
    
    public void inputBasics()
    {
        if(caller == null) caller = this.getClass();
        String syslocale = System.getProperty("user.language");
        
        set("OS"      , System.getProperty("os.name"));
        set("Language", syslocale);
        
        System.out.println("OS : " + get("OS"));
        System.out.println("Language : " + syslocale);
        
        set("ErrorStackTraceConsole", "Y");
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int scrWid = (int) screenSize.getWidth();
        int scrHei = (int) screenSize.getHeight();
        screenSize = null;
        
        set("ScreenWidth" , scrWid + "");
        set("ScreenHeight", scrHei + "");
        
        set("Width" , (scrWid - 150) + "");
        set("Height", (scrHei - 200) + "");
        
        set("Undecorated", "Y");
        set("FontSize"   , 12 + "");
        set("FontSizeBig", 20 + "");
        
        InputStream inp1 = null;
        InputStream inp2 = null;
        String bundledFamily = null;
        try
        {
            inp1 = caller.getClassLoader().getResourceAsStream("resources/font/font.ttf");
            if(inp1 != null)
            {
            	inp2 = new BufferedInputStream(inp1);
                usingFont = Font.createFont(Font.TRUETYPE_FONT, inp2);
                inp2.close(); inp2 = null;
                inp1.close(); inp1 = null;
                
                usingFont   = usingFont.deriveFont(Font.PLAIN, getInt("FontSize"));
                usingFont2  = usingFont.deriveFont(Font.PLAIN, getInt("FontSizeBig"));
                usingFontB  = usingFont.deriveFont(Font.BOLD , getInt("FontSize"));
                usingFont2B = usingFont.deriveFont(Font.BOLD , getInt("FontSizeBig"));
                bundledFamily = usingFont.getFamily();
                if(! contains("FontFamily")) set("FontFamily", bundledFamily);
            }
            else
            {
            	usingFont = new Font("Courier", Font.PLAIN, getInt("FontSize"));
                usingFont2  = usingFont.deriveFont(Font.PLAIN, getInt("FontSizeBig"));
                usingFontB  = usingFont.deriveFont(Font.BOLD , getInt("FontSize"));
                usingFont2B = usingFont.deriveFont(Font.BOLD , getInt("FontSizeBig"));
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        
        if(contains("FontFamily"))
        {
        	if(bundledFamily != null && get("FontFamily").equals(bundledFamily))
        	{
        		usingFont = new Font(get("FontFamily"), Font.PLAIN, getInt("FontSize"));
                usingFont2  = usingFont.deriveFont(Font.PLAIN, getInt("FontSizeBig"));
                usingFontB  = usingFont.deriveFont(Font.BOLD , getInt("FontSize"));
                usingFont2B = usingFont.deriveFont(Font.BOLD , getInt("FontSizeBig"));
        	}
        }
        
        set("LookAndFeel", "javax.swing.plaf.metal.MetalLookAndFeel");
        
        ThemeSet thmSet = new ThemeSet(0);
        set("ColorSelectedBack"      , thmSet.getSelected_back());
        set("ColorSelectedInsideBack", thmSet.getSelected_inside());
        set("ColorSelectedFore"      , thmSet.getSelected_fore());
        set("ColorSelectedButton"    , thmSet.getSelected_button());
        set("ColorUnselectedBack"      , thmSet.getUnselected_back());
        set("ColorUnselectedInsideBack", thmSet.getUnselected_inside());
        set("ColorUnselectedFore"      , thmSet.getUnselected_fore());
        set("ColorUnselectedButton"    , thmSet.getUnselected_button());
        
        set("ServerURL1", "http://hjow.github.io/Reflexioner/");
        set("ServerURL2", "http://hjow.github.io/Reflexioner/");
        
        set("KEY_0"    , KeyEvent.VK_0);
        set("KEY_1"    , KeyEvent.VK_1);
        set("KEY_2"    , KeyEvent.VK_2);
        set("KEY_3"    , KeyEvent.VK_3);
        set("KEY_4"    , KeyEvent.VK_4);
        set("KEY_5"    , KeyEvent.VK_5);
        set("KEY_6"    , KeyEvent.VK_6);
        set("KEY_7"    , KeyEvent.VK_7);
        set("KEY_8"    , KeyEvent.VK_8);
        set("KEY_9"    , KeyEvent.VK_9);
        set("KEY_SHIFT", KeyEvent.VK_SHIFT);
        set("KEY_SPACE", KeyEvent.VK_SPACE);
        set("KEY_UP"   , KeyEvent.VK_UP);
        set("KEY_DOWN" , KeyEvent.VK_DOWN);
        set("KEY_LEFT" , KeyEvent.VK_LEFT);
        set("KEY_RIGHT", KeyEvent.VK_RIGHT);
        set("KEY_W"    , KeyEvent.VK_W);
        set("KEY_A"    , KeyEvent.VK_A);
        set("KEY_S"    , KeyEvent.VK_S);
        set("KEY_D"    , KeyEvent.VK_D);
        set("KEY_L"    , KeyEvent.VK_L);
        set("KEY_K"    , KeyEvent.VK_K);
        set("KEY_P"    , KeyEvent.VK_P);
        
        List<Language> languagePacks = new ArrayList<Language>();
        languagePacks.add(new Korean());
        languagePacks.add(new English());
        
        for(Language l : languagePacks)
        {
        	if(syslocale.equals(l.language()))
        	{
        		language.putAll(l.properties());
        	}
        }
    }
}