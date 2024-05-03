package prabhakar.varma.packag;

public class TermIdGenerator {

	
	public static String getNextTermId(String currentId, String applicationConstant) {
		if(currentId==null || currentId.trim().length()==0) currentId= "#G00";
		else currentId = currentId.toUpperCase();
		if(currentId.length()<4) currentId = padRight(currentId, 4);
		
		
		String alpha = "ABCDEFGHIJKLMNOPQRSTWXYZ0123456789";
		String nextId ="";
		
		
		String thirdLetter = String.valueOf(currentId.charAt(2));
		String fourthLetter = String.valueOf(currentId.charAt(3));
		String firstLetter = String.valueOf(alpha.charAt(0));
		String lastLetter = String.valueOf(alpha.charAt(alpha.length()-1));
		
		
		if(fourthLetter.equals(lastLetter)) {
			int idx = alpha.indexOf(thirdLetter);
			if(idx>alpha.length()-2) idx=0; else idx = idx+1;
			thirdLetter = String.valueOf(alpha.charAt(idx));
			fourthLetter = firstLetter;
			nextId = applicationConstant+thirdLetter+fourthLetter;
		}
		return nextId;
			
		}
	
	public static String padRight(String str, int size) {
		String padChar = "0";
		StringBuffer padded = new StringBuffer(str);
		while (padded.length() < size)
			padded.append(padChar);
		return padded.toString();
	
	}
}
