package enjoyTravel.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.User;
import model.UserModel;
import service.GetSession;

/**
 * Servlet implementation class UserAdd
 */
@WebServlet("/UserAdd")
public class UserAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserAdd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		new GetSession().authorization(request, response);
		RequestDispatcher rd = request.getRequestDispatcher("/user_add.jsp");

        request.setAttribute("name", new GetSession().getName(request));
        rd.forward(request, response);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String level = request.getParameter("level");

        try {
            new User().insert(new UserModel(name, email, password, level));
            response.sendRedirect("/UserServlet");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            response.sendRedirect("/UserServlet");
        }
	}

}
