package common;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import com.JobSeeker.model.Users;
import common.Constants;

public class CookieUtil {

	public List<Cookie> addCookie(Users userBean) {
		Cookie cookieU = new Cookie(Constants.SESSION_USER, userBean.getUsername());
		cookieU.setMaxAge(Constants.BROWSER_COOKIE_MAX_AGE);
		cookieU.setPath("/");
		List<Cookie> list = new ArrayList<Cookie>();
		list.add(cookieU);
		return list;
	}

	public static String getCookie(HttpServletRequest request, String key) {
		Cookie[] cookies = request.getCookies();
		// System.out.println("cookies: " + cookies);
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				// System.out.println("cookieName: " + cookie.getName());
				if (key.equals(cookie.getName())) {
					String value = cookie.getValue();
					// System.out.println("cookieValue: " + cookie.getValue());
					return value;
				}
			}
		}
		return null;
	}

}
