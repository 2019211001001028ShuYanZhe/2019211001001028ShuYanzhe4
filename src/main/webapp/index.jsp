<%@include file="header.jsp"%>
<h1>Welcome to My Online Shop Home Page.</h1><br>
<form method="get" target="_blank" action="search">
    <!--<url-pattern>/search</url-pattern>-->
    <input type="text" name="txt" size=30/>
    <select name="search">
        <option value="baidu">Baidu</option>
        <option value="bing">Bing</option>
        <option value="google">Google</option>
    </select>
    <input type="submit" value="Search"/>
</form>
<%@include file="footer.jsp"%>