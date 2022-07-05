
package enjoyTravel;

import java.io.IOException;
import java.io.PrintWriter;

import database.User;
import model.UserModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "login", urlPatterns = { "/login" })
public class Login extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request  servlet request 
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
        	HttpSession session = request.getSession();
        	if(session.getAttribute("auth") != null){
        		response.sendRedirect("/dashboard");
        	}
        	
            RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the
    // + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("logout") != null) {
            HttpSession session = request.getSession();
            session.removeAttribute("auth");
            response.sendRedirect("/");
        } else {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
    
            User users = new User();
            UserModel user = users.login(email, password);
    
            if (user.getLevel() != null && user.getLevel().equals("admin")) {
                HttpSession session = request.getSession();
                session.setAttribute("auth", user);
                response.sendRedirect("/dashboard");
            } else if(user.getLevel() != null) {
            	HttpSession session = request.getSession();
                session.setAttribute("auth", user);
            	response.sendRedirect("/");
            } else {
                RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
                request.setAttribute("error", "Credential is incorrect");
                rd.forward(request, response);
            }
        }
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
