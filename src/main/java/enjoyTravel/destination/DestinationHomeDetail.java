package enjoyTravel.destination;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.Destination;
import model.UserModel;

/**
 * Servlet implementation class DestinationHomeDetail
 */
@WebServlet("/destination/home/detail")
public class DestinationHomeDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DestinationHomeDetail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
        RequestDispatcher rd = request.getRequestDispatcher("/destination/home_detail.jsp");

        HttpSession session = request.getSession();
        UserModel user = (UserModel) session.getAttribute("auth");
        
        request.setAttribute("isLoggedIn", false);
        request.setAttribute("isAdmin", false);
        
        if (user != null && user.getLevel().equals("admin")) {
            request.setAttribute("isLoggedIn", true);
            request.setAttribute("isAdmin", true);
        } else if(user != null) {
            request.setAttribute("isLoggedIn", true);
        }

        request.setAttribute("data", new Destination().getDetailData(id));
        rd.forward(request, response);
	}

}
