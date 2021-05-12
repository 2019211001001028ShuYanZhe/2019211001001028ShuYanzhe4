<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/4/13
  Time: 22:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp"%>
<h1> User Info</h1>
<table>
    <tr>
        <td>Username:</td><td><%=request.getAttribute("username")%></td>
        <td>Password:</td><td><%=request.getAttribute("password")%></td>
        <td>Email:</td><td><%=request.getAttribute("email")%></td>
        <td>Gender:</td><td><%=request.getAttribute("gender")%></td>
        <td>Birthdate:</td><td><%=request.getAttribute("birthdate")%></td>
    </tr>
</table>


<%@include file="footer.jsp"%>