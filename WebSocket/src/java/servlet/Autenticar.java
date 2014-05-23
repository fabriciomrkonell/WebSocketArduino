package servlet;

import conexao.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Autenticar", urlPatterns = {"/Autenticar"}, initParams = {
    @WebInitParam(name = "Nome", value = "Valor")})
public class Autenticar extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        WebUtil util = new WebUtil();
        response.setContentType("text/html;charset=UTF-8");
        String theLogin = util.readParameter(request, "email", "");
        String thePasswd = util.readParameter(request, "senha", "");                
        AutenticarService service = new AutenticarService();
        User user = service.authenticate(theLogin, thePasswd);
               
        if (user != null) {
            HttpSession session = request.getSession(true);
            session.setAttribute("id", String.valueOf(user.getId()));
            session.setAttribute("name", user.getNome());
            response.sendRedirect("menu.jsp");            
        } else {
            response.setContentType("text/html;charset=UTF-8");
            response.sendRedirect("index.jsp");
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
