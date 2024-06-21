package com.hjow.game.reflexioner.mainClasses;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
}
