package com.JobSeeker.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.*;
import com.opensymphony.xwork2.*;
import java.util.*;
import common.CookieUtil;
import common.Constants;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

public class AuthorizationInterceptor extends AbstractInterceptor {
	private String ignoreActions;

	public String getIgnoreActios() {
		return ignoreActions;
	}

	public void setIgnoreActions(String ignoreActions) {
		this.ignoreActions = ignoreActions;
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {

		ActionContext ctx = invocation.getInvocationContext();
		HttpServletRequest request = ServletActionContext.getRequest();
		String username = CookieUtil.getCookie(request, Constants.SESSION_USER);
		String status = CookieUtil
				.getCookie(request, Constants.OFFICIAL_STATUS);
		// Get page and store it into session
		Map<String, Object> session = ctx.getSession();
		String path = request.getRequestURI();
		boolean judge = false;
		String currentAction = invocation.getProxy().getActionName();
		String[] actions = ignoreActions.split(",");

		for (String action : actions) {
			if (currentAction.matches(action.trim())) {
				judge = true;
				break;
			}
		}
		if (!path.equals(Constants.ADDCOOKIE_PATH)
				&& !path.equals(Constants.LOGINPATH)
				&& !path.equals(Constants.LOGIN_PATH)
				&& !path.equals(Constants.REGISTER_PATH)
				&& !path.equals(Constants.REGISTERPATH)
				&& !path.equals(Constants.VALIDATION_PATH))
			session.put(Constants.BROSER_PRE_PAGE, path);
		System.out.println(session.get(Constants.BROSER_PRE_PAGE));
		if (username == null) {
			if (path.equals(Constants.ADDCOOKIE_PATH))
				return invocation.invoke();
			else
				return "cookie";
		} else {
			String user = (String) session.get(Constants.SESSION_USER);
			System.out.println(judge + " " + username);
			if (judge == true && user == null) {
				return Action.LOGIN;
			} else {
				return invocation.invoke();
			}
		}
	}
}
