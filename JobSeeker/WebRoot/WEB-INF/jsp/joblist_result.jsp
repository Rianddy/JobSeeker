<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

{"jobs": 
[

<s:iterator id="element" value="jobs" status="status" >
   {
      "jobid":"<s:property value='#element.jobid' />", 
      "title":"<s:property value='#element.title' />",
      "company":"<s:property value='#element.company' />",
      "location":"<s:property value='#element.location' />",
      "summary":"<s:property value='#element.summary' />"
      "description":"<s:property value='#element.description' />"
   }
   <s:if test="#status.count < jobs.size">,</s:if>   

</s:iterator>
] }
