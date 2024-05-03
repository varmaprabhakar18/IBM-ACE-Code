package prabhakar.varma.packag;

import java.util.regex.Pattern;
import org.apache.xerces.util.XMLChar;
import com.ibm.broker.plugin.MbGlobalMap;
import com.ibm.broker.plugin.MbGlobalMapSessionPolicy;

public class Utility {
	public static String trimEmptyBytes(String input)
	{
		if(input != null)
		{
			input = input.trim();
			return input;
		}
		else
		{
			return "";
		}
	}
	public static String isNumeric(String str) {
		Pattern pat = Pattern.compile("([0-9]*)");
		boolean doesMatch = pat.matcher(str).matches();
		if(doesMatch)
			return "Y";
		else
			return "N";
	}
	public static String getWordCount(String message_string) {
		
		int message_word_count=0;
		if(message_string!=null) {
			String splitString[] = message_string.split("[\\s]+");
			message_word_count = message_word_count + splitString.length;
		}
		return message_word_count+"".toString();
	}
	public static String nameFormats(String S_R_Type, String nameType, String name1, String name2, String name3)
	{
		if(nameType!=null && nameType.trim().equalsIgnoreCase("D"))
		{
			if(name1.length()>35)
			{
				return "T1111";
			}
			else if(name2.length()>35)
			{
				System.out.println("in fname");
				return "T1112";
			}
			else if(name1.length()+name2.length()>39)
			{
				return "T1113";
			}
			else
			{
				return name1+"\\"+name2;
			}
		}
		else if(S_R_Type.trim().equalsIgnoreCase("R")&& nameType!=null && nameType.trim().equalsIgnoreCase("D"))
		{
			if(name1.length()>35)
			{
				return "T1121";
			}
			else if(name2.length()>60)
			{
				return "T1122";
			}
			else if(name1.length()+name2.length()>77)
			{
				return "T1123";
			}
			else 
			{
				return name1+"\\"+name2;
			}
		}
		return "";
	}
	
	public static String convertJulianDate(String julian_day) {
		if ((julian_day == null) || (julian_day.trim().length() <5))
			return null;
		String year = julian_day.substring(0,2);
		String dayinyear;
		
		if (julian_day.substring(2,3).equals("0"))
			dayinyear = julian_day.substring(3,5);
		else
			dayinyear = julian_day.substring(2,5);
		
		String full_year = "20"+ year;
		int int_year = 0;
		
		int_year = Integer.parseInt(full_year);
		
		String day_month = getDayAndMonth(int_year, Integer.parseInt(dayinyear));
		
		return full_year+ "-" + day_month;
	}
	
	private static String getDayAndMonth(int year, int day) {
		int cal_days_in_month[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		
		int cal_no_of_month[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
		
		if(year % 4 == 0)
			cal_days_in_month[1] = 29;
		
		String dayStr = "";
		
		for (int i = 0; i<=11; i++) {
			if (day <= cal_days_in_month[i]) {
				dayStr = day + "";
				if (dayStr.substring(0, 1).equals("0"))
					day = Integer.parseInt(dayStr.substring(1));
				if (cal_no_of_month[i] <10 && day <10)
					return "0" + cal_no_of_month[i] + "-" + "0" +day;
				else if (cal_no_of_month[i] < 10 && day >= 10)
					return "0" + cal_no_of_month[i] + "-" + "0" +day;
				else if (cal_no_of_month[i] >= 10 && day < 10 )
					return cal_no_of_month[i] + "-" + "0" + day;
				else
					return cal_no_of_month[i] + "-" + "" + day;
			
			}
			day = day - cal_days_in_month[i];
		}
			return day + "";
	}
	
	public static String cleanXml(String input) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			if (XMLChar.isValid(c)) {
				sb.append(c);
			}
			else sb.append(' ');
		}
		return sb.toString();
	}
	
	public  static String removeNewLine(String input) {
		input = input.replace("\n", "");
				return input;
	}
	
	
	public static void main(String[] args) {
		String s = nameFormats("R","D","fdsaertydgsdgsdg", "prabhakar123456789wertyuiopqjeqelefkelflmvbvfvkrglr","sfdlflsdsaaaa");
		System.out.println(s);
	}
	
	
	public static final String DATA_CACHE_PUT_STATUS_SUCCESS = "SUCCESS";
	public static final String DATA_CACHE_PUT_STATUS_FAILED = "FAILED";
	
	public static final String DATA_CACHE_UPDATE_STATUS_SUCCESS = "SUCCESS";
	
	public static void putIntoNamedCache(String mapName, String key, String value)
	{
		String returnValue = null;
		MbGlobalMap globalMap = null;
		
		try {
			globalMap = MbGlobalMap.getGlobalMap(mapName);
			if(globalMap.get(key) == null) {
				globalMap.put(key, value);
				
				returnValue = DATA_CACHE_PUT_STATUS_SUCCESS;
			}
			
			else if (globalMap.get(key) !=null) {
				globalMap.update(key, value);
				returnValue = DATA_CACHE_UPDATE_STATUS_SUCCESS;
			}
			else {
				returnValue = "Already Available";
			}
		}
		catch (Exception e) {
			returnValue = DATA_CACHE_PUT_STATUS_FAILED;
		}
	}
		
		public static void putTokenValIntoCache(String mapName, String key, String value, Long expiryTime)
		{
			String returnValue = null;
			MbGlobalMap globalMap = null;
			try {
				globalMap = MbGlobalMap.getGlobalMap(mapName, new MbGlobalMapSessionPolicy((int)(expiryTime/1000)));
				if (globalMap.get(key) == null) {
					
					globalMap.put(key, value);
					
					returnValue = DATA_CACHE_PUT_STATUS_SUCCESS;
				}
				
				else if(globalMap.get(key) != null) {
					globalMap.update(key, value);
					returnValue = DATA_CACHE_UPDATE_STATUS_SUCCESS;
				}
				
				
			}
			catch(Exception e) {
				
				returnValue = DATA_CACHE_PUT_STATUS_FAILED;
			}
		}
		
		
				
				
	}


