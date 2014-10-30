<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'job.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="css/main.css" rel="stylesheet" type="text/css" media="screen" /> 
	<script type="text/javascript" src="javascript/common.js"></script>
	<script type="text/javascript" src="javascript/jquery.js"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

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
    <section class = "srchtop">
        <section class = "srchmenu">
        <form action="searchjobs.action">
            <input id = "query" name="query" class="searchbox" type = "text" size = "35" onmouseover="mouseOver(this)" onmouseout="mouseOut(this)" onblur="blurinput(this)" placeholder = "Job Titles, Keywords, or Company" />
            <input id = "location" name="location" class="searchbox" type = "text" size = "35" onmouseover="mouseOver(this)" onmouseout="mouseOut(this)" onblur="blurinput(this)" placeholder = "Location"/>
            <button type="submit" id = "search" >Search</button>
        </form>
        </section>
    </section>
        <section class = "content">
            <h1 class="grayWords">Total <s:property value='jobs.size'/> jobs</h1>
            <hr/>
            <s:iterator id="element" value="jobs" status="status" >
           	 	<section class = "item">
	            <div name="jobs" class = "jobs">
	                <div name="title" class="title"><h1><s:property value='#element.title' /></h1></div>
	                <div name="company" class="company"><p><s:property value='#element.company' />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:property value='#element.location' /></p></div>
	                <div name="summary" class="summary">
	                	<s:property value='#element.summary' />
	                </div>
	            </div>
	            <input name="apply" class="button" value="Apply" type="button" onclick="saveThisJobToCart('<s:property value='#element.jobid'/>')"/>
	            </section>
			   <s:if test="#status.count < jobs.size"></s:if>   
			</s:iterator>
        </section> 
    <footer>
    </footer>
  </body>
</html>
