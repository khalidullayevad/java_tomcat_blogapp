package servlets;

import db.DBManager;
import db.Post;
import db.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/search")
public class SearchServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Student currentStudent = (Student) request.getSession().getAttribute("CURRENT_USER");
        if(currentStudent != null){
            String search_text = request.getParameter("search_text");
            System.out.println(search_text);
            ArrayList<Post> posts  = DBManager.getSearch(search_text, true);
            request.setAttribute("posts", posts);
            request.getRequestDispatcher("views/studentPage.jsp").forward(request, response);

        }

        else{
            response.sendRedirect("/");
        }
    }
}
