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

/**
 * Servlet implementation class UserEdit
 */
@WebServlet("/user/edit")
public class UserEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserEdit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		new GetSession().authorization(request, response);
		RequestDispatcher rd = request.getRequestDispatcher("/user_edit.jsp");

        HttpSession session = request.getSession();
		UserModel user = (UserModel) session.getAttribute("auth");
		String id = request.getParameter("id");
        
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String level = request.getParameter("level");

        try {
			new User().update(new UserModel(id, name, email, password, level));
            response.sendRedirect("/UserServlet");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            response.sendRedirect("/UserServlet");
        }
	}

}
