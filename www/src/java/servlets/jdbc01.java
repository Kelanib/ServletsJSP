package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "jdbc01", urlPatterns = {"/jdbc01"})
public class jdbc01 extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet jdbc01</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet jdbc01 at " + request.getContextPath() + "</h1>");

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

            String query = "SELECT * FROM ISO3166 ORDER BY Pays;";
            try {
                Statement stmt = connexion.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                out.println("<form action='jdbc02' method='post'>");
                out.println("<SELECT NAME='A2'>");

                while (rs.next()) {
                    out.println(
                            "<option value='"+rs.getString("A2")+"'>"
                            + rs.getString("Pays")
                            + "</option>");
//                    out.println("<a href='jdbc02?A2="
//                            + rs.getString("A2") + "'>"
//                            + rs.getString("Pays")
//                            + "</a>"
//                            + "<br>");

//                    System.out.println( rs.getString("Pays"));
//                    System.out.println( rs.getString("A2"));
//                    System.out.println( rs.getString("A3"));
//                    System.out.println( rs.getString("Number"));
//                    System.out.println("-----");
                }

                out.println("</SELECT>");
                out.println("<input type='submit' name='doIt' value='Ok' >");
                out.println("</form>");
                
                rs.close();
                stmt.close();
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
