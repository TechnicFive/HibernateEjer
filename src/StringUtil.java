package com.serbatic.course.datamodel.utils;

public abstract class StringUtil {

    private final static char [] dniChars = {'T','R','W','A','G','M','Y','F','P','D','X','B','N','J','Z','S','Q','V','H','L','C','K','E'};
   
    public static String getLeftPaddedWithZeros(int num, int length) {
    	// Sin validaciones
    	String format = "%0"+ length + "d";
    	return String.format(format, num);
    }
    
    public static char calculateDniLetter(String dniNumber){
    	// Sin validaciones
    	int num = Integer.parseInt(dniNumber);
        return dniChars[num % 23];
    }
	
}
