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

@WebServlet(value = "/filter")
public class FilterPostServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Student currentStudent = (Student) request.getSession().getAttribute("CURRENT_USER");
        if (currentStudent != null) {
            ArrayList<Post> posts = new ArrayList<>();
            if (request.getParameter("romance") != null) {
                posts =DBManager.getFilterPost(true, "Romance");

            }
            else if(request.getParameter("humor") != null){
                posts =DBManager.getFilterPost(true, "Humor");
            }
            else if(request.getParameter("mix") != null){
                posts =DBManager.getFilterPost(true, "Mix");
            }
            else if(request.getParameter("drama") != null){
                posts =DBManager.getFilterPost(true, "Drama");
            }
            else if(request.getParameter("essay") != null){
                posts =DBManager.getFilterPost(true, "Essay");
            }
            else if(request.getParameter("article") != null){
                posts =DBManager.getFilterPost(true, "Article");
            }
            else if(request.getParameter("fantasy") != null){
                posts =DBManager.getFilterPost(true, "Fantasy");
            }
            request.setAttribute("posts", posts);
            request.getRequestDispatcher("views/studentPage.jsp").forward(request, response);
        }
        else{
            response.sendRedirect("/signIn");
        }

    }
}
