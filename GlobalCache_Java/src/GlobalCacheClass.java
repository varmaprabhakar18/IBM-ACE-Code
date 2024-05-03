import com.ibm.broker.plugin.MbException;
import com.ibm.broker.plugin.MbGlobalMap;

public class GlobalCacheClass {

	
	public static String MapName = "GBMAP";
	
	public static void LoadXMLToCache(String key, byte[] xml) {
		
		try {
			MbGlobalMap map = MbGlobalMap.getGlobalMap(MapName);
			
			if (map.containsKey(key)) {
				map.update(key, xml);
			}
			else
			{
				map.put(key, xml);
				
			}
			
		}
		catch (MbException e)
		{
			//TODO Auto-generated block
			e.printStackTrace();
		}
	}

	
	public static byte[] GetCacheValue(String key) {
		
		byte[] xml = null;
		MbGlobalMap map;
		try{
			
			map = MbGlobalMap.getGlobalMap(MapName);
			xml = (byte[])map.get(key);
		}
		catch(MbException e) {
			//TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return xml;
	}
	
	}
	
