package com.hjow.game.reflexioner.mainClasses;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class RXUtils {
    public static String hexString(byte[] bytes) {
        StringBuffer results = new StringBuffer("");
        
        for(int i=0; i<bytes.length; i++)
        {
            results.append(Integer.toString(((bytes[i] & 0xf0) >> 4), 16) );
            results.append(Integer.toString((bytes[i] & 0x0f), 16));
        }
                                
        return String.valueOf(results);
    }
    
    public static  byte[] hexBytes(String hexString) {
        byte[] results = new byte[hexString.length() / 2];
        for(int idx=0; idx<hexString.length(); idx+=2) {
            results[(int) idx / 2] = (byte) (( Character.digit(hexString.charAt(idx), 16) << 4 ) + Character.digit(hexString.charAt(idx + 1), 16));
        }
        
        return results;
    }
    
    public static byte[] hash(byte[] originals, String algorithm) throws NoSuchAlgorithmException
    {
        MessageDigest digest = MessageDigest.getInstance(algorithm);
        return digest.digest(originals);
    }
    
    public static byte[] hash(byte[] originals)
    {
        try { return hash(originals, "SHA-256"); } catch(NoSuchAlgorithmException e) {}
        return new byte[0];
    }
    
    public static boolean isEmpty(Object ob)
    {
        if(ob == null) return true;
        if(ob instanceof Number)
        {
            return false;
        }
        else if(ob instanceof Boolean)
        {
            return false;
        }
        else if(ob instanceof String)
        {
            if(((String) ob).trim().equals("")) return true;
            else if(((String) ob).trim().equals("null")) return true;
            else return false;
        }
        else if(ob instanceof List<?>)
        {
            return ((List<?>) ob).isEmpty();
        }
        else if(ob instanceof Map<?, ?>)
        {
            return ((Map<?, ?>) ob).isEmpty();
        }
        else if(String.valueOf(ob).trim().equals("null")) return true;
        else if(String.valueOf(ob).trim().equals("")) return true;
        else return isEmpty(String.valueOf(ob));
    }
    
    public static boolean parseBoolean(Object ob)
    {
        if(ob == null) return false;
        if(isEmpty(ob)) return false;
        if(ob instanceof String)
        {
            String target = ((String) ob).trim();
            if(target.equalsIgnoreCase("t") || target.equalsIgnoreCase("true")
                    || target.equalsIgnoreCase("y") || target.equalsIgnoreCase("yes")) return true;
            else return false;
        }
        else if(ob instanceof Integer)
        {
            if(((Integer) ob).intValue() == 0) return false;
            else return true;
        }
        else if(ob instanceof BigInteger)
        {
            if(((Integer) ob).intValue() == 0) return false;
            else return true;
        }
        else if(ob instanceof Boolean)
        {
            return ((Boolean) ob).booleanValue();
        }
        throw new RuntimeException(String.valueOf(ob));
    }
    
    public static String serializeProperty(Properties prop, boolean gzip)
    {
        ByteArrayOutputStream coll = new ByteArrayOutputStream();
        GZIPOutputStream gzipper = null;
        try
        {
        	if(gzip)
        	{
        		gzipper = new GZIPOutputStream(coll);
                prop.storeToXML(gzipper, "");
                
                gzipper.close();
                gzipper = null;
        	}
        	else
        	{
        		prop.storeToXML(coll, "");
        	}
        }
        catch(Exception exc)
        { throw new RuntimeException(exc.getMessage(), exc); }
        finally
        {
            if(gzipper != null) { try { gzipper.close(); } catch(Exception exc) {} }
            if(coll    != null) { try { coll.close();    } catch(Exception exc) {} }
        }
        
        if(gzip) return RXUtils.hexString(coll.toByteArray());
		else try { return new String(coll.toByteArray(), "UTF-8"); } catch (UnsupportedEncodingException e) { throw new RuntimeException(e.getMessage(), e); }
    }
    
    public static String serializeProperty(Properties prop)
    {
    	return serializeProperty(prop, true);
    }
    
    public static Properties extractProperty(String serialized)
    {
    	return extractProperty(serialized, true);
    }
    
    public static Properties extractProperty(String serialized, boolean gzip)
    {
        GZIPInputStream gzipper = null;
        ByteArrayInputStream coll = null;
        try
        {
            Properties prop = new Properties();
            
            if(gzip)
            {
            	coll = new ByteArrayInputStream(RXUtils.hexBytes(serialized));
                serialized = null;
                
            	gzipper = new GZIPInputStream(coll);
                prop.loadFromXML(gzipper);
                gzipper.close(); gzipper = null;
            }
            else
            {
            	coll = new ByteArrayInputStream(serialized.getBytes("UTF-8"));
                serialized = null;
                
            	prop.loadFromXML(coll);
            }
            
            
            coll = null;
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
}
