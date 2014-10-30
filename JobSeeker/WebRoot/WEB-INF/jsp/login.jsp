<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<link type="text/css" rel="stylesheet" href="css/main.css" />
<title>Login</title>

<script type="text/javascript">
	function refresh() {
		var img = document.getElementById("img_validation_code");
		img.src = "validate_code.action?" + Math.random();
	}
</script>
</head>
<body>
<header>
        <nav>
            <ul>
                <li class = "logo"><a href="main_page.action" target="_top" rel="nofollow">JobSeeker</a></li>
                <li class = "jobstab"><a href="getalljobsbydate.action" target="_top" rel="nofollow">Jobs</a></li>
                <li class = "companiestab"><a href="getfeeds.action" target="_top" rel="nofollow">Feeds</li>
            </ul>
            <section class = "logintab"> 
                <span class="cart"><a href="getcartjobs.action" target="_top" rel="nofollow">Cart</a></span>  
                <a href="login_page.action" target="_top" rel="nofollow">Sign In</a>
                <a href="relogin.action" target="_top" rel="nofollow">Sign Out</a>
            </section> 
        </nav>    
    </header>
	<div style="margin:5%;">
		<div
			style="margin-top: 20px; margin-left: 20px; font-size: 20px; height: 50px;color: #666666;">
			User's login interface</div>
		If you didn't register yet, please click here: <a
			href="register_page.action">Register</a> <FONT color="red"><s:actionerror />
		</FONT>
		<s:form action="login">
			<s:textfield label="Username" cssClass="searchbox" name="username" />
			<s:password label="Password" name="passwordMD5" cssClass="searchbox" />
			<s:textfield label="Validation code" name="validationCode"
				cssClass="searchbox" />
			<s:submit value="Log In" cssClass="button" />
		</s:form>

		Validation code: <img id="img_validation_code"
			src="validate_code.action" /> <a href="#" onClick="refresh()">refresh</a>
	</div>

</body>
</html>
