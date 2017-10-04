package servlets;

import beans.beans01;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ServletBean", urlPatterns = {"/ServletBean"})
public class ServletBean extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServletBean</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServletBean at " + request.getContextPath() + "</h1>");
            
            HttpSession session= request.getSession();
            beans.beans01 beanSession= 
                    (beans.beans01) session.getAttribute("beanSession");
            if( beanSession==null) {
                beanSession= new beans01();
                session.setAttribute("beanSession", beanSession);
            }
            
            out.println("session : "+ beanSession.getDate() );
            out.println("<br>");
            
            ServletContext application= this.getServletContext();
            beans01 applicationBean= 
                    (beans01) application.getAttribute("beanApplication");
            if( applicationBean==null) {
                applicationBean= new beans01();
                application.setAttribute("beanApplication", applicationBean);
            }
            
            out.println( "application : "+ applicationBean.getDate());
            
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
