<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>

<html>
<head>
<title>Register</title>
<link type="text/css" rel="stylesheet" href="css/main.css" />
<s:head />
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
		User's register interface</div>
	Click here to log in:
	<a href="login_page.action">Login</a>
	<FONT color="red"><s:actionerror /> </FONT>
	<s:form action="register">
		<s:textfield label="Username" cssClass="searchbox" name="username"
			required="true" value="" />
		<s:password label="Password" name="passwordMD5" cssClass="searchbox"
			required="true" />
		<s:password label="Reinput Password:" name="repassword"
			cssClass="searchbox" required="true" />
		<s:textfield label="Email" name="email" cssClass="searchbox" value="" />
		<s:textfield label="Valication Code" name="validationCode"
			cssClass="searchbox" value="" required="true" />
		<s:submit value="Register" cssClass="button" />
	</s:form>
	User's validation code:
	<img id="img_validation_code" src="validate_code.action" />
	<a href="#" onClick="refresh()">Reresh</a>
</div>
</body>
</html>
