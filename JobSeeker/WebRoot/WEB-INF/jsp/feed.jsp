<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'cart.jsp' starting page</title>
    
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
    	<section class="left">
            <section class = "subtotal">
                <h1 class="title">Total: <s:property value='feeds.size'/> feeds</h1>
                <hr/>
               <form action=createfeed.action class="createfeed">
	               TITLE:<input name="createtitle" class="searchbox" type="text" class="applyButton" onmouseover="mouseOver(this)" onmouseout="mouseOut(this)" onblur="blurinput(this)"/>
	               LOCATION:<input name="createlocation" class="searchbox" type="text" class="applyButton" onmouseover="mouseOver(this)" onmouseout="mouseOut(this)" onblur="blurinput(this)"/>
	               <input name="create" value="Craete" class="button" type="submit" />
	           </form>
            </section>
        </section> 
        <section class = "right">
        	<section class = "item">
               <table id="tableid">
               <tr>
                   <td width="30%">
                      Alert Name

                   </td>
                   <td width="20%">
                      Date

                   </td>
                   <td width="40%">
                      Operation
                   </td>
               </tr>
               <s:iterator id="element" value="feeds" status="status" >
               <tr>
	                <td><div name="title"><s:property value='#element.title' /> in <s:property value='#element.location' /></div></td>
	                <td><div name="date"><p><s:property value='#element.date' /></p></div></td>
	                <td><div name="operation"><input name="Delete" class="button" style="margin-top:0px;" value="Delete" type="button" onclick="DeleteFeed('<s:property value='#element.feedid' />');"></div></td>
				</tr>
				</s:iterator>
               </table>
			</section>
        </section>
    </section>
  </body>
</html>
