<%-- 
    Document   : jspPanier
    Created on : 4 oct. 2017, 14:43:27
    Author     : cdi310
--%>

<%@page import="classes.Item"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello Panier!</h1>

        <jsp:include page="jspCatalogue.jsp" flush="true"/>
        <hr>

        <jsp:useBean class="beans.beanPanier" id="beanPanier" scope="session" /> <!-- Mecanisme de gestion du panier -->

        <%
            if (request.getParameter("add") != null) {
                beanPanier.add(request.getParameter("add"));
            }
            if (request.getParameter("dec") != null) {
                beanPanier.dec(request.getParameter("dec"));
            }
            if (request.getParameter("del") != null) {
                beanPanier.del(request.getParameter("del"));
            }
            if (request.getParameter("clear") != null) {
                beanPanier.clear();
            }
        %>

        <%
            if (beanPanier.isEmpty()) {
                out.println("Panier vide.");
            } else {
                for (Item i : beanPanier.list()) { %>

        <%=i.getReference()%><%=i.getQuantite()%>
        <a href="jspPanier.jsp?add=<%=i.getReference()%>">+</a>
        <a href="jspPanier.jsp?dec=<%=i.getReference()%>">-</a>
        <a href="jspPanier.jsp?del=<%=i.getReference()%>">X</a>
        <br>
        <%

                } %>
                 <a href="jspPanier.jsp?clear">Vider le panier</a>
          <%  }
        %>
        
    </body>
</html>
