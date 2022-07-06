package enjoyTravel.destination;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import database.Destination;
import model.DestinationModel;
import service.FileStorage;
import service.GetSession;

/**
 * Servlet implementation class DestinationAdd
 */
@WebServlet(name = "DestinationCreate", urlPatterns = { "/destination/create" })
@MultipartConfig(
fileSizeThreshold = 1024 * 1024,
  maxFileSize = 1024 * 1024 * 5, 
  maxRequestSize = 1024 * 1024 * 5 * 5)
public class DestinationAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DestinationAdd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		new GetSession().authorization(request, response);
		RequestDispatcher rd = request.getRequestDispatcher("/destination/create.jsp");

		request.setAttribute("name", new GetSession().getName(request));
        rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
        String detail = request.getParameter("detail");
        String search = request.getParameter("search");
        Part photoPart = request.getPart("photo");
        Part backgroundPart = request.getPart("background");

        try {
        	database.Destination ds = new Destination();
            FileStorage file = new FileStorage();
            String photoName = file.upload(photoPart);
            String backgroundName = file.upload(backgroundPart);

            ds.insert(new DestinationModel(name, detail, search, photoName, backgroundName));
            response.sendRedirect("/destination");
        } catch (Exception e) {
            System.out.println("Error on DestinationServlet");
            System.out.println(e.getMessage());
        }
	}

}
