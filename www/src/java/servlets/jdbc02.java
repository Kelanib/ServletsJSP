package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "jdbc02", urlPatterns = {"/jdbc02"})
public class jdbc02 extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet jdbc02</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet jdbc02 at " + request.getContextPath() + "</h1>");

            if (request.getParameter("A2") == null) {
                out.println("<a href='jdbc01'>Sélection d'un pays</a>");
            } else {

                try {
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                } catch (ClassNotFoundException ex) {
                    out.println("Probleme de bases de données"
                            + "<!-- "
                            + "Oops:Driver:" + ex.getMessage()
                            + " -->");
                    return;
                }
                Connection connexion = null;
                try {
                    connexion = DriverManager.getConnection(
                            "jdbc:sqlserver://localhost:1433;"
                            + "databaseName=maBase;user=sa;password=sa");
                } catch (SQLException ex) {
                    out.println("Probleme de bases de données"
                            + "<!-- "
                            + "Oops:Connection:" + ex.getErrorCode() + ":" + ex.getMessage()
                            + " -->");
                    return;
                }

                try {
//                    String query = "SELECT * FROM ISO3166"
//                            + " WHERE A2='" + request.getParameter("A2") + "'";
////                    System.out.println(query);
//                    Statement stmt = connexion.createStatement();
//                    ResultSet rs = stmt.executeQuery(query);
                    
                    String query = "SELECT * FROM ISO3166 WHERE A2=?";
                    PreparedStatement pstmt= 
                            connexion.prepareStatement(query);
                    pstmt.setString(1, request.getParameter("A2"));
                    ResultSet rs= pstmt.executeQuery();
                    
                    if (rs.next()) {
                        out.println(rs.getString("Pays"));
                        out.println(rs.getString("A2"));
                        out.println(rs.getString("A3"));
                        out.println(rs.getString("Number"));
                        out.println("-----");
                    }

                    rs.close();
                    pstmt.close();
                } catch (SQLException ex) {
                    out.println("Probleme de bases de données"
                            + "<!-- "
                            + "Oops:SQL:" + ex.getErrorCode() + ":" + ex.getMessage()
                            + " -->");
                    return;
                }

                try {
                    connexion.close();
                } catch (SQLException ex) {
                    out.println("Probleme de bases de données"
                            + "<!-- "
                            + "Oops:Close:" + ex.getErrorCode() + ":" + ex.getMessage()
                            + " -->");
                    return;
                }
            }
            out.println("Done!");

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
