package enjoyTravel;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.UserModel;

/**
 * Servlet implementation class Contact
 */
@WebServlet("/contact")
public class Contact extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Contact() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher rd = request.getRequestDispatcher("/contact.jsp");
		
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
        
        rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
