<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello Form!</h1>

        <jsp:useBean class="beans.form" scope="request" id="beanForm" />
        <%--         
        <jsp:setProperty name="beanForm" property="nom" 
            value="<%=request.getParameter("Nom")%>" />        
        <jsp:setProperty name="beanForm" property="prenom" 
            value="<%=request.getParameter("Prenom")%>" />        
        --%>

        <jsp:setProperty name="beanForm" property="*" />

        Nom : <jsp:getProperty name="beanForm" property="nom" /><br>
        Prenom : <jsp:getProperty name="beanForm" property="prenom" /><br>

    </body>
</html>












