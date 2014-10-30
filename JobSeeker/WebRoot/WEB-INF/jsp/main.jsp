<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>JobSeeker</title>
<script type="text/javascript" src="javascript/jquery.js">
	
</script>
<link href="css/main.css" rel="stylesheet" type="text/css" media="screen" /> 
<script type="text/javascript" src="javascript/common.js"></script>
<script type="text/javascript" src="javascript/jquery.js"></script>
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
    <section class = "index-content">
    <img src="pic/JobBanner_meitu_2.png" class="item"/>
    <section class = "srchmenu">
    	
    	<form action="searchjobs.action">
            <input id = "query" name="query" class="searchbox" type = "text" size = "35" onmouseover="mouseOver(this)" onmouseout="mouseOut(this)" onblur="blurinput(this)" placeholder = "Job Titles, Keywords, or Company" />
            <input id = "location" name="location" class="searchbox" type = "text" size = "35" onmouseover="mouseOver(this)" onmouseout="mouseOut(this)" onblur="blurinput(this)" placeholder = "Location"/>
            <button type="submit" id = "search" >Search</button>
        </form>
    </section>
    </section>
    <footer>
          
    </footer>
</body>
</html>
