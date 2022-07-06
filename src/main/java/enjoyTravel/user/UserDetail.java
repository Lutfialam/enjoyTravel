
package enjoyTravel.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException; 
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.User;
import model.UserModel;
import service.GetSession;


@WebServlet(name = "UserDetail", urlPatterns = { "/UserDetail" })
public class UserDetail extends HttpServlet {
    User user = new User();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response, String id)
            throws ServletException, IOException {
    	new GetSession().authorization(request, response);
    	RequestDispatcher rd = request.getRequestDispatcher("/user_edit.jsp");

        HttpSession session = request.getSession();
        UserModel user = (UserModel) session.getAttribute("auth");
        
        request.setAttribute("isLoggedIn", false);
        request.setAttribute("isAdmin", false);
        
        if (user != null && user.getLevel().equals("admin")) {
            request.setAttribute("isLoggedIn", true);
            request.setAttribute("isAdmin", true);
        } else if (user != null) {
            request.setAttribute("isLoggedIn", true);
        }
        
        request.setAttribute("data", new User().getDetailData(id));
        rd.forward(request, response);
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
        // String id = request.getParameter("id");
        // processRequest(request, response, id);
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
        String id = request.getParameter("id").toString();
        String type = request.getParameter("type");
        
        if (type.equals("delete")) {
            user.destroy(id);
            response.sendRedirect("/UserServlet");
        } else {
            processRequest(request, response, id);
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
