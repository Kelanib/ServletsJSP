<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%!
    private Cookie getCookie(Cookie[] cookies, String name) {

        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals(name))  {
                    return c;
                }
            }
        }

        return null;
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="top.jsp" %>

        <h1>Hello World!</h1>

        <%--
            out.println(new Date());
        --%>
        <!--
            Coucou
        -->
        <br>
        <%=new Date()%>
        <hr>
        <% for (int i = 1;i <= 6; i++) {%>
    <H<%=i%>><%=i%></H<%=i%>>        
        <% }%>

    <%@include file="bottom.html" %>

</body>
</html>









