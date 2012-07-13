<%@ page contentType="text/html,charset=UTF-8" %>
<%@ page session="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>Home</title>
</head>
<body>
<div align="center">
				<table width="559" border="0" cellspacing="2" cellpadding="0">
                    <tr height="45">
						<td colspan="3" valign="top" height="45">
                        <!-- InstanceBeginEditable name="Region" -->
                        	<p>Legacy BBS Sample</p>
                        <!-- InstanceEndEditable -->
                        </td>
					</tr>
					<tr height="25">
						<td colspan="3" valign="top" height="25">
                        <!-- InstanceBeginEditable name="Region2" -->
                        	<form:form modelAttribute="entry" method="post">
                        		<table width="100%" border="0">
                        		  <tr>
                        		    <td width="9%">Submitter</td>
                        		    <td width="91%"><form:input path="name" maxlength="32" cssStyle="width:150px;" /><br />
                        		    <font color="red"><form:errors path="name" /></font></td>
                      		    </tr>
                        		  <tr>
                        		    <td>Mail</td>
                        		    <td><form:input path="email" maxlength="64" cssStyle="width:150px;" /></td>
                      		    </tr>
                        		  <tr>
                        		    <td>Title</td>
                        		    <td><form:input path="title" maxlength="64" cssStyle="width:150px;" /></td>
                      		    </tr>
                        		  <tr>
                        		    <td>Message</td>
                        		    <td><form:textarea path="message" cssStyle="width:300px;height:100px;" /><br />
                        		    <font color="red"><form:errors path="message" /></font></td>
                      		    </tr>
                      		    <tr>
                        		    <td></td>
                        		    <td><input type="submit" name="submit" value="Submit" /></td>
                      		    </tr>
                      		  </table>
                           </form:form>
                           	<c:forEach items="${entryList}" var="entry">
                        		<hr />
                        		<c:if test="${entry.email != ''}" var="mailFlg" />
                        		<p>${entry.title}  Submitter:
                        		<c:if test="${mailFlg}"><a href="mailto:${entry.email}"></c:if>
                        		${entry.name}<c:if test="${mailFlg}"></a></c:if>
                        		  Date:${entry.date} </p>
                        		<p>${entry.message}
                      		  </p>
                      		</c:forEach>
                      		<hr />
                      		<c:forEach begin="1" end="${pages.totalPage}" varStatus="i">
                      			<c:if test="${pages.currentPage != i.index}" var="indexFlg" />
                      				<c:if test="${indexFlg}"><a href="?page=${i.index}"></c:if>
                      				${i.index}<c:if test="${indexFlg}"></a></c:if>  
                      		</c:forEach>
                      		<c:if test="${pages.currentPage != '1'}" var="prevFlg" />
                      		<c:if test="${pages.currentPage != pages.totalPage}" var="nextFlg" />
                      			<c:if test="${prevFlg}"><a href="?page=${pages.currentPage - 1}"></c:if>
                      			Previous<c:if test="${prevFlg}"></a></c:if>  
                      			<c:if test="${nextFlg}"><a href="?page=${pages.currentPage + 1}"></c:if>
                      			Next<c:if test="${nextFlg}"></a></c:if>
                        <!-- InstanceEndEditable -->
                        </td>
                    </tr>    
                </table>
             </div>
</body>
</html>
