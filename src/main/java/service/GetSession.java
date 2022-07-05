package service;

import model.UserModel;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class GetSession {
    public String getName(HttpServletRequest request) {
        String name = null;
        try {
            HttpSession session = request.getSession();
            UserModel user = (UserModel) session.getAttribute("auth");
            name = user.getName();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return name;
    }

    public String getId(HttpServletRequest request) {
        String id = "";
        try {
            HttpSession session = request.getSession();
            UserModel user = (UserModel) session.getAttribute("auth");
            id = user.getId();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return id;
    }
}
