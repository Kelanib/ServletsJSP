package servlets;

import beans.beanLogin;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "login", urlPatterns = {"/login"})
public class login extends HttpServlet {

    private Cookie getCookie(Cookie[] cookies, String name) {
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals(name)) {
                    return c;
                }
            }
        }
        return null;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet login</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet login at " + request.getContextPath() + "</h1>");

            String msg = null;
            String welcome = null;
            String fatalError = null;
            String user = "";

            ServletContext application = this.getServletContext();

            if (request.getParameter("doIt") == null) {
                msg = "";
            } else {
                beanLogin bLogin
                        = (beanLogin) application.getAttribute("beanLogin");
                if (bLogin == null) {
                    bLogin = new beanLogin();
                    application.setAttribute("beanLogin", bLogin);
                }
                if (bLogin.checkLogin(request.getParameter("login"),
                        request.getParameter("password"))) {
                    welcome = request.getParameter("login");
                    Cookie cc = new Cookie("ok", welcome);
                    response.addCookie(cc);
                    
                    cc = new Cookie("try", "");
                    cc.setMaxAge(0);
                    response.addCookie(cc);
                } else {
                    user = request.getParameter("login");
                    msg = "Utilisateur/Mot de passe invalide !!!";
                    Cookie cc = getCookie(request.getCookies(), "try");
                    if (cc == null) {
                        cc = new Cookie("try", "*");
                    } else {
                        cc.setValue(cc.getValue() + "*");
                    }
                    cc.setMaxAge(45);
                    response.addCookie(cc);
                    if (cc.getValue().length() >= 3) {
                        fatalError = "Trop de tentatives !!";
                        msg = null;
                    }
                }
            }

            Cookie c = getCookie(request.getCookies(), "ok");
            if (c != null) {
                welcome = c.getValue();
                msg = null;
            }

            Cookie ccc = getCookie(request.getCookies(), "try");
            if (ccc != null) {
                if (ccc.getValue().length() >= 3) {
                    fatalError = "Trop de tentatives !!";
                    msg = null;
                }
            }

            if (request.getParameter("deconnect") != null) {
                Cookie cc = new Cookie("ok", "");
                cc.setMaxAge(0);
                response.addCookie(cc);
                msg = "";
                welcome = null;
            }

            if (msg != null) {
                out.println(""
                        + "<form action='login' method='post'>"
                        + "Utilisateur :"
                        + "<input type='text' name='login' value='" + user + "' />"
                        + "<br>"
                        + "Mot de passe :"
                        + "<input type='password' name='password' />"
                        + "<br>"
                        + "<input type='submit' name='doIt' value='Ok' />"
                        + "<br>"
                        + ""
                        + "</form>"
                        + msg
                        + "");
            }

            if (welcome != null) {
                out.println("Bienvenue " + welcome
                        + " <a href='login?deconnect'>Déconnexion</a>");
            }

            if (fatalError != null) {
                out.println(fatalError);
            }

            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
