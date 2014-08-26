package business;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.horusframework.business.HorusTOTP;
import org.json.simple.JSONObject;

public class TotpBO {
	private String SecretKey = "NBXXE5LTMZZGC3LFO5XXE2Y";

	/**
	 * @return http://www.google.com/chart?chs=200x200&chld=M|0&cht=qr&chl=otpauth://totp/isupply.embraer.com.br?secret= + SecretKey
	 */
	public FileInputStream getProgramming(String folder) {
		try {
			return new FileInputStream(folder + "\\TotpQRCode.png");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public JSONObject valiCode(long temporaryCode) {
		JSONObject json = new JSONObject();
		HorusTOTP.setSecretKey(SecretKey);
		json.put("action", HorusTOTP.getValidation(temporaryCode, 6));
		return json;
	}
}
