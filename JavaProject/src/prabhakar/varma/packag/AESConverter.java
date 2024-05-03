package prabhakar.varma.packag;

//import java.io.ByteBuffer;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

//import org.apache.commons.codec.binary.Base64;

//import sun.misc.*;



public class AESConverter {

	private static String algorithm = "AES";
	private static byte[] keyValue = "WUGateway-#12345".getBytes();
	
	
	public AESConverter()
	{
	}
	
	
	//Performs Encryption
	public static String encrypt(String plainText)
	{
		String encryptedValue = "";
		try {
			Key key = generateKey();
			Cipher chiper = Cipher.getInstance(algorithm);
			chiper.init(Cipher.ENCRYPT_MODE, key);
			byte[] encVal = chiper.doFinal(plainText.getBytes());
//Try this below Encoding line -- This is not tested --Changed by PV
			encryptedValue = Base64.getEncoder().encodeToString(encVal);
		}
		catch(Exception e) {
			encryptedValue = "ERROR:Invalid Image Digest";
		}
		return encryptedValue.toString();
	}
	
	
	//PERFORM DECRYPTION
	
	public static String decrypt(String encryptedText)
	{
		
		//generate key
		String decryptedValue = "";
		try
		{
			Key key = generateKey();
			Cipher chiper = Cipher.getInstance(algorithm);
			chiper.init(Cipher.DECRYPT_MODE, key);
			//Try this below Decoding line -- This is not tested --Changed by PV			
			byte[] decordedValue = Base64.getDecoder().decode(encryptedText);
			byte[] decValue = chiper.doFinal(decordedValue);
			decryptedValue = new String(decValue);
		}
		catch (Exception e) {
			decryptedValue = "ERROR: Invalid Image Digest";
		}
		return decryptedValue.toString();
	}
	
	
	//generateKey() is used to generate a secret key for AES algorithm
	
	
	private static Key generateKey() throws Exception
	{
		Key key = new SecretKeySpec(keyValue, algorithm);
		return key;
	}
	
		}
