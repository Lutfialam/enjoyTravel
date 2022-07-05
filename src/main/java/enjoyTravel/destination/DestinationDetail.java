package enjoyTravel.destination;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.Destination;
import model.DestinationModel;
import model.UserModel;
import service.FileStorage;

/**
 * Servlet implementation class DestinationDetail
 */
@WebServlet("/destination/detail")
@MultipartConfig(
fileSizeThreshold = 1024 * 1024,
  maxFileSize = 1024 * 1024 * 5, 
  maxRequestSize = 1024 * 1024 * 5 * 5)
public class DestinationDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DestinationDetail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/destination/edit.jsp");

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
        
        request.setAttribute("data", new Destination().getDetailData(id));
        rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");
        
        if (type.equals("delete")) {
            doDelete(request, response);
        } else if (type.equals("edit")) {
            doPut(request, response);
        } else {
            doGet(request, response);
        }
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
            FileStorage file = new FileStorage();
            DestinationModel destination = new DestinationModel();
            destination.setId(request.getParameter("id"));
            destination.setName(request.getParameter("name"));
            destination.setdetail(request.getParameter("detail"));
            destination.setSearch_key(request.getParameter("search"));
            
            if (request.getPart("photo") != null && request.getPart("photo").getSize() > 0) {
                destination.setPhoto(file.upload(request.getPart("photo")));
            }
            
            if (request.getPart("background") != null && request.getPart("background").getSize() > 0) {
                destination.setBackground(file.upload(request.getPart("background")));
            }
            
            new Destination().update(destination);
            response.sendRedirect("/destination");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            response.sendRedirect("/destination");
        }
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
            String id = request.getParameter("id");
            new Destination().destroy(id);
            response.sendRedirect("/destination");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
	}

}
