package prabhakar.varma.packag;

import java.io.File;
import java.util.Calendar;

public class LogsCleaner {

	
	public static void cleaner(String path, String durationInHours) {
		
		File dir = new File(path);
		File file = null;
		long lastModified = 0L;
		long timeDifference = 0L;
		long currentTime = Calendar.getInstance().getTimeInMillis();
		long durationInMillis = Long.parseLong(durationInHours) * 60 * 60 * 1000;
		
		if(dir.isDirectory()) {
			String fileList[] = dir.list();
			for(int i=0; i<fileList.length; i++) {
				file = new File(path +fileList[i]);
				lastModified = file.lastModified();
				timeDifference = currentTime - lastModified;
				
				if(timeDifference > durationInMillis) {
					file.delete();
				}
			}
		}
	}
}
