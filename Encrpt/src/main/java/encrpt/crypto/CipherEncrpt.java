package encrpt.crypto;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class CipherEncrpt {

	public static final String ALGORITHM = "DES";
	
	public static Key initKey(String key) {
		try {
			SecretKeyFactory instance = SecretKeyFactory.getInstance(ALGORITHM);
			byte[] DESKey = key.getBytes();
			KeySpec keySpec = new DESKeySpec(DESKey);
			SecretKey generateSecret = instance.generateSecret(keySpec);
			return generateSecret;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Cipher getInstance() {
		try {
			return Cipher.getInstance(ALGORITHM);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static byte[] encrpt(byte[] src, Key key) {
		Cipher cipher = CipherEncrpt.getInstance();
		try {
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] dist = cipher.doFinal(src);
			return dist;
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static byte[] decrpt(byte[] src, Key key) {
		Cipher cipher = CipherEncrpt.getInstance();
		try {
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] dist = cipher.doFinal(src);
			return dist;
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static void main(String[] args) {
		Key initKey = CipherEncrpt.initKey("12345678");
		System.out.println(initKey);
		String a = "abc";
		byte[] aa = a.getBytes();
		System.out.println(aa);
		byte[] encrpt = CipherEncrpt.encrpt(aa, initKey);
		System.out.println(encrpt);
		byte[] decrpt = CipherEncrpt.decrpt(encrpt, initKey);
		System.out.println(new String(decrpt));
	}
}
