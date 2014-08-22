package org.horusframework.business;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base32;

/**
 * @author miamarti
 *
 */
public class HorusTOTP {
	private static String secretKey;

	/**
	 * @param code
	 * @param lengthToken
	 * @return
	 */
	public static boolean getValidation(long code, int lengthToken) {
		return (new HorusTOTP()).checkCode(secretKey, code, lengthToken);
	}

	/**
	 * @param secret
	 * @param code
	 * @param lengthToken
	 * @return
	 */
	public static boolean getValidation(String secret, long code, int lengthToken) {
		HorusTOTP.setSecretKey(secret);
		return (new HorusTOTP()).checkCode(secretKey, code, lengthToken);
	}

	/**
	 * @param secret
	 * @param lengthToken
	 * @return
	 */
	public static long getOneTimePassword(String secret, int lengthToken) {
		HorusTOTP.setSecretKey(secret);
		Base32 codec = new Base32();
		byte[] data = new byte[8];
		long value = (new Date().getTime()) / TimeUnit.SECONDS.toMillis(30);
		for (int i = 8; i-- > 0; value >>>= 8) {
			data[i] = (byte) value;
		}
		SecretKeySpec signKey = new SecretKeySpec(codec.decode(secretKey), "HmacSHA1");
		try {
			Mac mac = Mac.getInstance("HmacSHA1");
			mac.init(signKey);
			byte[] hash = mac.doFinal(data);
			int offset = hash[20 - 1] & 0xF;
			long truncatedHash = 0;
			for (int i = 0; i < 4; ++i) {
				truncatedHash <<= 8;
				truncatedHash |= (hash[offset + i] & 0xFF);
			}
			truncatedHash &= 0x7FFFFFFF;
			truncatedHash %= 1000 * 1000;
			return truncatedHash;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * @param lengthToken
	 * @return
	 */
	public static long getOneTimePassword(int lengthToken) {
		Base32 codec = new Base32();
		byte[] data = new byte[8];
		long value = (new Date().getTime()) / TimeUnit.SECONDS.toMillis(30);
		for (int i = 8; i-- > 0; value >>>= 8) {
			data[i] = (byte) value;
		}
		SecretKeySpec signKey = new SecretKeySpec(codec.decode(secretKey), "HmacSHA1");
		try {
			Mac mac = Mac.getInstance("HmacSHA1");
			mac.init(signKey);
			byte[] hash = mac.doFinal(data);
			int offset = hash[20 - 1] & 0xF;
			long truncatedHash = 0;
			for (int i = 0; i < 4; ++i) {
				truncatedHash <<= 8;
				truncatedHash |= (hash[offset + i] & 0xFF);
			}
			truncatedHash &= 0x7FFFFFFF;
			truncatedHash %= 1000 * 1000;
			return truncatedHash;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * @param secretKey
	 */
	public static void setSecretKey(String secretKey) {
		HorusTOTP.secretKey = secretKey;
	}

	/**
	 * @param secret
	 * @param code
	 * @param window
	 * @return
	 */
	private boolean checkCode(String secret, long code, int window) {
		Base32 codec = new Base32();
		byte[] decodedKey = codec.decode(secret);
		final long timeWindow = (new Date().getTime()) / TimeUnit.SECONDS.toMillis(30);
		for (int i = -((window - 1) / 2); i <= window / 2; ++i) {
			long hash = calculateCode(decodedKey, timeWindow + i);
			if (hash == code) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @param key
	 * @param tm
	 * @return
	 */
	private int calculateCode(byte[] key, long tm) {
		byte[] data = new byte[8];
		long value = tm;
		for (int i = 8; i-- > 0; value >>>= 8) {
			data[i] = (byte) value;
		}
		SecretKeySpec signKey = new SecretKeySpec(key, "HmacSHA1");
		try {
			Mac mac = Mac.getInstance("HmacSHA1");
			mac.init(signKey);
			byte[] hash = mac.doFinal(data);
			int offset = hash[20 - 1] & 0xF;
			long truncatedHash = 0;
			for (int i = 0; i < 4; ++i) {
				truncatedHash <<= 8;
				truncatedHash |= (hash[offset + i] & 0xFF);
			}
			truncatedHash &= 0x7FFFFFFF;
			truncatedHash %= 1000 * 1000;
			return (int) truncatedHash;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
}
