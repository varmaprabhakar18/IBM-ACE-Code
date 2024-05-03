package prabhakar.varma.packag;

import java.io.UnsupportedEncodingException;
import java.util.zip.*;
import org.apache.commons.codec.binary.Base64;
public class Base64Converter {

	public static String decompressToString(String base64String) {
		String uncompressedData = null;
		byte[] a = base64String.getBytes();
		byte[] compressedData = Base64.decodeBase64(a);
		
		Inflater deCompressor = new Inflater();
		deCompressor.setInput(compressedData, 0, compressedData.length);
		byte[] output = new byte[1024];
		int decompressedDataLength = 0;
		try {
			decompressedDataLength = deCompressor.inflate(output);
			
		}
		catch(DataFormatException e) {
			uncompressedData = "ERROR";
		}
		deCompressor.end();
		
		if(decompressedDataLength != 0) {
			try {
				uncompressedData = new String(output, 0, decompressedDataLength, "UTF-8");
			}
			catch (UnsupportedEncodingException e) {
				uncompressedData = "ERROR";
				e.printStackTrace();
			}
		}
			else
			{
				uncompressedData ="ERROR";
			}
		return uncompressedData;
	}
}
