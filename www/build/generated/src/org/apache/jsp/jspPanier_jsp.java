package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import classes.Item;

public final class jspPanier_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>JSP Page</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <h1>Hello Panier!</h1>\n");
      out.write("\n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "jspCatalogue.jsp", out, true);
      out.write("\n");
      out.write("        <hr>\n");
      out.write("\n");
      out.write("        ");
      beans.beanPanier beanPanier = null;
      synchronized (session) {
        beanPanier = (beans.beanPanier) _jspx_page_context.getAttribute("beanPanier", PageContext.SESSION_SCOPE);
        if (beanPanier == null){
          beanPanier = new beans.beanPanier();
          _jspx_page_context.setAttribute("beanPanier", beanPanier, PageContext.SESSION_SCOPE);
        }
      }
      out.write(" <!-- Mecanisme de gestion du panier -->\n");
      out.write("\n");
      out.write("        ");

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
        
      out.write("\n");
      out.write("\n");
      out.write("        ");

            if (beanPanier.isEmpty()) {
                out.println("Panier vide.");
            } else {
                for (Item i : beanPanier.list()) { 
      out.write("\n");
      out.write("\n");
      out.write("        ");
      out.print(i.getReference());
      out.print(i.getQuantite());
      out.write("\n");
      out.write("        <a href=\"jspPanier.jsp?add=");
      out.print(i.getReference());
      out.write("\">+</a>\n");
      out.write("        <a href=\"jspPanier.jsp?dec=");
      out.print(i.getReference());
      out.write("\">-</a>\n");
      out.write("        <a href=\"jspPanier.jsp?del=");
      out.print(i.getReference());
      out.write("\">X</a>\n");
      out.write("        <br>\n");
      out.write("        ");


                }
            }
        
      out.write("\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
