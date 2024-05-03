package prabhakar.varma.packag;

public class HexDigitsConverter {
	/**
	 * param args 
	 */

	public static void main(String[] args) {
		System.out.println(toHexDigits("01"));
		
	}
	

	
	public static String toHexDigits(String value) {
		StringBuffer sb = new StringBuffer();
				short hexVal = Short.parseShort(value, 16);
		byte[] byteVal = new byte[1];
		byteVal[0] = (byte)hexVal;
		sb.append(new String(byteVal));
		
		return value;
	}
}
