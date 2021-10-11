package wrapper;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class EncryptWrapper extends HttpServletRequestWrapper {

	public EncryptWrapper(HttpServletRequest request) {
		super(request);
	}
	
	@Override
	public String getParameter(String key) {
		String value = "";
		
		if(key != null && (key.equals("userPwd") || key.equals("newPwd1") || key.equals("m_Password") || key.equals("m_Password2") || key.equals("mPwd1"))) {
			value = getSha512(super.getParameter(key));
		} else {
			value = super.getParameter(key);
		}
		return value;
	}

	// sha512 해쉬함수를 통한 처리
	public String getSha512(String userPwd) {
		String encPwd = null;
		
		try {
			// sha512 방식 암호화
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			
			byte[] bytes = userPwd.getBytes(Charset.forName("UTF-8"));
			
			md.update(bytes);
			
			encPwd = Base64.getEncoder().encodeToString(md.digest());
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		
		return encPwd;
	}
}

