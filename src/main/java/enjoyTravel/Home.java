
package enjoyTravel;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.Destination;
import model.DestinationModel;
import model.UserModel;

import javax.servlet.RequestDispatcher;

/**
 *
 * @author lutfi 
 */
@WebServlet(name = "index", urlPatterns = { "/" })
public class Home extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
            
            List<DestinationModel> destination_data = new Destination().getHighlightData();
            DestinationModel destinationFavorite = new DestinationModel();
            
            HttpSession session = request.getSession();
            UserModel user = (UserModel) session.getAttribute("auth");

            for (DestinationModel destination : destination_data) {
                System.out.println(destination.isFavorite());
                if (destination.isFavorite()) {
                    destinationFavorite = destination;
                    break;
                }
            }

            request.setAttribute("isAdmin", false);
            request.setAttribute("isLoggedIn", false);
            request.setAttribute("data", destination_data);
            request.setAttribute("favorite", destination_data.get(0));

            if (destinationFavorite != null) {
                request.setAttribute("favorite", destinationFavorite);
            }
            
            if (user != null && user.getLevel().equals("admin")) {
                request.setAttribute("isLoggedIn", true);
                request.setAttribute("isAdmin", true);
            } else if (user != null) {
                request.setAttribute("isLoggedIn", true);
            }
            
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        // processRequest(request, response);
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
