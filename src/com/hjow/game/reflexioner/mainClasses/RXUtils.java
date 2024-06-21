package com.hjow.game.reflexioner.mainClasses;

public class RXUtils {
	/**
     * 바이너리를 16진수 형태의 문자열로 변환합니다. 문자열 형태로 바이너리 데이터를 전달하는 데 사용됩니다.
     * 
     * @param bytes : 바이너리
     * @return 16진수 문자열
     */
    public static String hexString(byte[] bytes) {
    	StringBuffer results = new StringBuffer("");
        
        for(int i=0; i<bytes.length; i++)
        {
            results.append(Integer.toString(((bytes[i] & 0xf0) >> 4), 16) );
            results.append(Integer.toString((bytes[i] & 0x0f), 16));
        }
                                
        return String.valueOf(results);
    }
    
    /**
     * 16진수 문자열을 다시 바이너리로 변환합니다.
     * 
     * @param hexString : 바이너리 정보가 담긴 16진수 문자열 (모든 문자열이 호환되지 않음 ! 16진수 문자열만 사용 가능)
     * @return 바이너리 원본
     */
    public static  byte[] hexBytes(String hexString) {
    	byte[] results = new byte[hexString.length() / 2];
    	for(int idx=0; idx<hexString.length(); idx+=2) {
    		results[(int) idx / 2] = (byte) (( Character.digit(hexString.charAt(idx), 16) << 4 ) + Character.digit(hexString.charAt(idx + 1), 16));
    	}
    	
    	return results;
    }
}
