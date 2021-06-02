package servlets;

import db.DBManager;
import db.Post;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/adminSearchPost")
public class AdminSearchPostServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String teacher = (String) request.getSession().getAttribute("TEACHER");
        if(teacher != null) {
            ArrayList<Post> posts;
            String title = request.getParameter("title");

            String category = request.getParameter("category");
            posts = DBManager.adminPostFilter(title,category);

            request.setAttribute("posts", posts);
            request.getRequestDispatcher("views/admin.jsp").forward(request, response);
        }

       else{
        response.sendRedirect("/");
    }
    }
}
